head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.29.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TpCalcResultEquityDetailParams.java;


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
 * tp_calc_result_equity_detailテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link TpCalcResultEquityDetailRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link TpCalcResultEquityDetailParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link TpCalcResultEquityDetailParams}が{@@link TpCalcResultEquityDetailRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see TpCalcResultEquityDetailPK 
 * @@see TpCalcResultEquityDetailRow 
 */
public class TpCalcResultEquityDetailParams extends Params implements TpCalcResultEquityDetailRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "tp_calc_result_equity_detail";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = TpCalcResultEquityDetailRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return TpCalcResultEquityDetailRow.TYPE;
  }


  /** 
   * <em>calc_result_equity_id</em>カラムの値 
   */
  public  long  calc_result_equity_id;

  /** 
   * <em>account_id</em>カラムの値 
   */
  public  long  account_id;

  /** 
   * <em>equity_asset_delivered</em>カラムの値 
   */
  public  Double  equity_asset_delivered;

  /** 
   * <em>equity_asset_executed</em>カラムの値 
   */
  public  Double  equity_asset_executed;

  /** 
   * <em>mini_stock_asset_delivered</em>カラムの値 
   */
  public  Double  mini_stock_asset_delivered;

  /** 
   * <em>mini_stock_asset_executed</em>カラムの値 
   */
  public  Double  mini_stock_asset_executed;

  /** 
   * <em>ruito_asset_delivered</em>カラムの値 
   */
  public  Double  ruito_asset_delivered;

  /** 
   * <em>ruito_asset_executed</em>カラムの値 
   */
  public  Double  ruito_asset_executed;

  /** 
   * <em>mutual_fund_asset_delivered</em>カラムの値 
   */
  public  Double  mutual_fund_asset_delivered;

  /** 
   * <em>mutual_fund_asset_executed</em>カラムの値 
   */
  public  Double  mutual_fund_asset_executed;

  /** 
   * <em>bond_asset_delivered</em>カラムの値 
   */
  public  Double  bond_asset_delivered;

  /** 
   * <em>bond_asset_executed</em>カラムの値 
   */
  public  Double  bond_asset_executed;

  /** 
   * <em>payment_amount_designate_0</em>カラムの値 
   */
  public  Double  payment_amount_designate_0;

  /** 
   * <em>payment_amount_designate_1</em>カラムの値 
   */
  public  Double  payment_amount_designate_1;

  /** 
   * <em>payment_amount_designate_2</em>カラムの値 
   */
  public  Double  payment_amount_designate_2;

  /** 
   * <em>payment_amount_designate_3</em>カラムの値 
   */
  public  Double  payment_amount_designate_3;

  /** 
   * <em>payment_amount_designate_4</em>カラムの値 
   */
  public  Double  payment_amount_designate_4;

  /** 
   * <em>payment_amount_designate_5</em>カラムの値 
   */
  public  Double  payment_amount_designate_5;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>foreign_equity_asset_delivered</em>カラムの値 
   */
  public  Double  foreign_equity_asset_delivered;

  /** 
   * <em>foreign_equity_asset_executed</em>カラムの値 
   */
  public  Double  foreign_equity_asset_executed;

  private boolean calc_result_equity_id_is_set = false;
  private boolean calc_result_equity_id_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean equity_asset_delivered_is_set = false;
  private boolean equity_asset_delivered_is_modified = false;
  private boolean equity_asset_executed_is_set = false;
  private boolean equity_asset_executed_is_modified = false;
  private boolean mini_stock_asset_delivered_is_set = false;
  private boolean mini_stock_asset_delivered_is_modified = false;
  private boolean mini_stock_asset_executed_is_set = false;
  private boolean mini_stock_asset_executed_is_modified = false;
  private boolean ruito_asset_delivered_is_set = false;
  private boolean ruito_asset_delivered_is_modified = false;
  private boolean ruito_asset_executed_is_set = false;
  private boolean ruito_asset_executed_is_modified = false;
  private boolean mutual_fund_asset_delivered_is_set = false;
  private boolean mutual_fund_asset_delivered_is_modified = false;
  private boolean mutual_fund_asset_executed_is_set = false;
  private boolean mutual_fund_asset_executed_is_modified = false;
  private boolean bond_asset_delivered_is_set = false;
  private boolean bond_asset_delivered_is_modified = false;
  private boolean bond_asset_executed_is_set = false;
  private boolean bond_asset_executed_is_modified = false;
  private boolean payment_amount_designate_0_is_set = false;
  private boolean payment_amount_designate_0_is_modified = false;
  private boolean payment_amount_designate_1_is_set = false;
  private boolean payment_amount_designate_1_is_modified = false;
  private boolean payment_amount_designate_2_is_set = false;
  private boolean payment_amount_designate_2_is_modified = false;
  private boolean payment_amount_designate_3_is_set = false;
  private boolean payment_amount_designate_3_is_modified = false;
  private boolean payment_amount_designate_4_is_set = false;
  private boolean payment_amount_designate_4_is_modified = false;
  private boolean payment_amount_designate_5_is_set = false;
  private boolean payment_amount_designate_5_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean foreign_equity_asset_delivered_is_set = false;
  private boolean foreign_equity_asset_delivered_is_modified = false;
  private boolean foreign_equity_asset_executed_is_set = false;
  private boolean foreign_equity_asset_executed_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "calc_result_equity_id=" + calc_result_equity_id
      + "," + "account_id=" +account_id
      + "," + "equity_asset_delivered=" +equity_asset_delivered
      + "," + "equity_asset_executed=" +equity_asset_executed
      + "," + "mini_stock_asset_delivered=" +mini_stock_asset_delivered
      + "," + "mini_stock_asset_executed=" +mini_stock_asset_executed
      + "," + "ruito_asset_delivered=" +ruito_asset_delivered
      + "," + "ruito_asset_executed=" +ruito_asset_executed
      + "," + "mutual_fund_asset_delivered=" +mutual_fund_asset_delivered
      + "," + "mutual_fund_asset_executed=" +mutual_fund_asset_executed
      + "," + "bond_asset_delivered=" +bond_asset_delivered
      + "," + "bond_asset_executed=" +bond_asset_executed
      + "," + "payment_amount_designate_0=" +payment_amount_designate_0
      + "," + "payment_amount_designate_1=" +payment_amount_designate_1
      + "," + "payment_amount_designate_2=" +payment_amount_designate_2
      + "," + "payment_amount_designate_3=" +payment_amount_designate_3
      + "," + "payment_amount_designate_4=" +payment_amount_designate_4
      + "," + "payment_amount_designate_5=" +payment_amount_designate_5
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "foreign_equity_asset_delivered=" +foreign_equity_asset_delivered
      + "," + "foreign_equity_asset_executed=" +foreign_equity_asset_executed
      + "}";
  }


  /** 
   * 値が未設定のTpCalcResultEquityDetailParamsオブジェクトを作成します。 
   */
  public TpCalcResultEquityDetailParams() {
    calc_result_equity_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のTpCalcResultEquityDetailRowオブジェクトの値を利用してTpCalcResultEquityDetailParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するTpCalcResultEquityDetailRowオブジェクト 
   */
  public TpCalcResultEquityDetailParams( TpCalcResultEquityDetailRow p_row )
  {
    calc_result_equity_id = p_row.getCalcResultEquityId();
    calc_result_equity_id_is_set = p_row.getCalcResultEquityIdIsSet();
    calc_result_equity_id_is_modified = p_row.getCalcResultEquityIdIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    if ( !p_row.getEquityAssetDeliveredIsNull() )
      equity_asset_delivered = new Double( p_row.getEquityAssetDelivered() );
    equity_asset_delivered_is_set = p_row.getEquityAssetDeliveredIsSet();
    equity_asset_delivered_is_modified = p_row.getEquityAssetDeliveredIsModified();
    if ( !p_row.getEquityAssetExecutedIsNull() )
      equity_asset_executed = new Double( p_row.getEquityAssetExecuted() );
    equity_asset_executed_is_set = p_row.getEquityAssetExecutedIsSet();
    equity_asset_executed_is_modified = p_row.getEquityAssetExecutedIsModified();
    if ( !p_row.getMiniStockAssetDeliveredIsNull() )
      mini_stock_asset_delivered = new Double( p_row.getMiniStockAssetDelivered() );
    mini_stock_asset_delivered_is_set = p_row.getMiniStockAssetDeliveredIsSet();
    mini_stock_asset_delivered_is_modified = p_row.getMiniStockAssetDeliveredIsModified();
    if ( !p_row.getMiniStockAssetExecutedIsNull() )
      mini_stock_asset_executed = new Double( p_row.getMiniStockAssetExecuted() );
    mini_stock_asset_executed_is_set = p_row.getMiniStockAssetExecutedIsSet();
    mini_stock_asset_executed_is_modified = p_row.getMiniStockAssetExecutedIsModified();
    if ( !p_row.getRuitoAssetDeliveredIsNull() )
      ruito_asset_delivered = new Double( p_row.getRuitoAssetDelivered() );
    ruito_asset_delivered_is_set = p_row.getRuitoAssetDeliveredIsSet();
    ruito_asset_delivered_is_modified = p_row.getRuitoAssetDeliveredIsModified();
    if ( !p_row.getRuitoAssetExecutedIsNull() )
      ruito_asset_executed = new Double( p_row.getRuitoAssetExecuted() );
    ruito_asset_executed_is_set = p_row.getRuitoAssetExecutedIsSet();
    ruito_asset_executed_is_modified = p_row.getRuitoAssetExecutedIsModified();
    if ( !p_row.getMutualFundAssetDeliveredIsNull() )
      mutual_fund_asset_delivered = new Double( p_row.getMutualFundAssetDelivered() );
    mutual_fund_asset_delivered_is_set = p_row.getMutualFundAssetDeliveredIsSet();
    mutual_fund_asset_delivered_is_modified = p_row.getMutualFundAssetDeliveredIsModified();
    if ( !p_row.getMutualFundAssetExecutedIsNull() )
      mutual_fund_asset_executed = new Double( p_row.getMutualFundAssetExecuted() );
    mutual_fund_asset_executed_is_set = p_row.getMutualFundAssetExecutedIsSet();
    mutual_fund_asset_executed_is_modified = p_row.getMutualFundAssetExecutedIsModified();
    if ( !p_row.getBondAssetDeliveredIsNull() )
      bond_asset_delivered = new Double( p_row.getBondAssetDelivered() );
    bond_asset_delivered_is_set = p_row.getBondAssetDeliveredIsSet();
    bond_asset_delivered_is_modified = p_row.getBondAssetDeliveredIsModified();
    if ( !p_row.getBondAssetExecutedIsNull() )
      bond_asset_executed = new Double( p_row.getBondAssetExecuted() );
    bond_asset_executed_is_set = p_row.getBondAssetExecutedIsSet();
    bond_asset_executed_is_modified = p_row.getBondAssetExecutedIsModified();
    if ( !p_row.getPaymentAmountDesignate0IsNull() )
      payment_amount_designate_0 = new Double( p_row.getPaymentAmountDesignate0() );
    payment_amount_designate_0_is_set = p_row.getPaymentAmountDesignate0IsSet();
    payment_amount_designate_0_is_modified = p_row.getPaymentAmountDesignate0IsModified();
    if ( !p_row.getPaymentAmountDesignate1IsNull() )
      payment_amount_designate_1 = new Double( p_row.getPaymentAmountDesignate1() );
    payment_amount_designate_1_is_set = p_row.getPaymentAmountDesignate1IsSet();
    payment_amount_designate_1_is_modified = p_row.getPaymentAmountDesignate1IsModified();
    if ( !p_row.getPaymentAmountDesignate2IsNull() )
      payment_amount_designate_2 = new Double( p_row.getPaymentAmountDesignate2() );
    payment_amount_designate_2_is_set = p_row.getPaymentAmountDesignate2IsSet();
    payment_amount_designate_2_is_modified = p_row.getPaymentAmountDesignate2IsModified();
    if ( !p_row.getPaymentAmountDesignate3IsNull() )
      payment_amount_designate_3 = new Double( p_row.getPaymentAmountDesignate3() );
    payment_amount_designate_3_is_set = p_row.getPaymentAmountDesignate3IsSet();
    payment_amount_designate_3_is_modified = p_row.getPaymentAmountDesignate3IsModified();
    if ( !p_row.getPaymentAmountDesignate4IsNull() )
      payment_amount_designate_4 = new Double( p_row.getPaymentAmountDesignate4() );
    payment_amount_designate_4_is_set = p_row.getPaymentAmountDesignate4IsSet();
    payment_amount_designate_4_is_modified = p_row.getPaymentAmountDesignate4IsModified();
    if ( !p_row.getPaymentAmountDesignate5IsNull() )
      payment_amount_designate_5 = new Double( p_row.getPaymentAmountDesignate5() );
    payment_amount_designate_5_is_set = p_row.getPaymentAmountDesignate5IsSet();
    payment_amount_designate_5_is_modified = p_row.getPaymentAmountDesignate5IsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    if ( !p_row.getForeignEquityAssetDeliveredIsNull() )
      foreign_equity_asset_delivered = new Double( p_row.getForeignEquityAssetDelivered() );
    foreign_equity_asset_delivered_is_set = p_row.getForeignEquityAssetDeliveredIsSet();
    foreign_equity_asset_delivered_is_modified = p_row.getForeignEquityAssetDeliveredIsModified();
    if ( !p_row.getForeignEquityAssetExecutedIsNull() )
      foreign_equity_asset_executed = new Double( p_row.getForeignEquityAssetExecuted() );
    foreign_equity_asset_executed_is_set = p_row.getForeignEquityAssetExecutedIsSet();
    foreign_equity_asset_executed_is_modified = p_row.getForeignEquityAssetExecutedIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      account_id_is_set = true;
      account_id_is_modified = true;
      equity_asset_delivered_is_set = true;
      equity_asset_delivered_is_modified = true;
      equity_asset_executed_is_set = true;
      equity_asset_executed_is_modified = true;
      mini_stock_asset_delivered_is_set = true;
      mini_stock_asset_delivered_is_modified = true;
      mini_stock_asset_executed_is_set = true;
      mini_stock_asset_executed_is_modified = true;
      ruito_asset_delivered_is_set = true;
      ruito_asset_delivered_is_modified = true;
      ruito_asset_executed_is_set = true;
      ruito_asset_executed_is_modified = true;
      mutual_fund_asset_delivered_is_set = true;
      mutual_fund_asset_delivered_is_modified = true;
      mutual_fund_asset_executed_is_set = true;
      mutual_fund_asset_executed_is_modified = true;
      bond_asset_delivered_is_set = true;
      bond_asset_delivered_is_modified = true;
      bond_asset_executed_is_set = true;
      bond_asset_executed_is_modified = true;
      payment_amount_designate_0_is_set = true;
      payment_amount_designate_0_is_modified = true;
      payment_amount_designate_1_is_set = true;
      payment_amount_designate_1_is_modified = true;
      payment_amount_designate_2_is_set = true;
      payment_amount_designate_2_is_modified = true;
      payment_amount_designate_3_is_set = true;
      payment_amount_designate_3_is_modified = true;
      payment_amount_designate_4_is_set = true;
      payment_amount_designate_4_is_modified = true;
      payment_amount_designate_5_is_set = true;
      payment_amount_designate_5_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      foreign_equity_asset_delivered_is_set = true;
      foreign_equity_asset_delivered_is_modified = true;
      foreign_equity_asset_executed_is_set = true;
      foreign_equity_asset_executed_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof TpCalcResultEquityDetailRow ) )
       return false;
    return fieldsEqual( (TpCalcResultEquityDetailRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のTpCalcResultEquityDetailRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( TpCalcResultEquityDetailRow row )
  {
    if ( calc_result_equity_id != row.getCalcResultEquityId() )
      return false;
    if ( account_id != row.getAccountId() )
      return false;
    if ( equity_asset_delivered == null ) {
      if ( !row.getEquityAssetDeliveredIsNull() )
        return false;
    } else if ( row.getEquityAssetDeliveredIsNull() || ( equity_asset_delivered.doubleValue() != row.getEquityAssetDelivered() ) ) {
        return false;
    }
    if ( equity_asset_executed == null ) {
      if ( !row.getEquityAssetExecutedIsNull() )
        return false;
    } else if ( row.getEquityAssetExecutedIsNull() || ( equity_asset_executed.doubleValue() != row.getEquityAssetExecuted() ) ) {
        return false;
    }
    if ( mini_stock_asset_delivered == null ) {
      if ( !row.getMiniStockAssetDeliveredIsNull() )
        return false;
    } else if ( row.getMiniStockAssetDeliveredIsNull() || ( mini_stock_asset_delivered.doubleValue() != row.getMiniStockAssetDelivered() ) ) {
        return false;
    }
    if ( mini_stock_asset_executed == null ) {
      if ( !row.getMiniStockAssetExecutedIsNull() )
        return false;
    } else if ( row.getMiniStockAssetExecutedIsNull() || ( mini_stock_asset_executed.doubleValue() != row.getMiniStockAssetExecuted() ) ) {
        return false;
    }
    if ( ruito_asset_delivered == null ) {
      if ( !row.getRuitoAssetDeliveredIsNull() )
        return false;
    } else if ( row.getRuitoAssetDeliveredIsNull() || ( ruito_asset_delivered.doubleValue() != row.getRuitoAssetDelivered() ) ) {
        return false;
    }
    if ( ruito_asset_executed == null ) {
      if ( !row.getRuitoAssetExecutedIsNull() )
        return false;
    } else if ( row.getRuitoAssetExecutedIsNull() || ( ruito_asset_executed.doubleValue() != row.getRuitoAssetExecuted() ) ) {
        return false;
    }
    if ( mutual_fund_asset_delivered == null ) {
      if ( !row.getMutualFundAssetDeliveredIsNull() )
        return false;
    } else if ( row.getMutualFundAssetDeliveredIsNull() || ( mutual_fund_asset_delivered.doubleValue() != row.getMutualFundAssetDelivered() ) ) {
        return false;
    }
    if ( mutual_fund_asset_executed == null ) {
      if ( !row.getMutualFundAssetExecutedIsNull() )
        return false;
    } else if ( row.getMutualFundAssetExecutedIsNull() || ( mutual_fund_asset_executed.doubleValue() != row.getMutualFundAssetExecuted() ) ) {
        return false;
    }
    if ( bond_asset_delivered == null ) {
      if ( !row.getBondAssetDeliveredIsNull() )
        return false;
    } else if ( row.getBondAssetDeliveredIsNull() || ( bond_asset_delivered.doubleValue() != row.getBondAssetDelivered() ) ) {
        return false;
    }
    if ( bond_asset_executed == null ) {
      if ( !row.getBondAssetExecutedIsNull() )
        return false;
    } else if ( row.getBondAssetExecutedIsNull() || ( bond_asset_executed.doubleValue() != row.getBondAssetExecuted() ) ) {
        return false;
    }
    if ( payment_amount_designate_0 == null ) {
      if ( !row.getPaymentAmountDesignate0IsNull() )
        return false;
    } else if ( row.getPaymentAmountDesignate0IsNull() || ( payment_amount_designate_0.doubleValue() != row.getPaymentAmountDesignate0() ) ) {
        return false;
    }
    if ( payment_amount_designate_1 == null ) {
      if ( !row.getPaymentAmountDesignate1IsNull() )
        return false;
    } else if ( row.getPaymentAmountDesignate1IsNull() || ( payment_amount_designate_1.doubleValue() != row.getPaymentAmountDesignate1() ) ) {
        return false;
    }
    if ( payment_amount_designate_2 == null ) {
      if ( !row.getPaymentAmountDesignate2IsNull() )
        return false;
    } else if ( row.getPaymentAmountDesignate2IsNull() || ( payment_amount_designate_2.doubleValue() != row.getPaymentAmountDesignate2() ) ) {
        return false;
    }
    if ( payment_amount_designate_3 == null ) {
      if ( !row.getPaymentAmountDesignate3IsNull() )
        return false;
    } else if ( row.getPaymentAmountDesignate3IsNull() || ( payment_amount_designate_3.doubleValue() != row.getPaymentAmountDesignate3() ) ) {
        return false;
    }
    if ( payment_amount_designate_4 == null ) {
      if ( !row.getPaymentAmountDesignate4IsNull() )
        return false;
    } else if ( row.getPaymentAmountDesignate4IsNull() || ( payment_amount_designate_4.doubleValue() != row.getPaymentAmountDesignate4() ) ) {
        return false;
    }
    if ( payment_amount_designate_5 == null ) {
      if ( !row.getPaymentAmountDesignate5IsNull() )
        return false;
    } else if ( row.getPaymentAmountDesignate5IsNull() || ( payment_amount_designate_5.doubleValue() != row.getPaymentAmountDesignate5() ) ) {
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
    if ( foreign_equity_asset_delivered == null ) {
      if ( !row.getForeignEquityAssetDeliveredIsNull() )
        return false;
    } else if ( row.getForeignEquityAssetDeliveredIsNull() || ( foreign_equity_asset_delivered.doubleValue() != row.getForeignEquityAssetDelivered() ) ) {
        return false;
    }
    if ( foreign_equity_asset_executed == null ) {
      if ( !row.getForeignEquityAssetExecutedIsNull() )
        return false;
    } else if ( row.getForeignEquityAssetExecutedIsNull() || ( foreign_equity_asset_executed.doubleValue() != row.getForeignEquityAssetExecuted() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  ((int) calc_result_equity_id)
        + ((int) account_id)
        + (equity_asset_delivered!=null? equity_asset_delivered.hashCode(): 0) 
        + (equity_asset_executed!=null? equity_asset_executed.hashCode(): 0) 
        + (mini_stock_asset_delivered!=null? mini_stock_asset_delivered.hashCode(): 0) 
        + (mini_stock_asset_executed!=null? mini_stock_asset_executed.hashCode(): 0) 
        + (ruito_asset_delivered!=null? ruito_asset_delivered.hashCode(): 0) 
        + (ruito_asset_executed!=null? ruito_asset_executed.hashCode(): 0) 
        + (mutual_fund_asset_delivered!=null? mutual_fund_asset_delivered.hashCode(): 0) 
        + (mutual_fund_asset_executed!=null? mutual_fund_asset_executed.hashCode(): 0) 
        + (bond_asset_delivered!=null? bond_asset_delivered.hashCode(): 0) 
        + (bond_asset_executed!=null? bond_asset_executed.hashCode(): 0) 
        + (payment_amount_designate_0!=null? payment_amount_designate_0.hashCode(): 0) 
        + (payment_amount_designate_1!=null? payment_amount_designate_1.hashCode(): 0) 
        + (payment_amount_designate_2!=null? payment_amount_designate_2.hashCode(): 0) 
        + (payment_amount_designate_3!=null? payment_amount_designate_3.hashCode(): 0) 
        + (payment_amount_designate_4!=null? payment_amount_designate_4.hashCode(): 0) 
        + (payment_amount_designate_5!=null? payment_amount_designate_5.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (foreign_equity_asset_delivered!=null? foreign_equity_asset_delivered.hashCode(): 0) 
        + (foreign_equity_asset_executed!=null? foreign_equity_asset_executed.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_id' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("calc_result_equity_id",new Long(calc_result_equity_id));
		map.put("account_id",new Long(account_id));
		if ( equity_asset_delivered_is_set )
			map.put("equity_asset_delivered",equity_asset_delivered);
		if ( equity_asset_executed_is_set )
			map.put("equity_asset_executed",equity_asset_executed);
		if ( mini_stock_asset_delivered_is_set )
			map.put("mini_stock_asset_delivered",mini_stock_asset_delivered);
		if ( mini_stock_asset_executed_is_set )
			map.put("mini_stock_asset_executed",mini_stock_asset_executed);
		if ( ruito_asset_delivered_is_set )
			map.put("ruito_asset_delivered",ruito_asset_delivered);
		if ( ruito_asset_executed_is_set )
			map.put("ruito_asset_executed",ruito_asset_executed);
		if ( mutual_fund_asset_delivered_is_set )
			map.put("mutual_fund_asset_delivered",mutual_fund_asset_delivered);
		if ( mutual_fund_asset_executed_is_set )
			map.put("mutual_fund_asset_executed",mutual_fund_asset_executed);
		if ( bond_asset_delivered_is_set )
			map.put("bond_asset_delivered",bond_asset_delivered);
		if ( bond_asset_executed_is_set )
			map.put("bond_asset_executed",bond_asset_executed);
		if ( payment_amount_designate_0_is_set )
			map.put("payment_amount_designate_0",payment_amount_designate_0);
		if ( payment_amount_designate_1_is_set )
			map.put("payment_amount_designate_1",payment_amount_designate_1);
		if ( payment_amount_designate_2_is_set )
			map.put("payment_amount_designate_2",payment_amount_designate_2);
		if ( payment_amount_designate_3_is_set )
			map.put("payment_amount_designate_3",payment_amount_designate_3);
		if ( payment_amount_designate_4_is_set )
			map.put("payment_amount_designate_4",payment_amount_designate_4);
		if ( payment_amount_designate_5_is_set )
			map.put("payment_amount_designate_5",payment_amount_designate_5);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( foreign_equity_asset_delivered != null )
			map.put("foreign_equity_asset_delivered",foreign_equity_asset_delivered);
		if ( foreign_equity_asset_executed != null )
			map.put("foreign_equity_asset_executed",foreign_equity_asset_executed);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( account_id_is_modified )
			map.put("account_id",new Long(account_id));
		if ( equity_asset_delivered_is_modified )
			map.put("equity_asset_delivered",equity_asset_delivered);
		if ( equity_asset_executed_is_modified )
			map.put("equity_asset_executed",equity_asset_executed);
		if ( mini_stock_asset_delivered_is_modified )
			map.put("mini_stock_asset_delivered",mini_stock_asset_delivered);
		if ( mini_stock_asset_executed_is_modified )
			map.put("mini_stock_asset_executed",mini_stock_asset_executed);
		if ( ruito_asset_delivered_is_modified )
			map.put("ruito_asset_delivered",ruito_asset_delivered);
		if ( ruito_asset_executed_is_modified )
			map.put("ruito_asset_executed",ruito_asset_executed);
		if ( mutual_fund_asset_delivered_is_modified )
			map.put("mutual_fund_asset_delivered",mutual_fund_asset_delivered);
		if ( mutual_fund_asset_executed_is_modified )
			map.put("mutual_fund_asset_executed",mutual_fund_asset_executed);
		if ( bond_asset_delivered_is_modified )
			map.put("bond_asset_delivered",bond_asset_delivered);
		if ( bond_asset_executed_is_modified )
			map.put("bond_asset_executed",bond_asset_executed);
		if ( payment_amount_designate_0_is_modified )
			map.put("payment_amount_designate_0",payment_amount_designate_0);
		if ( payment_amount_designate_1_is_modified )
			map.put("payment_amount_designate_1",payment_amount_designate_1);
		if ( payment_amount_designate_2_is_modified )
			map.put("payment_amount_designate_2",payment_amount_designate_2);
		if ( payment_amount_designate_3_is_modified )
			map.put("payment_amount_designate_3",payment_amount_designate_3);
		if ( payment_amount_designate_4_is_modified )
			map.put("payment_amount_designate_4",payment_amount_designate_4);
		if ( payment_amount_designate_5_is_modified )
			map.put("payment_amount_designate_5",payment_amount_designate_5);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( foreign_equity_asset_delivered_is_modified )
			map.put("foreign_equity_asset_delivered",foreign_equity_asset_delivered);
		if ( foreign_equity_asset_executed_is_modified )
			map.put("foreign_equity_asset_executed",foreign_equity_asset_executed);
		if (map.size() == 0) {
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			if ( equity_asset_delivered_is_set )
				map.put("equity_asset_delivered",equity_asset_delivered);
			if ( equity_asset_executed_is_set )
				map.put("equity_asset_executed",equity_asset_executed);
			if ( mini_stock_asset_delivered_is_set )
				map.put("mini_stock_asset_delivered",mini_stock_asset_delivered);
			if ( mini_stock_asset_executed_is_set )
				map.put("mini_stock_asset_executed",mini_stock_asset_executed);
			if ( ruito_asset_delivered_is_set )
				map.put("ruito_asset_delivered",ruito_asset_delivered);
			if ( ruito_asset_executed_is_set )
				map.put("ruito_asset_executed",ruito_asset_executed);
			if ( mutual_fund_asset_delivered_is_set )
				map.put("mutual_fund_asset_delivered",mutual_fund_asset_delivered);
			if ( mutual_fund_asset_executed_is_set )
				map.put("mutual_fund_asset_executed",mutual_fund_asset_executed);
			if ( bond_asset_delivered_is_set )
				map.put("bond_asset_delivered",bond_asset_delivered);
			if ( bond_asset_executed_is_set )
				map.put("bond_asset_executed",bond_asset_executed);
			if ( payment_amount_designate_0_is_set )
				map.put("payment_amount_designate_0",payment_amount_designate_0);
			if ( payment_amount_designate_1_is_set )
				map.put("payment_amount_designate_1",payment_amount_designate_1);
			if ( payment_amount_designate_2_is_set )
				map.put("payment_amount_designate_2",payment_amount_designate_2);
			if ( payment_amount_designate_3_is_set )
				map.put("payment_amount_designate_3",payment_amount_designate_3);
			if ( payment_amount_designate_4_is_set )
				map.put("payment_amount_designate_4",payment_amount_designate_4);
			if ( payment_amount_designate_5_is_set )
				map.put("payment_amount_designate_5",payment_amount_designate_5);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("foreign_equity_asset_delivered",foreign_equity_asset_delivered);
			map.put("foreign_equity_asset_executed",foreign_equity_asset_executed);
		}
		return map;
	}


  /** 
   * <em>calc_result_equity_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getCalcResultEquityId()
  {
    return calc_result_equity_id;
  }


  /** 
   * <em>calc_result_equity_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCalcResultEquityIdIsSet() {
    return calc_result_equity_id_is_set;
  }


  /** 
   * <em>calc_result_equity_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCalcResultEquityIdIsModified() {
    return calc_result_equity_id_is_modified;
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
   * <em>equity_asset_delivered</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getEquityAssetDelivered()
  {
    return ( equity_asset_delivered==null? ((double)0): equity_asset_delivered.doubleValue() );
  }


  /** 
   * <em>equity_asset_delivered</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getEquityAssetDeliveredIsNull()
  {
    return equity_asset_delivered == null;
  }


  /** 
   * <em>equity_asset_delivered</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEquityAssetDeliveredIsSet() {
    return equity_asset_delivered_is_set;
  }


  /** 
   * <em>equity_asset_delivered</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEquityAssetDeliveredIsModified() {
    return equity_asset_delivered_is_modified;
  }


  /** 
   * <em>equity_asset_executed</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getEquityAssetExecuted()
  {
    return ( equity_asset_executed==null? ((double)0): equity_asset_executed.doubleValue() );
  }


  /** 
   * <em>equity_asset_executed</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getEquityAssetExecutedIsNull()
  {
    return equity_asset_executed == null;
  }


  /** 
   * <em>equity_asset_executed</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEquityAssetExecutedIsSet() {
    return equity_asset_executed_is_set;
  }


  /** 
   * <em>equity_asset_executed</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEquityAssetExecutedIsModified() {
    return equity_asset_executed_is_modified;
  }


  /** 
   * <em>mini_stock_asset_delivered</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMiniStockAssetDelivered()
  {
    return ( mini_stock_asset_delivered==null? ((double)0): mini_stock_asset_delivered.doubleValue() );
  }


  /** 
   * <em>mini_stock_asset_delivered</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMiniStockAssetDeliveredIsNull()
  {
    return mini_stock_asset_delivered == null;
  }


  /** 
   * <em>mini_stock_asset_delivered</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMiniStockAssetDeliveredIsSet() {
    return mini_stock_asset_delivered_is_set;
  }


  /** 
   * <em>mini_stock_asset_delivered</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMiniStockAssetDeliveredIsModified() {
    return mini_stock_asset_delivered_is_modified;
  }


  /** 
   * <em>mini_stock_asset_executed</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMiniStockAssetExecuted()
  {
    return ( mini_stock_asset_executed==null? ((double)0): mini_stock_asset_executed.doubleValue() );
  }


  /** 
   * <em>mini_stock_asset_executed</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMiniStockAssetExecutedIsNull()
  {
    return mini_stock_asset_executed == null;
  }


  /** 
   * <em>mini_stock_asset_executed</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMiniStockAssetExecutedIsSet() {
    return mini_stock_asset_executed_is_set;
  }


  /** 
   * <em>mini_stock_asset_executed</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMiniStockAssetExecutedIsModified() {
    return mini_stock_asset_executed_is_modified;
  }


  /** 
   * <em>ruito_asset_delivered</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getRuitoAssetDelivered()
  {
    return ( ruito_asset_delivered==null? ((double)0): ruito_asset_delivered.doubleValue() );
  }


  /** 
   * <em>ruito_asset_delivered</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getRuitoAssetDeliveredIsNull()
  {
    return ruito_asset_delivered == null;
  }


  /** 
   * <em>ruito_asset_delivered</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRuitoAssetDeliveredIsSet() {
    return ruito_asset_delivered_is_set;
  }


  /** 
   * <em>ruito_asset_delivered</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRuitoAssetDeliveredIsModified() {
    return ruito_asset_delivered_is_modified;
  }


  /** 
   * <em>ruito_asset_executed</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getRuitoAssetExecuted()
  {
    return ( ruito_asset_executed==null? ((double)0): ruito_asset_executed.doubleValue() );
  }


  /** 
   * <em>ruito_asset_executed</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getRuitoAssetExecutedIsNull()
  {
    return ruito_asset_executed == null;
  }


  /** 
   * <em>ruito_asset_executed</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRuitoAssetExecutedIsSet() {
    return ruito_asset_executed_is_set;
  }


  /** 
   * <em>ruito_asset_executed</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRuitoAssetExecutedIsModified() {
    return ruito_asset_executed_is_modified;
  }


  /** 
   * <em>mutual_fund_asset_delivered</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMutualFundAssetDelivered()
  {
    return ( mutual_fund_asset_delivered==null? ((double)0): mutual_fund_asset_delivered.doubleValue() );
  }


  /** 
   * <em>mutual_fund_asset_delivered</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMutualFundAssetDeliveredIsNull()
  {
    return mutual_fund_asset_delivered == null;
  }


  /** 
   * <em>mutual_fund_asset_delivered</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMutualFundAssetDeliveredIsSet() {
    return mutual_fund_asset_delivered_is_set;
  }


  /** 
   * <em>mutual_fund_asset_delivered</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMutualFundAssetDeliveredIsModified() {
    return mutual_fund_asset_delivered_is_modified;
  }


  /** 
   * <em>mutual_fund_asset_executed</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMutualFundAssetExecuted()
  {
    return ( mutual_fund_asset_executed==null? ((double)0): mutual_fund_asset_executed.doubleValue() );
  }


  /** 
   * <em>mutual_fund_asset_executed</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMutualFundAssetExecutedIsNull()
  {
    return mutual_fund_asset_executed == null;
  }


  /** 
   * <em>mutual_fund_asset_executed</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMutualFundAssetExecutedIsSet() {
    return mutual_fund_asset_executed_is_set;
  }


  /** 
   * <em>mutual_fund_asset_executed</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMutualFundAssetExecutedIsModified() {
    return mutual_fund_asset_executed_is_modified;
  }


  /** 
   * <em>bond_asset_delivered</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getBondAssetDelivered()
  {
    return ( bond_asset_delivered==null? ((double)0): bond_asset_delivered.doubleValue() );
  }


  /** 
   * <em>bond_asset_delivered</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getBondAssetDeliveredIsNull()
  {
    return bond_asset_delivered == null;
  }


  /** 
   * <em>bond_asset_delivered</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBondAssetDeliveredIsSet() {
    return bond_asset_delivered_is_set;
  }


  /** 
   * <em>bond_asset_delivered</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBondAssetDeliveredIsModified() {
    return bond_asset_delivered_is_modified;
  }


  /** 
   * <em>bond_asset_executed</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getBondAssetExecuted()
  {
    return ( bond_asset_executed==null? ((double)0): bond_asset_executed.doubleValue() );
  }


  /** 
   * <em>bond_asset_executed</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getBondAssetExecutedIsNull()
  {
    return bond_asset_executed == null;
  }


  /** 
   * <em>bond_asset_executed</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBondAssetExecutedIsSet() {
    return bond_asset_executed_is_set;
  }


  /** 
   * <em>bond_asset_executed</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBondAssetExecutedIsModified() {
    return bond_asset_executed_is_modified;
  }


  /** 
   * <em>payment_amount_designate_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPaymentAmountDesignate0()
  {
    return ( payment_amount_designate_0==null? ((double)0): payment_amount_designate_0.doubleValue() );
  }


  /** 
   * <em>payment_amount_designate_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPaymentAmountDesignate0IsNull()
  {
    return payment_amount_designate_0 == null;
  }


  /** 
   * <em>payment_amount_designate_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentAmountDesignate0IsSet() {
    return payment_amount_designate_0_is_set;
  }


  /** 
   * <em>payment_amount_designate_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentAmountDesignate0IsModified() {
    return payment_amount_designate_0_is_modified;
  }


  /** 
   * <em>payment_amount_designate_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPaymentAmountDesignate1()
  {
    return ( payment_amount_designate_1==null? ((double)0): payment_amount_designate_1.doubleValue() );
  }


  /** 
   * <em>payment_amount_designate_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPaymentAmountDesignate1IsNull()
  {
    return payment_amount_designate_1 == null;
  }


  /** 
   * <em>payment_amount_designate_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentAmountDesignate1IsSet() {
    return payment_amount_designate_1_is_set;
  }


  /** 
   * <em>payment_amount_designate_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentAmountDesignate1IsModified() {
    return payment_amount_designate_1_is_modified;
  }


  /** 
   * <em>payment_amount_designate_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPaymentAmountDesignate2()
  {
    return ( payment_amount_designate_2==null? ((double)0): payment_amount_designate_2.doubleValue() );
  }


  /** 
   * <em>payment_amount_designate_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPaymentAmountDesignate2IsNull()
  {
    return payment_amount_designate_2 == null;
  }


  /** 
   * <em>payment_amount_designate_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentAmountDesignate2IsSet() {
    return payment_amount_designate_2_is_set;
  }


  /** 
   * <em>payment_amount_designate_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentAmountDesignate2IsModified() {
    return payment_amount_designate_2_is_modified;
  }


  /** 
   * <em>payment_amount_designate_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPaymentAmountDesignate3()
  {
    return ( payment_amount_designate_3==null? ((double)0): payment_amount_designate_3.doubleValue() );
  }


  /** 
   * <em>payment_amount_designate_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPaymentAmountDesignate3IsNull()
  {
    return payment_amount_designate_3 == null;
  }


  /** 
   * <em>payment_amount_designate_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentAmountDesignate3IsSet() {
    return payment_amount_designate_3_is_set;
  }


  /** 
   * <em>payment_amount_designate_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentAmountDesignate3IsModified() {
    return payment_amount_designate_3_is_modified;
  }


  /** 
   * <em>payment_amount_designate_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPaymentAmountDesignate4()
  {
    return ( payment_amount_designate_4==null? ((double)0): payment_amount_designate_4.doubleValue() );
  }


  /** 
   * <em>payment_amount_designate_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPaymentAmountDesignate4IsNull()
  {
    return payment_amount_designate_4 == null;
  }


  /** 
   * <em>payment_amount_designate_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentAmountDesignate4IsSet() {
    return payment_amount_designate_4_is_set;
  }


  /** 
   * <em>payment_amount_designate_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentAmountDesignate4IsModified() {
    return payment_amount_designate_4_is_modified;
  }


  /** 
   * <em>payment_amount_designate_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPaymentAmountDesignate5()
  {
    return ( payment_amount_designate_5==null? ((double)0): payment_amount_designate_5.doubleValue() );
  }


  /** 
   * <em>payment_amount_designate_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPaymentAmountDesignate5IsNull()
  {
    return payment_amount_designate_5 == null;
  }


  /** 
   * <em>payment_amount_designate_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentAmountDesignate5IsSet() {
    return payment_amount_designate_5_is_set;
  }


  /** 
   * <em>payment_amount_designate_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentAmountDesignate5IsModified() {
    return payment_amount_designate_5_is_modified;
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
   * <em>foreign_equity_asset_delivered</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getForeignEquityAssetDelivered()
  {
    return ( foreign_equity_asset_delivered==null? ((double)0): foreign_equity_asset_delivered.doubleValue() );
  }


  /** 
   * <em>foreign_equity_asset_delivered</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getForeignEquityAssetDeliveredIsNull()
  {
    return foreign_equity_asset_delivered == null;
  }


  /** 
   * <em>foreign_equity_asset_delivered</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignEquityAssetDeliveredIsSet() {
    return foreign_equity_asset_delivered_is_set;
  }


  /** 
   * <em>foreign_equity_asset_delivered</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignEquityAssetDeliveredIsModified() {
    return foreign_equity_asset_delivered_is_modified;
  }


  /** 
   * <em>foreign_equity_asset_executed</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getForeignEquityAssetExecuted()
  {
    return ( foreign_equity_asset_executed==null? ((double)0): foreign_equity_asset_executed.doubleValue() );
  }


  /** 
   * <em>foreign_equity_asset_executed</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getForeignEquityAssetExecutedIsNull()
  {
    return foreign_equity_asset_executed == null;
  }


  /** 
   * <em>foreign_equity_asset_executed</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignEquityAssetExecutedIsSet() {
    return foreign_equity_asset_executed_is_set;
  }


  /** 
   * <em>foreign_equity_asset_executed</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignEquityAssetExecutedIsModified() {
    return foreign_equity_asset_executed_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new TpCalcResultEquityDetailPK(calc_result_equity_id);
  }


  /** 
   * <em>calc_result_equity_id</em>カラムの値を設定します。 
   *
   * @@param p_calcResultEquityId <em>calc_result_equity_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setCalcResultEquityId( long p_calcResultEquityId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    calc_result_equity_id = p_calcResultEquityId;
    calc_result_equity_id_is_set = true;
    calc_result_equity_id_is_modified = true;
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
   * <em>equity_asset_delivered</em>カラムの値を設定します。 
   *
   * @@param p_equityAssetDelivered <em>equity_asset_delivered</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setEquityAssetDelivered( double p_equityAssetDelivered )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    equity_asset_delivered = new Double(p_equityAssetDelivered);
    equity_asset_delivered_is_set = true;
    equity_asset_delivered_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>equity_asset_delivered</em>カラムに値を設定します。 
   */
  public final void setEquityAssetDelivered( Double p_equityAssetDelivered )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    equity_asset_delivered = p_equityAssetDelivered;
    equity_asset_delivered_is_set = true;
    equity_asset_delivered_is_modified = true;
  }


  /** 
   * <em>equity_asset_executed</em>カラムの値を設定します。 
   *
   * @@param p_equityAssetExecuted <em>equity_asset_executed</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setEquityAssetExecuted( double p_equityAssetExecuted )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    equity_asset_executed = new Double(p_equityAssetExecuted);
    equity_asset_executed_is_set = true;
    equity_asset_executed_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>equity_asset_executed</em>カラムに値を設定します。 
   */
  public final void setEquityAssetExecuted( Double p_equityAssetExecuted )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    equity_asset_executed = p_equityAssetExecuted;
    equity_asset_executed_is_set = true;
    equity_asset_executed_is_modified = true;
  }


  /** 
   * <em>mini_stock_asset_delivered</em>カラムの値を設定します。 
   *
   * @@param p_miniStockAssetDelivered <em>mini_stock_asset_delivered</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMiniStockAssetDelivered( double p_miniStockAssetDelivered )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mini_stock_asset_delivered = new Double(p_miniStockAssetDelivered);
    mini_stock_asset_delivered_is_set = true;
    mini_stock_asset_delivered_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>mini_stock_asset_delivered</em>カラムに値を設定します。 
   */
  public final void setMiniStockAssetDelivered( Double p_miniStockAssetDelivered )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    mini_stock_asset_delivered = p_miniStockAssetDelivered;
    mini_stock_asset_delivered_is_set = true;
    mini_stock_asset_delivered_is_modified = true;
  }


  /** 
   * <em>mini_stock_asset_executed</em>カラムの値を設定します。 
   *
   * @@param p_miniStockAssetExecuted <em>mini_stock_asset_executed</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMiniStockAssetExecuted( double p_miniStockAssetExecuted )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mini_stock_asset_executed = new Double(p_miniStockAssetExecuted);
    mini_stock_asset_executed_is_set = true;
    mini_stock_asset_executed_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>mini_stock_asset_executed</em>カラムに値を設定します。 
   */
  public final void setMiniStockAssetExecuted( Double p_miniStockAssetExecuted )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    mini_stock_asset_executed = p_miniStockAssetExecuted;
    mini_stock_asset_executed_is_set = true;
    mini_stock_asset_executed_is_modified = true;
  }


  /** 
   * <em>ruito_asset_delivered</em>カラムの値を設定します。 
   *
   * @@param p_ruitoAssetDelivered <em>ruito_asset_delivered</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setRuitoAssetDelivered( double p_ruitoAssetDelivered )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ruito_asset_delivered = new Double(p_ruitoAssetDelivered);
    ruito_asset_delivered_is_set = true;
    ruito_asset_delivered_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ruito_asset_delivered</em>カラムに値を設定します。 
   */
  public final void setRuitoAssetDelivered( Double p_ruitoAssetDelivered )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ruito_asset_delivered = p_ruitoAssetDelivered;
    ruito_asset_delivered_is_set = true;
    ruito_asset_delivered_is_modified = true;
  }


  /** 
   * <em>ruito_asset_executed</em>カラムの値を設定します。 
   *
   * @@param p_ruitoAssetExecuted <em>ruito_asset_executed</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setRuitoAssetExecuted( double p_ruitoAssetExecuted )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ruito_asset_executed = new Double(p_ruitoAssetExecuted);
    ruito_asset_executed_is_set = true;
    ruito_asset_executed_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ruito_asset_executed</em>カラムに値を設定します。 
   */
  public final void setRuitoAssetExecuted( Double p_ruitoAssetExecuted )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ruito_asset_executed = p_ruitoAssetExecuted;
    ruito_asset_executed_is_set = true;
    ruito_asset_executed_is_modified = true;
  }


  /** 
   * <em>mutual_fund_asset_delivered</em>カラムの値を設定します。 
   *
   * @@param p_mutualFundAssetDelivered <em>mutual_fund_asset_delivered</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMutualFundAssetDelivered( double p_mutualFundAssetDelivered )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mutual_fund_asset_delivered = new Double(p_mutualFundAssetDelivered);
    mutual_fund_asset_delivered_is_set = true;
    mutual_fund_asset_delivered_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>mutual_fund_asset_delivered</em>カラムに値を設定します。 
   */
  public final void setMutualFundAssetDelivered( Double p_mutualFundAssetDelivered )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    mutual_fund_asset_delivered = p_mutualFundAssetDelivered;
    mutual_fund_asset_delivered_is_set = true;
    mutual_fund_asset_delivered_is_modified = true;
  }


  /** 
   * <em>mutual_fund_asset_executed</em>カラムの値を設定します。 
   *
   * @@param p_mutualFundAssetExecuted <em>mutual_fund_asset_executed</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMutualFundAssetExecuted( double p_mutualFundAssetExecuted )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mutual_fund_asset_executed = new Double(p_mutualFundAssetExecuted);
    mutual_fund_asset_executed_is_set = true;
    mutual_fund_asset_executed_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>mutual_fund_asset_executed</em>カラムに値を設定します。 
   */
  public final void setMutualFundAssetExecuted( Double p_mutualFundAssetExecuted )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    mutual_fund_asset_executed = p_mutualFundAssetExecuted;
    mutual_fund_asset_executed_is_set = true;
    mutual_fund_asset_executed_is_modified = true;
  }


  /** 
   * <em>bond_asset_delivered</em>カラムの値を設定します。 
   *
   * @@param p_bondAssetDelivered <em>bond_asset_delivered</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setBondAssetDelivered( double p_bondAssetDelivered )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bond_asset_delivered = new Double(p_bondAssetDelivered);
    bond_asset_delivered_is_set = true;
    bond_asset_delivered_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>bond_asset_delivered</em>カラムに値を設定します。 
   */
  public final void setBondAssetDelivered( Double p_bondAssetDelivered )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    bond_asset_delivered = p_bondAssetDelivered;
    bond_asset_delivered_is_set = true;
    bond_asset_delivered_is_modified = true;
  }


  /** 
   * <em>bond_asset_executed</em>カラムの値を設定します。 
   *
   * @@param p_bondAssetExecuted <em>bond_asset_executed</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setBondAssetExecuted( double p_bondAssetExecuted )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bond_asset_executed = new Double(p_bondAssetExecuted);
    bond_asset_executed_is_set = true;
    bond_asset_executed_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>bond_asset_executed</em>カラムに値を設定します。 
   */
  public final void setBondAssetExecuted( Double p_bondAssetExecuted )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    bond_asset_executed = p_bondAssetExecuted;
    bond_asset_executed_is_set = true;
    bond_asset_executed_is_modified = true;
  }


  /** 
   * <em>payment_amount_designate_0</em>カラムの値を設定します。 
   *
   * @@param p_paymentAmountDesignate0 <em>payment_amount_designate_0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPaymentAmountDesignate0( double p_paymentAmountDesignate0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_amount_designate_0 = new Double(p_paymentAmountDesignate0);
    payment_amount_designate_0_is_set = true;
    payment_amount_designate_0_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>payment_amount_designate_0</em>カラムに値を設定します。 
   */
  public final void setPaymentAmountDesignate0( Double p_paymentAmountDesignate0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    payment_amount_designate_0 = p_paymentAmountDesignate0;
    payment_amount_designate_0_is_set = true;
    payment_amount_designate_0_is_modified = true;
  }


  /** 
   * <em>payment_amount_designate_1</em>カラムの値を設定します。 
   *
   * @@param p_paymentAmountDesignate1 <em>payment_amount_designate_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPaymentAmountDesignate1( double p_paymentAmountDesignate1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_amount_designate_1 = new Double(p_paymentAmountDesignate1);
    payment_amount_designate_1_is_set = true;
    payment_amount_designate_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>payment_amount_designate_1</em>カラムに値を設定します。 
   */
  public final void setPaymentAmountDesignate1( Double p_paymentAmountDesignate1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    payment_amount_designate_1 = p_paymentAmountDesignate1;
    payment_amount_designate_1_is_set = true;
    payment_amount_designate_1_is_modified = true;
  }


  /** 
   * <em>payment_amount_designate_2</em>カラムの値を設定します。 
   *
   * @@param p_paymentAmountDesignate2 <em>payment_amount_designate_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPaymentAmountDesignate2( double p_paymentAmountDesignate2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_amount_designate_2 = new Double(p_paymentAmountDesignate2);
    payment_amount_designate_2_is_set = true;
    payment_amount_designate_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>payment_amount_designate_2</em>カラムに値を設定します。 
   */
  public final void setPaymentAmountDesignate2( Double p_paymentAmountDesignate2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    payment_amount_designate_2 = p_paymentAmountDesignate2;
    payment_amount_designate_2_is_set = true;
    payment_amount_designate_2_is_modified = true;
  }


  /** 
   * <em>payment_amount_designate_3</em>カラムの値を設定します。 
   *
   * @@param p_paymentAmountDesignate3 <em>payment_amount_designate_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPaymentAmountDesignate3( double p_paymentAmountDesignate3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_amount_designate_3 = new Double(p_paymentAmountDesignate3);
    payment_amount_designate_3_is_set = true;
    payment_amount_designate_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>payment_amount_designate_3</em>カラムに値を設定します。 
   */
  public final void setPaymentAmountDesignate3( Double p_paymentAmountDesignate3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    payment_amount_designate_3 = p_paymentAmountDesignate3;
    payment_amount_designate_3_is_set = true;
    payment_amount_designate_3_is_modified = true;
  }


  /** 
   * <em>payment_amount_designate_4</em>カラムの値を設定します。 
   *
   * @@param p_paymentAmountDesignate4 <em>payment_amount_designate_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPaymentAmountDesignate4( double p_paymentAmountDesignate4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_amount_designate_4 = new Double(p_paymentAmountDesignate4);
    payment_amount_designate_4_is_set = true;
    payment_amount_designate_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>payment_amount_designate_4</em>カラムに値を設定します。 
   */
  public final void setPaymentAmountDesignate4( Double p_paymentAmountDesignate4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    payment_amount_designate_4 = p_paymentAmountDesignate4;
    payment_amount_designate_4_is_set = true;
    payment_amount_designate_4_is_modified = true;
  }


  /** 
   * <em>payment_amount_designate_5</em>カラムの値を設定します。 
   *
   * @@param p_paymentAmountDesignate5 <em>payment_amount_designate_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPaymentAmountDesignate5( double p_paymentAmountDesignate5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_amount_designate_5 = new Double(p_paymentAmountDesignate5);
    payment_amount_designate_5_is_set = true;
    payment_amount_designate_5_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>payment_amount_designate_5</em>カラムに値を設定します。 
   */
  public final void setPaymentAmountDesignate5( Double p_paymentAmountDesignate5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    payment_amount_designate_5 = p_paymentAmountDesignate5;
    payment_amount_designate_5_is_set = true;
    payment_amount_designate_5_is_modified = true;
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
   * <em>foreign_equity_asset_delivered</em>カラムの値を設定します。 
   *
   * @@param p_foreignEquityAssetDelivered <em>foreign_equity_asset_delivered</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setForeignEquityAssetDelivered( double p_foreignEquityAssetDelivered )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    foreign_equity_asset_delivered = new Double(p_foreignEquityAssetDelivered);
    foreign_equity_asset_delivered_is_set = true;
    foreign_equity_asset_delivered_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>foreign_equity_asset_delivered</em>カラムに値を設定します。 
   */
  public final void setForeignEquityAssetDelivered( Double p_foreignEquityAssetDelivered )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    foreign_equity_asset_delivered = p_foreignEquityAssetDelivered;
    foreign_equity_asset_delivered_is_set = true;
    foreign_equity_asset_delivered_is_modified = true;
  }


  /** 
   * <em>foreign_equity_asset_executed</em>カラムの値を設定します。 
   *
   * @@param p_foreignEquityAssetExecuted <em>foreign_equity_asset_executed</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setForeignEquityAssetExecuted( double p_foreignEquityAssetExecuted )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    foreign_equity_asset_executed = new Double(p_foreignEquityAssetExecuted);
    foreign_equity_asset_executed_is_set = true;
    foreign_equity_asset_executed_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>foreign_equity_asset_executed</em>カラムに値を設定します。 
   */
  public final void setForeignEquityAssetExecuted( Double p_foreignEquityAssetExecuted )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    foreign_equity_asset_executed = p_foreignEquityAssetExecuted;
    foreign_equity_asset_executed_is_set = true;
    foreign_equity_asset_executed_is_modified = true;
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
            case 'b':
                if ( name.equals("bond_asset_delivered") ) {
                    return this.bond_asset_delivered;
                }
                else if ( name.equals("bond_asset_executed") ) {
                    return this.bond_asset_executed;
                }
                break;
            case 'c':
                if ( name.equals("calc_result_equity_id") ) {
                    return new Long( calc_result_equity_id );
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("equity_asset_delivered") ) {
                    return this.equity_asset_delivered;
                }
                else if ( name.equals("equity_asset_executed") ) {
                    return this.equity_asset_executed;
                }
                break;
            case 'f':
                if ( name.equals("foreign_equity_asset_delivered") ) {
                    return this.foreign_equity_asset_delivered;
                }
                else if ( name.equals("foreign_equity_asset_executed") ) {
                    return this.foreign_equity_asset_executed;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("mini_stock_asset_delivered") ) {
                    return this.mini_stock_asset_delivered;
                }
                else if ( name.equals("mini_stock_asset_executed") ) {
                    return this.mini_stock_asset_executed;
                }
                else if ( name.equals("mutual_fund_asset_delivered") ) {
                    return this.mutual_fund_asset_delivered;
                }
                else if ( name.equals("mutual_fund_asset_executed") ) {
                    return this.mutual_fund_asset_executed;
                }
                break;
            case 'p':
                if ( name.equals("payment_amount_designate_0") ) {
                    return this.payment_amount_designate_0;
                }
                else if ( name.equals("payment_amount_designate_1") ) {
                    return this.payment_amount_designate_1;
                }
                else if ( name.equals("payment_amount_designate_2") ) {
                    return this.payment_amount_designate_2;
                }
                else if ( name.equals("payment_amount_designate_3") ) {
                    return this.payment_amount_designate_3;
                }
                else if ( name.equals("payment_amount_designate_4") ) {
                    return this.payment_amount_designate_4;
                }
                else if ( name.equals("payment_amount_designate_5") ) {
                    return this.payment_amount_designate_5;
                }
                break;
            case 'r':
                if ( name.equals("ruito_asset_delivered") ) {
                    return this.ruito_asset_delivered;
                }
                else if ( name.equals("ruito_asset_executed") ) {
                    return this.ruito_asset_executed;
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
            case 'b':
                if ( name.equals("bond_asset_delivered") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'bond_asset_delivered' must be Double: '"+value+"' is not." );
                    this.bond_asset_delivered = (Double) value;
                    if (this.bond_asset_delivered_is_set)
                        this.bond_asset_delivered_is_modified = true;
                    this.bond_asset_delivered_is_set = true;
                    return;
                }
                else if ( name.equals("bond_asset_executed") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'bond_asset_executed' must be Double: '"+value+"' is not." );
                    this.bond_asset_executed = (Double) value;
                    if (this.bond_asset_executed_is_set)
                        this.bond_asset_executed_is_modified = true;
                    this.bond_asset_executed_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("calc_result_equity_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'calc_result_equity_id' must be Long: '"+value+"' is not." );
                    this.calc_result_equity_id = ((Long) value).longValue();
                    if (this.calc_result_equity_id_is_set)
                        this.calc_result_equity_id_is_modified = true;
                    this.calc_result_equity_id_is_set = true;
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
            case 'e':
                if ( name.equals("equity_asset_delivered") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'equity_asset_delivered' must be Double: '"+value+"' is not." );
                    this.equity_asset_delivered = (Double) value;
                    if (this.equity_asset_delivered_is_set)
                        this.equity_asset_delivered_is_modified = true;
                    this.equity_asset_delivered_is_set = true;
                    return;
                }
                else if ( name.equals("equity_asset_executed") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'equity_asset_executed' must be Double: '"+value+"' is not." );
                    this.equity_asset_executed = (Double) value;
                    if (this.equity_asset_executed_is_set)
                        this.equity_asset_executed_is_modified = true;
                    this.equity_asset_executed_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("foreign_equity_asset_delivered") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'foreign_equity_asset_delivered' must be Double: '"+value+"' is not." );
                    this.foreign_equity_asset_delivered = (Double) value;
                    if (this.foreign_equity_asset_delivered_is_set)
                        this.foreign_equity_asset_delivered_is_modified = true;
                    this.foreign_equity_asset_delivered_is_set = true;
                    return;
                }
                else if ( name.equals("foreign_equity_asset_executed") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'foreign_equity_asset_executed' must be Double: '"+value+"' is not." );
                    this.foreign_equity_asset_executed = (Double) value;
                    if (this.foreign_equity_asset_executed_is_set)
                        this.foreign_equity_asset_executed_is_modified = true;
                    this.foreign_equity_asset_executed_is_set = true;
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
            case 'm':
                if ( name.equals("mini_stock_asset_delivered") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'mini_stock_asset_delivered' must be Double: '"+value+"' is not." );
                    this.mini_stock_asset_delivered = (Double) value;
                    if (this.mini_stock_asset_delivered_is_set)
                        this.mini_stock_asset_delivered_is_modified = true;
                    this.mini_stock_asset_delivered_is_set = true;
                    return;
                }
                else if ( name.equals("mini_stock_asset_executed") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'mini_stock_asset_executed' must be Double: '"+value+"' is not." );
                    this.mini_stock_asset_executed = (Double) value;
                    if (this.mini_stock_asset_executed_is_set)
                        this.mini_stock_asset_executed_is_modified = true;
                    this.mini_stock_asset_executed_is_set = true;
                    return;
                }
                else if ( name.equals("mutual_fund_asset_delivered") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'mutual_fund_asset_delivered' must be Double: '"+value+"' is not." );
                    this.mutual_fund_asset_delivered = (Double) value;
                    if (this.mutual_fund_asset_delivered_is_set)
                        this.mutual_fund_asset_delivered_is_modified = true;
                    this.mutual_fund_asset_delivered_is_set = true;
                    return;
                }
                else if ( name.equals("mutual_fund_asset_executed") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'mutual_fund_asset_executed' must be Double: '"+value+"' is not." );
                    this.mutual_fund_asset_executed = (Double) value;
                    if (this.mutual_fund_asset_executed_is_set)
                        this.mutual_fund_asset_executed_is_modified = true;
                    this.mutual_fund_asset_executed_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("payment_amount_designate_0") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'payment_amount_designate_0' must be Double: '"+value+"' is not." );
                    this.payment_amount_designate_0 = (Double) value;
                    if (this.payment_amount_designate_0_is_set)
                        this.payment_amount_designate_0_is_modified = true;
                    this.payment_amount_designate_0_is_set = true;
                    return;
                }
                else if ( name.equals("payment_amount_designate_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'payment_amount_designate_1' must be Double: '"+value+"' is not." );
                    this.payment_amount_designate_1 = (Double) value;
                    if (this.payment_amount_designate_1_is_set)
                        this.payment_amount_designate_1_is_modified = true;
                    this.payment_amount_designate_1_is_set = true;
                    return;
                }
                else if ( name.equals("payment_amount_designate_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'payment_amount_designate_2' must be Double: '"+value+"' is not." );
                    this.payment_amount_designate_2 = (Double) value;
                    if (this.payment_amount_designate_2_is_set)
                        this.payment_amount_designate_2_is_modified = true;
                    this.payment_amount_designate_2_is_set = true;
                    return;
                }
                else if ( name.equals("payment_amount_designate_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'payment_amount_designate_3' must be Double: '"+value+"' is not." );
                    this.payment_amount_designate_3 = (Double) value;
                    if (this.payment_amount_designate_3_is_set)
                        this.payment_amount_designate_3_is_modified = true;
                    this.payment_amount_designate_3_is_set = true;
                    return;
                }
                else if ( name.equals("payment_amount_designate_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'payment_amount_designate_4' must be Double: '"+value+"' is not." );
                    this.payment_amount_designate_4 = (Double) value;
                    if (this.payment_amount_designate_4_is_set)
                        this.payment_amount_designate_4_is_modified = true;
                    this.payment_amount_designate_4_is_set = true;
                    return;
                }
                else if ( name.equals("payment_amount_designate_5") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'payment_amount_designate_5' must be Double: '"+value+"' is not." );
                    this.payment_amount_designate_5 = (Double) value;
                    if (this.payment_amount_designate_5_is_set)
                        this.payment_amount_designate_5_is_modified = true;
                    this.payment_amount_designate_5_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("ruito_asset_delivered") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'ruito_asset_delivered' must be Double: '"+value+"' is not." );
                    this.ruito_asset_delivered = (Double) value;
                    if (this.ruito_asset_delivered_is_set)
                        this.ruito_asset_delivered_is_modified = true;
                    this.ruito_asset_delivered_is_set = true;
                    return;
                }
                else if ( name.equals("ruito_asset_executed") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'ruito_asset_executed' must be Double: '"+value+"' is not." );
                    this.ruito_asset_executed = (Double) value;
                    if (this.ruito_asset_executed_is_set)
                        this.ruito_asset_executed_is_modified = true;
                    this.ruito_asset_executed_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
