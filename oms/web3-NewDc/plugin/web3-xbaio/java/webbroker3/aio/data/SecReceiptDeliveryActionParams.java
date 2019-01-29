head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.48.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	SecReceiptDeliveryActionParams.java;


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
 * sec_receipt_delivery_actionテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link SecReceiptDeliveryActionRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link SecReceiptDeliveryActionParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link SecReceiptDeliveryActionParams}が{@@link SecReceiptDeliveryActionRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SecReceiptDeliveryActionPK 
 * @@see SecReceiptDeliveryActionRow 
 */
public class SecReceiptDeliveryActionParams extends Params implements SecReceiptDeliveryActionRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "sec_receipt_delivery_action";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = SecReceiptDeliveryActionRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return SecReceiptDeliveryActionRow.TYPE;
  }


  /** 
   * <em>sec_receipt_delivery_action_id</em>カラムの値 
   */
  public  long  sec_receipt_delivery_action_id;

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
   * <em>work_date</em>カラムの値 
   */
  public  java.sql.Timestamp  work_date;

  /** 
   * <em>execute_date</em>カラムの値 
   */
  public  java.sql.Timestamp  execute_date;

  /** 
   * <em>delivery_date</em>カラムの値 
   */
  public  java.sql.Timestamp  delivery_date;

  /** 
   * <em>record_div</em>カラムの値 
   */
  public  String  record_div;

  /** 
   * <em>product_div</em>カラムの値 
   */
  public  String  product_div;

  /** 
   * <em>product_group</em>カラムの値 
   */
  public  String  product_group;

  /** 
   * <em>product_code</em>カラムの値 
   */
  public  String  product_code;

  /** 
   * <em>name_method_div</em>カラムの値 
   */
  public  String  name_method_div;

  /** 
   * <em>remarks_div</em>カラムの値 
   */
  public  String  remarks_div;

  /** 
   * <em>custdy_div</em>カラムの値 
   */
  public  String  custdy_div;

  /** 
   * <em>deal_div</em>カラムの値 
   */
  public  String  deal_div;

  /** 
   * <em>io_div</em>カラムの値 
   */
  public  String  io_div;

  /** 
   * <em>io_group</em>カラムの値 
   */
  public  String  io_group;

  /** 
   * <em>sec_receipt_delivery_group</em>カラムの値 
   */
  public  String  sec_receipt_delivery_group;

  /** 
   * <em>market_div</em>カラムの値 
   */
  public  String  market_div;

  /** 
   * <em>quantity</em>カラムの値 
   */
  public  Long  quantity;

  /** 
   * <em>executed_price</em>カラムの値 
   */
  public  Double  executed_price;

  /** 
   * <em>executed_amount</em>カラムの値 
   */
  public  Long  executed_amount;

  /** 
   * <em>quantity_unit</em>カラムの値 
   */
  public  String  quantity_unit;

  /** 
   * <em>delivery_method</em>カラムの値 
   */
  public  String  delivery_method;

  /** 
   * <em>foreign_sec_div</em>カラムの値 
   */
  public  String  foreign_sec_div;

  /** 
   * <em>settlement_div</em>カラムの値 
   */
  public  String  settlement_div;

  /** 
   * <em>exchange_rate</em>カラムの値 
   */
  public  Double  exchange_rate;

  /** 
   * <em>account_name</em>カラムの値 
   */
  public  String  account_name;

  /** 
   * <em>product_name</em>カラムの値 
   */
  public  String  product_name;

  /** 
   * <em>specific_account_div</em>カラムの値 
   */
  public  String  specific_account_div;

  /** 
   * <em>delivery_div</em>カラムの値 
   */
  public  String  delivery_div;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  private boolean sec_receipt_delivery_action_id_is_set = false;
  private boolean sec_receipt_delivery_action_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean trader_code_is_set = false;
  private boolean trader_code_is_modified = false;
  private boolean work_date_is_set = false;
  private boolean work_date_is_modified = false;
  private boolean execute_date_is_set = false;
  private boolean execute_date_is_modified = false;
  private boolean delivery_date_is_set = false;
  private boolean delivery_date_is_modified = false;
  private boolean record_div_is_set = false;
  private boolean record_div_is_modified = false;
  private boolean product_div_is_set = false;
  private boolean product_div_is_modified = false;
  private boolean product_group_is_set = false;
  private boolean product_group_is_modified = false;
  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;
  private boolean name_method_div_is_set = false;
  private boolean name_method_div_is_modified = false;
  private boolean remarks_div_is_set = false;
  private boolean remarks_div_is_modified = false;
  private boolean custdy_div_is_set = false;
  private boolean custdy_div_is_modified = false;
  private boolean deal_div_is_set = false;
  private boolean deal_div_is_modified = false;
  private boolean io_div_is_set = false;
  private boolean io_div_is_modified = false;
  private boolean io_group_is_set = false;
  private boolean io_group_is_modified = false;
  private boolean sec_receipt_delivery_group_is_set = false;
  private boolean sec_receipt_delivery_group_is_modified = false;
  private boolean market_div_is_set = false;
  private boolean market_div_is_modified = false;
  private boolean quantity_is_set = false;
  private boolean quantity_is_modified = false;
  private boolean executed_price_is_set = false;
  private boolean executed_price_is_modified = false;
  private boolean executed_amount_is_set = false;
  private boolean executed_amount_is_modified = false;
  private boolean quantity_unit_is_set = false;
  private boolean quantity_unit_is_modified = false;
  private boolean delivery_method_is_set = false;
  private boolean delivery_method_is_modified = false;
  private boolean foreign_sec_div_is_set = false;
  private boolean foreign_sec_div_is_modified = false;
  private boolean settlement_div_is_set = false;
  private boolean settlement_div_is_modified = false;
  private boolean exchange_rate_is_set = false;
  private boolean exchange_rate_is_modified = false;
  private boolean account_name_is_set = false;
  private boolean account_name_is_modified = false;
  private boolean product_name_is_set = false;
  private boolean product_name_is_modified = false;
  private boolean specific_account_div_is_set = false;
  private boolean specific_account_div_is_modified = false;
  private boolean delivery_div_is_set = false;
  private boolean delivery_div_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "sec_receipt_delivery_action_id=" + sec_receipt_delivery_action_id
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "," + "trader_code=" +trader_code
      + "," + "work_date=" +work_date
      + "," + "execute_date=" +execute_date
      + "," + "delivery_date=" +delivery_date
      + "," + "record_div=" +record_div
      + "," + "product_div=" +product_div
      + "," + "product_group=" +product_group
      + "," + "product_code=" +product_code
      + "," + "name_method_div=" +name_method_div
      + "," + "remarks_div=" +remarks_div
      + "," + "custdy_div=" +custdy_div
      + "," + "deal_div=" +deal_div
      + "," + "io_div=" +io_div
      + "," + "io_group=" +io_group
      + "," + "sec_receipt_delivery_group=" +sec_receipt_delivery_group
      + "," + "market_div=" +market_div
      + "," + "quantity=" +quantity
      + "," + "executed_price=" +executed_price
      + "," + "executed_amount=" +executed_amount
      + "," + "quantity_unit=" +quantity_unit
      + "," + "delivery_method=" +delivery_method
      + "," + "foreign_sec_div=" +foreign_sec_div
      + "," + "settlement_div=" +settlement_div
      + "," + "exchange_rate=" +exchange_rate
      + "," + "account_name=" +account_name
      + "," + "product_name=" +product_name
      + "," + "specific_account_div=" +specific_account_div
      + "," + "delivery_div=" +delivery_div
      + "," + "created_timestamp=" +created_timestamp
      + "}";
  }


  /** 
   * 値が未設定のSecReceiptDeliveryActionParamsオブジェクトを作成します。 
   */
  public SecReceiptDeliveryActionParams() {
    sec_receipt_delivery_action_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のSecReceiptDeliveryActionRowオブジェクトの値を利用してSecReceiptDeliveryActionParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するSecReceiptDeliveryActionRowオブジェクト 
   */
  public SecReceiptDeliveryActionParams( SecReceiptDeliveryActionRow p_row )
  {
    sec_receipt_delivery_action_id = p_row.getSecReceiptDeliveryActionId();
    sec_receipt_delivery_action_id_is_set = p_row.getSecReceiptDeliveryActionIdIsSet();
    sec_receipt_delivery_action_id_is_modified = p_row.getSecReceiptDeliveryActionIdIsModified();
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
    work_date = p_row.getWorkDate();
    work_date_is_set = p_row.getWorkDateIsSet();
    work_date_is_modified = p_row.getWorkDateIsModified();
    execute_date = p_row.getExecuteDate();
    execute_date_is_set = p_row.getExecuteDateIsSet();
    execute_date_is_modified = p_row.getExecuteDateIsModified();
    delivery_date = p_row.getDeliveryDate();
    delivery_date_is_set = p_row.getDeliveryDateIsSet();
    delivery_date_is_modified = p_row.getDeliveryDateIsModified();
    record_div = p_row.getRecordDiv();
    record_div_is_set = p_row.getRecordDivIsSet();
    record_div_is_modified = p_row.getRecordDivIsModified();
    product_div = p_row.getProductDiv();
    product_div_is_set = p_row.getProductDivIsSet();
    product_div_is_modified = p_row.getProductDivIsModified();
    product_group = p_row.getProductGroup();
    product_group_is_set = p_row.getProductGroupIsSet();
    product_group_is_modified = p_row.getProductGroupIsModified();
    product_code = p_row.getProductCode();
    product_code_is_set = p_row.getProductCodeIsSet();
    product_code_is_modified = p_row.getProductCodeIsModified();
    name_method_div = p_row.getNameMethodDiv();
    name_method_div_is_set = p_row.getNameMethodDivIsSet();
    name_method_div_is_modified = p_row.getNameMethodDivIsModified();
    remarks_div = p_row.getRemarksDiv();
    remarks_div_is_set = p_row.getRemarksDivIsSet();
    remarks_div_is_modified = p_row.getRemarksDivIsModified();
    custdy_div = p_row.getCustdyDiv();
    custdy_div_is_set = p_row.getCustdyDivIsSet();
    custdy_div_is_modified = p_row.getCustdyDivIsModified();
    deal_div = p_row.getDealDiv();
    deal_div_is_set = p_row.getDealDivIsSet();
    deal_div_is_modified = p_row.getDealDivIsModified();
    io_div = p_row.getIoDiv();
    io_div_is_set = p_row.getIoDivIsSet();
    io_div_is_modified = p_row.getIoDivIsModified();
    io_group = p_row.getIoGroup();
    io_group_is_set = p_row.getIoGroupIsSet();
    io_group_is_modified = p_row.getIoGroupIsModified();
    sec_receipt_delivery_group = p_row.getSecReceiptDeliveryGroup();
    sec_receipt_delivery_group_is_set = p_row.getSecReceiptDeliveryGroupIsSet();
    sec_receipt_delivery_group_is_modified = p_row.getSecReceiptDeliveryGroupIsModified();
    market_div = p_row.getMarketDiv();
    market_div_is_set = p_row.getMarketDivIsSet();
    market_div_is_modified = p_row.getMarketDivIsModified();
    if ( !p_row.getQuantityIsNull() )
      quantity = new Long( p_row.getQuantity() );
    quantity_is_set = p_row.getQuantityIsSet();
    quantity_is_modified = p_row.getQuantityIsModified();
    if ( !p_row.getExecutedPriceIsNull() )
      executed_price = new Double( p_row.getExecutedPrice() );
    executed_price_is_set = p_row.getExecutedPriceIsSet();
    executed_price_is_modified = p_row.getExecutedPriceIsModified();
    if ( !p_row.getExecutedAmountIsNull() )
      executed_amount = new Long( p_row.getExecutedAmount() );
    executed_amount_is_set = p_row.getExecutedAmountIsSet();
    executed_amount_is_modified = p_row.getExecutedAmountIsModified();
    quantity_unit = p_row.getQuantityUnit();
    quantity_unit_is_set = p_row.getQuantityUnitIsSet();
    quantity_unit_is_modified = p_row.getQuantityUnitIsModified();
    delivery_method = p_row.getDeliveryMethod();
    delivery_method_is_set = p_row.getDeliveryMethodIsSet();
    delivery_method_is_modified = p_row.getDeliveryMethodIsModified();
    foreign_sec_div = p_row.getForeignSecDiv();
    foreign_sec_div_is_set = p_row.getForeignSecDivIsSet();
    foreign_sec_div_is_modified = p_row.getForeignSecDivIsModified();
    settlement_div = p_row.getSettlementDiv();
    settlement_div_is_set = p_row.getSettlementDivIsSet();
    settlement_div_is_modified = p_row.getSettlementDivIsModified();
    if ( !p_row.getExchangeRateIsNull() )
      exchange_rate = new Double( p_row.getExchangeRate() );
    exchange_rate_is_set = p_row.getExchangeRateIsSet();
    exchange_rate_is_modified = p_row.getExchangeRateIsModified();
    account_name = p_row.getAccountName();
    account_name_is_set = p_row.getAccountNameIsSet();
    account_name_is_modified = p_row.getAccountNameIsModified();
    product_name = p_row.getProductName();
    product_name_is_set = p_row.getProductNameIsSet();
    product_name_is_modified = p_row.getProductNameIsModified();
    specific_account_div = p_row.getSpecificAccountDiv();
    specific_account_div_is_set = p_row.getSpecificAccountDivIsSet();
    specific_account_div_is_modified = p_row.getSpecificAccountDivIsModified();
    delivery_div = p_row.getDeliveryDiv();
    delivery_div_is_set = p_row.getDeliveryDivIsSet();
    delivery_div_is_modified = p_row.getDeliveryDivIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      institution_code_is_set = true;
      institution_code_is_modified = true;
      branch_code_is_set = true;
      branch_code_is_modified = true;
      account_code_is_set = true;
      account_code_is_modified = true;
      trader_code_is_set = true;
      trader_code_is_modified = true;
      work_date_is_set = true;
      work_date_is_modified = true;
      execute_date_is_set = true;
      execute_date_is_modified = true;
      delivery_date_is_set = true;
      delivery_date_is_modified = true;
      record_div_is_set = true;
      record_div_is_modified = true;
      product_div_is_set = true;
      product_div_is_modified = true;
      product_group_is_set = true;
      product_group_is_modified = true;
      product_code_is_set = true;
      product_code_is_modified = true;
      name_method_div_is_set = true;
      name_method_div_is_modified = true;
      remarks_div_is_set = true;
      remarks_div_is_modified = true;
      custdy_div_is_set = true;
      custdy_div_is_modified = true;
      deal_div_is_set = true;
      deal_div_is_modified = true;
      io_div_is_set = true;
      io_div_is_modified = true;
      io_group_is_set = true;
      io_group_is_modified = true;
      sec_receipt_delivery_group_is_set = true;
      sec_receipt_delivery_group_is_modified = true;
      market_div_is_set = true;
      market_div_is_modified = true;
      quantity_is_set = true;
      quantity_is_modified = true;
      executed_price_is_set = true;
      executed_price_is_modified = true;
      executed_amount_is_set = true;
      executed_amount_is_modified = true;
      quantity_unit_is_set = true;
      quantity_unit_is_modified = true;
      delivery_method_is_set = true;
      delivery_method_is_modified = true;
      foreign_sec_div_is_set = true;
      foreign_sec_div_is_modified = true;
      settlement_div_is_set = true;
      settlement_div_is_modified = true;
      exchange_rate_is_set = true;
      exchange_rate_is_modified = true;
      account_name_is_set = true;
      account_name_is_modified = true;
      product_name_is_set = true;
      product_name_is_modified = true;
      specific_account_div_is_set = true;
      specific_account_div_is_modified = true;
      delivery_div_is_set = true;
      delivery_div_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof SecReceiptDeliveryActionRow ) )
       return false;
    return fieldsEqual( (SecReceiptDeliveryActionRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のSecReceiptDeliveryActionRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( SecReceiptDeliveryActionRow row )
  {
    if ( sec_receipt_delivery_action_id != row.getSecReceiptDeliveryActionId() )
      return false;
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
    if ( work_date == null ) {
      if ( row.getWorkDate() != null )
        return false;
    } else if ( !work_date.equals( row.getWorkDate() ) ) {
        return false;
    }
    if ( execute_date == null ) {
      if ( row.getExecuteDate() != null )
        return false;
    } else if ( !execute_date.equals( row.getExecuteDate() ) ) {
        return false;
    }
    if ( delivery_date == null ) {
      if ( row.getDeliveryDate() != null )
        return false;
    } else if ( !delivery_date.equals( row.getDeliveryDate() ) ) {
        return false;
    }
    if ( record_div == null ) {
      if ( row.getRecordDiv() != null )
        return false;
    } else if ( !record_div.equals( row.getRecordDiv() ) ) {
        return false;
    }
    if ( product_div == null ) {
      if ( row.getProductDiv() != null )
        return false;
    } else if ( !product_div.equals( row.getProductDiv() ) ) {
        return false;
    }
    if ( product_group == null ) {
      if ( row.getProductGroup() != null )
        return false;
    } else if ( !product_group.equals( row.getProductGroup() ) ) {
        return false;
    }
    if ( product_code == null ) {
      if ( row.getProductCode() != null )
        return false;
    } else if ( !product_code.equals( row.getProductCode() ) ) {
        return false;
    }
    if ( name_method_div == null ) {
      if ( row.getNameMethodDiv() != null )
        return false;
    } else if ( !name_method_div.equals( row.getNameMethodDiv() ) ) {
        return false;
    }
    if ( remarks_div == null ) {
      if ( row.getRemarksDiv() != null )
        return false;
    } else if ( !remarks_div.equals( row.getRemarksDiv() ) ) {
        return false;
    }
    if ( custdy_div == null ) {
      if ( row.getCustdyDiv() != null )
        return false;
    } else if ( !custdy_div.equals( row.getCustdyDiv() ) ) {
        return false;
    }
    if ( deal_div == null ) {
      if ( row.getDealDiv() != null )
        return false;
    } else if ( !deal_div.equals( row.getDealDiv() ) ) {
        return false;
    }
    if ( io_div == null ) {
      if ( row.getIoDiv() != null )
        return false;
    } else if ( !io_div.equals( row.getIoDiv() ) ) {
        return false;
    }
    if ( io_group == null ) {
      if ( row.getIoGroup() != null )
        return false;
    } else if ( !io_group.equals( row.getIoGroup() ) ) {
        return false;
    }
    if ( sec_receipt_delivery_group == null ) {
      if ( row.getSecReceiptDeliveryGroup() != null )
        return false;
    } else if ( !sec_receipt_delivery_group.equals( row.getSecReceiptDeliveryGroup() ) ) {
        return false;
    }
    if ( market_div == null ) {
      if ( row.getMarketDiv() != null )
        return false;
    } else if ( !market_div.equals( row.getMarketDiv() ) ) {
        return false;
    }
    if ( quantity == null ) {
      if ( !row.getQuantityIsNull() )
        return false;
    } else if ( row.getQuantityIsNull() || ( quantity.longValue() != row.getQuantity() ) ) {
        return false;
    }
    if ( executed_price == null ) {
      if ( !row.getExecutedPriceIsNull() )
        return false;
    } else if ( row.getExecutedPriceIsNull() || ( executed_price.doubleValue() != row.getExecutedPrice() ) ) {
        return false;
    }
    if ( executed_amount == null ) {
      if ( !row.getExecutedAmountIsNull() )
        return false;
    } else if ( row.getExecutedAmountIsNull() || ( executed_amount.longValue() != row.getExecutedAmount() ) ) {
        return false;
    }
    if ( quantity_unit == null ) {
      if ( row.getQuantityUnit() != null )
        return false;
    } else if ( !quantity_unit.equals( row.getQuantityUnit() ) ) {
        return false;
    }
    if ( delivery_method == null ) {
      if ( row.getDeliveryMethod() != null )
        return false;
    } else if ( !delivery_method.equals( row.getDeliveryMethod() ) ) {
        return false;
    }
    if ( foreign_sec_div == null ) {
      if ( row.getForeignSecDiv() != null )
        return false;
    } else if ( !foreign_sec_div.equals( row.getForeignSecDiv() ) ) {
        return false;
    }
    if ( settlement_div == null ) {
      if ( row.getSettlementDiv() != null )
        return false;
    } else if ( !settlement_div.equals( row.getSettlementDiv() ) ) {
        return false;
    }
    if ( exchange_rate == null ) {
      if ( !row.getExchangeRateIsNull() )
        return false;
    } else if ( row.getExchangeRateIsNull() || ( exchange_rate.doubleValue() != row.getExchangeRate() ) ) {
        return false;
    }
    if ( account_name == null ) {
      if ( row.getAccountName() != null )
        return false;
    } else if ( !account_name.equals( row.getAccountName() ) ) {
        return false;
    }
    if ( product_name == null ) {
      if ( row.getProductName() != null )
        return false;
    } else if ( !product_name.equals( row.getProductName() ) ) {
        return false;
    }
    if ( specific_account_div == null ) {
      if ( row.getSpecificAccountDiv() != null )
        return false;
    } else if ( !specific_account_div.equals( row.getSpecificAccountDiv() ) ) {
        return false;
    }
    if ( delivery_div == null ) {
      if ( row.getDeliveryDiv() != null )
        return false;
    } else if ( !delivery_div.equals( row.getDeliveryDiv() ) ) {
        return false;
    }
    if ( created_timestamp == null ) {
      if ( row.getCreatedTimestamp() != null )
        return false;
    } else if ( !created_timestamp.equals( row.getCreatedTimestamp() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  ((int) sec_receipt_delivery_action_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (trader_code!=null? trader_code.hashCode(): 0) 
        + (work_date!=null? work_date.hashCode(): 0) 
        + (execute_date!=null? execute_date.hashCode(): 0) 
        + (delivery_date!=null? delivery_date.hashCode(): 0) 
        + (record_div!=null? record_div.hashCode(): 0) 
        + (product_div!=null? product_div.hashCode(): 0) 
        + (product_group!=null? product_group.hashCode(): 0) 
        + (product_code!=null? product_code.hashCode(): 0) 
        + (name_method_div!=null? name_method_div.hashCode(): 0) 
        + (remarks_div!=null? remarks_div.hashCode(): 0) 
        + (custdy_div!=null? custdy_div.hashCode(): 0) 
        + (deal_div!=null? deal_div.hashCode(): 0) 
        + (io_div!=null? io_div.hashCode(): 0) 
        + (io_group!=null? io_group.hashCode(): 0) 
        + (sec_receipt_delivery_group!=null? sec_receipt_delivery_group.hashCode(): 0) 
        + (market_div!=null? market_div.hashCode(): 0) 
        + (quantity!=null? quantity.hashCode(): 0) 
        + (executed_price!=null? executed_price.hashCode(): 0) 
        + (executed_amount!=null? executed_amount.hashCode(): 0) 
        + (quantity_unit!=null? quantity_unit.hashCode(): 0) 
        + (delivery_method!=null? delivery_method.hashCode(): 0) 
        + (foreign_sec_div!=null? foreign_sec_div.hashCode(): 0) 
        + (settlement_div!=null? settlement_div.hashCode(): 0) 
        + (exchange_rate!=null? exchange_rate.hashCode(): 0) 
        + (account_name!=null? account_name.hashCode(): 0) 
        + (product_name!=null? product_name.hashCode(): 0) 
        + (specific_account_div!=null? specific_account_div.hashCode(): 0) 
        + (delivery_div!=null? delivery_div.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !branch_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'branch_code' must be set before inserting.");
		if ( !account_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_code' must be set before inserting.");
		if ( !created_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'created_timestamp' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("sec_receipt_delivery_action_id",new Long(sec_receipt_delivery_action_id));
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("account_code",account_code);
		if ( trader_code != null )
			map.put("trader_code",trader_code);
		if ( work_date != null )
			map.put("work_date",work_date);
		if ( execute_date != null )
			map.put("execute_date",execute_date);
		if ( delivery_date != null )
			map.put("delivery_date",delivery_date);
		if ( record_div != null )
			map.put("record_div",record_div);
		if ( product_div != null )
			map.put("product_div",product_div);
		if ( product_group != null )
			map.put("product_group",product_group);
		if ( product_code != null )
			map.put("product_code",product_code);
		if ( name_method_div != null )
			map.put("name_method_div",name_method_div);
		if ( remarks_div != null )
			map.put("remarks_div",remarks_div);
		if ( custdy_div != null )
			map.put("custdy_div",custdy_div);
		if ( deal_div != null )
			map.put("deal_div",deal_div);
		if ( io_div != null )
			map.put("io_div",io_div);
		if ( io_group != null )
			map.put("io_group",io_group);
		if ( sec_receipt_delivery_group != null )
			map.put("sec_receipt_delivery_group",sec_receipt_delivery_group);
		if ( market_div != null )
			map.put("market_div",market_div);
		if ( quantity != null )
			map.put("quantity",quantity);
		if ( executed_price != null )
			map.put("executed_price",executed_price);
		if ( executed_amount != null )
			map.put("executed_amount",executed_amount);
		if ( quantity_unit != null )
			map.put("quantity_unit",quantity_unit);
		if ( delivery_method != null )
			map.put("delivery_method",delivery_method);
		if ( foreign_sec_div != null )
			map.put("foreign_sec_div",foreign_sec_div);
		if ( settlement_div != null )
			map.put("settlement_div",settlement_div);
		if ( exchange_rate != null )
			map.put("exchange_rate",exchange_rate);
		if ( account_name != null )
			map.put("account_name",account_name);
		if ( product_name != null )
			map.put("product_name",product_name);
		if ( specific_account_div != null )
			map.put("specific_account_div",specific_account_div);
		if ( delivery_div != null )
			map.put("delivery_div",delivery_div);
		map.put("created_timestamp",created_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( branch_code_is_modified )
			map.put("branch_code",branch_code);
		if ( account_code_is_modified )
			map.put("account_code",account_code);
		if ( trader_code_is_modified )
			map.put("trader_code",trader_code);
		if ( work_date_is_modified )
			map.put("work_date",work_date);
		if ( execute_date_is_modified )
			map.put("execute_date",execute_date);
		if ( delivery_date_is_modified )
			map.put("delivery_date",delivery_date);
		if ( record_div_is_modified )
			map.put("record_div",record_div);
		if ( product_div_is_modified )
			map.put("product_div",product_div);
		if ( product_group_is_modified )
			map.put("product_group",product_group);
		if ( product_code_is_modified )
			map.put("product_code",product_code);
		if ( name_method_div_is_modified )
			map.put("name_method_div",name_method_div);
		if ( remarks_div_is_modified )
			map.put("remarks_div",remarks_div);
		if ( custdy_div_is_modified )
			map.put("custdy_div",custdy_div);
		if ( deal_div_is_modified )
			map.put("deal_div",deal_div);
		if ( io_div_is_modified )
			map.put("io_div",io_div);
		if ( io_group_is_modified )
			map.put("io_group",io_group);
		if ( sec_receipt_delivery_group_is_modified )
			map.put("sec_receipt_delivery_group",sec_receipt_delivery_group);
		if ( market_div_is_modified )
			map.put("market_div",market_div);
		if ( quantity_is_modified )
			map.put("quantity",quantity);
		if ( executed_price_is_modified )
			map.put("executed_price",executed_price);
		if ( executed_amount_is_modified )
			map.put("executed_amount",executed_amount);
		if ( quantity_unit_is_modified )
			map.put("quantity_unit",quantity_unit);
		if ( delivery_method_is_modified )
			map.put("delivery_method",delivery_method);
		if ( foreign_sec_div_is_modified )
			map.put("foreign_sec_div",foreign_sec_div);
		if ( settlement_div_is_modified )
			map.put("settlement_div",settlement_div);
		if ( exchange_rate_is_modified )
			map.put("exchange_rate",exchange_rate);
		if ( account_name_is_modified )
			map.put("account_name",account_name);
		if ( product_name_is_modified )
			map.put("product_name",product_name);
		if ( specific_account_div_is_modified )
			map.put("specific_account_div",specific_account_div);
		if ( delivery_div_is_modified )
			map.put("delivery_div",delivery_div);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if (map.size() == 0) {
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( branch_code_is_set )
				map.put("branch_code",branch_code);
			if ( account_code_is_set )
				map.put("account_code",account_code);
			map.put("trader_code",trader_code);
			map.put("work_date",work_date);
			map.put("execute_date",execute_date);
			map.put("delivery_date",delivery_date);
			map.put("record_div",record_div);
			map.put("product_div",product_div);
			map.put("product_group",product_group);
			map.put("product_code",product_code);
			map.put("name_method_div",name_method_div);
			map.put("remarks_div",remarks_div);
			map.put("custdy_div",custdy_div);
			map.put("deal_div",deal_div);
			map.put("io_div",io_div);
			map.put("io_group",io_group);
			map.put("sec_receipt_delivery_group",sec_receipt_delivery_group);
			map.put("market_div",market_div);
			map.put("quantity",quantity);
			map.put("executed_price",executed_price);
			map.put("executed_amount",executed_amount);
			map.put("quantity_unit",quantity_unit);
			map.put("delivery_method",delivery_method);
			map.put("foreign_sec_div",foreign_sec_div);
			map.put("settlement_div",settlement_div);
			map.put("exchange_rate",exchange_rate);
			map.put("account_name",account_name);
			map.put("product_name",product_name);
			map.put("specific_account_div",specific_account_div);
			map.put("delivery_div",delivery_div);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
		}
		return map;
	}


  /** 
   * <em>sec_receipt_delivery_action_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getSecReceiptDeliveryActionId()
  {
    return sec_receipt_delivery_action_id;
  }


  /** 
   * <em>sec_receipt_delivery_action_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSecReceiptDeliveryActionIdIsSet() {
    return sec_receipt_delivery_action_id_is_set;
  }


  /** 
   * <em>sec_receipt_delivery_action_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSecReceiptDeliveryActionIdIsModified() {
    return sec_receipt_delivery_action_id_is_modified;
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
   * <em>work_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getWorkDate()
  {
    return work_date;
  }


  /** 
   * <em>work_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getWorkDateIsSet() {
    return work_date_is_set;
  }


  /** 
   * <em>work_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getWorkDateIsModified() {
    return work_date_is_modified;
  }


  /** 
   * <em>execute_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getExecuteDate()
  {
    return execute_date;
  }


  /** 
   * <em>execute_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecuteDateIsSet() {
    return execute_date_is_set;
  }


  /** 
   * <em>execute_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecuteDateIsModified() {
    return execute_date_is_modified;
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
   * <em>record_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRecordDiv()
  {
    return record_div;
  }


  /** 
   * <em>record_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecordDivIsSet() {
    return record_div_is_set;
  }


  /** 
   * <em>record_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecordDivIsModified() {
    return record_div_is_modified;
  }


  /** 
   * <em>product_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getProductDiv()
  {
    return product_div;
  }


  /** 
   * <em>product_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductDivIsSet() {
    return product_div_is_set;
  }


  /** 
   * <em>product_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductDivIsModified() {
    return product_div_is_modified;
  }


  /** 
   * <em>product_group</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getProductGroup()
  {
    return product_group;
  }


  /** 
   * <em>product_group</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductGroupIsSet() {
    return product_group_is_set;
  }


  /** 
   * <em>product_group</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductGroupIsModified() {
    return product_group_is_modified;
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
   * <em>name_method_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getNameMethodDiv()
  {
    return name_method_div;
  }


  /** 
   * <em>name_method_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNameMethodDivIsSet() {
    return name_method_div_is_set;
  }


  /** 
   * <em>name_method_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNameMethodDivIsModified() {
    return name_method_div_is_modified;
  }


  /** 
   * <em>remarks_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRemarksDiv()
  {
    return remarks_div;
  }


  /** 
   * <em>remarks_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRemarksDivIsSet() {
    return remarks_div_is_set;
  }


  /** 
   * <em>remarks_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRemarksDivIsModified() {
    return remarks_div_is_modified;
  }


  /** 
   * <em>custdy_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCustdyDiv()
  {
    return custdy_div;
  }


  /** 
   * <em>custdy_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCustdyDivIsSet() {
    return custdy_div_is_set;
  }


  /** 
   * <em>custdy_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCustdyDivIsModified() {
    return custdy_div_is_modified;
  }


  /** 
   * <em>deal_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDealDiv()
  {
    return deal_div;
  }


  /** 
   * <em>deal_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDealDivIsSet() {
    return deal_div_is_set;
  }


  /** 
   * <em>deal_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDealDivIsModified() {
    return deal_div_is_modified;
  }


  /** 
   * <em>io_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIoDiv()
  {
    return io_div;
  }


  /** 
   * <em>io_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIoDivIsSet() {
    return io_div_is_set;
  }


  /** 
   * <em>io_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIoDivIsModified() {
    return io_div_is_modified;
  }


  /** 
   * <em>io_group</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIoGroup()
  {
    return io_group;
  }


  /** 
   * <em>io_group</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIoGroupIsSet() {
    return io_group_is_set;
  }


  /** 
   * <em>io_group</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIoGroupIsModified() {
    return io_group_is_modified;
  }


  /** 
   * <em>sec_receipt_delivery_group</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSecReceiptDeliveryGroup()
  {
    return sec_receipt_delivery_group;
  }


  /** 
   * <em>sec_receipt_delivery_group</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSecReceiptDeliveryGroupIsSet() {
    return sec_receipt_delivery_group_is_set;
  }


  /** 
   * <em>sec_receipt_delivery_group</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSecReceiptDeliveryGroupIsModified() {
    return sec_receipt_delivery_group_is_modified;
  }


  /** 
   * <em>market_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMarketDiv()
  {
    return market_div;
  }


  /** 
   * <em>market_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketDivIsSet() {
    return market_div_is_set;
  }


  /** 
   * <em>market_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketDivIsModified() {
    return market_div_is_modified;
  }


  /** 
   * <em>quantity</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getQuantity()
  {
    return ( quantity==null? ((long)0): quantity.longValue() );
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
   * <em>executed_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getExecutedPrice()
  {
    return ( executed_price==null? ((double)0): executed_price.doubleValue() );
  }


  /** 
   * <em>executed_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExecutedPriceIsNull()
  {
    return executed_price == null;
  }


  /** 
   * <em>executed_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecutedPriceIsSet() {
    return executed_price_is_set;
  }


  /** 
   * <em>executed_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecutedPriceIsModified() {
    return executed_price_is_modified;
  }


  /** 
   * <em>executed_amount</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getExecutedAmount()
  {
    return ( executed_amount==null? ((long)0): executed_amount.longValue() );
  }


  /** 
   * <em>executed_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExecutedAmountIsNull()
  {
    return executed_amount == null;
  }


  /** 
   * <em>executed_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecutedAmountIsSet() {
    return executed_amount_is_set;
  }


  /** 
   * <em>executed_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecutedAmountIsModified() {
    return executed_amount_is_modified;
  }


  /** 
   * <em>quantity_unit</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getQuantityUnit()
  {
    return quantity_unit;
  }


  /** 
   * <em>quantity_unit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getQuantityUnitIsSet() {
    return quantity_unit_is_set;
  }


  /** 
   * <em>quantity_unit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getQuantityUnitIsModified() {
    return quantity_unit_is_modified;
  }


  /** 
   * <em>delivery_method</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDeliveryMethod()
  {
    return delivery_method;
  }


  /** 
   * <em>delivery_method</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveryMethodIsSet() {
    return delivery_method_is_set;
  }


  /** 
   * <em>delivery_method</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveryMethodIsModified() {
    return delivery_method_is_modified;
  }


  /** 
   * <em>foreign_sec_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getForeignSecDiv()
  {
    return foreign_sec_div;
  }


  /** 
   * <em>foreign_sec_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignSecDivIsSet() {
    return foreign_sec_div_is_set;
  }


  /** 
   * <em>foreign_sec_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignSecDivIsModified() {
    return foreign_sec_div_is_modified;
  }


  /** 
   * <em>settlement_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSettlementDiv()
  {
    return settlement_div;
  }


  /** 
   * <em>settlement_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSettlementDivIsSet() {
    return settlement_div_is_set;
  }


  /** 
   * <em>settlement_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSettlementDivIsModified() {
    return settlement_div_is_modified;
  }


  /** 
   * <em>exchange_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getExchangeRate()
  {
    return ( exchange_rate==null? ((double)0): exchange_rate.doubleValue() );
  }


  /** 
   * <em>exchange_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExchangeRateIsNull()
  {
    return exchange_rate == null;
  }


  /** 
   * <em>exchange_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExchangeRateIsSet() {
    return exchange_rate_is_set;
  }


  /** 
   * <em>exchange_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExchangeRateIsModified() {
    return exchange_rate_is_modified;
  }


  /** 
   * <em>account_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountName()
  {
    return account_name;
  }


  /** 
   * <em>account_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountNameIsSet() {
    return account_name_is_set;
  }


  /** 
   * <em>account_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountNameIsModified() {
    return account_name_is_modified;
  }


  /** 
   * <em>product_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getProductName()
  {
    return product_name;
  }


  /** 
   * <em>product_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductNameIsSet() {
    return product_name_is_set;
  }


  /** 
   * <em>product_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductNameIsModified() {
    return product_name_is_modified;
  }


  /** 
   * <em>specific_account_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSpecificAccountDiv()
  {
    return specific_account_div;
  }


  /** 
   * <em>specific_account_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecificAccountDivIsSet() {
    return specific_account_div_is_set;
  }


  /** 
   * <em>specific_account_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecificAccountDivIsModified() {
    return specific_account_div_is_modified;
  }


  /** 
   * <em>delivery_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDeliveryDiv()
  {
    return delivery_div;
  }


  /** 
   * <em>delivery_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveryDivIsSet() {
    return delivery_div_is_set;
  }


  /** 
   * <em>delivery_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveryDivIsModified() {
    return delivery_div_is_modified;
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
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new SecReceiptDeliveryActionPK(sec_receipt_delivery_action_id);
  }


  /** 
   * <em>sec_receipt_delivery_action_id</em>カラムの値を設定します。 
   *
   * @@param p_secReceiptDeliveryActionId <em>sec_receipt_delivery_action_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setSecReceiptDeliveryActionId( long p_secReceiptDeliveryActionId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sec_receipt_delivery_action_id = p_secReceiptDeliveryActionId;
    sec_receipt_delivery_action_id_is_set = true;
    sec_receipt_delivery_action_id_is_modified = true;
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
   * <em>work_date</em>カラムの値を設定します。 
   *
   * @@param p_workDate <em>work_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setWorkDate( java.sql.Timestamp p_workDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    work_date = p_workDate;
    work_date_is_set = true;
    work_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>work_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setWorkDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    work_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    work_date_is_set = true;
    work_date_is_modified = true;
  }


  /** 
   * <em>execute_date</em>カラムの値を設定します。 
   *
   * @@param p_executeDate <em>execute_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setExecuteDate( java.sql.Timestamp p_executeDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    execute_date = p_executeDate;
    execute_date_is_set = true;
    execute_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>execute_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setExecuteDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    execute_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    execute_date_is_set = true;
    execute_date_is_modified = true;
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
   * <em>record_div</em>カラムの値を設定します。 
   *
   * @@param p_recordDiv <em>record_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setRecordDiv( String p_recordDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    record_div = p_recordDiv;
    record_div_is_set = true;
    record_div_is_modified = true;
  }


  /** 
   * <em>product_div</em>カラムの値を設定します。 
   *
   * @@param p_productDiv <em>product_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setProductDiv( String p_productDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_div = p_productDiv;
    product_div_is_set = true;
    product_div_is_modified = true;
  }


  /** 
   * <em>product_group</em>カラムの値を設定します。 
   *
   * @@param p_productGroup <em>product_group</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setProductGroup( String p_productGroup )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_group = p_productGroup;
    product_group_is_set = true;
    product_group_is_modified = true;
  }


  /** 
   * <em>product_code</em>カラムの値を設定します。 
   *
   * @@param p_productCode <em>product_code</em>カラムの値をあらわす9桁以下のString値 
   */
  public final void setProductCode( String p_productCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_code = p_productCode;
    product_code_is_set = true;
    product_code_is_modified = true;
  }


  /** 
   * <em>name_method_div</em>カラムの値を設定します。 
   *
   * @@param p_nameMethodDiv <em>name_method_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setNameMethodDiv( String p_nameMethodDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    name_method_div = p_nameMethodDiv;
    name_method_div_is_set = true;
    name_method_div_is_modified = true;
  }


  /** 
   * <em>remarks_div</em>カラムの値を設定します。 
   *
   * @@param p_remarksDiv <em>remarks_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setRemarksDiv( String p_remarksDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    remarks_div = p_remarksDiv;
    remarks_div_is_set = true;
    remarks_div_is_modified = true;
  }


  /** 
   * <em>custdy_div</em>カラムの値を設定します。 
   *
   * @@param p_custdyDiv <em>custdy_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCustdyDiv( String p_custdyDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    custdy_div = p_custdyDiv;
    custdy_div_is_set = true;
    custdy_div_is_modified = true;
  }


  /** 
   * <em>deal_div</em>カラムの値を設定します。 
   *
   * @@param p_dealDiv <em>deal_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setDealDiv( String p_dealDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    deal_div = p_dealDiv;
    deal_div_is_set = true;
    deal_div_is_modified = true;
  }


  /** 
   * <em>io_div</em>カラムの値を設定します。 
   *
   * @@param p_ioDiv <em>io_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setIoDiv( String p_ioDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    io_div = p_ioDiv;
    io_div_is_set = true;
    io_div_is_modified = true;
  }


  /** 
   * <em>io_group</em>カラムの値を設定します。 
   *
   * @@param p_ioGroup <em>io_group</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setIoGroup( String p_ioGroup )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    io_group = p_ioGroup;
    io_group_is_set = true;
    io_group_is_modified = true;
  }


  /** 
   * <em>sec_receipt_delivery_group</em>カラムの値を設定します。 
   *
   * @@param p_secReceiptDeliveryGroup <em>sec_receipt_delivery_group</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSecReceiptDeliveryGroup( String p_secReceiptDeliveryGroup )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sec_receipt_delivery_group = p_secReceiptDeliveryGroup;
    sec_receipt_delivery_group_is_set = true;
    sec_receipt_delivery_group_is_modified = true;
  }


  /** 
   * <em>market_div</em>カラムの値を設定します。 
   *
   * @@param p_marketDiv <em>market_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMarketDiv( String p_marketDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_div = p_marketDiv;
    market_div_is_set = true;
    market_div_is_modified = true;
  }


  /** 
   * <em>quantity</em>カラムの値を設定します。 
   *
   * @@param p_quantity <em>quantity</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setQuantity( long p_quantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    quantity = new Long(p_quantity);
    quantity_is_set = true;
    quantity_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>quantity</em>カラムに値を設定します。 
   */
  public final void setQuantity( Long p_quantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    quantity = p_quantity;
    quantity_is_set = true;
    quantity_is_modified = true;
  }


  /** 
   * <em>executed_price</em>カラムの値を設定します。 
   *
   * @@param p_executedPrice <em>executed_price</em>カラムの値をあらわす10桁以下のdouble値で、その精度は2桁まで
   */
  public final void setExecutedPrice( double p_executedPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    executed_price = new Double(p_executedPrice);
    executed_price_is_set = true;
    executed_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>executed_price</em>カラムに値を設定します。 
   */
  public final void setExecutedPrice( Double p_executedPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    executed_price = p_executedPrice;
    executed_price_is_set = true;
    executed_price_is_modified = true;
  }


  /** 
   * <em>executed_amount</em>カラムの値を設定します。 
   *
   * @@param p_executedAmount <em>executed_amount</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setExecutedAmount( long p_executedAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    executed_amount = new Long(p_executedAmount);
    executed_amount_is_set = true;
    executed_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>executed_amount</em>カラムに値を設定します。 
   */
  public final void setExecutedAmount( Long p_executedAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    executed_amount = p_executedAmount;
    executed_amount_is_set = true;
    executed_amount_is_modified = true;
  }


  /** 
   * <em>quantity_unit</em>カラムの値を設定します。 
   *
   * @@param p_quantityUnit <em>quantity_unit</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setQuantityUnit( String p_quantityUnit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    quantity_unit = p_quantityUnit;
    quantity_unit_is_set = true;
    quantity_unit_is_modified = true;
  }


  /** 
   * <em>delivery_method</em>カラムの値を設定します。 
   *
   * @@param p_deliveryMethod <em>delivery_method</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDeliveryMethod( String p_deliveryMethod )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delivery_method = p_deliveryMethod;
    delivery_method_is_set = true;
    delivery_method_is_modified = true;
  }


  /** 
   * <em>foreign_sec_div</em>カラムの値を設定します。 
   *
   * @@param p_foreignSecDiv <em>foreign_sec_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setForeignSecDiv( String p_foreignSecDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    foreign_sec_div = p_foreignSecDiv;
    foreign_sec_div_is_set = true;
    foreign_sec_div_is_modified = true;
  }


  /** 
   * <em>settlement_div</em>カラムの値を設定します。 
   *
   * @@param p_settlementDiv <em>settlement_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSettlementDiv( String p_settlementDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    settlement_div = p_settlementDiv;
    settlement_div_is_set = true;
    settlement_div_is_modified = true;
  }


  /** 
   * <em>exchange_rate</em>カラムの値を設定します。 
   *
   * @@param p_exchangeRate <em>exchange_rate</em>カラムの値をあらわす15桁以下のdouble値で、その精度は8桁まで
   */
  public final void setExchangeRate( double p_exchangeRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exchange_rate = new Double(p_exchangeRate);
    exchange_rate_is_set = true;
    exchange_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>exchange_rate</em>カラムに値を設定します。 
   */
  public final void setExchangeRate( Double p_exchangeRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    exchange_rate = p_exchangeRate;
    exchange_rate_is_set = true;
    exchange_rate_is_modified = true;
  }


  /** 
   * <em>account_name</em>カラムの値を設定します。 
   *
   * @@param p_accountName <em>account_name</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setAccountName( String p_accountName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_name = p_accountName;
    account_name_is_set = true;
    account_name_is_modified = true;
  }


  /** 
   * <em>product_name</em>カラムの値を設定します。 
   *
   * @@param p_productName <em>product_name</em>カラムの値をあらわす100桁以下のString値 
   */
  public final void setProductName( String p_productName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_name = p_productName;
    product_name_is_set = true;
    product_name_is_modified = true;
  }


  /** 
   * <em>specific_account_div</em>カラムの値を設定します。 
   *
   * @@param p_specificAccountDiv <em>specific_account_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSpecificAccountDiv( String p_specificAccountDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    specific_account_div = p_specificAccountDiv;
    specific_account_div_is_set = true;
    specific_account_div_is_modified = true;
  }


  /** 
   * <em>delivery_div</em>カラムの値を設定します。 
   *
   * @@param p_deliveryDiv <em>delivery_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDeliveryDiv( String p_deliveryDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delivery_div = p_deliveryDiv;
    delivery_div_is_set = true;
    delivery_div_is_modified = true;
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
                else if ( name.equals("account_name") ) {
                    return this.account_name;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'c':
                if ( name.equals("custdy_div") ) {
                    return this.custdy_div;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("delivery_date") ) {
                    return this.delivery_date;
                }
                else if ( name.equals("deal_div") ) {
                    return this.deal_div;
                }
                else if ( name.equals("delivery_method") ) {
                    return this.delivery_method;
                }
                else if ( name.equals("delivery_div") ) {
                    return this.delivery_div;
                }
                break;
            case 'e':
                if ( name.equals("execute_date") ) {
                    return this.execute_date;
                }
                else if ( name.equals("executed_price") ) {
                    return this.executed_price;
                }
                else if ( name.equals("executed_amount") ) {
                    return this.executed_amount;
                }
                else if ( name.equals("exchange_rate") ) {
                    return this.exchange_rate;
                }
                break;
            case 'f':
                if ( name.equals("foreign_sec_div") ) {
                    return this.foreign_sec_div;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("io_div") ) {
                    return this.io_div;
                }
                else if ( name.equals("io_group") ) {
                    return this.io_group;
                }
                break;
            case 'm':
                if ( name.equals("market_div") ) {
                    return this.market_div;
                }
                break;
            case 'n':
                if ( name.equals("name_method_div") ) {
                    return this.name_method_div;
                }
                break;
            case 'p':
                if ( name.equals("product_div") ) {
                    return this.product_div;
                }
                else if ( name.equals("product_group") ) {
                    return this.product_group;
                }
                else if ( name.equals("product_code") ) {
                    return this.product_code;
                }
                else if ( name.equals("product_name") ) {
                    return this.product_name;
                }
                break;
            case 'q':
                if ( name.equals("quantity") ) {
                    return this.quantity;
                }
                else if ( name.equals("quantity_unit") ) {
                    return this.quantity_unit;
                }
                break;
            case 'r':
                if ( name.equals("record_div") ) {
                    return this.record_div;
                }
                else if ( name.equals("remarks_div") ) {
                    return this.remarks_div;
                }
                break;
            case 's':
                if ( name.equals("sec_receipt_delivery_action_id") ) {
                    return new Long( sec_receipt_delivery_action_id );
                }
                else if ( name.equals("sec_receipt_delivery_group") ) {
                    return this.sec_receipt_delivery_group;
                }
                else if ( name.equals("settlement_div") ) {
                    return this.settlement_div;
                }
                else if ( name.equals("specific_account_div") ) {
                    return this.specific_account_div;
                }
                break;
            case 't':
                if ( name.equals("trader_code") ) {
                    return this.trader_code;
                }
                break;
            case 'w':
                if ( name.equals("work_date") ) {
                    return this.work_date;
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
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_code' must be String: '"+value+"' is not." );
                    this.account_code = (String) value;
                    if (this.account_code_is_set)
                        this.account_code_is_modified = true;
                    this.account_code_is_set = true;
                    return;
                }
                else if ( name.equals("account_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_name' must be String: '"+value+"' is not." );
                    this.account_name = (String) value;
                    if (this.account_name_is_set)
                        this.account_name_is_modified = true;
                    this.account_name_is_set = true;
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
                if ( name.equals("custdy_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'custdy_div' must be String: '"+value+"' is not." );
                    this.custdy_div = (String) value;
                    if (this.custdy_div_is_set)
                        this.custdy_div_is_modified = true;
                    this.custdy_div_is_set = true;
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
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'delivery_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.delivery_date = (java.sql.Timestamp) value;
                    if (this.delivery_date_is_set)
                        this.delivery_date_is_modified = true;
                    this.delivery_date_is_set = true;
                    return;
                }
                else if ( name.equals("deal_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'deal_div' must be String: '"+value+"' is not." );
                    this.deal_div = (String) value;
                    if (this.deal_div_is_set)
                        this.deal_div_is_modified = true;
                    this.deal_div_is_set = true;
                    return;
                }
                else if ( name.equals("delivery_method") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'delivery_method' must be String: '"+value+"' is not." );
                    this.delivery_method = (String) value;
                    if (this.delivery_method_is_set)
                        this.delivery_method_is_modified = true;
                    this.delivery_method_is_set = true;
                    return;
                }
                else if ( name.equals("delivery_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'delivery_div' must be String: '"+value+"' is not." );
                    this.delivery_div = (String) value;
                    if (this.delivery_div_is_set)
                        this.delivery_div_is_modified = true;
                    this.delivery_div_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("execute_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'execute_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.execute_date = (java.sql.Timestamp) value;
                    if (this.execute_date_is_set)
                        this.execute_date_is_modified = true;
                    this.execute_date_is_set = true;
                    return;
                }
                else if ( name.equals("executed_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'executed_price' must be Double: '"+value+"' is not." );
                    this.executed_price = (Double) value;
                    if (this.executed_price_is_set)
                        this.executed_price_is_modified = true;
                    this.executed_price_is_set = true;
                    return;
                }
                else if ( name.equals("executed_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'executed_amount' must be Long: '"+value+"' is not." );
                    this.executed_amount = (Long) value;
                    if (this.executed_amount_is_set)
                        this.executed_amount_is_modified = true;
                    this.executed_amount_is_set = true;
                    return;
                }
                else if ( name.equals("exchange_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'exchange_rate' must be Double: '"+value+"' is not." );
                    this.exchange_rate = (Double) value;
                    if (this.exchange_rate_is_set)
                        this.exchange_rate_is_modified = true;
                    this.exchange_rate_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("foreign_sec_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'foreign_sec_div' must be String: '"+value+"' is not." );
                    this.foreign_sec_div = (String) value;
                    if (this.foreign_sec_div_is_set)
                        this.foreign_sec_div_is_modified = true;
                    this.foreign_sec_div_is_set = true;
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
                else if ( name.equals("io_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'io_div' must be String: '"+value+"' is not." );
                    this.io_div = (String) value;
                    if (this.io_div_is_set)
                        this.io_div_is_modified = true;
                    this.io_div_is_set = true;
                    return;
                }
                else if ( name.equals("io_group") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'io_group' must be String: '"+value+"' is not." );
                    this.io_group = (String) value;
                    if (this.io_group_is_set)
                        this.io_group_is_modified = true;
                    this.io_group_is_set = true;
                    return;
                }
                break;
            case 'm':
                if ( name.equals("market_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'market_div' must be String: '"+value+"' is not." );
                    this.market_div = (String) value;
                    if (this.market_div_is_set)
                        this.market_div_is_modified = true;
                    this.market_div_is_set = true;
                    return;
                }
                break;
            case 'n':
                if ( name.equals("name_method_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'name_method_div' must be String: '"+value+"' is not." );
                    this.name_method_div = (String) value;
                    if (this.name_method_div_is_set)
                        this.name_method_div_is_modified = true;
                    this.name_method_div_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("product_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_div' must be String: '"+value+"' is not." );
                    this.product_div = (String) value;
                    if (this.product_div_is_set)
                        this.product_div_is_modified = true;
                    this.product_div_is_set = true;
                    return;
                }
                else if ( name.equals("product_group") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_group' must be String: '"+value+"' is not." );
                    this.product_group = (String) value;
                    if (this.product_group_is_set)
                        this.product_group_is_modified = true;
                    this.product_group_is_set = true;
                    return;
                }
                else if ( name.equals("product_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_code' must be String: '"+value+"' is not." );
                    this.product_code = (String) value;
                    if (this.product_code_is_set)
                        this.product_code_is_modified = true;
                    this.product_code_is_set = true;
                    return;
                }
                else if ( name.equals("product_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_name' must be String: '"+value+"' is not." );
                    this.product_name = (String) value;
                    if (this.product_name_is_set)
                        this.product_name_is_modified = true;
                    this.product_name_is_set = true;
                    return;
                }
                break;
            case 'q':
                if ( name.equals("quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'quantity' must be Long: '"+value+"' is not." );
                    this.quantity = (Long) value;
                    if (this.quantity_is_set)
                        this.quantity_is_modified = true;
                    this.quantity_is_set = true;
                    return;
                }
                else if ( name.equals("quantity_unit") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'quantity_unit' must be String: '"+value+"' is not." );
                    this.quantity_unit = (String) value;
                    if (this.quantity_unit_is_set)
                        this.quantity_unit_is_modified = true;
                    this.quantity_unit_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("record_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'record_div' must be String: '"+value+"' is not." );
                    this.record_div = (String) value;
                    if (this.record_div_is_set)
                        this.record_div_is_modified = true;
                    this.record_div_is_set = true;
                    return;
                }
                else if ( name.equals("remarks_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'remarks_div' must be String: '"+value+"' is not." );
                    this.remarks_div = (String) value;
                    if (this.remarks_div_is_set)
                        this.remarks_div_is_modified = true;
                    this.remarks_div_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sec_receipt_delivery_action_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'sec_receipt_delivery_action_id' must be Long: '"+value+"' is not." );
                    this.sec_receipt_delivery_action_id = ((Long) value).longValue();
                    if (this.sec_receipt_delivery_action_id_is_set)
                        this.sec_receipt_delivery_action_id_is_modified = true;
                    this.sec_receipt_delivery_action_id_is_set = true;
                    return;
                }
                else if ( name.equals("sec_receipt_delivery_group") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sec_receipt_delivery_group' must be String: '"+value+"' is not." );
                    this.sec_receipt_delivery_group = (String) value;
                    if (this.sec_receipt_delivery_group_is_set)
                        this.sec_receipt_delivery_group_is_modified = true;
                    this.sec_receipt_delivery_group_is_set = true;
                    return;
                }
                else if ( name.equals("settlement_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'settlement_div' must be String: '"+value+"' is not." );
                    this.settlement_div = (String) value;
                    if (this.settlement_div_is_set)
                        this.settlement_div_is_modified = true;
                    this.settlement_div_is_set = true;
                    return;
                }
                else if ( name.equals("specific_account_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'specific_account_div' must be String: '"+value+"' is not." );
                    this.specific_account_div = (String) value;
                    if (this.specific_account_div_is_set)
                        this.specific_account_div_is_modified = true;
                    this.specific_account_div_is_set = true;
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
                break;
            case 'w':
                if ( name.equals("work_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'work_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.work_date = (java.sql.Timestamp) value;
                    if (this.work_date_is_set)
                        this.work_date_is_modified = true;
                    this.work_date_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
