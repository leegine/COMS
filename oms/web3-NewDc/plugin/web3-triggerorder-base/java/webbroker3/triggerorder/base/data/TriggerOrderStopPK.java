head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.18.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8a44d7ecc762b5b;
filename	TriggerOrderStopPK.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.triggerorder.base.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * <b>TriggerOrderStop</b>データベーステーブルで一意である1つのレコードをあらわす<b>TriggerOrderStop</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>TriggerOrderStop</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see TriggerOrderStopRow 
 */
public class TriggerOrderStopPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "trigger_order_stop";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: TriggerOrderStopRow. 
   */
  public RowType getRowType() {
    return TriggerOrderStopRow.TYPE;
  }

  /**
   * <em>trigger_order_stop_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long trigger_order_stop_id;


  /** 
   * デフォルトコンストラクタ 
   */
  public TriggerOrderStopPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_triggerOrderStopId <em>trigger_order_stop_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public TriggerOrderStopPK( long p_triggerOrderStopId ) {
      trigger_order_stop_id = p_triggerOrderStopId;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static TriggerOrderStopPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    TriggerOrderStopPK pk = new TriggerOrderStopPK();
    pk.trigger_order_stop_id = Long.valueOf(pkValueString).longValue();
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(trigger_order_stop_id);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof TriggerOrderStopPK) )
      return false;
    TriggerOrderStopPK o = (TriggerOrderStopPK) other;
    if ( trigger_order_stop_id != o.trigger_order_stop_id )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int 
   */
  public int hashCode() {
    return ((int) trigger_order_stop_id)
    ;
  }

}

@
