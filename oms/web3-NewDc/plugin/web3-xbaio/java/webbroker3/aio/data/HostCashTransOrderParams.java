head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.40.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	HostCashTransOrderParams.java;


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
 * host_cash_trans_orderテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link HostCashTransOrderRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link HostCashTransOrderParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link HostCashTransOrderParams}が{@@link HostCashTransOrderRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see HostCashTransOrderPK 
 * @@see HostCashTransOrderRow 
 */
public class HostCashTransOrderParams extends Params implements HostCashTransOrderRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "host_cash_trans_order";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = HostCashTransOrderRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return HostCashTransOrderRow.TYPE;
  }


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
   * <em>order_request_number</em>カラムの値 
   */
  public  String  order_request_number;

  /** 
   * <em>trader_code</em>カラムの値 
   */
  public  String  trader_code;

  /** 
   * <em>debit_amount</em>カラムの値 
   */
  public  Long  debit_amount;

  /** 
   * <em>credit_amount</em>カラムの値 
   */
  public  Long  credit_amount;

  /** 
   * <em>cash_transfer_type</em>カラムの値 
   */
  public  String  cash_transfer_type;

  /** 
   * <em>remittance_fee</em>カラムの値 
   */
  public  Integer  remittance_fee;

  /** 
   * <em>delivery_type</em>カラムの値 
   */
  public  String  delivery_type;

  /** 
   * <em>advance_settlement_date</em>カラムの値 
   */
  public  String  advance_settlement_date;

  /** 
   * <em>pay_temporarily_div</em>カラムの値 
   */
  public  String  pay_temporarily_div;

  /** 
   * <em>order_original_date</em>カラムの値 
   */
  public  String  order_original_date;

  /** 
   * <em>sonar_code</em>カラムの値 
   */
  public  String  sonar_code;

  /** 
   * <em>cancel_div</em>カラムの値 
   */
  public  String  cancel_div;

  /** 
   * <em>reciept_div</em>カラムの値 
   */
  public  String  reciept_div;

  /** 
   * <em>guarantee_div</em>カラムの値 
   */
  public  String  guarantee_div;

  /** 
   * <em>ordered_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  ordered_timestamp;

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
  private boolean debit_amount_is_set = false;
  private boolean debit_amount_is_modified = false;
  private boolean credit_amount_is_set = false;
  private boolean credit_amount_is_modified = false;
  private boolean cash_transfer_type_is_set = false;
  private boolean cash_transfer_type_is_modified = false;
  private boolean remittance_fee_is_set = false;
  private boolean remittance_fee_is_modified = false;
  private boolean delivery_type_is_set = false;
  private boolean delivery_type_is_modified = false;
  private boolean advance_settlement_date_is_set = false;
  private boolean advance_settlement_date_is_modified = false;
  private boolean pay_temporarily_div_is_set = false;
  private boolean pay_temporarily_div_is_modified = false;
  private boolean order_original_date_is_set = false;
  private boolean order_original_date_is_modified = false;
  private boolean sonar_code_is_set = false;
  private boolean sonar_code_is_modified = false;
  private boolean cancel_div_is_set = false;
  private boolean cancel_div_is_modified = false;
  private boolean reciept_div_is_set = false;
  private boolean reciept_div_is_modified = false;
  private boolean guarantee_div_is_set = false;
  private boolean guarantee_div_is_modified = false;
  private boolean ordered_timestamp_is_set = false;
  private boolean ordered_timestamp_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "request_code=" + request_code
      + "," + "institution_code=" + institution_code
      + "," + "branch_code=" + branch_code
      + "," + "account_code=" + account_code
      + "," + "order_request_number=" + order_request_number
      + "," + "trader_code=" +trader_code
      + "," + "debit_amount=" +debit_amount
      + "," + "credit_amount=" +credit_amount
      + "," + "cash_transfer_type=" +cash_transfer_type
      + "," + "remittance_fee=" +remittance_fee
      + "," + "delivery_type=" +delivery_type
      + "," + "advance_settlement_date=" +advance_settlement_date
      + "," + "pay_temporarily_div=" +pay_temporarily_div
      + "," + "order_original_date=" +order_original_date
      + "," + "sonar_code=" +sonar_code
      + "," + "cancel_div=" +cancel_div
      + "," + "reciept_div=" +reciept_div
      + "," + "guarantee_div=" +guarantee_div
      + "," + "ordered_timestamp=" +ordered_timestamp
      + "," + "status=" +status
      + "}";
  }


  /** 
   * 値が未設定のHostCashTransOrderParamsオブジェクトを作成します。 
   */
  public HostCashTransOrderParams() {
    request_code_is_modified = true;
    institution_code_is_modified = true;
    branch_code_is_modified = true;
    account_code_is_modified = true;
    order_request_number_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のHostCashTransOrderRowオブジェクトの値を利用してHostCashTransOrderParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するHostCashTransOrderRowオブジェクト 
   */
  public HostCashTransOrderParams( HostCashTransOrderRow p_row )
  {
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
    order_request_number = p_row.getOrderRequestNumber();
    order_request_number_is_set = p_row.getOrderRequestNumberIsSet();
    order_request_number_is_modified = p_row.getOrderRequestNumberIsModified();
    trader_code = p_row.getTraderCode();
    trader_code_is_set = p_row.getTraderCodeIsSet();
    trader_code_is_modified = p_row.getTraderCodeIsModified();
    if ( !p_row.getDebitAmountIsNull() )
      debit_amount = new Long( p_row.getDebitAmount() );
    debit_amount_is_set = p_row.getDebitAmountIsSet();
    debit_amount_is_modified = p_row.getDebitAmountIsModified();
    if ( !p_row.getCreditAmountIsNull() )
      credit_amount = new Long( p_row.getCreditAmount() );
    credit_amount_is_set = p_row.getCreditAmountIsSet();
    credit_amount_is_modified = p_row.getCreditAmountIsModified();
    cash_transfer_type = p_row.getCashTransferType();
    cash_transfer_type_is_set = p_row.getCashTransferTypeIsSet();
    cash_transfer_type_is_modified = p_row.getCashTransferTypeIsModified();
    if ( !p_row.getRemittanceFeeIsNull() )
      remittance_fee = new Integer( p_row.getRemittanceFee() );
    remittance_fee_is_set = p_row.getRemittanceFeeIsSet();
    remittance_fee_is_modified = p_row.getRemittanceFeeIsModified();
    delivery_type = p_row.getDeliveryType();
    delivery_type_is_set = p_row.getDeliveryTypeIsSet();
    delivery_type_is_modified = p_row.getDeliveryTypeIsModified();
    advance_settlement_date = p_row.getAdvanceSettlementDate();
    advance_settlement_date_is_set = p_row.getAdvanceSettlementDateIsSet();
    advance_settlement_date_is_modified = p_row.getAdvanceSettlementDateIsModified();
    pay_temporarily_div = p_row.getPayTemporarilyDiv();
    pay_temporarily_div_is_set = p_row.getPayTemporarilyDivIsSet();
    pay_temporarily_div_is_modified = p_row.getPayTemporarilyDivIsModified();
    order_original_date = p_row.getOrderOriginalDate();
    order_original_date_is_set = p_row.getOrderOriginalDateIsSet();
    order_original_date_is_modified = p_row.getOrderOriginalDateIsModified();
    sonar_code = p_row.getSonarCode();
    sonar_code_is_set = p_row.getSonarCodeIsSet();
    sonar_code_is_modified = p_row.getSonarCodeIsModified();
    cancel_div = p_row.getCancelDiv();
    cancel_div_is_set = p_row.getCancelDivIsSet();
    cancel_div_is_modified = p_row.getCancelDivIsModified();
    reciept_div = p_row.getRecieptDiv();
    reciept_div_is_set = p_row.getRecieptDivIsSet();
    reciept_div_is_modified = p_row.getRecieptDivIsModified();
    guarantee_div = p_row.getGuaranteeDiv();
    guarantee_div_is_set = p_row.getGuaranteeDivIsSet();
    guarantee_div_is_modified = p_row.getGuaranteeDivIsModified();
    ordered_timestamp = p_row.getOrderedTimestamp();
    ordered_timestamp_is_set = p_row.getOrderedTimestampIsSet();
    ordered_timestamp_is_modified = p_row.getOrderedTimestampIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      trader_code_is_set = true;
      trader_code_is_modified = true;
      debit_amount_is_set = true;
      debit_amount_is_modified = true;
      credit_amount_is_set = true;
      credit_amount_is_modified = true;
      cash_transfer_type_is_set = true;
      cash_transfer_type_is_modified = true;
      remittance_fee_is_set = true;
      remittance_fee_is_modified = true;
      delivery_type_is_set = true;
      delivery_type_is_modified = true;
      advance_settlement_date_is_set = true;
      advance_settlement_date_is_modified = true;
      pay_temporarily_div_is_set = true;
      pay_temporarily_div_is_modified = true;
      order_original_date_is_set = true;
      order_original_date_is_modified = true;
      sonar_code_is_set = true;
      sonar_code_is_modified = true;
      cancel_div_is_set = true;
      cancel_div_is_modified = true;
      reciept_div_is_set = true;
      reciept_div_is_modified = true;
      guarantee_div_is_set = true;
      guarantee_div_is_modified = true;
      ordered_timestamp_is_set = true;
      ordered_timestamp_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof HostCashTransOrderRow ) )
       return false;
    return fieldsEqual( (HostCashTransOrderRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のHostCashTransOrderRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( HostCashTransOrderRow row )
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
    if ( debit_amount == null ) {
      if ( !row.getDebitAmountIsNull() )
        return false;
    } else if ( row.getDebitAmountIsNull() || ( debit_amount.longValue() != row.getDebitAmount() ) ) {
        return false;
    }
    if ( credit_amount == null ) {
      if ( !row.getCreditAmountIsNull() )
        return false;
    } else if ( row.getCreditAmountIsNull() || ( credit_amount.longValue() != row.getCreditAmount() ) ) {
        return false;
    }
    if ( cash_transfer_type == null ) {
      if ( row.getCashTransferType() != null )
        return false;
    } else if ( !cash_transfer_type.equals( row.getCashTransferType() ) ) {
        return false;
    }
    if ( remittance_fee == null ) {
      if ( !row.getRemittanceFeeIsNull() )
        return false;
    } else if ( row.getRemittanceFeeIsNull() || ( remittance_fee.intValue() != row.getRemittanceFee() ) ) {
        return false;
    }
    if ( delivery_type == null ) {
      if ( row.getDeliveryType() != null )
        return false;
    } else if ( !delivery_type.equals( row.getDeliveryType() ) ) {
        return false;
    }
    if ( advance_settlement_date == null ) {
      if ( row.getAdvanceSettlementDate() != null )
        return false;
    } else if ( !advance_settlement_date.equals( row.getAdvanceSettlementDate() ) ) {
        return false;
    }
    if ( pay_temporarily_div == null ) {
      if ( row.getPayTemporarilyDiv() != null )
        return false;
    } else if ( !pay_temporarily_div.equals( row.getPayTemporarilyDiv() ) ) {
        return false;
    }
    if ( order_original_date == null ) {
      if ( row.getOrderOriginalDate() != null )
        return false;
    } else if ( !order_original_date.equals( row.getOrderOriginalDate() ) ) {
        return false;
    }
    if ( sonar_code == null ) {
      if ( row.getSonarCode() != null )
        return false;
    } else if ( !sonar_code.equals( row.getSonarCode() ) ) {
        return false;
    }
    if ( cancel_div == null ) {
      if ( row.getCancelDiv() != null )
        return false;
    } else if ( !cancel_div.equals( row.getCancelDiv() ) ) {
        return false;
    }
    if ( reciept_div == null ) {
      if ( row.getRecieptDiv() != null )
        return false;
    } else if ( !reciept_div.equals( row.getRecieptDiv() ) ) {
        return false;
    }
    if ( guarantee_div == null ) {
      if ( row.getGuaranteeDiv() != null )
        return false;
    } else if ( !guarantee_div.equals( row.getGuaranteeDiv() ) ) {
        return false;
    }
    if ( ordered_timestamp == null ) {
      if ( row.getOrderedTimestamp() != null )
        return false;
    } else if ( !ordered_timestamp.equals( row.getOrderedTimestamp() ) ) {
        return false;
    }
    if ( status == null ) {
      if ( row.getStatus() != null )
        return false;
    } else if ( !status.equals( row.getStatus() ) ) {
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
        + (debit_amount!=null? debit_amount.hashCode(): 0) 
        + (credit_amount!=null? credit_amount.hashCode(): 0) 
        + (cash_transfer_type!=null? cash_transfer_type.hashCode(): 0) 
        + (remittance_fee!=null? remittance_fee.hashCode(): 0) 
        + (delivery_type!=null? delivery_type.hashCode(): 0) 
        + (advance_settlement_date!=null? advance_settlement_date.hashCode(): 0) 
        + (pay_temporarily_div!=null? pay_temporarily_div.hashCode(): 0) 
        + (order_original_date!=null? order_original_date.hashCode(): 0) 
        + (sonar_code!=null? sonar_code.hashCode(): 0) 
        + (cancel_div!=null? cancel_div.hashCode(): 0) 
        + (reciept_div!=null? reciept_div.hashCode(): 0) 
        + (guarantee_div!=null? guarantee_div.hashCode(): 0) 
        + (ordered_timestamp!=null? ordered_timestamp.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !cash_transfer_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'cash_transfer_type' must be set before inserting.");
		if ( !delivery_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'delivery_type' must be set before inserting.");
		if ( !sonar_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'sonar_code' must be set before inserting.");
		if ( !status_is_set )
			throw new IllegalArgumentException("non-nullable field 'status' must be set before inserting.");
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
		map.put("account_code",account_code);
		if ( trader_code != null )
			map.put("trader_code",trader_code);
		map.put("order_request_number",order_request_number);
		if ( debit_amount != null )
			map.put("debit_amount",debit_amount);
		if ( credit_amount != null )
			map.put("credit_amount",credit_amount);
		map.put("cash_transfer_type",cash_transfer_type);
		if ( remittance_fee != null )
			map.put("remittance_fee",remittance_fee);
		map.put("delivery_type",delivery_type);
		if ( advance_settlement_date != null )
			map.put("advance_settlement_date",advance_settlement_date);
		if ( pay_temporarily_div != null )
			map.put("pay_temporarily_div",pay_temporarily_div);
		if ( order_original_date != null )
			map.put("order_original_date",order_original_date);
		map.put("sonar_code",sonar_code);
		if ( cancel_div != null )
			map.put("cancel_div",cancel_div);
		if ( reciept_div != null )
			map.put("reciept_div",reciept_div);
		if ( guarantee_div != null )
			map.put("guarantee_div",guarantee_div);
		if ( ordered_timestamp != null )
			map.put("ordered_timestamp",ordered_timestamp);
		map.put("status",status);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( trader_code_is_modified )
			map.put("trader_code",trader_code);
		if ( debit_amount_is_modified )
			map.put("debit_amount",debit_amount);
		if ( credit_amount_is_modified )
			map.put("credit_amount",credit_amount);
		if ( cash_transfer_type_is_modified )
			map.put("cash_transfer_type",cash_transfer_type);
		if ( remittance_fee_is_modified )
			map.put("remittance_fee",remittance_fee);
		if ( delivery_type_is_modified )
			map.put("delivery_type",delivery_type);
		if ( advance_settlement_date_is_modified )
			map.put("advance_settlement_date",advance_settlement_date);
		if ( pay_temporarily_div_is_modified )
			map.put("pay_temporarily_div",pay_temporarily_div);
		if ( order_original_date_is_modified )
			map.put("order_original_date",order_original_date);
		if ( sonar_code_is_modified )
			map.put("sonar_code",sonar_code);
		if ( cancel_div_is_modified )
			map.put("cancel_div",cancel_div);
		if ( reciept_div_is_modified )
			map.put("reciept_div",reciept_div);
		if ( guarantee_div_is_modified )
			map.put("guarantee_div",guarantee_div);
		if ( ordered_timestamp_is_modified )
			map.put("ordered_timestamp",ordered_timestamp);
		if ( status_is_modified )
			map.put("status",status);
		if (map.size() == 0) {
			map.put("trader_code",trader_code);
			map.put("debit_amount",debit_amount);
			map.put("credit_amount",credit_amount);
			if ( cash_transfer_type_is_set )
				map.put("cash_transfer_type",cash_transfer_type);
			map.put("remittance_fee",remittance_fee);
			if ( delivery_type_is_set )
				map.put("delivery_type",delivery_type);
			map.put("advance_settlement_date",advance_settlement_date);
			map.put("pay_temporarily_div",pay_temporarily_div);
			map.put("order_original_date",order_original_date);
			if ( sonar_code_is_set )
				map.put("sonar_code",sonar_code);
			map.put("cancel_div",cancel_div);
			map.put("reciept_div",reciept_div);
			map.put("guarantee_div",guarantee_div);
			map.put("ordered_timestamp",ordered_timestamp);
			if ( status_is_set )
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
   * <em>debit_amount</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getDebitAmount()
  {
    return ( debit_amount==null? ((long)0): debit_amount.longValue() );
  }


  /** 
   * <em>debit_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getDebitAmountIsNull()
  {
    return debit_amount == null;
  }


  /** 
   * <em>debit_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDebitAmountIsSet() {
    return debit_amount_is_set;
  }


  /** 
   * <em>debit_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDebitAmountIsModified() {
    return debit_amount_is_modified;
  }


  /** 
   * <em>credit_amount</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getCreditAmount()
  {
    return ( credit_amount==null? ((long)0): credit_amount.longValue() );
  }


  /** 
   * <em>credit_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCreditAmountIsNull()
  {
    return credit_amount == null;
  }


  /** 
   * <em>credit_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreditAmountIsSet() {
    return credit_amount_is_set;
  }


  /** 
   * <em>credit_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreditAmountIsModified() {
    return credit_amount_is_modified;
  }


  /** 
   * <em>cash_transfer_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCashTransferType()
  {
    return cash_transfer_type;
  }


  /** 
   * <em>cash_transfer_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashTransferTypeIsSet() {
    return cash_transfer_type_is_set;
  }


  /** 
   * <em>cash_transfer_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashTransferTypeIsModified() {
    return cash_transfer_type_is_modified;
  }


  /** 
   * <em>remittance_fee</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getRemittanceFee()
  {
    return ( remittance_fee==null? ((int)0): remittance_fee.intValue() );
  }


  /** 
   * <em>remittance_fee</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getRemittanceFeeIsNull()
  {
    return remittance_fee == null;
  }


  /** 
   * <em>remittance_fee</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRemittanceFeeIsSet() {
    return remittance_fee_is_set;
  }


  /** 
   * <em>remittance_fee</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRemittanceFeeIsModified() {
    return remittance_fee_is_modified;
  }


  /** 
   * <em>delivery_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDeliveryType()
  {
    return delivery_type;
  }


  /** 
   * <em>delivery_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveryTypeIsSet() {
    return delivery_type_is_set;
  }


  /** 
   * <em>delivery_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveryTypeIsModified() {
    return delivery_type_is_modified;
  }


  /** 
   * <em>advance_settlement_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAdvanceSettlementDate()
  {
    return advance_settlement_date;
  }


  /** 
   * <em>advance_settlement_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAdvanceSettlementDateIsSet() {
    return advance_settlement_date_is_set;
  }


  /** 
   * <em>advance_settlement_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAdvanceSettlementDateIsModified() {
    return advance_settlement_date_is_modified;
  }


  /** 
   * <em>pay_temporarily_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPayTemporarilyDiv()
  {
    return pay_temporarily_div;
  }


  /** 
   * <em>pay_temporarily_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPayTemporarilyDivIsSet() {
    return pay_temporarily_div_is_set;
  }


  /** 
   * <em>pay_temporarily_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPayTemporarilyDivIsModified() {
    return pay_temporarily_div_is_modified;
  }


  /** 
   * <em>order_original_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderOriginalDate()
  {
    return order_original_date;
  }


  /** 
   * <em>order_original_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderOriginalDateIsSet() {
    return order_original_date_is_set;
  }


  /** 
   * <em>order_original_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderOriginalDateIsModified() {
    return order_original_date_is_modified;
  }


  /** 
   * <em>sonar_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSonarCode()
  {
    return sonar_code;
  }


  /** 
   * <em>sonar_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarCodeIsSet() {
    return sonar_code_is_set;
  }


  /** 
   * <em>sonar_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarCodeIsModified() {
    return sonar_code_is_modified;
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
   * <em>reciept_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRecieptDiv()
  {
    return reciept_div;
  }


  /** 
   * <em>reciept_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecieptDivIsSet() {
    return reciept_div_is_set;
  }


  /** 
   * <em>reciept_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecieptDivIsModified() {
    return reciept_div_is_modified;
  }


  /** 
   * <em>guarantee_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGuaranteeDiv()
  {
    return guarantee_div;
  }


  /** 
   * <em>guarantee_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGuaranteeDivIsSet() {
    return guarantee_div_is_set;
  }


  /** 
   * <em>guarantee_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGuaranteeDivIsModified() {
    return guarantee_div_is_modified;
  }


  /** 
   * <em>ordered_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getOrderedTimestamp()
  {
    return ordered_timestamp;
  }


  /** 
   * <em>ordered_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderedTimestampIsSet() {
    return ordered_timestamp_is_set;
  }


  /** 
   * <em>ordered_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderedTimestampIsModified() {
    return ordered_timestamp_is_modified;
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
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new HostCashTransOrderPK(request_code, institution_code, branch_code, account_code, order_request_number);
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
   * <em>debit_amount</em>カラムの値を設定します。 
   *
   * @@param p_debitAmount <em>debit_amount</em>カラムの値をあらわす12桁以下のlong値 
   */
  public final void setDebitAmount( long p_debitAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    debit_amount = new Long(p_debitAmount);
    debit_amount_is_set = true;
    debit_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>debit_amount</em>カラムに値を設定します。 
   */
  public final void setDebitAmount( Long p_debitAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    debit_amount = p_debitAmount;
    debit_amount_is_set = true;
    debit_amount_is_modified = true;
  }


  /** 
   * <em>credit_amount</em>カラムの値を設定します。 
   *
   * @@param p_creditAmount <em>credit_amount</em>カラムの値をあらわす12桁以下のlong値 
   */
  public final void setCreditAmount( long p_creditAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    credit_amount = new Long(p_creditAmount);
    credit_amount_is_set = true;
    credit_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>credit_amount</em>カラムに値を設定します。 
   */
  public final void setCreditAmount( Long p_creditAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    credit_amount = p_creditAmount;
    credit_amount_is_set = true;
    credit_amount_is_modified = true;
  }


  /** 
   * <em>cash_transfer_type</em>カラムの値を設定します。 
   *
   * @@param p_cashTransferType <em>cash_transfer_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCashTransferType( String p_cashTransferType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_transfer_type = p_cashTransferType;
    cash_transfer_type_is_set = true;
    cash_transfer_type_is_modified = true;
  }


  /** 
   * <em>remittance_fee</em>カラムの値を設定します。 
   *
   * @@param p_remittanceFee <em>remittance_fee</em>カラムの値をあらわす5桁以下のint値 
   */
  public final void setRemittanceFee( int p_remittanceFee )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    remittance_fee = new Integer(p_remittanceFee);
    remittance_fee_is_set = true;
    remittance_fee_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>remittance_fee</em>カラムに値を設定します。 
   */
  public final void setRemittanceFee( Integer p_remittanceFee )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    remittance_fee = p_remittanceFee;
    remittance_fee_is_set = true;
    remittance_fee_is_modified = true;
  }


  /** 
   * <em>delivery_type</em>カラムの値を設定します。 
   *
   * @@param p_deliveryType <em>delivery_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDeliveryType( String p_deliveryType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delivery_type = p_deliveryType;
    delivery_type_is_set = true;
    delivery_type_is_modified = true;
  }


  /** 
   * <em>advance_settlement_date</em>カラムの値を設定します。 
   *
   * @@param p_advanceSettlementDate <em>advance_settlement_date</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setAdvanceSettlementDate( String p_advanceSettlementDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    advance_settlement_date = p_advanceSettlementDate;
    advance_settlement_date_is_set = true;
    advance_settlement_date_is_modified = true;
  }


  /** 
   * <em>pay_temporarily_div</em>カラムの値を設定します。 
   *
   * @@param p_payTemporarilyDiv <em>pay_temporarily_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPayTemporarilyDiv( String p_payTemporarilyDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    pay_temporarily_div = p_payTemporarilyDiv;
    pay_temporarily_div_is_set = true;
    pay_temporarily_div_is_modified = true;
  }


  /** 
   * <em>order_original_date</em>カラムの値を設定します。 
   *
   * @@param p_orderOriginalDate <em>order_original_date</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setOrderOriginalDate( String p_orderOriginalDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_original_date = p_orderOriginalDate;
    order_original_date_is_set = true;
    order_original_date_is_modified = true;
  }


  /** 
   * <em>sonar_code</em>カラムの値を設定します。 
   *
   * @@param p_sonarCode <em>sonar_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setSonarCode( String p_sonarCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sonar_code = p_sonarCode;
    sonar_code_is_set = true;
    sonar_code_is_modified = true;
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
   * <em>reciept_div</em>カラムの値を設定します。 
   *
   * @@param p_recieptDiv <em>reciept_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setRecieptDiv( String p_recieptDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    reciept_div = p_recieptDiv;
    reciept_div_is_set = true;
    reciept_div_is_modified = true;
  }


  /** 
   * <em>guarantee_div</em>カラムの値を設定します。 
   *
   * @@param p_guaranteeDiv <em>guarantee_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setGuaranteeDiv( String p_guaranteeDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    guarantee_div = p_guaranteeDiv;
    guarantee_div_is_set = true;
    guarantee_div_is_modified = true;
  }


  /** 
   * <em>ordered_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_orderedTimestamp <em>ordered_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setOrderedTimestamp( java.sql.Timestamp p_orderedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ordered_timestamp = p_orderedTimestamp;
    ordered_timestamp_is_set = true;
    ordered_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>ordered_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setOrderedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ordered_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    ordered_timestamp_is_set = true;
    ordered_timestamp_is_modified = true;
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
                else if ( name.equals("advance_settlement_date") ) {
                    return this.advance_settlement_date;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'c':
                if ( name.equals("credit_amount") ) {
                    return this.credit_amount;
                }
                else if ( name.equals("cash_transfer_type") ) {
                    return this.cash_transfer_type;
                }
                else if ( name.equals("cancel_div") ) {
                    return this.cancel_div;
                }
                break;
            case 'd':
                if ( name.equals("debit_amount") ) {
                    return this.debit_amount;
                }
                else if ( name.equals("delivery_type") ) {
                    return this.delivery_type;
                }
                break;
            case 'g':
                if ( name.equals("guarantee_div") ) {
                    return this.guarantee_div;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'o':
                if ( name.equals("order_request_number") ) {
                    return this.order_request_number;
                }
                else if ( name.equals("order_original_date") ) {
                    return this.order_original_date;
                }
                else if ( name.equals("ordered_timestamp") ) {
                    return this.ordered_timestamp;
                }
                break;
            case 'p':
                if ( name.equals("pay_temporarily_div") ) {
                    return this.pay_temporarily_div;
                }
                break;
            case 'r':
                if ( name.equals("request_code") ) {
                    return this.request_code;
                }
                else if ( name.equals("remittance_fee") ) {
                    return this.remittance_fee;
                }
                else if ( name.equals("reciept_div") ) {
                    return this.reciept_div;
                }
                break;
            case 's':
                if ( name.equals("sonar_code") ) {
                    return this.sonar_code;
                }
                else if ( name.equals("status") ) {
                    return this.status;
                }
                break;
            case 't':
                if ( name.equals("trader_code") ) {
                    return this.trader_code;
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
                else if ( name.equals("advance_settlement_date") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'advance_settlement_date' must be String: '"+value+"' is not." );
                    this.advance_settlement_date = (String) value;
                    if (this.advance_settlement_date_is_set)
                        this.advance_settlement_date_is_modified = true;
                    this.advance_settlement_date_is_set = true;
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
                if ( name.equals("credit_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'credit_amount' must be Long: '"+value+"' is not." );
                    this.credit_amount = (Long) value;
                    if (this.credit_amount_is_set)
                        this.credit_amount_is_modified = true;
                    this.credit_amount_is_set = true;
                    return;
                }
                else if ( name.equals("cash_transfer_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cash_transfer_type' must be String: '"+value+"' is not." );
                    this.cash_transfer_type = (String) value;
                    if (this.cash_transfer_type_is_set)
                        this.cash_transfer_type_is_modified = true;
                    this.cash_transfer_type_is_set = true;
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
                break;
            case 'd':
                if ( name.equals("debit_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'debit_amount' must be Long: '"+value+"' is not." );
                    this.debit_amount = (Long) value;
                    if (this.debit_amount_is_set)
                        this.debit_amount_is_modified = true;
                    this.debit_amount_is_set = true;
                    return;
                }
                else if ( name.equals("delivery_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'delivery_type' must be String: '"+value+"' is not." );
                    this.delivery_type = (String) value;
                    if (this.delivery_type_is_set)
                        this.delivery_type_is_modified = true;
                    this.delivery_type_is_set = true;
                    return;
                }
                break;
            case 'g':
                if ( name.equals("guarantee_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'guarantee_div' must be String: '"+value+"' is not." );
                    this.guarantee_div = (String) value;
                    if (this.guarantee_div_is_set)
                        this.guarantee_div_is_modified = true;
                    this.guarantee_div_is_set = true;
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
                else if ( name.equals("order_original_date") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_original_date' must be String: '"+value+"' is not." );
                    this.order_original_date = (String) value;
                    if (this.order_original_date_is_set)
                        this.order_original_date_is_modified = true;
                    this.order_original_date_is_set = true;
                    return;
                }
                else if ( name.equals("ordered_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'ordered_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.ordered_timestamp = (java.sql.Timestamp) value;
                    if (this.ordered_timestamp_is_set)
                        this.ordered_timestamp_is_modified = true;
                    this.ordered_timestamp_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("pay_temporarily_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'pay_temporarily_div' must be String: '"+value+"' is not." );
                    this.pay_temporarily_div = (String) value;
                    if (this.pay_temporarily_div_is_set)
                        this.pay_temporarily_div_is_modified = true;
                    this.pay_temporarily_div_is_set = true;
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
                else if ( name.equals("remittance_fee") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'remittance_fee' must be Integer: '"+value+"' is not." );
                    this.remittance_fee = (Integer) value;
                    if (this.remittance_fee_is_set)
                        this.remittance_fee_is_modified = true;
                    this.remittance_fee_is_set = true;
                    return;
                }
                else if ( name.equals("reciept_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'reciept_div' must be String: '"+value+"' is not." );
                    this.reciept_div = (String) value;
                    if (this.reciept_div_is_set)
                        this.reciept_div_is_modified = true;
                    this.reciept_div_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sonar_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sonar_code' must be String: '"+value+"' is not." );
                    this.sonar_code = (String) value;
                    if (this.sonar_code_is_set)
                        this.sonar_code_is_modified = true;
                    this.sonar_code_is_set = true;
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
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
