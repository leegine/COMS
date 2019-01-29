head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyCloseDateDrawDateCalc.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定時定額買付締切日引落日計算(WEB3MutualFixedBuyCloseDateDrawDateCalc.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/09 武波 (中訊) 新規作成 モデルNo.607,612
*/
package webbroker3.mf;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesPK;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateCalcParameterDef;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3EnableOrderDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3SystemPreferencesNameDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (定時定額買付締切日引落日計算)<BR>
 * 定時定額買付締切日引落日計算<BR>
 *
 * @@author 武波(中訊)
 * @@version 1.0
 */
public class WEB3MutualFixedBuyCloseDateDrawDateCalc
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFixedBuyCloseDateDrawDateCalc.class);

    /**
     * (通常引落日)<BR>
     * 通常引落日<BR>
     */
    private long usuallyDrawDate;

    /**
     * (賞与引落日)<BR>
     * 賞与引落日<BR>
     */
    private long prizeAndDrawDate;

    /**
     * (通常締切日起算日数)<BR>
     * 通常締切日起算日数<BR>
     */
    private long usuallyCloseDateBaseDate;

    /**
     * (賞与締切日起算日数)<BR>
     * 賞与締切日起算日数<BR>
     */
    private long prizeAndCloseDateBaseDate;

    /**
     * (定時定額買付締切時間)<BR>
     * 定時定額買付締切時間<BR>
     */
    private long fixedBuyCloseDate;

    /**
     * (calc通常締切日（WEB）)<BR>
     * 指定年月の通常締切日（WEB）を取得する。<BR>
     * <BR>
     * 1) this.calc通常引落日()をコール<BR>
     * 　@　@[calc通常引落日の引数]<BR>
     * 　@　@指定年月：引数.指定年月<BR>
     * <BR>
     * 2) 営業日計算インスタンスを生成する。<BR>
     * 　@　@　@[営業日計算インスタンスの引数]<BR>
     * 　@　@　@　@基準日：calc通常引落日()の戻り値<BR>
     * <BR>
     * 3) 取得した営業日計算オブジェクト.rollをコール<BR>
     * 　@　@[rollの引数]<BR>
     * 　@　@加算・減算日数：-(this.通常締切日起算日数)<BR>
     * <BR>
     * 4) rollの戻り値をリターンする。<BR>
     * @@param l_datSelectMY - (指定年月)<BR>
     * 指定年月<BR>
     * @@return Date<BR>
     * @@throws WEB3BaseException
     */
    public Date calcUsuallyCloseDate(Date l_datSelectMY) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcUsuallyCloseDate(Date)";
        log.entering(STR_METHOD_NAME);

        //1) this.calc通常引落日()をコール
        //[calc通常引落日の引数]
        //指定年月：引数.指定年月
        Date l_datUsuallyDrawDate = this.calcUsuallyDrawDate(l_datSelectMY);

        //2) 営業日計算インスタンスを生成する。
        //[営業日計算インスタンスの引数]
        //基準日：calc通常引落日()の戻り値
        WEB3GentradeBizDate l_gentradeBizDate =
            new WEB3GentradeBizDate(
                new Timestamp(l_datUsuallyDrawDate.getTime()));

        //3) 取得した営業日計算オブジェクト.rollをコール
        //[rollの引数]
        //加算・減算日数：-(this.通常締切日起算日数)
        Timestamp l_tsBizDate =
            l_gentradeBizDate.roll(-(int)this.usuallyCloseDateBaseDate);

        //4) rollの戻り値をリターンする。
        log.exiting(STR_METHOD_NAME);
        return WEB3DateUtility.toDay(l_tsBizDate);
    }

    /**
     * (calc通常引落日)<BR>
     * 指定年月の通常引落日を取得する。<BR>
     * ※指定年月の通常引落日が非営業日の場合は、翌営業日を取得する。<BR>
     * <BR>
     * 1)　@引数.指定年月の年月(yyyy/mm)とthis.通常引落日を連結させた、<BR>
     * 　@　@Dateオブジェクトを作成する。<BR>
     * <BR>
     * 2)　@取引時間管理インスタンスを作成。<BR>
     * <BR>
     * 3)　@取得した取引時間管理.get営業日区分()をコール。<BR>
     * 　@　@[get営業日区分の引数]<BR>
     * 　@　@　@日付：作成したDateオブジェクト<BR>
     * <BR>
     * 4)　@取得した取引時間管理.get営業日区分()の戻り値が”営業日”の場合<BR>
     * <BR>
     * 　@　@4)-1)　@作成したDateオブジェクトをリターンする。<BR>
     * <BR>
     * 5)　@取得した取引時間管理.get営業日区分()の戻り値が”非営業日”の場合<BR>
     * <BR>
     * 　@　@5)-1) 作成したDateオブジェクトをインクリメントし、<BR>
     * 　@　@　@取得した取引時間管理.get営業日区分()の戻り値が”営業日”になるまで繰り返す。<BR>
     * 　@　@　@　@　@　@”営業日”になったら、作成したDateオブジェクトをリターンする。<BR>
     * @@param l_datSelectMY - (指定年月)<BR>
     * 指定年月<BR>
     * @@return Date<BR>
     * @@throws WEB3BaseException
     */
    public Date calcUsuallyDrawDate(Date l_datSelectMY) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcUsuallyDrawDate(Date)";
        log.entering(STR_METHOD_NAME);

        String l_strUsuallyDrawDate = String.valueOf(this.usuallyDrawDate);
        Date l_datSelectDMY;
        String l_strSelectMYDate = WEB3DateUtility.formatDate(
            l_datSelectMY, WEB3GentradeTimeDef.DATE_FORMAT_YM);
        //1)  引数.指定年月の年月(yyyy/mm)とthis.通常引落日を連結させた、Dateオブジェクトを作成する。
        if (l_strUsuallyDrawDate.length() == 1)
        {
            l_datSelectDMY = WEB3DateUtility.getDate(
                l_strSelectMYDate + "0" + l_strUsuallyDrawDate,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        }
        else
        {
            l_datSelectDMY = WEB3DateUtility.getDate(
                l_strSelectMYDate + l_strUsuallyDrawDate,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        }

        //2) 取引時間管理インスタンスを作成。
        //3) 取得した取引時間管理.get営業日区分()をコール。
        //[get営業日区分の引数]
        //日付：作成したDateオブジェクト
        String l_strBizDateType = WEB3GentradeTradingTimeManagement.getBizDateType(
            new Timestamp(l_datSelectDMY.getTime()));

        //4) 取得した取引時間管理.get営業日区分()の戻り値が”営業日”の場合
        if (WEB3BizDateTypeDef.BIZ_DATE.equals(l_strBizDateType))
        {
            //4)-1) 作成したDateオブジェクトをリターンする。
            log.exiting(STR_METHOD_NAME);
            return l_datSelectDMY;
        }

        Timestamp l_tsBizDate = new Timestamp(l_datSelectDMY.getTime());
        //5) 取得した取引時間管理.get営業日区分()の戻り値が”非営業日”の場合
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
        {
            //5)-1) 作成したDateオブジェクトをインクリメントし、取得した取引時間管理.get営業日区分()
            //の戻り値が”営業日”になるまで繰り返す。
            //”営業日”になったら、作成したDateオブジェクトをリターンする。
            l_tsBizDate = new WEB3GentradeBizDate(l_tsBizDate).roll(1);
        }

        log.exiting(STR_METHOD_NAME);
        return new Date(l_tsBizDate.getTime());
    }

    /**
     * (calc賞与締切日)<BR>
     * 指定年月の賞与締切日を取得する。<BR>
     * 指定年月の賞与引落日から賞与締切日起算日数前の日付を取得する<BR>
     * <BR>
     * 1) this.calc賞与引落日()をコール<BR>
     * 　@　@[calc賞与引落日の引数]<BR>
     * 　@　@指定年月：引数.指定年月<BR>
     * <BR>
     * 2) 営業日計算インスタンスを生成する。<BR>
     * 　@　@　@[営業日計算インスタンスの引数]<BR>
     * 　@　@　@　@基準日：calc賞与引落日()の戻り値<BR>
     * <BR>
     * 3) 取得した営業日計算オブジェクト.rollをコール<BR>
     * 　@　@[rollの引数]<BR>
     * 　@　@加算・減算日数：-(this.賞与締切日起算日数)<BR>
     * <BR>
     * 4) rollの戻り値をリターンする。<BR>
     * <BR>
     * @@param l_datSelectMY - (指定年月)<BR>
     * 指定年月<BR>
     * @@return Date<BR>
     * @@throws WEB3BaseException
     */
    public Date calcPrizeAndCloseDate(Date l_datSelectMY) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcPrizeAndCloseDate(Date)";
        log.entering(STR_METHOD_NAME);

        //1) this.calc賞与引落日()をコール
        //[calc賞与引落日の引数]
        //指定年月：引数.指定年月
        Date l_datPrizeAndDrawDate = this.calcPrizeAndDrawDate(l_datSelectMY);

        //2) 営業日計算インスタンスを生成する。
        //[営業日計算インスタンスの引数]
        //基準日：calc賞与引落日()の戻り値
        WEB3GentradeBizDate l_gentradeBizDate =
            new WEB3GentradeBizDate(
                new Timestamp(l_datPrizeAndDrawDate.getTime()));

        //3) 取得した営業日計算オブジェクト.rollをコール
        //[rollの引数]
        //加算・減算日数：-(this.賞与締切日起算日数)
        Timestamp l_tsBizDate =
            l_gentradeBizDate.roll(-(int)this.prizeAndCloseDateBaseDate);

        //4) rollの戻り値をリターンする。
        log.exiting(STR_METHOD_NAME);
        return l_tsBizDate;
    }

    /**
     * (calc賞与引落日)<BR>
     * 指定年月の賞与引落日を取得する。<BR>
     * ※指定年月の賞与引落日が非営業日の場合は、翌営業日を取得する。<BR>
     * <BR>
     * 1) 引数.指定年月の年月(yyyy/mm)とthis.賞与引落日を連結させた、<BR>
     * 　@Dateオブジェクトを作成する。<BR>
     * <BR>
     * 2) 取引時間管理インスタンスを作成。<BR>
     * <BR>
     * 3) 取得した取引時間管理.get営業日区分()をコール。<BR>
     * 　@　@[get営業日区分の引数]<BR>
     * 　@　@　@日付：作成したDateオブジェクト<BR>
     * <BR>
     * 4) 取得した取引時間管理.get営業日区分()の戻り値が”営業日”の場合<BR>
     * <BR>
     * 　@　@4)-1) 作成したDateオブジェクトをリターンする。<BR>
     * <BR>
     * 5) 取得した取引時間管理.get営業日区分()の戻り値が”非営業日”の場合<BR>
     * <BR>
     * 　@　@5)-1) 作成したDateオブジェクトをインクリメントし、<BR>
     * 　@　@　@取得した取引時間管理.get営業日区分()の戻り値が”営業日”になるまで繰り返す。<BR>
     * 　@　@　@”営業日”になったら、作成したDateオブジェクトをリターンする。<BR>
     * @@param l_datSelectMY - (指定年月)<BR>
     * 指定年月<BR>
     * @@return Date<BR>
     * @@throws WEB3BaseException
     */
    public Date calcPrizeAndDrawDate(Date l_datSelectMY) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcPrizeAndDrawDate(Date)";
        log.entering(STR_METHOD_NAME);

        String l_strPrizeAndDrawDate = String.valueOf(this.prizeAndDrawDate);
        Date l_datSelectDMY;
        String l_strSelectMYDate = WEB3DateUtility.formatDate(
            l_datSelectMY, WEB3GentradeTimeDef.DATE_FORMAT_YM);
        //1)  引数.指定年月の年月(yyyy/mm)とthis.賞与引落日を連結させた、Dateオブジェクトを作成する。
        if (l_strPrizeAndDrawDate.length() == 1)
        {
            l_datSelectDMY = WEB3DateUtility.getDate(
                l_strSelectMYDate + "0" + l_strPrizeAndDrawDate,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        }
        else
        {
            l_datSelectDMY = WEB3DateUtility.getDate(
                l_strSelectMYDate + l_strPrizeAndDrawDate,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        }

        //2) 取引時間管理インスタンスを作成。
        //3) 取得した取引時間管理.get営業日区分()をコール。
        //[get営業日区分の引数]
        //日付：作成したDateオブジェクト
        String l_strBizDateType = WEB3GentradeTradingTimeManagement.getBizDateType(
            new Timestamp(l_datSelectDMY.getTime()));

        //4) 取得した取引時間管理.get営業日区分()の戻り値が”営業日”の場合
        if (WEB3BizDateTypeDef.BIZ_DATE.equals(l_strBizDateType))
        {
            //4)-1) 作成したDateオブジェクトをリターンする。
            log.exiting(STR_METHOD_NAME);
            return l_datSelectDMY;
        }

        Timestamp l_tsBizDate = new Timestamp(l_datSelectDMY.getTime());
        //5) 取得した取引時間管理.get営業日区分()の戻り値が”非営業日”の場合
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
        {
            //5)-1) 作成したDateオブジェクトをインクリメントし、取得した取引時間管理.get営業日区分()
            //の戻り値が”営業日”になるまで繰り返す。
            //”営業日”になったら、作成したDateオブジェクトをリターンする。
            l_tsBizDate = new WEB3GentradeBizDate(l_tsBizDate).roll(1);
        }

        log.exiting(STR_METHOD_NAME);
        return new Date(l_tsBizDate.getTime());
    }

    /**
     * (calc通常締切日時（WEB）)<BR>
     * 指定年月の通常締切日時（WEB）を取得する。<BR>
     * 指定年月の通常締切日（WEB）の定時定額買付締切時間を取得する<BR>
     * <BR>
     * 1) this.calc通常締切日（WEB）()をコール<BR>
     * 　@　@[calc通常締切日（WEB）の引数]<BR>
     * 　@　@指定年月：引数.指定年月<BR>
     * <BR>
     * 2) calc通常締切日（WEB）の戻り値とthis.定時定額買付締切時間を連結した値をリターンする。<BR>
     * @@param l_datSelectMY - (指定年月)<BR>
     * 指定年月<BR>
     * @@return Date<BR>
     * @@throws WEB3BaseException
     */
    public Date calcUsuallyCloseDateTime(Date l_datSelectMY) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcUsuallyCloseDateTime(Date)";
        log.entering(STR_METHOD_NAME);

        //1) this.calc通常締切日（WEB）()をコール
        //[calc通常締切日（WEB）の引数]
        //指定年月：引数.指定年月
        Date l_datUsuallyCloseDate = this.calcUsuallyCloseDate(l_datSelectMY);

        String l_strFixedBuyCloseDate = String.valueOf(this.fixedBuyCloseDate);
        Date l_datReturnDate;
        String l_strUsuallyCloseDate = WEB3DateUtility.formatDate(
            l_datUsuallyCloseDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        //2) calc通常締切日（WEB）の戻り値とthis.定時定額買付締切時間を連結した値をリターンする。
        if (l_strFixedBuyCloseDate.length() == 5)
        {
            l_datReturnDate = WEB3DateUtility.getDate(
                l_strUsuallyCloseDate + "0" + l_strFixedBuyCloseDate,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS);
        }
        else
        {
            l_datReturnDate = WEB3DateUtility.getDate(
                l_strUsuallyCloseDate + l_strFixedBuyCloseDate,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS);
        }

        log.exiting(STR_METHOD_NAME);
        return l_datReturnDate;
    }

    /**
     * (calc賞与締切日時)<BR>
     * 指定年月の賞与締切日時を取得する。<BR>
     * 指定年月の賞与締切日の定時定額買付締切時間を取得する<BR>
     * <BR>
     * 1) this.calc賞与締切日()をコール<BR>
     * 　@　@[calc賞与締切日の引数]<BR>
     * 　@　@指定年月：引数.指定年月<BR>
     * <BR>
     * 2) calc賞与締切日の戻り値とthis.定時定額買付締切時間を連結した値をリターンする。<BR>
     * @@param l_datSelectMY - (指定年月)<BR>
     * 指定年月<BR>
     * @@return Date<BR>
     * @@throws WEB3BaseException
     */
    public Date calcPrizeAndCloseDateHour(Date l_datSelectMY) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcPrizeAndCloseDateHour(Date)";
        log.entering(STR_METHOD_NAME);

        //1) this.calc賞与締切日()をコール
        //[calc賞与締切日の引数]
        //指定年月：引数.指定年月
        Date l_datPrizeAndCloseDate = this.calcPrizeAndCloseDate(l_datSelectMY);

        String l_strFixedBuyCloseDate = String.valueOf(this.fixedBuyCloseDate);
        Date l_datReturnDate;
        String l_strPrizeAndCloseDate = WEB3DateUtility.formatDate(
                l_datPrizeAndCloseDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        //2) calc賞与締切日の戻り値とthis.定時定額買付締切時間を連結した値をリターンする。
        if (l_strFixedBuyCloseDate.length() == 5)
        {
            l_datReturnDate = WEB3DateUtility.getDate(
                    l_strPrizeAndCloseDate + "0" + l_strFixedBuyCloseDate,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS);
        }
        else
        {
            l_datReturnDate = WEB3DateUtility.getDate(
                    l_strPrizeAndCloseDate + l_strFixedBuyCloseDate,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS);
        }

        log.exiting(STR_METHOD_NAME);
        return l_datReturnDate;
    }

    /**
     * (定時定額買付締切日引落日計算)<BR>
     * デフォルトコンストラクタ<BR>
     */
    public WEB3MutualFixedBuyCloseDateDrawDateCalc()
    {

    }

    /**
     * (定時定額買付締切日引落日計算)<BR>
     * コンストラクタ<BR>
     * <BR>
     * 1) システムプリファ@レンステーブルから以下の条件に該当するレコードを取得し、値をthis.通常引落日へセットする。<BR>
     * [条件]<BR>
     * 　@名称（環境変数名）：XX_SBS_DRAW_DD<BR>
     * 　@※XXは引数.証券会社コード<BR>
     * <BR>
     * 2) システムプリファ@レンステーブルから以下の条件に該当するレコードを取得し、値をthis.通常締切日起算日数へセットする。<BR>
     * [条件]<BR>
     * 　@名称（環境変数名）：XX_SBS_DAY_COUNT<BR>
     * 　@※XXは引数.証券会社コード<BR>
     * <BR>
     * 3) システムプリファ@レンステーブルから以下の条件に該当するレコードを取得し、値をthis.賞与引落日へセットする。<BR>
     * [条件]<BR>
     * 　@名称（環境変数名）：XX_SBS_DRAW_DD_BONUS<BR>
     * 　@※XXは引数.証券会社コード<BR>
     * <BR>
     * 4) システムプリファ@レンステーブルから以下の条件に該当するレコードを取得し、値をthis賞与締切日起算日数へセットする。<BR>
     * [条件]<BR>
     * 　@名称（環境変数名）：XX_SBS_DAY_COUNT_BONUS<BR>
     * 　@※XXは引数.証券会社コード<BR>
     * <BR>
     * 5) 取引時間テーブルを検索し、取引時間テーブルRow.開始時間をthis.定時定額買付締切時間へセットする。<BR>
     * 　@　@[条件]<BR>
     * 　@　@証券会社コード：引数.証券会社コード<BR>
     * 　@　@部店コード：引数.部店コード<BR>
     * 　@　@市場コード：0：DEFAULT<BR>
     * 　@　@受付時間区分：33：投信定時定額買付<BR>
     * 　@　@商品コード:0：DEFAULT<BR>
     * 　@　@営業日区分：1：終日営業日<BR>
     * 　@　@受付可能：0：受付可能<BR>
     * 　@　@発注日計算：1：翌営業日<BR>
     * 　@　@※ 取得したレコードが1件以外なら「データ不整合エラー」の例外をスローする。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@throws WEB3BaseException
     */
    public WEB3MutualFixedBuyCloseDateDrawDateCalc(
        String l_strInstitutionCode,
        String l_strBranchCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3MutualFixedBuyCloseDateDrawDateCalc("
            + "String, String)";
        log.entering(STR_METHOD_NAME);

        SystemPreferencesRow l_systemPreferencesRow = null;
        //1) システムプリファ@レンステーブルから以下の条件に該当するレコードを取得し、値をthis.通常引落日へセットする。
        //[条件]
        //名称（環境変数名）：XX_SBS_DRAW_DD
        //※XXは引数.証券会社コード
        SystemPreferencesPK l_systemPreferencesPK =
            new SystemPreferencesPK(l_strInstitutionCode
                + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
        try
        {
            l_systemPreferencesRow =
                SystemPreferencesDao.findRowByPk(l_systemPreferencesPK);
            this.usuallyDrawDate = Long.parseLong(
                l_systemPreferencesRow.getValue());
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //2) システムプリファ@レンステーブルから以下の条件に該当するレコードを取得し、値をthis.通常締切日起算日数へセットする。
        //[条件]
        //名称（環境変数名）：XX_SBS_DAY_COUNT
        //※XXは引数.証券会社コード
        l_systemPreferencesPK =
            new SystemPreferencesPK(l_strInstitutionCode
                + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
        try
        {
            l_systemPreferencesRow =
                SystemPreferencesDao.findRowByPk(l_systemPreferencesPK);
            this.usuallyCloseDateBaseDate =
                Long.parseLong(l_systemPreferencesRow.getValue());
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //3) システムプリファ@レンステーブルから以下の条件に該当するレコードを取得し、値をthis.賞与引落日へセットする。
        //[条件]
        //名称（環境変数名）：XX_SBS_DRAW_DD_BONUS
        //※XXは引数.証券会社コード
        l_systemPreferencesPK =
            new SystemPreferencesPK(l_strInstitutionCode
                + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
        try
        {
            l_systemPreferencesRow =
                SystemPreferencesDao.findRowByPk(l_systemPreferencesPK);
            this.prizeAndDrawDate =
                Long.parseLong(l_systemPreferencesRow.getValue());
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //4) システムプリファ@レンステーブルから以下の条件に該当するレコードを取得し、値をthis賞与締切日起算日数へセットする。
        //[条件]
        //名称（環境変数名）：XX_SBS_DAY_COUNT_BONUS
        //※XXは引数.証券会社コード
        l_systemPreferencesPK =
            new SystemPreferencesPK(l_strInstitutionCode
                + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
        try
        {
            l_systemPreferencesRow =
                SystemPreferencesDao.findRowByPk(l_systemPreferencesPK);
            this.prizeAndCloseDateBaseDate =
                Long.parseLong(l_systemPreferencesRow.getValue());
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //5) 取引時間テーブルを検索し、取引時間テーブルRow.開始時間をthis.定時定額買付締切時間へセットする。
        //[条件]
        //証券会社コード：引数.証券会社コード
        //部店コード：引数.部店コード
        //市場コード：0：DEFAULT
        //受付時間区分：33：投信定時定額買付
        //商品コード:0：DEFAULT
        //営業日区分：1：終日営業日
        //受付可能：0：受付可能
        //発注日計算：1：翌営業日
        //※ 取得したレコードが1件以外なら「データ不整合エラー」の例外をスローする。
        String l_strWhereClause = " institution_code = ? and branch_code = ?"
            + " and market_code = ? and trading_time_type = ? and product_code = ?"
            + " and biz_date_type = ? and enable_order = ? and bizdate_calc_parameter =? ";

        Object[] l_bindVars =
        {
            l_strInstitutionCode,
            l_strBranchCode,
            WEB3MarketCodeDef.DEFAULT,
            WEB3TradingTimeTypeDef.MF_FIXED_BUYING,
            WEB3ProductCodeDef.DEFAULT,
            WEB3BizDateTypeDef.BIZ_DATE,
            WEB3EnableOrderDef.ENABLE,
            WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE
        };
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisReturnLists =
                l_queryProcessor.doFindAllQuery(
                    TradingTimeRow.TYPE,
                    l_strWhereClause,
                    l_bindVars);
            int l_intSize = l_lisReturnLists.size();
            if (l_intSize == 0 || l_intSize > 1)
            {
                log.debug("データ不整合エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "データ不整合エラー。");
            }
            else
            {
                TradingTimeRow l_tradingTimeRow =
                    (TradingTimeRow)l_lisReturnLists.get(0);
                this.fixedBuyCloseDate =
                    Long.parseLong(l_tradingTimeRow.getStartTime());
            }
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);

        }

        log.exiting(STR_METHOD_NAME);
    }
}

@
