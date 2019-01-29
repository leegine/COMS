head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DateUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 日付型のデータのユーティリティ(WEB3DateUtility.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 張宝楠 (中訊) 新規作成
Revesion History : 2004/09/08 張宝楠 (中訊) toDay(Date)を追加
Revesion History : 2004/09/09 張宝楠 (中訊) compareToMonth(Date, Date)を追加
Revesion History : 2004/09/09 張宝楠 (中訊) compareToYear(Date, Date)を追加
Revesion History : 2004/09/13 張宝楠 (中訊) toDay(Date)の返却を修正
Revesion History : 2004/09/23 張宝楠 (中訊) compare(Date, Date)を追加
Revesion History : 2007/02/07 齊珂   (中訊) addMonth(Date, int)を追加
Revesion History : 2008/11/05 SRA水落       addMonth(Date, int)にsynchronizedを追加
*/

package webbroker3.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

/**
 * 日付型のデータの処理を行う関数を持つユーティリティクラス。<BR>
 * <BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3DateUtility
{

    private static Calendar gCalendar = new GregorianCalendar();

    /**
     * 文字列を日付型データに型変換して返却します。
     *
     * @@param l_str 文字列
     * @@param l_strPattern フォーマット
     * @@return 入力されたstrは指定したフォーマットの日付なら、<BR>
     * 変換後の日付型データを返却する。そうでないとfalseを返す。
     */
    public static Date getDate(String l_str, String l_strPattern)
    {

        if (l_str == null || l_strPattern == null)
        {
            return null;
        }

        SimpleDateFormat l_dateFormat =
            GtlUtils.getThreadSafeSimpleDateFormat(l_strPattern);
        l_dateFormat.setLenient(false);

        try
        {
            return l_dateFormat.parse(l_str);
        }
        catch (ParseException ex)
        {
            return null;
        }
    }

    /**
     * 日付型データを指定したフォーマットで文字列に型変換します。
     *
     * @@param l_dat 日付型データ
     * @@param l_strPattern フォーマット
     * @@return 変換後の文字列を返す。
     */
    public static String formatDate(Date l_dat, String l_strPattern)
    {

        if (l_dat == null || l_strPattern == null)
        {
            return "";
        }

        SimpleDateFormat l_dateFormat =
            GtlUtils.getThreadSafeSimpleDateFormat(l_strPattern);

        return l_dateFormat.format(l_dat);
    }
    
    /**
     * 入力した日付に指定した月数をプラスし、返却します。
     *
     * @@param l_dat        日付　@     
     * @@param l_intMonth   月数
     * @@return 計算後の結果を返却する。
     */
    public static synchronized Date addMonth(Date l_dat, int l_intMonth)
    {
        if (l_dat == null)
        {
            return null;
        }
        
        gCalendar.setTime(l_dat);
        gCalendar.add(Calendar.MONTH, l_intMonth);
        
        return gCalendar.getTime();
    }
    
    /**
     * 入力した日付に指定した日数をプラスし、返却します。
     *
     * @@param l_dat    日付
     * @@param l_lngDay 日数
     * @@return 計算後の結果を返却する。
     */
    public static Date addDay(Date l_dat, long l_lngDay)
    {
        if (l_dat == null)
        {
            return null;
        }

        long l_lngTmpTime = l_dat.getTime();
        long l_lngNewTime = l_lngTmpTime + l_lngDay * 24 * 60 * 60 * 1000;

        return new Date(l_lngNewTime);
    }

    /**
     * 入力した日付に指定した時間数をプラスし、返却します。
     *
     * @@param l_dat     日付
     * @@param l_lngHour 時間数
     * @@return 計算後の結果を返却する。
     */
    public static Date addHour(Date l_dat, long l_lngHour)
    {
        if (l_dat == null)
        {
            return null;
        }

        long l_lngTmpTime = l_dat.getTime();
        long l_lngNewTime = l_lngTmpTime + l_lngHour * 60 * 60 * 1000;

        return new Date(l_lngNewTime);
    }

    /**
     * 入力した日付に指定した分数をプラスし、返却します。
     *
     * @@param l_dat       日付
     * @@param l_lngMinute 分数
     * @@return 計算後の結果を返却する。
     */
    public static Date addMinute(Date l_dat, long l_lngMinute)
    {
        if (l_dat == null)
        {
            return null;
        }

        long l_lngTmpTime = l_dat.getTime();
        long l_lngNewTime = l_lngTmpTime + l_lngMinute * 60 * 1000;

        return new Date(l_lngNewTime);
    }

    /**
     * 入力した日付に指定した秒数をプラスし、返却します。
     *
     * @@param l_dat       日付
     * @@param l_lngSecond 秒数
     * @@return 計算後の結果を返却する。
     */
    public static Date addSecond(Date l_dat, long l_lngSecond)
    {
        if (l_dat == null)
        {
            return null;
        }

        long l_lngTmpTime = l_dat.getTime();
        long l_lngNewTime = l_lngTmpTime + l_lngSecond * 1000;

        return new Date(l_lngNewTime);
    }

    /**
     * 二つの日付を比較します(精度は日までとする)。
     *
     * @@param l_dat1 日付1
     * @@param l_dat2 日付2
     * @@return l_dat1がl_dat2の後の場合、０より大きい整数を返却する。<BR>
     *         l_dat1がl_dat2の前の場合、０より小さい整数を返却する。<BR>
     *         l_dat1とl_dat2が同様の場合、０を返却する。
     */
    public static int compareToDay(Date l_dat1, Date l_dat2)
    {
        if (l_dat1 == null)
        {
            l_dat1 = new Date(0);
        }

        if (l_dat2 == null)
        {
            l_dat2 = new Date(0);
        }

        SimpleDateFormat l_dateFormat =
            GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
        String l_strDate1 = l_dateFormat.format(l_dat1);
        String l_strDate2 = l_dateFormat.format(l_dat2);

        return l_strDate1.compareTo(l_strDate2);
    }

    /**
     * 二つの日付を比較します(精度は時間までとする)。
     *
     * @@param l_dat1 日付1
     * @@param l_dat2 日付2
     * @@return l_dat1がl_dat2の後の場合、０より大きい整数を返却する。<BR>
     *         l_dat1がl_dat2の前の場合、０より小さい整数を返却する。<BR>
     *         l_dat1とl_dat2が同様の場合、０を返却する。
     */
    public static int compareToHour(Date l_dat1, Date l_dat2)
    {
        if (l_dat1 == null)
        {
            l_dat1 = new Date(0);
        }

        if (l_dat2 == null)
        {
            l_dat2 = new Date(0);
        }

        SimpleDateFormat l_dateFormat =
            GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMddHH");
        String l_strDate1 = l_dateFormat.format(l_dat1);
        String l_strDate2 = l_dateFormat.format(l_dat2);

        return l_strDate1.compareTo(l_strDate2);
    }

    /**
     * 二つの日付を比較します(精度は分までとする)。
     *
     * @@param l_dat1 日付1
     * @@param l_dat2 日付2
     * @@return l_dat1がl_dat2の後の場合、０より大きい整数を返却する。<BR>
     *         l_dat1がl_dat2の前の場合、０より小さい整数を返却する。<BR>
     *         l_dat1とl_dat2が同様の場合、０を返却する。
     */
    public static int compareToMinute(Date l_dat1, Date l_dat2)
    {
        if (l_dat1 == null)
        {
            l_dat1 = new Date(0);
        }

        if (l_dat2 == null)
        {
            l_dat2 = new Date(0);
        }

        SimpleDateFormat l_dateFormat =
            GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMddHHmm");
        String l_strDate1 = l_dateFormat.format(l_dat1);
        String l_strDate2 = l_dateFormat.format(l_dat2);

        return l_strDate1.compareTo(l_strDate2);
    }

    /**
     * 二つの日付を比較します(精度は秒までとする)。
     *
     * @@param l_dat1 日付1
     * @@param l_dat2 日付2
     * @@return l_dat1がl_dat2の後の場合、０より大きい整数を返却する。<BR>
     *         l_dat1がl_dat2の前の場合、０より小さい整数を返却する。<BR>
     *         l_dat1とl_dat2が同様の場合、０を返却する。
     */
    public static int compareToSecond(Date l_dat1, Date l_dat2)
    {
        if (l_dat1 == null)
        {
            l_dat1 = new Date(0);
        }

        if (l_dat2 == null)
        {
            l_dat2 = new Date(0);
        }

        SimpleDateFormat l_dateFormat =
            GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMddHHmmss");
        String l_strDate1 = l_dateFormat.format(l_dat1);
        String l_strDate2 = l_dateFormat.format(l_dat2);

        return l_strDate1.compareTo(l_strDate2);
    }

    /**
     * 日付を取得する。
     *
     * @@param l_dat    日時     
     * @@return 日付を返却する。
     */
    public static synchronized Date toDay(Date l_dat)
    {
        if (l_dat == null)
        {
            return null;
        }

        gCalendar.setTime(l_dat);
        gCalendar.set(Calendar.HOUR_OF_DAY, 0);
        gCalendar.set(Calendar.MINUTE, 0);
        gCalendar.set(Calendar.SECOND, 0);
        gCalendar.set(Calendar.MILLISECOND, 0);

        //l_dat.setTime(gCalendar.getTimeInMillis());

        return gCalendar.getTime();
    }

    /**
     * 二つの日付を比較します(精度は月までとする)。
     *
     * @@param l_dat1 日付1
     * @@param l_dat2 日付2
     * @@return l_dat1がl_dat2の後の場合、０より大きい整数を返却する。<BR>
     *         l_dat1がl_dat2の前の場合、０より小さい整数を返却する。<BR>
     *         l_dat1とl_dat2が同様の場合、０を返却する。
     */
    public static int compareToMonth(Date l_dat1, Date l_dat2)
    {
        if (l_dat1 == null)
        {
            l_dat1 = new Date(0);
        }

        if (l_dat2 == null)
        {
            l_dat2 = new Date(0);
        }

        SimpleDateFormat l_dateFormat =
            GtlUtils.getThreadSafeSimpleDateFormat("yyyyMM");
        String l_strDate1 = l_dateFormat.format(l_dat1);
        String l_strDate2 = l_dateFormat.format(l_dat2);

        return l_strDate1.compareTo(l_strDate2);
    }

    /**
     * 二つの日付を比較します(精度は年までとする)。
     *
     * @@param l_dat1 日付1
     * @@param l_dat2 日付2
     * @@return l_dat1がl_dat2の後の場合、０より大きい整数を返却する。<BR>
     *         l_dat1がl_dat2の前の場合、０より小さい整数を返却する。<BR>
     *         l_dat1とl_dat2が同様の場合、０を返却する。
     */
    public static int compareToYear(Date l_dat1, Date l_dat2)
    {
        if (l_dat1 == null)
        {
            l_dat1 = new Date(0);
        }

        if (l_dat2 == null)
        {
            l_dat2 = new Date(0);
        }

        SimpleDateFormat l_dateFormat =
            GtlUtils.getThreadSafeSimpleDateFormat("yyyy");
        String l_strDate1 = l_dateFormat.format(l_dat1);
        String l_strDate2 = l_dateFormat.format(l_dat2);

        return l_strDate1.compareTo(l_strDate2);
    }

    /**
     * 二つの日付を比較します。
     *
     * @@param l_dat1 日付1
     * @@param l_dat2 日付2
     * @@return l_dat1がl_dat2の後の場合、０より大きい整数を返却する。<BR>
     *         l_dat1がl_dat2の前の場合、０より小さい整数を返却する。<BR>
     *         l_dat1とl_dat2が同様の場合、０を返却する。
     */
    public static int compare(Date l_dat1, Date l_dat2)
    {
        if (l_dat1 == null)
        {
            l_dat1 = new Date(0);
        }

        if (l_dat2 == null)
        {
            l_dat2 = new Date(0);
        }

        return l_dat1.compareTo(l_dat2);
    }
    
    /**
    * 二つの日付を比較します(精度は時間部分のみとする)。
    *
    * @@param l_dat1 日付1
    * @@param l_dat2 日付2
    * @@return l_dat1の時間部分がl_dat2の時間部分の後の場合、０より大きい整数を返却する。<BR>
    *         l_dat1の時間部分がl_dat2の時間部分の前の場合、０より小さい整数を返却する。<BR>
    *         l_dat1の時間部分とl_dat2の時間部分が同様の場合、０を返却する。
    */
   public static int compareTime(Date l_dat1, Date l_dat2)
   {
       if (l_dat1 == null)
       {
           l_dat1 = new Date(0);
       }
 
       if (l_dat2 == null)
       {
           l_dat2 = new Date(0);
       }
       String l_strDat1 = WEB3DateUtility.formatDate(l_dat1, "HHmmss");
       String l_strDat2 = WEB3DateUtility.formatDate(l_dat2, "HHmmss");
       return l_strDat1.compareTo(l_strDat2);
   }

}
@
