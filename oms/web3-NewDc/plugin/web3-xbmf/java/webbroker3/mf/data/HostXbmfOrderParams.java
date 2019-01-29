head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	HostXbmfOrderParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * host_xbmf_orderテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link HostXbmfOrderRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link HostXbmfOrderParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link HostXbmfOrderParams}が{@@link HostXbmfOrderRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see HostXbmfOrderPK 
 * @@see HostXbmfOrderRow 
 */
public class HostXbmfOrderParams extends Params implements HostXbmfOrderRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "host_xbmf_order";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = HostXbmfOrderRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return HostXbmfOrderRow.TYPE;
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
   * <em>order_request_number</em>カラムの値 
   */
  public  String  order_request_number;

  /** 
   * <em>request_code</em>カラムの値 
   */
  public  String  request_code;

  /** 
   * <em>account_code</em>カラムの値 
   */
  public  String  account_code;

  /** 
   * <em>trader_code</em>カラムの値 
   */
  public  String  trader_code;

  /** 
   * <em>product_code</em>カラムの値 
   */
  public  String  product_code;

  /** 
   * <em>specify_div</em>カラムの値 
   */
  public  String  specify_div;

  /** 
   * <em>settlement_div</em>カラムの値 
   */
  public  String  settlement_div;

  /** 
   * <em>sell_order_quantity</em>カラムの値 
   */
  public  Long  sell_order_quantity;

  /** 
   * <em>buy_order_quantity</em>カラムの値 
   */
  public  Long  buy_order_quantity;

  /** 
   * <em>ticket_number</em>カラムの値 
   */
  public  Integer  ticket_number;

  /** 
   * <em>pr_div</em>カラムの値 
   */
  public  String  pr_div;

  /** 
   * <em>commission_div</em>カラムの値 
   */
  public  String  commission_div;

  /** 
   * <em>deposit_check_div</em>カラムの値 
   */
  public  String  deposit_check_div;

  /** 
   * <em>create_date</em>カラムの値 
   */
  public  java.sql.Timestamp  create_date;

  /** 
   * <em>create_time</em>カラムの値 
   */
  public  java.sql.Timestamp  create_time;

  /** 
   * <em>order_date</em>カラムの値 
   */
  public  java.sql.Timestamp  order_date;

  /** 
   * <em>swt_div</em>カラムの値 
   */
  public  String  swt_div;

  /** 
   * <em>swt_product_code</em>カラムの値 
   */
  public  String  swt_product_code;

  /** 
   * <em>tax_type</em>カラムの値 
   */
  public  String  tax_type;

  /** 
   * <em>swt_tax_type</em>カラムの値 
   */
  public  String  swt_tax_type;

  /** 
   * <em>claim_div</em>カラムの値 
   */
  public  String  claim_div;

  /** 
   * <em>order_chanel</em>カラムの値 
   */
  public  String  order_chanel;

  /** 
   * <em>status</em>カラムの値 
   */
  public  String  status;

  /** 
   * <em>payment_date</em>カラムの値 
   */
  public  java.sql.Timestamp  payment_date;

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
  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;
  private boolean specify_div_is_set = false;
  private boolean specify_div_is_modified = false;
  private boolean settlement_div_is_set = false;
  private boolean settlement_div_is_modified = false;
  private boolean sell_order_quantity_is_set = false;
  private boolean sell_order_quantity_is_modified = false;
  private boolean buy_order_quantity_is_set = false;
  private boolean buy_order_quantity_is_modified = false;
  private boolean ticket_number_is_set = false;
  private boolean ticket_number_is_modified = false;
  private boolean pr_div_is_set = false;
  private boolean pr_div_is_modified = false;
  private boolean commission_div_is_set = false;
  private boolean commission_div_is_modified = false;
  private boolean deposit_check_div_is_set = false;
  private boolean deposit_check_div_is_modified = false;
  private boolean order_request_number_is_set = false;
  private boolean order_request_number_is_modified = false;
  private boolean create_date_is_set = false;
  private boolean create_date_is_modified = false;
  private boolean create_time_is_set = false;
  private boolean create_time_is_modified = false;
  private boolean order_date_is_set = false;
  private boolean order_date_is_modified = false;
  private boolean swt_div_is_set = false;
  private boolean swt_div_is_modified = false;
  private boolean swt_product_code_is_set = false;
  private boolean swt_product_code_is_modified = false;
  private boolean tax_type_is_set = false;
  private boolean tax_type_is_modified = false;
  private boolean swt_tax_type_is_set = false;
  private boolean swt_tax_type_is_modified = false;
  private boolean claim_div_is_set = false;
  private boolean claim_div_is_modified = false;
  private boolean order_chanel_is_set = false;
  private boolean order_chanel_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean payment_date_is_set = false;
  private boolean payment_date_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "branch_code=" + branch_code
      + "," + "order_request_number=" + order_request_number
      + "," + "request_code=" +request_code
      + "," + "account_code=" +account_code
      + "," + "trader_code=" +trader_code
      + "," + "product_code=" +product_code
      + "," + "specify_div=" +specify_div
      + "," + "settlement_div=" +settlement_div
      + "," + "sell_order_quantity=" +sell_order_quantity
      + "," + "buy_order_quantity=" +buy_order_quantity
      + "," + "ticket_number=" +ticket_number
      + "," + "pr_div=" +pr_div
      + "," + "commission_div=" +commission_div
      + "," + "deposit_check_div=" +deposit_check_div
      + "," + "create_date=" +create_date
      + "," + "create_time=" +create_time
      + "," + "order_date=" +order_date
      + "," + "swt_div=" +swt_div
      + "," + "swt_product_code=" +swt_product_code
      + "," + "tax_type=" +tax_type
      + "," + "swt_tax_type=" +swt_tax_type
      + "," + "claim_div=" +claim_div
      + "," + "order_chanel=" +order_chanel
      + "," + "status=" +status
      + "," + "payment_date=" +payment_date
      + "}";
  }


  /** 
   * 値が未設定のHostXbmfOrderParamsオブジェクトを作成します。 
   */
  public HostXbmfOrderParams() {
    institution_code_is_modified = true;
    branch_code_is_modified = true;
    order_request_number_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のHostXbmfOrderRowオブジェクトの値を利用してHostXbmfOrderParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するHostXbmfOrderRowオブジェクト 
   */
  public HostXbmfOrderParams( HostXbmfOrderRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    order_request_number = p_row.getOrderRequestNumber();
    order_request_number_is_set = p_row.getOrderRequestNumberIsSet();
    order_request_number_is_modified = p_row.getOrderRequestNumberIsModified();
    request_code = p_row.getRequestCode();
    request_code_is_set = p_row.getRequestCodeIsSet();
    request_code_is_modified = p_row.getRequestCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    trader_code = p_row.getTraderCode();
    trader_code_is_set = p_row.getTraderCodeIsSet();
    trader_code_is_modified = p_row.getTraderCodeIsModified();
    product_code = p_row.getProductCode();
    product_code_is_set = p_row.getProductCodeIsSet();
    product_code_is_modified = p_row.getProductCodeIsModified();
    specify_div = p_row.getSpecifyDiv();
    specify_div_is_set = p_row.getSpecifyDivIsSet();
    specify_div_is_modified = p_row.getSpecifyDivIsModified();
    settlement_div = p_row.getSettlementDiv();
    settlement_div_is_set = p_row.getSettlementDivIsSet();
    settlement_div_is_modified = p_row.getSettlementDivIsModified();
    if ( !p_row.getSellOrderQuantityIsNull() )
      sell_order_quantity = new Long( p_row.getSellOrderQuantity() );
    sell_order_quantity_is_set = p_row.getSellOrderQuantityIsSet();
    sell_order_quantity_is_modified = p_row.getSellOrderQuantityIsModified();
    if ( !p_row.getBuyOrderQuantityIsNull() )
      buy_order_quantity = new Long( p_row.getBuyOrderQuantity() );
    buy_order_quantity_is_set = p_row.getBuyOrderQuantityIsSet();
    buy_order_quantity_is_modified = p_row.getBuyOrderQuantityIsModified();
    if ( !p_row.getTicketNumberIsNull() )
      ticket_number = new Integer( p_row.getTicketNumber() );
    ticket_number_is_set = p_row.getTicketNumberIsSet();
    ticket_number_is_modified = p_row.getTicketNumberIsModified();
    pr_div = p_row.getPrDiv();
    pr_div_is_set = p_row.getPrDivIsSet();
    pr_div_is_modified = p_row.getPrDivIsModified();
    commission_div = p_row.getCommissionDiv();
    commission_div_is_set = p_row.getCommissionDivIsSet();
    commission_div_is_modified = p_row.getCommissionDivIsModified();
    deposit_check_div = p_row.getDepositCheckDiv();
    deposit_check_div_is_set = p_row.getDepositCheckDivIsSet();
    deposit_check_div_is_modified = p_row.getDepositCheckDivIsModified();
    create_date = p_row.getCreateDate();
    create_date_is_set = p_row.getCreateDateIsSet();
    create_date_is_modified = p_row.getCreateDateIsModified();
    create_time = p_row.getCreateTime();
    create_time_is_set = p_row.getCreateTimeIsSet();
    create_time_is_modified = p_row.getCreateTimeIsModified();
    order_date = p_row.getOrderDate();
    order_date_is_set = p_row.getOrderDateIsSet();
    order_date_is_modified = p_row.getOrderDateIsModified();
    swt_div = p_row.getSwtDiv();
    swt_div_is_set = p_row.getSwtDivIsSet();
    swt_div_is_modified = p_row.getSwtDivIsModified();
    swt_product_code = p_row.getSwtProductCode();
    swt_product_code_is_set = p_row.getSwtProductCodeIsSet();
    swt_product_code_is_modified = p_row.getSwtProductCodeIsModified();
    tax_type = p_row.getTaxType();
    tax_type_is_set = p_row.getTaxTypeIsSet();
    tax_type_is_modified = p_row.getTaxTypeIsModified();
    swt_tax_type = p_row.getSwtTaxType();
    swt_tax_type_is_set = p_row.getSwtTaxTypeIsSet();
    swt_tax_type_is_modified = p_row.getSwtTaxTypeIsModified();
    claim_div = p_row.getClaimDiv();
    claim_div_is_set = p_row.getClaimDivIsSet();
    claim_div_is_modified = p_row.getClaimDivIsModified();
    order_chanel = p_row.getOrderChanel();
    order_chanel_is_set = p_row.getOrderChanelIsSet();
    order_chanel_is_modified = p_row.getOrderChanelIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
    payment_date = p_row.getPaymentDate();
    payment_date_is_set = p_row.getPaymentDateIsSet();
    payment_date_is_modified = p_row.getPaymentDateIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      request_code_is_set = true;
      request_code_is_modified = true;
      account_code_is_set = true;
      account_code_is_modified = true;
      trader_code_is_set = true;
      trader_code_is_modified = true;
      product_code_is_set = true;
      product_code_is_modified = true;
      specify_div_is_set = true;
      specify_div_is_modified = true;
      settlement_div_is_set = true;
      settlement_div_is_modified = true;
      sell_order_quantity_is_set = true;
      sell_order_quantity_is_modified = true;
      buy_order_quantity_is_set = true;
      buy_order_quantity_is_modified = true;
      ticket_number_is_set = true;
      ticket_number_is_modified = true;
      pr_div_is_set = true;
      pr_div_is_modified = true;
      commission_div_is_set = true;
      commission_div_is_modified = true;
      deposit_check_div_is_set = true;
      deposit_check_div_is_modified = true;
      create_date_is_set = true;
      create_date_is_modified = true;
      create_time_is_set = true;
      create_time_is_modified = true;
      order_date_is_set = true;
      order_date_is_modified = true;
      swt_div_is_set = true;
      swt_div_is_modified = true;
      swt_product_code_is_set = true;
      swt_product_code_is_modified = true;
      tax_type_is_set = true;
      tax_type_is_modified = true;
      swt_tax_type_is_set = true;
      swt_tax_type_is_modified = true;
      claim_div_is_set = true;
      claim_div_is_modified = true;
      order_chanel_is_set = true;
      order_chanel_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
      payment_date_is_set = true;
      payment_date_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof HostXbmfOrderRow ) )
       return false;
    return fieldsEqual( (HostXbmfOrderRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のHostXbmfOrderRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( HostXbmfOrderRow row )
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
    if ( product_code == null ) {
      if ( row.getProductCode() != null )
        return false;
    } else if ( !product_code.equals( row.getProductCode() ) ) {
        return false;
    }
    if ( specify_div == null ) {
      if ( row.getSpecifyDiv() != null )
        return false;
    } else if ( !specify_div.equals( row.getSpecifyDiv() ) ) {
        return false;
    }
    if ( settlement_div == null ) {
      if ( row.getSettlementDiv() != null )
        return false;
    } else if ( !settlement_div.equals( row.getSettlementDiv() ) ) {
        return false;
    }
    if ( sell_order_quantity == null ) {
      if ( !row.getSellOrderQuantityIsNull() )
        return false;
    } else if ( row.getSellOrderQuantityIsNull() || ( sell_order_quantity.longValue() != row.getSellOrderQuantity() ) ) {
        return false;
    }
    if ( buy_order_quantity == null ) {
      if ( !row.getBuyOrderQuantityIsNull() )
        return false;
    } else if ( row.getBuyOrderQuantityIsNull() || ( buy_order_quantity.longValue() != row.getBuyOrderQuantity() ) ) {
        return false;
    }
    if ( ticket_number == null ) {
      if ( !row.getTicketNumberIsNull() )
        return false;
    } else if ( row.getTicketNumberIsNull() || ( ticket_number.intValue() != row.getTicketNumber() ) ) {
        return false;
    }
    if ( pr_div == null ) {
      if ( row.getPrDiv() != null )
        return false;
    } else if ( !pr_div.equals( row.getPrDiv() ) ) {
        return false;
    }
    if ( commission_div == null ) {
      if ( row.getCommissionDiv() != null )
        return false;
    } else if ( !commission_div.equals( row.getCommissionDiv() ) ) {
        return false;
    }
    if ( deposit_check_div == null ) {
      if ( row.getDepositCheckDiv() != null )
        return false;
    } else if ( !deposit_check_div.equals( row.getDepositCheckDiv() ) ) {
        return false;
    }
    if ( order_request_number == null ) {
      if ( row.getOrderRequestNumber() != null )
        return false;
    } else if ( !order_request_number.equals( row.getOrderRequestNumber() ) ) {
        return false;
    }
    if ( create_date == null ) {
      if ( row.getCreateDate() != null )
        return false;
    } else if ( !create_date.equals( row.getCreateDate() ) ) {
        return false;
    }
    if ( create_time == null ) {
      if ( row.getCreateTime() != null )
        return false;
    } else if ( !create_time.equals( row.getCreateTime() ) ) {
        return false;
    }
    if ( order_date == null ) {
      if ( row.getOrderDate() != null )
        return false;
    } else if ( !order_date.equals( row.getOrderDate() ) ) {
        return false;
    }
    if ( swt_div == null ) {
      if ( row.getSwtDiv() != null )
        return false;
    } else if ( !swt_div.equals( row.getSwtDiv() ) ) {
        return false;
    }
    if ( swt_product_code == null ) {
      if ( row.getSwtProductCode() != null )
        return false;
    } else if ( !swt_product_code.equals( row.getSwtProductCode() ) ) {
        return false;
    }
    if ( tax_type == null ) {
      if ( row.getTaxType() != null )
        return false;
    } else if ( !tax_type.equals( row.getTaxType() ) ) {
        return false;
    }
    if ( swt_tax_type == null ) {
      if ( row.getSwtTaxType() != null )
        return false;
    } else if ( !swt_tax_type.equals( row.getSwtTaxType() ) ) {
        return false;
    }
    if ( claim_div == null ) {
      if ( row.getClaimDiv() != null )
        return false;
    } else if ( !claim_div.equals( row.getClaimDiv() ) ) {
        return false;
    }
    if ( order_chanel == null ) {
      if ( row.getOrderChanel() != null )
        return false;
    } else if ( !order_chanel.equals( row.getOrderChanel() ) ) {
        return false;
    }
    if ( status == null ) {
      if ( row.getStatus() != null )
        return false;
    } else if ( !status.equals( row.getStatus() ) ) {
        return false;
    }
    if ( payment_date == null ) {
      if ( row.getPaymentDate() != null )
        return false;
    } else if ( !payment_date.equals( row.getPaymentDate() ) ) {
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
        + (product_code!=null? product_code.hashCode(): 0) 
        + (specify_div!=null? specify_div.hashCode(): 0) 
        + (settlement_div!=null? settlement_div.hashCode(): 0) 
        + (sell_order_quantity!=null? sell_order_quantity.hashCode(): 0) 
        + (buy_order_quantity!=null? buy_order_quantity.hashCode(): 0) 
        + (ticket_number!=null? ticket_number.hashCode(): 0) 
        + (pr_div!=null? pr_div.hashCode(): 0) 
        + (commission_div!=null? commission_div.hashCode(): 0) 
        + (deposit_check_div!=null? deposit_check_div.hashCode(): 0) 
        + (order_request_number!=null? order_request_number.hashCode(): 0) 
        + (create_date!=null? create_date.hashCode(): 0) 
        + (create_time!=null? create_time.hashCode(): 0) 
        + (order_date!=null? order_date.hashCode(): 0) 
        + (swt_div!=null? swt_div.hashCode(): 0) 
        + (swt_product_code!=null? swt_product_code.hashCode(): 0) 
        + (tax_type!=null? tax_type.hashCode(): 0) 
        + (swt_tax_type!=null? swt_tax_type.hashCode(): 0) 
        + (claim_div!=null? claim_div.hashCode(): 0) 
        + (order_chanel!=null? order_chanel.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
        + (payment_date!=null? payment_date.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !request_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'request_code' must be set before inserting.");
		if ( !account_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_code' must be set before inserting.");
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
		if ( product_code != null )
			map.put("product_code",product_code);
		if ( specify_div != null )
			map.put("specify_div",specify_div);
		if ( settlement_div != null )
			map.put("settlement_div",settlement_div);
		if ( sell_order_quantity != null )
			map.put("sell_order_quantity",sell_order_quantity);
		if ( buy_order_quantity != null )
			map.put("buy_order_quantity",buy_order_quantity);
		if ( ticket_number != null )
			map.put("ticket_number",ticket_number);
		if ( pr_div != null )
			map.put("pr_div",pr_div);
		if ( commission_div != null )
			map.put("commission_div",commission_div);
		if ( deposit_check_div != null )
			map.put("deposit_check_div",deposit_check_div);
		map.put("order_request_number",order_request_number);
		if ( create_date != null )
			map.put("create_date",create_date);
		if ( create_time != null )
			map.put("create_time",create_time);
		if ( order_date != null )
			map.put("order_date",order_date);
		if ( swt_div != null )
			map.put("swt_div",swt_div);
		if ( swt_product_code != null )
			map.put("swt_product_code",swt_product_code);
		if ( tax_type != null )
			map.put("tax_type",tax_type);
		if ( swt_tax_type != null )
			map.put("swt_tax_type",swt_tax_type);
		if ( claim_div != null )
			map.put("claim_div",claim_div);
		if ( order_chanel != null )
			map.put("order_chanel",order_chanel);
		if ( status != null )
			map.put("status",status);
		if ( payment_date != null )
			map.put("payment_date",payment_date);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( request_code_is_modified )
			map.put("request_code",request_code);
		if ( account_code_is_modified )
			map.put("account_code",account_code);
		if ( trader_code_is_modified )
			map.put("trader_code",trader_code);
		if ( product_code_is_modified )
			map.put("product_code",product_code);
		if ( specify_div_is_modified )
			map.put("specify_div",specify_div);
		if ( settlement_div_is_modified )
			map.put("settlement_div",settlement_div);
		if ( sell_order_quantity_is_modified )
			map.put("sell_order_quantity",sell_order_quantity);
		if ( buy_order_quantity_is_modified )
			map.put("buy_order_quantity",buy_order_quantity);
		if ( ticket_number_is_modified )
			map.put("ticket_number",ticket_number);
		if ( pr_div_is_modified )
			map.put("pr_div",pr_div);
		if ( commission_div_is_modified )
			map.put("commission_div",commission_div);
		if ( deposit_check_div_is_modified )
			map.put("deposit_check_div",deposit_check_div);
		if ( create_date_is_modified )
			map.put("create_date",create_date);
		if ( create_time_is_modified )
			map.put("create_time",create_time);
		if ( order_date_is_modified )
			map.put("order_date",order_date);
		if ( swt_div_is_modified )
			map.put("swt_div",swt_div);
		if ( swt_product_code_is_modified )
			map.put("swt_product_code",swt_product_code);
		if ( tax_type_is_modified )
			map.put("tax_type",tax_type);
		if ( swt_tax_type_is_modified )
			map.put("swt_tax_type",swt_tax_type);
		if ( claim_div_is_modified )
			map.put("claim_div",claim_div);
		if ( order_chanel_is_modified )
			map.put("order_chanel",order_chanel);
		if ( status_is_modified )
			map.put("status",status);
		if ( payment_date_is_modified )
			map.put("payment_date",payment_date);
		if (map.size() == 0) {
			if ( request_code_is_set )
				map.put("request_code",request_code);
			if ( account_code_is_set )
				map.put("account_code",account_code);
			map.put("trader_code",trader_code);
			map.put("product_code",product_code);
			map.put("specify_div",specify_div);
			map.put("settlement_div",settlement_div);
			map.put("sell_order_quantity",sell_order_quantity);
			map.put("buy_order_quantity",buy_order_quantity);
			map.put("ticket_number",ticket_number);
			map.put("pr_div",pr_div);
			map.put("commission_div",commission_div);
			map.put("deposit_check_div",deposit_check_div);
			map.put("create_date",create_date);
			map.put("create_time",create_time);
			map.put("order_date",order_date);
			map.put("swt_div",swt_div);
			map.put("swt_product_code",swt_product_code);
			map.put("tax_type",tax_type);
			map.put("swt_tax_type",swt_tax_type);
			map.put("claim_div",claim_div);
			map.put("order_chanel",order_chanel);
			map.put("status",status);
			map.put("payment_date",payment_date);
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
   * <em>specify_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSpecifyDiv()
  {
    return specify_div;
  }


  /** 
   * <em>specify_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecifyDivIsSet() {
    return specify_div_is_set;
  }


  /** 
   * <em>specify_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecifyDivIsModified() {
    return specify_div_is_modified;
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
   * <em>sell_order_quantity</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getSellOrderQuantity()
  {
    return ( sell_order_quantity==null? ((long)0): sell_order_quantity.longValue() );
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
   * @@return longの値 
   */
  public final long getBuyOrderQuantity()
  {
    return ( buy_order_quantity==null? ((long)0): buy_order_quantity.longValue() );
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
   * <em>ticket_number</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getTicketNumber()
  {
    return ( ticket_number==null? ((int)0): ticket_number.intValue() );
  }


  /** 
   * <em>ticket_number</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTicketNumberIsNull()
  {
    return ticket_number == null;
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
   * <em>pr_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPrDiv()
  {
    return pr_div;
  }


  /** 
   * <em>pr_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPrDivIsSet() {
    return pr_div_is_set;
  }


  /** 
   * <em>pr_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPrDivIsModified() {
    return pr_div_is_modified;
  }


  /** 
   * <em>commission_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCommissionDiv()
  {
    return commission_div;
  }


  /** 
   * <em>commission_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionDivIsSet() {
    return commission_div_is_set;
  }


  /** 
   * <em>commission_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionDivIsModified() {
    return commission_div_is_modified;
  }


  /** 
   * <em>deposit_check_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDepositCheckDiv()
  {
    return deposit_check_div;
  }


  /** 
   * <em>deposit_check_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDepositCheckDivIsSet() {
    return deposit_check_div_is_set;
  }


  /** 
   * <em>deposit_check_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDepositCheckDivIsModified() {
    return deposit_check_div_is_modified;
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
   * <em>create_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getCreateDate()
  {
    return create_date;
  }


  /** 
   * <em>create_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreateDateIsSet() {
    return create_date_is_set;
  }


  /** 
   * <em>create_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreateDateIsModified() {
    return create_date_is_modified;
  }


  /** 
   * <em>create_time</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getCreateTime()
  {
    return create_time;
  }


  /** 
   * <em>create_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreateTimeIsSet() {
    return create_time_is_set;
  }


  /** 
   * <em>create_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreateTimeIsModified() {
    return create_time_is_modified;
  }


  /** 
   * <em>order_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getOrderDate()
  {
    return order_date;
  }


  /** 
   * <em>order_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderDateIsSet() {
    return order_date_is_set;
  }


  /** 
   * <em>order_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderDateIsModified() {
    return order_date_is_modified;
  }


  /** 
   * <em>swt_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSwtDiv()
  {
    return swt_div;
  }


  /** 
   * <em>swt_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwtDivIsSet() {
    return swt_div_is_set;
  }


  /** 
   * <em>swt_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwtDivIsModified() {
    return swt_div_is_modified;
  }


  /** 
   * <em>swt_product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSwtProductCode()
  {
    return swt_product_code;
  }


  /** 
   * <em>swt_product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwtProductCodeIsSet() {
    return swt_product_code_is_set;
  }


  /** 
   * <em>swt_product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwtProductCodeIsModified() {
    return swt_product_code_is_modified;
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
   * <em>swt_tax_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSwtTaxType()
  {
    return swt_tax_type;
  }


  /** 
   * <em>swt_tax_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwtTaxTypeIsSet() {
    return swt_tax_type_is_set;
  }


  /** 
   * <em>swt_tax_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwtTaxTypeIsModified() {
    return swt_tax_type_is_modified;
  }


  /** 
   * <em>claim_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getClaimDiv()
  {
    return claim_div;
  }


  /** 
   * <em>claim_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getClaimDivIsSet() {
    return claim_div_is_set;
  }


  /** 
   * <em>claim_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getClaimDivIsModified() {
    return claim_div_is_modified;
  }


  /** 
   * <em>order_chanel</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderChanel()
  {
    return order_chanel;
  }


  /** 
   * <em>order_chanel</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderChanelIsSet() {
    return order_chanel_is_set;
  }


  /** 
   * <em>order_chanel</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderChanelIsModified() {
    return order_chanel_is_modified;
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
   * <em>payment_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getPaymentDate()
  {
    return payment_date;
  }


  /** 
   * <em>payment_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentDateIsSet() {
    return payment_date_is_set;
  }


  /** 
   * <em>payment_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentDateIsModified() {
    return payment_date_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new HostXbmfOrderPK(institution_code, branch_code, order_request_number);
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
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす2桁以下のString値 
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
   * <em>product_code</em>カラムの値を設定します。 
   *
   * @@param p_productCode <em>product_code</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setProductCode( String p_productCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_code = p_productCode;
    product_code_is_set = true;
    product_code_is_modified = true;
  }


  /** 
   * <em>specify_div</em>カラムの値を設定します。 
   *
   * @@param p_specifyDiv <em>specify_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSpecifyDiv( String p_specifyDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    specify_div = p_specifyDiv;
    specify_div_is_set = true;
    specify_div_is_modified = true;
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
   * <em>sell_order_quantity</em>カラムの値を設定します。 
   *
   * @@param p_sellOrderQuantity <em>sell_order_quantity</em>カラムの値をあらわす13桁以下のlong値 
   */
  public final void setSellOrderQuantity( long p_sellOrderQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_order_quantity = new Long(p_sellOrderQuantity);
    sell_order_quantity_is_set = true;
    sell_order_quantity_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>sell_order_quantity</em>カラムに値を設定します。 
   */
  public final void setSellOrderQuantity( Long p_sellOrderQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sell_order_quantity = p_sellOrderQuantity;
    sell_order_quantity_is_set = true;
    sell_order_quantity_is_modified = true;
  }


  /** 
   * <em>buy_order_quantity</em>カラムの値を設定します。 
   *
   * @@param p_buyOrderQuantity <em>buy_order_quantity</em>カラムの値をあらわす13桁以下のlong値 
   */
  public final void setBuyOrderQuantity( long p_buyOrderQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_order_quantity = new Long(p_buyOrderQuantity);
    buy_order_quantity_is_set = true;
    buy_order_quantity_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>buy_order_quantity</em>カラムに値を設定します。 
   */
  public final void setBuyOrderQuantity( Long p_buyOrderQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    buy_order_quantity = p_buyOrderQuantity;
    buy_order_quantity_is_set = true;
    buy_order_quantity_is_modified = true;
  }


  /** 
   * <em>ticket_number</em>カラムの値を設定します。 
   *
   * @@param p_ticketNumber <em>ticket_number</em>カラムの値をあらわす4桁以下のint値 
   */
  public final void setTicketNumber( int p_ticketNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ticket_number = new Integer(p_ticketNumber);
    ticket_number_is_set = true;
    ticket_number_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ticket_number</em>カラムに値を設定します。 
   */
  public final void setTicketNumber( Integer p_ticketNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ticket_number = p_ticketNumber;
    ticket_number_is_set = true;
    ticket_number_is_modified = true;
  }


  /** 
   * <em>pr_div</em>カラムの値を設定します。 
   *
   * @@param p_prDiv <em>pr_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPrDiv( String p_prDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    pr_div = p_prDiv;
    pr_div_is_set = true;
    pr_div_is_modified = true;
  }


  /** 
   * <em>commission_div</em>カラムの値を設定します。 
   *
   * @@param p_commissionDiv <em>commission_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCommissionDiv( String p_commissionDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commission_div = p_commissionDiv;
    commission_div_is_set = true;
    commission_div_is_modified = true;
  }


  /** 
   * <em>deposit_check_div</em>カラムの値を設定します。 
   *
   * @@param p_depositCheckDiv <em>deposit_check_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDepositCheckDiv( String p_depositCheckDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    deposit_check_div = p_depositCheckDiv;
    deposit_check_div_is_set = true;
    deposit_check_div_is_modified = true;
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
   * <em>create_date</em>カラムの値を設定します。 
   *
   * @@param p_createDate <em>create_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setCreateDate( java.sql.Timestamp p_createDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    create_date = p_createDate;
    create_date_is_set = true;
    create_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>create_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setCreateDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    create_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    create_date_is_set = true;
    create_date_is_modified = true;
  }


  /** 
   * <em>create_time</em>カラムの値を設定します。 
   *
   * @@param p_createTime <em>create_time</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setCreateTime( java.sql.Timestamp p_createTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    create_time = p_createTime;
    create_time_is_set = true;
    create_time_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>create_time</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setCreateTime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    create_time = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    create_time_is_set = true;
    create_time_is_modified = true;
  }


  /** 
   * <em>order_date</em>カラムの値を設定します。 
   *
   * @@param p_orderDate <em>order_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setOrderDate( java.sql.Timestamp p_orderDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_date = p_orderDate;
    order_date_is_set = true;
    order_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>order_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setOrderDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    order_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    order_date_is_set = true;
    order_date_is_modified = true;
  }


  /** 
   * <em>swt_div</em>カラムの値を設定します。 
   *
   * @@param p_swtDiv <em>swt_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSwtDiv( String p_swtDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    swt_div = p_swtDiv;
    swt_div_is_set = true;
    swt_div_is_modified = true;
  }


  /** 
   * <em>swt_product_code</em>カラムの値を設定します。 
   *
   * @@param p_swtProductCode <em>swt_product_code</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setSwtProductCode( String p_swtProductCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    swt_product_code = p_swtProductCode;
    swt_product_code_is_set = true;
    swt_product_code_is_modified = true;
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
   * <em>swt_tax_type</em>カラムの値を設定します。 
   *
   * @@param p_swtTaxType <em>swt_tax_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSwtTaxType( String p_swtTaxType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    swt_tax_type = p_swtTaxType;
    swt_tax_type_is_set = true;
    swt_tax_type_is_modified = true;
  }


  /** 
   * <em>claim_div</em>カラムの値を設定します。 
   *
   * @@param p_claimDiv <em>claim_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setClaimDiv( String p_claimDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    claim_div = p_claimDiv;
    claim_div_is_set = true;
    claim_div_is_modified = true;
  }


  /** 
   * <em>order_chanel</em>カラムの値を設定します。 
   *
   * @@param p_orderChanel <em>order_chanel</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOrderChanel( String p_orderChanel )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_chanel = p_orderChanel;
    order_chanel_is_set = true;
    order_chanel_is_modified = true;
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
   * <em>payment_date</em>カラムの値を設定します。 
   *
   * @@param p_paymentDate <em>payment_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setPaymentDate( java.sql.Timestamp p_paymentDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_date = p_paymentDate;
    payment_date_is_set = true;
    payment_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>payment_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setPaymentDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    payment_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    payment_date_is_set = true;
    payment_date_is_modified = true;
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
                else if ( name.equals("buy_order_quantity") ) {
                    return this.buy_order_quantity;
                }
                break;
            case 'c':
                if ( name.equals("commission_div") ) {
                    return this.commission_div;
                }
                else if ( name.equals("create_date") ) {
                    return this.create_date;
                }
                else if ( name.equals("create_time") ) {
                    return this.create_time;
                }
                else if ( name.equals("claim_div") ) {
                    return this.claim_div;
                }
                break;
            case 'd':
                if ( name.equals("deposit_check_div") ) {
                    return this.deposit_check_div;
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
                else if ( name.equals("order_date") ) {
                    return this.order_date;
                }
                else if ( name.equals("order_chanel") ) {
                    return this.order_chanel;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    return this.product_code;
                }
                else if ( name.equals("pr_div") ) {
                    return this.pr_div;
                }
                else if ( name.equals("payment_date") ) {
                    return this.payment_date;
                }
                break;
            case 'r':
                if ( name.equals("request_code") ) {
                    return this.request_code;
                }
                break;
            case 's':
                if ( name.equals("specify_div") ) {
                    return this.specify_div;
                }
                else if ( name.equals("settlement_div") ) {
                    return this.settlement_div;
                }
                else if ( name.equals("sell_order_quantity") ) {
                    return this.sell_order_quantity;
                }
                else if ( name.equals("swt_div") ) {
                    return this.swt_div;
                }
                else if ( name.equals("swt_product_code") ) {
                    return this.swt_product_code;
                }
                else if ( name.equals("swt_tax_type") ) {
                    return this.swt_tax_type;
                }
                else if ( name.equals("status") ) {
                    return this.status;
                }
                break;
            case 't':
                if ( name.equals("trader_code") ) {
                    return this.trader_code;
                }
                else if ( name.equals("ticket_number") ) {
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
                else if ( name.equals("buy_order_quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'buy_order_quantity' must be Long: '"+value+"' is not." );
                    this.buy_order_quantity = (Long) value;
                    if (this.buy_order_quantity_is_set)
                        this.buy_order_quantity_is_modified = true;
                    this.buy_order_quantity_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("commission_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'commission_div' must be String: '"+value+"' is not." );
                    this.commission_div = (String) value;
                    if (this.commission_div_is_set)
                        this.commission_div_is_modified = true;
                    this.commission_div_is_set = true;
                    return;
                }
                else if ( name.equals("create_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'create_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.create_date = (java.sql.Timestamp) value;
                    if (this.create_date_is_set)
                        this.create_date_is_modified = true;
                    this.create_date_is_set = true;
                    return;
                }
                else if ( name.equals("create_time") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'create_time' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.create_time = (java.sql.Timestamp) value;
                    if (this.create_time_is_set)
                        this.create_time_is_modified = true;
                    this.create_time_is_set = true;
                    return;
                }
                else if ( name.equals("claim_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'claim_div' must be String: '"+value+"' is not." );
                    this.claim_div = (String) value;
                    if (this.claim_div_is_set)
                        this.claim_div_is_modified = true;
                    this.claim_div_is_set = true;
                    return;
                }
                break;
            case 'd':
                if ( name.equals("deposit_check_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'deposit_check_div' must be String: '"+value+"' is not." );
                    this.deposit_check_div = (String) value;
                    if (this.deposit_check_div_is_set)
                        this.deposit_check_div_is_modified = true;
                    this.deposit_check_div_is_set = true;
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
                else if ( name.equals("order_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'order_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.order_date = (java.sql.Timestamp) value;
                    if (this.order_date_is_set)
                        this.order_date_is_modified = true;
                    this.order_date_is_set = true;
                    return;
                }
                else if ( name.equals("order_chanel") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_chanel' must be String: '"+value+"' is not." );
                    this.order_chanel = (String) value;
                    if (this.order_chanel_is_set)
                        this.order_chanel_is_modified = true;
                    this.order_chanel_is_set = true;
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
                else if ( name.equals("pr_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'pr_div' must be String: '"+value+"' is not." );
                    this.pr_div = (String) value;
                    if (this.pr_div_is_set)
                        this.pr_div_is_modified = true;
                    this.pr_div_is_set = true;
                    return;
                }
                else if ( name.equals("payment_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'payment_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.payment_date = (java.sql.Timestamp) value;
                    if (this.payment_date_is_set)
                        this.payment_date_is_modified = true;
                    this.payment_date_is_set = true;
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
                if ( name.equals("specify_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'specify_div' must be String: '"+value+"' is not." );
                    this.specify_div = (String) value;
                    if (this.specify_div_is_set)
                        this.specify_div_is_modified = true;
                    this.specify_div_is_set = true;
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
                else if ( name.equals("sell_order_quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'sell_order_quantity' must be Long: '"+value+"' is not." );
                    this.sell_order_quantity = (Long) value;
                    if (this.sell_order_quantity_is_set)
                        this.sell_order_quantity_is_modified = true;
                    this.sell_order_quantity_is_set = true;
                    return;
                }
                else if ( name.equals("swt_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'swt_div' must be String: '"+value+"' is not." );
                    this.swt_div = (String) value;
                    if (this.swt_div_is_set)
                        this.swt_div_is_modified = true;
                    this.swt_div_is_set = true;
                    return;
                }
                else if ( name.equals("swt_product_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'swt_product_code' must be String: '"+value+"' is not." );
                    this.swt_product_code = (String) value;
                    if (this.swt_product_code_is_set)
                        this.swt_product_code_is_modified = true;
                    this.swt_product_code_is_set = true;
                    return;
                }
                else if ( name.equals("swt_tax_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'swt_tax_type' must be String: '"+value+"' is not." );
                    this.swt_tax_type = (String) value;
                    if (this.swt_tax_type_is_set)
                        this.swt_tax_type_is_modified = true;
                    this.swt_tax_type_is_set = true;
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
                else if ( name.equals("ticket_number") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'ticket_number' must be Integer: '"+value+"' is not." );
                    this.ticket_number = (Integer) value;
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
