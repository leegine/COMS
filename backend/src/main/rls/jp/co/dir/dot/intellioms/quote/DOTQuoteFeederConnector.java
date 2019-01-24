/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3QuoteFeederConnectorクラス(DOTQuoteFeederConnector.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/10 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.quote;


import com.fitechlabs.fin.intellioms.util.Startable;

import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteStatusManager;


/**
 * (時価サーバ接続サービス)<BR>
 * <BR>
 * ルールエンジンの起動時に時価サーバとの接続を開始・停止するために
 * Startableインターフェースを拡張したインターフェース。<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public interface DOTQuoteFeederConnector extends Startable
{
    
    /**
     * (register時価アダプタ)<BR>
     * <BR>
     * 時価アダプタを登録する。<BR>
     * 
     * @param l_feederAdaptor 時価アダプタ
     */
    public void registerQuoteFeederAdaptor(DOTQuoteFeederAdaptor l_feederAdaptor);
    
    /**
     * (register接続状態マネージャ)<BR>
     * <BR>
     * 接続状態マネージャを登録する。<BR>
     * 
     * @param l_statusManager 接続状態マネージャ
     */
    public void registerQuoteStatusManager(DOTQuoteStatusManager l_statusManager);

}
