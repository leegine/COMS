head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.47.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	BookValueSpecParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradehistory.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * book_value_specテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link BookValueSpecRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link BookValueSpecParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link BookValueSpecParams}が{@@link BookValueSpecRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BookValueSpecPK 
 * @@see BookValueSpecRow 
 */
public class BookValueSpecParams extends Params implements BookValueSpecRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "book_value_spec";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = BookValueSpecRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return BookValueSpecRow.TYPE;
  }


  /** 
   * <em>book_value_spec_id</em>カラムの値 
   */
  public  long  book_value_spec_id;

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
   * <em>tax_type</em>カラムの値 
   */
  public  String  tax_type;

  /** 
   * <em>work_date</em>カラムの値 
   */
  public  java.sql.Timestamp  work_date;

  /** 
   * <em>rec_div</em>カラムの値 
   */
  public  String  rec_div;

  /** 
   * <em>commodity_code</em>カラムの値 
   */
  public  String  commodity_code;

  /** 
   * <em>product_code</em>カラムの値 
   */
  public  String  product_code;

  /** 
   * <em>sort_no</em>カラムの値 
   */
  public  String  sort_no;

  /** 
   * <em>calc_date</em>カラムの値 
   */
  public  java.sql.Timestamp  calc_date;

  /** 
   * <em>exec_date</em>カラムの値 
   */
  public  java.sql.Timestamp  exec_date;

  /** 
   * <em>delivery_date</em>カラムの値 
   */
  public  java.sql.Timestamp  delivery_date;

  /** 
   * <em>stock_exchg</em>カラムの値 
   */
  public  String  stock_exchg;

  /** 
   * <em>trade_type</em>カラムの値 
   */
  public  String  trade_type;

  /** 
   * <em>buy_sell_div</em>カラムの値 
   */
  public  String  buy_sell_div;

  /** 
   * <em>debit_bal_div</em>カラムの値 
   */
  public  String  debit_bal_div;

  /** 
   * <em>quantity</em>カラムの値 
   */
  public  Double  quantity;

  /** 
   * <em>price</em>カラムの値 
   */
  public  Double  price;

  /** 
   * <em>ccy_code</em>カラムの値 
   */
  public  String  ccy_code;

  /** 
   * <em>net_amount</em>カラムの値 
   */
  public  Double  net_amount;

  /** 
   * <em>calc_amount</em>カラムの値 
   */
  public  Double  calc_amount;

  /** 
   * <em>bal_quantity</em>カラムの値 
   */
  public  Double  bal_quantity;

  /** 
   * <em>book_value</em>カラムの値 
   */
  public  Double  book_value;

  /** 
   * <em>book_amount</em>カラムの値 
   */
  public  Double  book_amount;

  /** 
   * <em>proloss1</em>カラムの値 
   */
  public  Double  proloss1;

  /** 
   * <em>proloss2</em>カラムの値 
   */
  public  Double  proloss2;

  /** 
   * <em>remark</em>カラムの値 
   */
  public  String  remark;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean book_value_spec_id_is_set = false;
  private boolean book_value_spec_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean tax_type_is_set = false;
  private boolean tax_type_is_modified = false;
  private boolean work_date_is_set = false;
  private boolean work_date_is_modified = false;
  private boolean rec_div_is_set = false;
  private boolean rec_div_is_modified = false;
  private boolean commodity_code_is_set = false;
  private boolean commodity_code_is_modified = false;
  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;
  private boolean sort_no_is_set = false;
  private boolean sort_no_is_modified = false;
  private boolean calc_date_is_set = false;
  private boolean calc_date_is_modified = false;
  private boolean exec_date_is_set = false;
  private boolean exec_date_is_modified = false;
  private boolean delivery_date_is_set = false;
  private boolean delivery_date_is_modified = false;
  private boolean stock_exchg_is_set = false;
  private boolean stock_exchg_is_modified = false;
  private boolean trade_type_is_set = false;
  private boolean trade_type_is_modified = false;
  private boolean buy_sell_div_is_set = false;
  private boolean buy_sell_div_is_modified = false;
  private boolean debit_bal_div_is_set = false;
  private boolean debit_bal_div_is_modified = false;
  private boolean quantity_is_set = false;
  private boolean quantity_is_modified = false;
  private boolean price_is_set = false;
  private boolean price_is_modified = false;
  private boolean ccy_code_is_set = false;
  private boolean ccy_code_is_modified = false;
  private boolean net_amount_is_set = false;
  private boolean net_amount_is_modified = false;
  private boolean calc_amount_is_set = false;
  private boolean calc_amount_is_modified = false;
  private boolean bal_quantity_is_set = false;
  private boolean bal_quantity_is_modified = false;
  private boolean book_value_is_set = false;
  private boolean book_value_is_modified = false;
  private boolean book_amount_is_set = false;
  private boolean book_amount_is_modified = false;
  private boolean proloss1_is_set = false;
  private boolean proloss1_is_modified = false;
  private boolean proloss2_is_set = false;
  private boolean proloss2_is_modified = false;
  private boolean remark_is_set = false;
  private boolean remark_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "book_value_spec_id=" + book_value_spec_id
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "," + "tax_type=" +tax_type
      + "," + "work_date=" +work_date
      + "," + "rec_div=" +rec_div
      + "," + "commodity_code=" +commodity_code
      + "," + "product_code=" +product_code
      + "," + "sort_no=" +sort_no
      + "," + "calc_date=" +calc_date
      + "," + "exec_date=" +exec_date
      + "," + "delivery_date=" +delivery_date
      + "," + "stock_exchg=" +stock_exchg
      + "," + "trade_type=" +trade_type
      + "," + "buy_sell_div=" +buy_sell_div
      + "," + "debit_bal_div=" +debit_bal_div
      + "," + "quantity=" +quantity
      + "," + "price=" +price
      + "," + "ccy_code=" +ccy_code
      + "," + "net_amount=" +net_amount
      + "," + "calc_amount=" +calc_amount
      + "," + "bal_quantity=" +bal_quantity
      + "," + "book_value=" +book_value
      + "," + "book_amount=" +book_amount
      + "," + "proloss1=" +proloss1
      + "," + "proloss2=" +proloss2
      + "," + "remark=" +remark
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のBookValueSpecParamsオブジェクトを作成します。 
   */
  public BookValueSpecParams() {
    book_value_spec_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のBookValueSpecRowオブジェクトの値を利用してBookValueSpecParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するBookValueSpecRowオブジェクト 
   */
  public BookValueSpecParams( BookValueSpecRow p_row )
  {
    book_value_spec_id = p_row.getBookValueSpecId();
    book_value_spec_id_is_set = p_row.getBookValueSpecIdIsSet();
    book_value_spec_id_is_modified = p_row.getBookValueSpecIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    tax_type = p_row.getTaxType();
    tax_type_is_set = p_row.getTaxTypeIsSet();
    tax_type_is_modified = p_row.getTaxTypeIsModified();
    work_date = p_row.getWorkDate();
    work_date_is_set = p_row.getWorkDateIsSet();
    work_date_is_modified = p_row.getWorkDateIsModified();
    rec_div = p_row.getRecDiv();
    rec_div_is_set = p_row.getRecDivIsSet();
    rec_div_is_modified = p_row.getRecDivIsModified();
    commodity_code = p_row.getCommodityCode();
    commodity_code_is_set = p_row.getCommodityCodeIsSet();
    commodity_code_is_modified = p_row.getCommodityCodeIsModified();
    product_code = p_row.getProductCode();
    product_code_is_set = p_row.getProductCodeIsSet();
    product_code_is_modified = p_row.getProductCodeIsModified();
    sort_no = p_row.getSortNo();
    sort_no_is_set = p_row.getSortNoIsSet();
    sort_no_is_modified = p_row.getSortNoIsModified();
    calc_date = p_row.getCalcDate();
    calc_date_is_set = p_row.getCalcDateIsSet();
    calc_date_is_modified = p_row.getCalcDateIsModified();
    exec_date = p_row.getExecDate();
    exec_date_is_set = p_row.getExecDateIsSet();
    exec_date_is_modified = p_row.getExecDateIsModified();
    delivery_date = p_row.getDeliveryDate();
    delivery_date_is_set = p_row.getDeliveryDateIsSet();
    delivery_date_is_modified = p_row.getDeliveryDateIsModified();
    stock_exchg = p_row.getStockExchg();
    stock_exchg_is_set = p_row.getStockExchgIsSet();
    stock_exchg_is_modified = p_row.getStockExchgIsModified();
    trade_type = p_row.getTradeType();
    trade_type_is_set = p_row.getTradeTypeIsSet();
    trade_type_is_modified = p_row.getTradeTypeIsModified();
    buy_sell_div = p_row.getBuySellDiv();
    buy_sell_div_is_set = p_row.getBuySellDivIsSet();
    buy_sell_div_is_modified = p_row.getBuySellDivIsModified();
    debit_bal_div = p_row.getDebitBalDiv();
    debit_bal_div_is_set = p_row.getDebitBalDivIsSet();
    debit_bal_div_is_modified = p_row.getDebitBalDivIsModified();
    if ( !p_row.getQuantityIsNull() )
      quantity = new Double( p_row.getQuantity() );
    quantity_is_set = p_row.getQuantityIsSet();
    quantity_is_modified = p_row.getQuantityIsModified();
    if ( !p_row.getPriceIsNull() )
      price = new Double( p_row.getPrice() );
    price_is_set = p_row.getPriceIsSet();
    price_is_modified = p_row.getPriceIsModified();
    ccy_code = p_row.getCcyCode();
    ccy_code_is_set = p_row.getCcyCodeIsSet();
    ccy_code_is_modified = p_row.getCcyCodeIsModified();
    if ( !p_row.getNetAmountIsNull() )
      net_amount = new Double( p_row.getNetAmount() );
    net_amount_is_set = p_row.getNetAmountIsSet();
    net_amount_is_modified = p_row.getNetAmountIsModified();
    if ( !p_row.getCalcAmountIsNull() )
      calc_amount = new Double( p_row.getCalcAmount() );
    calc_amount_is_set = p_row.getCalcAmountIsSet();
    calc_amount_is_modified = p_row.getCalcAmountIsModified();
    if ( !p_row.getBalQuantityIsNull() )
      bal_quantity = new Double( p_row.getBalQuantity() );
    bal_quantity_is_set = p_row.getBalQuantityIsSet();
    bal_quantity_is_modified = p_row.getBalQuantityIsModified();
    if ( !p_row.getBookValueIsNull() )
      book_value = new Double( p_row.getBookValue() );
    book_value_is_set = p_row.getBookValueIsSet();
    book_value_is_modified = p_row.getBookValueIsModified();
    if ( !p_row.getBookAmountIsNull() )
      book_amount = new Double( p_row.getBookAmount() );
    book_amount_is_set = p_row.getBookAmountIsSet();
    book_amount_is_modified = p_row.getBookAmountIsModified();
    if ( !p_row.getProloss1IsNull() )
      proloss1 = new Double( p_row.getProloss1() );
    proloss1_is_set = p_row.getProloss1IsSet();
    proloss1_is_modified = p_row.getProloss1IsModified();
    if ( !p_row.getProloss2IsNull() )
      proloss2 = new Double( p_row.getProloss2() );
    proloss2_is_set = p_row.getProloss2IsSet();
    proloss2_is_modified = p_row.getProloss2IsModified();
    remark = p_row.getRemark();
    remark_is_set = p_row.getRemarkIsSet();
    remark_is_modified = p_row.getRemarkIsModified();
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
      institution_code_is_set = true;
      institution_code_is_modified = true;
      branch_code_is_set = true;
      branch_code_is_modified = true;
      account_code_is_set = true;
      account_code_is_modified = true;
      tax_type_is_set = true;
      tax_type_is_modified = true;
      work_date_is_set = true;
      work_date_is_modified = true;
      rec_div_is_set = true;
      rec_div_is_modified = true;
      commodity_code_is_set = true;
      commodity_code_is_modified = true;
      product_code_is_set = true;
      product_code_is_modified = true;
      sort_no_is_set = true;
      sort_no_is_modified = true;
      calc_date_is_set = true;
      calc_date_is_modified = true;
      exec_date_is_set = true;
      exec_date_is_modified = true;
      delivery_date_is_set = true;
      delivery_date_is_modified = true;
      stock_exchg_is_set = true;
      stock_exchg_is_modified = true;
      trade_type_is_set = true;
      trade_type_is_modified = true;
      buy_sell_div_is_set = true;
      buy_sell_div_is_modified = true;
      debit_bal_div_is_set = true;
      debit_bal_div_is_modified = true;
      quantity_is_set = true;
      quantity_is_modified = true;
      price_is_set = true;
      price_is_modified = true;
      ccy_code_is_set = true;
      ccy_code_is_modified = true;
      net_amount_is_set = true;
      net_amount_is_modified = true;
      calc_amount_is_set = true;
      calc_amount_is_modified = true;
      bal_quantity_is_set = true;
      bal_quantity_is_modified = true;
      book_value_is_set = true;
      book_value_is_modified = true;
      book_amount_is_set = true;
      book_amount_is_modified = true;
      proloss1_is_set = true;
      proloss1_is_modified = true;
      proloss2_is_set = true;
      proloss2_is_modified = true;
      remark_is_set = true;
      remark_is_modified = true;
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
    if ( !( other instanceof BookValueSpecRow ) )
       return false;
    return fieldsEqual( (BookValueSpecRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のBookValueSpecRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( BookValueSpecRow row )
  {
    if ( book_value_spec_id != row.getBookValueSpecId() )
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
    if ( tax_type == null ) {
      if ( row.getTaxType() != null )
        return false;
    } else if ( !tax_type.equals( row.getTaxType() ) ) {
        return false;
    }
    if ( work_date == null ) {
      if ( row.getWorkDate() != null )
        return false;
    } else if ( !work_date.equals( row.getWorkDate() ) ) {
        return false;
    }
    if ( rec_div == null ) {
      if ( row.getRecDiv() != null )
        return false;
    } else if ( !rec_div.equals( row.getRecDiv() ) ) {
        return false;
    }
    if ( commodity_code == null ) {
      if ( row.getCommodityCode() != null )
        return false;
    } else if ( !commodity_code.equals( row.getCommodityCode() ) ) {
        return false;
    }
    if ( product_code == null ) {
      if ( row.getProductCode() != null )
        return false;
    } else if ( !product_code.equals( row.getProductCode() ) ) {
        return false;
    }
    if ( sort_no == null ) {
      if ( row.getSortNo() != null )
        return false;
    } else if ( !sort_no.equals( row.getSortNo() ) ) {
        return false;
    }
    if ( calc_date == null ) {
      if ( row.getCalcDate() != null )
        return false;
    } else if ( !calc_date.equals( row.getCalcDate() ) ) {
        return false;
    }
    if ( exec_date == null ) {
      if ( row.getExecDate() != null )
        return false;
    } else if ( !exec_date.equals( row.getExecDate() ) ) {
        return false;
    }
    if ( delivery_date == null ) {
      if ( row.getDeliveryDate() != null )
        return false;
    } else if ( !delivery_date.equals( row.getDeliveryDate() ) ) {
        return false;
    }
    if ( stock_exchg == null ) {
      if ( row.getStockExchg() != null )
        return false;
    } else if ( !stock_exchg.equals( row.getStockExchg() ) ) {
        return false;
    }
    if ( trade_type == null ) {
      if ( row.getTradeType() != null )
        return false;
    } else if ( !trade_type.equals( row.getTradeType() ) ) {
        return false;
    }
    if ( buy_sell_div == null ) {
      if ( row.getBuySellDiv() != null )
        return false;
    } else if ( !buy_sell_div.equals( row.getBuySellDiv() ) ) {
        return false;
    }
    if ( debit_bal_div == null ) {
      if ( row.getDebitBalDiv() != null )
        return false;
    } else if ( !debit_bal_div.equals( row.getDebitBalDiv() ) ) {
        return false;
    }
    if ( quantity == null ) {
      if ( !row.getQuantityIsNull() )
        return false;
    } else if ( row.getQuantityIsNull() || ( quantity.doubleValue() != row.getQuantity() ) ) {
        return false;
    }
    if ( price == null ) {
      if ( !row.getPriceIsNull() )
        return false;
    } else if ( row.getPriceIsNull() || ( price.doubleValue() != row.getPrice() ) ) {
        return false;
    }
    if ( ccy_code == null ) {
      if ( row.getCcyCode() != null )
        return false;
    } else if ( !ccy_code.equals( row.getCcyCode() ) ) {
        return false;
    }
    if ( net_amount == null ) {
      if ( !row.getNetAmountIsNull() )
        return false;
    } else if ( row.getNetAmountIsNull() || ( net_amount.doubleValue() != row.getNetAmount() ) ) {
        return false;
    }
    if ( calc_amount == null ) {
      if ( !row.getCalcAmountIsNull() )
        return false;
    } else if ( row.getCalcAmountIsNull() || ( calc_amount.doubleValue() != row.getCalcAmount() ) ) {
        return false;
    }
    if ( bal_quantity == null ) {
      if ( !row.getBalQuantityIsNull() )
        return false;
    } else if ( row.getBalQuantityIsNull() || ( bal_quantity.doubleValue() != row.getBalQuantity() ) ) {
        return false;
    }
    if ( book_value == null ) {
      if ( !row.getBookValueIsNull() )
        return false;
    } else if ( row.getBookValueIsNull() || ( book_value.doubleValue() != row.getBookValue() ) ) {
        return false;
    }
    if ( book_amount == null ) {
      if ( !row.getBookAmountIsNull() )
        return false;
    } else if ( row.getBookAmountIsNull() || ( book_amount.doubleValue() != row.getBookAmount() ) ) {
        return false;
    }
    if ( proloss1 == null ) {
      if ( !row.getProloss1IsNull() )
        return false;
    } else if ( row.getProloss1IsNull() || ( proloss1.doubleValue() != row.getProloss1() ) ) {
        return false;
    }
    if ( proloss2 == null ) {
      if ( !row.getProloss2IsNull() )
        return false;
    } else if ( row.getProloss2IsNull() || ( proloss2.doubleValue() != row.getProloss2() ) ) {
        return false;
    }
    if ( remark == null ) {
      if ( row.getRemark() != null )
        return false;
    } else if ( !remark.equals( row.getRemark() ) ) {
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
      return  ((int) book_value_spec_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (tax_type!=null? tax_type.hashCode(): 0) 
        + (work_date!=null? work_date.hashCode(): 0) 
        + (rec_div!=null? rec_div.hashCode(): 0) 
        + (commodity_code!=null? commodity_code.hashCode(): 0) 
        + (product_code!=null? product_code.hashCode(): 0) 
        + (sort_no!=null? sort_no.hashCode(): 0) 
        + (calc_date!=null? calc_date.hashCode(): 0) 
        + (exec_date!=null? exec_date.hashCode(): 0) 
        + (delivery_date!=null? delivery_date.hashCode(): 0) 
        + (stock_exchg!=null? stock_exchg.hashCode(): 0) 
        + (trade_type!=null? trade_type.hashCode(): 0) 
        + (buy_sell_div!=null? buy_sell_div.hashCode(): 0) 
        + (debit_bal_div!=null? debit_bal_div.hashCode(): 0) 
        + (quantity!=null? quantity.hashCode(): 0) 
        + (price!=null? price.hashCode(): 0) 
        + (ccy_code!=null? ccy_code.hashCode(): 0) 
        + (net_amount!=null? net_amount.hashCode(): 0) 
        + (calc_amount!=null? calc_amount.hashCode(): 0) 
        + (bal_quantity!=null? bal_quantity.hashCode(): 0) 
        + (book_value!=null? book_value.hashCode(): 0) 
        + (book_amount!=null? book_amount.hashCode(): 0) 
        + (proloss1!=null? proloss1.hashCode(): 0) 
        + (proloss2!=null? proloss2.hashCode(): 0) 
        + (remark!=null? remark.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !created_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'created_timestamp' must be set before inserting.");
		if ( !last_updated_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updated_timestamp' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("book_value_spec_id",new Long(book_value_spec_id));
		if ( institution_code != null )
			map.put("institution_code",institution_code);
		if ( branch_code != null )
			map.put("branch_code",branch_code);
		if ( account_code != null )
			map.put("account_code",account_code);
		if ( tax_type != null )
			map.put("tax_type",tax_type);
		if ( work_date != null )
			map.put("work_date",work_date);
		if ( rec_div != null )
			map.put("rec_div",rec_div);
		if ( commodity_code != null )
			map.put("commodity_code",commodity_code);
		if ( product_code != null )
			map.put("product_code",product_code);
		if ( sort_no != null )
			map.put("sort_no",sort_no);
		if ( calc_date != null )
			map.put("calc_date",calc_date);
		if ( exec_date != null )
			map.put("exec_date",exec_date);
		if ( delivery_date != null )
			map.put("delivery_date",delivery_date);
		if ( stock_exchg != null )
			map.put("stock_exchg",stock_exchg);
		if ( trade_type != null )
			map.put("trade_type",trade_type);
		if ( buy_sell_div != null )
			map.put("buy_sell_div",buy_sell_div);
		if ( debit_bal_div != null )
			map.put("debit_bal_div",debit_bal_div);
		if ( quantity != null )
			map.put("quantity",quantity);
		if ( price != null )
			map.put("price",price);
		if ( ccy_code != null )
			map.put("ccy_code",ccy_code);
		if ( net_amount != null )
			map.put("net_amount",net_amount);
		if ( calc_amount != null )
			map.put("calc_amount",calc_amount);
		if ( bal_quantity != null )
			map.put("bal_quantity",bal_quantity);
		if ( book_value != null )
			map.put("book_value",book_value);
		if ( book_amount != null )
			map.put("book_amount",book_amount);
		if ( proloss1 != null )
			map.put("proloss1",proloss1);
		if ( proloss2 != null )
			map.put("proloss2",proloss2);
		if ( remark != null )
			map.put("remark",remark);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
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
		if ( tax_type_is_modified )
			map.put("tax_type",tax_type);
		if ( work_date_is_modified )
			map.put("work_date",work_date);
		if ( rec_div_is_modified )
			map.put("rec_div",rec_div);
		if ( commodity_code_is_modified )
			map.put("commodity_code",commodity_code);
		if ( product_code_is_modified )
			map.put("product_code",product_code);
		if ( sort_no_is_modified )
			map.put("sort_no",sort_no);
		if ( calc_date_is_modified )
			map.put("calc_date",calc_date);
		if ( exec_date_is_modified )
			map.put("exec_date",exec_date);
		if ( delivery_date_is_modified )
			map.put("delivery_date",delivery_date);
		if ( stock_exchg_is_modified )
			map.put("stock_exchg",stock_exchg);
		if ( trade_type_is_modified )
			map.put("trade_type",trade_type);
		if ( buy_sell_div_is_modified )
			map.put("buy_sell_div",buy_sell_div);
		if ( debit_bal_div_is_modified )
			map.put("debit_bal_div",debit_bal_div);
		if ( quantity_is_modified )
			map.put("quantity",quantity);
		if ( price_is_modified )
			map.put("price",price);
		if ( ccy_code_is_modified )
			map.put("ccy_code",ccy_code);
		if ( net_amount_is_modified )
			map.put("net_amount",net_amount);
		if ( calc_amount_is_modified )
			map.put("calc_amount",calc_amount);
		if ( bal_quantity_is_modified )
			map.put("bal_quantity",bal_quantity);
		if ( book_value_is_modified )
			map.put("book_value",book_value);
		if ( book_amount_is_modified )
			map.put("book_amount",book_amount);
		if ( proloss1_is_modified )
			map.put("proloss1",proloss1);
		if ( proloss2_is_modified )
			map.put("proloss2",proloss2);
		if ( remark_is_modified )
			map.put("remark",remark);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("institution_code",institution_code);
			map.put("branch_code",branch_code);
			map.put("account_code",account_code);
			map.put("tax_type",tax_type);
			map.put("work_date",work_date);
			map.put("rec_div",rec_div);
			map.put("commodity_code",commodity_code);
			map.put("product_code",product_code);
			map.put("sort_no",sort_no);
			map.put("calc_date",calc_date);
			map.put("exec_date",exec_date);
			map.put("delivery_date",delivery_date);
			map.put("stock_exchg",stock_exchg);
			map.put("trade_type",trade_type);
			map.put("buy_sell_div",buy_sell_div);
			map.put("debit_bal_div",debit_bal_div);
			map.put("quantity",quantity);
			map.put("price",price);
			map.put("ccy_code",ccy_code);
			map.put("net_amount",net_amount);
			map.put("calc_amount",calc_amount);
			map.put("bal_quantity",bal_quantity);
			map.put("book_value",book_value);
			map.put("book_amount",book_amount);
			map.put("proloss1",proloss1);
			map.put("proloss2",proloss2);
			map.put("remark",remark);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>book_value_spec_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getBookValueSpecId()
  {
    return book_value_spec_id;
  }


  /** 
   * <em>book_value_spec_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBookValueSpecIdIsSet() {
    return book_value_spec_id_is_set;
  }


  /** 
   * <em>book_value_spec_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBookValueSpecIdIsModified() {
    return book_value_spec_id_is_modified;
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
   * <em>rec_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRecDiv()
  {
    return rec_div;
  }


  /** 
   * <em>rec_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecDivIsSet() {
    return rec_div_is_set;
  }


  /** 
   * <em>rec_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecDivIsModified() {
    return rec_div_is_modified;
  }


  /** 
   * <em>commodity_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCommodityCode()
  {
    return commodity_code;
  }


  /** 
   * <em>commodity_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommodityCodeIsSet() {
    return commodity_code_is_set;
  }


  /** 
   * <em>commodity_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommodityCodeIsModified() {
    return commodity_code_is_modified;
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
   * <em>sort_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSortNo()
  {
    return sort_no;
  }


  /** 
   * <em>sort_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSortNoIsSet() {
    return sort_no_is_set;
  }


  /** 
   * <em>sort_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSortNoIsModified() {
    return sort_no_is_modified;
  }


  /** 
   * <em>calc_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getCalcDate()
  {
    return calc_date;
  }


  /** 
   * <em>calc_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCalcDateIsSet() {
    return calc_date_is_set;
  }


  /** 
   * <em>calc_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCalcDateIsModified() {
    return calc_date_is_modified;
  }


  /** 
   * <em>exec_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getExecDate()
  {
    return exec_date;
  }


  /** 
   * <em>exec_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecDateIsSet() {
    return exec_date_is_set;
  }


  /** 
   * <em>exec_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecDateIsModified() {
    return exec_date_is_modified;
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
   * <em>stock_exchg</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStockExchg()
  {
    return stock_exchg;
  }


  /** 
   * <em>stock_exchg</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStockExchgIsSet() {
    return stock_exchg_is_set;
  }


  /** 
   * <em>stock_exchg</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStockExchgIsModified() {
    return stock_exchg_is_modified;
  }


  /** 
   * <em>trade_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTradeType()
  {
    return trade_type;
  }


  /** 
   * <em>trade_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradeTypeIsSet() {
    return trade_type_is_set;
  }


  /** 
   * <em>trade_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradeTypeIsModified() {
    return trade_type_is_modified;
  }


  /** 
   * <em>buy_sell_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBuySellDiv()
  {
    return buy_sell_div;
  }


  /** 
   * <em>buy_sell_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuySellDivIsSet() {
    return buy_sell_div_is_set;
  }


  /** 
   * <em>buy_sell_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuySellDivIsModified() {
    return buy_sell_div_is_modified;
  }


  /** 
   * <em>debit_bal_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDebitBalDiv()
  {
    return debit_bal_div;
  }


  /** 
   * <em>debit_bal_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDebitBalDivIsSet() {
    return debit_bal_div_is_set;
  }


  /** 
   * <em>debit_bal_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDebitBalDivIsModified() {
    return debit_bal_div_is_modified;
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
   * <em>price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPrice()
  {
    return ( price==null? ((double)0): price.doubleValue() );
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
   * <em>ccy_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCcyCode()
  {
    return ccy_code;
  }


  /** 
   * <em>ccy_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCcyCodeIsSet() {
    return ccy_code_is_set;
  }


  /** 
   * <em>ccy_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCcyCodeIsModified() {
    return ccy_code_is_modified;
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
   * <em>calc_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCalcAmount()
  {
    return ( calc_amount==null? ((double)0): calc_amount.doubleValue() );
  }


  /** 
   * <em>calc_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCalcAmountIsNull()
  {
    return calc_amount == null;
  }


  /** 
   * <em>calc_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCalcAmountIsSet() {
    return calc_amount_is_set;
  }


  /** 
   * <em>calc_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCalcAmountIsModified() {
    return calc_amount_is_modified;
  }


  /** 
   * <em>bal_quantity</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getBalQuantity()
  {
    return ( bal_quantity==null? ((double)0): bal_quantity.doubleValue() );
  }


  /** 
   * <em>bal_quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getBalQuantityIsNull()
  {
    return bal_quantity == null;
  }


  /** 
   * <em>bal_quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBalQuantityIsSet() {
    return bal_quantity_is_set;
  }


  /** 
   * <em>bal_quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBalQuantityIsModified() {
    return bal_quantity_is_modified;
  }


  /** 
   * <em>book_value</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getBookValue()
  {
    return ( book_value==null? ((double)0): book_value.doubleValue() );
  }


  /** 
   * <em>book_value</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getBookValueIsNull()
  {
    return book_value == null;
  }


  /** 
   * <em>book_value</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBookValueIsSet() {
    return book_value_is_set;
  }


  /** 
   * <em>book_value</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBookValueIsModified() {
    return book_value_is_modified;
  }


  /** 
   * <em>book_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getBookAmount()
  {
    return ( book_amount==null? ((double)0): book_amount.doubleValue() );
  }


  /** 
   * <em>book_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getBookAmountIsNull()
  {
    return book_amount == null;
  }


  /** 
   * <em>book_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBookAmountIsSet() {
    return book_amount_is_set;
  }


  /** 
   * <em>book_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBookAmountIsModified() {
    return book_amount_is_modified;
  }


  /** 
   * <em>proloss1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getProloss1()
  {
    return ( proloss1==null? ((double)0): proloss1.doubleValue() );
  }


  /** 
   * <em>proloss1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getProloss1IsNull()
  {
    return proloss1 == null;
  }


  /** 
   * <em>proloss1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProloss1IsSet() {
    return proloss1_is_set;
  }


  /** 
   * <em>proloss1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProloss1IsModified() {
    return proloss1_is_modified;
  }


  /** 
   * <em>proloss2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getProloss2()
  {
    return ( proloss2==null? ((double)0): proloss2.doubleValue() );
  }


  /** 
   * <em>proloss2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getProloss2IsNull()
  {
    return proloss2 == null;
  }


  /** 
   * <em>proloss2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProloss2IsSet() {
    return proloss2_is_set;
  }


  /** 
   * <em>proloss2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProloss2IsModified() {
    return proloss2_is_modified;
  }


  /** 
   * <em>remark</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRemark()
  {
    return remark;
  }


  /** 
   * <em>remark</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRemarkIsSet() {
    return remark_is_set;
  }


  /** 
   * <em>remark</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRemarkIsModified() {
    return remark_is_modified;
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
    return new BookValueSpecPK(book_value_spec_id);
  }


  /** 
   * <em>book_value_spec_id</em>カラムの値を設定します。 
   *
   * @@param p_bookValueSpecId <em>book_value_spec_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setBookValueSpecId( long p_bookValueSpecId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    book_value_spec_id = p_bookValueSpecId;
    book_value_spec_id_is_set = true;
    book_value_spec_id_is_modified = true;
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
   * <em>rec_div</em>カラムの値を設定します。 
   *
   * @@param p_recDiv <em>rec_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setRecDiv( String p_recDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    rec_div = p_recDiv;
    rec_div_is_set = true;
    rec_div_is_modified = true;
  }


  /** 
   * <em>commodity_code</em>カラムの値を設定します。 
   *
   * @@param p_commodityCode <em>commodity_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setCommodityCode( String p_commodityCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commodity_code = p_commodityCode;
    commodity_code_is_set = true;
    commodity_code_is_modified = true;
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
   * <em>sort_no</em>カラムの値を設定します。 
   *
   * @@param p_sortNo <em>sort_no</em>カラムの値をあらわす6桁以下のString値 
   */
  public final void setSortNo( String p_sortNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sort_no = p_sortNo;
    sort_no_is_set = true;
    sort_no_is_modified = true;
  }


  /** 
   * <em>calc_date</em>カラムの値を設定します。 
   *
   * @@param p_calcDate <em>calc_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setCalcDate( java.sql.Timestamp p_calcDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    calc_date = p_calcDate;
    calc_date_is_set = true;
    calc_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>calc_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setCalcDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    calc_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    calc_date_is_set = true;
    calc_date_is_modified = true;
  }


  /** 
   * <em>exec_date</em>カラムの値を設定します。 
   *
   * @@param p_execDate <em>exec_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setExecDate( java.sql.Timestamp p_execDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exec_date = p_execDate;
    exec_date_is_set = true;
    exec_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>exec_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setExecDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    exec_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    exec_date_is_set = true;
    exec_date_is_modified = true;
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
   * <em>stock_exchg</em>カラムの値を設定します。 
   *
   * @@param p_stockExchg <em>stock_exchg</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setStockExchg( String p_stockExchg )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stock_exchg = p_stockExchg;
    stock_exchg_is_set = true;
    stock_exchg_is_modified = true;
  }


  /** 
   * <em>trade_type</em>カラムの値を設定します。 
   *
   * @@param p_tradeType <em>trade_type</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setTradeType( String p_tradeType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trade_type = p_tradeType;
    trade_type_is_set = true;
    trade_type_is_modified = true;
  }


  /** 
   * <em>buy_sell_div</em>カラムの値を設定します。 
   *
   * @@param p_buySellDiv <em>buy_sell_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setBuySellDiv( String p_buySellDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_sell_div = p_buySellDiv;
    buy_sell_div_is_set = true;
    buy_sell_div_is_modified = true;
  }


  /** 
   * <em>debit_bal_div</em>カラムの値を設定します。 
   *
   * @@param p_debitBalDiv <em>debit_bal_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDebitBalDiv( String p_debitBalDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    debit_bal_div = p_debitBalDiv;
    debit_bal_div_is_set = true;
    debit_bal_div_is_modified = true;
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
   * <em>price</em>カラムの値を設定します。 
   *
   * @@param p_price <em>price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPrice( double p_price )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    price = new Double(p_price);
    price_is_set = true;
    price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>price</em>カラムに値を設定します。 
   */
  public final void setPrice( Double p_price )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    price = p_price;
    price_is_set = true;
    price_is_modified = true;
  }


  /** 
   * <em>ccy_code</em>カラムの値を設定します。 
   *
   * @@param p_ccyCode <em>ccy_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setCcyCode( String p_ccyCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ccy_code = p_ccyCode;
    ccy_code_is_set = true;
    ccy_code_is_modified = true;
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
   * <em>calc_amount</em>カラムの値を設定します。 
   *
   * @@param p_calcAmount <em>calc_amount</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCalcAmount( double p_calcAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    calc_amount = new Double(p_calcAmount);
    calc_amount_is_set = true;
    calc_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>calc_amount</em>カラムに値を設定します。 
   */
  public final void setCalcAmount( Double p_calcAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    calc_amount = p_calcAmount;
    calc_amount_is_set = true;
    calc_amount_is_modified = true;
  }


  /** 
   * <em>bal_quantity</em>カラムの値を設定します。 
   *
   * @@param p_balQuantity <em>bal_quantity</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setBalQuantity( double p_balQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bal_quantity = new Double(p_balQuantity);
    bal_quantity_is_set = true;
    bal_quantity_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>bal_quantity</em>カラムに値を設定します。 
   */
  public final void setBalQuantity( Double p_balQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    bal_quantity = p_balQuantity;
    bal_quantity_is_set = true;
    bal_quantity_is_modified = true;
  }


  /** 
   * <em>book_value</em>カラムの値を設定します。 
   *
   * @@param p_bookValue <em>book_value</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setBookValue( double p_bookValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    book_value = new Double(p_bookValue);
    book_value_is_set = true;
    book_value_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>book_value</em>カラムに値を設定します。 
   */
  public final void setBookValue( Double p_bookValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    book_value = p_bookValue;
    book_value_is_set = true;
    book_value_is_modified = true;
  }


  /** 
   * <em>book_amount</em>カラムの値を設定します。 
   *
   * @@param p_bookAmount <em>book_amount</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setBookAmount( double p_bookAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    book_amount = new Double(p_bookAmount);
    book_amount_is_set = true;
    book_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>book_amount</em>カラムに値を設定します。 
   */
  public final void setBookAmount( Double p_bookAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    book_amount = p_bookAmount;
    book_amount_is_set = true;
    book_amount_is_modified = true;
  }


  /** 
   * <em>proloss1</em>カラムの値を設定します。 
   *
   * @@param p_proloss1 <em>proloss1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setProloss1( double p_proloss1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    proloss1 = new Double(p_proloss1);
    proloss1_is_set = true;
    proloss1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>proloss1</em>カラムに値を設定します。 
   */
  public final void setProloss1( Double p_proloss1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    proloss1 = p_proloss1;
    proloss1_is_set = true;
    proloss1_is_modified = true;
  }


  /** 
   * <em>proloss2</em>カラムの値を設定します。 
   *
   * @@param p_proloss2 <em>proloss2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setProloss2( double p_proloss2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    proloss2 = new Double(p_proloss2);
    proloss2_is_set = true;
    proloss2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>proloss2</em>カラムに値を設定します。 
   */
  public final void setProloss2( Double p_proloss2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    proloss2 = p_proloss2;
    proloss2_is_set = true;
    proloss2_is_modified = true;
  }


  /** 
   * <em>remark</em>カラムの値を設定します。 
   *
   * @@param p_remark <em>remark</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setRemark( String p_remark )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    remark = p_remark;
    remark_is_set = true;
    remark_is_modified = true;
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
                if ( name.equals("account_code") ) {
                    return this.account_code;
                }
                break;
            case 'b':
                if ( name.equals("book_value_spec_id") ) {
                    return new Long( book_value_spec_id );
                }
                else if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                else if ( name.equals("buy_sell_div") ) {
                    return this.buy_sell_div;
                }
                else if ( name.equals("bal_quantity") ) {
                    return this.bal_quantity;
                }
                else if ( name.equals("book_value") ) {
                    return this.book_value;
                }
                else if ( name.equals("book_amount") ) {
                    return this.book_amount;
                }
                break;
            case 'c':
                if ( name.equals("commodity_code") ) {
                    return this.commodity_code;
                }
                else if ( name.equals("calc_date") ) {
                    return this.calc_date;
                }
                else if ( name.equals("ccy_code") ) {
                    return this.ccy_code;
                }
                else if ( name.equals("calc_amount") ) {
                    return this.calc_amount;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("delivery_date") ) {
                    return this.delivery_date;
                }
                else if ( name.equals("debit_bal_div") ) {
                    return this.debit_bal_div;
                }
                break;
            case 'e':
                if ( name.equals("exec_date") ) {
                    return this.exec_date;
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
            case 'n':
                if ( name.equals("net_amount") ) {
                    return this.net_amount;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    return this.product_code;
                }
                else if ( name.equals("price") ) {
                    return this.price;
                }
                else if ( name.equals("proloss1") ) {
                    return this.proloss1;
                }
                else if ( name.equals("proloss2") ) {
                    return this.proloss2;
                }
                break;
            case 'q':
                if ( name.equals("quantity") ) {
                    return this.quantity;
                }
                break;
            case 'r':
                if ( name.equals("rec_div") ) {
                    return this.rec_div;
                }
                else if ( name.equals("remark") ) {
                    return this.remark;
                }
                break;
            case 's':
                if ( name.equals("sort_no") ) {
                    return this.sort_no;
                }
                else if ( name.equals("stock_exchg") ) {
                    return this.stock_exchg;
                }
                break;
            case 't':
                if ( name.equals("tax_type") ) {
                    return this.tax_type;
                }
                else if ( name.equals("trade_type") ) {
                    return this.trade_type;
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
                if ( name.equals("book_value_spec_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'book_value_spec_id' must be Long: '"+value+"' is not." );
                    this.book_value_spec_id = ((Long) value).longValue();
                    if (this.book_value_spec_id_is_set)
                        this.book_value_spec_id_is_modified = true;
                    this.book_value_spec_id_is_set = true;
                    return;
                }
                else if ( name.equals("branch_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'branch_code' must be String: '"+value+"' is not." );
                    this.branch_code = (String) value;
                    if (this.branch_code_is_set)
                        this.branch_code_is_modified = true;
                    this.branch_code_is_set = true;
                    return;
                }
                else if ( name.equals("buy_sell_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'buy_sell_div' must be String: '"+value+"' is not." );
                    this.buy_sell_div = (String) value;
                    if (this.buy_sell_div_is_set)
                        this.buy_sell_div_is_modified = true;
                    this.buy_sell_div_is_set = true;
                    return;
                }
                else if ( name.equals("bal_quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'bal_quantity' must be Double: '"+value+"' is not." );
                    this.bal_quantity = (Double) value;
                    if (this.bal_quantity_is_set)
                        this.bal_quantity_is_modified = true;
                    this.bal_quantity_is_set = true;
                    return;
                }
                else if ( name.equals("book_value") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'book_value' must be Double: '"+value+"' is not." );
                    this.book_value = (Double) value;
                    if (this.book_value_is_set)
                        this.book_value_is_modified = true;
                    this.book_value_is_set = true;
                    return;
                }
                else if ( name.equals("book_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'book_amount' must be Double: '"+value+"' is not." );
                    this.book_amount = (Double) value;
                    if (this.book_amount_is_set)
                        this.book_amount_is_modified = true;
                    this.book_amount_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("commodity_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'commodity_code' must be String: '"+value+"' is not." );
                    this.commodity_code = (String) value;
                    if (this.commodity_code_is_set)
                        this.commodity_code_is_modified = true;
                    this.commodity_code_is_set = true;
                    return;
                }
                else if ( name.equals("calc_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'calc_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.calc_date = (java.sql.Timestamp) value;
                    if (this.calc_date_is_set)
                        this.calc_date_is_modified = true;
                    this.calc_date_is_set = true;
                    return;
                }
                else if ( name.equals("ccy_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ccy_code' must be String: '"+value+"' is not." );
                    this.ccy_code = (String) value;
                    if (this.ccy_code_is_set)
                        this.ccy_code_is_modified = true;
                    this.ccy_code_is_set = true;
                    return;
                }
                else if ( name.equals("calc_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'calc_amount' must be Double: '"+value+"' is not." );
                    this.calc_amount = (Double) value;
                    if (this.calc_amount_is_set)
                        this.calc_amount_is_modified = true;
                    this.calc_amount_is_set = true;
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
                else if ( name.equals("debit_bal_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'debit_bal_div' must be String: '"+value+"' is not." );
                    this.debit_bal_div = (String) value;
                    if (this.debit_bal_div_is_set)
                        this.debit_bal_div_is_modified = true;
                    this.debit_bal_div_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("exec_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'exec_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.exec_date = (java.sql.Timestamp) value;
                    if (this.exec_date_is_set)
                        this.exec_date_is_modified = true;
                    this.exec_date_is_set = true;
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
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'price' must be Double: '"+value+"' is not." );
                    this.price = (Double) value;
                    if (this.price_is_set)
                        this.price_is_modified = true;
                    this.price_is_set = true;
                    return;
                }
                else if ( name.equals("proloss1") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'proloss1' must be Double: '"+value+"' is not." );
                    this.proloss1 = (Double) value;
                    if (this.proloss1_is_set)
                        this.proloss1_is_modified = true;
                    this.proloss1_is_set = true;
                    return;
                }
                else if ( name.equals("proloss2") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'proloss2' must be Double: '"+value+"' is not." );
                    this.proloss2 = (Double) value;
                    if (this.proloss2_is_set)
                        this.proloss2_is_modified = true;
                    this.proloss2_is_set = true;
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
                if ( name.equals("rec_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'rec_div' must be String: '"+value+"' is not." );
                    this.rec_div = (String) value;
                    if (this.rec_div_is_set)
                        this.rec_div_is_modified = true;
                    this.rec_div_is_set = true;
                    return;
                }
                else if ( name.equals("remark") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'remark' must be String: '"+value+"' is not." );
                    this.remark = (String) value;
                    if (this.remark_is_set)
                        this.remark_is_modified = true;
                    this.remark_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sort_no") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sort_no' must be String: '"+value+"' is not." );
                    this.sort_no = (String) value;
                    if (this.sort_no_is_set)
                        this.sort_no_is_modified = true;
                    this.sort_no_is_set = true;
                    return;
                }
                else if ( name.equals("stock_exchg") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stock_exchg' must be String: '"+value+"' is not." );
                    this.stock_exchg = (String) value;
                    if (this.stock_exchg_is_set)
                        this.stock_exchg_is_modified = true;
                    this.stock_exchg_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("tax_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'tax_type' must be String: '"+value+"' is not." );
                    this.tax_type = (String) value;
                    if (this.tax_type_is_set)
                        this.tax_type_is_modified = true;
                    this.tax_type_is_set = true;
                    return;
                }
                else if ( name.equals("trade_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trade_type' must be String: '"+value+"' is not." );
                    this.trade_type = (String) value;
                    if (this.trade_type_is_set)
                        this.trade_type_is_modified = true;
                    this.trade_type_is_set = true;
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
