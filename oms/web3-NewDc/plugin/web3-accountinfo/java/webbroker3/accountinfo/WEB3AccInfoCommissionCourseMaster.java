head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.23.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoCommissionCourseMaster.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 委託手数料コースマスタ(WEB3AccInfoCommissionCourseMaster)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 張宝楠 (中訊) 新規作成
                   2006/01/31 鈴木（SRA) 仕様変更管理No.084
                   2006/06/30 丁昭奎 (中訊) 仕様変更管理No.112
                   2006/07/29 山田 (SCS) 仕様変更管理No.120
Revesion History : 2008/08/18 楊夫志 (中訊) 仕様変更・モデルNo.239,242,246
*/

package webbroker3.accountinfo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.accountinfo.data.CommissionCourseMasterDao;
import webbroker3.accountinfo.data.CommissionCourseMasterParams;
import webbroker3.accountinfo.data.CommissionCourseMasterRow;
import webbroker3.accountinfo.define.WEB3AccInfoRegistEndDaySpecDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccInfoCommissionDivDef;
import webbroker3.common.define.WEB3AppliStartDateDivDef;
import webbroker3.common.define.WEB3AppliTermDivDef;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeUtils;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (委託手数料コースマスタ)<BR>
 * 委託手数料コースマスタクラス<BR>
 *
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AccInfoCommissionCourseMaster implements BusinessObject
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoCommissionCourseMaster.class);

    /**
     * (委託手数料コースマスタ行)<BR>
     * 委託手数料コースマスタ行オブジェクト<BR>
     * <BR>
     * ※ 委託手数料コースマスタParamsクラスはDDLより自動生成する。<BR>
     */
    private CommissionCourseMasterParams commissionCourseMasterParams;

    /**
     * (委託手数料コースマスタ)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * 指定行オブジェクトをプロパティにセットし、インスタンスを生成する。 <BR>
     * @@param l_commissionCourseMasterParams - 委託手数料コースマスタ行オブジェクト<BR>
     * <BR>
     * ※　@委託手数料コースマスタParamsはDDLより自動生成する。<BR>
     * @@roseuid 413D9D960038
     */
    public WEB3AccInfoCommissionCourseMaster(CommissionCourseMasterParams l_commissionCourseMasterParams)
    {
        this.commissionCourseMasterParams = l_commissionCourseMasterParams;
    }

    /**
     * (委託手数料コースマスタ)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * 証券会社コード，手数料商品コード，手数料コースコードに該当する行を、<BR>
     * 委託手数料コースマスタテーブルより検索する。<BR>
     * （該当行がない場合は、例外をスローする）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01168<BR>
     * <BR>
     * 検索結果の委託手数料コースマスタ行オブジェクトを引数に指定して、<BR>
     * コンストラクタをコールする。 <BR>
     * コンストラクタの戻り値を返却する。 <BR>
     *
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strCommissionProductCode - 手数料商品コード
     * @@param l_strCommissionCourseCode - 手数料コースコード
     * @@return webbroker3.accountinfo.WEB3AccInfoCommissionCourseMaster
     * @@roseuid 413D86280103
     */
    public WEB3AccInfoCommissionCourseMaster(String l_strInstitutionCode, String l_strCommissionProductCode, String l_strCommissionCourseCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " WEB3AccInfoCommissionCourseMaster(String, String, String)";
        log.entering(STR_METHOD_NAME);

        //証券会社コード，手数料商品コード，手数料コースコードに該当する行を、
        //委託手数料コースマスタテーブルより検索する。
        try
        {
            CommissionCourseMasterRow l_commissionCourseMasterRow = CommissionCourseMasterDao.findRowByPk(
                l_strInstitutionCode,           //証券会社コード
                l_strCommissionProductCode,     //手数料商品コード
                l_strCommissionCourseCode       //手数料コースコード
                );
                log.debug("l_strInstitutionCode===" + l_strInstitutionCode);
                log.debug("l_strCommissionProductCode===" + l_strCommissionProductCode);
                log.debug("l_strCommissionCourseCode===" + l_strCommissionCourseCode);

            if (l_commissionCourseMasterRow == null)
            {
                //該当行がない場合は、例外をスローする
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01168,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            else
            {
                this.commissionCourseMasterParams = (CommissionCourseMasterParams)l_commissionCourseMasterRow;
            }

        }
        catch (DataFindException l_ex)
        {
            //該当行がない場合は、例外をスローする
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01168,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （getDataSourceObjectの実装） <BR>
     * <BR>
     * this.委託手数料コースマスタ行を返却する。 <BR>
     * @@return Object
     * @@roseuid 413D6E360076
     */
    public Object getDataSourceObject()
    {
        return this.commissionCourseMasterParams;
    }

    /**
     * 　@this.委託手数料コースマスタ行をコピーして、同じ内容の別インスタンスを<BR>
     * 作成する（clone）。 <BR>
     * 作成したコピーを自身のthis.委託手数料コースマスタ行にセットする。 <BR>
     * @@roseuid 413D6E360086
     */
    public void createForUpdateParams()
    {
        CommissionCourseMasterParams l_params = new CommissionCourseMasterParams(this.commissionCourseMasterParams);
        this.commissionCourseMasterParams = l_params;
    }

    /**
     * (get変更申込締切日時)<BR>
     * 変更申込締切日時を取得する。<BR>
     * <BR>
     * this.委託手数料コースマスタ行.変更申込締切指定日，<BR>
     * this.委託手数料コースマスタ行.変更申込締切時間<BR>
     * より、変更申込締切日時を編集する。<BR>
     * <BR>
     * ※ 変更申込締切日時の編集<BR>
     * <BR>
     * 　@【日付の計算】<BR>
     * 　@①@　@処理日時より計算する。<BR>
     * 　@－（変更申込締切指定日 == 00：毎日または01：毎週）の場合、<BR>
     * 　@　@処理日時(*1)の年月日（YYYYMMDD） + 変更申込締切時間（HHMMSS）<BR>
     * <BR>
     * 　@－以外の場合、<BR>
     * 　@　@処理日時(*1)の年月（YYYYMM） + 変更申込締切指定日（DD） + <BR>
     * 変更申込締切時間（HHMMSS）<BR>
     * <BR>
     * 　@②　@変更申込締切日時の計算<BR>
     * 　@－（①@の計算結果 >= 処理日時）の場合、①@の計算結果をDate型に<BR>
     * 変換した値を返却する。<BR>
     * 　@－以外の場合、以下のDate型値を返却する。<BR>
     * 　@　@－（変更申込締切指定日 == 00：毎日）の場合、<BR>
     * 　@　@　@処理日時の翌営業日（YYYYMMDD） + <BR>
     * 変更申込締切時間（HHMMSS）<BR>
     * <BR>
     * 　@　@－（変更申込締切指定日 == 01：毎週）の場合、<BR>
     * 　@　@　@処理日時の翌週第一営業日（YYYYMMDD）+ 変更申込締切時間（HHMMSS）<BR>
     * <BR>
     * 　@　@　@※翌週第一営業日は以下の処理にて取得する。 <BR>
     * 　@　@　@　@１．「営業日計算(基準日 : Timestamp)」コール<BR>
     * 　@　@　@　@　@　@[引数] 基準日：処理日時 から7日後<BR>
     * 　@　@　@　@２．「get週初営業日」コール<BR>
     * <BR>
     * 　@　@－以外の場合、<BR>
     * 　@　@　@処理日時の翌月（YYYYMM） + 変更申込締切指定日（DD） + 変更申込締切時間（HHMMSS）<BR>
     * <BR>
     * 　@(*1)　@処理日時：　@（変更申込締切指定日 == 00：毎日）の場合かつ、TradingSystem.getSystemTimestamp()で<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@取得した日付が非営業日の場合は翌営業日を算出する。<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@（変更申込締切指定日 == 01：毎週）の場合、今週第一営業日を使用する。<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@それ以外の場合は、TradingSystem.getSystemTimestamp()の戻り値を使用する。 <BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@※今週第一営業日は以下の処理にて取得する。<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@１．「営業日計算(基準日 : Timestamp)」コール<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@[引数] 基準日：TradingSystem.getSystemTimestamp()の戻り値<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@２．「get週初営業日」コール<BR>
     * @@return Date
     * @@roseuid 413D6F3E0086
     */
    public Date getRegistEndTimestamp() throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " getRegistEndTimestamp()";
        log.entering(STR_METHOD_NAME);

        //変更申込締切指定日
        String l_strRegistEndDaySpec = this.commissionCourseMasterParams.getRegistEndDaySpec();
        //変更申込締切時間
        String l_strRegistEndTime = this.commissionCourseMasterParams.getRegistEndTime();

        //処理日時(*1)
        //変更申込締切指定日 == 00：毎日）の場合かつ、TradingSystem.getSystemTimestamp()で
        //取得した日付が非営業日の場合は翌営業日を算出する。
        Timestamp l_datProcessDate = GtlUtils.getSystemTimestamp();
        String l_strProcessDateYMD = null;
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(
            WEB3GentradeTradingTimeManagement.getBizDateType(l_datProcessDate))
            && WEB3AccInfoRegistEndDaySpecDivDef.EVERYDAY.equals(l_strRegistEndDaySpec))
        {
            WEB3GentradeBizDate l_date = new WEB3GentradeBizDate(l_datProcessDate);
            Timestamp l_tsNextProcessDate = l_date.roll(1);
            l_strProcessDateYMD =
                WEB3DateUtility.formatDate(l_tsNextProcessDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        }
        //（変更申込締切指定日 == 01：毎週）の場合、今週第一営業日を使用する。
        else if (WEB3AccInfoRegistEndDaySpecDivDef.EVERYWEEK.equals(l_strRegistEndDaySpec))
        {
            //※今週第一営業日は以下の処理にて取得する。
            //１．「営業日計算(基準日 : Timestamp)」コール
            //[引数] 基準日：TradingSystem.getSystemTimestamp()の戻り値
            WEB3GentradeBizDate l_date = new WEB3GentradeBizDate(l_datProcessDate);
            //２．「get週初営業日」コール
            Timestamp l_tsThisWeekProcessDate = l_date.getWeekStartBizDate();
            l_strProcessDateYMD =
                WEB3DateUtility.formatDate(l_tsThisWeekProcessDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        }
        else
        {
            l_strProcessDateYMD =
                WEB3DateUtility.formatDate(l_datProcessDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        }
        String l_strProcessDateYM =
            WEB3DateUtility.formatDate(l_datProcessDate, WEB3GentradeTimeDef.DATE_FORMAT_YM);

        //変更申込締切日時
        Date l_datRegistEndDate = null;

        //①@　@処理日時より計算する。
        //（変更申込締切指定日 == 00：毎日または01：毎週）の場合
        if (WEB3AccInfoRegistEndDaySpecDivDef.EVERYDAY.equals(l_strRegistEndDaySpec)
            || WEB3AccInfoRegistEndDaySpecDivDef.EVERYWEEK.equals(l_strRegistEndDaySpec))
        {
            //処理日時(*1)の年月日（YYYYMMDD） + 変更申込締切時間（HHMMSS）
            String l_strDate = l_strProcessDateYMD + " " + l_strRegistEndTime;
            l_datRegistEndDate = WEB3DateUtility.getDate(l_strDate,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + " " + WEB3GentradeTimeDef.TIME_FORMAT_HMS);
        }
        else
        {
            //以外の場合、
            //処理日時(*1)の年月（YYYYMM） + 変更申込締切指定日（DD） + 変更申込締切時間（HHMMSS）
            String l_strDate = l_strProcessDateYM + l_strRegistEndDaySpec + " " + l_strRegistEndTime;
            l_datRegistEndDate = WEB3DateUtility.getDate(l_strDate,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + " " + WEB3GentradeTimeDef.TIME_FORMAT_HMS);
        }

        //②　@変更申込締切日時の計算
        //(計算結果 < 処理日時）の場合
        if (WEB3DateUtility.compareToSecond(l_datRegistEndDate, l_datProcessDate) < 0)
        {
            Calendar l_calendar = new GregorianCalendar();
            l_calendar.setTime(l_datProcessDate);   //処理日時

            if (WEB3AccInfoRegistEndDaySpecDivDef.EVERYDAY.equals(l_strRegistEndDaySpec))
            {
                //（変更申込締切指定日 == 00：毎日）の場合、
                //処理日時の翌営業日（YYYYMMDD） + 変更申込締切時間（HHMMSS）
                WEB3GentradeBizDate l_date = new WEB3GentradeBizDate(l_datProcessDate);
                Timestamp l_tsNextProcessDate = l_date.roll(1);
                String l_strDateYMD =
                    WEB3DateUtility.formatDate(l_tsNextProcessDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
                String l_strDate = l_strDateYMD + " " + l_strRegistEndTime;
                l_datRegistEndDate = WEB3DateUtility.getDate(l_strDate,
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD + " " + WEB3GentradeTimeDef.TIME_FORMAT_HMS);
            }
            else if (WEB3AccInfoRegistEndDaySpecDivDef.EVERYWEEK.equals(l_strRegistEndDaySpec))
            {
                //（変更申込締切指定日 == 01：毎週）の場合
                //処理日時の翌週第一営業日（YYYYMMDD）+ 変更申込締切時間（HHMMSS）
                //※翌週第一営業日は以下の処理にて取得する。
                //１．「営業日計算(基準日 : Timestamp)」コール
                //[引数] 基準日：処理日時 から7日後
                Date l_datProcessDateSevenDayAfter = WEB3DateUtility.addDay(l_datProcessDate, 7);
                WEB3GentradeBizDate l_dateOfNextWeek =
                    new WEB3GentradeBizDate(new Timestamp(l_datProcessDateSevenDayAfter.getTime()));
                //２．「get週初営業日」コール
                Timestamp l_tsNextWeekProcessDate = l_dateOfNextWeek.getWeekStartBizDate();
                String l_strDateYMD =
                    WEB3DateUtility.formatDate(l_tsNextWeekProcessDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
                String l_strDate = l_strDateYMD + " " + l_strRegistEndTime;
                l_datRegistEndDate = WEB3DateUtility.getDate(l_strDate,
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD + " " + WEB3GentradeTimeDef.TIME_FORMAT_HMS);
            }
            else
            {
                //以外の場合、
                //処理日時の翌月（YYYYMM） + 変更申込締切指定日（DD） + 変更申込締切時間（HHMMSS）
                l_calendar.add(Calendar.MONTH, 1);
                String l_strDateYM =
                    WEB3DateUtility.formatDate(l_calendar.getTime(), WEB3GentradeTimeDef.DATE_FORMAT_YM);
                String l_strDate = l_strDateYM + l_strRegistEndDaySpec + " " + l_strRegistEndTime;
                l_datRegistEndDate = WEB3DateUtility.getDate(
                    l_strDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD + " " + WEB3GentradeTimeDef.TIME_FORMAT_HMS);
            }

        }

        log.exiting(STR_METHOD_NAME);
        return l_datRegistEndDate;
    }

    /**
     * (get適用開始日時)<BR>
     * 変更適用開始日時を取得する。<BR>
     * <BR>
     * this.get変更申込締切日時()，<BR>
     * this.委託手数料コースマスタ行.変更適用開始日指定区分，<BR>
     * this.委託手数料コースマスタ行.変更適用開始日数，<BR>
     * this.委託手数料コースマスタ行.適用開始／終了時間<BR>
     * より、変更適用開始日時を編集する。<BR>
     * <BR>
     * ※ 変更適用開始日時の編集<BR>
     * 　@【日付の計算】<BR>
     * 　@　@－（変更適用開始日指定区分 == 1：申込日の翌月（月初営業日））の場合、<BR>
     * 　@　@変更申込締切日時(*1)の翌月の月初営業日<BR>
     * <BR>
     * 　@　@－（変更適用開始日指定区分 == 2：申込日の翌々月（月初営業日））の場合、<BR>
     * 　@　@変更申込締切日時(*1)の翌々月の月初営業日<BR>
     * <BR>
     * 　@　@－（変更適用開始日指定区分 == 9：日数指定）の場合、<BR>
     * 　@　@変更申込締切日時(*1)の日付から、営業日ベースで <BR>
     * [変更適用開始日数] 後（片端）の日付。<BR>
     * <BR>
     * 　@　@上記で計算した日付（YYMMDD）＋適用開始／終了時間（HHMMSS）を<BR>
     * Date型で返却する。<BR>
     * <BR>
     * (*1)　@変更申込締切日時：　@this.get変更申込締切日時()<BR>
     * @@return Date
     * @@roseuid 413D775202F7
     */
    public Date getAppliStartTimestamp() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getAppliStartTimestamp()";
        log.entering(STR_METHOD_NAME);

        //this.get変更申込締切日時()
        Date l_datRegistEndDate = getRegistEndTimestamp();
        Calendar l_calendar = new GregorianCalendar();
        l_calendar.setTime(l_datRegistEndDate);

        //this.委託手数料コースマスタ行.変更適用開始日指定区分
        String l_strAppliStartDateDiv = this.commissionCourseMasterParams.getAppliStartDateDiv();
        //this.委託手数料コースマスタ行.変更適用開始日数
        int l_intAppliStartDayCount = this.commissionCourseMasterParams.getAppliStartDayCount();
        //this.委託手数料コースマスタ行.適用開始／終了時間
        String l_strAppliStartEndTime = this.commissionCourseMasterParams.getAppliStartEndTime();

        //変更適用開始日付
        String l_strDateYMD = "";

        if (WEB3AppliStartDateDivDef.NEXT_MONTH_OF_APPLI_DAY.equals(l_strAppliStartDateDiv))
        {
            //（変更適用開始日指定区分 == 1：申込日の翌月（月初営業日））の場合、
            //変更申込締切日時の翌月の月初営業日
            l_calendar.add(Calendar.MONTH, 1);
            l_calendar.set(Calendar.DATE, l_calendar.getActualMinimum(Calendar.DATE));

            l_strDateYMD = WEB3DateUtility.formatDate(WEB3GentradeUtils.getBizDate(l_calendar.getTime(), 0), "yyyyMMdd");
        }
        else if (WEB3AppliStartDateDivDef.TWO_MONTHS_AFTER_OF_APPLI_DAY.equals(l_strAppliStartDateDiv))
        {
            //（変更適用開始日指定区分 == 2：申込日の翌々月（月初営業日））の場合、
            //変更申込締切日時の翌々月の月初営業日
            l_calendar.add(Calendar.MONTH, 2);
            l_calendar.set(Calendar.DATE, l_calendar.getActualMinimum(Calendar.DATE));

            l_strDateYMD = WEB3DateUtility.formatDate(WEB3GentradeUtils.getBizDate(l_calendar.getTime(), 0), "yyyyMMdd");
        }
        else if (WEB3AppliStartDateDivDef.DAYS_DESIGNATED.equals(l_strAppliStartDateDiv))
        {
            //（変更適用開始日指定区分 == 9：日数指定）の場合、
            // 変更申込締切日時(*1)の日付から、営業日ベースで [変更適用開始日数] 後（片端）の日付。

            l_strDateYMD = WEB3DateUtility.formatDate(WEB3GentradeUtils.getBizDate(l_calendar.getTime(), l_intAppliStartDayCount), "yyyyMMdd");
        }
        else
        {
            log.error("データ不整合エラー: 変更適用開始日指定区分 = " + l_strAppliStartDateDiv);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME
                );
        }

        //上記で計算した日付（YYMMDD）＋適用開始／終了時間（HHMMSS）をDate型で返却する。
        String l_strDate = l_strDateYMD + " " + l_strAppliStartEndTime;

        //変更適用開始日時
        Date l_datAppliStartDate = WEB3DateUtility.getDate(l_strDate, "yyyyMMdd HHmmss");

        log.exiting(STR_METHOD_NAME);
        return l_datAppliStartDate;
    }

    /**
     * (get適用終了日時)<BR>
     * 変更適用終了日時を取得する。<BR>
     * <BR>
     * this.get適用開始日時()，<BR>
     * this.委託手数料コースマスタ行.適用期間区分，<BR>
     * this.委託手数料コースマスタ行.適用期間数<BR>
     * より、変更適用終了日時を編集する。<BR>
     * <BR>
     * ※ 変更適用終了日時の編集<BR>
     * <BR>
     * 　@　@－（適用期間区分 == 0：DEFAULT（期間制限なし））の場合、<BR>
     * 　@　@日付最大値（HighValue：9999/12/31 00：00：00）を返却する。<BR>
     * <BR>
     * 　@　@－（適用期間数区分 == 1：年数指定）の場合、<BR>
     * 　@　@適用開始日時(*1)の [適用期間数] 年後（片端）の日時。<BR>
     * <BR>
     * 　@　@－（適用期間数区分 == 2：月数指定）の場合、<BR>
     * 　@　@適用開始日時(*1)の [適用期間数] 月後（片端）の日時。<BR>
     * <BR>
     * 　@　@－（適用期間数区分 == 3：日数指定）の場合、<BR>
     * 　@　@適用開始日時(*1)の [適用期間数] 日後（片端）の日時。<BR>
     * <BR>
     * 年数指定／月数指定で、カレンダー上存在しない日付になる場合は、<BR>
     * 最終日に補正する。<BR>
     * 2004/8/31の1ヵ月後　@の場合は、2004/9/30。<BR>
     * <BR>
     * (*1)　@適用開始日時：　@this.get適用開始日時()<BR>
     * @@return Date
     * @@roseuid 413D767002B8
     */
    public Date getAppliEndTimestamp() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getAppliEndTimestamp()";
        log.entering(STR_METHOD_NAME);

        //this.get適用開始日時()
        Date l_datAppliStartDate = getAppliStartTimestamp();
        Calendar l_calendar = new GregorianCalendar();
        l_calendar.setTime(l_datAppliStartDate);

        //変更適用終了日時
        Date l_datAppliEndDate = null;

        //this.委託手数料コースマスタ行.適用期間区分
        String l_strAppliTermDiv = this.commissionCourseMasterParams.getAppliTermDiv();
        // this.委託手数料コースマスタ行.適用期間数
        int l_intAppliTermDateCount = this.commissionCourseMasterParams.getAppliTermDateCount();

        if (WEB3AppliTermDivDef.DEFAULT.equals(l_strAppliTermDiv))
        {
            //（適用期間区分 == 0：DEFAULT（期間制限なし））の場合、
            //日付最大値（HighValue：9999/12/31 00：00：00）を返却する。

            l_datAppliEndDate = WEB3DateUtility.getDate("9999-12-31 00:00:00", "yyyy-MM-dd HH:mm:ss");
        }
        else if (WEB3AppliTermDivDef.YEARS_DESIGNATED.equals(l_strAppliTermDiv))
        {
            //（適用期間数区分 == 1：年数指定）の場合、
            //適用開始日時(*1)の [適用期間数] 年後（片端）の日時。
            l_calendar.add(Calendar.YEAR, l_intAppliTermDateCount);
            l_datAppliEndDate = l_calendar.getTime();
        }
        else if (WEB3AppliTermDivDef.MONTHS_DESIGNATED.equals(l_strAppliTermDiv))
        {
            //（適用期間数区分 == 2：月数指定）の場合、
            //適用開始日時(*1)の [適用期間数] 月後（片端）の日時。
            l_calendar.add(Calendar.MONTH, l_intAppliTermDateCount);
            l_datAppliEndDate = l_calendar.getTime();
        }
        else if (WEB3AppliTermDivDef.DAYS_DESIGNATED.equals(l_strAppliTermDiv))
        {
            //（適用期間数区分 == 3：日数指定）の場合、
            //適用開始日時(*1)の [適用期間数] 日後（片端）の日時。
            l_calendar.add(Calendar.DATE, l_intAppliTermDateCount);
            l_datAppliEndDate = l_calendar.getTime();
        }
        else
        {
            log.error("データ不整合エラー: 適用期間区分 = " + l_strAppliTermDiv);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME
                );
        }

        log.exiting(STR_METHOD_NAME);
        return l_datAppliEndDate;
    }

    /**
     * (get証券会社コード)<BR>
     * this.委託手数料コースマスタ行.証券会社コードを返却する。<BR>
     * @@return String
     * @@roseuid 413D9F040113
     */
    public String getInstitutionCode()
    {
        return this.commissionCourseMasterParams.getInstitutionCode();
    }

    /**
     * (get手数料商品コード)<BR>
     * this.委託手数料コースマスタ行.手数料商品コードを返却する。<BR>
     * @@return String
     * @@roseuid 413D9F350141
     */
    public String getCommissionProductCode()
    {
        return this.commissionCourseMasterParams.getCommProductCode();
    }

    /**
     * (get手数料コースコード)<BR>
     * this.委託手数料コースマスタ行.手数料コースコードを返却する。<BR>
     * @@return String
     * @@roseuid 413D9F5502D8
     */
    public String getCommissionCourseCode()
    {
        return this.commissionCourseMasterParams.getCommissionCourseDiv();
    }

    /**
     * (get手数料区分)<BR>
     * this.委託手数料コースマスタ行.手数料区分を返却する。<BR>
     * @@return String
     */
    public String getCommissionDiv()
    {
        return this.commissionCourseMasterParams.getCommissionDiv();
    }

    /**
     * (get取扱可能委託手数料コース)<BR>
     * （static メソッド）<BR>
     * 証券会社に該当する委託手数料コースマスタを配列にて取得する。<BR>
     * <BR>
     * １）　@委託手数料コースマスタテーブル検索<BR>
     * 　@以下の条件で、委託手数料コースマスタテーブルを検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@証券会社コード = 証券会社コード<BR>
     * 　@手数料商品コード = 手数料商品コード<BR>
     * <BR>
     * ２）　@返却値生成<BR>
     * 　@Hashtableを生成し、取得した行オブジェクト各要素について<BR>
     * 委託手数料コースマスタオブジェクトを生成し返却する。<BR>
     * <BR>
     * 　@[コンストラクタの引数]<BR>
     * 　@委託手数料コースマスタ行：　@（各要素）<BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strCommissionProductCode - 手数料商品コード
     * @@return webbroker3.accountinfo.WEB3AccInfoCommissionCourseMaster[]
     * @@roseuid 413DA9DF0190
     */
    public static WEB3AccInfoCommissionCourseMaster[] getHandlingPossibleCommissionCourse(String l_strInstitutionCode, String l_strCommissionProductCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getHandlingPossibleCommissionCourse(String, String)";
        log.entering(STR_METHOD_NAME);

        List l_lisRecords = null;

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            String l_strQueryString = "institution_code = ? and comm_product_code = ?";
            Object[] l_queryDataContainer = new Object[] {
                l_strInstitutionCode,
                l_strCommissionProductCode
                };

            //以下の条件で、委託手数料コースマスタテーブルを検索する。
            //[条件]
            //証券会社コード = 証券会社コード
            //手数料商品コード = 手数料商品コード
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                CommissionCourseMasterRow.TYPE,
                l_strQueryString,
                l_queryDataContainer
                );
        }
        catch (DataFindException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                WEB3AccInfoCommissionCourseMaster.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseMaster.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseMaster.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        int l_intSize = l_lisRecords.size();

        if (l_intSize == 0)
        {
            return null;
        }

        WEB3AccInfoCommissionCourseMaster[] l_commissionCourseMasters =
            new WEB3AccInfoCommissionCourseMaster[l_intSize];

        for (int i = 0; i < l_intSize; i++)
        {
            l_commissionCourseMasters[i] =
                new WEB3AccInfoCommissionCourseMaster((CommissionCourseMasterParams)l_lisRecords.get(i));
        }

        log.exiting(STR_METHOD_NAME);
        return l_commissionCourseMasters;

    }

    /**
     * (get取扱可能委託手数料コース)<BR>
     * （static メソッド）<BR>
     * 証券会社に該当する委託手数料コースマスタを配列にて取得する。<BR>
     * <BR>
     * １）委託手数料コースマスタテーブルを検索する。<BR>
     * <BR>
     * 　@　@＜引数.信用口座開設フラグ == true の場合＞<BR>
     * 　@　@　@以下の条件で、委託手数料コースマスタテーブルを検索する。<BR>
     * <BR>
     * 　@　@　@[条件]<BR>
     * 　@　@　@証券会社コード = 引数.証券会社コード<BR>
     * 　@　@　@手数料商品コード = 引数.手数料商品コード<BR>
     * 　@　@　@手数料区分 = 0 または 1（現物または信用）<BR>
     * <BR>
     * 　@　@＜上記に当てはまらない場合＞<BR>
     * 　@　@　@以下の条件で、委託手数料コースマスタテーブルを検索する。<BR>
     * <BR>
     * 　@　@　@[条件]<BR>
     * 　@　@　@証券会社コード = 引数.証券会社コード<BR>
     * 　@　@　@手数料商品コード = 引数.手数料商品コード<BR>
     * 　@　@　@手数料区分 = 0 （現物）<BR>
     * <BR>
     * ２）Hashtableを生成し、取得した行オブジェクト各要素について委託手数料コースマスタオブジェクトを生成し返却する。
     * <BR>
     * 　@　@[コンストラクタの引数]<BR>
     * 　@　@委託手数料コースマスタ行：　@（各要素）<BR>
     * @@param l_strInstitutionCode - (証券会社コード)）<BR>
     * 証券会社コード。）<BR>
     * @@param l_strCommissionProductCode - (手数料商品コード)）<BR>
     * 手数料商品コード。）<BR>
     * @@param l_strMarginOpenFlag - (信用口座開設フラグ)）<BR>
     * 信用口座開設フラグ。）<BR>
     * @@return WEB3AccInfoCommissionCourseMaster[]
     * @@throws WEB3BaseException
     */
    public static WEB3AccInfoCommissionCourseMaster[] getHandlingPossibleCommissionCourse(
        String l_strInstitutionCode,
        String l_strCommissionProductCode,
        boolean l_strMarginOpenFlag)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getHandlingPossibleCommissionCourse(String, String, boolean)";
        log.entering(STR_METHOD_NAME);

        List l_lisRecords = new ArrayList();

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            StringBuffer l_strWhere = new StringBuffer();
            l_strWhere.append(" institution_code = ? ");
            l_strWhere.append(" and comm_product_code = ? ");
            Object[] l_queryDataContainer = null;
            //引数.信用口座開設フラグ == true の場合
            if (l_strMarginOpenFlag)
            {
                l_strWhere.append(" and ( commission_div = ? ");
                l_strWhere.append(" or commission_div= ? ) ");
                l_queryDataContainer = new Object[] {
                    l_strInstitutionCode,
                    l_strCommissionProductCode,
                    WEB3AccInfoCommissionDivDef.EQUITY_TRADE_COMMISSION,
                    WEB3AccInfoCommissionDivDef.MARGIN_TRADE_COMMISSION
                    };
            }
            //上記に当てはまらない場合
            else
            {
                l_strWhere.append(" and commission_div = ? ");
                l_queryDataContainer = new Object[] {
                    l_strInstitutionCode,
                    l_strCommissionProductCode,
                    WEB3AccInfoCommissionDivDef.EQUITY_TRADE_COMMISSION
                    };
            }

            //以下の条件で、委託手数料コースマスタテーブルを検索する。
            //[条件]
            //証券会社コード = 証券会社コード
            //手数料商品コード = 手数料商品コード
            //手数料区分 = 0 または 1（現物または信用）＜引数.信用口座開設フラグ == true の場合＞
            //手数料区分 = 0 （現物）＜上記に当てはまらない場合＞
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                CommissionCourseMasterRow.TYPE,
                l_strWhere.toString(),
                l_queryDataContainer
                );
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseMaster.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseMaster.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        int l_intSize = l_lisRecords.size();

        if (l_intSize == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        WEB3AccInfoCommissionCourseMaster[] l_commissionCourseMasters =
            new WEB3AccInfoCommissionCourseMaster[l_intSize];

        for (int i = 0; i < l_intSize; i++)
        {
            l_commissionCourseMasters[i] =
                new WEB3AccInfoCommissionCourseMaster((CommissionCourseMasterParams)l_lisRecords.get(i));
        }

        log.exiting(STR_METHOD_NAME);
        return l_commissionCourseMasters;
    }
}
@
