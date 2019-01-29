/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3QuoteFormatterクラス(DOTQuoteFormatter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/30 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.quote.adaptor.impl;

import java.sql.Timestamp;
import java.util.Date;

import jp.co.dir.dot.intellioms.enums.AskPriceTitle;
import jp.co.dir.dot.intellioms.enums.BidPriceTitle;
import jp.co.dir.dot.intellioms.enums.CurrentPriceFlag;
import jp.co.dir.dot.intellioms.enums.DataType;
import jp.co.dir.dot.intellioms.enums.RealType;
import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData;




/**
 * (時価情報フォーマッタ)<BR>
 * <BR>
 * 時価情報を固定長の文字列にフォーマットする。<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
class DOTQuoteFormatter
{
    
    /** 左寄せフラグ */
    private static final int ALIGNMENT_LEFT = 0;
    
    /** 右寄せフラグ */
    private static final int ALIGNMENT_RIGHT = 1;
    
    /** 株式銘柄コードの接尾辞 */
    private static final String EQUITY_PRODUCT_CODE_SUFFIX = "0000";

    /**
     * コンストラクタ
     */
    private DOTQuoteFormatter()
    {
    }
    
    /**
     * (format(時価情報))<BR>
     * <BR>
     * 時価情報を固定長の文字列にフォーマットする。<BR>
     * 
     * @param l_quoteData 時価情報
     * @return 時価情報をフォーマットした固定行の文字列を返す。
     */
    static String format(DOTQuoteData l_quoteData)
    {
        StringBuffer l_sb = new StringBuffer();
        format(l_sb, l_quoteData);
        return l_sb.toString();
    }
    
    /**
     * (format(StringBuffer, 時価情報))<BR>
     * <BR>
     * 時価情報をフォーマットした固定長の文字列を指定した文字列バッファに追加する。<BR>
     * 
     * @param sb 時価情報をフォーマットした文字列を追加する文字列バッファ
     * @param quoteData 時価情報
     */
    static void format(StringBuffer sb, DOTQuoteData quoteData)
    {
        appendLong(sb, quoteData.getSequenceNo(), DOTQuoteFormats.AP_SEQUENCE_NO_SIZE);
        appendTimestamp(sb, quoteData.getUpdatedTime(), DOTQuoteFormats.UPDATED_TIME_SIZE);
        appendDate(sb, quoteData.getQuoteDate(), DOTQuoteFormats.QUOTE_DATE_SIZE);
        appendRealType(sb, quoteData.getRealType());
        appendDataType(sb, quoteData.getDataType());
        appendString(sb, quoteData.getMarketCode(), DOTQuoteFormats.MARKET_CODE_SIZE);
        appendProductCode(sb, quoteData);
        appendMonthOfDelivery(sb, quoteData);
        appendPutAndCall(sb, quoteData);
        appendStrikePrice(sb, quoteData);
        appendDouble(sb, quoteData.getOpenPrice(), DOTQuoteFormats.OPEN_PRICE_SIZE);
        appendTime(sb, quoteData.getOpenPriceTime(), DOTQuoteFormats.OPEN_PRICE_TIME_SIZE);
        appendDouble(sb, quoteData.getHighPrice(), DOTQuoteFormats.HIGH_PRICE_SIZE);
        appendTime(sb, quoteData.getHighPriceTime(), DOTQuoteFormats.HIGH_PRICE_TIME_SIZE);
        appendDouble(sb, quoteData.getLowPrice(), DOTQuoteFormats.LOW_PRICE_SIZE);
        appendTime(sb, quoteData.getLowPriceTime(), DOTQuoteFormats.LOW_PRICE_TIME_SIZE);
        appendDouble(sb, quoteData.getCurrentPrice(), DOTQuoteFormats.CURRENT_PRICE_SIZE);
        appendTime(sb, quoteData.getCurrentPriceTime(), DOTQuoteFormats.CURRENT_PRICE_TIME_SIZE);
        appendCurrentPriceFlag(sb, quoteData.getCurrentPriceFlag());
        appendDouble(sb, quoteData.getChange(), DOTQuoteFormats.CHANGE_SIZE);
        appendDouble(sb, quoteData.getVolume(), DOTQuoteFormats.VOLUME_SIZE);
        appendTime(sb, quoteData.getVolumeTime(), DOTQuoteFormats.VOLUME_TIME_SIZE);
        appendAskPriceTitle(sb, quoteData.getAskPriceTitle());
        appendDouble(sb, quoteData.getAskPrice(), DOTQuoteFormats.ASK_PRICE_SIZE);
        appendTime(sb, quoteData.getAskPriceTime(), DOTQuoteFormats.ASK_PRICE_TIME_SIZE);
        appendBidPriceTitle(sb, quoteData.getBidPriceTitle());
        appendDouble(sb, quoteData.getBidPrice(), DOTQuoteFormats.BID_PRICE_SIZE);
        appendTime(sb, quoteData.getBidPriceTime(), DOTQuoteFormats.BID_PRICE_TIME_SIZE);
        appendDouble(sb, quoteData.getBasePrice(), DOTQuoteFormats.BASE_PRICE_SIZE);
    }
    
    /**
     * (appendString(StringBuffer, String, int, int))<BR>
     * <BR>
     * 指定した文字列バッファに文字列を追加する。<BR>
     * パラメータ.dataで指定した文字列の長さが、パラメータ.lengthで指定した
     * 文字列の長さより短い場合は、足りない分をブランク（<code>" "</code>）
     * を設定する。ブランクを設定する位置は、パラメータ.alignmentで指定する。
     * パラメータ.dataで指定した文字列の長さが、パラメータ.lengthで指定した
     * 文字列の長さより長い場合は、その分を切り捨てる。<BR>
     *  
     * @param sb 指定した文字列を追加するStringBuffer
     * @param data 追加する文字列
     * @param length 追加する文字列の長さ
     * @param alignment 0：左寄せ、1：右寄せ
     */
    private static void appendString(StringBuffer sb, String data, int length, int alignment)
    {
        int l = data != null ? data.length() : 0;
        if (l > length)
        {
            sb.append(data.substring(0, length));
        } else
        {
            if (ALIGNMENT_RIGHT == alignment)
            {
                appendBlank(sb, length - l);
            }
            if (l > 0)
            {
                sb.append(data);
            }
            if (ALIGNMENT_LEFT == alignment)
            {
                appendBlank(sb, length - l);
            }
        }
    }
    
    /**
     * (appendString(StringBuffer, String, int))<BR>
     * <BR>
     * 指定した文字列バッファに文字列を追加する。<BR>
     * 
     * @param sb 指定した文字列を追加するStringBuffer
     * @param data 追加する文字列
     * @param length 追加する文字列の長さ
     */
    private static void appendString(StringBuffer sb, String data, int length)
    {
        appendString(sb, data, length, ALIGNMENT_RIGHT);
    }
    
    /**
     * (appendBlank)<BR>
     * <BR>
     * 指定した文字列バッファにブランク（<code>" "</code>）を追加する。<BR>
     * 
     * @param sb ブランクを追加するStringBuffer
     * @param length 設定するブランクの長さ
     */
    private static void appendBlank(StringBuffer sb, int length)
    {
        for (int i = 0; i < length; i++)
        {
            sb.append(" ");
        }
    }
    
    /**
     * (appendDate)<BR>
     * <BR>
     * 指定したStringBufferに「yyyyMMdd」フォーマットで日付を追加する。<BR>
     * 
     * @param sb 指定した日付を追加するStringBuffer
     * @param date 追加する日付
     */
    private static void appendDate(StringBuffer sb, Date date, int length)
    {
        String s = null;
        if (date != null)
        {
            s = DOTQuoteUtils.getDateFormat(DOTQuoteFormats.DATE_FORMAT).format(date);
        }
        appendString(sb, s, length);
    }
    
    /**
     * (appendTimestamp)<BR>
     * <BR>
     * 指定した文字列バッファに「yyyy-MM-dd HH:mm:ss.SSS」フォーマットでタイムスタンプを追加する。<BR>
     * 
     * @param sb 指定したタイムスタンプを追加する文字列バッファ
     * @param timestamp 追加するタイムスタンプ
     */
    private static void appendTimestamp(StringBuffer sb, Timestamp timestamp, int length)
    {
        String s = null;
        if (timestamp != null)
        {
            s = DOTQuoteUtils.getDateFormat(DOTQuoteFormats.TIMESTAMP_FORMAT).format(timestamp);
        }
        appendString(sb, s, length);
    }
    
    /**
     * (appendTime)<BR>
     * <BR>
     * 指定した文字列バッファに「HHmm」フォーマットでタイムスタンプを追加する。<BR>
     * 
     * @param sb 指定した時間を追加する文字列バッファ
     * @param timestamp 追加するタイムスタンプ
     */
    private static void appendTime(StringBuffer sb, Timestamp timestamp, int length)
    {
        String s = null;
        if (timestamp != null)
        {
            s = DOTQuoteUtils.getDateFormat(DOTQuoteFormats.TIME_FORMAT).format(timestamp);
        }
        appendString(sb, s, length);
    }
    
    /**
     * (appendDouble)<BR>
     * <BR>
     * 指定した文字列バッファに右寄せでdouble値を追加する。<BR>
     * 
     * @param sb 指定したdouble値を追加する文字列バッファ
     * @param number 追加するdouble値
     * @param length 追加するdouble値の長さ
     */
    private static void appendDouble(StringBuffer sb, double number, int length)
    {
        String s = null;
        if (!Double.isNaN(number))
        {
            s = DOTQuoteUtils.getDecimalFormat(DOTQuoteFormats.DECIMAL_FORMAT).format(number);
        }
        appendString(sb, s, length);
    }
    
    /**
     * (appendLong)<BR>
     * <BR>
     * 指定した文字列バッファに右寄せでlong値を追加する。<BR>
     * 
     * @param sb 指定したlong値を追加する文字列バッファ
     * @param number 追加するlong値
     * @param length 追加するlong値の長さ
     */
    private static void appendLong(StringBuffer sb, long number, int length)
    {
        String s = String.valueOf(number);
        appendString(sb, s, length);
    }
    
    /**
     * (appendInteger)<BR>
     * <BR>
     * 指定した文字列バッファに右寄せでint値を追加する。<BR>
     * 
     * @param sb 指定したint値を追加する文字列バッファ
     * @param number 追加するint値
     * @param length 追加するint値の長さ
     */
    private static void appendInteger(StringBuffer sb, int number, int length)
    {
        String s = String.valueOf(number);
        appendString(sb, s, length);
    }
    
    /**
     * (appendリアル区分)<BR>
     * <BR>
     * 指定した文字列バッファにリアル区分のint値を追加する。<BR>
     * 
     * @param sb 指定したリアル区分を追加する文字列バッファ
     * @param realType 追加するリアル区分
     */
    private static void appendRealType(StringBuffer sb, RealType realType)
    {
        if (realType == null)
        {
            throw new IllegalStateException("realType must be not null.");
        }
        appendInteger(sb, realType.toValue(), DOTQuoteFormats.REAL_TYPE_SIZE);
    }
    
    /**
     * (append種別コード)<BR>
     * <BR>
     * 指定した文字列バッファに種別コードのint値を追加する。<BR>
     * 
     * @param sb 指定した種別コードを追加する文字列バッファ
     * @param realType 追加する種別コード
     */
    private static void appendDataType(StringBuffer sb, DataType dataType)
    {
        if (dataType == null)
        {
            throw new IllegalStateException("dataType must be not null.");
        }
        appendInteger(sb, dataType.toValue(), DOTQuoteFormats.REAL_TYPE_SIZE);
    }
    
    /**
     * (append銘柄コード)<BR>
     * <BR>
     * 指定した文字列バッファに指定した時価情報の銘柄コードを追加する。<BR>
     * 指定した時価情報の種別コードが株式の場合は銘柄コードに
     * <code>"0000"</code>を追加する。
     * 
     * @param sb 銘柄コードを追加する文字列バッファ
     * @param quoteData 時価情報
     */
    private static void appendProductCode(StringBuffer sb, DOTQuoteData quoteData)
    {
        if (DataType.EQUITY.equals(quoteData.getDataType()))
        {
            appendString(sb, quoteData.getProductCode(), (DOTQuoteFormats.PRODUCT_CODE_SIZE - EQUITY_PRODUCT_CODE_SUFFIX.length()));
            appendString(sb, EQUITY_PRODUCT_CODE_SUFFIX, EQUITY_PRODUCT_CODE_SUFFIX.length());
        } else
        {
            appendString(sb, quoteData.getProductCode(), DOTQuoteFormats.PRODUCT_CODE_SIZE, ALIGNMENT_LEFT);
        }
    }
    
    /**
     * (append限月)<BR>
     * <BR>
     * 指定した文字列バッファに指定した時価情報の限月を追加する。<BR>
     * 指定した時価情報の種別コードが株価指数先物か株価指数オプション以外の場合は
     * ブランク（<code>" "</code>）を設定する。<BR>
     * 
     * @param sb 限月を追加する文字列バッファ
     * @param quoteData 時価情報
     */
    private static void appendMonthOfDelivery(StringBuffer sb, DOTQuoteData quoteData)
    {
        switch (quoteData.getDataType().toValue())
        {
            case DataType.IntValues.INDEX_FUTURE :
            case DataType.IntValues.INDEX_OPTION :
                appendString(sb, quoteData.getMonthOfDelivery(), DOTQuoteFormats.MONTH_OF_DELIVERY_SIZE);
                return;
            default :
                appendBlank(sb, DOTQuoteFormats.MONTH_OF_DELIVERY_SIZE);
                return;
        }
    }
    
    /**
     * (appendプット&コール)<BR>
     * <BR>
     * 指定した文字列バッファに指定した時価情報のプット&コールを追加する。<BR>
     * 指定した時価情報の種別コードが株価指数オプション以外の場合は
     * ブランク（<code>" "</code>）を設定する。<BR>
     * 
     * @param sb プット&コールを追加する文字列バッファ
     * @param quoteData 時価情報
     */
    private static void appendPutAndCall(StringBuffer sb, DOTQuoteData quoteData)
    {
        switch (quoteData.getDataType().toValue())
        {
            case DataType.IntValues.INDEX_OPTION :
                appendString(sb, quoteData.getPutAndCall().toStringValue(), DOTQuoteFormats.PUT_AND_CALL_SIZE);
                return;
            default :
                appendBlank(sb, DOTQuoteFormats.PUT_AND_CALL_SIZE);
                return;
        }
    }
    
    /**
     * (append行使価格)<BR>
     * <BR>
     * 指定した文字列バッファに指定した時価情報の行使価格を追加する。<BR>
     * 指定した時価情報の種別コードが株価指数オプション以外の場合は
     * ブランク（<code>" "</code>）を設定する。<BR>
     * 
     * @param sb 行使価格を追加する文字列バッファ
     * @param quoteData 時価情報
     */
    private static void appendStrikePrice(StringBuffer sb, DOTQuoteData quoteData)
    {
        switch (quoteData.getDataType().toValue())
        {
            case DataType.IntValues.INDEX_OPTION :
                appendDouble(sb, quoteData.getStrikePrice(), DOTQuoteFormats.STRIKE_PRICE_SIZE);
                return;
            default :
                appendBlank(sb, DOTQuoteFormats.STRIKE_PRICE_SIZE);
                return;
        }
    }
    
    /**
     * (append現在値フラグ)<BR>
     * <BR>
     * 指定した文字列バッファに現在値フラグのint値を追加する。<BR>
     * 指定した現在値フラグが未定義の場合はブランク（<code>" "</code>）を設定する。<BR>
     * 
     * @param sb 指定した現在値フラグを追加する文字列バッファ
     * @param currentPriceFlag 現在値フラグ
     */
    private static void appendCurrentPriceFlag(StringBuffer sb, CurrentPriceFlag currentPriceFlag)
    {
        int intValue = currentPriceFlag != null ? 
            currentPriceFlag.toValue() : 
            CurrentPriceFlag.IntValues.UNDEFINED;
        if (CurrentPriceFlag.IntValues.UNDEFINED == intValue)
        {
            appendBlank(sb, DOTQuoteFormats.CURRENT_PRICE_FLAG_SIZE);
        } else
        {
            appendInteger(sb, intValue, DOTQuoteFormats.CURRENT_PRICE_FLAG_SIZE);
        }
    }
    
    /**
     * (append買気配値タイトル)<BR>
     * <BR>
     * 指定した文字列バッファに買気配値タイトルのint値を追加する。<BR>
     * 指定した買気配値タイトルが未定義の場合はブランク（<code>" "</code>）を設定する。<BR>
     * 
     * @param sb 指定した買気配値タイトルを追加する文字列バッファ
     * @param currentPriceFlag 買気配値タイトル
     */
    private static void appendAskPriceTitle(StringBuffer sb, AskPriceTitle askPriceTitle)
    {
        int intValue = askPriceTitle != null ? 
            askPriceTitle.toValue() : 
            AskPriceTitle.IntValues.UNDEFINED;
        if (AskPriceTitle.IntValues.UNDEFINED == intValue)
        {
            appendBlank(sb, DOTQuoteFormats.ASK_PRICE_TITLE_SIZE);
        } else
        {
            appendInteger(sb, intValue, DOTQuoteFormats.ASK_PRICE_TITLE_SIZE);
        }
    }
    
    /**
     * (append売気配値タイトル)<BR>
     * <BR>
     * 指定した文字列バッファに売気配値タイトルのint値を追加する。<BR>
     * 指定した売気配値タイトルが未定義の場合はブランク（<code>" "</code>）を設定する。<BR>
     * 
     * @param sb 指定した売気配値タイトルを追加する文字列バッファ
     * @param currentPriceFlag 売気配値タイトル
     */
    private static void appendBidPriceTitle(StringBuffer sb, BidPriceTitle bidPriceTitle)
    {
        int intValue = bidPriceTitle != null ? 
            bidPriceTitle.toValue() : 
            BidPriceTitle.IntValues.UNDEFINED;
        if (BidPriceTitle.IntValues.UNDEFINED == intValue)
        {
            appendBlank(sb, DOTQuoteFormats.BID_PRICE_TITLE_SIZE);
        } else
        {
            appendInteger(sb, intValue, DOTQuoteFormats.BID_PRICE_TITLE_SIZE);
        }
    }
    
}
