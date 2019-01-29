head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.30.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	LoginWdogAttributePK.java;


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
 * <b>LoginWdogAttribute</b>データベーステーブルで一意である1つのレコードをあらわす<b>LoginWdogAttribute</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>LoginWdogAttribute</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see LoginWdogAttributeRow 
 */
public class LoginWdogAttributePK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "login_wdog_attribute";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: LoginWdogAttributeRow. 
   */
  public RowType getRowType() {
    return LoginWdogAttributeRow.TYPE;
  }

  /**
   * <em>institution_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public String institution_code;
  /**
   * <em>attribute_name</em>カラムの値をあらわす32桁以下のString値 
   */
  public String attribute_name;
  /**
   * <em>attribute_name_serial_no</em>カラムの値をあらわす6桁以下のint値 
   */
  public int attribute_name_serial_no;


  /** 
   * デフォルトコンストラクタ 
   */
  public LoginWdogAttributePK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_attributeName <em>attribute_name</em>カラムの値をあらわす32桁以下のString値 
   * @@param p_attributeNameSerialNo <em>attribute_name_serial_no</em>カラムの値をあらわす6桁以下のint値 
   */
  public LoginWdogAttributePK( String p_institutionCode, String p_attributeName, int p_attributeNameSerialNo ) {
      institution_code = p_institutionCode;
      attribute_name = p_attributeName;
      attribute_name_serial_no = p_attributeNameSerialNo;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static LoginWdogAttributePK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    LoginWdogAttributePK pk = new LoginWdogAttributePK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.attribute_name = st.nextToken();
    pk.attribute_name_serial_no = Integer.valueOf(st.nextToken()).intValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + attribute_name + "," + String.valueOf(attribute_name_serial_no);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof LoginWdogAttributePK) )
      return false;
    LoginWdogAttributePK o = (LoginWdogAttributePK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( attribute_name == null ) {
      if ( o.attribute_name != null )
        return false;
    } else if ( !attribute_name.equals( o.attribute_name ) ) {
        return false;
    }
    if ( attribute_name_serial_no != o.attribute_name_serial_no )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (institution_code!=null? institution_code.hashCode(): 0) 
        + (attribute_name!=null? attribute_name.hashCode(): 0) 
        + ((int) attribute_name_serial_no)
    ;
  }

}

@
