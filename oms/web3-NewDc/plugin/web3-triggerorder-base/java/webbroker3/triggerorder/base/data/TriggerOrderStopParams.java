head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.19.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8a44d7ecc762b5b;
filename	TriggerOrderStopParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.triggerorder.base.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * trigger_order_stopテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link TriggerOrderStopRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link TriggerOrderStopParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link TriggerOrderStopParams}が{@@link TriggerOrderStopRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see TriggerOrderStopPK 
 * @@see TriggerOrderStopRow 
 */
public class TriggerOrderStopParams extends Params implements TriggerOrderStopRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "trigger_order_stop";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = TriggerOrderStopRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return TriggerOrderStopRow.TYPE;
  }


  /** 
   * <em>trigger_order_stop_id</em>カラムの値 
   */
  public  long  trigger_order_stop_id;

  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>branch_code</em>カラムの値 
   */
  public  String  branch_code;

  /** 
   * <em>target_type</em>カラムの値 
   */
  public  String  target_type;

  /** 
   * <em>key</em>カラムの値 
   */
  public  String  key;

  /** 
   * <em>addition</em>カラムの値 
   */
  public  String  addition;

  /** 
   * <em>stop_reason</em>カラムの値 
   */
  public  String  stop_reason;

  /** 
   * <em>succ_order_stop_flag</em>カラムの値 
   */
  public  int  succ_order_stop_flag;

  /** 
   * <em>stop_order_stop_flag</em>カラムの値 
   */
  public  int  stop_order_stop_flag;

  /** 
   * <em>wlimit_order_stop_flag</em>カラムの値 
   */
  public  int  wlimit_order_stop_flag;

  /** 
   * <em>oco_order_stop_flag</em>カラムの値 
   */
  public  int  oco_order_stop_flag;

  /** 
   * <em>ifd_order_stop_flag</em>カラムの値 
   */
  public  int  ifd_order_stop_flag;

  /** 
   * <em>valid_term_from</em>カラムの値 
   */
  public  java.sql.Timestamp  valid_term_from;

  /** 
   * <em>valid_term_to</em>カラムの値 
   */
  public  java.sql.Timestamp  valid_term_to;

  /** 
   * <em>delete_flag</em>カラムの値 
   */
  public  int  delete_flag;

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

  private boolean trigger_order_stop_id_is_set = false;
  private boolean trigger_order_stop_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean target_type_is_set = false;
  private boolean target_type_is_modified = false;
  private boolean key_is_set = false;
  private boolean key_is_modified = false;
  private boolean addition_is_set = false;
  private boolean addition_is_modified = false;
  private boolean stop_reason_is_set = false;
  private boolean stop_reason_is_modified = false;
  private boolean succ_order_stop_flag_is_set = false;
  private boolean succ_order_stop_flag_is_modified = false;
  private boolean stop_order_stop_flag_is_set = false;
  private boolean stop_order_stop_flag_is_modified = false;
  private boolean wlimit_order_stop_flag_is_set = false;
  private boolean wlimit_order_stop_flag_is_modified = false;
  private boolean oco_order_stop_flag_is_set = false;
  private boolean oco_order_stop_flag_is_modified = false;
  private boolean ifd_order_stop_flag_is_set = false;
  private boolean ifd_order_stop_flag_is_modified = false;
  private boolean valid_term_from_is_set = false;
  private boolean valid_term_from_is_modified = false;
  private boolean valid_term_to_is_set = false;
  private boolean valid_term_to_is_modified = false;
  private boolean delete_flag_is_set = false;
  private boolean delete_flag_is_modified = false;
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
       + "trigger_order_stop_id=" + trigger_order_stop_id
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "target_type=" +target_type
      + "," + "key=" +key
      + "," + "addition=" +addition
      + "," + "stop_reason=" +stop_reason
      + "," + "succ_order_stop_flag=" +succ_order_stop_flag
      + "," + "stop_order_stop_flag=" +stop_order_stop_flag
      + "," + "wlimit_order_stop_flag=" +wlimit_order_stop_flag
      + "," + "oco_order_stop_flag=" +oco_order_stop_flag
      + "," + "ifd_order_stop_flag=" +ifd_order_stop_flag
      + "," + "valid_term_from=" +valid_term_from
      + "," + "valid_term_to=" +valid_term_to
      + "," + "delete_flag=" +delete_flag
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のTriggerOrderStopParamsオブジェクトを作成します。 
   */
  public TriggerOrderStopParams() {
    trigger_order_stop_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のTriggerOrderStopRowオブジェクトの値を利用してTriggerOrderStopParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するTriggerOrderStopRowオブジェクト 
   */
  public TriggerOrderStopParams( TriggerOrderStopRow p_row )
  {
    trigger_order_stop_id = p_row.getTriggerOrderStopId();
    trigger_order_stop_id_is_set = p_row.getTriggerOrderStopIdIsSet();
    trigger_order_stop_id_is_modified = p_row.getTriggerOrderStopIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    target_type = p_row.getTargetType();
    target_type_is_set = p_row.getTargetTypeIsSet();
    target_type_is_modified = p_row.getTargetTypeIsModified();
    key = p_row.getKey();
    key_is_set = p_row.getKeyIsSet();
    key_is_modified = p_row.getKeyIsModified();
    addition = p_row.getAddition();
    addition_is_set = p_row.getAdditionIsSet();
    addition_is_modified = p_row.getAdditionIsModified();
    stop_reason = p_row.getStopReason();
    stop_reason_is_set = p_row.getStopReasonIsSet();
    stop_reason_is_modified = p_row.getStopReasonIsModified();
    succ_order_stop_flag = p_row.getSuccOrderStopFlag();
    succ_order_stop_flag_is_set = p_row.getSuccOrderStopFlagIsSet();
    succ_order_stop_flag_is_modified = p_row.getSuccOrderStopFlagIsModified();
    stop_order_stop_flag = p_row.getStopOrderStopFlag();
    stop_order_stop_flag_is_set = p_row.getStopOrderStopFlagIsSet();
    stop_order_stop_flag_is_modified = p_row.getStopOrderStopFlagIsModified();
    wlimit_order_stop_flag = p_row.getWlimitOrderStopFlag();
    wlimit_order_stop_flag_is_set = p_row.getWlimitOrderStopFlagIsSet();
    wlimit_order_stop_flag_is_modified = p_row.getWlimitOrderStopFlagIsModified();
    oco_order_stop_flag = p_row.getOcoOrderStopFlag();
    oco_order_stop_flag_is_set = p_row.getOcoOrderStopFlagIsSet();
    oco_order_stop_flag_is_modified = p_row.getOcoOrderStopFlagIsModified();
    ifd_order_stop_flag = p_row.getIfdOrderStopFlag();
    ifd_order_stop_flag_is_set = p_row.getIfdOrderStopFlagIsSet();
    ifd_order_stop_flag_is_modified = p_row.getIfdOrderStopFlagIsModified();
    valid_term_from = p_row.getValidTermFrom();
    valid_term_from_is_set = p_row.getValidTermFromIsSet();
    valid_term_from_is_modified = p_row.getValidTermFromIsModified();
    valid_term_to = p_row.getValidTermTo();
    valid_term_to_is_set = p_row.getValidTermToIsSet();
    valid_term_to_is_modified = p_row.getValidTermToIsModified();
    delete_flag = p_row.getDeleteFlag();
    delete_flag_is_set = p_row.getDeleteFlagIsSet();
    delete_flag_is_modified = p_row.getDeleteFlagIsModified();
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
      institution_code_is_set = true;
      institution_code_is_modified = true;
      branch_code_is_set = true;
      branch_code_is_modified = true;
      target_type_is_set = true;
      target_type_is_modified = true;
      key_is_set = true;
      key_is_modified = true;
      addition_is_set = true;
      addition_is_modified = true;
      stop_reason_is_set = true;
      stop_reason_is_modified = true;
      succ_order_stop_flag_is_set = true;
      succ_order_stop_flag_is_modified = true;
      stop_order_stop_flag_is_set = true;
      stop_order_stop_flag_is_modified = true;
      wlimit_order_stop_flag_is_set = true;
      wlimit_order_stop_flag_is_modified = true;
      oco_order_stop_flag_is_set = true;
      oco_order_stop_flag_is_modified = true;
      ifd_order_stop_flag_is_set = true;
      ifd_order_stop_flag_is_modified = true;
      valid_term_from_is_set = true;
      valid_term_from_is_modified = true;
      valid_term_to_is_set = true;
      valid_term_to_is_modified = true;
      delete_flag_is_set = true;
      delete_flag_is_modified = true;
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
    if ( !( other instanceof TriggerOrderStopRow ) )
       return false;
    return fieldsEqual( (TriggerOrderStopRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のTriggerOrderStopRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( TriggerOrderStopRow row )
  {
    if ( trigger_order_stop_id != row.getTriggerOrderStopId() )
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
    if ( target_type == null ) {
      if ( row.getTargetType() != null )
        return false;
    } else if ( !target_type.equals( row.getTargetType() ) ) {
        return false;
    }
    if ( key == null ) {
      if ( row.getKey() != null )
        return false;
    } else if ( !key.equals( row.getKey() ) ) {
        return false;
    }
    if ( addition == null ) {
      if ( row.getAddition() != null )
        return false;
    } else if ( !addition.equals( row.getAddition() ) ) {
        return false;
    }
    if ( stop_reason == null ) {
      if ( row.getStopReason() != null )
        return false;
    } else if ( !stop_reason.equals( row.getStopReason() ) ) {
        return false;
    }
    if ( succ_order_stop_flag != row.getSuccOrderStopFlag() )
      return false;
    if ( stop_order_stop_flag != row.getStopOrderStopFlag() )
      return false;
    if ( wlimit_order_stop_flag != row.getWlimitOrderStopFlag() )
      return false;
    if ( oco_order_stop_flag != row.getOcoOrderStopFlag() )
      return false;
    if ( ifd_order_stop_flag != row.getIfdOrderStopFlag() )
      return false;
    if ( valid_term_from == null ) {
      if ( row.getValidTermFrom() != null )
        return false;
    } else if ( !valid_term_from.equals( row.getValidTermFrom() ) ) {
        return false;
    }
    if ( valid_term_to == null ) {
      if ( row.getValidTermTo() != null )
        return false;
    } else if ( !valid_term_to.equals( row.getValidTermTo() ) ) {
        return false;
    }
    if ( delete_flag != row.getDeleteFlag() )
      return false;
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
      return  ((int) trigger_order_stop_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (target_type!=null? target_type.hashCode(): 0) 
        + (key!=null? key.hashCode(): 0) 
        + (addition!=null? addition.hashCode(): 0) 
        + (stop_reason!=null? stop_reason.hashCode(): 0) 
        + ((int) succ_order_stop_flag)
        + ((int) stop_order_stop_flag)
        + ((int) wlimit_order_stop_flag)
        + ((int) oco_order_stop_flag)
        + ((int) ifd_order_stop_flag)
        + (valid_term_from!=null? valid_term_from.hashCode(): 0) 
        + (valid_term_to!=null? valid_term_to.hashCode(): 0) 
        + ((int) delete_flag)
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
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !branch_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'branch_code' must be set before inserting.");
		if ( !target_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'target_type' must be set before inserting.");
		if ( !key_is_set )
			throw new IllegalArgumentException("non-nullable field 'key' must be set before inserting.");
		if ( !valid_term_from_is_set )
			throw new IllegalArgumentException("non-nullable field 'valid_term_from' must be set before inserting.");
		if ( !valid_term_to_is_set )
			throw new IllegalArgumentException("non-nullable field 'valid_term_to' must be set before inserting.");
		if ( !delete_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'delete_flag' must be set before inserting.");
		if ( !last_updater_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updater' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("trigger_order_stop_id",new Long(trigger_order_stop_id));
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("target_type",target_type);
		map.put("key",key);
		if ( addition != null )
			map.put("addition",addition);
		if ( stop_reason != null )
			map.put("stop_reason",stop_reason);
		if ( succ_order_stop_flag_is_set )
			map.put("succ_order_stop_flag",new Integer(succ_order_stop_flag));
		if ( stop_order_stop_flag_is_set )
			map.put("stop_order_stop_flag",new Integer(stop_order_stop_flag));
		if ( wlimit_order_stop_flag_is_set )
			map.put("wlimit_order_stop_flag",new Integer(wlimit_order_stop_flag));
		if ( oco_order_stop_flag_is_set )
			map.put("oco_order_stop_flag",new Integer(oco_order_stop_flag));
		if ( ifd_order_stop_flag_is_set )
			map.put("ifd_order_stop_flag",new Integer(ifd_order_stop_flag));
		map.put("valid_term_from",valid_term_from);
		map.put("valid_term_to",valid_term_to);
		map.put("delete_flag",new Integer(delete_flag));
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
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( branch_code_is_modified )
			map.put("branch_code",branch_code);
		if ( target_type_is_modified )
			map.put("target_type",target_type);
		if ( key_is_modified )
			map.put("key",key);
		if ( addition_is_modified )
			map.put("addition",addition);
		if ( stop_reason_is_modified )
			map.put("stop_reason",stop_reason);
		if ( succ_order_stop_flag_is_modified )
			map.put("succ_order_stop_flag",new Integer(succ_order_stop_flag));
		if ( stop_order_stop_flag_is_modified )
			map.put("stop_order_stop_flag",new Integer(stop_order_stop_flag));
		if ( wlimit_order_stop_flag_is_modified )
			map.put("wlimit_order_stop_flag",new Integer(wlimit_order_stop_flag));
		if ( oco_order_stop_flag_is_modified )
			map.put("oco_order_stop_flag",new Integer(oco_order_stop_flag));
		if ( ifd_order_stop_flag_is_modified )
			map.put("ifd_order_stop_flag",new Integer(ifd_order_stop_flag));
		if ( valid_term_from_is_modified )
			map.put("valid_term_from",valid_term_from);
		if ( valid_term_to_is_modified )
			map.put("valid_term_to",valid_term_to);
		if ( delete_flag_is_modified )
			map.put("delete_flag",new Integer(delete_flag));
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( branch_code_is_set )
				map.put("branch_code",branch_code);
			if ( target_type_is_set )
				map.put("target_type",target_type);
			if ( key_is_set )
				map.put("key",key);
			map.put("addition",addition);
			map.put("stop_reason",stop_reason);
			if ( succ_order_stop_flag_is_set )
				map.put("succ_order_stop_flag",new Integer(succ_order_stop_flag));
			if ( stop_order_stop_flag_is_set )
				map.put("stop_order_stop_flag",new Integer(stop_order_stop_flag));
			if ( wlimit_order_stop_flag_is_set )
				map.put("wlimit_order_stop_flag",new Integer(wlimit_order_stop_flag));
			if ( oco_order_stop_flag_is_set )
				map.put("oco_order_stop_flag",new Integer(oco_order_stop_flag));
			if ( ifd_order_stop_flag_is_set )
				map.put("ifd_order_stop_flag",new Integer(ifd_order_stop_flag));
			if ( valid_term_from_is_set )
				map.put("valid_term_from",valid_term_from);
			if ( valid_term_to_is_set )
				map.put("valid_term_to",valid_term_to);
			if ( delete_flag_is_set )
				map.put("delete_flag",new Integer(delete_flag));
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
   * <em>trigger_order_stop_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getTriggerOrderStopId()
  {
    return trigger_order_stop_id;
  }


  /** 
   * <em>trigger_order_stop_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTriggerOrderStopIdIsSet() {
    return trigger_order_stop_id_is_set;
  }


  /** 
   * <em>trigger_order_stop_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTriggerOrderStopIdIsModified() {
    return trigger_order_stop_id_is_modified;
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
   * <em>target_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTargetType()
  {
    return target_type;
  }


  /** 
   * <em>target_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTargetTypeIsSet() {
    return target_type_is_set;
  }


  /** 
   * <em>target_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTargetTypeIsModified() {
    return target_type_is_modified;
  }


  /** 
   * <em>key</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getKey()
  {
    return key;
  }


  /** 
   * <em>key</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getKeyIsSet() {
    return key_is_set;
  }


  /** 
   * <em>key</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getKeyIsModified() {
    return key_is_modified;
  }


  /** 
   * <em>addition</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAddition()
  {
    return addition;
  }


  /** 
   * <em>addition</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAdditionIsSet() {
    return addition_is_set;
  }


  /** 
   * <em>addition</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAdditionIsModified() {
    return addition_is_modified;
  }


  /** 
   * <em>stop_reason</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStopReason()
  {
    return stop_reason;
  }


  /** 
   * <em>stop_reason</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopReasonIsSet() {
    return stop_reason_is_set;
  }


  /** 
   * <em>stop_reason</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopReasonIsModified() {
    return stop_reason_is_modified;
  }


  /** 
   * <em>succ_order_stop_flag</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getSuccOrderStopFlag()
  {
    return succ_order_stop_flag;
  }


  /** 
   * <em>succ_order_stop_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSuccOrderStopFlagIsSet() {
    return succ_order_stop_flag_is_set;
  }


  /** 
   * <em>succ_order_stop_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSuccOrderStopFlagIsModified() {
    return succ_order_stop_flag_is_modified;
  }


  /** 
   * <em>stop_order_stop_flag</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getStopOrderStopFlag()
  {
    return stop_order_stop_flag;
  }


  /** 
   * <em>stop_order_stop_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopOrderStopFlagIsSet() {
    return stop_order_stop_flag_is_set;
  }


  /** 
   * <em>stop_order_stop_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopOrderStopFlagIsModified() {
    return stop_order_stop_flag_is_modified;
  }


  /** 
   * <em>wlimit_order_stop_flag</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getWlimitOrderStopFlag()
  {
    return wlimit_order_stop_flag;
  }


  /** 
   * <em>wlimit_order_stop_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getWlimitOrderStopFlagIsSet() {
    return wlimit_order_stop_flag_is_set;
  }


  /** 
   * <em>wlimit_order_stop_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getWlimitOrderStopFlagIsModified() {
    return wlimit_order_stop_flag_is_modified;
  }


  /** 
   * <em>oco_order_stop_flag</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getOcoOrderStopFlag()
  {
    return oco_order_stop_flag;
  }


  /** 
   * <em>oco_order_stop_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOcoOrderStopFlagIsSet() {
    return oco_order_stop_flag_is_set;
  }


  /** 
   * <em>oco_order_stop_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOcoOrderStopFlagIsModified() {
    return oco_order_stop_flag_is_modified;
  }


  /** 
   * <em>ifd_order_stop_flag</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getIfdOrderStopFlag()
  {
    return ifd_order_stop_flag;
  }


  /** 
   * <em>ifd_order_stop_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfdOrderStopFlagIsSet() {
    return ifd_order_stop_flag_is_set;
  }


  /** 
   * <em>ifd_order_stop_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfdOrderStopFlagIsModified() {
    return ifd_order_stop_flag_is_modified;
  }


  /** 
   * <em>valid_term_from</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getValidTermFrom()
  {
    return valid_term_from;
  }


  /** 
   * <em>valid_term_from</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getValidTermFromIsSet() {
    return valid_term_from_is_set;
  }


  /** 
   * <em>valid_term_from</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getValidTermFromIsModified() {
    return valid_term_from_is_modified;
  }


  /** 
   * <em>valid_term_to</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getValidTermTo()
  {
    return valid_term_to;
  }


  /** 
   * <em>valid_term_to</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getValidTermToIsSet() {
    return valid_term_to_is_set;
  }


  /** 
   * <em>valid_term_to</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getValidTermToIsModified() {
    return valid_term_to_is_modified;
  }


  /** 
   * <em>delete_flag</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getDeleteFlag()
  {
    return delete_flag;
  }


  /** 
   * <em>delete_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeleteFlagIsSet() {
    return delete_flag_is_set;
  }


  /** 
   * <em>delete_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeleteFlagIsModified() {
    return delete_flag_is_modified;
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
    return new TriggerOrderStopPK(trigger_order_stop_id);
  }


  /** 
   * <em>trigger_order_stop_id</em>カラムの値を設定します。 
   *
   * @@param p_triggerOrderStopId <em>trigger_order_stop_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setTriggerOrderStopId( long p_triggerOrderStopId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trigger_order_stop_id = p_triggerOrderStopId;
    trigger_order_stop_id_is_set = true;
    trigger_order_stop_id_is_modified = true;
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
   * <em>target_type</em>カラムの値を設定します。 
   *
   * @@param p_targetType <em>target_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTargetType( String p_targetType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    target_type = p_targetType;
    target_type_is_set = true;
    target_type_is_modified = true;
  }


  /** 
   * <em>key</em>カラムの値を設定します。 
   *
   * @@param p_key <em>key</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setKey( String p_key )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    key = p_key;
    key_is_set = true;
    key_is_modified = true;
  }


  /** 
   * <em>addition</em>カラムの値を設定します。 
   *
   * @@param p_addition <em>addition</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setAddition( String p_addition )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    addition = p_addition;
    addition_is_set = true;
    addition_is_modified = true;
  }


  /** 
   * <em>stop_reason</em>カラムの値を設定します。 
   *
   * @@param p_stopReason <em>stop_reason</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setStopReason( String p_stopReason )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stop_reason = p_stopReason;
    stop_reason_is_set = true;
    stop_reason_is_modified = true;
  }


  /** 
   * <em>succ_order_stop_flag</em>カラムの値を設定します。 
   *
   * @@param p_succOrderStopFlag <em>succ_order_stop_flag</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setSuccOrderStopFlag( int p_succOrderStopFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    succ_order_stop_flag = p_succOrderStopFlag;
    succ_order_stop_flag_is_set = true;
    succ_order_stop_flag_is_modified = true;
  }


  /** 
   * <em>stop_order_stop_flag</em>カラムの値を設定します。 
   *
   * @@param p_stopOrderStopFlag <em>stop_order_stop_flag</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setStopOrderStopFlag( int p_stopOrderStopFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stop_order_stop_flag = p_stopOrderStopFlag;
    stop_order_stop_flag_is_set = true;
    stop_order_stop_flag_is_modified = true;
  }


  /** 
   * <em>wlimit_order_stop_flag</em>カラムの値を設定します。 
   *
   * @@param p_wlimitOrderStopFlag <em>wlimit_order_stop_flag</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setWlimitOrderStopFlag( int p_wlimitOrderStopFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    wlimit_order_stop_flag = p_wlimitOrderStopFlag;
    wlimit_order_stop_flag_is_set = true;
    wlimit_order_stop_flag_is_modified = true;
  }


  /** 
   * <em>oco_order_stop_flag</em>カラムの値を設定します。 
   *
   * @@param p_ocoOrderStopFlag <em>oco_order_stop_flag</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setOcoOrderStopFlag( int p_ocoOrderStopFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    oco_order_stop_flag = p_ocoOrderStopFlag;
    oco_order_stop_flag_is_set = true;
    oco_order_stop_flag_is_modified = true;
  }


  /** 
   * <em>ifd_order_stop_flag</em>カラムの値を設定します。 
   *
   * @@param p_ifdOrderStopFlag <em>ifd_order_stop_flag</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setIfdOrderStopFlag( int p_ifdOrderStopFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifd_order_stop_flag = p_ifdOrderStopFlag;
    ifd_order_stop_flag_is_set = true;
    ifd_order_stop_flag_is_modified = true;
  }


  /** 
   * <em>valid_term_from</em>カラムの値を設定します。 
   *
   * @@param p_validTermFrom <em>valid_term_from</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setValidTermFrom( java.sql.Timestamp p_validTermFrom )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    valid_term_from = p_validTermFrom;
    valid_term_from_is_set = true;
    valid_term_from_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>valid_term_from</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setValidTermFrom( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    valid_term_from = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    valid_term_from_is_set = true;
    valid_term_from_is_modified = true;
  }


  /** 
   * <em>valid_term_to</em>カラムの値を設定します。 
   *
   * @@param p_validTermTo <em>valid_term_to</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setValidTermTo( java.sql.Timestamp p_validTermTo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    valid_term_to = p_validTermTo;
    valid_term_to_is_set = true;
    valid_term_to_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>valid_term_to</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setValidTermTo( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    valid_term_to = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    valid_term_to_is_set = true;
    valid_term_to_is_modified = true;
  }


  /** 
   * <em>delete_flag</em>カラムの値を設定します。 
   *
   * @@param p_deleteFlag <em>delete_flag</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setDeleteFlag( int p_deleteFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delete_flag = p_deleteFlag;
    delete_flag_is_set = true;
    delete_flag_is_modified = true;
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
                if ( name.equals("addition") ) {
                    return this.addition;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("delete_flag") ) {
                    return new Integer( delete_flag );
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("ifd_order_stop_flag") ) {
                    return new Integer( ifd_order_stop_flag );
                }
                break;
            case 'k':
                if ( name.equals("key") ) {
                    return this.key;
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
                if ( name.equals("oco_order_stop_flag") ) {
                    return new Integer( oco_order_stop_flag );
                }
                break;
            case 's':
                if ( name.equals("stop_reason") ) {
                    return this.stop_reason;
                }
                else if ( name.equals("succ_order_stop_flag") ) {
                    return new Integer( succ_order_stop_flag );
                }
                else if ( name.equals("stop_order_stop_flag") ) {
                    return new Integer( stop_order_stop_flag );
                }
                break;
            case 't':
                if ( name.equals("trigger_order_stop_id") ) {
                    return new Long( trigger_order_stop_id );
                }
                else if ( name.equals("target_type") ) {
                    return this.target_type;
                }
                break;
            case 'v':
                if ( name.equals("valid_term_from") ) {
                    return this.valid_term_from;
                }
                else if ( name.equals("valid_term_to") ) {
                    return this.valid_term_to;
                }
                break;
            case 'w':
                if ( name.equals("wlimit_order_stop_flag") ) {
                    return new Integer( wlimit_order_stop_flag );
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
                if ( name.equals("addition") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'addition' must be String: '"+value+"' is not." );
                    this.addition = (String) value;
                    if (this.addition_is_set)
                        this.addition_is_modified = true;
                    this.addition_is_set = true;
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
                if ( name.equals("created_timestamp") ) {
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
                if ( name.equals("delete_flag") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'delete_flag' must be Integer: '"+value+"' is not." );
                    this.delete_flag = ((Integer) value).intValue();
                    if (this.delete_flag_is_set)
                        this.delete_flag_is_modified = true;
                    this.delete_flag_is_set = true;
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
                else if ( name.equals("ifd_order_stop_flag") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'ifd_order_stop_flag' must be Integer: '"+value+"' is not." );
                    this.ifd_order_stop_flag = ((Integer) value).intValue();
                    if (this.ifd_order_stop_flag_is_set)
                        this.ifd_order_stop_flag_is_modified = true;
                    this.ifd_order_stop_flag_is_set = true;
                    return;
                }
                break;
            case 'k':
                if ( name.equals("key") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'key' must be String: '"+value+"' is not." );
                    this.key = (String) value;
                    if (this.key_is_set)
                        this.key_is_modified = true;
                    this.key_is_set = true;
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
                if ( name.equals("oco_order_stop_flag") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'oco_order_stop_flag' must be Integer: '"+value+"' is not." );
                    this.oco_order_stop_flag = ((Integer) value).intValue();
                    if (this.oco_order_stop_flag_is_set)
                        this.oco_order_stop_flag_is_modified = true;
                    this.oco_order_stop_flag_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("stop_reason") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stop_reason' must be String: '"+value+"' is not." );
                    this.stop_reason = (String) value;
                    if (this.stop_reason_is_set)
                        this.stop_reason_is_modified = true;
                    this.stop_reason_is_set = true;
                    return;
                }
                else if ( name.equals("succ_order_stop_flag") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'succ_order_stop_flag' must be Integer: '"+value+"' is not." );
                    this.succ_order_stop_flag = ((Integer) value).intValue();
                    if (this.succ_order_stop_flag_is_set)
                        this.succ_order_stop_flag_is_modified = true;
                    this.succ_order_stop_flag_is_set = true;
                    return;
                }
                else if ( name.equals("stop_order_stop_flag") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'stop_order_stop_flag' must be Integer: '"+value+"' is not." );
                    this.stop_order_stop_flag = ((Integer) value).intValue();
                    if (this.stop_order_stop_flag_is_set)
                        this.stop_order_stop_flag_is_modified = true;
                    this.stop_order_stop_flag_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("trigger_order_stop_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'trigger_order_stop_id' must be Long: '"+value+"' is not." );
                    this.trigger_order_stop_id = ((Long) value).longValue();
                    if (this.trigger_order_stop_id_is_set)
                        this.trigger_order_stop_id_is_modified = true;
                    this.trigger_order_stop_id_is_set = true;
                    return;
                }
                else if ( name.equals("target_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'target_type' must be String: '"+value+"' is not." );
                    this.target_type = (String) value;
                    if (this.target_type_is_set)
                        this.target_type_is_modified = true;
                    this.target_type_is_set = true;
                    return;
                }
                break;
            case 'v':
                if ( name.equals("valid_term_from") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'valid_term_from' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.valid_term_from = (java.sql.Timestamp) value;
                    if (this.valid_term_from_is_set)
                        this.valid_term_from_is_modified = true;
                    this.valid_term_from_is_set = true;
                    return;
                }
                else if ( name.equals("valid_term_to") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'valid_term_to' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.valid_term_to = (java.sql.Timestamp) value;
                    if (this.valid_term_to_is_set)
                        this.valid_term_to_is_modified = true;
                    this.valid_term_to_is_set = true;
                    return;
                }
                break;
            case 'w':
                if ( name.equals("wlimit_order_stop_flag") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'wlimit_order_stop_flag' must be Integer: '"+value+"' is not." );
                    this.wlimit_order_stop_flag = ((Integer) value).intValue();
                    if (this.wlimit_order_stop_flag_is_set)
                        this.wlimit_order_stop_flag_is_modified = true;
                    this.wlimit_order_stop_flag_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
