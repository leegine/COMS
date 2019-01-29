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
filename	MfFixedBuyingDrawAccountParams.java;


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
 * mf_fixed_buying_draw_accountテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link MfFixedBuyingDrawAccountRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link MfFixedBuyingDrawAccountParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link MfFixedBuyingDrawAccountParams}が{@@link MfFixedBuyingDrawAccountRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MfFixedBuyingDrawAccountPK 
 * @@see MfFixedBuyingDrawAccountRow 
 */
public class MfFixedBuyingDrawAccountParams extends Params implements MfFixedBuyingDrawAccountRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "mf_fixed_buying_draw_account";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = MfFixedBuyingDrawAccountRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return MfFixedBuyingDrawAccountRow.TYPE;
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
   * <em>account_code</em>カラムの値 
   */
  public  String  account_code;

  /** 
   * <em>fin_institution_div</em>カラムの値 
   */
  public  String  fin_institution_div;

  /** 
   * <em>fin_institution_code</em>カラムの値 
   */
  public  String  fin_institution_code;

  /** 
   * <em>fin_branch_code</em>カラムの値 
   */
  public  String  fin_branch_code;

  /** 
   * <em>deposit_div</em>カラムの値 
   */
  public  String  deposit_div;

  /** 
   * <em>draw_account_no</em>カラムの値 
   */
  public  String  draw_account_no;

  /** 
   * <em>draw_account_name_kana</em>カラムの値 
   */
  public  String  draw_account_name_kana;

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
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean fin_institution_div_is_set = false;
  private boolean fin_institution_div_is_modified = false;
  private boolean fin_institution_code_is_set = false;
  private boolean fin_institution_code_is_modified = false;
  private boolean fin_branch_code_is_set = false;
  private boolean fin_branch_code_is_modified = false;
  private boolean deposit_div_is_set = false;
  private boolean deposit_div_is_modified = false;
  private boolean draw_account_no_is_set = false;
  private boolean draw_account_no_is_modified = false;
  private boolean draw_account_name_kana_is_set = false;
  private boolean draw_account_name_kana_is_modified = false;
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
      + "," + "account_code=" + account_code
      + "," + "fin_institution_div=" +fin_institution_div
      + "," + "fin_institution_code=" +fin_institution_code
      + "," + "fin_branch_code=" +fin_branch_code
      + "," + "deposit_div=" +deposit_div
      + "," + "draw_account_no=" +draw_account_no
      + "," + "draw_account_name_kana=" +draw_account_name_kana
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のMfFixedBuyingDrawAccountParamsオブジェクトを作成します。 
   */
  public MfFixedBuyingDrawAccountParams() {
    institution_code_is_modified = true;
    branch_code_is_modified = true;
    account_code_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のMfFixedBuyingDrawAccountRowオブジェクトの値を利用してMfFixedBuyingDrawAccountParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するMfFixedBuyingDrawAccountRowオブジェクト 
   */
  public MfFixedBuyingDrawAccountParams( MfFixedBuyingDrawAccountRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    fin_institution_div = p_row.getFinInstitutionDiv();
    fin_institution_div_is_set = p_row.getFinInstitutionDivIsSet();
    fin_institution_div_is_modified = p_row.getFinInstitutionDivIsModified();
    fin_institution_code = p_row.getFinInstitutionCode();
    fin_institution_code_is_set = p_row.getFinInstitutionCodeIsSet();
    fin_institution_code_is_modified = p_row.getFinInstitutionCodeIsModified();
    fin_branch_code = p_row.getFinBranchCode();
    fin_branch_code_is_set = p_row.getFinBranchCodeIsSet();
    fin_branch_code_is_modified = p_row.getFinBranchCodeIsModified();
    deposit_div = p_row.getDepositDiv();
    deposit_div_is_set = p_row.getDepositDivIsSet();
    deposit_div_is_modified = p_row.getDepositDivIsModified();
    draw_account_no = p_row.getDrawAccountNo();
    draw_account_no_is_set = p_row.getDrawAccountNoIsSet();
    draw_account_no_is_modified = p_row.getDrawAccountNoIsModified();
    draw_account_name_kana = p_row.getDrawAccountNameKana();
    draw_account_name_kana_is_set = p_row.getDrawAccountNameKanaIsSet();
    draw_account_name_kana_is_modified = p_row.getDrawAccountNameKanaIsModified();
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
      fin_institution_div_is_set = true;
      fin_institution_div_is_modified = true;
      fin_institution_code_is_set = true;
      fin_institution_code_is_modified = true;
      fin_branch_code_is_set = true;
      fin_branch_code_is_modified = true;
      deposit_div_is_set = true;
      deposit_div_is_modified = true;
      draw_account_no_is_set = true;
      draw_account_no_is_modified = true;
      draw_account_name_kana_is_set = true;
      draw_account_name_kana_is_modified = true;
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
    if ( !( other instanceof MfFixedBuyingDrawAccountRow ) )
       return false;
    return fieldsEqual( (MfFixedBuyingDrawAccountRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のMfFixedBuyingDrawAccountRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( MfFixedBuyingDrawAccountRow row )
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
    if ( account_code == null ) {
      if ( row.getAccountCode() != null )
        return false;
    } else if ( !account_code.equals( row.getAccountCode() ) ) {
        return false;
    }
    if ( fin_institution_div == null ) {
      if ( row.getFinInstitutionDiv() != null )
        return false;
    } else if ( !fin_institution_div.equals( row.getFinInstitutionDiv() ) ) {
        return false;
    }
    if ( fin_institution_code == null ) {
      if ( row.getFinInstitutionCode() != null )
        return false;
    } else if ( !fin_institution_code.equals( row.getFinInstitutionCode() ) ) {
        return false;
    }
    if ( fin_branch_code == null ) {
      if ( row.getFinBranchCode() != null )
        return false;
    } else if ( !fin_branch_code.equals( row.getFinBranchCode() ) ) {
        return false;
    }
    if ( deposit_div == null ) {
      if ( row.getDepositDiv() != null )
        return false;
    } else if ( !deposit_div.equals( row.getDepositDiv() ) ) {
        return false;
    }
    if ( draw_account_no == null ) {
      if ( row.getDrawAccountNo() != null )
        return false;
    } else if ( !draw_account_no.equals( row.getDrawAccountNo() ) ) {
        return false;
    }
    if ( draw_account_name_kana == null ) {
      if ( row.getDrawAccountNameKana() != null )
        return false;
    } else if ( !draw_account_name_kana.equals( row.getDrawAccountNameKana() ) ) {
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
        + (account_code!=null? account_code.hashCode(): 0) 
        + (fin_institution_div!=null? fin_institution_div.hashCode(): 0) 
        + (fin_institution_code!=null? fin_institution_code.hashCode(): 0) 
        + (fin_branch_code!=null? fin_branch_code.hashCode(): 0) 
        + (deposit_div!=null? deposit_div.hashCode(): 0) 
        + (draw_account_no!=null? draw_account_no.hashCode(): 0) 
        + (draw_account_name_kana!=null? draw_account_name_kana.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !fin_institution_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'fin_institution_div' must be set before inserting.");
		if ( !fin_institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'fin_institution_code' must be set before inserting.");
		if ( !fin_branch_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'fin_branch_code' must be set before inserting.");
		if ( !draw_account_no_is_set )
			throw new IllegalArgumentException("non-nullable field 'draw_account_no' must be set before inserting.");
		if ( !draw_account_name_kana_is_set )
			throw new IllegalArgumentException("non-nullable field 'draw_account_name_kana' must be set before inserting.");
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
		map.put("account_code",account_code);
		map.put("fin_institution_div",fin_institution_div);
		map.put("fin_institution_code",fin_institution_code);
		map.put("fin_branch_code",fin_branch_code);
		if ( deposit_div != null )
			map.put("deposit_div",deposit_div);
		map.put("draw_account_no",draw_account_no);
		map.put("draw_account_name_kana",draw_account_name_kana);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( fin_institution_div_is_modified )
			map.put("fin_institution_div",fin_institution_div);
		if ( fin_institution_code_is_modified )
			map.put("fin_institution_code",fin_institution_code);
		if ( fin_branch_code_is_modified )
			map.put("fin_branch_code",fin_branch_code);
		if ( deposit_div_is_modified )
			map.put("deposit_div",deposit_div);
		if ( draw_account_no_is_modified )
			map.put("draw_account_no",draw_account_no);
		if ( draw_account_name_kana_is_modified )
			map.put("draw_account_name_kana",draw_account_name_kana);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( fin_institution_div_is_set )
				map.put("fin_institution_div",fin_institution_div);
			if ( fin_institution_code_is_set )
				map.put("fin_institution_code",fin_institution_code);
			if ( fin_branch_code_is_set )
				map.put("fin_branch_code",fin_branch_code);
			map.put("deposit_div",deposit_div);
			if ( draw_account_no_is_set )
				map.put("draw_account_no",draw_account_no);
			if ( draw_account_name_kana_is_set )
				map.put("draw_account_name_kana",draw_account_name_kana);
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
   * <em>fin_institution_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFinInstitutionDiv()
  {
    return fin_institution_div;
  }


  /** 
   * <em>fin_institution_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinInstitutionDivIsSet() {
    return fin_institution_div_is_set;
  }


  /** 
   * <em>fin_institution_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinInstitutionDivIsModified() {
    return fin_institution_div_is_modified;
  }


  /** 
   * <em>fin_institution_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFinInstitutionCode()
  {
    return fin_institution_code;
  }


  /** 
   * <em>fin_institution_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinInstitutionCodeIsSet() {
    return fin_institution_code_is_set;
  }


  /** 
   * <em>fin_institution_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinInstitutionCodeIsModified() {
    return fin_institution_code_is_modified;
  }


  /** 
   * <em>fin_branch_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFinBranchCode()
  {
    return fin_branch_code;
  }


  /** 
   * <em>fin_branch_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinBranchCodeIsSet() {
    return fin_branch_code_is_set;
  }


  /** 
   * <em>fin_branch_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinBranchCodeIsModified() {
    return fin_branch_code_is_modified;
  }


  /** 
   * <em>deposit_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDepositDiv()
  {
    return deposit_div;
  }


  /** 
   * <em>deposit_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDepositDivIsSet() {
    return deposit_div_is_set;
  }


  /** 
   * <em>deposit_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDepositDivIsModified() {
    return deposit_div_is_modified;
  }


  /** 
   * <em>draw_account_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDrawAccountNo()
  {
    return draw_account_no;
  }


  /** 
   * <em>draw_account_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDrawAccountNoIsSet() {
    return draw_account_no_is_set;
  }


  /** 
   * <em>draw_account_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDrawAccountNoIsModified() {
    return draw_account_no_is_modified;
  }


  /** 
   * <em>draw_account_name_kana</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDrawAccountNameKana()
  {
    return draw_account_name_kana;
  }


  /** 
   * <em>draw_account_name_kana</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDrawAccountNameKanaIsSet() {
    return draw_account_name_kana_is_set;
  }


  /** 
   * <em>draw_account_name_kana</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDrawAccountNameKanaIsModified() {
    return draw_account_name_kana_is_modified;
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
    return new MfFixedBuyingDrawAccountPK(institution_code, branch_code, account_code);
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
   * <em>fin_institution_div</em>カラムの値を設定します。 
   *
   * @@param p_finInstitutionDiv <em>fin_institution_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFinInstitutionDiv( String p_finInstitutionDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_institution_div = p_finInstitutionDiv;
    fin_institution_div_is_set = true;
    fin_institution_div_is_modified = true;
  }


  /** 
   * <em>fin_institution_code</em>カラムの値を設定します。 
   *
   * @@param p_finInstitutionCode <em>fin_institution_code</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setFinInstitutionCode( String p_finInstitutionCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_institution_code = p_finInstitutionCode;
    fin_institution_code_is_set = true;
    fin_institution_code_is_modified = true;
  }


  /** 
   * <em>fin_branch_code</em>カラムの値を設定します。 
   *
   * @@param p_finBranchCode <em>fin_branch_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setFinBranchCode( String p_finBranchCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_branch_code = p_finBranchCode;
    fin_branch_code_is_set = true;
    fin_branch_code_is_modified = true;
  }


  /** 
   * <em>deposit_div</em>カラムの値を設定します。 
   *
   * @@param p_depositDiv <em>deposit_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDepositDiv( String p_depositDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    deposit_div = p_depositDiv;
    deposit_div_is_set = true;
    deposit_div_is_modified = true;
  }


  /** 
   * <em>draw_account_no</em>カラムの値を設定します。 
   *
   * @@param p_drawAccountNo <em>draw_account_no</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setDrawAccountNo( String p_drawAccountNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    draw_account_no = p_drawAccountNo;
    draw_account_no_is_set = true;
    draw_account_no_is_modified = true;
  }


  /** 
   * <em>draw_account_name_kana</em>カラムの値を設定します。 
   *
   * @@param p_drawAccountNameKana <em>draw_account_name_kana</em>カラムの値をあらわす30桁以下のString値 
   */
  public final void setDrawAccountNameKana( String p_drawAccountNameKana )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    draw_account_name_kana = p_drawAccountNameKana;
    draw_account_name_kana_is_set = true;
    draw_account_name_kana_is_modified = true;
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
                if ( name.equals("deposit_div") ) {
                    return this.deposit_div;
                }
                else if ( name.equals("draw_account_no") ) {
                    return this.draw_account_no;
                }
                else if ( name.equals("draw_account_name_kana") ) {
                    return this.draw_account_name_kana;
                }
                break;
            case 'f':
                if ( name.equals("fin_institution_div") ) {
                    return this.fin_institution_div;
                }
                else if ( name.equals("fin_institution_code") ) {
                    return this.fin_institution_code;
                }
                else if ( name.equals("fin_branch_code") ) {
                    return this.fin_branch_code;
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
                if ( name.equals("deposit_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'deposit_div' must be String: '"+value+"' is not." );
                    this.deposit_div = (String) value;
                    if (this.deposit_div_is_set)
                        this.deposit_div_is_modified = true;
                    this.deposit_div_is_set = true;
                    return;
                }
                else if ( name.equals("draw_account_no") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'draw_account_no' must be String: '"+value+"' is not." );
                    this.draw_account_no = (String) value;
                    if (this.draw_account_no_is_set)
                        this.draw_account_no_is_modified = true;
                    this.draw_account_no_is_set = true;
                    return;
                }
                else if ( name.equals("draw_account_name_kana") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'draw_account_name_kana' must be String: '"+value+"' is not." );
                    this.draw_account_name_kana = (String) value;
                    if (this.draw_account_name_kana_is_set)
                        this.draw_account_name_kana_is_modified = true;
                    this.draw_account_name_kana_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("fin_institution_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fin_institution_div' must be String: '"+value+"' is not." );
                    this.fin_institution_div = (String) value;
                    if (this.fin_institution_div_is_set)
                        this.fin_institution_div_is_modified = true;
                    this.fin_institution_div_is_set = true;
                    return;
                }
                else if ( name.equals("fin_institution_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fin_institution_code' must be String: '"+value+"' is not." );
                    this.fin_institution_code = (String) value;
                    if (this.fin_institution_code_is_set)
                        this.fin_institution_code_is_modified = true;
                    this.fin_institution_code_is_set = true;
                    return;
                }
                else if ( name.equals("fin_branch_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fin_branch_code' must be String: '"+value+"' is not." );
                    this.fin_branch_code = (String) value;
                    if (this.fin_branch_code_is_set)
                        this.fin_branch_code_is_modified = true;
                    this.fin_branch_code_is_set = true;
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
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
