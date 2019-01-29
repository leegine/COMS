head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.30.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	DocDeliveryManagementHistPK.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>DocDeliveryManagementHist</b>データベーステーブルで一意である1つのレコードをあらわす<b>DocDeliveryManagementHist</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>DocDeliveryManagementHist</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see DocDeliveryManagementHistRow 
 */
public class DocDeliveryManagementHistPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "doc_delivery_management_hist";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: DocDeliveryManagementHistRow. 
   */
  public RowType getRowType() {
    return DocDeliveryManagementHistRow.TYPE;
  }

  /**
   * <em>account_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long account_id;
  /**
   * <em>document_div</em>カラムの値をあらわす3桁以下のString値 
   */
  public String document_div;
  /**
   * <em>product_code</em>カラムの値をあらわす10桁以下のString値 
   */
  public String product_code;
  /**
   * <em>delivery_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public java.sql.Timestamp delivery_date;
  /**
   * <em>created_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public java.sql.Timestamp created_timestamp;
  /**
   * <em>document_category</em>カラムの値をあらわす3桁以下のString値 
   */
  public String document_category;


  /** 
   * デフォルトコンストラクタ 
   */
  public DocDeliveryManagementHistPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_accountId <em>account_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_documentDiv <em>document_div</em>カラムの値をあらわす3桁以下のString値 
   * @@param p_productCode <em>product_code</em>カラムの値をあらわす10桁以下のString値 
   * @@param p_deliveryDate <em>delivery_date</em>カラムの値をあらわすjava.sql.Timestamp値
   * @@param p_createdTimestamp <em>created_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   * @@param p_documentCategory <em>document_category</em>カラムの値をあらわす3桁以下のString値 
   */
  public DocDeliveryManagementHistPK( long p_accountId, String p_documentDiv, String p_productCode, java.sql.Timestamp p_deliveryDate, java.sql.Timestamp p_createdTimestamp, String p_documentCategory ) {
      account_id = p_accountId;
      document_div = p_documentDiv;
      product_code = p_productCode;
      delivery_date = p_deliveryDate;
      created_timestamp = p_createdTimestamp;
      document_category = p_documentCategory;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static DocDeliveryManagementHistPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    DocDeliveryManagementHistPK pk = new DocDeliveryManagementHistPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.account_id = Long.valueOf(st.nextToken()).longValue();
    pk.document_div = st.nextToken();
    pk.product_code = st.nextToken();
    pk.delivery_date = java.sql.Timestamp.valueOf(st.nextToken());
    pk.created_timestamp = java.sql.Timestamp.valueOf(st.nextToken());
    pk.document_category = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(account_id) + "," + document_div + "," + product_code + "," + com.fitechlabs.xtrade.kernel.util.ThreadSafeDateFormat.getSimpleDateFormat("yyMMddHHmmss").format(delivery_date) + "," + com.fitechlabs.xtrade.kernel.util.ThreadSafeDateFormat.getSimpleDateFormat("yyMMddHHmmss").format(created_timestamp) + "," + document_category;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof DocDeliveryManagementHistPK) )
      return false;
    DocDeliveryManagementHistPK o = (DocDeliveryManagementHistPK) other;
    if ( account_id != o.account_id )
      return false;
    if ( document_div == null ) {
      if ( o.document_div != null )
        return false;
    } else if ( !document_div.equals( o.document_div ) ) {
        return false;
    }
    if ( product_code == null ) {
      if ( o.product_code != null )
        return false;
    } else if ( !product_code.equals( o.product_code ) ) {
        return false;
    }
    if ( delivery_date == null ) {
      if ( o.delivery_date != null )
        return false;
    } else if ( !delivery_date.equals( o.delivery_date ) ) {
        return false;
    }
    if ( created_timestamp == null ) {
      if ( o.created_timestamp != null )
        return false;
    } else if ( !created_timestamp.equals( o.created_timestamp ) ) {
        return false;
    }
    if ( document_category == null ) {
      if ( o.document_category != null )
        return false;
    } else if ( !document_category.equals( o.document_category ) ) {
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
        + (document_div!=null? document_div.hashCode(): 0) 
        + (product_code!=null? product_code.hashCode(): 0) 
        + (delivery_date!=null? delivery_date.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (document_category!=null? document_category.hashCode(): 0) 
    ;
  }

}

@
