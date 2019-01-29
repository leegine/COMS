head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	HostFotypeOrderHistoryPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifo.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.*;

/** 
 * <b>HostFotypeOrderHistory</b>データベーステーブルで一意である1つのレコードをあらわす<b>HostFotypeOrderHistory</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>HostFotypeOrderHistory</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see HostFotypeOrderHistoryRow 
 */
public class HostFotypeOrderHistoryPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "host_fotype_order_history";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: HostFotypeOrderHistoryRow. 
   */
  public RowType getRowType() {
    return HostFotypeOrderHistoryRow.TYPE;
  }

  /**
   * <em>rowid</em>カラムの値をあらわす20桁以下のString値で、その精度は20桁まで
   */
  public String rowid;


  /** 
   * デフォルトコンストラクタ 
   */
  public HostFotypeOrderHistoryPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_rowid <em>rowid</em>カラムの値をあらわす20桁以下のString値で、その精度は20桁まで
   */
  public HostFotypeOrderHistoryPK( String p_rowid ) {
      rowid = p_rowid;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static HostFotypeOrderHistoryPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    return new HostFotypeOrderHistoryPK( pkValueString );
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    return rowid;
  }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof HostFotypeOrderHistoryPK) )
      return false;
    HostFotypeOrderHistoryPK o = (HostFotypeOrderHistoryPK) other;
    if ( rowid == null ) {
      if ( o.rowid != null )
        return false;
    } else if ( !rowid.equals( o.rowid ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (rowid!=null? rowid.hashCode(): 0) 
    ;
  }

}

@
