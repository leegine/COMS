head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.18.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	BatchPreferencesPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>BatchPreferences</b>データベーステーブルで一意である1つのレコードをあらわす<b>BatchPreferences</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>BatchPreferences</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BatchPreferencesRow 
 */
public class BatchPreferencesPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "batch_preferences";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: BatchPreferencesRow. 
   */
  public RowType getRowType() {
    return BatchPreferencesRow.TYPE;
  }

  /**
   * <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String institution_code;
  /**
   * <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String branch_code;
  /**
   * <em>name</em>カラムの値をあらわす200桁以下のString値 
   */
  public String name;
  /**
   * <em>name_serial_no</em>カラムの値をあらわす6桁以下のint値 
   */
  public int name_serial_no;


  /** 
   * デフォルトコンストラクタ 
   */
  public BatchPreferencesPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_name <em>name</em>カラムの値をあらわす200桁以下のString値 
   * @@param p_nameSerialNo <em>name_serial_no</em>カラムの値をあらわす6桁以下のint値 
   */
  public BatchPreferencesPK( String p_institutionCode, String p_branchCode, String p_name, int p_nameSerialNo ) {
      institution_code = p_institutionCode;
      branch_code = p_branchCode;
      name = p_name;
      name_serial_no = p_nameSerialNo;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static BatchPreferencesPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    BatchPreferencesPK pk = new BatchPreferencesPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.branch_code = st.nextToken();
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
      m_id = institution_code + "," + branch_code + "," + name + "," + String.valueOf(name_serial_no);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof BatchPreferencesPK) )
      return false;
    BatchPreferencesPK o = (BatchPreferencesPK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( branch_code == null ) {
      if ( o.branch_code != null )
        return false;
    } else if ( !branch_code.equals( o.branch_code ) ) {
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
    return (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (name!=null? name.hashCode(): 0) 
        + ((int) name_serial_no)
    ;
  }

}

@
