head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.43.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	BankDepositNotifyPK.java;


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
 * <b>BankDepositNotify</b>データベーステーブルで一意である1つのレコードをあらわす<b>BankDepositNotify</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>BankDepositNotify</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BankDepositNotifyRow 
 */
public class BankDepositNotifyPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "bank_deposit_notify";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: BankDepositNotifyRow. 
   */
  public RowType getRowType() {
    return BankDepositNotifyRow.TYPE;
  }

  /**
   * <em>bank_deposit_notify_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long bank_deposit_notify_id;
  /**
   * <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public String institution_code;
  /**
   * <em>data_load_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public String data_load_div;


  /** 
   * デフォルトコンストラクタ 
   */
  public BankDepositNotifyPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_bankDepositNotifyId <em>bank_deposit_notify_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_dataLoadDiv <em>data_load_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public BankDepositNotifyPK( long p_bankDepositNotifyId, String p_institutionCode, String p_dataLoadDiv ) {
      bank_deposit_notify_id = p_bankDepositNotifyId;
      institution_code = p_institutionCode;
      data_load_div = p_dataLoadDiv;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static BankDepositNotifyPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    BankDepositNotifyPK pk = new BankDepositNotifyPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.bank_deposit_notify_id = Long.valueOf(st.nextToken()).longValue();
    pk.institution_code = st.nextToken();
    pk.data_load_div = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(bank_deposit_notify_id) + "," + institution_code + "," + data_load_div;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof BankDepositNotifyPK) )
      return false;
    BankDepositNotifyPK o = (BankDepositNotifyPK) other;
    if ( bank_deposit_notify_id != o.bank_deposit_notify_id )
      return false;
    if ( institution_code == null ) {
      if ( o.institution_code != null )
        return false;
    } else if ( !institution_code.equals( o.institution_code ) ) {
        return false;
    }
    if ( data_load_div == null ) {
      if ( o.data_load_div != null )
        return false;
    } else if ( !data_load_div.equals( o.data_load_div ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) bank_deposit_notify_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (data_load_div!=null? data_load_div.hashCode(): 0) 
    ;
  }

}

@
