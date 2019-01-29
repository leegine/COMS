head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.22.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	CodeTranslationPK.java;


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
 * <b>CodeTranslation</b>データベーステーブルで一意である1つのレコードをあらわす<b>CodeTranslation</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>CodeTranslation</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see CodeTranslationRow 
 */
public class CodeTranslationPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "code_translation";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: CodeTranslationRow. 
   */
  public RowType getRowType() {
    return CodeTranslationRow.TYPE;
  }

  /**
   * <em>code_type</em>カラムの値をあらわす64桁以下のString値 
   */
  public String code_type;
  /**
   * <em>institution_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public String institution_code;
  /**
   * <em>code</em>カラムの値をあらわす64桁以下のString値 
   */
  public String code;


  /** 
   * デフォルトコンストラクタ 
   */
  public CodeTranslationPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_codeType <em>code_type</em>カラムの値をあらわす64桁以下のString値 
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_code <em>code</em>カラムの値をあらわす64桁以下のString値 
   */
  public CodeTranslationPK( String p_codeType, String p_institutionCode, String p_code ) {
      code_type = p_codeType;
      institution_code = p_institutionCode;
      code = p_code;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static CodeTranslationPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    CodeTranslationPK pk = new CodeTranslationPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.code_type = st.nextToken();
    pk.institution_code = st.nextToken();
    pk.code = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = code_type + "," + institution_code + "," + code;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof CodeTranslationPK) )
      return false;
    CodeTranslationPK o = (CodeTranslationPK) other;
    if ( code_type == null ) {
      if ( o.code_type != null )
        return false;
    } else if ( !code_type.equals( o.code_type ) ) {
        return false;
    }
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( code == null ) {
      if ( o.code != null )
        return false;
    } else if ( !code.equals( o.code ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (code_type!=null? code_type.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (code!=null? code.hashCode(): 0) 
    ;
  }

}

@
