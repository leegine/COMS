head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.27.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	InstBranchProductParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * inst_branch_productテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link InstBranchProductRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link InstBranchProductParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link InstBranchProductParams}が{@@link InstBranchProductRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see InstBranchProductPK 
 * @@see InstBranchProductRow 
 */
public class InstBranchProductParams extends Params implements InstBranchProductRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "inst_branch_product";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = InstBranchProductRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return InstBranchProductRow.TYPE;
  }


  /** 
   * <em>branch_id</em>カラムの値 
   */
  public  long  branch_id;

  /** 
   * <em>commission_product_code</em>カラムの値 
   */
  public  String  commission_product_code;

  /** 
   * <em>commission_fee_cond_flag</em>カラムの値 
   */
  public  String  commission_fee_cond_flag;

  /** 
   * <em>estimate_price_calc_form</em>カラムの値 
   */
  public  Integer  estimate_price_calc_form;

  /** 
   * <em>premium_restraint_rate</em>カラムの値 
   */
  public  Double  premium_restraint_rate;

  /** 
   * <em>discount_restraint_rate</em>カラムの値 
   */
  public  Double  discount_restraint_rate;

  /** 
   * <em>cancel_price_restraint_rate</em>カラムの値 
   */
  public  Double  cancel_price_restraint_rate;

  /** 
   * <em>commission_lump_adjust_amount</em>カラムの値 
   */
  public  Double  commission_lump_adjust_amount;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean branch_id_is_set = false;
  private boolean branch_id_is_modified = false;
  private boolean commission_product_code_is_set = false;
  private boolean commission_product_code_is_modified = false;
  private boolean commission_fee_cond_flag_is_set = false;
  private boolean commission_fee_cond_flag_is_modified = false;
  private boolean estimate_price_calc_form_is_set = false;
  private boolean estimate_price_calc_form_is_modified = false;
  private boolean premium_restraint_rate_is_set = false;
  private boolean premium_restraint_rate_is_modified = false;
  private boolean discount_restraint_rate_is_set = false;
  private boolean discount_restraint_rate_is_modified = false;
  private boolean cancel_price_restraint_rate_is_set = false;
  private boolean cancel_price_restraint_rate_is_modified = false;
  private boolean commission_lump_adjust_amount_is_set = false;
  private boolean commission_lump_adjust_amount_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "branch_id=" + branch_id
      + "," + "commission_product_code=" + commission_product_code
      + "," + "commission_fee_cond_flag=" +commission_fee_cond_flag
      + "," + "estimate_price_calc_form=" +estimate_price_calc_form
      + "," + "premium_restraint_rate=" +premium_restraint_rate
      + "," + "discount_restraint_rate=" +discount_restraint_rate
      + "," + "cancel_price_restraint_rate=" +cancel_price_restraint_rate
      + "," + "commission_lump_adjust_amount=" +commission_lump_adjust_amount
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のInstBranchProductParamsオブジェクトを作成します。 
   */
  public InstBranchProductParams() {
    branch_id_is_modified = true;
    commission_product_code_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のInstBranchProductRowオブジェクトの値を利用してInstBranchProductParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するInstBranchProductRowオブジェクト 
   */
  public InstBranchProductParams( InstBranchProductRow p_row )
  {
    branch_id = p_row.getBranchId();
    branch_id_is_set = p_row.getBranchIdIsSet();
    branch_id_is_modified = p_row.getBranchIdIsModified();
    commission_product_code = p_row.getCommissionProductCode();
    commission_product_code_is_set = p_row.getCommissionProductCodeIsSet();
    commission_product_code_is_modified = p_row.getCommissionProductCodeIsModified();
    commission_fee_cond_flag = p_row.getCommissionFeeCondFlag();
    commission_fee_cond_flag_is_set = p_row.getCommissionFeeCondFlagIsSet();
    commission_fee_cond_flag_is_modified = p_row.getCommissionFeeCondFlagIsModified();
    if ( !p_row.getEstimatePriceCalcFormIsNull() )
      estimate_price_calc_form = new Integer( p_row.getEstimatePriceCalcForm() );
    estimate_price_calc_form_is_set = p_row.getEstimatePriceCalcFormIsSet();
    estimate_price_calc_form_is_modified = p_row.getEstimatePriceCalcFormIsModified();
    if ( !p_row.getPremiumRestraintRateIsNull() )
      premium_restraint_rate = new Double( p_row.getPremiumRestraintRate() );
    premium_restraint_rate_is_set = p_row.getPremiumRestraintRateIsSet();
    premium_restraint_rate_is_modified = p_row.getPremiumRestraintRateIsModified();
    if ( !p_row.getDiscountRestraintRateIsNull() )
      discount_restraint_rate = new Double( p_row.getDiscountRestraintRate() );
    discount_restraint_rate_is_set = p_row.getDiscountRestraintRateIsSet();
    discount_restraint_rate_is_modified = p_row.getDiscountRestraintRateIsModified();
    if ( !p_row.getCancelPriceRestraintRateIsNull() )
      cancel_price_restraint_rate = new Double( p_row.getCancelPriceRestraintRate() );
    cancel_price_restraint_rate_is_set = p_row.getCancelPriceRestraintRateIsSet();
    cancel_price_restraint_rate_is_modified = p_row.getCancelPriceRestraintRateIsModified();
    if ( !p_row.getCommissionLumpAdjustAmountIsNull() )
      commission_lump_adjust_amount = new Double( p_row.getCommissionLumpAdjustAmount() );
    commission_lump_adjust_amount_is_set = p_row.getCommissionLumpAdjustAmountIsSet();
    commission_lump_adjust_amount_is_modified = p_row.getCommissionLumpAdjustAmountIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      commission_fee_cond_flag_is_set = true;
      commission_fee_cond_flag_is_modified = true;
      estimate_price_calc_form_is_set = true;
      estimate_price_calc_form_is_modified = true;
      premium_restraint_rate_is_set = true;
      premium_restraint_rate_is_modified = true;
      discount_restraint_rate_is_set = true;
      discount_restraint_rate_is_modified = true;
      cancel_price_restraint_rate_is_set = true;
      cancel_price_restraint_rate_is_modified = true;
      commission_lump_adjust_amount_is_set = true;
      commission_lump_adjust_amount_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof InstBranchProductRow ) )
       return false;
    return fieldsEqual( (InstBranchProductRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のInstBranchProductRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( InstBranchProductRow row )
  {
    if ( branch_id != row.getBranchId() )
      return false;
    if ( commission_product_code == null ) {
      if ( row.getCommissionProductCode() != null )
        return false;
    } else if ( !commission_product_code.equals( row.getCommissionProductCode() ) ) {
        return false;
    }
    if ( commission_fee_cond_flag == null ) {
      if ( row.getCommissionFeeCondFlag() != null )
        return false;
    } else if ( !commission_fee_cond_flag.equals( row.getCommissionFeeCondFlag() ) ) {
        return false;
    }
    if ( estimate_price_calc_form == null ) {
      if ( !row.getEstimatePriceCalcFormIsNull() )
        return false;
    } else if ( row.getEstimatePriceCalcFormIsNull() || ( estimate_price_calc_form.intValue() != row.getEstimatePriceCalcForm() ) ) {
        return false;
    }
    if ( premium_restraint_rate == null ) {
      if ( !row.getPremiumRestraintRateIsNull() )
        return false;
    } else if ( row.getPremiumRestraintRateIsNull() || ( premium_restraint_rate.doubleValue() != row.getPremiumRestraintRate() ) ) {
        return false;
    }
    if ( discount_restraint_rate == null ) {
      if ( !row.getDiscountRestraintRateIsNull() )
        return false;
    } else if ( row.getDiscountRestraintRateIsNull() || ( discount_restraint_rate.doubleValue() != row.getDiscountRestraintRate() ) ) {
        return false;
    }
    if ( cancel_price_restraint_rate == null ) {
      if ( !row.getCancelPriceRestraintRateIsNull() )
        return false;
    } else if ( row.getCancelPriceRestraintRateIsNull() || ( cancel_price_restraint_rate.doubleValue() != row.getCancelPriceRestraintRate() ) ) {
        return false;
    }
    if ( commission_lump_adjust_amount == null ) {
      if ( !row.getCommissionLumpAdjustAmountIsNull() )
        return false;
    } else if ( row.getCommissionLumpAdjustAmountIsNull() || ( commission_lump_adjust_amount.doubleValue() != row.getCommissionLumpAdjustAmount() ) ) {
        return false;
    }
    if ( created_timestamp == null ) {
      if ( row.getCreatedTimestamp() != null )
        return false;
    } else if ( !created_timestamp.equals( row.getCreatedTimestamp() ) ) {
        return false;
    }
    if ( last_updated_timestamp == null ) {
      if ( row.getLastUpdatedTimestamp() != null )
        return false;
    } else if ( !last_updated_timestamp.equals( row.getLastUpdatedTimestamp() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  ((int) branch_id)
        + (commission_product_code!=null? commission_product_code.hashCode(): 0) 
        + (commission_fee_cond_flag!=null? commission_fee_cond_flag.hashCode(): 0) 
        + (estimate_price_calc_form!=null? estimate_price_calc_form.hashCode(): 0) 
        + (premium_restraint_rate!=null? premium_restraint_rate.hashCode(): 0) 
        + (discount_restraint_rate!=null? discount_restraint_rate.hashCode(): 0) 
        + (cancel_price_restraint_rate!=null? cancel_price_restraint_rate.hashCode(): 0) 
        + (commission_lump_adjust_amount!=null? commission_lump_adjust_amount.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("branch_id",new Long(branch_id));
		map.put("commission_product_code",commission_product_code);
		if ( commission_fee_cond_flag != null )
			map.put("commission_fee_cond_flag",commission_fee_cond_flag);
		if ( estimate_price_calc_form != null )
			map.put("estimate_price_calc_form",estimate_price_calc_form);
		if ( premium_restraint_rate != null )
			map.put("premium_restraint_rate",premium_restraint_rate);
		if ( discount_restraint_rate != null )
			map.put("discount_restraint_rate",discount_restraint_rate);
		if ( cancel_price_restraint_rate != null )
			map.put("cancel_price_restraint_rate",cancel_price_restraint_rate);
		if ( commission_lump_adjust_amount != null )
			map.put("commission_lump_adjust_amount",commission_lump_adjust_amount);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( commission_fee_cond_flag_is_modified )
			map.put("commission_fee_cond_flag",commission_fee_cond_flag);
		if ( estimate_price_calc_form_is_modified )
			map.put("estimate_price_calc_form",estimate_price_calc_form);
		if ( premium_restraint_rate_is_modified )
			map.put("premium_restraint_rate",premium_restraint_rate);
		if ( discount_restraint_rate_is_modified )
			map.put("discount_restraint_rate",discount_restraint_rate);
		if ( cancel_price_restraint_rate_is_modified )
			map.put("cancel_price_restraint_rate",cancel_price_restraint_rate);
		if ( commission_lump_adjust_amount_is_modified )
			map.put("commission_lump_adjust_amount",commission_lump_adjust_amount);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("commission_fee_cond_flag",commission_fee_cond_flag);
			map.put("estimate_price_calc_form",estimate_price_calc_form);
			map.put("premium_restraint_rate",premium_restraint_rate);
			map.put("discount_restraint_rate",discount_restraint_rate);
			map.put("cancel_price_restraint_rate",cancel_price_restraint_rate);
			map.put("commission_lump_adjust_amount",commission_lump_adjust_amount);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>branch_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getBranchId()
  {
    return branch_id;
  }


  /** 
   * <em>branch_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchIdIsSet() {
    return branch_id_is_set;
  }


  /** 
   * <em>branch_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchIdIsModified() {
    return branch_id_is_modified;
  }


  /** 
   * <em>commission_product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCommissionProductCode()
  {
    return commission_product_code;
  }


  /** 
   * <em>commission_product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionProductCodeIsSet() {
    return commission_product_code_is_set;
  }


  /** 
   * <em>commission_product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionProductCodeIsModified() {
    return commission_product_code_is_modified;
  }


  /** 
   * <em>commission_fee_cond_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCommissionFeeCondFlag()
  {
    return commission_fee_cond_flag;
  }


  /** 
   * <em>commission_fee_cond_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionFeeCondFlagIsSet() {
    return commission_fee_cond_flag_is_set;
  }


  /** 
   * <em>commission_fee_cond_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionFeeCondFlagIsModified() {
    return commission_fee_cond_flag_is_modified;
  }


  /** 
   * <em>estimate_price_calc_form</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getEstimatePriceCalcForm()
  {
    return ( estimate_price_calc_form==null? ((int)0): estimate_price_calc_form.intValue() );
  }


  /** 
   * <em>estimate_price_calc_form</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getEstimatePriceCalcFormIsNull()
  {
    return estimate_price_calc_form == null;
  }


  /** 
   * <em>estimate_price_calc_form</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEstimatePriceCalcFormIsSet() {
    return estimate_price_calc_form_is_set;
  }


  /** 
   * <em>estimate_price_calc_form</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEstimatePriceCalcFormIsModified() {
    return estimate_price_calc_form_is_modified;
  }


  /** 
   * <em>premium_restraint_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPremiumRestraintRate()
  {
    return ( premium_restraint_rate==null? ((double)0): premium_restraint_rate.doubleValue() );
  }


  /** 
   * <em>premium_restraint_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPremiumRestraintRateIsNull()
  {
    return premium_restraint_rate == null;
  }


  /** 
   * <em>premium_restraint_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPremiumRestraintRateIsSet() {
    return premium_restraint_rate_is_set;
  }


  /** 
   * <em>premium_restraint_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPremiumRestraintRateIsModified() {
    return premium_restraint_rate_is_modified;
  }


  /** 
   * <em>discount_restraint_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getDiscountRestraintRate()
  {
    return ( discount_restraint_rate==null? ((double)0): discount_restraint_rate.doubleValue() );
  }


  /** 
   * <em>discount_restraint_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getDiscountRestraintRateIsNull()
  {
    return discount_restraint_rate == null;
  }


  /** 
   * <em>discount_restraint_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDiscountRestraintRateIsSet() {
    return discount_restraint_rate_is_set;
  }


  /** 
   * <em>discount_restraint_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDiscountRestraintRateIsModified() {
    return discount_restraint_rate_is_modified;
  }


  /** 
   * <em>cancel_price_restraint_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCancelPriceRestraintRate()
  {
    return ( cancel_price_restraint_rate==null? ((double)0): cancel_price_restraint_rate.doubleValue() );
  }


  /** 
   * <em>cancel_price_restraint_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCancelPriceRestraintRateIsNull()
  {
    return cancel_price_restraint_rate == null;
  }


  /** 
   * <em>cancel_price_restraint_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCancelPriceRestraintRateIsSet() {
    return cancel_price_restraint_rate_is_set;
  }


  /** 
   * <em>cancel_price_restraint_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCancelPriceRestraintRateIsModified() {
    return cancel_price_restraint_rate_is_modified;
  }


  /** 
   * <em>commission_lump_adjust_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCommissionLumpAdjustAmount()
  {
    return ( commission_lump_adjust_amount==null? ((double)0): commission_lump_adjust_amount.doubleValue() );
  }


  /** 
   * <em>commission_lump_adjust_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCommissionLumpAdjustAmountIsNull()
  {
    return commission_lump_adjust_amount == null;
  }


  /** 
   * <em>commission_lump_adjust_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionLumpAdjustAmountIsSet() {
    return commission_lump_adjust_amount_is_set;
  }


  /** 
   * <em>commission_lump_adjust_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionLumpAdjustAmountIsModified() {
    return commission_lump_adjust_amount_is_modified;
  }


  /** 
   * <em>created_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getCreatedTimestamp()
  {
    return created_timestamp;
  }


  /** 
   * <em>created_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreatedTimestampIsSet() {
    return created_timestamp_is_set;
  }


  /** 
   * <em>created_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreatedTimestampIsModified() {
    return created_timestamp_is_modified;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getLastUpdatedTimestamp()
  {
    return last_updated_timestamp;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdatedTimestampIsSet() {
    return last_updated_timestamp_is_set;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdatedTimestampIsModified() {
    return last_updated_timestamp_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new InstBranchProductPK(branch_id, commission_product_code);
  }


  /** 
   * <em>branch_id</em>カラムの値を設定します。 
   *
   * @@param p_branchId <em>branch_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setBranchId( long p_branchId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_id = p_branchId;
    branch_id_is_set = true;
    branch_id_is_modified = true;
  }


  /** 
   * <em>commission_product_code</em>カラムの値を設定します。 
   *
   * @@param p_commissionProductCode <em>commission_product_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setCommissionProductCode( String p_commissionProductCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commission_product_code = p_commissionProductCode;
    commission_product_code_is_set = true;
    commission_product_code_is_modified = true;
  }


  /** 
   * <em>commission_fee_cond_flag</em>カラムの値を設定します。 
   *
   * @@param p_commissionFeeCondFlag <em>commission_fee_cond_flag</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCommissionFeeCondFlag( String p_commissionFeeCondFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commission_fee_cond_flag = p_commissionFeeCondFlag;
    commission_fee_cond_flag_is_set = true;
    commission_fee_cond_flag_is_modified = true;
  }


  /** 
   * <em>estimate_price_calc_form</em>カラムの値を設定します。 
   *
   * @@param p_estimatePriceCalcForm <em>estimate_price_calc_form</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setEstimatePriceCalcForm( int p_estimatePriceCalcForm )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    estimate_price_calc_form = new Integer(p_estimatePriceCalcForm);
    estimate_price_calc_form_is_set = true;
    estimate_price_calc_form_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>estimate_price_calc_form</em>カラムに値を設定します。 
   */
  public final void setEstimatePriceCalcForm( Integer p_estimatePriceCalcForm )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    estimate_price_calc_form = p_estimatePriceCalcForm;
    estimate_price_calc_form_is_set = true;
    estimate_price_calc_form_is_modified = true;
  }


  /** 
   * <em>premium_restraint_rate</em>カラムの値を設定します。 
   *
   * @@param p_premiumRestraintRate <em>premium_restraint_rate</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPremiumRestraintRate( double p_premiumRestraintRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    premium_restraint_rate = new Double(p_premiumRestraintRate);
    premium_restraint_rate_is_set = true;
    premium_restraint_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>premium_restraint_rate</em>カラムに値を設定します。 
   */
  public final void setPremiumRestraintRate( Double p_premiumRestraintRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    premium_restraint_rate = p_premiumRestraintRate;
    premium_restraint_rate_is_set = true;
    premium_restraint_rate_is_modified = true;
  }


  /** 
   * <em>discount_restraint_rate</em>カラムの値を設定します。 
   *
   * @@param p_discountRestraintRate <em>discount_restraint_rate</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDiscountRestraintRate( double p_discountRestraintRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    discount_restraint_rate = new Double(p_discountRestraintRate);
    discount_restraint_rate_is_set = true;
    discount_restraint_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>discount_restraint_rate</em>カラムに値を設定します。 
   */
  public final void setDiscountRestraintRate( Double p_discountRestraintRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    discount_restraint_rate = p_discountRestraintRate;
    discount_restraint_rate_is_set = true;
    discount_restraint_rate_is_modified = true;
  }


  /** 
   * <em>cancel_price_restraint_rate</em>カラムの値を設定します。 
   *
   * @@param p_cancelPriceRestraintRate <em>cancel_price_restraint_rate</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCancelPriceRestraintRate( double p_cancelPriceRestraintRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cancel_price_restraint_rate = new Double(p_cancelPriceRestraintRate);
    cancel_price_restraint_rate_is_set = true;
    cancel_price_restraint_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>cancel_price_restraint_rate</em>カラムに値を設定します。 
   */
  public final void setCancelPriceRestraintRate( Double p_cancelPriceRestraintRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    cancel_price_restraint_rate = p_cancelPriceRestraintRate;
    cancel_price_restraint_rate_is_set = true;
    cancel_price_restraint_rate_is_modified = true;
  }


  /** 
   * <em>commission_lump_adjust_amount</em>カラムの値を設定します。 
   *
   * @@param p_commissionLumpAdjustAmount <em>commission_lump_adjust_amount</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCommissionLumpAdjustAmount( double p_commissionLumpAdjustAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commission_lump_adjust_amount = new Double(p_commissionLumpAdjustAmount);
    commission_lump_adjust_amount_is_set = true;
    commission_lump_adjust_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>commission_lump_adjust_amount</em>カラムに値を設定します。 
   */
  public final void setCommissionLumpAdjustAmount( Double p_commissionLumpAdjustAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    commission_lump_adjust_amount = p_commissionLumpAdjustAmount;
    commission_lump_adjust_amount_is_set = true;
    commission_lump_adjust_amount_is_modified = true;
  }


  /** 
   * <em>created_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_createdTimestamp <em>created_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setCreatedTimestamp( java.sql.Timestamp p_createdTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = p_createdTimestamp;
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>created_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setCreatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_lastUpdatedTimestamp <em>last_updated_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setLastUpdatedTimestamp( java.sql.Timestamp p_lastUpdatedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = p_lastUpdatedTimestamp;
    last_updated_timestamp_is_set = true;
    last_updated_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>last_updated_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setLastUpdatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    last_updated_timestamp_is_set = true;
    last_updated_timestamp_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'b':
                if ( name.equals("branch_id") ) {
                    return new Long( branch_id );
                }
                break;
            case 'c':
                if ( name.equals("commission_product_code") ) {
                    return this.commission_product_code;
                }
                else if ( name.equals("commission_fee_cond_flag") ) {
                    return this.commission_fee_cond_flag;
                }
                else if ( name.equals("cancel_price_restraint_rate") ) {
                    return this.cancel_price_restraint_rate;
                }
                else if ( name.equals("commission_lump_adjust_amount") ) {
                    return this.commission_lump_adjust_amount;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("discount_restraint_rate") ) {
                    return this.discount_restraint_rate;
                }
                break;
            case 'e':
                if ( name.equals("estimate_price_calc_form") ) {
                    return this.estimate_price_calc_form;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'p':
                if ( name.equals("premium_restraint_rate") ) {
                    return this.premium_restraint_rate;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }


  /** 
   * @@see com.fitechlabs.dbind.Params#setColumn(String, Object) 
   */
    public void setColumn( String name, Object value ) {
        if(!mutable())
            throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'b':
                if ( name.equals("branch_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'branch_id' must be Long: '"+value+"' is not." );
                    this.branch_id = ((Long) value).longValue();
                    if (this.branch_id_is_set)
                        this.branch_id_is_modified = true;
                    this.branch_id_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("commission_product_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'commission_product_code' must be String: '"+value+"' is not." );
                    this.commission_product_code = (String) value;
                    if (this.commission_product_code_is_set)
                        this.commission_product_code_is_modified = true;
                    this.commission_product_code_is_set = true;
                    return;
                }
                else if ( name.equals("commission_fee_cond_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'commission_fee_cond_flag' must be String: '"+value+"' is not." );
                    this.commission_fee_cond_flag = (String) value;
                    if (this.commission_fee_cond_flag_is_set)
                        this.commission_fee_cond_flag_is_modified = true;
                    this.commission_fee_cond_flag_is_set = true;
                    return;
                }
                else if ( name.equals("cancel_price_restraint_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cancel_price_restraint_rate' must be Double: '"+value+"' is not." );
                    this.cancel_price_restraint_rate = (Double) value;
                    if (this.cancel_price_restraint_rate_is_set)
                        this.cancel_price_restraint_rate_is_modified = true;
                    this.cancel_price_restraint_rate_is_set = true;
                    return;
                }
                else if ( name.equals("commission_lump_adjust_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'commission_lump_adjust_amount' must be Double: '"+value+"' is not." );
                    this.commission_lump_adjust_amount = (Double) value;
                    if (this.commission_lump_adjust_amount_is_set)
                        this.commission_lump_adjust_amount_is_modified = true;
                    this.commission_lump_adjust_amount_is_set = true;
                    return;
                }
                else if ( name.equals("created_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    if (this.created_timestamp_is_set)
                        this.created_timestamp_is_modified = true;
                    this.created_timestamp_is_set = true;
                    return;
                }
                break;
            case 'd':
                if ( name.equals("discount_restraint_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'discount_restraint_rate' must be Double: '"+value+"' is not." );
                    this.discount_restraint_rate = (Double) value;
                    if (this.discount_restraint_rate_is_set)
                        this.discount_restraint_rate_is_modified = true;
                    this.discount_restraint_rate_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("estimate_price_calc_form") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'estimate_price_calc_form' must be Integer: '"+value+"' is not." );
                    this.estimate_price_calc_form = (Integer) value;
                    if (this.estimate_price_calc_form_is_set)
                        this.estimate_price_calc_form_is_modified = true;
                    this.estimate_price_calc_form_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.last_updated_timestamp_is_set)
                        this.last_updated_timestamp_is_modified = true;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("premium_restraint_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'premium_restraint_rate' must be Double: '"+value+"' is not." );
                    this.premium_restraint_rate = (Double) value;
                    if (this.premium_restraint_rate_is_set)
                        this.premium_restraint_rate_is_modified = true;
                    this.premium_restraint_rate_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
