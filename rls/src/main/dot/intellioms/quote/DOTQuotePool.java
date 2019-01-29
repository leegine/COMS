/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3QuotePoolクラス(DOTQuotePool.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/17 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.quote;

import java.util.List;

import com.fitechlabs.fin.intellioms.ticker.Ticker;

import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData;



/**
 * (時価情報プール)<BR>
 * <BR>
 * 時価サーバから受信した時価情報を一時的にプールするクラス。<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public interface DOTQuotePool
{
    
    /**
     * (add時価情報)<BR>
     * <BR>
     * 指定したティッカーをキーとして、
     * 時価情報を時価情報プールに追加する。<BR>
     * 
     * @param l_quoteData 時価情報
     */
    public void addQuoteData(Ticker l_ticker, DOTQuoteData l_quoteData);
    
    /**
     * (get時価スナップショットリスト)<BR>
     * <BR>
     * 時価情報プールが保持している全ての時価情報から
     * 作成した時価情報スナップショットのリストを返し、
     * 時価情報プールを空にする。<BR>
     * 時価情報がプールされていない場合は、空の<code>List</code>を返す。<BR>
     * 
     * @return <code>DOTQuote</code>の<code>List</code>
     */
    public List getQuotes();

}
