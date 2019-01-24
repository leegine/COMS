/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3QuoteDataImplクラス(DOTQuoteDataImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/06 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.quote.adaptor.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Timestamp;
import java.util.Date;

import jp.co.dir.dot.intellioms.enums.AskPriceTitle;
import jp.co.dir.dot.intellioms.enums.BidPriceTitle;
import jp.co.dir.dot.intellioms.enums.CurrentPriceFlag;
import jp.co.dir.dot.intellioms.enums.DataType;
import jp.co.dir.dot.intellioms.enums.PutAndCall;
import jp.co.dir.dot.intellioms.enums.RealType;
import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData;




/**
 * (時価情報Impl)<BR>
 * <BR>
 * 時価情報の実装クラス<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
class DOTQuoteDataImpl extends DOTBaseQuoteData implements DOTQuoteData
{
    
    // constructers -----------------------------------------------------------
    
    /**
     * コンストラクタ
     */
    DOTQuoteDataImpl()
    {
        super();
    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData#getQuoteDate()
     */
    public Date getQuoteDate()
    {
        return quoteDate;
    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData#getRealType()
     */
    public RealType getRealType()
    {
        return realType;
    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData#getDataType()
     */
    public DataType getDataType()
    {
        return dataType;
    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData#getMarketCode()
     */
    public String getMarketCode()
    {
        return marketCode;
    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData#getProductCode()
     */
    public String getProductCode()
    {
        return productCode;
    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData#getMonthOfDelivery()
     */
    public String getMonthOfDelivery()
    {
        return monthOfDelivery;
    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData#getPutAndCall()
     */
    public PutAndCall getPutAndCall()
    {
        return putAndCall;
    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData#getStrikePrice()
     */
    public double getStrikePrice()
    {
        return strikePrice;
    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData#getOpenPrice()
     */
    public double getOpenPrice()
    {
        return openPrice;
    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData#getOpenPriceTime()
     */
    public Timestamp getOpenPriceTime()
    {
        return openPriceTime;
    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData#getHighPrice()
     */
    public double getHighPrice()
    {
        return highPrice;
    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData#getHighPriceTime()
     */
    public Timestamp getHighPriceTime()
    {
        return highPriceTime;
    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData#getLowPrice()
     */
    public double getLowPrice()
    {
        return lowPrice;
    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData#getLowPriceTime()
     */
    public Timestamp getLowPriceTime()
    {
        return lowPriceTime;
    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData#getCurrentPrice()
     */
    public double getCurrentPrice()
    {
        return currentPrice;
    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData#getCurrentPriceTime()
     */
    public Timestamp getCurrentPriceTime()
    {
        return currentPriceTime;
    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData#getCurrentPriceFlag()
     */
    public CurrentPriceFlag getCurrentPriceFlag()
    {
        return currentPriceFlag;
    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData#getChange()
     */
    public double getChange()
    {
        return change;
    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData#getVolume()
     */
    public double getVolume()
    {
        return volume;
    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData#getVolumeTime()
     */
    public Timestamp getVolumeTime()
    {
        return volumeTime;
    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData#getAskPriceTitle()
     */
    public AskPriceTitle getAskPriceTitle()
    {
        return askPriceTitle;
    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData#getAskPrice()
     */
    public double getAskPrice()
    {
        return askPrice;
    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData#getAskPriceTime()
     */
    public Timestamp getAskPriceTime()
    {
        return askPriceTime;
    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData#getBidPriceTitle()
     */
    public BidPriceTitle getBidPriceTitle()
    {
        return bidPriceTitle;
    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData#getBidPrice()
     */
    public double getBidPrice()
    {
        return bidPrice;
    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData#getBidPriceTime()
     */
    public Timestamp getBidPriceTime()
    {
        return bidPriceTime;
    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData#getBasePrice()
     */
    public double getBasePrice()
    {
        return basePrice;
    }
    
    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData#getSequenceNo()
     */
    public long getSequenceNo()
    {
        return sequenceNo;
    }
    
    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData#getUpdatedTime()
     */
    public Timestamp getUpdatedTime()
    {
        return toTimestamp(updatedTime);
    }
    
    /**
     * 時価情報をシリアライズする。
     * 
     * WEB3BaseQuoteDataをSerializableに出来ないためカスタム実装。
     * @see java.io.Serializable
     */
    private void writeObject(ObjectOutputStream l_out) throws IOException
    {
        String l_strQuoteData = DOTQuoteFormatter.format(this);
        byte[] l_bytQuoteData = l_strQuoteData.getBytes();
        l_out.write(l_bytQuoteData);
    }

    /**
     * 時価情報をデシリアライズする。
     * 
     * WEB3BaseQuoteDataをSerializableに出来ないためカスタム実装。
     * @see java.io.Serializable
     */
    private void readObject(ObjectInputStream l_in) throws IOException,
        ClassNotFoundException
    {
        
        byte[] l_bytBuf = new byte[DOTQuoteFormats.AP_RECORD_SIZE];
        l_in.read(l_bytBuf);

        int l_intOffset = 0;

        long l_lngSequenceNo = DOTQuoteUtils.toLong(
            l_bytBuf,
            l_intOffset,
            DOTQuoteFormats.AP_SEQUENCE_NO_SIZE,
            0);
        l_intOffset += DOTQuoteFormats.AP_SEQUENCE_NO_SIZE;

        Timestamp l_updatedTime = DOTQuoteUtils.toTimestamp(
            l_bytBuf,
            12,
            DOTQuoteFormats.UPDATED_TIME_SIZE,
            DOTQuoteFormats.TIMESTAMP_FORMAT);
        long l_lngUpdatedTime = l_updatedTime != null ? l_updatedTime.getTime() : 0;
        l_intOffset += DOTQuoteFormats.UPDATED_TIME_SIZE;

        DOTQuoteEventImpl l_parser = new DOTQuoteEventImpl();
        l_parser.setData(
            l_bytBuf,
            l_intOffset,
            DOTQuoteConstants.RECORD_SIZE,
            l_lngSequenceNo,
            l_lngUpdatedTime);

        setParams(l_parser);
        
    }
    
}
