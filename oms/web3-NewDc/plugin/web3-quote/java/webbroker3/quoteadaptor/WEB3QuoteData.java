head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.43.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteData.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 時価情報クラス(WEB3QuoteData.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/10 山田　@卓司(FLJ) 新規作成
                 : 2005/03/30 山田　@卓司(FLJ) メソッド定義をWEB3QuoteDataBodyとWEB3QuoteDataHeaderから移動
                 : 2005/03/30 山田　@卓司(FLJ) Javadocコメント修正 
*/
package webbroker3.quoteadaptor;

import java.sql.Timestamp;
import java.util.Date;

/**
 * <p>
 * WebBroker3の時価サービスが提供する時価情報のインターフェース。<br>
 * </p>
 * <p>
 * 時価サービスが提供する時価情報は、
 * このインターフェースと商品毎のトレーディングモジュールに定義された、
 * 商品毎の時価情報のインターフェース（<code>EqTypeQuoteData</code>など）を実装している。<br>
 * </p>
 * 
 * @@author Takuji Yamada
 * @@version 1.1
 */
public interface WEB3QuoteData extends WEB3QuoteDataBody, WEB3QuoteDataHeader
{

    /**
     * 時価データ取得日を取得する。
     * 
     * @@return 時価データ取得日
     */
    public Date getQuoteDate();

    /**
     * リアル区分を取得する。
     * 
     * @@return リアル区分
     */
    public RealType getRealType();

    /**
     * 種別コードを取得する。
     * 
     * @@return 種別コード
     */
    public DataType getDataType();

    /**
     * 市場コードを取得する。
     * 
     * @@return 市場コード
     */
    public String getMarketCode();

    /**
     * 銘柄コードを取得する。
     * 
     * @@return 銘柄コード
     */
    public String getProductCode();
    
    /**
     * 限月を取得する。<br>
     * 株式、指数の場合は、<code>null</code>を返す。
     * 
     * @@return 限月
     */
    public String getMonthOfDelivery();
    
    /**
     * プット＆コールを取得する。<br>
     * 株式、指数の場合は、<code>PutAndCall.UNDEFINED</code>を返す。
     * 
     * @@return プット＆コール
     */
    public PutAndCall getPutAndCall();
    
    /**
     * 行使価格を取得する。<br>
     * 株式、指数の場合は、「0」を返す。
     * 
     * @@return 行使価格
     */
    public double getStrikePrice();

    /**
     * 始値を取得する。<br>
     * 始値が設定されていない場合は、「0」を返す。<br>
     * 
     * @@return 初値
     */
    public double getOpenPrice();
    
    /**
     * 始値時刻を取得する。
     * 始値時刻が設定されていない場合は、<code>null</code>を返す。<br>
     * 
     * @@return 始値時刻
     */
    public Timestamp getOpenPriceTime();
    
    /**
     * 高値を取得する。
     * 高値が設定されていない場合は、「0」を返す。<br>
     * 
     * @@return 高値
     */
    public double getHighPrice();
    
    /**
     * 高値時刻を取得する。
     * 高値時刻が設定されていない場合は、<code>null</code>を返す。<br>
     * 
     * @@return 高値時刻
     */
    public Timestamp getHighPriceTime();
    
    /**
     * 安値を取得する。
     * 安値が設定されていない場合は、「0」を返す。<br>
     * 
     * @@return 安値
     */
    public double getLowPrice();
    
    /**
     * 安値時刻を取得する。
     * 安値時刻が設定されていない場合は、<code>null</code>を返す。<br>
     * 
     * @@return 安値
     */
    public Timestamp getLowPriceTime();
    
    /**
     * 現在値を取得する。
     * 現在値が設定されていない場合は、「0」を返す。<br>
     * 
     * @@return 現在値
     */
    public double getCurrentPrice();
    
    /**
     * 現在値時刻を取得する。
     * 現在値時刻が設定されていない場合は、<code>null</code>を返す。<br>
     * 
     * @@return 現在値時刻
     */
    public Timestamp getCurrentPriceTime();
    
    /**
     * 現在値フラグを取得する。
     * 
     * @@return 現在値時刻
     */
    public CurrentPriceFlag getCurrentPriceFlag();
    
    /**
     * 前日比を取得する。
     * 前日比が設定されていない場合は、<code>Double.NaN</code>を返す。<br>
     * 
     * @@return 前日比
     */
    public double getChange();
    
    /**
     * 出来高を取得する。
     * 出来高が設定されていない場合は、「0」を返す。<br>
     * 
     * @@return 出来高
     */
    public double getVolume();
    
    /**
     * 出来高時刻を取得する。
     * 出来高時刻が設定されていない場合は、<code>null</code>を返す。<br>
     * 
     * @@return 出来高時刻
     */
    public Timestamp getVolumeTime();
    
    /**
     * 買気配値タイトルを取得する。
     * 
     * @@return 買気配値タイトル
     */
    public AskPriceTitle getAskPriceTitle();
    
    /**
     * 買気配値を取得する。
     * 買気配値が設定されていない場合は、「0」を返す。<br>
     * 
     * @@return 買気配値
     */
    public double getAskPrice();
    
    /**
     * 買気配値時刻を取得する。
     * 買気配値時刻が設定されていない場合は、<code>null</code>を返す。<br>
     * 
     * @@return 買気配値時刻
     */
    public Timestamp getAskPriceTime();
    
    /**
     * 売気配値タイトルを取得する。
     * 
     * @@return 売気配値タイトル
     */
    public BidPriceTitle getBidPriceTitle();
    
    /**
     * 売気配値を取得する。
     * 売気配値が設定されていない場合は、「0」を返す。<br>
     * 
     * @@return 売気配値
     */
    public double getBidPrice();
    
    /**
     * 売気配値時刻を取得する。
     * 売気配値時刻が設定されていない場合は、<code>null</code>を返す。<br>
     * 
     * @@return 売気配値時刻
     */
    public Timestamp getBidPriceTime();
    
    /**
     * 基準値段を取得する。
     * 基準値段が設定されていない場合は、「0」を返す。<br>
     * 
     * @@return 基準値段を取得する。
     */
    public double getBasePrice();
    
}
@
