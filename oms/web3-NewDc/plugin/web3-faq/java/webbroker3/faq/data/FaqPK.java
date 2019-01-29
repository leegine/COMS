head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	FaqPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.faq.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>Faq</b>データベーステーブルで一意である1つのレコードをあらわす<b>Faq</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>Faq</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see FaqRow 
 */
public class FaqPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "faq";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: FaqRow. 
   */
  public RowType getRowType() {
    return FaqRow.TYPE;
  }

  /**
   * <em>faq_number</em>カラムの値をあらわす13桁以下のString値 
   */
  public String faq_number;
  /**
   * <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String institution_code;


  /** 
   * デフォルトコンストラクタ 
   */
  public FaqPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_faqNumber <em>faq_number</em>カラムの値をあらわす13桁以下のString値 
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public FaqPK( String p_faqNumber, String p_institutionCode ) {
      faq_number = p_faqNumber;
      institution_code = p_institutionCode;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static FaqPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    FaqPK pk = new FaqPK();
    int i = pkValueString.indexOf(',');
    pk.faq_number = pkValueString.substring(0,i);
    pk.institution_code = pkValueString.substring(i+1);
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = faq_number + "," + institution_code;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof FaqPK) )
      return false;
    FaqPK o = (FaqPK) other;
    if ( faq_number == null ) {
      if ( o.faq_number != null )
        return false;
    } else if ( !faq_number.equals( o.faq_number ) ) {
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
    return (faq_number!=null? faq_number.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
    ;
  }

}

@
