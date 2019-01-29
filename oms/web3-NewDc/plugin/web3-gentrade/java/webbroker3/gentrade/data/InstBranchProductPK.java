head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.33.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	InstBranchProductPK.java;


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
 * <b>InstBranchProduct</b>データベーステーブルで一意である1つのレコードをあらわす<b>InstBranchProduct</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>InstBranchProduct</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see InstBranchProductRow 
 */
public class InstBranchProductPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "inst_branch_product";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: InstBranchProductRow. 
   */
  public RowType getRowType() {
    return InstBranchProductRow.TYPE;
  }

  /**
   * <em>branch_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long branch_id;
  /**
   * <em>commission_product_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public String commission_product_code;


  /** 
   * デフォルトコンストラクタ 
   */
  public InstBranchProductPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_branchId <em>branch_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_commissionProductCode <em>commission_product_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public InstBranchProductPK( long p_branchId, String p_commissionProductCode ) {
      branch_id = p_branchId;
      commission_product_code = p_commissionProductCode;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static InstBranchProductPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    InstBranchProductPK pk = new InstBranchProductPK();
    int i = pkValueString.indexOf(',');
    pk.branch_id = Long.valueOf(pkValueString.substring(0,i)).longValue();
    pk.commission_product_code = pkValueString.substring(i+1);
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(branch_id) + "," + commission_product_code;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof InstBranchProductPK) )
      return false;
    InstBranchProductPK o = (InstBranchProductPK) other;
    if ( branch_id != o.branch_id )
      return false;
    if ( commission_product_code == null ) {
      if ( o.commission_product_code != null )
        return false;
    } else if ( !commission_product_code.equals( o.commission_product_code ) ) {
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
        + (commission_product_code!=null? commission_product_code.hashCode(): 0) 
    ;
  }

}

@
