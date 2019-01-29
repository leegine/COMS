head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.21.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	EquityCommCondMstParams.java;


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
 * equity_comm_cond_mstテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link EquityCommCondMstRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link EquityCommCondMstParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link EquityCommCondMstParams}が{@@link EquityCommCondMstRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see EquityCommCondMstPK 
 * @@see EquityCommCondMstRow 
 */
public class EquityCommCondMstParams extends Params implements EquityCommCondMstRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "equity_comm_cond_mst";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = EquityCommCondMstRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return EquityCommCondMstRow.TYPE;
  }


  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>comm_product_code</em>カラムの値 
   */
  public  String  comm_product_code;

  /** 
   * <em>reg_no</em>カラムの値 
   */
  public  String  reg_no;

  /** 
   * <em>appli_start_date</em>カラムの値 
   */
  public  java.sql.Timestamp  appli_start_date;

  /** 
   * <em>appli_end_date</em>カラムの値 
   */
  public  java.sql.Timestamp  appli_end_date;

  /** 
   * <em>max_commission</em>カラムの値 
   */
  public  Double  max_commission;

  /** 
   * <em>min_commission</em>カラムの値 
   */
  public  double  min_commission;

  /** 
   * <em>share_type</em>カラムの値 
   */
  public  String  share_type;

  /** 
   * <em>commission_course_div</em>カラムの値 
   */
  public  String  commission_course_div;

  /** 
   * <em>reference_column</em>カラムの値 
   */
  public  String  reference_column;

  /** 
   * <em>spc_start_date</em>カラムの値 
   */
  public  java.sql.Timestamp  spc_start_date;

  /** 
   * <em>spc_end_date</em>カラムの値 
   */
  public  java.sql.Timestamp  spc_end_date;

  /** 
   * <em>spc_charge_ratio</em>カラムの値 
   */
  public  Double  spc_charge_ratio;

  /** 
   * <em>spc_max_commission</em>カラムの値 
   */
  public  Double  spc_max_commission;

  /** 
   * <em>spc_min_commission</em>カラムの値 
   */
  public  Double  spc_min_commission;

  /** 
   * <em>reg_type</em>カラムの値 
   */
  public  String  reg_type;

  /** 
   * <em>reg_date</em>カラムの値 
   */
  public  java.sql.Timestamp  reg_date;

  /** 
   * <em>final_mod_date</em>カラムの値 
   */
  public  java.sql.Timestamp  final_mod_date;

  /** 
   * <em>final_mod_time</em>カラムの値 
   */
  public  String  final_mod_time;

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
  private boolean comm_product_code_is_set = false;
  private boolean comm_product_code_is_modified = false;
  private boolean reg_no_is_set = false;
  private boolean reg_no_is_modified = false;
  private boolean appli_start_date_is_set = false;
  private boolean appli_start_date_is_modified = false;
  private boolean appli_end_date_is_set = false;
  private boolean appli_end_date_is_modified = false;
  private boolean max_commission_is_set = false;
  private boolean max_commission_is_modified = false;
  private boolean min_commission_is_set = false;
  private boolean min_commission_is_modified = false;
  private boolean share_type_is_set = false;
  private boolean share_type_is_modified = false;
  private boolean commission_course_div_is_set = false;
  private boolean commission_course_div_is_modified = false;
  private boolean reference_column_is_set = false;
  private boolean reference_column_is_modified = false;
  private boolean spc_start_date_is_set = false;
  private boolean spc_start_date_is_modified = false;
  private boolean spc_end_date_is_set = false;
  private boolean spc_end_date_is_modified = false;
  private boolean spc_charge_ratio_is_set = false;
  private boolean spc_charge_ratio_is_modified = false;
  private boolean spc_max_commission_is_set = false;
  private boolean spc_max_commission_is_modified = false;
  private boolean spc_min_commission_is_set = false;
  private boolean spc_min_commission_is_modified = false;
  private boolean reg_type_is_set = false;
  private boolean reg_type_is_modified = false;
  private boolean reg_date_is_set = false;
  private boolean reg_date_is_modified = false;
  private boolean final_mod_date_is_set = false;
  private boolean final_mod_date_is_modified = false;
  private boolean final_mod_time_is_set = false;
  private boolean final_mod_time_is_modified = false;
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
      + "," + "comm_product_code=" + comm_product_code
      + "," + "reg_no=" + reg_no
      + "," + "appli_start_date=" + appli_start_date
      + "," + "appli_end_date=" +appli_end_date
      + "," + "max_commission=" +max_commission
      + "," + "min_commission=" +min_commission
      + "," + "share_type=" +share_type
      + "," + "commission_course_div=" +commission_course_div
      + "," + "reference_column=" +reference_column
      + "," + "spc_start_date=" +spc_start_date
      + "," + "spc_end_date=" +spc_end_date
      + "," + "spc_charge_ratio=" +spc_charge_ratio
      + "," + "spc_max_commission=" +spc_max_commission
      + "," + "spc_min_commission=" +spc_min_commission
      + "," + "reg_type=" +reg_type
      + "," + "reg_date=" +reg_date
      + "," + "final_mod_date=" +final_mod_date
      + "," + "final_mod_time=" +final_mod_time
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のEquityCommCondMstParamsオブジェクトを作成します。 
   */
  public EquityCommCondMstParams() {
    institution_code_is_modified = true;
    comm_product_code_is_modified = true;
    reg_no_is_modified = true;
    appli_start_date_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のEquityCommCondMstRowオブジェクトの値を利用してEquityCommCondMstParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するEquityCommCondMstRowオブジェクト 
   */
  public EquityCommCondMstParams( EquityCommCondMstRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    comm_product_code = p_row.getCommProductCode();
    comm_product_code_is_set = p_row.getCommProductCodeIsSet();
    comm_product_code_is_modified = p_row.getCommProductCodeIsModified();
    reg_no = p_row.getRegNo();
    reg_no_is_set = p_row.getRegNoIsSet();
    reg_no_is_modified = p_row.getRegNoIsModified();
    appli_start_date = p_row.getAppliStartDate();
    appli_start_date_is_set = p_row.getAppliStartDateIsSet();
    appli_start_date_is_modified = p_row.getAppliStartDateIsModified();
    appli_end_date = p_row.getAppliEndDate();
    appli_end_date_is_set = p_row.getAppliEndDateIsSet();
    appli_end_date_is_modified = p_row.getAppliEndDateIsModified();
    if ( !p_row.getMaxCommissionIsNull() )
      max_commission = new Double( p_row.getMaxCommission() );
    max_commission_is_set = p_row.getMaxCommissionIsSet();
    max_commission_is_modified = p_row.getMaxCommissionIsModified();
    min_commission = p_row.getMinCommission();
    min_commission_is_set = p_row.getMinCommissionIsSet();
    min_commission_is_modified = p_row.getMinCommissionIsModified();
    share_type = p_row.getShareType();
    share_type_is_set = p_row.getShareTypeIsSet();
    share_type_is_modified = p_row.getShareTypeIsModified();
    commission_course_div = p_row.getCommissionCourseDiv();
    commission_course_div_is_set = p_row.getCommissionCourseDivIsSet();
    commission_course_div_is_modified = p_row.getCommissionCourseDivIsModified();
    reference_column = p_row.getReferenceColumn();
    reference_column_is_set = p_row.getReferenceColumnIsSet();
    reference_column_is_modified = p_row.getReferenceColumnIsModified();
    spc_start_date = p_row.getSpcStartDate();
    spc_start_date_is_set = p_row.getSpcStartDateIsSet();
    spc_start_date_is_modified = p_row.getSpcStartDateIsModified();
    spc_end_date = p_row.getSpcEndDate();
    spc_end_date_is_set = p_row.getSpcEndDateIsSet();
    spc_end_date_is_modified = p_row.getSpcEndDateIsModified();
    if ( !p_row.getSpcChargeRatioIsNull() )
      spc_charge_ratio = new Double( p_row.getSpcChargeRatio() );
    spc_charge_ratio_is_set = p_row.getSpcChargeRatioIsSet();
    spc_charge_ratio_is_modified = p_row.getSpcChargeRatioIsModified();
    if ( !p_row.getSpcMaxCommissionIsNull() )
      spc_max_commission = new Double( p_row.getSpcMaxCommission() );
    spc_max_commission_is_set = p_row.getSpcMaxCommissionIsSet();
    spc_max_commission_is_modified = p_row.getSpcMaxCommissionIsModified();
    if ( !p_row.getSpcMinCommissionIsNull() )
      spc_min_commission = new Double( p_row.getSpcMinCommission() );
    spc_min_commission_is_set = p_row.getSpcMinCommissionIsSet();
    spc_min_commission_is_modified = p_row.getSpcMinCommissionIsModified();
    reg_type = p_row.getRegType();
    reg_type_is_set = p_row.getRegTypeIsSet();
    reg_type_is_modified = p_row.getRegTypeIsModified();
    reg_date = p_row.getRegDate();
    reg_date_is_set = p_row.getRegDateIsSet();
    reg_date_is_modified = p_row.getRegDateIsModified();
    final_mod_date = p_row.getFinalModDate();
    final_mod_date_is_set = p_row.getFinalModDateIsSet();
    final_mod_date_is_modified = p_row.getFinalModDateIsModified();
    final_mod_time = p_row.getFinalModTime();
    final_mod_time_is_set = p_row.getFinalModTimeIsSet();
    final_mod_time_is_modified = p_row.getFinalModTimeIsModified();
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
      appli_end_date_is_set = true;
      appli_end_date_is_modified = true;
      max_commission_is_set = true;
      max_commission_is_modified = true;
      min_commission_is_set = true;
      min_commission_is_modified = true;
      share_type_is_set = true;
      share_type_is_modified = true;
      commission_course_div_is_set = true;
      commission_course_div_is_modified = true;
      reference_column_is_set = true;
      reference_column_is_modified = true;
      spc_start_date_is_set = true;
      spc_start_date_is_modified = true;
      spc_end_date_is_set = true;
      spc_end_date_is_modified = true;
      spc_charge_ratio_is_set = true;
      spc_charge_ratio_is_modified = true;
      spc_max_commission_is_set = true;
      spc_max_commission_is_modified = true;
      spc_min_commission_is_set = true;
      spc_min_commission_is_modified = true;
      reg_type_is_set = true;
      reg_type_is_modified = true;
      reg_date_is_set = true;
      reg_date_is_modified = true;
      final_mod_date_is_set = true;
      final_mod_date_is_modified = true;
      final_mod_time_is_set = true;
      final_mod_time_is_modified = true;
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
    if ( !( other instanceof EquityCommCondMstRow ) )
       return false;
    return fieldsEqual( (EquityCommCondMstRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のEquityCommCondMstRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( EquityCommCondMstRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( comm_product_code == null ) {
      if ( row.getCommProductCode() != null )
        return false;
    } else if ( !comm_product_code.equals( row.getCommProductCode() ) ) {
        return false;
    }
    if ( reg_no == null ) {
      if ( row.getRegNo() != null )
        return false;
    } else if ( !reg_no.equals( row.getRegNo() ) ) {
        return false;
    }
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
    if ( max_commission == null ) {
      if ( !row.getMaxCommissionIsNull() )
        return false;
    } else if ( row.getMaxCommissionIsNull() || ( max_commission.doubleValue() != row.getMaxCommission() ) ) {
        return false;
    }
    if ( min_commission != row.getMinCommission() )
      return false;
    if ( share_type == null ) {
      if ( row.getShareType() != null )
        return false;
    } else if ( !share_type.equals( row.getShareType() ) ) {
        return false;
    }
    if ( commission_course_div == null ) {
      if ( row.getCommissionCourseDiv() != null )
        return false;
    } else if ( !commission_course_div.equals( row.getCommissionCourseDiv() ) ) {
        return false;
    }
    if ( reference_column == null ) {
      if ( row.getReferenceColumn() != null )
        return false;
    } else if ( !reference_column.equals( row.getReferenceColumn() ) ) {
        return false;
    }
    if ( spc_start_date == null ) {
      if ( row.getSpcStartDate() != null )
        return false;
    } else if ( !spc_start_date.equals( row.getSpcStartDate() ) ) {
        return false;
    }
    if ( spc_end_date == null ) {
      if ( row.getSpcEndDate() != null )
        return false;
    } else if ( !spc_end_date.equals( row.getSpcEndDate() ) ) {
        return false;
    }
    if ( spc_charge_ratio == null ) {
      if ( !row.getSpcChargeRatioIsNull() )
        return false;
    } else if ( row.getSpcChargeRatioIsNull() || ( spc_charge_ratio.doubleValue() != row.getSpcChargeRatio() ) ) {
        return false;
    }
    if ( spc_max_commission == null ) {
      if ( !row.getSpcMaxCommissionIsNull() )
        return false;
    } else if ( row.getSpcMaxCommissionIsNull() || ( spc_max_commission.doubleValue() != row.getSpcMaxCommission() ) ) {
        return false;
    }
    if ( spc_min_commission == null ) {
      if ( !row.getSpcMinCommissionIsNull() )
        return false;
    } else if ( row.getSpcMinCommissionIsNull() || ( spc_min_commission.doubleValue() != row.getSpcMinCommission() ) ) {
        return false;
    }
    if ( reg_type == null ) {
      if ( row.getRegType() != null )
        return false;
    } else if ( !reg_type.equals( row.getRegType() ) ) {
        return false;
    }
    if ( reg_date == null ) {
      if ( row.getRegDate() != null )
        return false;
    } else if ( !reg_date.equals( row.getRegDate() ) ) {
        return false;
    }
    if ( final_mod_date == null ) {
      if ( row.getFinalModDate() != null )
        return false;
    } else if ( !final_mod_date.equals( row.getFinalModDate() ) ) {
        return false;
    }
    if ( final_mod_time == null ) {
      if ( row.getFinalModTime() != null )
        return false;
    } else if ( !final_mod_time.equals( row.getFinalModTime() ) ) {
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
        + (comm_product_code!=null? comm_product_code.hashCode(): 0) 
        + (reg_no!=null? reg_no.hashCode(): 0) 
        + (appli_start_date!=null? appli_start_date.hashCode(): 0) 
        + (appli_end_date!=null? appli_end_date.hashCode(): 0) 
        + (max_commission!=null? max_commission.hashCode(): 0) 
        + ((int) min_commission)
        + (share_type!=null? share_type.hashCode(): 0) 
        + (commission_course_div!=null? commission_course_div.hashCode(): 0) 
        + (reference_column!=null? reference_column.hashCode(): 0) 
        + (spc_start_date!=null? spc_start_date.hashCode(): 0) 
        + (spc_end_date!=null? spc_end_date.hashCode(): 0) 
        + (spc_charge_ratio!=null? spc_charge_ratio.hashCode(): 0) 
        + (spc_max_commission!=null? spc_max_commission.hashCode(): 0) 
        + (spc_min_commission!=null? spc_min_commission.hashCode(): 0) 
        + (reg_type!=null? reg_type.hashCode(): 0) 
        + (reg_date!=null? reg_date.hashCode(): 0) 
        + (final_mod_date!=null? final_mod_date.hashCode(): 0) 
        + (final_mod_time!=null? final_mod_time.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !appli_end_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'appli_end_date' must be set before inserting.");
		if ( !min_commission_is_set )
			throw new IllegalArgumentException("non-nullable field 'min_commission' must be set before inserting.");
		if ( !commission_course_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'commission_course_div' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("comm_product_code",comm_product_code);
		map.put("reg_no",reg_no);
		map.put("appli_start_date",appli_start_date);
		map.put("appli_end_date",appli_end_date);
		if ( max_commission != null )
			map.put("max_commission",max_commission);
		map.put("min_commission",new Double(min_commission));
		if ( share_type != null )
			map.put("share_type",share_type);
		map.put("commission_course_div",commission_course_div);
		if ( reference_column != null )
			map.put("reference_column",reference_column);
		if ( spc_start_date != null )
			map.put("spc_start_date",spc_start_date);
		if ( spc_end_date != null )
			map.put("spc_end_date",spc_end_date);
		if ( spc_charge_ratio != null )
			map.put("spc_charge_ratio",spc_charge_ratio);
		if ( spc_max_commission != null )
			map.put("spc_max_commission",spc_max_commission);
		if ( spc_min_commission != null )
			map.put("spc_min_commission",spc_min_commission);
		if ( reg_type != null )
			map.put("reg_type",reg_type);
		if ( reg_date != null )
			map.put("reg_date",reg_date);
		if ( final_mod_date != null )
			map.put("final_mod_date",final_mod_date);
		if ( final_mod_time != null )
			map.put("final_mod_time",final_mod_time);
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
		if ( appli_end_date_is_modified )
			map.put("appli_end_date",appli_end_date);
		if ( max_commission_is_modified )
			map.put("max_commission",max_commission);
		if ( min_commission_is_modified )
			map.put("min_commission",new Double(min_commission));
		if ( share_type_is_modified )
			map.put("share_type",share_type);
		if ( commission_course_div_is_modified )
			map.put("commission_course_div",commission_course_div);
		if ( reference_column_is_modified )
			map.put("reference_column",reference_column);
		if ( spc_start_date_is_modified )
			map.put("spc_start_date",spc_start_date);
		if ( spc_end_date_is_modified )
			map.put("spc_end_date",spc_end_date);
		if ( spc_charge_ratio_is_modified )
			map.put("spc_charge_ratio",spc_charge_ratio);
		if ( spc_max_commission_is_modified )
			map.put("spc_max_commission",spc_max_commission);
		if ( spc_min_commission_is_modified )
			map.put("spc_min_commission",spc_min_commission);
		if ( reg_type_is_modified )
			map.put("reg_type",reg_type);
		if ( reg_date_is_modified )
			map.put("reg_date",reg_date);
		if ( final_mod_date_is_modified )
			map.put("final_mod_date",final_mod_date);
		if ( final_mod_time_is_modified )
			map.put("final_mod_time",final_mod_time);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( appli_end_date_is_set )
				map.put("appli_end_date",appli_end_date);
			map.put("max_commission",max_commission);
			if ( min_commission_is_set )
				map.put("min_commission",new Double(min_commission));
			map.put("share_type",share_type);
			if ( commission_course_div_is_set )
				map.put("commission_course_div",commission_course_div);
			map.put("reference_column",reference_column);
			map.put("spc_start_date",spc_start_date);
			map.put("spc_end_date",spc_end_date);
			map.put("spc_charge_ratio",spc_charge_ratio);
			map.put("spc_max_commission",spc_max_commission);
			map.put("spc_min_commission",spc_min_commission);
			map.put("reg_type",reg_type);
			map.put("reg_date",reg_date);
			map.put("final_mod_date",final_mod_date);
			map.put("final_mod_time",final_mod_time);
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
   * <em>comm_product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCommProductCode()
  {
    return comm_product_code;
  }


  /** 
   * <em>comm_product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommProductCodeIsSet() {
    return comm_product_code_is_set;
  }


  /** 
   * <em>comm_product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommProductCodeIsModified() {
    return comm_product_code_is_modified;
  }


  /** 
   * <em>reg_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRegNo()
  {
    return reg_no;
  }


  /** 
   * <em>reg_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRegNoIsSet() {
    return reg_no_is_set;
  }


  /** 
   * <em>reg_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRegNoIsModified() {
    return reg_no_is_modified;
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
   * <em>max_commission</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMaxCommission()
  {
    return ( max_commission==null? ((double)0): max_commission.doubleValue() );
  }


  /** 
   * <em>max_commission</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMaxCommissionIsNull()
  {
    return max_commission == null;
  }


  /** 
   * <em>max_commission</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxCommissionIsSet() {
    return max_commission_is_set;
  }


  /** 
   * <em>max_commission</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxCommissionIsModified() {
    return max_commission_is_modified;
  }


  /** 
   * <em>min_commission</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMinCommission()
  {
    return min_commission;
  }


  /** 
   * <em>min_commission</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMinCommissionIsSet() {
    return min_commission_is_set;
  }


  /** 
   * <em>min_commission</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMinCommissionIsModified() {
    return min_commission_is_modified;
  }


  /** 
   * <em>share_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getShareType()
  {
    return share_type;
  }


  /** 
   * <em>share_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShareTypeIsSet() {
    return share_type_is_set;
  }


  /** 
   * <em>share_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getShareTypeIsModified() {
    return share_type_is_modified;
  }


  /** 
   * <em>commission_course_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCommissionCourseDiv()
  {
    return commission_course_div;
  }


  /** 
   * <em>commission_course_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionCourseDivIsSet() {
    return commission_course_div_is_set;
  }


  /** 
   * <em>commission_course_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionCourseDivIsModified() {
    return commission_course_div_is_modified;
  }


  /** 
   * <em>reference_column</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getReferenceColumn()
  {
    return reference_column;
  }


  /** 
   * <em>reference_column</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReferenceColumnIsSet() {
    return reference_column_is_set;
  }


  /** 
   * <em>reference_column</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReferenceColumnIsModified() {
    return reference_column_is_modified;
  }


  /** 
   * <em>spc_start_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getSpcStartDate()
  {
    return spc_start_date;
  }


  /** 
   * <em>spc_start_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpcStartDateIsSet() {
    return spc_start_date_is_set;
  }


  /** 
   * <em>spc_start_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpcStartDateIsModified() {
    return spc_start_date_is_modified;
  }


  /** 
   * <em>spc_end_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getSpcEndDate()
  {
    return spc_end_date;
  }


  /** 
   * <em>spc_end_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpcEndDateIsSet() {
    return spc_end_date_is_set;
  }


  /** 
   * <em>spc_end_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpcEndDateIsModified() {
    return spc_end_date_is_modified;
  }


  /** 
   * <em>spc_charge_ratio</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSpcChargeRatio()
  {
    return ( spc_charge_ratio==null? ((double)0): spc_charge_ratio.doubleValue() );
  }


  /** 
   * <em>spc_charge_ratio</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSpcChargeRatioIsNull()
  {
    return spc_charge_ratio == null;
  }


  /** 
   * <em>spc_charge_ratio</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpcChargeRatioIsSet() {
    return spc_charge_ratio_is_set;
  }


  /** 
   * <em>spc_charge_ratio</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpcChargeRatioIsModified() {
    return spc_charge_ratio_is_modified;
  }


  /** 
   * <em>spc_max_commission</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSpcMaxCommission()
  {
    return ( spc_max_commission==null? ((double)0): spc_max_commission.doubleValue() );
  }


  /** 
   * <em>spc_max_commission</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSpcMaxCommissionIsNull()
  {
    return spc_max_commission == null;
  }


  /** 
   * <em>spc_max_commission</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpcMaxCommissionIsSet() {
    return spc_max_commission_is_set;
  }


  /** 
   * <em>spc_max_commission</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpcMaxCommissionIsModified() {
    return spc_max_commission_is_modified;
  }


  /** 
   * <em>spc_min_commission</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSpcMinCommission()
  {
    return ( spc_min_commission==null? ((double)0): spc_min_commission.doubleValue() );
  }


  /** 
   * <em>spc_min_commission</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSpcMinCommissionIsNull()
  {
    return spc_min_commission == null;
  }


  /** 
   * <em>spc_min_commission</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpcMinCommissionIsSet() {
    return spc_min_commission_is_set;
  }


  /** 
   * <em>spc_min_commission</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpcMinCommissionIsModified() {
    return spc_min_commission_is_modified;
  }


  /** 
   * <em>reg_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRegType()
  {
    return reg_type;
  }


  /** 
   * <em>reg_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRegTypeIsSet() {
    return reg_type_is_set;
  }


  /** 
   * <em>reg_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRegTypeIsModified() {
    return reg_type_is_modified;
  }


  /** 
   * <em>reg_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getRegDate()
  {
    return reg_date;
  }


  /** 
   * <em>reg_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRegDateIsSet() {
    return reg_date_is_set;
  }


  /** 
   * <em>reg_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRegDateIsModified() {
    return reg_date_is_modified;
  }


  /** 
   * <em>final_mod_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getFinalModDate()
  {
    return final_mod_date;
  }


  /** 
   * <em>final_mod_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinalModDateIsSet() {
    return final_mod_date_is_set;
  }


  /** 
   * <em>final_mod_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinalModDateIsModified() {
    return final_mod_date_is_modified;
  }


  /** 
   * <em>final_mod_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFinalModTime()
  {
    return final_mod_time;
  }


  /** 
   * <em>final_mod_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinalModTimeIsSet() {
    return final_mod_time_is_set;
  }


  /** 
   * <em>final_mod_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinalModTimeIsModified() {
    return final_mod_time_is_modified;
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
    return new EquityCommCondMstPK(institution_code, comm_product_code, reg_no, appli_start_date);
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
   * <em>comm_product_code</em>カラムの値を設定します。 
   *
   * @@param p_commProductCode <em>comm_product_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setCommProductCode( String p_commProductCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comm_product_code = p_commProductCode;
    comm_product_code_is_set = true;
    comm_product_code_is_modified = true;
  }


  /** 
   * <em>reg_no</em>カラムの値を設定します。 
   *
   * @@param p_regNo <em>reg_no</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setRegNo( String p_regNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    reg_no = p_regNo;
    reg_no_is_set = true;
    reg_no_is_modified = true;
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
   * <em>max_commission</em>カラムの値を設定します。 
   *
   * @@param p_maxCommission <em>max_commission</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMaxCommission( double p_maxCommission )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    max_commission = new Double(p_maxCommission);
    max_commission_is_set = true;
    max_commission_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>max_commission</em>カラムに値を設定します。 
   */
  public final void setMaxCommission( Double p_maxCommission )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    max_commission = p_maxCommission;
    max_commission_is_set = true;
    max_commission_is_modified = true;
  }


  /** 
   * <em>min_commission</em>カラムの値を設定します。 
   *
   * @@param p_minCommission <em>min_commission</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMinCommission( double p_minCommission )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    min_commission = p_minCommission;
    min_commission_is_set = true;
    min_commission_is_modified = true;
  }


  /** 
   * <em>share_type</em>カラムの値を設定します。 
   *
   * @@param p_shareType <em>share_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setShareType( String p_shareType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    share_type = p_shareType;
    share_type_is_set = true;
    share_type_is_modified = true;
  }


  /** 
   * <em>commission_course_div</em>カラムの値を設定します。 
   *
   * @@param p_commissionCourseDiv <em>commission_course_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setCommissionCourseDiv( String p_commissionCourseDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commission_course_div = p_commissionCourseDiv;
    commission_course_div_is_set = true;
    commission_course_div_is_modified = true;
  }


  /** 
   * <em>reference_column</em>カラムの値を設定します。 
   *
   * @@param p_referenceColumn <em>reference_column</em>カラムの値をあらわす52桁以下のString値 
   */
  public final void setReferenceColumn( String p_referenceColumn )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    reference_column = p_referenceColumn;
    reference_column_is_set = true;
    reference_column_is_modified = true;
  }


  /** 
   * <em>spc_start_date</em>カラムの値を設定します。 
   *
   * @@param p_spcStartDate <em>spc_start_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setSpcStartDate( java.sql.Timestamp p_spcStartDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    spc_start_date = p_spcStartDate;
    spc_start_date_is_set = true;
    spc_start_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>spc_start_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setSpcStartDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    spc_start_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    spc_start_date_is_set = true;
    spc_start_date_is_modified = true;
  }


  /** 
   * <em>spc_end_date</em>カラムの値を設定します。 
   *
   * @@param p_spcEndDate <em>spc_end_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setSpcEndDate( java.sql.Timestamp p_spcEndDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    spc_end_date = p_spcEndDate;
    spc_end_date_is_set = true;
    spc_end_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>spc_end_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setSpcEndDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    spc_end_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    spc_end_date_is_set = true;
    spc_end_date_is_modified = true;
  }


  /** 
   * <em>spc_charge_ratio</em>カラムの値を設定します。 
   *
   * @@param p_spcChargeRatio <em>spc_charge_ratio</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSpcChargeRatio( double p_spcChargeRatio )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    spc_charge_ratio = new Double(p_spcChargeRatio);
    spc_charge_ratio_is_set = true;
    spc_charge_ratio_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>spc_charge_ratio</em>カラムに値を設定します。 
   */
  public final void setSpcChargeRatio( Double p_spcChargeRatio )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    spc_charge_ratio = p_spcChargeRatio;
    spc_charge_ratio_is_set = true;
    spc_charge_ratio_is_modified = true;
  }


  /** 
   * <em>spc_max_commission</em>カラムの値を設定します。 
   *
   * @@param p_spcMaxCommission <em>spc_max_commission</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSpcMaxCommission( double p_spcMaxCommission )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    spc_max_commission = new Double(p_spcMaxCommission);
    spc_max_commission_is_set = true;
    spc_max_commission_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>spc_max_commission</em>カラムに値を設定します。 
   */
  public final void setSpcMaxCommission( Double p_spcMaxCommission )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    spc_max_commission = p_spcMaxCommission;
    spc_max_commission_is_set = true;
    spc_max_commission_is_modified = true;
  }


  /** 
   * <em>spc_min_commission</em>カラムの値を設定します。 
   *
   * @@param p_spcMinCommission <em>spc_min_commission</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSpcMinCommission( double p_spcMinCommission )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    spc_min_commission = new Double(p_spcMinCommission);
    spc_min_commission_is_set = true;
    spc_min_commission_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>spc_min_commission</em>カラムに値を設定します。 
   */
  public final void setSpcMinCommission( Double p_spcMinCommission )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    spc_min_commission = p_spcMinCommission;
    spc_min_commission_is_set = true;
    spc_min_commission_is_modified = true;
  }


  /** 
   * <em>reg_type</em>カラムの値を設定します。 
   *
   * @@param p_regType <em>reg_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setRegType( String p_regType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    reg_type = p_regType;
    reg_type_is_set = true;
    reg_type_is_modified = true;
  }


  /** 
   * <em>reg_date</em>カラムの値を設定します。 
   *
   * @@param p_regDate <em>reg_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setRegDate( java.sql.Timestamp p_regDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    reg_date = p_regDate;
    reg_date_is_set = true;
    reg_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>reg_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setRegDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    reg_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    reg_date_is_set = true;
    reg_date_is_modified = true;
  }


  /** 
   * <em>final_mod_date</em>カラムの値を設定します。 
   *
   * @@param p_finalModDate <em>final_mod_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setFinalModDate( java.sql.Timestamp p_finalModDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    final_mod_date = p_finalModDate;
    final_mod_date_is_set = true;
    final_mod_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>final_mod_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setFinalModDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    final_mod_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    final_mod_date_is_set = true;
    final_mod_date_is_modified = true;
  }


  /** 
   * <em>final_mod_time</em>カラムの値を設定します。 
   *
   * @@param p_finalModTime <em>final_mod_time</em>カラムの値をあらわす6桁以下のString値 
   */
  public final void setFinalModTime( String p_finalModTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    final_mod_time = p_finalModTime;
    final_mod_time_is_set = true;
    final_mod_time_is_modified = true;
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
                if ( name.equals("appli_start_date") ) {
                    return this.appli_start_date;
                }
                else if ( name.equals("appli_end_date") ) {
                    return this.appli_end_date;
                }
                break;
            case 'c':
                if ( name.equals("comm_product_code") ) {
                    return this.comm_product_code;
                }
                else if ( name.equals("commission_course_div") ) {
                    return this.commission_course_div;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'f':
                if ( name.equals("final_mod_date") ) {
                    return this.final_mod_date;
                }
                else if ( name.equals("final_mod_time") ) {
                    return this.final_mod_time;
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
                if ( name.equals("max_commission") ) {
                    return this.max_commission;
                }
                else if ( name.equals("min_commission") ) {
                    return new Double( min_commission );
                }
                break;
            case 'r':
                if ( name.equals("reg_no") ) {
                    return this.reg_no;
                }
                else if ( name.equals("reference_column") ) {
                    return this.reference_column;
                }
                else if ( name.equals("reg_type") ) {
                    return this.reg_type;
                }
                else if ( name.equals("reg_date") ) {
                    return this.reg_date;
                }
                break;
            case 's':
                if ( name.equals("share_type") ) {
                    return this.share_type;
                }
                else if ( name.equals("spc_start_date") ) {
                    return this.spc_start_date;
                }
                else if ( name.equals("spc_end_date") ) {
                    return this.spc_end_date;
                }
                else if ( name.equals("spc_charge_ratio") ) {
                    return this.spc_charge_ratio;
                }
                else if ( name.equals("spc_max_commission") ) {
                    return this.spc_max_commission;
                }
                else if ( name.equals("spc_min_commission") ) {
                    return this.spc_min_commission;
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
                if ( name.equals("appli_start_date") ) {
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
                break;
            case 'c':
                if ( name.equals("comm_product_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'comm_product_code' must be String: '"+value+"' is not." );
                    this.comm_product_code = (String) value;
                    if (this.comm_product_code_is_set)
                        this.comm_product_code_is_modified = true;
                    this.comm_product_code_is_set = true;
                    return;
                }
                else if ( name.equals("commission_course_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'commission_course_div' must be String: '"+value+"' is not." );
                    this.commission_course_div = (String) value;
                    if (this.commission_course_div_is_set)
                        this.commission_course_div_is_modified = true;
                    this.commission_course_div_is_set = true;
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
                if ( name.equals("final_mod_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'final_mod_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.final_mod_date = (java.sql.Timestamp) value;
                    if (this.final_mod_date_is_set)
                        this.final_mod_date_is_modified = true;
                    this.final_mod_date_is_set = true;
                    return;
                }
                else if ( name.equals("final_mod_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'final_mod_time' must be String: '"+value+"' is not." );
                    this.final_mod_time = (String) value;
                    if (this.final_mod_time_is_set)
                        this.final_mod_time_is_modified = true;
                    this.final_mod_time_is_set = true;
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
                if ( name.equals("max_commission") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'max_commission' must be Double: '"+value+"' is not." );
                    this.max_commission = (Double) value;
                    if (this.max_commission_is_set)
                        this.max_commission_is_modified = true;
                    this.max_commission_is_set = true;
                    return;
                }
                else if ( name.equals("min_commission") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'min_commission' must be Double: '"+value+"' is not." );
                    this.min_commission = ((Double) value).doubleValue();
                    if (this.min_commission_is_set)
                        this.min_commission_is_modified = true;
                    this.min_commission_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("reg_no") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'reg_no' must be String: '"+value+"' is not." );
                    this.reg_no = (String) value;
                    if (this.reg_no_is_set)
                        this.reg_no_is_modified = true;
                    this.reg_no_is_set = true;
                    return;
                }
                else if ( name.equals("reference_column") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'reference_column' must be String: '"+value+"' is not." );
                    this.reference_column = (String) value;
                    if (this.reference_column_is_set)
                        this.reference_column_is_modified = true;
                    this.reference_column_is_set = true;
                    return;
                }
                else if ( name.equals("reg_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'reg_type' must be String: '"+value+"' is not." );
                    this.reg_type = (String) value;
                    if (this.reg_type_is_set)
                        this.reg_type_is_modified = true;
                    this.reg_type_is_set = true;
                    return;
                }
                else if ( name.equals("reg_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'reg_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.reg_date = (java.sql.Timestamp) value;
                    if (this.reg_date_is_set)
                        this.reg_date_is_modified = true;
                    this.reg_date_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("share_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'share_type' must be String: '"+value+"' is not." );
                    this.share_type = (String) value;
                    if (this.share_type_is_set)
                        this.share_type_is_modified = true;
                    this.share_type_is_set = true;
                    return;
                }
                else if ( name.equals("spc_start_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'spc_start_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.spc_start_date = (java.sql.Timestamp) value;
                    if (this.spc_start_date_is_set)
                        this.spc_start_date_is_modified = true;
                    this.spc_start_date_is_set = true;
                    return;
                }
                else if ( name.equals("spc_end_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'spc_end_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.spc_end_date = (java.sql.Timestamp) value;
                    if (this.spc_end_date_is_set)
                        this.spc_end_date_is_modified = true;
                    this.spc_end_date_is_set = true;
                    return;
                }
                else if ( name.equals("spc_charge_ratio") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'spc_charge_ratio' must be Double: '"+value+"' is not." );
                    this.spc_charge_ratio = (Double) value;
                    if (this.spc_charge_ratio_is_set)
                        this.spc_charge_ratio_is_modified = true;
                    this.spc_charge_ratio_is_set = true;
                    return;
                }
                else if ( name.equals("spc_max_commission") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'spc_max_commission' must be Double: '"+value+"' is not." );
                    this.spc_max_commission = (Double) value;
                    if (this.spc_max_commission_is_set)
                        this.spc_max_commission_is_modified = true;
                    this.spc_max_commission_is_set = true;
                    return;
                }
                else if ( name.equals("spc_min_commission") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'spc_min_commission' must be Double: '"+value+"' is not." );
                    this.spc_min_commission = (Double) value;
                    if (this.spc_min_commission_is_set)
                        this.spc_min_commission_is_modified = true;
                    this.spc_min_commission_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
