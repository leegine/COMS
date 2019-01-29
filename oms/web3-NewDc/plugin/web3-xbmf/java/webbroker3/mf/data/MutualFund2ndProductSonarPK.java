head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MutualFund2ndProductSonarPK.java;


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
 * <b>MutualFund2ndProductSonar</b>データベーステーブルで一意である1つのレコードをあらわす<b>MutualFund2ndProductSonar</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>MutualFund2ndProductSonar</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MutualFund2ndProductSonarRow 
 */
public class MutualFund2ndProductSonarPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "mutual_fund_2nd_product_sonar";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: MutualFund2ndProductSonarRow. 
   */
  public RowType getRowType() {
    return MutualFund2ndProductSonarRow.TYPE;
  }

  /**
   * <em>product_code</em>カラムの値をあらわす7桁以下のString値 
   */
  public String product_code;
  /**
   * <em>institution_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public String institution_code;


  /** 
   * デフォルトコンストラクタ 
   */
  public MutualFund2ndProductSonarPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_productCode <em>product_code</em>カラムの値をあらわす7桁以下のString値 
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public MutualFund2ndProductSonarPK( String p_productCode, String p_institutionCode ) {
      product_code = p_productCode;
      institution_code = p_institutionCode;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static MutualFund2ndProductSonarPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    MutualFund2ndProductSonarPK pk = new MutualFund2ndProductSonarPK();
    int i = pkValueString.indexOf(',');
    pk.product_code = pkValueString.substring(0,i);
    pk.institution_code = pkValueString.substring(i+1);
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = product_code + "," + institution_code;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof MutualFund2ndProductSonarPK) )
      return false;
    MutualFund2ndProductSonarPK o = (MutualFund2ndProductSonarPK) other;
    if ( product_code == null ) {
      if ( o.product_code != null )
        return false;
    } else if ( !product_code.equals( o.product_code ) ) {
        return false;
    }
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (product_code!=null? product_code.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
    ;
  }

}

@
