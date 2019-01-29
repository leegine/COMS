head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.27.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	FinInstitutionBankPK.java;


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
 * <b>FinInstitutionBank</b>データベーステーブルで一意である1つのレコードをあらわす<b>FinInstitutionBank</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>FinInstitutionBank</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see FinInstitutionBankRow 
 */
public class FinInstitutionBankPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "fin_institution_bank";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: FinInstitutionBankRow. 
   */
  public RowType getRowType() {
    return FinInstitutionBankRow.TYPE;
  }

  /**
   * <em>fin_institution_code</em>カラムの値をあらわす4桁以下のString値 
   */
  public String fin_institution_code;
  /**
   * <em>fin_branch_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String fin_branch_code;


  /** 
   * デフォルトコンストラクタ 
   */
  public FinInstitutionBankPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_finInstitutionCode <em>fin_institution_code</em>カラムの値をあらわす4桁以下のString値 
   * @@param p_finBranchCode <em>fin_branch_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public FinInstitutionBankPK( String p_finInstitutionCode, String p_finBranchCode ) {
      fin_institution_code = p_finInstitutionCode;
      fin_branch_code = p_finBranchCode;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static FinInstitutionBankPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    FinInstitutionBankPK pk = new FinInstitutionBankPK();
    int i = pkValueString.indexOf(',');
    pk.fin_institution_code = pkValueString.substring(0,i);
    pk.fin_branch_code = pkValueString.substring(i+1);
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = fin_institution_code + "," + fin_branch_code;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof FinInstitutionBankPK) )
      return false;
    FinInstitutionBankPK o = (FinInstitutionBankPK) other;
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
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (fin_institution_code!=null? fin_institution_code.hashCode(): 0) 
        + (fin_branch_code!=null? fin_branch_code.hashCode(): 0) 
    ;
  }

}

@
