head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.23.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	RlsOrderMissPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.rlsgateway.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * <b>RlsOrderMiss</b>データベーステーブルで一意である1つのレコードをあらわす<b>RlsOrderMiss</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>RlsOrderMiss</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see RlsOrderMissRow 
 */
public class RlsOrderMissPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "rls_order_miss";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: RlsOrderMissRow. 
   */
  public RowType getRowType() {
    return RlsOrderMissRow.TYPE;
  }

  /**
   * <em>miss_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public String miss_type;
  /**
   * <em>account_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long account_id;
  /**
   * <em>sub_account_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long sub_account_id;
  /**
   * <em>order_unit_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long order_unit_id;
  /**
   * <em>order_action_serial_no</em>カラムの値をあらわす8桁以下のint値 
   */
  public int order_action_serial_no;
  /**
   * <em>product_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum product_type;
  /**
   * <em>oms_cond_order_type</em>カラムの値をあらわす6桁以下のint値 
   */
  public int oms_cond_order_type;
  /**
   * <em>detect_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public String detect_type;


  /** 
   * デフォルトコンストラクタ 
   */
  public RlsOrderMissPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_missType <em>miss_type</em>カラムの値をあらわす1桁以下のString値 
   * @@param p_accountId <em>account_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_subAccountId <em>sub_account_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_orderUnitId <em>order_unit_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_orderActionSerialNo <em>order_action_serial_no</em>カラムの値をあらわす8桁以下のint値 
   * @@param p_productType <em>product_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum値 
   * @@param p_omsCondOrderType <em>oms_cond_order_type</em>カラムの値をあらわす6桁以下のint値 
   * @@param p_detectType <em>detect_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public RlsOrderMissPK( String p_missType, long p_accountId, long p_subAccountId, long p_orderUnitId, int p_orderActionSerialNo, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, int p_omsCondOrderType, String p_detectType ) {
      miss_type = p_missType;
      account_id = p_accountId;
      sub_account_id = p_subAccountId;
      order_unit_id = p_orderUnitId;
      order_action_serial_no = p_orderActionSerialNo;
      product_type = p_productType;
      oms_cond_order_type = p_omsCondOrderType;
      detect_type = p_detectType;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static RlsOrderMissPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    RlsOrderMissPK pk = new RlsOrderMissPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.miss_type = st.nextToken();
    pk.account_id = Long.valueOf(st.nextToken()).longValue();
    pk.sub_account_id = Long.valueOf(st.nextToken()).longValue();
    pk.order_unit_id = Long.valueOf(st.nextToken()).longValue();
    pk.order_action_serial_no = Integer.valueOf(st.nextToken()).intValue();
    pk.oms_cond_order_type = Integer.valueOf(st.nextToken()).intValue();
    pk.detect_type = st.nextToken();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = miss_type + "," + String.valueOf(account_id) + "," + String.valueOf(sub_account_id) + "," + String.valueOf(order_unit_id) + "," + String.valueOf(order_action_serial_no) + "," + String.valueOf(product_type.intValue()) + "," + String.valueOf(oms_cond_order_type) + "," + detect_type;
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof RlsOrderMissPK) )
      return false;
    RlsOrderMissPK o = (RlsOrderMissPK) other;
    if ( miss_type == null ) {
      if ( o.miss_type != null )
        return false;
    } else if ( !miss_type.equals( o.miss_type ) ) {
        return false;
    }
    if ( account_id != o.account_id )
      return false;
    if ( sub_account_id != o.sub_account_id )
      return false;
    if ( order_unit_id != o.order_unit_id )
      return false;
    if ( order_action_serial_no != o.order_action_serial_no )
      return false;
    if ( product_type == null ) {
      if ( o.product_type != null )
        return false;
    } else if ( !product_type.equals( o.product_type ) ) {
        return false;
    }
    if ( oms_cond_order_type != o.oms_cond_order_type )
      return false;
    if ( detect_type == null ) {
      if ( o.detect_type != null )
        return false;
    } else if ( !detect_type.equals( o.detect_type ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return (miss_type!=null? miss_type.hashCode(): 0) 
        + ((int) account_id)
        + ((int) sub_account_id)
        + ((int) order_unit_id)
        + ((int) order_action_serial_no)
        + (product_type!=null? product_type.hashCode(): 0) 
        + ((int) oms_cond_order_type)
        + (detect_type!=null? detect_type.hashCode(): 0) 
    ;
  }

}

@
