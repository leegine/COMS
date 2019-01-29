head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.58.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4d04d7ef1b224f3;
filename	InformDivPreferencesPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.inform.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>InformDivPreferences</b>データベーステーブルで一意である1つのレコードをあらわす<b>InformDivPreferences</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>InformDivPreferences</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see InformDivPreferencesRow 
 */
public class InformDivPreferencesPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "inform_div_preferences";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: InformDivPreferencesRow. 
   */
  public RowType getRowType() {
    return InformDivPreferencesRow.TYPE;
  }

  /**
   * <em>branch_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long branch_id;
  /**
   * <em>inform_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public String inform_div;
  /**
   * <em>name</em>カラムの値をあらわす80桁以下のString値 
   */
  public String name;
  /**
   * <em>name_serial_no</em>カラムの値をあらわす6桁以下のint値 
   */
  public int name_serial_no;


  /** 
   * デフォルトコンストラクタ 
   */
  public InformDivPreferencesPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_branchId <em>branch_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_informDiv <em>inform_div</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_name <em>name</em>カラムの値をあらわす80桁以下のString値 
   * @@param p_nameSerialNo <em>name_serial_no</em>カラムの値をあらわす6桁以下のint値 
   */
  public InformDivPreferencesPK( long p_branchId, String p_informDiv, String p_name, int p_nameSerialNo ) {
      branch_id = p_branchId;
      inform_div = p_informDiv;
      name = p_name;
      name_serial_no = p_nameSerialNo;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static InformDivPreferencesPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    InformDivPreferencesPK pk = new InformDivPreferencesPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.branch_id = Long.valueOf(st.nextToken()).longValue();
    pk.inform_div = st.nextToken();
    pk.name = st.nextToken();
    pk.name_serial_no = Integer.valueOf(st.nextToken()).intValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(branch_id) + "," + inform_div + "," + name + "," + String.valueOf(name_serial_no);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof InformDivPreferencesPK) )
      return false;
    InformDivPreferencesPK o = (InformDivPreferencesPK) other;
    if ( branch_id != o.branch_id )
      return false;
    if ( inform_div == null ) {
      if ( o.inform_div != null )
        return false;
    } else if ( !inform_div.equals( o.inform_div ) ) {
        return false;
    }
    if ( name == null ) {
      if ( o.name != null )
        return false;
    } else if ( !name.equals( o.name ) ) {
        return false;
    }
    if ( name_serial_no != o.name_serial_no )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) branch_id)
        + (inform_div!=null? inform_div.hashCode(): 0) 
        + (name!=null? name.hashCode(): 0) 
        + ((int) name_serial_no)
    ;
  }

}

@
