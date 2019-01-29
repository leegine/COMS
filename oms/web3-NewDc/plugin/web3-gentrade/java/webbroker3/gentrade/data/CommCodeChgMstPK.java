head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.21.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	CommCodeChgMstPK.java;


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
 * <b>CommCodeChgMst</b>データベーステーブルで一意である1つのレコードをあらわす<b>CommCodeChgMst</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>CommCodeChgMst</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see CommCodeChgMstRow 
 */
public class CommCodeChgMstPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "comm_code_chg_mst";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: CommCodeChgMstRow. 
   */
  public RowType getRowType() {
    return CommCodeChgMstRow.TYPE;
  }

  /**
   * <em>branch_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long branch_id;
  /**
   * <em>comm_product_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public String comm_product_code;
  /**
   * <em>commission_no</em>カラムの値をあらわす2桁以下のString値 
   */
  public String commission_no;
  /**
   * <em>appli_start_date</em>カラムの値をあらわす8桁以下のString値 
   */
  public String appli_start_date;


  /** 
   * デフォルトコンストラクタ 
   */
  public CommCodeChgMstPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_branchId <em>branch_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_commProductCode <em>comm_product_code</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_commissionNo <em>commission_no</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_appliStartDate <em>appli_start_date</em>カラムの値をあらわす8桁以下のString値 
   */
  public CommCodeChgMstPK( long p_branchId, String p_commProductCode, String p_commissionNo, String p_appliStartDate ) {
      branch_id = p_branchId;
      comm_product_code = p_commProductCode;
      commission_no = p_commissionNo;
      appli_start_date = p_appliStartDate;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static CommCodeChgMstPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    CommCodeChgMstPK pk = new CommCodeChgMstPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.branch_id = Long.valueOf(st.nextToken()).longValue();
    pk.comm_product_code = st.nextToken();
    pk.commission_no = st.nextToken();
    pk.appli_start_date = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(branch_id) + "," + comm_product_code + "," + commission_no + "," + appli_start_date;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof CommCodeChgMstPK) )
      return false;
    CommCodeChgMstPK o = (CommCodeChgMstPK) other;
    if ( branch_id != o.branch_id )
      return false;
    if ( comm_product_code == null ) {
      if ( o.comm_product_code != null )
        return false;
    } else if ( !comm_product_code.equals( o.comm_product_code ) ) {
        return false;
    }
    if ( commission_no == null ) {
      if ( o.commission_no != null )
        return false;
    } else if ( !commission_no.equals( o.commission_no ) ) {
        return false;
    }
    if ( appli_start_date == null ) {
      if ( o.appli_start_date != null )
        return false;
    } else if ( !appli_start_date.equals( o.appli_start_date ) ) {
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
        + (comm_product_code!=null? comm_product_code.hashCode(): 0) 
        + (commission_no!=null? commission_no.hashCode(): 0) 
        + (appli_start_date!=null? appli_start_date.hashCode(): 0) 
    ;
  }

}

@
