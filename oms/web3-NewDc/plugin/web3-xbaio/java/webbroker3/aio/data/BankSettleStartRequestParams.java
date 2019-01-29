head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.45.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	BankSettleStartRequestParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * bank_settle_start_requestテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link BankSettleStartRequestRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link BankSettleStartRequestParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link BankSettleStartRequestParams}が{@@link BankSettleStartRequestRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BankSettleStartRequestPK 
 * @@see BankSettleStartRequestRow 
 */
public class BankSettleStartRequestParams extends Params implements BankSettleStartRequestRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "bank_settle_start_request";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = BankSettleStartRequestRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return BankSettleStartRequestRow.TYPE;
  }


  /** 
   * <em>rowid</em>カラムの値 
   */
  public  String  rowid;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  String  created_timestamp;

  /** 
   * <em>protocol_version</em>カラムの値 
   */
  public  String  protocol_version;

  /** 
   * <em>linked_1</em>カラムの値 
   */
  public  String  linked_1;

  /** 
   * <em>shop_id</em>カラムの値 
   */
  public  String  shop_id;

  /** 
   * <em>order_date_time</em>カラムの値 
   */
  public  String  order_date_time;

  /** 
   * <em>center_pay_id</em>カラムの値 
   */
  public  String  center_pay_id;

  /** 
   * <em>pay_scheme_id</em>カラムの値 
   */
  public  String  pay_scheme_id;

  /** 
   * <em>access_key</em>カラムの値 
   */
  public  String  access_key;

  /** 
   * <em>pay_status</em>カラムの値 
   */
  public  String  pay_status;

  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean protocol_version_is_set = false;
  private boolean protocol_version_is_modified = false;
  private boolean linked_1_is_set = false;
  private boolean linked_1_is_modified = false;
  private boolean shop_id_is_set = false;
  private boolean shop_id_is_modified = false;
  private boolean order_date_time_is_set = false;
  private boolean order_date_time_is_modified = false;
  private boolean center_pay_id_is_set = false;
  private boolean center_pay_id_is_modified = false;
  private boolean pay_scheme_id_is_set = false;
  private boolean pay_scheme_id_is_modified = false;
  private boolean access_key_is_set = false;
  private boolean access_key_is_modified = false;
  private boolean pay_status_is_set = false;
  private boolean pay_status_is_modified = false;
  private boolean rowid_is_set = false;
  private boolean rowid_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "rowid=" + rowid
      + "," + "created_timestamp=" +created_timestamp
      + "," + "protocol_version=" +protocol_version
      + "," + "linked_1=" +linked_1
      + "," + "shop_id=" +shop_id
      + "," + "order_date_time=" +order_date_time
      + "," + "center_pay_id=" +center_pay_id
      + "," + "pay_scheme_id=" +pay_scheme_id
      + "," + "access_key=" +access_key
      + "," + "pay_status=" +pay_status
      + "}";
  }


  /** 
   * 値が未設定のBankSettleStartRequestParamsオブジェクトを作成します。 
   */
  public BankSettleStartRequestParams() {
    rowid_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のBankSettleStartRequestRowオブジェクトの値を利用してBankSettleStartRequestParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するBankSettleStartRequestRowオブジェクト 
   */
  public BankSettleStartRequestParams( BankSettleStartRequestRow p_row )
  {
    rowid = p_row.getRowid();
    rowid_is_set = p_row.getRowidIsSet();
    rowid_is_modified = p_row.getRowidIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    protocol_version = p_row.getProtocolVersion();
    protocol_version_is_set = p_row.getProtocolVersionIsSet();
    protocol_version_is_modified = p_row.getProtocolVersionIsModified();
    linked_1 = p_row.getLinked1();
    linked_1_is_set = p_row.getLinked1IsSet();
    linked_1_is_modified = p_row.getLinked1IsModified();
    shop_id = p_row.getShopId();
    shop_id_is_set = p_row.getShopIdIsSet();
    shop_id_is_modified = p_row.getShopIdIsModified();
    order_date_time = p_row.getOrderDateTime();
    order_date_time_is_set = p_row.getOrderDateTimeIsSet();
    order_date_time_is_modified = p_row.getOrderDateTimeIsModified();
    center_pay_id = p_row.getCenterPayId();
    center_pay_id_is_set = p_row.getCenterPayIdIsSet();
    center_pay_id_is_modified = p_row.getCenterPayIdIsModified();
    pay_scheme_id = p_row.getPaySchemeId();
    pay_scheme_id_is_set = p_row.getPaySchemeIdIsSet();
    pay_scheme_id_is_modified = p_row.getPaySchemeIdIsModified();
    access_key = p_row.getAccessKey();
    access_key_is_set = p_row.getAccessKeyIsSet();
    access_key_is_modified = p_row.getAccessKeyIsModified();
    pay_status = p_row.getPayStatus();
    pay_status_is_set = p_row.getPayStatusIsSet();
    pay_status_is_modified = p_row.getPayStatusIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      protocol_version_is_set = true;
      protocol_version_is_modified = true;
      linked_1_is_set = true;
      linked_1_is_modified = true;
      shop_id_is_set = true;
      shop_id_is_modified = true;
      order_date_time_is_set = true;
      order_date_time_is_modified = true;
      center_pay_id_is_set = true;
      center_pay_id_is_modified = true;
      pay_scheme_id_is_set = true;
      pay_scheme_id_is_modified = true;
      access_key_is_set = true;
      access_key_is_modified = true;
      pay_status_is_set = true;
      pay_status_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof BankSettleStartRequestRow ) )
       return false;
    return fieldsEqual( (BankSettleStartRequestRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のBankSettleStartRequestRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( BankSettleStartRequestRow row )
  {
    if ( created_timestamp == null ) {
      if ( row.getCreatedTimestamp() != null )
        return false;
    } else if ( !created_timestamp.equals( row.getCreatedTimestamp() ) ) {
        return false;
    }
    if ( protocol_version == null ) {
      if ( row.getProtocolVersion() != null )
        return false;
    } else if ( !protocol_version.equals( row.getProtocolVersion() ) ) {
        return false;
    }
    if ( linked_1 == null ) {
      if ( row.getLinked1() != null )
        return false;
    } else if ( !linked_1.equals( row.getLinked1() ) ) {
        return false;
    }
    if ( shop_id == null ) {
      if ( row.getShopId() != null )
        return false;
    } else if ( !shop_id.equals( row.getShopId() ) ) {
        return false;
    }
    if ( order_date_time == null ) {
      if ( row.getOrderDateTime() != null )
        return false;
    } else if ( !order_date_time.equals( row.getOrderDateTime() ) ) {
        return false;
    }
    if ( center_pay_id == null ) {
      if ( row.getCenterPayId() != null )
        return false;
    } else if ( !center_pay_id.equals( row.getCenterPayId() ) ) {
        return false;
    }
    if ( pay_scheme_id == null ) {
      if ( row.getPaySchemeId() != null )
        return false;
    } else if ( !pay_scheme_id.equals( row.getPaySchemeId() ) ) {
        return false;
    }
    if ( access_key == null ) {
      if ( row.getAccessKey() != null )
        return false;
    } else if ( !access_key.equals( row.getAccessKey() ) ) {
        return false;
    }
    if ( pay_status == null ) {
      if ( row.getPayStatus() != null )
        return false;
    } else if ( !pay_status.equals( row.getPayStatus() ) ) {
        return false;
    }
    if ( rowid == null ) {
      if ( row.getRowid() != null )
        return false;
    } else if ( !rowid.equals( row.getRowid() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (protocol_version!=null? protocol_version.hashCode(): 0) 
        + (linked_1!=null? linked_1.hashCode(): 0) 
        + (shop_id!=null? shop_id.hashCode(): 0) 
        + (order_date_time!=null? order_date_time.hashCode(): 0) 
        + (center_pay_id!=null? center_pay_id.hashCode(): 0) 
        + (pay_scheme_id!=null? pay_scheme_id.hashCode(): 0) 
        + (access_key!=null? access_key.hashCode(): 0) 
        + (pay_status!=null? pay_status.hashCode(): 0) 
        + (rowid!=null? rowid.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !created_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'created_timestamp' must be set before inserting.");
		if ( !protocol_version_is_set )
			throw new IllegalArgumentException("non-nullable field 'protocol_version' must be set before inserting.");
		if ( !linked_1_is_set )
			throw new IllegalArgumentException("non-nullable field 'linked_1' must be set before inserting.");
		if ( !shop_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'shop_id' must be set before inserting.");
		if ( !order_date_time_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_date_time' must be set before inserting.");
		if ( !center_pay_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'center_pay_id' must be set before inserting.");
		if ( !pay_scheme_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'pay_scheme_id' must be set before inserting.");
		if ( !access_key_is_set )
			throw new IllegalArgumentException("non-nullable field 'access_key' must be set before inserting.");
		if ( !pay_status_is_set )
			throw new IllegalArgumentException("non-nullable field 'pay_status' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("created_timestamp",created_timestamp);
		map.put("protocol_version",protocol_version);
		map.put("linked_1",linked_1);
		map.put("shop_id",shop_id);
		map.put("order_date_time",order_date_time);
		map.put("center_pay_id",center_pay_id);
		map.put("pay_scheme_id",pay_scheme_id);
		map.put("access_key",access_key);
		map.put("pay_status",pay_status);
		map.put("rowid",rowid);
		map.remove("rowid");
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( protocol_version_is_modified )
			map.put("protocol_version",protocol_version);
		if ( linked_1_is_modified )
			map.put("linked_1",linked_1);
		if ( shop_id_is_modified )
			map.put("shop_id",shop_id);
		if ( order_date_time_is_modified )
			map.put("order_date_time",order_date_time);
		if ( center_pay_id_is_modified )
			map.put("center_pay_id",center_pay_id);
		if ( pay_scheme_id_is_modified )
			map.put("pay_scheme_id",pay_scheme_id);
		if ( access_key_is_modified )
			map.put("access_key",access_key);
		if ( pay_status_is_modified )
			map.put("pay_status",pay_status);
		if (map.size() == 0) {
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( protocol_version_is_set )
				map.put("protocol_version",protocol_version);
			if ( linked_1_is_set )
				map.put("linked_1",linked_1);
			if ( shop_id_is_set )
				map.put("shop_id",shop_id);
			if ( order_date_time_is_set )
				map.put("order_date_time",order_date_time);
			if ( center_pay_id_is_set )
				map.put("center_pay_id",center_pay_id);
			if ( pay_scheme_id_is_set )
				map.put("pay_scheme_id",pay_scheme_id);
			if ( access_key_is_set )
				map.put("access_key",access_key);
			if ( pay_status_is_set )
				map.put("pay_status",pay_status);
		}
		return map;
	}


  /** 
   * <em>created_timestamp</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCreatedTimestamp()
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
   * <em>protocol_version</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getProtocolVersion()
  {
    return protocol_version;
  }


  /** 
   * <em>protocol_version</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProtocolVersionIsSet() {
    return protocol_version_is_set;
  }


  /** 
   * <em>protocol_version</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProtocolVersionIsModified() {
    return protocol_version_is_modified;
  }


  /** 
   * <em>linked_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLinked1()
  {
    return linked_1;
  }


  /** 
   * <em>linked_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLinked1IsSet() {
    return linked_1_is_set;
  }


  /** 
   * <em>linked_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLinked1IsModified() {
    return linked_1_is_modified;
  }


  /** 
   * <em>shop_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getShopId()
  {
    return shop_id;
  }


  /** 
   * <em>shop_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShopIdIsSet() {
    return shop_id_is_set;
  }


  /** 
   * <em>shop_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShopIdIsModified() {
    return shop_id_is_modified;
  }


  /** 
   * <em>order_date_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderDateTime()
  {
    return order_date_time;
  }


  /** 
   * <em>order_date_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderDateTimeIsSet() {
    return order_date_time_is_set;
  }


  /** 
   * <em>order_date_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderDateTimeIsModified() {
    return order_date_time_is_modified;
  }


  /** 
   * <em>center_pay_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCenterPayId()
  {
    return center_pay_id;
  }


  /** 
   * <em>center_pay_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCenterPayIdIsSet() {
    return center_pay_id_is_set;
  }


  /** 
   * <em>center_pay_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCenterPayIdIsModified() {
    return center_pay_id_is_modified;
  }


  /** 
   * <em>pay_scheme_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPaySchemeId()
  {
    return pay_scheme_id;
  }


  /** 
   * <em>pay_scheme_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaySchemeIdIsSet() {
    return pay_scheme_id_is_set;
  }


  /** 
   * <em>pay_scheme_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaySchemeIdIsModified() {
    return pay_scheme_id_is_modified;
  }


  /** 
   * <em>access_key</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccessKey()
  {
    return access_key;
  }


  /** 
   * <em>access_key</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccessKeyIsSet() {
    return access_key_is_set;
  }


  /** 
   * <em>access_key</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccessKeyIsModified() {
    return access_key_is_modified;
  }


  /** 
   * <em>pay_status</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPayStatus()
  {
    return pay_status;
  }


  /** 
   * <em>pay_status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPayStatusIsSet() {
    return pay_status_is_set;
  }


  /** 
   * <em>pay_status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPayStatusIsModified() {
    return pay_status_is_modified;
  }


  /** 
   * <em>rowid</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRowid()
  {
    return rowid;
  }


  /** 
   * <em>rowid</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRowidIsSet() {
    return rowid_is_set;
  }


  /** 
   * <em>rowid</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRowidIsModified() {
    return rowid_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new BankSettleStartRequestPK(rowid);
  }


  /** 
   * <em>created_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_createdTimestamp <em>created_timestamp</em>カラムの値をあらわす14桁以下のString値 
   */
  public final void setCreatedTimestamp( String p_createdTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = p_createdTimestamp;
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


  /** 
   * <em>protocol_version</em>カラムの値を設定します。 
   *
   * @@param p_protocolVersion <em>protocol_version</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setProtocolVersion( String p_protocolVersion )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    protocol_version = p_protocolVersion;
    protocol_version_is_set = true;
    protocol_version_is_modified = true;
  }


  /** 
   * <em>linked_1</em>カラムの値を設定します。 
   *
   * @@param p_linked1 <em>linked_1</em>カラムの値をあらわす80桁以下のString値 
   */
  public final void setLinked1( String p_linked1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    linked_1 = p_linked1;
    linked_1_is_set = true;
    linked_1_is_modified = true;
  }


  /** 
   * <em>shop_id</em>カラムの値を設定します。 
   *
   * @@param p_shopId <em>shop_id</em>カラムの値をあらわす8桁以下のString値 
   */
  public final void setShopId( String p_shopId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    shop_id = p_shopId;
    shop_id_is_set = true;
    shop_id_is_modified = true;
  }


  /** 
   * <em>order_date_time</em>カラムの値を設定します。 
   *
   * @@param p_orderDateTime <em>order_date_time</em>カラムの値をあらわす14桁以下のString値 
   */
  public final void setOrderDateTime( String p_orderDateTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_date_time = p_orderDateTime;
    order_date_time_is_set = true;
    order_date_time_is_modified = true;
  }


  /** 
   * <em>center_pay_id</em>カラムの値を設定します。 
   *
   * @@param p_centerPayId <em>center_pay_id</em>カラムの値をあらわす15桁以下のString値 
   */
  public final void setCenterPayId( String p_centerPayId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    center_pay_id = p_centerPayId;
    center_pay_id_is_set = true;
    center_pay_id_is_modified = true;
  }


  /** 
   * <em>pay_scheme_id</em>カラムの値を設定します。 
   *
   * @@param p_paySchemeId <em>pay_scheme_id</em>カラムの値をあらわす11桁以下のString値 
   */
  public final void setPaySchemeId( String p_paySchemeId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    pay_scheme_id = p_paySchemeId;
    pay_scheme_id_is_set = true;
    pay_scheme_id_is_modified = true;
  }


  /** 
   * <em>access_key</em>カラムの値を設定します。 
   *
   * @@param p_accessKey <em>access_key</em>カラムの値をあらわす8桁以下のString値 
   */
  public final void setAccessKey( String p_accessKey )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    access_key = p_accessKey;
    access_key_is_set = true;
    access_key_is_modified = true;
  }


  /** 
   * <em>pay_status</em>カラムの値を設定します。 
   *
   * @@param p_payStatus <em>pay_status</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setPayStatus( String p_payStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    pay_status = p_payStatus;
    pay_status_is_set = true;
    pay_status_is_modified = true;
  }


  /** 
   * <em>rowid</em>カラムの値を設定します。 
   *
   * @@param p_rowid <em>rowid</em>カラムの値をあらわす20桁以下のString値で、その精度は20桁まで
   */
  public final void setRowid( String p_rowid )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    rowid = p_rowid;
    rowid_is_set = true;
    rowid_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("access_key") ) {
                    return this.access_key;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                else if ( name.equals("center_pay_id") ) {
                    return this.center_pay_id;
                }
                break;
            case 'l':
                if ( name.equals("linked_1") ) {
                    return this.linked_1;
                }
                break;
            case 'o':
                if ( name.equals("order_date_time") ) {
                    return this.order_date_time;
                }
                break;
            case 'p':
                if ( name.equals("protocol_version") ) {
                    return this.protocol_version;
                }
                else if ( name.equals("pay_scheme_id") ) {
                    return this.pay_scheme_id;
                }
                else if ( name.equals("pay_status") ) {
                    return this.pay_status;
                }
                break;
            case 'r':
                if ( name.equals("rowid") ) {
                    return this.rowid;
                }
                break;
            case 's':
                if ( name.equals("shop_id") ) {
                    return this.shop_id;
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
                if ( name.equals("access_key") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'access_key' must be String: '"+value+"' is not." );
                    this.access_key = (String) value;
                    if (this.access_key_is_set)
                        this.access_key_is_modified = true;
                    this.access_key_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be String: '"+value+"' is not." );
                    this.created_timestamp = (String) value;
                    if (this.created_timestamp_is_set)
                        this.created_timestamp_is_modified = true;
                    this.created_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("center_pay_id") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'center_pay_id' must be String: '"+value+"' is not." );
                    this.center_pay_id = (String) value;
                    if (this.center_pay_id_is_set)
                        this.center_pay_id_is_modified = true;
                    this.center_pay_id_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("linked_1") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'linked_1' must be String: '"+value+"' is not." );
                    this.linked_1 = (String) value;
                    if (this.linked_1_is_set)
                        this.linked_1_is_modified = true;
                    this.linked_1_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("order_date_time") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_date_time' must be String: '"+value+"' is not." );
                    this.order_date_time = (String) value;
                    if (this.order_date_time_is_set)
                        this.order_date_time_is_modified = true;
                    this.order_date_time_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("protocol_version") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'protocol_version' must be String: '"+value+"' is not." );
                    this.protocol_version = (String) value;
                    if (this.protocol_version_is_set)
                        this.protocol_version_is_modified = true;
                    this.protocol_version_is_set = true;
                    return;
                }
                else if ( name.equals("pay_scheme_id") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'pay_scheme_id' must be String: '"+value+"' is not." );
                    this.pay_scheme_id = (String) value;
                    if (this.pay_scheme_id_is_set)
                        this.pay_scheme_id_is_modified = true;
                    this.pay_scheme_id_is_set = true;
                    return;
                }
                else if ( name.equals("pay_status") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'pay_status' must be String: '"+value+"' is not." );
                    this.pay_status = (String) value;
                    if (this.pay_status_is_set)
                        this.pay_status_is_modified = true;
                    this.pay_status_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("rowid") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'rowid' must be String: '"+value+"' is not." );
                    this.rowid = (String) value;
                    if (this.rowid_is_set)
                        this.rowid_is_modified = true;
                    this.rowid_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("shop_id") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'shop_id' must be String: '"+value+"' is not." );
                    this.shop_id = (String) value;
                    if (this.shop_id_is_set)
                        this.shop_id_is_modified = true;
                    this.shop_id_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
