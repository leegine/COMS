/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3RecoveryQuoteCommandクラス(WEB3RecoveryQuoteCommand.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/23 堀野　和美(FLJ) 新規作成
 */

package jp.co.dir.dot.intellioms.recovery;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;


import com.fitechlabs.fin.intellioms.util.Log;
import com.fitechlabs.xtier.jmxrmi.JmxRmiClientException;

import jp.co.dir.dot.intellioms.jmx.rmi.DOTClusterQuoteCacheStoreAdaptor;
import jp.co.dir.dot.intellioms.quote.DOTRlsQuoteCacheStore;
import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData;
import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteDataSource;
import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteFileAdaptor;

/**
 * 時価復旧処理
 * 
 * @author kazumi HORINO(FLJ)
 * @version 1.0
 */
public abstract class DOTRecoveryQuote extends DOTRecoveryCommand
{    
    /**
     * ロガー。
     */
    private static final Log log = Log.getLogger(DOTRecoveryQuote.class);
    
    private static final boolean DBG = log.isDebug();    
        
    /**
     * クラスタリングされたルールエンジンの他ノードから
     * 時価情報を取得するアダプタクラス。
     */
    protected DOTClusterQuoteCacheStoreAdaptor quoteCacheStoreAdaptor;    

    /**
     * 時価情報をファイルに保存・ファイルから読み込むアダプタ
     */
    protected DOTQuoteFileAdaptor quoteFileAdaptor;
    
    /**
     * 時価データソース
     */
    protected DOTQuoteDataSource quoteDataSource;
    
    /**
     * 時価情報管理テーブル
     */
    protected DOTRlsQuoteCacheStore quoteCashStore;            
    
    /**
     * @return quoteCacheStoreAdaptor を戻します。
     */
    public DOTClusterQuoteCacheStoreAdaptor getQuoteCacheStoreAdaptor() {
        return quoteCacheStoreAdaptor;
    }
    
    /**
     * quoteCacheStoreAdaptor を設定する。
     * @param quoteCacheStoreAdaptor 他ノードから時価情報を取得するアダプタ
     */
    public void setQuoteCacheStoreAdaptor(DOTClusterQuoteCacheStoreAdaptor quoteCacheStoreAdaptor) 
    {
        this.quoteCacheStoreAdaptor = quoteCacheStoreAdaptor;
    }        

    /**
     * @return quoteFileAdaptor を戻します。
     */
    public DOTQuoteFileAdaptor getQuoteFileAdaptor() {
        return quoteFileAdaptor;
    }
    
    /**
     * quoteFileAdaptor を設定する。
     * @param quoteFileAdaptor 時価情報をファイルから読み込むアダプタ
     */
    public void setQuoteFileAdaptor(DOTQuoteFileAdaptor quoteFileAdaptor) 
    {
        this.quoteFileAdaptor = quoteFileAdaptor;
    }
    
    /**
     * @return quoteDataSource を戻します。
     */
    public DOTQuoteDataSource getQuoteDataSource() {
        return quoteDataSource;
    }
    
    /**
     * quoteDataSource を設定する。
     * @param quoteDataSource 時価データソース
     */
    public void setQuoteDataSource(DOTQuoteDataSource quoteDataSource) 
    {
        this.quoteDataSource = quoteDataSource;
    }    
    
    /**
     * @return quoteCashStore を戻します。
     */
    public DOTRlsQuoteCacheStore getQuoteCashStore() {
        return quoteCashStore;
    }

    /**
     * quoteCashStore を設定する。
     * @param quoteCashStore 時価情報管理テーブル
     */
    public void setQuoteCashStore(DOTRlsQuoteCacheStore quoteCashStore) 
    {
        this.quoteCashStore = quoteCashStore;
    }
    
    /**
     * 他ノードから時価情報を取得し、時価情報管理テーブルに追加する。
     * 
     * @param l_fromTimeStamp 取得時刻(FROM)
     * @param l_toTimeStamp 取得時刻(TO)
     */
    protected void restoreQuoteDataViaQuoteCacheStoreAdaptor(Timestamp l_fromTimeStamp, Timestamp l_toTimeStamp)
    {  
        //開始、終了時間のどちらかが未指定
        //開始時間と終了時間が逆転してる場合
        if((l_fromTimeStamp == null || l_toTimeStamp == null) ||
                (l_fromTimeStamp.compareTo(l_toTimeStamp) > 0))
        {
            if(DBG)
            {
                log.debug("l_fromTimeStamp=" + l_fromTimeStamp + " l_toTimeStamp=" + l_toTimeStamp);
            }
            throw new IllegalArgumentException("Parameter Error! [Timestamp(from)=" +  l_fromTimeStamp + " Timestamp(to)=" + l_toTimeStamp +"]");
        }
        
        try
        {
            //時間帯指定で他のノードから時価情報を取得する。        
            List l_lisQuoteData = quoteCacheStoreAdaptor.getQuoteData(l_fromTimeStamp, l_toTimeStamp);                
            
            if(l_lisQuoteData == null)
            {
                log.info("there was no quote data from other node.");
                return;                
            }
            
            //時価情報管理テーブルに追加。
            for(Iterator l_iter = l_lisQuoteData.iterator(); l_iter.hasNext();)
            {
                DOTQuoteData l_quoteData = (DOTQuoteData)l_iter.next();
                quoteCashStore.addQuoteData(l_quoteData);
                
                if(DBG)
                {
                    log.debug("added quote data(to quoteCashStore)=" + l_quoteData);                    
                }
            }
            
            if(DBG)
            {
                log.debug("recovered quote data (from other node) size=" + l_lisQuoteData.size());                
            }
            
        }
        catch(JmxRmiClientException l_jrc)
        {
            log.info("can't connect other node. don't do recovery via other node.");
        }
        catch(Throwable l_th)
        {
            log.error(l_th.getMessage());
        }        
    }
    
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer(getClass().getName());
        l_sb.append("[");
        l_sb.append(super.toString());
        l_sb.append(",quoteCacheStoreAdaptor=" + this.quoteCacheStoreAdaptor);
        l_sb.append(",quoteCashStore=" + this.quoteCashStore);
        l_sb.append(",quoteDataSource=" + this.quoteDataSource);
        l_sb.append(",quoteFileAdaptor=" + this.quoteFileAdaptor);
        l_sb.append("]");
        return l_sb.toString();
    }
}
