head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.16.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	HostLockRegistParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * host_lock_registテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link HostLockRegistRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link HostLockRegistParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link HostLockRegistParams}が{@@link HostLockRegistRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see HostLockRegistPK 
 * @@see HostLockRegistRow 
 */
public class HostLockRegistParams extends Params implements HostLockRegistRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "host_lock_regist";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = HostLockRegistRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return HostLockRegistRow.TYPE;
  }


  /** 
   * <em>rowid</em>カラムの値 
   */
  public  String  rowid;

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
   * <em>order_request_number</em>カラムの値 
   */
  public  String  order_request_number;

  /** 
   * <em>before_mng_lock_start_date</em>カラムの値 
   */
  public  java.sql.Timestamp  before_mng_lock_start_date;

  /** 
   * <em>before_mng_lock_end_date</em>カラムの値 
   */
  public  java.sql.Timestamp  before_mng_lock_end_date;

  /** 
   * <em>before_branch_lock</em>カラムの値 
   */
  public  String  before_branch_lock;

  /** 
   * <em>before_order_permission</em>カラムの値 
   */
  public  String  before_order_permission;

  /** 
   * <em>before_lock_regist_reason</em>カラムの値 
   */
  public  String  before_lock_regist_reason;

  /** 
   * <em>before_yellow_customer</em>カラムの値 
   */
  public  String  before_yellow_customer;

  /** 
   * <em>mng_lock_cancel_div</em>カラムの値 
   */
  public  String  mng_lock_cancel_div;

  /** 
   * <em>mng_lock_cancel_start_date</em>カラムの値 
   */
  public  java.sql.Timestamp  mng_lock_cancel_start_date;

  /** 
   * <em>mng_lock_cancel_end_date</em>カラムの値 
   */
  public  java.sql.Timestamp  mng_lock_cancel_end_date;

  /** 
   * <em>branch_lock</em>カラムの値 
   */
  public  String  branch_lock;

  /** 
   * <em>order_permission</em>カラムの値 
   */
  public  String  order_permission;

  /** 
   * <em>lock_registration_reason</em>カラムの値 
   */
  public  String  lock_registration_reason;

  /** 
   * <em>attribute</em>カラムの値 
   */
  public  String  attribute;

  /** 
   * <em>regi_erase_div</em>カラムの値 
   */
  public  String  regi_erase_div;

  /** 
   * <em>status</em>カラムの値 
   */
  public  String  status;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

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
  private boolean before_mng_lock_start_date_is_set = false;
  private boolean before_mng_lock_start_date_is_modified = false;
  private boolean before_mng_lock_end_date_is_set = false;
  private boolean before_mng_lock_end_date_is_modified = false;
  private boolean before_branch_lock_is_set = false;
  private boolean before_branch_lock_is_modified = false;
  private boolean before_order_permission_is_set = false;
  private boolean before_order_permission_is_modified = false;
  private boolean before_lock_regist_reason_is_set = false;
  private boolean before_lock_regist_reason_is_modified = false;
  private boolean before_yellow_customer_is_set = false;
  private boolean before_yellow_customer_is_modified = false;
  private boolean mng_lock_cancel_div_is_set = false;
  private boolean mng_lock_cancel_div_is_modified = false;
  private boolean mng_lock_cancel_start_date_is_set = false;
  private boolean mng_lock_cancel_start_date_is_modified = false;
  private boolean mng_lock_cancel_end_date_is_set = false;
  private boolean mng_lock_cancel_end_date_is_modified = false;
  private boolean branch_lock_is_set = false;
  private boolean branch_lock_is_modified = false;
  private boolean order_permission_is_set = false;
  private boolean order_permission_is_modified = false;
  private boolean lock_registration_reason_is_set = false;
  private boolean lock_registration_reason_is_modified = false;
  private boolean attribute_is_set = false;
  private boolean attribute_is_modified = false;
  private boolean regi_erase_div_is_set = false;
  private boolean regi_erase_div_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean rowid_is_set = false;
  private boolean rowid_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "rowid=" + rowid
      + "," + "request_code=" +request_code
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "," + "trader_code=" +trader_code
      + "," + "order_request_number=" +order_request_number
      + "," + "before_mng_lock_start_date=" +before_mng_lock_start_date
      + "," + "before_mng_lock_end_date=" +before_mng_lock_end_date
      + "," + "before_branch_lock=" +before_branch_lock
      + "," + "before_order_permission=" +before_order_permission
      + "," + "before_lock_regist_reason=" +before_lock_regist_reason
      + "," + "before_yellow_customer=" +before_yellow_customer
      + "," + "mng_lock_cancel_div=" +mng_lock_cancel_div
      + "," + "mng_lock_cancel_start_date=" +mng_lock_cancel_start_date
      + "," + "mng_lock_cancel_end_date=" +mng_lock_cancel_end_date
      + "," + "branch_lock=" +branch_lock
      + "," + "order_permission=" +order_permission
      + "," + "lock_registration_reason=" +lock_registration_reason
      + "," + "attribute=" +attribute
      + "," + "regi_erase_div=" +regi_erase_div
      + "," + "status=" +status
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のHostLockRegistParamsオブジェクトを作成します。 
   */
  public HostLockRegistParams() {
    rowid_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のHostLockRegistRowオブジェクトの値を利用してHostLockRegistParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するHostLockRegistRowオブジェクト 
   */
  public HostLockRegistParams( HostLockRegistRow p_row )
  {
    rowid = p_row.getRowid();
    rowid_is_set = p_row.getRowidIsSet();
    rowid_is_modified = p_row.getRowidIsModified();
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
    order_request_number = p_row.getOrderRequestNumber();
    order_request_number_is_set = p_row.getOrderRequestNumberIsSet();
    order_request_number_is_modified = p_row.getOrderRequestNumberIsModified();
    before_mng_lock_start_date = p_row.getBeforeMngLockStartDate();
    before_mng_lock_start_date_is_set = p_row.getBeforeMngLockStartDateIsSet();
    before_mng_lock_start_date_is_modified = p_row.getBeforeMngLockStartDateIsModified();
    before_mng_lock_end_date = p_row.getBeforeMngLockEndDate();
    before_mng_lock_end_date_is_set = p_row.getBeforeMngLockEndDateIsSet();
    before_mng_lock_end_date_is_modified = p_row.getBeforeMngLockEndDateIsModified();
    before_branch_lock = p_row.getBeforeBranchLock();
    before_branch_lock_is_set = p_row.getBeforeBranchLockIsSet();
    before_branch_lock_is_modified = p_row.getBeforeBranchLockIsModified();
    before_order_permission = p_row.getBeforeOrderPermission();
    before_order_permission_is_set = p_row.getBeforeOrderPermissionIsSet();
    before_order_permission_is_modified = p_row.getBeforeOrderPermissionIsModified();
    before_lock_regist_reason = p_row.getBeforeLockRegistReason();
    before_lock_regist_reason_is_set = p_row.getBeforeLockRegistReasonIsSet();
    before_lock_regist_reason_is_modified = p_row.getBeforeLockRegistReasonIsModified();
    before_yellow_customer = p_row.getBeforeYellowCustomer();
    before_yellow_customer_is_set = p_row.getBeforeYellowCustomerIsSet();
    before_yellow_customer_is_modified = p_row.getBeforeYellowCustomerIsModified();
    mng_lock_cancel_div = p_row.getMngLockCancelDiv();
    mng_lock_cancel_div_is_set = p_row.getMngLockCancelDivIsSet();
    mng_lock_cancel_div_is_modified = p_row.getMngLockCancelDivIsModified();
    mng_lock_cancel_start_date = p_row.getMngLockCancelStartDate();
    mng_lock_cancel_start_date_is_set = p_row.getMngLockCancelStartDateIsSet();
    mng_lock_cancel_start_date_is_modified = p_row.getMngLockCancelStartDateIsModified();
    mng_lock_cancel_end_date = p_row.getMngLockCancelEndDate();
    mng_lock_cancel_end_date_is_set = p_row.getMngLockCancelEndDateIsSet();
    mng_lock_cancel_end_date_is_modified = p_row.getMngLockCancelEndDateIsModified();
    branch_lock = p_row.getBranchLock();
    branch_lock_is_set = p_row.getBranchLockIsSet();
    branch_lock_is_modified = p_row.getBranchLockIsModified();
    order_permission = p_row.getOrderPermission();
    order_permission_is_set = p_row.getOrderPermissionIsSet();
    order_permission_is_modified = p_row.getOrderPermissionIsModified();
    lock_registration_reason = p_row.getLockRegistrationReason();
    lock_registration_reason_is_set = p_row.getLockRegistrationReasonIsSet();
    lock_registration_reason_is_modified = p_row.getLockRegistrationReasonIsModified();
    attribute = p_row.getAttribute();
    attribute_is_set = p_row.getAttributeIsSet();
    attribute_is_modified = p_row.getAttributeIsModified();
    regi_erase_div = p_row.getRegiEraseDiv();
    regi_erase_div_is_set = p_row.getRegiEraseDivIsSet();
    regi_erase_div_is_modified = p_row.getRegiEraseDivIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
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
      order_request_number_is_set = true;
      order_request_number_is_modified = true;
      before_mng_lock_start_date_is_set = true;
      before_mng_lock_start_date_is_modified = true;
      before_mng_lock_end_date_is_set = true;
      before_mng_lock_end_date_is_modified = true;
      before_branch_lock_is_set = true;
      before_branch_lock_is_modified = true;
      before_order_permission_is_set = true;
      before_order_permission_is_modified = true;
      before_lock_regist_reason_is_set = true;
      before_lock_regist_reason_is_modified = true;
      before_yellow_customer_is_set = true;
      before_yellow_customer_is_modified = true;
      mng_lock_cancel_div_is_set = true;
      mng_lock_cancel_div_is_modified = true;
      mng_lock_cancel_start_date_is_set = true;
      mng_lock_cancel_start_date_is_modified = true;
      mng_lock_cancel_end_date_is_set = true;
      mng_lock_cancel_end_date_is_modified = true;
      branch_lock_is_set = true;
      branch_lock_is_modified = true;
      order_permission_is_set = true;
      order_permission_is_modified = true;
      lock_registration_reason_is_set = true;
      lock_registration_reason_is_modified = true;
      attribute_is_set = true;
      attribute_is_modified = true;
      regi_erase_div_is_set = true;
      regi_erase_div_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
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
    if ( !( other instanceof HostLockRegistRow ) )
       return false;
    return fieldsEqual( (HostLockRegistRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のHostLockRegistRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( HostLockRegistRow row )
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
    if ( before_mng_lock_start_date == null ) {
      if ( row.getBeforeMngLockStartDate() != null )
        return false;
    } else if ( !before_mng_lock_start_date.equals( row.getBeforeMngLockStartDate() ) ) {
        return false;
    }
    if ( before_mng_lock_end_date == null ) {
      if ( row.getBeforeMngLockEndDate() != null )
        return false;
    } else if ( !before_mng_lock_end_date.equals( row.getBeforeMngLockEndDate() ) ) {
        return false;
    }
    if ( before_branch_lock == null ) {
      if ( row.getBeforeBranchLock() != null )
        return false;
    } else if ( !before_branch_lock.equals( row.getBeforeBranchLock() ) ) {
        return false;
    }
    if ( before_order_permission == null ) {
      if ( row.getBeforeOrderPermission() != null )
        return false;
    } else if ( !before_order_permission.equals( row.getBeforeOrderPermission() ) ) {
        return false;
    }
    if ( before_lock_regist_reason == null ) {
      if ( row.getBeforeLockRegistReason() != null )
        return false;
    } else if ( !before_lock_regist_reason.equals( row.getBeforeLockRegistReason() ) ) {
        return false;
    }
    if ( before_yellow_customer == null ) {
      if ( row.getBeforeYellowCustomer() != null )
        return false;
    } else if ( !before_yellow_customer.equals( row.getBeforeYellowCustomer() ) ) {
        return false;
    }
    if ( mng_lock_cancel_div == null ) {
      if ( row.getMngLockCancelDiv() != null )
        return false;
    } else if ( !mng_lock_cancel_div.equals( row.getMngLockCancelDiv() ) ) {
        return false;
    }
    if ( mng_lock_cancel_start_date == null ) {
      if ( row.getMngLockCancelStartDate() != null )
        return false;
    } else if ( !mng_lock_cancel_start_date.equals( row.getMngLockCancelStartDate() ) ) {
        return false;
    }
    if ( mng_lock_cancel_end_date == null ) {
      if ( row.getMngLockCancelEndDate() != null )
        return false;
    } else if ( !mng_lock_cancel_end_date.equals( row.getMngLockCancelEndDate() ) ) {
        return false;
    }
    if ( branch_lock == null ) {
      if ( row.getBranchLock() != null )
        return false;
    } else if ( !branch_lock.equals( row.getBranchLock() ) ) {
        return false;
    }
    if ( order_permission == null ) {
      if ( row.getOrderPermission() != null )
        return false;
    } else if ( !order_permission.equals( row.getOrderPermission() ) ) {
        return false;
    }
    if ( lock_registration_reason == null ) {
      if ( row.getLockRegistrationReason() != null )
        return false;
    } else if ( !lock_registration_reason.equals( row.getLockRegistrationReason() ) ) {
        return false;
    }
    if ( attribute == null ) {
      if ( row.getAttribute() != null )
        return false;
    } else if ( !attribute.equals( row.getAttribute() ) ) {
        return false;
    }
    if ( regi_erase_div == null ) {
      if ( row.getRegiEraseDiv() != null )
        return false;
    } else if ( !regi_erase_div.equals( row.getRegiEraseDiv() ) ) {
        return false;
    }
    if ( status == null ) {
      if ( row.getStatus() != null )
        return false;
    } else if ( !status.equals( row.getStatus() ) ) {
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
    if ( rowid == null ) {
      if ( row.getRowid() != null )
        return false;
    } else if ( !rowid.equals( row.getRowid() ) ) {
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
        + (before_mng_lock_start_date!=null? before_mng_lock_start_date.hashCode(): 0) 
        + (before_mng_lock_end_date!=null? before_mng_lock_end_date.hashCode(): 0) 
        + (before_branch_lock!=null? before_branch_lock.hashCode(): 0) 
        + (before_order_permission!=null? before_order_permission.hashCode(): 0) 
        + (before_lock_regist_reason!=null? before_lock_regist_reason.hashCode(): 0) 
        + (before_yellow_customer!=null? before_yellow_customer.hashCode(): 0) 
        + (mng_lock_cancel_div!=null? mng_lock_cancel_div.hashCode(): 0) 
        + (mng_lock_cancel_start_date!=null? mng_lock_cancel_start_date.hashCode(): 0) 
        + (mng_lock_cancel_end_date!=null? mng_lock_cancel_end_date.hashCode(): 0) 
        + (branch_lock!=null? branch_lock.hashCode(): 0) 
        + (order_permission!=null? order_permission.hashCode(): 0) 
        + (lock_registration_reason!=null? lock_registration_reason.hashCode(): 0) 
        + (attribute!=null? attribute.hashCode(): 0) 
        + (regi_erase_div!=null? regi_erase_div.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (rowid!=null? rowid.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		if ( request_code != null )
			map.put("request_code",request_code);
		if ( institution_code != null )
			map.put("institution_code",institution_code);
		if ( branch_code != null )
			map.put("branch_code",branch_code);
		if ( account_code != null )
			map.put("account_code",account_code);
		if ( trader_code != null )
			map.put("trader_code",trader_code);
		if ( order_request_number != null )
			map.put("order_request_number",order_request_number);
		if ( before_mng_lock_start_date != null )
			map.put("before_mng_lock_start_date",before_mng_lock_start_date);
		if ( before_mng_lock_end_date != null )
			map.put("before_mng_lock_end_date",before_mng_lock_end_date);
		if ( before_branch_lock != null )
			map.put("before_branch_lock",before_branch_lock);
		if ( before_order_permission != null )
			map.put("before_order_permission",before_order_permission);
		if ( before_lock_regist_reason != null )
			map.put("before_lock_regist_reason",before_lock_regist_reason);
		if ( before_yellow_customer != null )
			map.put("before_yellow_customer",before_yellow_customer);
		if ( mng_lock_cancel_div != null )
			map.put("mng_lock_cancel_div",mng_lock_cancel_div);
		if ( mng_lock_cancel_start_date != null )
			map.put("mng_lock_cancel_start_date",mng_lock_cancel_start_date);
		if ( mng_lock_cancel_end_date != null )
			map.put("mng_lock_cancel_end_date",mng_lock_cancel_end_date);
		if ( branch_lock != null )
			map.put("branch_lock",branch_lock);
		if ( order_permission != null )
			map.put("order_permission",order_permission);
		if ( lock_registration_reason != null )
			map.put("lock_registration_reason",lock_registration_reason);
		if ( attribute != null )
			map.put("attribute",attribute);
		if ( regi_erase_div != null )
			map.put("regi_erase_div",regi_erase_div);
		if ( status != null )
			map.put("status",status);
		if ( created_timestamp != null )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp != null )
			map.put("last_updated_timestamp",last_updated_timestamp);
		map.put("rowid",rowid);
		map.remove("rowid");
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
		if ( order_request_number_is_modified )
			map.put("order_request_number",order_request_number);
		if ( before_mng_lock_start_date_is_modified )
			map.put("before_mng_lock_start_date",before_mng_lock_start_date);
		if ( before_mng_lock_end_date_is_modified )
			map.put("before_mng_lock_end_date",before_mng_lock_end_date);
		if ( before_branch_lock_is_modified )
			map.put("before_branch_lock",before_branch_lock);
		if ( before_order_permission_is_modified )
			map.put("before_order_permission",before_order_permission);
		if ( before_lock_regist_reason_is_modified )
			map.put("before_lock_regist_reason",before_lock_regist_reason);
		if ( before_yellow_customer_is_modified )
			map.put("before_yellow_customer",before_yellow_customer);
		if ( mng_lock_cancel_div_is_modified )
			map.put("mng_lock_cancel_div",mng_lock_cancel_div);
		if ( mng_lock_cancel_start_date_is_modified )
			map.put("mng_lock_cancel_start_date",mng_lock_cancel_start_date);
		if ( mng_lock_cancel_end_date_is_modified )
			map.put("mng_lock_cancel_end_date",mng_lock_cancel_end_date);
		if ( branch_lock_is_modified )
			map.put("branch_lock",branch_lock);
		if ( order_permission_is_modified )
			map.put("order_permission",order_permission);
		if ( lock_registration_reason_is_modified )
			map.put("lock_registration_reason",lock_registration_reason);
		if ( attribute_is_modified )
			map.put("attribute",attribute);
		if ( regi_erase_div_is_modified )
			map.put("regi_erase_div",regi_erase_div);
		if ( status_is_modified )
			map.put("status",status);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("request_code",request_code);
			map.put("institution_code",institution_code);
			map.put("branch_code",branch_code);
			map.put("account_code",account_code);
			map.put("trader_code",trader_code);
			map.put("order_request_number",order_request_number);
			map.put("before_mng_lock_start_date",before_mng_lock_start_date);
			map.put("before_mng_lock_end_date",before_mng_lock_end_date);
			map.put("before_branch_lock",before_branch_lock);
			map.put("before_order_permission",before_order_permission);
			map.put("before_lock_regist_reason",before_lock_regist_reason);
			map.put("before_yellow_customer",before_yellow_customer);
			map.put("mng_lock_cancel_div",mng_lock_cancel_div);
			map.put("mng_lock_cancel_start_date",mng_lock_cancel_start_date);
			map.put("mng_lock_cancel_end_date",mng_lock_cancel_end_date);
			map.put("branch_lock",branch_lock);
			map.put("order_permission",order_permission);
			map.put("lock_registration_reason",lock_registration_reason);
			map.put("attribute",attribute);
			map.put("regi_erase_div",regi_erase_div);
			map.put("status",status);
			map.put("created_timestamp",created_timestamp);
			map.put("last_updated_timestamp",last_updated_timestamp);
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
   * <em>before_mng_lock_start_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getBeforeMngLockStartDate()
  {
    return before_mng_lock_start_date;
  }


  /** 
   * <em>before_mng_lock_start_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBeforeMngLockStartDateIsSet() {
    return before_mng_lock_start_date_is_set;
  }


  /** 
   * <em>before_mng_lock_start_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBeforeMngLockStartDateIsModified() {
    return before_mng_lock_start_date_is_modified;
  }


  /** 
   * <em>before_mng_lock_end_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getBeforeMngLockEndDate()
  {
    return before_mng_lock_end_date;
  }


  /** 
   * <em>before_mng_lock_end_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBeforeMngLockEndDateIsSet() {
    return before_mng_lock_end_date_is_set;
  }


  /** 
   * <em>before_mng_lock_end_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBeforeMngLockEndDateIsModified() {
    return before_mng_lock_end_date_is_modified;
  }


  /** 
   * <em>before_branch_lock</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBeforeBranchLock()
  {
    return before_branch_lock;
  }


  /** 
   * <em>before_branch_lock</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBeforeBranchLockIsSet() {
    return before_branch_lock_is_set;
  }


  /** 
   * <em>before_branch_lock</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBeforeBranchLockIsModified() {
    return before_branch_lock_is_modified;
  }


  /** 
   * <em>before_order_permission</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBeforeOrderPermission()
  {
    return before_order_permission;
  }


  /** 
   * <em>before_order_permission</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBeforeOrderPermissionIsSet() {
    return before_order_permission_is_set;
  }


  /** 
   * <em>before_order_permission</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBeforeOrderPermissionIsModified() {
    return before_order_permission_is_modified;
  }


  /** 
   * <em>before_lock_regist_reason</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBeforeLockRegistReason()
  {
    return before_lock_regist_reason;
  }


  /** 
   * <em>before_lock_regist_reason</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBeforeLockRegistReasonIsSet() {
    return before_lock_regist_reason_is_set;
  }


  /** 
   * <em>before_lock_regist_reason</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBeforeLockRegistReasonIsModified() {
    return before_lock_regist_reason_is_modified;
  }


  /** 
   * <em>before_yellow_customer</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBeforeYellowCustomer()
  {
    return before_yellow_customer;
  }


  /** 
   * <em>before_yellow_customer</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBeforeYellowCustomerIsSet() {
    return before_yellow_customer_is_set;
  }


  /** 
   * <em>before_yellow_customer</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBeforeYellowCustomerIsModified() {
    return before_yellow_customer_is_modified;
  }


  /** 
   * <em>mng_lock_cancel_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMngLockCancelDiv()
  {
    return mng_lock_cancel_div;
  }


  /** 
   * <em>mng_lock_cancel_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMngLockCancelDivIsSet() {
    return mng_lock_cancel_div_is_set;
  }


  /** 
   * <em>mng_lock_cancel_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMngLockCancelDivIsModified() {
    return mng_lock_cancel_div_is_modified;
  }


  /** 
   * <em>mng_lock_cancel_start_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getMngLockCancelStartDate()
  {
    return mng_lock_cancel_start_date;
  }


  /** 
   * <em>mng_lock_cancel_start_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMngLockCancelStartDateIsSet() {
    return mng_lock_cancel_start_date_is_set;
  }


  /** 
   * <em>mng_lock_cancel_start_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMngLockCancelStartDateIsModified() {
    return mng_lock_cancel_start_date_is_modified;
  }


  /** 
   * <em>mng_lock_cancel_end_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getMngLockCancelEndDate()
  {
    return mng_lock_cancel_end_date;
  }


  /** 
   * <em>mng_lock_cancel_end_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMngLockCancelEndDateIsSet() {
    return mng_lock_cancel_end_date_is_set;
  }


  /** 
   * <em>mng_lock_cancel_end_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMngLockCancelEndDateIsModified() {
    return mng_lock_cancel_end_date_is_modified;
  }


  /** 
   * <em>branch_lock</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBranchLock()
  {
    return branch_lock;
  }


  /** 
   * <em>branch_lock</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchLockIsSet() {
    return branch_lock_is_set;
  }


  /** 
   * <em>branch_lock</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchLockIsModified() {
    return branch_lock_is_modified;
  }


  /** 
   * <em>order_permission</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderPermission()
  {
    return order_permission;
  }


  /** 
   * <em>order_permission</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderPermissionIsSet() {
    return order_permission_is_set;
  }


  /** 
   * <em>order_permission</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderPermissionIsModified() {
    return order_permission_is_modified;
  }


  /** 
   * <em>lock_registration_reason</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLockRegistrationReason()
  {
    return lock_registration_reason;
  }


  /** 
   * <em>lock_registration_reason</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLockRegistrationReasonIsSet() {
    return lock_registration_reason_is_set;
  }


  /** 
   * <em>lock_registration_reason</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLockRegistrationReasonIsModified() {
    return lock_registration_reason_is_modified;
  }


  /** 
   * <em>attribute</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAttribute()
  {
    return attribute;
  }


  /** 
   * <em>attribute</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAttributeIsSet() {
    return attribute_is_set;
  }


  /** 
   * <em>attribute</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAttributeIsModified() {
    return attribute_is_modified;
  }


  /** 
   * <em>regi_erase_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRegiEraseDiv()
  {
    return regi_erase_div;
  }


  /** 
   * <em>regi_erase_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRegiEraseDivIsSet() {
    return regi_erase_div_is_set;
  }


  /** 
   * <em>regi_erase_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRegiEraseDivIsModified() {
    return regi_erase_div_is_modified;
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
   * <em>rowid</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRowid()
  {
    return rowid;
  }


  /** 
   * <em>rowid</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRowidIsSet() {
    return rowid_is_set;
  }


  /** 
   * <em>rowid</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRowidIsModified() {
    return rowid_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new HostLockRegistPK(rowid);
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
   * <em>before_mng_lock_start_date</em>カラムの値を設定します。 
   *
   * @@param p_beforeMngLockStartDate <em>before_mng_lock_start_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setBeforeMngLockStartDate( java.sql.Timestamp p_beforeMngLockStartDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    before_mng_lock_start_date = p_beforeMngLockStartDate;
    before_mng_lock_start_date_is_set = true;
    before_mng_lock_start_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>before_mng_lock_start_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setBeforeMngLockStartDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    before_mng_lock_start_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    before_mng_lock_start_date_is_set = true;
    before_mng_lock_start_date_is_modified = true;
  }


  /** 
   * <em>before_mng_lock_end_date</em>カラムの値を設定します。 
   *
   * @@param p_beforeMngLockEndDate <em>before_mng_lock_end_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setBeforeMngLockEndDate( java.sql.Timestamp p_beforeMngLockEndDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    before_mng_lock_end_date = p_beforeMngLockEndDate;
    before_mng_lock_end_date_is_set = true;
    before_mng_lock_end_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>before_mng_lock_end_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setBeforeMngLockEndDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    before_mng_lock_end_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    before_mng_lock_end_date_is_set = true;
    before_mng_lock_end_date_is_modified = true;
  }


  /** 
   * <em>before_branch_lock</em>カラムの値を設定します。 
   *
   * @@param p_beforeBranchLock <em>before_branch_lock</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setBeforeBranchLock( String p_beforeBranchLock )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    before_branch_lock = p_beforeBranchLock;
    before_branch_lock_is_set = true;
    before_branch_lock_is_modified = true;
  }


  /** 
   * <em>before_order_permission</em>カラムの値を設定します。 
   *
   * @@param p_beforeOrderPermission <em>before_order_permission</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setBeforeOrderPermission( String p_beforeOrderPermission )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    before_order_permission = p_beforeOrderPermission;
    before_order_permission_is_set = true;
    before_order_permission_is_modified = true;
  }


  /** 
   * <em>before_lock_regist_reason</em>カラムの値を設定します。 
   *
   * @@param p_beforeLockRegistReason <em>before_lock_regist_reason</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setBeforeLockRegistReason( String p_beforeLockRegistReason )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    before_lock_regist_reason = p_beforeLockRegistReason;
    before_lock_regist_reason_is_set = true;
    before_lock_regist_reason_is_modified = true;
  }


  /** 
   * <em>before_yellow_customer</em>カラムの値を設定します。 
   *
   * @@param p_beforeYellowCustomer <em>before_yellow_customer</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setBeforeYellowCustomer( String p_beforeYellowCustomer )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    before_yellow_customer = p_beforeYellowCustomer;
    before_yellow_customer_is_set = true;
    before_yellow_customer_is_modified = true;
  }


  /** 
   * <em>mng_lock_cancel_div</em>カラムの値を設定します。 
   *
   * @@param p_mngLockCancelDiv <em>mng_lock_cancel_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMngLockCancelDiv( String p_mngLockCancelDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mng_lock_cancel_div = p_mngLockCancelDiv;
    mng_lock_cancel_div_is_set = true;
    mng_lock_cancel_div_is_modified = true;
  }


  /** 
   * <em>mng_lock_cancel_start_date</em>カラムの値を設定します。 
   *
   * @@param p_mngLockCancelStartDate <em>mng_lock_cancel_start_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setMngLockCancelStartDate( java.sql.Timestamp p_mngLockCancelStartDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mng_lock_cancel_start_date = p_mngLockCancelStartDate;
    mng_lock_cancel_start_date_is_set = true;
    mng_lock_cancel_start_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>mng_lock_cancel_start_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setMngLockCancelStartDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    mng_lock_cancel_start_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    mng_lock_cancel_start_date_is_set = true;
    mng_lock_cancel_start_date_is_modified = true;
  }


  /** 
   * <em>mng_lock_cancel_end_date</em>カラムの値を設定します。 
   *
   * @@param p_mngLockCancelEndDate <em>mng_lock_cancel_end_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setMngLockCancelEndDate( java.sql.Timestamp p_mngLockCancelEndDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mng_lock_cancel_end_date = p_mngLockCancelEndDate;
    mng_lock_cancel_end_date_is_set = true;
    mng_lock_cancel_end_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>mng_lock_cancel_end_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setMngLockCancelEndDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    mng_lock_cancel_end_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    mng_lock_cancel_end_date_is_set = true;
    mng_lock_cancel_end_date_is_modified = true;
  }


  /** 
   * <em>branch_lock</em>カラムの値を設定します。 
   *
   * @@param p_branchLock <em>branch_lock</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setBranchLock( String p_branchLock )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_lock = p_branchLock;
    branch_lock_is_set = true;
    branch_lock_is_modified = true;
  }


  /** 
   * <em>order_permission</em>カラムの値を設定します。 
   *
   * @@param p_orderPermission <em>order_permission</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOrderPermission( String p_orderPermission )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_permission = p_orderPermission;
    order_permission_is_set = true;
    order_permission_is_modified = true;
  }


  /** 
   * <em>lock_registration_reason</em>カラムの値を設定します。 
   *
   * @@param p_lockRegistrationReason <em>lock_registration_reason</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setLockRegistrationReason( String p_lockRegistrationReason )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    lock_registration_reason = p_lockRegistrationReason;
    lock_registration_reason_is_set = true;
    lock_registration_reason_is_modified = true;
  }


  /** 
   * <em>attribute</em>カラムの値を設定します。 
   *
   * @@param p_attribute <em>attribute</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAttribute( String p_attribute )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    attribute = p_attribute;
    attribute_is_set = true;
    attribute_is_modified = true;
  }


  /** 
   * <em>regi_erase_div</em>カラムの値を設定します。 
   *
   * @@param p_regiEraseDiv <em>regi_erase_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setRegiEraseDiv( String p_regiEraseDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    regi_erase_div = p_regiEraseDiv;
    regi_erase_div_is_set = true;
    regi_erase_div_is_modified = true;
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
   * <em>rowid</em>カラムの値を設定します。 
   *
   * @@param p_rowid <em>rowid</em>カラムの値をあらわす20桁以下のString値で、その精度は20桁まで
   */
  public final void setRowid( String p_rowid )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    rowid = p_rowid;
    rowid_is_set = true;
    rowid_is_modified = true;
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
                else if ( name.equals("attribute") ) {
                    return this.attribute;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                else if ( name.equals("before_mng_lock_start_date") ) {
                    return this.before_mng_lock_start_date;
                }
                else if ( name.equals("before_mng_lock_end_date") ) {
                    return this.before_mng_lock_end_date;
                }
                else if ( name.equals("before_branch_lock") ) {
                    return this.before_branch_lock;
                }
                else if ( name.equals("before_order_permission") ) {
                    return this.before_order_permission;
                }
                else if ( name.equals("before_lock_regist_reason") ) {
                    return this.before_lock_regist_reason;
                }
                else if ( name.equals("before_yellow_customer") ) {
                    return this.before_yellow_customer;
                }
                else if ( name.equals("branch_lock") ) {
                    return this.branch_lock;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("lock_registration_reason") ) {
                    return this.lock_registration_reason;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("mng_lock_cancel_div") ) {
                    return this.mng_lock_cancel_div;
                }
                else if ( name.equals("mng_lock_cancel_start_date") ) {
                    return this.mng_lock_cancel_start_date;
                }
                else if ( name.equals("mng_lock_cancel_end_date") ) {
                    return this.mng_lock_cancel_end_date;
                }
                break;
            case 'o':
                if ( name.equals("order_request_number") ) {
                    return this.order_request_number;
                }
                else if ( name.equals("order_permission") ) {
                    return this.order_permission;
                }
                break;
            case 'r':
                if ( name.equals("request_code") ) {
                    return this.request_code;
                }
                else if ( name.equals("regi_erase_div") ) {
                    return this.regi_erase_div;
                }
                else if ( name.equals("rowid") ) {
                    return this.rowid;
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
                else if ( name.equals("attribute") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'attribute' must be String: '"+value+"' is not." );
                    this.attribute = (String) value;
                    if (this.attribute_is_set)
                        this.attribute_is_modified = true;
                    this.attribute_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'branch_code' must be String: '"+value+"' is not." );
                    this.branch_code = (String) value;
                    if (this.branch_code_is_set)
                        this.branch_code_is_modified = true;
                    this.branch_code_is_set = true;
                    return;
                }
                else if ( name.equals("before_mng_lock_start_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'before_mng_lock_start_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.before_mng_lock_start_date = (java.sql.Timestamp) value;
                    if (this.before_mng_lock_start_date_is_set)
                        this.before_mng_lock_start_date_is_modified = true;
                    this.before_mng_lock_start_date_is_set = true;
                    return;
                }
                else if ( name.equals("before_mng_lock_end_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'before_mng_lock_end_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.before_mng_lock_end_date = (java.sql.Timestamp) value;
                    if (this.before_mng_lock_end_date_is_set)
                        this.before_mng_lock_end_date_is_modified = true;
                    this.before_mng_lock_end_date_is_set = true;
                    return;
                }
                else if ( name.equals("before_branch_lock") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'before_branch_lock' must be String: '"+value+"' is not." );
                    this.before_branch_lock = (String) value;
                    if (this.before_branch_lock_is_set)
                        this.before_branch_lock_is_modified = true;
                    this.before_branch_lock_is_set = true;
                    return;
                }
                else if ( name.equals("before_order_permission") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'before_order_permission' must be String: '"+value+"' is not." );
                    this.before_order_permission = (String) value;
                    if (this.before_order_permission_is_set)
                        this.before_order_permission_is_modified = true;
                    this.before_order_permission_is_set = true;
                    return;
                }
                else if ( name.equals("before_lock_regist_reason") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'before_lock_regist_reason' must be String: '"+value+"' is not." );
                    this.before_lock_regist_reason = (String) value;
                    if (this.before_lock_regist_reason_is_set)
                        this.before_lock_regist_reason_is_modified = true;
                    this.before_lock_regist_reason_is_set = true;
                    return;
                }
                else if ( name.equals("before_yellow_customer") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'before_yellow_customer' must be String: '"+value+"' is not." );
                    this.before_yellow_customer = (String) value;
                    if (this.before_yellow_customer_is_set)
                        this.before_yellow_customer_is_modified = true;
                    this.before_yellow_customer_is_set = true;
                    return;
                }
                else if ( name.equals("branch_lock") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'branch_lock' must be String: '"+value+"' is not." );
                    this.branch_lock = (String) value;
                    if (this.branch_lock_is_set)
                        this.branch_lock_is_modified = true;
                    this.branch_lock_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    if (this.created_timestamp_is_set)
                        this.created_timestamp_is_modified = true;
                    this.created_timestamp_is_set = true;
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
                if ( name.equals("lock_registration_reason") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'lock_registration_reason' must be String: '"+value+"' is not." );
                    this.lock_registration_reason = (String) value;
                    if (this.lock_registration_reason_is_set)
                        this.lock_registration_reason_is_modified = true;
                    this.lock_registration_reason_is_set = true;
                    return;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    if ( value != null )
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
                if ( name.equals("mng_lock_cancel_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mng_lock_cancel_div' must be String: '"+value+"' is not." );
                    this.mng_lock_cancel_div = (String) value;
                    if (this.mng_lock_cancel_div_is_set)
                        this.mng_lock_cancel_div_is_modified = true;
                    this.mng_lock_cancel_div_is_set = true;
                    return;
                }
                else if ( name.equals("mng_lock_cancel_start_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'mng_lock_cancel_start_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.mng_lock_cancel_start_date = (java.sql.Timestamp) value;
                    if (this.mng_lock_cancel_start_date_is_set)
                        this.mng_lock_cancel_start_date_is_modified = true;
                    this.mng_lock_cancel_start_date_is_set = true;
                    return;
                }
                else if ( name.equals("mng_lock_cancel_end_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'mng_lock_cancel_end_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.mng_lock_cancel_end_date = (java.sql.Timestamp) value;
                    if (this.mng_lock_cancel_end_date_is_set)
                        this.mng_lock_cancel_end_date_is_modified = true;
                    this.mng_lock_cancel_end_date_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("order_request_number") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_request_number' must be String: '"+value+"' is not." );
                    this.order_request_number = (String) value;
                    if (this.order_request_number_is_set)
                        this.order_request_number_is_modified = true;
                    this.order_request_number_is_set = true;
                    return;
                }
                else if ( name.equals("order_permission") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_permission' must be String: '"+value+"' is not." );
                    this.order_permission = (String) value;
                    if (this.order_permission_is_set)
                        this.order_permission_is_modified = true;
                    this.order_permission_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("request_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'request_code' must be String: '"+value+"' is not." );
                    this.request_code = (String) value;
                    if (this.request_code_is_set)
                        this.request_code_is_modified = true;
                    this.request_code_is_set = true;
                    return;
                }
                else if ( name.equals("regi_erase_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'regi_erase_div' must be String: '"+value+"' is not." );
                    this.regi_erase_div = (String) value;
                    if (this.regi_erase_div_is_set)
                        this.regi_erase_div_is_modified = true;
                    this.regi_erase_div_is_set = true;
                    return;
                }
                else if ( name.equals("rowid") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'rowid' must be String: '"+value+"' is not." );
                    this.rowid = (String) value;
                    if (this.rowid_is_set)
                        this.rowid_is_modified = true;
                    this.rowid_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("status") ) {
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
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
