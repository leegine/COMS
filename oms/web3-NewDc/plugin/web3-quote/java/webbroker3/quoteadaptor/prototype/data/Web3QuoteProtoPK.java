head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.42.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	Web3QuoteProtoPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.quoteadaptor.prototype.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * <b>Web3QuoteProto</b>データベーステーブルで一意である1つのレコードをあらわす<b>Web3QuoteProto</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>Web3QuoteProto</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see Web3QuoteProtoRow 
 */
public class Web3QuoteProtoPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "web3_quote_proto";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: Web3QuoteProtoRow. 
   */
  public RowType getRowType() {
    return Web3QuoteProtoRow.TYPE;
  }

  /**
   * <em>quote_data_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long quote_data_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public Web3QuoteProtoPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_quoteDataId <em>quote_data_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public Web3QuoteProtoPK( long p_quoteDataId ) {
      quote_data_id = p_quoteDataId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static Web3QuoteProtoPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    Web3QuoteProtoPK pk = new Web3QuoteProtoPK();
    pk.quote_data_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(quote_data_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof Web3QuoteProtoPK) )
      return false;
    Web3QuoteProtoPK o = (Web3QuoteProtoPK) other;
    if ( quote_data_id != o.quote_data_id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) quote_data_id)
    ;
  }

}

@
