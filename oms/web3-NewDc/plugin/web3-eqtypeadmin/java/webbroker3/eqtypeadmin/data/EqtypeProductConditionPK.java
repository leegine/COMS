head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	EqtypeProductConditionPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.eqtypeadmin.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>EqtypeProductCondition</b>データベーステーブルで一意である1つのレコードをあらわす<b>EqtypeProductCondition</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>EqtypeProductCondition</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see EqtypeProductConditionRow 
 */
public class EqtypeProductConditionPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "eqtype_product_condition";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: EqtypeProductConditionRow. 
   */
  public RowType getRowType() {
    return EqtypeProductConditionRow.TYPE;
  }

  /**
   * <em>eqtype_product_condition_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long eqtype_product_condition_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public EqtypeProductConditionPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_eqtypeProductConditionId <em>eqtype_product_condition_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public EqtypeProductConditionPK( long p_eqtypeProductConditionId ) {
      eqtype_product_condition_id = p_eqtypeProductConditionId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static EqtypeProductConditionPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    EqtypeProductConditionPK pk = new EqtypeProductConditionPK();
    pk.eqtype_product_condition_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(eqtype_product_condition_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof EqtypeProductConditionPK) )
      return false;
    EqtypeProductConditionPK o = (EqtypeProductConditionPK) other;
    if ( eqtype_product_condition_id != o.eqtype_product_condition_id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) eqtype_product_condition_id)
    ;
  }

}

@
