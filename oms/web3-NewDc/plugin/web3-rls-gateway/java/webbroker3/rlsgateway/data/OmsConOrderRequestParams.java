head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.24.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	OmsConOrderRequestParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.rlsgateway.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * oms_con_order_requestテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link OmsConOrderRequestRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link OmsConOrderRequestParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link OmsConOrderRequestParams}が{@@link OmsConOrderRequestRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see OmsConOrderRequestPK 
 * @@see OmsConOrderRequestRow 
 */
public class OmsConOrderRequestParams extends Params implements OmsConOrderRequestRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "oms_con_order_request";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = OmsConOrderRequestRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return OmsConOrderRequestRow.TYPE;
  }


  /** 
   * <em>account_id</em>カラムの値 
   */
  public  long  account_id;

  /** 
   * <em>sub_account_id</em>カラムの値 
   */
  public  long  sub_account_id;

  /** 
   * <em>order_type</em>カラムの値 
   */
  public  int  order_type;

  /** 
   * <em>request_type</em>カラムの値 
   */
  public  int  request_type;

  /** 
   * <em>order_id</em>カラムの値 
   */
  public  long  order_id;

  /** 
   * <em>product_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum  product_type;

  /** 
   * <em>sub_order_id</em>カラムの値 
   */
  public  long  sub_order_id;

  /** 
   * <em>sub_product_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum  sub_product_type;

  /** 
   * <em>serial_no_in_parent</em>カラムの値 
   */
  public  int  serial_no_in_parent;

  /** 
   * <em>status</em>カラムの値 
   */
  public  String  status;

  /** 
   * <em>rls_accepted_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  rls_accepted_timestamp;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean sub_account_id_is_set = false;
  private boolean sub_account_id_is_modified = false;
  private boolean order_type_is_set = false;
  private boolean order_type_is_modified = false;
  private boolean request_type_is_set = false;
  private boolean request_type_is_modified = false;
  private boolean order_id_is_set = false;
  private boolean order_id_is_modified = false;
  private boolean product_type_is_set = false;
  private boolean product_type_is_modified = false;
  private boolean sub_order_id_is_set = false;
  private boolean sub_order_id_is_modified = false;
  private boolean sub_product_type_is_set = false;
  private boolean sub_product_type_is_modified = false;
  private boolean serial_no_in_parent_is_set = false;
  private boolean serial_no_in_parent_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean rls_accepted_timestamp_is_set = false;
  private boolean rls_accepted_timestamp_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "account_id=" + account_id
      + "," + "sub_account_id=" + sub_account_id
      + "," + "order_type=" + order_type
      + "," + "request_type=" + request_type
      + "," + "order_id=" + order_id
      + "," + "product_type=" + product_type
      + "," + "sub_order_id=" + sub_order_id
      + "," + "sub_product_type=" + sub_product_type
      + "," + "serial_no_in_parent=" +serial_no_in_parent
      + "," + "status=" +status
      + "," + "rls_accepted_timestamp=" +rls_accepted_timestamp
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のOmsConOrderRequestParamsオブジェクトを作成します。 
   */
  public OmsConOrderRequestParams() {
    account_id_is_modified = true;
    sub_account_id_is_modified = true;
    order_type_is_modified = true;
    request_type_is_modified = true;
    order_id_is_modified = true;
    product_type_is_modified = true;
    sub_order_id_is_modified = true;
    sub_product_type_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のOmsConOrderRequestRowオブジェクトの値を利用してOmsConOrderRequestParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するOmsConOrderRequestRowオブジェクト 
   */
  public OmsConOrderRequestParams( OmsConOrderRequestRow p_row )
  {
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    sub_account_id = p_row.getSubAccountId();
    sub_account_id_is_set = p_row.getSubAccountIdIsSet();
    sub_account_id_is_modified = p_row.getSubAccountIdIsModified();
    order_type = p_row.getOrderType();
    order_type_is_set = p_row.getOrderTypeIsSet();
    order_type_is_modified = p_row.getOrderTypeIsModified();
    request_type = p_row.getRequestType();
    request_type_is_set = p_row.getRequestTypeIsSet();
    request_type_is_modified = p_row.getRequestTypeIsModified();
    order_id = p_row.getOrderId();
    order_id_is_set = p_row.getOrderIdIsSet();
    order_id_is_modified = p_row.getOrderIdIsModified();
    product_type = p_row.getProductType();
    product_type_is_set = p_row.getProductTypeIsSet();
    product_type_is_modified = p_row.getProductTypeIsModified();
    sub_order_id = p_row.getSubOrderId();
    sub_order_id_is_set = p_row.getSubOrderIdIsSet();
    sub_order_id_is_modified = p_row.getSubOrderIdIsModified();
    sub_product_type = p_row.getSubProductType();
    sub_product_type_is_set = p_row.getSubProductTypeIsSet();
    sub_product_type_is_modified = p_row.getSubProductTypeIsModified();
    serial_no_in_parent = p_row.getSerialNoInParent();
    serial_no_in_parent_is_set = p_row.getSerialNoInParentIsSet();
    serial_no_in_parent_is_modified = p_row.getSerialNoInParentIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
    rls_accepted_timestamp = p_row.getRlsAcceptedTimestamp();
    rls_accepted_timestamp_is_set = p_row.getRlsAcceptedTimestampIsSet();
    rls_accepted_timestamp_is_modified = p_row.getRlsAcceptedTimestampIsModified();
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
      serial_no_in_parent_is_set = true;
      serial_no_in_parent_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
      rls_accepted_timestamp_is_set = true;
      rls_accepted_timestamp_is_modified = true;
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
    if ( !( other instanceof OmsConOrderRequestRow ) )
       return false;
    return fieldsEqual( (OmsConOrderRequestRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のOmsConOrderRequestRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( OmsConOrderRequestRow row )
  {
    if ( account_id != row.getAccountId() )
      return false;
    if ( sub_account_id != row.getSubAccountId() )
      return false;
    if ( order_type != row.getOrderType() )
      return false;
    if ( request_type != row.getRequestType() )
      return false;
    if ( order_id != row.getOrderId() )
      return false;
    if ( product_type == null ) {
      if ( row.getProductType() != null )
        return false;
    } else if ( !product_type.equals( row.getProductType() ) ) {
        return false;
    }
    if ( sub_order_id != row.getSubOrderId() )
      return false;
    if ( sub_product_type == null ) {
      if ( row.getSubProductType() != null )
        return false;
    } else if ( !sub_product_type.equals( row.getSubProductType() ) ) {
        return false;
    }
    if ( serial_no_in_parent != row.getSerialNoInParent() )
      return false;
    if ( status == null ) {
      if ( row.getStatus() != null )
        return false;
    } else if ( !status.equals( row.getStatus() ) ) {
        return false;
    }
    if ( rls_accepted_timestamp == null ) {
      if ( row.getRlsAcceptedTimestamp() != null )
        return false;
    } else if ( !rls_accepted_timestamp.equals( row.getRlsAcceptedTimestamp() ) ) {
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
      return  ((int) account_id)
        + ((int) sub_account_id)
        + ((int) order_type)
        + ((int) request_type)
        + ((int) order_id)
        + (product_type!=null? product_type.hashCode(): 0) 
        + ((int) sub_order_id)
        + (sub_product_type!=null? sub_product_type.hashCode(): 0) 
        + ((int) serial_no_in_parent)
        + (status!=null? status.hashCode(): 0) 
        + (rls_accepted_timestamp!=null? rls_accepted_timestamp.hashCode(): 0) 
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
		map.put("account_id",new Long(account_id));
		map.put("sub_account_id",new Long(sub_account_id));
		map.put("order_type",new Integer(order_type));
		map.put("request_type",new Integer(request_type));
		map.put("order_id",new Long(order_id));
		map.put("product_type",product_type);
		if ( sub_order_id_is_set )
			map.put("sub_order_id",new Long(sub_order_id));
		if ( sub_product_type_is_set )
			map.put("sub_product_type",sub_product_type);
		if ( serial_no_in_parent_is_set )
			map.put("serial_no_in_parent",new Integer(serial_no_in_parent));
		if ( status_is_set )
			map.put("status",status);
		if ( rls_accepted_timestamp != null )
			map.put("rls_accepted_timestamp",rls_accepted_timestamp);
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
		if ( serial_no_in_parent_is_modified )
			map.put("serial_no_in_parent",new Integer(serial_no_in_parent));
		if ( status_is_modified )
			map.put("status",status);
		if ( rls_accepted_timestamp_is_modified )
			map.put("rls_accepted_timestamp",rls_accepted_timestamp);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( serial_no_in_parent_is_set )
				map.put("serial_no_in_parent",new Integer(serial_no_in_parent));
			if ( status_is_set )
				map.put("status",status);
			map.put("rls_accepted_timestamp",rls_accepted_timestamp);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>account_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAccountId()
  {
    return account_id;
  }


  /** 
   * <em>account_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountIdIsSet() {
    return account_id_is_set;
  }


  /** 
   * <em>account_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountIdIsModified() {
    return account_id_is_modified;
  }


  /** 
   * <em>sub_account_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getSubAccountId()
  {
    return sub_account_id;
  }


  /** 
   * <em>sub_account_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubAccountIdIsSet() {
    return sub_account_id_is_set;
  }


  /** 
   * <em>sub_account_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubAccountIdIsModified() {
    return sub_account_id_is_modified;
  }


  /** 
   * <em>order_type</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getOrderType()
  {
    return order_type;
  }


  /** 
   * <em>order_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderTypeIsSet() {
    return order_type_is_set;
  }


  /** 
   * <em>order_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderTypeIsModified() {
    return order_type_is_modified;
  }


  /** 
   * <em>request_type</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getRequestType()
  {
    return request_type;
  }


  /** 
   * <em>request_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRequestTypeIsSet() {
    return request_type_is_set;
  }


  /** 
   * <em>request_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRequestTypeIsModified() {
    return request_type_is_modified;
  }


  /** 
   * <em>order_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getOrderId()
  {
    return order_id;
  }


  /** 
   * <em>order_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderIdIsSet() {
    return order_id_is_set;
  }


  /** 
   * <em>order_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderIdIsModified() {
    return order_id_is_modified;
  }


  /** 
   * <em>product_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum getProductType()
  {
    return product_type;
  }


  /** 
   * <em>product_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductTypeIsSet() {
    return product_type_is_set;
  }


  /** 
   * <em>product_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductTypeIsModified() {
    return product_type_is_modified;
  }


  /** 
   * <em>sub_order_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getSubOrderId()
  {
    return sub_order_id;
  }


  /** 
   * <em>sub_order_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubOrderIdIsSet() {
    return sub_order_id_is_set;
  }


  /** 
   * <em>sub_order_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubOrderIdIsModified() {
    return sub_order_id_is_modified;
  }


  /** 
   * <em>sub_product_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum getSubProductType()
  {
    return sub_product_type;
  }


  /** 
   * <em>sub_product_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubProductTypeIsSet() {
    return sub_product_type_is_set;
  }


  /** 
   * <em>sub_product_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubProductTypeIsModified() {
    return sub_product_type_is_modified;
  }


  /** 
   * <em>serial_no_in_parent</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getSerialNoInParent()
  {
    return serial_no_in_parent;
  }


  /** 
   * <em>serial_no_in_parent</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSerialNoInParentIsSet() {
    return serial_no_in_parent_is_set;
  }


  /** 
   * <em>serial_no_in_parent</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSerialNoInParentIsModified() {
    return serial_no_in_parent_is_modified;
  }


  /** 
   * <em>status</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStatus()
  {
    return status;
  }


  /** 
   * <em>status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStatusIsSet() {
    return status_is_set;
  }


  /** 
   * <em>status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStatusIsModified() {
    return status_is_modified;
  }


  /** 
   * <em>rls_accepted_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getRlsAcceptedTimestamp()
  {
    return rls_accepted_timestamp;
  }


  /** 
   * <em>rls_accepted_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRlsAcceptedTimestampIsSet() {
    return rls_accepted_timestamp_is_set;
  }


  /** 
   * <em>rls_accepted_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRlsAcceptedTimestampIsModified() {
    return rls_accepted_timestamp_is_modified;
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
    return new OmsConOrderRequestPK(account_id, sub_account_id, order_type, request_type, order_id, product_type, sub_order_id, sub_product_type);
  }


  /** 
   * <em>account_id</em>カラムの値を設定します。 
   *
   * @@param p_accountId <em>account_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setAccountId( long p_accountId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_id = p_accountId;
    account_id_is_set = true;
    account_id_is_modified = true;
  }


  /** 
   * <em>sub_account_id</em>カラムの値を設定します。 
   *
   * @@param p_subAccountId <em>sub_account_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setSubAccountId( long p_subAccountId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sub_account_id = p_subAccountId;
    sub_account_id_is_set = true;
    sub_account_id_is_modified = true;
  }


  /** 
   * <em>order_type</em>カラムの値を設定します。 
   *
   * @@param p_orderType <em>order_type</em>カラムの値をあらわす6桁以下のint値 
   */
  public final void setOrderType( int p_orderType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_type = p_orderType;
    order_type_is_set = true;
    order_type_is_modified = true;
  }


  /** 
   * <em>request_type</em>カラムの値を設定します。 
   *
   * @@param p_requestType <em>request_type</em>カラムの値をあらわす6桁以下のint値 
   */
  public final void setRequestType( int p_requestType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    request_type = p_requestType;
    request_type_is_set = true;
    request_type_is_modified = true;
  }


  /** 
   * <em>order_id</em>カラムの値を設定します。 
   *
   * @@param p_orderId <em>order_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setOrderId( long p_orderId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_id = p_orderId;
    order_id_is_set = true;
    order_id_is_modified = true;
  }


  /** 
   * <em>product_type</em>カラムの値を設定します。 
   *
   * @@param p_productType <em>product_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum値 
   */
  public final void setProductType( com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_type = p_productType;
    product_type_is_set = true;
    product_type_is_modified = true;
  }


  /** 
   * <em>sub_order_id</em>カラムの値を設定します。 
   *
   * @@param p_subOrderId <em>sub_order_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setSubOrderId( long p_subOrderId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sub_order_id = p_subOrderId;
    sub_order_id_is_set = true;
    sub_order_id_is_modified = true;
  }


  /** 
   * <em>sub_product_type</em>カラムの値を設定します。 
   *
   * @@param p_subProductType <em>sub_product_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum値 
   */
  public final void setSubProductType( com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_subProductType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sub_product_type = p_subProductType;
    sub_product_type_is_set = true;
    sub_product_type_is_modified = true;
  }


  /** 
   * <em>serial_no_in_parent</em>カラムの値を設定します。 
   *
   * @@param p_serialNoInParent <em>serial_no_in_parent</em>カラムの値をあらわす8桁以下のint値 
   */
  public final void setSerialNoInParent( int p_serialNoInParent )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    serial_no_in_parent = p_serialNoInParent;
    serial_no_in_parent_is_set = true;
    serial_no_in_parent_is_modified = true;
  }


  /** 
   * <em>status</em>カラムの値を設定します。 
   *
   * @@param p_status <em>status</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setStatus( String p_status )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    status = p_status;
    status_is_set = true;
    status_is_modified = true;
  }


  /** 
   * <em>rls_accepted_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_rlsAcceptedTimestamp <em>rls_accepted_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setRlsAcceptedTimestamp( java.sql.Timestamp p_rlsAcceptedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    rls_accepted_timestamp = p_rlsAcceptedTimestamp;
    rls_accepted_timestamp_is_set = true;
    rls_accepted_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>rls_accepted_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setRlsAcceptedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    rls_accepted_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    rls_accepted_timestamp_is_set = true;
    rls_accepted_timestamp_is_modified = true;
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
            case 'a':
                if ( name.equals("account_id") ) {
                    return new Long( account_id );
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'o':
                if ( name.equals("order_type") ) {
                    return new Integer( order_type );
                }
                else if ( name.equals("order_id") ) {
                    return new Long( order_id );
                }
                break;
            case 'p':
                if ( name.equals("product_type") ) {
                    return this.product_type;
                }
                break;
            case 'r':
                if ( name.equals("request_type") ) {
                    return new Integer( request_type );
                }
                else if ( name.equals("rls_accepted_timestamp") ) {
                    return this.rls_accepted_timestamp;
                }
                break;
            case 's':
                if ( name.equals("sub_account_id") ) {
                    return new Long( sub_account_id );
                }
                else if ( name.equals("sub_order_id") ) {
                    return new Long( sub_order_id );
                }
                else if ( name.equals("sub_product_type") ) {
                    return this.sub_product_type;
                }
                else if ( name.equals("serial_no_in_parent") ) {
                    return new Integer( serial_no_in_parent );
                }
                else if ( name.equals("status") ) {
                    return this.status;
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
                if ( name.equals("account_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'account_id' must be Long: '"+value+"' is not." );
                    this.account_id = ((Long) value).longValue();
                    if (this.account_id_is_set)
                        this.account_id_is_modified = true;
                    this.account_id_is_set = true;
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
            case 'o':
                if ( name.equals("order_type") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'order_type' must be Integer: '"+value+"' is not." );
                    this.order_type = ((Integer) value).intValue();
                    if (this.order_type_is_set)
                        this.order_type_is_modified = true;
                    this.order_type_is_set = true;
                    return;
                }
                else if ( name.equals("order_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'order_id' must be Long: '"+value+"' is not." );
                    this.order_id = ((Long) value).longValue();
                    if (this.order_id_is_set)
                        this.order_id_is_modified = true;
                    this.order_id_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("product_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) )
                        throw new IllegalArgumentException( "value for 'product_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum: '"+value+"' is not." );
                    this.product_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) value;
                    if (this.product_type_is_set)
                        this.product_type_is_modified = true;
                    this.product_type_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("request_type") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'request_type' must be Integer: '"+value+"' is not." );
                    this.request_type = ((Integer) value).intValue();
                    if (this.request_type_is_set)
                        this.request_type_is_modified = true;
                    this.request_type_is_set = true;
                    return;
                }
                else if ( name.equals("rls_accepted_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'rls_accepted_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.rls_accepted_timestamp = (java.sql.Timestamp) value;
                    if (this.rls_accepted_timestamp_is_set)
                        this.rls_accepted_timestamp_is_modified = true;
                    this.rls_accepted_timestamp_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sub_account_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'sub_account_id' must be Long: '"+value+"' is not." );
                    this.sub_account_id = ((Long) value).longValue();
                    if (this.sub_account_id_is_set)
                        this.sub_account_id_is_modified = true;
                    this.sub_account_id_is_set = true;
                    return;
                }
                else if ( name.equals("sub_order_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'sub_order_id' must be Long: '"+value+"' is not." );
                    this.sub_order_id = ((Long) value).longValue();
                    if (this.sub_order_id_is_set)
                        this.sub_order_id_is_modified = true;
                    this.sub_order_id_is_set = true;
                    return;
                }
                else if ( name.equals("sub_product_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) )
                        throw new IllegalArgumentException( "value for 'sub_product_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum: '"+value+"' is not." );
                    this.sub_product_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) value;
                    if (this.sub_product_type_is_set)
                        this.sub_product_type_is_modified = true;
                    this.sub_product_type_is_set = true;
                    return;
                }
                else if ( name.equals("serial_no_in_parent") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'serial_no_in_parent' must be Integer: '"+value+"' is not." );
                    this.serial_no_in_parent = ((Integer) value).intValue();
                    if (this.serial_no_in_parent_is_set)
                        this.serial_no_in_parent_is_modified = true;
                    this.serial_no_in_parent_is_set = true;
                    return;
                }
                else if ( name.equals("status") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'status' must be String: '"+value+"' is not." );
                    this.status = (String) value;
                    if (this.status_is_set)
                        this.status_is_modified = true;
                    this.status_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
