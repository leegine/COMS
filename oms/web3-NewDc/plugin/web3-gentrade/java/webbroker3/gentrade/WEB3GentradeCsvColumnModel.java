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
filename	WEB3GentradeCsvColumnModel.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : CSVカラムモデル(WEB3GentradeCsvColumnModel.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/07/28 石 炎(中訊) 新規作成
*/

package webbroker3.gentrade;

import java.text.DateFormat;

/**
 * (CSVカラムモデル) <BR>
 * （WEB3CsvColumnModel）<BR>
 * <BR>
 * CVSファ@イルの列（カラム）を表現するクラス。<BR>
 * CVSデータモデルクラスにて使用する。<BR>
 */
public class WEB3GentradeCsvColumnModel
{

    /**
     * (項目型_文字列) <BR>
     * 定数定義プロパティ <BR>
     * <BR>
     * 文字列型（java.lang.String）を示すint値を定義する。 <BR>
     */
    public static int STRINGTYPE = 0;

    /**
     * (項目型_数値)（int） <BR>
     * 定数定義プロパティ <BR>
     * <BR>
     * 数値型（java.lang.Integer）を示すint値を定義する。<BR>
     */
    public static int INTEGERTYPE = 10;

    /**
     * (項目型_数値)（long） <BR>
     * 定数定義プロパティ <BR>
     * <BR>
     * 数値型（java.lang.Long）を示すint値を定義する。<BR>
     */
    public static int LONGTYPE = 11;

    /**
     * (項目型_数値)（double） <BR>
     * 定数定義プロパティ <BR>
     * <BR>
     * 数値型（java.lang.Double）を示すint値を定義する。 <BR>
     */
    public static int DOUBLETYPE = 12;

    /**
     * (項目型_日付) <BR>
     * 定数定義プロパティ <BR>
     * <BR>
     * 日付型（java.util.Date）を示すint値を定義する。<BR>
     */
    public static int DATETYPE = 20;

    /**
     * (項目型_日付時間) <BR>
     * 定数定義プロパティ <BR>
     * <BR>
     * 日付時間型（java.util.Date、java.sql.Timestamp）を<BR>
     * 示すint値を定義する。<BR>
     */
    public static int TIMESTAMPTYPE = 21;

    /**
     * (項目ラベル) <BR>
     * 対象カラムのタイトル文字列<BR>
     */
    private String columnLabel;

    /**
     * (カラム番号) <BR>
     * 対象カラムのカラム番号。<BR>
     * <BR>
     * ※　@0 Originにてセットする。<BR>
     * ※　@CSVデータモデル.カラムヘッダ配列の要素番号と同じ値をセットする。<BR>
     */
    private int columnNumber;

    /**
     * (項目型) <BR>
     * 対象カラムのデータ型を示すフラグ <BR>
     * <BR>
     * ※　@以下の何れかをセットする。<BR>
     * 　@CSV列モデル.項目型_文字列 <BR>
     * 　@CSV列モデル.項目型_数値（int） <BR>
     * 　@CSV列モデル.項目型_数値（long） <BR>
     * 　@CSV列モデル.項目型_数値（double） <BR>
     * 　@CSV列モデル.項目型_日付 <BR>
     * 　@CSV列モデル.項目型_日付時間 <BR>
     */
    private int columnType;

    /**
     * (日付フォーマット) <BR>
     * 対象カラムのデータの項目型が「日付」、<BR>
     * または「日付時間」の場合に指定するDateFormat。<BR>
     * <BR>
     * ※　@項目型が「日付」、「日付時間」でない場合はnull。<BR>
     */
    private DateFormat dateFormat;

    /**
     * @@roseuid 41076DC702DE
     */
    public WEB3GentradeCsvColumnModel()
    {

    }

    /**
     * (CSV列モデル) <BR>
     * コンストラクタ。 <BR>
     * <BR>
     * インスタンスを生成し、引数の値をプロパティにセットする。<BR>
     * 生成したインスタンスを返却する。<BR>
     * @@param l_strColumnLabel - 項目ラベル <BR>
     * 対象カラムのタイトル文字列<BR>
     * @@param l_intColumnNumber - カラム番号 <BR>
     * 対象カラムのカラム番号。<BR>
     * <BR>
     * ※　@0 Originにてセットする。<BR>
     * ※　@CSVデータモデル.カラムヘッダ配列の要素番号と同じ値をセットする。<BR>
     * 
     * @@param l_intColumnType - 項目型 <BR>
     * 対象カラムのデータ型を示すフラグ <BR>
     * <BR>
     * ※　@以下の何れかの値を指定する。<BR>
     * 　@CSV列モデル.項目型_文字列 <BR>
     * 　@CSV列モデル.項目型_数値 <BR>
     * 　@CSV列モデル.項目型_日付 <BR>
     * 　@CSV列モデル.項目型_日付時間 <BR>
     * 
     * @@param l_dateFormat - 日付フォーマット <BR>
     * 対象カラムのデータの項目型が「日付」、<BR>
     * または「日付時間」の場合に指定するDateFormat。<BR>
     * <BR>
     * ※　@項目型が「日付」、「日付時間」でない場合はnullを指定する。<BR>
     * 
     * 
     * @@return webbroker3.業務ユーティリティ.WEB3CsvColumnModel
     * @@roseuid 40E2506A0047
     */
    public WEB3GentradeCsvColumnModel(
        String l_strColumnLabel,
        int l_intColumnNumber,
        int l_intColumnType,
        DateFormat l_dateFormat)
    {
        this.columnLabel = l_strColumnLabel;
        this.columnNumber = l_intColumnNumber;
        this.columnType = l_intColumnType;
        this.dateFormat = l_dateFormat;
    }

    /**
     * (get項目ラベル) <BR>
     * this.項目ラベルを返却する。<BR>
     * @@return java.lang.String
     * @@roseuid 40E249FF00B4
     */
    public String getColumnLabel()
    {
        return this.columnLabel;
    }

    /**
     * (getカラム番号) <BR>
     * this.カラム番号を返却する。<BR>
     * @@return int
     * @@roseuid 40E2501E017F
     */
    public int getColumnNumber()
    {
        return this.columnNumber;
    }

    /**
     * (get項目型) <BR>
     * this.項目型を返却する。<BR>
     * @@return int
     * @@roseuid 40E2503D02E7
     */
    public int getColumnType()
    {
        return this.columnType;
    }

    /**
     * (get日付フォーマット) <BR>
     * this.日付フォーマットを返却する。<BR>
     * @@return java.text.DateFormat
     * @@roseuid 40E2504E02F6
     */
    public DateFormat getDateFormat()
    {
        return this.dateFormat;
    }
}
@
