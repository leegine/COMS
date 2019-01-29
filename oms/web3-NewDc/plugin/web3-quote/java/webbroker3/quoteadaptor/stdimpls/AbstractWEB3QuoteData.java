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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3QuoteData�̊��N���X(AbstractWEB3QuoteData.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/18 �R�c�@@��i(FLJ) �V�K�쐬
                 : 2005/03/30 �R�c�@@��i(FLJ) �l���擾�ł��Ȃ��ꍇ�́u0�v��Ԃ��悤�ɕύX�i�O����ȊO�j
                   2009/04/20 ���@@�@@�@@�@@��(FLJ) �����V�X�e���֑ؑΉ�
*/
package webbroker3.quoteadaptor.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import webbroker3.quoteadaptor.*;

/**
 * WEB3QuoteData�̊�ꒊ�ۃN���X<br>
 * <br>
 * WEB3QuoteData�̎����N���X�ŋ��ʂŕK�v�ƂȂ鏈������������B<br>
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
     * �w�肳�ꂽ�l��Double.NaN�̏ꍇ�A�u0�v��Ԃ�
     */
    protected double nanToZero(double value)
    {
        return (!Double.isNaN(value)) ? value : 0.0d;
    }

}
@
