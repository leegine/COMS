head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.39.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdDownloadCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV(WEB3AdminSrvRegiOtherOrgIdDownloadCsv.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/19 周墨洋 (中訊) 新規作成・モデルNo.336
Revesion History : 2008/04/03 竹村　@信茂 (SCS) QTP連携対応（createキーヘッダ()追加）
*/

package webbroker3.srvregi;

import java.sql.Timestamp;
import java.util.Date;

import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;


/**
 * (外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV)<BR>
 * 外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV<BR>
 * <BR>
 * @@author 周墨洋
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdDownloadCsv
    extends WEB3GentradeCsvDataModel
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminSrvRegiOtherOrgIdDownloadCsv.class);

    /**
     * (通番ラベル)<BR>
     * 定数定義プロパティ　@"通番"<BR>
     */
    public static String SEQUENCE_NUMBER_LABEL = "通番";

    /**
     * (IDラベル)<BR>
     * 定数定義プロパティ　@"ID"<BR>
     */
    public static String ID_LABEL = "ID";

    /**
     * (ステータスラベル)<BR>
     * 定数定義プロパティ　@"ステータス"<BR>
     */
    public static String STATUS_LABEL = "ステータス";

    /**
     * (証券会社コードラベル)<BR>
     * 定数定義プロパティ　@”証券会社コード”<BR>
     */
    public static String INSTITUTION_CODE_LABEL = "証券会社コード";

    /**
     * (部店コードラベル)<BR>
     * 定数定義プロパティ　@”部店コード”<BR>
     */
    public static String BRANCH_CODE_LABEL = "部店コード";

    /**
     * (口座コードラベル)<BR>
     * 定数定義プロパティ　@”口座コード”<BR>
     */
    public static String ACCOUNT_CODE_LABEL = "口座コード";

    /**
     * (適用期間Fromラベル)<BR>
     * 定数定義プロパティ　@"適用期間From"<BR>
     */
    public static String APPLI_START_DATE_LABEL = "適用期間From";

    /**
     * (適用期間Toラベル)<BR>
     * 定数定義プロパティ　@"適用期間To"<BR>
     */
    public static String APPLI_END_DATE_LABEL = "適用期間To";

    /**
     * (外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV)<BR>
     * コンストラクタ<BR>
     * <BR>
     * インスタンスを生成し、ヘッダ情報をセットする。<BR>
     * 　@－createキーヘッダ()をコールし、キーヘッダ情報を作成する。<BR>
     * 　@－createカラムヘッダ()をコールし、カラムヘッダ情報を作成する。<BR>
     * @@roseuid 47BA96C402DD
     */
    public WEB3AdminSrvRegiOtherOrgIdDownloadCsv()
    {
        // インスタンスを生成し、ヘッダ情報をセットする。
        // 　@－createキーヘッダ()をコールし、キーヘッダ情報を作成する。
        // 　@－createカラムヘッダ()をコールし、カラムヘッダ情報を作成する。
        this.createKeyHeader();
        this.createColumnHeader();
    }

    /**
     * (createキーヘッダ())<BR>
     * キーヘッダをセットする。 <BR>
     * <BR>
     * 　@以下の通り文字列の配列を生成し、setキーヘッダ()にてインスタンスにセットする。 <BR>
     * <BR>
     * [キーヘッダ配列] <BR>
     * <BR>
     * －　@index = 0 <BR>
     * 　@現在日時を"yyyy/MM/dd HH:mm:ss"の形式にformatした文字列。 <BR>
     * <BR>
     * (*1) 現在日時 <BR>
     * TradingSystem.getSystemTimestamp() <BR>
     * @@roseuid 4104DBE602EE
     */
    private void createKeyHeader() 
    {
        final String STR_METHOD_NAME = " createKeyHeader()";
        log.entering(STR_METHOD_NAME );
        
        Timestamp l_tsSysDate = GtlUtils.getTradingSystem().getSystemTimestamp();
        String l_strCurrentTime = WEB3DateUtility.formatDate(l_tsSysDate, "yyyy/MM/dd HH:mm:ss");
        String[] l_strKeyHeaders = {l_strCurrentTime};
        
        this.setKeyHeader(l_strKeyHeaders);
        
        log.exiting(STR_METHOD_NAME); 
    }

    /**
     * (createカラムヘッダ)<BR>
     * カラムヘッダをセットする。<BR>
     * <BR>
     * 　@以下の通りCSVカラムモデルの配列を生成し、<BR>
     * 　@setカラムヘッダ()にてインスタンスにセットする。<BR>
     * <BR>
     * [カラムヘッダ配列]<BR>
     * <BR>
     * －　@index = 0<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.通番ラベル<BR>
     * 　@カラム番号： 0<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 1<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.IDラベル<BR>
     * 　@カラム番号： 1<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 2<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.ステータスラベル<BR>
     * 　@カラム番号： 2<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 3<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.証券会社コードラベル<BR>
     * 　@カラム番号： 3<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 4<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.部店コードラベル<BR>
     * 　@カラム番号： 4<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 5<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.口座コードラベル<BR>
     * 　@カラム番号： 5<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 6<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.適用期間Fromラベル<BR>
     * 　@カラム番号： 6<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 7<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.適用期間Toラベル<BR>
     * 　@カラム番号： 7<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * <BR>
     * @@roseuid 47BA969401F6
     */
    private void createColumnHeader()
    {
        final String STR_METHOD_NAME = "createColumnHeader()";
        log.entering(STR_METHOD_NAME);

        // 　@以下の通りCSVカラムモデルの配列を生成し、
        // 　@setカラムヘッダ()にてインスタンスにセットする。
        final int COLUMN_MAX = 8;
        WEB3GentradeCsvColumnModel[] l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel[COLUMN_MAX];

        int l_intIndex = 0;

        // [カラムヘッダ配列]
        // －　@index = 0
        // 　@[CSVカラムモデル コンストラクタの引数]
        // 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.通番ラベル
        // 　@カラム番号： 0
        // 　@項目型：　@CSVカラムモデル.項目型_文字列
        // 　@日付フォーマット：　@null
        l_gentradeCsvColumnModel[l_intIndex] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiOtherOrgIdDownloadCsv.SEQUENCE_NUMBER_LABEL,
                l_intIndex,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        // －　@index = 1
        // 　@[CSVカラムモデル コンストラクタの引数]
        // 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.IDラベル
        // 　@カラム番号： 1
        // 　@項目型：　@CSVカラムモデル.項目型_文字列
        // 　@日付フォーマット：　@null
        l_intIndex = ++l_intIndex;
        l_gentradeCsvColumnModel[l_intIndex] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiOtherOrgIdDownloadCsv.ID_LABEL,
                l_intIndex,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        // －　@index = 2
        // 　@[CSVカラムモデル コンストラクタの引数]
        // 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.ステータスラベル
        // 　@カラム番号： 2
        // 　@項目型：　@CSVカラムモデル.項目型_文字列
        // 　@日付フォーマット：　@null
        l_intIndex = ++l_intIndex;
        l_gentradeCsvColumnModel[l_intIndex] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiOtherOrgIdDownloadCsv.STATUS_LABEL,
                l_intIndex,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        // －　@index = 3
        // 　@[CSVカラムモデル コンストラクタの引数]
        // 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.証券会社コードラベル
        // 　@カラム番号： 3
        // 　@項目型：　@CSVカラムモデル.項目型_文字列
        // 　@日付フォーマット：　@null
        l_intIndex = ++l_intIndex;
        l_gentradeCsvColumnModel[l_intIndex] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiOtherOrgIdDownloadCsv.INSTITUTION_CODE_LABEL,
                l_intIndex,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        // －　@index = 4
        // 　@[CSVカラムモデル コンストラクタの引数]
        // 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.部店コードラベル
        // 　@カラム番号： 4
        // 　@項目型：　@CSVカラムモデル.項目型_文字列
        // 　@日付フォーマット：　@null
        l_intIndex = ++l_intIndex;
        l_gentradeCsvColumnModel[l_intIndex] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiOtherOrgIdDownloadCsv.BRANCH_CODE_LABEL,
                l_intIndex,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        // －　@index = 5
        // 　@[CSVカラムモデル コンストラクタの引数]
        // 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.口座コードラベル
        // 　@カラム番号： 5
        // 　@項目型：　@CSVカラムモデル.項目型_文字列
        // 　@日付フォーマット：　@null
        l_intIndex = ++l_intIndex;
        l_gentradeCsvColumnModel[l_intIndex] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiOtherOrgIdDownloadCsv.ACCOUNT_CODE_LABEL,
                l_intIndex,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        // －　@index = 6
        // 　@[CSVカラムモデル コンストラクタの引数]
        // 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.適用期間Fromラベル
        // 　@カラム番号： 6
        // 　@項目型：　@CSVカラムモデル.項目型_文字列
        // 　@日付フォーマット：　@null
        l_intIndex = ++l_intIndex;
        l_gentradeCsvColumnModel[l_intIndex] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiOtherOrgIdDownloadCsv.APPLI_START_DATE_LABEL,
                l_intIndex,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        // －　@index = 7
        // 　@[CSVカラムモデル コンストラクタの引数]
        // 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.適用期間Toラベル
        // 　@カラム番号： 7
        // 　@項目型：　@CSVカラムモデル.項目型_文字列
        // 　@日付フォーマット：　@null
        l_intIndex = ++l_intIndex;
        l_gentradeCsvColumnModel[l_intIndex] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiOtherOrgIdDownloadCsv.APPLI_END_DATE_LABEL,
                l_intIndex,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        this.setColumnHeader(l_gentradeCsvColumnModel);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set通番)<BR>
     * 通番をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.通番ラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@外部連携情報管理テーブルの通番<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@param l_lngSequenceNumber - (通番)<BR>
     * 通番を指定する。<BR>
     * @@roseuid 47BA9AA802D8
     */
    public void setSequenceNumber(int l_intLineNo, String l_strSequenceNumber)
    {
        final String STR_METHOD_NAME = "setSequenceNumber(int, long)";
        log.entering(STR_METHOD_NAME);

        // １）　@カラムモデル取得
        // 　@this.getカラムモデル()にてCSVカラムモデルを取得する。
        // 　@[getカラムモデル()に指定する引数]
        // 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.通番ラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(
                WEB3AdminSrvRegiOtherOrgIdDownloadCsv.SEQUENCE_NUMBER_LABEL);

        // ２）　@値セット
        // 　@this.set項目値()にて項目値をセットする。
        // 　@[set項目値()に指定する引数]
        // 　@行番号：　@引数の行番号
        // 　@カラム：　@１）で取得したカラムモデル
        // 　@値：　@外部連携情報管理テーブルの通番
        this.setValue(l_intLineNo, l_gentradeCsvColumnModel, l_strSequenceNumber);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (setID)<BR>
     * IDをセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.IDラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@外部連携情報管理テーブルのID<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@param l_strId - (ID)<BR>
     * IDを指定する。<BR>
     * @@roseuid 47BA9AB3012B
     */
    public void setId(int l_intLineNo, String l_strId)
    {
        final String STR_METHOD_NAME = "setId(int, String)";
        log.entering(STR_METHOD_NAME);

        // １）　@カラムモデル取得
        // 　@this.getカラムモデル()にてCSVカラムモデルを取得する。
        // 　@[getカラムモデル()に指定する引数]
        // 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.IDラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(
                WEB3AdminSrvRegiOtherOrgIdDownloadCsv.ID_LABEL);

        // ２）　@値セット
        // 　@this.set項目値()にて項目値をセットする。
        // 　@[set項目値()に指定する引数]
        // 　@行番号：　@引数の行番号
        // 　@カラム：　@１）で取得したカラムモデル
        // 　@値：　@外部連携情報管理テーブルのID
        this.setValue(l_intLineNo, l_gentradeCsvColumnModel, l_strId);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (setステータス)<BR>
     * ステータスをセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.ステータスラベル<BR>
     * <BR>
     * ２）　@値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@外部連携情報管理テーブルのステータス<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@param l_strStatus - (ステータス)<BR>
     * ステータスを指定する。<BR>
     * @@roseuid 47BA9ABC0356
     */
    public void setStatus(int l_intLineNo, String l_strStatus)
    {
        final String STR_METHOD_NAME = "setStatus(int, String)";
        log.entering(STR_METHOD_NAME);

        // １）　@カラムモデル取得
        // 　@this.getカラムモデル()にてCSVカラムモデルを取得する。
        // 　@[getカラムモデル()に指定する引数]
        // 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.ステータスラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(
                WEB3AdminSrvRegiOtherOrgIdDownloadCsv.STATUS_LABEL);

        // ２）　@値セット
        // 　@this.set項目値()にて項目値をセットする。
        // 　@[set項目値()に指定する引数]
        // 　@行番号：　@引数の行番号
        // 　@カラム：　@１）で取得したカラムモデル
        // 　@値：　@外部連携情報管理テーブルのステータス
        this.setValue(l_intLineNo, l_gentradeCsvColumnModel, l_strStatus);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set証券会社コード)<BR>
     * 証券会社コードをセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.証券会社コードラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@外部連携情報管理テーブルの証券会社コード<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コードを指定する。<BR>
     * @@roseuid 47BA96940215
     */
    public void setInstitutionCode(int l_intLineNo, String l_strInstitutionCode)
    {
        final String STR_METHOD_NAME = "setInstitutionCode(int, String)";
        log.entering(STR_METHOD_NAME);

        // １）　@カラムモデル取得
        // 　@this.getカラムモデル()にてCSVカラムモデルを取得する。
        // 　@[getカラムモデル()に指定する引数]
        // 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.証券会社コードラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(
                WEB3AdminSrvRegiOtherOrgIdDownloadCsv.INSTITUTION_CODE_LABEL);

        // ２）　@値セット
        // 　@this.set項目値()にて項目値をセットする。
        // 　@[set項目値()に指定する引数]
        // 　@行番号：　@引数の行番号
        // 　@カラム：　@１）で取得したカラムモデル
        // 　@値：　@外部連携情報管理テーブルの証券会社コード
        this.setValue(l_intLineNo, l_gentradeCsvColumnModel, l_strInstitutionCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set部店コード)<BR>
     * 部店コードをセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.部店コードラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@外部連携情報管理テーブルの部店コード<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コードを指定する。<BR>
     * @@roseuid 47BA96940224
     */
    public void setBranchCode(int l_intLineNo, String l_strBranchCode)
    {
        final String STR_METHOD_NAME = "setBranchCode(int, String)";
        log.entering(STR_METHOD_NAME);

        // １）　@カラムモデル取得
        // 　@this.getカラムモデル()にてCSVカラムモデルを取得する。
        // 　@[getカラムモデル()に指定する引数]
        // 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.部店コードラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(
                WEB3AdminSrvRegiOtherOrgIdDownloadCsv.BRANCH_CODE_LABEL);

        // ２）　@値セット
        // 　@this.set項目値()にて項目値をセットする。
        // 　@[set項目値()に指定する引数]
        // 　@行番号：　@引数の行番号
        // 　@カラム：　@１）で取得したカラムモデル
        // 　@値：　@外部連携情報管理テーブルの部店コード
        this.setValue(l_intLineNo, l_gentradeCsvColumnModel, l_strBranchCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set口座コード)<BR>
     * 口座コードをセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.口座コードラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@外部連携情報管理テーブルの口座コード<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コードを指定する。<BR>
     * @@roseuid 47BA96940253
     */
    public void setAccountCode(int l_intLineNo, String l_strAccountCode)
    {
        final String STR_METHOD_NAME = "setAccountCode(int, String)";
        log.entering(STR_METHOD_NAME);

        // １）　@カラムモデル取得
        // 　@this.getカラムモデル()にてCSVカラムモデルを取得する。
        // 　@[getカラムモデル()に指定する引数]
        // 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.口座コードラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(
                WEB3AdminSrvRegiOtherOrgIdDownloadCsv.ACCOUNT_CODE_LABEL);

        // ２）　@値セット
        // 　@this.set項目値()にて項目値をセットする。
        // 　@[set項目値()に指定する引数]
        // 　@行番号：　@引数の行番号
        // 　@カラム：　@１）で取得したカラムモデル
        // 　@値：　@外部連携情報管理テーブルの口座コード
        this.setValue(l_intLineNo, l_gentradeCsvColumnModel, l_strAccountCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set適用期間From)<BR>
     * 適用期間Fromをセットする。<BR>
     * <BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.適用期間Fromラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@外部連携情報管理テーブルの適用期間From<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@param l_datAppliStartDate - (適用期間From)<BR>
     * 適用期間Fromを指定する。<BR>
     * @@roseuid 47BA96940272
     */
    public void setAppliStartDate(int l_intLineNo, Date l_datAppliStartDate)
    {
        final String STR_METHOD_NAME = "setAppliStartDate(int, Date)";
        log.entering(STR_METHOD_NAME);

        // １）　@カラムモデル取得
        // 　@this.getカラムモデル()にてCSVカラムモデルを取得する。
        // 　@[getカラムモデル()に指定する引数]
        // 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.適用期間Fromラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(
                WEB3AdminSrvRegiOtherOrgIdDownloadCsv.APPLI_START_DATE_LABEL);

        // ２）　@値セット
        // 　@this.set項目値()にて項目値をセットする。
        // 　@[set項目値()に指定する引数]
        // 　@行番号：　@引数の行番号
        // 　@カラム：　@１）で取得したカラムモデル
        // 　@値：　@外部連携情報管理テーブルの適用期間From
        this.setValue(
            l_intLineNo,
            l_gentradeCsvColumnModel,
            WEB3DateUtility.formatDate(
                l_datAppliStartDate,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD));

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set適用期間To)<BR>
     * 適用期間Toをセットする。<BR>
     * <BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.適用期間Toラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@外部連携情報管理テーブルの適用期間To<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@param l_datAppliEndDate - (適用期間To)<BR>
     * 適用期間Toを指定する。<BR>
     * @@roseuid 47BA96940282
     */
    public void setAppliEndDate(int l_intLineNo, Date l_datAppliEndDate)
    {
        final String STR_METHOD_NAME = "setAppliEndDate(int, Date)";
        log.entering(STR_METHOD_NAME);

        // １）　@カラムモデル取得
        // 　@this.getカラムモデル()にてCSVカラムモデルを取得する。
        // 　@[getカラムモデル()に指定する引数]
        // 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.適用期間Toラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(
                WEB3AdminSrvRegiOtherOrgIdDownloadCsv.APPLI_END_DATE_LABEL);

        // ２）　@値セット
        // 　@this.set項目値()にて項目値をセットする。
        // 　@[set項目値()に指定する引数]
        // 　@行番号：　@引数の行番号
        // 　@カラム：　@１）で取得したカラムモデル
        // 　@値：　@外部連携情報管理テーブルの適用期間To
        this.setValue(
            l_intLineNo,
            l_gentradeCsvColumnModel,
            WEB3DateUtility.formatDate(
                l_datAppliEndDate,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD));

        log.exiting(STR_METHOD_NAME);
    }

}
@
