head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.36.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	OtherOrgOutOfSrvWeekPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>OtherOrgOutOfSrvWeek</b>データベーステーブルで一意である1つのレコードをあらわす<b>OtherOrgOutOfSrvWeek</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>OtherOrgOutOfSrvWeek</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see OtherOrgOutOfSrvWeekRow 
 */
public class OtherOrgOutOfSrvWeekPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "other_org_out_of_srv_week";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: OtherOrgOutOfSrvWeekRow. 
   */
  public RowType getRowType() {
    return OtherOrgOutOfSrvWeekRow.TYPE;
  }

  /**
   * <em>other_org_code</em>カラムの値をあらわす11桁以下のString値 
   */
  public String other_org_code;
  /**
   * <em>month</em>カラムの値をあらわす2桁以下のString値 
   */
  public String month;
  /**
   * <em>week_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public String week_div;
  /**
   * <em>week_no</em>カラムの値をあらわす1桁以下のString値 
   */
  public String week_no;
  /**
   * <em>suspension_start_time</em>カラムの値をあらわす6桁以下のString値 
   */
  public String suspension_start_time;
  /**
   * <em>suspension_end_time</em>カラムの値をあらわす6桁以下のString値 
   */
  public String suspension_end_time;


  /** 
   * デフォルトコンストラクタ 
   */
  public OtherOrgOutOfSrvWeekPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_otherOrgCode <em>other_org_code</em>カラムの値をあらわす11桁以下のString値 
   * @@param p_month <em>month</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_weekDiv <em>week_div</em>カラムの値をあらわす1桁以下のString値 
   * @@param p_weekNo <em>week_no</em>カラムの値をあらわす1桁以下のString値 
   * @@param p_suspensionStartTime <em>suspension_start_time</em>カラムの値をあらわす6桁以下のString値 
   * @@param p_suspensionEndTime <em>suspension_end_time</em>カラムの値をあらわす6桁以下のString値 
   */
  public OtherOrgOutOfSrvWeekPK( String p_otherOrgCode, String p_month, String p_weekDiv, String p_weekNo, String p_suspensionStartTime, String p_suspensionEndTime ) {
      other_org_code = p_otherOrgCode;
      month = p_month;
      week_div = p_weekDiv;
      week_no = p_weekNo;
      suspension_start_time = p_suspensionStartTime;
      suspension_end_time = p_suspensionEndTime;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static OtherOrgOutOfSrvWeekPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    OtherOrgOutOfSrvWeekPK pk = new OtherOrgOutOfSrvWeekPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.other_org_code = st.nextToken();
    pk.month = st.nextToken();
    pk.week_div = st.nextToken();
    pk.week_no = st.nextToken();
    pk.suspension_start_time = st.nextToken();
    pk.suspension_end_time = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = other_org_code + "," + month + "," + week_div + "," + week_no + "," + suspension_start_time + "," + suspension_end_time;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof OtherOrgOutOfSrvWeekPK) )
      return false;
    OtherOrgOutOfSrvWeekPK o = (OtherOrgOutOfSrvWeekPK) other;
    if ( other_org_code == null ) {
      if ( o.other_org_code != null )
        return false;
    } else if ( !other_org_code.equals( o.other_org_code ) ) {
        return false;
    }
    if ( month == null ) {
      if ( o.month != null )
        return false;
    } else if ( !month.equals( o.month ) ) {
        return false;
    }
    if ( week_div == null ) {
      if ( o.week_div != null )
        return false;
    } else if ( !week_div.equals( o.week_div ) ) {
        return false;
    }
    if ( week_no == null ) {
      if ( o.week_no != null )
        return false;
    } else if ( !week_no.equals( o.week_no ) ) {
        return false;
    }
    if ( suspension_start_time == null ) {
      if ( o.suspension_start_time != null )
        return false;
    } else if ( !suspension_start_time.equals( o.suspension_start_time ) ) {
        return false;
    }
    if ( suspension_end_time == null ) {
      if ( o.suspension_end_time != null )
        return false;
    } else if ( !suspension_end_time.equals( o.suspension_end_time ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (other_org_code!=null? other_org_code.hashCode(): 0) 
        + (month!=null? month.hashCode(): 0) 
        + (week_div!=null? week_div.hashCode(): 0) 
        + (week_no!=null? week_no.hashCode(): 0) 
        + (suspension_start_time!=null? suspension_start_time.hashCode(): 0) 
        + (suspension_end_time!=null? suspension_end_time.hashCode(): 0) 
    ;
  }

}

@
