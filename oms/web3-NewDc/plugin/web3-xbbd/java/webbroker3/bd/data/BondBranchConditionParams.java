head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	BondBranchConditionParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.*;

/** 
 * bond_branch_conditionテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link BondBranchConditionRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link BondBranchConditionParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link BondBranchConditionParams}が{@@link BondBranchConditionRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BondBranchConditionPK 
 * @@see BondBranchConditionRow 
 */
public class BondBranchConditionParams extends Params implements BondBranchConditionRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "bond_branch_condition";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = BondBranchConditionRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return BondBranchConditionRow.TYPE;
  }


  /** 
   * <em>branch_id</em>カラムの値 
   */
  public  long  branch_id;

  /** 
   * <em>enforce_div</em>カラムの値 
   */
  public  String  enforce_div;

  /** 
   * <em>asset_check_div</em>カラムの値 
   */
  public  String  asset_check_div;

  /** 
   * <em>order_lock_use_div</em>カラムの値 
   */
  public  String  order_lock_use_div;

  /** 
   * <em>payment_date_set_div</em>カラムの値 
   */
  public  String  payment_date_set_div;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>branch_recruit_limit_div</em>カラムの値 
   */
  public  String  branch_recruit_limit_div;

  private boolean branch_id_is_set = false;
  private boolean branch_id_is_modified = false;
  private boolean enforce_div_is_set = false;
  private boolean enforce_div_is_modified = false;
  private boolean asset_check_div_is_set = false;
  private boolean asset_check_div_is_modified = false;
  private boolean order_lock_use_div_is_set = false;
  private boolean order_lock_use_div_is_modified = false;
  private boolean payment_date_set_div_is_set = false;
  private boolean payment_date_set_div_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean branch_recruit_limit_div_is_set = false;
  private boolean branch_recruit_limit_div_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "branch_id=" + branch_id
      + "," + "enforce_div=" +enforce_div
      + "," + "asset_check_div=" +asset_check_div
      + "," + "order_lock_use_div=" +order_lock_use_div
      + "," + "payment_date_set_div=" +payment_date_set_div
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "branch_recruit_limit_div=" +branch_recruit_limit_div
      + "}";
  }


  /** 
   * 値が未設定のBondBranchConditionParamsオブジェクトを作成します。 
   */
  public BondBranchConditionParams() {
    branch_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のBondBranchConditionRowオブジェクトの値を利用してBondBranchConditionParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するBondBranchConditionRowオブジェクト 
   */
  public BondBranchConditionParams( BondBranchConditionRow p_row )
  {
    branch_id = p_row.getBranchId();
    branch_id_is_set = p_row.getBranchIdIsSet();
    branch_id_is_modified = p_row.getBranchIdIsModified();
    enforce_div = p_row.getEnforceDiv();
    enforce_div_is_set = p_row.getEnforceDivIsSet();
    enforce_div_is_modified = p_row.getEnforceDivIsModified();
    asset_check_div = p_row.getAssetCheckDiv();
    asset_check_div_is_set = p_row.getAssetCheckDivIsSet();
    asset_check_div_is_modified = p_row.getAssetCheckDivIsModified();
    order_lock_use_div = p_row.getOrderLockUseDiv();
    order_lock_use_div_is_set = p_row.getOrderLockUseDivIsSet();
    order_lock_use_div_is_modified = p_row.getOrderLockUseDivIsModified();
    payment_date_set_div = p_row.getPaymentDateSetDiv();
    payment_date_set_div_is_set = p_row.getPaymentDateSetDivIsSet();
    payment_date_set_div_is_modified = p_row.getPaymentDateSetDivIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    branch_recruit_limit_div = p_row.getBranchRecruitLimitDiv();
    branch_recruit_limit_div_is_set = p_row.getBranchRecruitLimitDivIsSet();
    branch_recruit_limit_div_is_modified = p_row.getBranchRecruitLimitDivIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      enforce_div_is_set = true;
      enforce_div_is_modified = true;
      asset_check_div_is_set = true;
      asset_check_div_is_modified = true;
      order_lock_use_div_is_set = true;
      order_lock_use_div_is_modified = true;
      payment_date_set_div_is_set = true;
      payment_date_set_div_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      branch_recruit_limit_div_is_set = true;
      branch_recruit_limit_div_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof BondBranchConditionRow ) )
       return false;
    return fieldsEqual( (BondBranchConditionRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のBondBranchConditionRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( BondBranchConditionRow row )
  {
    if ( branch_id != row.getBranchId() )
      return false;
    if ( enforce_div == null ) {
      if ( row.getEnforceDiv() != null )
        return false;
    } else if ( !enforce_div.equals( row.getEnforceDiv() ) ) {
        return false;
    }
    if ( asset_check_div == null ) {
      if ( row.getAssetCheckDiv() != null )
        return false;
    } else if ( !asset_check_div.equals( row.getAssetCheckDiv() ) ) {
        return false;
    }
    if ( order_lock_use_div == null ) {
      if ( row.getOrderLockUseDiv() != null )
        return false;
    } else if ( !order_lock_use_div.equals( row.getOrderLockUseDiv() ) ) {
        return false;
    }
    if ( payment_date_set_div == null ) {
      if ( row.getPaymentDateSetDiv() != null )
        return false;
    } else if ( !payment_date_set_div.equals( row.getPaymentDateSetDiv() ) ) {
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
    if ( branch_recruit_limit_div == null ) {
      if ( row.getBranchRecruitLimitDiv() != null )
        return false;
    } else if ( !branch_recruit_limit_div.equals( row.getBranchRecruitLimitDiv() ) ) {
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
        + (enforce_div!=null? enforce_div.hashCode(): 0) 
        + (asset_check_div!=null? asset_check_div.hashCode(): 0) 
        + (order_lock_use_div!=null? order_lock_use_div.hashCode(): 0) 
        + (payment_date_set_div!=null? payment_date_set_div.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (branch_recruit_limit_div!=null? branch_recruit_limit_div.hashCode(): 0) 
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
		if ( enforce_div != null )
			map.put("enforce_div",enforce_div);
		if ( asset_check_div != null )
			map.put("asset_check_div",asset_check_div);
		if ( order_lock_use_div != null )
			map.put("order_lock_use_div",order_lock_use_div);
		if ( payment_date_set_div != null )
			map.put("payment_date_set_div",payment_date_set_div);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( branch_recruit_limit_div != null )
			map.put("branch_recruit_limit_div",branch_recruit_limit_div);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( enforce_div_is_modified )
			map.put("enforce_div",enforce_div);
		if ( asset_check_div_is_modified )
			map.put("asset_check_div",asset_check_div);
		if ( order_lock_use_div_is_modified )
			map.put("order_lock_use_div",order_lock_use_div);
		if ( payment_date_set_div_is_modified )
			map.put("payment_date_set_div",payment_date_set_div);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( branch_recruit_limit_div_is_modified )
			map.put("branch_recruit_limit_div",branch_recruit_limit_div);
		if (map.size() == 0) {
			map.put("enforce_div",enforce_div);
			map.put("asset_check_div",asset_check_div);
			map.put("order_lock_use_div",order_lock_use_div);
			map.put("payment_date_set_div",payment_date_set_div);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("branch_recruit_limit_div",branch_recruit_limit_div);
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
   * <em>enforce_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getEnforceDiv()
  {
    return enforce_div;
  }


  /** 
   * <em>enforce_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEnforceDivIsSet() {
    return enforce_div_is_set;
  }


  /** 
   * <em>enforce_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEnforceDivIsModified() {
    return enforce_div_is_modified;
  }


  /** 
   * <em>asset_check_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAssetCheckDiv()
  {
    return asset_check_div;
  }


  /** 
   * <em>asset_check_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAssetCheckDivIsSet() {
    return asset_check_div_is_set;
  }


  /** 
   * <em>asset_check_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAssetCheckDivIsModified() {
    return asset_check_div_is_modified;
  }


  /** 
   * <em>order_lock_use_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderLockUseDiv()
  {
    return order_lock_use_div;
  }


  /** 
   * <em>order_lock_use_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderLockUseDivIsSet() {
    return order_lock_use_div_is_set;
  }


  /** 
   * <em>order_lock_use_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderLockUseDivIsModified() {
    return order_lock_use_div_is_modified;
  }


  /** 
   * <em>payment_date_set_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPaymentDateSetDiv()
  {
    return payment_date_set_div;
  }


  /** 
   * <em>payment_date_set_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentDateSetDivIsSet() {
    return payment_date_set_div_is_set;
  }


  /** 
   * <em>payment_date_set_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentDateSetDivIsModified() {
    return payment_date_set_div_is_modified;
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
   * <em>branch_recruit_limit_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBranchRecruitLimitDiv()
  {
    return branch_recruit_limit_div;
  }


  /** 
   * <em>branch_recruit_limit_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchRecruitLimitDivIsSet() {
    return branch_recruit_limit_div_is_set;
  }


  /** 
   * <em>branch_recruit_limit_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchRecruitLimitDivIsModified() {
    return branch_recruit_limit_div_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new BondBranchConditionPK(branch_id);
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
   * <em>enforce_div</em>カラムの値を設定します。 
   *
   * @@param p_enforceDiv <em>enforce_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setEnforceDiv( String p_enforceDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    enforce_div = p_enforceDiv;
    enforce_div_is_set = true;
    enforce_div_is_modified = true;
  }


  /** 
   * <em>asset_check_div</em>カラムの値を設定します。 
   *
   * @@param p_assetCheckDiv <em>asset_check_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAssetCheckDiv( String p_assetCheckDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    asset_check_div = p_assetCheckDiv;
    asset_check_div_is_set = true;
    asset_check_div_is_modified = true;
  }


  /** 
   * <em>order_lock_use_div</em>カラムの値を設定します。 
   *
   * @@param p_orderLockUseDiv <em>order_lock_use_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOrderLockUseDiv( String p_orderLockUseDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_lock_use_div = p_orderLockUseDiv;
    order_lock_use_div_is_set = true;
    order_lock_use_div_is_modified = true;
  }


  /** 
   * <em>payment_date_set_div</em>カラムの値を設定します。 
   *
   * @@param p_paymentDateSetDiv <em>payment_date_set_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPaymentDateSetDiv( String p_paymentDateSetDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_date_set_div = p_paymentDateSetDiv;
    payment_date_set_div_is_set = true;
    payment_date_set_div_is_modified = true;
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
   * <em>branch_recruit_limit_div</em>カラムの値を設定します。 
   *
   * @@param p_branchRecruitLimitDiv <em>branch_recruit_limit_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setBranchRecruitLimitDiv( String p_branchRecruitLimitDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_recruit_limit_div = p_branchRecruitLimitDiv;
    branch_recruit_limit_div_is_set = true;
    branch_recruit_limit_div_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("asset_check_div") ) {
                    return this.asset_check_div;
                }
                break;
            case 'b':
                if ( name.equals("branch_id") ) {
                    return new Long( branch_id );
                }
                else if ( name.equals("branch_recruit_limit_div") ) {
                    return this.branch_recruit_limit_div;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("enforce_div") ) {
                    return this.enforce_div;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'o':
                if ( name.equals("order_lock_use_div") ) {
                    return this.order_lock_use_div;
                }
                break;
            case 'p':
                if ( name.equals("payment_date_set_div") ) {
                    return this.payment_date_set_div;
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
            case 'a':
                if ( name.equals("asset_check_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'asset_check_div' must be String: '"+value+"' is not." );
                    this.asset_check_div = (String) value;
                    if (this.asset_check_div_is_set)
                        this.asset_check_div_is_modified = true;
                    this.asset_check_div_is_set = true;
                    return;
                }
                break;
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
                else if ( name.equals("branch_recruit_limit_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'branch_recruit_limit_div' must be String: '"+value+"' is not." );
                    this.branch_recruit_limit_div = (String) value;
                    if (this.branch_recruit_limit_div_is_set)
                        this.branch_recruit_limit_div_is_modified = true;
                    this.branch_recruit_limit_div_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    if (this.created_timestamp_is_set)
                        this.created_timestamp_is_modified = true;
                    this.created_timestamp_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("enforce_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'enforce_div' must be String: '"+value+"' is not." );
                    this.enforce_div = (String) value;
                    if (this.enforce_div_is_set)
                        this.enforce_div_is_modified = true;
                    this.enforce_div_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.last_updated_timestamp_is_set)
                        this.last_updated_timestamp_is_modified = true;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("order_lock_use_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_lock_use_div' must be String: '"+value+"' is not." );
                    this.order_lock_use_div = (String) value;
                    if (this.order_lock_use_div_is_set)
                        this.order_lock_use_div_is_modified = true;
                    this.order_lock_use_div_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("payment_date_set_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'payment_date_set_div' must be String: '"+value+"' is not." );
                    this.payment_date_set_div = (String) value;
                    if (this.payment_date_set_div_is_set)
                        this.payment_date_set_div_is_modified = true;
                    this.payment_date_set_div_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
