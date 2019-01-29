head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.38.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	HostMrgsecTransferParams.java;


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
 * host_mrgsec_transferテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link HostMrgsecTransferRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link HostMrgsecTransferParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link HostMrgsecTransferParams}が{@@link HostMrgsecTransferRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see HostMrgsecTransferPK 
 * @@see HostMrgsecTransferRow 
 */
public class HostMrgsecTransferParams extends Params implements HostMrgsecTransferRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "host_mrgsec_transfer";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = HostMrgsecTransferRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return HostMrgsecTransferRow.TYPE;
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
   * <em>trader_code</em>カラムの値 
   */
  public  String  trader_code;

  /** 
   * <em>transfer_flag</em>カラムの値 
   */
  public  String  transfer_flag;

  /** 
   * <em>original_transfer_date</em>カラムの値 
   */
  public  String  original_transfer_date;

  /** 
   * <em>cancel_flag</em>カラムの値 
   */
  public  String  cancel_flag;

  /** 
   * <em>commodity</em>カラムの値 
   */
  public  String  commodity;

  /** 
   * <em>product_code</em>カラムの値 
   */
  public  String  product_code;

  /** 
   * <em>quantity</em>カラムの値 
   */
  public  Long  quantity;

  /** 
   * <em>price</em>カラムの値 
   */
  public  Integer  price;

  /** 
   * <em>deliv_type</em>カラムの値 
   */
  public  String  deliv_type;

  /** 
   * <em>order_request_number</em>カラムの値 
   */
  public  String  order_request_number;

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

  /** 
   * <em>specific_account_div</em>カラムの値 
   */
  public  String  specific_account_div;

  /** 
   * <em>interlock</em>カラムの値 
   */
  public  String  interlock;

  /** 
   * <em>deposit</em>カラムの値 
   */
  public  String  deposit;

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
  private boolean transfer_flag_is_set = false;
  private boolean transfer_flag_is_modified = false;
  private boolean original_transfer_date_is_set = false;
  private boolean original_transfer_date_is_modified = false;
  private boolean cancel_flag_is_set = false;
  private boolean cancel_flag_is_modified = false;
  private boolean commodity_is_set = false;
  private boolean commodity_is_modified = false;
  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;
  private boolean quantity_is_set = false;
  private boolean quantity_is_modified = false;
  private boolean price_is_set = false;
  private boolean price_is_modified = false;
  private boolean deliv_type_is_set = false;
  private boolean deliv_type_is_modified = false;
  private boolean order_request_number_is_set = false;
  private boolean order_request_number_is_modified = false;
  private boolean transfer_date_is_set = false;
  private boolean transfer_date_is_modified = false;
  private boolean transfer_time_is_set = false;
  private boolean transfer_time_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean specific_account_div_is_set = false;
  private boolean specific_account_div_is_modified = false;
  private boolean interlock_is_set = false;
  private boolean interlock_is_modified = false;
  private boolean deposit_is_set = false;
  private boolean deposit_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "request_code=" +request_code
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "," + "trader_code=" +trader_code
      + "," + "transfer_flag=" +transfer_flag
      + "," + "original_transfer_date=" +original_transfer_date
      + "," + "cancel_flag=" +cancel_flag
      + "," + "commodity=" +commodity
      + "," + "product_code=" +product_code
      + "," + "quantity=" +quantity
      + "," + "price=" +price
      + "," + "deliv_type=" +deliv_type
      + "," + "order_request_number=" +order_request_number
      + "," + "transfer_date=" +transfer_date
      + "," + "transfer_time=" +transfer_time
      + "," + "status=" +status
      + "," + "specific_account_div=" +specific_account_div
      + "," + "interlock=" +interlock
      + "," + "deposit=" +deposit
      + "}";
  }


  /** 
   * 値が未設定のHostMrgsecTransferParamsオブジェクトを作成します。 
   */
  public HostMrgsecTransferParams() {
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のHostMrgsecTransferRowオブジェクトの値を利用してHostMrgsecTransferParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するHostMrgsecTransferRowオブジェクト 
   */
  public HostMrgsecTransferParams( HostMrgsecTransferRow p_row )
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
    trader_code = p_row.getTraderCode();
    trader_code_is_set = p_row.getTraderCodeIsSet();
    trader_code_is_modified = p_row.getTraderCodeIsModified();
    transfer_flag = p_row.getTransferFlag();
    transfer_flag_is_set = p_row.getTransferFlagIsSet();
    transfer_flag_is_modified = p_row.getTransferFlagIsModified();
    original_transfer_date = p_row.getOriginalTransferDate();
    original_transfer_date_is_set = p_row.getOriginalTransferDateIsSet();
    original_transfer_date_is_modified = p_row.getOriginalTransferDateIsModified();
    cancel_flag = p_row.getCancelFlag();
    cancel_flag_is_set = p_row.getCancelFlagIsSet();
    cancel_flag_is_modified = p_row.getCancelFlagIsModified();
    commodity = p_row.getCommodity();
    commodity_is_set = p_row.getCommodityIsSet();
    commodity_is_modified = p_row.getCommodityIsModified();
    product_code = p_row.getProductCode();
    product_code_is_set = p_row.getProductCodeIsSet();
    product_code_is_modified = p_row.getProductCodeIsModified();
    if ( !p_row.getQuantityIsNull() )
      quantity = new Long( p_row.getQuantity() );
    quantity_is_set = p_row.getQuantityIsSet();
    quantity_is_modified = p_row.getQuantityIsModified();
    if ( !p_row.getPriceIsNull() )
      price = new Integer( p_row.getPrice() );
    price_is_set = p_row.getPriceIsSet();
    price_is_modified = p_row.getPriceIsModified();
    deliv_type = p_row.getDelivType();
    deliv_type_is_set = p_row.getDelivTypeIsSet();
    deliv_type_is_modified = p_row.getDelivTypeIsModified();
    order_request_number = p_row.getOrderRequestNumber();
    order_request_number_is_set = p_row.getOrderRequestNumberIsSet();
    order_request_number_is_modified = p_row.getOrderRequestNumberIsModified();
    transfer_date = p_row.getTransferDate();
    transfer_date_is_set = p_row.getTransferDateIsSet();
    transfer_date_is_modified = p_row.getTransferDateIsModified();
    transfer_time = p_row.getTransferTime();
    transfer_time_is_set = p_row.getTransferTimeIsSet();
    transfer_time_is_modified = p_row.getTransferTimeIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
    specific_account_div = p_row.getSpecificAccountDiv();
    specific_account_div_is_set = p_row.getSpecificAccountDivIsSet();
    specific_account_div_is_modified = p_row.getSpecificAccountDivIsModified();
    interlock = p_row.getInterlock();
    interlock_is_set = p_row.getInterlockIsSet();
    interlock_is_modified = p_row.getInterlockIsModified();
    deposit = p_row.getDeposit();
    deposit_is_set = p_row.getDepositIsSet();
    deposit_is_modified = p_row.getDepositIsModified();
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
      transfer_flag_is_set = true;
      transfer_flag_is_modified = true;
      original_transfer_date_is_set = true;
      original_transfer_date_is_modified = true;
      cancel_flag_is_set = true;
      cancel_flag_is_modified = true;
      commodity_is_set = true;
      commodity_is_modified = true;
      product_code_is_set = true;
      product_code_is_modified = true;
      quantity_is_set = true;
      quantity_is_modified = true;
      price_is_set = true;
      price_is_modified = true;
      deliv_type_is_set = true;
      deliv_type_is_modified = true;
      order_request_number_is_set = true;
      order_request_number_is_modified = true;
      transfer_date_is_set = true;
      transfer_date_is_modified = true;
      transfer_time_is_set = true;
      transfer_time_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
      specific_account_div_is_set = true;
      specific_account_div_is_modified = true;
      interlock_is_set = true;
      interlock_is_modified = true;
      deposit_is_set = true;
      deposit_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof HostMrgsecTransferRow ) )
       return false;
    return fieldsEqual( (HostMrgsecTransferRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のHostMrgsecTransferRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( HostMrgsecTransferRow row )
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
    if ( transfer_flag == null ) {
      if ( row.getTransferFlag() != null )
        return false;
    } else if ( !transfer_flag.equals( row.getTransferFlag() ) ) {
        return false;
    }
    if ( original_transfer_date == null ) {
      if ( row.getOriginalTransferDate() != null )
        return false;
    } else if ( !original_transfer_date.equals( row.getOriginalTransferDate() ) ) {
        return false;
    }
    if ( cancel_flag == null ) {
      if ( row.getCancelFlag() != null )
        return false;
    } else if ( !cancel_flag.equals( row.getCancelFlag() ) ) {
        return false;
    }
    if ( commodity == null ) {
      if ( row.getCommodity() != null )
        return false;
    } else if ( !commodity.equals( row.getCommodity() ) ) {
        return false;
    }
    if ( product_code == null ) {
      if ( row.getProductCode() != null )
        return false;
    } else if ( !product_code.equals( row.getProductCode() ) ) {
        return false;
    }
    if ( quantity == null ) {
      if ( !row.getQuantityIsNull() )
        return false;
    } else if ( row.getQuantityIsNull() || ( quantity.longValue() != row.getQuantity() ) ) {
        return false;
    }
    if ( price == null ) {
      if ( !row.getPriceIsNull() )
        return false;
    } else if ( row.getPriceIsNull() || ( price.intValue() != row.getPrice() ) ) {
        return false;
    }
    if ( deliv_type == null ) {
      if ( row.getDelivType() != null )
        return false;
    } else if ( !deliv_type.equals( row.getDelivType() ) ) {
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
    if ( specific_account_div == null ) {
      if ( row.getSpecificAccountDiv() != null )
        return false;
    } else if ( !specific_account_div.equals( row.getSpecificAccountDiv() ) ) {
        return false;
    }
    if ( interlock == null ) {
      if ( row.getInterlock() != null )
        return false;
    } else if ( !interlock.equals( row.getInterlock() ) ) {
        return false;
    }
    if ( deposit == null ) {
      if ( row.getDeposit() != null )
        return false;
    } else if ( !deposit.equals( row.getDeposit() ) ) {
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
        + (transfer_flag!=null? transfer_flag.hashCode(): 0) 
        + (original_transfer_date!=null? original_transfer_date.hashCode(): 0) 
        + (cancel_flag!=null? cancel_flag.hashCode(): 0) 
        + (commodity!=null? commodity.hashCode(): 0) 
        + (product_code!=null? product_code.hashCode(): 0) 
        + (quantity!=null? quantity.hashCode(): 0) 
        + (price!=null? price.hashCode(): 0) 
        + (deliv_type!=null? deliv_type.hashCode(): 0) 
        + (order_request_number!=null? order_request_number.hashCode(): 0) 
        + (transfer_date!=null? transfer_date.hashCode(): 0) 
        + (transfer_time!=null? transfer_time.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
        + (specific_account_div!=null? specific_account_div.hashCode(): 0) 
        + (interlock!=null? interlock.hashCode(): 0) 
        + (deposit!=null? deposit.hashCode(): 0) 
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
		if ( !account_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_code' must be set before inserting.");
		if ( !transfer_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'transfer_flag' must be set before inserting.");
		if ( !commodity_is_set )
			throw new IllegalArgumentException("non-nullable field 'commodity' must be set before inserting.");
		if ( !deliv_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'deliv_type' must be set before inserting.");
		if ( !order_request_number_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_request_number' must be set before inserting.");
		if ( !status_is_set )
			throw new IllegalArgumentException("non-nullable field 'status' must be set before inserting.");
		if ( !specific_account_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'specific_account_div' must be set before inserting.");
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
		map.put("transfer_flag",transfer_flag);
		if ( original_transfer_date != null )
			map.put("original_transfer_date",original_transfer_date);
		if ( cancel_flag != null )
			map.put("cancel_flag",cancel_flag);
		map.put("commodity",commodity);
		if ( product_code != null )
			map.put("product_code",product_code);
		if ( quantity != null )
			map.put("quantity",quantity);
		if ( price != null )
			map.put("price",price);
		map.put("deliv_type",deliv_type);
		map.put("order_request_number",order_request_number);
		if ( transfer_date != null )
			map.put("transfer_date",transfer_date);
		if ( transfer_time != null )
			map.put("transfer_time",transfer_time);
		map.put("status",status);
		map.put("specific_account_div",specific_account_div);
		if ( interlock != null )
			map.put("interlock",interlock);
		if ( deposit != null )
			map.put("deposit",deposit);
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
		if ( transfer_flag_is_modified )
			map.put("transfer_flag",transfer_flag);
		if ( original_transfer_date_is_modified )
			map.put("original_transfer_date",original_transfer_date);
		if ( cancel_flag_is_modified )
			map.put("cancel_flag",cancel_flag);
		if ( commodity_is_modified )
			map.put("commodity",commodity);
		if ( product_code_is_modified )
			map.put("product_code",product_code);
		if ( quantity_is_modified )
			map.put("quantity",quantity);
		if ( price_is_modified )
			map.put("price",price);
		if ( deliv_type_is_modified )
			map.put("deliv_type",deliv_type);
		if ( order_request_number_is_modified )
			map.put("order_request_number",order_request_number);
		if ( transfer_date_is_modified )
			map.put("transfer_date",transfer_date);
		if ( transfer_time_is_modified )
			map.put("transfer_time",transfer_time);
		if ( status_is_modified )
			map.put("status",status);
		if ( specific_account_div_is_modified )
			map.put("specific_account_div",specific_account_div);
		if ( interlock_is_modified )
			map.put("interlock",interlock);
		if ( deposit_is_modified )
			map.put("deposit",deposit);
		if (map.size() == 0) {
			if ( request_code_is_set )
				map.put("request_code",request_code);
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( branch_code_is_set )
				map.put("branch_code",branch_code);
			if ( account_code_is_set )
				map.put("account_code",account_code);
			map.put("trader_code",trader_code);
			if ( transfer_flag_is_set )
				map.put("transfer_flag",transfer_flag);
			map.put("original_transfer_date",original_transfer_date);
			map.put("cancel_flag",cancel_flag);
			if ( commodity_is_set )
				map.put("commodity",commodity);
			map.put("product_code",product_code);
			map.put("quantity",quantity);
			map.put("price",price);
			if ( deliv_type_is_set )
				map.put("deliv_type",deliv_type);
			if ( order_request_number_is_set )
				map.put("order_request_number",order_request_number);
			map.put("transfer_date",transfer_date);
			map.put("transfer_time",transfer_time);
			if ( status_is_set )
				map.put("status",status);
			if ( specific_account_div_is_set )
				map.put("specific_account_div",specific_account_div);
			map.put("interlock",interlock);
			map.put("deposit",deposit);
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
   * <em>transfer_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTransferFlag()
  {
    return transfer_flag;
  }


  /** 
   * <em>transfer_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferFlagIsSet() {
    return transfer_flag_is_set;
  }


  /** 
   * <em>transfer_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferFlagIsModified() {
    return transfer_flag_is_modified;
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
   * <em>cancel_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCancelFlag()
  {
    return cancel_flag;
  }


  /** 
   * <em>cancel_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCancelFlagIsSet() {
    return cancel_flag_is_set;
  }


  /** 
   * <em>cancel_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCancelFlagIsModified() {
    return cancel_flag_is_modified;
  }


  /** 
   * <em>commodity</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCommodity()
  {
    return commodity;
  }


  /** 
   * <em>commodity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommodityIsSet() {
    return commodity_is_set;
  }


  /** 
   * <em>commodity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommodityIsModified() {
    return commodity_is_modified;
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
   * <em>price</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getPrice()
  {
    return ( price==null? ((int)0): price.intValue() );
  }


  /** 
   * <em>price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPriceIsNull()
  {
    return price == null;
  }


  /** 
   * <em>price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPriceIsSet() {
    return price_is_set;
  }


  /** 
   * <em>price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPriceIsModified() {
    return price_is_modified;
  }


  /** 
   * <em>deliv_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDelivType()
  {
    return deliv_type;
  }


  /** 
   * <em>deliv_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDelivTypeIsSet() {
    return deliv_type_is_set;
  }


  /** 
   * <em>deliv_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDelivTypeIsModified() {
    return deliv_type_is_modified;
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
   * <em>interlock</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInterlock()
  {
    return interlock;
  }


  /** 
   * <em>interlock</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterlockIsSet() {
    return interlock_is_set;
  }


  /** 
   * <em>interlock</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterlockIsModified() {
    return interlock_is_modified;
  }


  /** 
   * <em>deposit</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDeposit()
  {
    return deposit;
  }


  /** 
   * <em>deposit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDepositIsSet() {
    return deposit_is_set;
  }


  /** 
   * <em>deposit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDepositIsModified() {
    return deposit_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    throw new UnsupportedOperationException("Table host_mrgsec_transfer has no primary key.");
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
   * <em>transfer_flag</em>カラムの値を設定します。 
   *
   * @@param p_transferFlag <em>transfer_flag</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setTransferFlag( String p_transferFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transfer_flag = p_transferFlag;
    transfer_flag_is_set = true;
    transfer_flag_is_modified = true;
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
   * <em>cancel_flag</em>カラムの値を設定します。 
   *
   * @@param p_cancelFlag <em>cancel_flag</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCancelFlag( String p_cancelFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cancel_flag = p_cancelFlag;
    cancel_flag_is_set = true;
    cancel_flag_is_modified = true;
  }


  /** 
   * <em>commodity</em>カラムの値を設定します。 
   *
   * @@param p_commodity <em>commodity</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCommodity( String p_commodity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commodity = p_commodity;
    commodity_is_set = true;
    commodity_is_modified = true;
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
   * <em>quantity</em>カラムの値を設定します。 
   *
   * @@param p_quantity <em>quantity</em>カラムの値をあらわす12桁以下のlong値 
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
   * <em>price</em>カラムの値を設定します。 
   *
   * @@param p_price <em>price</em>カラムの値をあらわす9桁以下のint値 
   */
  public final void setPrice( int p_price )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    price = new Integer(p_price);
    price_is_set = true;
    price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>price</em>カラムに値を設定します。 
   */
  public final void setPrice( Integer p_price )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    price = p_price;
    price_is_set = true;
    price_is_modified = true;
  }


  /** 
   * <em>deliv_type</em>カラムの値を設定します。 
   *
   * @@param p_delivType <em>deliv_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDelivType( String p_delivType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    deliv_type = p_delivType;
    deliv_type_is_set = true;
    deliv_type_is_modified = true;
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
   * <em>interlock</em>カラムの値を設定します。 
   *
   * @@param p_interlock <em>interlock</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setInterlock( String p_interlock )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    interlock = p_interlock;
    interlock_is_set = true;
    interlock_is_modified = true;
  }


  /** 
   * <em>deposit</em>カラムの値を設定します。 
   *
   * @@param p_deposit <em>deposit</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDeposit( String p_deposit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    deposit = p_deposit;
    deposit_is_set = true;
    deposit_is_modified = true;
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
                if ( name.equals("cancel_flag") ) {
                    return this.cancel_flag;
                }
                else if ( name.equals("commodity") ) {
                    return this.commodity;
                }
                break;
            case 'd':
                if ( name.equals("deliv_type") ) {
                    return this.deliv_type;
                }
                else if ( name.equals("deposit") ) {
                    return this.deposit;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("interlock") ) {
                    return this.interlock;
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
            case 'p':
                if ( name.equals("product_code") ) {
                    return this.product_code;
                }
                else if ( name.equals("price") ) {
                    return this.price;
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
                break;
            case 's':
                if ( name.equals("status") ) {
                    return this.status;
                }
                else if ( name.equals("specific_account_div") ) {
                    return this.specific_account_div;
                }
                break;
            case 't':
                if ( name.equals("trader_code") ) {
                    return this.trader_code;
                }
                else if ( name.equals("transfer_flag") ) {
                    return this.transfer_flag;
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
                if ( name.equals("cancel_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cancel_flag' must be String: '"+value+"' is not." );
                    this.cancel_flag = (String) value;
                    if (this.cancel_flag_is_set)
                        this.cancel_flag_is_modified = true;
                    this.cancel_flag_is_set = true;
                    return;
                }
                else if ( name.equals("commodity") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'commodity' must be String: '"+value+"' is not." );
                    this.commodity = (String) value;
                    if (this.commodity_is_set)
                        this.commodity_is_modified = true;
                    this.commodity_is_set = true;
                    return;
                }
                break;
            case 'd':
                if ( name.equals("deliv_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'deliv_type' must be String: '"+value+"' is not." );
                    this.deliv_type = (String) value;
                    if (this.deliv_type_is_set)
                        this.deliv_type_is_modified = true;
                    this.deliv_type_is_set = true;
                    return;
                }
                else if ( name.equals("deposit") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'deposit' must be String: '"+value+"' is not." );
                    this.deposit = (String) value;
                    if (this.deposit_is_set)
                        this.deposit_is_modified = true;
                    this.deposit_is_set = true;
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
                else if ( name.equals("interlock") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'interlock' must be String: '"+value+"' is not." );
                    this.interlock = (String) value;
                    if (this.interlock_is_set)
                        this.interlock_is_modified = true;
                    this.interlock_is_set = true;
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
                else if ( name.equals("price") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'price' must be Integer: '"+value+"' is not." );
                    this.price = (Integer) value;
                    if (this.price_is_set)
                        this.price_is_modified = true;
                    this.price_is_set = true;
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
                else if ( name.equals("specific_account_div") ) {
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
                else if ( name.equals("transfer_flag") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'transfer_flag' must be String: '"+value+"' is not." );
                    this.transfer_flag = (String) value;
                    if (this.transfer_flag_is_set)
                        this.transfer_flag_is_modified = true;
                    this.transfer_flag_is_set = true;
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
