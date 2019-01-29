/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3QuoteEventクラス(DOTQuoteEvent.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/23 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.quote.adaptor;


/**
 * (時価イベント)<BR>
 * <BR>
 * 時価データソースによって作成され、時価イベントハンドラに処理
 * される時価イベント。<BR>
 * 
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public interface DOTQuoteEvent
{
    
    /**
     * (get時価情報)<BR>
     * <BR>
     * 時価イベントが保持する時価情報を取得する。<BR>
     * 
     * @return 時価情報
     */
    public DOTQuoteData getQuoteData();

}
