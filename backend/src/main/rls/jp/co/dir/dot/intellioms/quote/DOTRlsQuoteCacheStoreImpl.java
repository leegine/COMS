/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3RlsQuoteCacheStoreImpl�N���X(DOTRlsQuoteCacheStoreImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/18 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


import com.fitechlabs.fin.intellioms.omsclt.Price;
import com.fitechlabs.fin.intellioms.ticker.Ticker;
import com.fitechlabs.fin.intellioms.ticker.TickersManagerException;
import com.fitechlabs.fin.intellioms.util.Log;

import jp.co.dir.dot.intellioms.enums.IndexType;
import jp.co.dir.dot.intellioms.enums.PutAndCall;
import jp.co.dir.dot.intellioms.enums.RealType;
import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData;
import jp.co.dir.dot.intellioms.ticker.DOTEquityTicker;
import jp.co.dir.dot.intellioms.ticker.DOTIndexFutureTicker;
import jp.co.dir.dot.intellioms.ticker.DOTIndexOptionTicker;
import jp.co.dir.dot.intellioms.ticker.DOTIndexTicker;
import jp.co.dir.dot.intellioms.ticker.DOTTicker;
import jp.co.dir.dot.intellioms.ticker.DOTTickerManager;


/**
 * (�g���������Ǘ��e�[�u��Impl)<BR>
 * <BR>
 * �g���������Ǘ��e�[�u���̎����N���X<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class DOTRlsQuoteCacheStoreImpl implements DOTRlsQuoteCacheStore
{

    /** ���K�[ */
    private final Log log = Log.getLogger(getClass());

    /** Ticker�}�l�[�W�� */
    private DOTTickerManager tickersManager;

    /** �����|�������Ǘ��e�[�u�� */
    private QuotesByTicker quotesByTicker;

    /** ���ԁ|�������Ǘ��e�[�u�� */
    private QuotesByTime quotesByTime;

    /** �w���p�[ */
    private DOTQuoteHelper helper;

    /**
     * �R���X�g���N�^
     */
    public DOTRlsQuoteCacheStoreImpl()
    {
        this.quotesByTicker = new QuotesByTicker();
        this.quotesByTime = new QuotesByTime();
        this.helper = DOTQuoteHelper.getInstance();
    }

    /**
     * (register�g���e�B�b�J�[�}�l�[�W��)<BR>
     * <BR>
     * �g���e�B�b�J�[�}�l�[�W����o�^����B<BR>
     *
     * @param l_tickersManager �g���e�B�b�J�[�}�l�[�W��
     */
    public void registerTickersManager(DOTTickerManager l_tickersManager)
    {
        this.tickersManager = l_tickersManager;
        log.info("TickersManager registered. [" + l_tickersManager.getClass().getName() + "]");
    }

    /**
     * @see DOTRlsQuoteCacheStore#addQuoteData(DOTQuoteData)
     */
    public void addQuoteData(DOTQuoteData l_quoteData)
    {
        Ticker l_ticker = getTicker(l_quoteData);
        addQuoteData(l_ticker, l_quoteData);
    }

    /**
     * @see DOTRlsQuoteCacheStore#addQuoteData(com.fitechlabs.fin.intellioms.ticker.Ticker, DOTQuoteData)
     */
    public void addQuoteData(Ticker l_ticker, DOTQuoteData l_quoteData)
    {
        QuoteIndex l_index =
            new QuoteIndex(
                l_quoteData.getUpdatedTime().getTime(),
                l_quoteData.getSequenceNo());
        addQuoteData(l_ticker, l_index, l_quoteData);
    }

    /**
     * @see DOTRlsQuoteCacheStore#getQuoteData(com.fitechlabs.fin.intellioms.ticker.Ticker)
     */
    public DOTQuoteData getQuoteData(Ticker l_ticker)
    {
        return getLastQuoteData(l_ticker);
    }

    /**
     * @see DOTRlsQuoteCacheStore#getQuote(com.fitechlabs.fin.intellioms.ticker.Ticker, Timestamp, Timestamp)
     */
    public DOTQuote getQuote(Ticker l_ticker, Timestamp l_from, Timestamp l_to)
    {

        DOTQuote l_quote = null;

        QuoteIndex l_indexFrom = toQuoteIndex(l_from);
        QuoteIndex l_indexTo = toQuoteIndex(l_to);

        List l_quoteDataList = getQuoteDataFromQuotesByTicker(l_ticker, l_indexFrom, l_indexTo);

        if (l_quoteDataList != null && !l_quoteDataList.isEmpty())
        {
            l_quote = new DOTQuoteImpl(l_ticker, (DOTQuoteData) l_quoteDataList.get(0));
            for (int i = 1; i < l_quoteDataList.size(); i++)
            {
                l_quote.addQuoteData((DOTQuoteData) l_quoteDataList.get(i));
            }
        }

        return l_quote;
    }

    /**
     * @see DOTRlsQuoteCacheStore#getQuotes(Timestamp, Timestamp)
     */
    public List getQuotes(Timestamp l_from, Timestamp l_to)
    {

        List l_quoteList = null;

        QuoteIndex l_indexFrom = toQuoteIndex(l_from);
        QuoteIndex l_indexTo = toQuoteIndex(l_to);

        List l_quoteDataList = getQuoteDataFromQuotesByTime(l_indexFrom, l_indexTo);

        if (l_quoteDataList != null && !l_quoteDataList.isEmpty())
        {

            Map l_quotesByTicker = new HashMap();

            for (Iterator l_it = l_quoteDataList.iterator(); l_it.hasNext();)
            {

                DOTQuoteData l_quoteData = (DOTQuoteData) l_it.next();
                Ticker l_ticker = getTicker(l_quoteData);

                if (l_ticker == null)
                {
                    continue;
                }

                DOTQuote l_quote = (DOTQuote) l_quotesByTicker.get(l_ticker);

                if (l_quote != null)
                {
                    l_quote.addQuoteData(l_quoteData);
                } else
                {
                    l_quote = new DOTQuoteImpl(l_ticker, l_quoteData);
                    l_quotesByTicker.put(l_ticker, l_quote);
                }
            }

            l_quoteList = new ArrayList(l_quotesByTicker.values());

        }

        return l_quoteList;
    }

    /**
     * @see DOTRlsQuoteCacheStore#getQuoteData(Timestamp, Timestamp)
     */
    public List getQuoteData(Timestamp l_from, Timestamp l_to)
    {
        QuoteIndex l_indexFrom = toQuoteIndex(l_from);
        QuoteIndex l_indexTo = toQuoteIndex(l_to);
        return getQuoteDataFromQuotesByTime(l_indexFrom, l_indexTo);
    }

    /**
     * @see DOTRlsQuoteCacheStore#remove(Timestamp, Timestamp)
     */
    public synchronized void remove(Timestamp l_from, Timestamp l_to)
    {

        QuoteIndex l_indexFrom = toQuoteIndex(l_from);
        QuoteIndex l_indexTo = toQuoteIndex(l_to);

        removeQuoteData(l_indexFrom, l_indexTo);

    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteCacheStore#clear()
     */
    public void clear()
    {
        remove(null, null);
    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteCacheStore#getEquityQuote(RealType,
     *      String, String)
     */
    public DOTQuoteData getEquityQuote(RealType l_realType,
        String l_strProductCode, String l_strMarketCode)
    {
        DOTTicker l_ticker = new DOTEquityTicker(
            l_strProductCode,
            l_strMarketCode);
        return getQuoteData(l_ticker);
    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteCacheStore#getIndexFutureQuote(RealType,
     *      String, String, String)
     */
    public DOTQuoteData getIndexFutureQuote(RealType l_realType,
        String l_strUnderlyingProductCode, String l_strMonthOfDelivery,
        String l_strMarketCode)
    {
        DOTTicker l_ticker = new DOTIndexFutureTicker(
            l_strUnderlyingProductCode,
            l_strMarketCode,
            l_strMonthOfDelivery);
        return getQuoteData(l_ticker);
    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteCacheStore#getIndexOptionQuote(RealType,
     *      String, String,
     *      PutAndCall, double, String)
     */
    public DOTQuoteData getIndexOptionQuote(RealType l_realType,
        String l_strUnderlyingProductCode, String l_strMonthOfDelivery,
        PutAndCall l_putAndCall, double l_dblStrikePrice, String l_strMarketCode)
    {
        DOTTicker l_ticker = new DOTIndexOptionTicker(
            l_strUnderlyingProductCode,
            l_strMarketCode,
            l_strMonthOfDelivery,
            l_putAndCall,
            new Price(l_dblStrikePrice));
        return getQuoteData(l_ticker);
    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteCacheStore#getIndexQuote(RealType,
     *      IndexType)
     */
    public DOTQuoteData getIndexQuote(RealType l_realType,
        IndexType l_intIndexType)
    {
        DOTTicker l_ticker = new DOTIndexTicker(l_intIndexType);
        return getQuoteData(l_ticker);
    }

    /**
     * (get�������(WEB3�p���ۃe�B�b�J�[))<BR>
     * <BR>
     * �����|�������e�[�u������w�肵��Ticker�̍ŐV�̎��������擾����B<BR>
     *
     * @param l_web3Ticker Ticker
     * @return �������
     */
    private DOTQuoteData getQuoteData(DOTTicker l_web3Ticker)
    {

        DOTQuoteData l_quoteData = null;

        Ticker l_ticker = getTicker(l_web3Ticker);
        if (l_ticker != null)
        {
            l_quoteData = getLastQuoteData(l_ticker);
        }

        return l_quoteData;

    }

    /**
     * (add�������(�e�B�b�J�[, �������C���f�b�N�X, �������)<BR>
     * <BR>
     * ������������|�������e�[�u���Ǝ��ԁ|�������e�[�u���ɒǉ�����B<BR>
     *
     * @param l_ticker Ticker
     * @param l_index �C���f�b�N�X
     * @param l_quoteData �������
     */
    private synchronized void addQuoteData(Ticker l_ticker, QuoteIndex l_index, DOTQuoteData l_quoteData)
    {
        quotesByTicker.addQuoteData(l_ticker, l_index, l_quoteData);
        quotesByTime.addQuoteData(l_index, l_quoteData);
    }

    /**
     * (get�ŐV�������)<BR>
     * <BR>
     * �����|�������e�[�u������w�肵��Ticker�̍ŐV�̎��������擾����B<BR>
     *
     * @param l_ticker Ticker
     * @return �ŐV�̎������
     */
    private synchronized DOTQuoteData getLastQuoteData(Ticker l_ticker)
    {
        return quotesByTicker.getLastQuoteData(l_ticker);
    }

    /**
     * (get������񃊃X�gFrom�����|�������e�[�u��)<BR>
     * <BR>
     * �����|�������e�[�u������w�肵��Ticker�̎������̃��X�g���擾����B<BR>
     *
     * @param l_ticker Ticker
     * @param l_indexFrom �������C���f�b�N�X�iFROM�j
     * @param l_indexTo �������C���f�b�N�X�iTO�j
     * @return �������̈ꃊ�X�g
     */
    private synchronized List getQuoteDataFromQuotesByTicker(Ticker l_ticker, QuoteIndex l_indexFrom, QuoteIndex l_indexTo)
    {
        return quotesByTicker.getQuoteData(l_ticker, l_indexFrom, l_indexTo);
    }

    /**
     * (get������񃊃X�gFrom���ԁ|�������e�[�u��)<BR>
     * <BR>
     * ���ԁ|�������e�[�u�����玞�����̃��X�g���擾����B<BR>
     *
     * @param l_indexFrom �������C���f�b�N�X�iFROM�j
     * @param l_indexTo �������C���f�b�N�X�iTO�j
     * @return �������̃��X�g
     */
    private synchronized List getQuoteDataFromQuotesByTime(QuoteIndex l_indexFrom, QuoteIndex l_indexTo)
    {
        return quotesByTime.getQuoteData(l_indexFrom, l_indexTo);
    }

    /**
     * (remove�������(�������C���f�b�N�X�iFROM�j�A�������C���f�b�N�X�iTO))<BR>
     * <BR>
     * �����|�������e�[�u���Ǝ��ԁ|�������e�[�u�����玞�������폜����B<BR>
     *
     * @param l_indexFrom �������C���f�b�N�X�iFROM�j
     * @param l_indexTo �������C���f�b�N�X�iTO�j
     */
    private synchronized void removeQuoteData(QuoteIndex l_indexFrom, QuoteIndex l_indexTo)
    {

        List l_quoteDataList = quotesByTime.getQuoteData(l_indexFrom, l_indexTo);

        if (l_quoteDataList == null || l_quoteDataList.isEmpty())
        {
            return;
        }

        Set l_tickers = new HashSet();
        for (Iterator l_it = l_quoteDataList.iterator(); l_it.hasNext();)
        {

            DOTQuoteData l_quoteData = (DOTQuoteData) l_it.next();
            Ticker l_ticker = getTicker(l_quoteData);

            if (l_ticker == null)
            {
                continue;
            }

            l_tickers.add(l_ticker);

        }

        if (l_tickers != null && !l_tickers.isEmpty())
        {
            for (Iterator l_it = l_tickers.iterator(); l_it.hasNext();)
            {
                quotesByTicker.removeQuoteData(
                    (Ticker) l_it.next(),
                    l_indexFrom,
                    l_indexTo);
            }
        }

        quotesByTime.removeQuoteData(l_indexFrom, l_indexTo);

    }

    /**
     * (to�������C���f�b�N�X(�^�C���X�^���v))<BR>
     * <BR>
     * �w�肵���^�C���X�^���v����쐬�����������C���f�b�N�X���擾����B<BR>
     * �w�肵���^�C���X�^���v��<code>null</code>�̏ꍇ��<code>null</code>��Ԃ��B<BR>
     *
     * @param l_timestamp �^�C���X�^���v
     * @return �w�肵���^�C���X�^���v����쐬�����������C���f�b�N�X
     */
    private QuoteIndex toQuoteIndex(Timestamp l_timestamp)
    {
        return (l_timestamp != null) ? new QuoteIndex(l_timestamp) : null;
    }

    /**
     * get�e�B�b�J�[(WEB3�p���ۃe�B�b�J�[))<BR>
     * <BR>
     * �w�肵��WEB3�p���ۃe�B�b�J�[�ɑΉ�����e�B�b�J�[���擾����B<BR>
     * �w�肵��WEB3�p���ۃe�B�b�J�[�ɑΉ�����e�B�b�J�[�����݂��Ȃ��ꍇ��<code>null</code>��Ԃ��B
     *
     * @param l_web3Ticker WEB3�p���ۃe�B�b�J�[
     * @return �w�肵��WEB3�p���ۃe�B�b�J�[�ɑΉ�����e�B�b�J�[
     * @throws IllegalStateException �g���e�B�b�J�[�}�l�[�W�����o�^����Ă��Ȃ��ꍇ�B
     */
    private Ticker getTicker(DOTTicker l_web3Ticker)
    {

        if (tickersManager == null)
        {
            throw new IllegalStateException("tickersManager is not registered.");
        }

        Ticker l_ticker = null;
        try
        {
            l_ticker = tickersManager.getTicker(l_web3Ticker);
        } catch (TickersManagerException l_tme)
        {
            log.warn("Ticker not found. [web3Ticker=" + l_web3Ticker + "]", l_tme);
        }

        return l_ticker;

    }

    /**
     * (get�e�B�b�J�[(�������))<BR>
     * <BR>
     * �w�肵���������ɑΉ�����e�B�b�J�[���擾����B<BR>
     * �w�肵���������ɑΉ�����e�B�b�J�[�����݂��Ȃ��ꍇ��<code>null</code>��Ԃ��B
     *
     * @param l_quoteData �������
     * @return �w�肵���������ɑΉ�����e�B�b�J�[
     */
    private Ticker getTicker(DOTQuoteData l_quoteData)
    {
        return getTicker(helper.createTicker(l_quoteData));
    }

}
