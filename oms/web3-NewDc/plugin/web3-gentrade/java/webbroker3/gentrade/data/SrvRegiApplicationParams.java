head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.44.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	SrvRegiApplicationParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * srv_regi_applicationテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link SrvRegiApplicationRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link SrvRegiApplicationParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link SrvRegiApplicationParams}が{@@link SrvRegiApplicationRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SrvRegiApplicationPK 
 * @@see SrvRegiApplicationRow 
 */
public class SrvRegiApplicationParams extends Params implements SrvRegiApplicationRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "srv_regi_application";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = SrvRegiApplicationRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return SrvRegiApplicationRow.TYPE;
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
   * <em>srv_div</em>カラムの値 
   */
  public  String  srv_div;

  /** 
   * <em>account_code</em>カラムの値 
   */
  public  String  account_code;

  /** 
   * <em>regist_id</em>カラムの値 
   */
  public  long  regist_id;

  /** 
   * <em>appli_start_date</em>カラムの値 
   */
  public  java.sql.Timestamp  appli_start_date;

  /** 
   * <em>appli_end_date</em>カラムの値 
   */
  public  java.sql.Timestamp  appli_end_date;

  /** 
   * <em>order_id</em>カラムの値 
   */
  public  Long  order_id;

  /** 
   * <em>appli_date</em>カラムの値 
   */
  public  java.sql.Timestamp  appli_date;

  /** 
   * <em>payment_div</em>カラムの値 
   */
  public  String  payment_div;

  /** 
   * <em>appli_lot_div</em>カラムの値 
   */
  public  String  appli_lot_div;

  /** 
   * <em>effective_div</em>カラムの値 
   */
  public  String  effective_div;

  /** 
   * <em>cancel_div</em>カラムの値 
   */
  public  String  cancel_div;

  /** 
   * <em>use_amt</em>カラムの値 
   */
  public  Long  use_amt;

  /** 
   * <em>payment_date</em>カラムの値 
   */
  public  java.sql.Timestamp  payment_date;

  /** 
   * <em>cancel_limit_date</em>カラムの値 
   */
  public  java.sql.Timestamp  cancel_limit_date;

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

  /** 
   * <em>free_srv_div</em>カラムの値 
   */
  public  String  free_srv_div;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean srv_div_is_set = false;
  private boolean srv_div_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean regist_id_is_set = false;
  private boolean regist_id_is_modified = false;
  private boolean appli_start_date_is_set = false;
  private boolean appli_start_date_is_modified = false;
  private boolean appli_end_date_is_set = false;
  private boolean appli_end_date_is_modified = false;
  private boolean order_id_is_set = false;
  private boolean order_id_is_modified = false;
  private boolean appli_date_is_set = false;
  private boolean appli_date_is_modified = false;
  private boolean payment_div_is_set = false;
  private boolean payment_div_is_modified = false;
  private boolean appli_lot_div_is_set = false;
  private boolean appli_lot_div_is_modified = false;
  private boolean effective_div_is_set = false;
  private boolean effective_div_is_modified = false;
  private boolean cancel_div_is_set = false;
  private boolean cancel_div_is_modified = false;
  private boolean use_amt_is_set = false;
  private boolean use_amt_is_modified = false;
  private boolean payment_date_is_set = false;
  private boolean payment_date_is_modified = false;
  private boolean cancel_limit_date_is_set = false;
  private boolean cancel_limit_date_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean free_srv_div_is_set = false;
  private boolean free_srv_div_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "branch_code=" + branch_code
      + "," + "srv_div=" + srv_div
      + "," + "account_code=" + account_code
      + "," + "regist_id=" + regist_id
      + "," + "appli_start_date=" +appli_start_date
      + "," + "appli_end_date=" +appli_end_date
      + "," + "order_id=" +order_id
      + "," + "appli_date=" +appli_date
      + "," + "payment_div=" +payment_div
      + "," + "appli_lot_div=" +appli_lot_div
      + "," + "effective_div=" +effective_div
      + "," + "cancel_div=" +cancel_div
      + "," + "use_amt=" +use_amt
      + "," + "payment_date=" +payment_date
      + "," + "cancel_limit_date=" +cancel_limit_date
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "free_srv_div=" +free_srv_div
      + "}";
  }


  /** 
   * 値が未設定のSrvRegiApplicationParamsオブジェクトを作成します。 
   */
  public SrvRegiApplicationParams() {
    institution_code_is_modified = true;
    branch_code_is_modified = true;
    srv_div_is_modified = true;
    account_code_is_modified = true;
    regist_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のSrvRegiApplicationRowオブジェクトの値を利用してSrvRegiApplicationParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するSrvRegiApplicationRowオブジェクト 
   */
  public SrvRegiApplicationParams( SrvRegiApplicationRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    srv_div = p_row.getSrvDiv();
    srv_div_is_set = p_row.getSrvDivIsSet();
    srv_div_is_modified = p_row.getSrvDivIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    regist_id = p_row.getRegistId();
    regist_id_is_set = p_row.getRegistIdIsSet();
    regist_id_is_modified = p_row.getRegistIdIsModified();
    appli_start_date = p_row.getAppliStartDate();
    appli_start_date_is_set = p_row.getAppliStartDateIsSet();
    appli_start_date_is_modified = p_row.getAppliStartDateIsModified();
    appli_end_date = p_row.getAppliEndDate();
    appli_end_date_is_set = p_row.getAppliEndDateIsSet();
    appli_end_date_is_modified = p_row.getAppliEndDateIsModified();
    if ( !p_row.getOrderIdIsNull() )
      order_id = new Long( p_row.getOrderId() );
    order_id_is_set = p_row.getOrderIdIsSet();
    order_id_is_modified = p_row.getOrderIdIsModified();
    appli_date = p_row.getAppliDate();
    appli_date_is_set = p_row.getAppliDateIsSet();
    appli_date_is_modified = p_row.getAppliDateIsModified();
    payment_div = p_row.getPaymentDiv();
    payment_div_is_set = p_row.getPaymentDivIsSet();
    payment_div_is_modified = p_row.getPaymentDivIsModified();
    appli_lot_div = p_row.getAppliLotDiv();
    appli_lot_div_is_set = p_row.getAppliLotDivIsSet();
    appli_lot_div_is_modified = p_row.getAppliLotDivIsModified();
    effective_div = p_row.getEffectiveDiv();
    effective_div_is_set = p_row.getEffectiveDivIsSet();
    effective_div_is_modified = p_row.getEffectiveDivIsModified();
    cancel_div = p_row.getCancelDiv();
    cancel_div_is_set = p_row.getCancelDivIsSet();
    cancel_div_is_modified = p_row.getCancelDivIsModified();
    if ( !p_row.getUseAmtIsNull() )
      use_amt = new Long( p_row.getUseAmt() );
    use_amt_is_set = p_row.getUseAmtIsSet();
    use_amt_is_modified = p_row.getUseAmtIsModified();
    payment_date = p_row.getPaymentDate();
    payment_date_is_set = p_row.getPaymentDateIsSet();
    payment_date_is_modified = p_row.getPaymentDateIsModified();
    cancel_limit_date = p_row.getCancelLimitDate();
    cancel_limit_date_is_set = p_row.getCancelLimitDateIsSet();
    cancel_limit_date_is_modified = p_row.getCancelLimitDateIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    free_srv_div = p_row.getFreeSrvDiv();
    free_srv_div_is_set = p_row.getFreeSrvDivIsSet();
    free_srv_div_is_modified = p_row.getFreeSrvDivIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      appli_start_date_is_set = true;
      appli_start_date_is_modified = true;
      appli_end_date_is_set = true;
      appli_end_date_is_modified = true;
      order_id_is_set = true;
      order_id_is_modified = true;
      appli_date_is_set = true;
      appli_date_is_modified = true;
      payment_div_is_set = true;
      payment_div_is_modified = true;
      appli_lot_div_is_set = true;
      appli_lot_div_is_modified = true;
      effective_div_is_set = true;
      effective_div_is_modified = true;
      cancel_div_is_set = true;
      cancel_div_is_modified = true;
      use_amt_is_set = true;
      use_amt_is_modified = true;
      payment_date_is_set = true;
      payment_date_is_modified = true;
      cancel_limit_date_is_set = true;
      cancel_limit_date_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      free_srv_div_is_set = true;
      free_srv_div_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof SrvRegiApplicationRow ) )
       return false;
    return fieldsEqual( (SrvRegiApplicationRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のSrvRegiApplicationRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( SrvRegiApplicationRow row )
  {
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
    if ( srv_div == null ) {
      if ( row.getSrvDiv() != null )
        return false;
    } else if ( !srv_div.equals( row.getSrvDiv() ) ) {
        return false;
    }
    if ( account_code == null ) {
      if ( row.getAccountCode() != null )
        return false;
    } else if ( !account_code.equals( row.getAccountCode() ) ) {
        return false;
    }
    if ( regist_id != row.getRegistId() )
      return false;
    if ( appli_start_date == null ) {
      if ( row.getAppliStartDate() != null )
        return false;
    } else if ( !appli_start_date.equals( row.getAppliStartDate() ) ) {
        return false;
    }
    if ( appli_end_date == null ) {
      if ( row.getAppliEndDate() != null )
        return false;
    } else if ( !appli_end_date.equals( row.getAppliEndDate() ) ) {
        return false;
    }
    if ( order_id == null ) {
      if ( !row.getOrderIdIsNull() )
        return false;
    } else if ( row.getOrderIdIsNull() || ( order_id.longValue() != row.getOrderId() ) ) {
        return false;
    }
    if ( appli_date == null ) {
      if ( row.getAppliDate() != null )
        return false;
    } else if ( !appli_date.equals( row.getAppliDate() ) ) {
        return false;
    }
    if ( payment_div == null ) {
      if ( row.getPaymentDiv() != null )
        return false;
    } else if ( !payment_div.equals( row.getPaymentDiv() ) ) {
        return false;
    }
    if ( appli_lot_div == null ) {
      if ( row.getAppliLotDiv() != null )
        return false;
    } else if ( !appli_lot_div.equals( row.getAppliLotDiv() ) ) {
        return false;
    }
    if ( effective_div == null ) {
      if ( row.getEffectiveDiv() != null )
        return false;
    } else if ( !effective_div.equals( row.getEffectiveDiv() ) ) {
        return false;
    }
    if ( cancel_div == null ) {
      if ( row.getCancelDiv() != null )
        return false;
    } else if ( !cancel_div.equals( row.getCancelDiv() ) ) {
        return false;
    }
    if ( use_amt == null ) {
      if ( !row.getUseAmtIsNull() )
        return false;
    } else if ( row.getUseAmtIsNull() || ( use_amt.longValue() != row.getUseAmt() ) ) {
        return false;
    }
    if ( payment_date == null ) {
      if ( row.getPaymentDate() != null )
        return false;
    } else if ( !payment_date.equals( row.getPaymentDate() ) ) {
        return false;
    }
    if ( cancel_limit_date == null ) {
      if ( row.getCancelLimitDate() != null )
        return false;
    } else if ( !cancel_limit_date.equals( row.getCancelLimitDate() ) ) {
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
    if ( free_srv_div == null ) {
      if ( row.getFreeSrvDiv() != null )
        return false;
    } else if ( !free_srv_div.equals( row.getFreeSrvDiv() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (srv_div!=null? srv_div.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + ((int) regist_id)
        + (appli_start_date!=null? appli_start_date.hashCode(): 0) 
        + (appli_end_date!=null? appli_end_date.hashCode(): 0) 
        + (order_id!=null? order_id.hashCode(): 0) 
        + (appli_date!=null? appli_date.hashCode(): 0) 
        + (payment_div!=null? payment_div.hashCode(): 0) 
        + (appli_lot_div!=null? appli_lot_div.hashCode(): 0) 
        + (effective_div!=null? effective_div.hashCode(): 0) 
        + (cancel_div!=null? cancel_div.hashCode(): 0) 
        + (use_amt!=null? use_amt.hashCode(): 0) 
        + (payment_date!=null? payment_date.hashCode(): 0) 
        + (cancel_limit_date!=null? cancel_limit_date.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (free_srv_div!=null? free_srv_div.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !appli_start_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'appli_start_date' must be set before inserting.");
		if ( !appli_end_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'appli_end_date' must be set before inserting.");
		if ( !appli_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'appli_date' must be set before inserting.");
		if ( !payment_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'payment_div' must be set before inserting.");
		if ( !appli_lot_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'appli_lot_div' must be set before inserting.");
		if ( !effective_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'effective_div' must be set before inserting.");
		if ( !cancel_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'cancel_div' must be set before inserting.");
		if ( !last_updater_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updater' must be set before inserting.");
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
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("srv_div",srv_div);
		map.put("account_code",account_code);
		map.put("regist_id",new Long(regist_id));
		map.put("appli_start_date",appli_start_date);
		map.put("appli_end_date",appli_end_date);
		if ( order_id != null )
			map.put("order_id",order_id);
		map.put("appli_date",appli_date);
		map.put("payment_div",payment_div);
		map.put("appli_lot_div",appli_lot_div);
		map.put("effective_div",effective_div);
		map.put("cancel_div",cancel_div);
		if ( use_amt != null )
			map.put("use_amt",use_amt);
		if ( payment_date != null )
			map.put("payment_date",payment_date);
		if ( cancel_limit_date != null )
			map.put("cancel_limit_date",cancel_limit_date);
		map.put("last_updater",last_updater);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		if ( free_srv_div != null )
			map.put("free_srv_div",free_srv_div);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( appli_start_date_is_modified )
			map.put("appli_start_date",appli_start_date);
		if ( appli_end_date_is_modified )
			map.put("appli_end_date",appli_end_date);
		if ( order_id_is_modified )
			map.put("order_id",order_id);
		if ( appli_date_is_modified )
			map.put("appli_date",appli_date);
		if ( payment_div_is_modified )
			map.put("payment_div",payment_div);
		if ( appli_lot_div_is_modified )
			map.put("appli_lot_div",appli_lot_div);
		if ( effective_div_is_modified )
			map.put("effective_div",effective_div);
		if ( cancel_div_is_modified )
			map.put("cancel_div",cancel_div);
		if ( use_amt_is_modified )
			map.put("use_amt",use_amt);
		if ( payment_date_is_modified )
			map.put("payment_date",payment_date);
		if ( cancel_limit_date_is_modified )
			map.put("cancel_limit_date",cancel_limit_date);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( free_srv_div_is_modified )
			map.put("free_srv_div",free_srv_div);
		if (map.size() == 0) {
			if ( appli_start_date_is_set )
				map.put("appli_start_date",appli_start_date);
			if ( appli_end_date_is_set )
				map.put("appli_end_date",appli_end_date);
			map.put("order_id",order_id);
			if ( appli_date_is_set )
				map.put("appli_date",appli_date);
			if ( payment_div_is_set )
				map.put("payment_div",payment_div);
			if ( appli_lot_div_is_set )
				map.put("appli_lot_div",appli_lot_div);
			if ( effective_div_is_set )
				map.put("effective_div",effective_div);
			if ( cancel_div_is_set )
				map.put("cancel_div",cancel_div);
			map.put("use_amt",use_amt);
			map.put("payment_date",payment_date);
			map.put("cancel_limit_date",cancel_limit_date);
			if ( last_updater_is_set )
				map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("free_srv_div",free_srv_div);
		}
		return map;
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
   * <em>srv_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSrvDiv()
  {
    return srv_div;
  }


  /** 
   * <em>srv_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSrvDivIsSet() {
    return srv_div_is_set;
  }


  /** 
   * <em>srv_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSrvDivIsModified() {
    return srv_div_is_modified;
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
   * <em>regist_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getRegistId()
  {
    return regist_id;
  }


  /** 
   * <em>regist_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRegistIdIsSet() {
    return regist_id_is_set;
  }


  /** 
   * <em>regist_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRegistIdIsModified() {
    return regist_id_is_modified;
  }


  /** 
   * <em>appli_start_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getAppliStartDate()
  {
    return appli_start_date;
  }


  /** 
   * <em>appli_start_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAppliStartDateIsSet() {
    return appli_start_date_is_set;
  }


  /** 
   * <em>appli_start_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAppliStartDateIsModified() {
    return appli_start_date_is_modified;
  }


  /** 
   * <em>appli_end_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getAppliEndDate()
  {
    return appli_end_date;
  }


  /** 
   * <em>appli_end_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAppliEndDateIsSet() {
    return appli_end_date_is_set;
  }


  /** 
   * <em>appli_end_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAppliEndDateIsModified() {
    return appli_end_date_is_modified;
  }


  /** 
   * <em>order_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getOrderId()
  {
    return ( order_id==null? ((long)0): order_id.longValue() );
  }


  /** 
   * <em>order_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOrderIdIsNull()
  {
    return order_id == null;
  }


  /** 
   * <em>order_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderIdIsSet() {
    return order_id_is_set;
  }


  /** 
   * <em>order_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderIdIsModified() {
    return order_id_is_modified;
  }


  /** 
   * <em>appli_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getAppliDate()
  {
    return appli_date;
  }


  /** 
   * <em>appli_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAppliDateIsSet() {
    return appli_date_is_set;
  }


  /** 
   * <em>appli_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAppliDateIsModified() {
    return appli_date_is_modified;
  }


  /** 
   * <em>payment_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPaymentDiv()
  {
    return payment_div;
  }


  /** 
   * <em>payment_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentDivIsSet() {
    return payment_div_is_set;
  }


  /** 
   * <em>payment_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentDivIsModified() {
    return payment_div_is_modified;
  }


  /** 
   * <em>appli_lot_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAppliLotDiv()
  {
    return appli_lot_div;
  }


  /** 
   * <em>appli_lot_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAppliLotDivIsSet() {
    return appli_lot_div_is_set;
  }


  /** 
   * <em>appli_lot_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAppliLotDivIsModified() {
    return appli_lot_div_is_modified;
  }


  /** 
   * <em>effective_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getEffectiveDiv()
  {
    return effective_div;
  }


  /** 
   * <em>effective_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEffectiveDivIsSet() {
    return effective_div_is_set;
  }


  /** 
   * <em>effective_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEffectiveDivIsModified() {
    return effective_div_is_modified;
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
   * <em>use_amt</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getUseAmt()
  {
    return ( use_amt==null? ((long)0): use_amt.longValue() );
  }


  /** 
   * <em>use_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUseAmtIsNull()
  {
    return use_amt == null;
  }


  /** 
   * <em>use_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUseAmtIsSet() {
    return use_amt_is_set;
  }


  /** 
   * <em>use_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUseAmtIsModified() {
    return use_amt_is_modified;
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
   * <em>cancel_limit_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getCancelLimitDate()
  {
    return cancel_limit_date;
  }


  /** 
   * <em>cancel_limit_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCancelLimitDateIsSet() {
    return cancel_limit_date_is_set;
  }


  /** 
   * <em>cancel_limit_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCancelLimitDateIsModified() {
    return cancel_limit_date_is_modified;
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
   * <em>free_srv_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFreeSrvDiv()
  {
    return free_srv_div;
  }


  /** 
   * <em>free_srv_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFreeSrvDivIsSet() {
    return free_srv_div_is_set;
  }


  /** 
   * <em>free_srv_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFreeSrvDivIsModified() {
    return free_srv_div_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new SrvRegiApplicationPK(institution_code, branch_code, srv_div, account_code, regist_id);
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
   * <em>srv_div</em>カラムの値を設定します。 
   *
   * @@param p_srvDiv <em>srv_div</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setSrvDiv( String p_srvDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    srv_div = p_srvDiv;
    srv_div_is_set = true;
    srv_div_is_modified = true;
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
   * <em>regist_id</em>カラムの値を設定します。 
   *
   * @@param p_registId <em>regist_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setRegistId( long p_registId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    regist_id = p_registId;
    regist_id_is_set = true;
    regist_id_is_modified = true;
  }


  /** 
   * <em>appli_start_date</em>カラムの値を設定します。 
   *
   * @@param p_appliStartDate <em>appli_start_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setAppliStartDate( java.sql.Timestamp p_appliStartDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    appli_start_date = p_appliStartDate;
    appli_start_date_is_set = true;
    appli_start_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>appli_start_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setAppliStartDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    appli_start_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    appli_start_date_is_set = true;
    appli_start_date_is_modified = true;
  }


  /** 
   * <em>appli_end_date</em>カラムの値を設定します。 
   *
   * @@param p_appliEndDate <em>appli_end_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setAppliEndDate( java.sql.Timestamp p_appliEndDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    appli_end_date = p_appliEndDate;
    appli_end_date_is_set = true;
    appli_end_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>appli_end_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setAppliEndDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    appli_end_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    appli_end_date_is_set = true;
    appli_end_date_is_modified = true;
  }


  /** 
   * <em>order_id</em>カラムの値を設定します。 
   *
   * @@param p_orderId <em>order_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setOrderId( long p_orderId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_id = new Long(p_orderId);
    order_id_is_set = true;
    order_id_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>order_id</em>カラムに値を設定します。 
   */
  public final void setOrderId( Long p_orderId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    order_id = p_orderId;
    order_id_is_set = true;
    order_id_is_modified = true;
  }


  /** 
   * <em>appli_date</em>カラムの値を設定します。 
   *
   * @@param p_appliDate <em>appli_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setAppliDate( java.sql.Timestamp p_appliDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    appli_date = p_appliDate;
    appli_date_is_set = true;
    appli_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>appli_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setAppliDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    appli_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    appli_date_is_set = true;
    appli_date_is_modified = true;
  }


  /** 
   * <em>payment_div</em>カラムの値を設定します。 
   *
   * @@param p_paymentDiv <em>payment_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPaymentDiv( String p_paymentDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_div = p_paymentDiv;
    payment_div_is_set = true;
    payment_div_is_modified = true;
  }


  /** 
   * <em>appli_lot_div</em>カラムの値を設定します。 
   *
   * @@param p_appliLotDiv <em>appli_lot_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAppliLotDiv( String p_appliLotDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    appli_lot_div = p_appliLotDiv;
    appli_lot_div_is_set = true;
    appli_lot_div_is_modified = true;
  }


  /** 
   * <em>effective_div</em>カラムの値を設定します。 
   *
   * @@param p_effectiveDiv <em>effective_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setEffectiveDiv( String p_effectiveDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    effective_div = p_effectiveDiv;
    effective_div_is_set = true;
    effective_div_is_modified = true;
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
   * <em>use_amt</em>カラムの値を設定します。 
   *
   * @@param p_useAmt <em>use_amt</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setUseAmt( long p_useAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    use_amt = new Long(p_useAmt);
    use_amt_is_set = true;
    use_amt_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>use_amt</em>カラムに値を設定します。 
   */
  public final void setUseAmt( Long p_useAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    use_amt = p_useAmt;
    use_amt_is_set = true;
    use_amt_is_modified = true;
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
   * <em>cancel_limit_date</em>カラムの値を設定します。 
   *
   * @@param p_cancelLimitDate <em>cancel_limit_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setCancelLimitDate( java.sql.Timestamp p_cancelLimitDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cancel_limit_date = p_cancelLimitDate;
    cancel_limit_date_is_set = true;
    cancel_limit_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>cancel_limit_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setCancelLimitDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    cancel_limit_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    cancel_limit_date_is_set = true;
    cancel_limit_date_is_modified = true;
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
   * <em>free_srv_div</em>カラムの値を設定します。 
   *
   * @@param p_freeSrvDiv <em>free_srv_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFreeSrvDiv( String p_freeSrvDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    free_srv_div = p_freeSrvDiv;
    free_srv_div_is_set = true;
    free_srv_div_is_modified = true;
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
                else if ( name.equals("appli_start_date") ) {
                    return this.appli_start_date;
                }
                else if ( name.equals("appli_end_date") ) {
                    return this.appli_end_date;
                }
                else if ( name.equals("appli_date") ) {
                    return this.appli_date;
                }
                else if ( name.equals("appli_lot_div") ) {
                    return this.appli_lot_div;
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
                else if ( name.equals("cancel_limit_date") ) {
                    return this.cancel_limit_date;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("effective_div") ) {
                    return this.effective_div;
                }
                break;
            case 'f':
                if ( name.equals("free_srv_div") ) {
                    return this.free_srv_div;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
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
            case 'o':
                if ( name.equals("order_id") ) {
                    return this.order_id;
                }
                break;
            case 'p':
                if ( name.equals("payment_div") ) {
                    return this.payment_div;
                }
                else if ( name.equals("payment_date") ) {
                    return this.payment_date;
                }
                break;
            case 'r':
                if ( name.equals("regist_id") ) {
                    return new Long( regist_id );
                }
                break;
            case 's':
                if ( name.equals("srv_div") ) {
                    return this.srv_div;
                }
                break;
            case 'u':
                if ( name.equals("use_amt") ) {
                    return this.use_amt;
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
                else if ( name.equals("appli_start_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'appli_start_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.appli_start_date = (java.sql.Timestamp) value;
                    if (this.appli_start_date_is_set)
                        this.appli_start_date_is_modified = true;
                    this.appli_start_date_is_set = true;
                    return;
                }
                else if ( name.equals("appli_end_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'appli_end_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.appli_end_date = (java.sql.Timestamp) value;
                    if (this.appli_end_date_is_set)
                        this.appli_end_date_is_modified = true;
                    this.appli_end_date_is_set = true;
                    return;
                }
                else if ( name.equals("appli_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'appli_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.appli_date = (java.sql.Timestamp) value;
                    if (this.appli_date_is_set)
                        this.appli_date_is_modified = true;
                    this.appli_date_is_set = true;
                    return;
                }
                else if ( name.equals("appli_lot_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'appli_lot_div' must be String: '"+value+"' is not." );
                    this.appli_lot_div = (String) value;
                    if (this.appli_lot_div_is_set)
                        this.appli_lot_div_is_modified = true;
                    this.appli_lot_div_is_set = true;
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
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cancel_div' must be String: '"+value+"' is not." );
                    this.cancel_div = (String) value;
                    if (this.cancel_div_is_set)
                        this.cancel_div_is_modified = true;
                    this.cancel_div_is_set = true;
                    return;
                }
                else if ( name.equals("cancel_limit_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'cancel_limit_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.cancel_limit_date = (java.sql.Timestamp) value;
                    if (this.cancel_limit_date_is_set)
                        this.cancel_limit_date_is_modified = true;
                    this.cancel_limit_date_is_set = true;
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
                if ( name.equals("effective_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'effective_div' must be String: '"+value+"' is not." );
                    this.effective_div = (String) value;
                    if (this.effective_div_is_set)
                        this.effective_div_is_modified = true;
                    this.effective_div_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("free_srv_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'free_srv_div' must be String: '"+value+"' is not." );
                    this.free_srv_div = (String) value;
                    if (this.free_srv_div_is_set)
                        this.free_srv_div_is_modified = true;
                    this.free_srv_div_is_set = true;
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
            case 'o':
                if ( name.equals("order_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'order_id' must be Long: '"+value+"' is not." );
                    this.order_id = (Long) value;
                    if (this.order_id_is_set)
                        this.order_id_is_modified = true;
                    this.order_id_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("payment_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'payment_div' must be String: '"+value+"' is not." );
                    this.payment_div = (String) value;
                    if (this.payment_div_is_set)
                        this.payment_div_is_modified = true;
                    this.payment_div_is_set = true;
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
                if ( name.equals("regist_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'regist_id' must be Long: '"+value+"' is not." );
                    this.regist_id = ((Long) value).longValue();
                    if (this.regist_id_is_set)
                        this.regist_id_is_modified = true;
                    this.regist_id_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("srv_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'srv_div' must be String: '"+value+"' is not." );
                    this.srv_div = (String) value;
                    if (this.srv_div_is_set)
                        this.srv_div_is_modified = true;
                    this.srv_div_is_set = true;
                    return;
                }
                break;
            case 'u':
                if ( name.equals("use_amt") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'use_amt' must be Long: '"+value+"' is not." );
                    this.use_amt = (Long) value;
                    if (this.use_amt_is_set)
                        this.use_amt_is_modified = true;
                    this.use_amt_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
