head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.22.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTraderAdminLoginCountListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・IP別ログイン回数一覧検索結果リクエスト(WEB3AdminTraderAdminLoginCountListRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 孟亞南 (中訊) 新規作成 モデルNo.005,007,010
*/

package webbroker3.trademanagement.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者・IP別ログイン回数一覧検索結果リクエスト)<BR>
 * 管理者・IP別ログイン回数一覧検索結果リクエストクラス。<BR>
 * <BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3AdminTraderAdminLoginCountListRequest extends WEB3GenRequest
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTraderAdminLoginCountListRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_trader_admin_login_count_list";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200809221758L;

    /**
     * (日付)<BR>
     * 日付。('yyyymmdd' 形式で入力)<BR>
     */
    public String searchDate;

    /**
     * (時間(自))<BR>
     * 時間(自)。('hh24mi'形式で入力)<BR>
     */
    public String startTime;

    /**
     * (時間(至))<BR>
     * 時間(至)。('hh24mi'形式で入力)<BR>
     */
    public String endTime;

    /**
     * (ランク)<BR>
     * ログイン処理回数の順位。<BR>
     */
    public String rank;

    /**
     * (ページ内表示行数)<BR>
     * ページ内表示行数。<BR>
     */
    public String pageSize;

    /**
     * (要求ページ番号)<BR>
     * 要求ページ番号。<BR>
     */
    public String pageIndex;

    /**
     * @@roseuid 48D75CD6026F
     */
    public WEB3AdminTraderAdminLoginCountListRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）日付チェック<BR>
     * 　@１－１）this.日付 == nullの場合は、<BR>
     * 　@　@　@　@　@「オペレーション日付が未指定です。」<BR>
     *           (BUSINESS_ERROR_01272)の例外をスローする。<BR>
     * <BR>
     * 　@１－２）this.日付の入力形式が 'yyyymmdd' ではない場合は、<BR>
     * 　@　@　@　@　@「日付が不正です。」<BR>
     *           (BUSINESS_ERROR_02994)の例外をスローする。<BR>
     * <BR>
     * ２）時間(自)チェック<BR>
     * 　@２－１）this.時間(自) == nullの場合は、<BR>
     * 　@　@　@　@　@「時間(自)がnullです。」の例外をスローする。<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag: BUSINESS_ERROR_03118<BR>
     * <BR>
     * 　@２－２）this.時間(自)の入力形式が 'hh24mi' ではない場合は、<BR>
     * 　@　@　@　@　@「表示期間（自）日付フォーマットエラー。」<BR>
     *           (BUSINESS_ERROR_01065)の例外をスローする。<BR>
     * <BR>
     * ３）時間(至)チェック<BR>
     * 　@３－１）this.時間(至) == nullの場合は、<BR>
     * 　@　@　@　@　@「時間(至)がnullです。」の例外をスローする。<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag: BUSINESS_ERROR_03119<BR>
     * <BR>
     * 　@３－２）this.時間(至)の入力形式が 'hh24mi' ではない場合は、<BR>
     * 　@　@　@　@　@「表示期間（至）日付フォーマットエラー。」<BR>
     *           (BUSINESS_ERROR_01066)の例外をスローする。<BR>
     * <BR>
     * ４）入力時間整合性チェック<BR>
     * 　@４－１）this.時間(自) > this.時間(至) の場合は、<BR>
     * 　@　@　@　@　@「表示期間（自）は表示期間（至）より大きいです。」<BR>
     * 　@　@　@　@　@(BUSINESS_ERROR_01051)の例外をスローする。<BR>
     * <BR>
     * 　@４－２） [this.時間(至) - this.時間(自)] > １時間 の場合は、<BR>
     * 　@　@　@　@　@「指定範囲は１時間以内で入力してください。」の例外をスローする。<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag: BUSINESS_ERROR_03128<BR>
     * <BR>
     * ５）ランクチェック<BR>
     * 　@５－１）this.ランク == nullの場合は、<BR>
     * 　@　@　@　@　@「ランクが未入力です。」の例外をスローする。<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag: BUSINESS_ERROR_03129<BR>
     * <BR>
     * 　@５－２）this.ランク に半角数字以外の文字が入っていた場合は、<BR>
     * 　@　@　@　@　@「ランクは半角数字で入力してください。」の例外をスローする。<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag: BUSINESS_ERROR_03130<BR>
     * <BR>
     * 　@５－３）this.ランク > 30 の場合は、<BR>
     * 　@　@　@　@　@「ランクは上位30位までの表示しかできません。」の例外をスローする。<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag: BUSINESS_ERROR_03131<BR>
     * <BR>
     * ６）ページ内表示行数チェック <BR>
     * 　@６－１）this.ページ内表示行数 == nullの場合、 <BR>
     * 　@　@　@　@　@「ページ内表示行数の入力が不正です。」の例外をスローする。 <BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag: BUSINESS_ERROR_00091<BR>
     * <BR>
     *　@６－２）this.ページ内表示行数が半角数字以外の値であった場合、 <BR>
     *　@　@　@　@　@「ページ内表示行数が数字以外の値です。」の例外をスローする。 <BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag: BUSINESS_ERROR_00092<BR>
     * <BR>
     *　@６－３）this.ページ内表示行数 <= 0であった場合、 <BR>
     *　@　@　@　@　@「ページ内表示行数の値が0以下です。」の例外をスローする。 <BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag: BUSINESS_ERROR_00617<BR>
     * <BR>
     * ７）要求ページ番号チェック <BR>
     *　@７－１）this.要求ページ番号 == nullの場合、 <BR>
     *　@　@　@　@　@「要求ページ番号が未指定です。」の例外をスローする。 <BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag: BUSINESS_ERROR_00089<BR>
     * <BR>
     *　@７－２）this.要求ページ番号が半角数字以外の値であった場合、 <BR>
     *　@　@　@　@　@「要求ページ番号が数字以外の値です。」の例外をスローする。 <BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag: BUSINESS_ERROR_00090<BR>
     * <BR>
     *　@７－３）this.要求ページ番号 <= 0であった場合、 <BR>
     *　@　@　@　@　@「要求ページ番号の値が0以下です。」の例外をスローする。<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag: BUSINESS_ERROR_00616<BR>
     * @@roseuid 48BCCEB20088
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        //１）日付チェック
        //１－１）this.日付 == nullの場合は、
        //「オペレーション日付が未指定です。」
        //(BUSINESS_ERROR_01272)の例外をスローする。
        if (this.searchDate == null)
        {
            log.debug("オペレーション日付が指定されていません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01272,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "オペレーション日付が指定されていません。");
        }

        //１－２）this.日付の入力形式が 'yyyymmdd' ではない場合は、
        //「日付が不正です。」
        //(BUSINESS_ERROR_02994)の例外をスローする。
        if (!WEB3StringTypeUtility.isDateStr(this.searchDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD))
        {
            log.debug("日付が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02994,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "日付が不正です。");
        }

        //２）時間(自)チェック
        //２－１）this.時間(自) == nullの場合は、
        //「時間(自)がnullです。」の例外をスローする。
        if (this.startTime == null)
        {
            log.debug("時間(自)がnullです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03118,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "時間(自)がnullです。");
        }

        //２－２）this.時間(自)の入力形式が 'hh24mi' ではない場合は、
        //「表示期間（自）日付フォーマットエラー。」
        //(BUSINESS_ERROR_01065)の例外をスローする。
        if (!WEB3StringTypeUtility.isDateStr(this.startTime, WEB3GentradeTimeDef.TIME_FORMAT_HM))
        {
            log.debug("表示期間（自）日付フォーマットエラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01065,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "表示期間（自）日付フォーマットエラー。");
        }

        //３）時間(至)チェック
        //３－１）this.時間(至) == nullの場合は、
        //「時間(至)がnullです。」の例外をスローする。
        if (this.endTime == null)
        {
            log.debug("時間(至)がnullです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03119,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "時間(至)がnullです。");
        }

        //３－２）this.時間(至)の入力形式が 'hh24mi' ではない場合は、
        //「表示期間（至）日付フォーマットエラー。」
        //(BUSINESS_ERROR_01066)の例外をスローする。
        if (!WEB3StringTypeUtility.isDateStr(this.endTime, WEB3GentradeTimeDef.TIME_FORMAT_HM))
        {
            log.debug("表示期間（至）日付フォーマットエラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01066,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "表示期間（至）日付フォーマットエラー。");
        }

        //４）入力時間整合性チェック
        //４－１）this.時間(自) > this.時間(至) の場合は、
        //「表示期間（自）は表示期間（至）より大きいです。」
        //(BUSINESS_ERROR_01051)の例外をスローする。
        Date l_datStartTime = WEB3DateUtility.getDate(this.startTime, WEB3GentradeTimeDef.TIME_FORMAT_HM);
        Date l_datEndTime = WEB3DateUtility.getDate(this.endTime, WEB3GentradeTimeDef.TIME_FORMAT_HM);
        if (WEB3DateUtility.compareToMinute(l_datStartTime, l_datEndTime) > 0)
        {
            log.debug("表示期間（自）は表示期間（至）より大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01051,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "表示期間（自）は表示期間（至）より大きいです。");
        }

        //４－２） [this.時間(至) - this.時間(自)] > １時間 の場合は、
        //「指定範囲は１時間以内で入力してください。」の例外をスローする。
        if (WEB3DateUtility.compareToMinute(WEB3DateUtility.addHour(l_datEndTime, -1), l_datStartTime) > 0)
        {
            log.debug("指定範囲は１時間以内で入力してください。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03128,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "指定範囲は１時間以内で入力してください。");
        }

        //５）ランクチェック
        //５－１）this.ランク == nullの場合は、
        //「ランクが未入力です。」の例外をスローする。
        if (this.rank == null)
        {
            log.debug("ランクが未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03129,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ランクが未入力です。");
        }

        //５－２）this.ランク に半角数字以外の文字が入っていた場合は、
        //「ランクは半角数字で入力してください。」の例外をスローする。
        if (!WEB3StringTypeUtility.isDigit(this.rank))
        {
            log.debug("ランクは半角数字で入力してください。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03130,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ランクは半角数字で入力してください。");
        }

        //５－３）this.ランク > 30 の場合は、
        //「ランクは上位30位までの表示しかできません。」の例外をスローする。
        int l_intRank = Integer.parseInt(this.rank);
        if (l_intRank > 30)
        {
            log.debug("ランクは上位30位までの表示しかできません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03131,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ランクは上位30位までの表示しかできません。");
        }

        //６）ページ内表示行数チェック
        //６－１）this.ページ内表示行数 == nullの場合、
        //「ページ内表示行数の入力が不正です。」の例外をスローする。
        if (this.pageSize == null)
        {
            log.debug("ページ内表示行数の入力が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数の入力が不正です。");
        }

        //６－２）this.ページ内表示行数が半角数字以外の値であった場合、
        //「ページ内表示行数が数字以外の値です。」の例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.debug("ページ内表示行数が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。");
        }

        //６－３）this.ページ内表示行数 <= 0であった場合、
        //「ページ内表示行数の値が0以下です。」の例外をスローする。
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.debug("ページ内表示行数の値が0以下です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数の値が0以下です。");
        }

        //７）要求ページ番号チェック
        //７－１）this.要求ページ番号 == nullの場合、
        //「要求ページ番号が未指定です。」の例外をスローする。
        if (this.pageIndex == null)
        {
            log.debug("要求ページ番号が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が未指定です。");
        }

        //７－２）this.要求ページ番号が半角数字以外の値であった場合、
        //「要求ページ番号が数字以外の値です。」の例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.debug("要求ページ番号が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が数字以外の値です。");
        }

        //７－３）this.要求ページ番号 <= 0であった場合、
        //「要求ページ番号の値が0以下です。」の例外をスローする。
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.debug("要求ページ番号の値が0以下です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号の値が0以下です。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * レスポンスデータを作成する。<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminTraderAdminLoginCountListResponse(this);
    }
}
@
