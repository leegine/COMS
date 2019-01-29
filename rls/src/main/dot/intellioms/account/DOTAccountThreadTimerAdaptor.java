/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 実作業をするタイマーアダプター(DOTAccountThreadTimerAdaptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/02/07 齋藤　栄三(FLJ) 新規作成
*/
package jp.co.dir.dot.intellioms.account;

/**
 * 実作業をするタイマーアダプター
 */
public interface DOTAccountThreadTimerAdaptor
{
    
    /**
     * タイマーを開始する
     */
    public void start();

    /**
     * タイマーを停止する
     */
    public void stop();

    /**
     * タイマー停止処理が必要か判定する
     */
    public boolean isNeedStopping();
}
