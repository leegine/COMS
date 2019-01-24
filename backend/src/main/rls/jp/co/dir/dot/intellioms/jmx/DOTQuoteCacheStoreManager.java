/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3QuoteCacheStoreManager�N���X(DOTQuoteCacheStoreManager.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/02/27 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.jmx;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.fitechlabs.fin.intellioms.util.Log;

import jp.co.dir.dot.intellioms.quote.DOTRlsQuoteCacheStore;


/**
 * WEB3QuoteCacheStoreManagerMBean�̎����N���X�B
 *
 * �������Ǘ��e�[�u���ւ̃v���L�V�Ƃ��ċ@�\����B
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class DOTQuoteCacheStoreManager implements
    DOTQuoteCacheStoreManagerMBean
{

    /** �uyyyy/MM/dd HH:mm:ss�v�t�H�[�}�b�g */
    private static final String YYYYMMDD_HHMMSS_FORMAT = "yyyy/MM/dd HH:mm:ss";

    /** ���K�[ */
    private final Log log = Log.getLogger(getClass());

    /** �������Ǘ��e�[�u�� */
    private DOTRlsQuoteCacheStore cacheStore;

    /**
     * �Ǘ��ΏۂƂȂ鎞�����Ǘ��e�[�u����o�^����B
     *
     * @param l_cacheStore �������Ǘ��e�[�u��
     */
    public void registerCacheStore(DOTRlsQuoteCacheStore l_cacheStore)
    {
        if (l_cacheStore == null)
        {
            throw new IllegalArgumentException("l_cacheStore must be not null.");
        }

        this.cacheStore = l_cacheStore;

        log.info("QuoteCacheStore registered. [" + l_cacheStore.getClass().getName() + "]");

    }

    /**
     * @see jp.co.dir.dot.intellioms.jmx.DOTQuoteCacheStoreManagerMBean#getQuoteData(Timestamp, Timestamp)
     */
    public List getQuoteData(Timestamp l_from, Timestamp l_to)
    {
        return cacheStore.getQuoteData(l_from, l_to);
    }

    /**
     * @see jp.co.dir.dot.intellioms.jmx.DOTQuoteCacheStoreManagerMBean#getQuoteData(String, String)
     */
    public List getQuoteData(String l_strFrom, String l_strTo)
    {
        Timestamp l_from = toTimestamp(l_strFrom);
        Timestamp l_to = toTimestamp(l_strTo);
        return cacheStore.getQuoteData(l_from, l_to);
    }

    /**
     * �w�肵����������uyyyy/MM/dd HH:mm:ss�v�t�H�[�}�b�g�Ńp�[�X����Timestamp��Ԃ��B
     *
     * @param l_strSource �p�[�X���镶����
     * @return �w�肵�������񂩂琶�����ꂽTimestamp
     */
    private Timestamp toTimestamp(String l_strSource)
    {
        Timestamp l_ts = null;
        if (l_strSource != null)
        {
            DateFormat l_df = new SimpleDateFormat(YYYYMMDD_HHMMSS_FORMAT);
            try
            {
                l_ts = new Timestamp(l_df.parse(l_strSource).getTime());
            } catch (ParseException l_pe)
            {
                throw new RuntimeException("Parse error. input=[" + l_strSource
                    + "]", l_pe);
            }
        }
        return l_ts;
    }

}
