/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3ThreadLocalQuoteEventPoolクラス(DOTThreadLocalQuoteEventPool.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/06 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.quote;

import com.fitechlabs.fin.intellioms.event.Event;


/**
 * (時価イベントプール)<BR>
 * <BR>
 * スレッドローカルな時価イベントを管理するクラス。<BR>
 * <BR>
 * 時価イベントの発生前に、発生する時価イベントが設定され、
 * 時価イベントが終了すると、クリアされる。<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public interface DOTThreadLocalQuoteEventPool
{
    
    /**
     * (get時価イベント)<BR>
     * <BR>
     * 時価イベントを取得する。<BR>
     * 
     * @return 時価イベント
     */
    public Event getQuoteEvent();
    
    /**
     * (set時価イベント)<BR>
     * <BR>
     * 時価イベントを設定する。<BR>
     * 
     * @param l_event 時価イベント
     */
    public void setQuoteEvent(Event l_event);
    
    /**
     * (remove時価イベント)<BR>
     * <BR>
     * 設定されている時価イベントを削除する。<BR>
     */
    public void removeQuoteEvent();

}
