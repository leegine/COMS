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
filename	WEB3GentradeEra.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 年号(WEB3GentradeEra.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/02/13 趙林鵬(中訊) 新規作成 モデルNo.337
*/

package webbroker3.gentrade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3EraBornDef;
import webbroker3.common.define.WEB3SystemPreferencesNameDef;
import webbroker3.gentrade.data.EraRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (年号)<BR>
 * 年号を表すクラス。<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3GentradeEra
{
    /**
     * ログ出力オブジェクト。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeEra.class);

    /**
     * 年号を表すクラス
     */
    public WEB3GentradeEra()
    {

    }

    /**
     * 引数の年号、和暦文字列を西暦の日付型に変換する。<BR>
     * <BR>
     * １） 年号マスターテーブルを以下の条件で検索する。<BR>
     * <BR>
     * 　@　@【検索条件】<BR>
     * 　@　@　@・年号区分 ＝ 引数.年号区分 and<BR>
     * 　@　@　@・開始年月日(和暦)(*1) ≦ 引数.和暦文字列 and<BR>
     * 　@　@　@・引数.和暦文字列 ≦ 終了年月日(和暦)(*2)<BR>
     * 　@　@　@　@(*1) [開始年(和暦)]と[開始月日]を結合した文字列<BR>
     * 　@　@　@　@(*2) [終了年(和暦)]と[終了月日]を結合した文字列<BR>
     * <BR>
     * 　@　@※レコードが取得できなかった場合、nullを返却する。<BR>
     * <BR>
     * ２） 西暦年数を算出する。<BR>
     * 　@　@ 引数.和暦文字列の年数(YY) ＋ （取得したレコード.開始年(西暦) － 取得したレコード.開始年(和暦)）<BR>
     * <BR>
     * ３） 算出した西暦年数と引数.和暦文字列の日付部分(MMDD)を結合し、Date型で返却する。<BR>
     * @@param l_strJapaneseEra - (年号区分)<BR>
     * 年号区分<BR>
     * @@param l_strDateString - (和暦文字列)<BR>
     * 和暦（YYMMDD形式）文字列<BR>
     * @@return Date
     */
    public static Date toDate(String l_strJapaneseEra, String l_strDateString)
    {
        final String STR_METHOD_NAME = "toDate(String, String)";
        log.entering(STR_METHOD_NAME);

        if (l_strJapaneseEra == null || l_strDateString == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        if (!WEB3StringTypeUtility.isDigit(l_strDateString) || l_strDateString.length() != 6)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //１） 年号マスターテーブルを以下の条件で検索する。
        //【検索条件】
        //・年号区分 ＝ 引数.年号区分 and
        //開始年月日(和暦)(*1) ≦ 引数.和暦文字列 and
        //引数.和暦文字列 ≦ 終了年月日(和暦)(*2)
        String l_strWhere = "japanese_era_div = ?";
        l_strWhere = l_strWhere + " and concat(start_year_jap, start_date) <= ?";
        l_strWhere = l_strWhere + " and concat(end_year_jap, end_date) >= ?";

        Object[] l_objValues = {new Integer(l_strJapaneseEra), l_strDateString, l_strDateString};

        List l_lisEraRecords = new ArrayList();
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisEraRecords = l_queryProcessor.doFindAllQuery(
                EraRow.TYPE,
                l_strWhere,
                l_objValues);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3StringTypeUtility.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3StringTypeUtility.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //レコードが取得できなかった場合、nullを返却する。
        if (l_lisEraRecords.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        EraRow l_eraRow = (EraRow)l_lisEraRecords.get(0);
        //西暦年数を算出する。
        //引数.和暦文字列の年数(YY) ＋ （取得したレコード.開始年(西暦) － 取得したレコード.開始年(和暦)）
        int l_intDateString = Integer.parseInt(l_strDateString.substring(0, 2));
        int l_intStartYear = Integer.parseInt(l_eraRow.getStartYear());
        int l_intStartYearJap = Integer.parseInt(l_eraRow.getStartYearJap());

        String l_strYear = String.valueOf(l_intDateString + (l_intStartYear - l_intStartYearJap));

        //３） 算出した西暦年数と引数.和暦文字列の日付部分(MMDD)を結合し、Date型で返却する。
        String l_strToDate = l_strYear + l_strDateString.substring(2, 6);
        Date l_datToDate =
            WEB3DateUtility.getDate(l_strToDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);

        log.exiting(STR_METHOD_NAME);
        return l_datToDate;
    }

    /**
     * 引数の西暦日付を年号、和暦文字列の配列に変換する。<BR>
     * １） 年号マスターテーブルを以下の条件で検索する。<BR>
     * <BR>
     * 　@　@【検索条件】<BR>
     * 　@　@　@・開始年月日(西暦)(*1) ≦ 引数.日付 and<BR>
     * 　@　@　@・引数.日付 ≦ 終了年月日(西暦)(*2)<BR>
     * 　@　@　@　@(*1) [開始年(西暦)]と[開始月日]を結合した文字列を日付型に変換<BR>
     * 　@　@　@　@(*2) [終了年(西暦)]と[終了月日]を結合した文字列を日付型に変換<BR>
     * <BR>
     * 　@　@ ※レコードが取得できなかった場合、nullを返却する。<BR>
     * <BR>
     * ２） 和暦年数を算出する。<BR>
     * 　@　@ 引数.西暦日付の年数(YYYY) － （取得したレコード.開始年(西暦) － 取得したレコード.開始年(和暦)）<BR>
     * 　@　@ ※計算結果が一桁の場合は１桁目に"0"を追加（例：1995-1990=5 → 05）<BR>
     * <BR>
     * ３） ArrayListを生成し、以下を追加する。<BR>
     * 　@　@ ・取得したレコードの年号区分<BR>
     * 　@　@ ・算出した和暦年数(YY)と引数.西暦日付の月日(MMDD)を結合した文字列<BR>
     * <BR>
     * ４） 作成したArrayList.toArray()の戻り値を返却する。<BR>
     * <BR>
     * 例えば<BR>
     * 入力： 1990.01.07<BR>
     * 出力： {1, 010107}<BR>
     * @@param l_datInput - (西暦日付)<BR>
     * 西暦日付。<BR>
     * @@return String[]
     */
    public static String[] toJapDate(Date l_datInput)
    {
        final String STR_METHOD_NAME = "toJapDate(Date)";
        log.entering(STR_METHOD_NAME);

        if (l_datInput == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //１） 年号マスターテーブルを以下の条件で検索する。
        //【検索条件】
        //開始年月日(西暦)(*1) ≦ 引数.日付 and引数.日付 ≦ 終了年月日(西暦)(*2)
        String l_strWhere = "concat(start_year, start_date) <= ? and concat(end_year, end_date) >= ?";

        String l_strDatInput =
            WEB3DateUtility.formatDate(l_datInput, WEB3GentradeTimeDef.DATE_FORMAT_YMD);

        Object[] l_objValues = {l_strDatInput, l_strDatInput};

        List l_lisEraRecords = new ArrayList();
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisEraRecords = l_queryProcessor.doFindAllQuery(
                EraRow.TYPE,
                l_strWhere,
                l_objValues);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3StringTypeUtility.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3StringTypeUtility.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //レコードが取得できなかった場合、nullを返却する。
        if (l_lisEraRecords.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        EraRow l_eraRow = (EraRow)l_lisEraRecords.get(0);
        //２） 和暦年数を算出する。
        //引数.西暦日付の年数(YYYY) － （取得したレコード.開始年(西暦) － 取得したレコード.開始年(和暦)）
        int l_intInput = Integer.parseInt(l_strDatInput.substring(0, 4));
        int l_intStartYear = Integer.parseInt(l_eraRow.getStartYear());
        int l_intStartYearJap = Integer.parseInt(l_eraRow.getStartYearJap());

        String l_strYearJap = String.valueOf(l_intInput - (l_intStartYear - l_intStartYearJap));

        //計算結果が一桁の場合は１桁目に"0"を追加（例：1995-1990=5 → 05）
        if (l_strYearJap.length() == 1)
        {
            l_strYearJap = "0" + l_strYearJap;
        }

        //３） ArrayListを生成し、以下を追加する。
        //取得したレコードの年号区分
        //算出した和暦年数(YY)と引数.西暦日付の月日(MMDD)を結合した文字列
        List l_lisYears = new ArrayList();
        l_lisYears.add(l_eraRow.getJapaneseEraDiv() + "");
        l_lisYears.add(l_strYearJap + l_strDatInput.substring(4, 8));

        //４） 作成したArrayList.toArray()の戻り値を返却する。
        String[] l_strJapDates = new String[l_lisYears.size()];
        l_lisYears.toArray(l_strJapDates);

        log.exiting(STR_METHOD_NAME);
        return l_strJapDates;
    }

    /**
     * 引数の和暦文字列に対応する年号区分を返却する。<BR>
     * <BR>
     * １） 年号マスターテーブルを取得する。<BR>
     * <BR>
     * 　@　@【検索条件】<BR>
     * 　@　@　@・年号区分 != 9：不明<BR>
     * <BR>
     * 　@　@【ソート条件】<BR>
     * 　@　@　@年号区分(降順)<BR>
     * <BR>
     * ２） システムプリファ@レンステーブルから以下の条件でレコードを取得する。<BR>
     * <BR>
     * 　@　@【検索条件】<BR>
     * 　@　@　@名称（環境変数名） = "era_condition_year"<BR>
     * <BR>
     * ３） 該当する年号区分を返却する。<BR>
     * <BR>
     * 　@３－１） 「引数.和暦文字列の年数(YY) ＞ 取得したシステムプリファ@レンス.値」の場合、<BR>
     * 　@　@　@　@　@ 取得した年号マスターテーブルの２件目のレコード.年号区分を返却。<BR>
     * <BR>
     * 　@３－２） システムプリファ@レンスに該当レコードが存在しなかった場合、または、上記以外の場合、<BR>
     * 　@　@　@　@　@ 取得した年号マスターテーブルの１件目のレコード.年号区分を返却。<BR>
     * <BR>
     * @@param l_strDateString - (和暦（YYMMDD形式）文字列)<BR>
     * 和暦（YYMMDD形式）文字列<BR>
     * @@return String
     */
    public static String getJapEraDiv(String l_strDateString)
    {
        final String STR_METHOD_NAME = "getJapEraDiv(String)";
        log.entering(STR_METHOD_NAME);

        if (l_strDateString == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        if (!WEB3StringTypeUtility.isDigit(l_strDateString) || l_strDateString.length() != 6)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //１） 年号マスターテーブルを取得する。
        //【検索条件】
        //・年号区分 != 9：不明
        String l_strWhere = " japanese_era_div <> ? ";
        Object[] l_objValues = {new Integer(WEB3EraBornDef.UNKNOWN)};

        //【ソート条件】
        //年号区分(降順)
        String l_strSortCond = " japanese_era_div desc ";

        List l_lisEraRecords = new ArrayList();
        SystemPreferencesRow l_systemPreferencesRow = null;

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisEraRecords = l_queryProcessor.doFindAllQuery(
                EraRow.TYPE,
                l_strWhere,
                l_strSortCond,
                null,
                l_objValues);

            if (l_lisEraRecords.isEmpty())
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            //システムプリファ@レンステーブルから以下の条件でレコードを取得する。
            //【検索条件】
            //名称（環境変数名） = "era_condition_year"
            l_systemPreferencesRow =
                SystemPreferencesDao.findRowByName(WEB3SystemPreferencesNameDef.ERA_CONDITION_YEAR);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3StringTypeUtility.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3StringTypeUtility.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //「引数.和暦文字列の年数(YY) ＞ 取得したシステムプリファ@レンス.値」の場合
        if (l_systemPreferencesRow != null
            && Integer.parseInt(l_strDateString.substring(0, 2)) >
                Integer.parseInt(l_systemPreferencesRow.getValue()))
        {
            //取得した年号マスターテーブルの２件目のレコード.年号区分を返却。
            if (l_lisEraRecords.size() > 1)
            {
                EraRow l_eraRow = (EraRow)l_lisEraRecords.get(1);

                log.exiting(STR_METHOD_NAME);
                return l_eraRow.getJapaneseEraDiv() + "";
            }
        }
        else
        {
            //システムプリファ@レンスに該当レコードが存在しなかった場合、または、上記以外の場合、
            //取得した年号マスターテーブルの１件目のレコード.年号区分を返却。
            if (l_lisEraRecords.size() > 0)
            {
                EraRow l_eraRow = (EraRow)l_lisEraRecords.get(0);

                log.exiting(STR_METHOD_NAME);
                return l_eraRow.getJapaneseEraDiv() + "";
            }
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }
}@
