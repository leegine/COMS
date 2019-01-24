/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3QuoteFileAdaptorクラス(DOTQuoteFileAdaptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/19 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.quote.adaptor;

import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.fin.intellioms.util.Startable;



/**
 * (時価情報ファイルアダプタ)<BR>
 * <BR>
 * 時価情報をファイルに保存・ファイルから読み込むアダプタクラス。<BR>
 * 
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public interface DOTQuoteFileAdaptor extends Startable
{
    
    /**
     * (save)
     * <BR>
     * 時価情報をファイルに保存する。<BR>
     * 
     * @param l_quoteData 時価情報
     */
    public void save(DOTQuoteData l_quoteData);
    
    /**
     * (load)
     * <BR>
     * 時価情報をファイルから読み込む。<BR>
     * 
     * @return <code>DOTQuoteData</code>のList。
     */
    public List load();
    
    /**
     * (get最終書込み時間)
     * <BR>
     * ファイルの最終書き込み時刻を返す。<BR>
     * 
     * @return Timestamp 最終書き込み時刻
     */
    public Timestamp getLastUpdatedTimestamp();
    

    /**
     * (isファイルが存在する)
     * <BR>
     * ファイルが存在するかどうか返却する。
     * 
     * @return boolean ファイルあればtrue, なければfalse
     */
    public boolean dataExists();
    
    
}
