/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3FetchServiceManager�N���X(DOTFetchServiceManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/30 �V���@�h�O(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.jmx;

import com.fitechlabs.fin.intellioms.util.InitializationException;
import com.fitechlabs.fin.intellioms.util.Log;

import jp.co.dir.dot.intellioms.account.DOTAccountThreadService;



/**
 * Fetch�T�[�r�X��JMX�ŊǗ����邽�߂�MBean�����N���X�B
 *
 * @author Eizo Saito (FLJ)
 * @version 1.0
 */
public class DOTFetchServiceManager implements DOTFetchServiceManagerMBean
{
    
    /**
     * ���K�[
     */
    private final Log log = Log.getLogger(getClass());

    /**
     * Fetch�T�[�r�X
     */
    private DOTAccountThreadService service;

    /**
     * Fetch�T�[�r�X��o�^����B
     * 
     * @param l_service Fetch�T�[�r�X
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
