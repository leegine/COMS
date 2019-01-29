head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.52.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	FxAccountCodePK.java;


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
 * <b>FxAccountCode</b>データベーステーブルで一意である1つのレコードをあらわす<b>FxAccountCode</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>FxAccountCode</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see FxAccountCodeRow 
 */
public class FxAccountCodePK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "fx_account_code";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: FxAccountCodeRow. 
   */
  public RowType getRowType() {
    return FxAccountCodeRow.TYPE;
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
   * <em>fx_system_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public String fx_system_code;
  /**
   * <em>account_code</em>カラムの値をあらわす7桁以下のString値 
   */
  public String account_code;
  /**
   * <em>fx_course_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public String fx_course_div;


  /** 
   * デフォルトコンストラクタ 
   */
  public FxAccountCodePK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_fxSystemCode <em>fx_system_code</em>カラムの値をあらわす2桁以下のString値 
   * @@param p_accountCode <em>account_code</em>カラムの値をあらわす7桁以下のString値 
   * @@param p_fxCourseDiv <em>fx_course_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public FxAccountCodePK( String p_institutionCode, String p_branchCode, String p_fxSystemCode, String p_accountCode, String p_fxCourseDiv ) {
      institution_code = p_institutionCode;
      branch_code = p_branchCode;
      fx_system_code = p_fxSystemCode;
      account_code = p_accountCode;
      fx_course_div = p_fxCourseDiv;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static FxAccountCodePK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    FxAccountCodePK pk = new FxAccountCodePK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.branch_code = st.nextToken();
    pk.fx_system_code = st.nextToken();
    pk.account_code = st.nextToken();
    pk.fx_course_div = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + branch_code + "," + fx_system_code + "," + account_code + "," + fx_course_div;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof FxAccountCodePK) )
      return false;
    FxAccountCodePK o = (FxAccountCodePK) other;
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
    if ( fx_system_code == null ) {
      if ( o.fx_system_code != null )
        return false;
    } else if ( !fx_system_code.equals( o.fx_system_code ) ) {
        return false;
    }
    if ( account_code == null ) {
      if ( o.account_code != null )
        return false;
    } else if ( !account_code.equals( o.account_code ) ) {
        return false;
    }
    if ( fx_course_div == null ) {
      if ( o.fx_course_div != null )
        return false;
    } else if ( !fx_course_div.equals( o.fx_course_div ) ) {
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
        + (fx_system_code!=null? fx_system_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (fx_course_div!=null? fx_course_div.hashCode(): 0) 
    ;
  }

}

@
