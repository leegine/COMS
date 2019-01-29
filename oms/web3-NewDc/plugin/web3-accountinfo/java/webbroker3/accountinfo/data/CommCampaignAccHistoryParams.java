head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.16.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	CommCampaignAccHistoryParams.java;


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
 * comm_campaign_acc_historyテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link CommCampaignAccHistoryRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link CommCampaignAccHistoryParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link CommCampaignAccHistoryParams}が{@@link CommCampaignAccHistoryRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see CommCampaignAccHistoryPK 
 * @@see CommCampaignAccHistoryRow 
 */
public class CommCampaignAccHistoryParams extends Params implements CommCampaignAccHistoryRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "comm_campaign_acc_history";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = CommCampaignAccHistoryRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return CommCampaignAccHistoryRow.TYPE;
  }


  /** 
   * <em>account_id</em>カラムの値 
   */
  public  long  account_id;

  /** 
   * <em>campaign_id</em>カラムの値 
   */
  public  long  campaign_id;

  /** 
   * <em>comm_campaign_name</em>カラムの値 
   */
  public  String  comm_campaign_name;

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
   * <em>family_name</em>カラムの値 
   */
  public  String  family_name;

  /** 
   * <em>sonar_trader_code</em>カラムの値 
   */
  public  String  sonar_trader_code;

  /** 
   * <em>acc_open_kind_div</em>カラムの値 
   */
  public  String  acc_open_kind_div;

  /** 
   * <em>account_charge_ratio</em>カラムの値 
   */
  public  double  account_charge_ratio;

  /** 
   * <em>appli_start_date</em>カラムの値 
   */
  public  java.sql.Timestamp  appli_start_date;

  /** 
   * <em>appli_end_date</em>カラムの値 
   */
  public  java.sql.Timestamp  appli_end_date;

  /** 
   * <em>regist_type</em>カラムの値 
   */
  public  String  regist_type;

  /** 
   * <em>valid_div</em>カラムの値 
   */
  public  String  valid_div;

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

  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean campaign_id_is_set = false;
  private boolean campaign_id_is_modified = false;
  private boolean comm_campaign_name_is_set = false;
  private boolean comm_campaign_name_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean family_name_is_set = false;
  private boolean family_name_is_modified = false;
  private boolean sonar_trader_code_is_set = false;
  private boolean sonar_trader_code_is_modified = false;
  private boolean acc_open_kind_div_is_set = false;
  private boolean acc_open_kind_div_is_modified = false;
  private boolean account_charge_ratio_is_set = false;
  private boolean account_charge_ratio_is_modified = false;
  private boolean appli_start_date_is_set = false;
  private boolean appli_start_date_is_modified = false;
  private boolean appli_end_date_is_set = false;
  private boolean appli_end_date_is_modified = false;
  private boolean regist_type_is_set = false;
  private boolean regist_type_is_modified = false;
  private boolean valid_div_is_set = false;
  private boolean valid_div_is_modified = false;
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
       + "account_id=" + account_id
      + "," + "campaign_id=" + campaign_id
      + "," + "comm_campaign_name=" +comm_campaign_name
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "," + "family_name=" +family_name
      + "," + "sonar_trader_code=" +sonar_trader_code
      + "," + "acc_open_kind_div=" +acc_open_kind_div
      + "," + "account_charge_ratio=" +account_charge_ratio
      + "," + "appli_start_date=" +appli_start_date
      + "," + "appli_end_date=" +appli_end_date
      + "," + "regist_type=" +regist_type
      + "," + "valid_div=" +valid_div
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のCommCampaignAccHistoryParamsオブジェクトを作成します。 
   */
  public CommCampaignAccHistoryParams() {
    account_id_is_modified = true;
    campaign_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のCommCampaignAccHistoryRowオブジェクトの値を利用してCommCampaignAccHistoryParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するCommCampaignAccHistoryRowオブジェクト 
   */
  public CommCampaignAccHistoryParams( CommCampaignAccHistoryRow p_row )
  {
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    campaign_id = p_row.getCampaignId();
    campaign_id_is_set = p_row.getCampaignIdIsSet();
    campaign_id_is_modified = p_row.getCampaignIdIsModified();
    comm_campaign_name = p_row.getCommCampaignName();
    comm_campaign_name_is_set = p_row.getCommCampaignNameIsSet();
    comm_campaign_name_is_modified = p_row.getCommCampaignNameIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    family_name = p_row.getFamilyName();
    family_name_is_set = p_row.getFamilyNameIsSet();
    family_name_is_modified = p_row.getFamilyNameIsModified();
    sonar_trader_code = p_row.getSonarTraderCode();
    sonar_trader_code_is_set = p_row.getSonarTraderCodeIsSet();
    sonar_trader_code_is_modified = p_row.getSonarTraderCodeIsModified();
    acc_open_kind_div = p_row.getAccOpenKindDiv();
    acc_open_kind_div_is_set = p_row.getAccOpenKindDivIsSet();
    acc_open_kind_div_is_modified = p_row.getAccOpenKindDivIsModified();
    account_charge_ratio = p_row.getAccountChargeRatio();
    account_charge_ratio_is_set = p_row.getAccountChargeRatioIsSet();
    account_charge_ratio_is_modified = p_row.getAccountChargeRatioIsModified();
    appli_start_date = p_row.getAppliStartDate();
    appli_start_date_is_set = p_row.getAppliStartDateIsSet();
    appli_start_date_is_modified = p_row.getAppliStartDateIsModified();
    appli_end_date = p_row.getAppliEndDate();
    appli_end_date_is_set = p_row.getAppliEndDateIsSet();
    appli_end_date_is_modified = p_row.getAppliEndDateIsModified();
    regist_type = p_row.getRegistType();
    regist_type_is_set = p_row.getRegistTypeIsSet();
    regist_type_is_modified = p_row.getRegistTypeIsModified();
    valid_div = p_row.getValidDiv();
    valid_div_is_set = p_row.getValidDivIsSet();
    valid_div_is_modified = p_row.getValidDivIsModified();
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
      comm_campaign_name_is_set = true;
      comm_campaign_name_is_modified = true;
      institution_code_is_set = true;
      institution_code_is_modified = true;
      branch_code_is_set = true;
      branch_code_is_modified = true;
      account_code_is_set = true;
      account_code_is_modified = true;
      family_name_is_set = true;
      family_name_is_modified = true;
      sonar_trader_code_is_set = true;
      sonar_trader_code_is_modified = true;
      acc_open_kind_div_is_set = true;
      acc_open_kind_div_is_modified = true;
      account_charge_ratio_is_set = true;
      account_charge_ratio_is_modified = true;
      appli_start_date_is_set = true;
      appli_start_date_is_modified = true;
      appli_end_date_is_set = true;
      appli_end_date_is_modified = true;
      regist_type_is_set = true;
      regist_type_is_modified = true;
      valid_div_is_set = true;
      valid_div_is_modified = true;
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
    if ( !( other instanceof CommCampaignAccHistoryRow ) )
       return false;
    return fieldsEqual( (CommCampaignAccHistoryRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のCommCampaignAccHistoryRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( CommCampaignAccHistoryRow row )
  {
    if ( account_id != row.getAccountId() )
      return false;
    if ( campaign_id != row.getCampaignId() )
      return false;
    if ( comm_campaign_name == null ) {
      if ( row.getCommCampaignName() != null )
        return false;
    } else if ( !comm_campaign_name.equals( row.getCommCampaignName() ) ) {
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
    if ( family_name == null ) {
      if ( row.getFamilyName() != null )
        return false;
    } else if ( !family_name.equals( row.getFamilyName() ) ) {
        return false;
    }
    if ( sonar_trader_code == null ) {
      if ( row.getSonarTraderCode() != null )
        return false;
    } else if ( !sonar_trader_code.equals( row.getSonarTraderCode() ) ) {
        return false;
    }
    if ( acc_open_kind_div == null ) {
      if ( row.getAccOpenKindDiv() != null )
        return false;
    } else if ( !acc_open_kind_div.equals( row.getAccOpenKindDiv() ) ) {
        return false;
    }
    if ( account_charge_ratio != row.getAccountChargeRatio() )
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
    if ( regist_type == null ) {
      if ( row.getRegistType() != null )
        return false;
    } else if ( !regist_type.equals( row.getRegistType() ) ) {
        return false;
    }
    if ( valid_div == null ) {
      if ( row.getValidDiv() != null )
        return false;
    } else if ( !valid_div.equals( row.getValidDiv() ) ) {
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
      return  ((int) account_id)
        + ((int) campaign_id)
        + (comm_campaign_name!=null? comm_campaign_name.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (family_name!=null? family_name.hashCode(): 0) 
        + (sonar_trader_code!=null? sonar_trader_code.hashCode(): 0) 
        + (acc_open_kind_div!=null? acc_open_kind_div.hashCode(): 0) 
        + ((int) account_charge_ratio)
        + (appli_start_date!=null? appli_start_date.hashCode(): 0) 
        + (appli_end_date!=null? appli_end_date.hashCode(): 0) 
        + (regist_type!=null? regist_type.hashCode(): 0) 
        + (valid_div!=null? valid_div.hashCode(): 0) 
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
		if ( !account_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_code' must be set before inserting.");
		if ( !account_charge_ratio_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_charge_ratio' must be set before inserting.");
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
		map.put("account_id",new Long(account_id));
		map.put("campaign_id",new Long(campaign_id));
		if ( comm_campaign_name != null )
			map.put("comm_campaign_name",comm_campaign_name);
		map.put("institution_code",institution_code);
		if ( branch_code != null )
			map.put("branch_code",branch_code);
		map.put("account_code",account_code);
		if ( family_name != null )
			map.put("family_name",family_name);
		if ( sonar_trader_code != null )
			map.put("sonar_trader_code",sonar_trader_code);
		if ( acc_open_kind_div != null )
			map.put("acc_open_kind_div",acc_open_kind_div);
		map.put("account_charge_ratio",new Double(account_charge_ratio));
		if ( appli_start_date != null )
			map.put("appli_start_date",appli_start_date);
		if ( appli_end_date != null )
			map.put("appli_end_date",appli_end_date);
		if ( regist_type != null )
			map.put("regist_type",regist_type);
		if ( valid_div != null )
			map.put("valid_div",valid_div);
		map.put("last_updater",last_updater);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( comm_campaign_name_is_modified )
			map.put("comm_campaign_name",comm_campaign_name);
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( branch_code_is_modified )
			map.put("branch_code",branch_code);
		if ( account_code_is_modified )
			map.put("account_code",account_code);
		if ( family_name_is_modified )
			map.put("family_name",family_name);
		if ( sonar_trader_code_is_modified )
			map.put("sonar_trader_code",sonar_trader_code);
		if ( acc_open_kind_div_is_modified )
			map.put("acc_open_kind_div",acc_open_kind_div);
		if ( account_charge_ratio_is_modified )
			map.put("account_charge_ratio",new Double(account_charge_ratio));
		if ( appli_start_date_is_modified )
			map.put("appli_start_date",appli_start_date);
		if ( appli_end_date_is_modified )
			map.put("appli_end_date",appli_end_date);
		if ( regist_type_is_modified )
			map.put("regist_type",regist_type);
		if ( valid_div_is_modified )
			map.put("valid_div",valid_div);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("comm_campaign_name",comm_campaign_name);
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			map.put("branch_code",branch_code);
			if ( account_code_is_set )
				map.put("account_code",account_code);
			map.put("family_name",family_name);
			map.put("sonar_trader_code",sonar_trader_code);
			map.put("acc_open_kind_div",acc_open_kind_div);
			if ( account_charge_ratio_is_set )
				map.put("account_charge_ratio",new Double(account_charge_ratio));
			map.put("appli_start_date",appli_start_date);
			map.put("appli_end_date",appli_end_date);
			map.put("regist_type",regist_type);
			map.put("valid_div",valid_div);
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
   * <em>campaign_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getCampaignId()
  {
    return campaign_id;
  }


  /** 
   * <em>campaign_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCampaignIdIsSet() {
    return campaign_id_is_set;
  }


  /** 
   * <em>campaign_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCampaignIdIsModified() {
    return campaign_id_is_modified;
  }


  /** 
   * <em>comm_campaign_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCommCampaignName()
  {
    return comm_campaign_name;
  }


  /** 
   * <em>comm_campaign_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommCampaignNameIsSet() {
    return comm_campaign_name_is_set;
  }


  /** 
   * <em>comm_campaign_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommCampaignNameIsModified() {
    return comm_campaign_name_is_modified;
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
   * <em>family_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFamilyName()
  {
    return family_name;
  }


  /** 
   * <em>family_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFamilyNameIsSet() {
    return family_name_is_set;
  }


  /** 
   * <em>family_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFamilyNameIsModified() {
    return family_name_is_modified;
  }


  /** 
   * <em>sonar_trader_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSonarTraderCode()
  {
    return sonar_trader_code;
  }


  /** 
   * <em>sonar_trader_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarTraderCodeIsSet() {
    return sonar_trader_code_is_set;
  }


  /** 
   * <em>sonar_trader_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarTraderCodeIsModified() {
    return sonar_trader_code_is_modified;
  }


  /** 
   * <em>acc_open_kind_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccOpenKindDiv()
  {
    return acc_open_kind_div;
  }


  /** 
   * <em>acc_open_kind_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccOpenKindDivIsSet() {
    return acc_open_kind_div_is_set;
  }


  /** 
   * <em>acc_open_kind_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccOpenKindDivIsModified() {
    return acc_open_kind_div_is_modified;
  }


  /** 
   * <em>account_charge_ratio</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getAccountChargeRatio()
  {
    return account_charge_ratio;
  }


  /** 
   * <em>account_charge_ratio</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountChargeRatioIsSet() {
    return account_charge_ratio_is_set;
  }


  /** 
   * <em>account_charge_ratio</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountChargeRatioIsModified() {
    return account_charge_ratio_is_modified;
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
   * <em>regist_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRegistType()
  {
    return regist_type;
  }


  /** 
   * <em>regist_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRegistTypeIsSet() {
    return regist_type_is_set;
  }


  /** 
   * <em>regist_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRegistTypeIsModified() {
    return regist_type_is_modified;
  }


  /** 
   * <em>valid_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getValidDiv()
  {
    return valid_div;
  }


  /** 
   * <em>valid_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getValidDivIsSet() {
    return valid_div_is_set;
  }


  /** 
   * <em>valid_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getValidDivIsModified() {
    return valid_div_is_modified;
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
    return new CommCampaignAccHistoryPK(account_id, campaign_id);
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
   * <em>campaign_id</em>カラムの値を設定します。 
   *
   * @@param p_campaignId <em>campaign_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setCampaignId( long p_campaignId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    campaign_id = p_campaignId;
    campaign_id_is_set = true;
    campaign_id_is_modified = true;
  }


  /** 
   * <em>comm_campaign_name</em>カラムの値を設定します。 
   *
   * @@param p_commCampaignName <em>comm_campaign_name</em>カラムの値をあらわす100桁以下のString値 
   */
  public final void setCommCampaignName( String p_commCampaignName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comm_campaign_name = p_commCampaignName;
    comm_campaign_name_is_set = true;
    comm_campaign_name_is_modified = true;
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
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす20桁以下のString値 
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
   * <em>family_name</em>カラムの値を設定します。 
   *
   * @@param p_familyName <em>family_name</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setFamilyName( String p_familyName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    family_name = p_familyName;
    family_name_is_set = true;
    family_name_is_modified = true;
  }


  /** 
   * <em>sonar_trader_code</em>カラムの値を設定します。 
   *
   * @@param p_sonarTraderCode <em>sonar_trader_code</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setSonarTraderCode( String p_sonarTraderCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sonar_trader_code = p_sonarTraderCode;
    sonar_trader_code_is_set = true;
    sonar_trader_code_is_modified = true;
  }


  /** 
   * <em>acc_open_kind_div</em>カラムの値を設定します。 
   *
   * @@param p_accOpenKindDiv <em>acc_open_kind_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAccOpenKindDiv( String p_accOpenKindDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    acc_open_kind_div = p_accOpenKindDiv;
    acc_open_kind_div_is_set = true;
    acc_open_kind_div_is_modified = true;
  }


  /** 
   * <em>account_charge_ratio</em>カラムの値を設定します。 
   *
   * @@param p_accountChargeRatio <em>account_charge_ratio</em>カラムの値をあらわす8桁以下のdouble値で、その精度は5桁まで
   */
  public final void setAccountChargeRatio( double p_accountChargeRatio )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_charge_ratio = p_accountChargeRatio;
    account_charge_ratio_is_set = true;
    account_charge_ratio_is_modified = true;
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
   * <em>regist_type</em>カラムの値を設定します。 
   *
   * @@param p_registType <em>regist_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setRegistType( String p_registType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    regist_type = p_registType;
    regist_type_is_set = true;
    regist_type_is_modified = true;
  }


  /** 
   * <em>valid_div</em>カラムの値を設定します。 
   *
   * @@param p_validDiv <em>valid_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setValidDiv( String p_validDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    valid_div = p_validDiv;
    valid_div_is_set = true;
    valid_div_is_modified = true;
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
                else if ( name.equals("account_code") ) {
                    return this.account_code;
                }
                else if ( name.equals("acc_open_kind_div") ) {
                    return this.acc_open_kind_div;
                }
                else if ( name.equals("account_charge_ratio") ) {
                    return new Double( account_charge_ratio );
                }
                else if ( name.equals("appli_start_date") ) {
                    return this.appli_start_date;
                }
                else if ( name.equals("appli_end_date") ) {
                    return this.appli_end_date;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'c':
                if ( name.equals("campaign_id") ) {
                    return new Long( campaign_id );
                }
                else if ( name.equals("comm_campaign_name") ) {
                    return this.comm_campaign_name;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'f':
                if ( name.equals("family_name") ) {
                    return this.family_name;
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
            case 'r':
                if ( name.equals("regist_type") ) {
                    return this.regist_type;
                }
                break;
            case 's':
                if ( name.equals("sonar_trader_code") ) {
                    return this.sonar_trader_code;
                }
                break;
            case 'v':
                if ( name.equals("valid_div") ) {
                    return this.valid_div;
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
                else if ( name.equals("account_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_code' must be String: '"+value+"' is not." );
                    this.account_code = (String) value;
                    if (this.account_code_is_set)
                        this.account_code_is_modified = true;
                    this.account_code_is_set = true;
                    return;
                }
                else if ( name.equals("acc_open_kind_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'acc_open_kind_div' must be String: '"+value+"' is not." );
                    this.acc_open_kind_div = (String) value;
                    if (this.acc_open_kind_div_is_set)
                        this.acc_open_kind_div_is_modified = true;
                    this.acc_open_kind_div_is_set = true;
                    return;
                }
                else if ( name.equals("account_charge_ratio") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'account_charge_ratio' must be Double: '"+value+"' is not." );
                    this.account_charge_ratio = ((Double) value).doubleValue();
                    if (this.account_charge_ratio_is_set)
                        this.account_charge_ratio_is_modified = true;
                    this.account_charge_ratio_is_set = true;
                    return;
                }
                else if ( name.equals("appli_start_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'appli_start_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.appli_start_date = (java.sql.Timestamp) value;
                    if (this.appli_start_date_is_set)
                        this.appli_start_date_is_modified = true;
                    this.appli_start_date_is_set = true;
                    return;
                }
                else if ( name.equals("appli_end_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'appli_end_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.appli_end_date = (java.sql.Timestamp) value;
                    if (this.appli_end_date_is_set)
                        this.appli_end_date_is_modified = true;
                    this.appli_end_date_is_set = true;
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
                if ( name.equals("campaign_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'campaign_id' must be Long: '"+value+"' is not." );
                    this.campaign_id = ((Long) value).longValue();
                    if (this.campaign_id_is_set)
                        this.campaign_id_is_modified = true;
                    this.campaign_id_is_set = true;
                    return;
                }
                else if ( name.equals("comm_campaign_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'comm_campaign_name' must be String: '"+value+"' is not." );
                    this.comm_campaign_name = (String) value;
                    if (this.comm_campaign_name_is_set)
                        this.comm_campaign_name_is_modified = true;
                    this.comm_campaign_name_is_set = true;
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
                if ( name.equals("family_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'family_name' must be String: '"+value+"' is not." );
                    this.family_name = (String) value;
                    if (this.family_name_is_set)
                        this.family_name_is_modified = true;
                    this.family_name_is_set = true;
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
            case 'r':
                if ( name.equals("regist_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'regist_type' must be String: '"+value+"' is not." );
                    this.regist_type = (String) value;
                    if (this.regist_type_is_set)
                        this.regist_type_is_modified = true;
                    this.regist_type_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sonar_trader_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sonar_trader_code' must be String: '"+value+"' is not." );
                    this.sonar_trader_code = (String) value;
                    if (this.sonar_trader_code_is_set)
                        this.sonar_trader_code_is_modified = true;
                    this.sonar_trader_code_is_set = true;
                    return;
                }
                break;
            case 'v':
                if ( name.equals("valid_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'valid_div' must be String: '"+value+"' is not." );
                    this.valid_div = (String) value;
                    if (this.valid_div_is_set)
                        this.valid_div_is_modified = true;
                    this.valid_div_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
