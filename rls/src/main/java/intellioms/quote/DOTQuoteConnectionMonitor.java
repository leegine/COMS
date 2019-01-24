/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3QuoteConnectionMonitorクラス(DOTQuoteConnectionMonitor.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/03/15 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.quote;

import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteDataSource;

/**
 * (接続状態モニター)<BR>
 * <BR>
 * 時価サーバとの接続状態を監視する。<BR>
 * 
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public interface DOTQuoteConnectionMonitor
{

    /**
     * (接続待機)<BR>
     * <BR>
     * 指定した時価データソースと時価サーバとの接続が確立するまで待機する。
     * <BR>
     * 指定したタイムアウト時間を経過しても時価サーバとの接続が確立しない場合、
     * 一定時間待機後、ルールエンジンを停止する。<BR>
     * 
     * @param l_dataSource 監視対象となる時価データソース
     * @return 時価サーバとの接続が確立した場合は<code>true</code>、
     *         それ以外の場合は<code>false</code>を返す。
     */
    public boolean waitForConnectionToBeEstablished(DOTQuoteDataSource l_dataSource);
    
}