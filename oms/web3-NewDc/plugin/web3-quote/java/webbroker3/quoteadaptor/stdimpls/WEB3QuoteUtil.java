head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.40.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteUtil.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 * Copyright        : (株)大和総研 証券ソリューションシステム第二部
 * File Name        : 時価サービスで使用するユーティリティクラス(WEB3QuoteUtil.java)
 * Author Name      : Daiwa Institute of Research
 * Revision History : 2004/02/04 山田　@卓司(FLJ) 新規作成
 *                  : 2005/05/17 山田　@卓司(FLJ) 一部のメソッドのスコープを変更
 *                    2009/04/20 許　@　@　@　@競(FLJ) 時価システム切替対応
 */
package webbroker3.quoteadaptor.stdimpls;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import webbroker3.quoteadaptor.AskPriceTitle;
import webbroker3.quoteadaptor.BidPriceTitle;
import webbroker3.quoteadaptor.CurrentPriceFlag;
import webbroker3.quoteadaptor.DataType;
import webbroker3.quoteadaptor.PutAndCall;
import webbroker3.quoteadaptor.RealType;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

/**
 * WebBroker3の時価サービスで使用するユーティリティメソッドを定義。<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
class WEB3QuoteUtil
{
    
    static final String BLANK = " ";
    
    static final String LINE_SEPARATOR = System.getProperty("line.separator");
    
    private static final WEB3LogUtility _log = WEB3LogUtility.getInstance(WEB3QuoteUtil.class);
    
    private static final ThreadLocal decimalFormatPool = new ThreadLocal() {
        protected Object initialValue()
        {
            return new DecimalFormat(DECIMAL_FORMAT);
        }
    };
    
    private static final String DECIMAL_FORMAT = "#.##";
    
    static void dump(Hashtable cachedQuoteData)
    {
        
        _log.info("### 時価情報をファ@イルに出力します。 ###");
        
        Timestamp now = GtlUtils.getSystemTimestamp();
        String timestamp = GtlUtils.getThreadSafeSimpleDateFormat(
                "yyyyMMdd_HHmm").format(now);
        File file = null;
        BufferedWriter writer = null;
        try
        {
            file = new File("./web3_quote_cached-" + timestamp + ".txt");
            file.createNewFile();
            
            writer = new BufferedWriter(new FileWriter(file));
            
            Set entrySet = cachedQuoteData.entrySet();
            for (Iterator it = entrySet.iterator(); it.hasNext();)
            {
                Map.Entry entry = (Map.Entry) it.next();
                AbstractQuoteData quoteData = (AbstractQuoteData) entry.getValue();
                writer.write(format(quoteData));
                writer.write(LINE_SEPARATOR);
            }
            
            _log.info("### 時価情報をファ@イルに出力しました。 ファ@イル名 : "
                    + file.getName() + " ###");
            
        } catch (IOException ioe)
        {
            _log.error("###時価情報の出力中にエラーが発生しました。###", ioe);
        } finally
        {
            if (writer != null)
            {
                try
                {
                    writer.flush();
                    writer.close();
                } catch (IOException ioe)
                {
                }
            }
            
        }
        
    }
    
    /**
     * キャッシュされている時価情報をファ@イル主力用フォーマットに変換する。
     * 
     * @@param quoteData 時価情報
     * @@return ファ@イル出力用フォーマット文字列
     */
    static String format(AbstractQuoteData quoteData)
    {
        StringBuffer sb = new StringBuffer();
        append(sb, quoteData.quoteDate, WEB3QuoteRecordFormat.QUOTE_DATE_SIZE);
        append(sb, quoteData.realType, WEB3QuoteRecordFormat.REAL_TYPE_SIZE);
        append(sb, quoteData.dataType, WEB3QuoteRecordFormat.DATA_TYPE_SIZE);
        append(sb, quoteData.marketCode, WEB3QuoteRecordFormat.MARKET_CODE_SIZE);
        append(sb, getProductCode(quoteData), WEB3QuoteRecordFormat.PRODUCT_CODE_SIZE, true);
        append(sb, quoteData.monthOfDelivery, WEB3QuoteRecordFormat.MONTH_OF_DELIVERY_SIZE);
        append(sb, quoteData.putAndCall, WEB3QuoteRecordFormat.PUT_AND_CALL_SIZE);
        append(sb, quoteData.strikePrice, WEB3QuoteRecordFormat.STRIKE_PRICE_SIZE);
        append(sb, quoteData.openPrice, WEB3QuoteRecordFormat.OPEN_PRICE_SIZE);
        append(sb, quoteData.openPriceTime, WEB3QuoteRecordFormat.OPEN_PRICE_TIME_SIZE);
        append(sb, quoteData.highPrice, WEB3QuoteRecordFormat.HIGH_PRICE_SIZE);
        append(sb, quoteData.highPriceTime, WEB3QuoteRecordFormat.HIGH_PRICE_TIME_SIZE);
        append(sb, quoteData.lowPrice, WEB3QuoteRecordFormat.LOW_PRICE_SIZE);
        append(sb, quoteData.lowPriceTime, WEB3QuoteRecordFormat.LOW_PRICE_TIME_SIZE);
        append(sb, quoteData.currentPrice, WEB3QuoteRecordFormat.CURRENT_PRICE_SIZE);
        append(sb, quoteData.currentPriceTime, WEB3QuoteRecordFormat.CURRENT_PRICE_TIME_SIZE);
        append(sb, quoteData.currentPriceFlag, quoteData.currentPrice, WEB3QuoteRecordFormat.CURRENT_PRICE_FLAG_SIZE);
        append(sb, quoteData.change, WEB3QuoteRecordFormat.CHANGE_SIZE);
        append(sb, quoteData.volume, WEB3QuoteRecordFormat.VOLUME_SIZE);
        append(sb, quoteData.volumeTime, WEB3QuoteRecordFormat.VOLUME_TIME_SIZE);
        append(sb, quoteData.askPriceTitle, quoteData.askPrice, WEB3QuoteRecordFormat.ASK_PRICE_TITLE_SIZE);
        append(sb, quoteData.askPrice, WEB3QuoteRecordFormat.ASK_PRICE_SIZE);
        append(sb, quoteData.askPriceTime, WEB3QuoteRecordFormat.ASK_PRICE_TIME_SIZE);
        append(sb, quoteData.bidPriceTitle, quoteData.bidPrice, WEB3QuoteRecordFormat.BID_PRICE_TITLE_SIZE);
        append(sb, quoteData.bidPrice, WEB3QuoteRecordFormat.BID_PRICE_SIZE);
        append(sb, quoteData.bidPriceTime, WEB3QuoteRecordFormat.BID_PRICE_TIME_SIZE);
        append(sb, quoteData.basePrice, WEB3QuoteRecordFormat.BASE_PRICE_SIZE);
        return sb.toString();
    }
    
    /**
     * 指定されたバイト配列をStringに復号する。
     * 
     * @@param data バイト配列
     * @@param offset 変換するバイト配列の先頭のINDEX
     * @@param length 変換するバイト配列の長さ
     * @@return 復号した文字列
     */
    static String toString(byte[] data, int offset, int length, boolean useTrimming)
    {
        String s = null;
        try
        {
            s = new String(data, offset, length, WEB3QuoteConstants.ENCODING);
            if (s != null && useTrimming)
            {
                s = s.trim();
            }
            if ("".equals(s))
            {
                s = null;
            }
        } catch (UnsupportedEncodingException uee)
        {
            _log.error(uee.getMessage(), uee);
            
        }
        return s;
    }

    /**
     * 指定されたバイト配列をStringに復号する。
     * 
     * @@param data バイト配列
     * @@param offset 変換するバイト配列の先頭のINDEX
     * @@param length 変換するバイト配列の長さ
     * @@return 復号した文字列
     */
    static String toString(byte[] data, int offset, int length)
    {
        return toString(data, offset, length, true);
    }

    /**
     * 指定されたバイト配列をintに復号する。
     * 
     * @@param data バイト配列
     * @@param offset 変換するバイト配列の先頭のINDEX
     * @@param length 変換するバイト配列の長さ
     * @@return 復号したint
     */
    static int toInteger(byte[] data, int offset, int length)
    {
        int i = 0;
        String s = toString(data, offset, length);
        if (s != null && !"".equals(s))
        {
            try
            {
                i = Integer.parseInt(s);
            } catch (NumberFormatException nfe)
            {
            }
        }
        return i;
    }

    /**
     * 指定されたバイト配列をdoubleに復号する。
     * 
     * @@param data バイト配列
     * @@param offset 変換するバイト配列の先頭のINDEX
     * @@param length 変換するバイト配列の長さ
     * @@return 復号したdouble
     */
    static double toDouble(byte[] data, int offset, int length)
    {
        double d = Double.NaN;
        String s = toString(data, offset, length);
        if (s != null && !"".equals(s))
        {
            try
            {
                d = Double.parseDouble(s);
            } catch (NumberFormatException nfe)
            {
            }
        }
        return d;
    }


    /**
     * 指定されたバイト配列をDateに復号する。
     * yyyyMMdd形式の文字列を符号化したバイト配列を復号する。
     * 
     * @@param data バイト配列
     * @@param offset 変換するバイト配列の先頭のINDEX
     * @@param length 変換するバイト配列の長さ
     * @@return 復号したDate
     */
    static Date toDate(byte[] data, int offset, int length)
    {
        Date date = null;
        try
        {
            DateFormat df = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            date = df.parse(toString(data, offset, length));
        } catch (ParseException pe)
        {
        }
        return date;
    }
    
    /**
     * (toDate)<BR>
     * <BR>
     * @@return Date
     */
    static Date toDate(String data)
    {
        if (data == null)
        {
            return null;
        }
        
        Date date = null;
        try
        {
            DateFormat df = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            date = df.parse(data);
        } catch (ParseException pe)
        {
        }
        return date;
    }

    /**
     * 指定されたバイト配列をTimestampに復号する。
     * yyyyMMdd形式の文字列を符号化したバイト配列を復号する。
     * 
     * @@param data バイト配列
     * @@param offset 変換するバイト配列の先頭のINDEX
     * @@param length 変換するバイト配列の長さ
     * @@return 復号したTimestamp
     */
    static Timestamp toTimestamp(byte[] data, int offset, int length)
    {
        Timestamp timestamp = null;
        try
        {
            DateFormat df =
                GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMddHHmm");
            timestamp = new Timestamp(df.parse(toString(data, offset, length)).getTime());
        } catch (ParseException pe)
        {
        }
        return timestamp;
    }
    
    /**
     * (toTimestamp)<BR>
     * <BR>
     * @@return タイムスタンプ
     */
    static Timestamp toTimestamp(String date, String time)
    {
        if (date == null || time == null)
        {
            return null;
        }
        String l_newDateString = date + time;
        Date l_newDate;
        try{
            l_newDate = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMddHHmm").parse(l_newDateString);
        }catch(ParseException pe){
            throw new RuntimeException(pe);
        }
        return new Timestamp(l_newDate.getTime());
    }

    static void append(StringBuffer sb, String source, int size)
    {
        append(sb, source, size, false);
    }
    
    static void append(StringBuffer sb, String source, int size, boolean isPaddingLeft)
    {
        int length = source != null ? source.length() : 0;
        if (length > size)
        {
            sb.append(source.substring(0, size));
        } else {
            // 右詰めの場合
            if (!isPaddingLeft)
            {
                appendBlank(sb, size - length);
            }
            if (length > 0)
            {
                sb.append(source);
            }
            // 左詰めの場合
            if (isPaddingLeft)
            {
                appendBlank(sb, size - length);
            }
        }
    }
    
    private static void appendBlank(StringBuffer sb, int length)
    {
        for (int i = 0; i < length; i++)
        {
            sb.append(BLANK);
        }
    }
    
    private static void append(StringBuffer sb, Date source, int size)
    {

        String s = null;
        if (source != null)
        {
            DateFormat df = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd"); 
            s = df.format(source);
        }
        append(sb, s, size);
    }
    
    private static void append(StringBuffer sb, Timestamp source, int size)
    {
        String s = null;
        if (source != null)
        {
            DateFormat df = GtlUtils.getThreadSafeSimpleDateFormat("HHmm"); 
            s = df.format(source);
        }
        append(sb, s, size);
    }

    private static void append(StringBuffer sb, double source, int size)
    {
        String s = null;
        if (!Double.isNaN(source))
        {
            s = getDecimalFormat().format(source);
        }
        append(sb, s, size);
    }
    
    private static void append(StringBuffer sb, int source, int size)
    {
        String s = null;
        s = String.valueOf(source);
        append(sb, s, size);
    }
    
    private static void append(StringBuffer sb, RealType realType, int size)
    {
        if (realType != null 
            && RealType.IntValues.UNDEFINED != realType.intValue())
        {
            append(sb, realType.intValue(), size);
        } else {
            append(sb, BLANK, size);
        }
    }

    private static void append(StringBuffer sb, DataType dataType, int size)
    {
        if (dataType != null 
            && DataType.IntValues.UNDEFINED != dataType.intValue())
        {
            append(sb, dataType.intValue(), size);
        } else {
            append(sb, BLANK, size);
        }
    }

    private static void append(StringBuffer sb, PutAndCall putAndCall, int size)
    {
        if (putAndCall != null 
            && PutAndCall.IntValues.UNDEFINED != putAndCall.intValue())
        {
            append(sb, putAndCall.stringValue(), size);
        } else {
            append(sb, BLANK, size);
        }
    }

    private static void append(StringBuffer sb, CurrentPriceFlag currentPriceFlag, double currentPrice, int size)
    {
        if (currentPriceFlag != null && !Double.isNaN(currentPrice))
        {
            append(sb, currentPriceFlag.intValue(), size);
        } else {
            append(sb, BLANK, size);
        }
    }

    private static void append(StringBuffer sb, AskPriceTitle askPriceTitle, double askPrice, int size)
    {
        if (askPriceTitle != null 
            && AskPriceTitle.IntValues.UNDEFINED != askPriceTitle.intValue()
            && !Double.isNaN(askPrice))
        {
            append(sb, askPriceTitle.intValue(), size);
        } else {
            append(sb, BLANK, size);
        }
    }

    private static void append(StringBuffer sb, BidPriceTitle bidPriceTitle, double bidPrice, int size)
    {
        if (bidPriceTitle != null 
            && BidPriceTitle.IntValues.UNDEFINED != bidPriceTitle.intValue()
            && !Double.isNaN(bidPrice))
        {
            append(sb, bidPriceTitle.intValue(), size);
        } else {
            append(sb, BLANK, size);
        }
    }
    
    private static String getProductCode(AbstractQuoteData quoteData)
    {
        DataType dataType = quoteData.dataType;
        if (DataType.IntValues.EQUITY == dataType.intValue())
        {
            return quoteData.productCode + WEB3QuoteProductCodes.EQUITY_SUFFIX;
        } else {
            return quoteData.productCode;
        }
        
    }
    
    private static DecimalFormat getDecimalFormat()
    {
        return (DecimalFormat) decimalFormatPool.get();
    }
    
    /**
     * (toLong)<BR>
     * <BR>
     * 指定したバイト配列から復号化したlong値を返す。<BR>
     * 
     * @@param data 復号化されるバイト配列
     * @@param offset 復号化される先頭バイトのインデックス
     * @@param length 複合化するバイト数
     * @@return 復号化したint値
     * @@throws NumberFormatException
     */
    static long toLong(byte[] data, int offset, int length)
    {
        long l = 0;
        String s = toString(data, offset, length);
        if (s != null && !"".equals(s))
        {
            l = Long.parseLong(s);
        }
        return l;
    }

    /**
     * (getSystemDate)<BR>
     * <BR>
     * 指定した日付フォーマット（yyyyMMdd）でシステム日付を返す。<BR>
     * 
     * @@return システム日付
     */
    static String getSystemDate()
    {
        String date = null;
        DateFormat df = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
        date = df.format(GtlUtils.getSystemTimestamp());
        return date;
    }
}
@
