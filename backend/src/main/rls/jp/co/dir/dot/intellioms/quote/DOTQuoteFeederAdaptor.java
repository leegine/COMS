/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3QuoteFeederAdaptorクラス(DOTQuoteFeederAdaptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/21 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.quote;


import com.fitechlabs.fin.intellioms.quote.QuoteFeederAdaptor;
import com.fitechlabs.fin.intellioms.quote.QuoteFeederAdaptorException;

import jp.co.dir.dot.intellioms.enums.QuoteStatus;
import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteDataSource;


/**
 * (拡張時価アダプタ)<BR>
 * <BR>
 * 時価アダプタのWEB3ルールエンジン用拡張インターフェース<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public interface DOTQuoteFeederAdaptor extends QuoteFeederAdaptor
{
    
    /**
     * (register時価データソース)<BR>
     * <BR>
     * WEB3QuoteDataSourceを登録する。<BR>
     * 
     * @param l_dataSource DOTQuoteDataSource
     */
    public void registerQuoteDataSource(DOTQuoteDataSource l_dataSource);
    
    /**
     * (connect)<BR>
     * <BR>
     * 時価サーバへの接続を開始する。<BR>
     * 
     * @return 時価サーバへの接続に成功した場合は<code>true</code>、
     *         それ以外の場合は<code>false</code>を返す。
     */
    public boolean connect() throws QuoteFeederAdaptorException;
    
    /**
     * (get接続状態)<BR>
     * <BR>
     * 時価サーバとの接続状態を取得する。<BR>
     * 
     * @return 時価サーバとの接続状態
     */
    public QuoteStatus getStatus();
    
}
