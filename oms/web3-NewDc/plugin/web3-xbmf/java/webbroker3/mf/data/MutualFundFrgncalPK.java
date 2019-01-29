head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MutualFundFrgncalPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>MutualFundFrgncal</b>データベーステーブルで一意である1つのレコードをあらわす<b>MutualFundFrgncal</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>MutualFundFrgncal</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MutualFundFrgncalRow 
 */
public class MutualFundFrgncalPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "mutual_fund_frgncal";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: MutualFundFrgncalRow. 
   */
  public RowType getRowType() {
    return MutualFundFrgncalRow.TYPE;
  }

  /**
   * <em>institution_code</em>カラムの値をあらわす20桁以下のString値 
   */
  public String institution_code;
  /**
   * <em>product_code</em>カラムの値をあらわす10桁以下のString値 
   */
  public String product_code;
  /**
   * <em>holiday</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public java.sql.Timestamp holiday;


  /** 
   * デフォルトコンストラクタ 
   */
  public MutualFundFrgncalPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす20桁以下のString値 
   * @@param p_productCode <em>product_code</em>カラムの値をあらわす10桁以下のString値 
   * @@param p_holiday <em>holiday</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public MutualFundFrgncalPK( String p_institutionCode, String p_productCode, java.sql.Timestamp p_holiday ) {
      institution_code = p_institutionCode;
      product_code = p_productCode;
      holiday = p_holiday;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static MutualFundFrgncalPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    MutualFundFrgncalPK pk = new MutualFundFrgncalPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.product_code = st.nextToken();
    pk.holiday = java.sql.Timestamp.valueOf(st.nextToken());
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + product_code + "," + com.fitechlabs.xtrade.kernel.util.ThreadSafeDateFormat.getSimpleDateFormat("yyMMddHHmmss").format(holiday);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof MutualFundFrgncalPK) )
      return false;
    MutualFundFrgncalPK o = (MutualFundFrgncalPK) other;
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
    if ( holiday == null ) {
      if ( o.holiday != null )
        return false;
    } else if ( !holiday.equals( o.holiday ) ) {
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
        + (holiday!=null? holiday.hashCode(): 0) 
    ;
  }

}

@
