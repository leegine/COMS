head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.39.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	MainAccountAllPK.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>MainAccountAll</b>データベーステーブルで一意である1つのレコードをあらわす<b>MainAccountAll</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>MainAccountAll</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MainAccountAllRow 
 */
public class MainAccountAllPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "main_account_all";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: MainAccountAllRow. 
   */
  public RowType getRowType() {
    return MainAccountAllRow.TYPE;
  }

  /**
   * <em>comp_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public String comp_code;
  /**
   * <em>br_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String br_code;
  /**
   * <em>cust_code</em>カラムの値をあらわす6桁以下のString値 
   */
  public String cust_code;
  /**
   * <em>cust_code_cd</em>カラムの値をあらわす1桁以下のString値 
   */
  public String cust_code_cd;


  /** 
   * デフォルトコンストラクタ 
   */
  public MainAccountAllPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_compCode <em>comp_code</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_brCode <em>br_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_custCode <em>cust_code</em>カラムの値をあらわす6桁以下のString値 
   * @@param p_custCodeCd <em>cust_code_cd</em>カラムの値をあらわす1桁以下のString値 
   */
  public MainAccountAllPK( String p_compCode, String p_brCode, String p_custCode, String p_custCodeCd ) {
      comp_code = p_compCode;
      br_code = p_brCode;
      cust_code = p_custCode;
      cust_code_cd = p_custCodeCd;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static MainAccountAllPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    MainAccountAllPK pk = new MainAccountAllPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.comp_code = st.nextToken();
    pk.br_code = st.nextToken();
    pk.cust_code = st.nextToken();
    pk.cust_code_cd = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = comp_code + "," + br_code + "," + cust_code + "," + cust_code_cd;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof MainAccountAllPK) )
      return false;
    MainAccountAllPK o = (MainAccountAllPK) other;
    if ( comp_code == null ) {
      if ( o.comp_code != null )
        return false;
    } else if ( !comp_code.equals( o.comp_code ) ) {
        return false;
    }
    if ( br_code == null ) {
      if ( o.br_code != null )
        return false;
    } else if ( !br_code.equals( o.br_code ) ) {
        return false;
    }
    if ( cust_code == null ) {
      if ( o.cust_code != null )
        return false;
    } else if ( !cust_code.equals( o.cust_code ) ) {
        return false;
    }
    if ( cust_code_cd == null ) {
      if ( o.cust_code_cd != null )
        return false;
    } else if ( !cust_code_cd.equals( o.cust_code_cd ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (comp_code!=null? comp_code.hashCode(): 0) 
        + (br_code!=null? br_code.hashCode(): 0) 
        + (cust_code!=null? cust_code.hashCode(): 0) 
        + (cust_code_cd!=null? cust_code_cd.hashCode(): 0) 
    ;
  }

}

@
