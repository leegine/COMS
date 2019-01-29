head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.51.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8984d7efe084d3b;
filename	IpoBookbuildingPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ipo.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>IpoBookbuilding</b>データベーステーブルで一意である1つのレコードをあらわす<b>IpoBookbuilding</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>IpoBookbuilding</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see IpoBookbuildingRow 
 */
public class IpoBookbuildingPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "ipo_bookbuilding";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: IpoBookbuildingRow. 
   */
  public RowType getRowType() {
    return IpoBookbuildingRow.TYPE;
  }

  /**
   * <em>ipo_product_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long ipo_product_id;
  /**
   * <em>bb_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public String bb_div;
  /**
   * <em>bb_seq</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long bb_seq;
  /**
   * <em>branch_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long branch_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public IpoBookbuildingPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_ipoProductId <em>ipo_product_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_bbDiv <em>bb_div</em>カラムの値をあらわす1桁以下のString値 
   * @@param p_bbSeq <em>bb_seq</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_branchId <em>branch_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public IpoBookbuildingPK( long p_ipoProductId, String p_bbDiv, long p_bbSeq, long p_branchId ) {
      ipo_product_id = p_ipoProductId;
      bb_div = p_bbDiv;
      bb_seq = p_bbSeq;
      branch_id = p_branchId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static IpoBookbuildingPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    IpoBookbuildingPK pk = new IpoBookbuildingPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.ipo_product_id = Long.valueOf(st.nextToken()).longValue();
    pk.bb_div = st.nextToken();
    pk.bb_seq = Long.valueOf(st.nextToken()).longValue();
    pk.branch_id = Long.valueOf(st.nextToken()).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(ipo_product_id) + "," + bb_div + "," + String.valueOf(bb_seq) + "," + String.valueOf(branch_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof IpoBookbuildingPK) )
      return false;
    IpoBookbuildingPK o = (IpoBookbuildingPK) other;
    if ( ipo_product_id != o.ipo_product_id )
      return false;
    if ( bb_div == null ) {
      if ( o.bb_div != null )
        return false;
    } else if ( !bb_div.equals( o.bb_div ) ) {
        return false;
    }
    if ( bb_seq != o.bb_seq )
      return false;
    if ( branch_id != o.branch_id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) ipo_product_id)
        + (bb_div!=null? bb_div.hashCode(): 0) 
        + ((int) bb_seq)
        + ((int) branch_id)
    ;
  }

}

@
