head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.58.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	PaymentRequisitionEquityPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpoweradmin.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * <b>PaymentRequisitionEquity</b>データベーステーブルで一意である1つのレコードをあらわす<b>PaymentRequisitionEquity</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>PaymentRequisitionEquity</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see PaymentRequisitionEquityRow 
 */
public class PaymentRequisitionEquityPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "payment_requisition_equity";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: PaymentRequisitionEquityRow. 
   */
  public RowType getRowType() {
    return PaymentRequisitionEquityRow.TYPE;
  }

  /**
   * <em>account_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long account_id;
  /**
   * <em>calc_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public java.sql.Timestamp calc_date;


  /** 
   * デフォルトコンストラクタ 
   */
  public PaymentRequisitionEquityPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_accountId <em>account_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_calcDate <em>calc_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public PaymentRequisitionEquityPK( long p_accountId, java.sql.Timestamp p_calcDate ) {
      account_id = p_accountId;
      calc_date = p_calcDate;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static PaymentRequisitionEquityPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    PaymentRequisitionEquityPK pk = new PaymentRequisitionEquityPK();
    int i = pkValueString.indexOf(',');
    pk.account_id = Long.valueOf(pkValueString.substring(0,i)).longValue();
    pk.calc_date = java.sql.Timestamp.valueOf(pkValueString.substring(i+1));
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(account_id) + "," + com.fitechlabs.xtrade.kernel.util.ThreadSafeDateFormat.getSimpleDateFormat("yyMMddHHmmss").format(calc_date);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof PaymentRequisitionEquityPK) )
      return false;
    PaymentRequisitionEquityPK o = (PaymentRequisitionEquityPK) other;
    if ( account_id != o.account_id )
      return false;
    if ( calc_date == null ) {
      if ( o.calc_date != null )
        return false;
    } else if ( !calc_date.equals( o.calc_date ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) account_id)
        + (calc_date!=null? calc_date.hashCode(): 0) 
    ;
  }

}

@
