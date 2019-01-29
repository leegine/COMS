/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3QuoteImpl�N���X(DOTQuoteImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/01/16 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import com.fitechlabs.fin.intellioms.omsclt.Price;
import com.fitechlabs.fin.intellioms.quote.BARecord;
import com.fitechlabs.fin.intellioms.ticker.Ticker;

import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData;

/**
 * (�g���������X�i�b�v�V���b�gImpl)<BR>
 * <BR>
 * �g���������X�i�b�v�V���b�g�̎����N���X�B<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class DOTQuoteImpl implements DOTQuote
{

    /** �e�B�b�J�[ */
    private Ticker ticker;

    /** ������񃊃X�g */
    private List quoteDataList;

    /** �������i�ŐV�j */
    private DOTQuoteData lastPriceQuoteData;

    /** �������i�����l�j */
    private DOTQuoteData openPriceQuoteData;

    /** �������i���l�j */
    private DOTQuoteData highPriceQuoteData;

    /** �������i���l�j */
    private DOTQuoteData lowPriceQuoteData;

    /** �R���X�g���N�^ */
    public DOTQuoteImpl(Ticker l_ticker, DOTQuoteData l_quoteData)
    {
        this.ticker = l_ticker;
        this.quoteDataList = new ArrayList();
        setQuoteData(l_quoteData);
    }

    /**
     * @see DOTQuote#addQuoteData()
     */
    public void addQuoteData(DOTQuoteData l_quoteData)
    {
        setQuoteData(l_quoteData);
    }

    /**
     * @see DOTQuote#getLastPriceQuoteData()
     */
    public DOTQuoteData getLastPriceQuoteData()
    {
        return lastPriceQuoteData;
    }

    /**
     * @see DOTQuote#getOpenPriceQuoteData()
     */
    public DOTQuoteData getOpenPriceQuoteData()
    {
        return openPriceQuoteData;
    }

    /**
     * @see DOTQuote#getHighPriceQuoteData()
     */
    public DOTQuoteData getHighPriceQuoteData()
    {
        return highPriceQuoteData;
    }

    /**
     * @see DOTQuote#getLowPriceQuoteData()
     */
    public DOTQuoteData getLowPriceQuoteData()
    {
        return lowPriceQuoteData;
    }

    /**
     * @see DOTQuote#getAllQuoteData()
     */
    public List getAllQuoteData()
    {
        return new ArrayList(quoteDataList);
    }

    /**
     * @see com.fitechlabs.fin.intellioms.quote.Quote#getTicker()
     */
    public Ticker getTicker()
    {
        return ticker;
    }

    /**
     * @see com.fitechlabs.fin.intellioms.quote.Quote#getStartTime()
     */
    public long getStartTime()
    {
        return openPriceQuoteData.getUpdatedTime().getTime();
    }

    /**
     * @see com.fitechlabs.fin.intellioms.quote.Quote#getEndTime()
     */
    public long getEndTime()
    {
        return lastPriceQuoteData.getUpdatedTime().getTime();
    }

    /**
     * @see com.fitechlabs.fin.intellioms.quote.Quote#getLastPrice()
     */
    public Price getLastPrice()
    {
        return toPrice(lastPriceQuoteData.getCurrentPrice());
    }

    /**
     * @see com.fitechlabs.fin.intellioms.quote.Quote#getOpenPrice()
     */
    public Price getOpenPrice()
    {
        return toPrice(openPriceQuoteData.getCurrentPrice());
    }

    /**
     * @see com.fitechlabs.fin.intellioms.quote.Quote#getBasePrice()
     */
    public Price getBasePrice()
    {
        throw new UnsupportedOperationException();
    }

    /**
     * @see com.fitechlabs.fin.intellioms.quote.Quote#getHighPrice()
     */
    public Price getHighPrice()
    {
        return toPrice(highPriceQuoteData.getCurrentPrice());
    }

    /**
     * @see com.fitechlabs.fin.intellioms.quote.Quote#getLowPrice()
     */
    public Price getLowPrice()
    {
        return toPrice(lowPriceQuoteData.getCurrentPrice());
    }

    /**
     * @see com.fitechlabs.fin.intellioms.quote.Quote#getAskPrice()
     */
    public Price getAskPrice()
    {
        throw new UnsupportedOperationException();
    }

    /**
     * @see com.fitechlabs.fin.intellioms.quote.Quote#getBidPrice()
     */
    public Price getBidPrice()
    {
        throw new UnsupportedOperationException();
    }

    /**
     * @see com.fitechlabs.fin.intellioms.quote.Quote#getAsks()
     */
    public BARecord[] getAsks()
    {
        throw new UnsupportedOperationException();
    }

    /**
     * @see com.fitechlabs.fin.intellioms.quote.Quote#getBids()
     */
    public BARecord[] getBids()
    {
        throw new UnsupportedOperationException();
    }

    /**
     * @see Object#toString()
     */
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer();
        l_sb.append("DOTQuoteImpl[");
        l_sb.append("ticker=").append(getTicker()).append(", ");
        l_sb.append("startTime=").append(new Timestamp(getStartTime())).append(", ");
        l_sb.append("endTime=").append(new Timestamp(getEndTime())).append(", ");
        l_sb.append("lastPrice=").append(getLastPrice()).append(", ");
        l_sb.append("openPrice=").append(getOpenPrice()).append(", ");
        l_sb.append("highPrice=").append(getHighPrice()).append(", ");
        l_sb.append("lowPrice=").append(getLowPrice());
        l_sb.append("]");
        return l_sb.toString();
    }

    /**
     * (set�������)<BR>
     * <BR>
     * �g���������X�i�b�v�V���b�g�Ɏw�肵����������ǉ�����B<BR>
     *
     * @param l_quoteData �������
     */
    private void setQuoteData(DOTQuoteData l_quoteData)
    {

        lastPriceQuoteData = l_quoteData;

        if (openPriceQuoteData == null)
        {
            openPriceQuoteData = l_quoteData;
        }

        double l_dblCurrentPrice = l_quoteData.getCurrentPrice();

        if (highPriceQuoteData == null
            || l_dblCurrentPrice >= highPriceQuoteData.getCurrentPrice())
        {
            highPriceQuoteData = l_quoteData;
        }

        if (lowPriceQuoteData == null
            || l_dblCurrentPrice <= lowPriceQuoteData.getCurrentPrice())
        {
            lowPriceQuoteData = l_quoteData;
        }

        quoteDataList.add(l_quoteData);

    }

    /**
     * (toPrice)<BR>
     * <BR>
     * �w�肵��double�l��Price�ɕϊ�����B<BR>
     *
     * @param l_dblValue double�l
     * @return �쐬����Price�B
     *         <code>Double.isNaN(�p�����[�^.double�l) == true</code>�̏ꍇ��
     *         <code>null</code>��Ԃ��B
     */
    private Price toPrice(double l_dblValue)
    {
        if (!Double.isNaN(l_dblValue))
        {
            return new Price(l_dblValue);
        } else
        {
            return null;
        }
    }

}
