/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3QuoteFeederAdaptorManager�N���X(DOTQuoteFeederAdaptorManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/09 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.jmx;

import com.fitechlabs.fin.intellioms.quote.QuoteFeederAdaptorException;
import com.fitechlabs.fin.intellioms.util.Log;

import jp.co.dir.dot.intellioms.enums.QuoteStatus;
import jp.co.dir.dot.intellioms.quote.DOTQuoteFeederAdaptor;



/**
 * WEB3QuoteFeederAdaptorManagerMBean�̎����N���X�B
 * 
 * �����A�_�v�^�ւ̃v���L�V�Ƃ��ē��삷��B
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class DOTQuoteFeederAdaptorManager implements
    DOTQuoteFeederAdaptorManagerMBean
{
    
    /** ���K�[ */
    private final Log log = Log.getLogger(getClass());
    
    /** �����A�_�v�^ */
    private DOTQuoteFeederAdaptor feederAdaptor;
    
    /**
     * �����A�_�v�^��o�^����B
     * 
     * @param l_feederAdaptor �����A�_�v�^
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
