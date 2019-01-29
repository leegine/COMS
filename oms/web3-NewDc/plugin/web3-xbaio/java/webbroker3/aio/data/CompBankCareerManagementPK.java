head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.51.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	CompBankCareerManagementPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * <b>CompBankCareerManagement</b>データベーステーブルで一意である1つのレコードをあらわす<b>CompBankCareerManagement</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>CompBankCareerManagement</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see CompBankCareerManagementRow 
 */
public class CompBankCareerManagementPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "comp_bank_career_management";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: CompBankCareerManagementRow. 
   */
  public RowType getRowType() {
    return CompBankCareerManagementRow.TYPE;
  }

  /**
   * <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String institution_code;
  /**
   * <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String branch_code;
  /**
   * <em>pay_scheme_id</em>カラムの値をあらわす11桁以下のString値 
   */
  public String pay_scheme_id;
  /**
   * <em>career_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public String career_div;


  /** 
   * デフォルトコンストラクタ 
   */
  public CompBankCareerManagementPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_paySchemeId <em>pay_scheme_id</em>カラムの値をあらわす11桁以下のString値 
   * @@param p_careerDiv <em>career_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public CompBankCareerManagementPK( String p_institutionCode, String p_branchCode, String p_paySchemeId, String p_careerDiv ) {
      institution_code = p_institutionCode;
      branch_code = p_branchCode;
      pay_scheme_id = p_paySchemeId;
      career_div = p_careerDiv;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static CompBankCareerManagementPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    CompBankCareerManagementPK pk = new CompBankCareerManagementPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.branch_code = st.nextToken();
    pk.pay_scheme_id = st.nextToken();
    pk.career_div = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + branch_code + "," + pay_scheme_id + "," + career_div;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof CompBankCareerManagementPK) )
      return false;
    CompBankCareerManagementPK o = (CompBankCareerManagementPK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( branch_code == null ) {
      if ( o.branch_code != null )
        return false;
    } else if ( !branch_code.equals( o.branch_code ) ) {
        return false;
    }
    if ( pay_scheme_id == null ) {
      if ( o.pay_scheme_id != null )
        return false;
    } else if ( !pay_scheme_id.equals( o.pay_scheme_id ) ) {
        return false;
    }
    if ( career_div == null ) {
      if ( o.career_div != null )
        return false;
    } else if ( !career_div.equals( o.career_div ) ) {
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
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (pay_scheme_id!=null? pay_scheme_id.hashCode(): 0) 
        + (career_div!=null? career_div.hashCode(): 0) 
    ;
  }

}

@
