head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.10.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccOpenDlFormMasterPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>AccOpenDlFormMaster</b>データベーステーブルで一意である1つのレコードをあらわす<b>AccOpenDlFormMaster</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>AccOpenDlFormMaster</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AccOpenDlFormMasterRow 
 */
public class AccOpenDlFormMasterPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "acc_open_dl_form_master";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: AccOpenDlFormMasterRow. 
   */
  public RowType getRowType() {
    return AccOpenDlFormMasterRow.TYPE;
  }

  /**
   * <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String institution_code;
  /**
   * <em>account_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public String account_div;
  /**
   * <em>column_number</em>カラムの値をあらわす6桁以下のint値 
   */
  public int column_number;


  /** 
   * デフォルトコンストラクタ 
   */
  public AccOpenDlFormMasterPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_accountDiv <em>account_div</em>カラムの値をあらわす1桁以下のString値 
   * @@param p_columnNumber <em>column_number</em>カラムの値をあらわす6桁以下のint値 
   */
  public AccOpenDlFormMasterPK( String p_institutionCode, String p_accountDiv, int p_columnNumber ) {
      institution_code = p_institutionCode;
      account_div = p_accountDiv;
      column_number = p_columnNumber;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static AccOpenDlFormMasterPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    AccOpenDlFormMasterPK pk = new AccOpenDlFormMasterPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.account_div = st.nextToken();
    pk.column_number = Integer.valueOf(st.nextToken()).intValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + account_div + "," + String.valueOf(column_number);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof AccOpenDlFormMasterPK) )
      return false;
    AccOpenDlFormMasterPK o = (AccOpenDlFormMasterPK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( account_div == null ) {
      if ( o.account_div != null )
        return false;
    } else if ( !account_div.equals( o.account_div ) ) {
        return false;
    }
    if ( column_number != o.column_number )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (institution_code!=null? institution_code.hashCode(): 0) 
        + (account_div!=null? account_div.hashCode(): 0) 
        + ((int) column_number)
    ;
  }

}

@
