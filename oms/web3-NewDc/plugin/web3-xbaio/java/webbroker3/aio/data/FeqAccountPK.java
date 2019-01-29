head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.40.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	FeqAccountPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * <b>FeqAccount</b>データベーステーブルで一意である1つのレコードをあらわす<b>FeqAccount</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>FeqAccount</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see FeqAccountRow 
 */
public class FeqAccountPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "feq_account";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: FeqAccountRow. 
   */
  public RowType getRowType() {
    return FeqAccountRow.TYPE;
  }

  /**
   * <em>feq_account_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long feq_account_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public FeqAccountPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_feqAccountId <em>feq_account_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public FeqAccountPK( long p_feqAccountId ) {
      feq_account_id = p_feqAccountId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static FeqAccountPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    FeqAccountPK pk = new FeqAccountPK();
    pk.feq_account_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(feq_account_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof FeqAccountPK) )
      return false;
    FeqAccountPK o = (FeqAccountPK) other;
    if ( feq_account_id != o.feq_account_id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) feq_account_id)
    ;
  }

}

@
