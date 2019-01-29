head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.11.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	DisplayContentsParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.pvinfo.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * display_contentsテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link DisplayContentsRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link DisplayContentsParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link DisplayContentsParams}が{@@link DisplayContentsRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see DisplayContentsPK 
 * @@see DisplayContentsRow 
 */
public class DisplayContentsParams extends Params implements DisplayContentsRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "display_contents";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = DisplayContentsRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return DisplayContentsRow.TYPE;
  }


  /** 
   * <em>display_contents_id</em>カラムの値 
   */
  public  long  display_contents_id;

  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>branch_code</em>カラムの値 
   */
  public  String  branch_code;

  /** 
   * <em>condition_no</em>カラムの値 
   */
  public  String  condition_no;

  /** 
   * <em>priority_div</em>カラムの値 
   */
  public  String  priority_div;

  /** 
   * <em>term_from</em>カラムの値 
   */
  public  java.sql.Timestamp  term_from;

  /** 
   * <em>term_to</em>カラムの値 
   */
  public  java.sql.Timestamp  term_to;

  /** 
   * <em>display_title</em>カラムの値 
   */
  public  String  display_title;

  /** 
   * <em>display_message</em>カラムの値 
   */
  public  String  display_message;

  /** 
   * <em>display_color</em>カラムの値 
   */
  public  String  display_color;

  /** 
   * <em>blink_display_flag</em>カラムの値 
   */
  public  String  blink_display_flag;

  /** 
   * <em>display_url</em>カラムの値 
   */
  public  String  display_url;

  /** 
   * <em>last_update_time_display_flag</em>カラムの値 
   */
  public  String  last_update_time_display_flag;

  /** 
   * <em>effective_flag</em>カラムの値 
   */
  public  String  effective_flag;

  /** 
   * <em>display_device</em>カラムの値 
   */
  public  String  display_device;

  /** 
   * <em>last_update_member</em>カラムの値 
   */
  public  String  last_update_member;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean display_contents_id_is_set = false;
  private boolean display_contents_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean condition_no_is_set = false;
  private boolean condition_no_is_modified = false;
  private boolean priority_div_is_set = false;
  private boolean priority_div_is_modified = false;
  private boolean term_from_is_set = false;
  private boolean term_from_is_modified = false;
  private boolean term_to_is_set = false;
  private boolean term_to_is_modified = false;
  private boolean display_title_is_set = false;
  private boolean display_title_is_modified = false;
  private boolean display_message_is_set = false;
  private boolean display_message_is_modified = false;
  private boolean display_color_is_set = false;
  private boolean display_color_is_modified = false;
  private boolean blink_display_flag_is_set = false;
  private boolean blink_display_flag_is_modified = false;
  private boolean display_url_is_set = false;
  private boolean display_url_is_modified = false;
  private boolean last_update_time_display_flag_is_set = false;
  private boolean last_update_time_display_flag_is_modified = false;
  private boolean effective_flag_is_set = false;
  private boolean effective_flag_is_modified = false;
  private boolean display_device_is_set = false;
  private boolean display_device_is_modified = false;
  private boolean last_update_member_is_set = false;
  private boolean last_update_member_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "display_contents_id=" + display_contents_id
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "condition_no=" +condition_no
      + "," + "priority_div=" +priority_div
      + "," + "term_from=" +term_from
      + "," + "term_to=" +term_to
      + "," + "display_title=" +display_title
      + "," + "display_message=" +display_message
      + "," + "display_color=" +display_color
      + "," + "blink_display_flag=" +blink_display_flag
      + "," + "display_url=" +display_url
      + "," + "last_update_time_display_flag=" +last_update_time_display_flag
      + "," + "effective_flag=" +effective_flag
      + "," + "display_device=" +display_device
      + "," + "last_update_member=" +last_update_member
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のDisplayContentsParamsオブジェクトを作成します。 
   */
  public DisplayContentsParams() {
    display_contents_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のDisplayContentsRowオブジェクトの値を利用してDisplayContentsParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するDisplayContentsRowオブジェクト 
   */
  public DisplayContentsParams( DisplayContentsRow p_row )
  {
    display_contents_id = p_row.getDisplayContentsId();
    display_contents_id_is_set = p_row.getDisplayContentsIdIsSet();
    display_contents_id_is_modified = p_row.getDisplayContentsIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    condition_no = p_row.getConditionNo();
    condition_no_is_set = p_row.getConditionNoIsSet();
    condition_no_is_modified = p_row.getConditionNoIsModified();
    priority_div = p_row.getPriorityDiv();
    priority_div_is_set = p_row.getPriorityDivIsSet();
    priority_div_is_modified = p_row.getPriorityDivIsModified();
    term_from = p_row.getTermFrom();
    term_from_is_set = p_row.getTermFromIsSet();
    term_from_is_modified = p_row.getTermFromIsModified();
    term_to = p_row.getTermTo();
    term_to_is_set = p_row.getTermToIsSet();
    term_to_is_modified = p_row.getTermToIsModified();
    display_title = p_row.getDisplayTitle();
    display_title_is_set = p_row.getDisplayTitleIsSet();
    display_title_is_modified = p_row.getDisplayTitleIsModified();
    display_message = p_row.getDisplayMessage();
    display_message_is_set = p_row.getDisplayMessageIsSet();
    display_message_is_modified = p_row.getDisplayMessageIsModified();
    display_color = p_row.getDisplayColor();
    display_color_is_set = p_row.getDisplayColorIsSet();
    display_color_is_modified = p_row.getDisplayColorIsModified();
    blink_display_flag = p_row.getBlinkDisplayFlag();
    blink_display_flag_is_set = p_row.getBlinkDisplayFlagIsSet();
    blink_display_flag_is_modified = p_row.getBlinkDisplayFlagIsModified();
    display_url = p_row.getDisplayUrl();
    display_url_is_set = p_row.getDisplayUrlIsSet();
    display_url_is_modified = p_row.getDisplayUrlIsModified();
    last_update_time_display_flag = p_row.getLastUpdateTimeDisplayFlag();
    last_update_time_display_flag_is_set = p_row.getLastUpdateTimeDisplayFlagIsSet();
    last_update_time_display_flag_is_modified = p_row.getLastUpdateTimeDisplayFlagIsModified();
    effective_flag = p_row.getEffectiveFlag();
    effective_flag_is_set = p_row.getEffectiveFlagIsSet();
    effective_flag_is_modified = p_row.getEffectiveFlagIsModified();
    display_device = p_row.getDisplayDevice();
    display_device_is_set = p_row.getDisplayDeviceIsSet();
    display_device_is_modified = p_row.getDisplayDeviceIsModified();
    last_update_member = p_row.getLastUpdateMember();
    last_update_member_is_set = p_row.getLastUpdateMemberIsSet();
    last_update_member_is_modified = p_row.getLastUpdateMemberIsModified();
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
      condition_no_is_set = true;
      condition_no_is_modified = true;
      priority_div_is_set = true;
      priority_div_is_modified = true;
      term_from_is_set = true;
      term_from_is_modified = true;
      term_to_is_set = true;
      term_to_is_modified = true;
      display_title_is_set = true;
      display_title_is_modified = true;
      display_message_is_set = true;
      display_message_is_modified = true;
      display_color_is_set = true;
      display_color_is_modified = true;
      blink_display_flag_is_set = true;
      blink_display_flag_is_modified = true;
      display_url_is_set = true;
      display_url_is_modified = true;
      last_update_time_display_flag_is_set = true;
      last_update_time_display_flag_is_modified = true;
      effective_flag_is_set = true;
      effective_flag_is_modified = true;
      display_device_is_set = true;
      display_device_is_modified = true;
      last_update_member_is_set = true;
      last_update_member_is_modified = true;
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
    if ( !( other instanceof DisplayContentsRow ) )
       return false;
    return fieldsEqual( (DisplayContentsRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のDisplayContentsRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( DisplayContentsRow row )
  {
    if ( display_contents_id != row.getDisplayContentsId() )
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
    if ( condition_no == null ) {
      if ( row.getConditionNo() != null )
        return false;
    } else if ( !condition_no.equals( row.getConditionNo() ) ) {
        return false;
    }
    if ( priority_div == null ) {
      if ( row.getPriorityDiv() != null )
        return false;
    } else if ( !priority_div.equals( row.getPriorityDiv() ) ) {
        return false;
    }
    if ( term_from == null ) {
      if ( row.getTermFrom() != null )
        return false;
    } else if ( !term_from.equals( row.getTermFrom() ) ) {
        return false;
    }
    if ( term_to == null ) {
      if ( row.getTermTo() != null )
        return false;
    } else if ( !term_to.equals( row.getTermTo() ) ) {
        return false;
    }
    if ( display_title == null ) {
      if ( row.getDisplayTitle() != null )
        return false;
    } else if ( !display_title.equals( row.getDisplayTitle() ) ) {
        return false;
    }
    if ( display_message == null ) {
      if ( row.getDisplayMessage() != null )
        return false;
    } else if ( !display_message.equals( row.getDisplayMessage() ) ) {
        return false;
    }
    if ( display_color == null ) {
      if ( row.getDisplayColor() != null )
        return false;
    } else if ( !display_color.equals( row.getDisplayColor() ) ) {
        return false;
    }
    if ( blink_display_flag == null ) {
      if ( row.getBlinkDisplayFlag() != null )
        return false;
    } else if ( !blink_display_flag.equals( row.getBlinkDisplayFlag() ) ) {
        return false;
    }
    if ( display_url == null ) {
      if ( row.getDisplayUrl() != null )
        return false;
    } else if ( !display_url.equals( row.getDisplayUrl() ) ) {
        return false;
    }
    if ( last_update_time_display_flag == null ) {
      if ( row.getLastUpdateTimeDisplayFlag() != null )
        return false;
    } else if ( !last_update_time_display_flag.equals( row.getLastUpdateTimeDisplayFlag() ) ) {
        return false;
    }
    if ( effective_flag == null ) {
      if ( row.getEffectiveFlag() != null )
        return false;
    } else if ( !effective_flag.equals( row.getEffectiveFlag() ) ) {
        return false;
    }
    if ( display_device == null ) {
      if ( row.getDisplayDevice() != null )
        return false;
    } else if ( !display_device.equals( row.getDisplayDevice() ) ) {
        return false;
    }
    if ( last_update_member == null ) {
      if ( row.getLastUpdateMember() != null )
        return false;
    } else if ( !last_update_member.equals( row.getLastUpdateMember() ) ) {
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
      return  ((int) display_contents_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (condition_no!=null? condition_no.hashCode(): 0) 
        + (priority_div!=null? priority_div.hashCode(): 0) 
        + (term_from!=null? term_from.hashCode(): 0) 
        + (term_to!=null? term_to.hashCode(): 0) 
        + (display_title!=null? display_title.hashCode(): 0) 
        + (display_message!=null? display_message.hashCode(): 0) 
        + (display_color!=null? display_color.hashCode(): 0) 
        + (blink_display_flag!=null? blink_display_flag.hashCode(): 0) 
        + (display_url!=null? display_url.hashCode(): 0) 
        + (last_update_time_display_flag!=null? last_update_time_display_flag.hashCode(): 0) 
        + (effective_flag!=null? effective_flag.hashCode(): 0) 
        + (display_device!=null? display_device.hashCode(): 0) 
        + (last_update_member!=null? last_update_member.hashCode(): 0) 
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
		if ( !condition_no_is_set )
			throw new IllegalArgumentException("non-nullable field 'condition_no' must be set before inserting.");
		if ( !display_title_is_set )
			throw new IllegalArgumentException("non-nullable field 'display_title' must be set before inserting.");
		if ( !display_message_is_set )
			throw new IllegalArgumentException("non-nullable field 'display_message' must be set before inserting.");
		if ( !last_update_time_display_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_update_time_display_flag' must be set before inserting.");
		if ( !effective_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'effective_flag' must be set before inserting.");
		if ( !display_device_is_set )
			throw new IllegalArgumentException("non-nullable field 'display_device' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("display_contents_id",new Long(display_contents_id));
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("condition_no",condition_no);
		if ( priority_div != null )
			map.put("priority_div",priority_div);
		if ( term_from != null )
			map.put("term_from",term_from);
		if ( term_to != null )
			map.put("term_to",term_to);
		map.put("display_title",display_title);
		map.put("display_message",display_message);
		if ( display_color != null )
			map.put("display_color",display_color);
		if ( blink_display_flag != null )
			map.put("blink_display_flag",blink_display_flag);
		if ( display_url != null )
			map.put("display_url",display_url);
		map.put("last_update_time_display_flag",last_update_time_display_flag);
		map.put("effective_flag",effective_flag);
		map.put("display_device",display_device);
		if ( last_update_member != null )
			map.put("last_update_member",last_update_member);
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
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( branch_code_is_modified )
			map.put("branch_code",branch_code);
		if ( condition_no_is_modified )
			map.put("condition_no",condition_no);
		if ( priority_div_is_modified )
			map.put("priority_div",priority_div);
		if ( term_from_is_modified )
			map.put("term_from",term_from);
		if ( term_to_is_modified )
			map.put("term_to",term_to);
		if ( display_title_is_modified )
			map.put("display_title",display_title);
		if ( display_message_is_modified )
			map.put("display_message",display_message);
		if ( display_color_is_modified )
			map.put("display_color",display_color);
		if ( blink_display_flag_is_modified )
			map.put("blink_display_flag",blink_display_flag);
		if ( display_url_is_modified )
			map.put("display_url",display_url);
		if ( last_update_time_display_flag_is_modified )
			map.put("last_update_time_display_flag",last_update_time_display_flag);
		if ( effective_flag_is_modified )
			map.put("effective_flag",effective_flag);
		if ( display_device_is_modified )
			map.put("display_device",display_device);
		if ( last_update_member_is_modified )
			map.put("last_update_member",last_update_member);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( branch_code_is_set )
				map.put("branch_code",branch_code);
			if ( condition_no_is_set )
				map.put("condition_no",condition_no);
			map.put("priority_div",priority_div);
			map.put("term_from",term_from);
			map.put("term_to",term_to);
			if ( display_title_is_set )
				map.put("display_title",display_title);
			if ( display_message_is_set )
				map.put("display_message",display_message);
			map.put("display_color",display_color);
			map.put("blink_display_flag",blink_display_flag);
			map.put("display_url",display_url);
			if ( last_update_time_display_flag_is_set )
				map.put("last_update_time_display_flag",last_update_time_display_flag);
			if ( effective_flag_is_set )
				map.put("effective_flag",effective_flag);
			if ( display_device_is_set )
				map.put("display_device",display_device);
			map.put("last_update_member",last_update_member);
			map.put("created_timestamp",created_timestamp);
			map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>display_contents_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getDisplayContentsId()
  {
    return display_contents_id;
  }


  /** 
   * <em>display_contents_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDisplayContentsIdIsSet() {
    return display_contents_id_is_set;
  }


  /** 
   * <em>display_contents_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDisplayContentsIdIsModified() {
    return display_contents_id_is_modified;
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
   * <em>condition_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getConditionNo()
  {
    return condition_no;
  }


  /** 
   * <em>condition_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConditionNoIsSet() {
    return condition_no_is_set;
  }


  /** 
   * <em>condition_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConditionNoIsModified() {
    return condition_no_is_modified;
  }


  /** 
   * <em>priority_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPriorityDiv()
  {
    return priority_div;
  }


  /** 
   * <em>priority_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPriorityDivIsSet() {
    return priority_div_is_set;
  }


  /** 
   * <em>priority_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPriorityDivIsModified() {
    return priority_div_is_modified;
  }


  /** 
   * <em>term_from</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getTermFrom()
  {
    return term_from;
  }


  /** 
   * <em>term_from</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTermFromIsSet() {
    return term_from_is_set;
  }


  /** 
   * <em>term_from</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTermFromIsModified() {
    return term_from_is_modified;
  }


  /** 
   * <em>term_to</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getTermTo()
  {
    return term_to;
  }


  /** 
   * <em>term_to</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTermToIsSet() {
    return term_to_is_set;
  }


  /** 
   * <em>term_to</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTermToIsModified() {
    return term_to_is_modified;
  }


  /** 
   * <em>display_title</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDisplayTitle()
  {
    return display_title;
  }


  /** 
   * <em>display_title</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDisplayTitleIsSet() {
    return display_title_is_set;
  }


  /** 
   * <em>display_title</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDisplayTitleIsModified() {
    return display_title_is_modified;
  }


  /** 
   * <em>display_message</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDisplayMessage()
  {
    return display_message;
  }


  /** 
   * <em>display_message</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDisplayMessageIsSet() {
    return display_message_is_set;
  }


  /** 
   * <em>display_message</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDisplayMessageIsModified() {
    return display_message_is_modified;
  }


  /** 
   * <em>display_color</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDisplayColor()
  {
    return display_color;
  }


  /** 
   * <em>display_color</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDisplayColorIsSet() {
    return display_color_is_set;
  }


  /** 
   * <em>display_color</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDisplayColorIsModified() {
    return display_color_is_modified;
  }


  /** 
   * <em>blink_display_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBlinkDisplayFlag()
  {
    return blink_display_flag;
  }


  /** 
   * <em>blink_display_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBlinkDisplayFlagIsSet() {
    return blink_display_flag_is_set;
  }


  /** 
   * <em>blink_display_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBlinkDisplayFlagIsModified() {
    return blink_display_flag_is_modified;
  }


  /** 
   * <em>display_url</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDisplayUrl()
  {
    return display_url;
  }


  /** 
   * <em>display_url</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDisplayUrlIsSet() {
    return display_url_is_set;
  }


  /** 
   * <em>display_url</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDisplayUrlIsModified() {
    return display_url_is_modified;
  }


  /** 
   * <em>last_update_time_display_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLastUpdateTimeDisplayFlag()
  {
    return last_update_time_display_flag;
  }


  /** 
   * <em>last_update_time_display_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdateTimeDisplayFlagIsSet() {
    return last_update_time_display_flag_is_set;
  }


  /** 
   * <em>last_update_time_display_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdateTimeDisplayFlagIsModified() {
    return last_update_time_display_flag_is_modified;
  }


  /** 
   * <em>effective_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getEffectiveFlag()
  {
    return effective_flag;
  }


  /** 
   * <em>effective_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEffectiveFlagIsSet() {
    return effective_flag_is_set;
  }


  /** 
   * <em>effective_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEffectiveFlagIsModified() {
    return effective_flag_is_modified;
  }


  /** 
   * <em>display_device</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDisplayDevice()
  {
    return display_device;
  }


  /** 
   * <em>display_device</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDisplayDeviceIsSet() {
    return display_device_is_set;
  }


  /** 
   * <em>display_device</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDisplayDeviceIsModified() {
    return display_device_is_modified;
  }


  /** 
   * <em>last_update_member</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLastUpdateMember()
  {
    return last_update_member;
  }


  /** 
   * <em>last_update_member</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdateMemberIsSet() {
    return last_update_member_is_set;
  }


  /** 
   * <em>last_update_member</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdateMemberIsModified() {
    return last_update_member_is_modified;
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
    return new DisplayContentsPK(display_contents_id);
  }


  /** 
   * <em>display_contents_id</em>カラムの値を設定します。 
   *
   * @@param p_displayContentsId <em>display_contents_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setDisplayContentsId( long p_displayContentsId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    display_contents_id = p_displayContentsId;
    display_contents_id_is_set = true;
    display_contents_id_is_modified = true;
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
   * <em>condition_no</em>カラムの値を設定します。 
   *
   * @@param p_conditionNo <em>condition_no</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setConditionNo( String p_conditionNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    condition_no = p_conditionNo;
    condition_no_is_set = true;
    condition_no_is_modified = true;
  }


  /** 
   * <em>priority_div</em>カラムの値を設定します。 
   *
   * @@param p_priorityDiv <em>priority_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPriorityDiv( String p_priorityDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    priority_div = p_priorityDiv;
    priority_div_is_set = true;
    priority_div_is_modified = true;
  }


  /** 
   * <em>term_from</em>カラムの値を設定します。 
   *
   * @@param p_termFrom <em>term_from</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setTermFrom( java.sql.Timestamp p_termFrom )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    term_from = p_termFrom;
    term_from_is_set = true;
    term_from_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>term_from</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setTermFrom( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    term_from = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    term_from_is_set = true;
    term_from_is_modified = true;
  }


  /** 
   * <em>term_to</em>カラムの値を設定します。 
   *
   * @@param p_termTo <em>term_to</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setTermTo( java.sql.Timestamp p_termTo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    term_to = p_termTo;
    term_to_is_set = true;
    term_to_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>term_to</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setTermTo( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    term_to = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    term_to_is_set = true;
    term_to_is_modified = true;
  }


  /** 
   * <em>display_title</em>カラムの値を設定します。 
   *
   * @@param p_displayTitle <em>display_title</em>カラムの値をあらわす100桁以下のString値 
   */
  public final void setDisplayTitle( String p_displayTitle )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    display_title = p_displayTitle;
    display_title_is_set = true;
    display_title_is_modified = true;
  }


  /** 
   * <em>display_message</em>カラムの値を設定します。 
   *
   * @@param p_displayMessage <em>display_message</em>カラムの値をあらわす2000桁以下のString値 
   */
  public final void setDisplayMessage( String p_displayMessage )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    display_message = p_displayMessage;
    display_message_is_set = true;
    display_message_is_modified = true;
  }


  /** 
   * <em>display_color</em>カラムの値を設定します。 
   *
   * @@param p_displayColor <em>display_color</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDisplayColor( String p_displayColor )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    display_color = p_displayColor;
    display_color_is_set = true;
    display_color_is_modified = true;
  }


  /** 
   * <em>blink_display_flag</em>カラムの値を設定します。 
   *
   * @@param p_blinkDisplayFlag <em>blink_display_flag</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setBlinkDisplayFlag( String p_blinkDisplayFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    blink_display_flag = p_blinkDisplayFlag;
    blink_display_flag_is_set = true;
    blink_display_flag_is_modified = true;
  }


  /** 
   * <em>display_url</em>カラムの値を設定します。 
   *
   * @@param p_displayUrl <em>display_url</em>カラムの値をあらわす100桁以下のString値 
   */
  public final void setDisplayUrl( String p_displayUrl )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    display_url = p_displayUrl;
    display_url_is_set = true;
    display_url_is_modified = true;
  }


  /** 
   * <em>last_update_time_display_flag</em>カラムの値を設定します。 
   *
   * @@param p_lastUpdateTimeDisplayFlag <em>last_update_time_display_flag</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setLastUpdateTimeDisplayFlag( String p_lastUpdateTimeDisplayFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_update_time_display_flag = p_lastUpdateTimeDisplayFlag;
    last_update_time_display_flag_is_set = true;
    last_update_time_display_flag_is_modified = true;
  }


  /** 
   * <em>effective_flag</em>カラムの値を設定します。 
   *
   * @@param p_effectiveFlag <em>effective_flag</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setEffectiveFlag( String p_effectiveFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    effective_flag = p_effectiveFlag;
    effective_flag_is_set = true;
    effective_flag_is_modified = true;
  }


  /** 
   * <em>display_device</em>カラムの値を設定します。 
   *
   * @@param p_displayDevice <em>display_device</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDisplayDevice( String p_displayDevice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    display_device = p_displayDevice;
    display_device_is_set = true;
    display_device_is_modified = true;
  }


  /** 
   * <em>last_update_member</em>カラムの値を設定します。 
   *
   * @@param p_lastUpdateMember <em>last_update_member</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setLastUpdateMember( String p_lastUpdateMember )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_update_member = p_lastUpdateMember;
    last_update_member_is_set = true;
    last_update_member_is_modified = true;
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
                else if ( name.equals("blink_display_flag") ) {
                    return this.blink_display_flag;
                }
                break;
            case 'c':
                if ( name.equals("condition_no") ) {
                    return this.condition_no;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("display_contents_id") ) {
                    return new Long( display_contents_id );
                }
                else if ( name.equals("display_title") ) {
                    return this.display_title;
                }
                else if ( name.equals("display_message") ) {
                    return this.display_message;
                }
                else if ( name.equals("display_color") ) {
                    return this.display_color;
                }
                else if ( name.equals("display_url") ) {
                    return this.display_url;
                }
                else if ( name.equals("display_device") ) {
                    return this.display_device;
                }
                break;
            case 'e':
                if ( name.equals("effective_flag") ) {
                    return this.effective_flag;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("last_update_time_display_flag") ) {
                    return this.last_update_time_display_flag;
                }
                else if ( name.equals("last_update_member") ) {
                    return this.last_update_member;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'p':
                if ( name.equals("priority_div") ) {
                    return this.priority_div;
                }
                break;
            case 't':
                if ( name.equals("term_from") ) {
                    return this.term_from;
                }
                else if ( name.equals("term_to") ) {
                    return this.term_to;
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
                else if ( name.equals("blink_display_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'blink_display_flag' must be String: '"+value+"' is not." );
                    this.blink_display_flag = (String) value;
                    if (this.blink_display_flag_is_set)
                        this.blink_display_flag_is_modified = true;
                    this.blink_display_flag_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("condition_no") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'condition_no' must be String: '"+value+"' is not." );
                    this.condition_no = (String) value;
                    if (this.condition_no_is_set)
                        this.condition_no_is_modified = true;
                    this.condition_no_is_set = true;
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
            case 'd':
                if ( name.equals("display_contents_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'display_contents_id' must be Long: '"+value+"' is not." );
                    this.display_contents_id = ((Long) value).longValue();
                    if (this.display_contents_id_is_set)
                        this.display_contents_id_is_modified = true;
                    this.display_contents_id_is_set = true;
                    return;
                }
                else if ( name.equals("display_title") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'display_title' must be String: '"+value+"' is not." );
                    this.display_title = (String) value;
                    if (this.display_title_is_set)
                        this.display_title_is_modified = true;
                    this.display_title_is_set = true;
                    return;
                }
                else if ( name.equals("display_message") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'display_message' must be String: '"+value+"' is not." );
                    this.display_message = (String) value;
                    if (this.display_message_is_set)
                        this.display_message_is_modified = true;
                    this.display_message_is_set = true;
                    return;
                }
                else if ( name.equals("display_color") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'display_color' must be String: '"+value+"' is not." );
                    this.display_color = (String) value;
                    if (this.display_color_is_set)
                        this.display_color_is_modified = true;
                    this.display_color_is_set = true;
                    return;
                }
                else if ( name.equals("display_url") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'display_url' must be String: '"+value+"' is not." );
                    this.display_url = (String) value;
                    if (this.display_url_is_set)
                        this.display_url_is_modified = true;
                    this.display_url_is_set = true;
                    return;
                }
                else if ( name.equals("display_device") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'display_device' must be String: '"+value+"' is not." );
                    this.display_device = (String) value;
                    if (this.display_device_is_set)
                        this.display_device_is_modified = true;
                    this.display_device_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("effective_flag") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'effective_flag' must be String: '"+value+"' is not." );
                    this.effective_flag = (String) value;
                    if (this.effective_flag_is_set)
                        this.effective_flag_is_modified = true;
                    this.effective_flag_is_set = true;
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
                if ( name.equals("last_update_time_display_flag") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'last_update_time_display_flag' must be String: '"+value+"' is not." );
                    this.last_update_time_display_flag = (String) value;
                    if (this.last_update_time_display_flag_is_set)
                        this.last_update_time_display_flag_is_modified = true;
                    this.last_update_time_display_flag_is_set = true;
                    return;
                }
                else if ( name.equals("last_update_member") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'last_update_member' must be String: '"+value+"' is not." );
                    this.last_update_member = (String) value;
                    if (this.last_update_member_is_set)
                        this.last_update_member_is_modified = true;
                    this.last_update_member_is_set = true;
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
                if ( name.equals("priority_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'priority_div' must be String: '"+value+"' is not." );
                    this.priority_div = (String) value;
                    if (this.priority_div_is_set)
                        this.priority_div_is_modified = true;
                    this.priority_div_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("term_from") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'term_from' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.term_from = (java.sql.Timestamp) value;
                    if (this.term_from_is_set)
                        this.term_from_is_modified = true;
                    this.term_from_is_set = true;
                    return;
                }
                else if ( name.equals("term_to") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'term_to' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.term_to = (java.sql.Timestamp) value;
                    if (this.term_to_is_set)
                        this.term_to_is_modified = true;
                    this.term_to_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
