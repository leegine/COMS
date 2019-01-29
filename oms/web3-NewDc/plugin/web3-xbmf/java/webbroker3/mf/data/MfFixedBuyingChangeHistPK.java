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
filename	MfFixedBuyingChangeHistPK.java;


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

/** 
 * <b>MfFixedBuyingChangeHist</b>データベーステーブルで一意である1つのレコードをあらわす<b>MfFixedBuyingChangeHist</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>MfFixedBuyingChangeHist</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MfFixedBuyingChangeHistRow 
 */
public class MfFixedBuyingChangeHistPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "mf_fixed_buying_change_hist";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: MfFixedBuyingChangeHistRow. 
   */
  public RowType getRowType() {
    return MfFixedBuyingChangeHistRow.TYPE;
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
   * <em>account_code</em>カラムの値をあらわす7桁以下のString値 
   */
  public String account_code;
  /**
   * <em>product_code</em>カラムの値をあらわす10桁以下のString値 
   */
  public String product_code;
  /**
   * <em>valid_start_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public java.sql.Timestamp valid_start_date;
  /**
   * <em>serial_no</em>カラムの値をあらわす8桁以下のint値 
   */
  public int serial_no;


  /** 
   * デフォルトコンストラクタ 
   */
  public MfFixedBuyingChangeHistPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_accountCode <em>account_code</em>カラムの値をあらわす7桁以下のString値 
   * @@param p_productCode <em>product_code</em>カラムの値をあらわす10桁以下のString値 
   * @@param p_validStartDate <em>valid_start_date</em>カラムの値をあらわすjava.sql.Timestamp値
   * @@param p_serialNo <em>serial_no</em>カラムの値をあらわす8桁以下のint値 
   */
  public MfFixedBuyingChangeHistPK( String p_institutionCode, String p_branchCode, String p_accountCode, String p_productCode, java.sql.Timestamp p_validStartDate, int p_serialNo ) {
      institution_code = p_institutionCode;
      branch_code = p_branchCode;
      account_code = p_accountCode;
      product_code = p_productCode;
      valid_start_date = p_validStartDate;
      serial_no = p_serialNo;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static MfFixedBuyingChangeHistPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    MfFixedBuyingChangeHistPK pk = new MfFixedBuyingChangeHistPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.branch_code = st.nextToken();
    pk.account_code = st.nextToken();
    pk.product_code = st.nextToken();
    pk.valid_start_date = java.sql.Timestamp.valueOf(st.nextToken());
    pk.serial_no = Integer.valueOf(st.nextToken()).intValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + branch_code + "," + account_code + "," + product_code + "," + com.fitechlabs.xtrade.kernel.util.ThreadSafeDateFormat.getSimpleDateFormat("yyMMddHHmmss").format(valid_start_date) + "," + String.valueOf(serial_no);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof MfFixedBuyingChangeHistPK) )
      return false;
    MfFixedBuyingChangeHistPK o = (MfFixedBuyingChangeHistPK) other;
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
    if ( account_code == null ) {
      if ( o.account_code != null )
        return false;
    } else if ( !account_code.equals( o.account_code ) ) {
        return false;
    }
    if ( product_code == null ) {
      if ( o.product_code != null )
        return false;
    } else if ( !product_code.equals( o.product_code ) ) {
        return false;
    }
    if ( valid_start_date == null ) {
      if ( o.valid_start_date != null )
        return false;
    } else if ( !valid_start_date.equals( o.valid_start_date ) ) {
        return false;
    }
    if ( serial_no != o.serial_no )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (product_code!=null? product_code.hashCode(): 0) 
        + (valid_start_date!=null? valid_start_date.hashCode(): 0) 
        + ((int) serial_no)
    ;
  }

}

@
