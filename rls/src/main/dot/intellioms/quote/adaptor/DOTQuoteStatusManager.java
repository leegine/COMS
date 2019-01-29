/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3QuoteStatusManagerクラス(DOTQuoteStatusManager.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/03/14 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.quote.adaptor;

import jp.co.dir.dot.intellioms.enums.QuoteStatus;

/**
 * (接続状態マネージャ)<BR>
 * <BR>
 * 時価サーバとの接続状態を管理する。<BR>
 * 
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public interface DOTQuoteStatusManager
{

    /**
     * (get接続状態)<BR>
     * <BR>
     * 現在の接続状態を取得する。<BR>
     * 
     * @return 現在の接続状態
     */
    public QuoteStatus getCurrentStatus();

    /**
     * (modify接続状態)<BR>
     * <BR>
     * 指定したステータスに変更をする。<BR>
     * 
     * @param l_newStatus 接続状態
     */
    public void modifyStatus(QuoteStatus l_newStatus);
    
}