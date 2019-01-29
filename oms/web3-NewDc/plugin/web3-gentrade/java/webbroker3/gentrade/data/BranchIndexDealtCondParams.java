head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.20.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	BranchIndexDealtCondParams.java;


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
 * branch_index_dealt_condテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link BranchIndexDealtCondRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link BranchIndexDealtCondParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link BranchIndexDealtCondParams}が{@@link BranchIndexDealtCondRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BranchIndexDealtCondPK 
 * @@see BranchIndexDealtCondRow 
 */
public class BranchIndexDealtCondParams extends Params implements BranchIndexDealtCondRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "branch_index_dealt_cond";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = BranchIndexDealtCondRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return BranchIndexDealtCondRow.TYPE;
  }


  /** 
   * <em>target_product_code</em>カラムの値 
   */
  public  String  target_product_code;

  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>branch_code</em>カラムの値 
   */
  public  String  branch_code;

  /** 
   * <em>market_code</em>カラムの値 
   */
  public  String  market_code;

  /** 
   * <em>future_option_div</em>カラムの値 
   */
  public  String  future_option_div;

  /** 
   * <em>enable_order</em>カラムの値 
   */
  public  String  enable_order;

  /** 
   * <em>open_cont_long_order_limit</em>カラムの値 
   */
  public  Integer  open_cont_long_order_limit;

  /** 
   * <em>open_cont_short_order_limit</em>カラムの値 
   */
  public  Integer  open_cont_short_order_limit;

  /** 
   * <em>settle_cont_long_order_limit</em>カラムの値 
   */
  public  Integer  settle_cont_long_order_limit;

  /** 
   * <em>settle_cont_short_order_limit</em>カラムの値 
   */
  public  Integer  settle_cont_short_order_limit;

  /** 
   * <em>open_cont_limit</em>カラムの値 
   */
  public  Long  open_cont_limit;

  /** 
   * <em>ifo_deposit_per_unit0</em>カラムの値 
   */
  public  double  ifo_deposit_per_unit0;

  /** 
   * <em>ifo_deposit_per_unit1</em>カラムの値 
   */
  public  double  ifo_deposit_per_unit1;

  /** 
   * <em>ifo_deposit_per_unit0_red</em>カラムの値 
   */
  public  double  ifo_deposit_per_unit0_red;

  /** 
   * <em>ifo_deposit_per_unit1_red</em>カラムの値 
   */
  public  double  ifo_deposit_per_unit1_red;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_date</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_date;

  private boolean target_product_code_is_set = false;
  private boolean target_product_code_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean market_code_is_set = false;
  private boolean market_code_is_modified = false;
  private boolean future_option_div_is_set = false;
  private boolean future_option_div_is_modified = false;
  private boolean enable_order_is_set = false;
  private boolean enable_order_is_modified = false;
  private boolean open_cont_long_order_limit_is_set = false;
  private boolean open_cont_long_order_limit_is_modified = false;
  private boolean open_cont_short_order_limit_is_set = false;
  private boolean open_cont_short_order_limit_is_modified = false;
  private boolean settle_cont_long_order_limit_is_set = false;
  private boolean settle_cont_long_order_limit_is_modified = false;
  private boolean settle_cont_short_order_limit_is_set = false;
  private boolean settle_cont_short_order_limit_is_modified = false;
  private boolean open_cont_limit_is_set = false;
  private boolean open_cont_limit_is_modified = false;
  private boolean ifo_deposit_per_unit0_is_set = false;
  private boolean ifo_deposit_per_unit0_is_modified = false;
  private boolean ifo_deposit_per_unit1_is_set = false;
  private boolean ifo_deposit_per_unit1_is_modified = false;
  private boolean ifo_deposit_per_unit0_red_is_set = false;
  private boolean ifo_deposit_per_unit0_red_is_modified = false;
  private boolean ifo_deposit_per_unit1_red_is_set = false;
  private boolean ifo_deposit_per_unit1_red_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_date_is_set = false;
  private boolean last_updated_date_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "target_product_code=" + target_product_code
      + "," + "institution_code=" + institution_code
      + "," + "branch_code=" + branch_code
      + "," + "market_code=" + market_code
      + "," + "future_option_div=" + future_option_div
      + "," + "enable_order=" +enable_order
      + "," + "open_cont_long_order_limit=" +open_cont_long_order_limit
      + "," + "open_cont_short_order_limit=" +open_cont_short_order_limit
      + "," + "settle_cont_long_order_limit=" +settle_cont_long_order_limit
      + "," + "settle_cont_short_order_limit=" +settle_cont_short_order_limit
      + "," + "open_cont_limit=" +open_cont_limit
      + "," + "ifo_deposit_per_unit0=" +ifo_deposit_per_unit0
      + "," + "ifo_deposit_per_unit1=" +ifo_deposit_per_unit1
      + "," + "ifo_deposit_per_unit0_red=" +ifo_deposit_per_unit0_red
      + "," + "ifo_deposit_per_unit1_red=" +ifo_deposit_per_unit1_red
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_date=" +last_updated_date
      + "}";
  }


  /** 
   * 値が未設定のBranchIndexDealtCondParamsオブジェクトを作成します。 
   */
  public BranchIndexDealtCondParams() {
    target_product_code_is_modified = true;
    institution_code_is_modified = true;
    branch_code_is_modified = true;
    market_code_is_modified = true;
    future_option_div_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のBranchIndexDealtCondRowオブジェクトの値を利用してBranchIndexDealtCondParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するBranchIndexDealtCondRowオブジェクト 
   */
  public BranchIndexDealtCondParams( BranchIndexDealtCondRow p_row )
  {
    target_product_code = p_row.getTargetProductCode();
    target_product_code_is_set = p_row.getTargetProductCodeIsSet();
    target_product_code_is_modified = p_row.getTargetProductCodeIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    market_code = p_row.getMarketCode();
    market_code_is_set = p_row.getMarketCodeIsSet();
    market_code_is_modified = p_row.getMarketCodeIsModified();
    future_option_div = p_row.getFutureOptionDiv();
    future_option_div_is_set = p_row.getFutureOptionDivIsSet();
    future_option_div_is_modified = p_row.getFutureOptionDivIsModified();
    enable_order = p_row.getEnableOrder();
    enable_order_is_set = p_row.getEnableOrderIsSet();
    enable_order_is_modified = p_row.getEnableOrderIsModified();
    if ( !p_row.getOpenContLongOrderLimitIsNull() )
      open_cont_long_order_limit = new Integer( p_row.getOpenContLongOrderLimit() );
    open_cont_long_order_limit_is_set = p_row.getOpenContLongOrderLimitIsSet();
    open_cont_long_order_limit_is_modified = p_row.getOpenContLongOrderLimitIsModified();
    if ( !p_row.getOpenContShortOrderLimitIsNull() )
      open_cont_short_order_limit = new Integer( p_row.getOpenContShortOrderLimit() );
    open_cont_short_order_limit_is_set = p_row.getOpenContShortOrderLimitIsSet();
    open_cont_short_order_limit_is_modified = p_row.getOpenContShortOrderLimitIsModified();
    if ( !p_row.getSettleContLongOrderLimitIsNull() )
      settle_cont_long_order_limit = new Integer( p_row.getSettleContLongOrderLimit() );
    settle_cont_long_order_limit_is_set = p_row.getSettleContLongOrderLimitIsSet();
    settle_cont_long_order_limit_is_modified = p_row.getSettleContLongOrderLimitIsModified();
    if ( !p_row.getSettleContShortOrderLimitIsNull() )
      settle_cont_short_order_limit = new Integer( p_row.getSettleContShortOrderLimit() );
    settle_cont_short_order_limit_is_set = p_row.getSettleContShortOrderLimitIsSet();
    settle_cont_short_order_limit_is_modified = p_row.getSettleContShortOrderLimitIsModified();
    if ( !p_row.getOpenContLimitIsNull() )
      open_cont_limit = new Long( p_row.getOpenContLimit() );
    open_cont_limit_is_set = p_row.getOpenContLimitIsSet();
    open_cont_limit_is_modified = p_row.getOpenContLimitIsModified();
    ifo_deposit_per_unit0 = p_row.getIfoDepositPerUnit0();
    ifo_deposit_per_unit0_is_set = p_row.getIfoDepositPerUnit0IsSet();
    ifo_deposit_per_unit0_is_modified = p_row.getIfoDepositPerUnit0IsModified();
    ifo_deposit_per_unit1 = p_row.getIfoDepositPerUnit1();
    ifo_deposit_per_unit1_is_set = p_row.getIfoDepositPerUnit1IsSet();
    ifo_deposit_per_unit1_is_modified = p_row.getIfoDepositPerUnit1IsModified();
    ifo_deposit_per_unit0_red = p_row.getIfoDepositPerUnit0Red();
    ifo_deposit_per_unit0_red_is_set = p_row.getIfoDepositPerUnit0RedIsSet();
    ifo_deposit_per_unit0_red_is_modified = p_row.getIfoDepositPerUnit0RedIsModified();
    ifo_deposit_per_unit1_red = p_row.getIfoDepositPerUnit1Red();
    ifo_deposit_per_unit1_red_is_set = p_row.getIfoDepositPerUnit1RedIsSet();
    ifo_deposit_per_unit1_red_is_modified = p_row.getIfoDepositPerUnit1RedIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_date = p_row.getLastUpdatedDate();
    last_updated_date_is_set = p_row.getLastUpdatedDateIsSet();
    last_updated_date_is_modified = p_row.getLastUpdatedDateIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      enable_order_is_set = true;
      enable_order_is_modified = true;
      open_cont_long_order_limit_is_set = true;
      open_cont_long_order_limit_is_modified = true;
      open_cont_short_order_limit_is_set = true;
      open_cont_short_order_limit_is_modified = true;
      settle_cont_long_order_limit_is_set = true;
      settle_cont_long_order_limit_is_modified = true;
      settle_cont_short_order_limit_is_set = true;
      settle_cont_short_order_limit_is_modified = true;
      open_cont_limit_is_set = true;
      open_cont_limit_is_modified = true;
      ifo_deposit_per_unit0_is_set = true;
      ifo_deposit_per_unit0_is_modified = true;
      ifo_deposit_per_unit1_is_set = true;
      ifo_deposit_per_unit1_is_modified = true;
      ifo_deposit_per_unit0_red_is_set = true;
      ifo_deposit_per_unit0_red_is_modified = true;
      ifo_deposit_per_unit1_red_is_set = true;
      ifo_deposit_per_unit1_red_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_date_is_set = true;
      last_updated_date_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof BranchIndexDealtCondRow ) )
       return false;
    return fieldsEqual( (BranchIndexDealtCondRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のBranchIndexDealtCondRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( BranchIndexDealtCondRow row )
  {
    if ( target_product_code == null ) {
      if ( row.getTargetProductCode() != null )
        return false;
    } else if ( !target_product_code.equals( row.getTargetProductCode() ) ) {
        return false;
    }
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( branch_code == null ) {
      if ( row.getBranchCode() != null )
        return false;
    } else if ( !branch_code.equals( row.getBranchCode() ) ) {
        return false;
    }
    if ( market_code == null ) {
      if ( row.getMarketCode() != null )
        return false;
    } else if ( !market_code.equals( row.getMarketCode() ) ) {
        return false;
    }
    if ( future_option_div == null ) {
      if ( row.getFutureOptionDiv() != null )
        return false;
    } else if ( !future_option_div.equals( row.getFutureOptionDiv() ) ) {
        return false;
    }
    if ( enable_order == null ) {
      if ( row.getEnableOrder() != null )
        return false;
    } else if ( !enable_order.equals( row.getEnableOrder() ) ) {
        return false;
    }
    if ( open_cont_long_order_limit == null ) {
      if ( !row.getOpenContLongOrderLimitIsNull() )
        return false;
    } else if ( row.getOpenContLongOrderLimitIsNull() || ( open_cont_long_order_limit.intValue() != row.getOpenContLongOrderLimit() ) ) {
        return false;
    }
    if ( open_cont_short_order_limit == null ) {
      if ( !row.getOpenContShortOrderLimitIsNull() )
        return false;
    } else if ( row.getOpenContShortOrderLimitIsNull() || ( open_cont_short_order_limit.intValue() != row.getOpenContShortOrderLimit() ) ) {
        return false;
    }
    if ( settle_cont_long_order_limit == null ) {
      if ( !row.getSettleContLongOrderLimitIsNull() )
        return false;
    } else if ( row.getSettleContLongOrderLimitIsNull() || ( settle_cont_long_order_limit.intValue() != row.getSettleContLongOrderLimit() ) ) {
        return false;
    }
    if ( settle_cont_short_order_limit == null ) {
      if ( !row.getSettleContShortOrderLimitIsNull() )
        return false;
    } else if ( row.getSettleContShortOrderLimitIsNull() || ( settle_cont_short_order_limit.intValue() != row.getSettleContShortOrderLimit() ) ) {
        return false;
    }
    if ( open_cont_limit == null ) {
      if ( !row.getOpenContLimitIsNull() )
        return false;
    } else if ( row.getOpenContLimitIsNull() || ( open_cont_limit.longValue() != row.getOpenContLimit() ) ) {
        return false;
    }
    if ( ifo_deposit_per_unit0 != row.getIfoDepositPerUnit0() )
      return false;
    if ( ifo_deposit_per_unit1 != row.getIfoDepositPerUnit1() )
      return false;
    if ( ifo_deposit_per_unit0_red != row.getIfoDepositPerUnit0Red() )
      return false;
    if ( ifo_deposit_per_unit1_red != row.getIfoDepositPerUnit1Red() )
      return false;
    if ( created_timestamp == null ) {
      if ( row.getCreatedTimestamp() != null )
        return false;
    } else if ( !created_timestamp.equals( row.getCreatedTimestamp() ) ) {
        return false;
    }
    if ( last_updated_date == null ) {
      if ( row.getLastUpdatedDate() != null )
        return false;
    } else if ( !last_updated_date.equals( row.getLastUpdatedDate() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  (target_product_code!=null? target_product_code.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (market_code!=null? market_code.hashCode(): 0) 
        + (future_option_div!=null? future_option_div.hashCode(): 0) 
        + (enable_order!=null? enable_order.hashCode(): 0) 
        + (open_cont_long_order_limit!=null? open_cont_long_order_limit.hashCode(): 0) 
        + (open_cont_short_order_limit!=null? open_cont_short_order_limit.hashCode(): 0) 
        + (settle_cont_long_order_limit!=null? settle_cont_long_order_limit.hashCode(): 0) 
        + (settle_cont_short_order_limit!=null? settle_cont_short_order_limit.hashCode(): 0) 
        + (open_cont_limit!=null? open_cont_limit.hashCode(): 0) 
        + ((int) ifo_deposit_per_unit0)
        + ((int) ifo_deposit_per_unit1)
        + ((int) ifo_deposit_per_unit0_red)
        + ((int) ifo_deposit_per_unit1_red)
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_date!=null? last_updated_date.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !ifo_deposit_per_unit0_is_set )
			throw new IllegalArgumentException("non-nullable field 'ifo_deposit_per_unit0' must be set before inserting.");
		if ( !ifo_deposit_per_unit1_is_set )
			throw new IllegalArgumentException("non-nullable field 'ifo_deposit_per_unit1' must be set before inserting.");
		if ( !ifo_deposit_per_unit0_red_is_set )
			throw new IllegalArgumentException("non-nullable field 'ifo_deposit_per_unit0_red' must be set before inserting.");
		if ( !ifo_deposit_per_unit1_red_is_set )
			throw new IllegalArgumentException("non-nullable field 'ifo_deposit_per_unit1_red' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("target_product_code",target_product_code);
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("market_code",market_code);
		map.put("future_option_div",future_option_div);
		if ( enable_order != null )
			map.put("enable_order",enable_order);
		if ( open_cont_long_order_limit != null )
			map.put("open_cont_long_order_limit",open_cont_long_order_limit);
		if ( open_cont_short_order_limit != null )
			map.put("open_cont_short_order_limit",open_cont_short_order_limit);
		if ( settle_cont_long_order_limit != null )
			map.put("settle_cont_long_order_limit",settle_cont_long_order_limit);
		if ( settle_cont_short_order_limit != null )
			map.put("settle_cont_short_order_limit",settle_cont_short_order_limit);
		if ( open_cont_limit != null )
			map.put("open_cont_limit",open_cont_limit);
		map.put("ifo_deposit_per_unit0",new Double(ifo_deposit_per_unit0));
		map.put("ifo_deposit_per_unit1",new Double(ifo_deposit_per_unit1));
		map.put("ifo_deposit_per_unit0_red",new Double(ifo_deposit_per_unit0_red));
		map.put("ifo_deposit_per_unit1_red",new Double(ifo_deposit_per_unit1_red));
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_date_is_set )
			map.put("last_updated_date",last_updated_date);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( enable_order_is_modified )
			map.put("enable_order",enable_order);
		if ( open_cont_long_order_limit_is_modified )
			map.put("open_cont_long_order_limit",open_cont_long_order_limit);
		if ( open_cont_short_order_limit_is_modified )
			map.put("open_cont_short_order_limit",open_cont_short_order_limit);
		if ( settle_cont_long_order_limit_is_modified )
			map.put("settle_cont_long_order_limit",settle_cont_long_order_limit);
		if ( settle_cont_short_order_limit_is_modified )
			map.put("settle_cont_short_order_limit",settle_cont_short_order_limit);
		if ( open_cont_limit_is_modified )
			map.put("open_cont_limit",open_cont_limit);
		if ( ifo_deposit_per_unit0_is_modified )
			map.put("ifo_deposit_per_unit0",new Double(ifo_deposit_per_unit0));
		if ( ifo_deposit_per_unit1_is_modified )
			map.put("ifo_deposit_per_unit1",new Double(ifo_deposit_per_unit1));
		if ( ifo_deposit_per_unit0_red_is_modified )
			map.put("ifo_deposit_per_unit0_red",new Double(ifo_deposit_per_unit0_red));
		if ( ifo_deposit_per_unit1_red_is_modified )
			map.put("ifo_deposit_per_unit1_red",new Double(ifo_deposit_per_unit1_red));
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_date_is_modified )
			map.put("last_updated_date",last_updated_date);
		if (map.size() == 0) {
			map.put("enable_order",enable_order);
			map.put("open_cont_long_order_limit",open_cont_long_order_limit);
			map.put("open_cont_short_order_limit",open_cont_short_order_limit);
			map.put("settle_cont_long_order_limit",settle_cont_long_order_limit);
			map.put("settle_cont_short_order_limit",settle_cont_short_order_limit);
			map.put("open_cont_limit",open_cont_limit);
			if ( ifo_deposit_per_unit0_is_set )
				map.put("ifo_deposit_per_unit0",new Double(ifo_deposit_per_unit0));
			if ( ifo_deposit_per_unit1_is_set )
				map.put("ifo_deposit_per_unit1",new Double(ifo_deposit_per_unit1));
			if ( ifo_deposit_per_unit0_red_is_set )
				map.put("ifo_deposit_per_unit0_red",new Double(ifo_deposit_per_unit0_red));
			if ( ifo_deposit_per_unit1_red_is_set )
				map.put("ifo_deposit_per_unit1_red",new Double(ifo_deposit_per_unit1_red));
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_date_is_set )
				map.put("last_updated_date",last_updated_date);
		}
		return map;
	}


  /** 
   * <em>target_product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTargetProductCode()
  {
    return target_product_code;
  }


  /** 
   * <em>target_product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTargetProductCodeIsSet() {
    return target_product_code_is_set;
  }


  /** 
   * <em>target_product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTargetProductCodeIsModified() {
    return target_product_code_is_modified;
  }


  /** 
   * <em>institution_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInstitutionCode()
  {
    return institution_code;
  }


  /** 
   * <em>institution_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstitutionCodeIsSet() {
    return institution_code_is_set;
  }


  /** 
   * <em>institution_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstitutionCodeIsModified() {
    return institution_code_is_modified;
  }


  /** 
   * <em>branch_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBranchCode()
  {
    return branch_code;
  }


  /** 
   * <em>branch_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchCodeIsSet() {
    return branch_code_is_set;
  }


  /** 
   * <em>branch_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchCodeIsModified() {
    return branch_code_is_modified;
  }


  /** 
   * <em>market_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMarketCode()
  {
    return market_code;
  }


  /** 
   * <em>market_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketCodeIsSet() {
    return market_code_is_set;
  }


  /** 
   * <em>market_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketCodeIsModified() {
    return market_code_is_modified;
  }


  /** 
   * <em>future_option_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFutureOptionDiv()
  {
    return future_option_div;
  }


  /** 
   * <em>future_option_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFutureOptionDivIsSet() {
    return future_option_div_is_set;
  }


  /** 
   * <em>future_option_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFutureOptionDivIsModified() {
    return future_option_div_is_modified;
  }


  /** 
   * <em>enable_order</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getEnableOrder()
  {
    return enable_order;
  }


  /** 
   * <em>enable_order</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEnableOrderIsSet() {
    return enable_order_is_set;
  }


  /** 
   * <em>enable_order</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEnableOrderIsModified() {
    return enable_order_is_modified;
  }


  /** 
   * <em>open_cont_long_order_limit</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getOpenContLongOrderLimit()
  {
    return ( open_cont_long_order_limit==null? ((int)0): open_cont_long_order_limit.intValue() );
  }


  /** 
   * <em>open_cont_long_order_limit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOpenContLongOrderLimitIsNull()
  {
    return open_cont_long_order_limit == null;
  }


  /** 
   * <em>open_cont_long_order_limit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOpenContLongOrderLimitIsSet() {
    return open_cont_long_order_limit_is_set;
  }


  /** 
   * <em>open_cont_long_order_limit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOpenContLongOrderLimitIsModified() {
    return open_cont_long_order_limit_is_modified;
  }


  /** 
   * <em>open_cont_short_order_limit</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getOpenContShortOrderLimit()
  {
    return ( open_cont_short_order_limit==null? ((int)0): open_cont_short_order_limit.intValue() );
  }


  /** 
   * <em>open_cont_short_order_limit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOpenContShortOrderLimitIsNull()
  {
    return open_cont_short_order_limit == null;
  }


  /** 
   * <em>open_cont_short_order_limit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOpenContShortOrderLimitIsSet() {
    return open_cont_short_order_limit_is_set;
  }


  /** 
   * <em>open_cont_short_order_limit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOpenContShortOrderLimitIsModified() {
    return open_cont_short_order_limit_is_modified;
  }


  /** 
   * <em>settle_cont_long_order_limit</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getSettleContLongOrderLimit()
  {
    return ( settle_cont_long_order_limit==null? ((int)0): settle_cont_long_order_limit.intValue() );
  }


  /** 
   * <em>settle_cont_long_order_limit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSettleContLongOrderLimitIsNull()
  {
    return settle_cont_long_order_limit == null;
  }


  /** 
   * <em>settle_cont_long_order_limit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSettleContLongOrderLimitIsSet() {
    return settle_cont_long_order_limit_is_set;
  }


  /** 
   * <em>settle_cont_long_order_limit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSettleContLongOrderLimitIsModified() {
    return settle_cont_long_order_limit_is_modified;
  }


  /** 
   * <em>settle_cont_short_order_limit</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getSettleContShortOrderLimit()
  {
    return ( settle_cont_short_order_limit==null? ((int)0): settle_cont_short_order_limit.intValue() );
  }


  /** 
   * <em>settle_cont_short_order_limit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSettleContShortOrderLimitIsNull()
  {
    return settle_cont_short_order_limit == null;
  }


  /** 
   * <em>settle_cont_short_order_limit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSettleContShortOrderLimitIsSet() {
    return settle_cont_short_order_limit_is_set;
  }


  /** 
   * <em>settle_cont_short_order_limit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSettleContShortOrderLimitIsModified() {
    return settle_cont_short_order_limit_is_modified;
  }


  /** 
   * <em>open_cont_limit</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getOpenContLimit()
  {
    return ( open_cont_limit==null? ((long)0): open_cont_limit.longValue() );
  }


  /** 
   * <em>open_cont_limit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOpenContLimitIsNull()
  {
    return open_cont_limit == null;
  }


  /** 
   * <em>open_cont_limit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOpenContLimitIsSet() {
    return open_cont_limit_is_set;
  }


  /** 
   * <em>open_cont_limit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOpenContLimitIsModified() {
    return open_cont_limit_is_modified;
  }


  /** 
   * <em>ifo_deposit_per_unit0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getIfoDepositPerUnit0()
  {
    return ifo_deposit_per_unit0;
  }


  /** 
   * <em>ifo_deposit_per_unit0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositPerUnit0IsSet() {
    return ifo_deposit_per_unit0_is_set;
  }


  /** 
   * <em>ifo_deposit_per_unit0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositPerUnit0IsModified() {
    return ifo_deposit_per_unit0_is_modified;
  }


  /** 
   * <em>ifo_deposit_per_unit1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getIfoDepositPerUnit1()
  {
    return ifo_deposit_per_unit1;
  }


  /** 
   * <em>ifo_deposit_per_unit1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositPerUnit1IsSet() {
    return ifo_deposit_per_unit1_is_set;
  }


  /** 
   * <em>ifo_deposit_per_unit1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositPerUnit1IsModified() {
    return ifo_deposit_per_unit1_is_modified;
  }


  /** 
   * <em>ifo_deposit_per_unit0_red</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getIfoDepositPerUnit0Red()
  {
    return ifo_deposit_per_unit0_red;
  }


  /** 
   * <em>ifo_deposit_per_unit0_red</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositPerUnit0RedIsSet() {
    return ifo_deposit_per_unit0_red_is_set;
  }


  /** 
   * <em>ifo_deposit_per_unit0_red</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositPerUnit0RedIsModified() {
    return ifo_deposit_per_unit0_red_is_modified;
  }


  /** 
   * <em>ifo_deposit_per_unit1_red</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getIfoDepositPerUnit1Red()
  {
    return ifo_deposit_per_unit1_red;
  }


  /** 
   * <em>ifo_deposit_per_unit1_red</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositPerUnit1RedIsSet() {
    return ifo_deposit_per_unit1_red_is_set;
  }


  /** 
   * <em>ifo_deposit_per_unit1_red</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoDepositPerUnit1RedIsModified() {
    return ifo_deposit_per_unit1_red_is_modified;
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
   * <em>last_updated_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getLastUpdatedDate()
  {
    return last_updated_date;
  }


  /** 
   * <em>last_updated_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdatedDateIsSet() {
    return last_updated_date_is_set;
  }


  /** 
   * <em>last_updated_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdatedDateIsModified() {
    return last_updated_date_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new BranchIndexDealtCondPK(target_product_code, institution_code, branch_code, market_code, future_option_div);
  }


  /** 
   * <em>target_product_code</em>カラムの値を設定します。 
   *
   * @@param p_targetProductCode <em>target_product_code</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setTargetProductCode( String p_targetProductCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    target_product_code = p_targetProductCode;
    target_product_code_is_set = true;
    target_product_code_is_modified = true;
  }


  /** 
   * <em>institution_code</em>カラムの値を設定します。 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setInstitutionCode( String p_institutionCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    institution_code = p_institutionCode;
    institution_code_is_set = true;
    institution_code_is_modified = true;
  }


  /** 
   * <em>branch_code</em>カラムの値を設定します。 
   *
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setBranchCode( String p_branchCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_code = p_branchCode;
    branch_code_is_set = true;
    branch_code_is_modified = true;
  }


  /** 
   * <em>market_code</em>カラムの値を設定します。 
   *
   * @@param p_marketCode <em>market_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setMarketCode( String p_marketCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_code = p_marketCode;
    market_code_is_set = true;
    market_code_is_modified = true;
  }


  /** 
   * <em>future_option_div</em>カラムの値を設定します。 
   *
   * @@param p_futureOptionDiv <em>future_option_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFutureOptionDiv( String p_futureOptionDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    future_option_div = p_futureOptionDiv;
    future_option_div_is_set = true;
    future_option_div_is_modified = true;
  }


  /** 
   * <em>enable_order</em>カラムの値を設定します。 
   *
   * @@param p_enableOrder <em>enable_order</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setEnableOrder( String p_enableOrder )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    enable_order = p_enableOrder;
    enable_order_is_set = true;
    enable_order_is_modified = true;
  }


  /** 
   * <em>open_cont_long_order_limit</em>カラムの値を設定します。 
   *
   * @@param p_openContLongOrderLimit <em>open_cont_long_order_limit</em>カラムの値をあらわす5桁以下のint値 
   */
  public final void setOpenContLongOrderLimit( int p_openContLongOrderLimit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    open_cont_long_order_limit = new Integer(p_openContLongOrderLimit);
    open_cont_long_order_limit_is_set = true;
    open_cont_long_order_limit_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>open_cont_long_order_limit</em>カラムに値を設定します。 
   */
  public final void setOpenContLongOrderLimit( Integer p_openContLongOrderLimit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    open_cont_long_order_limit = p_openContLongOrderLimit;
    open_cont_long_order_limit_is_set = true;
    open_cont_long_order_limit_is_modified = true;
  }


  /** 
   * <em>open_cont_short_order_limit</em>カラムの値を設定します。 
   *
   * @@param p_openContShortOrderLimit <em>open_cont_short_order_limit</em>カラムの値をあらわす5桁以下のint値 
   */
  public final void setOpenContShortOrderLimit( int p_openContShortOrderLimit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    open_cont_short_order_limit = new Integer(p_openContShortOrderLimit);
    open_cont_short_order_limit_is_set = true;
    open_cont_short_order_limit_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>open_cont_short_order_limit</em>カラムに値を設定します。 
   */
  public final void setOpenContShortOrderLimit( Integer p_openContShortOrderLimit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    open_cont_short_order_limit = p_openContShortOrderLimit;
    open_cont_short_order_limit_is_set = true;
    open_cont_short_order_limit_is_modified = true;
  }


  /** 
   * <em>settle_cont_long_order_limit</em>カラムの値を設定します。 
   *
   * @@param p_settleContLongOrderLimit <em>settle_cont_long_order_limit</em>カラムの値をあらわす5桁以下のint値 
   */
  public final void setSettleContLongOrderLimit( int p_settleContLongOrderLimit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    settle_cont_long_order_limit = new Integer(p_settleContLongOrderLimit);
    settle_cont_long_order_limit_is_set = true;
    settle_cont_long_order_limit_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>settle_cont_long_order_limit</em>カラムに値を設定します。 
   */
  public final void setSettleContLongOrderLimit( Integer p_settleContLongOrderLimit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    settle_cont_long_order_limit = p_settleContLongOrderLimit;
    settle_cont_long_order_limit_is_set = true;
    settle_cont_long_order_limit_is_modified = true;
  }


  /** 
   * <em>settle_cont_short_order_limit</em>カラムの値を設定します。 
   *
   * @@param p_settleContShortOrderLimit <em>settle_cont_short_order_limit</em>カラムの値をあらわす5桁以下のint値 
   */
  public final void setSettleContShortOrderLimit( int p_settleContShortOrderLimit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    settle_cont_short_order_limit = new Integer(p_settleContShortOrderLimit);
    settle_cont_short_order_limit_is_set = true;
    settle_cont_short_order_limit_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>settle_cont_short_order_limit</em>カラムに値を設定します。 
   */
  public final void setSettleContShortOrderLimit( Integer p_settleContShortOrderLimit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    settle_cont_short_order_limit = p_settleContShortOrderLimit;
    settle_cont_short_order_limit_is_set = true;
    settle_cont_short_order_limit_is_modified = true;
  }


  /** 
   * <em>open_cont_limit</em>カラムの値を設定します。 
   *
   * @@param p_openContLimit <em>open_cont_limit</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setOpenContLimit( long p_openContLimit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    open_cont_limit = new Long(p_openContLimit);
    open_cont_limit_is_set = true;
    open_cont_limit_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>open_cont_limit</em>カラムに値を設定します。 
   */
  public final void setOpenContLimit( Long p_openContLimit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    open_cont_limit = p_openContLimit;
    open_cont_limit_is_set = true;
    open_cont_limit_is_modified = true;
  }


  /** 
   * <em>ifo_deposit_per_unit0</em>カラムの値を設定します。 
   *
   * @@param p_ifoDepositPerUnit0 <em>ifo_deposit_per_unit0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setIfoDepositPerUnit0( double p_ifoDepositPerUnit0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_deposit_per_unit0 = p_ifoDepositPerUnit0;
    ifo_deposit_per_unit0_is_set = true;
    ifo_deposit_per_unit0_is_modified = true;
  }


  /** 
   * <em>ifo_deposit_per_unit1</em>カラムの値を設定します。 
   *
   * @@param p_ifoDepositPerUnit1 <em>ifo_deposit_per_unit1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setIfoDepositPerUnit1( double p_ifoDepositPerUnit1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_deposit_per_unit1 = p_ifoDepositPerUnit1;
    ifo_deposit_per_unit1_is_set = true;
    ifo_deposit_per_unit1_is_modified = true;
  }


  /** 
   * <em>ifo_deposit_per_unit0_red</em>カラムの値を設定します。 
   *
   * @@param p_ifoDepositPerUnit0Red <em>ifo_deposit_per_unit0_red</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setIfoDepositPerUnit0Red( double p_ifoDepositPerUnit0Red )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_deposit_per_unit0_red = p_ifoDepositPerUnit0Red;
    ifo_deposit_per_unit0_red_is_set = true;
    ifo_deposit_per_unit0_red_is_modified = true;
  }


  /** 
   * <em>ifo_deposit_per_unit1_red</em>カラムの値を設定します。 
   *
   * @@param p_ifoDepositPerUnit1Red <em>ifo_deposit_per_unit1_red</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setIfoDepositPerUnit1Red( double p_ifoDepositPerUnit1Red )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_deposit_per_unit1_red = p_ifoDepositPerUnit1Red;
    ifo_deposit_per_unit1_red_is_set = true;
    ifo_deposit_per_unit1_red_is_modified = true;
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
   * <em>last_updated_date</em>カラムの値を設定します。 
   *
   * @@param p_lastUpdatedDate <em>last_updated_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setLastUpdatedDate( java.sql.Timestamp p_lastUpdatedDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_date = p_lastUpdatedDate;
    last_updated_date_is_set = true;
    last_updated_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>last_updated_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setLastUpdatedDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    last_updated_date_is_set = true;
    last_updated_date_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("enable_order") ) {
                    return this.enable_order;
                }
                break;
            case 'f':
                if ( name.equals("future_option_div") ) {
                    return this.future_option_div;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("ifo_deposit_per_unit0") ) {
                    return new Double( ifo_deposit_per_unit0 );
                }
                else if ( name.equals("ifo_deposit_per_unit1") ) {
                    return new Double( ifo_deposit_per_unit1 );
                }
                else if ( name.equals("ifo_deposit_per_unit0_red") ) {
                    return new Double( ifo_deposit_per_unit0_red );
                }
                else if ( name.equals("ifo_deposit_per_unit1_red") ) {
                    return new Double( ifo_deposit_per_unit1_red );
                }
                break;
            case 'l':
                if ( name.equals("last_updated_date") ) {
                    return this.last_updated_date;
                }
                break;
            case 'm':
                if ( name.equals("market_code") ) {
                    return this.market_code;
                }
                break;
            case 'o':
                if ( name.equals("open_cont_long_order_limit") ) {
                    return this.open_cont_long_order_limit;
                }
                else if ( name.equals("open_cont_short_order_limit") ) {
                    return this.open_cont_short_order_limit;
                }
                else if ( name.equals("open_cont_limit") ) {
                    return this.open_cont_limit;
                }
                break;
            case 's':
                if ( name.equals("settle_cont_long_order_limit") ) {
                    return this.settle_cont_long_order_limit;
                }
                else if ( name.equals("settle_cont_short_order_limit") ) {
                    return this.settle_cont_short_order_limit;
                }
                break;
            case 't':
                if ( name.equals("target_product_code") ) {
                    return this.target_product_code;
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
                if ( name.equals("branch_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'branch_code' must be String: '"+value+"' is not." );
                    this.branch_code = (String) value;
                    if (this.branch_code_is_set)
                        this.branch_code_is_modified = true;
                    this.branch_code_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
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
                if ( name.equals("enable_order") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'enable_order' must be String: '"+value+"' is not." );
                    this.enable_order = (String) value;
                    if (this.enable_order_is_set)
                        this.enable_order_is_modified = true;
                    this.enable_order_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("future_option_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'future_option_div' must be String: '"+value+"' is not." );
                    this.future_option_div = (String) value;
                    if (this.future_option_div_is_set)
                        this.future_option_div_is_modified = true;
                    this.future_option_div_is_set = true;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'institution_code' must be String: '"+value+"' is not." );
                    this.institution_code = (String) value;
                    if (this.institution_code_is_set)
                        this.institution_code_is_modified = true;
                    this.institution_code_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_deposit_per_unit0") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'ifo_deposit_per_unit0' must be Double: '"+value+"' is not." );
                    this.ifo_deposit_per_unit0 = ((Double) value).doubleValue();
                    if (this.ifo_deposit_per_unit0_is_set)
                        this.ifo_deposit_per_unit0_is_modified = true;
                    this.ifo_deposit_per_unit0_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_deposit_per_unit1") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'ifo_deposit_per_unit1' must be Double: '"+value+"' is not." );
                    this.ifo_deposit_per_unit1 = ((Double) value).doubleValue();
                    if (this.ifo_deposit_per_unit1_is_set)
                        this.ifo_deposit_per_unit1_is_modified = true;
                    this.ifo_deposit_per_unit1_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_deposit_per_unit0_red") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'ifo_deposit_per_unit0_red' must be Double: '"+value+"' is not." );
                    this.ifo_deposit_per_unit0_red = ((Double) value).doubleValue();
                    if (this.ifo_deposit_per_unit0_red_is_set)
                        this.ifo_deposit_per_unit0_red_is_modified = true;
                    this.ifo_deposit_per_unit0_red_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_deposit_per_unit1_red") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'ifo_deposit_per_unit1_red' must be Double: '"+value+"' is not." );
                    this.ifo_deposit_per_unit1_red = ((Double) value).doubleValue();
                    if (this.ifo_deposit_per_unit1_red_is_set)
                        this.ifo_deposit_per_unit1_red_is_modified = true;
                    this.ifo_deposit_per_unit1_red_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_date = (java.sql.Timestamp) value;
                    if (this.last_updated_date_is_set)
                        this.last_updated_date_is_modified = true;
                    this.last_updated_date_is_set = true;
                    return;
                }
                break;
            case 'm':
                if ( name.equals("market_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'market_code' must be String: '"+value+"' is not." );
                    this.market_code = (String) value;
                    if (this.market_code_is_set)
                        this.market_code_is_modified = true;
                    this.market_code_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("open_cont_long_order_limit") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'open_cont_long_order_limit' must be Integer: '"+value+"' is not." );
                    this.open_cont_long_order_limit = (Integer) value;
                    if (this.open_cont_long_order_limit_is_set)
                        this.open_cont_long_order_limit_is_modified = true;
                    this.open_cont_long_order_limit_is_set = true;
                    return;
                }
                else if ( name.equals("open_cont_short_order_limit") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'open_cont_short_order_limit' must be Integer: '"+value+"' is not." );
                    this.open_cont_short_order_limit = (Integer) value;
                    if (this.open_cont_short_order_limit_is_set)
                        this.open_cont_short_order_limit_is_modified = true;
                    this.open_cont_short_order_limit_is_set = true;
                    return;
                }
                else if ( name.equals("open_cont_limit") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'open_cont_limit' must be Long: '"+value+"' is not." );
                    this.open_cont_limit = (Long) value;
                    if (this.open_cont_limit_is_set)
                        this.open_cont_limit_is_modified = true;
                    this.open_cont_limit_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("settle_cont_long_order_limit") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'settle_cont_long_order_limit' must be Integer: '"+value+"' is not." );
                    this.settle_cont_long_order_limit = (Integer) value;
                    if (this.settle_cont_long_order_limit_is_set)
                        this.settle_cont_long_order_limit_is_modified = true;
                    this.settle_cont_long_order_limit_is_set = true;
                    return;
                }
                else if ( name.equals("settle_cont_short_order_limit") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'settle_cont_short_order_limit' must be Integer: '"+value+"' is not." );
                    this.settle_cont_short_order_limit = (Integer) value;
                    if (this.settle_cont_short_order_limit_is_set)
                        this.settle_cont_short_order_limit_is_modified = true;
                    this.settle_cont_short_order_limit_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("target_product_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'target_product_code' must be String: '"+value+"' is not." );
                    this.target_product_code = (String) value;
                    if (this.target_product_code_is_set)
                        this.target_product_code_is_modified = true;
                    this.target_product_code_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
