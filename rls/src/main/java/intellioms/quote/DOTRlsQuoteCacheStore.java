/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3RlsQuoteCacheStoreクラス(DOTRlsQuoteCacheStore.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/16 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.quote;

import java.sql.Timestamp;
import java.util.List;


import com.fitechlabs.fin.intellioms.ticker.Ticker;

import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteCacheStore;
import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData;



/**
 * (拡張時価情報管理テーブル)<BR>
 * <BR>
 * 時価情報管理テーブルをWEB3ルールエンジン用に拡張したインターフェース。<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public interface DOTRlsQuoteCacheStore extends DOTQuoteCacheStore
{
    
    /**
     * (add時価情報(時価情報))<BR>
     * <BR>
     * 時価情報管理テーブルに時価情報を追加する。<BR>
     * 
     * @param l_quoteData 時価情報
     */
    public void addQuoteData(DOTQuoteData l_quoteData);
    
    /**
     * (add時価情報(ティッカー, 時価情報))<BR>
     * <BR>
     * 指定したティッカーをキーとして、
     * 時価情報管理テーブルに時価情報を追加する。<BR>
     * 
     * @param l_ticker ティッカー
     * @param l_quoteData 時価情報
     */
    public void addQuoteData(Ticker l_ticker, DOTQuoteData l_quoteData);
    
    /**
     * (get時価情報)<BR>
     * <BR>
     * 指定した銘柄の最新の時価情報を取得する。<BR>
     * 対象データが存在しない場合<code>null</code>を返す。<BR>
     * 
     * @param l_ticker ティッカー
     * @return 時価情報
     */
    public DOTQuoteData getQuoteData(Ticker l_ticker);
    
    /**
     * (get時価情報スナップショット)<BR>
     * <BR>
     * 指定した銘柄の更新時間が更新時間（FROM）以上、
     * 更新時間（TO）以下の時価情報スナップショットを取得する。<BR>
     * 対象データが存在しない場合<code>null</code>を返す。<BR>
     * 
     * @param l_ticker ティッカー
     * @param l_from 更新時間（FROM）
     * @param l_to 更新時間（TO）
     * @return 時価情報
     */
    public DOTQuote getQuote(Ticker l_ticker, Timestamp l_from, Timestamp l_to);
    
    /**
     * (get時価情報スナップショットリスト)<BR>
     * <BR>
     * 更新時間が更新時間（FROM）以上、
     * 更新時間（TO）以下の時価情報スナップショットのリストを取得する。<BR>
     * 対象データが存在しない場合<code>null</code>を返す。<BR>
     * 
     * @param l_from 更新時間（FROM）
     * @param l_to 更新時間（TO）
     * @return <code>DOTQuote</code>の<code>List</code>
     */
    public List getQuotes(Timestamp l_from, Timestamp l_to);
    
    /**
     * (get時価情報リスト)<BR>
     * <BR>
     * 更新時間が更新時間（FROM）以上、
     * 更新時間（TO）以下の時価情報のリストを取得する。<BR>
     * 対象データが存在しない場合<code>null</code>を返す。<BR>
     * 
     * @param l_from 更新時間（FROM）
     * @param l_to 更新時間（TO）
     * @return <code>DOTQuoteData</code>の<code>List</code>
     */
    public List getQuoteData(Timestamp l_from, Timestamp l_to);
    
    /**
     * (remove時価情報)<BR>
     * <BR>
     * 更新時間が更新時間（FROM）以上、
     * 更新時間（TO）以下の時価情報が存在する場合、
     * その時価情報を時価情報管理テーブルから削除する。<BR>
     * 
     * @param l_from 削除対象時価情報の更新時間（FROM）
     * @param l_to 削除対象時価情報の更新時間（TO）
     */
    public void remove(Timestamp l_from, Timestamp l_to);
    
}
