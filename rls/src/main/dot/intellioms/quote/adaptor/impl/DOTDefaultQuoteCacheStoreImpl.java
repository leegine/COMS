/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : DefaultQuoteCacheStoreImpl�N���X(DOTDefaultQuoteCacheStoreImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/06 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote.adaptor.impl;

import java.util.Hashtable;
import java.util.Map;


import com.fitechlabs.fin.intellioms.util.Log;

import jp.co.dir.dot.intellioms.enums.DataType;
import jp.co.dir.dot.intellioms.enums.IndexType;
import jp.co.dir.dot.intellioms.enums.PutAndCall;
import jp.co.dir.dot.intellioms.enums.RealType;
import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteCacheStore;
import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData;
import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteEvent;
import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteEventHandler;



/**
 * (�f�t�H���g�����Ǘ��e�[�u��Impl)<BR>
 * <BR>
 * �������Ǘ��e�[�u���̃f�t�H���g�����N���X�B<BR>
 * �������Ǘ��e�[�u���Ǝ����C�x���g�n���h�����������Ă���A
 * �����f�[�^�\�[�X����ʒm���ꂽ����������������ŊǗ�����B<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class DOTDefaultQuoteCacheStoreImpl implements DOTQuoteCacheStore,
    DOTQuoteEventHandler
{

    /** ���K�[ */
    private static final Log log = Log.getLogger(DOTDefaultQuoteCacheStoreImpl.class);

    /** �������e�[�u�� */
    private final Map cacheStore;

    /**
     * �R���X�g���N�^
     *
     * @param initialCapacity �����e��
     */
    public DOTDefaultQuoteCacheStoreImpl(int initialCapacity)
    {
        this.cacheStore = new Hashtable(initialCapacity);
    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteCacheStore#getEquityQuote(jp.co.fitechlabs.fin.quote.enum.RealType, String, String)
     */
    public DOTQuoteData getEquityQuote(RealType realType, String productCode,
        String marketCode)
    {

        if (realType == null || productCode == null || marketCode == null)
        {
            throw new IllegalArgumentException("realType, productCode and markteCode must be not null.");
        }

        DOTQuoteDataImpl quoteData = new DOTQuoteDataImpl();
        quoteData.realType = realType;
        quoteData.dataType = DataType.EQUITY;
        quoteData.marketCode = marketCode;
        quoteData.productCode = productCode;

        get(quoteData);

        return quoteData;

    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteCacheStore#getIndexQuote(jp.co.fitechlabs.fin.quote.enum.RealType, jp.co.fitechlabs.fin.quote.enum.IndexType)
     */
    public DOTQuoteData getIndexQuote(RealType realType, IndexType indexType)
    {

        if (realType == null || indexType == null)
        {
            throw new IllegalArgumentException("realType and indexType must be not null.");
        }

        DOTQuoteDataImpl quoteData = new DOTQuoteDataImpl();
        quoteData.realType = realType;
        quoteData.dataType = DataType.INDEX;
        quoteData.productCode = indexType.getProductCode();

        get(quoteData);

        return quoteData;

    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteCacheStore#getIndexFutureQuote(jp.co.fitechlabs.fin.quote.enum.RealType, String, String, String)
     */
    public DOTQuoteData getIndexFutureQuote(RealType realType,
        String underlyingProductCode, String monthOfDelivery, String marketCode)
    {

        if (realType == null || underlyingProductCode == null
            || monthOfDelivery == null || marketCode == null)
        {
            throw new IllegalArgumentException("realType, underlyingProductCode, monthOfDelivery and marketCode must be not null.");
        }

        DOTQuoteDataImpl quoteData = new DOTQuoteDataImpl();
        quoteData.realType = realType;
        quoteData.dataType = DataType.INDEX_FUTURE;
        quoteData.marketCode = marketCode;
        quoteData.productCode = underlyingProductCode;
        quoteData.monthOfDelivery = monthOfDelivery;

        get(quoteData);

        return quoteData;

    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteCacheStore#getIndexOptionQuote(jp.co.fitechlabs.fin.quote.enum.RealType, String, String, jp.co.fitechlabs.fin.quote.enum.PutAndCall, double, String)
     */
    public DOTQuoteData getIndexOptionQuote(RealType realType,
        String underlyingProductCode, String monthOfDelivery, PutAndCall putAndCall,
        double strikePrice, String marketCode)
    {

        if (realType == null || underlyingProductCode == null
            || monthOfDelivery == null || putAndCall == null
            || marketCode == null)
        {
            throw new IllegalArgumentException("realType, underlyingProductCode, monthOfDelivery, strikePrice and marketCode must be not null.");
        }

        if (Double.isNaN(strikePrice))
        {
            throw new IllegalArgumentException("strikePrice must be number.");
        }

        DOTQuoteDataImpl quoteData = new DOTQuoteDataImpl();
        quoteData.realType = realType;
        quoteData.dataType = DataType.INDEX_OPTION;
        quoteData.marketCode = marketCode;
        quoteData.productCode = underlyingProductCode;
        quoteData.monthOfDelivery = monthOfDelivery;
        quoteData.putAndCall = putAndCall;
        quoteData.strikePrice = strikePrice;

        get(quoteData);

        return quoteData;

    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteCacheStore#clear()
     */
    public synchronized void clear()
    {

        cacheStore.clear();

        log.warn("DOTQuoteCacheStore cleared!!!");

    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteEventHandler#push(jp.co.fitechlabs.fin.quote.DOTQuoteEvent)
     */
    public void push(DOTQuoteEvent event)
    {

        DOTQuoteEventImpl quoteData = (DOTQuoteEventImpl) event;
        String key = quoteData.createKey();

        addOrOverwrite(key, quoteData);

    }

    /**
     * (get)<BR>
     * <BR>
     * �w�肵���������Impl����쐬�����L�[�Ŏ������e�[�u������������B<BR>
     * �������e�[�u���Ɏ�����񂪑��݂���ꍇ�A
     * �w�肵���������Impl�Ɏ������e�[�u���̎�����񂩂�e�p�����[�^�̒l��
     * �R�s�[���Ď������Impl��Ԃ��B<BR>
     * ����ȊO�̏ꍇ�A�������Impl�̐V�����C���X�^���X���쐬���A
     * �p�����[�^�Ŏw�肵���������Impl����e�p�����[�^�̒l���R�s�[����
     * �������e�[�u���ɒǉ�����B<BR>
     *
     * @param quoteData �擾���鎞�����̃L�[���ڂ��ݒ肳�ꂽ�������Impl
     * @return �������e�[�u������擾����������񂩂�e�p�����[�^�ɒl���ݒ肳�ꂽ�������Impl
     */
    private DOTQuoteDataImpl get(DOTQuoteDataImpl quoteData)
    {

        String key = quoteData.createKey();
        DOTQuoteDataImpl cachedEntry = (DOTQuoteDataImpl) cacheStore.get(key);

        if (cachedEntry != null)
        {

            if (log.isDebug())
            {
                log.debug("Entry in cache : " + cachedEntry.toString());
            }

            quoteData.setParams(cachedEntry);

        } else
        {

            if (log.isDebug())
            {
                log.debug("No entry in cache : " + quoteData.toString());
            }

            DOTQuoteDataImpl newEntry = new DOTQuoteDataImpl();
            newEntry.setParams(quoteData);

            addIfNotExists(key, newEntry);

        }

        return quoteData;

    }

    /**
     * (addIfNotExists)<BR>
     * <BR>
     * �w�肵���L�[�Ŏ������e�[�u�����������A������񂪑��݂��Ȃ��ꍇ�́A
     * �w�肵�����������������e�[�u���ɒǉ�����B<BR>
     *
     * @param key �L�[
     * @param newEntry �������Impl
     */
    private synchronized void addIfNotExists(String key, DOTQuoteDataImpl newEntry)
    {
        if (cacheStore.get(key) == null)
        {
            cacheStore.put(key, newEntry);
        }
    }

    /**
     * (addOrOverwrite)<BR>
     * <BR>
     * �w�肵���L�[�Ŏ������e�[�u������������B
     * �������e�[�u���Ɏ�����񂪓o�^����Ă���ꍇ�́A
     * �p�����[�^�Ŏw�肵���������Impl����e�p�����[�^�ɒl���R�s�[����B<BR>
     * ����ȊO�̏ꍇ�A�������Impl�̐V�����C���X�^���X���쐬���A
     * �p�����[�^�Ŏw�肵���������Impl����e�p�����[�^�̒l���R�s�[����
     * �������e�[�u���ɒǉ�����B<BR>
     *
     * @param key �L�[
     * @param quoteData �������Impl
     */
    private synchronized void addOrOverwrite(String key, DOTQuoteEventImpl quoteData)
    {

        DOTQuoteDataImpl cachedEntry = (DOTQuoteDataImpl) cacheStore.get(key);

        if (cachedEntry != null)
        {

            if (log.isDebug())
            {
                log.debug("cacheStore#push overwrite existing enty : " + quoteData.toString());
            }

            cachedEntry.setParams(quoteData);

        } else
        {

            if (log.isDebug())
            {
                log.debug("cacheStore#push add new entry : " + quoteData.toString());
            }

            DOTQuoteDataImpl newEntry = new DOTQuoteDataImpl();
            newEntry.setParams(quoteData);
            cacheStore.put(key, newEntry);

        }

    }

}
