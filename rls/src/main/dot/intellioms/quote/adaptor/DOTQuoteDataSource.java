/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3QuoteDataSourceクラス(DOTQuoteDataSource.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/23 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.quote.adaptor;

import java.sql.Timestamp;

import jp.co.dir.dot.intellioms.enums.QuoteStatus;



/**
 * (時価データソース)<BR>
 * <BR>
 * 時価サーバに接続し時価情報を受信する。<BR>
 * 
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public interface DOTQuoteDataSource
{
    
    /**
     * (register時価イベントハンドラ)<BR>
     * <BR>
     * 時価イベントハンドラを登録する。<BR>
     * 
     * @param handler 時価イベントハンドラ
     */
    public void registerHandler(DOTQuoteEventHandler handler);
    
    /**
     * (start)<BR>
     * <BR>
     * 時価サーバへの接続を開始する。<BR>
     * 
     */
    public void start();
    
    /**
     * (stop)<BR>
     * <BR>
     * 時価サーバへの接続を停止する。<BR>
     * 
     */
    public void stop();
    
    /**
     * (get接続ステータス)<BR>
     * <BR>
     * 時価サーバとの接続ステータスを取得する。<BR>
     * 
     * @return 接続ステータス
     */
    public QuoteStatus getStatus();
    
    /**
     * (get接続確立時間)<BR>
     * <BR>
     * 時価サーバとの接続が確立した時間を返す。<BR>
     * <BR>
     * システムを起動してから、時価サーバとの接続が1回も確立して
     * 以内場合は<code>null</code>を返す。<BR>
     * 2回以上、時価サーバとの接続－切断を行った場合、このメソッド
     * は最後に接続された時間を返す。<BR>
     * 
     * @return 接続確立時間
     */
    public Timestamp getLastConnectedTime();
    
    /**
     * (get接続切断時間)<BR>
     * <BR>
     * 時価サーバとの接続が切断された時間を返す。<BR>
     * <BR>
     * システムを起動してから、時価サーバとの接続が1回も確立して
     * 以内場合は<code>null</code>を返す。<BR>
     * 2回以上、時価サーバとの接続－切断を行った場合、このメソッド
     * は最後に切断された時間を返す。<BR>
     * 
     * @return 接続確立時間
     */
    public Timestamp getLastDisconnectedTime();
    
    /**
     * (is接続済)<BR>
     * <BR>
     * 時価サーバとの接続が確立している場合は<code>true</code>、
     * それ以外の場合は<code>false</code>を返す。<BR>
     * 
     */
    public boolean isConnected();
    
    /**
     * (register接続エラーハンドラ)<BR>
     * <BR>
     * 接続エラーハンドラを取得する。<BR>
     * 
     * @param errorHandler 接続エラーハンドラ－
     * @see DOTQuoteConnectionErrorHandler#onRegister(DOTQuoteDataSource)
     */
    public void registerErrorHandler(DOTQuoteConnectionErrorHandler errorHandler);

}
