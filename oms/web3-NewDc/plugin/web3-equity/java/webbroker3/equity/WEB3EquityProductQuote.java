head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityProductQuote.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式銘柄時価情報(WEB3EquityProductQuote.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/06 中尾寿彦(SRA) 新規作成
Revesion History : 2005/01/05 岡村和明(SRA) JavaDoc修正
                   2006/07/04 周捷 (中訊) 仕様変更管理No.935
*/
package webbroker3.equity;

import java.sql.Timestamp;

/**
 * （株式銘柄時価情報）。<BR>
 * <BR>
 * 株式銘柄時価情報を保持するデータクラス。<BR>
 * 時価情報取得メソッドの戻り値を保持する。
 * @@version 1.0
 */
public class WEB3EquityProductQuote
{
    /**
     * (時価)<BR>
     * 時価。<BR>
     */
    private double quote;

    /**
     * (前日比)<BR>
     * 前日比。<BR>
     */
    private double comparedPreviousDay;

    /**
     * (時価発表時間)<BR>
     * 時価発表時間。<BR>
     */
    private Timestamp quoteTime;

    /**
     * (時価取得区分)<BR>
     * 時価取得区分。<BR>
     * （1：時価　@2：前日終値　@3：当日終値）<BR>
     */
    private String quoteFromDiv;

    /**
     * (時価区分)<BR>
     * 時価区分。 <BR>
     * （1：現在値　@2：売気配値　@3：買気配値　@4：前日終値）<BR>
     */
    private String quoteTypeDiv;
    
    /**
     * (市場コード)<BR>
     * 市場コード。<BR>
     */
    private String marketCode;

    /**
     * （現在値）<BR>
     * 現在値<BR>
     */
    private String boardCurrentPrice;
    
    /**
     * （現在値時刻）<BR>
     * 現在値時刻<BR>
     */
    private Timestamp boardCurrentPriceTime;
    
    /**
     * （現在値区分）<BR>
     * 現在値区分 <BR>
     *（0：通常 <BR>
     *　@1：終値）<BR>
     */
    private String boardCurrentPriceDiv;
    
    /**
     * （現在値前日比）<BR>
     * 現在値前日比<BR>
     */
    private String boardChange;
    
    /**
     * （出来高）<BR>
     * 出来高<BR>
     */
    private String volume;
    
    /**
     * （出来高時刻）<BR>
     * 出来高時刻<BR>
     */
    private Timestamp volumeTime;
    
    /**
     * （買気配値タイトル区分）<BR>
     * 買気配値タイトル区分 <BR>
     *（1：買気配 <BR>
     *　@2：売買停止 <BR>
     *　@3：板寄せ中 <BR>
     *　@5：特別気配）<BR>
     */
    private String askPriceTitle;
    
    /**
     * （買気配値）<BR>
     * 買気配値<BR>
     */
    private String askPrice;
    
    /**
     * （買気配値時刻）<BR>
     * 買気配値時刻<BR>
     */
    private Timestamp askPriceTime;
    
    /**
     * （売気配値タイトル区分）<BR>
     * 売気配値タイトル区分 <BR>
     * （3：板寄せ中 <BR>
     * 　@4：売気配 <BR>
     * 　@5：特別気配）<BR>
     */
    private String bidPriceTitle;
    
    /**
     * （売気配値）<BR>
     * 売気配値<BR>
     */
    private String bidPrice;
    
    /**
     * （売気配値時刻）<BR>
     * 売気配値時刻<BR>
     */
    private Timestamp bidPriceTime;
    
    /**
     * （基準値段）<BR>
     * 基準値段<BR>
     */
    private String basePrice;

    /**
     * コンストラクタ。<BR>
     */
    public WEB3EquityProductQuote()
    {
    }

    /**
     * (set時価)<BR>
     * <BR>
     * 時価をセットする。<BR>
     * <BR>
     * @@param l_dblQuote - (時価)
     */
    public void setQuote(double l_dblQuote)
    {
        this.quote = l_dblQuote;
    }

    /**
     * (get時価)<BR>
     * <BR>
     * 時価を取得する。<BR>
     * @@return double
     */
    public double getQuote()
    {
        return this.quote;
    }

    /**
     * (set前日比)<BR>
     * <BR>
     * 前日比をセットする。<BR>
     * <BR>
     * @@param l_dblComparedPreviousDay - (前日比)
     */
    public void setComparedPreviousDay(double l_dblComparedPreviousDay)
    {
        this.comparedPreviousDay = l_dblComparedPreviousDay;
    }

    /**
     * (get前日比)<BR>
     * <BR>
     * 前日比を取得する。<BR>
     * @@return double
     */
    public double getComparedPreviousDay()
    {
        return this.comparedPreviousDay;
    }

    /**
     * (set時価発表時間)<BR>
     * <BR>
     * 時価発表時間をセットする。<BR>
     * <BR>
     * @@param l_tsQuoteTime - (時価発表時間)
     */
    public void setQuoteTime(Timestamp l_tsQuoteTime)
    {
        this.quoteTime = l_tsQuoteTime;
    }

    /**
     * (get時価発表時間)<BR>
     * <BR>
     * 時価発表時間を取得する。<BR>
     * @@return Timestamp
     */
    public Timestamp getQuoteTime()
    {
        return this.quoteTime;
    }

    /**
     * (set時価取得区分)<BR>
     * <BR>
     * 時価取得区分をセットする。<BR>
     * <BR>
     * @@param l_strQuoteFromDiv - (時価取得区分)
     */
    public void setQuoteFromDiv(String l_strQuoteFromDiv)
    {
        this.quoteFromDiv = l_strQuoteFromDiv;
    }

    /**
     * (get時価取得区分)<BR>
     * <BR>
     * 時価取得区分を取得する。<BR>
     * @@return String
     */
    public String getQuoteFromDiv()
    {
        return this.quoteFromDiv;
    }

    /**
     * (set時価区分)<BR>
     * <BR>
     * 時価区分をセットする。<BR>
     * <BR>
     * @@param l_strQuoteTypeDiv - (時価区分)
     */
    public void setQuoteTypeDiv(String l_strQuoteTypeDiv)
    {
        this.quoteTypeDiv = l_strQuoteTypeDiv;
    }

    /**
     * (get時価区分)<BR>
     * <BR>
     * 時価区分を取得する。<BR>
     * @@return String
     */
    public String getQuoteTypeDiv()
    {
        return this.quoteTypeDiv;
    }

    /**
     * (set市場コード)<BR>
     * <BR>
     * 市場コードをセットする。<BR>
     * <BR>
     * @@param l_strMarketCode - (市場コード)
     */
    public void setMarketCode(String l_strMarketCode)
    {
        this.marketCode = l_strMarketCode;
    }

    /**
     * (get市場コード)<BR>
     * <BR>
     * 市場コードを取得する。<BR>
     * @@return String
     */
    public String getMarketCode()
    {
        return this.marketCode;
    }
    
    /**
     * (set現在値)<BR>
     * 現在値をセットする。<BR>
     * <BR>
     * @@param l_strBoardCurrentPrice - (現在値)<BR>
     * 現在値。
     */
    public void setBoardCurrentPrice(String l_strBoardCurrentPrice)
    {
        this.boardCurrentPrice = l_strBoardCurrentPrice;
    }
    
    /**
     * (get現在値)<BR>
     * <BR>
     * 現在値を取得する。<BR>
     * @@return String
     */
    public String getBoardCurrentPrice()
    {
        return this.boardCurrentPrice;
    }
    
    /**
     * (set現在値時刻)<BR>
     * 現在値時刻をセットする。<BR>
     * <BR>
     * @@param l_tsBoardCurrentPriceTime - (現在値時刻)<BR>
     * 現在値時刻。
     */
    public void setBoardCurrentPriceTime(Timestamp l_tsBoardCurrentPriceTime)
    {
        this.boardCurrentPriceTime = l_tsBoardCurrentPriceTime;
    }
    
    /**
     * (get現在値時刻)<BR>
     * <BR>
     * 現在値時刻を取得する。<BR>
     * @@return Timestamp
     */
    public Timestamp getBoardCurrentPriceTime()
    {
        return this.boardCurrentPriceTime;
    }
    
    /**
     * (set現在値区分)<BR>
     * 現在値区分をセットする。<BR>
     * <BR>
     * @@param l_strBoardCurrentPriceDiv - (現在値区分)<BR>
     * 現在値区分。
     */
    public void setBoardCurrentPriceDiv(String l_strBoardCurrentPriceDiv)
    {
        this.boardCurrentPriceDiv = l_strBoardCurrentPriceDiv;
    }
    
    /**
     * (get現在値区分)<BR>
     * <BR>
     * 現在値区分を取得する。<BR>
     * @@return String
     */
    public String getBoardCurrentPriceDiv()
    {
        return this.boardCurrentPriceDiv;
    }
    
    /**
     * (set現在値前日比)<BR>
     * 現在値前日比をセットする。<BR>
     * <BR>
     * @@param l_strBoardChange - (現在値前日比)<BR>
     * 現在値前日比。
     */
    public void setBoardChange(String l_strBoardChange)
    {
        this.boardChange = l_strBoardChange;
    }
    
    /**
     * (get現在値前日比)<BR>
     * <BR>
     * 現在値前日比を取得する。<BR>
     * @@return String
     */
    public String getBoardChange()
    {
        return this.boardChange;
    }
    
    /**
     * (set出来高)<BR>
     * 出来高をセットする。<BR>
     * <BR>
     * @@param l_strVolume - (出来高)<BR>
     * 出来高。
     */
    public void setVolume(String l_strVolume)
    {
        this.volume = l_strVolume;
    }
    
    /**
     * (get出来高)<BR>
     * <BR>
     * 出来高を取得する。<BR>
     * @@return String
     */
    public String getVolume()
    {
        return this.volume;
    }
    
    /**
     * (set出来高時刻)<BR>
     * 出来高時刻をセットする。<BR>
     * <BR>
     * @@param l_tsBoardChange - (出来高時刻)<BR>
     * 出来高時刻。
     */
    public void setVolumeTime(Timestamp l_tsVolumeTime)
    {
        this.volumeTime = l_tsVolumeTime;
    }
    
    /**
     * (get出来高時刻)<BR>
     * <BR>
     * 出来高時刻を取得する。<BR>
     * @@return Timestamp
     */
    public Timestamp getVolumeTime()
    {
        return this.volumeTime;
    }
    
    /**
     * (set買気配値タイトル区分)<BR>
     * 買気配値タイトル区分をセットする。<BR>
     * <BR>
     * @@param l_strAskPriceTitle - (買気配値タイトル区分)<BR>
     * 買気配値タイトル区分。
     */
    public void setAskPriceTitle(String l_strAskPriceTitle)
    {
        this.askPriceTitle = l_strAskPriceTitle;
    }
    
    /**
     * (get買気配値タイトル区分)<BR>
     * <BR>
     * 買気配値タイトル区分を取得する。<BR>
     * @@return String
     */
    public String getAskPriceTitle()
    {
        return this.askPriceTitle;
    }
    
    /**
     * (set買気配値)<BR>
     * 買気配値をセットする。<BR>
     * <BR>
     * @@param l_strAskPrice - (買気配値)<BR>
     * 買気配値。
     */
    public void setAskPrice(String l_strAskPrice)
    {
        this.askPrice = l_strAskPrice;
    }
    
    /**
     * (get買気配値)<BR>
     * <BR>
     * 買気配値を取得する。<BR>
     * @@return String
     */
    public String getAskPrice()
    {
        return this.askPrice;
    }
    
    /**
     * (set買気配値時刻)<BR>
     * 買気配値時刻をセットする。<BR>
     * <BR>
     * @@param l_tsAskPriceTime - (買気配値時刻)<BR>
     * 買気配値時刻。
     */
    public void setAskPriceTime(Timestamp l_tsAskPriceTime)
    {
        this.askPriceTime = l_tsAskPriceTime;
    }
    
    /**
     * (get買気配値時刻)<BR>
     * <BR>
     * 買気配値時刻を取得する。<BR>
     * @@return Timestamp
     */
    public Timestamp getAskPriceTime()
    {
        return this.askPriceTime;
    }
    
    /**
     * (set売気配値タイトル区分)<BR>
     * 売気配値タイトル区分をセットする。<BR>
     * <BR>
     * @@param l_strBidPriceTitle - (売気配値タイトル区分)<BR>
     * 売気配値タイトル区分。
     */
    public void setBidPriceTitle(String l_strBidPriceTitle)
    {
        this.bidPriceTitle = l_strBidPriceTitle;
    }
    
    /**
     * (get売気配値タイトル区分)<BR>
     * <BR>
     * 売気配値タイトル区分を取得する。<BR>
     * @@return String
     */
    public String getBidPriceTitle()
    {
        return this.bidPriceTitle;
    }
    
    /**
     * (set売気配値)<BR>
     * 売気配値をセットする。<BR>
     * <BR>
     * @@param l_strBidPrice - (売気配値)<BR>
     * 売気配値。
     */
    public void setBidPrice(String l_strBidPrice)
    {
        this.bidPrice = l_strBidPrice;
    }
    
    /**
     * (get売気配値)<BR>
     * <BR>
     * 売気配値を取得する。<BR>
     * @@return String
     */
    public String getBidPrice()
    {
        return this.bidPrice;
    }
    
    /**
     * (set売気配値時刻)<BR>
     * 売気配値時刻をセットする。<BR>
     * <BR>
     * @@param l_tsBidPriceTime - (売気配値時刻)<BR>
     * 売気配値時刻。
     */
    public void setBidPriceTime(Timestamp l_tsBidPriceTime)
    {
        this.bidPriceTime = l_tsBidPriceTime;
    }
    
    /**
     * (get売気配値時刻)<BR>
     * <BR>
     * 売気配値時刻を取得する。<BR>
     * @@return Timestamp
     */
    public Timestamp getBidPriceTime()
    {
        return this.bidPriceTime;
    }
    
    /**
     * (set基準値段)<BR>
     * 基準値段をセットする。<BR>
     * <BR>
     * @@param l_strBasePrice - (基準値段)<BR>
     * 基準値段。
     */
    public void setBasePrice(String l_strBasePrice)
    {
        this.basePrice = l_strBasePrice;
    }
    
    /**
     * (get基準値段)<BR>
     * <BR>
     * 基準値段を取得する。<BR>
     * @@return String
     */
    public String getBasePrice()
    {
        return this.basePrice;
    }
}
@
