/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3RecoveryStartupServiceImplクラス(DOTRecoveryStartupServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/03/07 堀野　和美(FLJ) 新規作成
 */

package jp.co.dir.dot.intellioms.recovery.adaptor.impl;

import com.fitechlabs.fin.intellioms.util.InitializationException;
import com.fitechlabs.fin.intellioms.util.Log;

import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteDataSource;
import jp.co.dir.dot.intellioms.recovery.DOTRecoveryService;
import jp.co.dir.dot.intellioms.recovery.adaptor.DOTRecoveryStartupService;


/**
 *  起動時障害復旧サービスの実装クラス。
 * 
 * @author kazumi HORINO (FLJ)
 * @version 1.0
 */
public class DOTRecoveryStartupServiceImpl implements DOTRecoveryStartupService
{
    /**
     * ロガー。
     */
    private static final Log log = Log.getLogger(DOTRecoveryStartupServiceImpl.class);
    
    private static final boolean DBG = log.isDebug();    
    
    /**
     * 障害復旧サービス
     */
    private DOTRecoveryService recoveryService;

    /**
     * 時価データソース
     */
    private DOTQuoteDataSource quoteDataSource;
    
    /**
     * recoveryService を設定。
     * @param recoveryService　障害復旧サービス
     */
    public void setRecoveryService(DOTRecoveryService recoveryService)
    {
        this.recoveryService = recoveryService;
    }
    
    /**
     * quoteDataSource を設定する。
     * @param quoteDataSource 時価データソース
     */
    public void setQuoteDataSource(DOTQuoteDataSource quoteDataSource) 
    {
        this.quoteDataSource = quoteDataSource;
    }            
    
    /**
     * @see com.fitechlabs.fin.intellioms.util.Startable#start()
     */
    public void start() throws InitializationException 
    {
        //再起動時リカバリースレッド開始
        recoveryService.restoreQuoteCashStoreAll(quoteDataSource);
    }
    
    /**
     * @see com.fitechlabs.fin.intellioms.util.Startable#stop()
     */
    public void stop() 
    {
        //リカバリースレッドを停止状態にする。
        recoveryService.cancel();        
    }
    
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer(getClass().getName());
        l_sb.append("[recoveryService=" + this.recoveryService);
        l_sb.append(",quoteDataSource=" + this.quoteDataSource);
        l_sb.append("]");
        return l_sb.toString();
        
    }

    
}
