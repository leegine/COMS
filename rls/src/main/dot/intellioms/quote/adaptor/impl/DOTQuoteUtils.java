/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : QuoteUtilsクラス(DOTQuoteUtils.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/26 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.quote.adaptor.impl;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * (時価アダプタユーティリティ)<BR>
 * <BR>
 * 時価アダプタで使用するユーティリティメソッドを定義したクラス。<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
class DOTQuoteUtils
{
    
    /** ブランク */
    private static byte BLANK = 0;
    
    static
    {
        try
        {
            BLANK = " ".getBytes(DOTQuoteConstants.DEFAULT_ENCODING)[0];
        } catch (UnsupportedEncodingException uee)
        {
            throw new RuntimeException(uee);
        }
    }
    
    /** スレッドローカル日付フォーマットプール */
    private static final ThreadLocal DATE_FORMAT_POOL = new ThreadLocal() {
        protected Object initialValue()
        {
            return new HashMap();
        }
    };
    
    /** スレッドローカル数値フォーマットプール */
    private static final ThreadLocal DECIMAL_FORMAT_POOL = new ThreadLocal() {
        protected Object initialValue()
        {
            return new HashMap();
        }
    };
    
    /**
     * コンストラクタ<BR>
     */
    private DOTQuoteUtils()
    {
    }
    
    /**
     * (toString(byte, int, int, boolean))<BR>
     * <BR>
     * 指定されたバイト配列をStringに復号する。<BR>
     * 
     * @param data バイト配列
     * @param offset 変換するバイト配列の先頭のINDEX
     * @param length 変換するバイト配列の長さ
     * @param useTrimming 先頭と最後の空白は省略する場合<code>true</code>、
     *                    省略しない場合は、<code>false</code>
     * @return 復号した文字列
     */
    static String toString(byte[] data, int offset, int length, boolean useTrimming)
    {
        String s = null;
        try
        {
            s = new String(data, offset, length, DOTQuoteConstants.DEFAULT_ENCODING);
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
            throw new RuntimeException(uee);
        }
        return s;
    }

    /**
     * (toString(byte, int, int))<BR>
     * <BR>
     * 指定されたバイト配列をStringに復号する。<BR>
     * 
     * @param data バイト配列
     * @param offset 変換するバイト配列の先頭のINDEX
     * @param length 変換するバイト配列の長さ
     * @return 復号した文字列
     */
    static String toString(byte[] data, int offset, int length)
    {
        return toString(data, offset, length, true);
    }

    /** 空文字列 */
    private static final String EMPTY = "";
    
    /**
     * (is空文字列)<BR>
     * <BR>
     * 指定した文字列が<code>null</code>、または空文字列の場合に<code>true</code>、
     * それ以外の場合は<code>false</code>を返す。<BR>
     */
    static boolean isEmpty(String s)
    {
        return (s == null || EMPTY.equals(s));
    }
    
    /**
     * (toDouble)<BR>
     * <BR>
     * 指定したバイト配列から復号化したdouble値を返す。<BR>
     * 
     * @param data 復号化されるバイト配列
     * @param offset 復号化される先頭バイトのインデックス
     * @param length 複合化するバイト数
     * @param defaultValue 指定したバイト配列の範囲が全てブランクの場合に返されるdouble値
     * @return 復号化したdouble値
     * @throws NumberFormatException
     */
    static double toDouble(byte[] data, int offset, int length, double defaultValue)
    {
        double d = defaultValue;
        String s = toString(data, offset, length);
        if (s != null && !isEmpty(s))
        {
            d = Double.parseDouble(s);
        }
        return d;
    }
    
    /**
     * (toInteger)<BR>
     * <BR>
     * 指定したバイト配列から復号化したint値を返す。<BR>
     * 
     * @param data 復号化されるバイト配列
     * @param offset 復号化される先頭バイトのインデックス
     * @param length 複合化するバイト数
     * @param 指定したバイト配列の範囲が全てブランクの場合に返されるint値
     * @return 復号化したint値
     * @throws NumberFormatException
     */
    static int toInteger(byte[] data, int offset, int length, int defaultValue)
    {
        int i = defaultValue;
        String s = toString(data, offset, length);
        if (s != null && !isEmpty(s))
        {
            i = Integer.parseInt(s);
        }
        return i;
    }
    
    /**
     * (toLong)<BR>
     * <BR>
     * 指定したバイト配列から復号化したlong値を返す。<BR>
     * 
     * @param data 復号化されるバイト配列
     * @param offset 復号化される先頭バイトのインデックス
     * @param length 複合化するバイト数
     * @param defaultValue 指定したバイト配列の範囲が全てブランクの場合に返されるlong値
     * @return 復号化したint値
     * @throws NumberFormatException
     */
    static long toLong(byte[] data, int offset, int length, long defaultValue)
    {
        long l = defaultValue;
        String s = toString(data, offset, length);
        if (s != null && !isEmpty(s))
        {
            l = Long.parseLong(s);
        }
        return l;
    }
    
    /**
     * (toDate)<BR>
     * <BR>
     * 指定したバイト配列から復号化した日付を返す。<BR>
     * 
     * @param data 復号化されるバイト配列
     * @param offset 復号化される先頭バイトのインデックス
     * @param length 複合化するバイト数
     * @param pattern 日付パターン
     * @return 復号化した日付
     */
    static Date toDate(byte[] data, int offset, int length, String pattern)
    {
        Date d = null;
        String s = toString(data, offset, length);
        if (!isEmpty(s))
        {
            try
            {
                d = getDateFormat(pattern).parse(s);
            } catch (ParseException pe)
            {
                throw new RuntimeException(pe);
            }
        }
        return d;
    }
    
    /**
     * (toTimestamp)<BR>
     * <BR>
     * 指定したバイト配列から複合化したタイムスタンプを返す。<BR>
     * 
     * @param data 復号化されるバイト配列
     * @param offset 復号化される先頭バイトのインデックス
     * @param length 複合化するバイト数
     * @param pattern 日付パターン
     * @return 復号化したタイムスタンプ
     */
    static Timestamp toTimestamp(byte[] data, int offset, int length, String pattern)
    {
        Timestamp t = null;
        String s = toString(data, offset, length);
        if (!isEmpty(s))
        {
            try
            {
                long time = getDateFormat(pattern).parse(s).getTime();
                t = new Timestamp(time);
            } catch (ParseException pe)
            {
                throw new RuntimeException(pe);
            }
        }
        return t;
    }
    
    /**
     * (isBlank)<BR>
     * <BR>
     * 指定したバイト配列がブランクか判定する。<BR>
     * 
     * @param data バイト配列
     * @param offset インデックス
     * @param length バイト数
     * @return 指定したバイト配列の一部が全てブランクの場合に<code>true</code>、
     *  それ以外の場合は<code>false</code>を返す。
     */
    static boolean isBlank(byte[] data, int offset, int length)
    {
        for (int i = 0; i < length; i++)
        {
            if (BLANK != data[offset + i])
            {
                return false;
            }
        }
        return true;
    }

    /**
     * (get日付フォーマット)<BR>
     * <BR>
     * パラメータで指定したパターンの日付フォーマットを取得する。<BR>
     * 
     * @param pattern パターン
     * @return 日付フォーマット
     */
    static DateFormat getDateFormat(String pattern)
    {
        Map dateFormatMap = (Map) DATE_FORMAT_POOL.get();
        DateFormat dateFormat = (DateFormat) dateFormatMap.get(pattern);
        if (dateFormat == null)
        {
            dateFormat = new SimpleDateFormat(pattern);
            dateFormatMap.put(pattern, dateFormat);
        }
        return dateFormat;
    }
    
    /**
     * (get数値フォーマット)<BR>
     * <BR>
     * パラメータで指定したパターンの数値フォーマットを取得する。<BR>
     * 
     * @param pattern パターン
     * @return 数値フォーマット
     */
    static DecimalFormat getDecimalFormat(String pattern)
    {
        Map decimalFormatMap = (Map) DECIMAL_FORMAT_POOL.get();
        DecimalFormat decimalFormat = (DecimalFormat) decimalFormatMap.get(pattern);
        if (decimalFormat == null)
        {
            decimalFormat = new DecimalFormat(pattern);
            decimalFormatMap.put(pattern, decimalFormat);
        }
        return decimalFormat;
    }
    
}
