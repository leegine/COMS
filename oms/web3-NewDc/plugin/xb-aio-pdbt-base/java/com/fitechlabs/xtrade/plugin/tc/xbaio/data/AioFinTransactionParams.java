head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.56.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	35c4d88640f7e4e;
filename	AioFinTransactionParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbaio.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * aio_fin_transactionテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link AioFinTransactionRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link AioFinTransactionParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link AioFinTransactionParams}が{@@link AioFinTransactionRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AioFinTransactionPK 
 * @@see AioFinTransactionRow 
 */
public class AioFinTransactionParams extends Params implements AioFinTransactionRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "aio_fin_transaction";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = AioFinTransactionRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return AioFinTransactionRow.TYPE;
  }


  /** 
   * <em>fin_transaction_id</em>カラムの値 
   */
  public  long  fin_transaction_id;

  /** 
   * <em>account_id</em>カラムの値 
   */
  public  long  account_id;

  /** 
   * <em>sub_account_id</em>カラムの値 
   */
  public  long  sub_account_id;

  /** 
   * <em>product_id</em>カラムの値 
   */
  public  long  product_id;

  /** 
   * <em>fin_transaction_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType  fin_transaction_type;

  /** 
   * <em>fin_transaction_categ</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg  fin_transaction_categ;

  /** 
   * <em>fin_transaction_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  fin_transaction_timestamp;

  /** 
   * <em>delivery_date</em>カラムの値 
   */
  public  java.sql.Timestamp  delivery_date;

  /** 
   * <em>net_amount</em>カラムの値 
   */
  public  Double  net_amount;

  /** 
   * <em>product_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum  product_type;

  /** 
   * <em>quantity</em>カラムの値 
   */
  public  Double  quantity;

  /** 
   * <em>order_id</em>カラムの値 
   */
  public  long  order_id;

  /** 
   * <em>order_unit_id</em>カラムの値 
   */
  public  long  order_unit_id;

  /** 
   * <em>commission_fee</em>カラムの値 
   */
  public  Double  commission_fee;

  /** 
   * <em>commission_fee_tax</em>カラムの値 
   */
  public  Double  commission_fee_tax;

  /** 
   * <em>delete_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  delete_flag;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean fin_transaction_id_is_set = false;
  private boolean fin_transaction_id_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean sub_account_id_is_set = false;
  private boolean sub_account_id_is_modified = false;
  private boolean product_id_is_set = false;
  private boolean product_id_is_modified = false;
  private boolean fin_transaction_type_is_set = false;
  private boolean fin_transaction_type_is_modified = false;
  private boolean fin_transaction_categ_is_set = false;
  private boolean fin_transaction_categ_is_modified = false;
  private boolean fin_transaction_timestamp_is_set = false;
  private boolean fin_transaction_timestamp_is_modified = false;
  private boolean delivery_date_is_set = false;
  private boolean delivery_date_is_modified = false;
  private boolean net_amount_is_set = false;
  private boolean net_amount_is_modified = false;
  private boolean product_type_is_set = false;
  private boolean product_type_is_modified = false;
  private boolean quantity_is_set = false;
  private boolean quantity_is_modified = false;
  private boolean order_id_is_set = false;
  private boolean order_id_is_modified = false;
  private boolean order_unit_id_is_set = false;
  private boolean order_unit_id_is_modified = false;
  private boolean commission_fee_is_set = false;
  private boolean commission_fee_is_modified = false;
  private boolean commission_fee_tax_is_set = false;
  private boolean commission_fee_tax_is_modified = false;
  private boolean delete_flag_is_set = false;
  private boolean delete_flag_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "fin_transaction_id=" + fin_transaction_id
      + "," + "account_id=" +account_id
      + "," + "sub_account_id=" +sub_account_id
      + "," + "product_id=" +product_id
      + "," + "fin_transaction_type=" +fin_transaction_type
      + "," + "fin_transaction_categ=" +fin_transaction_categ
      + "," + "fin_transaction_timestamp=" +fin_transaction_timestamp
      + "," + "delivery_date=" +delivery_date
      + "," + "net_amount=" +net_amount
      + "," + "product_type=" +product_type
      + "," + "quantity=" +quantity
      + "," + "order_id=" +order_id
      + "," + "order_unit_id=" +order_unit_id
      + "," + "commission_fee=" +commission_fee
      + "," + "commission_fee_tax=" +commission_fee_tax
      + "," + "delete_flag=" +delete_flag
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のAioFinTransactionParamsオブジェクトを作成します。 
   */
  public AioFinTransactionParams() {
    fin_transaction_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のAioFinTransactionRowオブジェクトの値を利用してAioFinTransactionParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するAioFinTransactionRowオブジェクト 
   */
  public AioFinTransactionParams( AioFinTransactionRow p_row )
  {
    fin_transaction_id = p_row.getFinTransactionId();
    fin_transaction_id_is_set = p_row.getFinTransactionIdIsSet();
    fin_transaction_id_is_modified = p_row.getFinTransactionIdIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    sub_account_id = p_row.getSubAccountId();
    sub_account_id_is_set = p_row.getSubAccountIdIsSet();
    sub_account_id_is_modified = p_row.getSubAccountIdIsModified();
    product_id = p_row.getProductId();
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    fin_transaction_type = p_row.getFinTransactionType();
    fin_transaction_type_is_set = p_row.getFinTransactionTypeIsSet();
    fin_transaction_type_is_modified = p_row.getFinTransactionTypeIsModified();
    fin_transaction_categ = p_row.getFinTransactionCateg();
    fin_transaction_categ_is_set = p_row.getFinTransactionCategIsSet();
    fin_transaction_categ_is_modified = p_row.getFinTransactionCategIsModified();
    fin_transaction_timestamp = p_row.getFinTransactionTimestamp();
    fin_transaction_timestamp_is_set = p_row.getFinTransactionTimestampIsSet();
    fin_transaction_timestamp_is_modified = p_row.getFinTransactionTimestampIsModified();
    delivery_date = p_row.getDeliveryDate();
    delivery_date_is_set = p_row.getDeliveryDateIsSet();
    delivery_date_is_modified = p_row.getDeliveryDateIsModified();
    if ( !p_row.getNetAmountIsNull() )
      net_amount = new Double( p_row.getNetAmount() );
    net_amount_is_set = p_row.getNetAmountIsSet();
    net_amount_is_modified = p_row.getNetAmountIsModified();
    product_type = p_row.getProductType();
    product_type_is_set = p_row.getProductTypeIsSet();
    product_type_is_modified = p_row.getProductTypeIsModified();
    if ( !p_row.getQuantityIsNull() )
      quantity = new Double( p_row.getQuantity() );
    quantity_is_set = p_row.getQuantityIsSet();
    quantity_is_modified = p_row.getQuantityIsModified();
    order_id = p_row.getOrderId();
    order_id_is_set = p_row.getOrderIdIsSet();
    order_id_is_modified = p_row.getOrderIdIsModified();
    order_unit_id = p_row.getOrderUnitId();
    order_unit_id_is_set = p_row.getOrderUnitIdIsSet();
    order_unit_id_is_modified = p_row.getOrderUnitIdIsModified();
    if ( !p_row.getCommissionFeeIsNull() )
      commission_fee = new Double( p_row.getCommissionFee() );
    commission_fee_is_set = p_row.getCommissionFeeIsSet();
    commission_fee_is_modified = p_row.getCommissionFeeIsModified();
    if ( !p_row.getCommissionFeeTaxIsNull() )
      commission_fee_tax = new Double( p_row.getCommissionFeeTax() );
    commission_fee_tax_is_set = p_row.getCommissionFeeTaxIsSet();
    commission_fee_tax_is_modified = p_row.getCommissionFeeTaxIsModified();
    delete_flag = p_row.getDeleteFlag();
    delete_flag_is_set = p_row.getDeleteFlagIsSet();
    delete_flag_is_modified = p_row.getDeleteFlagIsModified();
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
      account_id_is_set = true;
      account_id_is_modified = true;
      sub_account_id_is_set = true;
      sub_account_id_is_modified = true;
      product_id_is_set = true;
      product_id_is_modified = true;
      fin_transaction_type_is_set = true;
      fin_transaction_type_is_modified = true;
      fin_transaction_categ_is_set = true;
      fin_transaction_categ_is_modified = true;
      fin_transaction_timestamp_is_set = true;
      fin_transaction_timestamp_is_modified = true;
      delivery_date_is_set = true;
      delivery_date_is_modified = true;
      net_amount_is_set = true;
      net_amount_is_modified = true;
      product_type_is_set = true;
      product_type_is_modified = true;
      quantity_is_set = true;
      quantity_is_modified = true;
      order_id_is_set = true;
      order_id_is_modified = true;
      order_unit_id_is_set = true;
      order_unit_id_is_modified = true;
      commission_fee_is_set = true;
      commission_fee_is_modified = true;
      commission_fee_tax_is_set = true;
      commission_fee_tax_is_modified = true;
      delete_flag_is_set = true;
      delete_flag_is_modified = true;
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
    if ( !( other instanceof AioFinTransactionRow ) )
       return false;
    return fieldsEqual( (AioFinTransactionRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のAioFinTransactionRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( AioFinTransactionRow row )
  {
    if ( fin_transaction_id != row.getFinTransactionId() )
      return false;
    if ( account_id != row.getAccountId() )
      return false;
    if ( sub_account_id != row.getSubAccountId() )
      return false;
    if ( product_id != row.getProductId() )
      return false;
    if ( fin_transaction_type == null ) {
      if ( row.getFinTransactionType() != null )
        return false;
    } else if ( !fin_transaction_type.equals( row.getFinTransactionType() ) ) {
        return false;
    }
    if ( fin_transaction_categ == null ) {
      if ( row.getFinTransactionCateg() != null )
        return false;
    } else if ( !fin_transaction_categ.equals( row.getFinTransactionCateg() ) ) {
        return false;
    }
    if ( fin_transaction_timestamp == null ) {
      if ( row.getFinTransactionTimestamp() != null )
        return false;
    } else if ( !fin_transaction_timestamp.equals( row.getFinTransactionTimestamp() ) ) {
        return false;
    }
    if ( delivery_date == null ) {
      if ( row.getDeliveryDate() != null )
        return false;
    } else if ( !delivery_date.equals( row.getDeliveryDate() ) ) {
        return false;
    }
    if ( net_amount == null ) {
      if ( !row.getNetAmountIsNull() )
        return false;
    } else if ( row.getNetAmountIsNull() || ( net_amount.doubleValue() != row.getNetAmount() ) ) {
        return false;
    }
    if ( product_type == null ) {
      if ( row.getProductType() != null )
        return false;
    } else if ( !product_type.equals( row.getProductType() ) ) {
        return false;
    }
    if ( quantity == null ) {
      if ( !row.getQuantityIsNull() )
        return false;
    } else if ( row.getQuantityIsNull() || ( quantity.doubleValue() != row.getQuantity() ) ) {
        return false;
    }
    if ( order_id != row.getOrderId() )
      return false;
    if ( order_unit_id != row.getOrderUnitId() )
      return false;
    if ( commission_fee == null ) {
      if ( !row.getCommissionFeeIsNull() )
        return false;
    } else if ( row.getCommissionFeeIsNull() || ( commission_fee.doubleValue() != row.getCommissionFee() ) ) {
        return false;
    }
    if ( commission_fee_tax == null ) {
      if ( !row.getCommissionFeeTaxIsNull() )
        return false;
    } else if ( row.getCommissionFeeTaxIsNull() || ( commission_fee_tax.doubleValue() != row.getCommissionFeeTax() ) ) {
        return false;
    }
    if ( delete_flag == null ) {
      if ( row.getDeleteFlag() != null )
        return false;
    } else if ( !delete_flag.equals( row.getDeleteFlag() ) ) {
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
      return  ((int) fin_transaction_id)
        + ((int) account_id)
        + ((int) sub_account_id)
        + ((int) product_id)
        + (fin_transaction_type!=null? fin_transaction_type.hashCode(): 0) 
        + (fin_transaction_categ!=null? fin_transaction_categ.hashCode(): 0) 
        + (fin_transaction_timestamp!=null? fin_transaction_timestamp.hashCode(): 0) 
        + (delivery_date!=null? delivery_date.hashCode(): 0) 
        + (net_amount!=null? net_amount.hashCode(): 0) 
        + (product_type!=null? product_type.hashCode(): 0) 
        + (quantity!=null? quantity.hashCode(): 0) 
        + ((int) order_id)
        + ((int) order_unit_id)
        + (commission_fee!=null? commission_fee.hashCode(): 0) 
        + (commission_fee_tax!=null? commission_fee_tax.hashCode(): 0) 
        + (delete_flag!=null? delete_flag.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_id' must be set before inserting.");
		if ( !sub_account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'sub_account_id' must be set before inserting.");
		if ( !product_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_id' must be set before inserting.");
		if ( !fin_transaction_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'fin_transaction_type' must be set before inserting.");
		if ( !fin_transaction_categ_is_set )
			throw new IllegalArgumentException("non-nullable field 'fin_transaction_categ' must be set before inserting.");
		if ( !product_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_type' must be set before inserting.");
		if ( !order_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_id' must be set before inserting.");
		if ( !order_unit_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_unit_id' must be set before inserting.");
		if ( !delete_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'delete_flag' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("fin_transaction_id",new Long(fin_transaction_id));
		map.put("account_id",new Long(account_id));
		map.put("sub_account_id",new Long(sub_account_id));
		map.put("product_id",new Long(product_id));
		map.put("fin_transaction_type",fin_transaction_type);
		map.put("fin_transaction_categ",fin_transaction_categ);
		if ( fin_transaction_timestamp != null )
			map.put("fin_transaction_timestamp",fin_transaction_timestamp);
		if ( delivery_date != null )
			map.put("delivery_date",delivery_date);
		if ( net_amount != null )
			map.put("net_amount",net_amount);
		map.put("product_type",product_type);
		if ( quantity != null )
			map.put("quantity",quantity);
		map.put("order_id",new Long(order_id));
		map.put("order_unit_id",new Long(order_unit_id));
		if ( commission_fee != null )
			map.put("commission_fee",commission_fee);
		if ( commission_fee_tax != null )
			map.put("commission_fee_tax",commission_fee_tax);
		map.put("delete_flag",delete_flag);
		if ( created_timestamp != null )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp != null )
			map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( account_id_is_modified )
			map.put("account_id",new Long(account_id));
		if ( sub_account_id_is_modified )
			map.put("sub_account_id",new Long(sub_account_id));
		if ( product_id_is_modified )
			map.put("product_id",new Long(product_id));
		if ( fin_transaction_type_is_modified )
			map.put("fin_transaction_type",fin_transaction_type);
		if ( fin_transaction_categ_is_modified )
			map.put("fin_transaction_categ",fin_transaction_categ);
		if ( fin_transaction_timestamp_is_modified )
			map.put("fin_transaction_timestamp",fin_transaction_timestamp);
		if ( delivery_date_is_modified )
			map.put("delivery_date",delivery_date);
		if ( net_amount_is_modified )
			map.put("net_amount",net_amount);
		if ( product_type_is_modified )
			map.put("product_type",product_type);
		if ( quantity_is_modified )
			map.put("quantity",quantity);
		if ( order_id_is_modified )
			map.put("order_id",new Long(order_id));
		if ( order_unit_id_is_modified )
			map.put("order_unit_id",new Long(order_unit_id));
		if ( commission_fee_is_modified )
			map.put("commission_fee",commission_fee);
		if ( commission_fee_tax_is_modified )
			map.put("commission_fee_tax",commission_fee_tax);
		if ( delete_flag_is_modified )
			map.put("delete_flag",delete_flag);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			if ( sub_account_id_is_set )
				map.put("sub_account_id",new Long(sub_account_id));
			if ( product_id_is_set )
				map.put("product_id",new Long(product_id));
			if ( fin_transaction_type_is_set )
				map.put("fin_transaction_type",fin_transaction_type);
			if ( fin_transaction_categ_is_set )
				map.put("fin_transaction_categ",fin_transaction_categ);
			map.put("fin_transaction_timestamp",fin_transaction_timestamp);
			map.put("delivery_date",delivery_date);
			map.put("net_amount",net_amount);
			if ( product_type_is_set )
				map.put("product_type",product_type);
			map.put("quantity",quantity);
			if ( order_id_is_set )
				map.put("order_id",new Long(order_id));
			if ( order_unit_id_is_set )
				map.put("order_unit_id",new Long(order_unit_id));
			map.put("commission_fee",commission_fee);
			map.put("commission_fee_tax",commission_fee_tax);
			if ( delete_flag_is_set )
				map.put("delete_flag",delete_flag);
			map.put("created_timestamp",created_timestamp);
			map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>fin_transaction_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getFinTransactionId()
  {
    return fin_transaction_id;
  }


  /** 
   * <em>fin_transaction_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinTransactionIdIsSet() {
    return fin_transaction_id_is_set;
  }


  /** 
   * <em>fin_transaction_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinTransactionIdIsModified() {
    return fin_transaction_id_is_modified;
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
   * <em>product_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getProductId()
  {
    return product_id;
  }


  /** 
   * <em>product_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductIdIsSet() {
    return product_id_is_set;
  }


  /** 
   * <em>product_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductIdIsModified() {
    return product_id_is_modified;
  }


  /** 
   * <em>fin_transaction_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionTypeの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType getFinTransactionType()
  {
    return fin_transaction_type;
  }


  /** 
   * <em>fin_transaction_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinTransactionTypeIsSet() {
    return fin_transaction_type_is_set;
  }


  /** 
   * <em>fin_transaction_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinTransactionTypeIsModified() {
    return fin_transaction_type_is_modified;
  }


  /** 
   * <em>fin_transaction_categ</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCategの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg getFinTransactionCateg()
  {
    return fin_transaction_categ;
  }


  /** 
   * <em>fin_transaction_categ</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinTransactionCategIsSet() {
    return fin_transaction_categ_is_set;
  }


  /** 
   * <em>fin_transaction_categ</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinTransactionCategIsModified() {
    return fin_transaction_categ_is_modified;
  }


  /** 
   * <em>fin_transaction_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getFinTransactionTimestamp()
  {
    return fin_transaction_timestamp;
  }


  /** 
   * <em>fin_transaction_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinTransactionTimestampIsSet() {
    return fin_transaction_timestamp_is_set;
  }


  /** 
   * <em>fin_transaction_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinTransactionTimestampIsModified() {
    return fin_transaction_timestamp_is_modified;
  }


  /** 
   * <em>delivery_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getDeliveryDate()
  {
    return delivery_date;
  }


  /** 
   * <em>delivery_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveryDateIsSet() {
    return delivery_date_is_set;
  }


  /** 
   * <em>delivery_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveryDateIsModified() {
    return delivery_date_is_modified;
  }


  /** 
   * <em>net_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getNetAmount()
  {
    return ( net_amount==null? ((double)0): net_amount.doubleValue() );
  }


  /** 
   * <em>net_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getNetAmountIsNull()
  {
    return net_amount == null;
  }


  /** 
   * <em>net_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNetAmountIsSet() {
    return net_amount_is_set;
  }


  /** 
   * <em>net_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNetAmountIsModified() {
    return net_amount_is_modified;
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
   * <em>quantity</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getQuantity()
  {
    return ( quantity==null? ((double)0): quantity.doubleValue() );
  }


  /** 
   * <em>quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getQuantityIsNull()
  {
    return quantity == null;
  }


  /** 
   * <em>quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getQuantityIsSet() {
    return quantity_is_set;
  }


  /** 
   * <em>quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getQuantityIsModified() {
    return quantity_is_modified;
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
   * <em>order_unit_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getOrderUnitId()
  {
    return order_unit_id;
  }


  /** 
   * <em>order_unit_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderUnitIdIsSet() {
    return order_unit_id_is_set;
  }


  /** 
   * <em>order_unit_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderUnitIdIsModified() {
    return order_unit_id_is_modified;
  }


  /** 
   * <em>commission_fee</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCommissionFee()
  {
    return ( commission_fee==null? ((double)0): commission_fee.doubleValue() );
  }


  /** 
   * <em>commission_fee</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCommissionFeeIsNull()
  {
    return commission_fee == null;
  }


  /** 
   * <em>commission_fee</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionFeeIsSet() {
    return commission_fee_is_set;
  }


  /** 
   * <em>commission_fee</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionFeeIsModified() {
    return commission_fee_is_modified;
  }


  /** 
   * <em>commission_fee_tax</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCommissionFeeTax()
  {
    return ( commission_fee_tax==null? ((double)0): commission_fee_tax.doubleValue() );
  }


  /** 
   * <em>commission_fee_tax</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCommissionFeeTaxIsNull()
  {
    return commission_fee_tax == null;
  }


  /** 
   * <em>commission_fee_tax</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionFeeTaxIsSet() {
    return commission_fee_tax_is_set;
  }


  /** 
   * <em>commission_fee_tax</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionFeeTaxIsModified() {
    return commission_fee_tax_is_modified;
  }


  /** 
   * <em>delete_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getDeleteFlag()
  {
    return delete_flag;
  }


  /** 
   * <em>delete_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeleteFlagIsSet() {
    return delete_flag_is_set;
  }


  /** 
   * <em>delete_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeleteFlagIsModified() {
    return delete_flag_is_modified;
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
    return new AioFinTransactionPK(fin_transaction_id);
  }


  /** 
   * <em>fin_transaction_id</em>カラムの値を設定します。 
   *
   * @@param p_finTransactionId <em>fin_transaction_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setFinTransactionId( long p_finTransactionId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_transaction_id = p_finTransactionId;
    fin_transaction_id_is_set = true;
    fin_transaction_id_is_modified = true;
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
   * <em>product_id</em>カラムの値を設定します。 
   *
   * @@param p_productId <em>product_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setProductId( long p_productId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_id = p_productId;
    product_id_is_set = true;
    product_id_is_modified = true;
  }


  /** 
   * <em>fin_transaction_type</em>カラムの値を設定します。 
   *
   * @@param p_finTransactionType <em>fin_transaction_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType値 
   */
  public final void setFinTransactionType( com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType p_finTransactionType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_transaction_type = p_finTransactionType;
    fin_transaction_type_is_set = true;
    fin_transaction_type_is_modified = true;
  }


  /** 
   * <em>fin_transaction_categ</em>カラムの値を設定します。 
   *
   * @@param p_finTransactionCateg <em>fin_transaction_categ</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg値 
   */
  public final void setFinTransactionCateg( com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg p_finTransactionCateg )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_transaction_categ = p_finTransactionCateg;
    fin_transaction_categ_is_set = true;
    fin_transaction_categ_is_modified = true;
  }


  /** 
   * <em>fin_transaction_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_finTransactionTimestamp <em>fin_transaction_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setFinTransactionTimestamp( java.sql.Timestamp p_finTransactionTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_transaction_timestamp = p_finTransactionTimestamp;
    fin_transaction_timestamp_is_set = true;
    fin_transaction_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>fin_transaction_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setFinTransactionTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    fin_transaction_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    fin_transaction_timestamp_is_set = true;
    fin_transaction_timestamp_is_modified = true;
  }


  /** 
   * <em>delivery_date</em>カラムの値を設定します。 
   *
   * @@param p_deliveryDate <em>delivery_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setDeliveryDate( java.sql.Timestamp p_deliveryDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delivery_date = p_deliveryDate;
    delivery_date_is_set = true;
    delivery_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>delivery_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setDeliveryDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    delivery_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    delivery_date_is_set = true;
    delivery_date_is_modified = true;
  }


  /** 
   * <em>net_amount</em>カラムの値を設定します。 
   *
   * @@param p_netAmount <em>net_amount</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setNetAmount( double p_netAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    net_amount = new Double(p_netAmount);
    net_amount_is_set = true;
    net_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>net_amount</em>カラムに値を設定します。 
   */
  public final void setNetAmount( Double p_netAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    net_amount = p_netAmount;
    net_amount_is_set = true;
    net_amount_is_modified = true;
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
   * <em>quantity</em>カラムの値を設定します。 
   *
   * @@param p_quantity <em>quantity</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setQuantity( double p_quantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    quantity = new Double(p_quantity);
    quantity_is_set = true;
    quantity_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>quantity</em>カラムに値を設定します。 
   */
  public final void setQuantity( Double p_quantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    quantity = p_quantity;
    quantity_is_set = true;
    quantity_is_modified = true;
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
   * <em>order_unit_id</em>カラムの値を設定します。 
   *
   * @@param p_orderUnitId <em>order_unit_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setOrderUnitId( long p_orderUnitId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_unit_id = p_orderUnitId;
    order_unit_id_is_set = true;
    order_unit_id_is_modified = true;
  }


  /** 
   * <em>commission_fee</em>カラムの値を設定します。 
   *
   * @@param p_commissionFee <em>commission_fee</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCommissionFee( double p_commissionFee )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commission_fee = new Double(p_commissionFee);
    commission_fee_is_set = true;
    commission_fee_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>commission_fee</em>カラムに値を設定します。 
   */
  public final void setCommissionFee( Double p_commissionFee )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    commission_fee = p_commissionFee;
    commission_fee_is_set = true;
    commission_fee_is_modified = true;
  }


  /** 
   * <em>commission_fee_tax</em>カラムの値を設定します。 
   *
   * @@param p_commissionFeeTax <em>commission_fee_tax</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCommissionFeeTax( double p_commissionFeeTax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commission_fee_tax = new Double(p_commissionFeeTax);
    commission_fee_tax_is_set = true;
    commission_fee_tax_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>commission_fee_tax</em>カラムに値を設定します。 
   */
  public final void setCommissionFeeTax( Double p_commissionFeeTax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    commission_fee_tax = p_commissionFeeTax;
    commission_fee_tax_is_set = true;
    commission_fee_tax_is_modified = true;
  }


  /** 
   * <em>delete_flag</em>カラムの値を設定します。 
   *
   * @@param p_deleteFlag <em>delete_flag</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setDeleteFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_deleteFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delete_flag = p_deleteFlag;
    delete_flag_is_set = true;
    delete_flag_is_modified = true;
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
                if ( name.equals("commission_fee") ) {
                    return this.commission_fee;
                }
                else if ( name.equals("commission_fee_tax") ) {
                    return this.commission_fee_tax;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("delivery_date") ) {
                    return this.delivery_date;
                }
                else if ( name.equals("delete_flag") ) {
                    return this.delete_flag;
                }
                break;
            case 'f':
                if ( name.equals("fin_transaction_id") ) {
                    return new Long( fin_transaction_id );
                }
                else if ( name.equals("fin_transaction_type") ) {
                    return this.fin_transaction_type;
                }
                else if ( name.equals("fin_transaction_categ") ) {
                    return this.fin_transaction_categ;
                }
                else if ( name.equals("fin_transaction_timestamp") ) {
                    return this.fin_transaction_timestamp;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'n':
                if ( name.equals("net_amount") ) {
                    return this.net_amount;
                }
                break;
            case 'o':
                if ( name.equals("order_id") ) {
                    return new Long( order_id );
                }
                else if ( name.equals("order_unit_id") ) {
                    return new Long( order_unit_id );
                }
                break;
            case 'p':
                if ( name.equals("product_id") ) {
                    return new Long( product_id );
                }
                else if ( name.equals("product_type") ) {
                    return this.product_type;
                }
                break;
            case 'q':
                if ( name.equals("quantity") ) {
                    return this.quantity;
                }
                break;
            case 's':
                if ( name.equals("sub_account_id") ) {
                    return new Long( sub_account_id );
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
                if ( name.equals("commission_fee") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'commission_fee' must be Double: '"+value+"' is not." );
                    this.commission_fee = (Double) value;
                    if (this.commission_fee_is_set)
                        this.commission_fee_is_modified = true;
                    this.commission_fee_is_set = true;
                    return;
                }
                else if ( name.equals("commission_fee_tax") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'commission_fee_tax' must be Double: '"+value+"' is not." );
                    this.commission_fee_tax = (Double) value;
                    if (this.commission_fee_tax_is_set)
                        this.commission_fee_tax_is_modified = true;
                    this.commission_fee_tax_is_set = true;
                    return;
                }
                else if ( name.equals("created_timestamp") ) {
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
            case 'd':
                if ( name.equals("delivery_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'delivery_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.delivery_date = (java.sql.Timestamp) value;
                    if (this.delivery_date_is_set)
                        this.delivery_date_is_modified = true;
                    this.delivery_date_is_set = true;
                    return;
                }
                else if ( name.equals("delete_flag") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'delete_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.delete_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.delete_flag_is_set)
                        this.delete_flag_is_modified = true;
                    this.delete_flag_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("fin_transaction_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'fin_transaction_id' must be Long: '"+value+"' is not." );
                    this.fin_transaction_id = ((Long) value).longValue();
                    if (this.fin_transaction_id_is_set)
                        this.fin_transaction_id_is_modified = true;
                    this.fin_transaction_id_is_set = true;
                    return;
                }
                else if ( name.equals("fin_transaction_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType) )
                        throw new IllegalArgumentException( "value for 'fin_transaction_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType: '"+value+"' is not." );
                    this.fin_transaction_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType) value;
                    if (this.fin_transaction_type_is_set)
                        this.fin_transaction_type_is_modified = true;
                    this.fin_transaction_type_is_set = true;
                    return;
                }
                else if ( name.equals("fin_transaction_categ") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg) )
                        throw new IllegalArgumentException( "value for 'fin_transaction_categ' must be com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg: '"+value+"' is not." );
                    this.fin_transaction_categ = (com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg) value;
                    if (this.fin_transaction_categ_is_set)
                        this.fin_transaction_categ_is_modified = true;
                    this.fin_transaction_categ_is_set = true;
                    return;
                }
                else if ( name.equals("fin_transaction_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'fin_transaction_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.fin_transaction_timestamp = (java.sql.Timestamp) value;
                    if (this.fin_transaction_timestamp_is_set)
                        this.fin_transaction_timestamp_is_modified = true;
                    this.fin_transaction_timestamp_is_set = true;
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
            case 'n':
                if ( name.equals("net_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'net_amount' must be Double: '"+value+"' is not." );
                    this.net_amount = (Double) value;
                    if (this.net_amount_is_set)
                        this.net_amount_is_modified = true;
                    this.net_amount_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("order_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'order_id' must be Long: '"+value+"' is not." );
                    this.order_id = ((Long) value).longValue();
                    if (this.order_id_is_set)
                        this.order_id_is_modified = true;
                    this.order_id_is_set = true;
                    return;
                }
                else if ( name.equals("order_unit_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'order_unit_id' must be Long: '"+value+"' is not." );
                    this.order_unit_id = ((Long) value).longValue();
                    if (this.order_unit_id_is_set)
                        this.order_unit_id_is_modified = true;
                    this.order_unit_id_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("product_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'product_id' must be Long: '"+value+"' is not." );
                    this.product_id = ((Long) value).longValue();
                    if (this.product_id_is_set)
                        this.product_id_is_modified = true;
                    this.product_id_is_set = true;
                    return;
                }
                else if ( name.equals("product_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) )
                        throw new IllegalArgumentException( "value for 'product_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum: '"+value+"' is not." );
                    this.product_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) value;
                    if (this.product_type_is_set)
                        this.product_type_is_modified = true;
                    this.product_type_is_set = true;
                    return;
                }
                break;
            case 'q':
                if ( name.equals("quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'quantity' must be Double: '"+value+"' is not." );
                    this.quantity = (Double) value;
                    if (this.quantity_is_set)
                        this.quantity_is_modified = true;
                    this.quantity_is_set = true;
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
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
