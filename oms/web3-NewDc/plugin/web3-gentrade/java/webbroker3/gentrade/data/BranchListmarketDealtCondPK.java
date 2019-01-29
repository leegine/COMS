head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.18.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	BranchListmarketDealtCondPK.java;


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
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>BranchListmarketDealtCond</b>データベーステーブルで一意である1つのレコードをあらわす<b>BranchListmarketDealtCond</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>BranchListmarketDealtCond</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BranchListmarketDealtCondRow 
 */
public class BranchListmarketDealtCondPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "branch_listmarket_dealt_cond";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: BranchListmarketDealtCondRow. 
   */
  public RowType getRowType() {
    return BranchListmarketDealtCondRow.TYPE;
  }

  /**
   * <em>branch_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long branch_id;
  /**
   * <em>market_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long market_id;
  /**
   * <em>list_type</em>カラムの値をあらわす2桁以下のString値 
   */
  public String list_type;
  /**
   * <em>new_list_type</em>カラムの値をあらわす2桁以下のString値 
   */
  public String new_list_type;
  /**
   * <em>open_otc_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public String open_otc_div;


  /** 
   * デフォルトコンストラクタ 
   */
  public BranchListmarketDealtCondPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_branchId <em>branch_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_marketId <em>market_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_listType <em>list_type</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_newListType <em>new_list_type</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_openOtcDiv <em>open_otc_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public BranchListmarketDealtCondPK( long p_branchId, long p_marketId, String p_listType, String p_newListType, String p_openOtcDiv ) {
      branch_id = p_branchId;
      market_id = p_marketId;
      list_type = p_listType;
      new_list_type = p_newListType;
      open_otc_div = p_openOtcDiv;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static BranchListmarketDealtCondPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    BranchListmarketDealtCondPK pk = new BranchListmarketDealtCondPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.branch_id = Long.valueOf(st.nextToken()).longValue();
    pk.market_id = Long.valueOf(st.nextToken()).longValue();
    pk.list_type = st.nextToken();
    pk.new_list_type = st.nextToken();
    pk.open_otc_div = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(branch_id) + "," + String.valueOf(market_id) + "," + list_type + "," + new_list_type + "," + open_otc_div;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof BranchListmarketDealtCondPK) )
      return false;
    BranchListmarketDealtCondPK o = (BranchListmarketDealtCondPK) other;
    if ( branch_id != o.branch_id )
      return false;
    if ( market_id != o.market_id )
      return false;
    if ( list_type == null ) {
      if ( o.list_type != null )
        return false;
    } else if ( !list_type.equals( o.list_type ) ) {
        return false;
    }
    if ( new_list_type == null ) {
      if ( o.new_list_type != null )
        return false;
    } else if ( !new_list_type.equals( o.new_list_type ) ) {
        return false;
    }
    if ( open_otc_div == null ) {
      if ( o.open_otc_div != null )
        return false;
    } else if ( !open_otc_div.equals( o.open_otc_div ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) branch_id)
        + ((int) market_id)
        + (list_type!=null? list_type.hashCode(): 0) 
        + (new_list_type!=null? new_list_type.hashCode(): 0) 
        + (open_otc_div!=null? open_otc_div.hashCode(): 0) 
    ;
  }

}

@
