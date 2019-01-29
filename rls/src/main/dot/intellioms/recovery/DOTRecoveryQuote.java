/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3RecoveryQuoteCommand�N���X(WEB3RecoveryQuoteCommand.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/23 �x��@�a��(FLJ) �V�K�쐬
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
 * ������������
 * 
 * @author kazumi HORINO(FLJ)
 * @version 1.0
 */
public abstract class DOTRecoveryQuote extends DOTRecoveryCommand
{    
    /**
     * ���K�[�B
     */
    private static final Log log = Log.getLogger(DOTRecoveryQuote.class);
    
    private static final boolean DBG = log.isDebug();    
        
    /**
     * �N���X�^�����O���ꂽ���[���G���W���̑��m�[�h����
     * ���������擾����A�_�v�^�N���X�B
     */
    protected DOTClusterQuoteCacheStoreAdaptor quoteCacheStoreAdaptor;    

    /**
     * ���������t�@�C���ɕۑ��E�t�@�C������ǂݍ��ރA�_�v�^
     */
    protected DOTQuoteFileAdaptor quoteFileAdaptor;
    
    /**
     * �����f�[�^�\�[�X
     */
    protected DOTQuoteDataSource quoteDataSource;
    
    /**
     * �������Ǘ��e�[�u��
     */
    protected DOTRlsQuoteCacheStore quoteCashStore;            
    
    /**
     * @return quoteCacheStoreAdaptor ��߂��܂��B
     */
    public DOTClusterQuoteCacheStoreAdaptor getQuoteCacheStoreAdaptor() {
        return quoteCacheStoreAdaptor;
    }
    
    /**
     * quoteCacheStoreAdaptor ��ݒ肷��B
     * @param quoteCacheStoreAdaptor ���m�[�h���玞�������擾����A�_�v�^
     */
    public void setQuoteCacheStoreAdaptor(DOTClusterQuoteCacheStoreAdaptor quoteCacheStoreAdaptor) 
    {
        this.quoteCacheStoreAdaptor = quoteCacheStoreAdaptor;
    }        

    /**
     * @return quoteFileAdaptor ��߂��܂��B
     */
    public DOTQuoteFileAdaptor getQuoteFileAdaptor() {
        return quoteFileAdaptor;
    }
    
    /**
     * quoteFileAdaptor ��ݒ肷��B
     * @param quoteFileAdaptor ���������t�@�C������ǂݍ��ރA�_�v�^
     */
    public void setQuoteFileAdaptor(DOTQuoteFileAdaptor quoteFileAdaptor) 
    {
        this.quoteFileAdaptor = quoteFileAdaptor;
    }
    
    /**
     * @return quoteDataSource ��߂��܂��B
     */
    public DOTQuoteDataSource getQuoteDataSource() {
        return quoteDataSource;
    }
    
    /**
     * quoteDataSource ��ݒ肷��B
     * @param quoteDataSource �����f�[�^�\�[�X
     */
    public void setQuoteDataSource(DOTQuoteDataSource quoteDataSource) 
    {
        this.quoteDataSource = quoteDataSource;
    }    
    
    /**
     * @return quoteCashStore ��߂��܂��B
     */
    public DOTRlsQuoteCacheStore getQuoteCashStore() {
        return quoteCashStore;
    }

    /**
     * quoteCashStore ��ݒ肷��B
     * @param quoteCashStore �������Ǘ��e�[�u��
     */
    public void setQuoteCashStore(DOTRlsQuoteCacheStore quoteCashStore) 
    {
        this.quoteCashStore = quoteCashStore;
    }
    
    /**
     * ���m�[�h���玞�������擾���A�������Ǘ��e�[�u���ɒǉ�����B
     * 
     * @param l_fromTimeStamp �擾����(FROM)
     * @param l_toTimeStamp �擾����(TO)
     */
    protected void restoreQuoteDataViaQuoteCacheStoreAdaptor(Timestamp l_fromTimeStamp, Timestamp l_toTimeStamp)
    {  
        //�J�n�A�I�����Ԃ̂ǂ��炩�����w��
        //�J�n���ԂƏI�����Ԃ��t�]���Ă�ꍇ
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
            //���ԑюw��ő��̃m�[�h���玞�������擾����B        
            List l_lisQuoteData = quoteCacheStoreAdaptor.getQuoteData(l_fromTimeStamp, l_toTimeStamp);                
            
            if(l_lisQuoteData == null)
            {
                log.info("there was no quote data from other node.");
                return;                
            }
            
            //�������Ǘ��e�[�u���ɒǉ��B
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
