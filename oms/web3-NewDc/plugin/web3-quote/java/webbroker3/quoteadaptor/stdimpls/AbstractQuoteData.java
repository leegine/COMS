head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.40.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	AbstractQuoteData.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 様々な時価情報の基底クラス(AbstractQuoteData.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/19 山田　@卓司(FLJ) 新規作成
                   2009/04/20 許　@　@　@　@競(FLJ) 時価システム切替対応
*/
package webbroker3.quoteadaptor.stdimpls;

import webbroker3.quoteadaptor.*;

/**
 * すべての時価情報の基底クラス<br>
 * 
 * @@author Takuji Yamada]
 * @@version 1.0
 */
abstract class AbstractQuoteData
{

    /** 株価日付 */
    String quoteDate;

    /** リアル区分 */
    RealType realType;

    /** 種別区分 */
    DataType dataType;

    /** 市場コード */
    String marketCode;

    /** 銘柄コード */
    String productCode;

    /** 限月 */
    String monthOfDelivery;

    /** プット＆コール */
    PutAndCall putAndCall;

    /** 行使価格 */
    double strikePrice;

    /** 始値 */
    double openPrice;

    /** 始値時刻 */
    String openPriceTime;

    /** 高値 */
    double highPrice;

    /** 高値時刻 */
    String highPriceTime;

    /** 安値 */
    double lowPrice;

    /** 安値時刻 */
    String lowPriceTime;

    /** 現在値 */
    double currentPrice;

    /** 現在値時刻 */
    String currentPriceTime;

    /** 現在値フラグ */
    CurrentPriceFlag currentPriceFlag;

    /** 前日比 */
    double change;

    /** 出来高 */
    double volume;

    /** 出来高時刻 */
    String volumeTime;

    /** 買気配値タイトル */
    AskPriceTitle askPriceTitle;

    /** 買気配値 */
    double askPrice;

    /** 買気配値時刻 */
    String askPriceTime;

    /** 売気配値タイトル */
    BidPriceTitle bidPriceTitle;

    /** 売気配値 */
    double bidPrice;

    /** 売気配値時刻 */
    String bidPriceTime;

    /** 基準値段 */
    double basePrice;

    /**
     * デフォルトコンストラクタ
     */
    protected AbstractQuoteData()
    {
        // doubleのプロパティはDouble.NaNで初期化
        realType = RealType.UNDEFINED;
        dataType = DataType.UNDEFINED;
        putAndCall = PutAndCall.UNDEFINED;
        strikePrice = Double.NaN;
        openPrice = Double.NaN;
        highPrice = Double.NaN;
        lowPrice = Double.NaN;
        currentPrice = Double.NaN;
        currentPriceFlag = CurrentPriceFlag.NORMAL;
        change = Double.NaN;
        volume = Double.NaN;
        askPriceTitle = AskPriceTitle.UNDEFINED;
        askPrice = Double.NaN;
        bidPriceTitle = BidPriceTitle.UNDEFINED;
        bidPrice = Double.NaN;
        basePrice = Double.NaN;
    }

    /**
     * Map等に登録する時のキーとなる文字列を生成する。
     * 前提条件として、キーを構成する要素に既に値がセットされていること。
     * 
     * @@return キーとなる文字列
     */
    String createKey()
    {
        StringBuffer sb = new StringBuffer();
        sb.append(String.valueOf(dataType.intValue()));
        if (!DataType.INDEX.equals(dataType))
        {
            sb.append(marketCode);
        }
        sb.append(productCode);
        if (DataType.INDEX_FUTURE.equals(dataType)
            || DataType.INDEX_OPTION.equals(dataType))
        {
            sb.append(monthOfDelivery);
            if (DataType.INDEX_OPTION.equals(dataType))
            {
                sb.append(putAndCall.stringValue());
                sb.append(String.valueOf(strikePrice));
            }

        }
        return sb.toString();
    }
    
    /**
     * 指定された時価情報からパラメータの値をセットする。
     * 
     * @@param from 元データ
     */
    void setParams(AbstractQuoteData from)
    {
        this.quoteDate = from.quoteDate;
        this.realType = from.realType;
        this.dataType = from.dataType;
        this.marketCode = from.marketCode;
        this.productCode = from.productCode;
        this.monthOfDelivery = from.monthOfDelivery;
        this.putAndCall = from.putAndCall;
        this.strikePrice = from.strikePrice;
        this.openPrice = from.openPrice;
        this.openPriceTime = from.openPriceTime;
        this.highPrice = from.highPrice;
        this.highPriceTime = from.highPriceTime;
        this.lowPrice = from.lowPrice;
        this.lowPriceTime = from.lowPriceTime;
        this.currentPrice = from.currentPrice;
        this.currentPriceTime = from.currentPriceTime;
        this.currentPriceFlag = from.currentPriceFlag;
        this.change = from.change;
        this.volume = from.volume;
        this.volumeTime = from.volumeTime;
        this.askPriceTitle = from.askPriceTitle;
        this.askPrice = from.askPrice;
        this.askPriceTime = from.askPriceTime;
        this.bidPriceTitle = from.bidPriceTitle;
        this.bidPrice  = from.bidPrice;
        this.bidPriceTime = from.bidPriceTime;
        this.basePrice = from.basePrice;
    }
    
    

    /**
     * @@see java.lang.Object#toString()
     */
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        sb.append("quoteDate=").append(quoteDate);
        sb.append(",realType=").append(realType);
        sb.append(",dataType=").append(dataType);
        sb.append(",marketCode=").append(marketCode);
        sb.append(",productCode=").append(productCode);
        sb.append(",monthOfDelivery=").append(monthOfDelivery);
        sb.append(",putAndCall=").append(putAndCall);
        sb.append(",strikePrice=").append(strikePrice);
        sb.append(",openPrice=").append(openPrice);
        sb.append(",openPriceTime=").append(openPriceTime);
        sb.append(",highPrice=").append(highPrice);
        sb.append(",highPriceTime=").append(highPriceTime);
        sb.append(",lowPrice=").append(lowPrice);
        sb.append(",lowPriceTime=").append(lowPriceTime);
        sb.append(",currentPrice=").append(currentPrice);
        sb.append(",currentPriceTime=").append(currentPriceTime);
        sb.append(",currentPriceFlag=").append(currentPriceFlag);
        sb.append(",change=").append(change);
        sb.append(",volume=").append(volume);
        sb.append(",volumeTime=").append(volumeTime);
        sb.append(",askPriceTitle=").append(askPriceTitle);
        sb.append(",askPrice=").append(askPrice);
        sb.append(",askPriceTime=").append(askPriceTime);
        sb.append(",bidPriceTitle=").append(bidPriceTitle);
        sb.append(",bidPrice=").append(bidPrice);
        sb.append(",bidPriceTime=").append(bidPriceTime);
        sb.append(",basePrice=").append(basePrice);
        sb.append("}");
        return sb.toString();
    }
}
@
