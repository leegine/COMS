/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3QuoteEventHandlerクラス(DOTQuoteEventHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/23 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.quote.adaptor;


/**
 * (時価イベントハンドラ)<BR>
 * <BR>
 * 時価イベントを処理するイベントハンドラー<BR>
 * 
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public interface DOTQuoteEventHandler
{
    
    /**
     * (push)<BR>
     * <BR>
     * 発生した時価イベントを処理する。<BR>
     * 
     * @param event 時価イベント
     */
    public void push(DOTQuoteEvent event);

}
