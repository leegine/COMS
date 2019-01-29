head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.10.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	BatchBranchParams.java;


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
 * batch_branchテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link BatchBranchRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link BatchBranchParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link BatchBranchParams}が{@@link BatchBranchRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BatchBranchPK 
 * @@see BatchBranchRow 
 */
public class BatchBranchParams extends Params implements BatchBranchRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "batch_branch";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = BatchBranchRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return BatchBranchRow.TYPE;
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
   * <em>equity_order_exe_mail_flag</em>カラムの値 
   */
  public  int  equity_order_exe_mail_flag;

  /** 
   * <em>equity_order_unexec_mail_flag</em>カラムの値 
   */
  public  int  equity_order_unexec_mail_flag;

  /** 
   * <em>ifo_order_exec_mail_flag</em>カラムの値 
   */
  public  int  ifo_order_exec_mail_flag;

  /** 
   * <em>ifo_order_unexec_mail_flag</em>カラムの値 
   */
  public  int  ifo_order_unexec_mail_flag;

  /** 
   * <em>information_mail_flag</em>カラムの値 
   */
  public  int  information_mail_flag;

  /** 
   * <em>transfer_count</em>カラムの値 
   */
  public  int  transfer_count;

  /** 
   * <em>top_page_id</em>カラムの値 
   */
  public  String  top_page_id;

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
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean equity_order_exe_mail_flag_is_set = false;
  private boolean equity_order_exe_mail_flag_is_modified = false;
  private boolean equity_order_unexec_mail_flag_is_set = false;
  private boolean equity_order_unexec_mail_flag_is_modified = false;
  private boolean ifo_order_exec_mail_flag_is_set = false;
  private boolean ifo_order_exec_mail_flag_is_modified = false;
  private boolean ifo_order_unexec_mail_flag_is_set = false;
  private boolean ifo_order_unexec_mail_flag_is_modified = false;
  private boolean information_mail_flag_is_set = false;
  private boolean information_mail_flag_is_modified = false;
  private boolean transfer_count_is_set = false;
  private boolean transfer_count_is_modified = false;
  private boolean top_page_id_is_set = false;
  private boolean top_page_id_is_modified = false;
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
      + "," + "branch_code=" + branch_code
      + "," + "equity_order_exe_mail_flag=" +equity_order_exe_mail_flag
      + "," + "equity_order_unexec_mail_flag=" +equity_order_unexec_mail_flag
      + "," + "ifo_order_exec_mail_flag=" +ifo_order_exec_mail_flag
      + "," + "ifo_order_unexec_mail_flag=" +ifo_order_unexec_mail_flag
      + "," + "information_mail_flag=" +information_mail_flag
      + "," + "transfer_count=" +transfer_count
      + "," + "top_page_id=" +top_page_id
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のBatchBranchParamsオブジェクトを作成します。 
   */
  public BatchBranchParams() {
    institution_code_is_modified = true;
    branch_code_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のBatchBranchRowオブジェクトの値を利用してBatchBranchParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するBatchBranchRowオブジェクト 
   */
  public BatchBranchParams( BatchBranchRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    equity_order_exe_mail_flag = p_row.getEquityOrderExeMailFlag();
    equity_order_exe_mail_flag_is_set = p_row.getEquityOrderExeMailFlagIsSet();
    equity_order_exe_mail_flag_is_modified = p_row.getEquityOrderExeMailFlagIsModified();
    equity_order_unexec_mail_flag = p_row.getEquityOrderUnexecMailFlag();
    equity_order_unexec_mail_flag_is_set = p_row.getEquityOrderUnexecMailFlagIsSet();
    equity_order_unexec_mail_flag_is_modified = p_row.getEquityOrderUnexecMailFlagIsModified();
    ifo_order_exec_mail_flag = p_row.getIfoOrderExecMailFlag();
    ifo_order_exec_mail_flag_is_set = p_row.getIfoOrderExecMailFlagIsSet();
    ifo_order_exec_mail_flag_is_modified = p_row.getIfoOrderExecMailFlagIsModified();
    ifo_order_unexec_mail_flag = p_row.getIfoOrderUnexecMailFlag();
    ifo_order_unexec_mail_flag_is_set = p_row.getIfoOrderUnexecMailFlagIsSet();
    ifo_order_unexec_mail_flag_is_modified = p_row.getIfoOrderUnexecMailFlagIsModified();
    information_mail_flag = p_row.getInformationMailFlag();
    information_mail_flag_is_set = p_row.getInformationMailFlagIsSet();
    information_mail_flag_is_modified = p_row.getInformationMailFlagIsModified();
    transfer_count = p_row.getTransferCount();
    transfer_count_is_set = p_row.getTransferCountIsSet();
    transfer_count_is_modified = p_row.getTransferCountIsModified();
    top_page_id = p_row.getTopPageId();
    top_page_id_is_set = p_row.getTopPageIdIsSet();
    top_page_id_is_modified = p_row.getTopPageIdIsModified();
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
      equity_order_exe_mail_flag_is_set = true;
      equity_order_exe_mail_flag_is_modified = true;
      equity_order_unexec_mail_flag_is_set = true;
      equity_order_unexec_mail_flag_is_modified = true;
      ifo_order_exec_mail_flag_is_set = true;
      ifo_order_exec_mail_flag_is_modified = true;
      ifo_order_unexec_mail_flag_is_set = true;
      ifo_order_unexec_mail_flag_is_modified = true;
      information_mail_flag_is_set = true;
      information_mail_flag_is_modified = true;
      transfer_count_is_set = true;
      transfer_count_is_modified = true;
      top_page_id_is_set = true;
      top_page_id_is_modified = true;
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
    if ( !( other instanceof BatchBranchRow ) )
       return false;
    return fieldsEqual( (BatchBranchRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のBatchBranchRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( BatchBranchRow row )
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
    if ( equity_order_exe_mail_flag != row.getEquityOrderExeMailFlag() )
      return false;
    if ( equity_order_unexec_mail_flag != row.getEquityOrderUnexecMailFlag() )
      return false;
    if ( ifo_order_exec_mail_flag != row.getIfoOrderExecMailFlag() )
      return false;
    if ( ifo_order_unexec_mail_flag != row.getIfoOrderUnexecMailFlag() )
      return false;
    if ( information_mail_flag != row.getInformationMailFlag() )
      return false;
    if ( transfer_count != row.getTransferCount() )
      return false;
    if ( top_page_id == null ) {
      if ( row.getTopPageId() != null )
        return false;
    } else if ( !top_page_id.equals( row.getTopPageId() ) ) {
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
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + ((int) equity_order_exe_mail_flag)
        + ((int) equity_order_unexec_mail_flag)
        + ((int) ifo_order_exec_mail_flag)
        + ((int) ifo_order_unexec_mail_flag)
        + ((int) information_mail_flag)
        + ((int) transfer_count)
        + (top_page_id!=null? top_page_id.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !equity_order_exe_mail_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'equity_order_exe_mail_flag' must be set before inserting.");
		if ( !equity_order_unexec_mail_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'equity_order_unexec_mail_flag' must be set before inserting.");
		if ( !ifo_order_exec_mail_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'ifo_order_exec_mail_flag' must be set before inserting.");
		if ( !ifo_order_unexec_mail_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'ifo_order_unexec_mail_flag' must be set before inserting.");
		if ( !information_mail_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'information_mail_flag' must be set before inserting.");
		if ( !transfer_count_is_set )
			throw new IllegalArgumentException("non-nullable field 'transfer_count' must be set before inserting.");
		if ( !top_page_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'top_page_id' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("equity_order_exe_mail_flag",new Integer(equity_order_exe_mail_flag));
		map.put("equity_order_unexec_mail_flag",new Integer(equity_order_unexec_mail_flag));
		map.put("ifo_order_exec_mail_flag",new Integer(ifo_order_exec_mail_flag));
		map.put("ifo_order_unexec_mail_flag",new Integer(ifo_order_unexec_mail_flag));
		map.put("information_mail_flag",new Integer(information_mail_flag));
		map.put("transfer_count",new Integer(transfer_count));
		map.put("top_page_id",top_page_id);
		if ( created_timestamp != null )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp != null )
			map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( equity_order_exe_mail_flag_is_modified )
			map.put("equity_order_exe_mail_flag",new Integer(equity_order_exe_mail_flag));
		if ( equity_order_unexec_mail_flag_is_modified )
			map.put("equity_order_unexec_mail_flag",new Integer(equity_order_unexec_mail_flag));
		if ( ifo_order_exec_mail_flag_is_modified )
			map.put("ifo_order_exec_mail_flag",new Integer(ifo_order_exec_mail_flag));
		if ( ifo_order_unexec_mail_flag_is_modified )
			map.put("ifo_order_unexec_mail_flag",new Integer(ifo_order_unexec_mail_flag));
		if ( information_mail_flag_is_modified )
			map.put("information_mail_flag",new Integer(information_mail_flag));
		if ( transfer_count_is_modified )
			map.put("transfer_count",new Integer(transfer_count));
		if ( top_page_id_is_modified )
			map.put("top_page_id",top_page_id);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( equity_order_exe_mail_flag_is_set )
				map.put("equity_order_exe_mail_flag",new Integer(equity_order_exe_mail_flag));
			if ( equity_order_unexec_mail_flag_is_set )
				map.put("equity_order_unexec_mail_flag",new Integer(equity_order_unexec_mail_flag));
			if ( ifo_order_exec_mail_flag_is_set )
				map.put("ifo_order_exec_mail_flag",new Integer(ifo_order_exec_mail_flag));
			if ( ifo_order_unexec_mail_flag_is_set )
				map.put("ifo_order_unexec_mail_flag",new Integer(ifo_order_unexec_mail_flag));
			if ( information_mail_flag_is_set )
				map.put("information_mail_flag",new Integer(information_mail_flag));
			if ( transfer_count_is_set )
				map.put("transfer_count",new Integer(transfer_count));
			if ( top_page_id_is_set )
				map.put("top_page_id",top_page_id);
			map.put("created_timestamp",created_timestamp);
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
   * <em>equity_order_exe_mail_flag</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getEquityOrderExeMailFlag()
  {
    return equity_order_exe_mail_flag;
  }


  /** 
   * <em>equity_order_exe_mail_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEquityOrderExeMailFlagIsSet() {
    return equity_order_exe_mail_flag_is_set;
  }


  /** 
   * <em>equity_order_exe_mail_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEquityOrderExeMailFlagIsModified() {
    return equity_order_exe_mail_flag_is_modified;
  }


  /** 
   * <em>equity_order_unexec_mail_flag</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getEquityOrderUnexecMailFlag()
  {
    return equity_order_unexec_mail_flag;
  }


  /** 
   * <em>equity_order_unexec_mail_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEquityOrderUnexecMailFlagIsSet() {
    return equity_order_unexec_mail_flag_is_set;
  }


  /** 
   * <em>equity_order_unexec_mail_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEquityOrderUnexecMailFlagIsModified() {
    return equity_order_unexec_mail_flag_is_modified;
  }


  /** 
   * <em>ifo_order_exec_mail_flag</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getIfoOrderExecMailFlag()
  {
    return ifo_order_exec_mail_flag;
  }


  /** 
   * <em>ifo_order_exec_mail_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoOrderExecMailFlagIsSet() {
    return ifo_order_exec_mail_flag_is_set;
  }


  /** 
   * <em>ifo_order_exec_mail_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoOrderExecMailFlagIsModified() {
    return ifo_order_exec_mail_flag_is_modified;
  }


  /** 
   * <em>ifo_order_unexec_mail_flag</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getIfoOrderUnexecMailFlag()
  {
    return ifo_order_unexec_mail_flag;
  }


  /** 
   * <em>ifo_order_unexec_mail_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoOrderUnexecMailFlagIsSet() {
    return ifo_order_unexec_mail_flag_is_set;
  }


  /** 
   * <em>ifo_order_unexec_mail_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoOrderUnexecMailFlagIsModified() {
    return ifo_order_unexec_mail_flag_is_modified;
  }


  /** 
   * <em>information_mail_flag</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getInformationMailFlag()
  {
    return information_mail_flag;
  }


  /** 
   * <em>information_mail_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInformationMailFlagIsSet() {
    return information_mail_flag_is_set;
  }


  /** 
   * <em>information_mail_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInformationMailFlagIsModified() {
    return information_mail_flag_is_modified;
  }


  /** 
   * <em>transfer_count</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getTransferCount()
  {
    return transfer_count;
  }


  /** 
   * <em>transfer_count</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferCountIsSet() {
    return transfer_count_is_set;
  }


  /** 
   * <em>transfer_count</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferCountIsModified() {
    return transfer_count_is_modified;
  }


  /** 
   * <em>top_page_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTopPageId()
  {
    return top_page_id;
  }


  /** 
   * <em>top_page_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTopPageIdIsSet() {
    return top_page_id_is_set;
  }


  /** 
   * <em>top_page_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTopPageIdIsModified() {
    return top_page_id_is_modified;
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
    return new BatchBranchPK(institution_code, branch_code);
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
   * <em>equity_order_exe_mail_flag</em>カラムの値を設定します。 
   *
   * @@param p_equityOrderExeMailFlag <em>equity_order_exe_mail_flag</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setEquityOrderExeMailFlag( int p_equityOrderExeMailFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    equity_order_exe_mail_flag = p_equityOrderExeMailFlag;
    equity_order_exe_mail_flag_is_set = true;
    equity_order_exe_mail_flag_is_modified = true;
  }


  /** 
   * <em>equity_order_unexec_mail_flag</em>カラムの値を設定します。 
   *
   * @@param p_equityOrderUnexecMailFlag <em>equity_order_unexec_mail_flag</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setEquityOrderUnexecMailFlag( int p_equityOrderUnexecMailFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    equity_order_unexec_mail_flag = p_equityOrderUnexecMailFlag;
    equity_order_unexec_mail_flag_is_set = true;
    equity_order_unexec_mail_flag_is_modified = true;
  }


  /** 
   * <em>ifo_order_exec_mail_flag</em>カラムの値を設定します。 
   *
   * @@param p_ifoOrderExecMailFlag <em>ifo_order_exec_mail_flag</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setIfoOrderExecMailFlag( int p_ifoOrderExecMailFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_order_exec_mail_flag = p_ifoOrderExecMailFlag;
    ifo_order_exec_mail_flag_is_set = true;
    ifo_order_exec_mail_flag_is_modified = true;
  }


  /** 
   * <em>ifo_order_unexec_mail_flag</em>カラムの値を設定します。 
   *
   * @@param p_ifoOrderUnexecMailFlag <em>ifo_order_unexec_mail_flag</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setIfoOrderUnexecMailFlag( int p_ifoOrderUnexecMailFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_order_unexec_mail_flag = p_ifoOrderUnexecMailFlag;
    ifo_order_unexec_mail_flag_is_set = true;
    ifo_order_unexec_mail_flag_is_modified = true;
  }


  /** 
   * <em>information_mail_flag</em>カラムの値を設定します。 
   *
   * @@param p_informationMailFlag <em>information_mail_flag</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setInformationMailFlag( int p_informationMailFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    information_mail_flag = p_informationMailFlag;
    information_mail_flag_is_set = true;
    information_mail_flag_is_modified = true;
  }


  /** 
   * <em>transfer_count</em>カラムの値を設定します。 
   *
   * @@param p_transferCount <em>transfer_count</em>カラムの値をあらわす2桁以下のint値 
   */
  public final void setTransferCount( int p_transferCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transfer_count = p_transferCount;
    transfer_count_is_set = true;
    transfer_count_is_modified = true;
  }


  /** 
   * <em>top_page_id</em>カラムの値を設定します。 
   *
   * @@param p_topPageId <em>top_page_id</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setTopPageId( String p_topPageId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    top_page_id = p_topPageId;
    top_page_id_is_set = true;
    top_page_id_is_modified = true;
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
            case 'e':
                if ( name.equals("equity_order_exe_mail_flag") ) {
                    return new Integer( equity_order_exe_mail_flag );
                }
                else if ( name.equals("equity_order_unexec_mail_flag") ) {
                    return new Integer( equity_order_unexec_mail_flag );
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("ifo_order_exec_mail_flag") ) {
                    return new Integer( ifo_order_exec_mail_flag );
                }
                else if ( name.equals("ifo_order_unexec_mail_flag") ) {
                    return new Integer( ifo_order_unexec_mail_flag );
                }
                else if ( name.equals("information_mail_flag") ) {
                    return new Integer( information_mail_flag );
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 't':
                if ( name.equals("transfer_count") ) {
                    return new Integer( transfer_count );
                }
                else if ( name.equals("top_page_id") ) {
                    return this.top_page_id;
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
            case 'e':
                if ( name.equals("equity_order_exe_mail_flag") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'equity_order_exe_mail_flag' must be Integer: '"+value+"' is not." );
                    this.equity_order_exe_mail_flag = ((Integer) value).intValue();
                    if (this.equity_order_exe_mail_flag_is_set)
                        this.equity_order_exe_mail_flag_is_modified = true;
                    this.equity_order_exe_mail_flag_is_set = true;
                    return;
                }
                else if ( name.equals("equity_order_unexec_mail_flag") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'equity_order_unexec_mail_flag' must be Integer: '"+value+"' is not." );
                    this.equity_order_unexec_mail_flag = ((Integer) value).intValue();
                    if (this.equity_order_unexec_mail_flag_is_set)
                        this.equity_order_unexec_mail_flag_is_modified = true;
                    this.equity_order_unexec_mail_flag_is_set = true;
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
                else if ( name.equals("ifo_order_exec_mail_flag") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'ifo_order_exec_mail_flag' must be Integer: '"+value+"' is not." );
                    this.ifo_order_exec_mail_flag = ((Integer) value).intValue();
                    if (this.ifo_order_exec_mail_flag_is_set)
                        this.ifo_order_exec_mail_flag_is_modified = true;
                    this.ifo_order_exec_mail_flag_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_order_unexec_mail_flag") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'ifo_order_unexec_mail_flag' must be Integer: '"+value+"' is not." );
                    this.ifo_order_unexec_mail_flag = ((Integer) value).intValue();
                    if (this.ifo_order_unexec_mail_flag_is_set)
                        this.ifo_order_unexec_mail_flag_is_modified = true;
                    this.ifo_order_unexec_mail_flag_is_set = true;
                    return;
                }
                else if ( name.equals("information_mail_flag") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'information_mail_flag' must be Integer: '"+value+"' is not." );
                    this.information_mail_flag = ((Integer) value).intValue();
                    if (this.information_mail_flag_is_set)
                        this.information_mail_flag_is_modified = true;
                    this.information_mail_flag_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
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
            case 't':
                if ( name.equals("transfer_count") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'transfer_count' must be Integer: '"+value+"' is not." );
                    this.transfer_count = ((Integer) value).intValue();
                    if (this.transfer_count_is_set)
                        this.transfer_count_is_modified = true;
                    this.transfer_count_is_set = true;
                    return;
                }
                else if ( name.equals("top_page_id") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'top_page_id' must be String: '"+value+"' is not." );
                    this.top_page_id = (String) value;
                    if (this.top_page_id_is_set)
                        this.top_page_id_is_modified = true;
                    this.top_page_id_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
