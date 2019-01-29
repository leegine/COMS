head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.58.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	PaymentRequisitionParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpoweradmin.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * payment_requisitionテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link PaymentRequisitionRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link PaymentRequisitionParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link PaymentRequisitionParams}が{@@link PaymentRequisitionRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see PaymentRequisitionPK 
 * @@see PaymentRequisitionRow 
 */
public class PaymentRequisitionParams extends Params implements PaymentRequisitionRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "payment_requisition";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = PaymentRequisitionRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return PaymentRequisitionRow.TYPE;
  }


  /** 
   * <em>payment_requisition_id</em>カラムの値 
   */
  public  long  payment_requisition_id;

  /** 
   * <em>account_id</em>カラムの値 
   */
  public  long  account_id;

  /** 
   * <em>branch_id</em>カラムの値 
   */
  public  long  branch_id;

  /** 
   * <em>occurred_date</em>カラムの値 
   */
  public  java.sql.Timestamp  occurred_date;

  /** 
   * <em>payment_requisition_division</em>カラムの値 
   */
  public  String  payment_requisition_division;

  /** 
   * <em>requisition_status</em>カラムの値 
   */
  public  String  requisition_status;

  /** 
   * <em>payment_requisition_amount</em>カラムの値 
   */
  public  double  payment_requisition_amount;

  /** 
   * <em>margin_collateral_rate</em>カラムの値 
   */
  public  double  margin_collateral_rate;

  /** 
   * <em>calclation_source</em>カラムの値 
   */
  public  String  calclation_source;

  /** 
   * <em>last_updater</em>カラムの値 
   */
  public  String  last_updater;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean payment_requisition_id_is_set = false;
  private boolean payment_requisition_id_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean branch_id_is_set = false;
  private boolean branch_id_is_modified = false;
  private boolean occurred_date_is_set = false;
  private boolean occurred_date_is_modified = false;
  private boolean payment_requisition_division_is_set = false;
  private boolean payment_requisition_division_is_modified = false;
  private boolean requisition_status_is_set = false;
  private boolean requisition_status_is_modified = false;
  private boolean payment_requisition_amount_is_set = false;
  private boolean payment_requisition_amount_is_modified = false;
  private boolean margin_collateral_rate_is_set = false;
  private boolean margin_collateral_rate_is_modified = false;
  private boolean calclation_source_is_set = false;
  private boolean calclation_source_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "payment_requisition_id=" + payment_requisition_id
      + "," + "account_id=" +account_id
      + "," + "branch_id=" +branch_id
      + "," + "occurred_date=" +occurred_date
      + "," + "payment_requisition_division=" +payment_requisition_division
      + "," + "requisition_status=" +requisition_status
      + "," + "payment_requisition_amount=" +payment_requisition_amount
      + "," + "margin_collateral_rate=" +margin_collateral_rate
      + "," + "calclation_source=" +calclation_source
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のPaymentRequisitionParamsオブジェクトを作成します。 
   */
  public PaymentRequisitionParams() {
    payment_requisition_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のPaymentRequisitionRowオブジェクトの値を利用してPaymentRequisitionParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するPaymentRequisitionRowオブジェクト 
   */
  public PaymentRequisitionParams( PaymentRequisitionRow p_row )
  {
    payment_requisition_id = p_row.getPaymentRequisitionId();
    payment_requisition_id_is_set = p_row.getPaymentRequisitionIdIsSet();
    payment_requisition_id_is_modified = p_row.getPaymentRequisitionIdIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    branch_id = p_row.getBranchId();
    branch_id_is_set = p_row.getBranchIdIsSet();
    branch_id_is_modified = p_row.getBranchIdIsModified();
    occurred_date = p_row.getOccurredDate();
    occurred_date_is_set = p_row.getOccurredDateIsSet();
    occurred_date_is_modified = p_row.getOccurredDateIsModified();
    payment_requisition_division = p_row.getPaymentRequisitionDivision();
    payment_requisition_division_is_set = p_row.getPaymentRequisitionDivisionIsSet();
    payment_requisition_division_is_modified = p_row.getPaymentRequisitionDivisionIsModified();
    requisition_status = p_row.getRequisitionStatus();
    requisition_status_is_set = p_row.getRequisitionStatusIsSet();
    requisition_status_is_modified = p_row.getRequisitionStatusIsModified();
    payment_requisition_amount = p_row.getPaymentRequisitionAmount();
    payment_requisition_amount_is_set = p_row.getPaymentRequisitionAmountIsSet();
    payment_requisition_amount_is_modified = p_row.getPaymentRequisitionAmountIsModified();
    margin_collateral_rate = p_row.getMarginCollateralRate();
    margin_collateral_rate_is_set = p_row.getMarginCollateralRateIsSet();
    margin_collateral_rate_is_modified = p_row.getMarginCollateralRateIsModified();
    calclation_source = p_row.getCalclationSource();
    calclation_source_is_set = p_row.getCalclationSourceIsSet();
    calclation_source_is_modified = p_row.getCalclationSourceIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
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
      account_id_is_set = true;
      account_id_is_modified = true;
      branch_id_is_set = true;
      branch_id_is_modified = true;
      occurred_date_is_set = true;
      occurred_date_is_modified = true;
      payment_requisition_division_is_set = true;
      payment_requisition_division_is_modified = true;
      requisition_status_is_set = true;
      requisition_status_is_modified = true;
      payment_requisition_amount_is_set = true;
      payment_requisition_amount_is_modified = true;
      margin_collateral_rate_is_set = true;
      margin_collateral_rate_is_modified = true;
      calclation_source_is_set = true;
      calclation_source_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
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
    if ( !( other instanceof PaymentRequisitionRow ) )
       return false;
    return fieldsEqual( (PaymentRequisitionRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のPaymentRequisitionRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( PaymentRequisitionRow row )
  {
    if ( payment_requisition_id != row.getPaymentRequisitionId() )
      return false;
    if ( account_id != row.getAccountId() )
      return false;
    if ( branch_id != row.getBranchId() )
      return false;
    if ( occurred_date == null ) {
      if ( row.getOccurredDate() != null )
        return false;
    } else if ( !occurred_date.equals( row.getOccurredDate() ) ) {
        return false;
    }
    if ( payment_requisition_division == null ) {
      if ( row.getPaymentRequisitionDivision() != null )
        return false;
    } else if ( !payment_requisition_division.equals( row.getPaymentRequisitionDivision() ) ) {
        return false;
    }
    if ( requisition_status == null ) {
      if ( row.getRequisitionStatus() != null )
        return false;
    } else if ( !requisition_status.equals( row.getRequisitionStatus() ) ) {
        return false;
    }
    if ( payment_requisition_amount != row.getPaymentRequisitionAmount() )
      return false;
    if ( margin_collateral_rate != row.getMarginCollateralRate() )
      return false;
    if ( calclation_source == null ) {
      if ( row.getCalclationSource() != null )
        return false;
    } else if ( !calclation_source.equals( row.getCalclationSource() ) ) {
        return false;
    }
    if ( last_updater == null ) {
      if ( row.getLastUpdater() != null )
        return false;
    } else if ( !last_updater.equals( row.getLastUpdater() ) ) {
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
      return  ((int) payment_requisition_id)
        + ((int) account_id)
        + ((int) branch_id)
        + (occurred_date!=null? occurred_date.hashCode(): 0) 
        + (payment_requisition_division!=null? payment_requisition_division.hashCode(): 0) 
        + (requisition_status!=null? requisition_status.hashCode(): 0) 
        + ((int) payment_requisition_amount)
        + ((int) margin_collateral_rate)
        + (calclation_source!=null? calclation_source.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_id' must be set before inserting.");
		if ( !branch_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'branch_id' must be set before inserting.");
		if ( !occurred_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'occurred_date' must be set before inserting.");
		if ( !payment_requisition_division_is_set )
			throw new IllegalArgumentException("non-nullable field 'payment_requisition_division' must be set before inserting.");
		if ( !requisition_status_is_set )
			throw new IllegalArgumentException("non-nullable field 'requisition_status' must be set before inserting.");
		if ( !payment_requisition_amount_is_set )
			throw new IllegalArgumentException("non-nullable field 'payment_requisition_amount' must be set before inserting.");
		if ( !margin_collateral_rate_is_set )
			throw new IllegalArgumentException("non-nullable field 'margin_collateral_rate' must be set before inserting.");
		if ( !calclation_source_is_set )
			throw new IllegalArgumentException("non-nullable field 'calclation_source' must be set before inserting.");
		if ( !last_updater_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updater' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("payment_requisition_id",new Long(payment_requisition_id));
		map.put("account_id",new Long(account_id));
		map.put("branch_id",new Long(branch_id));
		map.put("occurred_date",occurred_date);
		map.put("payment_requisition_division",payment_requisition_division);
		map.put("requisition_status",requisition_status);
		map.put("payment_requisition_amount",new Double(payment_requisition_amount));
		map.put("margin_collateral_rate",new Double(margin_collateral_rate));
		map.put("calclation_source",calclation_source);
		map.put("last_updater",last_updater);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( account_id_is_modified )
			map.put("account_id",new Long(account_id));
		if ( branch_id_is_modified )
			map.put("branch_id",new Long(branch_id));
		if ( occurred_date_is_modified )
			map.put("occurred_date",occurred_date);
		if ( payment_requisition_division_is_modified )
			map.put("payment_requisition_division",payment_requisition_division);
		if ( requisition_status_is_modified )
			map.put("requisition_status",requisition_status);
		if ( payment_requisition_amount_is_modified )
			map.put("payment_requisition_amount",new Double(payment_requisition_amount));
		if ( margin_collateral_rate_is_modified )
			map.put("margin_collateral_rate",new Double(margin_collateral_rate));
		if ( calclation_source_is_modified )
			map.put("calclation_source",calclation_source);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			if ( branch_id_is_set )
				map.put("branch_id",new Long(branch_id));
			if ( occurred_date_is_set )
				map.put("occurred_date",occurred_date);
			if ( payment_requisition_division_is_set )
				map.put("payment_requisition_division",payment_requisition_division);
			if ( requisition_status_is_set )
				map.put("requisition_status",requisition_status);
			if ( payment_requisition_amount_is_set )
				map.put("payment_requisition_amount",new Double(payment_requisition_amount));
			if ( margin_collateral_rate_is_set )
				map.put("margin_collateral_rate",new Double(margin_collateral_rate));
			if ( calclation_source_is_set )
				map.put("calclation_source",calclation_source);
			if ( last_updater_is_set )
				map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>payment_requisition_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getPaymentRequisitionId()
  {
    return payment_requisition_id;
  }


  /** 
   * <em>payment_requisition_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentRequisitionIdIsSet() {
    return payment_requisition_id_is_set;
  }


  /** 
   * <em>payment_requisition_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentRequisitionIdIsModified() {
    return payment_requisition_id_is_modified;
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
   * <em>branch_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getBranchId()
  {
    return branch_id;
  }


  /** 
   * <em>branch_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchIdIsSet() {
    return branch_id_is_set;
  }


  /** 
   * <em>branch_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchIdIsModified() {
    return branch_id_is_modified;
  }


  /** 
   * <em>occurred_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getOccurredDate()
  {
    return occurred_date;
  }


  /** 
   * <em>occurred_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOccurredDateIsSet() {
    return occurred_date_is_set;
  }


  /** 
   * <em>occurred_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOccurredDateIsModified() {
    return occurred_date_is_modified;
  }


  /** 
   * <em>payment_requisition_division</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPaymentRequisitionDivision()
  {
    return payment_requisition_division;
  }


  /** 
   * <em>payment_requisition_division</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentRequisitionDivisionIsSet() {
    return payment_requisition_division_is_set;
  }


  /** 
   * <em>payment_requisition_division</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentRequisitionDivisionIsModified() {
    return payment_requisition_division_is_modified;
  }


  /** 
   * <em>requisition_status</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRequisitionStatus()
  {
    return requisition_status;
  }


  /** 
   * <em>requisition_status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRequisitionStatusIsSet() {
    return requisition_status_is_set;
  }


  /** 
   * <em>requisition_status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRequisitionStatusIsModified() {
    return requisition_status_is_modified;
  }


  /** 
   * <em>payment_requisition_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPaymentRequisitionAmount()
  {
    return payment_requisition_amount;
  }


  /** 
   * <em>payment_requisition_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentRequisitionAmountIsSet() {
    return payment_requisition_amount_is_set;
  }


  /** 
   * <em>payment_requisition_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentRequisitionAmountIsModified() {
    return payment_requisition_amount_is_modified;
  }


  /** 
   * <em>margin_collateral_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMarginCollateralRate()
  {
    return margin_collateral_rate;
  }


  /** 
   * <em>margin_collateral_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginCollateralRateIsSet() {
    return margin_collateral_rate_is_set;
  }


  /** 
   * <em>margin_collateral_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginCollateralRateIsModified() {
    return margin_collateral_rate_is_modified;
  }


  /** 
   * <em>calclation_source</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCalclationSource()
  {
    return calclation_source;
  }


  /** 
   * <em>calclation_source</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCalclationSourceIsSet() {
    return calclation_source_is_set;
  }


  /** 
   * <em>calclation_source</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCalclationSourceIsModified() {
    return calclation_source_is_modified;
  }


  /** 
   * <em>last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLastUpdater()
  {
    return last_updater;
  }


  /** 
   * <em>last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdaterIsSet() {
    return last_updater_is_set;
  }


  /** 
   * <em>last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdaterIsModified() {
    return last_updater_is_modified;
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
    return new PaymentRequisitionPK(payment_requisition_id);
  }


  /** 
   * <em>payment_requisition_id</em>カラムの値を設定します。 
   *
   * @@param p_paymentRequisitionId <em>payment_requisition_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setPaymentRequisitionId( long p_paymentRequisitionId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_requisition_id = p_paymentRequisitionId;
    payment_requisition_id_is_set = true;
    payment_requisition_id_is_modified = true;
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
   * <em>branch_id</em>カラムの値を設定します。 
   *
   * @@param p_branchId <em>branch_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setBranchId( long p_branchId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_id = p_branchId;
    branch_id_is_set = true;
    branch_id_is_modified = true;
  }


  /** 
   * <em>occurred_date</em>カラムの値を設定します。 
   *
   * @@param p_occurredDate <em>occurred_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setOccurredDate( java.sql.Timestamp p_occurredDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    occurred_date = p_occurredDate;
    occurred_date_is_set = true;
    occurred_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>occurred_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setOccurredDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    occurred_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    occurred_date_is_set = true;
    occurred_date_is_modified = true;
  }


  /** 
   * <em>payment_requisition_division</em>カラムの値を設定します。 
   *
   * @@param p_paymentRequisitionDivision <em>payment_requisition_division</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPaymentRequisitionDivision( String p_paymentRequisitionDivision )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_requisition_division = p_paymentRequisitionDivision;
    payment_requisition_division_is_set = true;
    payment_requisition_division_is_modified = true;
  }


  /** 
   * <em>requisition_status</em>カラムの値を設定します。 
   *
   * @@param p_requisitionStatus <em>requisition_status</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setRequisitionStatus( String p_requisitionStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    requisition_status = p_requisitionStatus;
    requisition_status_is_set = true;
    requisition_status_is_modified = true;
  }


  /** 
   * <em>payment_requisition_amount</em>カラムの値を設定します。 
   *
   * @@param p_paymentRequisitionAmount <em>payment_requisition_amount</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPaymentRequisitionAmount( double p_paymentRequisitionAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_requisition_amount = p_paymentRequisitionAmount;
    payment_requisition_amount_is_set = true;
    payment_requisition_amount_is_modified = true;
  }


  /** 
   * <em>margin_collateral_rate</em>カラムの値を設定します。 
   *
   * @@param p_marginCollateralRate <em>margin_collateral_rate</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMarginCollateralRate( double p_marginCollateralRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_collateral_rate = p_marginCollateralRate;
    margin_collateral_rate_is_set = true;
    margin_collateral_rate_is_modified = true;
  }


  /** 
   * <em>calclation_source</em>カラムの値を設定します。 
   *
   * @@param p_calclationSource <em>calclation_source</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCalclationSource( String p_calclationSource )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    calclation_source = p_calclationSource;
    calclation_source_is_set = true;
    calclation_source_is_modified = true;
  }


  /** 
   * <em>last_updater</em>カラムの値を設定します。 
   *
   * @@param p_lastUpdater <em>last_updater</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setLastUpdater( String p_lastUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updater = p_lastUpdater;
    last_updater_is_set = true;
    last_updater_is_modified = true;
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
                if ( name.equals("account_id") ) {
                    return new Long( account_id );
                }
                break;
            case 'b':
                if ( name.equals("branch_id") ) {
                    return new Long( branch_id );
                }
                break;
            case 'c':
                if ( name.equals("calclation_source") ) {
                    return this.calclation_source;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
                    return this.last_updater;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("margin_collateral_rate") ) {
                    return new Double( margin_collateral_rate );
                }
                break;
            case 'o':
                if ( name.equals("occurred_date") ) {
                    return this.occurred_date;
                }
                break;
            case 'p':
                if ( name.equals("payment_requisition_id") ) {
                    return new Long( payment_requisition_id );
                }
                else if ( name.equals("payment_requisition_division") ) {
                    return this.payment_requisition_division;
                }
                else if ( name.equals("payment_requisition_amount") ) {
                    return new Double( payment_requisition_amount );
                }
                break;
            case 'r':
                if ( name.equals("requisition_status") ) {
                    return this.requisition_status;
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
                if ( name.equals("branch_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'branch_id' must be Long: '"+value+"' is not." );
                    this.branch_id = ((Long) value).longValue();
                    if (this.branch_id_is_set)
                        this.branch_id_is_modified = true;
                    this.branch_id_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("calclation_source") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'calclation_source' must be String: '"+value+"' is not." );
                    this.calclation_source = (String) value;
                    if (this.calclation_source_is_set)
                        this.calclation_source_is_modified = true;
                    this.calclation_source_is_set = true;
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
            case 'l':
                if ( name.equals("last_updater") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'last_updater' must be String: '"+value+"' is not." );
                    this.last_updater = (String) value;
                    if (this.last_updater_is_set)
                        this.last_updater_is_modified = true;
                    this.last_updater_is_set = true;
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
                if ( name.equals("margin_collateral_rate") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_collateral_rate' must be Double: '"+value+"' is not." );
                    this.margin_collateral_rate = ((Double) value).doubleValue();
                    if (this.margin_collateral_rate_is_set)
                        this.margin_collateral_rate_is_modified = true;
                    this.margin_collateral_rate_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("occurred_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'occurred_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.occurred_date = (java.sql.Timestamp) value;
                    if (this.occurred_date_is_set)
                        this.occurred_date_is_modified = true;
                    this.occurred_date_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("payment_requisition_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'payment_requisition_id' must be Long: '"+value+"' is not." );
                    this.payment_requisition_id = ((Long) value).longValue();
                    if (this.payment_requisition_id_is_set)
                        this.payment_requisition_id_is_modified = true;
                    this.payment_requisition_id_is_set = true;
                    return;
                }
                else if ( name.equals("payment_requisition_division") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'payment_requisition_division' must be String: '"+value+"' is not." );
                    this.payment_requisition_division = (String) value;
                    if (this.payment_requisition_division_is_set)
                        this.payment_requisition_division_is_modified = true;
                    this.payment_requisition_division_is_set = true;
                    return;
                }
                else if ( name.equals("payment_requisition_amount") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'payment_requisition_amount' must be Double: '"+value+"' is not." );
                    this.payment_requisition_amount = ((Double) value).doubleValue();
                    if (this.payment_requisition_amount_is_set)
                        this.payment_requisition_amount_is_modified = true;
                    this.payment_requisition_amount_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("requisition_status") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'requisition_status' must be String: '"+value+"' is not." );
                    this.requisition_status = (String) value;
                    if (this.requisition_status_is_set)
                        this.requisition_status_is_modified = true;
                    this.requisition_status_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
