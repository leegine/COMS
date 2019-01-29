head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.46.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	HostTransferOrderParams.java;


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
 * host_transfer_orderテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link HostTransferOrderRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link HostTransferOrderParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link HostTransferOrderParams}が{@@link HostTransferOrderRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see HostTransferOrderPK 
 * @@see HostTransferOrderRow 
 */
public class HostTransferOrderParams extends Params implements HostTransferOrderRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "host_transfer_order";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = HostTransferOrderRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return HostTransferOrderRow.TYPE;
  }


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
   * <em>request_code</em>カラムの値 
   */
  public  String  request_code;

  /** 
   * <em>trader_code</em>カラムの値 
   */
  public  String  trader_code;

  /** 
   * <em>transfer_amount_debitor</em>カラムの値 
   */
  public  Integer  transfer_amount_debitor;

  /** 
   * <em>transfer_amount_creditor</em>カラムの値 
   */
  public  Integer  transfer_amount_creditor;

  /** 
   * <em>remark_code</em>カラムの値 
   */
  public  String  remark_code;

  /** 
   * <em>remark_name</em>カラムの値 
   */
  public  String  remark_name;

  /** 
   * <em>involuntary_transfer</em>カラムの値 
   */
  public  String  involuntary_transfer;

  /** 
   * <em>original_transfer_date</em>カラムの値 
   */
  public  String  original_transfer_date;

  /** 
   * <em>cancel_div</em>カラムの値 
   */
  public  String  cancel_div;

  /** 
   * <em>transfer_date</em>カラムの値 
   */
  public  java.sql.Timestamp  transfer_date;

  /** 
   * <em>transfer_time</em>カラムの値 
   */
  public  java.sql.Timestamp  transfer_time;

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
  private boolean transfer_amount_debitor_is_set = false;
  private boolean transfer_amount_debitor_is_modified = false;
  private boolean transfer_amount_creditor_is_set = false;
  private boolean transfer_amount_creditor_is_modified = false;
  private boolean remark_code_is_set = false;
  private boolean remark_code_is_modified = false;
  private boolean remark_name_is_set = false;
  private boolean remark_name_is_modified = false;
  private boolean involuntary_transfer_is_set = false;
  private boolean involuntary_transfer_is_modified = false;
  private boolean original_transfer_date_is_set = false;
  private boolean original_transfer_date_is_modified = false;
  private boolean cancel_div_is_set = false;
  private boolean cancel_div_is_modified = false;
  private boolean order_request_number_is_set = false;
  private boolean order_request_number_is_modified = false;
  private boolean transfer_date_is_set = false;
  private boolean transfer_date_is_modified = false;
  private boolean transfer_time_is_set = false;
  private boolean transfer_time_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "branch_code=" + branch_code
      + "," + "account_code=" + account_code
      + "," + "order_request_number=" + order_request_number
      + "," + "request_code=" +request_code
      + "," + "trader_code=" +trader_code
      + "," + "transfer_amount_debitor=" +transfer_amount_debitor
      + "," + "transfer_amount_creditor=" +transfer_amount_creditor
      + "," + "remark_code=" +remark_code
      + "," + "remark_name=" +remark_name
      + "," + "involuntary_transfer=" +involuntary_transfer
      + "," + "original_transfer_date=" +original_transfer_date
      + "," + "cancel_div=" +cancel_div
      + "," + "transfer_date=" +transfer_date
      + "," + "transfer_time=" +transfer_time
      + "," + "status=" +status
      + "}";
  }


  /** 
   * 値が未設定のHostTransferOrderParamsオブジェクトを作成します。 
   */
  public HostTransferOrderParams() {
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
   * 指定のHostTransferOrderRowオブジェクトの値を利用してHostTransferOrderParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するHostTransferOrderRowオブジェクト 
   */
  public HostTransferOrderParams( HostTransferOrderRow p_row )
  {
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
    request_code = p_row.getRequestCode();
    request_code_is_set = p_row.getRequestCodeIsSet();
    request_code_is_modified = p_row.getRequestCodeIsModified();
    trader_code = p_row.getTraderCode();
    trader_code_is_set = p_row.getTraderCodeIsSet();
    trader_code_is_modified = p_row.getTraderCodeIsModified();
    if ( !p_row.getTransferAmountDebitorIsNull() )
      transfer_amount_debitor = new Integer( p_row.getTransferAmountDebitor() );
    transfer_amount_debitor_is_set = p_row.getTransferAmountDebitorIsSet();
    transfer_amount_debitor_is_modified = p_row.getTransferAmountDebitorIsModified();
    if ( !p_row.getTransferAmountCreditorIsNull() )
      transfer_amount_creditor = new Integer( p_row.getTransferAmountCreditor() );
    transfer_amount_creditor_is_set = p_row.getTransferAmountCreditorIsSet();
    transfer_amount_creditor_is_modified = p_row.getTransferAmountCreditorIsModified();
    remark_code = p_row.getRemarkCode();
    remark_code_is_set = p_row.getRemarkCodeIsSet();
    remark_code_is_modified = p_row.getRemarkCodeIsModified();
    remark_name = p_row.getRemarkName();
    remark_name_is_set = p_row.getRemarkNameIsSet();
    remark_name_is_modified = p_row.getRemarkNameIsModified();
    involuntary_transfer = p_row.getInvoluntaryTransfer();
    involuntary_transfer_is_set = p_row.getInvoluntaryTransferIsSet();
    involuntary_transfer_is_modified = p_row.getInvoluntaryTransferIsModified();
    original_transfer_date = p_row.getOriginalTransferDate();
    original_transfer_date_is_set = p_row.getOriginalTransferDateIsSet();
    original_transfer_date_is_modified = p_row.getOriginalTransferDateIsModified();
    cancel_div = p_row.getCancelDiv();
    cancel_div_is_set = p_row.getCancelDivIsSet();
    cancel_div_is_modified = p_row.getCancelDivIsModified();
    transfer_date = p_row.getTransferDate();
    transfer_date_is_set = p_row.getTransferDateIsSet();
    transfer_date_is_modified = p_row.getTransferDateIsModified();
    transfer_time = p_row.getTransferTime();
    transfer_time_is_set = p_row.getTransferTimeIsSet();
    transfer_time_is_modified = p_row.getTransferTimeIsModified();
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
      trader_code_is_set = true;
      trader_code_is_modified = true;
      transfer_amount_debitor_is_set = true;
      transfer_amount_debitor_is_modified = true;
      transfer_amount_creditor_is_set = true;
      transfer_amount_creditor_is_modified = true;
      remark_code_is_set = true;
      remark_code_is_modified = true;
      remark_name_is_set = true;
      remark_name_is_modified = true;
      involuntary_transfer_is_set = true;
      involuntary_transfer_is_modified = true;
      original_transfer_date_is_set = true;
      original_transfer_date_is_modified = true;
      cancel_div_is_set = true;
      cancel_div_is_modified = true;
      transfer_date_is_set = true;
      transfer_date_is_modified = true;
      transfer_time_is_set = true;
      transfer_time_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof HostTransferOrderRow ) )
       return false;
    return fieldsEqual( (HostTransferOrderRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のHostTransferOrderRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( HostTransferOrderRow row )
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
    if ( transfer_amount_debitor == null ) {
      if ( !row.getTransferAmountDebitorIsNull() )
        return false;
    } else if ( row.getTransferAmountDebitorIsNull() || ( transfer_amount_debitor.intValue() != row.getTransferAmountDebitor() ) ) {
        return false;
    }
    if ( transfer_amount_creditor == null ) {
      if ( !row.getTransferAmountCreditorIsNull() )
        return false;
    } else if ( row.getTransferAmountCreditorIsNull() || ( transfer_amount_creditor.intValue() != row.getTransferAmountCreditor() ) ) {
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
    if ( involuntary_transfer == null ) {
      if ( row.getInvoluntaryTransfer() != null )
        return false;
    } else if ( !involuntary_transfer.equals( row.getInvoluntaryTransfer() ) ) {
        return false;
    }
    if ( original_transfer_date == null ) {
      if ( row.getOriginalTransferDate() != null )
        return false;
    } else if ( !original_transfer_date.equals( row.getOriginalTransferDate() ) ) {
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
    if ( transfer_date == null ) {
      if ( row.getTransferDate() != null )
        return false;
    } else if ( !transfer_date.equals( row.getTransferDate() ) ) {
        return false;
    }
    if ( transfer_time == null ) {
      if ( row.getTransferTime() != null )
        return false;
    } else if ( !transfer_time.equals( row.getTransferTime() ) ) {
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
        + (transfer_amount_debitor!=null? transfer_amount_debitor.hashCode(): 0) 
        + (transfer_amount_creditor!=null? transfer_amount_creditor.hashCode(): 0) 
        + (remark_code!=null? remark_code.hashCode(): 0) 
        + (remark_name!=null? remark_name.hashCode(): 0) 
        + (involuntary_transfer!=null? involuntary_transfer.hashCode(): 0) 
        + (original_transfer_date!=null? original_transfer_date.hashCode(): 0) 
        + (cancel_div!=null? cancel_div.hashCode(): 0) 
        + (order_request_number!=null? order_request_number.hashCode(): 0) 
        + (transfer_date!=null? transfer_date.hashCode(): 0) 
        + (transfer_time!=null? transfer_time.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !request_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'request_code' must be set before inserting.");
		if ( !remark_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'remark_code' must be set before inserting.");
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
		if ( transfer_amount_debitor != null )
			map.put("transfer_amount_debitor",transfer_amount_debitor);
		if ( transfer_amount_creditor != null )
			map.put("transfer_amount_creditor",transfer_amount_creditor);
		map.put("remark_code",remark_code);
		if ( remark_name != null )
			map.put("remark_name",remark_name);
		if ( involuntary_transfer != null )
			map.put("involuntary_transfer",involuntary_transfer);
		if ( original_transfer_date != null )
			map.put("original_transfer_date",original_transfer_date);
		if ( cancel_div != null )
			map.put("cancel_div",cancel_div);
		map.put("order_request_number",order_request_number);
		if ( transfer_date != null )
			map.put("transfer_date",transfer_date);
		if ( transfer_time != null )
			map.put("transfer_time",transfer_time);
		map.put("status",status);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( request_code_is_modified )
			map.put("request_code",request_code);
		if ( trader_code_is_modified )
			map.put("trader_code",trader_code);
		if ( transfer_amount_debitor_is_modified )
			map.put("transfer_amount_debitor",transfer_amount_debitor);
		if ( transfer_amount_creditor_is_modified )
			map.put("transfer_amount_creditor",transfer_amount_creditor);
		if ( remark_code_is_modified )
			map.put("remark_code",remark_code);
		if ( remark_name_is_modified )
			map.put("remark_name",remark_name);
		if ( involuntary_transfer_is_modified )
			map.put("involuntary_transfer",involuntary_transfer);
		if ( original_transfer_date_is_modified )
			map.put("original_transfer_date",original_transfer_date);
		if ( cancel_div_is_modified )
			map.put("cancel_div",cancel_div);
		if ( transfer_date_is_modified )
			map.put("transfer_date",transfer_date);
		if ( transfer_time_is_modified )
			map.put("transfer_time",transfer_time);
		if ( status_is_modified )
			map.put("status",status);
		if (map.size() == 0) {
			if ( request_code_is_set )
				map.put("request_code",request_code);
			map.put("trader_code",trader_code);
			map.put("transfer_amount_debitor",transfer_amount_debitor);
			map.put("transfer_amount_creditor",transfer_amount_creditor);
			if ( remark_code_is_set )
				map.put("remark_code",remark_code);
			map.put("remark_name",remark_name);
			map.put("involuntary_transfer",involuntary_transfer);
			map.put("original_transfer_date",original_transfer_date);
			map.put("cancel_div",cancel_div);
			map.put("transfer_date",transfer_date);
			map.put("transfer_time",transfer_time);
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
   * <em>transfer_amount_debitor</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getTransferAmountDebitor()
  {
    return ( transfer_amount_debitor==null? ((int)0): transfer_amount_debitor.intValue() );
  }


  /** 
   * <em>transfer_amount_debitor</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTransferAmountDebitorIsNull()
  {
    return transfer_amount_debitor == null;
  }


  /** 
   * <em>transfer_amount_debitor</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferAmountDebitorIsSet() {
    return transfer_amount_debitor_is_set;
  }


  /** 
   * <em>transfer_amount_debitor</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferAmountDebitorIsModified() {
    return transfer_amount_debitor_is_modified;
  }


  /** 
   * <em>transfer_amount_creditor</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getTransferAmountCreditor()
  {
    return ( transfer_amount_creditor==null? ((int)0): transfer_amount_creditor.intValue() );
  }


  /** 
   * <em>transfer_amount_creditor</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTransferAmountCreditorIsNull()
  {
    return transfer_amount_creditor == null;
  }


  /** 
   * <em>transfer_amount_creditor</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferAmountCreditorIsSet() {
    return transfer_amount_creditor_is_set;
  }


  /** 
   * <em>transfer_amount_creditor</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferAmountCreditorIsModified() {
    return transfer_amount_creditor_is_modified;
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
   * <em>involuntary_transfer</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInvoluntaryTransfer()
  {
    return involuntary_transfer;
  }


  /** 
   * <em>involuntary_transfer</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInvoluntaryTransferIsSet() {
    return involuntary_transfer_is_set;
  }


  /** 
   * <em>involuntary_transfer</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInvoluntaryTransferIsModified() {
    return involuntary_transfer_is_modified;
  }


  /** 
   * <em>original_transfer_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOriginalTransferDate()
  {
    return original_transfer_date;
  }


  /** 
   * <em>original_transfer_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOriginalTransferDateIsSet() {
    return original_transfer_date_is_set;
  }


  /** 
   * <em>original_transfer_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOriginalTransferDateIsModified() {
    return original_transfer_date_is_modified;
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
   * <em>transfer_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getTransferDate()
  {
    return transfer_date;
  }


  /** 
   * <em>transfer_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferDateIsSet() {
    return transfer_date_is_set;
  }


  /** 
   * <em>transfer_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferDateIsModified() {
    return transfer_date_is_modified;
  }


  /** 
   * <em>transfer_time</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getTransferTime()
  {
    return transfer_time;
  }


  /** 
   * <em>transfer_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferTimeIsSet() {
    return transfer_time_is_set;
  }


  /** 
   * <em>transfer_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferTimeIsModified() {
    return transfer_time_is_modified;
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
    return new HostTransferOrderPK(institution_code, branch_code, account_code, order_request_number);
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
   * <em>transfer_amount_debitor</em>カラムの値を設定します。 
   *
   * @@param p_transferAmountDebitor <em>transfer_amount_debitor</em>カラムの値をあらわす9桁以下のint値 
   */
  public final void setTransferAmountDebitor( int p_transferAmountDebitor )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transfer_amount_debitor = new Integer(p_transferAmountDebitor);
    transfer_amount_debitor_is_set = true;
    transfer_amount_debitor_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>transfer_amount_debitor</em>カラムに値を設定します。 
   */
  public final void setTransferAmountDebitor( Integer p_transferAmountDebitor )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    transfer_amount_debitor = p_transferAmountDebitor;
    transfer_amount_debitor_is_set = true;
    transfer_amount_debitor_is_modified = true;
  }


  /** 
   * <em>transfer_amount_creditor</em>カラムの値を設定します。 
   *
   * @@param p_transferAmountCreditor <em>transfer_amount_creditor</em>カラムの値をあらわす9桁以下のint値 
   */
  public final void setTransferAmountCreditor( int p_transferAmountCreditor )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transfer_amount_creditor = new Integer(p_transferAmountCreditor);
    transfer_amount_creditor_is_set = true;
    transfer_amount_creditor_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>transfer_amount_creditor</em>カラムに値を設定します。 
   */
  public final void setTransferAmountCreditor( Integer p_transferAmountCreditor )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    transfer_amount_creditor = p_transferAmountCreditor;
    transfer_amount_creditor_is_set = true;
    transfer_amount_creditor_is_modified = true;
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
   * <em>involuntary_transfer</em>カラムの値を設定します。 
   *
   * @@param p_involuntaryTransfer <em>involuntary_transfer</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setInvoluntaryTransfer( String p_involuntaryTransfer )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    involuntary_transfer = p_involuntaryTransfer;
    involuntary_transfer_is_set = true;
    involuntary_transfer_is_modified = true;
  }


  /** 
   * <em>original_transfer_date</em>カラムの値を設定します。 
   *
   * @@param p_originalTransferDate <em>original_transfer_date</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setOriginalTransferDate( String p_originalTransferDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    original_transfer_date = p_originalTransferDate;
    original_transfer_date_is_set = true;
    original_transfer_date_is_modified = true;
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
   * <em>transfer_date</em>カラムの値を設定します。 
   *
   * @@param p_transferDate <em>transfer_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setTransferDate( java.sql.Timestamp p_transferDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transfer_date = p_transferDate;
    transfer_date_is_set = true;
    transfer_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>transfer_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setTransferDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    transfer_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    transfer_date_is_set = true;
    transfer_date_is_modified = true;
  }


  /** 
   * <em>transfer_time</em>カラムの値を設定します。 
   *
   * @@param p_transferTime <em>transfer_time</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setTransferTime( java.sql.Timestamp p_transferTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transfer_time = p_transferTime;
    transfer_time_is_set = true;
    transfer_time_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>transfer_time</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setTransferTime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    transfer_time = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    transfer_time_is_set = true;
    transfer_time_is_modified = true;
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
                if ( name.equals("cancel_div") ) {
                    return this.cancel_div;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("involuntary_transfer") ) {
                    return this.involuntary_transfer;
                }
                break;
            case 'o':
                if ( name.equals("original_transfer_date") ) {
                    return this.original_transfer_date;
                }
                else if ( name.equals("order_request_number") ) {
                    return this.order_request_number;
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
                break;
            case 's':
                if ( name.equals("status") ) {
                    return this.status;
                }
                break;
            case 't':
                if ( name.equals("trader_code") ) {
                    return this.trader_code;
                }
                else if ( name.equals("transfer_amount_debitor") ) {
                    return this.transfer_amount_debitor;
                }
                else if ( name.equals("transfer_amount_creditor") ) {
                    return this.transfer_amount_creditor;
                }
                else if ( name.equals("transfer_date") ) {
                    return this.transfer_date;
                }
                else if ( name.equals("transfer_time") ) {
                    return this.transfer_time;
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
                if ( name.equals("cancel_div") ) {
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
                else if ( name.equals("involuntary_transfer") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'involuntary_transfer' must be String: '"+value+"' is not." );
                    this.involuntary_transfer = (String) value;
                    if (this.involuntary_transfer_is_set)
                        this.involuntary_transfer_is_modified = true;
                    this.involuntary_transfer_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("original_transfer_date") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'original_transfer_date' must be String: '"+value+"' is not." );
                    this.original_transfer_date = (String) value;
                    if (this.original_transfer_date_is_set)
                        this.original_transfer_date_is_modified = true;
                    this.original_transfer_date_is_set = true;
                    return;
                }
                else if ( name.equals("order_request_number") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_request_number' must be String: '"+value+"' is not." );
                    this.order_request_number = (String) value;
                    if (this.order_request_number_is_set)
                        this.order_request_number_is_modified = true;
                    this.order_request_number_is_set = true;
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
            case 's':
                if ( name.equals("status") ) {
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
                else if ( name.equals("transfer_amount_debitor") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'transfer_amount_debitor' must be Integer: '"+value+"' is not." );
                    this.transfer_amount_debitor = (Integer) value;
                    if (this.transfer_amount_debitor_is_set)
                        this.transfer_amount_debitor_is_modified = true;
                    this.transfer_amount_debitor_is_set = true;
                    return;
                }
                else if ( name.equals("transfer_amount_creditor") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'transfer_amount_creditor' must be Integer: '"+value+"' is not." );
                    this.transfer_amount_creditor = (Integer) value;
                    if (this.transfer_amount_creditor_is_set)
                        this.transfer_amount_creditor_is_modified = true;
                    this.transfer_amount_creditor_is_set = true;
                    return;
                }
                else if ( name.equals("transfer_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'transfer_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.transfer_date = (java.sql.Timestamp) value;
                    if (this.transfer_date_is_set)
                        this.transfer_date_is_modified = true;
                    this.transfer_date_is_set = true;
                    return;
                }
                else if ( name.equals("transfer_time") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'transfer_time' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.transfer_time = (java.sql.Timestamp) value;
                    if (this.transfer_time_is_set)
                        this.transfer_time_is_modified = true;
                    this.transfer_time_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
