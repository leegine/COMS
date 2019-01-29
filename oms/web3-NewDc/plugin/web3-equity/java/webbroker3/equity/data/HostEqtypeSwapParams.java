head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	HostEqtypeSwapParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * host_eqtype_swapテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link HostEqtypeSwapRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link HostEqtypeSwapParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link HostEqtypeSwapParams}が{@@link HostEqtypeSwapRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see HostEqtypeSwapPK 
 * @@see HostEqtypeSwapRow 
 */
public class HostEqtypeSwapParams extends Params implements HostEqtypeSwapRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "host_eqtype_swap";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = HostEqtypeSwapRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return HostEqtypeSwapRow.TYPE;
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
   * <em>account_id</em>カラムの値 
   */
  public  Long  account_id;

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
   * <em>sonar_trader_code</em>カラムの値 
   */
  public  String  sonar_trader_code;

  /** 
   * <em>product_code</em>カラムの値 
   */
  public  String  product_code;

  /** 
   * <em>sell_order_quantity</em>カラムの値 
   */
  public  Double  sell_order_quantity;

  /** 
   * <em>buy_order_quantity</em>カラムの値 
   */
  public  Double  buy_order_quantity;

  /** 
   * <em>sonar_repayment_type</em>カラムの値 
   */
  public  String  sonar_repayment_type;

  /** 
   * <em>sonar_market_code</em>カラムの値 
   */
  public  String  sonar_market_code;

  /** 
   * <em>ticket_number</em>カラムの値 
   */
  public  String  ticket_number;

  /** 
   * <em>capital_gain_tax_type</em>カラムの値 
   */
  public  String  capital_gain_tax_type;

  /** 
   * <em>check_type</em>カラムの値 
   */
  public  String  check_type;

  /** 
   * <em>cancel_div</em>カラムの値 
   */
  public  String  cancel_div;

  /** 
   * <em>order_request_number</em>カラムの値 
   */
  public  String  order_request_number;

  /** 
   * <em>order_action_serial_no</em>カラムの値 
   */
  public  Integer  order_action_serial_no;

  /** 
   * <em>create_datetime</em>カラムの値 
   */
  public  java.sql.Timestamp  create_datetime;

  /** 
   * <em>tax_type</em>カラムの値 
   */
  public  String  tax_type;

  /** 
   * <em>swap_tax_type</em>カラムの値 
   */
  public  String  swap_tax_type;

  /** 
   * <em>status</em>カラムの値 
   */
  public  String  status;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean request_code_is_set = false;
  private boolean request_code_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean sonar_trader_code_is_set = false;
  private boolean sonar_trader_code_is_modified = false;
  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;
  private boolean sell_order_quantity_is_set = false;
  private boolean sell_order_quantity_is_modified = false;
  private boolean buy_order_quantity_is_set = false;
  private boolean buy_order_quantity_is_modified = false;
  private boolean sonar_repayment_type_is_set = false;
  private boolean sonar_repayment_type_is_modified = false;
  private boolean sonar_market_code_is_set = false;
  private boolean sonar_market_code_is_modified = false;
  private boolean ticket_number_is_set = false;
  private boolean ticket_number_is_modified = false;
  private boolean capital_gain_tax_type_is_set = false;
  private boolean capital_gain_tax_type_is_modified = false;
  private boolean check_type_is_set = false;
  private boolean check_type_is_modified = false;
  private boolean cancel_div_is_set = false;
  private boolean cancel_div_is_modified = false;
  private boolean order_request_number_is_set = false;
  private boolean order_request_number_is_modified = false;
  private boolean order_action_serial_no_is_set = false;
  private boolean order_action_serial_no_is_modified = false;
  private boolean create_datetime_is_set = false;
  private boolean create_datetime_is_modified = false;
  private boolean tax_type_is_set = false;
  private boolean tax_type_is_modified = false;
  private boolean swap_tax_type_is_set = false;
  private boolean swap_tax_type_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean rowid_is_set = false;
  private boolean rowid_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "rowid=" + rowid
      + "," + "request_code=" +request_code
      + "," + "account_id=" +account_id
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "," + "sonar_trader_code=" +sonar_trader_code
      + "," + "product_code=" +product_code
      + "," + "sell_order_quantity=" +sell_order_quantity
      + "," + "buy_order_quantity=" +buy_order_quantity
      + "," + "sonar_repayment_type=" +sonar_repayment_type
      + "," + "sonar_market_code=" +sonar_market_code
      + "," + "ticket_number=" +ticket_number
      + "," + "capital_gain_tax_type=" +capital_gain_tax_type
      + "," + "check_type=" +check_type
      + "," + "cancel_div=" +cancel_div
      + "," + "order_request_number=" +order_request_number
      + "," + "order_action_serial_no=" +order_action_serial_no
      + "," + "create_datetime=" +create_datetime
      + "," + "tax_type=" +tax_type
      + "," + "swap_tax_type=" +swap_tax_type
      + "," + "status=" +status
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のHostEqtypeSwapParamsオブジェクトを作成します。 
   */
  public HostEqtypeSwapParams() {
    rowid_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のHostEqtypeSwapRowオブジェクトの値を利用してHostEqtypeSwapParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するHostEqtypeSwapRowオブジェクト 
   */
  public HostEqtypeSwapParams( HostEqtypeSwapRow p_row )
  {
    rowid = p_row.getRowid();
    rowid_is_set = p_row.getRowidIsSet();
    rowid_is_modified = p_row.getRowidIsModified();
    request_code = p_row.getRequestCode();
    request_code_is_set = p_row.getRequestCodeIsSet();
    request_code_is_modified = p_row.getRequestCodeIsModified();
    if ( !p_row.getAccountIdIsNull() )
      account_id = new Long( p_row.getAccountId() );
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    sonar_trader_code = p_row.getSonarTraderCode();
    sonar_trader_code_is_set = p_row.getSonarTraderCodeIsSet();
    sonar_trader_code_is_modified = p_row.getSonarTraderCodeIsModified();
    product_code = p_row.getProductCode();
    product_code_is_set = p_row.getProductCodeIsSet();
    product_code_is_modified = p_row.getProductCodeIsModified();
    if ( !p_row.getSellOrderQuantityIsNull() )
      sell_order_quantity = new Double( p_row.getSellOrderQuantity() );
    sell_order_quantity_is_set = p_row.getSellOrderQuantityIsSet();
    sell_order_quantity_is_modified = p_row.getSellOrderQuantityIsModified();
    if ( !p_row.getBuyOrderQuantityIsNull() )
      buy_order_quantity = new Double( p_row.getBuyOrderQuantity() );
    buy_order_quantity_is_set = p_row.getBuyOrderQuantityIsSet();
    buy_order_quantity_is_modified = p_row.getBuyOrderQuantityIsModified();
    sonar_repayment_type = p_row.getSonarRepaymentType();
    sonar_repayment_type_is_set = p_row.getSonarRepaymentTypeIsSet();
    sonar_repayment_type_is_modified = p_row.getSonarRepaymentTypeIsModified();
    sonar_market_code = p_row.getSonarMarketCode();
    sonar_market_code_is_set = p_row.getSonarMarketCodeIsSet();
    sonar_market_code_is_modified = p_row.getSonarMarketCodeIsModified();
    ticket_number = p_row.getTicketNumber();
    ticket_number_is_set = p_row.getTicketNumberIsSet();
    ticket_number_is_modified = p_row.getTicketNumberIsModified();
    capital_gain_tax_type = p_row.getCapitalGainTaxType();
    capital_gain_tax_type_is_set = p_row.getCapitalGainTaxTypeIsSet();
    capital_gain_tax_type_is_modified = p_row.getCapitalGainTaxTypeIsModified();
    check_type = p_row.getCheckType();
    check_type_is_set = p_row.getCheckTypeIsSet();
    check_type_is_modified = p_row.getCheckTypeIsModified();
    cancel_div = p_row.getCancelDiv();
    cancel_div_is_set = p_row.getCancelDivIsSet();
    cancel_div_is_modified = p_row.getCancelDivIsModified();
    order_request_number = p_row.getOrderRequestNumber();
    order_request_number_is_set = p_row.getOrderRequestNumberIsSet();
    order_request_number_is_modified = p_row.getOrderRequestNumberIsModified();
    if ( !p_row.getOrderActionSerialNoIsNull() )
      order_action_serial_no = new Integer( p_row.getOrderActionSerialNo() );
    order_action_serial_no_is_set = p_row.getOrderActionSerialNoIsSet();
    order_action_serial_no_is_modified = p_row.getOrderActionSerialNoIsModified();
    create_datetime = p_row.getCreateDatetime();
    create_datetime_is_set = p_row.getCreateDatetimeIsSet();
    create_datetime_is_modified = p_row.getCreateDatetimeIsModified();
    tax_type = p_row.getTaxType();
    tax_type_is_set = p_row.getTaxTypeIsSet();
    tax_type_is_modified = p_row.getTaxTypeIsModified();
    swap_tax_type = p_row.getSwapTaxType();
    swap_tax_type_is_set = p_row.getSwapTaxTypeIsSet();
    swap_tax_type_is_modified = p_row.getSwapTaxTypeIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
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
      request_code_is_set = true;
      request_code_is_modified = true;
      account_id_is_set = true;
      account_id_is_modified = true;
      institution_code_is_set = true;
      institution_code_is_modified = true;
      branch_code_is_set = true;
      branch_code_is_modified = true;
      account_code_is_set = true;
      account_code_is_modified = true;
      sonar_trader_code_is_set = true;
      sonar_trader_code_is_modified = true;
      product_code_is_set = true;
      product_code_is_modified = true;
      sell_order_quantity_is_set = true;
      sell_order_quantity_is_modified = true;
      buy_order_quantity_is_set = true;
      buy_order_quantity_is_modified = true;
      sonar_repayment_type_is_set = true;
      sonar_repayment_type_is_modified = true;
      sonar_market_code_is_set = true;
      sonar_market_code_is_modified = true;
      ticket_number_is_set = true;
      ticket_number_is_modified = true;
      capital_gain_tax_type_is_set = true;
      capital_gain_tax_type_is_modified = true;
      check_type_is_set = true;
      check_type_is_modified = true;
      cancel_div_is_set = true;
      cancel_div_is_modified = true;
      order_request_number_is_set = true;
      order_request_number_is_modified = true;
      order_action_serial_no_is_set = true;
      order_action_serial_no_is_modified = true;
      create_datetime_is_set = true;
      create_datetime_is_modified = true;
      tax_type_is_set = true;
      tax_type_is_modified = true;
      swap_tax_type_is_set = true;
      swap_tax_type_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
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
    if ( !( other instanceof HostEqtypeSwapRow ) )
       return false;
    return fieldsEqual( (HostEqtypeSwapRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のHostEqtypeSwapRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( HostEqtypeSwapRow row )
  {
    if ( request_code == null ) {
      if ( row.getRequestCode() != null )
        return false;
    } else if ( !request_code.equals( row.getRequestCode() ) ) {
        return false;
    }
    if ( account_id == null ) {
      if ( !row.getAccountIdIsNull() )
        return false;
    } else if ( row.getAccountIdIsNull() || ( account_id.longValue() != row.getAccountId() ) ) {
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
    if ( sonar_trader_code == null ) {
      if ( row.getSonarTraderCode() != null )
        return false;
    } else if ( !sonar_trader_code.equals( row.getSonarTraderCode() ) ) {
        return false;
    }
    if ( product_code == null ) {
      if ( row.getProductCode() != null )
        return false;
    } else if ( !product_code.equals( row.getProductCode() ) ) {
        return false;
    }
    if ( sell_order_quantity == null ) {
      if ( !row.getSellOrderQuantityIsNull() )
        return false;
    } else if ( row.getSellOrderQuantityIsNull() || ( sell_order_quantity.doubleValue() != row.getSellOrderQuantity() ) ) {
        return false;
    }
    if ( buy_order_quantity == null ) {
      if ( !row.getBuyOrderQuantityIsNull() )
        return false;
    } else if ( row.getBuyOrderQuantityIsNull() || ( buy_order_quantity.doubleValue() != row.getBuyOrderQuantity() ) ) {
        return false;
    }
    if ( sonar_repayment_type == null ) {
      if ( row.getSonarRepaymentType() != null )
        return false;
    } else if ( !sonar_repayment_type.equals( row.getSonarRepaymentType() ) ) {
        return false;
    }
    if ( sonar_market_code == null ) {
      if ( row.getSonarMarketCode() != null )
        return false;
    } else if ( !sonar_market_code.equals( row.getSonarMarketCode() ) ) {
        return false;
    }
    if ( ticket_number == null ) {
      if ( row.getTicketNumber() != null )
        return false;
    } else if ( !ticket_number.equals( row.getTicketNumber() ) ) {
        return false;
    }
    if ( capital_gain_tax_type == null ) {
      if ( row.getCapitalGainTaxType() != null )
        return false;
    } else if ( !capital_gain_tax_type.equals( row.getCapitalGainTaxType() ) ) {
        return false;
    }
    if ( check_type == null ) {
      if ( row.getCheckType() != null )
        return false;
    } else if ( !check_type.equals( row.getCheckType() ) ) {
        return false;
    }
    if ( cancel_div == null ) {
      if ( row.getCancelDiv() != null )
        return false;
    } else if ( !cancel_div.equals( row.getCancelDiv() ) ) {
        return false;
    }
    if ( order_request_number == null ) {
      if ( row.getOrderRequestNumber() != null )
        return false;
    } else if ( !order_request_number.equals( row.getOrderRequestNumber() ) ) {
        return false;
    }
    if ( order_action_serial_no == null ) {
      if ( !row.getOrderActionSerialNoIsNull() )
        return false;
    } else if ( row.getOrderActionSerialNoIsNull() || ( order_action_serial_no.intValue() != row.getOrderActionSerialNo() ) ) {
        return false;
    }
    if ( create_datetime == null ) {
      if ( row.getCreateDatetime() != null )
        return false;
    } else if ( !create_datetime.equals( row.getCreateDatetime() ) ) {
        return false;
    }
    if ( tax_type == null ) {
      if ( row.getTaxType() != null )
        return false;
    } else if ( !tax_type.equals( row.getTaxType() ) ) {
        return false;
    }
    if ( swap_tax_type == null ) {
      if ( row.getSwapTaxType() != null )
        return false;
    } else if ( !swap_tax_type.equals( row.getSwapTaxType() ) ) {
        return false;
    }
    if ( status == null ) {
      if ( row.getStatus() != null )
        return false;
    } else if ( !status.equals( row.getStatus() ) ) {
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
        + (account_id!=null? account_id.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (sonar_trader_code!=null? sonar_trader_code.hashCode(): 0) 
        + (product_code!=null? product_code.hashCode(): 0) 
        + (sell_order_quantity!=null? sell_order_quantity.hashCode(): 0) 
        + (buy_order_quantity!=null? buy_order_quantity.hashCode(): 0) 
        + (sonar_repayment_type!=null? sonar_repayment_type.hashCode(): 0) 
        + (sonar_market_code!=null? sonar_market_code.hashCode(): 0) 
        + (ticket_number!=null? ticket_number.hashCode(): 0) 
        + (capital_gain_tax_type!=null? capital_gain_tax_type.hashCode(): 0) 
        + (check_type!=null? check_type.hashCode(): 0) 
        + (cancel_div!=null? cancel_div.hashCode(): 0) 
        + (order_request_number!=null? order_request_number.hashCode(): 0) 
        + (order_action_serial_no!=null? order_action_serial_no.hashCode(): 0) 
        + (create_datetime!=null? create_datetime.hashCode(): 0) 
        + (tax_type!=null? tax_type.hashCode(): 0) 
        + (swap_tax_type!=null? swap_tax_type.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (rowid!=null? rowid.hashCode(): 0) 
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
		if ( request_code != null )
			map.put("request_code",request_code);
		if ( account_id != null )
			map.put("account_id",account_id);
		if ( institution_code != null )
			map.put("institution_code",institution_code);
		if ( branch_code != null )
			map.put("branch_code",branch_code);
		if ( account_code != null )
			map.put("account_code",account_code);
		if ( sonar_trader_code != null )
			map.put("sonar_trader_code",sonar_trader_code);
		if ( product_code != null )
			map.put("product_code",product_code);
		if ( sell_order_quantity != null )
			map.put("sell_order_quantity",sell_order_quantity);
		if ( buy_order_quantity != null )
			map.put("buy_order_quantity",buy_order_quantity);
		if ( sonar_repayment_type != null )
			map.put("sonar_repayment_type",sonar_repayment_type);
		if ( sonar_market_code != null )
			map.put("sonar_market_code",sonar_market_code);
		if ( ticket_number != null )
			map.put("ticket_number",ticket_number);
		if ( capital_gain_tax_type != null )
			map.put("capital_gain_tax_type",capital_gain_tax_type);
		if ( check_type != null )
			map.put("check_type",check_type);
		if ( cancel_div != null )
			map.put("cancel_div",cancel_div);
		if ( order_request_number != null )
			map.put("order_request_number",order_request_number);
		if ( order_action_serial_no != null )
			map.put("order_action_serial_no",order_action_serial_no);
		if ( create_datetime != null )
			map.put("create_datetime",create_datetime);
		if ( tax_type != null )
			map.put("tax_type",tax_type);
		if ( swap_tax_type != null )
			map.put("swap_tax_type",swap_tax_type);
		if ( status != null )
			map.put("status",status);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
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
		if ( account_id_is_modified )
			map.put("account_id",account_id);
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( branch_code_is_modified )
			map.put("branch_code",branch_code);
		if ( account_code_is_modified )
			map.put("account_code",account_code);
		if ( sonar_trader_code_is_modified )
			map.put("sonar_trader_code",sonar_trader_code);
		if ( product_code_is_modified )
			map.put("product_code",product_code);
		if ( sell_order_quantity_is_modified )
			map.put("sell_order_quantity",sell_order_quantity);
		if ( buy_order_quantity_is_modified )
			map.put("buy_order_quantity",buy_order_quantity);
		if ( sonar_repayment_type_is_modified )
			map.put("sonar_repayment_type",sonar_repayment_type);
		if ( sonar_market_code_is_modified )
			map.put("sonar_market_code",sonar_market_code);
		if ( ticket_number_is_modified )
			map.put("ticket_number",ticket_number);
		if ( capital_gain_tax_type_is_modified )
			map.put("capital_gain_tax_type",capital_gain_tax_type);
		if ( check_type_is_modified )
			map.put("check_type",check_type);
		if ( cancel_div_is_modified )
			map.put("cancel_div",cancel_div);
		if ( order_request_number_is_modified )
			map.put("order_request_number",order_request_number);
		if ( order_action_serial_no_is_modified )
			map.put("order_action_serial_no",order_action_serial_no);
		if ( create_datetime_is_modified )
			map.put("create_datetime",create_datetime);
		if ( tax_type_is_modified )
			map.put("tax_type",tax_type);
		if ( swap_tax_type_is_modified )
			map.put("swap_tax_type",swap_tax_type);
		if ( status_is_modified )
			map.put("status",status);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("request_code",request_code);
			map.put("account_id",account_id);
			map.put("institution_code",institution_code);
			map.put("branch_code",branch_code);
			map.put("account_code",account_code);
			map.put("sonar_trader_code",sonar_trader_code);
			map.put("product_code",product_code);
			map.put("sell_order_quantity",sell_order_quantity);
			map.put("buy_order_quantity",buy_order_quantity);
			map.put("sonar_repayment_type",sonar_repayment_type);
			map.put("sonar_market_code",sonar_market_code);
			map.put("ticket_number",ticket_number);
			map.put("capital_gain_tax_type",capital_gain_tax_type);
			map.put("check_type",check_type);
			map.put("cancel_div",cancel_div);
			map.put("order_request_number",order_request_number);
			map.put("order_action_serial_no",order_action_serial_no);
			map.put("create_datetime",create_datetime);
			map.put("tax_type",tax_type);
			map.put("swap_tax_type",swap_tax_type);
			map.put("status",status);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
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
   * <em>account_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAccountId()
  {
    return ( account_id==null? ((long)0): account_id.longValue() );
  }


  /** 
   * <em>account_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAccountIdIsNull()
  {
    return account_id == null;
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
   * <em>sonar_trader_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSonarTraderCode()
  {
    return sonar_trader_code;
  }


  /** 
   * <em>sonar_trader_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarTraderCodeIsSet() {
    return sonar_trader_code_is_set;
  }


  /** 
   * <em>sonar_trader_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarTraderCodeIsModified() {
    return sonar_trader_code_is_modified;
  }


  /** 
   * <em>product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getProductCode()
  {
    return product_code;
  }


  /** 
   * <em>product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductCodeIsSet() {
    return product_code_is_set;
  }


  /** 
   * <em>product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductCodeIsModified() {
    return product_code_is_modified;
  }


  /** 
   * <em>sell_order_quantity</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSellOrderQuantity()
  {
    return ( sell_order_quantity==null? ((double)0): sell_order_quantity.doubleValue() );
  }


  /** 
   * <em>sell_order_quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSellOrderQuantityIsNull()
  {
    return sell_order_quantity == null;
  }


  /** 
   * <em>sell_order_quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellOrderQuantityIsSet() {
    return sell_order_quantity_is_set;
  }


  /** 
   * <em>sell_order_quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellOrderQuantityIsModified() {
    return sell_order_quantity_is_modified;
  }


  /** 
   * <em>buy_order_quantity</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getBuyOrderQuantity()
  {
    return ( buy_order_quantity==null? ((double)0): buy_order_quantity.doubleValue() );
  }


  /** 
   * <em>buy_order_quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getBuyOrderQuantityIsNull()
  {
    return buy_order_quantity == null;
  }


  /** 
   * <em>buy_order_quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyOrderQuantityIsSet() {
    return buy_order_quantity_is_set;
  }


  /** 
   * <em>buy_order_quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyOrderQuantityIsModified() {
    return buy_order_quantity_is_modified;
  }


  /** 
   * <em>sonar_repayment_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSonarRepaymentType()
  {
    return sonar_repayment_type;
  }


  /** 
   * <em>sonar_repayment_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarRepaymentTypeIsSet() {
    return sonar_repayment_type_is_set;
  }


  /** 
   * <em>sonar_repayment_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarRepaymentTypeIsModified() {
    return sonar_repayment_type_is_modified;
  }


  /** 
   * <em>sonar_market_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSonarMarketCode()
  {
    return sonar_market_code;
  }


  /** 
   * <em>sonar_market_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarMarketCodeIsSet() {
    return sonar_market_code_is_set;
  }


  /** 
   * <em>sonar_market_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarMarketCodeIsModified() {
    return sonar_market_code_is_modified;
  }


  /** 
   * <em>ticket_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTicketNumber()
  {
    return ticket_number;
  }


  /** 
   * <em>ticket_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTicketNumberIsSet() {
    return ticket_number_is_set;
  }


  /** 
   * <em>ticket_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTicketNumberIsModified() {
    return ticket_number_is_modified;
  }


  /** 
   * <em>capital_gain_tax_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCapitalGainTaxType()
  {
    return capital_gain_tax_type;
  }


  /** 
   * <em>capital_gain_tax_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCapitalGainTaxTypeIsSet() {
    return capital_gain_tax_type_is_set;
  }


  /** 
   * <em>capital_gain_tax_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCapitalGainTaxTypeIsModified() {
    return capital_gain_tax_type_is_modified;
  }


  /** 
   * <em>check_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCheckType()
  {
    return check_type;
  }


  /** 
   * <em>check_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCheckTypeIsSet() {
    return check_type_is_set;
  }


  /** 
   * <em>check_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCheckTypeIsModified() {
    return check_type_is_modified;
  }


  /** 
   * <em>cancel_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCancelDiv()
  {
    return cancel_div;
  }


  /** 
   * <em>cancel_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCancelDivIsSet() {
    return cancel_div_is_set;
  }


  /** 
   * <em>cancel_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCancelDivIsModified() {
    return cancel_div_is_modified;
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
   * <em>order_action_serial_no</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getOrderActionSerialNo()
  {
    return ( order_action_serial_no==null? ((int)0): order_action_serial_no.intValue() );
  }


  /** 
   * <em>order_action_serial_no</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOrderActionSerialNoIsNull()
  {
    return order_action_serial_no == null;
  }


  /** 
   * <em>order_action_serial_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderActionSerialNoIsSet() {
    return order_action_serial_no_is_set;
  }


  /** 
   * <em>order_action_serial_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderActionSerialNoIsModified() {
    return order_action_serial_no_is_modified;
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
   * <em>tax_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTaxType()
  {
    return tax_type;
  }


  /** 
   * <em>tax_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTaxTypeIsSet() {
    return tax_type_is_set;
  }


  /** 
   * <em>tax_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTaxTypeIsModified() {
    return tax_type_is_modified;
  }


  /** 
   * <em>swap_tax_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSwapTaxType()
  {
    return swap_tax_type;
  }


  /** 
   * <em>swap_tax_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwapTaxTypeIsSet() {
    return swap_tax_type_is_set;
  }


  /** 
   * <em>swap_tax_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwapTaxTypeIsModified() {
    return swap_tax_type_is_modified;
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
    return new HostEqtypeSwapPK(rowid);
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
   * <em>account_id</em>カラムの値を設定します。 
   *
   * @@param p_accountId <em>account_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setAccountId( long p_accountId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_id = new Long(p_accountId);
    account_id_is_set = true;
    account_id_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>account_id</em>カラムに値を設定します。 
   */
  public final void setAccountId( Long p_accountId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    account_id = p_accountId;
    account_id_is_set = true;
    account_id_is_modified = true;
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
   * <em>sonar_trader_code</em>カラムの値を設定します。 
   *
   * @@param p_sonarTraderCode <em>sonar_trader_code</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setSonarTraderCode( String p_sonarTraderCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sonar_trader_code = p_sonarTraderCode;
    sonar_trader_code_is_set = true;
    sonar_trader_code_is_modified = true;
  }


  /** 
   * <em>product_code</em>カラムの値を設定します。 
   *
   * @@param p_productCode <em>product_code</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setProductCode( String p_productCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_code = p_productCode;
    product_code_is_set = true;
    product_code_is_modified = true;
  }


  /** 
   * <em>sell_order_quantity</em>カラムの値を設定します。 
   *
   * @@param p_sellOrderQuantity <em>sell_order_quantity</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSellOrderQuantity( double p_sellOrderQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_order_quantity = new Double(p_sellOrderQuantity);
    sell_order_quantity_is_set = true;
    sell_order_quantity_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>sell_order_quantity</em>カラムに値を設定します。 
   */
  public final void setSellOrderQuantity( Double p_sellOrderQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sell_order_quantity = p_sellOrderQuantity;
    sell_order_quantity_is_set = true;
    sell_order_quantity_is_modified = true;
  }


  /** 
   * <em>buy_order_quantity</em>カラムの値を設定します。 
   *
   * @@param p_buyOrderQuantity <em>buy_order_quantity</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setBuyOrderQuantity( double p_buyOrderQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_order_quantity = new Double(p_buyOrderQuantity);
    buy_order_quantity_is_set = true;
    buy_order_quantity_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>buy_order_quantity</em>カラムに値を設定します。 
   */
  public final void setBuyOrderQuantity( Double p_buyOrderQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    buy_order_quantity = p_buyOrderQuantity;
    buy_order_quantity_is_set = true;
    buy_order_quantity_is_modified = true;
  }


  /** 
   * <em>sonar_repayment_type</em>カラムの値を設定します。 
   *
   * @@param p_sonarRepaymentType <em>sonar_repayment_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSonarRepaymentType( String p_sonarRepaymentType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sonar_repayment_type = p_sonarRepaymentType;
    sonar_repayment_type_is_set = true;
    sonar_repayment_type_is_modified = true;
  }


  /** 
   * <em>sonar_market_code</em>カラムの値を設定します。 
   *
   * @@param p_sonarMarketCode <em>sonar_market_code</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSonarMarketCode( String p_sonarMarketCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sonar_market_code = p_sonarMarketCode;
    sonar_market_code_is_set = true;
    sonar_market_code_is_modified = true;
  }


  /** 
   * <em>ticket_number</em>カラムの値を設定します。 
   *
   * @@param p_ticketNumber <em>ticket_number</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setTicketNumber( String p_ticketNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ticket_number = p_ticketNumber;
    ticket_number_is_set = true;
    ticket_number_is_modified = true;
  }


  /** 
   * <em>capital_gain_tax_type</em>カラムの値を設定します。 
   *
   * @@param p_capitalGainTaxType <em>capital_gain_tax_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCapitalGainTaxType( String p_capitalGainTaxType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    capital_gain_tax_type = p_capitalGainTaxType;
    capital_gain_tax_type_is_set = true;
    capital_gain_tax_type_is_modified = true;
  }


  /** 
   * <em>check_type</em>カラムの値を設定します。 
   *
   * @@param p_checkType <em>check_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCheckType( String p_checkType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    check_type = p_checkType;
    check_type_is_set = true;
    check_type_is_modified = true;
  }


  /** 
   * <em>cancel_div</em>カラムの値を設定します。 
   *
   * @@param p_cancelDiv <em>cancel_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCancelDiv( String p_cancelDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cancel_div = p_cancelDiv;
    cancel_div_is_set = true;
    cancel_div_is_modified = true;
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
   * <em>order_action_serial_no</em>カラムの値を設定します。 
   *
   * @@param p_orderActionSerialNo <em>order_action_serial_no</em>カラムの値をあらわす8桁以下のint値 
   */
  public final void setOrderActionSerialNo( int p_orderActionSerialNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_action_serial_no = new Integer(p_orderActionSerialNo);
    order_action_serial_no_is_set = true;
    order_action_serial_no_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>order_action_serial_no</em>カラムに値を設定します。 
   */
  public final void setOrderActionSerialNo( Integer p_orderActionSerialNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    order_action_serial_no = p_orderActionSerialNo;
    order_action_serial_no_is_set = true;
    order_action_serial_no_is_modified = true;
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
   * <em>tax_type</em>カラムの値を設定します。 
   *
   * @@param p_taxType <em>tax_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTaxType( String p_taxType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tax_type = p_taxType;
    tax_type_is_set = true;
    tax_type_is_modified = true;
  }


  /** 
   * <em>swap_tax_type</em>カラムの値を設定します。 
   *
   * @@param p_swapTaxType <em>swap_tax_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSwapTaxType( String p_swapTaxType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    swap_tax_type = p_swapTaxType;
    swap_tax_type_is_set = true;
    swap_tax_type_is_modified = true;
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
                if ( name.equals("account_id") ) {
                    return this.account_id;
                }
                else if ( name.equals("account_code") ) {
                    return this.account_code;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                else if ( name.equals("buy_order_quantity") ) {
                    return this.buy_order_quantity;
                }
                break;
            case 'c':
                if ( name.equals("capital_gain_tax_type") ) {
                    return this.capital_gain_tax_type;
                }
                else if ( name.equals("check_type") ) {
                    return this.check_type;
                }
                else if ( name.equals("cancel_div") ) {
                    return this.cancel_div;
                }
                else if ( name.equals("create_datetime") ) {
                    return this.create_datetime;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
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
            case 'o':
                if ( name.equals("order_request_number") ) {
                    return this.order_request_number;
                }
                else if ( name.equals("order_action_serial_no") ) {
                    return this.order_action_serial_no;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    return this.product_code;
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
                if ( name.equals("sonar_trader_code") ) {
                    return this.sonar_trader_code;
                }
                else if ( name.equals("sell_order_quantity") ) {
                    return this.sell_order_quantity;
                }
                else if ( name.equals("sonar_repayment_type") ) {
                    return this.sonar_repayment_type;
                }
                else if ( name.equals("sonar_market_code") ) {
                    return this.sonar_market_code;
                }
                else if ( name.equals("swap_tax_type") ) {
                    return this.swap_tax_type;
                }
                else if ( name.equals("status") ) {
                    return this.status;
                }
                break;
            case 't':
                if ( name.equals("ticket_number") ) {
                    return this.ticket_number;
                }
                else if ( name.equals("tax_type") ) {
                    return this.tax_type;
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
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'account_id' must be Long: '"+value+"' is not." );
                    this.account_id = (Long) value;
                    if (this.account_id_is_set)
                        this.account_id_is_modified = true;
                    this.account_id_is_set = true;
                    return;
                }
                else if ( name.equals("account_code") ) {
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
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'branch_code' must be String: '"+value+"' is not." );
                    this.branch_code = (String) value;
                    if (this.branch_code_is_set)
                        this.branch_code_is_modified = true;
                    this.branch_code_is_set = true;
                    return;
                }
                else if ( name.equals("buy_order_quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'buy_order_quantity' must be Double: '"+value+"' is not." );
                    this.buy_order_quantity = (Double) value;
                    if (this.buy_order_quantity_is_set)
                        this.buy_order_quantity_is_modified = true;
                    this.buy_order_quantity_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("capital_gain_tax_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'capital_gain_tax_type' must be String: '"+value+"' is not." );
                    this.capital_gain_tax_type = (String) value;
                    if (this.capital_gain_tax_type_is_set)
                        this.capital_gain_tax_type_is_modified = true;
                    this.capital_gain_tax_type_is_set = true;
                    return;
                }
                else if ( name.equals("check_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'check_type' must be String: '"+value+"' is not." );
                    this.check_type = (String) value;
                    if (this.check_type_is_set)
                        this.check_type_is_modified = true;
                    this.check_type_is_set = true;
                    return;
                }
                else if ( name.equals("cancel_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cancel_div' must be String: '"+value+"' is not." );
                    this.cancel_div = (String) value;
                    if (this.cancel_div_is_set)
                        this.cancel_div_is_modified = true;
                    this.cancel_div_is_set = true;
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
            case 'i':
                if ( name.equals("institution_code") ) {
                    if ( value != null )
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
            case 'o':
                if ( name.equals("order_request_number") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_request_number' must be String: '"+value+"' is not." );
                    this.order_request_number = (String) value;
                    if (this.order_request_number_is_set)
                        this.order_request_number_is_modified = true;
                    this.order_request_number_is_set = true;
                    return;
                }
                else if ( name.equals("order_action_serial_no") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'order_action_serial_no' must be Integer: '"+value+"' is not." );
                    this.order_action_serial_no = (Integer) value;
                    if (this.order_action_serial_no_is_set)
                        this.order_action_serial_no_is_modified = true;
                    this.order_action_serial_no_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_code' must be String: '"+value+"' is not." );
                    this.product_code = (String) value;
                    if (this.product_code_is_set)
                        this.product_code_is_modified = true;
                    this.product_code_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("request_code") ) {
                    if ( value != null )
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
                if ( name.equals("sonar_trader_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sonar_trader_code' must be String: '"+value+"' is not." );
                    this.sonar_trader_code = (String) value;
                    if (this.sonar_trader_code_is_set)
                        this.sonar_trader_code_is_modified = true;
                    this.sonar_trader_code_is_set = true;
                    return;
                }
                else if ( name.equals("sell_order_quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'sell_order_quantity' must be Double: '"+value+"' is not." );
                    this.sell_order_quantity = (Double) value;
                    if (this.sell_order_quantity_is_set)
                        this.sell_order_quantity_is_modified = true;
                    this.sell_order_quantity_is_set = true;
                    return;
                }
                else if ( name.equals("sonar_repayment_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sonar_repayment_type' must be String: '"+value+"' is not." );
                    this.sonar_repayment_type = (String) value;
                    if (this.sonar_repayment_type_is_set)
                        this.sonar_repayment_type_is_modified = true;
                    this.sonar_repayment_type_is_set = true;
                    return;
                }
                else if ( name.equals("sonar_market_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sonar_market_code' must be String: '"+value+"' is not." );
                    this.sonar_market_code = (String) value;
                    if (this.sonar_market_code_is_set)
                        this.sonar_market_code_is_modified = true;
                    this.sonar_market_code_is_set = true;
                    return;
                }
                else if ( name.equals("swap_tax_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'swap_tax_type' must be String: '"+value+"' is not." );
                    this.swap_tax_type = (String) value;
                    if (this.swap_tax_type_is_set)
                        this.swap_tax_type_is_modified = true;
                    this.swap_tax_type_is_set = true;
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
                if ( name.equals("ticket_number") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ticket_number' must be String: '"+value+"' is not." );
                    this.ticket_number = (String) value;
                    if (this.ticket_number_is_set)
                        this.ticket_number_is_modified = true;
                    this.ticket_number_is_set = true;
                    return;
                }
                else if ( name.equals("tax_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'tax_type' must be String: '"+value+"' is not." );
                    this.tax_type = (String) value;
                    if (this.tax_type_is_set)
                        this.tax_type_is_modified = true;
                    this.tax_type_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
