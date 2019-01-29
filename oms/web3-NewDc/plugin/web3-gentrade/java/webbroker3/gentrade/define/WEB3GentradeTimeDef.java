head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeTimeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 時間フォーマッタ定数定義インタフェイス(WEB3GentradeTimeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/08 鄒政 (中訊) 新規作成
Revesion History : 2006/03/24 凌建平 (中訊) 定数"HHMM00"を追加
Revesion History : 2006/03/27 凌建平 (中訊) 定数"HH:mm"を追加
Revesion History : 2007/07/30 栄イ (中訊) 定数"yyyyMM"を追加
Revesion History : 2007/08/07 栄イ (中訊) 定数"yyyy/MM/dd"を追加
Revesion History : 2007/08/09 栄イ (中訊) 定数"yyyy年M月d日（E）"を追加
Revesion History : 2007/09/29 栄イ (中訊) 定数"9999/12/31"を追加
Revesion History : 2008/01/29 栄イ (中訊) 定数"yyyy/M/d"、"yyyy/M/d H:mm"、"yyyy/MM/dd HH:mm:ss"を追加
Revesion History : 2009/03/16 趙林鵬 (中訊) 定数"MMdd"を追加
*/
package webbroker3.gentrade.define;

/**
 * 時間フォーマッタ定数定義インタフェイス
 *
 * @@author 鄒政
 * @@version 1.0
 */
public interface WEB3GentradeTimeDef
{
    /**
      * 最小限の時間。<BR>
      */
    public static final String MIN_RETURN = "000000";

    /**
      * 最大の時間。<BR>
      */
    public static final String MAX_RETURN = "235959";

    /**
      * コロン。<BR>
      */
    public static final String STR_COLON = ":";

    /**
      * 時間のフォーマッタ ：時分。<BR>
      */
    public static final String TIME_FORMAT_HMS = "HHmmss";

    /**
      * 時間のフォーマッタ ：時分。<BR>
      */
    public static final String TIME_FORMAT_HM = "HHmm";

    /**
      * 日付のフォーマッタ ：年。<BR>
      */
    public static final String DATE_FORMAT_YEAR = "yyyy";

    /**
     * 日付のフォーマッタ ：年月日。<BR>
    */
    public static final String DATE_FORMAT_YMD = "yyyyMMdd";

    /**
     * 時間のフォーマッタ ：時分秒。<BR>
     */
    public static final String TIME_FORMAT_HM0 = "HHmm00";

    /**
     * 時間のフォーマッタ ：時:分。<BR>
     */
    public static final String TIME_PARSE_HM = "HH:mm";

    /**
     * 日付のフォーマッタ ：年月。<BR>
    */
    public static final String DATE_FORMAT_YM = "yyyyMM";

    /**
     * 時間のフォーマッタ ：年/月/日。<BR>
     */
    public static final String DATE_SPLIT_YMD = "yyyy/MM/dd";

    /**
     * 時間のフォーマッタ ：○○○○年○月○日（●）<BR>
     * ●には、曜日(日,月,火,水,木,金,土)を設定する。<BR>
     */
    public static final String DATE_PARSE_YMDE = "yyyy年M月d日（E）";

    /**
      * 最大日期。<BR>
      */
    public static final String MAX_YMD = "9999/12/31";

    /**
      * 日付のフォーマッタ ：年/月/日。<BR>
      */
    public static final String DATE_FORMAT_YMD_SHORT = "yyyy/M/d";

    /**
      * 時間のフォーマッタ ：年/月/日 時:分。<BR>
      */
    public static final String TIME_FORMAT_YMDHM_SHORT = "yyyy/M/d H:mm";

    /**
      * 時間のフォーマッタ ：年/月/日 時:分:秒。<BR>
      */
    public static final String TIME_FORMAT_YMDHMS = "yyyy/MM/dd HH:mm:ss";

    /**
     * 日付のフォーマッタ ：月日。<BR>
     */
   public static final String DATE_FORMAT_MD = "MMdd";
}@
