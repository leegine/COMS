/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3RecoveryManagerクラス(WEB3RecoveryManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/29 堀野　和美(FLJ) 新規作成
 */

package jp.co.dir.dot.intellioms.recovery;

import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteDataSource;

/**
 * 障害復旧サービス
 * 
 * @author kazumi HORINO(FLJ)
 * @version 1.0
 */
public interface DOTRecoveryService 
{    
    /**
     * 時価情報管理テーブルを
     * 当日時価取得開始時点から最新の状態に復旧する。
     */
    public void restoreQuoteCashStoreAll(DOTQuoteDataSource l_dataSource);
    
    /**
     * 時価情報管理テーブルに
     * 時価サービス切断時間帯の時価情報を復旧する。
     */
    public void restoreQuoteCashStoreDuringLostTime(DOTQuoteDataSource l_dataSource); 
    
    /**
     * 時価リカバリー稼動中かどうかを返却する。
     * @return boolean
     */
    public boolean isRecoveryQuoteRunning();
    
    /**
     * リカバリー処理をすべてキャンセルする。
     *
     */
    public void cancel();
    
}
