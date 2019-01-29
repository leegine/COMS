head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.37.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	OtherOrgSrvTimePK.java;


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
 * <b>OtherOrgSrvTime</b>データベーステーブルで一意である1つのレコードをあらわす<b>OtherOrgSrvTime</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>OtherOrgSrvTime</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see OtherOrgSrvTimeRow 
 */
public class OtherOrgSrvTimePK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "other_org_srv_time";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: OtherOrgSrvTimeRow. 
   */
  public RowType getRowType() {
    return OtherOrgSrvTimeRow.TYPE;
  }

  /**
   * <em>other_org_code</em>カラムの値をあらわす11桁以下のString値 
   */
  public String other_org_code;
  /**
   * <em>week_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public String week_div;
  /**
   * <em>service_start_time</em>カラムの値をあらわす6桁以下のString値 
   */
  public String service_start_time;
  /**
   * <em>service_end_time</em>カラムの値をあらわす6桁以下のString値 
   */
  public String service_end_time;


  /** 
   * デフォルトコンストラクタ 
   */
  public OtherOrgSrvTimePK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_otherOrgCode <em>other_org_code</em>カラムの値をあらわす11桁以下のString値 
   * @@param p_weekDiv <em>week_div</em>カラムの値をあらわす1桁以下のString値 
   * @@param p_serviceStartTime <em>service_start_time</em>カラムの値をあらわす6桁以下のString値 
   * @@param p_serviceEndTime <em>service_end_time</em>カラムの値をあらわす6桁以下のString値 
   */
  public OtherOrgSrvTimePK( String p_otherOrgCode, String p_weekDiv, String p_serviceStartTime, String p_serviceEndTime ) {
      other_org_code = p_otherOrgCode;
      week_div = p_weekDiv;
      service_start_time = p_serviceStartTime;
      service_end_time = p_serviceEndTime;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static OtherOrgSrvTimePK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    OtherOrgSrvTimePK pk = new OtherOrgSrvTimePK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.other_org_code = st.nextToken();
    pk.week_div = st.nextToken();
    pk.service_start_time = st.nextToken();
    pk.service_end_time = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = other_org_code + "," + week_div + "," + service_start_time + "," + service_end_time;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof OtherOrgSrvTimePK) )
      return false;
    OtherOrgSrvTimePK o = (OtherOrgSrvTimePK) other;
    if ( other_org_code == null ) {
      if ( o.other_org_code != null )
        return false;
    } else if ( !other_org_code.equals( o.other_org_code ) ) {
        return false;
    }
    if ( week_div == null ) {
      if ( o.week_div != null )
        return false;
    } else if ( !week_div.equals( o.week_div ) ) {
        return false;
    }
    if ( service_start_time == null ) {
      if ( o.service_start_time != null )
        return false;
    } else if ( !service_start_time.equals( o.service_start_time ) ) {
        return false;
    }
    if ( service_end_time == null ) {
      if ( o.service_end_time != null )
        return false;
    } else if ( !service_end_time.equals( o.service_end_time ) ) {
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
        + (week_div!=null? week_div.hashCode(): 0) 
        + (service_start_time!=null? service_start_time.hashCode(): 0) 
        + (service_end_time!=null? service_end_time.hashCode(): 0) 
    ;
  }

}

@
