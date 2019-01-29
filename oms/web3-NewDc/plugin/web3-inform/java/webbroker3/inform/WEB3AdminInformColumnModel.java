head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.53.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformColumnModel.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 連絡情報カラムモデルクラス(WEB3AdminInformColumnModel.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/01/21 凌建平(中訊) 作成
*/

package webbroker3.inform;

import java.text.DateFormat;

import webbroker3.gentrade.WEB3GentradeCsvColumnModel;

/**
 * (連絡情報カラムモデル)<BR>
 * 連絡情報カラムモデルクラス<BR>
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3AdminInformColumnModel extends WEB3GentradeCsvColumnModel 
{
    /**
     * (入力項目物理名)<BR>
     * 入力項目物理名
     */
    private String inputItemSymbolName;
    
    /**
     * (連結項目デリミッタ)<BR>
     * 連結項目デリミッタ
     */
    private String catDelimitter;
    
    /**
     * (セクション番号)<BR>
     * セクション番号
     */
    private String sectionNumber;
    
    /**
     * @@roseuid 41EE642D0271
     */
    public WEB3AdminInformColumnModel() 
    {
     
    }
    
    /**
     * (連絡情報カラムモデル)<BR>
     * コンストラクタ<BR>
     * <BR>
     * １）super()をコールする。<BR>
     * <BR>
     *   [super()の引数] <BR>
     *   項目ラベル： 引数.項目ラベル <BR>
     *   カラム番号： 引数.カラム番号 <BR>
     *   項目型： 引数.項目型 <BR>
     *   日付フォーマット： 引数.日付フォーマット <BR>
     * <BR>
     * ２）引数.入力項目物理名、引数.連結項目デリミッタ、<BR>
     * 引数.セクション番号を自身のプロパティにセットする。
     * @@param l_strItemLevel - (項目ラベル)<BR>
     * 項目ラベル<BR>
     * 
     * @@param l_intColumn - (カラム番号)<BR>
     * カラム番号<BR>
     * 
     * @@param l_intItemType - (項目型)<BR>
     * 項目型<BR>
     * 
     * @@param l_dateFormat - (日付フォーマット)<BR>
     * 日付フォーマット<BR>
     * 
     * @@param l_strInputItemSymbolName - (入力項目物理名)<BR>
     * 入力項目物理名
     * 
     * @@param l_strConnectItemDelimiter - (連結項目デリミッタ)<BR>
     * 連結項目デリミッタ
     * 
     * @@param l_strSectionNumber - (セクション番号)<BR>
     * セクション番号
     * @@roseuid 41BED30A03A7
     */
    public WEB3AdminInformColumnModel(
        String l_strItemLevel, 
        int l_intColumn, 
        int l_intItemType, 
        DateFormat l_dateFormat, 
        String l_strInputItemSymbolName, 
        String l_strConnectItemDelimiter, 
        String l_strSectionNumber) 
    {
        //１）super()をコールする。<BR>
        super(l_strItemLevel,
            l_intColumn,
            l_intItemType,
            l_dateFormat);
                    
        //２）引数.入力項目物理名を自身のプロパティにセットする
        this.inputItemSymbolName = l_strInputItemSymbolName;

        // 引数.連結項目デリミッタを自身のプロパティにセットする
        this.catDelimitter = l_strConnectItemDelimiter;

        // 引数.セクション番号を自身のプロパティにセットする
        this.sectionNumber = l_strSectionNumber;
    }
    
    /**
     * (get入力項目物理名)<BR>
     * 入力項目物理名を取得する。<BR>
     * <BR>
     * this.入力項目物理名を返却する。<BR>
     * @@return String
     * @@roseuid 41BED4310155
     */
    public String getInputItemSymbolName() 
    {
        return this.inputItemSymbolName;
    }
    
    /**
     * (get連結項目デリミッタ)<BR>
     * 連結項目デリミッタを取得する。<BR>
     * <BR>
     * this.連結項目デリミッタを返却する。<BR>
     * @@return String
     * @@roseuid 41BED4390397
     */
    public String getCatDelimitter() 
    {
        return this.catDelimitter;
    }
    
    /**
     * (getセクション番号)<BR>
     * セクション番号を取得する。<BR>
     * <BR>
     * this.セクション番号を返却する。<BR>
     * @@return String
     * @@roseuid 41BED4470099
     */
    public String getSectionNumber() 
    {
        return this.sectionNumber;
    }
}
@
