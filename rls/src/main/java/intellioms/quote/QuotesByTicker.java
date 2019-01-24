/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : QuotesByTickerクラス(QuotesByTicker.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/16 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.quote;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.fitechlabs.fin.intellioms.ticker.Ticker;

import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData;


/**
 * (銘柄－時価情報テーブル)
 * 
 * 銘柄毎に時価情報を管理するクラス。
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
class QuotesByTicker
{
    
    /**
     * 銘柄－時価情報マップ
     * <BR>
     * キー : ティッカー<BR>
     * 値 : 時間－時価情報テーブル<BR>
     */
    private Map quotesByTicker;
    
    /**
     * コンストラクタ
     */
    public QuotesByTicker()
    {
        quotesByTicker = new HashMap();
    }
    
    /**
     * (add時価情報)<BR>
     * <BR>
     * 時価情報を追加する。<BR>
     * 
     * @param l_ticker ティッカー
     * @param l_index 時価情報インデックス
     * @param l_quoteData 時価情報
     */
    public void addQuoteData(Ticker l_ticker, QuoteIndex l_index, DOTQuoteData l_quoteData)
    {
        QuotesByTime l_quotesByTime = (QuotesByTime) quotesByTicker.get(l_ticker);
        if (l_quotesByTime == null)
        {
            l_quotesByTime = new QuotesByTime();
            quotesByTicker.put(l_ticker, l_quotesByTime);
        }
        l_quotesByTime.addQuoteData(l_index, l_quoteData);
    }
    
    /**
     * (get最新時価情報)<BR>
     * <BR>
     * 指定された銘柄の最新の時価情報を取得する。<BR>
     * 対象データが存在しない場合<code>null</code>を返す。<BR>
     * 
     * @param l_ticker ティッカー
     */
    public DOTQuoteData getLastQuoteData(Ticker l_ticker)
    {
        DOTQuoteData l_quoteData = null;
        QuotesByTime l_quotesByTime = (QuotesByTime) quotesByTicker.get(l_ticker);
        if (l_quotesByTime != null)
        {
            l_quoteData = l_quotesByTime.getLastQuoteData();
        }
        return l_quoteData;
    }
    
    /**
     * (get時価情報リスト)<BR>
     * <BR>
     * 指定された銘柄の更新時間が時価情報インデックス（FROM）以上、
     * 時価情報インデックス（TO）以下の時価情報のリストを取得する。<BR>
     * 対象データが存在しない場合<code>null</code>を返す。<BR>
     * 
     * @param l_ticker ティッカー
     * @param l_indexFrom 時価情報インデックス（FROM）
     * @param l_indexTo 時価情報インデックス（TO）
     * @return <code>DOTQuoteData</code>の<code>List</code>
     */
    public List getQuoteData(Ticker l_ticker, QuoteIndex l_indexFrom, QuoteIndex l_indexTo)
    {
        List l_quotes = null;
        QuotesByTime l_quotesByTime = (QuotesByTime) quotesByTicker.get(l_ticker);
        if (l_quotesByTime != null)
        {
            l_quotes = l_quotesByTime.getQuoteData(l_indexFrom, l_indexTo);
        }
        return l_quotes;
    }
    
    /**
     * (remove時価情報)<BR>
     * <BR>
     * 指定された銘柄の更新時間が時価情報インデックス（FROM）以上、
     * 時価情報インデックス（TO）以下の時価情報が存在する場合、
     * その時価情報を銘柄－時価情報テーブルから削除する。<BR>
     * 
     * @param l_ticker ティッカー
     * @param l_indexFrom 時価情報インデックス（FROM）
     * @param l_indexTo 時価情報インデックス（TO）
     */
    public void removeQuoteData(Ticker l_ticker, QuoteIndex l_indexFrom, QuoteIndex l_indexTo)
    {
        QuotesByTime l_quotesByTime = (QuotesByTime) quotesByTicker.get(l_ticker);
        if (l_quotesByTime != null)
        {
            l_quotesByTime.removeQuoteData(l_indexFrom, l_indexTo);
        }
    }

}
