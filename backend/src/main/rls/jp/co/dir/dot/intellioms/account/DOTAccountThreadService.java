/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : スレッドごとアカウントレンジを制御するサービスクラス(DOTAccountThreadService.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/02/07 齋藤　栄三(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.account;

import com.fitechlabs.fin.intellioms.util.Startable;

/**
 * スレッドごとアカウントレンジを制御するサービス
 * 
 * @author Eizo Saito (FLJ)
 * @version 1.0
 */
public interface DOTAccountThreadService extends Startable
{
    
    /**
     * インターバルを設定する。
     * 
     * @param インターバル
     */
    public void setInterval(long l_interval);
    
    /**
     * スレッドごとアカウントレンジファクトリを設定する。
     * 
     * @param スレッドごとアカウントレンジファクトリ
     */
    public void setFactory(DOTAccountThreadFactory l_factory);
    
    /**
     * スタート済か判定する。
     * 
     */
    public boolean isStarted();

}