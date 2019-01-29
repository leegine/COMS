head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	HostManagementPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.dirsec.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * <b>HostManagement</b>データベーステーブルで一意である1つのレコードをあらわす<b>HostManagement</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>HostManagement</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see HostManagementRow 
 */
public class HostManagementPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "host_management";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: HostManagementRow. 
   */
  public RowType getRowType() {
    return HostManagementRow.TYPE;
  }

  /**
   * <em>host_table_name</em>カラムの値をあらわす60桁以下のString値 
   */
  public String host_table_name;


  /** 
   * デフォルトコンストラクタ 
   */
  public HostManagementPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_hostTableName <em>host_table_name</em>カラムの値をあらわす60桁以下のString値 
   */
  public HostManagementPK( String p_hostTableName ) {
      host_table_name = p_hostTableName;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static HostManagementPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    return new HostManagementPK( pkValueString );
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    return host_table_name;
  }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof HostManagementPK) )
      return false;
    HostManagementPK o = (HostManagementPK) other;
    if ( host_table_name == null ) {
      if ( o.host_table_name != null )
        return false;
    } else if ( !host_table_name.equals( o.host_table_name ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (host_table_name!=null? host_table_name.hashCode(): 0) 
    ;
  }

}

@
