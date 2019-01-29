head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.25.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3TradeManagementLoginTableDataManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ログインテーブルデータマネージャ(WEB3TradeManagementLoginTableDataManager.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/22 肖志偉 (中訊) 新規作成
Revision History : 2008/10/06 肖志偉 (中訊) モデル013
Revision History : 2008/10/17 肖志偉 (中訊) モデル018
*/
package webbroker3.trademanagement;

import java.util.Date;

import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.gentrade.data.LoginHistoryPastRow;
import webbroker3.gentrade.data.LoginHistoryRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (ログインテーブルデータマネージャ)<BR>
 * ログインテーブルの管理をおこなうクラス。<BR>
 * <BR>
 * @@author 肖志偉(中訊)
 * @@version 1.0
 */
public class WEB3TradeManagementLoginTableDataManager
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TradeManagementLoginTableDataManager.class);

    /**
     * @@roseuid 48D75CD6006B
     */
    public WEB3TradeManagementLoginTableDataManager()
    {

    }

    /**
     * 画面入力された日付によって参照先テーブルを変化させる処理。 <BR>
     * <BR>
     * １） 入力時間帯のチェック <BR>
     * <BR>
     * 　@　@１－１） 現在時刻が 0:00:00～3:29:59 の場合 下記の処理を実施。 <BR>
     * 　@　@　@１－１－１） ①@yyyymmdd033000 <= (引数)日付 + (引数)時間(自) < ②yyyymmdd033000 <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@の場合は、ログイン履歴テーブルRowTypeを返却する。 <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@それ以外の場合は、ログイン過去履歴テーブルRowTypeを返却する。<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@※ ①@yyyymmddは現在日付 -1日 <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@②yyyymmddは現在日付 <BR>
     * <BR>
     * 　@　@１－２） 現在時刻が 3:30:00～23:59:59 の場合 下記の処理を実施。 <BR>
     * 　@　@　@１－２－１） ①@yyyymmdd033000 <= (引数)日付 + (引数)時間(自) < ②yyyymmdd033000 <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@の場合は、ログイン履歴テーブルRowTypeを返却する。 <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@それ以外の場合は、ログイン過去履歴テーブルRowTypeを返却する。<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@※ ①@yyyymmddは現在日付 <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@②yyyymmddは現在日付 +1日 <BR>
     * <BR>
     * @@param l_strDate - (日付)<BR>
     * 画面から入力された日付。'yyyymmdd' 形式。<BR>
     * @@param l_strStartTime - (時間(自))<BR>
     * 時間(自)。(hh24mi 形式)<BR>
     * @@return RowType
     * @@roseuid 48D1E5DA01FA
     */
    public RowType getRowType(String l_strDate, String l_strStartTime)
    {
        final String STR_METHOD_NAME = "getRowType(String, String)";
        log.entering(STR_METHOD_NAME);

        //現在日時
        Date l_datSystemTime = GtlUtils.getSystemTimestamp();

        //現在日付
        String l_strSystemTimeYMD =
            WEB3DateUtility.formatDate(l_datSystemTime, WEB3GentradeTimeDef.DATE_FORMAT_YMD);

        Date l_datSystemTimeYMD =
            WEB3DateUtility.getDate(l_strSystemTimeYMD, WEB3GentradeTimeDef.DATE_FORMAT_YMD);

        //(引数)日付 + (引数)時間(自)
        Date l_dateForCheck =
            WEB3DateUtility.getDate(
                l_strDate + l_strStartTime,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HM);

        //現在日付 のyyyymmdd033000
        Date l_datThreeThirty =
            WEB3DateUtility.addMinute(l_datSystemTimeYMD, 210);

        //現在日付 -1日 のyyyymmdd033000
        Date l_datThreeThirtyYesterday =
            WEB3DateUtility.addMinute(l_datSystemTimeYMD, -1230);

        //現在日付 +1日 のyyyymmdd033000
        Date l_datThreeThirtyForTomorrow =
            WEB3DateUtility.addMinute(l_datSystemTimeYMD, 1650);

        //１） 入力時間帯のチェック
        //　@　@１－１） 現在時刻が 0:00～3:29:59 の場合 下記の処理を実施。
        if (l_datSystemTime.before(l_datThreeThirty))
        {
            //１－１－１） ①@yyyymmdd033000 <= (引数)日付 + (引数)時間(自) < ②yyyymmdd033000
            if (WEB3DateUtility.compareToSecond(l_dateForCheck, l_datThreeThirtyYesterday) == 0
                || (l_dateForCheck.after(l_datThreeThirtyYesterday) && l_dateForCheck.before(l_datThreeThirty)))
            {
                //ログイン履歴テーブルRowTypeを返却する。
                log.exiting(STR_METHOD_NAME);
                return LoginHistoryRow.TYPE;
            }
            else
            {
                //それ以外の場合は、ログイン過去履歴テーブルRowTypeを返却する。
                log.exiting(STR_METHOD_NAME);
                return LoginHistoryPastRow.TYPE;
            }
        }
        //１－２） 現在時刻が 3:30～23:59:59 の場合 下記の処理を実施。
        else
        {
            //１－２－１） ①@yyyymmdd033000 <= (引数)日付 + (引数)時間(自) < ②yyyymmdd033000
            if (WEB3DateUtility.compareToSecond(l_dateForCheck, l_datThreeThirty) == 0
                || (l_dateForCheck.after(l_datThreeThirty) && l_dateForCheck.before(l_datThreeThirtyForTomorrow)))
            {
                //ログイン履歴テーブルRowTypeを返却する。
                log.exiting(STR_METHOD_NAME);
                return LoginHistoryRow.TYPE;
            }
            else
            {
                //それ以外の場合は、ログイン過去履歴テーブルRowTypeを返却する。
                log.exiting(STR_METHOD_NAME);
                return LoginHistoryPastRow.TYPE;
            }

        }

    }
}
@
