head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.17.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	TraderAccountInfoPK.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>TraderAccountInfo</b>データベーステーブルで一意である1つのレコードをあらわす<b>TraderAccountInfo</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>TraderAccountInfo</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see TraderAccountInfoRow 
 */
public class TraderAccountInfoPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "trader_account_info";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: TraderAccountInfoRow. 
   */
  public RowType getRowType() {
    return TraderAccountInfoRow.TYPE;
  }

  /**
   * <em>trader_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long trader_id;
  /**
   * <em>account_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long account_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public TraderAccountInfoPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_traderId <em>trader_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_accountId <em>account_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public TraderAccountInfoPK( long p_traderId, long p_accountId ) {
      trader_id = p_traderId;
      account_id = p_accountId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static TraderAccountInfoPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    TraderAccountInfoPK pk = new TraderAccountInfoPK();
    int i = pkValueString.indexOf(',');
    pk.trader_id = Long.valueOf(pkValueString.substring(0,i)).longValue();
    pk.account_id = Long.valueOf(pkValueString.substring(i+1)).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(trader_id) + "," + String.valueOf(account_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof TraderAccountInfoPK) )
      return false;
    TraderAccountInfoPK o = (TraderAccountInfoPK) other;
    if ( trader_id != o.trader_id )
      return false;
    if ( account_id != o.account_id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) trader_id)
        + ((int) account_id)
    ;
  }

}

@
