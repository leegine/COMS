head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.45.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	HostForeignCashTransOrderParams.java;


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
 * host_foreign_cash_trans_orderテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link HostForeignCashTransOrderRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link HostForeignCashTransOrderParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link HostForeignCashTransOrderParams}が{@@link HostForeignCashTransOrderRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see HostForeignCashTransOrderPK 
 * @@see HostForeignCashTransOrderRow 
 */
public class HostForeignCashTransOrderParams extends Params implements HostForeignCashTransOrderRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "host_foreign_cash_trans_order";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = HostForeignCashTransOrderRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return HostForeignCashTransOrderRow.TYPE;
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
   * <em>currency_code</em>カラムの値 
   */
  public  String  currency_code;

  /** 
   * <em>order_div</em>カラムの値 
   */
  public  String  order_div;

  /** 
   * <em>debit_amount</em>カラムの値 
   */
  public  Double  debit_amount;

  /** 
   * <em>credit_amount</em>カラムの値 
   */
  public  Double  credit_amount;

  /** 
   * <em>remark_code</em>カラムの値 
   */
  public  String  remark_code;

  /** 
   * <em>debit_convert_amount</em>カラムの値 
   */
  public  Long  debit_convert_amount;

  /** 
   * <em>credit_convert_amount</em>カラムの値 
   */
  public  Long  credit_convert_amount;

  /** 
   * <em>remark_name</em>カラムの値 
   */
  public  String  remark_name;

  /** 
   * <em>cash_trans_date</em>カラムの値 
   */
  public  String  cash_trans_date;

  /** 
   * <em>credit_div</em>カラムの値 
   */
  public  String  credit_div;

  /** 
   * <em>force_div</em>カラムの値 
   */
  public  String  force_div;

  /** 
   * <em>cancel_div</em>カラムの値 
   */
  public  String  cancel_div;

  /** 
   * <em>cash_transfer_type</em>カラムの値 
   */
  public  String  cash_transfer_type;

  /** 
   * <em>sonar_code</em>カラムの値 
   */
  public  String  sonar_code;

  /** 
   * <em>rate</em>カラムの値 
   */
  public  String  rate;

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
  private boolean currency_code_is_set = false;
  private boolean currency_code_is_modified = false;
  private boolean order_div_is_set = false;
  private boolean order_div_is_modified = false;
  private boolean debit_amount_is_set = false;
  private boolean debit_amount_is_modified = false;
  private boolean credit_amount_is_set = false;
  private boolean credit_amount_is_modified = false;
  private boolean remark_code_is_set = false;
  private boolean remark_code_is_modified = false;
  private boolean debit_convert_amount_is_set = false;
  private boolean debit_convert_amount_is_modified = false;
  private boolean credit_convert_amount_is_set = false;
  private boolean credit_convert_amount_is_modified = false;
  private boolean remark_name_is_set = false;
  private boolean remark_name_is_modified = false;
  private boolean cash_trans_date_is_set = false;
  private boolean cash_trans_date_is_modified = false;
  private boolean credit_div_is_set = false;
  private boolean credit_div_is_modified = false;
  private boolean force_div_is_set = false;
  private boolean force_div_is_modified = false;
  private boolean cancel_div_is_set = false;
  private boolean cancel_div_is_modified = false;
  private boolean cash_transfer_type_is_set = false;
  private boolean cash_transfer_type_is_modified = false;
  private boolean sonar_code_is_set = false;
  private boolean sonar_code_is_modified = false;
  private boolean rate_is_set = false;
  private boolean rate_is_modified = false;
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
      + "," + "currency_code=" +currency_code
      + "," + "order_div=" +order_div
      + "," + "debit_amount=" +debit_amount
      + "," + "credit_amount=" +credit_amount
      + "," + "remark_code=" +remark_code
      + "," + "debit_convert_amount=" +debit_convert_amount
      + "," + "credit_convert_amount=" +credit_convert_amount
      + "," + "remark_name=" +remark_name
      + "," + "cash_trans_date=" +cash_trans_date
      + "," + "credit_div=" +credit_div
      + "," + "force_div=" +force_div
      + "," + "cancel_div=" +cancel_div
      + "," + "cash_transfer_type=" +cash_transfer_type
      + "," + "sonar_code=" +sonar_code
      + "," + "rate=" +rate
      + "," + "ordered_timestamp=" +ordered_timestamp
      + "," + "status=" +status
      + "}";
  }


  /** 
   * 値が未設定のHostForeignCashTransOrderParamsオブジェクトを作成します。 
   */
  public HostForeignCashTransOrderParams() {
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
   * 指定のHostForeignCashTransOrderRowオブジェクトの値を利用してHostForeignCashTransOrderParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するHostForeignCashTransOrderRowオブジェクト 
   */
  public HostForeignCashTransOrderParams( HostForeignCashTransOrderRow p_row )
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
    currency_code = p_row.getCurrencyCode();
    currency_code_is_set = p_row.getCurrencyCodeIsSet();
    currency_code_is_modified = p_row.getCurrencyCodeIsModified();
    order_div = p_row.getOrderDiv();
    order_div_is_set = p_row.getOrderDivIsSet();
    order_div_is_modified = p_row.getOrderDivIsModified();
    if ( !p_row.getDebitAmountIsNull() )
      debit_amount = new Double( p_row.getDebitAmount() );
    debit_amount_is_set = p_row.getDebitAmountIsSet();
    debit_amount_is_modified = p_row.getDebitAmountIsModified();
    if ( !p_row.getCreditAmountIsNull() )
      credit_amount = new Double( p_row.getCreditAmount() );
    credit_amount_is_set = p_row.getCreditAmountIsSet();
    credit_amount_is_modified = p_row.getCreditAmountIsModified();
    remark_code = p_row.getRemarkCode();
    remark_code_is_set = p_row.getRemarkCodeIsSet();
    remark_code_is_modified = p_row.getRemarkCodeIsModified();
    if ( !p_row.getDebitConvertAmountIsNull() )
      debit_convert_amount = new Long( p_row.getDebitConvertAmount() );
    debit_convert_amount_is_set = p_row.getDebitConvertAmountIsSet();
    debit_convert_amount_is_modified = p_row.getDebitConvertAmountIsModified();
    if ( !p_row.getCreditConvertAmountIsNull() )
      credit_convert_amount = new Long( p_row.getCreditConvertAmount() );
    credit_convert_amount_is_set = p_row.getCreditConvertAmountIsSet();
    credit_convert_amount_is_modified = p_row.getCreditConvertAmountIsModified();
    remark_name = p_row.getRemarkName();
    remark_name_is_set = p_row.getRemarkNameIsSet();
    remark_name_is_modified = p_row.getRemarkNameIsModified();
    cash_trans_date = p_row.getCashTransDate();
    cash_trans_date_is_set = p_row.getCashTransDateIsSet();
    cash_trans_date_is_modified = p_row.getCashTransDateIsModified();
    credit_div = p_row.getCreditDiv();
    credit_div_is_set = p_row.getCreditDivIsSet();
    credit_div_is_modified = p_row.getCreditDivIsModified();
    force_div = p_row.getForceDiv();
    force_div_is_set = p_row.getForceDivIsSet();
    force_div_is_modified = p_row.getForceDivIsModified();
    cancel_div = p_row.getCancelDiv();
    cancel_div_is_set = p_row.getCancelDivIsSet();
    cancel_div_is_modified = p_row.getCancelDivIsModified();
    cash_transfer_type = p_row.getCashTransferType();
    cash_transfer_type_is_set = p_row.getCashTransferTypeIsSet();
    cash_transfer_type_is_modified = p_row.getCashTransferTypeIsModified();
    sonar_code = p_row.getSonarCode();
    sonar_code_is_set = p_row.getSonarCodeIsSet();
    sonar_code_is_modified = p_row.getSonarCodeIsModified();
    rate = p_row.getRate();
    rate_is_set = p_row.getRateIsSet();
    rate_is_modified = p_row.getRateIsModified();
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
      currency_code_is_set = true;
      currency_code_is_modified = true;
      order_div_is_set = true;
      order_div_is_modified = true;
      debit_amount_is_set = true;
      debit_amount_is_modified = true;
      credit_amount_is_set = true;
      credit_amount_is_modified = true;
      remark_code_is_set = true;
      remark_code_is_modified = true;
      debit_convert_amount_is_set = true;
      debit_convert_amount_is_modified = true;
      credit_convert_amount_is_set = true;
      credit_convert_amount_is_modified = true;
      remark_name_is_set = true;
      remark_name_is_modified = true;
      cash_trans_date_is_set = true;
      cash_trans_date_is_modified = true;
      credit_div_is_set = true;
      credit_div_is_modified = true;
      force_div_is_set = true;
      force_div_is_modified = true;
      cancel_div_is_set = true;
      cancel_div_is_modified = true;
      cash_transfer_type_is_set = true;
      cash_transfer_type_is_modified = true;
      sonar_code_is_set = true;
      sonar_code_is_modified = true;
      rate_is_set = true;
      rate_is_modified = true;
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
    if ( !( other instanceof HostForeignCashTransOrderRow ) )
       return false;
    return fieldsEqual( (HostForeignCashTransOrderRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のHostForeignCashTransOrderRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( HostForeignCashTransOrderRow row )
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
    if ( currency_code == null ) {
      if ( row.getCurrencyCode() != null )
        return false;
    } else if ( !currency_code.equals( row.getCurrencyCode() ) ) {
        return false;
    }
    if ( order_div == null ) {
      if ( row.getOrderDiv() != null )
        return false;
    } else if ( !order_div.equals( row.getOrderDiv() ) ) {
        return false;
    }
    if ( debit_amount == null ) {
      if ( !row.getDebitAmountIsNull() )
        return false;
    } else if ( row.getDebitAmountIsNull() || ( debit_amount.doubleValue() != row.getDebitAmount() ) ) {
        return false;
    }
    if ( credit_amount == null ) {
      if ( !row.getCreditAmountIsNull() )
        return false;
    } else if ( row.getCreditAmountIsNull() || ( credit_amount.doubleValue() != row.getCreditAmount() ) ) {
        return false;
    }
    if ( remark_code == null ) {
      if ( row.getRemarkCode() != null )
        return false;
    } else if ( !remark_code.equals( row.getRemarkCode() ) ) {
        return false;
    }
    if ( debit_convert_amount == null ) {
      if ( !row.getDebitConvertAmountIsNull() )
        return false;
    } else if ( row.getDebitConvertAmountIsNull() || ( debit_convert_amount.longValue() != row.getDebitConvertAmount() ) ) {
        return false;
    }
    if ( credit_convert_amount == null ) {
      if ( !row.getCreditConvertAmountIsNull() )
        return false;
    } else if ( row.getCreditConvertAmountIsNull() || ( credit_convert_amount.longValue() != row.getCreditConvertAmount() ) ) {
        return false;
    }
    if ( remark_name == null ) {
      if ( row.getRemarkName() != null )
        return false;
    } else if ( !remark_name.equals( row.getRemarkName() ) ) {
        return false;
    }
    if ( cash_trans_date == null ) {
      if ( row.getCashTransDate() != null )
        return false;
    } else if ( !cash_trans_date.equals( row.getCashTransDate() ) ) {
        return false;
    }
    if ( credit_div == null ) {
      if ( row.getCreditDiv() != null )
        return false;
    } else if ( !credit_div.equals( row.getCreditDiv() ) ) {
        return false;
    }
    if ( force_div == null ) {
      if ( row.getForceDiv() != null )
        return false;
    } else if ( !force_div.equals( row.getForceDiv() ) ) {
        return false;
    }
    if ( cancel_div == null ) {
      if ( row.getCancelDiv() != null )
        return false;
    } else if ( !cancel_div.equals( row.getCancelDiv() ) ) {
        return false;
    }
    if ( cash_transfer_type == null ) {
      if ( row.getCashTransferType() != null )
        return false;
    } else if ( !cash_transfer_type.equals( row.getCashTransferType() ) ) {
        return false;
    }
    if ( sonar_code == null ) {
      if ( row.getSonarCode() != null )
        return false;
    } else if ( !sonar_code.equals( row.getSonarCode() ) ) {
        return false;
    }
    if ( rate == null ) {
      if ( row.getRate() != null )
        return false;
    } else if ( !rate.equals( row.getRate() ) ) {
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
        + (currency_code!=null? currency_code.hashCode(): 0) 
        + (order_div!=null? order_div.hashCode(): 0) 
        + (debit_amount!=null? debit_amount.hashCode(): 0) 
        + (credit_amount!=null? credit_amount.hashCode(): 0) 
        + (remark_code!=null? remark_code.hashCode(): 0) 
        + (debit_convert_amount!=null? debit_convert_amount.hashCode(): 0) 
        + (credit_convert_amount!=null? credit_convert_amount.hashCode(): 0) 
        + (remark_name!=null? remark_name.hashCode(): 0) 
        + (cash_trans_date!=null? cash_trans_date.hashCode(): 0) 
        + (credit_div!=null? credit_div.hashCode(): 0) 
        + (force_div!=null? force_div.hashCode(): 0) 
        + (cancel_div!=null? cancel_div.hashCode(): 0) 
        + (cash_transfer_type!=null? cash_transfer_type.hashCode(): 0) 
        + (sonar_code!=null? sonar_code.hashCode(): 0) 
        + (rate!=null? rate.hashCode(): 0) 
        + (ordered_timestamp!=null? ordered_timestamp.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !currency_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'currency_code' must be set before inserting.");
		if ( !order_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_div' must be set before inserting.");
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
		map.put("currency_code",currency_code);
		map.put("order_div",order_div);
		if ( debit_amount != null )
			map.put("debit_amount",debit_amount);
		if ( credit_amount != null )
			map.put("credit_amount",credit_amount);
		if ( remark_code != null )
			map.put("remark_code",remark_code);
		if ( debit_convert_amount != null )
			map.put("debit_convert_amount",debit_convert_amount);
		if ( credit_convert_amount != null )
			map.put("credit_convert_amount",credit_convert_amount);
		if ( remark_name != null )
			map.put("remark_name",remark_name);
		if ( cash_trans_date != null )
			map.put("cash_trans_date",cash_trans_date);
		if ( credit_div != null )
			map.put("credit_div",credit_div);
		if ( force_div != null )
			map.put("force_div",force_div);
		if ( cancel_div != null )
			map.put("cancel_div",cancel_div);
		if ( cash_transfer_type != null )
			map.put("cash_transfer_type",cash_transfer_type);
		if ( sonar_code != null )
			map.put("sonar_code",sonar_code);
		if ( rate != null )
			map.put("rate",rate);
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
		if ( currency_code_is_modified )
			map.put("currency_code",currency_code);
		if ( order_div_is_modified )
			map.put("order_div",order_div);
		if ( debit_amount_is_modified )
			map.put("debit_amount",debit_amount);
		if ( credit_amount_is_modified )
			map.put("credit_amount",credit_amount);
		if ( remark_code_is_modified )
			map.put("remark_code",remark_code);
		if ( debit_convert_amount_is_modified )
			map.put("debit_convert_amount",debit_convert_amount);
		if ( credit_convert_amount_is_modified )
			map.put("credit_convert_amount",credit_convert_amount);
		if ( remark_name_is_modified )
			map.put("remark_name",remark_name);
		if ( cash_trans_date_is_modified )
			map.put("cash_trans_date",cash_trans_date);
		if ( credit_div_is_modified )
			map.put("credit_div",credit_div);
		if ( force_div_is_modified )
			map.put("force_div",force_div);
		if ( cancel_div_is_modified )
			map.put("cancel_div",cancel_div);
		if ( cash_transfer_type_is_modified )
			map.put("cash_transfer_type",cash_transfer_type);
		if ( sonar_code_is_modified )
			map.put("sonar_code",sonar_code);
		if ( rate_is_modified )
			map.put("rate",rate);
		if ( ordered_timestamp_is_modified )
			map.put("ordered_timestamp",ordered_timestamp);
		if ( status_is_modified )
			map.put("status",status);
		if (map.size() == 0) {
			map.put("trader_code",trader_code);
			if ( currency_code_is_set )
				map.put("currency_code",currency_code);
			if ( order_div_is_set )
				map.put("order_div",order_div);
			map.put("debit_amount",debit_amount);
			map.put("credit_amount",credit_amount);
			map.put("remark_code",remark_code);
			map.put("debit_convert_amount",debit_convert_amount);
			map.put("credit_convert_amount",credit_convert_amount);
			map.put("remark_name",remark_name);
			map.put("cash_trans_date",cash_trans_date);
			map.put("credit_div",credit_div);
			map.put("force_div",force_div);
			map.put("cancel_div",cancel_div);
			map.put("cash_transfer_type",cash_transfer_type);
			map.put("sonar_code",sonar_code);
			map.put("rate",rate);
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
   * <em>currency_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCurrencyCode()
  {
    return currency_code;
  }


  /** 
   * <em>currency_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCurrencyCodeIsSet() {
    return currency_code_is_set;
  }


  /** 
   * <em>currency_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCurrencyCodeIsModified() {
    return currency_code_is_modified;
  }


  /** 
   * <em>order_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderDiv()
  {
    return order_div;
  }


  /** 
   * <em>order_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderDivIsSet() {
    return order_div_is_set;
  }


  /** 
   * <em>order_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderDivIsModified() {
    return order_div_is_modified;
  }


  /** 
   * <em>debit_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getDebitAmount()
  {
    return ( debit_amount==null? ((double)0): debit_amount.doubleValue() );
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
   * @@return doubleの値 
   */
  public final double getCreditAmount()
  {
    return ( credit_amount==null? ((double)0): credit_amount.doubleValue() );
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
   * <em>debit_convert_amount</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getDebitConvertAmount()
  {
    return ( debit_convert_amount==null? ((long)0): debit_convert_amount.longValue() );
  }


  /** 
   * <em>debit_convert_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getDebitConvertAmountIsNull()
  {
    return debit_convert_amount == null;
  }


  /** 
   * <em>debit_convert_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDebitConvertAmountIsSet() {
    return debit_convert_amount_is_set;
  }


  /** 
   * <em>debit_convert_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDebitConvertAmountIsModified() {
    return debit_convert_amount_is_modified;
  }


  /** 
   * <em>credit_convert_amount</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getCreditConvertAmount()
  {
    return ( credit_convert_amount==null? ((long)0): credit_convert_amount.longValue() );
  }


  /** 
   * <em>credit_convert_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCreditConvertAmountIsNull()
  {
    return credit_convert_amount == null;
  }


  /** 
   * <em>credit_convert_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreditConvertAmountIsSet() {
    return credit_convert_amount_is_set;
  }


  /** 
   * <em>credit_convert_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreditConvertAmountIsModified() {
    return credit_convert_amount_is_modified;
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
   * <em>cash_trans_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCashTransDate()
  {
    return cash_trans_date;
  }


  /** 
   * <em>cash_trans_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashTransDateIsSet() {
    return cash_trans_date_is_set;
  }


  /** 
   * <em>cash_trans_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashTransDateIsModified() {
    return cash_trans_date_is_modified;
  }


  /** 
   * <em>credit_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCreditDiv()
  {
    return credit_div;
  }


  /** 
   * <em>credit_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreditDivIsSet() {
    return credit_div_is_set;
  }


  /** 
   * <em>credit_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreditDivIsModified() {
    return credit_div_is_modified;
  }


  /** 
   * <em>force_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getForceDiv()
  {
    return force_div;
  }


  /** 
   * <em>force_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForceDivIsSet() {
    return force_div_is_set;
  }


  /** 
   * <em>force_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForceDivIsModified() {
    return force_div_is_modified;
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
   * <em>rate</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRate()
  {
    return rate;
  }


  /** 
   * <em>rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRateIsSet() {
    return rate_is_set;
  }


  /** 
   * <em>rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRateIsModified() {
    return rate_is_modified;
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
    return new HostForeignCashTransOrderPK(request_code, institution_code, branch_code, account_code, order_request_number);
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
   * <em>currency_code</em>カラムの値を設定します。 
   *
   * @@param p_currencyCode <em>currency_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setCurrencyCode( String p_currencyCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    currency_code = p_currencyCode;
    currency_code_is_set = true;
    currency_code_is_modified = true;
  }


  /** 
   * <em>order_div</em>カラムの値を設定します。 
   *
   * @@param p_orderDiv <em>order_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOrderDiv( String p_orderDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_div = p_orderDiv;
    order_div_is_set = true;
    order_div_is_modified = true;
  }


  /** 
   * <em>debit_amount</em>カラムの値を設定します。 
   *
   * @@param p_debitAmount <em>debit_amount</em>カラムの値をあらわす13桁以下のdouble値で、その精度は2桁まで
   */
  public final void setDebitAmount( double p_debitAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    debit_amount = new Double(p_debitAmount);
    debit_amount_is_set = true;
    debit_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>debit_amount</em>カラムに値を設定します。 
   */
  public final void setDebitAmount( Double p_debitAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    debit_amount = p_debitAmount;
    debit_amount_is_set = true;
    debit_amount_is_modified = true;
  }


  /** 
   * <em>credit_amount</em>カラムの値を設定します。 
   *
   * @@param p_creditAmount <em>credit_amount</em>カラムの値をあらわす13桁以下のdouble値で、その精度は2桁まで
   */
  public final void setCreditAmount( double p_creditAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    credit_amount = new Double(p_creditAmount);
    credit_amount_is_set = true;
    credit_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>credit_amount</em>カラムに値を設定します。 
   */
  public final void setCreditAmount( Double p_creditAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    credit_amount = p_creditAmount;
    credit_amount_is_set = true;
    credit_amount_is_modified = true;
  }


  /** 
   * <em>remark_code</em>カラムの値を設定します。 
   *
   * @@param p_remarkCode <em>remark_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setRemarkCode( String p_remarkCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    remark_code = p_remarkCode;
    remark_code_is_set = true;
    remark_code_is_modified = true;
  }


  /** 
   * <em>debit_convert_amount</em>カラムの値を設定します。 
   *
   * @@param p_debitConvertAmount <em>debit_convert_amount</em>カラムの値をあらわす12桁以下のlong値 
   */
  public final void setDebitConvertAmount( long p_debitConvertAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    debit_convert_amount = new Long(p_debitConvertAmount);
    debit_convert_amount_is_set = true;
    debit_convert_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>debit_convert_amount</em>カラムに値を設定します。 
   */
  public final void setDebitConvertAmount( Long p_debitConvertAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    debit_convert_amount = p_debitConvertAmount;
    debit_convert_amount_is_set = true;
    debit_convert_amount_is_modified = true;
  }


  /** 
   * <em>credit_convert_amount</em>カラムの値を設定します。 
   *
   * @@param p_creditConvertAmount <em>credit_convert_amount</em>カラムの値をあらわす12桁以下のlong値 
   */
  public final void setCreditConvertAmount( long p_creditConvertAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    credit_convert_amount = new Long(p_creditConvertAmount);
    credit_convert_amount_is_set = true;
    credit_convert_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>credit_convert_amount</em>カラムに値を設定します。 
   */
  public final void setCreditConvertAmount( Long p_creditConvertAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    credit_convert_amount = p_creditConvertAmount;
    credit_convert_amount_is_set = true;
    credit_convert_amount_is_modified = true;
  }


  /** 
   * <em>remark_name</em>カラムの値を設定します。 
   *
   * @@param p_remarkName <em>remark_name</em>カラムの値をあらわす23桁以下のString値 
   */
  public final void setRemarkName( String p_remarkName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    remark_name = p_remarkName;
    remark_name_is_set = true;
    remark_name_is_modified = true;
  }


  /** 
   * <em>cash_trans_date</em>カラムの値を設定します。 
   *
   * @@param p_cashTransDate <em>cash_trans_date</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setCashTransDate( String p_cashTransDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_trans_date = p_cashTransDate;
    cash_trans_date_is_set = true;
    cash_trans_date_is_modified = true;
  }


  /** 
   * <em>credit_div</em>カラムの値を設定します。 
   *
   * @@param p_creditDiv <em>credit_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCreditDiv( String p_creditDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    credit_div = p_creditDiv;
    credit_div_is_set = true;
    credit_div_is_modified = true;
  }


  /** 
   * <em>force_div</em>カラムの値を設定します。 
   *
   * @@param p_forceDiv <em>force_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setForceDiv( String p_forceDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    force_div = p_forceDiv;
    force_div_is_set = true;
    force_div_is_modified = true;
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
   * <em>rate</em>カラムの値を設定します。 
   *
   * @@param p_rate <em>rate</em>カラムの値をあらわす9桁以下のString値 
   */
  public final void setRate( String p_rate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    rate = p_rate;
    rate_is_set = true;
    rate_is_modified = true;
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
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'c':
                if ( name.equals("currency_code") ) {
                    return this.currency_code;
                }
                else if ( name.equals("credit_amount") ) {
                    return this.credit_amount;
                }
                else if ( name.equals("credit_convert_amount") ) {
                    return this.credit_convert_amount;
                }
                else if ( name.equals("cash_trans_date") ) {
                    return this.cash_trans_date;
                }
                else if ( name.equals("credit_div") ) {
                    return this.credit_div;
                }
                else if ( name.equals("cancel_div") ) {
                    return this.cancel_div;
                }
                else if ( name.equals("cash_transfer_type") ) {
                    return this.cash_transfer_type;
                }
                break;
            case 'd':
                if ( name.equals("debit_amount") ) {
                    return this.debit_amount;
                }
                else if ( name.equals("debit_convert_amount") ) {
                    return this.debit_convert_amount;
                }
                break;
            case 'f':
                if ( name.equals("force_div") ) {
                    return this.force_div;
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
                else if ( name.equals("order_div") ) {
                    return this.order_div;
                }
                else if ( name.equals("ordered_timestamp") ) {
                    return this.ordered_timestamp;
                }
                break;
            case 'r':
                if ( name.equals("request_code") ) {
                    return this.request_code;
                }
                else if ( name.equals("remark_code") ) {
                    return this.remark_code;
                }
                else if ( name.equals("remark_name") ) {
                    return this.remark_name;
                }
                else if ( name.equals("rate") ) {
                    return this.rate;
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
                if ( name.equals("currency_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'currency_code' must be String: '"+value+"' is not." );
                    this.currency_code = (String) value;
                    if (this.currency_code_is_set)
                        this.currency_code_is_modified = true;
                    this.currency_code_is_set = true;
                    return;
                }
                else if ( name.equals("credit_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'credit_amount' must be Double: '"+value+"' is not." );
                    this.credit_amount = (Double) value;
                    if (this.credit_amount_is_set)
                        this.credit_amount_is_modified = true;
                    this.credit_amount_is_set = true;
                    return;
                }
                else if ( name.equals("credit_convert_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'credit_convert_amount' must be Long: '"+value+"' is not." );
                    this.credit_convert_amount = (Long) value;
                    if (this.credit_convert_amount_is_set)
                        this.credit_convert_amount_is_modified = true;
                    this.credit_convert_amount_is_set = true;
                    return;
                }
                else if ( name.equals("cash_trans_date") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cash_trans_date' must be String: '"+value+"' is not." );
                    this.cash_trans_date = (String) value;
                    if (this.cash_trans_date_is_set)
                        this.cash_trans_date_is_modified = true;
                    this.cash_trans_date_is_set = true;
                    return;
                }
                else if ( name.equals("credit_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'credit_div' must be String: '"+value+"' is not." );
                    this.credit_div = (String) value;
                    if (this.credit_div_is_set)
                        this.credit_div_is_modified = true;
                    this.credit_div_is_set = true;
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
                else if ( name.equals("cash_transfer_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cash_transfer_type' must be String: '"+value+"' is not." );
                    this.cash_transfer_type = (String) value;
                    if (this.cash_transfer_type_is_set)
                        this.cash_transfer_type_is_modified = true;
                    this.cash_transfer_type_is_set = true;
                    return;
                }
                break;
            case 'd':
                if ( name.equals("debit_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'debit_amount' must be Double: '"+value+"' is not." );
                    this.debit_amount = (Double) value;
                    if (this.debit_amount_is_set)
                        this.debit_amount_is_modified = true;
                    this.debit_amount_is_set = true;
                    return;
                }
                else if ( name.equals("debit_convert_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'debit_convert_amount' must be Long: '"+value+"' is not." );
                    this.debit_convert_amount = (Long) value;
                    if (this.debit_convert_amount_is_set)
                        this.debit_convert_amount_is_modified = true;
                    this.debit_convert_amount_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("force_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'force_div' must be String: '"+value+"' is not." );
                    this.force_div = (String) value;
                    if (this.force_div_is_set)
                        this.force_div_is_modified = true;
                    this.force_div_is_set = true;
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
                else if ( name.equals("order_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_div' must be String: '"+value+"' is not." );
                    this.order_div = (String) value;
                    if (this.order_div_is_set)
                        this.order_div_is_modified = true;
                    this.order_div_is_set = true;
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
                else if ( name.equals("remark_code") ) {
                    if ( value != null )
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
                else if ( name.equals("rate") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'rate' must be String: '"+value+"' is not." );
                    this.rate = (String) value;
                    if (this.rate_is_set)
                        this.rate_is_modified = true;
                    this.rate_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sonar_code") ) {
                    if ( value != null )
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
