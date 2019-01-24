/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3FetchServiceManagerクラス(DOTFetchServiceManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/30 齋藤　栄三(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.jmx;

import com.fitechlabs.fin.intellioms.util.InitializationException;
import com.fitechlabs.fin.intellioms.util.Log;

import jp.co.dir.dot.intellioms.account.DOTAccountThreadService;



/**
 * FetchサービスをJMXで管理するためのMBean実装クラス。
 *
 * @author Eizo Saito (FLJ)
 * @version 1.0
 */
public class DOTFetchServiceManager implements DOTFetchServiceManagerMBean
{
    
    /**
     * ロガー
     */
    private final Log log = Log.getLogger(getClass());

    /**
     * Fetchサービス
     */
    private DOTAccountThreadService service;

    /**
     * Fetchサービスを登録する。
     * 
     * @param l_service Fetchサービス
     */
    public void registerFetchService(DOTAccountThreadService l_service)
    {
        
        if (l_service == null)
        {
            throw new IllegalArgumentException("l_service must be not null.");
        }
        
        this.service = l_service;
        
        log.info("WEB3FetchService registered. [" + l_service.getClass().getName() + "]");
        
    }

    /**
     * @see jp.co.dir.dot.intellioms.jmx.DOTFetchServiceManagerMBean#start()
     */
    public void start() throws InitializationException
    {
        if (service == null)
        {
            throw new IllegalStateException("WEB3FetchService is not registered.");
        }
        
        if(service.isStarted())
        {
            throw new IllegalStateException("WEB3FetchService is already started.");
        }
            
        service.start();
    }

    /**
     * @see jp.co.dir.dot.intellioms.jmx.DOTFetchServiceManagerMBean#stop()
     */
    public void stop()
    {
        if (service == null)
        {
            throw new IllegalStateException("WEB3FetchService is not registered.");
        }
        
        if(! service.isStarted())
        {
            throw new IllegalStateException("WEB3FetchService is already stopped.");
        }

        service.stop();
    }
    
    /**
     * @see jp.co.dir.dot.intellioms.jmx.DOTFetchServiceManagerMBean#getStatus()
     */
    public String getStatus()
    {
        if (service == null)
        {
            throw new IllegalStateException("WEB3FetchService is not registered.");
        }

        if(service.isStarted())
        {
            return "ACTIVE";
        }
        else
        {
            return "INACTIVE";
        }
    }
}
