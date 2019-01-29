head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeBizDate.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 営業日計算(WEB3GentradeBizDate.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/12 髙橋　@良和(SRA) 新規作成
Revesion History : 2004/07/12 鄒政 (中訊) 変更
Revesion History : 2005/07/07 孟東 (中訊) calcFeqBizDate()を追加
Revesion History : 2007/12/18 謝旋 (中訊)【共通】仕様変更・モデルNo.294
Revesion History : 2008/08/15 趙林鵬 (中訊)【共通】仕様変更・モデルNo.333
*/
package webbroker3.gentrade;

import java.sql.Timestamp;
import java.util.Calendar;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * 営業日計算ユーティリティクラス<BR>
 */
public class WEB3GentradeBizDate
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeBizDate.class);

    /**
     * 計算基準日<BR>
     */
    private Timestamp baseDate = null;

    /**
     * コンストラクタ<BR>
     *<BR> 
     * @@param l_tsBaseBizDate 基準日
     */
    public WEB3GentradeBizDate(Timestamp l_tsBaseDate)
    {
        setBaseDate(l_tsBaseDate);
    }

    /**
     * 基準日の再設定。<BR>
     *<BR> 
     * @@param l_tsBaseDate 基準日
     */
    public void setBaseDate(Timestamp l_tsBaseDate)
    {
        this.baseDate = new Timestamp(l_tsBaseDate.getTime());
    }

    /**
     * 基準日から加算・減算した営業日を求め返します。<BR>
     *<BR> 
     * １）this.calc営業日()をコールし、営業日を算出する。<BR>
     * <BR>
     * [calc営業日に渡す引数]<BR>
     * 基準日：　@this.計算基準日<BR>
     * 加算／減算日数：　@パラメータ.加算／減算日数<BR>
     * <BR>
     * ２）算出された営業日を返却する。<BR>
     * @@param l_intRollDays - 加算／減算日数<BR>
     * ex) 1・・・翌営業日を返却<BR>
     *      0・・・当営業日を返却(非営業日の場合は例外をスロー)<BR>
     * 　@　@-1・・・前営業日を返却<BR>
     * @@throws WEB3SystemLayerException
     */
    public Timestamp roll(int l_intRoll) throws WEB3SystemLayerException
    {
        return calcBizDate(this.baseDate, l_intRoll);
    }

    /**
     * (get週初営業日)<BR>
     * 基準日を含めた週初営業日を求める。<BR>
     * <BR>
     * １）カレンダークラスのインスタンスを取得し、this.基準日をセットする。<BR>
     * <BR>
     * ２）カレンダークラス.add()メソッドをコールし、日付を日曜日の日付まで戻す。<BR>
     * <BR>
     * ３）this.calc営業日()メソッドをコールし、営業日を算出する。<BR>
     * <BR>
     * [calc営業日()に渡す引数] <BR>
     * 基準日：　@カレンダークラス.getTime().getTime()の戻り値を元に生成したTimestampクラス<BR>
     * 加算／減算日数：　@+1<BR>
     * <BR>
     * ４）算出された営業日を返却する。<BR>
     * @@return Timestamp
     * @@throws WEB3BaseException
     */
    public Timestamp getWeekStartBizDate() throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getWeekStartBizDate()";

        log.entering(STR_METHOD_NAME);

        //１）カレンダークラスのインスタンスを取得し、this.基準日をセットする。
        Calendar l_bizDateCalendar = Calendar.getInstance();
        l_bizDateCalendar.setTime(this.baseDate);

        //２）カレンダークラス.add()メソッドをコールし、日付を日曜日の日付まで戻す。
        int l_intSunday = 0;
        switch (l_bizDateCalendar.get(Calendar.DAY_OF_WEEK))
        {
            case Calendar.MONDAY :
                l_intSunday = -1;
                break;
            case Calendar.TUESDAY :
                l_intSunday = -2;
                break;
            case Calendar.WEDNESDAY :
                l_intSunday = -3;
                break;
            case Calendar.THURSDAY :
                l_intSunday = -4;
                break;
            case Calendar.FRIDAY :
                l_intSunday = -5;
                break;
            case Calendar.SATURDAY :
                l_intSunday = -6;
                break;
            default :

                }
        // 日曜日に設定する
        l_bizDateCalendar.add(Calendar.DATE, l_intSunday);

        //３）this.calc営業日()メソッドをコールし、営業日を算出する。
        Timestamp l_tsWeekStartBizDate =
            calcBizDate(
                new Timestamp(l_bizDateCalendar.getTime().getTime()),
                1);

        log.exiting(STR_METHOD_NAME);

        // ４）算出された営業日を返却する。
        return l_tsWeekStartBizDate;
    }

    /**
     * 基準日を含めた週末営業日を求め返します。<BR>
     *<BR> 
     * １）カレンダークラスのインスタンスを取得し、this.基準日をセットする。<BR>
     *  <BR>
     * ２）カレンダークラス.add()メソッドをコールし、日付を土曜日<BR>
     *    の日付まで進める。<BR>
     *  <BR>
     * ３）this.calc営業日()メソッドをコールし、営業日を算出する。<BR>
     *  <BR>
     *    [calc営業日()に渡す引数] <BR>
     *    基準日：　@カレンダークラス.getTime().getTime()の戻り値<BR>
     *    を元に生成したTimestampクラス <BR>
     *    加算／減算日数：　@-1 <BR>
     *  <BR>
     * ４）算出された営業日を返却する。<BR>
     * @@return 週末営業日
     * @@throws WEB3SystemLayerException
     */
    public Timestamp getWeekEndBizDate() throws WEB3SystemLayerException
    {
        String STR_METHOD_NAME = "calcWeekEndBizDate()";

        log.entering(STR_METHOD_NAME);

        //１）カレンダークラスのインスタンスを取得し、this.基準日をセットする。
        Calendar l_bizDateCalendar = Calendar.getInstance();
        l_bizDateCalendar.setTime(this.baseDate);

        //２）カレンダークラス.add()メソッドをコールし、日付を土曜日
        //の日付まで進める
        int l_intSaturday = 0;
        switch (l_bizDateCalendar.get(Calendar.DAY_OF_WEEK))
        {
            case Calendar.SUNDAY :
                l_intSaturday = 6;
                break;
            case Calendar.MONDAY :
                l_intSaturday = 5;
                break;
            case Calendar.TUESDAY :
                l_intSaturday = 4;
                break;
            case Calendar.WEDNESDAY :
                l_intSaturday = 3;
                break;
            case Calendar.THURSDAY :
                l_intSaturday = 2;
                break;
            case Calendar.FRIDAY :
                l_intSaturday = 1;
                break;
            default :

                }
        // 土曜日に設定する
        l_bizDateCalendar.add(Calendar.DATE, l_intSaturday);

        //３）this.calc営業日()メソッドをコールし、営業日を算出する。
        Timestamp l_tsWeekEndBizDate =
            calcBizDate(
                new Timestamp(l_bizDateCalendar.getTime().getTime()),
                -1);

        log.exiting(STR_METHOD_NAME);

        // ４）算出された営業日を返却する。
        return l_tsWeekEndBizDate;
    }

    /**
     * (calc営業日) <BR>
     * 基準日から加算／減算した営業日を算出し、返却する。<BR>
     *  <BR>
     * １）営業日としてカウントされた基準日の数 = パラメータ.加算／減算<BR>
     *    日数の絶対値となるまで以下の処理を繰り返す。<BR>
     *   ※パラメータ.加算／減算日数 = 0の場合は、1となるまで繰り返す。<BR>
     * <BR>
     * １－１）パラメータ.基準日に1を加算(または減算)する。<BR>
     * 　@※加算された基準日の曜日を取得し、土・日だった場合は、<BR>
     *      土・日以外になるまで1を加算(または減算)する。<BR>
     * <BR>
     * １－２）以下の条件にてカレンダーテーブルを検索し、データ<BR>
     *   が取得できなかった場合は、営業日としてカウントする。<BR>
     * <BR>
     *   [条件] <BR>
     * 　@  日付　@　@　@　@　@ ＝　@加算(または減算)された基準日 <BR>
     * 　@  営業日区分　@＝　@”非営業日” <BR>
     *  <BR>
     * ２）最後に営業日としてカウントされた基準日を返却する。<BR>
     * DBアクセスに失敗した場合<BR>
     * class    : WEB3SystemLayerException<BR>
     * tag      : SYSTEM_ERROR_80003<BR>
     *<BR> 
     * 営業日の計算時、基準日が非営業日でロール値が0が指定された場合<BR>
     * class    : WEB3SystemLayerException<BR>
     * tag      : SYSTEM_ERROR_80010<BR>
     * @@param l_tsBaseDate - 基準日
     * @@param l_intRoll - 加算・減算日数
     * @@return 営業日
     * @@throws WEB3SystemLayerException
     */
    private Timestamp calcBizDate(Timestamp l_tsBaseDate, int l_intRoll)
        throws WEB3SystemLayerException
    {
        String STR_METHOD_NAME = "calcBizDate(Timestamp, int)";
        log.entering(STR_METHOD_NAME);

        //基準日=非営業日 かつ 加算／減算日数 = 0の場合は例外をスローする
        String l_strBizDateType =
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tsBaseDate);
        if ((WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
            && (l_intRoll == 0))
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //カレンダークラスのインスタンスを取得し、基準日をセットする
        Calendar l_bizDateCalendar = Calendar.getInstance();
        l_bizDateCalendar.setTime(l_tsBaseDate);
        // DB検索用に時分秒ミリ秒を初期化
        l_bizDateCalendar.set(Calendar.HOUR_OF_DAY, 0);
        l_bizDateCalendar.set(Calendar.MINUTE, 0);
        l_bizDateCalendar.set(Calendar.SECOND, 0);
        l_bizDateCalendar.set(Calendar.MILLISECOND, 0);
        Timestamp l_tsTmpBizDate;
        int l_intRollDays = l_intRoll;

        if (l_intRollDays >= 0)
        {
            while (l_intRollDays != 0)
            {
                //基準日に1を加算する
                l_bizDateCalendar.add(Calendar.DATE, 1);
                l_tsTmpBizDate =
                    new Timestamp(l_bizDateCalendar.getTime().getTime());
                //営業日区分取得
                l_strBizDateType =
                    WEB3GentradeTradingTimeManagement.getBizDateType(
                        l_tsTmpBizDate);
                //営業日の場合
                if (!WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
                {
                    l_intRollDays = l_intRollDays - 1;
                }
            }

        }
        else
        {
            while (l_intRollDays != 0)
            {
                //基準日に1を減算する
                l_bizDateCalendar.add(Calendar.DATE, -1);
                l_tsTmpBizDate =
                    new Timestamp(l_bizDateCalendar.getTime().getTime());
                //営業日区分取得
                l_strBizDateType =
                    WEB3GentradeTradingTimeManagement.getBizDateType(
                        l_tsTmpBizDate);
                //営業日の場合
                if (!WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
                {
                    l_intRollDays = l_intRollDays + 1;
                }
            }
        }

        //最後に営業日としてカウントされた基準日を返却する
        log.exiting(STR_METHOD_NAME);
        return new Timestamp(l_bizDateCalendar.getTime().getTime());

    }

    /**
     * (get指定営業日) <BR>
     * 基準日から、引数で指定された年・月・日数の前または後の日付を<BR>
     * 算出し、返す。 日付のカウント方法@は、片端入れとする。<BR>
     *  <BR>
     *１）　@引数.基準日に対し、以下の日付計算（年）を行う。 <BR>
     * 引数.加算／減算＝"加算"の場合は、引数.年数 後の日付を算出する。<BR> 
     * 引数.加算／減算＝"減算"の場合は、引数.年数 前の日付を算出する。<BR> 
     *  <BR>
     * ２）　@１）で計算した日付に対し、以下の日付計算（月）を行う。<BR> 
     * 引数.加算／減算＝"加算"の場合は、引数.月数 後の日付を算出する。<BR> 
     * 引数.加算／減算＝"減算"の場合は、引数.月数 前の日付を算出する。<BR> 
     *  <BR>
     * 計算した日付が、 <BR>
     *     カレンダー上に存在しない場合（６月３１日等） <BR> 
     *     は、当該月の月末最終日付を算出し以降で使用する。<BR> 
     *  <BR>
     * ３）　@２）で計算した日付に対し、以下の日付計算（日）を行う。<BR> 
     * 引数.加算／減算＝"加算"の場合は、引数.日数 後の日付を算出する。<BR> 
     * 引数.加算／減算＝"減算"の場合は、引数.日数 前の日付を算出する。<BR> 
     *  <BR>
     * ４）　@３）で計算した日付が非営業日の場合、直前の営業日を算出する。<BR> 
     *  <BR>
     * ５）　@計算した日付を返す。 <BR>   
     * <BR>   
     * @@param l_tsBaseDate - (基準日) <BR>   
     *       営業日計算に使用する基準日 <BR> 
     * @@param l_lngYear - (年数) <BR>
     * @@param l_lngMonth - (月数) <BR>
     * @@param l_lngDay - (日数) <BR> 
     * @@param l_intFlag - (加算／減算)<BR>
     *       基準日に対し、加算（未来の日付を求める）の場合、1をセット。<BR> 
     *       基準日に対し、減算（過去の日付を求める）の場合、-1をセット。<BR>
     *  <BR>  
     * @@throws WEB3SystemLayerException<BR> 
     *  <BR> 
     */
    public static Timestamp getAppointmentBizDate(
        Timestamp l_tsBaseDate,
        long l_lngYear,
        long l_lngMonth,
        long l_lngDay,
        int l_intFlag)
        throws WEB3SystemLayerException
    {
        String STR_METHOD_NAME = "getAppointmentBizDate(Timestamp, long, long, long, int)";
        log.entering(STR_METHOD_NAME);
        
        if((l_intFlag != 1) && (l_intFlag != -1))
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3GentradeBizDate.class.getName() + "." + STR_METHOD_NAME,
                "(加算／減算 = " + l_intFlag + ") ： " 
                + "基準日に対し、加算（未来の日付を求める）の場合、1をセット。"
                +"基準日に対し、減算（過去の日付を求める）の場合、-1をセット。");
        }
        
        //カレンダークラスのインスタンスを取得し、基準日をセットする。
        Calendar l_bizDateCalendar = Calendar.getInstance();
        l_bizDateCalendar.setTime(l_tsBaseDate);
        
        //get 年数 月数 日数
        int l_intYear;
        int l_intMonth;
        int l_intDay;
        if(l_intFlag == 1)
        {
            l_intYear = Integer.parseInt(String.valueOf(l_lngYear));
            l_intMonth = Integer.parseInt(String.valueOf(l_lngMonth));
            l_intDay = Integer.parseInt(String.valueOf(l_lngDay));
        }
        else
        {
            l_intYear = - Integer.parseInt(String.valueOf(l_lngYear));
            l_intMonth = - Integer.parseInt(String.valueOf(l_lngMonth));
            l_intDay = - Integer.parseInt(String.valueOf(l_lngDay));
        }
          
        //日付を計算する
        l_bizDateCalendar.add(Calendar.YEAR, l_intYear);
        l_bizDateCalendar.add(Calendar.MONTH, l_intMonth);
        l_bizDateCalendar.add(Calendar.DATE, l_intDay);
        
        //計算した日付が非営業日の場合、直前の営業日を算出する。
        Timestamp l_tsAppointmentBizDate = new Timestamp(l_bizDateCalendar.getTime().getTime());
        String l_strBizDateType = WEB3GentradeTradingTimeManagement.getBizDateType(l_tsAppointmentBizDate);
        if(WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
        {
            WEB3GentradeBizDate l_genBizDate = new WEB3GentradeBizDate(l_tsAppointmentBizDate);
            l_tsAppointmentBizDate = l_genBizDate.roll(-1);
            
            //時分秒ミリ秒を戻り
            Calendar l_appBizDateCalendar = Calendar.getInstance();
            l_appBizDateCalendar.setTime(l_tsAppointmentBizDate);
            l_appBizDateCalendar.set(Calendar.HOUR,l_bizDateCalendar.get(Calendar.HOUR));
            l_appBizDateCalendar.set(Calendar.MINUTE,l_bizDateCalendar.get(Calendar.MINUTE));
            l_appBizDateCalendar.set(Calendar.SECOND,l_bizDateCalendar.get(Calendar.SECOND));
            l_appBizDateCalendar.set(Calendar.MILLISECOND,l_bizDateCalendar.get(Calendar.MILLISECOND));
            l_tsAppointmentBizDate = new Timestamp(l_appBizDateCalendar.getTime().getTime());
        }

        log.exiting(STR_METHOD_NAME);
        return l_tsAppointmentBizDate;
    }

    /**
     * (calc外株営業日) <BR>
     * 基準日から加算／減算した営業日を算出し、返却する。<BR>
     * <BR>
     * ※基準日=非営業日 かつ 加算／減算日数 = 0の場合は例外をスローする。<BR>
     * <BR>
     * <BR>
     * １）営業日としてカウントされた基準日の数 =<BR> 
     * パラメータ.加算／減算日数の絶対値となるまで以下の処理を繰り返す。<BR>
     * ※パラメータ.加算／減算日数 = 0の場合は、1となるまで繰り返す。<BR>
     * <BR>
     * １－１）パラメータ.基準日に1を加算(または減算)する。<BR>
     * ※加算された基準日の曜日を取得し、土・日だった場合は、<BR>
     * 土・日以外になるまで1を加算(または減算)する。<BR>
     * <BR>
     * １－２）以下の条件にてカレンダーテーブル，<BR>
     * 外株海外市場カレンダーを検索し、<BR>
     * どちらもデータが取得できなかった場合は、営業日としてカウントする。<BR>
     * <BR>
     * [カレンダーテーブル検索条件]<BR>
     * 日付 = 加算(または減算)された基準日<BR>
     * 営業日区分 = ”非営業日”<BR>
     * <BR>
     * [外株海外市場カレンダー検索条件]<BR>
     * 証券会社コード = 証券会社コード<BR>
     * 市場コード = 市場コード<BR>
     * 日付 = 加算(または減算)された基準日<BR>
     * 営業日区分 = ”非営業日”<BR>
     * <BR>
     * ２）最後に営業日としてカウントされた基準日を返却する。<BR> 
     *<BR> 
     * @@param l_strInstitutionCode 証券会社コード
     * @@param l_strMarketCode 市場コード
     * @@param l_tsBaseDate 基準日
     * @@param l_intRoll 加算・減算日数
     * @@return 営業日
     * @@throws WEB3SystemLayerException
     */
    public Timestamp calcFeqBizDate(
        String l_strInstitutionCode,
        String l_strMarketCode,
        Timestamp l_tsBaseDate,
        int l_intRoll)
        throws WEB3SystemLayerException
    {
        String STR_METHOD_NAME = "calcFeqBizDate(String, String, Timestamp, int)";
        log.entering(STR_METHOD_NAME);

        //基準日=非営業日 かつ 加算／減算日数 = 0の場合は例外をスローする
        String l_strBizDateType = 
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tsBaseDate);
        String l_strFeqBizDateType = 
            WEB3GentradeTradingTimeManagement.getFeqBizDateType(
                l_strInstitutionCode,
                l_strMarketCode,
                l_tsBaseDate);
        
        if ((WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType) || 
            (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strFeqBizDateType)))
            && (l_intRoll == 0))
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //カレンダークラスのインスタンスを取得し、基準日をセットする
        Calendar l_bizDateCalendar = Calendar.getInstance();
        l_bizDateCalendar.setTime(l_tsBaseDate);
        // DB検索用に時分秒ミリ秒を初期化
        l_bizDateCalendar.set(Calendar.HOUR_OF_DAY, 0);
        l_bizDateCalendar.set(Calendar.MINUTE, 0);
        l_bizDateCalendar.set(Calendar.SECOND, 0);
        l_bizDateCalendar.set(Calendar.MILLISECOND, 0);
        Timestamp l_tsTmpBizDate;
        int l_intRollDays = l_intRoll;

        if (l_intRollDays >= 0)
        {
            while (l_intRollDays != 0)
            {
                //基準日に1を加算する
                l_bizDateCalendar.add(Calendar.DATE, 1);
                l_tsTmpBizDate =
                    new Timestamp(l_bizDateCalendar.getTime().getTime());
                //カレンダーテーブルから営業日区分取得
                l_strBizDateType =
                    WEB3GentradeTradingTimeManagement.getBizDateType(
                        l_tsTmpBizDate);
                //外株海外市場カレンダーから営業日区分取得        
                l_strFeqBizDateType = 
                    WEB3GentradeTradingTimeManagement.getFeqBizDateType(
                        l_strInstitutionCode,
                        l_strMarketCode,
                        l_tsTmpBizDate);        
                //営業日の場合
                if (!WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType) &&
                    !WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strFeqBizDateType))
                {
                    l_intRollDays = l_intRollDays - 1;
                }
            }
        }
        else
        {
            while (l_intRollDays != 0)
            {
                //基準日に1を減算する
                l_bizDateCalendar.add(Calendar.DATE, -1);
                l_tsTmpBizDate =
                    new Timestamp(l_bizDateCalendar.getTime().getTime());
                //カレンダーテーブルから営業日区分取得
                l_strBizDateType =
                    WEB3GentradeTradingTimeManagement.getBizDateType(
                        l_tsTmpBizDate);
                //外株海外市場カレンダーから営業日区分取得
                l_strFeqBizDateType = 
                    WEB3GentradeTradingTimeManagement.getFeqBizDateType(
                        l_strInstitutionCode,
                        l_strMarketCode,
                        l_tsTmpBizDate);        
                //営業日の場合
                if (!WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType) &&
                    !WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strFeqBizDateType))
                {
                    l_intRollDays = l_intRollDays + 1;
                }
            }
        }

        //最後に営業日としてカウントされた基準日を返却する
        log.exiting(STR_METHOD_NAME);
        return new Timestamp(l_bizDateCalendar.getTime().getTime());
    }

    /**
     * (calcPTS営業日)<BR>
     * 基準日から加算／減算した営業日を算出し、返却する。  <BR>
     * <BR>
     * ※　@取引時間管理.getPTS営業日区分( )の戻り値が"非営業日"かつ、  <BR>
     * ※　@加算／減算日数 = 0の場合は例外をスローする。  <BR>
     * 　@　@［getPTS営業日区分( )の引数］  <BR>
     * 　@　@　@日付：　@基準日  <BR>
     * <BR>
     * １）　@営業日としてカウントされた基準日の数 = パラメータ.加算／減算日数<BR>
     * 　@　@　@の絶対値となるまで以下の処理を繰り返す。  <BR>
     * ※パラメータ.加算／減算日数 = 0の場合は、1となるまで繰り返す。  <BR>
     * <BR>
     * １－１）パラメータ.基準日に1を加算(または減算)する。  <BR>
     * 　@※　@取引時間管理.getPTS営業日区分( )をコールし、<BR>
     * 　@　@　@　@戻り値が"非営業日"の場合は  <BR>
     * 　@※　@"非営業日"以外になるまで1を加算(または減算)する。  <BR>
     * 　@　@　@［getPTS営業日区分( )の引数］  <BR>
     * 　@　@　@　@日付：　@1を加算(または減算)した基準日  <BR>
     * 　@  <BR>
     * ２）　@最後に営業日としてカウントされた基準日を返却する。<BR>
     * <BR>
     * @@param l_tsBaseDate - 基準日
     * @@param l_intRoll - 加算・減算日数
     * @@return 営業日
     * @@throws WEB3SystemLayerException
     */
    public Timestamp calcPTSBizDate(Timestamp l_tsBaseDate, int l_intRoll)
        throws WEB3SystemLayerException
    {
        String STR_METHOD_NAME = "calcPTSBizDate(Timestamp, int)";
        log.entering(STR_METHOD_NAME);

        //取引時間管理.getPTS営業日区分( )の戻り値が"非営業日"かつ、
        //加算／減算日数 = 0の場合は例外をスローする。
        String l_strBizDateType =
            WEB3GentradeTradingTimeManagement.getPTSBizDateType(l_tsBaseDate);
        if ((WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
            && (l_intRoll == 0))
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //カレンダークラスのインスタンスを取得し、基準日をセットする
        Calendar l_bizDateCalendar = Calendar.getInstance();
        l_bizDateCalendar.setTime(l_tsBaseDate);
        // DB検索用に時分秒ミリ秒を初期化
        l_bizDateCalendar.set(Calendar.HOUR_OF_DAY, 0);
        l_bizDateCalendar.set(Calendar.MINUTE, 0);
        l_bizDateCalendar.set(Calendar.SECOND, 0);
        l_bizDateCalendar.set(Calendar.MILLISECOND, 0);
        Timestamp l_tsTmpBizDate;
        int l_intRollDays = l_intRoll;

        if (l_intRollDays >= 0)
        {
            while (l_intRollDays != 0)
            {
                //基準日に1を加算する
                l_bizDateCalendar.add(Calendar.DATE, 1);
                l_tsTmpBizDate =
                    new Timestamp(l_bizDateCalendar.getTime().getTime());
                //営業日区分取得
                l_strBizDateType =
                    WEB3GentradeTradingTimeManagement.getPTSBizDateType(
                        l_tsTmpBizDate);
                //営業日の場合
                if (!WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
                {
                    l_intRollDays = l_intRollDays - 1;
                }
            }
        }
        else
        {
            while (l_intRollDays != 0)
            {
                //基準日に1を減算する
                l_bizDateCalendar.add(Calendar.DATE, -1);
                l_tsTmpBizDate =
                    new Timestamp(l_bizDateCalendar.getTime().getTime());
                //営業日区分取得
                l_strBizDateType =
                    WEB3GentradeTradingTimeManagement.getPTSBizDateType(
                        l_tsTmpBizDate);
                //営業日の場合
                if (!WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
                {
                    l_intRollDays = l_intRollDays + 1;
                }
            }
        }

        //最後に営業日としてカウントされた基準日を返却する
        log.exiting(STR_METHOD_NAME);
        return new Timestamp(l_bizDateCalendar.getTime().getTime());
    }
}
@
