head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.55.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	CompFxConditionParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * comp_fx_conditionテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link CompFxConditionRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link CompFxConditionParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link CompFxConditionParams}が{@@link CompFxConditionRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see CompFxConditionPK 
 * @@see CompFxConditionRow 
 */
public class CompFxConditionParams extends Params implements CompFxConditionRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "comp_fx_condition";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = CompFxConditionRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return CompFxConditionRow.TYPE;
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
   * <em>fx_system_code</em>カラムの値 
   */
  public  String  fx_system_code;

  /** 
   * <em>group_name</em>カラムの値 
   */
  public  String  group_name;

  /** 
   * <em>url</em>カラムの値 
   */
  public  String  url;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>fx_head_of_login_id</em>カラムの値 
   */
  public  String  fx_head_of_login_id;

  /** 
   * <em>single_sign_on_url</em>カラムの値 
   */
  public  String  single_sign_on_url;

  /** 
   * <em>valid_term</em>カラムの値 
   */
  public  String  valid_term;

  /** 
   * <em>fx_system_div</em>カラムの値 
   */
  public  String  fx_system_div;

  /** 
   * <em>ext_connect_system_code</em>カラムの値 
   */
  public  String  ext_connect_system_code;

  /** 
   * <em>trading_time_type</em>カラムの値 
   */
  public  String  trading_time_type;

  /** 
   * <em>online_acc_open</em>カラムの値 
   */
  public  String  online_acc_open;

  /** 
   * <em>soap_connect_div</em>カラムの値 
   */
  public  String  soap_connect_div;

  /** 
   * <em>acc_type</em>カラムの値 
   */
  public  String  acc_type;

  /** 
   * <em>mrf_allow_div</em>カラムの値 
   */
  public  String  mrf_allow_div;

  /** 
   * <em>acc_open_real_update</em>カラムの値 
   */
  public  String  acc_open_real_update;

  /** 
   * <em>question_check_div</em>カラムの値 
   */
  public  String  question_check_div;

  /** 
   * <em>fx_system_id</em>カラムの値 
   */
  public  long  fx_system_id;

  /** 
   * <em>get_transferable_amt_div</em>カラムの値 
   */
  public  String  get_transferable_amt_div;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean fx_system_code_is_set = false;
  private boolean fx_system_code_is_modified = false;
  private boolean group_name_is_set = false;
  private boolean group_name_is_modified = false;
  private boolean url_is_set = false;
  private boolean url_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean fx_head_of_login_id_is_set = false;
  private boolean fx_head_of_login_id_is_modified = false;
  private boolean single_sign_on_url_is_set = false;
  private boolean single_sign_on_url_is_modified = false;
  private boolean valid_term_is_set = false;
  private boolean valid_term_is_modified = false;
  private boolean fx_system_div_is_set = false;
  private boolean fx_system_div_is_modified = false;
  private boolean ext_connect_system_code_is_set = false;
  private boolean ext_connect_system_code_is_modified = false;
  private boolean trading_time_type_is_set = false;
  private boolean trading_time_type_is_modified = false;
  private boolean online_acc_open_is_set = false;
  private boolean online_acc_open_is_modified = false;
  private boolean soap_connect_div_is_set = false;
  private boolean soap_connect_div_is_modified = false;
  private boolean acc_type_is_set = false;
  private boolean acc_type_is_modified = false;
  private boolean mrf_allow_div_is_set = false;
  private boolean mrf_allow_div_is_modified = false;
  private boolean acc_open_real_update_is_set = false;
  private boolean acc_open_real_update_is_modified = false;
  private boolean question_check_div_is_set = false;
  private boolean question_check_div_is_modified = false;
  private boolean fx_system_id_is_set = false;
  private boolean fx_system_id_is_modified = false;
  private boolean get_transferable_amt_div_is_set = false;
  private boolean get_transferable_amt_div_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "branch_code=" + branch_code
      + "," + "fx_system_code=" + fx_system_code
      + "," + "group_name=" +group_name
      + "," + "url=" +url
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "fx_head_of_login_id=" +fx_head_of_login_id
      + "," + "single_sign_on_url=" +single_sign_on_url
      + "," + "valid_term=" +valid_term
      + "," + "fx_system_div=" +fx_system_div
      + "," + "ext_connect_system_code=" +ext_connect_system_code
      + "," + "trading_time_type=" +trading_time_type
      + "," + "online_acc_open=" +online_acc_open
      + "," + "soap_connect_div=" +soap_connect_div
      + "," + "acc_type=" +acc_type
      + "," + "mrf_allow_div=" +mrf_allow_div
      + "," + "acc_open_real_update=" +acc_open_real_update
      + "," + "question_check_div=" +question_check_div
      + "," + "fx_system_id=" +fx_system_id
      + "," + "get_transferable_amt_div=" +get_transferable_amt_div
      + "}";
  }


  /** 
   * 値が未設定のCompFxConditionParamsオブジェクトを作成します。 
   */
  public CompFxConditionParams() {
    institution_code_is_modified = true;
    branch_code_is_modified = true;
    fx_system_code_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のCompFxConditionRowオブジェクトの値を利用してCompFxConditionParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するCompFxConditionRowオブジェクト 
   */
  public CompFxConditionParams( CompFxConditionRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    fx_system_code = p_row.getFxSystemCode();
    fx_system_code_is_set = p_row.getFxSystemCodeIsSet();
    fx_system_code_is_modified = p_row.getFxSystemCodeIsModified();
    group_name = p_row.getGroupName();
    group_name_is_set = p_row.getGroupNameIsSet();
    group_name_is_modified = p_row.getGroupNameIsModified();
    url = p_row.getUrl();
    url_is_set = p_row.getUrlIsSet();
    url_is_modified = p_row.getUrlIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    fx_head_of_login_id = p_row.getFxHeadOfLoginId();
    fx_head_of_login_id_is_set = p_row.getFxHeadOfLoginIdIsSet();
    fx_head_of_login_id_is_modified = p_row.getFxHeadOfLoginIdIsModified();
    single_sign_on_url = p_row.getSingleSignOnUrl();
    single_sign_on_url_is_set = p_row.getSingleSignOnUrlIsSet();
    single_sign_on_url_is_modified = p_row.getSingleSignOnUrlIsModified();
    valid_term = p_row.getValidTerm();
    valid_term_is_set = p_row.getValidTermIsSet();
    valid_term_is_modified = p_row.getValidTermIsModified();
    fx_system_div = p_row.getFxSystemDiv();
    fx_system_div_is_set = p_row.getFxSystemDivIsSet();
    fx_system_div_is_modified = p_row.getFxSystemDivIsModified();
    ext_connect_system_code = p_row.getExtConnectSystemCode();
    ext_connect_system_code_is_set = p_row.getExtConnectSystemCodeIsSet();
    ext_connect_system_code_is_modified = p_row.getExtConnectSystemCodeIsModified();
    trading_time_type = p_row.getTradingTimeType();
    trading_time_type_is_set = p_row.getTradingTimeTypeIsSet();
    trading_time_type_is_modified = p_row.getTradingTimeTypeIsModified();
    online_acc_open = p_row.getOnlineAccOpen();
    online_acc_open_is_set = p_row.getOnlineAccOpenIsSet();
    online_acc_open_is_modified = p_row.getOnlineAccOpenIsModified();
    soap_connect_div = p_row.getSoapConnectDiv();
    soap_connect_div_is_set = p_row.getSoapConnectDivIsSet();
    soap_connect_div_is_modified = p_row.getSoapConnectDivIsModified();
    acc_type = p_row.getAccType();
    acc_type_is_set = p_row.getAccTypeIsSet();
    acc_type_is_modified = p_row.getAccTypeIsModified();
    mrf_allow_div = p_row.getMrfAllowDiv();
    mrf_allow_div_is_set = p_row.getMrfAllowDivIsSet();
    mrf_allow_div_is_modified = p_row.getMrfAllowDivIsModified();
    acc_open_real_update = p_row.getAccOpenRealUpdate();
    acc_open_real_update_is_set = p_row.getAccOpenRealUpdateIsSet();
    acc_open_real_update_is_modified = p_row.getAccOpenRealUpdateIsModified();
    question_check_div = p_row.getQuestionCheckDiv();
    question_check_div_is_set = p_row.getQuestionCheckDivIsSet();
    question_check_div_is_modified = p_row.getQuestionCheckDivIsModified();
    fx_system_id = p_row.getFxSystemId();
    fx_system_id_is_set = p_row.getFxSystemIdIsSet();
    fx_system_id_is_modified = p_row.getFxSystemIdIsModified();
    get_transferable_amt_div = p_row.getGetTransferableAmtDiv();
    get_transferable_amt_div_is_set = p_row.getGetTransferableAmtDivIsSet();
    get_transferable_amt_div_is_modified = p_row.getGetTransferableAmtDivIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      group_name_is_set = true;
      group_name_is_modified = true;
      url_is_set = true;
      url_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      fx_head_of_login_id_is_set = true;
      fx_head_of_login_id_is_modified = true;
      single_sign_on_url_is_set = true;
      single_sign_on_url_is_modified = true;
      valid_term_is_set = true;
      valid_term_is_modified = true;
      fx_system_div_is_set = true;
      fx_system_div_is_modified = true;
      ext_connect_system_code_is_set = true;
      ext_connect_system_code_is_modified = true;
      trading_time_type_is_set = true;
      trading_time_type_is_modified = true;
      online_acc_open_is_set = true;
      online_acc_open_is_modified = true;
      soap_connect_div_is_set = true;
      soap_connect_div_is_modified = true;
      acc_type_is_set = true;
      acc_type_is_modified = true;
      mrf_allow_div_is_set = true;
      mrf_allow_div_is_modified = true;
      acc_open_real_update_is_set = true;
      acc_open_real_update_is_modified = true;
      question_check_div_is_set = true;
      question_check_div_is_modified = true;
      fx_system_id_is_set = true;
      fx_system_id_is_modified = true;
      get_transferable_amt_div_is_set = true;
      get_transferable_amt_div_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof CompFxConditionRow ) )
       return false;
    return fieldsEqual( (CompFxConditionRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のCompFxConditionRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( CompFxConditionRow row )
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
    if ( fx_system_code == null ) {
      if ( row.getFxSystemCode() != null )
        return false;
    } else if ( !fx_system_code.equals( row.getFxSystemCode() ) ) {
        return false;
    }
    if ( group_name == null ) {
      if ( row.getGroupName() != null )
        return false;
    } else if ( !group_name.equals( row.getGroupName() ) ) {
        return false;
    }
    if ( url == null ) {
      if ( row.getUrl() != null )
        return false;
    } else if ( !url.equals( row.getUrl() ) ) {
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
    if ( fx_head_of_login_id == null ) {
      if ( row.getFxHeadOfLoginId() != null )
        return false;
    } else if ( !fx_head_of_login_id.equals( row.getFxHeadOfLoginId() ) ) {
        return false;
    }
    if ( single_sign_on_url == null ) {
      if ( row.getSingleSignOnUrl() != null )
        return false;
    } else if ( !single_sign_on_url.equals( row.getSingleSignOnUrl() ) ) {
        return false;
    }
    if ( valid_term == null ) {
      if ( row.getValidTerm() != null )
        return false;
    } else if ( !valid_term.equals( row.getValidTerm() ) ) {
        return false;
    }
    if ( fx_system_div == null ) {
      if ( row.getFxSystemDiv() != null )
        return false;
    } else if ( !fx_system_div.equals( row.getFxSystemDiv() ) ) {
        return false;
    }
    if ( ext_connect_system_code == null ) {
      if ( row.getExtConnectSystemCode() != null )
        return false;
    } else if ( !ext_connect_system_code.equals( row.getExtConnectSystemCode() ) ) {
        return false;
    }
    if ( trading_time_type == null ) {
      if ( row.getTradingTimeType() != null )
        return false;
    } else if ( !trading_time_type.equals( row.getTradingTimeType() ) ) {
        return false;
    }
    if ( online_acc_open == null ) {
      if ( row.getOnlineAccOpen() != null )
        return false;
    } else if ( !online_acc_open.equals( row.getOnlineAccOpen() ) ) {
        return false;
    }
    if ( soap_connect_div == null ) {
      if ( row.getSoapConnectDiv() != null )
        return false;
    } else if ( !soap_connect_div.equals( row.getSoapConnectDiv() ) ) {
        return false;
    }
    if ( acc_type == null ) {
      if ( row.getAccType() != null )
        return false;
    } else if ( !acc_type.equals( row.getAccType() ) ) {
        return false;
    }
    if ( mrf_allow_div == null ) {
      if ( row.getMrfAllowDiv() != null )
        return false;
    } else if ( !mrf_allow_div.equals( row.getMrfAllowDiv() ) ) {
        return false;
    }
    if ( acc_open_real_update == null ) {
      if ( row.getAccOpenRealUpdate() != null )
        return false;
    } else if ( !acc_open_real_update.equals( row.getAccOpenRealUpdate() ) ) {
        return false;
    }
    if ( question_check_div == null ) {
      if ( row.getQuestionCheckDiv() != null )
        return false;
    } else if ( !question_check_div.equals( row.getQuestionCheckDiv() ) ) {
        return false;
    }
    if ( fx_system_id != row.getFxSystemId() )
      return false;
    if ( get_transferable_amt_div == null ) {
      if ( row.getGetTransferableAmtDiv() != null )
        return false;
    } else if ( !get_transferable_amt_div.equals( row.getGetTransferableAmtDiv() ) ) {
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
        + (fx_system_code!=null? fx_system_code.hashCode(): 0) 
        + (group_name!=null? group_name.hashCode(): 0) 
        + (url!=null? url.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (fx_head_of_login_id!=null? fx_head_of_login_id.hashCode(): 0) 
        + (single_sign_on_url!=null? single_sign_on_url.hashCode(): 0) 
        + (valid_term!=null? valid_term.hashCode(): 0) 
        + (fx_system_div!=null? fx_system_div.hashCode(): 0) 
        + (ext_connect_system_code!=null? ext_connect_system_code.hashCode(): 0) 
        + (trading_time_type!=null? trading_time_type.hashCode(): 0) 
        + (online_acc_open!=null? online_acc_open.hashCode(): 0) 
        + (soap_connect_div!=null? soap_connect_div.hashCode(): 0) 
        + (acc_type!=null? acc_type.hashCode(): 0) 
        + (mrf_allow_div!=null? mrf_allow_div.hashCode(): 0) 
        + (acc_open_real_update!=null? acc_open_real_update.hashCode(): 0) 
        + (question_check_div!=null? question_check_div.hashCode(): 0) 
        + ((int) fx_system_id)
        + (get_transferable_amt_div!=null? get_transferable_amt_div.hashCode(): 0) 
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
		if ( !fx_head_of_login_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'fx_head_of_login_id' must be set before inserting.");
		if ( !trading_time_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'trading_time_type' must be set before inserting.");
		if ( !online_acc_open_is_set )
			throw new IllegalArgumentException("non-nullable field 'online_acc_open' must be set before inserting.");
		if ( !soap_connect_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'soap_connect_div' must be set before inserting.");
		if ( !acc_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'acc_type' must be set before inserting.");
		if ( !mrf_allow_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'mrf_allow_div' must be set before inserting.");
		if ( !acc_open_real_update_is_set )
			throw new IllegalArgumentException("non-nullable field 'acc_open_real_update' must be set before inserting.");
		if ( !question_check_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'question_check_div' must be set before inserting.");
		if ( !fx_system_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'fx_system_id' must be set before inserting.");
		if ( !get_transferable_amt_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'get_transferable_amt_div' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("fx_system_code",fx_system_code);
		if ( group_name != null )
			map.put("group_name",group_name);
		if ( url != null )
			map.put("url",url);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		map.put("fx_head_of_login_id",fx_head_of_login_id);
		if ( single_sign_on_url != null )
			map.put("single_sign_on_url",single_sign_on_url);
		if ( valid_term != null )
			map.put("valid_term",valid_term);
		if ( fx_system_div != null )
			map.put("fx_system_div",fx_system_div);
		if ( ext_connect_system_code != null )
			map.put("ext_connect_system_code",ext_connect_system_code);
		map.put("trading_time_type",trading_time_type);
		map.put("online_acc_open",online_acc_open);
		map.put("soap_connect_div",soap_connect_div);
		map.put("acc_type",acc_type);
		map.put("mrf_allow_div",mrf_allow_div);
		map.put("acc_open_real_update",acc_open_real_update);
		map.put("question_check_div",question_check_div);
		map.put("fx_system_id",new Long(fx_system_id));
		map.put("get_transferable_amt_div",get_transferable_amt_div);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( group_name_is_modified )
			map.put("group_name",group_name);
		if ( url_is_modified )
			map.put("url",url);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( fx_head_of_login_id_is_modified )
			map.put("fx_head_of_login_id",fx_head_of_login_id);
		if ( single_sign_on_url_is_modified )
			map.put("single_sign_on_url",single_sign_on_url);
		if ( valid_term_is_modified )
			map.put("valid_term",valid_term);
		if ( fx_system_div_is_modified )
			map.put("fx_system_div",fx_system_div);
		if ( ext_connect_system_code_is_modified )
			map.put("ext_connect_system_code",ext_connect_system_code);
		if ( trading_time_type_is_modified )
			map.put("trading_time_type",trading_time_type);
		if ( online_acc_open_is_modified )
			map.put("online_acc_open",online_acc_open);
		if ( soap_connect_div_is_modified )
			map.put("soap_connect_div",soap_connect_div);
		if ( acc_type_is_modified )
			map.put("acc_type",acc_type);
		if ( mrf_allow_div_is_modified )
			map.put("mrf_allow_div",mrf_allow_div);
		if ( acc_open_real_update_is_modified )
			map.put("acc_open_real_update",acc_open_real_update);
		if ( question_check_div_is_modified )
			map.put("question_check_div",question_check_div);
		if ( fx_system_id_is_modified )
			map.put("fx_system_id",new Long(fx_system_id));
		if ( get_transferable_amt_div_is_modified )
			map.put("get_transferable_amt_div",get_transferable_amt_div);
		if (map.size() == 0) {
			map.put("group_name",group_name);
			map.put("url",url);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			if ( fx_head_of_login_id_is_set )
				map.put("fx_head_of_login_id",fx_head_of_login_id);
			map.put("single_sign_on_url",single_sign_on_url);
			map.put("valid_term",valid_term);
			map.put("fx_system_div",fx_system_div);
			map.put("ext_connect_system_code",ext_connect_system_code);
			if ( trading_time_type_is_set )
				map.put("trading_time_type",trading_time_type);
			if ( online_acc_open_is_set )
				map.put("online_acc_open",online_acc_open);
			if ( soap_connect_div_is_set )
				map.put("soap_connect_div",soap_connect_div);
			if ( acc_type_is_set )
				map.put("acc_type",acc_type);
			if ( mrf_allow_div_is_set )
				map.put("mrf_allow_div",mrf_allow_div);
			if ( acc_open_real_update_is_set )
				map.put("acc_open_real_update",acc_open_real_update);
			if ( question_check_div_is_set )
				map.put("question_check_div",question_check_div);
			if ( fx_system_id_is_set )
				map.put("fx_system_id",new Long(fx_system_id));
			if ( get_transferable_amt_div_is_set )
				map.put("get_transferable_amt_div",get_transferable_amt_div);
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
   * <em>fx_system_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFxSystemCode()
  {
    return fx_system_code;
  }


  /** 
   * <em>fx_system_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFxSystemCodeIsSet() {
    return fx_system_code_is_set;
  }


  /** 
   * <em>fx_system_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFxSystemCodeIsModified() {
    return fx_system_code_is_modified;
  }


  /** 
   * <em>group_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGroupName()
  {
    return group_name;
  }


  /** 
   * <em>group_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGroupNameIsSet() {
    return group_name_is_set;
  }


  /** 
   * <em>group_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGroupNameIsModified() {
    return group_name_is_modified;
  }


  /** 
   * <em>url</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getUrl()
  {
    return url;
  }


  /** 
   * <em>url</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUrlIsSet() {
    return url_is_set;
  }


  /** 
   * <em>url</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUrlIsModified() {
    return url_is_modified;
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
   * <em>fx_head_of_login_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFxHeadOfLoginId()
  {
    return fx_head_of_login_id;
  }


  /** 
   * <em>fx_head_of_login_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFxHeadOfLoginIdIsSet() {
    return fx_head_of_login_id_is_set;
  }


  /** 
   * <em>fx_head_of_login_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFxHeadOfLoginIdIsModified() {
    return fx_head_of_login_id_is_modified;
  }


  /** 
   * <em>single_sign_on_url</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSingleSignOnUrl()
  {
    return single_sign_on_url;
  }


  /** 
   * <em>single_sign_on_url</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSingleSignOnUrlIsSet() {
    return single_sign_on_url_is_set;
  }


  /** 
   * <em>single_sign_on_url</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSingleSignOnUrlIsModified() {
    return single_sign_on_url_is_modified;
  }


  /** 
   * <em>valid_term</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getValidTerm()
  {
    return valid_term;
  }


  /** 
   * <em>valid_term</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getValidTermIsSet() {
    return valid_term_is_set;
  }


  /** 
   * <em>valid_term</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getValidTermIsModified() {
    return valid_term_is_modified;
  }


  /** 
   * <em>fx_system_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFxSystemDiv()
  {
    return fx_system_div;
  }


  /** 
   * <em>fx_system_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFxSystemDivIsSet() {
    return fx_system_div_is_set;
  }


  /** 
   * <em>fx_system_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFxSystemDivIsModified() {
    return fx_system_div_is_modified;
  }


  /** 
   * <em>ext_connect_system_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtConnectSystemCode()
  {
    return ext_connect_system_code;
  }


  /** 
   * <em>ext_connect_system_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtConnectSystemCodeIsSet() {
    return ext_connect_system_code_is_set;
  }


  /** 
   * <em>ext_connect_system_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtConnectSystemCodeIsModified() {
    return ext_connect_system_code_is_modified;
  }


  /** 
   * <em>trading_time_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTradingTimeType()
  {
    return trading_time_type;
  }


  /** 
   * <em>trading_time_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradingTimeTypeIsSet() {
    return trading_time_type_is_set;
  }


  /** 
   * <em>trading_time_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradingTimeTypeIsModified() {
    return trading_time_type_is_modified;
  }


  /** 
   * <em>online_acc_open</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOnlineAccOpen()
  {
    return online_acc_open;
  }


  /** 
   * <em>online_acc_open</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOnlineAccOpenIsSet() {
    return online_acc_open_is_set;
  }


  /** 
   * <em>online_acc_open</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOnlineAccOpenIsModified() {
    return online_acc_open_is_modified;
  }


  /** 
   * <em>soap_connect_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSoapConnectDiv()
  {
    return soap_connect_div;
  }


  /** 
   * <em>soap_connect_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSoapConnectDivIsSet() {
    return soap_connect_div_is_set;
  }


  /** 
   * <em>soap_connect_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSoapConnectDivIsModified() {
    return soap_connect_div_is_modified;
  }


  /** 
   * <em>acc_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccType()
  {
    return acc_type;
  }


  /** 
   * <em>acc_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccTypeIsSet() {
    return acc_type_is_set;
  }


  /** 
   * <em>acc_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccTypeIsModified() {
    return acc_type_is_modified;
  }


  /** 
   * <em>mrf_allow_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMrfAllowDiv()
  {
    return mrf_allow_div;
  }


  /** 
   * <em>mrf_allow_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMrfAllowDivIsSet() {
    return mrf_allow_div_is_set;
  }


  /** 
   * <em>mrf_allow_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMrfAllowDivIsModified() {
    return mrf_allow_div_is_modified;
  }


  /** 
   * <em>acc_open_real_update</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccOpenRealUpdate()
  {
    return acc_open_real_update;
  }


  /** 
   * <em>acc_open_real_update</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccOpenRealUpdateIsSet() {
    return acc_open_real_update_is_set;
  }


  /** 
   * <em>acc_open_real_update</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccOpenRealUpdateIsModified() {
    return acc_open_real_update_is_modified;
  }


  /** 
   * <em>question_check_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getQuestionCheckDiv()
  {
    return question_check_div;
  }


  /** 
   * <em>question_check_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getQuestionCheckDivIsSet() {
    return question_check_div_is_set;
  }


  /** 
   * <em>question_check_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getQuestionCheckDivIsModified() {
    return question_check_div_is_modified;
  }


  /** 
   * <em>fx_system_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getFxSystemId()
  {
    return fx_system_id;
  }


  /** 
   * <em>fx_system_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFxSystemIdIsSet() {
    return fx_system_id_is_set;
  }


  /** 
   * <em>fx_system_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFxSystemIdIsModified() {
    return fx_system_id_is_modified;
  }


  /** 
   * <em>get_transferable_amt_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGetTransferableAmtDiv()
  {
    return get_transferable_amt_div;
  }


  /** 
   * <em>get_transferable_amt_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGetTransferableAmtDivIsSet() {
    return get_transferable_amt_div_is_set;
  }


  /** 
   * <em>get_transferable_amt_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGetTransferableAmtDivIsModified() {
    return get_transferable_amt_div_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new CompFxConditionPK(institution_code, branch_code, fx_system_code);
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
   * <em>fx_system_code</em>カラムの値を設定します。 
   *
   * @@param p_fxSystemCode <em>fx_system_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setFxSystemCode( String p_fxSystemCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fx_system_code = p_fxSystemCode;
    fx_system_code_is_set = true;
    fx_system_code_is_modified = true;
  }


  /** 
   * <em>group_name</em>カラムの値を設定します。 
   *
   * @@param p_groupName <em>group_name</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setGroupName( String p_groupName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    group_name = p_groupName;
    group_name_is_set = true;
    group_name_is_modified = true;
  }


  /** 
   * <em>url</em>カラムの値を設定します。 
   *
   * @@param p_url <em>url</em>カラムの値をあらわす200桁以下のString値 
   */
  public final void setUrl( String p_url )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    url = p_url;
    url_is_set = true;
    url_is_modified = true;
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
   * <em>fx_head_of_login_id</em>カラムの値を設定します。 
   *
   * @@param p_fxHeadOfLoginId <em>fx_head_of_login_id</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setFxHeadOfLoginId( String p_fxHeadOfLoginId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fx_head_of_login_id = p_fxHeadOfLoginId;
    fx_head_of_login_id_is_set = true;
    fx_head_of_login_id_is_modified = true;
  }


  /** 
   * <em>single_sign_on_url</em>カラムの値を設定します。 
   *
   * @@param p_singleSignOnUrl <em>single_sign_on_url</em>カラムの値をあらわす200桁以下のString値 
   */
  public final void setSingleSignOnUrl( String p_singleSignOnUrl )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    single_sign_on_url = p_singleSignOnUrl;
    single_sign_on_url_is_set = true;
    single_sign_on_url_is_modified = true;
  }


  /** 
   * <em>valid_term</em>カラムの値を設定します。 
   *
   * @@param p_validTerm <em>valid_term</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setValidTerm( String p_validTerm )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    valid_term = p_validTerm;
    valid_term_is_set = true;
    valid_term_is_modified = true;
  }


  /** 
   * <em>fx_system_div</em>カラムの値を設定します。 
   *
   * @@param p_fxSystemDiv <em>fx_system_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFxSystemDiv( String p_fxSystemDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fx_system_div = p_fxSystemDiv;
    fx_system_div_is_set = true;
    fx_system_div_is_modified = true;
  }


  /** 
   * <em>ext_connect_system_code</em>カラムの値を設定します。 
   *
   * @@param p_extConnectSystemCode <em>ext_connect_system_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExtConnectSystemCode( String p_extConnectSystemCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_connect_system_code = p_extConnectSystemCode;
    ext_connect_system_code_is_set = true;
    ext_connect_system_code_is_modified = true;
  }


  /** 
   * <em>trading_time_type</em>カラムの値を設定します。 
   *
   * @@param p_tradingTimeType <em>trading_time_type</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setTradingTimeType( String p_tradingTimeType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trading_time_type = p_tradingTimeType;
    trading_time_type_is_set = true;
    trading_time_type_is_modified = true;
  }


  /** 
   * <em>online_acc_open</em>カラムの値を設定します。 
   *
   * @@param p_onlineAccOpen <em>online_acc_open</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setOnlineAccOpen( String p_onlineAccOpen )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    online_acc_open = p_onlineAccOpen;
    online_acc_open_is_set = true;
    online_acc_open_is_modified = true;
  }


  /** 
   * <em>soap_connect_div</em>カラムの値を設定します。 
   *
   * @@param p_soapConnectDiv <em>soap_connect_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setSoapConnectDiv( String p_soapConnectDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    soap_connect_div = p_soapConnectDiv;
    soap_connect_div_is_set = true;
    soap_connect_div_is_modified = true;
  }


  /** 
   * <em>acc_type</em>カラムの値を設定します。 
   *
   * @@param p_accType <em>acc_type</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setAccType( String p_accType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    acc_type = p_accType;
    acc_type_is_set = true;
    acc_type_is_modified = true;
  }


  /** 
   * <em>mrf_allow_div</em>カラムの値を設定します。 
   *
   * @@param p_mrfAllowDiv <em>mrf_allow_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setMrfAllowDiv( String p_mrfAllowDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mrf_allow_div = p_mrfAllowDiv;
    mrf_allow_div_is_set = true;
    mrf_allow_div_is_modified = true;
  }


  /** 
   * <em>acc_open_real_update</em>カラムの値を設定します。 
   *
   * @@param p_accOpenRealUpdate <em>acc_open_real_update</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setAccOpenRealUpdate( String p_accOpenRealUpdate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    acc_open_real_update = p_accOpenRealUpdate;
    acc_open_real_update_is_set = true;
    acc_open_real_update_is_modified = true;
  }


  /** 
   * <em>question_check_div</em>カラムの値を設定します。 
   *
   * @@param p_questionCheckDiv <em>question_check_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setQuestionCheckDiv( String p_questionCheckDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    question_check_div = p_questionCheckDiv;
    question_check_div_is_set = true;
    question_check_div_is_modified = true;
  }


  /** 
   * <em>fx_system_id</em>カラムの値を設定します。 
   *
   * @@param p_fxSystemId <em>fx_system_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setFxSystemId( long p_fxSystemId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fx_system_id = p_fxSystemId;
    fx_system_id_is_set = true;
    fx_system_id_is_modified = true;
  }


  /** 
   * <em>get_transferable_amt_div</em>カラムの値を設定します。 
   *
   * @@param p_getTransferableAmtDiv <em>get_transferable_amt_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setGetTransferableAmtDiv( String p_getTransferableAmtDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    get_transferable_amt_div = p_getTransferableAmtDiv;
    get_transferable_amt_div_is_set = true;
    get_transferable_amt_div_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("acc_type") ) {
                    return this.acc_type;
                }
                else if ( name.equals("acc_open_real_update") ) {
                    return this.acc_open_real_update;
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
            case 'e':
                if ( name.equals("ext_connect_system_code") ) {
                    return this.ext_connect_system_code;
                }
                break;
            case 'f':
                if ( name.equals("fx_system_code") ) {
                    return this.fx_system_code;
                }
                else if ( name.equals("fx_head_of_login_id") ) {
                    return this.fx_head_of_login_id;
                }
                else if ( name.equals("fx_system_div") ) {
                    return this.fx_system_div;
                }
                else if ( name.equals("fx_system_id") ) {
                    return new Long( fx_system_id );
                }
                break;
            case 'g':
                if ( name.equals("group_name") ) {
                    return this.group_name;
                }
                else if ( name.equals("get_transferable_amt_div") ) {
                    return this.get_transferable_amt_div;
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
            case 'm':
                if ( name.equals("mrf_allow_div") ) {
                    return this.mrf_allow_div;
                }
                break;
            case 'o':
                if ( name.equals("online_acc_open") ) {
                    return this.online_acc_open;
                }
                break;
            case 'q':
                if ( name.equals("question_check_div") ) {
                    return this.question_check_div;
                }
                break;
            case 's':
                if ( name.equals("single_sign_on_url") ) {
                    return this.single_sign_on_url;
                }
                else if ( name.equals("soap_connect_div") ) {
                    return this.soap_connect_div;
                }
                break;
            case 't':
                if ( name.equals("trading_time_type") ) {
                    return this.trading_time_type;
                }
                break;
            case 'u':
                if ( name.equals("url") ) {
                    return this.url;
                }
                break;
            case 'v':
                if ( name.equals("valid_term") ) {
                    return this.valid_term;
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
                if ( name.equals("acc_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'acc_type' must be String: '"+value+"' is not." );
                    this.acc_type = (String) value;
                    if (this.acc_type_is_set)
                        this.acc_type_is_modified = true;
                    this.acc_type_is_set = true;
                    return;
                }
                else if ( name.equals("acc_open_real_update") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'acc_open_real_update' must be String: '"+value+"' is not." );
                    this.acc_open_real_update = (String) value;
                    if (this.acc_open_real_update_is_set)
                        this.acc_open_real_update_is_modified = true;
                    this.acc_open_real_update_is_set = true;
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
            case 'e':
                if ( name.equals("ext_connect_system_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_connect_system_code' must be String: '"+value+"' is not." );
                    this.ext_connect_system_code = (String) value;
                    if (this.ext_connect_system_code_is_set)
                        this.ext_connect_system_code_is_modified = true;
                    this.ext_connect_system_code_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("fx_system_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fx_system_code' must be String: '"+value+"' is not." );
                    this.fx_system_code = (String) value;
                    if (this.fx_system_code_is_set)
                        this.fx_system_code_is_modified = true;
                    this.fx_system_code_is_set = true;
                    return;
                }
                else if ( name.equals("fx_head_of_login_id") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fx_head_of_login_id' must be String: '"+value+"' is not." );
                    this.fx_head_of_login_id = (String) value;
                    if (this.fx_head_of_login_id_is_set)
                        this.fx_head_of_login_id_is_modified = true;
                    this.fx_head_of_login_id_is_set = true;
                    return;
                }
                else if ( name.equals("fx_system_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fx_system_div' must be String: '"+value+"' is not." );
                    this.fx_system_div = (String) value;
                    if (this.fx_system_div_is_set)
                        this.fx_system_div_is_modified = true;
                    this.fx_system_div_is_set = true;
                    return;
                }
                else if ( name.equals("fx_system_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'fx_system_id' must be Long: '"+value+"' is not." );
                    this.fx_system_id = ((Long) value).longValue();
                    if (this.fx_system_id_is_set)
                        this.fx_system_id_is_modified = true;
                    this.fx_system_id_is_set = true;
                    return;
                }
                break;
            case 'g':
                if ( name.equals("group_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'group_name' must be String: '"+value+"' is not." );
                    this.group_name = (String) value;
                    if (this.group_name_is_set)
                        this.group_name_is_modified = true;
                    this.group_name_is_set = true;
                    return;
                }
                else if ( name.equals("get_transferable_amt_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'get_transferable_amt_div' must be String: '"+value+"' is not." );
                    this.get_transferable_amt_div = (String) value;
                    if (this.get_transferable_amt_div_is_set)
                        this.get_transferable_amt_div_is_modified = true;
                    this.get_transferable_amt_div_is_set = true;
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
            case 'm':
                if ( name.equals("mrf_allow_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mrf_allow_div' must be String: '"+value+"' is not." );
                    this.mrf_allow_div = (String) value;
                    if (this.mrf_allow_div_is_set)
                        this.mrf_allow_div_is_modified = true;
                    this.mrf_allow_div_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("online_acc_open") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'online_acc_open' must be String: '"+value+"' is not." );
                    this.online_acc_open = (String) value;
                    if (this.online_acc_open_is_set)
                        this.online_acc_open_is_modified = true;
                    this.online_acc_open_is_set = true;
                    return;
                }
                break;
            case 'q':
                if ( name.equals("question_check_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'question_check_div' must be String: '"+value+"' is not." );
                    this.question_check_div = (String) value;
                    if (this.question_check_div_is_set)
                        this.question_check_div_is_modified = true;
                    this.question_check_div_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("single_sign_on_url") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'single_sign_on_url' must be String: '"+value+"' is not." );
                    this.single_sign_on_url = (String) value;
                    if (this.single_sign_on_url_is_set)
                        this.single_sign_on_url_is_modified = true;
                    this.single_sign_on_url_is_set = true;
                    return;
                }
                else if ( name.equals("soap_connect_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'soap_connect_div' must be String: '"+value+"' is not." );
                    this.soap_connect_div = (String) value;
                    if (this.soap_connect_div_is_set)
                        this.soap_connect_div_is_modified = true;
                    this.soap_connect_div_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("trading_time_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trading_time_type' must be String: '"+value+"' is not." );
                    this.trading_time_type = (String) value;
                    if (this.trading_time_type_is_set)
                        this.trading_time_type_is_modified = true;
                    this.trading_time_type_is_set = true;
                    return;
                }
                break;
            case 'u':
                if ( name.equals("url") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'url' must be String: '"+value+"' is not." );
                    this.url = (String) value;
                    if (this.url_is_set)
                        this.url_is_modified = true;
                    this.url_is_set = true;
                    return;
                }
                break;
            case 'v':
                if ( name.equals("valid_term") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'valid_term' must be String: '"+value+"' is not." );
                    this.valid_term = (String) value;
                    if (this.valid_term_is_set)
                        this.valid_term_is_modified = true;
                    this.valid_term_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
