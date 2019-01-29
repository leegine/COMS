head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.16.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccOpenVoucherMasterPK.java;


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
 * <b>AccOpenVoucherMaster</b>データベーステーブルで一意である1つのレコードをあらわす<b>AccOpenVoucherMaster</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>AccOpenVoucherMaster</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AccOpenVoucherMasterRow 
 */
public class AccOpenVoucherMasterPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "acc_open_voucher_master";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: AccOpenVoucherMasterRow. 
   */
  public RowType getRowType() {
    return AccOpenVoucherMasterRow.TYPE;
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
   * <em>account_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public String account_div;
  /**
   * <em>request_code</em>カラムの値をあらわす5桁以下のString値 
   */
  public String request_code;
  /**
   * <em>serial_no</em>カラムの値をあらわす3桁以下のString値 
   */
  public String serial_no;


  /** 
   * デフォルトコンストラクタ 
   */
  public AccOpenVoucherMasterPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_accountDiv <em>account_div</em>カラムの値をあらわす1桁以下のString値 
   * @@param p_requestCode <em>request_code</em>カラムの値をあらわす5桁以下のString値 
   * @@param p_serialNo <em>serial_no</em>カラムの値をあらわす3桁以下のString値 
   */
  public AccOpenVoucherMasterPK( String p_institutionCode, String p_branchCode, String p_accountDiv, String p_requestCode, String p_serialNo ) {
      institution_code = p_institutionCode;
      branch_code = p_branchCode;
      account_div = p_accountDiv;
      request_code = p_requestCode;
      serial_no = p_serialNo;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static AccOpenVoucherMasterPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    AccOpenVoucherMasterPK pk = new AccOpenVoucherMasterPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.branch_code = st.nextToken();
    pk.account_div = st.nextToken();
    pk.request_code = st.nextToken();
    pk.serial_no = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = institution_code + "," + branch_code + "," + account_div + "," + request_code + "," + serial_no;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof AccOpenVoucherMasterPK) )
      return false;
    AccOpenVoucherMasterPK o = (AccOpenVoucherMasterPK) other;
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
    if ( account_div == null ) {
      if ( o.account_div != null )
        return false;
    } else if ( !account_div.equals( o.account_div ) ) {
        return false;
    }
    if ( request_code == null ) {
      if ( o.request_code != null )
        return false;
    } else if ( !request_code.equals( o.request_code ) ) {
        return false;
    }
    if ( serial_no == null ) {
      if ( o.serial_no != null )
        return false;
    } else if ( !serial_no.equals( o.serial_no ) ) {
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
        + (account_div!=null? account_div.hashCode(): 0) 
        + (request_code!=null? request_code.hashCode(): 0) 
        + (serial_no!=null? serial_no.hashCode(): 0) 
    ;
  }

}

@
