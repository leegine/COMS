head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.30.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	DailyAssetPK.java;


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
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * <b>DailyAsset</b>データベーステーブルで一意である1つのレコードをあらわす<b>DailyAsset</b>インスタンスを特定する主キーオブジェクトです。 
 * <p> 
 * これは通常メモリ内に構築され、<b>DailyAsset</b>テーブルから特定のレコードを検索するため{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor} 
 * クエリメソッドへ引数として渡されます。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see DailyAssetRow 
 */
public class DailyAssetPK implements PrimaryKey {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "pk";


  /** 他のpksと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "daily_asset";


  /** 
   * PrimaryKeyオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return この主キーに紐付くテーブルのRowType: DailyAssetRow. 
   */
  public RowType getRowType() {
    return DailyAssetRow.TYPE;
  }

  /**
   * <em>account_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long account_id;
  /**
   * <em>sub_account_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long sub_account_id;
  /**
   * <em>product_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public long product_id;
  /**
   * <em>tax_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum tax_type;
  /**
   * <em>delivery_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public java.sql.Timestamp delivery_date;
  /**
   * <em>mini_stock_div</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum mini_stock_div;
  /**
   * <em>split_new_stock_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public String split_new_stock_div;
  /**
   * <em>original_exec_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public java.sql.Timestamp original_exec_date;


  /** 
   * デフォルトコンストラクタ 
   */
  public DailyAssetPK() { 
  }


  /** 
   * 引数を伴うコンストラクタ 
   *
   * @@param p_accountId <em>account_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_subAccountId <em>sub_account_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_productId <em>product_id</em>カラムの値をあらわす18桁以下のlong値 
   * @@param p_taxType <em>tax_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum値 
   * @@param p_deliveryDate <em>delivery_date</em>カラムの値をあらわすjava.sql.Timestamp値
   * @@param p_miniStockDiv <em>mini_stock_div</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   * @@param p_splitNewStockDiv <em>split_new_stock_div</em>カラムの値をあらわす1桁以下のString値 
   * @@param p_originalExecDate <em>original_exec_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public DailyAssetPK( long p_accountId, long p_subAccountId, long p_productId, com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum p_taxType, java.sql.Timestamp p_deliveryDate, com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_miniStockDiv, String p_splitNewStockDiv, java.sql.Timestamp p_originalExecDate ) {
      account_id = p_accountId;
      sub_account_id = p_subAccountId;
      product_id = p_productId;
      tax_type = p_taxType;
      delivery_date = p_deliveryDate;
      mini_stock_div = p_miniStockDiv;
      split_new_stock_div = p_splitNewStockDiv;
      original_exec_date = p_originalExecDate;
  }


  /** 
   * カンマ区切りで指定した文字列を利用してpkを構築します。 
   * 
   * @@param pkValueString 主キーの値をカンマ区切りでリストした文字列 
   * @@exception NumberFormatException double、 long、intのパース中にエラーが発生した場合 
   */
  public static DailyAssetPK fromString( String pkValueString ) 
      throws NumberFormatException
  {
    DailyAssetPK pk = new DailyAssetPK();
    java.util.StringTokenizer st = new java.util.StringTokenizer(pkValueString,",");
    pk.account_id = Long.valueOf(st.nextToken()).longValue();
    pk.sub_account_id = Long.valueOf(st.nextToken()).longValue();
    pk.product_id = Long.valueOf(st.nextToken()).longValue();
    pk.delivery_date = java.sql.Timestamp.valueOf(st.nextToken());
    pk.split_new_stock_div = st.nextToken();
    pk.original_exec_date = java.sql.Timestamp.valueOf(st.nextToken());
    pk.m_id = pkValueString;
    return pk;
  }

  /** 
   * コンストラクタ作成時と同順でカンマ区切りの主キー値の文字列を返します。 
   */
  public String toString() {
    if ( m_id == null )
      m_id = String.valueOf(account_id) + "," + String.valueOf(sub_account_id) + "," + String.valueOf(product_id) + "," + String.valueOf(tax_type.intValue()) + "," + com.fitechlabs.xtrade.kernel.util.ThreadSafeDateFormat.getSimpleDateFormat("yyMMddHHmmss").format(delivery_date) + "," + String.valueOf(mini_stock_div.intValue()) + "," + split_new_stock_div + "," + com.fitechlabs.xtrade.kernel.util.ThreadSafeDateFormat.getSimpleDateFormat("yyMMddHHmmss").format(original_exec_date);
    return m_id;
  }

  private String m_id = null;


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( other == null || !( other instanceof DailyAssetPK) )
      return false;
    DailyAssetPK o = (DailyAssetPK) other;
    if ( account_id != o.account_id )
      return false;
    if ( sub_account_id != o.sub_account_id )
      return false;
    if ( product_id != o.product_id )
      return false;
    if ( tax_type == null ) {
      if ( o.tax_type != null )
        return false;
    } else if ( !tax_type.equals( o.tax_type ) ) {
        return false;
    }
    if ( delivery_date == null ) {
      if ( o.delivery_date != null )
        return false;
    } else if ( !delivery_date.equals( o.delivery_date ) ) {
        return false;
    }
    if ( mini_stock_div == null ) {
      if ( o.mini_stock_div != null )
        return false;
    } else if ( !mini_stock_div.equals( o.mini_stock_div ) ) {
        return false;
    }
    if ( split_new_stock_div == null ) {
      if ( o.split_new_stock_div != null )
        return false;
    } else if ( !split_new_stock_div.equals( o.split_new_stock_div ) ) {
        return false;
    }
    if ( original_exec_date == null ) {
      if ( o.original_exec_date != null )
        return false;
    } else if ( !original_exec_date.equals( o.original_exec_date ) ) {
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
        + ((int) sub_account_id)
        + ((int) product_id)
        + (tax_type!=null? tax_type.hashCode(): 0) 
        + (delivery_date!=null? delivery_date.hashCode(): 0) 
        + (mini_stock_div!=null? mini_stock_div.hashCode(): 0) 
        + (split_new_stock_div!=null? split_new_stock_div.hashCode(): 0) 
        + (original_exec_date!=null? original_exec_date.hashCode(): 0) 
    ;
  }

}

@
