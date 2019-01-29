/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : スレッドごとアカウントレンジを制御するマネージャークラス(DOTAccountThreadManager.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/02/07 齋藤　栄三(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.account;

import com.fitechlabs.fin.intellioms.rulesys.BizDateProvider;
import com.fitechlabs.fin.intellioms.rulesys.OmsRuleEngine;

import jp.co.dir.dot.intellioms.tx.DOTTxConnPoolAdaptor;


/**
 * スレッドごとアカウントレンジを制御するマネージャー
 * 
 * @author Eizo Saito (FLJ)
 * @version 1.0
 */
public interface DOTAccountThreadManager
{

    /**
     * タスクタイマーインターバルを設定する。
     * 
     * @param タスクタイマーインターバル
     */
    public void setTaskTimerInterval(long l_taskTimerInterval);

    /**
     * デフォルトスレッドサイズを設定する。
     * 
     * @param デフォルトスレッドサイズ
     */
    public void setDefaultThreadSize(long l_defaultThreadSize);

    /**
     * 最大スレッドサイズを設定する。
     * 
     * @param 最大スレッドサイズ
     */
    public void setMaxThreadSize(long l_maxThreadSize);

    /**
     * ルールエンジンを設定する。
     * 
     * @param ルールエンジン
     */
    public void setOmsRuleEngine(OmsRuleEngine l_ruleEngine);
    
    /**
     * スレッドごとアカウントレンジデータベースアダプターを設定する。
     * 
     * @param スレッドごとアカウントレンジデータベースアダプター
     */
    public void setAdaptor(DOTAccountThreadDbAdaptor l_adaptor);
    
    /**
     * bizDateプロバイダーを設定する。
     * 
     * @param bizDateプロバイダー
     */
    public void setBizDateProvider(BizDateProvider l_bizDateProvider);
    
    /**
     * スレッドごとアカウントレンジファクトリを設定する。
     * 
     * @param スレッドごとアカウントレンジファクトリ
     */
    public void setFactory(DOTAccountThreadFactory l_factory);
    
    /**
     * スレッドごとアカウントレンジマネージャーを終了する。
     */
    public void safeStop();

    /**
     * コネクションプールアダプターを設定する。
     * 
     * @param コネクションプールアダプター
     */
    public void setTxAdaptor(DOTTxConnPoolAdaptor txAdaptor);

    /**
     * スレッドごとアカウントレンジを制御するサービスを設定する。
     * 
     * @param スレッドごとアカウントレンジを制御するサービス
     */
    public void setAccountThreadService(DOTAccountThreadService l_accountThreadService);

    /**
     * すべてのTimerTaskが停止処理を必要としているか判定する。
     */
    public boolean isAllTimerNeedStopping();
}