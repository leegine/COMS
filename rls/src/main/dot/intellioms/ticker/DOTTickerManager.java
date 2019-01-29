/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3TickerManagerクラス(DOTTickerManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/17 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.ticker;

import com.fitechlabs.fin.intellioms.ticker.Ticker;
import com.fitechlabs.fin.intellioms.ticker.TickersManager;
import com.fitechlabs.fin.intellioms.ticker.TickersManagerException;


/**
 * (拡張ティッカーマネージャ)<BR>
 * <BR>
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public interface DOTTickerManager extends TickersManager
{
    
    /**
     * (getティッカー)<BR>
     * <BR>
     * 指定されたWEB3用抽象ティッカーに対応するルールエンジン用のティッカーを取得する。<BR>
     * <BR>
     * @param l_web3Ticker WEB3用抽象ティッカー 
     * @return ルールエンジン用のティッカー
     * @throws TickersManagerException 指定したWEB3用抽象ティッカーに対応する
     *  ルールエンジン用ティッカーが存在しない場合。
     */
    public Ticker getTicker(DOTTicker l_web3Ticker) throws TickersManagerException;
    
    /**
     * (loadティッカー)<BR>
     * <BR>
     * ティッカー情報をロードする。<BR>
     * <BR>
     * @throws TickersManagerException ティッカー情報をロードするときに、
     *  何らかの例外が発生した場合。
     */
    public void loadTickers() throws TickersManagerException;
    
}
