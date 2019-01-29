/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3ObserverServicePriceManagerクラス(DOTObserverServicePriceManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/28 齋藤　栄三(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.jmx;

import com.fitechlabs.fin.intellioms.util.InitializationException;
import com.fitechlabs.fin.intellioms.util.Log;

import jp.co.dir.dot.intellioms.account.DOTAccountThreadService;



/**
 * 逆指値監視サービスをJMXで管理するためのMBean実装クラス。
 *
 * @author Eizo Saito (FLJ)
 * @version 1.0
 */
public class DOTObserverServicePriceManager implements DOTObserverServicePriceManagerMBean
{
    
    /**
     * ロガー
     */
    private final Log log = Log.getLogger(getClass());

    /**
     * 逆指値監視サービス
     */
    private DOTAccountThreadService service;

    /**
     * 逆指値監視サービスを登録する。
     * 
     * @param l_service 逆指値監視サービス
     */
    public void registerObserverService(DOTAccountThreadService l_service)
    {
        
        if (l_service == null)
        {
            throw new IllegalArgumentException("l_service must be not null.");
        }
        
        this.service = l_service;
        
        log.info("WEB3ObserverServicePrice registered. [" + l_service.getClass().getName() + "]");
        
    }

    /**
     * @see jp.co.dir.dot.intellioms.jmx.DOTObserverServicePriceManagerMBean#start()
     */
    public void start() throws InitializationException
    {
        if (service == null)
        {
            throw new IllegalStateException("WEB3ObserverServicePrice is not registered.");
        }
        
        if(service.isStarted())
        {
            throw new IllegalStateException("WEB3ObserverServicePrice is already started.");
        }
            
        service.start();
    }

    /**
     * @see jp.co.dir.dot.intellioms.jmx.DOTObserverServicePriceManagerMBean#stop()
     */
    public void stop()
    {
        if (service == null)
        {
            throw new IllegalStateException("WEB3ObserverServicePrice is not registered.");
        }
        
        if(! service.isStarted())
        {
            throw new IllegalStateException("WEB3ObserverServicePrice is already stopped.");
        }

        service.stop();
    }
    
    /**
     * @see jp.co.dir.dot.intellioms.jmx.DOTObserverServicePriceManagerMBean#getStatus()
     */
    public String getStatus()
    {
        if (service == null)
        {
            throw new IllegalStateException("WEB3ObserverServicePrice is not registered.");
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
