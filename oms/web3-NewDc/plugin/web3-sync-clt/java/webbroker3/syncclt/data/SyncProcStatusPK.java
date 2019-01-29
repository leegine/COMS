head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.25.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d7db4ef1a89;
filename	SyncProcStatusPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.syncclt.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * <b>SyncProcStatus</b>データベーステーブルで一意である1つのレコードをあらわす<b>SyncProcStatus</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>SyncProcStatus</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SyncProcStatusRow 
 */
public class SyncProcStatusPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "sync_proc_status";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: SyncProcStatusRow. 
   */
  public RowType getRowType() {
    return SyncProcStatusRow.TYPE;
  }

  /**
   * <em>service_name</em>カラムの値をあらわす100桁以下のString値 
   */
  public String service_name;


  /** 
   * デフォルトコンストラクタ 
   */
  public SyncProcStatusPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_serviceName <em>service_name</em>カラムの値をあらわす100桁以下のString値 
   */
  public SyncProcStatusPK( String p_serviceName ) {
      service_name = p_serviceName;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static SyncProcStatusPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    return new SyncProcStatusPK( pkValueString );
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    return service_name;
  }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof SyncProcStatusPK) )
      return false;
    SyncProcStatusPK o = (SyncProcStatusPK) other;
    if ( service_name == null ) {
      if ( o.service_name != null )
        return false;
    } else if ( !service_name.equals( o.service_name ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (service_name!=null? service_name.hashCode(): 0) 
    ;
  }

}

@
