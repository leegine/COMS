/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3Quoteクラス(DOTQuote.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/16 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.quote;

import java.util.List;


import com.fitechlabs.fin.intellioms.quote.Quote;

import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData;


/**
 * (拡張時価情報スナップショット)<BR>
 * <BR>
 * 時価情報スナップショットをWEB3ルールエンジン用に拡張したインターフェース。<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public interface DOTQuote extends Quote
{

    /**
     * (add時価情報)<BR>
     * <BR>
     * 時価情報スナップショットに時価情報を追加する。<BR>
     * 
     * @param l_quoteData
     */
    public void addQuoteData(DOTQuoteData l_quoteData);

    /**
     * (get時価情報【最新】)<BR>
     * <BR>
     * この時価情報スナップショットが保持する時価情報から
     * 最新の時価情報を取得する。<BR>
     * 
     * @return 最新の時価情報
     */
    public DOTQuoteData getLastPriceQuoteData();

    /**
     * (get時価情報【初期値】)<BR>
     * <BR>
     * この時価情報スナップショットが保持する時価情報から
     * 最初に設定された時価情報を取得する。<BR>
     * 
     * @return 時価情報
     */
    public DOTQuoteData getOpenPriceQuoteData();

    /**
     * (get時価情報【高値】)<BR>
     * <BR>
     * この時価情報スナップショットが保持する時価情報から
     * 現在値がもっとも高い時の時価情報を取得する。<BR>
     * 
     * @return 時価情報
     */
    public DOTQuoteData getHighPriceQuoteData();

    /**
     * (get時価情報【安値】)<BR>
     * <BR>
     * この時価情報スナップショットが保持する時価情報から
     * 現在値がもっとも安い時の時価情報を取得する。<BR>
     * 
     * @return 時価情報
     */
    public DOTQuoteData getLowPriceQuoteData();
    
    /**
     * (get時価情報リスト)<BR>
     * <BR>
     * この時価情報スナップショットが保持する
     * 全ての時価情報のリストを取得する。<BR>
     * 
     * @return 時価情報
     */
    public List getAllQuoteData();

}
