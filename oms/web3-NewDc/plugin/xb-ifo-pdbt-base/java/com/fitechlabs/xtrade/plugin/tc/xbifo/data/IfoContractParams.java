head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.09.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	35c4d88667f0644;
filename	IfoContractParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbifo.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * ifo_contractテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link IfoContractRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link IfoContractParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link IfoContractParams}が{@@link IfoContractRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see IfoContractPK 
 * @@see IfoContractRow 
 */
public class IfoContractParams extends Params implements IfoContractRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "ifo_contract";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = IfoContractRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return IfoContractRow.TYPE;
  }


  /** 
   * <em>contract_id</em>カラムの値 
   */
  public  long  contract_id;

  /** 
   * <em>account_id</em>カラムの値 
   */
  public  long  account_id;

  /** 
   * <em>sub_account_id</em>カラムの値 
   */
  public  long  sub_account_id;

  /** 
   * <em>market_id</em>カラムの値 
   */
  public  long  market_id;

  /** 
   * <em>unit_size</em>カラムの値 
   */
  public  double  unit_size;

  /** 
   * <em>original_quantity</em>カラムの値 
   */
  public  double  original_quantity;

  /** 
   * <em>quantity</em>カラムの値 
   */
  public  double  quantity;

  /** 
   * <em>original_contract_price</em>カラムの値 
   */
  public  double  original_contract_price;

  /** 
   * <em>contract_price</em>カラムの値 
   */
  public  double  contract_price;

  /** 
   * <em>contract_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum  contract_type;

  /** 
   * <em>open_date</em>カラムの値 
   */
  public  java.sql.Timestamp  open_date;

  /** 
   * <em>close_date</em>カラムの値 
   */
  public  java.sql.Timestamp  close_date;

  /** 
   * <em>setup_fee</em>カラムの値 
   */
  public  double  setup_fee;

  /** 
   * <em>setup_fee_tax</em>カラムの値 
   */
  public  double  setup_fee_tax;

  /** 
   * <em>management_fee</em>カラムの値 
   */
  public  double  management_fee;

  /** 
   * <em>management_fee_tax</em>カラムの値 
   */
  public  double  management_fee_tax;

  /** 
   * <em>interest_fee</em>カラムの値 
   */
  public  double  interest_fee;

  /** 
   * <em>interest_fee_tax</em>カラムの値 
   */
  public  double  interest_fee_tax;

  /** 
   * <em>product_id</em>カラムの値 
   */
  public  long  product_id;

  /** 
   * <em>product_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum  product_type;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>delivery_date</em>カラムの値 
   */
  public  java.sql.Timestamp  delivery_date;

  /** 
   * <em>session_type</em>カラムの値 
   */
  public  String  session_type;

  private boolean contract_id_is_set = false;
  private boolean contract_id_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean sub_account_id_is_set = false;
  private boolean sub_account_id_is_modified = false;
  private boolean market_id_is_set = false;
  private boolean market_id_is_modified = false;
  private boolean unit_size_is_set = false;
  private boolean unit_size_is_modified = false;
  private boolean original_quantity_is_set = false;
  private boolean original_quantity_is_modified = false;
  private boolean quantity_is_set = false;
  private boolean quantity_is_modified = false;
  private boolean original_contract_price_is_set = false;
  private boolean original_contract_price_is_modified = false;
  private boolean contract_price_is_set = false;
  private boolean contract_price_is_modified = false;
  private boolean contract_type_is_set = false;
  private boolean contract_type_is_modified = false;
  private boolean open_date_is_set = false;
  private boolean open_date_is_modified = false;
  private boolean close_date_is_set = false;
  private boolean close_date_is_modified = false;
  private boolean setup_fee_is_set = false;
  private boolean setup_fee_is_modified = false;
  private boolean setup_fee_tax_is_set = false;
  private boolean setup_fee_tax_is_modified = false;
  private boolean management_fee_is_set = false;
  private boolean management_fee_is_modified = false;
  private boolean management_fee_tax_is_set = false;
  private boolean management_fee_tax_is_modified = false;
  private boolean interest_fee_is_set = false;
  private boolean interest_fee_is_modified = false;
  private boolean interest_fee_tax_is_set = false;
  private boolean interest_fee_tax_is_modified = false;
  private boolean product_id_is_set = false;
  private boolean product_id_is_modified = false;
  private boolean product_type_is_set = false;
  private boolean product_type_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean delivery_date_is_set = false;
  private boolean delivery_date_is_modified = false;
  private boolean session_type_is_set = false;
  private boolean session_type_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "contract_id=" + contract_id
      + "," + "account_id=" +account_id
      + "," + "sub_account_id=" +sub_account_id
      + "," + "market_id=" +market_id
      + "," + "unit_size=" +unit_size
      + "," + "original_quantity=" +original_quantity
      + "," + "quantity=" +quantity
      + "," + "original_contract_price=" +original_contract_price
      + "," + "contract_price=" +contract_price
      + "," + "contract_type=" +contract_type
      + "," + "open_date=" +open_date
      + "," + "close_date=" +close_date
      + "," + "setup_fee=" +setup_fee
      + "," + "setup_fee_tax=" +setup_fee_tax
      + "," + "management_fee=" +management_fee
      + "," + "management_fee_tax=" +management_fee_tax
      + "," + "interest_fee=" +interest_fee
      + "," + "interest_fee_tax=" +interest_fee_tax
      + "," + "product_id=" +product_id
      + "," + "product_type=" +product_type
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "delivery_date=" +delivery_date
      + "," + "session_type=" +session_type
      + "}";
  }


  /** 
   * 値が未設定のIfoContractParamsオブジェクトを作成します。 
   */
  public IfoContractParams() {
    contract_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のIfoContractRowオブジェクトの値を利用してIfoContractParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するIfoContractRowオブジェクト 
   */
  public IfoContractParams( IfoContractRow p_row )
  {
    contract_id = p_row.getContractId();
    contract_id_is_set = p_row.getContractIdIsSet();
    contract_id_is_modified = p_row.getContractIdIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    sub_account_id = p_row.getSubAccountId();
    sub_account_id_is_set = p_row.getSubAccountIdIsSet();
    sub_account_id_is_modified = p_row.getSubAccountIdIsModified();
    market_id = p_row.getMarketId();
    market_id_is_set = p_row.getMarketIdIsSet();
    market_id_is_modified = p_row.getMarketIdIsModified();
    unit_size = p_row.getUnitSize();
    unit_size_is_set = p_row.getUnitSizeIsSet();
    unit_size_is_modified = p_row.getUnitSizeIsModified();
    original_quantity = p_row.getOriginalQuantity();
    original_quantity_is_set = p_row.getOriginalQuantityIsSet();
    original_quantity_is_modified = p_row.getOriginalQuantityIsModified();
    quantity = p_row.getQuantity();
    quantity_is_set = p_row.getQuantityIsSet();
    quantity_is_modified = p_row.getQuantityIsModified();
    original_contract_price = p_row.getOriginalContractPrice();
    original_contract_price_is_set = p_row.getOriginalContractPriceIsSet();
    original_contract_price_is_modified = p_row.getOriginalContractPriceIsModified();
    contract_price = p_row.getContractPrice();
    contract_price_is_set = p_row.getContractPriceIsSet();
    contract_price_is_modified = p_row.getContractPriceIsModified();
    contract_type = p_row.getContractType();
    contract_type_is_set = p_row.getContractTypeIsSet();
    contract_type_is_modified = p_row.getContractTypeIsModified();
    open_date = p_row.getOpenDate();
    open_date_is_set = p_row.getOpenDateIsSet();
    open_date_is_modified = p_row.getOpenDateIsModified();
    close_date = p_row.getCloseDate();
    close_date_is_set = p_row.getCloseDateIsSet();
    close_date_is_modified = p_row.getCloseDateIsModified();
    setup_fee = p_row.getSetupFee();
    setup_fee_is_set = p_row.getSetupFeeIsSet();
    setup_fee_is_modified = p_row.getSetupFeeIsModified();
    setup_fee_tax = p_row.getSetupFeeTax();
    setup_fee_tax_is_set = p_row.getSetupFeeTaxIsSet();
    setup_fee_tax_is_modified = p_row.getSetupFeeTaxIsModified();
    management_fee = p_row.getManagementFee();
    management_fee_is_set = p_row.getManagementFeeIsSet();
    management_fee_is_modified = p_row.getManagementFeeIsModified();
    management_fee_tax = p_row.getManagementFeeTax();
    management_fee_tax_is_set = p_row.getManagementFeeTaxIsSet();
    management_fee_tax_is_modified = p_row.getManagementFeeTaxIsModified();
    interest_fee = p_row.getInterestFee();
    interest_fee_is_set = p_row.getInterestFeeIsSet();
    interest_fee_is_modified = p_row.getInterestFeeIsModified();
    interest_fee_tax = p_row.getInterestFeeTax();
    interest_fee_tax_is_set = p_row.getInterestFeeTaxIsSet();
    interest_fee_tax_is_modified = p_row.getInterestFeeTaxIsModified();
    product_id = p_row.getProductId();
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    product_type = p_row.getProductType();
    product_type_is_set = p_row.getProductTypeIsSet();
    product_type_is_modified = p_row.getProductTypeIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    delivery_date = p_row.getDeliveryDate();
    delivery_date_is_set = p_row.getDeliveryDateIsSet();
    delivery_date_is_modified = p_row.getDeliveryDateIsModified();
    session_type = p_row.getSessionType();
    session_type_is_set = p_row.getSessionTypeIsSet();
    session_type_is_modified = p_row.getSessionTypeIsModified();
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
      market_id_is_set = true;
      market_id_is_modified = true;
      unit_size_is_set = true;
      unit_size_is_modified = true;
      original_quantity_is_set = true;
      original_quantity_is_modified = true;
      quantity_is_set = true;
      quantity_is_modified = true;
      original_contract_price_is_set = true;
      original_contract_price_is_modified = true;
      contract_price_is_set = true;
      contract_price_is_modified = true;
      contract_type_is_set = true;
      contract_type_is_modified = true;
      open_date_is_set = true;
      open_date_is_modified = true;
      close_date_is_set = true;
      close_date_is_modified = true;
      setup_fee_is_set = true;
      setup_fee_is_modified = true;
      setup_fee_tax_is_set = true;
      setup_fee_tax_is_modified = true;
      management_fee_is_set = true;
      management_fee_is_modified = true;
      management_fee_tax_is_set = true;
      management_fee_tax_is_modified = true;
      interest_fee_is_set = true;
      interest_fee_is_modified = true;
      interest_fee_tax_is_set = true;
      interest_fee_tax_is_modified = true;
      product_id_is_set = true;
      product_id_is_modified = true;
      product_type_is_set = true;
      product_type_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      delivery_date_is_set = true;
      delivery_date_is_modified = true;
      session_type_is_set = true;
      session_type_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof IfoContractRow ) )
       return false;
    return fieldsEqual( (IfoContractRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のIfoContractRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( IfoContractRow row )
  {
    if ( contract_id != row.getContractId() )
      return false;
    if ( account_id != row.getAccountId() )
      return false;
    if ( sub_account_id != row.getSubAccountId() )
      return false;
    if ( market_id != row.getMarketId() )
      return false;
    if ( unit_size != row.getUnitSize() )
      return false;
    if ( original_quantity != row.getOriginalQuantity() )
      return false;
    if ( quantity != row.getQuantity() )
      return false;
    if ( original_contract_price != row.getOriginalContractPrice() )
      return false;
    if ( contract_price != row.getContractPrice() )
      return false;
    if ( contract_type == null ) {
      if ( row.getContractType() != null )
        return false;
    } else if ( !contract_type.equals( row.getContractType() ) ) {
        return false;
    }
    if ( open_date == null ) {
      if ( row.getOpenDate() != null )
        return false;
    } else if ( !open_date.equals( row.getOpenDate() ) ) {
        return false;
    }
    if ( close_date == null ) {
      if ( row.getCloseDate() != null )
        return false;
    } else if ( !close_date.equals( row.getCloseDate() ) ) {
        return false;
    }
    if ( setup_fee != row.getSetupFee() )
      return false;
    if ( setup_fee_tax != row.getSetupFeeTax() )
      return false;
    if ( management_fee != row.getManagementFee() )
      return false;
    if ( management_fee_tax != row.getManagementFeeTax() )
      return false;
    if ( interest_fee != row.getInterestFee() )
      return false;
    if ( interest_fee_tax != row.getInterestFeeTax() )
      return false;
    if ( product_id != row.getProductId() )
      return false;
    if ( product_type == null ) {
      if ( row.getProductType() != null )
        return false;
    } else if ( !product_type.equals( row.getProductType() ) ) {
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
    if ( delivery_date == null ) {
      if ( row.getDeliveryDate() != null )
        return false;
    } else if ( !delivery_date.equals( row.getDeliveryDate() ) ) {
        return false;
    }
    if ( session_type == null ) {
      if ( row.getSessionType() != null )
        return false;
    } else if ( !session_type.equals( row.getSessionType() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  ((int) contract_id)
        + ((int) account_id)
        + ((int) sub_account_id)
        + ((int) market_id)
        + ((int) unit_size)
        + ((int) original_quantity)
        + ((int) quantity)
        + ((int) original_contract_price)
        + ((int) contract_price)
        + (contract_type!=null? contract_type.hashCode(): 0) 
        + (open_date!=null? open_date.hashCode(): 0) 
        + (close_date!=null? close_date.hashCode(): 0) 
        + ((int) setup_fee)
        + ((int) setup_fee_tax)
        + ((int) management_fee)
        + ((int) management_fee_tax)
        + ((int) interest_fee)
        + ((int) interest_fee_tax)
        + ((int) product_id)
        + (product_type!=null? product_type.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (delivery_date!=null? delivery_date.hashCode(): 0) 
        + (session_type!=null? session_type.hashCode(): 0) 
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
		if ( !market_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'market_id' must be set before inserting.");
		if ( !unit_size_is_set )
			throw new IllegalArgumentException("non-nullable field 'unit_size' must be set before inserting.");
		if ( !original_quantity_is_set )
			throw new IllegalArgumentException("non-nullable field 'original_quantity' must be set before inserting.");
		if ( !quantity_is_set )
			throw new IllegalArgumentException("non-nullable field 'quantity' must be set before inserting.");
		if ( !original_contract_price_is_set )
			throw new IllegalArgumentException("non-nullable field 'original_contract_price' must be set before inserting.");
		if ( !contract_price_is_set )
			throw new IllegalArgumentException("non-nullable field 'contract_price' must be set before inserting.");
		if ( !contract_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'contract_type' must be set before inserting.");
		if ( !open_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'open_date' must be set before inserting.");
		if ( !close_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'close_date' must be set before inserting.");
		if ( !product_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_id' must be set before inserting.");
		if ( !product_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_type' must be set before inserting.");
		if ( !created_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'created_timestamp' must be set before inserting.");
		if ( !last_updated_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updated_timestamp' must be set before inserting.");
		if ( !delivery_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'delivery_date' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("contract_id",new Long(contract_id));
		map.put("account_id",new Long(account_id));
		map.put("sub_account_id",new Long(sub_account_id));
		map.put("market_id",new Long(market_id));
		map.put("unit_size",new Double(unit_size));
		map.put("original_quantity",new Double(original_quantity));
		map.put("quantity",new Double(quantity));
		map.put("original_contract_price",new Double(original_contract_price));
		map.put("contract_price",new Double(contract_price));
		map.put("contract_type",contract_type);
		map.put("open_date",open_date);
		map.put("close_date",close_date);
		if ( setup_fee_is_set )
			map.put("setup_fee",new Double(setup_fee));
		if ( setup_fee_tax_is_set )
			map.put("setup_fee_tax",new Double(setup_fee_tax));
		if ( management_fee_is_set )
			map.put("management_fee",new Double(management_fee));
		if ( management_fee_tax_is_set )
			map.put("management_fee_tax",new Double(management_fee_tax));
		if ( interest_fee_is_set )
			map.put("interest_fee",new Double(interest_fee));
		if ( interest_fee_tax_is_set )
			map.put("interest_fee_tax",new Double(interest_fee_tax));
		map.put("product_id",new Long(product_id));
		map.put("product_type",product_type);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		map.put("delivery_date",delivery_date);
		if ( session_type != null )
			map.put("session_type",session_type);
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
		if ( market_id_is_modified )
			map.put("market_id",new Long(market_id));
		if ( unit_size_is_modified )
			map.put("unit_size",new Double(unit_size));
		if ( original_quantity_is_modified )
			map.put("original_quantity",new Double(original_quantity));
		if ( quantity_is_modified )
			map.put("quantity",new Double(quantity));
		if ( original_contract_price_is_modified )
			map.put("original_contract_price",new Double(original_contract_price));
		if ( contract_price_is_modified )
			map.put("contract_price",new Double(contract_price));
		if ( contract_type_is_modified )
			map.put("contract_type",contract_type);
		if ( open_date_is_modified )
			map.put("open_date",open_date);
		if ( close_date_is_modified )
			map.put("close_date",close_date);
		if ( setup_fee_is_modified )
			map.put("setup_fee",new Double(setup_fee));
		if ( setup_fee_tax_is_modified )
			map.put("setup_fee_tax",new Double(setup_fee_tax));
		if ( management_fee_is_modified )
			map.put("management_fee",new Double(management_fee));
		if ( management_fee_tax_is_modified )
			map.put("management_fee_tax",new Double(management_fee_tax));
		if ( interest_fee_is_modified )
			map.put("interest_fee",new Double(interest_fee));
		if ( interest_fee_tax_is_modified )
			map.put("interest_fee_tax",new Double(interest_fee_tax));
		if ( product_id_is_modified )
			map.put("product_id",new Long(product_id));
		if ( product_type_is_modified )
			map.put("product_type",product_type);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( delivery_date_is_modified )
			map.put("delivery_date",delivery_date);
		if ( session_type_is_modified )
			map.put("session_type",session_type);
		if (map.size() == 0) {
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			if ( sub_account_id_is_set )
				map.put("sub_account_id",new Long(sub_account_id));
			if ( market_id_is_set )
				map.put("market_id",new Long(market_id));
			if ( unit_size_is_set )
				map.put("unit_size",new Double(unit_size));
			if ( original_quantity_is_set )
				map.put("original_quantity",new Double(original_quantity));
			if ( quantity_is_set )
				map.put("quantity",new Double(quantity));
			if ( original_contract_price_is_set )
				map.put("original_contract_price",new Double(original_contract_price));
			if ( contract_price_is_set )
				map.put("contract_price",new Double(contract_price));
			if ( contract_type_is_set )
				map.put("contract_type",contract_type);
			if ( open_date_is_set )
				map.put("open_date",open_date);
			if ( close_date_is_set )
				map.put("close_date",close_date);
			if ( setup_fee_is_set )
				map.put("setup_fee",new Double(setup_fee));
			if ( setup_fee_tax_is_set )
				map.put("setup_fee_tax",new Double(setup_fee_tax));
			if ( management_fee_is_set )
				map.put("management_fee",new Double(management_fee));
			if ( management_fee_tax_is_set )
				map.put("management_fee_tax",new Double(management_fee_tax));
			if ( interest_fee_is_set )
				map.put("interest_fee",new Double(interest_fee));
			if ( interest_fee_tax_is_set )
				map.put("interest_fee_tax",new Double(interest_fee_tax));
			if ( product_id_is_set )
				map.put("product_id",new Long(product_id));
			if ( product_type_is_set )
				map.put("product_type",product_type);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			if ( delivery_date_is_set )
				map.put("delivery_date",delivery_date);
			map.put("session_type",session_type);
		}
		return map;
	}


  /** 
   * <em>contract_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getContractId()
  {
    return contract_id;
  }


  /** 
   * <em>contract_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractIdIsSet() {
    return contract_id_is_set;
  }


  /** 
   * <em>contract_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractIdIsModified() {
    return contract_id_is_modified;
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
   * <em>market_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMarketId()
  {
    return market_id;
  }


  /** 
   * <em>market_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketIdIsSet() {
    return market_id_is_set;
  }


  /** 
   * <em>market_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketIdIsModified() {
    return market_id_is_modified;
  }


  /** 
   * <em>unit_size</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUnitSize()
  {
    return unit_size;
  }


  /** 
   * <em>unit_size</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnitSizeIsSet() {
    return unit_size_is_set;
  }


  /** 
   * <em>unit_size</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnitSizeIsModified() {
    return unit_size_is_modified;
  }


  /** 
   * <em>original_quantity</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getOriginalQuantity()
  {
    return original_quantity;
  }


  /** 
   * <em>original_quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOriginalQuantityIsSet() {
    return original_quantity_is_set;
  }


  /** 
   * <em>original_quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOriginalQuantityIsModified() {
    return original_quantity_is_modified;
  }


  /** 
   * <em>quantity</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getQuantity()
  {
    return quantity;
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
   * <em>original_contract_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getOriginalContractPrice()
  {
    return original_contract_price;
  }


  /** 
   * <em>original_contract_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOriginalContractPriceIsSet() {
    return original_contract_price_is_set;
  }


  /** 
   * <em>original_contract_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOriginalContractPriceIsModified() {
    return original_contract_price_is_modified;
  }


  /** 
   * <em>contract_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractPrice()
  {
    return contract_price;
  }


  /** 
   * <em>contract_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractPriceIsSet() {
    return contract_price_is_set;
  }


  /** 
   * <em>contract_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractPriceIsModified() {
    return contract_price_is_modified;
  }


  /** 
   * <em>contract_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum getContractType()
  {
    return contract_type;
  }


  /** 
   * <em>contract_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractTypeIsSet() {
    return contract_type_is_set;
  }


  /** 
   * <em>contract_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractTypeIsModified() {
    return contract_type_is_modified;
  }


  /** 
   * <em>open_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getOpenDate()
  {
    return open_date;
  }


  /** 
   * <em>open_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOpenDateIsSet() {
    return open_date_is_set;
  }


  /** 
   * <em>open_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOpenDateIsModified() {
    return open_date_is_modified;
  }


  /** 
   * <em>close_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getCloseDate()
  {
    return close_date;
  }


  /** 
   * <em>close_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCloseDateIsSet() {
    return close_date_is_set;
  }


  /** 
   * <em>close_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCloseDateIsModified() {
    return close_date_is_modified;
  }


  /** 
   * <em>setup_fee</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSetupFee()
  {
    return setup_fee;
  }


  /** 
   * <em>setup_fee</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSetupFeeIsSet() {
    return setup_fee_is_set;
  }


  /** 
   * <em>setup_fee</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSetupFeeIsModified() {
    return setup_fee_is_modified;
  }


  /** 
   * <em>setup_fee_tax</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSetupFeeTax()
  {
    return setup_fee_tax;
  }


  /** 
   * <em>setup_fee_tax</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSetupFeeTaxIsSet() {
    return setup_fee_tax_is_set;
  }


  /** 
   * <em>setup_fee_tax</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSetupFeeTaxIsModified() {
    return setup_fee_tax_is_modified;
  }


  /** 
   * <em>management_fee</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getManagementFee()
  {
    return management_fee;
  }


  /** 
   * <em>management_fee</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getManagementFeeIsSet() {
    return management_fee_is_set;
  }


  /** 
   * <em>management_fee</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getManagementFeeIsModified() {
    return management_fee_is_modified;
  }


  /** 
   * <em>management_fee_tax</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getManagementFeeTax()
  {
    return management_fee_tax;
  }


  /** 
   * <em>management_fee_tax</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getManagementFeeTaxIsSet() {
    return management_fee_tax_is_set;
  }


  /** 
   * <em>management_fee_tax</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getManagementFeeTaxIsModified() {
    return management_fee_tax_is_modified;
  }


  /** 
   * <em>interest_fee</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getInterestFee()
  {
    return interest_fee;
  }


  /** 
   * <em>interest_fee</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFeeIsSet() {
    return interest_fee_is_set;
  }


  /** 
   * <em>interest_fee</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFeeIsModified() {
    return interest_fee_is_modified;
  }


  /** 
   * <em>interest_fee_tax</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getInterestFeeTax()
  {
    return interest_fee_tax;
  }


  /** 
   * <em>interest_fee_tax</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFeeTaxIsSet() {
    return interest_fee_tax_is_set;
  }


  /** 
   * <em>interest_fee_tax</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFeeTaxIsModified() {
    return interest_fee_tax_is_modified;
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
   * <em>session_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSessionType()
  {
    return session_type;
  }


  /** 
   * <em>session_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSessionTypeIsSet() {
    return session_type_is_set;
  }


  /** 
   * <em>session_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSessionTypeIsModified() {
    return session_type_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new IfoContractPK(contract_id);
  }


  /** 
   * <em>contract_id</em>カラムの値を設定します。 
   *
   * @@param p_contractId <em>contract_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setContractId( long p_contractId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_id = p_contractId;
    contract_id_is_set = true;
    contract_id_is_modified = true;
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
   * <em>market_id</em>カラムの値を設定します。 
   *
   * @@param p_marketId <em>market_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setMarketId( long p_marketId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_id = p_marketId;
    market_id_is_set = true;
    market_id_is_modified = true;
  }


  /** 
   * <em>unit_size</em>カラムの値を設定します。 
   *
   * @@param p_unitSize <em>unit_size</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUnitSize( double p_unitSize )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    unit_size = p_unitSize;
    unit_size_is_set = true;
    unit_size_is_modified = true;
  }


  /** 
   * <em>original_quantity</em>カラムの値を設定します。 
   *
   * @@param p_originalQuantity <em>original_quantity</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setOriginalQuantity( double p_originalQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    original_quantity = p_originalQuantity;
    original_quantity_is_set = true;
    original_quantity_is_modified = true;
  }


  /** 
   * <em>quantity</em>カラムの値を設定します。 
   *
   * @@param p_quantity <em>quantity</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setQuantity( double p_quantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    quantity = p_quantity;
    quantity_is_set = true;
    quantity_is_modified = true;
  }


  /** 
   * <em>original_contract_price</em>カラムの値を設定します。 
   *
   * @@param p_originalContractPrice <em>original_contract_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setOriginalContractPrice( double p_originalContractPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    original_contract_price = p_originalContractPrice;
    original_contract_price_is_set = true;
    original_contract_price_is_modified = true;
  }


  /** 
   * <em>contract_price</em>カラムの値を設定します。 
   *
   * @@param p_contractPrice <em>contract_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractPrice( double p_contractPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_price = p_contractPrice;
    contract_price_is_set = true;
    contract_price_is_modified = true;
  }


  /** 
   * <em>contract_type</em>カラムの値を設定します。 
   *
   * @@param p_contractType <em>contract_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum値 
   */
  public final void setContractType( com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum p_contractType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_type = p_contractType;
    contract_type_is_set = true;
    contract_type_is_modified = true;
  }


  /** 
   * <em>open_date</em>カラムの値を設定します。 
   *
   * @@param p_openDate <em>open_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setOpenDate( java.sql.Timestamp p_openDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    open_date = p_openDate;
    open_date_is_set = true;
    open_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>open_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setOpenDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    open_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    open_date_is_set = true;
    open_date_is_modified = true;
  }


  /** 
   * <em>close_date</em>カラムの値を設定します。 
   *
   * @@param p_closeDate <em>close_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setCloseDate( java.sql.Timestamp p_closeDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    close_date = p_closeDate;
    close_date_is_set = true;
    close_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>close_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setCloseDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    close_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    close_date_is_set = true;
    close_date_is_modified = true;
  }


  /** 
   * <em>setup_fee</em>カラムの値を設定します。 
   *
   * @@param p_setupFee <em>setup_fee</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSetupFee( double p_setupFee )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    setup_fee = p_setupFee;
    setup_fee_is_set = true;
    setup_fee_is_modified = true;
  }


  /** 
   * <em>setup_fee_tax</em>カラムの値を設定します。 
   *
   * @@param p_setupFeeTax <em>setup_fee_tax</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSetupFeeTax( double p_setupFeeTax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    setup_fee_tax = p_setupFeeTax;
    setup_fee_tax_is_set = true;
    setup_fee_tax_is_modified = true;
  }


  /** 
   * <em>management_fee</em>カラムの値を設定します。 
   *
   * @@param p_managementFee <em>management_fee</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setManagementFee( double p_managementFee )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    management_fee = p_managementFee;
    management_fee_is_set = true;
    management_fee_is_modified = true;
  }


  /** 
   * <em>management_fee_tax</em>カラムの値を設定します。 
   *
   * @@param p_managementFeeTax <em>management_fee_tax</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setManagementFeeTax( double p_managementFeeTax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    management_fee_tax = p_managementFeeTax;
    management_fee_tax_is_set = true;
    management_fee_tax_is_modified = true;
  }


  /** 
   * <em>interest_fee</em>カラムの値を設定します。 
   *
   * @@param p_interestFee <em>interest_fee</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setInterestFee( double p_interestFee )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    interest_fee = p_interestFee;
    interest_fee_is_set = true;
    interest_fee_is_modified = true;
  }


  /** 
   * <em>interest_fee_tax</em>カラムの値を設定します。 
   *
   * @@param p_interestFeeTax <em>interest_fee_tax</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setInterestFeeTax( double p_interestFeeTax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    interest_fee_tax = p_interestFeeTax;
    interest_fee_tax_is_set = true;
    interest_fee_tax_is_modified = true;
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
   * <em>session_type</em>カラムの値を設定します。 
   *
   * @@param p_sessionType <em>session_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSessionType( String p_sessionType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    session_type = p_sessionType;
    session_type_is_set = true;
    session_type_is_modified = true;
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
                if ( name.equals("contract_id") ) {
                    return new Long( contract_id );
                }
                else if ( name.equals("contract_price") ) {
                    return new Double( contract_price );
                }
                else if ( name.equals("contract_type") ) {
                    return this.contract_type;
                }
                else if ( name.equals("close_date") ) {
                    return this.close_date;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("delivery_date") ) {
                    return this.delivery_date;
                }
                break;
            case 'i':
                if ( name.equals("interest_fee") ) {
                    return new Double( interest_fee );
                }
                else if ( name.equals("interest_fee_tax") ) {
                    return new Double( interest_fee_tax );
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("market_id") ) {
                    return new Long( market_id );
                }
                else if ( name.equals("management_fee") ) {
                    return new Double( management_fee );
                }
                else if ( name.equals("management_fee_tax") ) {
                    return new Double( management_fee_tax );
                }
                break;
            case 'o':
                if ( name.equals("original_quantity") ) {
                    return new Double( original_quantity );
                }
                else if ( name.equals("original_contract_price") ) {
                    return new Double( original_contract_price );
                }
                else if ( name.equals("open_date") ) {
                    return this.open_date;
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
                    return new Double( quantity );
                }
                break;
            case 's':
                if ( name.equals("sub_account_id") ) {
                    return new Long( sub_account_id );
                }
                else if ( name.equals("setup_fee") ) {
                    return new Double( setup_fee );
                }
                else if ( name.equals("setup_fee_tax") ) {
                    return new Double( setup_fee_tax );
                }
                else if ( name.equals("session_type") ) {
                    return this.session_type;
                }
                break;
            case 'u':
                if ( name.equals("unit_size") ) {
                    return new Double( unit_size );
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
                if ( name.equals("contract_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'contract_id' must be Long: '"+value+"' is not." );
                    this.contract_id = ((Long) value).longValue();
                    if (this.contract_id_is_set)
                        this.contract_id_is_modified = true;
                    this.contract_id_is_set = true;
                    return;
                }
                else if ( name.equals("contract_price") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_price' must be Double: '"+value+"' is not." );
                    this.contract_price = ((Double) value).doubleValue();
                    if (this.contract_price_is_set)
                        this.contract_price_is_modified = true;
                    this.contract_price_is_set = true;
                    return;
                }
                else if ( name.equals("contract_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum) )
                        throw new IllegalArgumentException( "value for 'contract_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum: '"+value+"' is not." );
                    this.contract_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum) value;
                    if (this.contract_type_is_set)
                        this.contract_type_is_modified = true;
                    this.contract_type_is_set = true;
                    return;
                }
                else if ( name.equals("close_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'close_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.close_date = (java.sql.Timestamp) value;
                    if (this.close_date_is_set)
                        this.close_date_is_modified = true;
                    this.close_date_is_set = true;
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
                if ( name.equals("delivery_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'delivery_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.delivery_date = (java.sql.Timestamp) value;
                    if (this.delivery_date_is_set)
                        this.delivery_date_is_modified = true;
                    this.delivery_date_is_set = true;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("interest_fee") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'interest_fee' must be Double: '"+value+"' is not." );
                    this.interest_fee = ((Double) value).doubleValue();
                    if (this.interest_fee_is_set)
                        this.interest_fee_is_modified = true;
                    this.interest_fee_is_set = true;
                    return;
                }
                else if ( name.equals("interest_fee_tax") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'interest_fee_tax' must be Double: '"+value+"' is not." );
                    this.interest_fee_tax = ((Double) value).doubleValue();
                    if (this.interest_fee_tax_is_set)
                        this.interest_fee_tax_is_modified = true;
                    this.interest_fee_tax_is_set = true;
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
            case 'm':
                if ( name.equals("market_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'market_id' must be Long: '"+value+"' is not." );
                    this.market_id = ((Long) value).longValue();
                    if (this.market_id_is_set)
                        this.market_id_is_modified = true;
                    this.market_id_is_set = true;
                    return;
                }
                else if ( name.equals("management_fee") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'management_fee' must be Double: '"+value+"' is not." );
                    this.management_fee = ((Double) value).doubleValue();
                    if (this.management_fee_is_set)
                        this.management_fee_is_modified = true;
                    this.management_fee_is_set = true;
                    return;
                }
                else if ( name.equals("management_fee_tax") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'management_fee_tax' must be Double: '"+value+"' is not." );
                    this.management_fee_tax = ((Double) value).doubleValue();
                    if (this.management_fee_tax_is_set)
                        this.management_fee_tax_is_modified = true;
                    this.management_fee_tax_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("original_quantity") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'original_quantity' must be Double: '"+value+"' is not." );
                    this.original_quantity = ((Double) value).doubleValue();
                    if (this.original_quantity_is_set)
                        this.original_quantity_is_modified = true;
                    this.original_quantity_is_set = true;
                    return;
                }
                else if ( name.equals("original_contract_price") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'original_contract_price' must be Double: '"+value+"' is not." );
                    this.original_contract_price = ((Double) value).doubleValue();
                    if (this.original_contract_price_is_set)
                        this.original_contract_price_is_modified = true;
                    this.original_contract_price_is_set = true;
                    return;
                }
                else if ( name.equals("open_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'open_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.open_date = (java.sql.Timestamp) value;
                    if (this.open_date_is_set)
                        this.open_date_is_modified = true;
                    this.open_date_is_set = true;
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
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'quantity' must be Double: '"+value+"' is not." );
                    this.quantity = ((Double) value).doubleValue();
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
                else if ( name.equals("setup_fee") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'setup_fee' must be Double: '"+value+"' is not." );
                    this.setup_fee = ((Double) value).doubleValue();
                    if (this.setup_fee_is_set)
                        this.setup_fee_is_modified = true;
                    this.setup_fee_is_set = true;
                    return;
                }
                else if ( name.equals("setup_fee_tax") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'setup_fee_tax' must be Double: '"+value+"' is not." );
                    this.setup_fee_tax = ((Double) value).doubleValue();
                    if (this.setup_fee_tax_is_set)
                        this.setup_fee_tax_is_modified = true;
                    this.setup_fee_tax_is_set = true;
                    return;
                }
                else if ( name.equals("session_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'session_type' must be String: '"+value+"' is not." );
                    this.session_type = (String) value;
                    if (this.session_type_is_set)
                        this.session_type_is_modified = true;
                    this.session_type_is_set = true;
                    return;
                }
                break;
            case 'u':
                if ( name.equals("unit_size") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'unit_size' must be Double: '"+value+"' is not." );
                    this.unit_size = ((Double) value).doubleValue();
                    if (this.unit_size_is_set)
                        this.unit_size_is_modified = true;
                    this.unit_size_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
