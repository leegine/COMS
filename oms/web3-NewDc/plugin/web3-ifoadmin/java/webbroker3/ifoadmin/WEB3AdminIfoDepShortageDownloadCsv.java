head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminIfoDepShortageDownloadCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証拠金不足状況ダウンロードCSV(WEB3AdminIfoDepShortageDownloadCsv.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/02/27　@劉剣(中訊) 新規作成 モデルNo.006
Revision History : 2009/04/10　@張騰宇(中訊) モデルNo.017
*/
package webbroker3.ifoadmin;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.ifoadmin.define.WEB3AdminIfoCurNonPayAmtDef;
import webbroker3.ifoadmin.define.WEB3AdminIfoExistFlagDef;
import webbroker3.ifoadmin.define.WEB3AdminIfoKeyHeaderDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (証拠金不足状況ダウンロードCSV )<BR>
 * 証拠金不足状況ダウンロードCSV クラス<BR>
 * <BR>
 * @@author 劉剣
 * @@version 1.0
 */
public class WEB3AdminIfoDepShortageDownloadCsv extends WEB3GentradeCsvDataModel
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoDepShortageDownloadCsv.class);

    /**
     * (部店コードラベル)<BR>
     * 部店コードラベル<BR>
     */
    public String branchCodeLabel = "部店";

    /**
     * (顧客コードラベル)<BR>
     * 顧客コードラベル<BR>
     */
    public String accountCodeLabel = "顧客";

    /**
     * (顧客名ラベル)<BR>
     * 顧客名ラベル<BR>
     */
    public String accountNameLabel = "顧客名";

    /**
     * (請求額ラベル)<BR>
     * 請求額ラベル<BR>
     */
    public String claimAmountLabel = "請求額";

    /**
     * (現在未入金額ラベル)<BR>
     * 現在未入金額ラベル<BR>
     */
    public String curNonPayAmtLabel = "現在の未解消金額";

    /**
     * (現在証拠金所要額ラベル)<BR>
     * 現在証拠金所要額ラベル<BR>
     */
    public String curIfoDepositNecessaryAmtLabel = "現在の証拠金所要額";

    /**
     * (建玉有無フラグラベル)<BR>
     * 建玉有無フラグラベル<BR>
     */
    public String contractExistFlagLabel = "建玉有無（OP買建除く）";

    /**
     * (注文有無フラグラベル)<BR>
     * 注文有無フラグラベル<BR>
     */
    public String orderExistFlagLabel = "注文有無（OP買建除く）";

    /**
     * (証拠金不足状況ダウンロードCSV)<BR>
     * コンストラクタ<BR>
     * <BR>
     * インスタンスを生成し、ヘッダ情報をセットする。 <BR>
     * <BR>
     * １）super()をコールし、インスタンスを生成する。 <BR>
     * <BR>
     * ２）createキーヘッダ(引数.発生日付)をコールし、キーヘッダ情報を作成する。 <BR>
     * <BR>
     * ３）createカラムヘッダ()をコールし、カラムヘッダ情報を作成する。 <BR>
     * @@param l_datOccurDate - (発生日付)<BR>
     * 発生日付<BR>
     * @@roseuid 499B72830062
     */
    public WEB3AdminIfoDepShortageDownloadCsv(Date l_datOccurDate)
    {
        super();
        this.createKeyHeader(l_datOccurDate);
        this.createColumnHeader();
    }

    /**
     * (createキーヘッダ)<BR>
     * キーヘッダをセットする。<BR>
     * <BR>
     * 以下の通り文字列の配列を生成し、setキーヘッダ()にてインスタンスにセットする。<BR>
     * <BR>
     * [キーヘッダ配列] <BR>
     * <BR>
     * －　@index = 0 <BR>
     * 　@「CSV出力日」の文字列。 <BR>
     * <BR>
     * －　@index = 1 <BR>
     * 　@現在日時を"yyyy/MM/dd HH:mm:ss"の形式にformatした文字列。 <BR>
     * <BR>
     * (*1) 現在日時 <BR>
     * TradingSystem.getSystemTimestamp() <BR>
     * <BR>
     * －　@index = 2 <BR>
     * 　@「発生日付」の文字列。 <BR>
     * <BR>
     * －　@index = 3 <BR>
     * 　@引数.発生日付 を"yyyy/MM/dd "の形式にformatした文字列。 <BR>
     * @@param l_datOccurDate - (発生日付)<BR>
     * 発生日付<BR>
     * @@roseuid 499B72F90070
     */
    protected void createKeyHeader(Date l_datOccurDate)
    {
        final String STR_METHOD_NAME = "createKeyHeader(Date)";
        log.entering(STR_METHOD_NAME);

        //以下の通り文字列の配列を生成し、
        //[キーヘッダ配列]
        String[] l_strKeyHeaders = new String[4];
        //－　@index = 0
        //　@「CSV出力日」の文字列。
        l_strKeyHeaders[0] = WEB3AdminIfoKeyHeaderDef.CSV_OUTPUT_DATE;

        //－　@index = 1
        //　@現在日時を"yyyy/MM/dd HH:mm:ss"の形式にformatした文字列。
        l_strKeyHeaders[1] = WEB3DateUtility.formatDate(
            GtlUtils.getSystemTimestamp(), WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS);

        //－　@index = 2
        //　@「発生日付」の文字列。
        l_strKeyHeaders[2] = WEB3AdminIfoKeyHeaderDef.OCCUR_DATE;

        //－　@index = 3
        //　@引数.発生日付 を"yyyy/MM/dd "の形式にformatした文字列。
        l_strKeyHeaders[3] = WEB3DateUtility.formatDate(
            l_datOccurDate, WEB3GentradeTimeDef.DATE_SPLIT_YMD);

        //setキーヘッダ()にてインスタンスにセットする。
        this.setKeyHeader(l_strKeyHeaders);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (createカラムヘッダ)<BR>
     * カラムヘッダをセットする。<BR>
     * <BR>
     * 以下のとおりにCSVカラムモデルの配列を生成し、<BR>
     * setカラムヘッダ()にてインスタンスにセットする。<BR>
     * <BR>
     * <BR>
     * １）　@部店コード <BR>
     * <BR>
     * 　@[CSVカラムモデルのコンストラクタの引数] <BR>
     * 　@　@項目ラベル： 証拠金不足状況ダウンロードCSV.部店コードラベル <BR>
     * 　@　@カラム番号： 0 <BR>
     * 　@　@項目型： CSVカラムモデル.項目型_文字列 <BR>
     * 　@　@日付フォーマット： null <BR>
     * <BR>
     * <BR>
     * ２）　@顧客コード <BR>
     * <BR>
     * 　@[CSVカラムモデルのコンストラクタの引数] <BR>
     * 　@　@項目ラベル： 証拠金不足状況ダウンロードCSV.顧客コードラベル <BR>
     * 　@　@カラム番号： 1 <BR>
     * 　@　@項目型： CSVカラムモデル.項目型_文字列 <BR>
     * 　@　@日付フォーマット： null <BR>
     * <BR>
     * ３）　@顧客名 <BR>
     * <BR>
     * 　@[CSVカラムモデルのコンストラクタの引数] <BR>
     * 　@　@項目ラベル： 証拠金不足状況ダウンロードCSV.顧客名ラベル <BR>
     * 　@　@カラム番号： 2 <BR>
     * 　@　@項目型： CSVカラムモデル.項目型_文字列 <BR>
     * 　@　@日付フォーマット： null <BR>
     * <BR>
     * ４）　@請求額 <BR>
     * <BR>
     * 　@[CSVカラムモデルのコンストラクタの引数] <BR>
     * 　@　@項目ラベル： 証拠金不足状況ダウンロードCSV.請求額ラベル <BR>
     * 　@　@カラム番号： 3 <BR>
     * 　@　@項目型： CSVカラムモデル.項目型_文字列 <BR>
     * 　@　@日付フォーマット： null <BR>
     * <BR>
     * ５）　@現在未入金額 <BR>
     * <BR>
     * 　@[CSVカラムモデルのコンストラクタの引数] <BR>
     * 　@　@項目ラベル： 証拠金不足状況ダウンロードCSV.現在未入金額ラベル <BR>
     * 　@　@カラム番号： 4<BR>
     * 　@　@項目型： CSVカラムモデル.項目型_文字列 <BR>
     * 　@　@日付フォーマット： null<BR>
     * <BR>
     * ６）　@現在証拠金所要額 <BR>
     * <BR>
     * 　@[CSVカラムモデルのコンストラクタの引数] <BR>
     * 　@　@項目ラベル： 証拠金不足状況ダウンロードCSV.現在証拠金所要額ラベル <BR>
     * 　@　@カラム番号： 5<BR>
     * 　@　@項目型： CSVカラムモデル.項目型_文字列 <BR>
     * 　@　@日付フォーマット： null<BR>
     * <BR>
     * <BR>
     * ７）　@建玉有無フラグ <BR>
     * <BR>
     * 　@[CSVカラムモデルのコンストラクタの引数] <BR>
     * 　@　@項目ラベル： 証拠金不足状況ダウンロードCSV.建玉有無フラグラベル <BR>
     * 　@　@カラム番号：　@6<BR>
     * 　@　@項目型： CSVカラムモデル.項目型_文字列 <BR>
     * 　@　@日付フォーマット： null<BR>
     * <BR>
     * ８）　@注文有無フラグ <BR>
     * <BR>
     * 　@[CSVカラムモデルのコンストラクタの引数] <BR>
     * 　@　@項目ラベル： 証拠金不足状況ダウンロードCSV.注文有無フラグラベル <BR>
     * 　@　@カラム番号：　@7<BR>
     * 　@　@項目型： CSVカラムモデル.項目型_文字列 <BR>
     * 　@　@日付フォーマット： null<BR>
     * @@roseuid 499B732F0225
     */
    protected void createColumnHeader()
    {
        final String STR_METHOD_NAME = "createColumnHeader()";
        log.entering(STR_METHOD_NAME);

        final int COLUMN_MAX = 8;
        WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[COLUMN_MAX];

        //部店コード
        //[CSVカラムモデルのコンストラクタの引数]
        //項目ラベル： 証拠金不足状況ダウンロードCSV.部店コードラベル
        //カラム番号： 0
        //項目型： CSVカラムモデル.項目型_文字列
        //日付フォーマット： null
        l_models[0] = new WEB3GentradeCsvColumnModel(
            this.branchCodeLabel,
            0,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //顧客コード
        //[CSVカラムモデルのコンストラクタの引数]
        //項目ラベル： 証拠金不足状況ダウンロードCSV.顧客コードラベル
        //カラム番号： 1
        //項目型： CSVカラムモデル.項目型_文字列
        //日付フォーマット： null
        l_models[1] = new WEB3GentradeCsvColumnModel(
            this.accountCodeLabel,
            1,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //顧客名
        //[CSVカラムモデルのコンストラクタの引数]
        //項目ラベル： 証拠金不足状況ダウンロードCSV.顧客名ラベル
        //カラム番号： 2
        //項目型： CSVカラムモデル.項目型_文字列
        //日付フォーマット： null
        l_models[2] = new WEB3GentradeCsvColumnModel(
            this.accountNameLabel,
            2,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //請求額
        //[CSVカラムモデルのコンストラクタの引数]
        //項目ラベル： 証拠金不足状況ダウンロードCSV.請求額ラベル
        //カラム番号： 3
        //項目型： CSVカラムモデル.項目型_文字列
        //日付フォーマット： null
        l_models[3] = new WEB3GentradeCsvColumnModel(
            this.claimAmountLabel,
            3,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //現在未入金額
        //[CSVカラムモデルのコンストラクタの引数]
        //項目ラベル： 証拠金不足状況ダウンロードCSV.現在未入金額ラベル
        //カラム番号： 4
        //項目型： CSVカラムモデル.項目型_文字列
        //日付フォーマット： null
        l_models[4] = new WEB3GentradeCsvColumnModel(
            this.curNonPayAmtLabel,
            4,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //現在証拠金所要額
        //[CSVカラムモデルのコンストラクタの引数]
        //項目ラベル： 証拠金不足状況ダウンロードCSV.現在証拠金所要額ラベル
        //カラム番号： 5
        //項目型： CSVカラムモデル.項目型_文字列
        //日付フォーマット： null
        l_models[5] = new WEB3GentradeCsvColumnModel(
            this.curIfoDepositNecessaryAmtLabel,
            5,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //建玉有無フラグ
        //[CSVカラムモデルのコンストラクタの引数]
        //項目ラベル： 証拠金不足状況ダウンロードCSV.建玉有無フラグラベル
        //カラム番号：　@6
        //項目型： CSVカラムモデル.項目型_文字列
        //日付フォーマット： null
        l_models[6] = new WEB3GentradeCsvColumnModel(
            this.contractExistFlagLabel,
            6,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //注文有無フラグ
        //[CSVカラムモデルのコンストラクタの引数]
        //項目ラベル： 証拠金不足状況ダウンロードCSV.注文有無フラグラベル
        //カラム番号：　@7
        //項目型： CSVカラムモデル.項目型_文字列
        //日付フォーマット： null
        l_models[7] = new WEB3GentradeCsvColumnModel(
            this.orderExistFlagLabel,
            7,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //setカラムヘッダ()にてインスタンスにセットする。
        this.setColumnHeader(l_models);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set部店コード)<BR>
     * １）カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@項目ラベル： 証拠金不足状況ダウンロードCSV.部店コードラベル<BR>
     * <BR>
     * ２）値セット <BR>
     * <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@行番号： 引数の行番号 <BR>
     * 　@　@カラム： １）で取得したカラムモデル <BR>
     * 　@　@値： 引数.部店コード <BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@roseuid 499B75FF02BD
     */
    public void setBranchCode(int l_intLineNo, String l_strBranchCode)
    {
        final String STR_METHOD_NAME = "setBranchCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        //this.getカラムモデル()にてCSVカラムモデルを取得する。
        //[引数]
        //項目ラベル： 証拠金不足状況ダウンロードCSV.部店コードラベル
        WEB3GentradeCsvColumnModel l_csvColumnModel = this.getColumnModel(this.branchCodeLabel);

        //値セット
        //this.set項目値()にて項目値をセットする。
        //[引数]
        //行番号： 引数の行番号
        //カラム： １）で取得したカラムモデル
        //値： 引数.部店コード
        this.setValue(l_intLineNo, l_csvColumnModel, l_strBranchCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set顧客コード)<BR>
     * １）カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@項目ラベル： 証拠金不足状況ダウンロードCSV.顧客コードラベル<BR>
     * <BR>
     * ２）値セット <BR>
     * <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@行番号： 引数の行番号 <BR>
     * 　@　@カラム： １）で取得したカラムモデル <BR>
     * 　@　@値： 引数.顧客コードの左6byte<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * @@roseuid 499B766B03D3
     */
    public void setAccountCode(int l_intLineNo, String l_strAccountCode)
    {
        final String STR_METHOD_NAME = "setAccountCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        //this.getカラムモデル()にてCSVカラムモデルを取得する。
        //[引数]
        //項目ラベル： 証拠金不足状況ダウンロードCSV.顧客コードラベル
        WEB3GentradeCsvColumnModel l_csvColumnModel = this.getColumnModel(this.accountCodeLabel);

        //値セット
        //this.set項目値()にて項目値をセットする。
        //[引数]
        //行番号： 引数の行番号
        //カラム： １）で取得したカラムモデル
        //値： 引数.顧客コードの左6byte
        String l_strValue = l_strAccountCode.substring(0, 6);
        this.setValue(l_intLineNo, l_csvColumnModel, l_strValue);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set顧客名)<BR>
     * １）カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@項目ラベル： 証拠金不足状況ダウンロードCSV.顧客名ラベル<BR>
     * <BR>
     * ２）値セット <BR>
     * <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@行番号： 引数の行番号 <BR>
     * 　@　@カラム： １）で取得したカラムモデル <BR>
     * 　@　@値： 引数.顧客名<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strAccountName - (顧客名)<BR>
     * 顧客名<BR>
     * @@roseuid 499B76BF0151
     */
    public void setAccountName(int l_intLineNo, String l_strAccountName)
    {
        final String STR_METHOD_NAME = "setAccountName(int, String)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        //this.getカラムモデル()にてCSVカラムモデルを取得する。
        //[引数]
        //項目ラベル： 証拠金不足状況ダウンロードCSV.顧客名ラベル
        WEB3GentradeCsvColumnModel l_csvColumnModel = this.getColumnModel(this.accountNameLabel);

        //値セット
        //this.set項目値()にて項目値をセットする。
        //[引数]
        //行番号： 引数の行番号
        //カラム： １）で取得したカラムモデル
        //値： 引数.顧客名
        this.setValue(l_intLineNo, l_csvColumnModel, l_strAccountName);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set請求額)<BR>
     * １）カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@項目ラベル： 証拠金不足状況ダウンロードCSV.請求額ラベル<BR>
     * <BR>
     * ２）値セット <BR>
     * <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@行番号： 引数の行番号 <BR>
     * 　@　@カラム： １）で取得したカラムモデル <BR>
     * 　@　@値： 引数.請求額<BR>
     * <BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strClaimAmount - (請求額)<BR>
     * 請求額<BR>
     * @@roseuid 499B774D01DB
     */
    public void setClaimAmount(int l_intLineNo, String l_strClaimAmount)
    {
        final String STR_METHOD_NAME = "setClaimAmount(int, String)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        //this.getカラムモデル()にてCSVカラムモデルを取得する。
        //[引数]
        //項目ラベル： 証拠金不足状況ダウンロードCSV.請求額ラベル
        WEB3GentradeCsvColumnModel l_csvColumnModel = this.getColumnModel(this.claimAmountLabel);

        //値セット
        //this.set項目値()にて項目値をセットする。
        //[引数]
        //行番号： 引数の行番号
        //カラム： １）で取得したカラムモデル
        //値： 引数.請求額
        this.setValue(l_intLineNo, l_csvColumnModel, l_strClaimAmount);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set現在未入金額)<BR>
     * １）カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@項目ラベル： 証拠金不足状況ダウンロードCSV.現在未入金額ラベル<BR>
     * <BR>
     * ２）値セット <BR>
     * <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@行番号： 引数の行番号 <BR>
     * 　@　@カラム： １）で取得したカラムモデル <BR>
     * 　@　@値： <BR>
     * 　@　@　@[引数.現在未入金額＝＝ null の場合] <BR>
     * 　@　@　@　@"-" <BR>
     * 　@　@　@[引数.現在未入金額! ＝ null の場合] <BR>
     * 　@　@　@　@引数.現在未入金額<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strCurNonPayAmt - (現在未入金額)<BR>
     * 現在未入金額<BR>
     * @@roseuid 499B86B60378
     */
    public void setCurNonPayAmt(int l_intLineNo, String l_strCurNonPayAmt)
    {
        final String STR_METHOD_NAME = "setCurNonPayAmt(int, String)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        //this.getカラムモデル()にてCSVカラムモデルを取得する。
        //[引数]
        //項目ラベル： 証拠金不足状況ダウンロードCSV.現在未入金額ラベル
        WEB3GentradeCsvColumnModel l_csvColumnModel = this.getColumnModel(this.curNonPayAmtLabel);

        //値セット
        //this.set項目値()にて項目値をセットする。
        //[引数]
        //行番号： 引数の行番号
        //カラム： １）で取得したカラムモデル
        //値：
        //[引数.現在未入金額＝＝ null の場合] "-"
        //[引数.現在未入金額! ＝ null の場合] 引数.現在未入金額
        String l_strValue = null;
        if (l_strCurNonPayAmt == null)
        {
            l_strValue = WEB3AdminIfoCurNonPayAmtDef.EMPTY;
        }
        else
        {
            l_strValue = l_strCurNonPayAmt;
        }

        this.setValue(l_intLineNo, l_csvColumnModel, l_strValue);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set現在証拠金所要額)<BR>
     * １）カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@項目ラベル： 証拠金不足状況ダウンロードCSV.現在証拠金所要額ラベル<BR>
     * <BR>
     * ２）値セット <BR>
     * <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@行番号： 引数の行番号 <BR>
     * 　@　@カラム： １）で取得したカラムモデル <BR>
     * 　@　@値： 引数.現在証拠金所要額<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strCurIfoDepositNecessaryAmt - (現在証拠金所要額)<BR>
     * 現在証拠金所要額<BR>
     * @@roseuid 499B87130135
     */
    public void setCurIfoDepositNecessaryAmt(int l_intLineNo, String l_strCurIfoDepositNecessaryAmt)
    {
        final String STR_METHOD_NAME = "setCurIfoDepositNecessaryAmt(int, String)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        //this.getカラムモデル()にてCSVカラムモデルを取得する。
        //[引数]
        //項目ラベル： 証拠金不足状況ダウンロードCSV.現在証拠金所要額ラベル
        WEB3GentradeCsvColumnModel l_csvColumnModel = this.getColumnModel(this.curIfoDepositNecessaryAmtLabel);

        //値セット
        //this.set項目値()にて項目値をセットする。
        //[引数]
        //行番号： 引数の行番号
        //カラム： １）で取得したカラムモデル
        //値： 引数.現在証拠金所要額ラベル
        this.setValue(l_intLineNo, l_csvColumnModel, l_strCurIfoDepositNecessaryAmt);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set建玉有無フラグ)<BR>
     * １）カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@項目ラベル： 証拠金不足状況ダウンロードCSV.建玉有無フラグラベル<BR>
     * <BR>
     * ２）値セット <BR>
     * <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@行番号： 引数の行番号 <BR>
     * 　@　@カラム： １）で取得したカラムモデル <BR>
     * 　@　@値： <BR>
     * 　@　@　@[引数.建玉有無フラグ＝＝trueの場合] <BR>
     * 　@　@　@　@"有" <BR>
     * 　@　@　@[引数.建玉有無フラグ＝＝falseの場合] <BR>
     * 　@　@　@　@"無"<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@param l_blnContractExistFlag - (建玉有無フラグ)<BR>
     * 建玉有無フラグ<BR>
     * @@roseuid 499B87550164
     */
    public void setContractExistFlag(int l_intLineNo, boolean l_blnContractExistFlag)
    {
        final String STR_METHOD_NAME = "setContractExistFlag(int, boolean)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        //this.getカラムモデル()にてCSVカラムモデルを取得する。
        //[引数]
        //項目ラベル： 証拠金不足状況ダウンロードCSV.建玉有無フラグラベル
        WEB3GentradeCsvColumnModel l_csvColumnModel = this.getColumnModel(this.contractExistFlagLabel);

        //値セット
        //this.set項目値()にて項目値をセットする。
        //[引数]
        //行番号： 引数の行番号
        //カラム： １）で取得したカラムモデル
        //値：
        //[引数.建玉有無フラグ＝＝trueの場合] "有"
        //[引数.建玉有無フラグ＝＝falseの場合] "無"
        String l_strValue = null;
        if (l_blnContractExistFlag)
        {
            l_strValue = WEB3AdminIfoExistFlagDef.EXIST;
        }
        else
        {
            l_strValue = WEB3AdminIfoExistFlagDef.NO;
        }
        this.setValue(l_intLineNo, l_csvColumnModel, l_strValue);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set注文有無フラグ)<BR>
     * １）カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@項目ラベル： 証拠金不足状況ダウンロードCSV.注文有無フラグラベル<BR>
     * <BR>
     * ２）値セット <BR>
     * <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@行番号： 引数の行番号 <BR>
     * 　@　@カラム： １）で取得したカラムモデル <BR>
     * 　@　@値： <BR>
     * 　@　@　@[引数.注文有無フラグ＝＝trueの場合] <BR>
     * 　@　@　@　@"有" <BR>
     * 　@　@　@[引数.注文有無フラグ＝＝falseの場合] <BR>
     * 　@　@　@　@"無"<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@param l_blnOrderExistFlag - (注文有無フラグ)<BR>
     * 注文有無フラグ<BR>
     * @@roseuid 499B87A703B5
     */
    public void setOrderExistFlag(int l_intLineNo, boolean l_blnOrderExistFlag)
    {
        final String STR_METHOD_NAME = "setOrderExistFlag(int, boolean)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        //this.getカラムモデル()にてCSVカラムモデルを取得する。
        //[引数]
        //項目ラベル： 証拠金不足状況ダウンロードCSV.注文有無フラグラベル
        WEB3GentradeCsvColumnModel l_csvColumnModel = this.getColumnModel(this.orderExistFlagLabel);

        //値セット
        //this.set項目値()にて項目値をセットする。
        //[引数]
        //行番号： 引数の行番号
        //カラム： １）で取得したカラムモデル
        //値：
        //[引数.注文有無フラグ＝＝trueの場合] "有"
        //[引数.注文有無フラグ＝＝falseの場合] "無"
        String l_strValue = null;
        if (l_blnOrderExistFlag)
        {
            l_strValue = WEB3AdminIfoExistFlagDef.EXIST;
        }
        else
        {
            l_strValue = WEB3AdminIfoExistFlagDef.NO;
        }
        this.setValue(l_intLineNo, l_csvColumnModel, l_strValue);

        log.exiting(STR_METHOD_NAME);
    }
}@
