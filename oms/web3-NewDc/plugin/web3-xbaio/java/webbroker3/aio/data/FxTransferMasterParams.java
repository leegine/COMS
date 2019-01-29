head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.53.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	FxTransferMasterParams.java;


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
 * fx_transfer_masterテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link FxTransferMasterRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link FxTransferMasterParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link FxTransferMasterParams}が{@@link FxTransferMasterRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see FxTransferMasterPK 
 * @@see FxTransferMasterRow 
 */
public class FxTransferMasterParams extends Params implements FxTransferMasterRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "fx_transfer_master";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = FxTransferMasterRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return FxTransferMasterRow.TYPE;
  }


  /** 
   * <em>fx_system_id</em>カラムの値 
   */
  public  long  fx_system_id;

  /** 
   * <em>transfer_div</em>カラムの値 
   */
  public  String  transfer_div;

  /** 
   * <em>delivery_date_div</em>カラムの値 
   */
  public  String  delivery_date_div;

  /** 
   * <em>order_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum  order_type;

  /** 
   * <em>remark_code</em>カラムの値 
   */
  public  String  remark_code;

  /** 
   * <em>remark_name</em>カラムの値 
   */
  public  String  remark_name;

  /** 
   * <em>io_list_trade_div</em>カラムの値 
   */
  public  String  io_list_trade_div;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean fx_system_id_is_set = false;
  private boolean fx_system_id_is_modified = false;
  private boolean transfer_div_is_set = false;
  private boolean transfer_div_is_modified = false;
  private boolean delivery_date_div_is_set = false;
  private boolean delivery_date_div_is_modified = false;
  private boolean order_type_is_set = false;
  private boolean order_type_is_modified = false;
  private boolean remark_code_is_set = false;
  private boolean remark_code_is_modified = false;
  private boolean remark_name_is_set = false;
  private boolean remark_name_is_modified = false;
  private boolean io_list_trade_div_is_set = false;
  private boolean io_list_trade_div_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "fx_system_id=" + fx_system_id
      + "," + "transfer_div=" + transfer_div
      + "," + "delivery_date_div=" +delivery_date_div
      + "," + "order_type=" +order_type
      + "," + "remark_code=" +remark_code
      + "," + "remark_name=" +remark_name
      + "," + "io_list_trade_div=" +io_list_trade_div
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のFxTransferMasterParamsオブジェクトを作成します。 
   */
  public FxTransferMasterParams() {
    fx_system_id_is_modified = true;
    transfer_div_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のFxTransferMasterRowオブジェクトの値を利用してFxTransferMasterParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するFxTransferMasterRowオブジェクト 
   */
  public FxTransferMasterParams( FxTransferMasterRow p_row )
  {
    fx_system_id = p_row.getFxSystemId();
    fx_system_id_is_set = p_row.getFxSystemIdIsSet();
    fx_system_id_is_modified = p_row.getFxSystemIdIsModified();
    transfer_div = p_row.getTransferDiv();
    transfer_div_is_set = p_row.getTransferDivIsSet();
    transfer_div_is_modified = p_row.getTransferDivIsModified();
    delivery_date_div = p_row.getDeliveryDateDiv();
    delivery_date_div_is_set = p_row.getDeliveryDateDivIsSet();
    delivery_date_div_is_modified = p_row.getDeliveryDateDivIsModified();
    order_type = p_row.getOrderType();
    order_type_is_set = p_row.getOrderTypeIsSet();
    order_type_is_modified = p_row.getOrderTypeIsModified();
    remark_code = p_row.getRemarkCode();
    remark_code_is_set = p_row.getRemarkCodeIsSet();
    remark_code_is_modified = p_row.getRemarkCodeIsModified();
    remark_name = p_row.getRemarkName();
    remark_name_is_set = p_row.getRemarkNameIsSet();
    remark_name_is_modified = p_row.getRemarkNameIsModified();
    io_list_trade_div = p_row.getIoListTradeDiv();
    io_list_trade_div_is_set = p_row.getIoListTradeDivIsSet();
    io_list_trade_div_is_modified = p_row.getIoListTradeDivIsModified();
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
      delivery_date_div_is_set = true;
      delivery_date_div_is_modified = true;
      order_type_is_set = true;
      order_type_is_modified = true;
      remark_code_is_set = true;
      remark_code_is_modified = true;
      remark_name_is_set = true;
      remark_name_is_modified = true;
      io_list_trade_div_is_set = true;
      io_list_trade_div_is_modified = true;
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
    if ( !( other instanceof FxTransferMasterRow ) )
       return false;
    return fieldsEqual( (FxTransferMasterRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のFxTransferMasterRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( FxTransferMasterRow row )
  {
    if ( fx_system_id != row.getFxSystemId() )
      return false;
    if ( transfer_div == null ) {
      if ( row.getTransferDiv() != null )
        return false;
    } else if ( !transfer_div.equals( row.getTransferDiv() ) ) {
        return false;
    }
    if ( delivery_date_div == null ) {
      if ( row.getDeliveryDateDiv() != null )
        return false;
    } else if ( !delivery_date_div.equals( row.getDeliveryDateDiv() ) ) {
        return false;
    }
    if ( order_type == null ) {
      if ( row.getOrderType() != null )
        return false;
    } else if ( !order_type.equals( row.getOrderType() ) ) {
        return false;
    }
    if ( remark_code == null ) {
      if ( row.getRemarkCode() != null )
        return false;
    } else if ( !remark_code.equals( row.getRemarkCode() ) ) {
        return false;
    }
    if ( remark_name == null ) {
      if ( row.getRemarkName() != null )
        return false;
    } else if ( !remark_name.equals( row.getRemarkName() ) ) {
        return false;
    }
    if ( io_list_trade_div == null ) {
      if ( row.getIoListTradeDiv() != null )
        return false;
    } else if ( !io_list_trade_div.equals( row.getIoListTradeDiv() ) ) {
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
      return  ((int) fx_system_id)
        + (transfer_div!=null? transfer_div.hashCode(): 0) 
        + (delivery_date_div!=null? delivery_date_div.hashCode(): 0) 
        + (order_type!=null? order_type.hashCode(): 0) 
        + (remark_code!=null? remark_code.hashCode(): 0) 
        + (remark_name!=null? remark_name.hashCode(): 0) 
        + (io_list_trade_div!=null? io_list_trade_div.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !delivery_date_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'delivery_date_div' must be set before inserting.");
		if ( !order_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_type' must be set before inserting.");
		if ( !remark_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'remark_code' must be set before inserting.");
		if ( !created_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'created_timestamp' must be set before inserting.");
		if ( !last_updated_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updated_timestamp' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("fx_system_id",new Long(fx_system_id));
		map.put("transfer_div",transfer_div);
		map.put("delivery_date_div",delivery_date_div);
		map.put("order_type",order_type);
		map.put("remark_code",remark_code);
		if ( remark_name != null )
			map.put("remark_name",remark_name);
		if ( io_list_trade_div != null )
			map.put("io_list_trade_div",io_list_trade_div);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( delivery_date_div_is_modified )
			map.put("delivery_date_div",delivery_date_div);
		if ( order_type_is_modified )
			map.put("order_type",order_type);
		if ( remark_code_is_modified )
			map.put("remark_code",remark_code);
		if ( remark_name_is_modified )
			map.put("remark_name",remark_name);
		if ( io_list_trade_div_is_modified )
			map.put("io_list_trade_div",io_list_trade_div);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( delivery_date_div_is_set )
				map.put("delivery_date_div",delivery_date_div);
			if ( order_type_is_set )
				map.put("order_type",order_type);
			if ( remark_code_is_set )
				map.put("remark_code",remark_code);
			map.put("remark_name",remark_name);
			map.put("io_list_trade_div",io_list_trade_div);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>fx_system_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getFxSystemId()
  {
    return fx_system_id;
  }


  /** 
   * <em>fx_system_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFxSystemIdIsSet() {
    return fx_system_id_is_set;
  }


  /** 
   * <em>fx_system_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFxSystemIdIsModified() {
    return fx_system_id_is_modified;
  }


  /** 
   * <em>transfer_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTransferDiv()
  {
    return transfer_div;
  }


  /** 
   * <em>transfer_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferDivIsSet() {
    return transfer_div_is_set;
  }


  /** 
   * <em>transfer_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferDivIsModified() {
    return transfer_div_is_modified;
  }


  /** 
   * <em>delivery_date_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDeliveryDateDiv()
  {
    return delivery_date_div;
  }


  /** 
   * <em>delivery_date_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveryDateDivIsSet() {
    return delivery_date_div_is_set;
  }


  /** 
   * <em>delivery_date_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveryDateDivIsModified() {
    return delivery_date_div_is_modified;
  }


  /** 
   * <em>order_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum getOrderType()
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
   * <em>remark_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRemarkCode()
  {
    return remark_code;
  }


  /** 
   * <em>remark_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRemarkCodeIsSet() {
    return remark_code_is_set;
  }


  /** 
   * <em>remark_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRemarkCodeIsModified() {
    return remark_code_is_modified;
  }


  /** 
   * <em>remark_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRemarkName()
  {
    return remark_name;
  }


  /** 
   * <em>remark_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRemarkNameIsSet() {
    return remark_name_is_set;
  }


  /** 
   * <em>remark_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRemarkNameIsModified() {
    return remark_name_is_modified;
  }


  /** 
   * <em>io_list_trade_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIoListTradeDiv()
  {
    return io_list_trade_div;
  }


  /** 
   * <em>io_list_trade_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIoListTradeDivIsSet() {
    return io_list_trade_div_is_set;
  }


  /** 
   * <em>io_list_trade_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIoListTradeDivIsModified() {
    return io_list_trade_div_is_modified;
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
    return new FxTransferMasterPK(fx_system_id, transfer_div);
  }


  /** 
   * <em>fx_system_id</em>カラムの値を設定します。 
   *
   * @@param p_fxSystemId <em>fx_system_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setFxSystemId( long p_fxSystemId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fx_system_id = p_fxSystemId;
    fx_system_id_is_set = true;
    fx_system_id_is_modified = true;
  }


  /** 
   * <em>transfer_div</em>カラムの値を設定します。 
   *
   * @@param p_transferDiv <em>transfer_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTransferDiv( String p_transferDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transfer_div = p_transferDiv;
    transfer_div_is_set = true;
    transfer_div_is_modified = true;
  }


  /** 
   * <em>delivery_date_div</em>カラムの値を設定します。 
   *
   * @@param p_deliveryDateDiv <em>delivery_date_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setDeliveryDateDiv( String p_deliveryDateDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delivery_date_div = p_deliveryDateDiv;
    delivery_date_div_is_set = true;
    delivery_date_div_is_modified = true;
  }


  /** 
   * <em>order_type</em>カラムの値を設定します。 
   *
   * @@param p_orderType <em>order_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum値 
   */
  public final void setOrderType( com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum p_orderType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_type = p_orderType;
    order_type_is_set = true;
    order_type_is_modified = true;
  }


  /** 
   * <em>remark_code</em>カラムの値を設定します。 
   *
   * @@param p_remarkCode <em>remark_code</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setRemarkCode( String p_remarkCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    remark_code = p_remarkCode;
    remark_code_is_set = true;
    remark_code_is_modified = true;
  }


  /** 
   * <em>remark_name</em>カラムの値を設定します。 
   *
   * @@param p_remarkName <em>remark_name</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setRemarkName( String p_remarkName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    remark_name = p_remarkName;
    remark_name_is_set = true;
    remark_name_is_modified = true;
  }


  /** 
   * <em>io_list_trade_div</em>カラムの値を設定します。 
   *
   * @@param p_ioListTradeDiv <em>io_list_trade_div</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setIoListTradeDiv( String p_ioListTradeDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    io_list_trade_div = p_ioListTradeDiv;
    io_list_trade_div_is_set = true;
    io_list_trade_div_is_modified = true;
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
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("delivery_date_div") ) {
                    return this.delivery_date_div;
                }
                break;
            case 'f':
                if ( name.equals("fx_system_id") ) {
                    return new Long( fx_system_id );
                }
                break;
            case 'i':
                if ( name.equals("io_list_trade_div") ) {
                    return this.io_list_trade_div;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'o':
                if ( name.equals("order_type") ) {
                    return this.order_type;
                }
                break;
            case 'r':
                if ( name.equals("remark_code") ) {
                    return this.remark_code;
                }
                else if ( name.equals("remark_name") ) {
                    return this.remark_name;
                }
                break;
            case 't':
                if ( name.equals("transfer_div") ) {
                    return this.transfer_div;
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
            case 'd':
                if ( name.equals("delivery_date_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'delivery_date_div' must be String: '"+value+"' is not." );
                    this.delivery_date_div = (String) value;
                    if (this.delivery_date_div_is_set)
                        this.delivery_date_div_is_modified = true;
                    this.delivery_date_div_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("fx_system_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'fx_system_id' must be Long: '"+value+"' is not." );
                    this.fx_system_id = ((Long) value).longValue();
                    if (this.fx_system_id_is_set)
                        this.fx_system_id_is_modified = true;
                    this.fx_system_id_is_set = true;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("io_list_trade_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'io_list_trade_div' must be String: '"+value+"' is not." );
                    this.io_list_trade_div = (String) value;
                    if (this.io_list_trade_div_is_set)
                        this.io_list_trade_div_is_modified = true;
                    this.io_list_trade_div_is_set = true;
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
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum) )
                        throw new IllegalArgumentException( "value for 'order_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum: '"+value+"' is not." );
                    this.order_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum) value;
                    if (this.order_type_is_set)
                        this.order_type_is_modified = true;
                    this.order_type_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("remark_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'remark_code' must be String: '"+value+"' is not." );
                    this.remark_code = (String) value;
                    if (this.remark_code_is_set)
                        this.remark_code_is_modified = true;
                    this.remark_code_is_set = true;
                    return;
                }
                else if ( name.equals("remark_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'remark_name' must be String: '"+value+"' is not." );
                    this.remark_name = (String) value;
                    if (this.remark_name_is_set)
                        this.remark_name_is_modified = true;
                    this.remark_name_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("transfer_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'transfer_div' must be String: '"+value+"' is not." );
                    this.transfer_div = (String) value;
                    if (this.transfer_div_is_set)
                        this.transfer_div_is_modified = true;
                    this.transfer_div_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
