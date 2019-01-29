/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3QuoteFeederAdaptorManagerクラス(DOTQuoteFeederAdaptorManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/09 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.jmx;

import com.fitechlabs.fin.intellioms.quote.QuoteFeederAdaptorException;
import com.fitechlabs.fin.intellioms.util.Log;

import jp.co.dir.dot.intellioms.enums.QuoteStatus;
import jp.co.dir.dot.intellioms.quote.DOTQuoteFeederAdaptor;



/**
 * WEB3QuoteFeederAdaptorManagerMBeanの実装クラス。
 * 
 * 時価アダプタへのプロキシとして動作する。
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class DOTQuoteFeederAdaptorManager implements
    DOTQuoteFeederAdaptorManagerMBean
{
    
    /** ロガー */
    private final Log log = Log.getLogger(getClass());
    
    /** 時価アダプタ */
    private DOTQuoteFeederAdaptor feederAdaptor;
    
    /**
     * 時価アダプタを登録する。
     * 
     * @param l_feederAdaptor 時価アダプタ
     */
    public void registerFeederAdaptor(DOTQuoteFeederAdaptor l_feederAdaptor)
    {
        
        if (l_feederAdaptor == null)
        {
            throw new IllegalArgumentException("l_feederAdaptor must be not null.");
        }
        
        this.feederAdaptor = l_feederAdaptor;
        
        log.info("QuoteFeederAdaptor registered. [" + l_feederAdaptor.getClass().getName() + "]");
        
    }

    /**
     * @see jp.co.dir.dot.intellioms.jmx.DOTQuoteFeederAdaptorManagerMBean#connect()
     */
    public void connect()
    {
        
        if (feederAdaptor == null)
        {
            throw new IllegalStateException("QuoteFeederAdaptor is not registered.");
        }
        
        boolean l_isConnected = false; 
        try
        {
            l_isConnected = feederAdaptor.connect();
        } catch (QuoteFeederAdaptorException l_qfae)
        {
            log.error(l_qfae.getMessage(), l_qfae);
        }
        
        if (!l_isConnected)
        {
            throw new RuntimeException("Connecting to quote server is failed.");
        }
        
    }

    /**
     * @see jp.co.dir.dot.intellioms.jmx.DOTQuoteFeederAdaptorManagerMBean#disconnect()
     */
    public void disconnect()
    {
        
        if (feederAdaptor == null)
        {
            throw new IllegalStateException("QuoteFeederAdaptor is not registered.");
        }
        
        boolean l_isDisconnected = false;
        try
        {
            l_isDisconnected = feederAdaptor.disconnect();
        } catch (QuoteFeederAdaptorException l_qfae)
        {
            log.error(l_qfae.getMessage(), l_qfae);
        }
        
        if (!l_isDisconnected)
        {
            throw new RuntimeException("Disconnecting from quote server is failed.");
        }
        
    }

    /**
     * @see jp.co.dir.dot.intellioms.jmx.DOTQuoteFeederAdaptorManagerMBean#getStatus()
     */
    public int getStatus()
    {
        
        if (feederAdaptor == null)
        {
            throw new IllegalStateException("QuoteFeederAdaptor is not registered.");
        }
        
        QuoteStatus l_status = QuoteStatus.CLOSED;
        l_status = feederAdaptor.getStatus();
        return l_status.toValue();
        
    }
    
}
