head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.38.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	ExclusiveUseAccountCondPK.java;


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
 * <b>ExclusiveUseAccountCond</b>データベーステーブルで一意である1つのレコードをあらわす<b>ExclusiveUseAccountCond</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>ExclusiveUseAccountCond</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see ExclusiveUseAccountCondRow 
 */
public class ExclusiveUseAccountCondPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "exclusive_use_account_cond";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: ExclusiveUseAccountCondRow. 
   */
  public RowType getRowType() {
    return ExclusiveUseAccountCondRow.TYPE;
  }

  /**
   * <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String institution_code;
  /**
   * <em>fin_institution_code</em>カラムの値をあらわす4桁以下のString値 
   */
  public String fin_institution_code;
  /**
   * <em>fin_branch_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String fin_branch_code;
  /**
   * <em>fin_account_no</em>カラムの値をあらわす7桁以下のString値 
   */
  public String fin_account_no;


  /** 
   * デフォルトコンストラクタ 
   */
  public ExclusiveUseAccountCondPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_finInstitutionCode <em>fin_institution_code</em>カラムの値をあらわす4桁以下のString値 
   * @@param p_finBranchCode <em>fin_branch_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_finAccountNo <em>fin_account_no</em>カラムの値をあらわす7桁以下のString値 
   */
  public ExclusiveUseAccountCondPK( String p_institutionCode, String p_finInstitutionCode, String p_finBranchCode, String p_finAccountNo ) {
      institution_code = p_institutionCode;
      fin_institution_code = p_finInstitutionCode;
      fin_branch_code = p_finBranchCode;
      fin_account_no = p_finAccountNo;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static ExclusiveUseAccountCondPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    ExclusiveUseAccountCondPK pk = new ExclusiveUseAccountCondPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.fin_institution_code = st.nextToken();
    pk.fin_branch_code = st.nextToken();
    pk.fin_account_no = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + fin_institution_code + "," + fin_branch_code + "," + fin_account_no;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof ExclusiveUseAccountCondPK) )
      return false;
    ExclusiveUseAccountCondPK o = (ExclusiveUseAccountCondPK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( fin_institution_code == null ) {
      if ( o.fin_institution_code != null )
        return false;
    } else if ( !fin_institution_code.equals( o.fin_institution_code ) ) {
        return false;
    }
    if ( fin_branch_code == null ) {
      if ( o.fin_branch_code != null )
        return false;
    } else if ( !fin_branch_code.equals( o.fin_branch_code ) ) {
        return false;
    }
    if ( fin_account_no == null ) {
      if ( o.fin_account_no != null )
        return false;
    } else if ( !fin_account_no.equals( o.fin_account_no ) ) {
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
        + (fin_institution_code!=null? fin_institution_code.hashCode(): 0) 
        + (fin_branch_code!=null? fin_branch_code.hashCode(): 0) 
        + (fin_account_no!=null? fin_account_no.hashCode(): 0) 
    ;
  }

}

@
