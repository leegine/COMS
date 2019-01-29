head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.12.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccOpenWaitingParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * acc_open_waitingテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link AccOpenWaitingRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link AccOpenWaitingParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link AccOpenWaitingParams}が{@@link AccOpenWaitingRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AccOpenWaitingPK 
 * @@see AccOpenWaitingRow 
 */
public class AccOpenWaitingParams extends Params implements AccOpenWaitingRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "acc_open_waiting";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = AccOpenWaitingRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return AccOpenWaitingRow.TYPE;
  }


  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>acc_open_request_number</em>カラムの値 
   */
  public  String  acc_open_request_number;

  /** 
   * <em>serial_no</em>カラムの値 
   */
  public  String  serial_no;

  /** 
   * <em>branch_code</em>カラムの値 
   */
  public  String  branch_code;

  /** 
   * <em>account_code</em>カラムの値 
   */
  public  String  account_code;

  /** 
   * <em>review_code</em>カラムの値 
   */
  public  String  review_code;

  /** 
   * <em>duplication_div</em>カラムの値 
   */
  public  String  duplication_div;

  /** 
   * <em>diplo_branch_code</em>カラムの値 
   */
  public  String  diplo_branch_code;

  /** 
   * <em>diplo_account_code</em>カラムの値 
   */
  public  String  diplo_account_code;

  /** 
   * <em>y_customer_id</em>カラムの値 
   */
  public  Long  y_customer_id;

  /** 
   * <em>control_branch_code</em>カラムの値 
   */
  public  String  control_branch_code;

  /** 
   * <em>operation_div</em>カラムの値 
   */
  public  String  operation_div;

  /** 
   * <em>control_number</em>カラムの値 
   */
  public  Integer  control_number;

  /** 
   * <em>check_div</em>カラムの値 
   */
  public  String  check_div;

  /** 
   * <em>checker_code</em>カラムの値 
   */
  public  String  checker_code;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean acc_open_request_number_is_set = false;
  private boolean acc_open_request_number_is_modified = false;
  private boolean serial_no_is_set = false;
  private boolean serial_no_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean review_code_is_set = false;
  private boolean review_code_is_modified = false;
  private boolean duplication_div_is_set = false;
  private boolean duplication_div_is_modified = false;
  private boolean diplo_branch_code_is_set = false;
  private boolean diplo_branch_code_is_modified = false;
  private boolean diplo_account_code_is_set = false;
  private boolean diplo_account_code_is_modified = false;
  private boolean y_customer_id_is_set = false;
  private boolean y_customer_id_is_modified = false;
  private boolean control_branch_code_is_set = false;
  private boolean control_branch_code_is_modified = false;
  private boolean operation_div_is_set = false;
  private boolean operation_div_is_modified = false;
  private boolean control_number_is_set = false;
  private boolean control_number_is_modified = false;
  private boolean check_div_is_set = false;
  private boolean check_div_is_modified = false;
  private boolean checker_code_is_set = false;
  private boolean checker_code_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "acc_open_request_number=" + acc_open_request_number
      + "," + "serial_no=" + serial_no
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "," + "review_code=" +review_code
      + "," + "duplication_div=" +duplication_div
      + "," + "diplo_branch_code=" +diplo_branch_code
      + "," + "diplo_account_code=" +diplo_account_code
      + "," + "y_customer_id=" +y_customer_id
      + "," + "control_branch_code=" +control_branch_code
      + "," + "operation_div=" +operation_div
      + "," + "control_number=" +control_number
      + "," + "check_div=" +check_div
      + "," + "checker_code=" +checker_code
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のAccOpenWaitingParamsオブジェクトを作成します。 
   */
  public AccOpenWaitingParams() {
    institution_code_is_modified = true;
    acc_open_request_number_is_modified = true;
    serial_no_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のAccOpenWaitingRowオブジェクトの値を利用してAccOpenWaitingParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するAccOpenWaitingRowオブジェクト 
   */
  public AccOpenWaitingParams( AccOpenWaitingRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    acc_open_request_number = p_row.getAccOpenRequestNumber();
    acc_open_request_number_is_set = p_row.getAccOpenRequestNumberIsSet();
    acc_open_request_number_is_modified = p_row.getAccOpenRequestNumberIsModified();
    serial_no = p_row.getSerialNo();
    serial_no_is_set = p_row.getSerialNoIsSet();
    serial_no_is_modified = p_row.getSerialNoIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    review_code = p_row.getReviewCode();
    review_code_is_set = p_row.getReviewCodeIsSet();
    review_code_is_modified = p_row.getReviewCodeIsModified();
    duplication_div = p_row.getDuplicationDiv();
    duplication_div_is_set = p_row.getDuplicationDivIsSet();
    duplication_div_is_modified = p_row.getDuplicationDivIsModified();
    diplo_branch_code = p_row.getDiploBranchCode();
    diplo_branch_code_is_set = p_row.getDiploBranchCodeIsSet();
    diplo_branch_code_is_modified = p_row.getDiploBranchCodeIsModified();
    diplo_account_code = p_row.getDiploAccountCode();
    diplo_account_code_is_set = p_row.getDiploAccountCodeIsSet();
    diplo_account_code_is_modified = p_row.getDiploAccountCodeIsModified();
    if ( !p_row.getYCustomerIdIsNull() )
      y_customer_id = new Long( p_row.getYCustomerId() );
    y_customer_id_is_set = p_row.getYCustomerIdIsSet();
    y_customer_id_is_modified = p_row.getYCustomerIdIsModified();
    control_branch_code = p_row.getControlBranchCode();
    control_branch_code_is_set = p_row.getControlBranchCodeIsSet();
    control_branch_code_is_modified = p_row.getControlBranchCodeIsModified();
    operation_div = p_row.getOperationDiv();
    operation_div_is_set = p_row.getOperationDivIsSet();
    operation_div_is_modified = p_row.getOperationDivIsModified();
    if ( !p_row.getControlNumberIsNull() )
      control_number = new Integer( p_row.getControlNumber() );
    control_number_is_set = p_row.getControlNumberIsSet();
    control_number_is_modified = p_row.getControlNumberIsModified();
    check_div = p_row.getCheckDiv();
    check_div_is_set = p_row.getCheckDivIsSet();
    check_div_is_modified = p_row.getCheckDivIsModified();
    checker_code = p_row.getCheckerCode();
    checker_code_is_set = p_row.getCheckerCodeIsSet();
    checker_code_is_modified = p_row.getCheckerCodeIsModified();
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
      branch_code_is_set = true;
      branch_code_is_modified = true;
      account_code_is_set = true;
      account_code_is_modified = true;
      review_code_is_set = true;
      review_code_is_modified = true;
      duplication_div_is_set = true;
      duplication_div_is_modified = true;
      diplo_branch_code_is_set = true;
      diplo_branch_code_is_modified = true;
      diplo_account_code_is_set = true;
      diplo_account_code_is_modified = true;
      y_customer_id_is_set = true;
      y_customer_id_is_modified = true;
      control_branch_code_is_set = true;
      control_branch_code_is_modified = true;
      operation_div_is_set = true;
      operation_div_is_modified = true;
      control_number_is_set = true;
      control_number_is_modified = true;
      check_div_is_set = true;
      check_div_is_modified = true;
      checker_code_is_set = true;
      checker_code_is_modified = true;
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
    if ( !( other instanceof AccOpenWaitingRow ) )
       return false;
    return fieldsEqual( (AccOpenWaitingRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のAccOpenWaitingRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( AccOpenWaitingRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( acc_open_request_number == null ) {
      if ( row.getAccOpenRequestNumber() != null )
        return false;
    } else if ( !acc_open_request_number.equals( row.getAccOpenRequestNumber() ) ) {
        return false;
    }
    if ( serial_no == null ) {
      if ( row.getSerialNo() != null )
        return false;
    } else if ( !serial_no.equals( row.getSerialNo() ) ) {
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
    if ( review_code == null ) {
      if ( row.getReviewCode() != null )
        return false;
    } else if ( !review_code.equals( row.getReviewCode() ) ) {
        return false;
    }
    if ( duplication_div == null ) {
      if ( row.getDuplicationDiv() != null )
        return false;
    } else if ( !duplication_div.equals( row.getDuplicationDiv() ) ) {
        return false;
    }
    if ( diplo_branch_code == null ) {
      if ( row.getDiploBranchCode() != null )
        return false;
    } else if ( !diplo_branch_code.equals( row.getDiploBranchCode() ) ) {
        return false;
    }
    if ( diplo_account_code == null ) {
      if ( row.getDiploAccountCode() != null )
        return false;
    } else if ( !diplo_account_code.equals( row.getDiploAccountCode() ) ) {
        return false;
    }
    if ( y_customer_id == null ) {
      if ( !row.getYCustomerIdIsNull() )
        return false;
    } else if ( row.getYCustomerIdIsNull() || ( y_customer_id.longValue() != row.getYCustomerId() ) ) {
        return false;
    }
    if ( control_branch_code == null ) {
      if ( row.getControlBranchCode() != null )
        return false;
    } else if ( !control_branch_code.equals( row.getControlBranchCode() ) ) {
        return false;
    }
    if ( operation_div == null ) {
      if ( row.getOperationDiv() != null )
        return false;
    } else if ( !operation_div.equals( row.getOperationDiv() ) ) {
        return false;
    }
    if ( control_number == null ) {
      if ( !row.getControlNumberIsNull() )
        return false;
    } else if ( row.getControlNumberIsNull() || ( control_number.intValue() != row.getControlNumber() ) ) {
        return false;
    }
    if ( check_div == null ) {
      if ( row.getCheckDiv() != null )
        return false;
    } else if ( !check_div.equals( row.getCheckDiv() ) ) {
        return false;
    }
    if ( checker_code == null ) {
      if ( row.getCheckerCode() != null )
        return false;
    } else if ( !checker_code.equals( row.getCheckerCode() ) ) {
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
      return  (institution_code!=null? institution_code.hashCode(): 0) 
        + (acc_open_request_number!=null? acc_open_request_number.hashCode(): 0) 
        + (serial_no!=null? serial_no.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (review_code!=null? review_code.hashCode(): 0) 
        + (duplication_div!=null? duplication_div.hashCode(): 0) 
        + (diplo_branch_code!=null? diplo_branch_code.hashCode(): 0) 
        + (diplo_account_code!=null? diplo_account_code.hashCode(): 0) 
        + (y_customer_id!=null? y_customer_id.hashCode(): 0) 
        + (control_branch_code!=null? control_branch_code.hashCode(): 0) 
        + (operation_div!=null? operation_div.hashCode(): 0) 
        + (control_number!=null? control_number.hashCode(): 0) 
        + (check_div!=null? check_div.hashCode(): 0) 
        + (checker_code!=null? checker_code.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !review_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'review_code' must be set before inserting.");
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
		map.put("acc_open_request_number",acc_open_request_number);
		map.put("serial_no",serial_no);
		if ( branch_code != null )
			map.put("branch_code",branch_code);
		if ( account_code != null )
			map.put("account_code",account_code);
		map.put("review_code",review_code);
		if ( duplication_div != null )
			map.put("duplication_div",duplication_div);
		if ( diplo_branch_code != null )
			map.put("diplo_branch_code",diplo_branch_code);
		if ( diplo_account_code != null )
			map.put("diplo_account_code",diplo_account_code);
		if ( y_customer_id != null )
			map.put("y_customer_id",y_customer_id);
		if ( control_branch_code != null )
			map.put("control_branch_code",control_branch_code);
		if ( operation_div != null )
			map.put("operation_div",operation_div);
		if ( control_number != null )
			map.put("control_number",control_number);
		if ( check_div_is_set )
			map.put("check_div",check_div);
		if ( checker_code != null )
			map.put("checker_code",checker_code);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( branch_code_is_modified )
			map.put("branch_code",branch_code);
		if ( account_code_is_modified )
			map.put("account_code",account_code);
		if ( review_code_is_modified )
			map.put("review_code",review_code);
		if ( duplication_div_is_modified )
			map.put("duplication_div",duplication_div);
		if ( diplo_branch_code_is_modified )
			map.put("diplo_branch_code",diplo_branch_code);
		if ( diplo_account_code_is_modified )
			map.put("diplo_account_code",diplo_account_code);
		if ( y_customer_id_is_modified )
			map.put("y_customer_id",y_customer_id);
		if ( control_branch_code_is_modified )
			map.put("control_branch_code",control_branch_code);
		if ( operation_div_is_modified )
			map.put("operation_div",operation_div);
		if ( control_number_is_modified )
			map.put("control_number",control_number);
		if ( check_div_is_modified )
			map.put("check_div",check_div);
		if ( checker_code_is_modified )
			map.put("checker_code",checker_code);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("branch_code",branch_code);
			map.put("account_code",account_code);
			if ( review_code_is_set )
				map.put("review_code",review_code);
			map.put("duplication_div",duplication_div);
			map.put("diplo_branch_code",diplo_branch_code);
			map.put("diplo_account_code",diplo_account_code);
			map.put("y_customer_id",y_customer_id);
			map.put("control_branch_code",control_branch_code);
			map.put("operation_div",operation_div);
			map.put("control_number",control_number);
			if ( check_div_is_set )
				map.put("check_div",check_div);
			map.put("checker_code",checker_code);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
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
   * <em>acc_open_request_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccOpenRequestNumber()
  {
    return acc_open_request_number;
  }


  /** 
   * <em>acc_open_request_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccOpenRequestNumberIsSet() {
    return acc_open_request_number_is_set;
  }


  /** 
   * <em>acc_open_request_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccOpenRequestNumberIsModified() {
    return acc_open_request_number_is_modified;
  }


  /** 
   * <em>serial_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSerialNo()
  {
    return serial_no;
  }


  /** 
   * <em>serial_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSerialNoIsSet() {
    return serial_no_is_set;
  }


  /** 
   * <em>serial_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSerialNoIsModified() {
    return serial_no_is_modified;
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
   * <em>review_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getReviewCode()
  {
    return review_code;
  }


  /** 
   * <em>review_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReviewCodeIsSet() {
    return review_code_is_set;
  }


  /** 
   * <em>review_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReviewCodeIsModified() {
    return review_code_is_modified;
  }


  /** 
   * <em>duplication_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDuplicationDiv()
  {
    return duplication_div;
  }


  /** 
   * <em>duplication_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDuplicationDivIsSet() {
    return duplication_div_is_set;
  }


  /** 
   * <em>duplication_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDuplicationDivIsModified() {
    return duplication_div_is_modified;
  }


  /** 
   * <em>diplo_branch_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDiploBranchCode()
  {
    return diplo_branch_code;
  }


  /** 
   * <em>diplo_branch_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDiploBranchCodeIsSet() {
    return diplo_branch_code_is_set;
  }


  /** 
   * <em>diplo_branch_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDiploBranchCodeIsModified() {
    return diplo_branch_code_is_modified;
  }


  /** 
   * <em>diplo_account_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDiploAccountCode()
  {
    return diplo_account_code;
  }


  /** 
   * <em>diplo_account_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDiploAccountCodeIsSet() {
    return diplo_account_code_is_set;
  }


  /** 
   * <em>diplo_account_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDiploAccountCodeIsModified() {
    return diplo_account_code_is_modified;
  }


  /** 
   * <em>y_customer_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getYCustomerId()
  {
    return ( y_customer_id==null? ((long)0): y_customer_id.longValue() );
  }


  /** 
   * <em>y_customer_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getYCustomerIdIsNull()
  {
    return y_customer_id == null;
  }


  /** 
   * <em>y_customer_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getYCustomerIdIsSet() {
    return y_customer_id_is_set;
  }


  /** 
   * <em>y_customer_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getYCustomerIdIsModified() {
    return y_customer_id_is_modified;
  }


  /** 
   * <em>control_branch_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getControlBranchCode()
  {
    return control_branch_code;
  }


  /** 
   * <em>control_branch_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getControlBranchCodeIsSet() {
    return control_branch_code_is_set;
  }


  /** 
   * <em>control_branch_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getControlBranchCodeIsModified() {
    return control_branch_code_is_modified;
  }


  /** 
   * <em>operation_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOperationDiv()
  {
    return operation_div;
  }


  /** 
   * <em>operation_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOperationDivIsSet() {
    return operation_div_is_set;
  }


  /** 
   * <em>operation_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOperationDivIsModified() {
    return operation_div_is_modified;
  }


  /** 
   * <em>control_number</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getControlNumber()
  {
    return ( control_number==null? ((int)0): control_number.intValue() );
  }


  /** 
   * <em>control_number</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getControlNumberIsNull()
  {
    return control_number == null;
  }


  /** 
   * <em>control_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getControlNumberIsSet() {
    return control_number_is_set;
  }


  /** 
   * <em>control_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getControlNumberIsModified() {
    return control_number_is_modified;
  }


  /** 
   * <em>check_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCheckDiv()
  {
    return check_div;
  }


  /** 
   * <em>check_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCheckDivIsSet() {
    return check_div_is_set;
  }


  /** 
   * <em>check_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCheckDivIsModified() {
    return check_div_is_modified;
  }


  /** 
   * <em>checker_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCheckerCode()
  {
    return checker_code;
  }


  /** 
   * <em>checker_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCheckerCodeIsSet() {
    return checker_code_is_set;
  }


  /** 
   * <em>checker_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCheckerCodeIsModified() {
    return checker_code_is_modified;
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
    return new AccOpenWaitingPK(institution_code, acc_open_request_number, serial_no);
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
   * <em>acc_open_request_number</em>カラムの値を設定します。 
   *
   * @@param p_accOpenRequestNumber <em>acc_open_request_number</em>カラムの値をあらわす13桁以下のString値 
   */
  public final void setAccOpenRequestNumber( String p_accOpenRequestNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    acc_open_request_number = p_accOpenRequestNumber;
    acc_open_request_number_is_set = true;
    acc_open_request_number_is_modified = true;
  }


  /** 
   * <em>serial_no</em>カラムの値を設定します。 
   *
   * @@param p_serialNo <em>serial_no</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setSerialNo( String p_serialNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    serial_no = p_serialNo;
    serial_no_is_set = true;
    serial_no_is_modified = true;
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
   * <em>review_code</em>カラムの値を設定します。 
   *
   * @@param p_reviewCode <em>review_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setReviewCode( String p_reviewCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    review_code = p_reviewCode;
    review_code_is_set = true;
    review_code_is_modified = true;
  }


  /** 
   * <em>duplication_div</em>カラムの値を設定します。 
   *
   * @@param p_duplicationDiv <em>duplication_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setDuplicationDiv( String p_duplicationDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    duplication_div = p_duplicationDiv;
    duplication_div_is_set = true;
    duplication_div_is_modified = true;
  }


  /** 
   * <em>diplo_branch_code</em>カラムの値を設定します。 
   *
   * @@param p_diploBranchCode <em>diplo_branch_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setDiploBranchCode( String p_diploBranchCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    diplo_branch_code = p_diploBranchCode;
    diplo_branch_code_is_set = true;
    diplo_branch_code_is_modified = true;
  }


  /** 
   * <em>diplo_account_code</em>カラムの値を設定します。 
   *
   * @@param p_diploAccountCode <em>diplo_account_code</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setDiploAccountCode( String p_diploAccountCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    diplo_account_code = p_diploAccountCode;
    diplo_account_code_is_set = true;
    diplo_account_code_is_modified = true;
  }


  /** 
   * <em>y_customer_id</em>カラムの値を設定します。 
   *
   * @@param p_yCustomerId <em>y_customer_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setYCustomerId( long p_yCustomerId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    y_customer_id = new Long(p_yCustomerId);
    y_customer_id_is_set = true;
    y_customer_id_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>y_customer_id</em>カラムに値を設定します。 
   */
  public final void setYCustomerId( Long p_yCustomerId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    y_customer_id = p_yCustomerId;
    y_customer_id_is_set = true;
    y_customer_id_is_modified = true;
  }


  /** 
   * <em>control_branch_code</em>カラムの値を設定します。 
   *
   * @@param p_controlBranchCode <em>control_branch_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setControlBranchCode( String p_controlBranchCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    control_branch_code = p_controlBranchCode;
    control_branch_code_is_set = true;
    control_branch_code_is_modified = true;
  }


  /** 
   * <em>operation_div</em>カラムの値を設定します。 
   *
   * @@param p_operationDiv <em>operation_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setOperationDiv( String p_operationDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    operation_div = p_operationDiv;
    operation_div_is_set = true;
    operation_div_is_modified = true;
  }


  /** 
   * <em>control_number</em>カラムの値を設定します。 
   *
   * @@param p_controlNumber <em>control_number</em>カラムの値をあらわす7桁以下のint値 
   */
  public final void setControlNumber( int p_controlNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    control_number = new Integer(p_controlNumber);
    control_number_is_set = true;
    control_number_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>control_number</em>カラムに値を設定します。 
   */
  public final void setControlNumber( Integer p_controlNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    control_number = p_controlNumber;
    control_number_is_set = true;
    control_number_is_modified = true;
  }


  /** 
   * <em>check_div</em>カラムの値を設定します。 
   *
   * @@param p_checkDiv <em>check_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCheckDiv( String p_checkDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    check_div = p_checkDiv;
    check_div_is_set = true;
    check_div_is_modified = true;
  }


  /** 
   * <em>checker_code</em>カラムの値を設定します。 
   *
   * @@param p_checkerCode <em>checker_code</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setCheckerCode( String p_checkerCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    checker_code = p_checkerCode;
    checker_code_is_set = true;
    checker_code_is_modified = true;
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
                if ( name.equals("acc_open_request_number") ) {
                    return this.acc_open_request_number;
                }
                else if ( name.equals("account_code") ) {
                    return this.account_code;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'c':
                if ( name.equals("control_branch_code") ) {
                    return this.control_branch_code;
                }
                else if ( name.equals("control_number") ) {
                    return this.control_number;
                }
                else if ( name.equals("check_div") ) {
                    return this.check_div;
                }
                else if ( name.equals("checker_code") ) {
                    return this.checker_code;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("duplication_div") ) {
                    return this.duplication_div;
                }
                else if ( name.equals("diplo_branch_code") ) {
                    return this.diplo_branch_code;
                }
                else if ( name.equals("diplo_account_code") ) {
                    return this.diplo_account_code;
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
            case 'o':
                if ( name.equals("operation_div") ) {
                    return this.operation_div;
                }
                break;
            case 'r':
                if ( name.equals("review_code") ) {
                    return this.review_code;
                }
                break;
            case 's':
                if ( name.equals("serial_no") ) {
                    return this.serial_no;
                }
                break;
            case 'y':
                if ( name.equals("y_customer_id") ) {
                    return this.y_customer_id;
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
                if ( name.equals("acc_open_request_number") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'acc_open_request_number' must be String: '"+value+"' is not." );
                    this.acc_open_request_number = (String) value;
                    if (this.acc_open_request_number_is_set)
                        this.acc_open_request_number_is_modified = true;
                    this.acc_open_request_number_is_set = true;
                    return;
                }
                else if ( name.equals("account_code") ) {
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
                break;
            case 'c':
                if ( name.equals("control_branch_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'control_branch_code' must be String: '"+value+"' is not." );
                    this.control_branch_code = (String) value;
                    if (this.control_branch_code_is_set)
                        this.control_branch_code_is_modified = true;
                    this.control_branch_code_is_set = true;
                    return;
                }
                else if ( name.equals("control_number") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'control_number' must be Integer: '"+value+"' is not." );
                    this.control_number = (Integer) value;
                    if (this.control_number_is_set)
                        this.control_number_is_modified = true;
                    this.control_number_is_set = true;
                    return;
                }
                else if ( name.equals("check_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'check_div' must be String: '"+value+"' is not." );
                    this.check_div = (String) value;
                    if (this.check_div_is_set)
                        this.check_div_is_modified = true;
                    this.check_div_is_set = true;
                    return;
                }
                else if ( name.equals("checker_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'checker_code' must be String: '"+value+"' is not." );
                    this.checker_code = (String) value;
                    if (this.checker_code_is_set)
                        this.checker_code_is_modified = true;
                    this.checker_code_is_set = true;
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
                if ( name.equals("duplication_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'duplication_div' must be String: '"+value+"' is not." );
                    this.duplication_div = (String) value;
                    if (this.duplication_div_is_set)
                        this.duplication_div_is_modified = true;
                    this.duplication_div_is_set = true;
                    return;
                }
                else if ( name.equals("diplo_branch_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'diplo_branch_code' must be String: '"+value+"' is not." );
                    this.diplo_branch_code = (String) value;
                    if (this.diplo_branch_code_is_set)
                        this.diplo_branch_code_is_modified = true;
                    this.diplo_branch_code_is_set = true;
                    return;
                }
                else if ( name.equals("diplo_account_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'diplo_account_code' must be String: '"+value+"' is not." );
                    this.diplo_account_code = (String) value;
                    if (this.diplo_account_code_is_set)
                        this.diplo_account_code_is_modified = true;
                    this.diplo_account_code_is_set = true;
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
            case 'o':
                if ( name.equals("operation_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'operation_div' must be String: '"+value+"' is not." );
                    this.operation_div = (String) value;
                    if (this.operation_div_is_set)
                        this.operation_div_is_modified = true;
                    this.operation_div_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("review_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'review_code' must be String: '"+value+"' is not." );
                    this.review_code = (String) value;
                    if (this.review_code_is_set)
                        this.review_code_is_modified = true;
                    this.review_code_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("serial_no") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'serial_no' must be String: '"+value+"' is not." );
                    this.serial_no = (String) value;
                    if (this.serial_no_is_set)
                        this.serial_no_is_modified = true;
                    this.serial_no_is_set = true;
                    return;
                }
                break;
            case 'y':
                if ( name.equals("y_customer_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'y_customer_id' must be Long: '"+value+"' is not." );
                    this.y_customer_id = (Long) value;
                    if (this.y_customer_id_is_set)
                        this.y_customer_id_is_modified = true;
                    this.y_customer_id_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
