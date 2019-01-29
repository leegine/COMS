head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.50.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	PointPremiumMasterParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.point.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * point_premium_masterテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link PointPremiumMasterRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link PointPremiumMasterParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link PointPremiumMasterParams}が{@@link PointPremiumMasterRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see PointPremiumMasterPK 
 * @@see PointPremiumMasterRow 
 */
public class PointPremiumMasterParams extends Params implements PointPremiumMasterRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "point_premium_master";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = PointPremiumMasterRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return PointPremiumMasterRow.TYPE;
  }


  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>premium_no</em>カラムの値 
   */
  public  String  premium_no;

  /** 
   * <em>category_no</em>カラムの値 
   */
  public  int  category_no;

  /** 
   * <em>premium_name</em>カラムの値 
   */
  public  String  premium_name;

  /** 
   * <em>required_point</em>カラムの値 
   */
  public  int  required_point;

  /** 
   * <em>start_date_time</em>カラムの値 
   */
  public  java.sql.Timestamp  start_date_time;

  /** 
   * <em>end_date_time</em>カラムの値 
   */
  public  java.sql.Timestamp  end_date_time;

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

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean category_no_is_set = false;
  private boolean category_no_is_modified = false;
  private boolean premium_no_is_set = false;
  private boolean premium_no_is_modified = false;
  private boolean premium_name_is_set = false;
  private boolean premium_name_is_modified = false;
  private boolean required_point_is_set = false;
  private boolean required_point_is_modified = false;
  private boolean start_date_time_is_set = false;
  private boolean start_date_time_is_modified = false;
  private boolean end_date_time_is_set = false;
  private boolean end_date_time_is_modified = false;
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
       + "institution_code=" + institution_code
      + "," + "premium_no=" + premium_no
      + "," + "category_no=" +category_no
      + "," + "premium_name=" +premium_name
      + "," + "required_point=" +required_point
      + "," + "start_date_time=" +start_date_time
      + "," + "end_date_time=" +end_date_time
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のPointPremiumMasterParamsオブジェクトを作成します。 
   */
  public PointPremiumMasterParams() {
    institution_code_is_modified = true;
    premium_no_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のPointPremiumMasterRowオブジェクトの値を利用してPointPremiumMasterParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するPointPremiumMasterRowオブジェクト 
   */
  public PointPremiumMasterParams( PointPremiumMasterRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    premium_no = p_row.getPremiumNo();
    premium_no_is_set = p_row.getPremiumNoIsSet();
    premium_no_is_modified = p_row.getPremiumNoIsModified();
    category_no = p_row.getCategoryNo();
    category_no_is_set = p_row.getCategoryNoIsSet();
    category_no_is_modified = p_row.getCategoryNoIsModified();
    premium_name = p_row.getPremiumName();
    premium_name_is_set = p_row.getPremiumNameIsSet();
    premium_name_is_modified = p_row.getPremiumNameIsModified();
    required_point = p_row.getRequiredPoint();
    required_point_is_set = p_row.getRequiredPointIsSet();
    required_point_is_modified = p_row.getRequiredPointIsModified();
    start_date_time = p_row.getStartDateTime();
    start_date_time_is_set = p_row.getStartDateTimeIsSet();
    start_date_time_is_modified = p_row.getStartDateTimeIsModified();
    end_date_time = p_row.getEndDateTime();
    end_date_time_is_set = p_row.getEndDateTimeIsSet();
    end_date_time_is_modified = p_row.getEndDateTimeIsModified();
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
      category_no_is_set = true;
      category_no_is_modified = true;
      premium_name_is_set = true;
      premium_name_is_modified = true;
      required_point_is_set = true;
      required_point_is_modified = true;
      start_date_time_is_set = true;
      start_date_time_is_modified = true;
      end_date_time_is_set = true;
      end_date_time_is_modified = true;
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
    if ( !( other instanceof PointPremiumMasterRow ) )
       return false;
    return fieldsEqual( (PointPremiumMasterRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のPointPremiumMasterRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( PointPremiumMasterRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( category_no != row.getCategoryNo() )
      return false;
    if ( premium_no == null ) {
      if ( row.getPremiumNo() != null )
        return false;
    } else if ( !premium_no.equals( row.getPremiumNo() ) ) {
        return false;
    }
    if ( premium_name == null ) {
      if ( row.getPremiumName() != null )
        return false;
    } else if ( !premium_name.equals( row.getPremiumName() ) ) {
        return false;
    }
    if ( required_point != row.getRequiredPoint() )
      return false;
    if ( start_date_time == null ) {
      if ( row.getStartDateTime() != null )
        return false;
    } else if ( !start_date_time.equals( row.getStartDateTime() ) ) {
        return false;
    }
    if ( end_date_time == null ) {
      if ( row.getEndDateTime() != null )
        return false;
    } else if ( !end_date_time.equals( row.getEndDateTime() ) ) {
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
      return  (institution_code!=null? institution_code.hashCode(): 0) 
        + ((int) category_no)
        + (premium_no!=null? premium_no.hashCode(): 0) 
        + (premium_name!=null? premium_name.hashCode(): 0) 
        + ((int) required_point)
        + (start_date_time!=null? start_date_time.hashCode(): 0) 
        + (end_date_time!=null? end_date_time.hashCode(): 0) 
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
		if ( !category_no_is_set )
			throw new IllegalArgumentException("non-nullable field 'category_no' must be set before inserting.");
		if ( !premium_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'premium_name' must be set before inserting.");
		if ( !required_point_is_set )
			throw new IllegalArgumentException("non-nullable field 'required_point' must be set before inserting.");
		if ( !start_date_time_is_set )
			throw new IllegalArgumentException("non-nullable field 'start_date_time' must be set before inserting.");
		if ( !end_date_time_is_set )
			throw new IllegalArgumentException("non-nullable field 'end_date_time' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("category_no",new Integer(category_no));
		map.put("premium_no",premium_no);
		map.put("premium_name",premium_name);
		map.put("required_point",new Integer(required_point));
		map.put("start_date_time",start_date_time);
		map.put("end_date_time",end_date_time);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
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
		if ( category_no_is_modified )
			map.put("category_no",new Integer(category_no));
		if ( premium_name_is_modified )
			map.put("premium_name",premium_name);
		if ( required_point_is_modified )
			map.put("required_point",new Integer(required_point));
		if ( start_date_time_is_modified )
			map.put("start_date_time",start_date_time);
		if ( end_date_time_is_modified )
			map.put("end_date_time",end_date_time);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( category_no_is_set )
				map.put("category_no",new Integer(category_no));
			if ( premium_name_is_set )
				map.put("premium_name",premium_name);
			if ( required_point_is_set )
				map.put("required_point",new Integer(required_point));
			if ( start_date_time_is_set )
				map.put("start_date_time",start_date_time);
			if ( end_date_time_is_set )
				map.put("end_date_time",end_date_time);
			map.put("last_updater",last_updater);
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
   * <em>category_no</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getCategoryNo()
  {
    return category_no;
  }


  /** 
   * <em>category_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCategoryNoIsSet() {
    return category_no_is_set;
  }


  /** 
   * <em>category_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCategoryNoIsModified() {
    return category_no_is_modified;
  }


  /** 
   * <em>premium_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPremiumNo()
  {
    return premium_no;
  }


  /** 
   * <em>premium_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPremiumNoIsSet() {
    return premium_no_is_set;
  }


  /** 
   * <em>premium_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPremiumNoIsModified() {
    return premium_no_is_modified;
  }


  /** 
   * <em>premium_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPremiumName()
  {
    return premium_name;
  }


  /** 
   * <em>premium_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPremiumNameIsSet() {
    return premium_name_is_set;
  }


  /** 
   * <em>premium_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPremiumNameIsModified() {
    return premium_name_is_modified;
  }


  /** 
   * <em>required_point</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getRequiredPoint()
  {
    return required_point;
  }


  /** 
   * <em>required_point</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRequiredPointIsSet() {
    return required_point_is_set;
  }


  /** 
   * <em>required_point</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRequiredPointIsModified() {
    return required_point_is_modified;
  }


  /** 
   * <em>start_date_time</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getStartDateTime()
  {
    return start_date_time;
  }


  /** 
   * <em>start_date_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStartDateTimeIsSet() {
    return start_date_time_is_set;
  }


  /** 
   * <em>start_date_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStartDateTimeIsModified() {
    return start_date_time_is_modified;
  }


  /** 
   * <em>end_date_time</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getEndDateTime()
  {
    return end_date_time;
  }


  /** 
   * <em>end_date_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEndDateTimeIsSet() {
    return end_date_time_is_set;
  }


  /** 
   * <em>end_date_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEndDateTimeIsModified() {
    return end_date_time_is_modified;
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
    return new PointPremiumMasterPK(institution_code, premium_no);
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
   * <em>category_no</em>カラムの値を設定します。 
   *
   * @@param p_categoryNo <em>category_no</em>カラムの値をあらわす6桁以下のint値 
   */
  public final void setCategoryNo( int p_categoryNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    category_no = p_categoryNo;
    category_no_is_set = true;
    category_no_is_modified = true;
  }


  /** 
   * <em>premium_no</em>カラムの値を設定します。 
   *
   * @@param p_premiumNo <em>premium_no</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setPremiumNo( String p_premiumNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    premium_no = p_premiumNo;
    premium_no_is_set = true;
    premium_no_is_modified = true;
  }


  /** 
   * <em>premium_name</em>カラムの値を設定します。 
   *
   * @@param p_premiumName <em>premium_name</em>カラムの値をあらわす80桁以下のString値 
   */
  public final void setPremiumName( String p_premiumName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    premium_name = p_premiumName;
    premium_name_is_set = true;
    premium_name_is_modified = true;
  }


  /** 
   * <em>required_point</em>カラムの値を設定します。 
   *
   * @@param p_requiredPoint <em>required_point</em>カラムの値をあらわす8桁以下のint値 
   */
  public final void setRequiredPoint( int p_requiredPoint )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    required_point = p_requiredPoint;
    required_point_is_set = true;
    required_point_is_modified = true;
  }


  /** 
   * <em>start_date_time</em>カラムの値を設定します。 
   *
   * @@param p_startDateTime <em>start_date_time</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setStartDateTime( java.sql.Timestamp p_startDateTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    start_date_time = p_startDateTime;
    start_date_time_is_set = true;
    start_date_time_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>start_date_time</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setStartDateTime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    start_date_time = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    start_date_time_is_set = true;
    start_date_time_is_modified = true;
  }


  /** 
   * <em>end_date_time</em>カラムの値を設定します。 
   *
   * @@param p_endDateTime <em>end_date_time</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setEndDateTime( java.sql.Timestamp p_endDateTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    end_date_time = p_endDateTime;
    end_date_time_is_set = true;
    end_date_time_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>end_date_time</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setEndDateTime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    end_date_time = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    end_date_time_is_set = true;
    end_date_time_is_modified = true;
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
            case 'c':
                if ( name.equals("category_no") ) {
                    return new Integer( category_no );
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("end_date_time") ) {
                    return this.end_date_time;
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
            case 'p':
                if ( name.equals("premium_no") ) {
                    return this.premium_no;
                }
                else if ( name.equals("premium_name") ) {
                    return this.premium_name;
                }
                break;
            case 'r':
                if ( name.equals("required_point") ) {
                    return new Integer( required_point );
                }
                break;
            case 's':
                if ( name.equals("start_date_time") ) {
                    return this.start_date_time;
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
            case 'c':
                if ( name.equals("category_no") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'category_no' must be Integer: '"+value+"' is not." );
                    this.category_no = ((Integer) value).intValue();
                    if (this.category_no_is_set)
                        this.category_no_is_modified = true;
                    this.category_no_is_set = true;
                    return;
                }
                else if ( name.equals("created_timestamp") ) {
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
                if ( name.equals("end_date_time") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'end_date_time' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.end_date_time = (java.sql.Timestamp) value;
                    if (this.end_date_time_is_set)
                        this.end_date_time_is_modified = true;
                    this.end_date_time_is_set = true;
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
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'last_updater' must be String: '"+value+"' is not." );
                    this.last_updater = (String) value;
                    if (this.last_updater_is_set)
                        this.last_updater_is_modified = true;
                    this.last_updater_is_set = true;
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
            case 'p':
                if ( name.equals("premium_no") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'premium_no' must be String: '"+value+"' is not." );
                    this.premium_no = (String) value;
                    if (this.premium_no_is_set)
                        this.premium_no_is_modified = true;
                    this.premium_no_is_set = true;
                    return;
                }
                else if ( name.equals("premium_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'premium_name' must be String: '"+value+"' is not." );
                    this.premium_name = (String) value;
                    if (this.premium_name_is_set)
                        this.premium_name_is_modified = true;
                    this.premium_name_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("required_point") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'required_point' must be Integer: '"+value+"' is not." );
                    this.required_point = ((Integer) value).intValue();
                    if (this.required_point_is_set)
                        this.required_point_is_modified = true;
                    this.required_point_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("start_date_time") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'start_date_time' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.start_date_time = (java.sql.Timestamp) value;
                    if (this.start_date_time_is_set)
                        this.start_date_time_is_modified = true;
                    this.start_date_time_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
