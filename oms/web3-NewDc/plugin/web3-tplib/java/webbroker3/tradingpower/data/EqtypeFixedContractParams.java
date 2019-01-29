head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.28.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	EqtypeFixedContractParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpower.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * eqtype_fixed_contractテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link EqtypeFixedContractRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link EqtypeFixedContractParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link EqtypeFixedContractParams}が{@@link EqtypeFixedContractRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see EqtypeFixedContractPK 
 * @@see EqtypeFixedContractRow 
 */
public class EqtypeFixedContractParams extends Params implements EqtypeFixedContractRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "eqtype_fixed_contract";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = EqtypeFixedContractRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return EqtypeFixedContractRow.TYPE;
  }


  /** 
   * <em>fixed_contract_id</em>カラムの値 
   */
  public  long  fixed_contract_id;

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
   * <em>name_transfer_fee</em>カラムの値 
   */
  public  Double  name_transfer_fee;

  /** 
   * <em>name_transfer_fee_tax</em>カラムの値 
   */
  public  Double  name_transfer_fee_tax;

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
   * <em>pay_interest_fee</em>カラムの値 
   */
  public  Double  pay_interest_fee;

  /** 
   * <em>pay_interest_fee_tax</em>カラムの値 
   */
  public  Double  pay_interest_fee_tax;

  /** 
   * <em>loan_equity_fee</em>カラムの値 
   */
  public  Double  loan_equity_fee;

  /** 
   * <em>other</em>カラムの値 
   */
  public  Double  other;

  /** 
   * <em>margin_deposit_rate</em>カラムの値 
   */
  public  Double  margin_deposit_rate;

  /** 
   * <em>cash_margin_deposit_rate</em>カラムの値 
   */
  public  Double  cash_margin_deposit_rate;

  /** 
   * <em>contract_asset_profit_loss</em>カラムの値 
   */
  public  Double  contract_asset_profit_loss;

  /** 
   * <em>product_id</em>カラムの値 
   */
  public  long  product_id;

  /** 
   * <em>tax_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum  tax_type;

  /** 
   * <em>repayment_type</em>カラムの値 
   */
  public  String  repayment_type;

  /** 
   * <em>repayment_num</em>カラムの値 
   */
  public  Integer  repayment_num;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>first_open_date</em>カラムの値 
   */
  public  java.sql.Timestamp  first_open_date;

  private boolean fixed_contract_id_is_set = false;
  private boolean fixed_contract_id_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean sub_account_id_is_set = false;
  private boolean sub_account_id_is_modified = false;
  private boolean market_id_is_set = false;
  private boolean market_id_is_modified = false;
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
  private boolean name_transfer_fee_is_set = false;
  private boolean name_transfer_fee_is_modified = false;
  private boolean name_transfer_fee_tax_is_set = false;
  private boolean name_transfer_fee_tax_is_modified = false;
  private boolean management_fee_is_set = false;
  private boolean management_fee_is_modified = false;
  private boolean management_fee_tax_is_set = false;
  private boolean management_fee_tax_is_modified = false;
  private boolean interest_fee_is_set = false;
  private boolean interest_fee_is_modified = false;
  private boolean interest_fee_tax_is_set = false;
  private boolean interest_fee_tax_is_modified = false;
  private boolean pay_interest_fee_is_set = false;
  private boolean pay_interest_fee_is_modified = false;
  private boolean pay_interest_fee_tax_is_set = false;
  private boolean pay_interest_fee_tax_is_modified = false;
  private boolean loan_equity_fee_is_set = false;
  private boolean loan_equity_fee_is_modified = false;
  private boolean other_is_set = false;
  private boolean other_is_modified = false;
  private boolean margin_deposit_rate_is_set = false;
  private boolean margin_deposit_rate_is_modified = false;
  private boolean cash_margin_deposit_rate_is_set = false;
  private boolean cash_margin_deposit_rate_is_modified = false;
  private boolean contract_asset_profit_loss_is_set = false;
  private boolean contract_asset_profit_loss_is_modified = false;
  private boolean product_id_is_set = false;
  private boolean product_id_is_modified = false;
  private boolean tax_type_is_set = false;
  private boolean tax_type_is_modified = false;
  private boolean repayment_type_is_set = false;
  private boolean repayment_type_is_modified = false;
  private boolean repayment_num_is_set = false;
  private boolean repayment_num_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean first_open_date_is_set = false;
  private boolean first_open_date_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "fixed_contract_id=" + fixed_contract_id
      + "," + "account_id=" +account_id
      + "," + "sub_account_id=" +sub_account_id
      + "," + "market_id=" +market_id
      + "," + "original_quantity=" +original_quantity
      + "," + "quantity=" +quantity
      + "," + "original_contract_price=" +original_contract_price
      + "," + "contract_price=" +contract_price
      + "," + "contract_type=" +contract_type
      + "," + "open_date=" +open_date
      + "," + "close_date=" +close_date
      + "," + "setup_fee=" +setup_fee
      + "," + "setup_fee_tax=" +setup_fee_tax
      + "," + "name_transfer_fee=" +name_transfer_fee
      + "," + "name_transfer_fee_tax=" +name_transfer_fee_tax
      + "," + "management_fee=" +management_fee
      + "," + "management_fee_tax=" +management_fee_tax
      + "," + "interest_fee=" +interest_fee
      + "," + "interest_fee_tax=" +interest_fee_tax
      + "," + "pay_interest_fee=" +pay_interest_fee
      + "," + "pay_interest_fee_tax=" +pay_interest_fee_tax
      + "," + "loan_equity_fee=" +loan_equity_fee
      + "," + "other=" +other
      + "," + "margin_deposit_rate=" +margin_deposit_rate
      + "," + "cash_margin_deposit_rate=" +cash_margin_deposit_rate
      + "," + "contract_asset_profit_loss=" +contract_asset_profit_loss
      + "," + "product_id=" +product_id
      + "," + "tax_type=" +tax_type
      + "," + "repayment_type=" +repayment_type
      + "," + "repayment_num=" +repayment_num
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "first_open_date=" +first_open_date
      + "}";
  }


  /** 
   * 値が未設定のEqtypeFixedContractParamsオブジェクトを作成します。 
   */
  public EqtypeFixedContractParams() {
    fixed_contract_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のEqtypeFixedContractRowオブジェクトの値を利用してEqtypeFixedContractParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するEqtypeFixedContractRowオブジェクト 
   */
  public EqtypeFixedContractParams( EqtypeFixedContractRow p_row )
  {
    fixed_contract_id = p_row.getFixedContractId();
    fixed_contract_id_is_set = p_row.getFixedContractIdIsSet();
    fixed_contract_id_is_modified = p_row.getFixedContractIdIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    sub_account_id = p_row.getSubAccountId();
    sub_account_id_is_set = p_row.getSubAccountIdIsSet();
    sub_account_id_is_modified = p_row.getSubAccountIdIsModified();
    market_id = p_row.getMarketId();
    market_id_is_set = p_row.getMarketIdIsSet();
    market_id_is_modified = p_row.getMarketIdIsModified();
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
    if ( !p_row.getNameTransferFeeIsNull() )
      name_transfer_fee = new Double( p_row.getNameTransferFee() );
    name_transfer_fee_is_set = p_row.getNameTransferFeeIsSet();
    name_transfer_fee_is_modified = p_row.getNameTransferFeeIsModified();
    if ( !p_row.getNameTransferFeeTaxIsNull() )
      name_transfer_fee_tax = new Double( p_row.getNameTransferFeeTax() );
    name_transfer_fee_tax_is_set = p_row.getNameTransferFeeTaxIsSet();
    name_transfer_fee_tax_is_modified = p_row.getNameTransferFeeTaxIsModified();
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
    if ( !p_row.getPayInterestFeeIsNull() )
      pay_interest_fee = new Double( p_row.getPayInterestFee() );
    pay_interest_fee_is_set = p_row.getPayInterestFeeIsSet();
    pay_interest_fee_is_modified = p_row.getPayInterestFeeIsModified();
    if ( !p_row.getPayInterestFeeTaxIsNull() )
      pay_interest_fee_tax = new Double( p_row.getPayInterestFeeTax() );
    pay_interest_fee_tax_is_set = p_row.getPayInterestFeeTaxIsSet();
    pay_interest_fee_tax_is_modified = p_row.getPayInterestFeeTaxIsModified();
    if ( !p_row.getLoanEquityFeeIsNull() )
      loan_equity_fee = new Double( p_row.getLoanEquityFee() );
    loan_equity_fee_is_set = p_row.getLoanEquityFeeIsSet();
    loan_equity_fee_is_modified = p_row.getLoanEquityFeeIsModified();
    if ( !p_row.getOtherIsNull() )
      other = new Double( p_row.getOther() );
    other_is_set = p_row.getOtherIsSet();
    other_is_modified = p_row.getOtherIsModified();
    if ( !p_row.getMarginDepositRateIsNull() )
      margin_deposit_rate = new Double( p_row.getMarginDepositRate() );
    margin_deposit_rate_is_set = p_row.getMarginDepositRateIsSet();
    margin_deposit_rate_is_modified = p_row.getMarginDepositRateIsModified();
    if ( !p_row.getCashMarginDepositRateIsNull() )
      cash_margin_deposit_rate = new Double( p_row.getCashMarginDepositRate() );
    cash_margin_deposit_rate_is_set = p_row.getCashMarginDepositRateIsSet();
    cash_margin_deposit_rate_is_modified = p_row.getCashMarginDepositRateIsModified();
    if ( !p_row.getContractAssetProfitLossIsNull() )
      contract_asset_profit_loss = new Double( p_row.getContractAssetProfitLoss() );
    contract_asset_profit_loss_is_set = p_row.getContractAssetProfitLossIsSet();
    contract_asset_profit_loss_is_modified = p_row.getContractAssetProfitLossIsModified();
    product_id = p_row.getProductId();
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    tax_type = p_row.getTaxType();
    tax_type_is_set = p_row.getTaxTypeIsSet();
    tax_type_is_modified = p_row.getTaxTypeIsModified();
    repayment_type = p_row.getRepaymentType();
    repayment_type_is_set = p_row.getRepaymentTypeIsSet();
    repayment_type_is_modified = p_row.getRepaymentTypeIsModified();
    if ( !p_row.getRepaymentNumIsNull() )
      repayment_num = new Integer( p_row.getRepaymentNum() );
    repayment_num_is_set = p_row.getRepaymentNumIsSet();
    repayment_num_is_modified = p_row.getRepaymentNumIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    first_open_date = p_row.getFirstOpenDate();
    first_open_date_is_set = p_row.getFirstOpenDateIsSet();
    first_open_date_is_modified = p_row.getFirstOpenDateIsModified();
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
      name_transfer_fee_is_set = true;
      name_transfer_fee_is_modified = true;
      name_transfer_fee_tax_is_set = true;
      name_transfer_fee_tax_is_modified = true;
      management_fee_is_set = true;
      management_fee_is_modified = true;
      management_fee_tax_is_set = true;
      management_fee_tax_is_modified = true;
      interest_fee_is_set = true;
      interest_fee_is_modified = true;
      interest_fee_tax_is_set = true;
      interest_fee_tax_is_modified = true;
      pay_interest_fee_is_set = true;
      pay_interest_fee_is_modified = true;
      pay_interest_fee_tax_is_set = true;
      pay_interest_fee_tax_is_modified = true;
      loan_equity_fee_is_set = true;
      loan_equity_fee_is_modified = true;
      other_is_set = true;
      other_is_modified = true;
      margin_deposit_rate_is_set = true;
      margin_deposit_rate_is_modified = true;
      cash_margin_deposit_rate_is_set = true;
      cash_margin_deposit_rate_is_modified = true;
      contract_asset_profit_loss_is_set = true;
      contract_asset_profit_loss_is_modified = true;
      product_id_is_set = true;
      product_id_is_modified = true;
      tax_type_is_set = true;
      tax_type_is_modified = true;
      repayment_type_is_set = true;
      repayment_type_is_modified = true;
      repayment_num_is_set = true;
      repayment_num_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      first_open_date_is_set = true;
      first_open_date_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof EqtypeFixedContractRow ) )
       return false;
    return fieldsEqual( (EqtypeFixedContractRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のEqtypeFixedContractRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( EqtypeFixedContractRow row )
  {
    if ( fixed_contract_id != row.getFixedContractId() )
      return false;
    if ( account_id != row.getAccountId() )
      return false;
    if ( sub_account_id != row.getSubAccountId() )
      return false;
    if ( market_id != row.getMarketId() )
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
    if ( name_transfer_fee == null ) {
      if ( !row.getNameTransferFeeIsNull() )
        return false;
    } else if ( row.getNameTransferFeeIsNull() || ( name_transfer_fee.doubleValue() != row.getNameTransferFee() ) ) {
        return false;
    }
    if ( name_transfer_fee_tax == null ) {
      if ( !row.getNameTransferFeeTaxIsNull() )
        return false;
    } else if ( row.getNameTransferFeeTaxIsNull() || ( name_transfer_fee_tax.doubleValue() != row.getNameTransferFeeTax() ) ) {
        return false;
    }
    if ( management_fee != row.getManagementFee() )
      return false;
    if ( management_fee_tax != row.getManagementFeeTax() )
      return false;
    if ( interest_fee != row.getInterestFee() )
      return false;
    if ( interest_fee_tax != row.getInterestFeeTax() )
      return false;
    if ( pay_interest_fee == null ) {
      if ( !row.getPayInterestFeeIsNull() )
        return false;
    } else if ( row.getPayInterestFeeIsNull() || ( pay_interest_fee.doubleValue() != row.getPayInterestFee() ) ) {
        return false;
    }
    if ( pay_interest_fee_tax == null ) {
      if ( !row.getPayInterestFeeTaxIsNull() )
        return false;
    } else if ( row.getPayInterestFeeTaxIsNull() || ( pay_interest_fee_tax.doubleValue() != row.getPayInterestFeeTax() ) ) {
        return false;
    }
    if ( loan_equity_fee == null ) {
      if ( !row.getLoanEquityFeeIsNull() )
        return false;
    } else if ( row.getLoanEquityFeeIsNull() || ( loan_equity_fee.doubleValue() != row.getLoanEquityFee() ) ) {
        return false;
    }
    if ( other == null ) {
      if ( !row.getOtherIsNull() )
        return false;
    } else if ( row.getOtherIsNull() || ( other.doubleValue() != row.getOther() ) ) {
        return false;
    }
    if ( margin_deposit_rate == null ) {
      if ( !row.getMarginDepositRateIsNull() )
        return false;
    } else if ( row.getMarginDepositRateIsNull() || ( margin_deposit_rate.doubleValue() != row.getMarginDepositRate() ) ) {
        return false;
    }
    if ( cash_margin_deposit_rate == null ) {
      if ( !row.getCashMarginDepositRateIsNull() )
        return false;
    } else if ( row.getCashMarginDepositRateIsNull() || ( cash_margin_deposit_rate.doubleValue() != row.getCashMarginDepositRate() ) ) {
        return false;
    }
    if ( contract_asset_profit_loss == null ) {
      if ( !row.getContractAssetProfitLossIsNull() )
        return false;
    } else if ( row.getContractAssetProfitLossIsNull() || ( contract_asset_profit_loss.doubleValue() != row.getContractAssetProfitLoss() ) ) {
        return false;
    }
    if ( product_id != row.getProductId() )
      return false;
    if ( tax_type == null ) {
      if ( row.getTaxType() != null )
        return false;
    } else if ( !tax_type.equals( row.getTaxType() ) ) {
        return false;
    }
    if ( repayment_type == null ) {
      if ( row.getRepaymentType() != null )
        return false;
    } else if ( !repayment_type.equals( row.getRepaymentType() ) ) {
        return false;
    }
    if ( repayment_num == null ) {
      if ( !row.getRepaymentNumIsNull() )
        return false;
    } else if ( row.getRepaymentNumIsNull() || ( repayment_num.intValue() != row.getRepaymentNum() ) ) {
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
    if ( first_open_date == null ) {
      if ( row.getFirstOpenDate() != null )
        return false;
    } else if ( !first_open_date.equals( row.getFirstOpenDate() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  ((int) fixed_contract_id)
        + ((int) account_id)
        + ((int) sub_account_id)
        + ((int) market_id)
        + ((int) original_quantity)
        + ((int) quantity)
        + ((int) original_contract_price)
        + ((int) contract_price)
        + (contract_type!=null? contract_type.hashCode(): 0) 
        + (open_date!=null? open_date.hashCode(): 0) 
        + (close_date!=null? close_date.hashCode(): 0) 
        + ((int) setup_fee)
        + ((int) setup_fee_tax)
        + (name_transfer_fee!=null? name_transfer_fee.hashCode(): 0) 
        + (name_transfer_fee_tax!=null? name_transfer_fee_tax.hashCode(): 0) 
        + ((int) management_fee)
        + ((int) management_fee_tax)
        + ((int) interest_fee)
        + ((int) interest_fee_tax)
        + (pay_interest_fee!=null? pay_interest_fee.hashCode(): 0) 
        + (pay_interest_fee_tax!=null? pay_interest_fee_tax.hashCode(): 0) 
        + (loan_equity_fee!=null? loan_equity_fee.hashCode(): 0) 
        + (other!=null? other.hashCode(): 0) 
        + (margin_deposit_rate!=null? margin_deposit_rate.hashCode(): 0) 
        + (cash_margin_deposit_rate!=null? cash_margin_deposit_rate.hashCode(): 0) 
        + (contract_asset_profit_loss!=null? contract_asset_profit_loss.hashCode(): 0) 
        + ((int) product_id)
        + (tax_type!=null? tax_type.hashCode(): 0) 
        + (repayment_type!=null? repayment_type.hashCode(): 0) 
        + (repayment_num!=null? repayment_num.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (first_open_date!=null? first_open_date.hashCode(): 0) 
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
		if ( !first_open_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'first_open_date' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("fixed_contract_id",new Long(fixed_contract_id));
		map.put("account_id",new Long(account_id));
		map.put("sub_account_id",new Long(sub_account_id));
		map.put("market_id",new Long(market_id));
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
		if ( name_transfer_fee != null )
			map.put("name_transfer_fee",name_transfer_fee);
		if ( name_transfer_fee_tax != null )
			map.put("name_transfer_fee_tax",name_transfer_fee_tax);
		if ( management_fee_is_set )
			map.put("management_fee",new Double(management_fee));
		if ( management_fee_tax_is_set )
			map.put("management_fee_tax",new Double(management_fee_tax));
		if ( interest_fee_is_set )
			map.put("interest_fee",new Double(interest_fee));
		if ( interest_fee_tax_is_set )
			map.put("interest_fee_tax",new Double(interest_fee_tax));
		if ( pay_interest_fee != null )
			map.put("pay_interest_fee",pay_interest_fee);
		if ( pay_interest_fee_tax != null )
			map.put("pay_interest_fee_tax",pay_interest_fee_tax);
		if ( loan_equity_fee != null )
			map.put("loan_equity_fee",loan_equity_fee);
		if ( other != null )
			map.put("other",other);
		if ( margin_deposit_rate != null )
			map.put("margin_deposit_rate",margin_deposit_rate);
		if ( cash_margin_deposit_rate != null )
			map.put("cash_margin_deposit_rate",cash_margin_deposit_rate);
		if ( contract_asset_profit_loss != null )
			map.put("contract_asset_profit_loss",contract_asset_profit_loss);
		map.put("product_id",new Long(product_id));
		if ( tax_type != null )
			map.put("tax_type",tax_type);
		if ( repayment_type != null )
			map.put("repayment_type",repayment_type);
		if ( repayment_num != null )
			map.put("repayment_num",repayment_num);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		map.put("first_open_date",first_open_date);
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
		if ( name_transfer_fee_is_modified )
			map.put("name_transfer_fee",name_transfer_fee);
		if ( name_transfer_fee_tax_is_modified )
			map.put("name_transfer_fee_tax",name_transfer_fee_tax);
		if ( management_fee_is_modified )
			map.put("management_fee",new Double(management_fee));
		if ( management_fee_tax_is_modified )
			map.put("management_fee_tax",new Double(management_fee_tax));
		if ( interest_fee_is_modified )
			map.put("interest_fee",new Double(interest_fee));
		if ( interest_fee_tax_is_modified )
			map.put("interest_fee_tax",new Double(interest_fee_tax));
		if ( pay_interest_fee_is_modified )
			map.put("pay_interest_fee",pay_interest_fee);
		if ( pay_interest_fee_tax_is_modified )
			map.put("pay_interest_fee_tax",pay_interest_fee_tax);
		if ( loan_equity_fee_is_modified )
			map.put("loan_equity_fee",loan_equity_fee);
		if ( other_is_modified )
			map.put("other",other);
		if ( margin_deposit_rate_is_modified )
			map.put("margin_deposit_rate",margin_deposit_rate);
		if ( cash_margin_deposit_rate_is_modified )
			map.put("cash_margin_deposit_rate",cash_margin_deposit_rate);
		if ( contract_asset_profit_loss_is_modified )
			map.put("contract_asset_profit_loss",contract_asset_profit_loss);
		if ( product_id_is_modified )
			map.put("product_id",new Long(product_id));
		if ( tax_type_is_modified )
			map.put("tax_type",tax_type);
		if ( repayment_type_is_modified )
			map.put("repayment_type",repayment_type);
		if ( repayment_num_is_modified )
			map.put("repayment_num",repayment_num);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( first_open_date_is_modified )
			map.put("first_open_date",first_open_date);
		if (map.size() == 0) {
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			if ( sub_account_id_is_set )
				map.put("sub_account_id",new Long(sub_account_id));
			if ( market_id_is_set )
				map.put("market_id",new Long(market_id));
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
			map.put("name_transfer_fee",name_transfer_fee);
			map.put("name_transfer_fee_tax",name_transfer_fee_tax);
			if ( management_fee_is_set )
				map.put("management_fee",new Double(management_fee));
			if ( management_fee_tax_is_set )
				map.put("management_fee_tax",new Double(management_fee_tax));
			if ( interest_fee_is_set )
				map.put("interest_fee",new Double(interest_fee));
			if ( interest_fee_tax_is_set )
				map.put("interest_fee_tax",new Double(interest_fee_tax));
			map.put("pay_interest_fee",pay_interest_fee);
			map.put("pay_interest_fee_tax",pay_interest_fee_tax);
			map.put("loan_equity_fee",loan_equity_fee);
			map.put("other",other);
			map.put("margin_deposit_rate",margin_deposit_rate);
			map.put("cash_margin_deposit_rate",cash_margin_deposit_rate);
			map.put("contract_asset_profit_loss",contract_asset_profit_loss);
			if ( product_id_is_set )
				map.put("product_id",new Long(product_id));
			map.put("tax_type",tax_type);
			map.put("repayment_type",repayment_type);
			map.put("repayment_num",repayment_num);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			if ( first_open_date_is_set )
				map.put("first_open_date",first_open_date);
		}
		return map;
	}


  /** 
   * <em>fixed_contract_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getFixedContractId()
  {
    return fixed_contract_id;
  }


  /** 
   * <em>fixed_contract_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFixedContractIdIsSet() {
    return fixed_contract_id_is_set;
  }


  /** 
   * <em>fixed_contract_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFixedContractIdIsModified() {
    return fixed_contract_id_is_modified;
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
   * <em>name_transfer_fee</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getNameTransferFee()
  {
    return ( name_transfer_fee==null? ((double)0): name_transfer_fee.doubleValue() );
  }


  /** 
   * <em>name_transfer_fee</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getNameTransferFeeIsNull()
  {
    return name_transfer_fee == null;
  }


  /** 
   * <em>name_transfer_fee</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNameTransferFeeIsSet() {
    return name_transfer_fee_is_set;
  }


  /** 
   * <em>name_transfer_fee</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNameTransferFeeIsModified() {
    return name_transfer_fee_is_modified;
  }


  /** 
   * <em>name_transfer_fee_tax</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getNameTransferFeeTax()
  {
    return ( name_transfer_fee_tax==null? ((double)0): name_transfer_fee_tax.doubleValue() );
  }


  /** 
   * <em>name_transfer_fee_tax</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getNameTransferFeeTaxIsNull()
  {
    return name_transfer_fee_tax == null;
  }


  /** 
   * <em>name_transfer_fee_tax</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNameTransferFeeTaxIsSet() {
    return name_transfer_fee_tax_is_set;
  }


  /** 
   * <em>name_transfer_fee_tax</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNameTransferFeeTaxIsModified() {
    return name_transfer_fee_tax_is_modified;
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
   * <em>pay_interest_fee</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPayInterestFee()
  {
    return ( pay_interest_fee==null? ((double)0): pay_interest_fee.doubleValue() );
  }


  /** 
   * <em>pay_interest_fee</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPayInterestFeeIsNull()
  {
    return pay_interest_fee == null;
  }


  /** 
   * <em>pay_interest_fee</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPayInterestFeeIsSet() {
    return pay_interest_fee_is_set;
  }


  /** 
   * <em>pay_interest_fee</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPayInterestFeeIsModified() {
    return pay_interest_fee_is_modified;
  }


  /** 
   * <em>pay_interest_fee_tax</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPayInterestFeeTax()
  {
    return ( pay_interest_fee_tax==null? ((double)0): pay_interest_fee_tax.doubleValue() );
  }


  /** 
   * <em>pay_interest_fee_tax</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPayInterestFeeTaxIsNull()
  {
    return pay_interest_fee_tax == null;
  }


  /** 
   * <em>pay_interest_fee_tax</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPayInterestFeeTaxIsSet() {
    return pay_interest_fee_tax_is_set;
  }


  /** 
   * <em>pay_interest_fee_tax</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPayInterestFeeTaxIsModified() {
    return pay_interest_fee_tax_is_modified;
  }


  /** 
   * <em>loan_equity_fee</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getLoanEquityFee()
  {
    return ( loan_equity_fee==null? ((double)0): loan_equity_fee.doubleValue() );
  }


  /** 
   * <em>loan_equity_fee</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLoanEquityFeeIsNull()
  {
    return loan_equity_fee == null;
  }


  /** 
   * <em>loan_equity_fee</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLoanEquityFeeIsSet() {
    return loan_equity_fee_is_set;
  }


  /** 
   * <em>loan_equity_fee</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLoanEquityFeeIsModified() {
    return loan_equity_fee_is_modified;
  }


  /** 
   * <em>other</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getOther()
  {
    return ( other==null? ((double)0): other.doubleValue() );
  }


  /** 
   * <em>other</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOtherIsNull()
  {
    return other == null;
  }


  /** 
   * <em>other</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOtherIsSet() {
    return other_is_set;
  }


  /** 
   * <em>other</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOtherIsModified() {
    return other_is_modified;
  }


  /** 
   * <em>margin_deposit_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMarginDepositRate()
  {
    return ( margin_deposit_rate==null? ((double)0): margin_deposit_rate.doubleValue() );
  }


  /** 
   * <em>margin_deposit_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarginDepositRateIsNull()
  {
    return margin_deposit_rate == null;
  }


  /** 
   * <em>margin_deposit_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDepositRateIsSet() {
    return margin_deposit_rate_is_set;
  }


  /** 
   * <em>margin_deposit_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDepositRateIsModified() {
    return margin_deposit_rate_is_modified;
  }


  /** 
   * <em>cash_margin_deposit_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCashMarginDepositRate()
  {
    return ( cash_margin_deposit_rate==null? ((double)0): cash_margin_deposit_rate.doubleValue() );
  }


  /** 
   * <em>cash_margin_deposit_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCashMarginDepositRateIsNull()
  {
    return cash_margin_deposit_rate == null;
  }


  /** 
   * <em>cash_margin_deposit_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashMarginDepositRateIsSet() {
    return cash_margin_deposit_rate_is_set;
  }


  /** 
   * <em>cash_margin_deposit_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashMarginDepositRateIsModified() {
    return cash_margin_deposit_rate_is_modified;
  }


  /** 
   * <em>contract_asset_profit_loss</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractAssetProfitLoss()
  {
    return ( contract_asset_profit_loss==null? ((double)0): contract_asset_profit_loss.doubleValue() );
  }


  /** 
   * <em>contract_asset_profit_loss</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractAssetProfitLossIsNull()
  {
    return contract_asset_profit_loss == null;
  }


  /** 
   * <em>contract_asset_profit_loss</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetProfitLossIsSet() {
    return contract_asset_profit_loss_is_set;
  }


  /** 
   * <em>contract_asset_profit_loss</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetProfitLossIsModified() {
    return contract_asset_profit_loss_is_modified;
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
   * <em>tax_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum getTaxType()
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
   * <em>repayment_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRepaymentType()
  {
    return repayment_type;
  }


  /** 
   * <em>repayment_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRepaymentTypeIsSet() {
    return repayment_type_is_set;
  }


  /** 
   * <em>repayment_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRepaymentTypeIsModified() {
    return repayment_type_is_modified;
  }


  /** 
   * <em>repayment_num</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getRepaymentNum()
  {
    return ( repayment_num==null? ((int)0): repayment_num.intValue() );
  }


  /** 
   * <em>repayment_num</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getRepaymentNumIsNull()
  {
    return repayment_num == null;
  }


  /** 
   * <em>repayment_num</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRepaymentNumIsSet() {
    return repayment_num_is_set;
  }


  /** 
   * <em>repayment_num</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRepaymentNumIsModified() {
    return repayment_num_is_modified;
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
   * <em>first_open_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getFirstOpenDate()
  {
    return first_open_date;
  }


  /** 
   * <em>first_open_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFirstOpenDateIsSet() {
    return first_open_date_is_set;
  }


  /** 
   * <em>first_open_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFirstOpenDateIsModified() {
    return first_open_date_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new EqtypeFixedContractPK(fixed_contract_id);
  }


  /** 
   * <em>fixed_contract_id</em>カラムの値を設定します。 
   *
   * @@param p_fixedContractId <em>fixed_contract_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setFixedContractId( long p_fixedContractId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fixed_contract_id = p_fixedContractId;
    fixed_contract_id_is_set = true;
    fixed_contract_id_is_modified = true;
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
   * <em>name_transfer_fee</em>カラムの値を設定します。 
   *
   * @@param p_nameTransferFee <em>name_transfer_fee</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setNameTransferFee( double p_nameTransferFee )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    name_transfer_fee = new Double(p_nameTransferFee);
    name_transfer_fee_is_set = true;
    name_transfer_fee_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>name_transfer_fee</em>カラムに値を設定します。 
   */
  public final void setNameTransferFee( Double p_nameTransferFee )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    name_transfer_fee = p_nameTransferFee;
    name_transfer_fee_is_set = true;
    name_transfer_fee_is_modified = true;
  }


  /** 
   * <em>name_transfer_fee_tax</em>カラムの値を設定します。 
   *
   * @@param p_nameTransferFeeTax <em>name_transfer_fee_tax</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setNameTransferFeeTax( double p_nameTransferFeeTax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    name_transfer_fee_tax = new Double(p_nameTransferFeeTax);
    name_transfer_fee_tax_is_set = true;
    name_transfer_fee_tax_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>name_transfer_fee_tax</em>カラムに値を設定します。 
   */
  public final void setNameTransferFeeTax( Double p_nameTransferFeeTax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    name_transfer_fee_tax = p_nameTransferFeeTax;
    name_transfer_fee_tax_is_set = true;
    name_transfer_fee_tax_is_modified = true;
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
   * <em>pay_interest_fee</em>カラムの値を設定します。 
   *
   * @@param p_payInterestFee <em>pay_interest_fee</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPayInterestFee( double p_payInterestFee )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    pay_interest_fee = new Double(p_payInterestFee);
    pay_interest_fee_is_set = true;
    pay_interest_fee_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>pay_interest_fee</em>カラムに値を設定します。 
   */
  public final void setPayInterestFee( Double p_payInterestFee )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    pay_interest_fee = p_payInterestFee;
    pay_interest_fee_is_set = true;
    pay_interest_fee_is_modified = true;
  }


  /** 
   * <em>pay_interest_fee_tax</em>カラムの値を設定します。 
   *
   * @@param p_payInterestFeeTax <em>pay_interest_fee_tax</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPayInterestFeeTax( double p_payInterestFeeTax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    pay_interest_fee_tax = new Double(p_payInterestFeeTax);
    pay_interest_fee_tax_is_set = true;
    pay_interest_fee_tax_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>pay_interest_fee_tax</em>カラムに値を設定します。 
   */
  public final void setPayInterestFeeTax( Double p_payInterestFeeTax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    pay_interest_fee_tax = p_payInterestFeeTax;
    pay_interest_fee_tax_is_set = true;
    pay_interest_fee_tax_is_modified = true;
  }


  /** 
   * <em>loan_equity_fee</em>カラムの値を設定します。 
   *
   * @@param p_loanEquityFee <em>loan_equity_fee</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setLoanEquityFee( double p_loanEquityFee )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    loan_equity_fee = new Double(p_loanEquityFee);
    loan_equity_fee_is_set = true;
    loan_equity_fee_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>loan_equity_fee</em>カラムに値を設定します。 
   */
  public final void setLoanEquityFee( Double p_loanEquityFee )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    loan_equity_fee = p_loanEquityFee;
    loan_equity_fee_is_set = true;
    loan_equity_fee_is_modified = true;
  }


  /** 
   * <em>other</em>カラムの値を設定します。 
   *
   * @@param p_other <em>other</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setOther( double p_other )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    other = new Double(p_other);
    other_is_set = true;
    other_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>other</em>カラムに値を設定します。 
   */
  public final void setOther( Double p_other )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    other = p_other;
    other_is_set = true;
    other_is_modified = true;
  }


  /** 
   * <em>margin_deposit_rate</em>カラムの値を設定します。 
   *
   * @@param p_marginDepositRate <em>margin_deposit_rate</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMarginDepositRate( double p_marginDepositRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_deposit_rate = new Double(p_marginDepositRate);
    margin_deposit_rate_is_set = true;
    margin_deposit_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>margin_deposit_rate</em>カラムに値を設定します。 
   */
  public final void setMarginDepositRate( Double p_marginDepositRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_deposit_rate = p_marginDepositRate;
    margin_deposit_rate_is_set = true;
    margin_deposit_rate_is_modified = true;
  }


  /** 
   * <em>cash_margin_deposit_rate</em>カラムの値を設定します。 
   *
   * @@param p_cashMarginDepositRate <em>cash_margin_deposit_rate</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCashMarginDepositRate( double p_cashMarginDepositRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_margin_deposit_rate = new Double(p_cashMarginDepositRate);
    cash_margin_deposit_rate_is_set = true;
    cash_margin_deposit_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>cash_margin_deposit_rate</em>カラムに値を設定します。 
   */
  public final void setCashMarginDepositRate( Double p_cashMarginDepositRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    cash_margin_deposit_rate = p_cashMarginDepositRate;
    cash_margin_deposit_rate_is_set = true;
    cash_margin_deposit_rate_is_modified = true;
  }


  /** 
   * <em>contract_asset_profit_loss</em>カラムの値を設定します。 
   *
   * @@param p_contractAssetProfitLoss <em>contract_asset_profit_loss</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractAssetProfitLoss( double p_contractAssetProfitLoss )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_profit_loss = new Double(p_contractAssetProfitLoss);
    contract_asset_profit_loss_is_set = true;
    contract_asset_profit_loss_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_asset_profit_loss</em>カラムに値を設定します。 
   */
  public final void setContractAssetProfitLoss( Double p_contractAssetProfitLoss )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_profit_loss = p_contractAssetProfitLoss;
    contract_asset_profit_loss_is_set = true;
    contract_asset_profit_loss_is_modified = true;
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
   * <em>tax_type</em>カラムの値を設定します。 
   *
   * @@param p_taxType <em>tax_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum値 
   */
  public final void setTaxType( com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum p_taxType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tax_type = p_taxType;
    tax_type_is_set = true;
    tax_type_is_modified = true;
  }


  /** 
   * <em>repayment_type</em>カラムの値を設定します。 
   *
   * @@param p_repaymentType <em>repayment_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setRepaymentType( String p_repaymentType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    repayment_type = p_repaymentType;
    repayment_type_is_set = true;
    repayment_type_is_modified = true;
  }


  /** 
   * <em>repayment_num</em>カラムの値を設定します。 
   *
   * @@param p_repaymentNum <em>repayment_num</em>カラムの値をあらわす7桁以下のint値 
   */
  public final void setRepaymentNum( int p_repaymentNum )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    repayment_num = new Integer(p_repaymentNum);
    repayment_num_is_set = true;
    repayment_num_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>repayment_num</em>カラムに値を設定します。 
   */
  public final void setRepaymentNum( Integer p_repaymentNum )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    repayment_num = p_repaymentNum;
    repayment_num_is_set = true;
    repayment_num_is_modified = true;
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
   * <em>first_open_date</em>カラムの値を設定します。 
   *
   * @@param p_firstOpenDate <em>first_open_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setFirstOpenDate( java.sql.Timestamp p_firstOpenDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    first_open_date = p_firstOpenDate;
    first_open_date_is_set = true;
    first_open_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>first_open_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setFirstOpenDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    first_open_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    first_open_date_is_set = true;
    first_open_date_is_modified = true;
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
                if ( name.equals("contract_price") ) {
                    return new Double( contract_price );
                }
                else if ( name.equals("contract_type") ) {
                    return this.contract_type;
                }
                else if ( name.equals("close_date") ) {
                    return this.close_date;
                }
                else if ( name.equals("cash_margin_deposit_rate") ) {
                    return this.cash_margin_deposit_rate;
                }
                else if ( name.equals("contract_asset_profit_loss") ) {
                    return this.contract_asset_profit_loss;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'f':
                if ( name.equals("fixed_contract_id") ) {
                    return new Long( fixed_contract_id );
                }
                else if ( name.equals("first_open_date") ) {
                    return this.first_open_date;
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
                if ( name.equals("loan_equity_fee") ) {
                    return this.loan_equity_fee;
                }
                else if ( name.equals("last_updated_timestamp") ) {
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
                else if ( name.equals("margin_deposit_rate") ) {
                    return this.margin_deposit_rate;
                }
                break;
            case 'n':
                if ( name.equals("name_transfer_fee") ) {
                    return this.name_transfer_fee;
                }
                else if ( name.equals("name_transfer_fee_tax") ) {
                    return this.name_transfer_fee_tax;
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
                else if ( name.equals("other") ) {
                    return this.other;
                }
                break;
            case 'p':
                if ( name.equals("pay_interest_fee") ) {
                    return this.pay_interest_fee;
                }
                else if ( name.equals("pay_interest_fee_tax") ) {
                    return this.pay_interest_fee_tax;
                }
                else if ( name.equals("product_id") ) {
                    return new Long( product_id );
                }
                break;
            case 'q':
                if ( name.equals("quantity") ) {
                    return new Double( quantity );
                }
                break;
            case 'r':
                if ( name.equals("repayment_type") ) {
                    return this.repayment_type;
                }
                else if ( name.equals("repayment_num") ) {
                    return this.repayment_num;
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
                break;
            case 't':
                if ( name.equals("tax_type") ) {
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
                if ( name.equals("contract_price") ) {
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
                else if ( name.equals("cash_margin_deposit_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_margin_deposit_rate' must be Double: '"+value+"' is not." );
                    this.cash_margin_deposit_rate = (Double) value;
                    if (this.cash_margin_deposit_rate_is_set)
                        this.cash_margin_deposit_rate_is_modified = true;
                    this.cash_margin_deposit_rate_is_set = true;
                    return;
                }
                else if ( name.equals("contract_asset_profit_loss") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_asset_profit_loss' must be Double: '"+value+"' is not." );
                    this.contract_asset_profit_loss = (Double) value;
                    if (this.contract_asset_profit_loss_is_set)
                        this.contract_asset_profit_loss_is_modified = true;
                    this.contract_asset_profit_loss_is_set = true;
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
            case 'f':
                if ( name.equals("fixed_contract_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'fixed_contract_id' must be Long: '"+value+"' is not." );
                    this.fixed_contract_id = ((Long) value).longValue();
                    if (this.fixed_contract_id_is_set)
                        this.fixed_contract_id_is_modified = true;
                    this.fixed_contract_id_is_set = true;
                    return;
                }
                else if ( name.equals("first_open_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'first_open_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.first_open_date = (java.sql.Timestamp) value;
                    if (this.first_open_date_is_set)
                        this.first_open_date_is_modified = true;
                    this.first_open_date_is_set = true;
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
                if ( name.equals("loan_equity_fee") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'loan_equity_fee' must be Double: '"+value+"' is not." );
                    this.loan_equity_fee = (Double) value;
                    if (this.loan_equity_fee_is_set)
                        this.loan_equity_fee_is_modified = true;
                    this.loan_equity_fee_is_set = true;
                    return;
                }
                else if ( name.equals("last_updated_timestamp") ) {
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
                else if ( name.equals("margin_deposit_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_deposit_rate' must be Double: '"+value+"' is not." );
                    this.margin_deposit_rate = (Double) value;
                    if (this.margin_deposit_rate_is_set)
                        this.margin_deposit_rate_is_modified = true;
                    this.margin_deposit_rate_is_set = true;
                    return;
                }
                break;
            case 'n':
                if ( name.equals("name_transfer_fee") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'name_transfer_fee' must be Double: '"+value+"' is not." );
                    this.name_transfer_fee = (Double) value;
                    if (this.name_transfer_fee_is_set)
                        this.name_transfer_fee_is_modified = true;
                    this.name_transfer_fee_is_set = true;
                    return;
                }
                else if ( name.equals("name_transfer_fee_tax") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'name_transfer_fee_tax' must be Double: '"+value+"' is not." );
                    this.name_transfer_fee_tax = (Double) value;
                    if (this.name_transfer_fee_tax_is_set)
                        this.name_transfer_fee_tax_is_modified = true;
                    this.name_transfer_fee_tax_is_set = true;
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
                else if ( name.equals("other") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'other' must be Double: '"+value+"' is not." );
                    this.other = (Double) value;
                    if (this.other_is_set)
                        this.other_is_modified = true;
                    this.other_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("pay_interest_fee") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'pay_interest_fee' must be Double: '"+value+"' is not." );
                    this.pay_interest_fee = (Double) value;
                    if (this.pay_interest_fee_is_set)
                        this.pay_interest_fee_is_modified = true;
                    this.pay_interest_fee_is_set = true;
                    return;
                }
                else if ( name.equals("pay_interest_fee_tax") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'pay_interest_fee_tax' must be Double: '"+value+"' is not." );
                    this.pay_interest_fee_tax = (Double) value;
                    if (this.pay_interest_fee_tax_is_set)
                        this.pay_interest_fee_tax_is_modified = true;
                    this.pay_interest_fee_tax_is_set = true;
                    return;
                }
                else if ( name.equals("product_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'product_id' must be Long: '"+value+"' is not." );
                    this.product_id = ((Long) value).longValue();
                    if (this.product_id_is_set)
                        this.product_id_is_modified = true;
                    this.product_id_is_set = true;
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
            case 'r':
                if ( name.equals("repayment_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'repayment_type' must be String: '"+value+"' is not." );
                    this.repayment_type = (String) value;
                    if (this.repayment_type_is_set)
                        this.repayment_type_is_modified = true;
                    this.repayment_type_is_set = true;
                    return;
                }
                else if ( name.equals("repayment_num") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'repayment_num' must be Integer: '"+value+"' is not." );
                    this.repayment_num = (Integer) value;
                    if (this.repayment_num_is_set)
                        this.repayment_num_is_modified = true;
                    this.repayment_num_is_set = true;
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
                break;
            case 't':
                if ( name.equals("tax_type") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum) )
                        throw new IllegalArgumentException( "value for 'tax_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum: '"+value+"' is not." );
                    this.tax_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum) value;
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
