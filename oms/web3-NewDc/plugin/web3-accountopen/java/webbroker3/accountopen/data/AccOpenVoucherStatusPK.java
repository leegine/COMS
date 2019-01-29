head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.21.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccOpenVoucherStatusPK.java;


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
 * <b>AccOpenVoucherStatus</b>データベーステーブルで一意である1つのレコードをあらわす<b>AccOpenVoucherStatus</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>AccOpenVoucherStatus</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AccOpenVoucherStatusRow 
 */
public class AccOpenVoucherStatusPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "acc_open_voucher_status";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: AccOpenVoucherStatusRow. 
   */
  public RowType getRowType() {
    return AccOpenVoucherStatusRow.TYPE;
  }

  /**
   * <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String institution_code;
  /**
   * <em>acc_open_request_number</em>カラムの値をあらわす13桁以下のString値 
   */
  public String acc_open_request_number;
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
  public AccOpenVoucherStatusPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_accOpenRequestNumber <em>acc_open_request_number</em>カラムの値をあらわす13桁以下のString値 
   * @@param p_requestCode <em>request_code</em>カラムの値をあらわす5桁以下のString値 
   * @@param p_serialNo <em>serial_no</em>カラムの値をあらわす3桁以下のString値 
   */
  public AccOpenVoucherStatusPK( String p_institutionCode, String p_accOpenRequestNumber, String p_requestCode, String p_serialNo ) {
      institution_code = p_institutionCode;
      acc_open_request_number = p_accOpenRequestNumber;
      request_code = p_requestCode;
      serial_no = p_serialNo;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static AccOpenVoucherStatusPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    AccOpenVoucherStatusPK pk = new AccOpenVoucherStatusPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.institution_code = st.nextToken();
    pk.acc_open_request_number = st.nextToken();
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
      m_id = institution_code + "," + acc_open_request_number + "," + request_code + "," + serial_no;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof AccOpenVoucherStatusPK) )
      return false;
    AccOpenVoucherStatusPK o = (AccOpenVoucherStatusPK) other;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( acc_open_request_number == null ) {
      if ( o.acc_open_request_number != null )
        return false;
    } else if ( !acc_open_request_number.equals( o.acc_open_request_number ) ) {
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
        + (acc_open_request_number!=null? acc_open_request_number.hashCode(): 0) 
        + (request_code!=null? request_code.hashCode(): 0) 
        + (serial_no!=null? serial_no.hashCode(): 0) 
    ;
  }

}

@
