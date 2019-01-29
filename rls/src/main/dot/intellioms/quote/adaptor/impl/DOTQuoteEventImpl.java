/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3QuoteEventImplクラス(DOTQuoteEventImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/26 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.quote.adaptor.impl;

import java.sql.Timestamp;
import java.util.Date;


import com.fitechlabs.fin.intellioms.util.Log;

import jp.co.dir.dot.intellioms.enums.AskPriceTitle;
import jp.co.dir.dot.intellioms.enums.BidPriceTitle;
import jp.co.dir.dot.intellioms.enums.CurrentPriceFlag;
import jp.co.dir.dot.intellioms.enums.DataType;
import jp.co.dir.dot.intellioms.enums.PutAndCall;
import jp.co.dir.dot.intellioms.enums.RealType;
import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData;
import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteEvent;



/**
 * (時価イベントImpl)<BR>
 * <BR>
 * 時価イベントの実装クラス。
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
class DOTQuoteEventImpl extends DOTBaseQuoteData implements DOTQuoteEvent
{
    
    /** ロガー */
    private static final Log log = Log.getLogger(DOTQuoteEventImpl.class);
    
    /** 基準日 */
    private byte[] baseDate;

    /**
     * コンストラクタ
     */
    public DOTQuoteEventImpl()
    {
        super();
        baseDate = new byte[12];
    }
    
    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteEvent#getQuoteData()
     */
    public DOTQuoteData getQuoteData()
    {
        DOTQuoteDataImpl quoteData = new DOTQuoteDataImpl();
        quoteData.setParams(this);
        return quoteData;
    }
    
    /**
     * (setデータ)<BR>
     * <BR>
     * 指定したバイト配列から時価情報を読み込み各パラメータに値を設定する。<BR>
     * 
     * @param data 時価情報を保持するバイト配列
     * @param offset オフセット
     * @param length 長さ
     * @param sequenceNo シーケンスNO
     * @param updatedTime 更新時間
     * @return 読み込みに成功した場合は<code>true</code>、それ以外の場合は<code>false</code>
     */
    boolean setData(byte[] data, int offset, int length, long sequenceNo, long updatedTime)
    {
        
        int index = offset;
        setBaseDate(data, index, 8);
        
        try
        {
            // [1] 株価日付
            quoteDate = toDate(data, index);
            index += 8;

            // [2] リアル区分
            realType = toRealType(data, index);
            index += 1;

            // [3] 種別区分
            dataType = toDataType(data, index);
            index += 1;

            // [4] 市場コード
            marketCode = DOTQuoteUtils.toString(data, index, 2);
            index += 2;

            // [5] 銘柄コード
            productCode = toProductCode(data, index);
            index += 9;

            // [6] 限月
            monthOfDelivery = DOTQuoteUtils.toString(data, index, 6);
            index += 6;

            // [7] プット＆コール
            putAndCall = toPutAndCall(data, index);
            index += 1;

            // [8] 行使価格
            strikePrice = toDouble(data, index, 12);
            index += 12;

            // [9] 始値
            openPrice = toDouble(data, index, 12);
            index += 12;

            // [10] 始値時刻
            openPriceTime = toTimestamp(data, index);
            index += 4;

            // [11] 高値
            highPrice = toDouble(data, index, 12);
            index += 12;

            // [12] 高値時刻
            highPriceTime = toTimestamp(data, index);
            index += 4;

            // [13] 安値
            lowPrice = toDouble(data, index, 12);
            index += 12;

            // [14] 安値時刻
            lowPriceTime = toTimestamp(data, index);
            index += 4;

            // [15] 現在値
            currentPrice = toDouble(data, index, 12);
            index += 12;

            // [16] 現在値時刻
            currentPriceTime = toTimestamp(data, index);
            index += 4;

            // [17] 現在値フラグ
            currentPriceFlag = toCurrentPriceFlag(data, index);
            index += 1;

            // [18] 前日比
            change = toDouble(data, index, 12);
            index += 12;

            // [19] 出来高
            volume = toDouble(data, index, 12);
            index += 12;

            // [20] 出来高時刻
            volumeTime = toTimestamp(data, index);
            index += 4;

            // [21] 買気配値タイトル
            askPriceTitle = toAskPriceTitle(data, index);
            index += 1;

            // [22] 買気配値
            askPrice = toDouble(data, index, 12);
            index += 12;

            // [23] 買気配値時刻
            askPriceTime = toTimestamp(data, index);
            index += 4;

            // [24] 売気配値タイトル
            bidPriceTitle = toBidPriceTitle(data, index);
            index += 1;

            // [25] 売気配値
            bidPrice = toDouble(data, index, 12);
            index += 12;

            // [26] 売気配値時刻
            bidPriceTime = toTimestamp(data, index);
            index += 4;

            // [27] 基準値段
            basePrice = toDouble(data, index, 12);
            
            // シーケンスNO
            this.sequenceNo = sequenceNo;
            
            // 更新時間
            this.updatedTime = updatedTime;
            
            return true;
            
        } catch (Exception ex)
        {
            String message = "Unexpected exception occured while parsing quote data."
                + "[errorOffset=" + index
                + ", data=" + new String(data, offset, length) + "]";
            log.error(message, ex);
            return false;
        }
        
        
        
    }
    
    /**
     * (set基準日)<BR>
     * <BR> 
     * 基準日を設定する。<BR>
     * 
     * @param data 時価情報を保持するバイト配列
     * @param offset オフセット
     * @param length 長さ
     */
    private void setBaseDate(byte[] data, int offset, int length)
    {
        System.arraycopy(data, offset, baseDate, 0, length);
    }
    
    /**
     * (toリアル区分)<BR>
     * <BR>
     * リアル区分を取得する。<BR>
     * 
     * @param data 時価情報を保持するバイト配列
     * @param offset オフセット
     * @return リアル区分
     */
    private RealType toRealType(byte[] data, int offset)
    {
        int intValue = DOTQuoteUtils.toInteger(data, offset, 1, Integer.MIN_VALUE);
        return RealType.toRealType(intValue);
    }
    
    /**
     * (to種別コード)<BR>
     * <BR>
     * 種別コードを取得する。<BR>
     * 
     * @param data 時価情報を保持するバイト配列
     * @param offset オフセット
     * @return 種別コード
     */
    private DataType toDataType(byte[] data, int offset)
    {
        int intValue = DOTQuoteUtils.toInteger(data, offset, 1, Integer.MIN_VALUE);
        return DataType.toDataType(intValue);
    }
    
    /**
     * (to銘柄コード)<BR>
     * <BR>
     * 銘柄コードを取得する。<BR>
     * 
     * @param data 時価情報を保持するバイト配列
     * @param offset オフセット
     * @return 銘柄コード
     */
    private String toProductCode(byte[] data, int offset)
    {
        String productCode = null;
        if (DataType.EQUITY.equals(dataType))
        {
            productCode = DOTQuoteUtils.toString(data, offset, 5);
        } else
        {
            productCode = DOTQuoteUtils.toString(data, offset, 9);
        }
        return productCode;
    }
    
    /**
     * (toプット&コール)<BR>
     * <BR>
     * プット&コールを取得する。<BR>
     * 
     * @param data 時価情報を保持するバイト配列
     * @param offset オフセット
     * @return プット&コール
     */
    private PutAndCall toPutAndCall(byte[] data, int offset)
    {
        String stringValue = DOTQuoteUtils.toString(data, offset, 1);
        if (PutAndCall.PUT.toStringValue().equals(stringValue))
        {
            return PutAndCall.PUT;
        } else if (PutAndCall.CALL.toStringValue().equals(stringValue))
        {
            return PutAndCall.CALL;
        } else
        {
            return PutAndCall.UNDEFINED;
        }
    }
    
    /**
     * (to日付)<BR>
     * <BR>
     * 「yyyyMMdd」形式で日付を取得する。<BR>
     * 
     * @param data 時価情報を保持するバイト配列
     * @param offset オフセット
     * @return 日付
     */
    private Date toDate(byte[] data, int offset)
    {
        return DOTQuoteUtils.toDate(data, offset, 8, "yyyyMMdd");
    }
    
    /**
     * (toタイムスタンプ)<BR>
     * <BR>
     * 「yyyyMMddHHmm」形式でタイムスタンプを取得する。<BR>
     * 時価サーバから送信されるタイムスタンプには、「HHmm」部分しか含まれていないため、
     * 「yyyyMMdd」部分には基準日を使用する。<BR>
     * 
     * @param data 時価情報を保持するバイト配列
     * @param offset オフセット
     * @return タイムスタンプ
     */
    private Timestamp toTimestamp(byte[] data, int offset)
    {
        Timestamp timestamp = null;
        if (!DOTQuoteUtils.isBlank(data, offset, 4))
        {
            System.arraycopy(data, offset, baseDate, 8, 4);
            timestamp = DOTQuoteUtils.toTimestamp(baseDate, 0, 12, "yyyyMMddHHmm");
        }
        return timestamp;
    }
    
    /**
     * (toDouble)<BR>
     * <BR>
     * double値に変換する。<BR>
     * 
     * @param data 時価情報を保持するバイト配列
     * @param offset オフセット
     * @param length 長さ
     * @return double値
     */
    private double toDouble(byte[] data, int offset, int length)
    {
        return DOTQuoteUtils.toDouble(data, offset, length, Double.NaN);
    }
    
    /**
     * (to現在値フラグ)<BR>
     * <BR>
     * 現在値フラグを取得する。<BR>
     * 
     * @param data 時価情報を保持するバイト配列
     * @param offset オフセット
     * @return 現在値フラグ
     */
    private CurrentPriceFlag toCurrentPriceFlag(byte[] data, int offset)
    {
        int intValue = DOTQuoteUtils.toInteger(data, offset, 1, CurrentPriceFlag.IntValues.UNDEFINED);
        return CurrentPriceFlag.toCurrentPriceFlag(intValue);
    }
    
    /**
     * (to買気配値タイトル)<BR>
     * <BR>
     * 買気配タイトルを取得する。<BR>
     * 
     * @param data 時価情報を保持するバイト配列
     * @param offset オフセット
     * @return 買気配フラグ
     */
    private AskPriceTitle toAskPriceTitle(byte[] data, int offset)
    {
        int intValue = DOTQuoteUtils.toInteger(data, offset, 1, AskPriceTitle.IntValues.UNDEFINED);
        return AskPriceTitle.toAskPriceTitle(intValue);
    }
    
    /**
     * (to売気配値タイトル)<BR>
     * <BR>
     * 売気配値タイトルを取得する。<BR>
     * 
     * @param data 時価情報を保持するバイト配列
     * @param offset オフセット
     * @return 売気配値フラグ
     */
    private BidPriceTitle toBidPriceTitle(byte[] data, int offset)
    {
        int intValue = DOTQuoteUtils.toInteger(data, offset, 1, BidPriceTitle.IntValues.UNDEFINED);
        return BidPriceTitle.toBidPriceTitle(intValue);
    }
    
}
