head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.38.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	AmountRangePK.java;


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
 * <b>AmountRange</b>データベーステーブルで一意である1つのレコードをあらわす<b>AmountRange</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>AmountRange</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AmountRangeRow 
 */
public class AmountRangePK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "amount_range";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: AmountRangeRow. 
   */
  public RowType getRowType() {
    return AmountRangeRow.TYPE;
  }

  /**
   * <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String institution_code;
  /**
   * <em>fund_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public String fund_type;
  /**
   * <em>transaction_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public String transaction_type;


  /** 
   * デフォルトコンストラクタ 
   */
  public AmountRangePK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_fundType <em>fund_type</em>カラムの値をあらわす1桁以下のString値 
   * @@param p_transactionType <em>transaction_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public AmountRangePK( String p_institutionCode, String p_fundType, String p_transactionType ) {
      institution_code = p_institutionCode;
      fund_type = p_fundType;
      transaction_type = p_transactionType;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static AmountRangePK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    AmountRangePK pk = new AmountRangePK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.fund_type = st.nextToken();
    pk.transaction_type = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + fund_type + "," + transaction_type;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof AmountRangePK) )
      return false;
    AmountRangePK o = (AmountRangePK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( fund_type == null ) {
      if ( o.fund_type != null )
        return false;
    } else if ( !fund_type.equals( o.fund_type ) ) {
        return false;
    }
    if ( transaction_type == null ) {
      if ( o.transaction_type != null )
        return false;
    } else if ( !transaction_type.equals( o.transaction_type ) ) {
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
        + (fund_type!=null? fund_type.hashCode(): 0) 
        + (transaction_type!=null? transaction_type.hashCode(): 0) 
    ;
  }

}

@
