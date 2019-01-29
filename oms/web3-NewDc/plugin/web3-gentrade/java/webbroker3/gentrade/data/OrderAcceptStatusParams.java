head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.34.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	OrderAcceptStatusParams.java;


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
 * order_accept_statusテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link OrderAcceptStatusRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link OrderAcceptStatusParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link OrderAcceptStatusParams}が{@@link OrderAcceptStatusRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see OrderAcceptStatusPK 
 * @@see OrderAcceptStatusRow 
 */
public class OrderAcceptStatusParams extends Params implements OrderAcceptStatusRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "order_accept_status";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = OrderAcceptStatusRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return OrderAcceptStatusRow.TYPE;
  }


  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>branch_code</em>カラムの値 
   */
  public  String  branch_code;

  /** 
   * <em>order_acc_product</em>カラムの値 
   */
  public  String  order_acc_product;

  /** 
   * <em>order_acc_transaction</em>カラムの値 
   */
  public  String  order_acc_transaction;

  /** 
   * <em>order_accept_status</em>カラムの値 
   */
  public  String  order_accept_status;

  /** 
   * <em>order_accept_status_before</em>カラムの値 
   */
  public  String  order_accept_status_before;

  /** 
   * <em>last_updater</em>カラムの値 
   */
  public  String  last_updater;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean order_acc_product_is_set = false;
  private boolean order_acc_product_is_modified = false;
  private boolean order_acc_transaction_is_set = false;
  private boolean order_acc_transaction_is_modified = false;
  private boolean order_accept_status_is_set = false;
  private boolean order_accept_status_is_modified = false;
  private boolean order_accept_status_before_is_set = false;
  private boolean order_accept_status_before_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "branch_code=" + branch_code
      + "," + "order_acc_product=" + order_acc_product
      + "," + "order_acc_transaction=" + order_acc_transaction
      + "," + "order_accept_status=" +order_accept_status
      + "," + "order_accept_status_before=" +order_accept_status_before
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のOrderAcceptStatusParamsオブジェクトを作成します。 
   */
  public OrderAcceptStatusParams() {
    institution_code_is_modified = true;
    branch_code_is_modified = true;
    order_acc_product_is_modified = true;
    order_acc_transaction_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のOrderAcceptStatusRowオブジェクトの値を利用してOrderAcceptStatusParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するOrderAcceptStatusRowオブジェクト 
   */
  public OrderAcceptStatusParams( OrderAcceptStatusRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    order_acc_product = p_row.getOrderAccProduct();
    order_acc_product_is_set = p_row.getOrderAccProductIsSet();
    order_acc_product_is_modified = p_row.getOrderAccProductIsModified();
    order_acc_transaction = p_row.getOrderAccTransaction();
    order_acc_transaction_is_set = p_row.getOrderAccTransactionIsSet();
    order_acc_transaction_is_modified = p_row.getOrderAccTransactionIsModified();
    order_accept_status = p_row.getOrderAcceptStatus();
    order_accept_status_is_set = p_row.getOrderAcceptStatusIsSet();
    order_accept_status_is_modified = p_row.getOrderAcceptStatusIsModified();
    order_accept_status_before = p_row.getOrderAcceptStatusBefore();
    order_accept_status_before_is_set = p_row.getOrderAcceptStatusBeforeIsSet();
    order_accept_status_before_is_modified = p_row.getOrderAcceptStatusBeforeIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
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
      order_accept_status_is_set = true;
      order_accept_status_is_modified = true;
      order_accept_status_before_is_set = true;
      order_accept_status_before_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
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
    if ( !( other instanceof OrderAcceptStatusRow ) )
       return false;
    return fieldsEqual( (OrderAcceptStatusRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のOrderAcceptStatusRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( OrderAcceptStatusRow row )
  {
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
    if ( order_acc_product == null ) {
      if ( row.getOrderAccProduct() != null )
        return false;
    } else if ( !order_acc_product.equals( row.getOrderAccProduct() ) ) {
        return false;
    }
    if ( order_acc_transaction == null ) {
      if ( row.getOrderAccTransaction() != null )
        return false;
    } else if ( !order_acc_transaction.equals( row.getOrderAccTransaction() ) ) {
        return false;
    }
    if ( order_accept_status == null ) {
      if ( row.getOrderAcceptStatus() != null )
        return false;
    } else if ( !order_accept_status.equals( row.getOrderAcceptStatus() ) ) {
        return false;
    }
    if ( order_accept_status_before == null ) {
      if ( row.getOrderAcceptStatusBefore() != null )
        return false;
    } else if ( !order_accept_status_before.equals( row.getOrderAcceptStatusBefore() ) ) {
        return false;
    }
    if ( last_updater == null ) {
      if ( row.getLastUpdater() != null )
        return false;
    } else if ( !last_updater.equals( row.getLastUpdater() ) ) {
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
      return  (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (order_acc_product!=null? order_acc_product.hashCode(): 0) 
        + (order_acc_transaction!=null? order_acc_transaction.hashCode(): 0) 
        + (order_accept_status!=null? order_accept_status.hashCode(): 0) 
        + (order_accept_status_before!=null? order_accept_status_before.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !order_accept_status_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_accept_status' must be set before inserting.");
		if ( !order_accept_status_before_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_accept_status_before' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("order_acc_product",order_acc_product);
		map.put("order_acc_transaction",order_acc_transaction);
		map.put("order_accept_status",order_accept_status);
		map.put("order_accept_status_before",order_accept_status_before);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
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
		if ( order_accept_status_is_modified )
			map.put("order_accept_status",order_accept_status);
		if ( order_accept_status_before_is_modified )
			map.put("order_accept_status_before",order_accept_status_before);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( order_accept_status_is_set )
				map.put("order_accept_status",order_accept_status);
			if ( order_accept_status_before_is_set )
				map.put("order_accept_status_before",order_accept_status_before);
			map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
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
   * <em>order_acc_product</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderAccProduct()
  {
    return order_acc_product;
  }


  /** 
   * <em>order_acc_product</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderAccProductIsSet() {
    return order_acc_product_is_set;
  }


  /** 
   * <em>order_acc_product</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderAccProductIsModified() {
    return order_acc_product_is_modified;
  }


  /** 
   * <em>order_acc_transaction</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderAccTransaction()
  {
    return order_acc_transaction;
  }


  /** 
   * <em>order_acc_transaction</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderAccTransactionIsSet() {
    return order_acc_transaction_is_set;
  }


  /** 
   * <em>order_acc_transaction</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderAccTransactionIsModified() {
    return order_acc_transaction_is_modified;
  }


  /** 
   * <em>order_accept_status</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderAcceptStatus()
  {
    return order_accept_status;
  }


  /** 
   * <em>order_accept_status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderAcceptStatusIsSet() {
    return order_accept_status_is_set;
  }


  /** 
   * <em>order_accept_status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderAcceptStatusIsModified() {
    return order_accept_status_is_modified;
  }


  /** 
   * <em>order_accept_status_before</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderAcceptStatusBefore()
  {
    return order_accept_status_before;
  }


  /** 
   * <em>order_accept_status_before</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderAcceptStatusBeforeIsSet() {
    return order_accept_status_before_is_set;
  }


  /** 
   * <em>order_accept_status_before</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderAcceptStatusBeforeIsModified() {
    return order_accept_status_before_is_modified;
  }


  /** 
   * <em>last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLastUpdater()
  {
    return last_updater;
  }


  /** 
   * <em>last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdaterIsSet() {
    return last_updater_is_set;
  }


  /** 
   * <em>last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdaterIsModified() {
    return last_updater_is_modified;
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
    return new OrderAcceptStatusPK(institution_code, branch_code, order_acc_product, order_acc_transaction);
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
   * <em>order_acc_product</em>カラムの値を設定します。 
   *
   * @@param p_orderAccProduct <em>order_acc_product</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setOrderAccProduct( String p_orderAccProduct )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_acc_product = p_orderAccProduct;
    order_acc_product_is_set = true;
    order_acc_product_is_modified = true;
  }


  /** 
   * <em>order_acc_transaction</em>カラムの値を設定します。 
   *
   * @@param p_orderAccTransaction <em>order_acc_transaction</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setOrderAccTransaction( String p_orderAccTransaction )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_acc_transaction = p_orderAccTransaction;
    order_acc_transaction_is_set = true;
    order_acc_transaction_is_modified = true;
  }


  /** 
   * <em>order_accept_status</em>カラムの値を設定します。 
   *
   * @@param p_orderAcceptStatus <em>order_accept_status</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOrderAcceptStatus( String p_orderAcceptStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_accept_status = p_orderAcceptStatus;
    order_accept_status_is_set = true;
    order_accept_status_is_modified = true;
  }


  /** 
   * <em>order_accept_status_before</em>カラムの値を設定します。 
   *
   * @@param p_orderAcceptStatusBefore <em>order_accept_status_before</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOrderAcceptStatusBefore( String p_orderAcceptStatusBefore )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_accept_status_before = p_orderAcceptStatusBefore;
    order_accept_status_before_is_set = true;
    order_accept_status_before_is_modified = true;
  }


  /** 
   * <em>last_updater</em>カラムの値を設定します。 
   *
   * @@param p_lastUpdater <em>last_updater</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setLastUpdater( String p_lastUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updater = p_lastUpdater;
    last_updater_is_set = true;
    last_updater_is_modified = true;
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
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
                    return this.last_updater;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'o':
                if ( name.equals("order_acc_product") ) {
                    return this.order_acc_product;
                }
                else if ( name.equals("order_acc_transaction") ) {
                    return this.order_acc_transaction;
                }
                else if ( name.equals("order_accept_status") ) {
                    return this.order_accept_status;
                }
                else if ( name.equals("order_accept_status_before") ) {
                    return this.order_accept_status_before;
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
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'last_updater' must be String: '"+value+"' is not." );
                    this.last_updater = (String) value;
                    if (this.last_updater_is_set)
                        this.last_updater_is_modified = true;
                    this.last_updater_is_set = true;
                    return;
                }
                else if ( name.equals("last_updated_timestamp") ) {
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
                if ( name.equals("order_acc_product") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_acc_product' must be String: '"+value+"' is not." );
                    this.order_acc_product = (String) value;
                    if (this.order_acc_product_is_set)
                        this.order_acc_product_is_modified = true;
                    this.order_acc_product_is_set = true;
                    return;
                }
                else if ( name.equals("order_acc_transaction") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_acc_transaction' must be String: '"+value+"' is not." );
                    this.order_acc_transaction = (String) value;
                    if (this.order_acc_transaction_is_set)
                        this.order_acc_transaction_is_modified = true;
                    this.order_acc_transaction_is_set = true;
                    return;
                }
                else if ( name.equals("order_accept_status") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_accept_status' must be String: '"+value+"' is not." );
                    this.order_accept_status = (String) value;
                    if (this.order_accept_status_is_set)
                        this.order_accept_status_is_modified = true;
                    this.order_accept_status_is_set = true;
                    return;
                }
                else if ( name.equals("order_accept_status_before") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_accept_status_before' must be String: '"+value+"' is not." );
                    this.order_accept_status_before = (String) value;
                    if (this.order_accept_status_before_is_set)
                        this.order_accept_status_before_is_modified = true;
                    this.order_accept_status_before_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
