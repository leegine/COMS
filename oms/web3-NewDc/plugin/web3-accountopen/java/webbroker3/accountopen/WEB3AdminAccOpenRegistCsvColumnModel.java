head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.30.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenRegistCsvColumnModel.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設申込カラムモデル(WEB3AdminAccOpenRegistCsvColumnModel.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 鄭海良(中訊) 新規作成
*/

package webbroker3.accountopen;

import java.text.DateFormat;

import webbroker3.gentrade.WEB3GentradeCsvColumnModel;


/**
 * (口座開設申込カラムモデル)<BR>
 * 口座開設申込カラムモデル<BR>
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminAccOpenRegistCsvColumnModel extends WEB3GentradeCsvColumnModel 
{
    
    /**
     * (入力項目物理名)<BR>
     * 入力項目物理名<BR>
     */
    private String inputItemSymbolName;
    
    /**
     * (連結項目デリミッタ)<BR>
     * 連結項目デリミッタ<BR>
     * <BR>
     * 0：なし<BR>
     * 1：半角SPACE<BR>
     * 2：全角SPACE<BR>
     * 3：ハイフン（’-’）<BR>
     */
    private String catDelimitter;
    
    /**
     * (セクション番号)<BR>
     * セクション番号<BR>
     * <BR>
     * ※デリミッタで分割したセクションの何番目を出力するかを、0から指定する。<BR>
     */
    private String sectionNo;
    
    /**
     * @@roseuid 41B45E6F02DE
     */
    public WEB3AdminAccOpenRegistCsvColumnModel() 
    {
     
    }
    
    /**
     * (口座開設申込カラムモデル)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * １）　@super()をコールする<BR>
     * <BR>
     * 　@[super()の引数]<BR>
     * 　@項目ラベル：　@項目ラベル<BR>
     * 　@カラム番号： カラム番号<BR>
     * 　@項目型：　@項目型<BR>
     * 　@日付フォーマット：　@日付フォーマット<BR>
     * <BR>
     * ２）　@入力項目物理名，連結項目デリミッタ，セクション番号を自身のプロパティにセットする。<BR>
     * @@param l_strColumnLabel - 対象カラムのタイトル文字列
     * @@param l_intColumnNumber - 対象カラムのカラム番号。<BR>
     * <BR>
     * ※　@0 Originにてセットする。<BR>
     * ※　@CSVデータモデル.カラムヘッダ配列の要素番号と同じ値をセットする。<BR>
     * 
     * @@param l_intColumnType - 対象カラムのデータ型を示すフラグ<BR>
     * <BR>
     * ※　@以下の何れかの値を指定する。<BR>
     * 　@CSV列モデル.項目型_文字列<BR>
     * 　@CSV列モデル.項目型_数値<BR>
     * 　@CSV列モデル.項目型_日付<BR>
     * 　@CSV列モデル.項目型_日付時間<BR>
     * 
     * @@param l_dateFormat - 対象カラムのデータの項目型が「日付」、または「日付時間」の場合に<BR>
     * 指定するDateFormat。<BR>
     * <BR>
     * ※　@項目型が「日付」、「日付時間」でない場合はnullを指定する。<BR>
     * <BR>
     * @@param l_strCatDelimitter - 連結項目デリミッタ<BR>
     * <BR>
     * 0：なし<BR>
     * 1：半角SPACE<BR>
     * 2：全角SPACE<BR>
     * 3：ハイフン（’-’）<BR>
     * <BR>
     * @@param l_strSectionNo - セクション番号<BR>
     *<BR>
     * ※デリミッタで分割したセクションの何番目を出力するかを、0から指定する。<BR>
     * <BR>
     * @@param l_strInputItemSymbolName - 入力項目物理名
     * @@return webbroker3.accountopen.WEB3AdminAccOpenRegistCsvColumnModel
     * @@roseuid 41A14CDF0039
     */
    public WEB3AdminAccOpenRegistCsvColumnModel(String l_strColumnLabel, int l_intColumnNumber, int l_intColumnType, DateFormat l_dateFormat, String l_strInputItemSymbolName, String l_strCatDelimitter, String l_strSectionNo) 
    {
        //１）　@super()をコールする
        super(l_strColumnLabel, l_intColumnNumber, l_intColumnType, l_dateFormat);
        
        //２）　@入力項目物理名を自身のプロパティにセットする
        this.inputItemSymbolName = l_strInputItemSymbolName;
        this.catDelimitter = l_strCatDelimitter;
        this.sectionNo = l_strSectionNo;
    }
    
    /**
     * (get入力項目物理名)<BR>
     * this.入力項目物理名を返却する。<BR>
     * @@return String
     * @@roseuid 41A14CB00172
     */
    public String getInputItemSymbolName() 
    {
        return this.inputItemSymbolName;
    }
    
    /**
     * (get連結項目デリミッタ)<BR>
     * this.連結項目デリミッタを取得する。<BR>
     * @@return String
     * @@roseuid 41A436380289
     */
    public String getCatDelimitter() 
    {
        return this.catDelimitter;
    }
    
    /**
     * (getセクション番号)<BR>
     * this.セクション番号を返却する。<BR>
     * @@return int
     * @@roseuid 41A4365E00E3
     */
    public String getSectionNo() 
    {
        return this.sectionNo;
    }
}
@
