/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : QuotesByTimeクラス(QuotesByTime.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/16 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.quote;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData;



/**
 * (時間－時価情報テーブル)<BR>
 * <BR>
 * 時価情報インデックスをキーとして、時間毎の時価情報を管理する。<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
class QuotesByTime
{
    
    /** 
     * 時間－時価情報マップ<BR>
     * <BR>
     * キー ： 時価情報インデックス<BR>
     * 値 : 時価情報を保持するList<BR>
     */
    private TreeMap quotesByTime;
    
    /**
     * コンストラクタ
     */
    public QuotesByTime()
    {
        quotesByTime = new TreeMap();
    }
    
    /**
     * (add時価情報)<BR>
     * <BR>
     * 時価情報を追加する。<BR>
     * 
     * @param l_index 時価情報インデックス
     * @param l_quoteData 時価情報
     */
    public void addQuoteData(QuoteIndex l_index, DOTQuoteData l_quoteData)
    {
        List l_quotes = (List) quotesByTime.get(l_index);
        if (l_quotes == null)
        {
            l_quotes = new ArrayList();
            quotesByTime.put(l_index, l_quotes);
        }
        l_quotes.add(l_quoteData);
    }
    
    /**
     * (get最新時価情報)<BR>
     * <BR>
     * 最新の時価情報を取得する。<BR>
     * 時間－時価情報テーブルが空の場合<code>null</code>を返す。<BR>
     * 
     * @return 最新の時価情報
     */
    public DOTQuoteData getLastQuoteData()
    {
        
        if (quotesByTime.isEmpty())
        {
            return null;
        }
        
        DOTQuoteData l_quoteData = null;
        Object l_lastKey = quotesByTime.lastKey();
        List l_quotes = (List) quotesByTime.get(l_lastKey);
        
        if (l_quotes != null && !l_quotes.isEmpty())
        {
            int l_intLastIndex = l_quotes.size() - 1;
            l_quoteData = (DOTQuoteData) l_quotes.get(l_intLastIndex);
        }
        
        return l_quoteData;
        
    }
    
    /**
     * (get時価情報リスト)<BR>
     * <BR>
     * 更新時間が時価情報インデックス（FROM）以上、
     * 時価情報インデックス（TO）以下の時価情報のリストを取得する。<BR>
     * 対象データが存在しない場合<code>null</code>を返す。<BR>
     * 
     * @param l_indexFrom 時価情報インデックス（FROM）
     * @param l_indexTo 時価情報インデックス（TO）
     * @return <code>DOTQuoteData</code>の<code>List</code>
     */
    public List getQuoteData(QuoteIndex l_indexFrom, QuoteIndex l_indexTo)
    {
        List l_quotes = null;
        SortedMap l_subQuotesByTime = getSubMap(l_indexFrom, l_indexTo);
        
        if (l_subQuotesByTime != null && !l_subQuotesByTime.isEmpty())
        {
            l_quotes = new ArrayList();
            for (Iterator it = l_subQuotesByTime.values().iterator(); it.hasNext();)
            {
                l_quotes.addAll((List) it.next());
            }
        }
        
        return l_quotes;
        
    }
    
    /**
     * (remove時価情報リスト)<BR>
     * <BR>
     * 更新時間が時価情報インデックス（FROM）以上、
     * 時価情報インデックス（TO）以下の時価情報の時価情報が存在する場合、
     * その時価情報を時間－時価情報テーブルから削除する。<BR>
     * 
     * @param l_indexFrom 時価情報インデックス（FROM）
     * @param l_indexTo 時価情報インデックス（TO）
     */
    public void removeQuoteData(QuoteIndex l_indexFrom, QuoteIndex l_indexTo)
    {
        
        SortedMap l_subQuotesByTime = getSubMap(l_indexFrom, l_indexTo);
        if (l_subQuotesByTime != null && !l_subQuotesByTime.isEmpty())
        {
            List l_keyList = new ArrayList(l_subQuotesByTime.keySet());
            for (Iterator l_it = l_keyList.iterator(); l_it.hasNext();)
            {
                quotesByTime.remove(l_it.next());
            }
        }
    }
    
    /**
     * (getサブ時間－時価情報マップ)<BR>
     * <BR>
     * 時間－時価情報マップから、更新時間が時価情報インデックス（FROM）以上、
     * 時価情報インデックス（TO）以下の範囲である部分ビューを取得する。<BR>
     * 
     * @param l_indexFrom 時価情報インデックス（FROM）
     * @param l_indexTo 時価情報インデックス（TO）
     */
    private SortedMap getSubMap(QuoteIndex l_indexFrom, QuoteIndex l_indexTo)
    {
        
        QuoteIndex l_indexToKey = null;
        SortedMap l_quotes = null;
        
        if (l_indexTo != null)
        {
            l_indexToKey = new QuoteIndex(l_indexTo.getUpdatedTime() + 1, l_indexTo.getSequenceNo());
        }
        
        if (l_indexFrom != null)
        {
            if (l_indexTo != null)
            {
                l_quotes = quotesByTime.subMap(l_indexFrom, l_indexToKey);
            } else
            {
                l_quotes = quotesByTime.tailMap(l_indexFrom);
            }
        } else
        {
            if (l_indexTo != null)
            {
                l_quotes = quotesByTime.headMap(l_indexToKey);
            } else
            {
                l_quotes = quotesByTime;
            }
        }
        return l_quotes;
    }
    
}
