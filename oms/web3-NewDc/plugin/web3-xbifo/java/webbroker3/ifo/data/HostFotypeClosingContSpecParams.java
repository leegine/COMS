head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	HostFotypeClosingContSpecParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifo.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.*;

/** 
 * host_fotype_closing_cont_specテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link HostFotypeClosingContSpecRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link HostFotypeClosingContSpecParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link HostFotypeClosingContSpecParams}が{@@link HostFotypeClosingContSpecRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see HostFotypeClosingContSpecPK 
 * @@see HostFotypeClosingContSpecRow 
 */
public class HostFotypeClosingContSpecParams extends Params implements HostFotypeClosingContSpecRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "host_fotype_closing_cont_spec";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = HostFotypeClosingContSpecRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return HostFotypeClosingContSpecRow.TYPE;
  }


  /** 
   * <em>rowid</em>カラムの値 
   */
  public  String  rowid;

  /** 
   * <em>request_code</em>カラムの値 
   */
  public  String  request_code;

  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>branch_code</em>カラムの値 
   */
  public  String  branch_code;

  /** 
   * <em>account_code</em>カラムの値 
   */
  public  String  account_code;

  /** 
   * <em>trader_code</em>カラムの値 
   */
  public  String  trader_code;

  /** 
   * <em>order_request_number</em>カラムの値 
   */
  public  String  order_request_number;

  /** 
   * <em>market_code</em>カラムの値 
   */
  public  Long  market_code;

  /** 
   * <em>target_product_code</em>カラムの値 
   */
  public  String  target_product_code;

  /** 
   * <em>delivary_month</em>カラムの値 
   */
  public  String  delivary_month;

  /** 
   * <em>future_option_product_type</em>カラムの値 
   */
  public  String  future_option_product_type;

  /** 
   * <em>strike_price</em>カラムの値 
   */
  public  Double  strike_price;

  /** 
   * <em>split_type</em>カラムの値 
   */
  public  String  split_type;

  /** 
   * <em>contract_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum  contract_type;

  /** 
   * <em>closing_type</em>カラムの値 
   */
  public  String  closing_type;

  /** 
   * <em>open_date</em>カラムの値 
   */
  public  java.sql.Timestamp  open_date;

  /** 
   * <em>contract_price</em>カラムの値 
   */
  public  Double  contract_price;

  /** 
   * <em>quantity</em>カラムの値 
   */
  public  Double  quantity;

  /** 
   * <em>create_datetime</em>カラムの値 
   */
  public  java.sql.Timestamp  create_datetime;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>status</em>カラムの値 
   */
  public  String  status;

  private boolean request_code_is_set = false;
  private boolean request_code_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean trader_code_is_set = false;
  private boolean trader_code_is_modified = false;
  private boolean order_request_number_is_set = false;
  private boolean order_request_number_is_modified = false;
  private boolean market_code_is_set = false;
  private boolean market_code_is_modified = false;
  private boolean target_product_code_is_set = false;
  private boolean target_product_code_is_modified = false;
  private boolean delivary_month_is_set = false;
  private boolean delivary_month_is_modified = false;
  private boolean future_option_product_type_is_set = false;
  private boolean future_option_product_type_is_modified = false;
  private boolean strike_price_is_set = false;
  private boolean strike_price_is_modified = false;
  private boolean split_type_is_set = false;
  private boolean split_type_is_modified = false;
  private boolean contract_type_is_set = false;
  private boolean contract_type_is_modified = false;
  private boolean closing_type_is_set = false;
  private boolean closing_type_is_modified = false;
  private boolean open_date_is_set = false;
  private boolean open_date_is_modified = false;
  private boolean contract_price_is_set = false;
  private boolean contract_price_is_modified = false;
  private boolean quantity_is_set = false;
  private boolean quantity_is_modified = false;
  private boolean create_datetime_is_set = false;
  private boolean create_datetime_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean rowid_is_set = false;
  private boolean rowid_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "rowid=" + rowid
      + "," + "request_code=" +request_code
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "," + "trader_code=" +trader_code
      + "," + "order_request_number=" +order_request_number
      + "," + "market_code=" +market_code
      + "," + "target_product_code=" +target_product_code
      + "," + "delivary_month=" +delivary_month
      + "," + "future_option_product_type=" +future_option_product_type
      + "," + "strike_price=" +strike_price
      + "," + "split_type=" +split_type
      + "," + "contract_type=" +contract_type
      + "," + "closing_type=" +closing_type
      + "," + "open_date=" +open_date
      + "," + "contract_price=" +contract_price
      + "," + "quantity=" +quantity
      + "," + "create_datetime=" +create_datetime
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "status=" +status
      + "}";
  }


  /** 
   * 値が未設定のHostFotypeClosingContSpecParamsオブジェクトを作成します。 
   */
  public HostFotypeClosingContSpecParams() {
    rowid_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のHostFotypeClosingContSpecRowオブジェクトの値を利用してHostFotypeClosingContSpecParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するHostFotypeClosingContSpecRowオブジェクト 
   */
  public HostFotypeClosingContSpecParams( HostFotypeClosingContSpecRow p_row )
  {
    rowid = p_row.getRowid();
    rowid_is_set = p_row.getRowidIsSet();
    rowid_is_modified = p_row.getRowidIsModified();
    request_code = p_row.getRequestCode();
    request_code_is_set = p_row.getRequestCodeIsSet();
    request_code_is_modified = p_row.getRequestCodeIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    trader_code = p_row.getTraderCode();
    trader_code_is_set = p_row.getTraderCodeIsSet();
    trader_code_is_modified = p_row.getTraderCodeIsModified();
    order_request_number = p_row.getOrderRequestNumber();
    order_request_number_is_set = p_row.getOrderRequestNumberIsSet();
    order_request_number_is_modified = p_row.getOrderRequestNumberIsModified();
    if ( !p_row.getMarketCodeIsNull() )
      market_code = new Long( p_row.getMarketCode() );
    market_code_is_set = p_row.getMarketCodeIsSet();
    market_code_is_modified = p_row.getMarketCodeIsModified();
    target_product_code = p_row.getTargetProductCode();
    target_product_code_is_set = p_row.getTargetProductCodeIsSet();
    target_product_code_is_modified = p_row.getTargetProductCodeIsModified();
    delivary_month = p_row.getDelivaryMonth();
    delivary_month_is_set = p_row.getDelivaryMonthIsSet();
    delivary_month_is_modified = p_row.getDelivaryMonthIsModified();
    future_option_product_type = p_row.getFutureOptionProductType();
    future_option_product_type_is_set = p_row.getFutureOptionProductTypeIsSet();
    future_option_product_type_is_modified = p_row.getFutureOptionProductTypeIsModified();
    if ( !p_row.getStrikePriceIsNull() )
      strike_price = new Double( p_row.getStrikePrice() );
    strike_price_is_set = p_row.getStrikePriceIsSet();
    strike_price_is_modified = p_row.getStrikePriceIsModified();
    split_type = p_row.getSplitType();
    split_type_is_set = p_row.getSplitTypeIsSet();
    split_type_is_modified = p_row.getSplitTypeIsModified();
    contract_type = p_row.getContractType();
    contract_type_is_set = p_row.getContractTypeIsSet();
    contract_type_is_modified = p_row.getContractTypeIsModified();
    closing_type = p_row.getClosingType();
    closing_type_is_set = p_row.getClosingTypeIsSet();
    closing_type_is_modified = p_row.getClosingTypeIsModified();
    open_date = p_row.getOpenDate();
    open_date_is_set = p_row.getOpenDateIsSet();
    open_date_is_modified = p_row.getOpenDateIsModified();
    if ( !p_row.getContractPriceIsNull() )
      contract_price = new Double( p_row.getContractPrice() );
    contract_price_is_set = p_row.getContractPriceIsSet();
    contract_price_is_modified = p_row.getContractPriceIsModified();
    if ( !p_row.getQuantityIsNull() )
      quantity = new Double( p_row.getQuantity() );
    quantity_is_set = p_row.getQuantityIsSet();
    quantity_is_modified = p_row.getQuantityIsModified();
    create_datetime = p_row.getCreateDatetime();
    create_datetime_is_set = p_row.getCreateDatetimeIsSet();
    create_datetime_is_modified = p_row.getCreateDatetimeIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      request_code_is_set = true;
      request_code_is_modified = true;
      institution_code_is_set = true;
      institution_code_is_modified = true;
      branch_code_is_set = true;
      branch_code_is_modified = true;
      account_code_is_set = true;
      account_code_is_modified = true;
      trader_code_is_set = true;
      trader_code_is_modified = true;
      order_request_number_is_set = true;
      order_request_number_is_modified = true;
      market_code_is_set = true;
      market_code_is_modified = true;
      target_product_code_is_set = true;
      target_product_code_is_modified = true;
      delivary_month_is_set = true;
      delivary_month_is_modified = true;
      future_option_product_type_is_set = true;
      future_option_product_type_is_modified = true;
      strike_price_is_set = true;
      strike_price_is_modified = true;
      split_type_is_set = true;
      split_type_is_modified = true;
      contract_type_is_set = true;
      contract_type_is_modified = true;
      closing_type_is_set = true;
      closing_type_is_modified = true;
      open_date_is_set = true;
      open_date_is_modified = true;
      contract_price_is_set = true;
      contract_price_is_modified = true;
      quantity_is_set = true;
      quantity_is_modified = true;
      create_datetime_is_set = true;
      create_datetime_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof HostFotypeClosingContSpecRow ) )
       return false;
    return fieldsEqual( (HostFotypeClosingContSpecRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のHostFotypeClosingContSpecRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( HostFotypeClosingContSpecRow row )
  {
    if ( request_code == null ) {
      if ( row.getRequestCode() != null )
        return false;
    } else if ( !request_code.equals( row.getRequestCode() ) ) {
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
    if ( account_code == null ) {
      if ( row.getAccountCode() != null )
        return false;
    } else if ( !account_code.equals( row.getAccountCode() ) ) {
        return false;
    }
    if ( trader_code == null ) {
      if ( row.getTraderCode() != null )
        return false;
    } else if ( !trader_code.equals( row.getTraderCode() ) ) {
        return false;
    }
    if ( order_request_number == null ) {
      if ( row.getOrderRequestNumber() != null )
        return false;
    } else if ( !order_request_number.equals( row.getOrderRequestNumber() ) ) {
        return false;
    }
    if ( market_code == null ) {
      if ( !row.getMarketCodeIsNull() )
        return false;
    } else if ( row.getMarketCodeIsNull() || ( market_code.longValue() != row.getMarketCode() ) ) {
        return false;
    }
    if ( target_product_code == null ) {
      if ( row.getTargetProductCode() != null )
        return false;
    } else if ( !target_product_code.equals( row.getTargetProductCode() ) ) {
        return false;
    }
    if ( delivary_month == null ) {
      if ( row.getDelivaryMonth() != null )
        return false;
    } else if ( !delivary_month.equals( row.getDelivaryMonth() ) ) {
        return false;
    }
    if ( future_option_product_type == null ) {
      if ( row.getFutureOptionProductType() != null )
        return false;
    } else if ( !future_option_product_type.equals( row.getFutureOptionProductType() ) ) {
        return false;
    }
    if ( strike_price == null ) {
      if ( !row.getStrikePriceIsNull() )
        return false;
    } else if ( row.getStrikePriceIsNull() || ( strike_price.doubleValue() != row.getStrikePrice() ) ) {
        return false;
    }
    if ( split_type == null ) {
      if ( row.getSplitType() != null )
        return false;
    } else if ( !split_type.equals( row.getSplitType() ) ) {
        return false;
    }
    if ( contract_type == null ) {
      if ( row.getContractType() != null )
        return false;
    } else if ( !contract_type.equals( row.getContractType() ) ) {
        return false;
    }
    if ( closing_type == null ) {
      if ( row.getClosingType() != null )
        return false;
    } else if ( !closing_type.equals( row.getClosingType() ) ) {
        return false;
    }
    if ( open_date == null ) {
      if ( row.getOpenDate() != null )
        return false;
    } else if ( !open_date.equals( row.getOpenDate() ) ) {
        return false;
    }
    if ( contract_price == null ) {
      if ( !row.getContractPriceIsNull() )
        return false;
    } else if ( row.getContractPriceIsNull() || ( contract_price.doubleValue() != row.getContractPrice() ) ) {
        return false;
    }
    if ( quantity == null ) {
      if ( !row.getQuantityIsNull() )
        return false;
    } else if ( row.getQuantityIsNull() || ( quantity.doubleValue() != row.getQuantity() ) ) {
        return false;
    }
    if ( create_datetime == null ) {
      if ( row.getCreateDatetime() != null )
        return false;
    } else if ( !create_datetime.equals( row.getCreateDatetime() ) ) {
        return false;
    }
    if ( last_updated_timestamp == null ) {
      if ( row.getLastUpdatedTimestamp() != null )
        return false;
    } else if ( !last_updated_timestamp.equals( row.getLastUpdatedTimestamp() ) ) {
        return false;
    }
    if ( status == null ) {
      if ( row.getStatus() != null )
        return false;
    } else if ( !status.equals( row.getStatus() ) ) {
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
      return  (request_code!=null? request_code.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (trader_code!=null? trader_code.hashCode(): 0) 
        + (order_request_number!=null? order_request_number.hashCode(): 0) 
        + (market_code!=null? market_code.hashCode(): 0) 
        + (target_product_code!=null? target_product_code.hashCode(): 0) 
        + (delivary_month!=null? delivary_month.hashCode(): 0) 
        + (future_option_product_type!=null? future_option_product_type.hashCode(): 0) 
        + (strike_price!=null? strike_price.hashCode(): 0) 
        + (split_type!=null? split_type.hashCode(): 0) 
        + (contract_type!=null? contract_type.hashCode(): 0) 
        + (closing_type!=null? closing_type.hashCode(): 0) 
        + (open_date!=null? open_date.hashCode(): 0) 
        + (contract_price!=null? contract_price.hashCode(): 0) 
        + (quantity!=null? quantity.hashCode(): 0) 
        + (create_datetime!=null? create_datetime.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
        + (rowid!=null? rowid.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !request_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'request_code' must be set before inserting.");
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !branch_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'branch_code' must be set before inserting.");
		if ( !order_request_number_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_request_number' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("request_code",request_code);
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		if ( account_code != null )
			map.put("account_code",account_code);
		if ( trader_code != null )
			map.put("trader_code",trader_code);
		map.put("order_request_number",order_request_number);
		if ( market_code != null )
			map.put("market_code",market_code);
		if ( target_product_code != null )
			map.put("target_product_code",target_product_code);
		if ( delivary_month != null )
			map.put("delivary_month",delivary_month);
		if ( future_option_product_type != null )
			map.put("future_option_product_type",future_option_product_type);
		if ( strike_price != null )
			map.put("strike_price",strike_price);
		if ( split_type != null )
			map.put("split_type",split_type);
		if ( contract_type != null )
			map.put("contract_type",contract_type);
		if ( closing_type != null )
			map.put("closing_type",closing_type);
		if ( open_date != null )
			map.put("open_date",open_date);
		if ( contract_price != null )
			map.put("contract_price",contract_price);
		if ( quantity != null )
			map.put("quantity",quantity);
		if ( create_datetime != null )
			map.put("create_datetime",create_datetime);
		if ( last_updated_timestamp != null )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( status != null )
			map.put("status",status);
		map.put("rowid",rowid);
		map.remove("rowid");
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( request_code_is_modified )
			map.put("request_code",request_code);
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( branch_code_is_modified )
			map.put("branch_code",branch_code);
		if ( account_code_is_modified )
			map.put("account_code",account_code);
		if ( trader_code_is_modified )
			map.put("trader_code",trader_code);
		if ( order_request_number_is_modified )
			map.put("order_request_number",order_request_number);
		if ( market_code_is_modified )
			map.put("market_code",market_code);
		if ( target_product_code_is_modified )
			map.put("target_product_code",target_product_code);
		if ( delivary_month_is_modified )
			map.put("delivary_month",delivary_month);
		if ( future_option_product_type_is_modified )
			map.put("future_option_product_type",future_option_product_type);
		if ( strike_price_is_modified )
			map.put("strike_price",strike_price);
		if ( split_type_is_modified )
			map.put("split_type",split_type);
		if ( contract_type_is_modified )
			map.put("contract_type",contract_type);
		if ( closing_type_is_modified )
			map.put("closing_type",closing_type);
		if ( open_date_is_modified )
			map.put("open_date",open_date);
		if ( contract_price_is_modified )
			map.put("contract_price",contract_price);
		if ( quantity_is_modified )
			map.put("quantity",quantity);
		if ( create_datetime_is_modified )
			map.put("create_datetime",create_datetime);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( status_is_modified )
			map.put("status",status);
		if (map.size() == 0) {
			if ( request_code_is_set )
				map.put("request_code",request_code);
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( branch_code_is_set )
				map.put("branch_code",branch_code);
			map.put("account_code",account_code);
			map.put("trader_code",trader_code);
			if ( order_request_number_is_set )
				map.put("order_request_number",order_request_number);
			map.put("market_code",market_code);
			map.put("target_product_code",target_product_code);
			map.put("delivary_month",delivary_month);
			map.put("future_option_product_type",future_option_product_type);
			map.put("strike_price",strike_price);
			map.put("split_type",split_type);
			map.put("contract_type",contract_type);
			map.put("closing_type",closing_type);
			map.put("open_date",open_date);
			map.put("contract_price",contract_price);
			map.put("quantity",quantity);
			map.put("create_datetime",create_datetime);
			map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("status",status);
		}
		return map;
	}


  /** 
   * <em>request_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRequestCode()
  {
    return request_code;
  }


  /** 
   * <em>request_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRequestCodeIsSet() {
    return request_code_is_set;
  }


  /** 
   * <em>request_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRequestCodeIsModified() {
    return request_code_is_modified;
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
   * <em>account_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountCode()
  {
    return account_code;
  }


  /** 
   * <em>account_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountCodeIsSet() {
    return account_code_is_set;
  }


  /** 
   * <em>account_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountCodeIsModified() {
    return account_code_is_modified;
  }


  /** 
   * <em>trader_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTraderCode()
  {
    return trader_code;
  }


  /** 
   * <em>trader_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTraderCodeIsSet() {
    return trader_code_is_set;
  }


  /** 
   * <em>trader_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTraderCodeIsModified() {
    return trader_code_is_modified;
  }


  /** 
   * <em>order_request_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderRequestNumber()
  {
    return order_request_number;
  }


  /** 
   * <em>order_request_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderRequestNumberIsSet() {
    return order_request_number_is_set;
  }


  /** 
   * <em>order_request_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderRequestNumberIsModified() {
    return order_request_number_is_modified;
  }


  /** 
   * <em>market_code</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMarketCode()
  {
    return ( market_code==null? ((long)0): market_code.longValue() );
  }


  /** 
   * <em>market_code</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarketCodeIsNull()
  {
    return market_code == null;
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
   * <em>delivary_month</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDelivaryMonth()
  {
    return delivary_month;
  }


  /** 
   * <em>delivary_month</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDelivaryMonthIsSet() {
    return delivary_month_is_set;
  }


  /** 
   * <em>delivary_month</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDelivaryMonthIsModified() {
    return delivary_month_is_modified;
  }


  /** 
   * <em>future_option_product_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFutureOptionProductType()
  {
    return future_option_product_type;
  }


  /** 
   * <em>future_option_product_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFutureOptionProductTypeIsSet() {
    return future_option_product_type_is_set;
  }


  /** 
   * <em>future_option_product_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFutureOptionProductTypeIsModified() {
    return future_option_product_type_is_modified;
  }


  /** 
   * <em>strike_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getStrikePrice()
  {
    return ( strike_price==null? ((double)0): strike_price.doubleValue() );
  }


  /** 
   * <em>strike_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getStrikePriceIsNull()
  {
    return strike_price == null;
  }


  /** 
   * <em>strike_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStrikePriceIsSet() {
    return strike_price_is_set;
  }


  /** 
   * <em>strike_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStrikePriceIsModified() {
    return strike_price_is_modified;
  }


  /** 
   * <em>split_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSplitType()
  {
    return split_type;
  }


  /** 
   * <em>split_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSplitTypeIsSet() {
    return split_type_is_set;
  }


  /** 
   * <em>split_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSplitTypeIsModified() {
    return split_type_is_modified;
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
   * <em>closing_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getClosingType()
  {
    return closing_type;
  }


  /** 
   * <em>closing_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getClosingTypeIsSet() {
    return closing_type_is_set;
  }


  /** 
   * <em>closing_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getClosingTypeIsModified() {
    return closing_type_is_modified;
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
   * <em>contract_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractPrice()
  {
    return ( contract_price==null? ((double)0): contract_price.doubleValue() );
  }


  /** 
   * <em>contract_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractPriceIsNull()
  {
    return contract_price == null;
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
   * <em>create_datetime</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getCreateDatetime()
  {
    return create_datetime;
  }


  /** 
   * <em>create_datetime</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreateDatetimeIsSet() {
    return create_datetime_is_set;
  }


  /** 
   * <em>create_datetime</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreateDatetimeIsModified() {
    return create_datetime_is_modified;
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
    return new HostFotypeClosingContSpecPK(rowid);
  }


  /** 
   * <em>request_code</em>カラムの値を設定します。 
   *
   * @@param p_requestCode <em>request_code</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setRequestCode( String p_requestCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    request_code = p_requestCode;
    request_code_is_set = true;
    request_code_is_modified = true;
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
   * <em>account_code</em>カラムの値を設定します。 
   *
   * @@param p_accountCode <em>account_code</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setAccountCode( String p_accountCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_code = p_accountCode;
    account_code_is_set = true;
    account_code_is_modified = true;
  }


  /** 
   * <em>trader_code</em>カラムの値を設定します。 
   *
   * @@param p_traderCode <em>trader_code</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setTraderCode( String p_traderCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trader_code = p_traderCode;
    trader_code_is_set = true;
    trader_code_is_modified = true;
  }


  /** 
   * <em>order_request_number</em>カラムの値を設定します。 
   *
   * @@param p_orderRequestNumber <em>order_request_number</em>カラムの値をあらわす9桁以下のString値 
   */
  public final void setOrderRequestNumber( String p_orderRequestNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_request_number = p_orderRequestNumber;
    order_request_number_is_set = true;
    order_request_number_is_modified = true;
  }


  /** 
   * <em>market_code</em>カラムの値を設定します。 
   *
   * @@param p_marketCode <em>market_code</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setMarketCode( long p_marketCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_code = new Long(p_marketCode);
    market_code_is_set = true;
    market_code_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>market_code</em>カラムに値を設定します。 
   */
  public final void setMarketCode( Long p_marketCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    market_code = p_marketCode;
    market_code_is_set = true;
    market_code_is_modified = true;
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
   * <em>delivary_month</em>カラムの値を設定します。 
   *
   * @@param p_delivaryMonth <em>delivary_month</em>カラムの値をあらわす6桁以下のString値 
   */
  public final void setDelivaryMonth( String p_delivaryMonth )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delivary_month = p_delivaryMonth;
    delivary_month_is_set = true;
    delivary_month_is_modified = true;
  }


  /** 
   * <em>future_option_product_type</em>カラムの値を設定します。 
   *
   * @@param p_futureOptionProductType <em>future_option_product_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFutureOptionProductType( String p_futureOptionProductType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    future_option_product_type = p_futureOptionProductType;
    future_option_product_type_is_set = true;
    future_option_product_type_is_modified = true;
  }


  /** 
   * <em>strike_price</em>カラムの値を設定します。 
   *
   * @@param p_strikePrice <em>strike_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setStrikePrice( double p_strikePrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    strike_price = new Double(p_strikePrice);
    strike_price_is_set = true;
    strike_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>strike_price</em>カラムに値を設定します。 
   */
  public final void setStrikePrice( Double p_strikePrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    strike_price = p_strikePrice;
    strike_price_is_set = true;
    strike_price_is_modified = true;
  }


  /** 
   * <em>split_type</em>カラムの値を設定します。 
   *
   * @@param p_splitType <em>split_type</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setSplitType( String p_splitType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    split_type = p_splitType;
    split_type_is_set = true;
    split_type_is_modified = true;
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
   * <em>closing_type</em>カラムの値を設定します。 
   *
   * @@param p_closingType <em>closing_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setClosingType( String p_closingType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    closing_type = p_closingType;
    closing_type_is_set = true;
    closing_type_is_modified = true;
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
   * <em>contract_price</em>カラムの値を設定します。 
   *
   * @@param p_contractPrice <em>contract_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractPrice( double p_contractPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_price = new Double(p_contractPrice);
    contract_price_is_set = true;
    contract_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_price</em>カラムに値を設定します。 
   */
  public final void setContractPrice( Double p_contractPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_price = p_contractPrice;
    contract_price_is_set = true;
    contract_price_is_modified = true;
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
   * <em>create_datetime</em>カラムの値を設定します。 
   *
   * @@param p_createDatetime <em>create_datetime</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setCreateDatetime( java.sql.Timestamp p_createDatetime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    create_datetime = p_createDatetime;
    create_datetime_is_set = true;
    create_datetime_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>create_datetime</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setCreateDatetime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    create_datetime = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    create_datetime_is_set = true;
    create_datetime_is_modified = true;
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
                if ( name.equals("account_code") ) {
                    return this.account_code;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'c':
                if ( name.equals("contract_type") ) {
                    return this.contract_type;
                }
                else if ( name.equals("closing_type") ) {
                    return this.closing_type;
                }
                else if ( name.equals("contract_price") ) {
                    return this.contract_price;
                }
                else if ( name.equals("create_datetime") ) {
                    return this.create_datetime;
                }
                break;
            case 'd':
                if ( name.equals("delivary_month") ) {
                    return this.delivary_month;
                }
                break;
            case 'f':
                if ( name.equals("future_option_product_type") ) {
                    return this.future_option_product_type;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("market_code") ) {
                    return this.market_code;
                }
                break;
            case 'o':
                if ( name.equals("order_request_number") ) {
                    return this.order_request_number;
                }
                else if ( name.equals("open_date") ) {
                    return this.open_date;
                }
                break;
            case 'q':
                if ( name.equals("quantity") ) {
                    return this.quantity;
                }
                break;
            case 'r':
                if ( name.equals("request_code") ) {
                    return this.request_code;
                }
                else if ( name.equals("rowid") ) {
                    return this.rowid;
                }
                break;
            case 's':
                if ( name.equals("strike_price") ) {
                    return this.strike_price;
                }
                else if ( name.equals("split_type") ) {
                    return this.split_type;
                }
                else if ( name.equals("status") ) {
                    return this.status;
                }
                break;
            case 't':
                if ( name.equals("trader_code") ) {
                    return this.trader_code;
                }
                else if ( name.equals("target_product_code") ) {
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
            case 'a':
                if ( name.equals("account_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_code' must be String: '"+value+"' is not." );
                    this.account_code = (String) value;
                    if (this.account_code_is_set)
                        this.account_code_is_modified = true;
                    this.account_code_is_set = true;
                    return;
                }
                break;
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
                if ( name.equals("contract_type") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum) )
                        throw new IllegalArgumentException( "value for 'contract_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum: '"+value+"' is not." );
                    this.contract_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum) value;
                    if (this.contract_type_is_set)
                        this.contract_type_is_modified = true;
                    this.contract_type_is_set = true;
                    return;
                }
                else if ( name.equals("closing_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'closing_type' must be String: '"+value+"' is not." );
                    this.closing_type = (String) value;
                    if (this.closing_type_is_set)
                        this.closing_type_is_modified = true;
                    this.closing_type_is_set = true;
                    return;
                }
                else if ( name.equals("contract_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_price' must be Double: '"+value+"' is not." );
                    this.contract_price = (Double) value;
                    if (this.contract_price_is_set)
                        this.contract_price_is_modified = true;
                    this.contract_price_is_set = true;
                    return;
                }
                else if ( name.equals("create_datetime") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'create_datetime' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.create_datetime = (java.sql.Timestamp) value;
                    if (this.create_datetime_is_set)
                        this.create_datetime_is_modified = true;
                    this.create_datetime_is_set = true;
                    return;
                }
                break;
            case 'd':
                if ( name.equals("delivary_month") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'delivary_month' must be String: '"+value+"' is not." );
                    this.delivary_month = (String) value;
                    if (this.delivary_month_is_set)
                        this.delivary_month_is_modified = true;
                    this.delivary_month_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("future_option_product_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'future_option_product_type' must be String: '"+value+"' is not." );
                    this.future_option_product_type = (String) value;
                    if (this.future_option_product_type_is_set)
                        this.future_option_product_type_is_modified = true;
                    this.future_option_product_type_is_set = true;
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
            case 'm':
                if ( name.equals("market_code") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'market_code' must be Long: '"+value+"' is not." );
                    this.market_code = (Long) value;
                    if (this.market_code_is_set)
                        this.market_code_is_modified = true;
                    this.market_code_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("order_request_number") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_request_number' must be String: '"+value+"' is not." );
                    this.order_request_number = (String) value;
                    if (this.order_request_number_is_set)
                        this.order_request_number_is_modified = true;
                    this.order_request_number_is_set = true;
                    return;
                }
                else if ( name.equals("open_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'open_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.open_date = (java.sql.Timestamp) value;
                    if (this.open_date_is_set)
                        this.open_date_is_modified = true;
                    this.open_date_is_set = true;
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
            case 'r':
                if ( name.equals("request_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'request_code' must be String: '"+value+"' is not." );
                    this.request_code = (String) value;
                    if (this.request_code_is_set)
                        this.request_code_is_modified = true;
                    this.request_code_is_set = true;
                    return;
                }
                else if ( name.equals("rowid") ) {
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
                if ( name.equals("strike_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'strike_price' must be Double: '"+value+"' is not." );
                    this.strike_price = (Double) value;
                    if (this.strike_price_is_set)
                        this.strike_price_is_modified = true;
                    this.strike_price_is_set = true;
                    return;
                }
                else if ( name.equals("split_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'split_type' must be String: '"+value+"' is not." );
                    this.split_type = (String) value;
                    if (this.split_type_is_set)
                        this.split_type_is_modified = true;
                    this.split_type_is_set = true;
                    return;
                }
                else if ( name.equals("status") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'status' must be String: '"+value+"' is not." );
                    this.status = (String) value;
                    if (this.status_is_set)
                        this.status_is_modified = true;
                    this.status_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("trader_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trader_code' must be String: '"+value+"' is not." );
                    this.trader_code = (String) value;
                    if (this.trader_code_is_set)
                        this.trader_code_is_modified = true;
                    this.trader_code_is_set = true;
                    return;
                }
                else if ( name.equals("target_product_code") ) {
                    if ( value != null )
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
