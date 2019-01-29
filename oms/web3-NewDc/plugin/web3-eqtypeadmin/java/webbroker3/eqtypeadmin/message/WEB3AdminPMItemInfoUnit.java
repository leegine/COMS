head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMItemInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 項目区分情報 (WEB3AdminPMItemInfoUnit.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.util.WEB3LogUtility;

/**
 * (項目区分情報)<BR>
 * <BR>
 * 項目区分情報クラス<BR>
 * <BR>
 * WEB3AdminPMItemInfoUnit<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminPMItemInfoUnit extends Message
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMItemInfoUnit.class);

   /**
    * （大項目区分）<BR>
    * <BR>
    * 大項目区分<BR>
    * <BR>
    * ※定義値についてはDBレイアウト<BR>
    * 　@「株式銘柄条件設定テーブル仕様.xls」参照<BR>
    * <BR>
    * ----<English>--------------------<BR>
    * <BR>
    * largeItemDiv<BR>
    * <BR>
    * ※Refer to the DB layout "eqtype_product_condition_table.xls" about defined
    * values<BR>
    * <BR>
    */
   public String largeItemDiv;

   /**
    * （小項目区分一覧）<BR>
    * <BR>
    * 大項目区分に属する小項目区分の一覧<BR>
    * ※大項目区分に対応する小項目区分配列としてセット。<BR>
    * <BR>
    * ※定義値についてはDBレイアウト<BR>
    * 　@「株式銘柄条件設定テーブル仕様.xls」参照<BR>
    * <BR>
    * ----<English>--------------------<BR>
    * <BR>
    * smallItemDivList<BR>
    * <BR>
    * List of smallItemDiv that belongs to largeItemDiv<BR>
    * ※Set as an array of smallItemDiv corresponding to largeItemDiv<BR>
    * <BR>
    * ※Refer to the DB layout "eqtype_product_condition_table.xls" about defined
    * values<BR>
    * <BR>
    */
   public String[] smallItemDivList;

   /**
    * （項目区分情報）<BR>
    * <BR>
    * 引数の大項目区分、小項目区分一覧を自身のプロパティに
    * セットするコンストラクタ。<BR>
    * <BR>
    * ----<English>--------------------<BR>
    * <BR>
    * Constructor that sets largeItemDiv and smallItemDiv list into its own
    * property<BR>
    * <BR>
    * @@param l_largeItemDiv - （大項目区分）<BR>
    * <BR>
    * 大項目区分<BR>
    * <BR>
    * ※定義値についてはDBレイアウト<BR>
    * 　@「株式銘柄条件設定テーブル仕様.xls」参照<BR>
    * <BR>
    * ----<English>---------<BR>
    * <BR>
    * l_largeItemDiv<BR>
    * <BR>
    * ※Refer to the DB layout "eqtype_product_condition_table.xls" about defined
    * values<BR>
    * <BR>
    * @@param l_strSmallItemDivList - （小項目区分一覧）<BR>
    * <BR>
    * 小項目区分一覧<BR>
    * <BR>
    * ※定義値についてはDBレイアウト<BR>
    * 　@「株式銘柄条件設定テーブル仕様.xls」参照<BR>
    * <BR>
    * ----<English>-------<BR>
    * <BR>
    * l_strSmallItemDivList<BR>
    * <BR>
    * ※Refer to the DB layout "eqtype_product_condition_table.xls" about defined
    * values<BR>
    * <BR>
    * @@roseuid 41980A0A019D
    */
   public WEB3AdminPMItemInfoUnit(String l_largeItemDiv, String[] l_strSmallItemDivList)
   {
        this.largeItemDiv = l_largeItemDiv;
        this.smallItemDivList = l_strSmallItemDivList;
   }

   /**
    * (項目区分情報)<BR>
    * <BR>
    * コンストラクタ<BR>
    * <BR>
    * Constructor<BR>
    * <BR>
    * @@roseuid 41944EBA00EC
    */
   public WEB3AdminPMItemInfoUnit()
   {

   }
}
@
