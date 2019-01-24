/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3QuoteDataクラス(DOTQuoteData.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/23 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.quote.adaptor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import jp.co.dir.dot.intellioms.enums.AskPriceTitle;
import jp.co.dir.dot.intellioms.enums.BidPriceTitle;
import jp.co.dir.dot.intellioms.enums.CurrentPriceFlag;
import jp.co.dir.dot.intellioms.enums.DataType;
import jp.co.dir.dot.intellioms.enums.PutAndCall;
import jp.co.dir.dot.intellioms.enums.RealType;




/**
 * (時価情報)<BR>
 * <BR>
 * 時価サーバから受信した1件の時価情報を表すクラス。<BR>
 * 
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public interface DOTQuoteData extends Serializable
{


    /**
     * (get時価データ取得日)<BR>
     * <BR>
     * 時価データ取得日を取得する。<BR>
     * 
     * @return 時価データ取得日
     */
    public Date getQuoteDate();

    /**
     * (getリアル区分)<BR>
     * <BR>
     * リアル区分を取得する。<BR>
     * 
     * @return リアル区分
     */
    public RealType getRealType();

    /**
     * (get種別コード)<BR>
     * <BR>
     * 種別コードを取得する。<BR>
     * 
     * @return 種別コード
     */
    public DataType getDataType();

    /**
     * (get市場コード)<BR>
     * <BR>
     * 市場コードを取得する。<BR>
     * 
     * @return 市場コード
     */
    public String getMarketCode();

    /**
     * (get銘柄コード)<BR>
     * <BR>
     * 銘柄コードを取得する。<BR>
     * 
     * @return 銘柄コード
     */
    public String getProductCode();
    
    /**
     * (get限月)<BR>
     * <BR>
     * 限月を取得する。<BR>
     * 株式、指数の場合は<code>null</code>を返す。<BR>
     * 
     * @return 限月
     */
    public String getMonthOfDelivery();
    
    /**
     * (getプット&コール)<BR>
     * <BR>
     * プット＆コールを取得する。<BR>
     * 株式、指数、株価指数先物の場合は<code>PutAndCall.UNDEFINED</code>を返す。<BR>
     * 
     * @return プット＆コール
     */
    public PutAndCall getPutAndCall();
    
    /**
     * (get行使価格)<BR>
     * <BR>
     * 行使価格を取得する。<BR>
     * 株式、指数、株価指数先物の場合は<code>Double.NaN</code>を返す。<BR>
     * 
     * @return 行使価格
     */
    public double getStrikePrice();

    /**
     * (get始値)<BR>
     * <BR>
     * 始値を取得する。<BR>
     * 始値が設定されていない場合は<code>Double.NaN</code>を返す。<BR>
     * 
     * @return 初値
     */
    public double getOpenPrice();
    
    /**
     * (get始値時刻)<BR>
     * <BR>
     * 始値時刻を取得する。<BR>
     * 始値時刻が設定されていない場合は<code>null</code>を返す。<BR>
     * 
     * @return 始値時刻
     */
    public Timestamp getOpenPriceTime();
    
    /**
     * (get高値)<BR>
     * <BR>
     * 高値を取得する。<BR>
     * 高値が設定されていない場合は<code>Double.NaN</code>を返す。<BR>
     * 
     * @return 高値
     */
    public double getHighPrice();
    
    /**
     * (get高値時刻)<BR>
     * <BR>
     * 高値時刻を取得する。<BR>
     * 高値時刻が設定されていない場合は<code>null</code>を返す。<BR>
     * 
     * @return 高値時刻
     */
    public Timestamp getHighPriceTime();
    
    /**
     * (get安値)<BR>
     * <BR>
     * 安値を取得する。<BR>
     * 安値が設定されていない場合は<code>Double.NaN</code>を返す。<BR>
     * 
     * @return 安値
     */
    public double getLowPrice();
    
    /**
     * (get安値時刻)<BR>
     * <BR>
     * 安値時刻を取得する。<BR>
     * 安値時刻が設定されていない場合は<code>null</code>を返す。<BR>
     * 
     * @return 安値
     */
    public Timestamp getLowPriceTime();
    
    /**
     * (get現在値)<BR>
     * <BR>
     * 現在値を取得する。<BR>
     * 現在値が設定されていない場合は<code>Double.NaN</code>を返す。<BR>
     *
     * @return 現在値
     */
    public double getCurrentPrice();
    
    /**
     * (get現在値時刻)<BR>
     * <BR>
     * 現在値時刻を取得する。<BR>
     * 現在値時刻が設定されていない場合は<code>null</code>を返す。<BR>
     *
     * @return 現在値時刻
     */
    public Timestamp getCurrentPriceTime();
    
    /**
     * (get現在値フラグ)<BR>
     * <BR>
     * 現在値フラグを取得する。<BR>
     *
     * @return 現在値時刻
     */
    public CurrentPriceFlag getCurrentPriceFlag();
    
    /**
     * (get前日比)<BR>
     * <BR>
     * 前日比を取得する。<BR>
     * 前日比が設定されていない場合は<code>Double.NaN</code>を返す。<BR>
     *
     * @return 前日比
     */
    public double getChange();
    
    /**
     * (get出来高)<BR>
     * <BR>
     * 出来高を取得する。<BR>
     * 出来高が設定されていない場合は<code>Double.NaN</code>を返す。<BR>
     *
     * @return 出来高
     */
    public double getVolume();
    
    /**
     * (get出来高時刻)<BR>
     * <BR>
     * 出来高時刻を取得する。<BR>
     * 出来高時刻が設定されていない場合は<code>null</code>を返す。<BR>
     *
     * @return 出来高時刻
     */
    public Timestamp getVolumeTime();
    
    /**
     * (get買気配値タイトル)<BR>
     * <BR>
     * 買気配値タイトルを取得する。<BR>
     *
     * @return 買気配値タイトル
     */
    public AskPriceTitle getAskPriceTitle();
    
    /**
     * (get買気配値)<BR>
     * <BR>
     * 買気配値を取得する。<BR>
     * 買気配値が設定されていない場合は<code>Double.NaN</code>を返す。<BR>
     *
     * @return 買気配値
     */
    public double getAskPrice();
    
    /**
     * (get買気配値時刻)<BR>
     * <BR>
     * 買気配値時刻を取得する。<BR>
     * 買気配値時刻が設定されていない場合は<code>null</code>を返す。<BR>
     *
     * @return 買気配値時刻
     */
    public Timestamp getAskPriceTime();
    
    /**
     * (get売気配値タイトル)<BR>
     * <BR>
     * 売気配値タイトルを取得する。<BR>
     *
     * @return 売気配値タイトル<BR>
     */
    public BidPriceTitle getBidPriceTitle();
    
    /**
     * (get売気配値)<BR>
     * <BR>
     * 売気配値を取得する。<BR>
     * 売気配値が設定されていない場合は<code>Double.NaN</code>を返す。<BR>
     *
     * @return 売気配値
     */
    public double getBidPrice();
    
    /**
     * (get売気配値時刻)<BR>
     * <BR>
     * 売気配値時刻を取得する。<BR>
     * 売気配値時刻が設定されていない場合は<code>null</code>を返す。<BR>
     *
     * @return 売気配値時刻
     */
    public Timestamp getBidPriceTime();
    
    /**
     * (get基準値段)<BR>
     * <BR>
     * 基準値段を取得する。<BR>
     * 基準値段が設定されていない場合は<code>Double.NaN</code>を返す。<BR>
     *
     * @return 基準値段を取得する。
     */
    public double getBasePrice();
    
    /**
     * (getシーケンスNO)<BR>
     * <BR>
     * シーケンスNOを取得する。<BR>
     *
     * @return シーケンスNO
     */
    public long getSequenceNo();
    
    /**
     * (get更新時間)<BR>
     * <BR>
     * 更新時間（時価情報を受信した時間）を取得する。<BR>
     *
     * @return 更新時間
     */
    public Timestamp getUpdatedTime();
    
}
