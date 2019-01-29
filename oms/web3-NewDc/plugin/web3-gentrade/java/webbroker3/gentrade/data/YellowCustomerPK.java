head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.26.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	YellowCustomerPK.java;


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
 * <b>YellowCustomer</b>データベーステーブルで一意である1つのレコードをあらわす<b>YellowCustomer</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>YellowCustomer</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see YellowCustomerRow 
 */
public class YellowCustomerPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "yellow_customer";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: YellowCustomerRow. 
   */
  public RowType getRowType() {
    return YellowCustomerRow.TYPE;
  }

  /**
   * <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String institution_code;
  /**
   * <em>era_born</em>カラムの値をあらわす1桁以下のString値 
   */
  public String era_born;
  /**
   * <em>born_date</em>カラムの値をあらわす6桁以下のString値 
   */
  public String born_date;
  /**
   * <em>name_kana</em>カラムの値をあらわす40桁以下のString値 
   */
  public String name_kana;
  /**
   * <em>name</em>カラムの値をあらわす40桁以下のString値 
   */
  public String name;


  /** 
   * デフォルトコンストラクタ 
   */
  public YellowCustomerPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_eraBorn <em>era_born</em>カラムの値をあらわす1桁以下のString値 
   * @@param p_bornDate <em>born_date</em>カラムの値をあらわす6桁以下のString値 
   * @@param p_nameKana <em>name_kana</em>カラムの値をあらわす40桁以下のString値 
   * @@param p_name <em>name</em>カラムの値をあらわす40桁以下のString値 
   */
  public YellowCustomerPK( String p_institutionCode, String p_eraBorn, String p_bornDate, String p_nameKana, String p_name ) {
      institution_code = p_institutionCode;
      era_born = p_eraBorn;
      born_date = p_bornDate;
      name_kana = p_nameKana;
      name = p_name;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static YellowCustomerPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    YellowCustomerPK pk = new YellowCustomerPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.era_born = st.nextToken();
    pk.born_date = st.nextToken();
    pk.name_kana = st.nextToken();
    pk.name = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + era_born + "," + born_date + "," + name_kana + "," + name;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof YellowCustomerPK) )
      return false;
    YellowCustomerPK o = (YellowCustomerPK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( era_born == null ) {
      if ( o.era_born != null )
        return false;
    } else if ( !era_born.equals( o.era_born ) ) {
        return false;
    }
    if ( born_date == null ) {
      if ( o.born_date != null )
        return false;
    } else if ( !born_date.equals( o.born_date ) ) {
        return false;
    }
    if ( name_kana == null ) {
      if ( o.name_kana != null )
        return false;
    } else if ( !name_kana.equals( o.name_kana ) ) {
        return false;
    }
    if ( name == null ) {
      if ( o.name != null )
        return false;
    } else if ( !name.equals( o.name ) ) {
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
        + (era_born!=null? era_born.hashCode(): 0) 
        + (born_date!=null? born_date.hashCode(): 0) 
        + (name_kana!=null? name_kana.hashCode(): 0) 
        + (name!=null? name.hashCode(): 0) 
    ;
  }

}

@
