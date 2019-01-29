head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.41.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	AbstractWEB3QuoteData.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3QuoteDataの基底クラス(AbstractWEB3QuoteData.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/18 山田　@卓司(FLJ) 新規作成
                 : 2005/03/30 山田　@卓司(FLJ) 値が取得できない場合は「0」を返すように変更（前日比以外）
                   2009/04/20 許　@　@　@　@競(FLJ) 時価システム切替対応
*/
package webbroker3.quoteadaptor.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import webbroker3.quoteadaptor.*;

/**
 * WEB3QuoteDataの基底抽象クラス<br>
 * <br>
 * WEB3QuoteDataの実装クラスで共通で必要となる処理を実装する。<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
abstract class AbstractWEB3QuoteData
    extends AbstractQuoteData
    implements WEB3QuoteData
{

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataHeader#getQuoteDate()
     */
    public Date getQuoteDate()
    {
        return WEB3QuoteUtil.toDate(quoteDate);
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataHeader#getRealType()
     */
    public RealType getRealType()
    {
        if (realType == null)
        {
            return RealType.UNDEFINED;
        }
        return realType;
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataHeader#getDataType()
     */
    public DataType getDataType()
    {
        if (dataType == null)
        {
            return DataType.UNDEFINED;
        }
        return dataType;
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataHeader#getMarketCode()
     */
    public String getMarketCode()
    {
        return marketCode;
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataHeader#getProductCode()
     */
    public String getProductCode()
    {
        return productCode;
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataHeader#getMonthOfDelivery()
     */
    public String getMonthOfDelivery()
    {
        return monthOfDelivery;
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataHeader#getPutAndCall()
     */
    public PutAndCall getPutAndCall()
    {
        if (putAndCall == null)
        {
            return PutAndCall.UNDEFINED;
        }
        return putAndCall;
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataHeader#getStrikePrice()
     */
    public double getStrikePrice()
    {
        return nanToZero(strikePrice);
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataBody#getOpenPrice()
     */
    public double getOpenPrice()
    {
        return nanToZero(openPrice);
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataBody#getOpenPriceTime()
     */
    public Timestamp getOpenPriceTime()
    {
        return WEB3QuoteUtil.toTimestamp(quoteDate, openPriceTime);
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataBody#getHighPrice()
     */
    public double getHighPrice()
    {
        return nanToZero(highPrice);
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataBody#getHighPriceTime()
     */
    public Timestamp getHighPriceTime()
    {
        return WEB3QuoteUtil.toTimestamp(quoteDate, highPriceTime);
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataBody#getLowPrice()
     */
    public double getLowPrice()
    {
        return nanToZero(lowPrice);
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataBody#getLowPriceTime()
     */
    public Timestamp getLowPriceTime()
    {
        return WEB3QuoteUtil.toTimestamp(quoteDate, lowPriceTime);
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataBody#getCurrentPrice()
     */
    public double getCurrentPrice()
    {
        return nanToZero(currentPrice);
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataBody#getCurrentPriceTime()
     */
    public Timestamp getCurrentPriceTime()
    {
        return WEB3QuoteUtil.toTimestamp(quoteDate, currentPriceTime);
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataBody#getCurrentPriceFlag()
     */
    public CurrentPriceFlag getCurrentPriceFlag()
    {
        if (currentPriceFlag == null)
        {
            return CurrentPriceFlag.NORMAL;
        }
        return currentPriceFlag;
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataBody#getChange()
     */
    public double getChange()
    {
        return change;
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataBody#getVolume()
     */
    public double getVolume()
    {
        return nanToZero(volume);
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataBody#getVolumeTime()
     */
    public Timestamp getVolumeTime()
    {
        return WEB3QuoteUtil.toTimestamp(quoteDate, volumeTime);
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataBody#getAskPriceTitle()
     */
    public AskPriceTitle getAskPriceTitle()
    {
        if (askPriceTitle == null)
        {
            return AskPriceTitle.UNDEFINED;
        }
        return askPriceTitle;
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataBody#getAskPrice()
     */
    public double getAskPrice()
    {
        return nanToZero(askPrice);
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataBody#getAskPriceTime()
     */
    public Timestamp getAskPriceTime()
    {
        return WEB3QuoteUtil.toTimestamp(quoteDate, askPriceTime);
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataBody#getBidPriceTitle()
     */
    public BidPriceTitle getBidPriceTitle()
    {
        if (bidPriceTitle == null)
        {
            return BidPriceTitle.UNDEFINED;
        }
        return bidPriceTitle;
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataBody#getBidPrice()
     */
    public double getBidPrice()
    {
        return nanToZero(bidPrice);
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataBody#getBidPriceTime()
     */
    public Timestamp getBidPriceTime()
    {
        return WEB3QuoteUtil.toTimestamp(quoteDate, bidPriceTime);
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataBody#getBasePrice()
     */
    public double getBasePrice()
    {
        return nanToZero(basePrice);
    }
    
    /**
     * 指定された値がDouble.NaNの場合、「0」を返す
     */
    protected double nanToZero(double value)
    {
        return (!Double.isNaN(value)) ? value : 0.0d;
    }

}
@
