head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MutualFund2ndProductSonarRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * MutualFund2ndProductSonarRowインタフェースは変更不可でリードオンリーである<em>mutual_fund_2nd_product_sonar</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link MutualFund2ndProductSonarRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MutualFund2ndProductSonarPK 
 */
public interface MutualFund2ndProductSonarRow extends Row {


  /** 
   * この{@@link MutualFund2ndProductSonarRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "mutual_fund_2nd_product_sonar", "master" );


  /** 
   * <em>product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getProductCode();


  /** 
   * <em>product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductCodeIsSet();


  /** 
   * <em>product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductCodeIsModified();


  /** 
   * <em>institution_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInstitutionCode();


  /** 
   * <em>institution_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInstitutionCodeIsSet();


  /** 
   * <em>institution_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInstitutionCodeIsModified();


  /** 
   * <em>work_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getWorkDate();


  /** 
   * <em>work_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getWorkDateIsSet();


  /** 
   * <em>work_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getWorkDateIsModified();


  /** 
   * <em>reg_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getRegDate();


  /** 
   * <em>reg_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRegDateIsSet();


  /** 
   * <em>reg_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRegDateIsModified();


  /** 
   * <em>last_update_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getLastUpdateDate();


  /** 
   * <em>last_update_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastUpdateDateIsSet();


  /** 
   * <em>last_update_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastUpdateDateIsModified();


  /** 
   * <em>reg_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRegDiv();


  /** 
   * <em>reg_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRegDivIsSet();


  /** 
   * <em>reg_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRegDivIsModified();


  /** 
   * <em>product_name_kana</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getProductNameKana();


  /** 
   * <em>product_name_kana</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductNameKanaIsSet();


  /** 
   * <em>product_name_kana</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductNameKanaIsModified();


  /** 
   * <em>product_name_kanji</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getProductNameKanji();


  /** 
   * <em>product_name_kanji</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductNameKanjiIsSet();


  /** 
   * <em>product_name_kanji</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductNameKanjiIsModified();


  /** 
   * <em>recruit_unit</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getRecruitUnit();


  /** 
   * <em>recruit_unit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getRecruitUnitIsNull();


  /** 
   * <em>recruit_unit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitUnitIsSet();


  /** 
   * <em>recruit_unit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitUnitIsModified();


  /** 
   * <em>dealing_unit_qty</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getDealingUnitQty();


  /** 
   * <em>dealing_unit_qty</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getDealingUnitQtyIsNull();


  /** 
   * <em>dealing_unit_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDealingUnitQtyIsSet();


  /** 
   * <em>dealing_unit_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDealingUnitQtyIsModified();


  /** 
   * <em>recruit_min_qty</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getRecruitMinQty();


  /** 
   * <em>recruit_min_qty</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getRecruitMinQtyIsNull();


  /** 
   * <em>recruit_min_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitMinQtyIsSet();


  /** 
   * <em>recruit_min_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitMinQtyIsModified();


  /** 
   * <em>buy_min_qty</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getBuyMinQty();


  /** 
   * <em>buy_min_qty</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getBuyMinQtyIsNull();


  /** 
   * <em>buy_min_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyMinQtyIsSet();


  /** 
   * <em>buy_min_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyMinQtyIsModified();


  /** 
   * <em>sell_min_qty</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getSellMinQty();


  /** 
   * <em>sell_min_qty</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSellMinQtyIsNull();


  /** 
   * <em>sell_min_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellMinQtyIsSet();


  /** 
   * <em>sell_min_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellMinQtyIsModified();


  /** 
   * <em>swt_min_qty</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getSwtMinQty();


  /** 
   * <em>swt_min_qty</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSwtMinQtyIsNull();


  /** 
   * <em>swt_min_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSwtMinQtyIsSet();


  /** 
   * <em>swt_min_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSwtMinQtyIsModified();


  /** 
   * <em>recruit_unit_qty</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getRecruitUnitQty();


  /** 
   * <em>recruit_unit_qty</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getRecruitUnitQtyIsNull();


  /** 
   * <em>recruit_unit_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitUnitQtyIsSet();


  /** 
   * <em>recruit_unit_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitUnitQtyIsModified();


  /** 
   * <em>buy_unit_qty</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getBuyUnitQty();


  /** 
   * <em>buy_unit_qty</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getBuyUnitQtyIsNull();


  /** 
   * <em>buy_unit_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyUnitQtyIsSet();


  /** 
   * <em>buy_unit_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyUnitQtyIsModified();


  /** 
   * <em>sell_unit_qty</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getSellUnitQty();


  /** 
   * <em>sell_unit_qty</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSellUnitQtyIsNull();


  /** 
   * <em>sell_unit_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellUnitQtyIsSet();


  /** 
   * <em>sell_unit_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellUnitQtyIsModified();


  /** 
   * <em>swt_unit_qty</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getSwtUnitQty();


  /** 
   * <em>swt_unit_qty</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSwtUnitQtyIsNull();


  /** 
   * <em>swt_unit_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSwtUnitQtyIsSet();


  /** 
   * <em>swt_unit_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSwtUnitQtyIsModified();


  /** 
   * <em>recruit_min_amt</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getRecruitMinAmt();


  /** 
   * <em>recruit_min_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getRecruitMinAmtIsNull();


  /** 
   * <em>recruit_min_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitMinAmtIsSet();


  /** 
   * <em>recruit_min_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitMinAmtIsModified();


  /** 
   * <em>buy_min_amt</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getBuyMinAmt();


  /** 
   * <em>buy_min_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getBuyMinAmtIsNull();


  /** 
   * <em>buy_min_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyMinAmtIsSet();


  /** 
   * <em>buy_min_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyMinAmtIsModified();


  /** 
   * <em>sell_min_amt</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getSellMinAmt();


  /** 
   * <em>sell_min_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSellMinAmtIsNull();


  /** 
   * <em>sell_min_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellMinAmtIsSet();


  /** 
   * <em>sell_min_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellMinAmtIsModified();


  /** 
   * <em>swt_min_amt</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getSwtMinAmt();


  /** 
   * <em>swt_min_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSwtMinAmtIsNull();


  /** 
   * <em>swt_min_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSwtMinAmtIsSet();


  /** 
   * <em>swt_min_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSwtMinAmtIsModified();


  /** 
   * <em>recruit_unit_amt</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getRecruitUnitAmt();


  /** 
   * <em>recruit_unit_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getRecruitUnitAmtIsNull();


  /** 
   * <em>recruit_unit_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitUnitAmtIsSet();


  /** 
   * <em>recruit_unit_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitUnitAmtIsModified();


  /** 
   * <em>buy_unit_amt</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getBuyUnitAmt();


  /** 
   * <em>buy_unit_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getBuyUnitAmtIsNull();


  /** 
   * <em>buy_unit_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyUnitAmtIsSet();


  /** 
   * <em>buy_unit_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyUnitAmtIsModified();


  /** 
   * <em>sell_unit_amt</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getSellUnitAmt();


  /** 
   * <em>sell_unit_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSellUnitAmtIsNull();


  /** 
   * <em>sell_unit_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellUnitAmtIsSet();


  /** 
   * <em>sell_unit_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellUnitAmtIsModified();


  /** 
   * <em>swt_unit_amt</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getSwtUnitAmt();


  /** 
   * <em>swt_unit_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSwtUnitAmtIsNull();


  /** 
   * <em>swt_unit_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSwtUnitAmtIsSet();


  /** 
   * <em>swt_unit_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSwtUnitAmtIsModified();


  /** 
   * <em>recruit_qty_spec_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRecruitQtySpecDiv();


  /** 
   * <em>recruit_qty_spec_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitQtySpecDivIsSet();


  /** 
   * <em>recruit_qty_spec_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitQtySpecDivIsModified();


  /** 
   * <em>recruit_amt_spec_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRecruitAmtSpecDiv();


  /** 
   * <em>recruit_amt_spec_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitAmtSpecDivIsSet();


  /** 
   * <em>recruit_amt_spec_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitAmtSpecDivIsModified();


  /** 
   * <em>buy_qty_spec_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBuyQtySpecDiv();


  /** 
   * <em>buy_qty_spec_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyQtySpecDivIsSet();


  /** 
   * <em>buy_qty_spec_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyQtySpecDivIsModified();


  /** 
   * <em>buy_amt_spec_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBuyAmtSpecDiv();


  /** 
   * <em>buy_amt_spec_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyAmtSpecDivIsSet();


  /** 
   * <em>buy_amt_spec_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyAmtSpecDivIsModified();


  /** 
   * <em>sell_qty_spec_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSellQtySpecDiv();


  /** 
   * <em>sell_qty_spec_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellQtySpecDivIsSet();


  /** 
   * <em>sell_qty_spec_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellQtySpecDivIsModified();


  /** 
   * <em>sell_amt_spec_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSellAmtSpecDiv();


  /** 
   * <em>sell_amt_spec_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellAmtSpecDivIsSet();


  /** 
   * <em>sell_amt_spec_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellAmtSpecDivIsModified();


  /** 
   * <em>swt_qty_spec_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSwtQtySpecDiv();


  /** 
   * <em>swt_qty_spec_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSwtQtySpecDivIsSet();


  /** 
   * <em>swt_qty_spec_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSwtQtySpecDivIsModified();


  /** 
   * <em>swt_amt_spec_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSwtAmtSpecDiv();


  /** 
   * <em>swt_amt_spec_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSwtAmtSpecDivIsSet();


  /** 
   * <em>swt_amt_spec_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSwtAmtSpecDivIsModified();


  /** 
   * <em>input_unit_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInputUnitDiv();


  /** 
   * <em>input_unit_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInputUnitDivIsSet();


  /** 
   * <em>input_unit_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInputUnitDivIsModified();


  /** 
   * <em>cal_price_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCalPriceDiv();


  /** 
   * <em>cal_price_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCalPriceDivIsSet();


  /** 
   * <em>cal_price_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCalPriceDivIsModified();


  /** 
   * <em>sell_exception_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSellExceptionDiv();


  /** 
   * <em>sell_exception_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellExceptionDivIsSet();


  /** 
   * <em>sell_exception_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellExceptionDivIsModified();


  /** 
   * <em>swt_trade_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSwtTradeDiv();


  /** 
   * <em>swt_trade_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSwtTradeDivIsSet();


  /** 
   * <em>swt_trade_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSwtTradeDivIsModified();


  /** 
   * <em>swt_group</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSwtGroup();


  /** 
   * <em>swt_group</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSwtGroupIsSet();


  /** 
   * <em>swt_group</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSwtGroupIsModified();


  /** 
   * <em>swt_companion_subject_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSwtCompanionSubjectDiv();


  /** 
   * <em>swt_companion_subject_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSwtCompanionSubjectDivIsSet();


  /** 
   * <em>swt_companion_subject_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSwtCompanionSubjectDivIsModified();


  /** 
   * <em>buy_disable_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBuyDisableDiv();


  /** 
   * <em>buy_disable_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyDisableDivIsSet();


  /** 
   * <em>buy_disable_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyDisableDivIsModified();


  /** 
   * <em>swt_perference_enable_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSwtPerferenceEnableDiv();


  /** 
   * <em>swt_perference_enable_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSwtPerferenceEnableDivIsSet();


  /** 
   * <em>swt_perference_enable_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSwtPerferenceEnableDivIsModified();


  /** 
   * <em>redemption_perference_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getRedemptionPerferencePrice();


  /** 
   * <em>redemption_perference_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getRedemptionPerferencePriceIsNull();


  /** 
   * <em>redemption_perference_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRedemptionPerferencePriceIsSet();


  /** 
   * <em>redemption_perference_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRedemptionPerferencePriceIsModified();


  /** 
   * <em>redemption_commission_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getRedemptionCommissionRate();


  /** 
   * <em>redemption_commission_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getRedemptionCommissionRateIsNull();


  /** 
   * <em>redemption_commission_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRedemptionCommissionRateIsSet();


  /** 
   * <em>redemption_commission_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRedemptionCommissionRateIsModified();


  /** 
   * <em>pre_redemption_begin_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getPreRedemptionBeginDate();


  /** 
   * <em>pre_redemption_begin_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPreRedemptionBeginDateIsSet();


  /** 
   * <em>pre_redemption_begin_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPreRedemptionBeginDateIsModified();


  /** 
   * <em>closing_date_cal_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getClosingDateCalDiv();


  /** 
   * <em>closing_date_cal_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getClosingDateCalDivIsSet();


  /** 
   * <em>closing_date_cal_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getClosingDateCalDivIsModified();


  /** 
   * <em>closing_spec_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getClosingSpecDate();


  /** 
   * <em>closing_spec_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getClosingSpecDateIsSet();


  /** 
   * <em>closing_spec_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getClosingSpecDateIsModified();


  /** 
   * <em>annual_profit_qty_times</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAnnualProfitQtyTimes();


  /** 
   * <em>annual_profit_qty_times</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAnnualProfitQtyTimesIsSet();


  /** 
   * <em>annual_profit_qty_times</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAnnualProfitQtyTimesIsModified();


  /** 
   * <em>sell_advance_order_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSellAdvanceOrderDiv();


  /** 
   * <em>sell_advance_order_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellAdvanceOrderDivIsSet();


  /** 
   * <em>sell_advance_order_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellAdvanceOrderDivIsModified();


  /** 
   * <em>buy_advance_order_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBuyAdvanceOrderDiv();


  /** 
   * <em>buy_advance_order_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyAdvanceOrderDivIsSet();


  /** 
   * <em>buy_advance_order_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyAdvanceOrderDivIsModified();


  /** 
   * <em>sell_undelivering_term</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSellUndeliveringTerm();


  /** 
   * <em>sell_undelivering_term</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellUndeliveringTermIsSet();


  /** 
   * <em>sell_undelivering_term</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellUndeliveringTermIsModified();


  /** 
   * <em>buy_undelivering_term</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBuyUndeliveringTerm();


  /** 
   * <em>buy_undelivering_term</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyUndeliveringTermIsSet();


  /** 
   * <em>buy_undelivering_term</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyUndeliveringTermIsModified();


  /** 
   * <em>recruit_stop_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRecruitStopDiv();


  /** 
   * <em>recruit_stop_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitStopDivIsSet();


  /** 
   * <em>recruit_stop_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitStopDivIsModified();


  /** 
   * <em>dealing_stop_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDealingStopDiv();


  /** 
   * <em>dealing_stop_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDealingStopDivIsSet();


  /** 
   * <em>dealing_stop_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDealingStopDivIsModified();


  /** 
   * <em>storage_stop_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStorageStopDiv();


  /** 
   * <em>storage_stop_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStorageStopDivIsSet();


  /** 
   * <em>storage_stop_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStorageStopDivIsModified();


  /** 
   * <em>specific_corpus_app</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSpecificCorpusApp();


  /** 
   * <em>specific_corpus_app</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpecificCorpusAppIsSet();


  /** 
   * <em>specific_corpus_app</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpecificCorpusAppIsModified();


  /** 
   * <em>perference_qualify</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPerferenceQualify();


  /** 
   * <em>perference_qualify</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPerferenceQualifyIsSet();


  /** 
   * <em>perference_qualify</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPerferenceQualifyIsModified();


  /** 
   * <em>collateral_qualify</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCollateralQualify();


  /** 
   * <em>collateral_qualify</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCollateralQualifyIsSet();


  /** 
   * <em>collateral_qualify</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCollateralQualifyIsModified();


  /** 
   * <em>operate_report_send_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOperateReportSendDiv();


  /** 
   * <em>operate_report_send_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOperateReportSendDivIsSet();


  /** 
   * <em>operate_report_send_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOperateReportSendDivIsModified();


  /** 
   * <em>operate_report_send_month1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOperateReportSendMonth1();


  /** 
   * <em>operate_report_send_month1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOperateReportSendMonth1IsSet();


  /** 
   * <em>operate_report_send_month1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOperateReportSendMonth1IsModified();


  /** 
   * <em>operate_report_send_month2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOperateReportSendMonth2();


  /** 
   * <em>operate_report_send_month2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOperateReportSendMonth2IsSet();


  /** 
   * <em>operate_report_send_month2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOperateReportSendMonth2IsModified();


  /** 
   * <em>biz_asset_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBizAssetCode();


  /** 
   * <em>biz_asset_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBizAssetCodeIsSet();


  /** 
   * <em>biz_asset_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBizAssetCodeIsModified();


  /** 
   * <em>biz_asset_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBizAssetName();


  /** 
   * <em>biz_asset_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBizAssetNameIsSet();


  /** 
   * <em>biz_asset_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBizAssetNameIsModified();


  /** 
   * <em>prospectus_conversion_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getProspectusConversionDate();


  /** 
   * <em>prospectus_conversion_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProspectusConversionDateIsSet();


  /** 
   * <em>prospectus_conversion_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProspectusConversionDateIsModified();


  /** 
   * <em>frgn_buy_min_amt</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getFrgnBuyMinAmt();


  /** 
   * <em>frgn_buy_min_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getFrgnBuyMinAmtIsNull();


  /** 
   * <em>frgn_buy_min_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFrgnBuyMinAmtIsSet();


  /** 
   * <em>frgn_buy_min_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFrgnBuyMinAmtIsModified();


  /** 
   * <em>frgn_sell_min_amt</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getFrgnSellMinAmt();


  /** 
   * <em>frgn_sell_min_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getFrgnSellMinAmtIsNull();


  /** 
   * <em>frgn_sell_min_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFrgnSellMinAmtIsSet();


  /** 
   * <em>frgn_sell_min_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFrgnSellMinAmtIsModified();


  /** 
   * <em>frgn_buy_unit_amt</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getFrgnBuyUnitAmt();


  /** 
   * <em>frgn_buy_unit_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getFrgnBuyUnitAmtIsNull();


  /** 
   * <em>frgn_buy_unit_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFrgnBuyUnitAmtIsSet();


  /** 
   * <em>frgn_buy_unit_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFrgnBuyUnitAmtIsModified();


  /** 
   * <em>frgn_sell_unit_amt</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getFrgnSellUnitAmt();


  /** 
   * <em>frgn_sell_unit_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getFrgnSellUnitAmtIsNull();


  /** 
   * <em>frgn_sell_unit_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFrgnSellUnitAmtIsSet();


  /** 
   * <em>frgn_sell_unit_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFrgnSellUnitAmtIsModified();


}
@
