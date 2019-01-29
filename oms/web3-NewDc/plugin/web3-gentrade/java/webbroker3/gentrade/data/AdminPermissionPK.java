head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.43.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AdminPermissionPK.java;


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
 * <b>AdminPermission</b>データベーステーブルで一意である1つのレコードをあらわす<b>AdminPermission</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>AdminPermission</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AdminPermissionRow 
 */
public class AdminPermissionPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "admin_permission";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: AdminPermissionRow. 
   */
  public RowType getRowType() {
    return AdminPermissionRow.TYPE;
  }

  /**
   * <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String institution_code;
  /**
   * <em>permission_level</em>カラムの値をあらわす3桁以下のString値 
   */
  public String permission_level;
  /**
   * <em>transaction_category</em>カラムの値をあらわす5桁以下のString値 
   */
  public String transaction_category;


  /** 
   * デフォルトコンストラクタ 
   */
  public AdminPermissionPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_permissionLevel <em>permission_level</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_transactionCategory <em>transaction_category</em>カラムの値をあらわす5桁以下のString値 
   */
  public AdminPermissionPK( String p_institutionCode, String p_permissionLevel, String p_transactionCategory ) {
      institution_code = p_institutionCode;
      permission_level = p_permissionLevel;
      transaction_category = p_transactionCategory;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static AdminPermissionPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    AdminPermissionPK pk = new AdminPermissionPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.permission_level = st.nextToken();
    pk.transaction_category = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + permission_level + "," + transaction_category;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof AdminPermissionPK) )
      return false;
    AdminPermissionPK o = (AdminPermissionPK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( permission_level == null ) {
      if ( o.permission_level != null )
        return false;
    } else if ( !permission_level.equals( o.permission_level ) ) {
        return false;
    }
    if ( transaction_category == null ) {
      if ( o.transaction_category != null )
        return false;
    } else if ( !transaction_category.equals( o.transaction_category ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (institution_code!=null? institution_code.hashCode(): 0) 
        + (permission_level!=null? permission_level.hashCode(): 0) 
        + (transaction_category!=null? transaction_category.hashCode(): 0) 
    ;
  }

}

@
