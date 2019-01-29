head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.37.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	SecReceiptDeliveryActionPK.java;


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
 * <b>SecReceiptDeliveryAction</b>データベーステーブルで一意である1つのレコードをあらわす<b>SecReceiptDeliveryAction</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>SecReceiptDeliveryAction</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SecReceiptDeliveryActionRow 
 */
public class SecReceiptDeliveryActionPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "sec_receipt_delivery_action";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: SecReceiptDeliveryActionRow. 
   */
  public RowType getRowType() {
    return SecReceiptDeliveryActionRow.TYPE;
  }

  /**
   * <em>sec_receipt_delivery_action_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long sec_receipt_delivery_action_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public SecReceiptDeliveryActionPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_secReceiptDeliveryActionId <em>sec_receipt_delivery_action_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public SecReceiptDeliveryActionPK( long p_secReceiptDeliveryActionId ) {
      sec_receipt_delivery_action_id = p_secReceiptDeliveryActionId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static SecReceiptDeliveryActionPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    SecReceiptDeliveryActionPK pk = new SecReceiptDeliveryActionPK();
    pk.sec_receipt_delivery_action_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(sec_receipt_delivery_action_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof SecReceiptDeliveryActionPK) )
      return false;
    SecReceiptDeliveryActionPK o = (SecReceiptDeliveryActionPK) other;
    if ( sec_receipt_delivery_action_id != o.sec_receipt_delivery_action_id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) sec_receipt_delivery_action_id)
    ;
  }

}

@
