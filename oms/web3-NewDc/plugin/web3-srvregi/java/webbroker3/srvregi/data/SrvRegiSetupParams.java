head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.43.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	SrvRegiSetupParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.srvregi.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * srv_regi_setupテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link SrvRegiSetupRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link SrvRegiSetupParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link SrvRegiSetupParams}が{@@link SrvRegiSetupRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SrvRegiSetupPK 
 * @@see SrvRegiSetupRow 
 */
public class SrvRegiSetupParams extends Params implements SrvRegiSetupRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "srv_regi_setup";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = SrvRegiSetupRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return SrvRegiSetupRow.TYPE;
  }


  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>srv_div</em>カラムの値 
   */
  public  String  srv_div;

  /** 
   * <em>summary</em>カラムの値 
   */
  public  String  summary;

  /** 
   * <em>lot_div</em>カラムの値 
   */
  public  String  lot_div;

  /** 
   * <em>trial_period_div</em>カラムの値 
   */
  public  String  trial_period_div;

  /** 
   * <em>trial_period</em>カラムの値 
   */
  public  Integer  trial_period;

  /** 
   * <em>appli_date_from</em>カラムの値 
   */
  public  Integer  appli_date_from;

  /** 
   * <em>appli_date_to</em>カラムの値 
   */
  public  Integer  appli_date_to;

  /** 
   * <em>srv_contents</em>カラムの値 
   */
  public  String  srv_contents;

  /** 
   * <em>srv_explan_url</em>カラムの値 
   */
  public  String  srv_explan_url;

  /** 
   * <em>start_mail_div</em>カラムの値 
   */
  public  String  start_mail_div;

  /** 
   * <em>end_mail_div</em>カラムの値 
   */
  public  String  end_mail_div;

  /** 
   * <em>send_mail_interval</em>カラムの値 
   */
  public  Integer  send_mail_interval;

  /** 
   * <em>electric_issue_div</em>カラムの値 
   */
  public  String  electric_issue_div;

  /** 
   * <em>min_comm_amt</em>カラムの値 
   */
  public  long  min_comm_amt;

  /** 
   * <em>supply_div</em>カラムの値 
   */
  public  String  supply_div;

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
   * <em>free_coverage_length</em>カラムの値 
   */
  public  Integer  free_coverage_length;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean srv_div_is_set = false;
  private boolean srv_div_is_modified = false;
  private boolean summary_is_set = false;
  private boolean summary_is_modified = false;
  private boolean lot_div_is_set = false;
  private boolean lot_div_is_modified = false;
  private boolean trial_period_div_is_set = false;
  private boolean trial_period_div_is_modified = false;
  private boolean trial_period_is_set = false;
  private boolean trial_period_is_modified = false;
  private boolean appli_date_from_is_set = false;
  private boolean appli_date_from_is_modified = false;
  private boolean appli_date_to_is_set = false;
  private boolean appli_date_to_is_modified = false;
  private boolean srv_contents_is_set = false;
  private boolean srv_contents_is_modified = false;
  private boolean srv_explan_url_is_set = false;
  private boolean srv_explan_url_is_modified = false;
  private boolean start_mail_div_is_set = false;
  private boolean start_mail_div_is_modified = false;
  private boolean end_mail_div_is_set = false;
  private boolean end_mail_div_is_modified = false;
  private boolean send_mail_interval_is_set = false;
  private boolean send_mail_interval_is_modified = false;
  private boolean electric_issue_div_is_set = false;
  private boolean electric_issue_div_is_modified = false;
  private boolean min_comm_amt_is_set = false;
  private boolean min_comm_amt_is_modified = false;
  private boolean supply_div_is_set = false;
  private boolean supply_div_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean free_coverage_length_is_set = false;
  private boolean free_coverage_length_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "srv_div=" + srv_div
      + "," + "summary=" +summary
      + "," + "lot_div=" +lot_div
      + "," + "trial_period_div=" +trial_period_div
      + "," + "trial_period=" +trial_period
      + "," + "appli_date_from=" +appli_date_from
      + "," + "appli_date_to=" +appli_date_to
      + "," + "srv_contents=" +srv_contents
      + "," + "srv_explan_url=" +srv_explan_url
      + "," + "start_mail_div=" +start_mail_div
      + "," + "end_mail_div=" +end_mail_div
      + "," + "send_mail_interval=" +send_mail_interval
      + "," + "electric_issue_div=" +electric_issue_div
      + "," + "min_comm_amt=" +min_comm_amt
      + "," + "supply_div=" +supply_div
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "free_coverage_length=" +free_coverage_length
      + "}";
  }


  /** 
   * 値が未設定のSrvRegiSetupParamsオブジェクトを作成します。 
   */
  public SrvRegiSetupParams() {
    institution_code_is_modified = true;
    srv_div_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のSrvRegiSetupRowオブジェクトの値を利用してSrvRegiSetupParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するSrvRegiSetupRowオブジェクト 
   */
  public SrvRegiSetupParams( SrvRegiSetupRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    srv_div = p_row.getSrvDiv();
    srv_div_is_set = p_row.getSrvDivIsSet();
    srv_div_is_modified = p_row.getSrvDivIsModified();
    summary = p_row.getSummary();
    summary_is_set = p_row.getSummaryIsSet();
    summary_is_modified = p_row.getSummaryIsModified();
    lot_div = p_row.getLotDiv();
    lot_div_is_set = p_row.getLotDivIsSet();
    lot_div_is_modified = p_row.getLotDivIsModified();
    trial_period_div = p_row.getTrialPeriodDiv();
    trial_period_div_is_set = p_row.getTrialPeriodDivIsSet();
    trial_period_div_is_modified = p_row.getTrialPeriodDivIsModified();
    if ( !p_row.getTrialPeriodIsNull() )
      trial_period = new Integer( p_row.getTrialPeriod() );
    trial_period_is_set = p_row.getTrialPeriodIsSet();
    trial_period_is_modified = p_row.getTrialPeriodIsModified();
    if ( !p_row.getAppliDateFromIsNull() )
      appli_date_from = new Integer( p_row.getAppliDateFrom() );
    appli_date_from_is_set = p_row.getAppliDateFromIsSet();
    appli_date_from_is_modified = p_row.getAppliDateFromIsModified();
    if ( !p_row.getAppliDateToIsNull() )
      appli_date_to = new Integer( p_row.getAppliDateTo() );
    appli_date_to_is_set = p_row.getAppliDateToIsSet();
    appli_date_to_is_modified = p_row.getAppliDateToIsModified();
    srv_contents = p_row.getSrvContents();
    srv_contents_is_set = p_row.getSrvContentsIsSet();
    srv_contents_is_modified = p_row.getSrvContentsIsModified();
    srv_explan_url = p_row.getSrvExplanUrl();
    srv_explan_url_is_set = p_row.getSrvExplanUrlIsSet();
    srv_explan_url_is_modified = p_row.getSrvExplanUrlIsModified();
    start_mail_div = p_row.getStartMailDiv();
    start_mail_div_is_set = p_row.getStartMailDivIsSet();
    start_mail_div_is_modified = p_row.getStartMailDivIsModified();
    end_mail_div = p_row.getEndMailDiv();
    end_mail_div_is_set = p_row.getEndMailDivIsSet();
    end_mail_div_is_modified = p_row.getEndMailDivIsModified();
    if ( !p_row.getSendMailIntervalIsNull() )
      send_mail_interval = new Integer( p_row.getSendMailInterval() );
    send_mail_interval_is_set = p_row.getSendMailIntervalIsSet();
    send_mail_interval_is_modified = p_row.getSendMailIntervalIsModified();
    electric_issue_div = p_row.getElectricIssueDiv();
    electric_issue_div_is_set = p_row.getElectricIssueDivIsSet();
    electric_issue_div_is_modified = p_row.getElectricIssueDivIsModified();
    min_comm_amt = p_row.getMinCommAmt();
    min_comm_amt_is_set = p_row.getMinCommAmtIsSet();
    min_comm_amt_is_modified = p_row.getMinCommAmtIsModified();
    supply_div = p_row.getSupplyDiv();
    supply_div_is_set = p_row.getSupplyDivIsSet();
    supply_div_is_modified = p_row.getSupplyDivIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    if ( !p_row.getFreeCoverageLengthIsNull() )
      free_coverage_length = new Integer( p_row.getFreeCoverageLength() );
    free_coverage_length_is_set = p_row.getFreeCoverageLengthIsSet();
    free_coverage_length_is_modified = p_row.getFreeCoverageLengthIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      summary_is_set = true;
      summary_is_modified = true;
      lot_div_is_set = true;
      lot_div_is_modified = true;
      trial_period_div_is_set = true;
      trial_period_div_is_modified = true;
      trial_period_is_set = true;
      trial_period_is_modified = true;
      appli_date_from_is_set = true;
      appli_date_from_is_modified = true;
      appli_date_to_is_set = true;
      appli_date_to_is_modified = true;
      srv_contents_is_set = true;
      srv_contents_is_modified = true;
      srv_explan_url_is_set = true;
      srv_explan_url_is_modified = true;
      start_mail_div_is_set = true;
      start_mail_div_is_modified = true;
      end_mail_div_is_set = true;
      end_mail_div_is_modified = true;
      send_mail_interval_is_set = true;
      send_mail_interval_is_modified = true;
      electric_issue_div_is_set = true;
      electric_issue_div_is_modified = true;
      min_comm_amt_is_set = true;
      min_comm_amt_is_modified = true;
      supply_div_is_set = true;
      supply_div_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      free_coverage_length_is_set = true;
      free_coverage_length_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof SrvRegiSetupRow ) )
       return false;
    return fieldsEqual( (SrvRegiSetupRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のSrvRegiSetupRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( SrvRegiSetupRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( srv_div == null ) {
      if ( row.getSrvDiv() != null )
        return false;
    } else if ( !srv_div.equals( row.getSrvDiv() ) ) {
        return false;
    }
    if ( summary == null ) {
      if ( row.getSummary() != null )
        return false;
    } else if ( !summary.equals( row.getSummary() ) ) {
        return false;
    }
    if ( lot_div == null ) {
      if ( row.getLotDiv() != null )
        return false;
    } else if ( !lot_div.equals( row.getLotDiv() ) ) {
        return false;
    }
    if ( trial_period_div == null ) {
      if ( row.getTrialPeriodDiv() != null )
        return false;
    } else if ( !trial_period_div.equals( row.getTrialPeriodDiv() ) ) {
        return false;
    }
    if ( trial_period == null ) {
      if ( !row.getTrialPeriodIsNull() )
        return false;
    } else if ( row.getTrialPeriodIsNull() || ( trial_period.intValue() != row.getTrialPeriod() ) ) {
        return false;
    }
    if ( appli_date_from == null ) {
      if ( !row.getAppliDateFromIsNull() )
        return false;
    } else if ( row.getAppliDateFromIsNull() || ( appli_date_from.intValue() != row.getAppliDateFrom() ) ) {
        return false;
    }
    if ( appli_date_to == null ) {
      if ( !row.getAppliDateToIsNull() )
        return false;
    } else if ( row.getAppliDateToIsNull() || ( appli_date_to.intValue() != row.getAppliDateTo() ) ) {
        return false;
    }
    if ( srv_contents == null ) {
      if ( row.getSrvContents() != null )
        return false;
    } else if ( !srv_contents.equals( row.getSrvContents() ) ) {
        return false;
    }
    if ( srv_explan_url == null ) {
      if ( row.getSrvExplanUrl() != null )
        return false;
    } else if ( !srv_explan_url.equals( row.getSrvExplanUrl() ) ) {
        return false;
    }
    if ( start_mail_div == null ) {
      if ( row.getStartMailDiv() != null )
        return false;
    } else if ( !start_mail_div.equals( row.getStartMailDiv() ) ) {
        return false;
    }
    if ( end_mail_div == null ) {
      if ( row.getEndMailDiv() != null )
        return false;
    } else if ( !end_mail_div.equals( row.getEndMailDiv() ) ) {
        return false;
    }
    if ( send_mail_interval == null ) {
      if ( !row.getSendMailIntervalIsNull() )
        return false;
    } else if ( row.getSendMailIntervalIsNull() || ( send_mail_interval.intValue() != row.getSendMailInterval() ) ) {
        return false;
    }
    if ( electric_issue_div == null ) {
      if ( row.getElectricIssueDiv() != null )
        return false;
    } else if ( !electric_issue_div.equals( row.getElectricIssueDiv() ) ) {
        return false;
    }
    if ( min_comm_amt != row.getMinCommAmt() )
      return false;
    if ( supply_div == null ) {
      if ( row.getSupplyDiv() != null )
        return false;
    } else if ( !supply_div.equals( row.getSupplyDiv() ) ) {
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
    if ( free_coverage_length == null ) {
      if ( !row.getFreeCoverageLengthIsNull() )
        return false;
    } else if ( row.getFreeCoverageLengthIsNull() || ( free_coverage_length.intValue() != row.getFreeCoverageLength() ) ) {
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
        + (srv_div!=null? srv_div.hashCode(): 0) 
        + (summary!=null? summary.hashCode(): 0) 
        + (lot_div!=null? lot_div.hashCode(): 0) 
        + (trial_period_div!=null? trial_period_div.hashCode(): 0) 
        + (trial_period!=null? trial_period.hashCode(): 0) 
        + (appli_date_from!=null? appli_date_from.hashCode(): 0) 
        + (appli_date_to!=null? appli_date_to.hashCode(): 0) 
        + (srv_contents!=null? srv_contents.hashCode(): 0) 
        + (srv_explan_url!=null? srv_explan_url.hashCode(): 0) 
        + (start_mail_div!=null? start_mail_div.hashCode(): 0) 
        + (end_mail_div!=null? end_mail_div.hashCode(): 0) 
        + (send_mail_interval!=null? send_mail_interval.hashCode(): 0) 
        + (electric_issue_div!=null? electric_issue_div.hashCode(): 0) 
        + ((int) min_comm_amt)
        + (supply_div!=null? supply_div.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (free_coverage_length!=null? free_coverage_length.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !summary_is_set )
			throw new IllegalArgumentException("non-nullable field 'summary' must be set before inserting.");
		if ( !lot_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'lot_div' must be set before inserting.");
		if ( !start_mail_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'start_mail_div' must be set before inserting.");
		if ( !end_mail_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'end_mail_div' must be set before inserting.");
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
		map.put("srv_div",srv_div);
		map.put("summary",summary);
		map.put("lot_div",lot_div);
		if ( trial_period_div != null )
			map.put("trial_period_div",trial_period_div);
		if ( trial_period != null )
			map.put("trial_period",trial_period);
		if ( appli_date_from != null )
			map.put("appli_date_from",appli_date_from);
		if ( appli_date_to != null )
			map.put("appli_date_to",appli_date_to);
		if ( srv_contents != null )
			map.put("srv_contents",srv_contents);
		if ( srv_explan_url != null )
			map.put("srv_explan_url",srv_explan_url);
		map.put("start_mail_div",start_mail_div);
		map.put("end_mail_div",end_mail_div);
		if ( send_mail_interval != null )
			map.put("send_mail_interval",send_mail_interval);
		if ( electric_issue_div != null )
			map.put("electric_issue_div",electric_issue_div);
		if ( min_comm_amt_is_set )
			map.put("min_comm_amt",new Long(min_comm_amt));
		if ( supply_div != null )
			map.put("supply_div",supply_div);
		map.put("last_updater",last_updater);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		if ( free_coverage_length != null )
			map.put("free_coverage_length",free_coverage_length);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( summary_is_modified )
			map.put("summary",summary);
		if ( lot_div_is_modified )
			map.put("lot_div",lot_div);
		if ( trial_period_div_is_modified )
			map.put("trial_period_div",trial_period_div);
		if ( trial_period_is_modified )
			map.put("trial_period",trial_period);
		if ( appli_date_from_is_modified )
			map.put("appli_date_from",appli_date_from);
		if ( appli_date_to_is_modified )
			map.put("appli_date_to",appli_date_to);
		if ( srv_contents_is_modified )
			map.put("srv_contents",srv_contents);
		if ( srv_explan_url_is_modified )
			map.put("srv_explan_url",srv_explan_url);
		if ( start_mail_div_is_modified )
			map.put("start_mail_div",start_mail_div);
		if ( end_mail_div_is_modified )
			map.put("end_mail_div",end_mail_div);
		if ( send_mail_interval_is_modified )
			map.put("send_mail_interval",send_mail_interval);
		if ( electric_issue_div_is_modified )
			map.put("electric_issue_div",electric_issue_div);
		if ( min_comm_amt_is_modified )
			map.put("min_comm_amt",new Long(min_comm_amt));
		if ( supply_div_is_modified )
			map.put("supply_div",supply_div);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( free_coverage_length_is_modified )
			map.put("free_coverage_length",free_coverage_length);
		if (map.size() == 0) {
			if ( summary_is_set )
				map.put("summary",summary);
			if ( lot_div_is_set )
				map.put("lot_div",lot_div);
			map.put("trial_period_div",trial_period_div);
			map.put("trial_period",trial_period);
			map.put("appli_date_from",appli_date_from);
			map.put("appli_date_to",appli_date_to);
			map.put("srv_contents",srv_contents);
			map.put("srv_explan_url",srv_explan_url);
			if ( start_mail_div_is_set )
				map.put("start_mail_div",start_mail_div);
			if ( end_mail_div_is_set )
				map.put("end_mail_div",end_mail_div);
			map.put("send_mail_interval",send_mail_interval);
			map.put("electric_issue_div",electric_issue_div);
			if ( min_comm_amt_is_set )
				map.put("min_comm_amt",new Long(min_comm_amt));
			map.put("supply_div",supply_div);
			if ( last_updater_is_set )
				map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("free_coverage_length",free_coverage_length);
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
   * <em>summary</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSummary()
  {
    return summary;
  }


  /** 
   * <em>summary</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSummaryIsSet() {
    return summary_is_set;
  }


  /** 
   * <em>summary</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSummaryIsModified() {
    return summary_is_modified;
  }


  /** 
   * <em>lot_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLotDiv()
  {
    return lot_div;
  }


  /** 
   * <em>lot_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLotDivIsSet() {
    return lot_div_is_set;
  }


  /** 
   * <em>lot_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLotDivIsModified() {
    return lot_div_is_modified;
  }


  /** 
   * <em>trial_period_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTrialPeriodDiv()
  {
    return trial_period_div;
  }


  /** 
   * <em>trial_period_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTrialPeriodDivIsSet() {
    return trial_period_div_is_set;
  }


  /** 
   * <em>trial_period_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTrialPeriodDivIsModified() {
    return trial_period_div_is_modified;
  }


  /** 
   * <em>trial_period</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getTrialPeriod()
  {
    return ( trial_period==null? ((int)0): trial_period.intValue() );
  }


  /** 
   * <em>trial_period</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTrialPeriodIsNull()
  {
    return trial_period == null;
  }


  /** 
   * <em>trial_period</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTrialPeriodIsSet() {
    return trial_period_is_set;
  }


  /** 
   * <em>trial_period</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTrialPeriodIsModified() {
    return trial_period_is_modified;
  }


  /** 
   * <em>appli_date_from</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getAppliDateFrom()
  {
    return ( appli_date_from==null? ((int)0): appli_date_from.intValue() );
  }


  /** 
   * <em>appli_date_from</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAppliDateFromIsNull()
  {
    return appli_date_from == null;
  }


  /** 
   * <em>appli_date_from</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAppliDateFromIsSet() {
    return appli_date_from_is_set;
  }


  /** 
   * <em>appli_date_from</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAppliDateFromIsModified() {
    return appli_date_from_is_modified;
  }


  /** 
   * <em>appli_date_to</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getAppliDateTo()
  {
    return ( appli_date_to==null? ((int)0): appli_date_to.intValue() );
  }


  /** 
   * <em>appli_date_to</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAppliDateToIsNull()
  {
    return appli_date_to == null;
  }


  /** 
   * <em>appli_date_to</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAppliDateToIsSet() {
    return appli_date_to_is_set;
  }


  /** 
   * <em>appli_date_to</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAppliDateToIsModified() {
    return appli_date_to_is_modified;
  }


  /** 
   * <em>srv_contents</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSrvContents()
  {
    return srv_contents;
  }


  /** 
   * <em>srv_contents</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSrvContentsIsSet() {
    return srv_contents_is_set;
  }


  /** 
   * <em>srv_contents</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSrvContentsIsModified() {
    return srv_contents_is_modified;
  }


  /** 
   * <em>srv_explan_url</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSrvExplanUrl()
  {
    return srv_explan_url;
  }


  /** 
   * <em>srv_explan_url</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSrvExplanUrlIsSet() {
    return srv_explan_url_is_set;
  }


  /** 
   * <em>srv_explan_url</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSrvExplanUrlIsModified() {
    return srv_explan_url_is_modified;
  }


  /** 
   * <em>start_mail_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStartMailDiv()
  {
    return start_mail_div;
  }


  /** 
   * <em>start_mail_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStartMailDivIsSet() {
    return start_mail_div_is_set;
  }


  /** 
   * <em>start_mail_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStartMailDivIsModified() {
    return start_mail_div_is_modified;
  }


  /** 
   * <em>end_mail_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getEndMailDiv()
  {
    return end_mail_div;
  }


  /** 
   * <em>end_mail_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEndMailDivIsSet() {
    return end_mail_div_is_set;
  }


  /** 
   * <em>end_mail_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEndMailDivIsModified() {
    return end_mail_div_is_modified;
  }


  /** 
   * <em>send_mail_interval</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getSendMailInterval()
  {
    return ( send_mail_interval==null? ((int)0): send_mail_interval.intValue() );
  }


  /** 
   * <em>send_mail_interval</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSendMailIntervalIsNull()
  {
    return send_mail_interval == null;
  }


  /** 
   * <em>send_mail_interval</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSendMailIntervalIsSet() {
    return send_mail_interval_is_set;
  }


  /** 
   * <em>send_mail_interval</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSendMailIntervalIsModified() {
    return send_mail_interval_is_modified;
  }


  /** 
   * <em>electric_issue_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getElectricIssueDiv()
  {
    return electric_issue_div;
  }


  /** 
   * <em>electric_issue_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getElectricIssueDivIsSet() {
    return electric_issue_div_is_set;
  }


  /** 
   * <em>electric_issue_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getElectricIssueDivIsModified() {
    return electric_issue_div_is_modified;
  }


  /** 
   * <em>min_comm_amt</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMinCommAmt()
  {
    return min_comm_amt;
  }


  /** 
   * <em>min_comm_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMinCommAmtIsSet() {
    return min_comm_amt_is_set;
  }


  /** 
   * <em>min_comm_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMinCommAmtIsModified() {
    return min_comm_amt_is_modified;
  }


  /** 
   * <em>supply_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSupplyDiv()
  {
    return supply_div;
  }


  /** 
   * <em>supply_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSupplyDivIsSet() {
    return supply_div_is_set;
  }


  /** 
   * <em>supply_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSupplyDivIsModified() {
    return supply_div_is_modified;
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
   * <em>free_coverage_length</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getFreeCoverageLength()
  {
    return ( free_coverage_length==null? ((int)0): free_coverage_length.intValue() );
  }


  /** 
   * <em>free_coverage_length</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getFreeCoverageLengthIsNull()
  {
    return free_coverage_length == null;
  }


  /** 
   * <em>free_coverage_length</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFreeCoverageLengthIsSet() {
    return free_coverage_length_is_set;
  }


  /** 
   * <em>free_coverage_length</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFreeCoverageLengthIsModified() {
    return free_coverage_length_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new SrvRegiSetupPK(institution_code, srv_div);
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
   * <em>summary</em>カラムの値を設定します。 
   *
   * @@param p_summary <em>summary</em>カラムの値をあらわす25桁以下のString値 
   */
  public final void setSummary( String p_summary )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    summary = p_summary;
    summary_is_set = true;
    summary_is_modified = true;
  }


  /** 
   * <em>lot_div</em>カラムの値を設定します。 
   *
   * @@param p_lotDiv <em>lot_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setLotDiv( String p_lotDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    lot_div = p_lotDiv;
    lot_div_is_set = true;
    lot_div_is_modified = true;
  }


  /** 
   * <em>trial_period_div</em>カラムの値を設定します。 
   *
   * @@param p_trialPeriodDiv <em>trial_period_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTrialPeriodDiv( String p_trialPeriodDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trial_period_div = p_trialPeriodDiv;
    trial_period_div_is_set = true;
    trial_period_div_is_modified = true;
  }


  /** 
   * <em>trial_period</em>カラムの値を設定します。 
   *
   * @@param p_trialPeriod <em>trial_period</em>カラムの値をあらわす3桁以下のint値 
   */
  public final void setTrialPeriod( int p_trialPeriod )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trial_period = new Integer(p_trialPeriod);
    trial_period_is_set = true;
    trial_period_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>trial_period</em>カラムに値を設定します。 
   */
  public final void setTrialPeriod( Integer p_trialPeriod )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    trial_period = p_trialPeriod;
    trial_period_is_set = true;
    trial_period_is_modified = true;
  }


  /** 
   * <em>appli_date_from</em>カラムの値を設定します。 
   *
   * @@param p_appliDateFrom <em>appli_date_from</em>カラムの値をあらわす2桁以下のint値 
   */
  public final void setAppliDateFrom( int p_appliDateFrom )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    appli_date_from = new Integer(p_appliDateFrom);
    appli_date_from_is_set = true;
    appli_date_from_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>appli_date_from</em>カラムに値を設定します。 
   */
  public final void setAppliDateFrom( Integer p_appliDateFrom )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    appli_date_from = p_appliDateFrom;
    appli_date_from_is_set = true;
    appli_date_from_is_modified = true;
  }


  /** 
   * <em>appli_date_to</em>カラムの値を設定します。 
   *
   * @@param p_appliDateTo <em>appli_date_to</em>カラムの値をあらわす2桁以下のint値 
   */
  public final void setAppliDateTo( int p_appliDateTo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    appli_date_to = new Integer(p_appliDateTo);
    appli_date_to_is_set = true;
    appli_date_to_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>appli_date_to</em>カラムに値を設定します。 
   */
  public final void setAppliDateTo( Integer p_appliDateTo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    appli_date_to = p_appliDateTo;
    appli_date_to_is_set = true;
    appli_date_to_is_modified = true;
  }


  /** 
   * <em>srv_contents</em>カラムの値を設定します。 
   *
   * @@param p_srvContents <em>srv_contents</em>カラムの値をあらわす4000桁以下のString値 
   */
  public final void setSrvContents( String p_srvContents )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    srv_contents = p_srvContents;
    srv_contents_is_set = true;
    srv_contents_is_modified = true;
  }


  /** 
   * <em>srv_explan_url</em>カラムの値を設定します。 
   *
   * @@param p_srvExplanUrl <em>srv_explan_url</em>カラムの値をあらわす256桁以下のString値 
   */
  public final void setSrvExplanUrl( String p_srvExplanUrl )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    srv_explan_url = p_srvExplanUrl;
    srv_explan_url_is_set = true;
    srv_explan_url_is_modified = true;
  }


  /** 
   * <em>start_mail_div</em>カラムの値を設定します。 
   *
   * @@param p_startMailDiv <em>start_mail_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setStartMailDiv( String p_startMailDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    start_mail_div = p_startMailDiv;
    start_mail_div_is_set = true;
    start_mail_div_is_modified = true;
  }


  /** 
   * <em>end_mail_div</em>カラムの値を設定します。 
   *
   * @@param p_endMailDiv <em>end_mail_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setEndMailDiv( String p_endMailDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    end_mail_div = p_endMailDiv;
    end_mail_div_is_set = true;
    end_mail_div_is_modified = true;
  }


  /** 
   * <em>send_mail_interval</em>カラムの値を設定します。 
   *
   * @@param p_sendMailInterval <em>send_mail_interval</em>カラムの値をあらわす3桁以下のint値 
   */
  public final void setSendMailInterval( int p_sendMailInterval )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    send_mail_interval = new Integer(p_sendMailInterval);
    send_mail_interval_is_set = true;
    send_mail_interval_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>send_mail_interval</em>カラムに値を設定します。 
   */
  public final void setSendMailInterval( Integer p_sendMailInterval )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    send_mail_interval = p_sendMailInterval;
    send_mail_interval_is_set = true;
    send_mail_interval_is_modified = true;
  }


  /** 
   * <em>electric_issue_div</em>カラムの値を設定します。 
   *
   * @@param p_electricIssueDiv <em>electric_issue_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setElectricIssueDiv( String p_electricIssueDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    electric_issue_div = p_electricIssueDiv;
    electric_issue_div_is_set = true;
    electric_issue_div_is_modified = true;
  }


  /** 
   * <em>min_comm_amt</em>カラムの値を設定します。 
   *
   * @@param p_minCommAmt <em>min_comm_amt</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setMinCommAmt( long p_minCommAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    min_comm_amt = p_minCommAmt;
    min_comm_amt_is_set = true;
    min_comm_amt_is_modified = true;
  }


  /** 
   * <em>supply_div</em>カラムの値を設定します。 
   *
   * @@param p_supplyDiv <em>supply_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSupplyDiv( String p_supplyDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    supply_div = p_supplyDiv;
    supply_div_is_set = true;
    supply_div_is_modified = true;
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
   * <em>free_coverage_length</em>カラムの値を設定します。 
   *
   * @@param p_freeCoverageLength <em>free_coverage_length</em>カラムの値をあらわす2桁以下のint値 
   */
  public final void setFreeCoverageLength( int p_freeCoverageLength )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    free_coverage_length = new Integer(p_freeCoverageLength);
    free_coverage_length_is_set = true;
    free_coverage_length_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>free_coverage_length</em>カラムに値を設定します。 
   */
  public final void setFreeCoverageLength( Integer p_freeCoverageLength )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    free_coverage_length = p_freeCoverageLength;
    free_coverage_length_is_set = true;
    free_coverage_length_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("appli_date_from") ) {
                    return this.appli_date_from;
                }
                else if ( name.equals("appli_date_to") ) {
                    return this.appli_date_to;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("end_mail_div") ) {
                    return this.end_mail_div;
                }
                else if ( name.equals("electric_issue_div") ) {
                    return this.electric_issue_div;
                }
                break;
            case 'f':
                if ( name.equals("free_coverage_length") ) {
                    return this.free_coverage_length;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("lot_div") ) {
                    return this.lot_div;
                }
                else if ( name.equals("last_updater") ) {
                    return this.last_updater;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("min_comm_amt") ) {
                    return new Long( min_comm_amt );
                }
                break;
            case 's':
                if ( name.equals("srv_div") ) {
                    return this.srv_div;
                }
                else if ( name.equals("summary") ) {
                    return this.summary;
                }
                else if ( name.equals("srv_contents") ) {
                    return this.srv_contents;
                }
                else if ( name.equals("srv_explan_url") ) {
                    return this.srv_explan_url;
                }
                else if ( name.equals("start_mail_div") ) {
                    return this.start_mail_div;
                }
                else if ( name.equals("send_mail_interval") ) {
                    return this.send_mail_interval;
                }
                else if ( name.equals("supply_div") ) {
                    return this.supply_div;
                }
                break;
            case 't':
                if ( name.equals("trial_period_div") ) {
                    return this.trial_period_div;
                }
                else if ( name.equals("trial_period") ) {
                    return this.trial_period;
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
                if ( name.equals("appli_date_from") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'appli_date_from' must be Integer: '"+value+"' is not." );
                    this.appli_date_from = (Integer) value;
                    if (this.appli_date_from_is_set)
                        this.appli_date_from_is_modified = true;
                    this.appli_date_from_is_set = true;
                    return;
                }
                else if ( name.equals("appli_date_to") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'appli_date_to' must be Integer: '"+value+"' is not." );
                    this.appli_date_to = (Integer) value;
                    if (this.appli_date_to_is_set)
                        this.appli_date_to_is_modified = true;
                    this.appli_date_to_is_set = true;
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
                if ( name.equals("end_mail_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'end_mail_div' must be String: '"+value+"' is not." );
                    this.end_mail_div = (String) value;
                    if (this.end_mail_div_is_set)
                        this.end_mail_div_is_modified = true;
                    this.end_mail_div_is_set = true;
                    return;
                }
                else if ( name.equals("electric_issue_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'electric_issue_div' must be String: '"+value+"' is not." );
                    this.electric_issue_div = (String) value;
                    if (this.electric_issue_div_is_set)
                        this.electric_issue_div_is_modified = true;
                    this.electric_issue_div_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("free_coverage_length") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'free_coverage_length' must be Integer: '"+value+"' is not." );
                    this.free_coverage_length = (Integer) value;
                    if (this.free_coverage_length_is_set)
                        this.free_coverage_length_is_modified = true;
                    this.free_coverage_length_is_set = true;
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
                if ( name.equals("lot_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'lot_div' must be String: '"+value+"' is not." );
                    this.lot_div = (String) value;
                    if (this.lot_div_is_set)
                        this.lot_div_is_modified = true;
                    this.lot_div_is_set = true;
                    return;
                }
                else if ( name.equals("last_updater") ) {
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
                if ( name.equals("min_comm_amt") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'min_comm_amt' must be Long: '"+value+"' is not." );
                    this.min_comm_amt = ((Long) value).longValue();
                    if (this.min_comm_amt_is_set)
                        this.min_comm_amt_is_modified = true;
                    this.min_comm_amt_is_set = true;
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
                else if ( name.equals("summary") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'summary' must be String: '"+value+"' is not." );
                    this.summary = (String) value;
                    if (this.summary_is_set)
                        this.summary_is_modified = true;
                    this.summary_is_set = true;
                    return;
                }
                else if ( name.equals("srv_contents") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'srv_contents' must be String: '"+value+"' is not." );
                    this.srv_contents = (String) value;
                    if (this.srv_contents_is_set)
                        this.srv_contents_is_modified = true;
                    this.srv_contents_is_set = true;
                    return;
                }
                else if ( name.equals("srv_explan_url") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'srv_explan_url' must be String: '"+value+"' is not." );
                    this.srv_explan_url = (String) value;
                    if (this.srv_explan_url_is_set)
                        this.srv_explan_url_is_modified = true;
                    this.srv_explan_url_is_set = true;
                    return;
                }
                else if ( name.equals("start_mail_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'start_mail_div' must be String: '"+value+"' is not." );
                    this.start_mail_div = (String) value;
                    if (this.start_mail_div_is_set)
                        this.start_mail_div_is_modified = true;
                    this.start_mail_div_is_set = true;
                    return;
                }
                else if ( name.equals("send_mail_interval") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'send_mail_interval' must be Integer: '"+value+"' is not." );
                    this.send_mail_interval = (Integer) value;
                    if (this.send_mail_interval_is_set)
                        this.send_mail_interval_is_modified = true;
                    this.send_mail_interval_is_set = true;
                    return;
                }
                else if ( name.equals("supply_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'supply_div' must be String: '"+value+"' is not." );
                    this.supply_div = (String) value;
                    if (this.supply_div_is_set)
                        this.supply_div_is_modified = true;
                    this.supply_div_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("trial_period_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trial_period_div' must be String: '"+value+"' is not." );
                    this.trial_period_div = (String) value;
                    if (this.trial_period_div_is_set)
                        this.trial_period_div_is_modified = true;
                    this.trial_period_div_is_set = true;
                    return;
                }
                else if ( name.equals("trial_period") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'trial_period' must be Integer: '"+value+"' is not." );
                    this.trial_period = (Integer) value;
                    if (this.trial_period_is_set)
                        this.trial_period_is_modified = true;
                    this.trial_period_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
