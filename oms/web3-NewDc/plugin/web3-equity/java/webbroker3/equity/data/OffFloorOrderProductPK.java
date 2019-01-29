head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	OffFloorOrderProductPK.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>OffFloorOrderProduct</b>データベーステーブルで一意である1つのレコードをあらわす<b>OffFloorOrderProduct</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>OffFloorOrderProduct</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see OffFloorOrderProductRow 
 */
public class OffFloorOrderProductPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "off_floor_order_product";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: OffFloorOrderProductRow. 
   */
  public RowType getRowType() {
    return OffFloorOrderProductRow.TYPE;
  }

  /**
   * <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String institution_code;
  /**
   * <em>product_code</em>カラムの値をあらわす10桁以下のString値 
   */
  public String product_code;
  /**
   * <em>market_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public String market_code;
  /**
   * <em>order_end_datetime</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public java.sql.Timestamp order_end_datetime;


  /** 
   * デフォルトコンストラクタ 
   */
  public OffFloorOrderProductPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_productCode <em>product_code</em>カラムの値をあらわす10桁以下のString値 
   * @@param p_marketCode <em>market_code</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_orderEndDatetime <em>order_end_datetime</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public OffFloorOrderProductPK( String p_institutionCode, String p_productCode, String p_marketCode, java.sql.Timestamp p_orderEndDatetime ) {
      institution_code = p_institutionCode;
      product_code = p_productCode;
      market_code = p_marketCode;
      order_end_datetime = p_orderEndDatetime;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static OffFloorOrderProductPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    OffFloorOrderProductPK pk = new OffFloorOrderProductPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.product_code = st.nextToken();
    pk.market_code = st.nextToken();
    pk.order_end_datetime = java.sql.Timestamp.valueOf(st.nextToken());
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + product_code + "," + market_code + "," + com.fitechlabs.xtrade.kernel.util.ThreadSafeDateFormat.getSimpleDateFormat("yyMMddHHmmss").format(order_end_datetime);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof OffFloorOrderProductPK) )
      return false;
    OffFloorOrderProductPK o = (OffFloorOrderProductPK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( product_code == null ) {
      if ( o.product_code != null )
        return false;
    } else if ( !product_code.equals( o.product_code ) ) {
        return false;
    }
    if ( market_code == null ) {
      if ( o.market_code != null )
        return false;
    } else if ( !market_code.equals( o.market_code ) ) {
        return false;
    }
    if ( order_end_datetime == null ) {
      if ( o.order_end_datetime != null )
        return false;
    } else if ( !order_end_datetime.equals( o.order_end_datetime ) ) {
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
        + (product_code!=null? product_code.hashCode(): 0) 
        + (market_code!=null? market_code.hashCode(): 0) 
        + (order_end_datetime!=null? order_end_datetime.hashCode(): 0) 
    ;
  }

}

@
