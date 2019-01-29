head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	HostEqtypeCloseOrderNotifyPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * <b>HostEqtypeCloseOrderNotify</b>データベーステーブルで一意である1つのレコードをあらわす<b>HostEqtypeCloseOrderNotify</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>HostEqtypeCloseOrderNotify</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see HostEqtypeCloseOrderNotifyRow 
 */
public class HostEqtypeCloseOrderNotifyPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "host_eqtype_close_order_notify";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: HostEqtypeCloseOrderNotifyRow. 
   */
  public RowType getRowType() {
    return HostEqtypeCloseOrderNotifyRow.TYPE;
  }

  /**
   * <em>rowid</em>カラムの値をあらわす20桁以下のString値で、その精度は20桁まで
   */
  public String rowid;


  /** 
   * デフォルトコンストラクタ 
   */
  public HostEqtypeCloseOrderNotifyPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_rowid <em>rowid</em>カラムの値をあらわす20桁以下のString値で、その精度は20桁まで
   */
  public HostEqtypeCloseOrderNotifyPK( String p_rowid ) {
      rowid = p_rowid;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static HostEqtypeCloseOrderNotifyPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    return new HostEqtypeCloseOrderNotifyPK( pkValueString );
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
    if ( other == null || !( other instanceof HostEqtypeCloseOrderNotifyPK) )
      return false;
    HostEqtypeCloseOrderNotifyPK o = (HostEqtypeCloseOrderNotifyPK) other;
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
