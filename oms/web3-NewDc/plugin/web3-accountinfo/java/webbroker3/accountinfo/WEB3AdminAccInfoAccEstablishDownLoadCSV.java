head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.24.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoAccEstablishDownLoadCSV.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 新規口座開設ダウンロードCSV(WEB3AdminAccInfoAccEstablishDownLoadCSV.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/09 何文敏 (中訊) 新規作成
                 : 2006/11/24 周捷 (中訊) モデル No.147、No.148
*/

package webbroker3.accountinfo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (新規口座開設・口座移管・ログインロック顧客ダウンロードCSV)<BR>
 * 新規口座開設・口座移管・ログインロック顧客ファ@イルダウンロードで作成するCSVファ@イルデータクラス <BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0
 */

public class WEB3AdminAccInfoAccEstablishDownLoadCSV extends WEB3GentradeCsvDataModel
{
    /**
     * ログ出力オブジェクト。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoAccEstablishDownLoadCSV.class);

    /**
     * (データ内容番号ラベル)<BR>
     * データ内容番号ラベル<BR>
     */
    public static  final String dataContentDiv = "データ内容番号";

    /**
     * (会社コード)<BR>
     * 会社コード<BR>
     */
    public static  final String institutionCode = "会社コード";

    /**
     * (部店コードラベル)<BR>
     * 部店コードラベル<BR>
     */
    public static  final String branchCode = "部店コード";

    /**
     * (扱者コードラベル)<BR>
     * 扱者コードラベル<BR>
     */
    public static  final String tradeCode = "扱者コード";

    /**
     * (顧客コードラベル)<BR>
     * 顧客コードラベル<BR>
     */
    public static  final String accountCode = "顧客コード";

    /**
     * (口座種別ラベル)<BR>
     * 口座種別ラベル<BR>
     */
    public static  final String accountType = "口座種別";

    /**
     * (顧客名（漢字）ラベル)<BR>
     * 顧客名（漢字）ラベル<BR>
     */
    public static  final String accountName = "顧客名（漢字）";

    /**
     * (顧客名（カナ）ラベル)<BR>
     * 顧客名（カナ）ラベル<BR>
     */
    public static  final String accountNameKana = "顧客名（カナ）";

    /**
     * (口座開設日ラベル)<BR>
     * 口座開設日ラベル<BR>
     */
    public static  final String accountOpenDate = "口座開設日";

    /**
     * (入金先銀行ラベル)<BR>
     * 入金先銀行ラベル<BR>
     */
    public static  final String payFinancialInstitution = "入金先銀行";

    /**
     * (銀行支店名ラベル)<BR>
     * 銀行支店名ラベル<BR>
     */
    public static  final String financialBranchName = "銀行支店名";

    /**
     * (科目ラベル)<BR>
     * 科目ラベル<BR>
     */
    public static  final String item = "科目";

    /**
     * (銀行番号ラベル)<BR>
     * 銀行番号ラベル<BR>
     */
    public static  final String financialInstitutionNumber = "銀行番号";

    /**
     * (銀行支店番号ラベル)<BR>
     * 銀行支店番号ラベル<BR>
     */
    public static  final String financialBranchCode = "銀行支店番号";

    /**
     * (銀行口座番号ラベル)<BR>
     * 銀行口座番号ラベル<BR>
     */
    public static  final String financialAccountCode = "銀行口座番号";

    /**
     * (顧客住所（郵便番号）ラベル)<BR>
     * 顧客住所（郵便番号）ラベル<BR>
     */
    public static  final String zipCode = "顧客住所（郵便番号）";

    /**
     * (顧客住所（住所1）ラベル)<BR>
     * 顧客住所（住所1）ラベル<BR>
     */
    public static  final String address1 = "顧客住所（住所1）";

    /**
     * (顧客住所（住所2）ラベル)<BR>
     * 顧客住所（住所2）ラベル<BR>
     */
    public static  final String address2 = "顧客住所（住所2）";

    /**
     * (顧客住所（住所3）ラベル)<BR>
     * 顧客住所（住所3）ラベル<BR>
     */
    public static  final String address3 = "顧客住所（住所3）";
    
    /**
     * (電話番号ラベル)<BR>
     * 電話番号ラベル<BR>
     */
    public static  final String telephone = "電話番号";
    
    /**
     * (部店名ラベル)<BR>
     * 部店名ラベル<BR>
     */
    public static  final String branchName = "部店名";

    /**
     * (新規口座開設・口座移管・ログインロック顧客ダウンロードCSV ())<BR>
     * コンストラクタ。 <BR>
     * <BR>
     * インスタンスを生成し、ヘッダ情報をセットする。<BR>
     * 　@・ super()にてインスタンスを生成する。<BR>
     * 　@・ createキーヘッダ()をコールし、キーヘッダ情報を作成する。<BR>
     * 　@・ createカラムヘッダ()をコールし、カラムヘッダ情報を作成する。<BR>
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public  WEB3AdminAccInfoAccEstablishDownLoadCSV()
    {
        //インスタンスを生成し、ヘッダ情報をセットする。
        super();
        this.createKeysHeader();
        this.createColumnHeader();
    }

    /**
     * (createカラムヘッダ)<BR>
     * カラムヘッダをセットする。<BR>
     * <BR>
     * 以下の通りCSVカラムモデルの配列を生成し、setカラムヘッダ()にてインスタンスにセットする。<BR>
     * <BR>
     * [カラムヘッダ配列]<BR>
     * <BR>
     * －　@index = 0<BR>
     * 　@[CSVカラムモデル コンストラクタの引数<BR>
     * 　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.データ内容番号ラベル<BR>
     * 　@カラム番号： 0<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 1<BR>
     * 　@[CSVカラムモデル コンストラクタの引数<BR>
     * 　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.会社コードラベル<BR>
     * 　@カラム番号： 1<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 2<BR>
     * 　@[CSVカラムモデル コンストラクタの引数<BR>
     * 　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.部店コードラベル<BR>
     * 　@カラム番号： 2<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 3<BR>
     * 　@[CSVカラムモデル コンストラクタの引数<BR>
     * 　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.扱者コードラベル<BR>
     * 　@カラム番号： 3<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 4<BR>
     * 　@[CSVカラムモデル コンストラクタの引数<BR>
     * 　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.顧客コードラベル<BR>
     * 　@カラム番号： 4<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 5<BR>
     * 　@[CSVカラムモデル コンストラクタの引数<BR>
     * 　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.口座種別ラベル<BR>
     * 　@カラム番号： 5<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 6<BR>
     * 　@[CSVカラムモデル コンストラクタの引数<BR>
     * 　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.顧客名(漢字）ラベル<BR>
     * 　@カラム番号： 6<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 7<BR>
     * 　@[CSVカラムモデル コンストラクタの引数<BR>
     * 　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.顧客名(カナ）ラベル<BR>
     * 　@カラム番号： 7<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 8<BR>
     * 　@[CSVカラムモデル コンストラクタの引数<BR>
     * 　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.口座開設日ラベル<BR>
     * 　@カラム番号： 8<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@new SimpleDateFormat("yyyy/ｍｍ/dd") <BR>
     * <BR>
     * －　@index = 9<BR>
     * 　@[CSVカラムモデル コンストラクタの引数<BR>
     * 　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.入金先銀行ラベル<BR>
     * 　@カラム番号： 9<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 10<BR>
     * 　@[CSVカラムモデル コンストラクタの引数<BR>
     * 　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.銀行支店名ラベル<BR>
     * 　@カラム番号： 10<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 11<BR>
     * 　@[CSVカラムモデル コンストラクタの引数<BR>
     * 　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.科目ラベル<BR>
     * 　@カラム番号： 11<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 12<BR>
     * 　@[CSVカラムモデル コンストラクタの引数<BR>
     * 　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.銀行番号ラベル<BR>
     * 　@カラム番号： 12<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 13<BR>
     * 　@[CSVカラムモデル コンストラクタの引数<BR>
     * 　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.銀行支店番号ラベル<BR>
     * 　@カラム番号： 13<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 14<BR>
     * 　@[CSVカラムモデル コンストラクタの引数<BR>
     * 　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.銀行口座番号ラベル<BR>
     * 　@カラム番号： 14<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 15<BR>
     * 　@[CSVカラムモデル コンストラクタの引数<BR>
     * 　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.顧客住所（郵便番号）ラベル<BR>
     * 　@カラム番号： 15<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 16<BR>
     * 　@[CSVカラムモデル コンストラクタの引数<BR>
     * 　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.顧客住所（住所1）ラベル<BR>
     * 　@カラム番号： 16<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 17<BR>
     * 　@[CSVカラムモデル コンストラクタの引数<BR>
     * 　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.顧客住所（住所2）ラベル<BR>
     * 　@カラム番号： 17<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 18<BR>
     * 　@[CSVカラムモデル コンストラクタの引数<BR>
     * 　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.顧客住所（住所3）ラベル<BR>
     * 　@カラム番号： 18<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 19<BR>
     * 　@[CSVカラムモデル コンストラクタの引数<BR>
     * 　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.電話番号ラベル<BR>
     * 　@カラム番号： 19<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 20<BR>
     * 　@[CSVカラムモデル コンストラクタの引数<BR>
     * 　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.部店名ラベル<BR>
     * 　@カラム番号： 20<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void createColumnHeader()
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);

        final int COLUMN_MAX = 21;
        this.columnHeader = new WEB3GentradeCsvColumnModel[COLUMN_MAX];

        //以下の通りCSVカラムモデルの配列を生成し、setカラムヘッダ()にてインスタンスにセットする。
        //[カラムヘッダ配列]
        //－　@index = 0
        //　@[CSVカラムモデル コンストラクタの引数]
        //　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.データ内容番号ラベル
        //　@カラム番号： 0
        //　@項目型：　@CSVカラムモデル.項目型_文字列
        //　@日付フォーマット：　@null
        this.columnHeader[0] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.dataContentDiv,
            0,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //－　@index = 1
        //　@[CSVカラムモデル コンストラクタの引数]
        //　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.会社コードラベル
        //　@カラム番号： 1
        //　@項目型：　@CSVカラムモデル.項目型_文字列
        //　@日付フォーマット：　@null
        this.columnHeader[1] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.institutionCode,
            1,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //－　@index = 2
        //　@[CSVカラムモデル コンストラクタの引数]
        //　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.部店コードラベル
        //　@カラム番号： 2
        //　@項目型：　@CSVカラムモデル.項目型_文字列
        //　@日付フォーマット：　@null
        this.columnHeader[2] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.branchCode,
            2,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //－　@index = 3
        //　@[CSVカラムモデル コンストラクタの引数]
        //　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.扱者コードラベル
        //　@カラム番号： 3
        //　@項目型：　@CSVカラムモデル.項目型_文字列
        //　@日付フォーマット：　@null
        this.columnHeader[3] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.tradeCode,
            3,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //－　@index = 4
        //　@[CSVカラムモデル コンストラクタの引数]
        //　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.顧客コードラベル
        //　@カラム番号： 4
        //　@項目型：　@CSVカラムモデル.項目型_文字列
        //　@日付フォーマット：　@null
        this.columnHeader[4] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.accountCode,
            4,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //－　@index = 5
        //　@[CSVカラムモデル コンストラクタの引数]
        //　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.口座種別ラベル
        //　@カラム番号： 5
        //　@項目型：　@CSVカラムモデル.項目型_文字列
        //　@日付フォーマット：　@null
        this.columnHeader[5] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.accountType,
            5,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //－　@index = 6
        //　@[CSVカラムモデル コンストラクタの引数]
        //　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.顧客名(漢字）ラベル
        //　@カラム番号： 6
        //　@項目型：　@CSVカラムモデル.項目型_文字列
        //　@日付フォーマット：　@null
        this.columnHeader[6] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.accountName,
            6,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //－　@index = 7
        //　@[CSVカラムモデル コンストラクタの引数]
        //　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.顧客名(カナ）ラベル
        //　@カラム番号： 7
        //　@項目型：　@CSVカラムモデル.項目型_文字列
        //　@日付フォーマット：　@null
        this.columnHeader[7] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.accountNameKana,
            7,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //－　@index = 8
        //　@[CSVカラムモデル コンストラクタの引数]
        //　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.口座開設日ラベル
        //　@カラム番号： 8
        //　@項目型：　@CSVカラムモデル.項目型_日付時間
        //　@日付フォーマット：　@new SimpleDateFormat("yyyy/ｍｍ/dd")
        SimpleDateFormat l_dataFormat = new SimpleDateFormat("yyyy/ｍｍ/dd");
        this.columnHeader[8] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.accountOpenDate,
            8,
            WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
            l_dataFormat);

        //－　@index = 9
        //　@[CSVカラムモデル コンストラクタの引数]
        //　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.入金先銀行ラベル
        //　@カラム番号： 9
        //　@項目型：　@CSVカラムモデル.項目型_文字列
        //　@日付フォーマット：　@null
        this.columnHeader[9] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.payFinancialInstitution,
            9,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //－　@index = 10
        //　@[CSVカラムモデル コンストラクタの引数]
        //　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.銀行支店名ラベル
        //　@カラム番号： 10
        //　@項目型：　@CSVカラムモデル.項目型_文字列
        //　@日付フォーマット：　@null
        this.columnHeader[10] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.financialBranchName,
            10,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //－　@index = 11
        //　@[CSVカラムモデル コンストラクタの引数]
        //　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.科目ラベル
        //　@カラム番号： 11
        //　@項目型：　@CSVカラムモデル.項目型_文字列
        //　@日付フォーマット：　@null
        this.columnHeader[11] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.item,
            11,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //－　@index = 12
        //　@[CSVカラムモデル コンストラクタの引数]
        //　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.銀行番号ラベル
        //　@カラム番号： 12
        //　@項目型：　@CSVカラムモデル.項目型_文字列
        //　@日付フォーマット：　@null
        this.columnHeader[12] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.financialInstitutionNumber,
            12,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //－　@index = 13
        //　@[CSVカラムモデル コンストラクタの引数]
        //　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.銀行支店番号ラベル
        //　@カラム番号： 13
        //　@項目型：　@CSVカラムモデル.項目型_文字列
        //　@日付フォーマット：　@null
        this.columnHeader[13] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.financialBranchCode,
            13,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //－　@index = 14
        //　@[CSVカラムモデル コンストラクタの引数]
        //　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.銀行口座番号ラベル
        //　@カラム番号： 14
        //　@項目型：　@CSVカラムモデル.項目型_文字列
        //　@日付フォーマット：　@null
        this.columnHeader[14] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.financialAccountCode,
            14,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //－　@index = 15
        //　@[CSVカラムモデル コンストラクタの引数]
        //　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.顧客住所（郵便番号）ラベル
        //　@カラム番号： 15
        //　@項目型：　@CSVカラムモデル.項目型_文字列
        //　@日付フォーマット：　@null
        this.columnHeader[15] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.zipCode,
            15,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //－　@index = 16
        //　@[CSVカラムモデル コンストラクタの引数]
        //　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.顧客住所（住所1）ラベル
        //　@カラム番号： 16
        //　@項目型：　@CSVカラムモデル.項目型_文字列
        //　@日付フォーマット：　@null
        this.columnHeader[16] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.address1,
            16,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //－　@index = 17
        //　@[CSVカラムモデル コンストラクタの引数]
        //　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.顧客住所（住所2）ラベル
        //　@カラム番号： 17
        //　@項目型：　@CSVカラムモデル.項目型_文字列
        //　@日付フォーマット：　@null
        this.columnHeader[17] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.address2,
            17,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //－　@index = 18
        //　@[CSVカラムモデル コンストラクタの引数]
        //　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.顧客住所（住所3）ラベル
        //　@カラム番号： 18
        //　@項目型：　@CSVカラムモデル.項目型_文字列
        //　@日付フォーマット：　@null
        this.columnHeader[18] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.address3,
            18,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //－　@index = 19
        //　@[CSVカラムモデル コンストラクタの引数]
        //　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.電話番号ラベル
        //　@カラム番号： 19
        //　@項目型：　@CSVカラムモデル.項目型_文字列
        //　@日付フォーマット：　@null
        this.columnHeader[19] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.telephone,
            19,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //－　@index = 20
        //　@[CSVカラムモデル コンストラクタの引数]
        //　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.部店名ラベル
        //　@カラム番号： 20
        //　@項目型：　@CSVカラムモデル.項目型_文字列
        //　@日付フォーマット：　@null
        this.columnHeader[20] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.branchName,
            20,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (createキーヘッダ)<BR>
     * キーヘッダをセットする。<BR>
     * <BR>
     * 以下の通り文字列の配列を生成し、setキーヘッダ()にてインスタンスにセットする。<BR>
     * <BR>
     * [キーヘッダ配列]<BR>
     * <BR>
     * －　@index = 0<BR>
     * 　@現在日時(*1)を"yyyy/MM/dd HH:mm:ss"の形式にformatした文字列。<BR>
     * <BR>
     * (*1) 現在日時<BR>
     * TradingSystem.getSystemTimestamp()<BR>
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void createKeysHeader()
    {
        final String STR_METHOD_NAME = " createKeysHeader()";
        log.entering(STR_METHOD_NAME);

        String[] l_strSetKeyHeader = new String[1];
        Timestamp l_systemTimestamp = GtlUtils.getSystemTimestamp();

        //現在日時(*1)を"yyyy/MM/dd HH:mm:ss"の形式にformatした文字列。
        l_strSetKeyHeader[0] =
            WEB3DateUtility.formatDate(l_systemTimestamp, "yyyy/MM/dd HH:mm:ss");

        //以下の通り文字列の配列を生成し、setキーヘッダ()にてインスタンスにセットする。
        this.setKeyHeader(l_strSetKeyHeader);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (setデータ内容番号)<BR>
     * 「データ内容番号」をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *  　@[getカラムモデル()に指定する引数]<BR>
     *  　@　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.データ内容番号ラベル<BR>
     * <BR>
     *  ２）　@値セット<BR>
     *  　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *  　@[set項目値()に指定する引数]<BR>
     *  　@　@行番号：　@引数.行番号<BR>
     *  　@　@カラム：　@１）で取得したカラムモデル<BR>
     *  　@　@値：　@引数.データ内容番号<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号
     * @@param l_strDataContentDiv - (データ内容番号)<BR>
     * データ内容番号
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void setDataContentDiv(int l_intLineNumber, String l_strDataContentDiv)
    {
        final String STR_METHOD_NAME = " setDataContentDiv(int, String)";
        log.entering(STR_METHOD_NAME);

        //１）　@カラムモデル取得
        //this.getカラムモデル()にてCSVカラムモデルを取得する。
        //[getカラムモデル()に指定する引数]
        //項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.データ内容番号ラベル
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.dataContentDiv);

        //２）　@値セット
        //this.set項目値()にて項目値をセットする。
        //[set項目値()に指定する引数]
        //行番号：　@引数.行番号
        //カラム：　@１）で取得したカラムモデル
        //値：　@引数.データ内容番号
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strDataContentDiv);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set会社コード)<BR>
     * 「会社コード」をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *  　@[getカラムモデル()に指定する引数]<BR>
     *  　@　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.会社コードラベル<BR>
     * <BR>
     *  ２）　@値セット<BR>
     *  　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *  　@[set項目値()に指定する引数]<BR>
     *  　@　@行番号：　@引数.行番号<BR>
     *  　@　@カラム：　@１）で取得したカラムモデル<BR>
     *  　@　@値：　@引数.会社コード<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void setInstitutionCode(int l_intLineNumber, String l_strInstitutionCode)
    {
        final String STR_METHOD_NAME = " setInstitutionCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //１）　@カラムモデル取得
        //this.getカラムモデル()にてCSVカラムモデルを取得する。
        //[getカラムモデル()に指定する引数]
        //項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.会社コードラベル
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.institutionCode);

        //２）　@値セット
        //this.set項目値()にて項目値をセットする。
        //[set項目値()に指定する引数]
        //行番号：　@引数.行番号
        //カラム：　@１）で取得したカラムモデル
        //値：　@引数.証券会社コード
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strInstitutionCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set部店コード)<BR>
     * 「部店コード」をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *  　@[getカラムモデル()に指定する引数]<BR>
     *  　@　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.部店コード内容番号ラベル<BR>
     * <BR>
     *  ２）　@値セット<BR>
     *  　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *  　@[set項目値()に指定する引数]<BR>
     *  　@　@行番号：　@引数.行番号<BR>
     *  　@　@カラム：　@１）で取得したカラムモデル<BR>
     *  　@　@値：　@引数.部店コード<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void setBranchCode(int l_intLineNumber, String l_strBranchCode)
    {
        final String STR_METHOD_NAME = " setBranchCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //１）　@カラムモデル取得
        //this.getカラムモデル()にてCSVカラムモデルを取得する。
        //[getカラムモデル()に指定する引数]
        //項目ラベル：　@新規口座開設・ログインロック顧客ダウンロードCSV.部店コードラベル
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.branchCode);

        //２）　@値セット
        //this.set項目値()にて項目値をセットする。
        //[set項目値()に指定する引数]
        //行番号：　@引数.行番号
        //カラム：　@１）で取得したカラムモデル
        //値：　@引数.部店コード
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strBranchCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set扱者コード)<BR>
     * 「扱者コード」をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *  　@[getカラムモデル()に指定する引数]<BR>
     *  　@　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.扱者コードラベル<BR>
     * <BR>
     *  ２）　@値セット<BR>
     *  　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *  　@[set項目値()に指定する引数]<BR>
     *  　@　@行番号：　@引数.行番号<BR>
     *  　@　@カラム：　@１）で取得したカラムモデル<BR>
     *  　@　@値：　@引数.扱者コード<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号
     * @@param l_strTraderCode - (扱者コード)<BR>
     * 扱者コード
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void setTraderCode(int l_intLineNumber, String l_strTraderCode)
    {
        final String STR_METHOD_NAME = " setTraderCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //１）　@カラムモデル取得
        //this.getカラムモデル()にてCSVカラムモデルを取得する。
        //[getカラムモデル()に指定する引数]
        //項目ラベル：　@新規口座開設・ログインロック顧客ダウンロードCSV.扱者コードラベル
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.tradeCode);

        //２）　@値セット
        //this.set項目値()にて項目値をセットする。
        //[set項目値()に指定する引数]
        //行番号：　@引数.行番号
        //カラム：　@１）で取得したカラムモデル
        //値：　@引数.扱者コード
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strTraderCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set顧客コード)<BR>
     * 「顧客コード 」をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *  　@[getカラムモデル()に指定する引数]<BR>
     *  　@　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.顧客コード ラベル<BR>
     * <BR>
     *  ２）　@値セット<BR>
     *  　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *  　@[set項目値()に指定する引数]<BR>
     *  　@　@行番号：　@引数.行番号<BR>
     *  　@　@カラム：　@１）で取得したカラムモデル<BR>
     *　@　@  値：　@　@　@引数.口座コードを編集した値（*1) <BR>
     * <BR>
     *（*1） 出力用に「引数.顧客コード」を編集<BR>
     *　@・引数.口座コード（7桁）の1桁目～6桁目を抜出す。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コード
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void setAccountCode(int l_intLineNumber, String l_strAccountCode)
    {
        final String STR_METHOD_NAME = " setAccountCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //１）　@カラムモデル取得
        //this.getカラムモデル()にてCSVカラムモデルを取得する。
        //[getカラムモデル()に指定する引数]
        //項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.顧客コードラベル
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.accountCode);

        //２）　@値セット
        //this.set項目値()にて項目値をセットする。
        //[set項目値()に指定する引数]
        //行番号：　@引数.行番号
        //カラム：　@１）で取得したカラムモデル
        //値：　@引数.口座コードを編集した値（*1）
        //（*1） 出力用に「引数.顧客コード」を編集
        //・引数.口座コード（7桁）の1桁目～6桁目を抜出す。
        String l_strNewAccountCode = null;
        if (l_strAccountCode != null)
        {
            l_strNewAccountCode = l_strAccountCode.substring(0, 6);
        }

        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strNewAccountCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set口座種別)<BR>
     * 「口座種別」をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *  　@[getカラムモデル()に指定する引数]<BR>
     *  　@　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.口座種別ラベル<BR>
     * <BR>
     *  ２）　@値セット<BR>
     *  　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *  　@[set項目値()に指定する引数]<BR>
     *  　@　@行番号：　@引数.行番号<BR>
     *  　@　@カラム：　@１）で取得したカラムモデル<BR>
     *  　@　@値：　@引数.口座タイプ<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号
     * @@param l_strAccountType - (口座タイプ)<BR>
     * 口座タイプ
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void setAccountTypeCode(int l_intLineNumber, String l_strAccountType)
    {
        final String STR_METHOD_NAME = " setAccountTypeCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //１）　@カラムモデル取得
        //this.getカラムモデル()にてCSVカラムモデルを取得する。
        //[getカラムモデル()に指定する引数]
        //項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.口座種別ラベル
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.accountType);

        //２）　@値セット
        //this.set項目値()にて項目値をセットする。
        //[set項目値()に指定する引数]
        //行番号：　@引数.行番号
        //カラム：　@１）で取得したカラムモデル
        //値：　@引数.口座タイプ
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strAccountType);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set顧客名(漢字）)<BR>
     * 「顧客名(漢字）」をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *  　@[getカラムモデル()に指定する引数]<BR>
     *  　@　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.顧客名(漢字）ラベル<BR>
     * <BR>
     *  ２）　@値セット<BR>
     *  　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *  　@[set項目値()に指定する引数]<BR>
     *  　@　@行番号：　@引数.行番号<BR>
     *  　@　@カラム：　@１）で取得したカラムモデル<BR>
     *  　@　@値：　@引数.名前(苗字)<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号
     * @@param l_strFamilyName - (名前(苗字）)<BR>
     * 名前(苗字）
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void setAccountName(int l_intLineNumber, String l_strFamilyName)
    {
        final String STR_METHOD_NAME = " setAccountName(int, String)";
        log.entering(STR_METHOD_NAME);

        //１）　@カラムモデル取得
        //this.getカラムモデル()にてCSVカラムモデルを取得する。
        //[getカラムモデル()に指定する引数]
        //項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.顧客名(漢字）ラベル
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.accountName);

        //２）　@値セット
        //this.set項目値()にて項目値をセットする。
        //[set項目値()に指定する引数]
        //行番号：　@引数.行番号
        //カラム：　@１）で取得したカラムモデル
        //値：　@引数.名前(苗字）
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strFamilyName);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set顧客名(カナ))<BR>
     * 「顧客名(カナ)」をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *  　@[getカラムモデル()に指定する引数]<BR>
     *  　@　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.顧客名(カナ)ラベル<BR>
     * <BR>
     *  ２）　@値セット<BR>
     *  　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *  　@[set項目値()に指定する引数]<BR>
     *  　@　@行番号：　@引数.行番号<BR>
     *  　@　@カラム：　@１）で取得したカラムモデル<BR>
     *  　@　@値：　@引数.名前（苗字１）<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号
     * @@param l_strFamilyNameAlt1 - 名前（苗字１）<BR>
     * 名前（苗字１）
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void setAccountNameKana(int l_intLineNumber, String l_strFamilyNameAlt1)
    {
        final String STR_METHOD_NAME = " setAccountNameKana(int, String)";
        log.entering(STR_METHOD_NAME);

        //１）　@カラムモデル取得
        //this.getカラムモデル()にてCSVカラムモデルを取得する。
        //[getカラムモデル()に指定する引数]
        //項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.顧客名(カナ）ラベル
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.accountNameKana);

        //２）　@値セット
        //this.set項目値()にて項目値をセットする。
        //[set項目値()に指定する引数]
        //行番号：　@引数.行番号
        //カラム：　@１）で取得したカラムモデル
        //値：　@引数.名前（苗字１）
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strFamilyNameAlt1);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set口座開設日)<BR>
     * 「口座開設日」をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *  　@[getカラムモデル()に指定する引数]<BR>
     *  　@　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.口座開設日ラベル<BR>
     * <BR>
     *  ２）　@値セット<BR>
     *  　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *  　@[set項目値()に指定する引数]<BR>
     *  　@　@行番号：　@引数.行番号<BR>
     *  　@　@カラム：　@１）で取得したカラムモデル<BR>
     *  　@　@値：　@引数.口座登録日<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号
     * @@param l_strAccountOpenDate - (口座登録日)<BR>
     * 口座登録日
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void setAccountOpenDate(int l_intLineNumber, String l_strAccountOpenDate)
    {
        final String STR_METHOD_NAME = " setAccountOpenDate(int, String)";
        log.entering(STR_METHOD_NAME);

        //１）　@カラムモデル取得
        //this.getカラムモデル()にてCSVカラムモデルを取得する。
        //[getカラムモデル()に指定する引数]
        //項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.口座開設日ラベル
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.accountOpenDate);

        //２）　@値セット
        //this.set項目値()にて項目値をセットする。
        //[set項目値()に指定する引数]
        //行番号：　@引数.行番号
        //カラム：　@１）で取得したカラムモデル
        //値：　@引数.口座登録日
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strAccountOpenDate);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set入金先銀行)<BR>
     * 「入金先銀行」をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *  　@[getカラムモデル()に指定する引数]<BR>
     *  　@　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.入金先銀行ラベル<BR>
     * <BR>
     *  ２）　@値セット<BR>
     *  　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *  　@[set項目値()に指定する引数]<BR>
     *  　@　@行番号：　@引数.行番号<BR>
     *  　@　@カラム：　@１）で取得したカラムモデル<BR>
     *  　@　@値：　@引数.銀行名<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号
     * @@param l_strFinInstitutionName - (銀行名)<BR>
     * 銀行名
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void setPayFinancialInstitution(int l_intLineNumber, String l_strFinInstitutionName)
    {
        final String STR_METHOD_NAME = " setPayFinancialInstitution(int, String)";
        log.entering(STR_METHOD_NAME);

        //１）　@カラムモデル取得
        //this.getカラムモデル()にてCSVカラムモデルを取得する。
        //[getカラムモデル()に指定する引数]
        //項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.入金先銀行ラベル
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.payFinancialInstitution);

        //２）　@値セット
        //this.set項目値()にて項目値をセットする。
        //[set項目値()に指定する引数]
        //行番号：　@引数.行番号
        //カラム：　@１）で取得したカラムモデル
        //値：　@引数.銀行名
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strFinInstitutionName);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set銀行支店名)<BR>
     * 「銀行支店名」をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *  　@[getカラムモデル()に指定する引数]<BR>
     *  　@　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.銀行支店名ラベル<BR>
     * <BR>
     *  ２）　@値セット<BR>
     *  　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *  　@[set項目値()に指定する引数]<BR>
     *  　@　@行番号：　@引数.行番号<BR>
     *  　@　@カラム：　@１）で取得したカラムモデル<BR>
     *  　@　@値：　@引数.支店名<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号
     * @@param l_strFinancialBranchName - (支店名)<BR>
     * 支店名
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void setFinancialBranchName(int l_intLineNumber, String l_strFinancialBranchName)
    {
        final String STR_METHOD_NAME = " setFinancialBranchName(int, String)";
        log.entering(STR_METHOD_NAME);

        //１）　@カラムモデル取得
        //this.getカラムモデル()にてCSVカラムモデルを取得する。
        //[getカラムモデル()に指定する引数]
        //項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.銀行支店名ラベル
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.financialBranchName);

        //２）　@値セット
        //this.set項目値()にて項目値をセットする。
        //[set項目値()に指定する引数]
        //行番号：　@引数.行番号
        //カラム：　@１）で取得したカラムモデル
        //値：　@引数.支店名
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strFinancialBranchName);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set科目)<BR>
     * 「科目」をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *  　@[getカラムモデル()に指定する引数]<BR>
     *  　@　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.科目ラベル<BR>
     * <BR>
     *  ２）　@値セット<BR>
     *  　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *  　@[set項目値()に指定する引数]<BR>
     *  　@　@行番号：　@引数.行番号<BR>
     *  　@　@カラム：　@１）で取得したカラムモデル<BR>
     *  　@　@値：　@引数.口座種類名<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号
     * @@param l_strItem - (科目)<BR>
     * 科目
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void setItem(int l_intLineNumber, String l_strItem)
    {
        final String STR_METHOD_NAME = " setItem(int, String)";
        log.entering(STR_METHOD_NAME);

        //１）　@カラムモデル取得
        //this.getカラムモデル()にてCSVカラムモデルを取得する。
        //[getカラムモデル()に指定する引数]
        //項目ラベル：　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.科目ラベル
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.item);

        //２）　@値セット
        //this.set項目値()にて項目値をセットする。
        //[set項目値()に指定する引数]
        //行番号：　@引数.行番号
        //カラム：　@１）で取得したカラムモデル
        //値：　@引数.口座種類名
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strItem);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set銀行番号)<BR>
     * 「銀行番号」をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *  　@[getカラムモデル()に指定する引数]<BR>
     *  　@　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.銀行番号ラベル<BR>
     * <BR>
     *  ２）　@値セット<BR>
     *  　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *  　@[set項目値()に指定する引数]<BR>
     *  　@　@行番号：　@引数.行番号<BR>
     *  　@　@カラム：　@１）で取得したカラムモデル<BR>
     *  　@　@値：　@引数.銀行コード<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号
     * @@param l_strFinInstitutionCode - (銀行コード)<BR>
     * 銀行コード
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void setFinancialInstitutionNumber(int l_intLineNumber, String l_strFinInstitutionCode)
    {
        final String STR_METHOD_NAME = " setFinancialInstitutionNumber(int, String)";
        log.entering(STR_METHOD_NAME);

        //１）　@カラムモデル取得
        //this.getカラムモデル()にてCSVカラムモデルを取得する。
        //[getカラムモデル()に指定する引数]
        //項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.銀行番号ラベル
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.financialInstitutionNumber);

        //２）　@値セット
        //this.set項目値()にて項目値をセットする。
        //[set項目値()に指定する引数]
        //行番号：　@引数.行番号
        //カラム：　@１）で取得したカラムモデル
        //値：　@引数.銀行コード
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strFinInstitutionCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set銀行支店番号)<BR>
     * 「銀行支店番号」をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *  　@[getカラムモデル()に指定する引数]<BR>
     *  　@　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.銀行支店番号ラベル<BR>
     * <BR>
     *  ２）　@値セット<BR>
     *  　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *  　@[set項目値()に指定する引数]<BR>
     *  　@　@行番号：　@引数.行番号<BR>
     *  　@　@カラム：　@１）で取得したカラムモデル<BR>
     *  　@　@値：　@引数.支店コード<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号
     * @@param l_strFinancialBranchCode - (支店コード)<BR>
     * 支店コード
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void setFinancialBranchCode(int l_intLineNumber, String l_strFinancialBranchCode)
    {
        final String STR_METHOD_NAME = " setFinancialBranchCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //１）　@カラムモデル取得
        //this.getカラムモデル()にてCSVカラムモデルを取得する。
        //[getカラムモデル()に指定する引数]
        //項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.銀行支店番号ラベル
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.financialBranchCode);

        //２）　@値セット
        //this.set項目値()にて項目値をセットする。
        //[set項目値()に指定する引数]
        //行番号：　@引数.行番号
        //カラム：　@１）で取得したカラムモデル
        //値：　@引数.支店コード
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strFinancialBranchCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set銀行口座番号)<BR>
     * 「銀行口座番号」をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *  　@[getカラムモデル()に指定する引数]<BR>
     *  　@　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.銀行口座番号ラベル<BR>
     * <BR>
     *  ２）　@値セット<BR>
     *  　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *  　@[set項目値()に指定する引数]<BR>
     *  　@　@行番号：　@引数.行番号<BR>
     *  　@　@カラム：　@１）で取得したカラムモデル<BR>
     *  　@　@値：　@引数.口座番号<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号
     * @@param l_strFinancialAccountCode - (口座番号)<BR>
     * 口座番号
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void setFinancialAccountCode(int l_intLineNumber, String l_strFinancialAccountCode)
    {
        final String STR_METHOD_NAME = " setFinancialAccountCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //１）　@カラムモデル取得
        //this.getカラムモデル()にてCSVカラムモデルを取得する。
        //[getカラムモデル()に指定する引数]
        //項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.銀行口座番号ラベル
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.financialAccountCode);

        //２）　@値セット
        //this.set項目値()にて項目値をセットする。
        //[set項目値()に指定する引数]
        //行番号：　@引数.行番号
        //カラム：　@１）で取得したカラムモデル
        //値：　@引数.口座番号
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strFinancialAccountCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set顧客住所（郵便番号）)<BR>
     * 「顧客住所（郵便番号）」をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *  　@[getカラムモデル()に指定する引数]<BR>
     *  　@　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.顧客住所（郵便番号）ラベル<BR>
     * <BR>
     *  ２）　@値セット<BR>
     *  　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *  　@[set項目値()に指定する引数]<BR>
     *  　@　@行番号：　@引数.行番号<BR>
     *  　@　@カラム：　@１）で取得したカラムモデル<BR>
     *  　@　@値：　@引数.郵便番号<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号
     * @@param l_strZipCode - (郵便番号)<BR>
     * 郵便番号
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void setZipCode(int l_intLineNumber, String l_strZipCode)
    {
        final String STR_METHOD_NAME = " setZipCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //１）　@カラムモデル取得
        //this.getカラムモデル()にてCSVカラムモデルを取得する。
        //[getカラムモデル()に指定する引数]
        //項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.顧客住所（郵便番号）ラベル
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.zipCode);

        //２）　@値セット
        //this.set項目値()にて項目値をセットする。
        //[set項目値()に指定する引数]
        //行番号：　@引数.行番号
        //カラム：　@１）で取得したカラムモデル
        //値：　@引数.郵便番号
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strZipCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set顧客住所（住所1）)<BR>
     * 「顧客住所（住所1）」をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *  　@[getカラムモデル()に指定する引数]<BR>
     *  　@　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.顧客住所（住所1）ラベル<BR>
     * <BR>
     *  ２）　@値セット<BR>
     *  　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *  　@[set項目値()に指定する引数]<BR>
     *  　@　@行番号：　@引数.行番号<BR>
     *  　@　@カラム：　@１）で取得したカラムモデル<BR>
     *  　@　@値：　@引数.住所1<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号
     * @@param l_strAddressLine1 - (住所1)<BR>
     * 住所1
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void setAddress1(int l_intLineNumber, String l_strAddressLine1)
    {
        final String STR_METHOD_NAME = " setAddress1(int, String)";
        log.entering(STR_METHOD_NAME);

        //１）　@カラムモデル取得
        //this.getカラムモデル()にてCSVカラムモデルを取得する。
        //[getカラムモデル()に指定する引数]
        //項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.顧客住所（住所1）ラベル
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.address1);

        //２）　@値セット
        //this.set項目値()にて項目値をセットする。
        //[set項目値()に指定する引数]
        //行番号：　@引数.行番号
        //カラム：　@１）で取得したカラムモデル
        //値：　@引数.住所1
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strAddressLine1);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set顧客住所（住所2）)<BR>
     * 「顧客住所（住所2）」をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *  　@[getカラムモデル()に指定する引数]<BR>
     *  　@　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.顧客住所（住所2）ラベル<BR>
     * <BR>
     *  ２）　@値セット<BR>
     *  　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *  　@[set項目値()に指定する引数]<BR>
     *  　@　@行番号：　@引数.行番号<BR>
     *  　@　@カラム：　@１）で取得したカラムモデル<BR>
     *  　@　@値：　@引数.住所2<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号
     * @@param l_strAddressLine2 - (住所2)<BR>
     * 住所2
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void setAddress2(int l_intLineNumber, String l_strAddressLine2)
    {
        final String STR_METHOD_NAME = " setAddress2(int, String)";
        log.entering(STR_METHOD_NAME);

        //１）　@カラムモデル取得
        //this.getカラムモデル()にてCSVカラムモデルを取得する。
        //[getカラムモデル()に指定する引数]
        //項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.顧客住所（住所2）ラベル
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.address2);

        //２）　@値セット
        //this.set項目値()にて項目値をセットする。
        //[set項目値()に指定する引数]
        //行番号：　@引数.行番号
        //カラム：　@１）で取得したカラムモデル
        //値：　@引数.住所2
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strAddressLine2);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set顧客住所（住所3）)<BR>
     * 「顧客住所（住所3）」をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *  　@[getカラムモデル()に指定する引数]<BR>
     *  　@　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.顧客住所（住所3）ラベル<BR>
     * <BR>
     *  ２）　@値セット<BR>
     *  　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *  　@[set項目値()に指定する引数]<BR>
     *  　@　@行番号：　@引数.行番号<BR>
     *  　@　@カラム：　@１）で取得したカラムモデル<BR>
     *  　@　@値：　@引数.住所3<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号
     * @@param l_strAddressLine3 - (住所3)<BR>
     * 住所3
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void setAddress3(int l_intLineNumber, String l_strAddressLine3)
    {
        final String STR_METHOD_NAME = " setAddress3(int, String)";
        log.entering(STR_METHOD_NAME);

        //１）　@カラムモデル取得
        //this.getカラムモデル()にてCSVカラムモデルを取得する。
        //[getカラムモデル()に指定する引数]
        //項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.顧客住所（住所3）ラベル
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.address3);

        //２）　@値セット
        //this.set項目値()にて項目値をセットする。
        //[set項目値()に指定する引数]
        //行番号：　@引数.行番号
        //カラム：　@１）で取得したカラムモデル
        //値：　@引数.住所3
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strAddressLine3);

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set電話番号)<BR>
     * 「電話番号」をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.電話番号ラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@　@行番号：　@引数.行番号<BR>
     * 　@　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@　@値：　@　@　@引数.電話番号<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号
     * @@param l_strTelephone - (電話番号)<BR>
     * 電話番号
     */
    public void setTelephone(int l_intLineNumber, String l_strTelephone)
    {
        final String STR_METHOD_NAME = " setTelephone(int, String)";
        log.entering(STR_METHOD_NAME);

        //１）　@カラムモデル取得
        //this.getカラムモデル()にてCSVカラムモデルを取得する。
        //[getカラムモデル()に指定する引数]
        //項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.電話番号ラベル
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.telephone);

        //２）　@値セット
        //this.set項目値()にて項目値をセットする。
        //[set項目値()に指定する引数]
        //行番号：　@引数.行番号
        //カラム：　@１）で取得したカラムモデル
        //値：　@引数.電話番号
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strTelephone);

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set部店名)<BR>
     * 「部店名」をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@　@項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.部店名ラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@　@行番号：　@引数.行番号<BR>
     * 　@　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@　@値：　@　@　@引数.部店名<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号
     * @@param l_strBranchName - (部店名)<BR>
     * 部店名
     */
    public void setBranchName(int l_intLineNumber, String l_strBranchName)
    {
        final String STR_METHOD_NAME = " setBranchName(int, String)";
        log.entering(STR_METHOD_NAME);

        //１）　@カラムモデル取得
        //this.getカラムモデル()にてCSVカラムモデルを取得する。
        //[getカラムモデル()に指定する引数]
        //項目ラベル：　@新規口座開設・口座移管・ログインロック顧客ダウンロードCSV.部店名ラベル
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.branchName);

        //２）　@値セット
        //this.set項目値()にて項目値をセットする。
        //[set項目値()に指定する引数]
        //行番号：　@引数.行番号
        //カラム：　@１）で取得したカラムモデル
        //値：　@引数.部店名
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strBranchName);

        log.exiting(STR_METHOD_NAME);
    }
}
@
