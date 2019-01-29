head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	HostAttentionInfoNotifyParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.eqtypeadmin.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;

/** 
 * host_attention_info_notifyテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link HostAttentionInfoNotifyRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link HostAttentionInfoNotifyParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link HostAttentionInfoNotifyParams}が{@@link HostAttentionInfoNotifyRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see HostAttentionInfoNotifyPK 
 * @@see HostAttentionInfoNotifyRow 
 */
public class HostAttentionInfoNotifyParams extends Params implements HostAttentionInfoNotifyRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "host_attention_info_notify";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = HostAttentionInfoNotifyRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return HostAttentionInfoNotifyRow.TYPE;
  }


  /** 
   * <em>rowid</em>カラムの値 
   */
  public  String  rowid;

  /** 
   * <em>request_code</em>カラムの値 
   */
  public  String  request_code;

  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>product_code</em>カラムの値 
   */
  public  String  product_code;

  /** 
   * <em>sonar_market_code</em>カラムの値 
   */
  public  String  sonar_market_code;

  /** 
   * <em>attention_info_div_code</em>カラムの値 
   */
  public  String  attention_info_div_code;

  /** 
   * <em>base_price</em>カラムの値 
   */
  public  Double  base_price;

  /** 
   * <em>high_price_range</em>カラムの値 
   */
  public  Double  high_price_range;

  /** 
   * <em>low_price_range</em>カラムの値 
   */
  public  Double  low_price_range;

  /** 
   * <em>free_format_title</em>カラムの値 
   */
  public  String  free_format_title;

  /** 
   * <em>free_format_text</em>カラムの値 
   */
  public  String  free_format_text;

  /** 
   * <em>info_generation_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  info_generation_timestamp;

  /** 
   * <em>ord_receipt_restart_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  ord_receipt_restart_timestamp;

  /** 
   * <em>trade_stop_restart_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  trade_stop_restart_timestamp;

  /** 
   * <em>status</em>カラムの値 
   */
  public  String  status;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean request_code_is_set = false;
  private boolean request_code_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;
  private boolean sonar_market_code_is_set = false;
  private boolean sonar_market_code_is_modified = false;
  private boolean attention_info_div_code_is_set = false;
  private boolean attention_info_div_code_is_modified = false;
  private boolean base_price_is_set = false;
  private boolean base_price_is_modified = false;
  private boolean high_price_range_is_set = false;
  private boolean high_price_range_is_modified = false;
  private boolean low_price_range_is_set = false;
  private boolean low_price_range_is_modified = false;
  private boolean free_format_title_is_set = false;
  private boolean free_format_title_is_modified = false;
  private boolean free_format_text_is_set = false;
  private boolean free_format_text_is_modified = false;
  private boolean info_generation_timestamp_is_set = false;
  private boolean info_generation_timestamp_is_modified = false;
  private boolean ord_receipt_restart_timestamp_is_set = false;
  private boolean ord_receipt_restart_timestamp_is_modified = false;
  private boolean trade_stop_restart_timestamp_is_set = false;
  private boolean trade_stop_restart_timestamp_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean rowid_is_set = false;
  private boolean rowid_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "rowid=" + rowid
      + "," + "request_code=" +request_code
      + "," + "institution_code=" +institution_code
      + "," + "product_code=" +product_code
      + "," + "sonar_market_code=" +sonar_market_code
      + "," + "attention_info_div_code=" +attention_info_div_code
      + "," + "base_price=" +base_price
      + "," + "high_price_range=" +high_price_range
      + "," + "low_price_range=" +low_price_range
      + "," + "free_format_title=" +free_format_title
      + "," + "free_format_text=" +free_format_text
      + "," + "info_generation_timestamp=" +info_generation_timestamp
      + "," + "ord_receipt_restart_timestamp=" +ord_receipt_restart_timestamp
      + "," + "trade_stop_restart_timestamp=" +trade_stop_restart_timestamp
      + "," + "status=" +status
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のHostAttentionInfoNotifyParamsオブジェクトを作成します。 
   */
  public HostAttentionInfoNotifyParams() {
    rowid_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のHostAttentionInfoNotifyRowオブジェクトの値を利用してHostAttentionInfoNotifyParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するHostAttentionInfoNotifyRowオブジェクト 
   */
  public HostAttentionInfoNotifyParams( HostAttentionInfoNotifyRow p_row )
  {
    rowid = p_row.getRowid();
    rowid_is_set = p_row.getRowidIsSet();
    rowid_is_modified = p_row.getRowidIsModified();
    request_code = p_row.getRequestCode();
    request_code_is_set = p_row.getRequestCodeIsSet();
    request_code_is_modified = p_row.getRequestCodeIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    product_code = p_row.getProductCode();
    product_code_is_set = p_row.getProductCodeIsSet();
    product_code_is_modified = p_row.getProductCodeIsModified();
    sonar_market_code = p_row.getSonarMarketCode();
    sonar_market_code_is_set = p_row.getSonarMarketCodeIsSet();
    sonar_market_code_is_modified = p_row.getSonarMarketCodeIsModified();
    attention_info_div_code = p_row.getAttentionInfoDivCode();
    attention_info_div_code_is_set = p_row.getAttentionInfoDivCodeIsSet();
    attention_info_div_code_is_modified = p_row.getAttentionInfoDivCodeIsModified();
    if ( !p_row.getBasePriceIsNull() )
      base_price = new Double( p_row.getBasePrice() );
    base_price_is_set = p_row.getBasePriceIsSet();
    base_price_is_modified = p_row.getBasePriceIsModified();
    if ( !p_row.getHighPriceRangeIsNull() )
      high_price_range = new Double( p_row.getHighPriceRange() );
    high_price_range_is_set = p_row.getHighPriceRangeIsSet();
    high_price_range_is_modified = p_row.getHighPriceRangeIsModified();
    if ( !p_row.getLowPriceRangeIsNull() )
      low_price_range = new Double( p_row.getLowPriceRange() );
    low_price_range_is_set = p_row.getLowPriceRangeIsSet();
    low_price_range_is_modified = p_row.getLowPriceRangeIsModified();
    free_format_title = p_row.getFreeFormatTitle();
    free_format_title_is_set = p_row.getFreeFormatTitleIsSet();
    free_format_title_is_modified = p_row.getFreeFormatTitleIsModified();
    free_format_text = p_row.getFreeFormatText();
    free_format_text_is_set = p_row.getFreeFormatTextIsSet();
    free_format_text_is_modified = p_row.getFreeFormatTextIsModified();
    info_generation_timestamp = p_row.getInfoGenerationTimestamp();
    info_generation_timestamp_is_set = p_row.getInfoGenerationTimestampIsSet();
    info_generation_timestamp_is_modified = p_row.getInfoGenerationTimestampIsModified();
    ord_receipt_restart_timestamp = p_row.getOrdReceiptRestartTimestamp();
    ord_receipt_restart_timestamp_is_set = p_row.getOrdReceiptRestartTimestampIsSet();
    ord_receipt_restart_timestamp_is_modified = p_row.getOrdReceiptRestartTimestampIsModified();
    trade_stop_restart_timestamp = p_row.getTradeStopRestartTimestamp();
    trade_stop_restart_timestamp_is_set = p_row.getTradeStopRestartTimestampIsSet();
    trade_stop_restart_timestamp_is_modified = p_row.getTradeStopRestartTimestampIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      request_code_is_set = true;
      request_code_is_modified = true;
      institution_code_is_set = true;
      institution_code_is_modified = true;
      product_code_is_set = true;
      product_code_is_modified = true;
      sonar_market_code_is_set = true;
      sonar_market_code_is_modified = true;
      attention_info_div_code_is_set = true;
      attention_info_div_code_is_modified = true;
      base_price_is_set = true;
      base_price_is_modified = true;
      high_price_range_is_set = true;
      high_price_range_is_modified = true;
      low_price_range_is_set = true;
      low_price_range_is_modified = true;
      free_format_title_is_set = true;
      free_format_title_is_modified = true;
      free_format_text_is_set = true;
      free_format_text_is_modified = true;
      info_generation_timestamp_is_set = true;
      info_generation_timestamp_is_modified = true;
      ord_receipt_restart_timestamp_is_set = true;
      ord_receipt_restart_timestamp_is_modified = true;
      trade_stop_restart_timestamp_is_set = true;
      trade_stop_restart_timestamp_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof HostAttentionInfoNotifyRow ) )
       return false;
    return fieldsEqual( (HostAttentionInfoNotifyRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のHostAttentionInfoNotifyRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( HostAttentionInfoNotifyRow row )
  {
    if ( request_code == null ) {
      if ( row.getRequestCode() != null )
        return false;
    } else if ( !request_code.equals( row.getRequestCode() ) ) {
        return false;
    }
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( product_code == null ) {
      if ( row.getProductCode() != null )
        return false;
    } else if ( !product_code.equals( row.getProductCode() ) ) {
        return false;
    }
    if ( sonar_market_code == null ) {
      if ( row.getSonarMarketCode() != null )
        return false;
    } else if ( !sonar_market_code.equals( row.getSonarMarketCode() ) ) {
        return false;
    }
    if ( attention_info_div_code == null ) {
      if ( row.getAttentionInfoDivCode() != null )
        return false;
    } else if ( !attention_info_div_code.equals( row.getAttentionInfoDivCode() ) ) {
        return false;
    }
    if ( base_price == null ) {
      if ( !row.getBasePriceIsNull() )
        return false;
    } else if ( row.getBasePriceIsNull() || ( base_price.doubleValue() != row.getBasePrice() ) ) {
        return false;
    }
    if ( high_price_range == null ) {
      if ( !row.getHighPriceRangeIsNull() )
        return false;
    } else if ( row.getHighPriceRangeIsNull() || ( high_price_range.doubleValue() != row.getHighPriceRange() ) ) {
        return false;
    }
    if ( low_price_range == null ) {
      if ( !row.getLowPriceRangeIsNull() )
        return false;
    } else if ( row.getLowPriceRangeIsNull() || ( low_price_range.doubleValue() != row.getLowPriceRange() ) ) {
        return false;
    }
    if ( free_format_title == null ) {
      if ( row.getFreeFormatTitle() != null )
        return false;
    } else if ( !free_format_title.equals( row.getFreeFormatTitle() ) ) {
        return false;
    }
    if ( free_format_text == null ) {
      if ( row.getFreeFormatText() != null )
        return false;
    } else if ( !free_format_text.equals( row.getFreeFormatText() ) ) {
        return false;
    }
    if ( info_generation_timestamp == null ) {
      if ( row.getInfoGenerationTimestamp() != null )
        return false;
    } else if ( !info_generation_timestamp.equals( row.getInfoGenerationTimestamp() ) ) {
        return false;
    }
    if ( ord_receipt_restart_timestamp == null ) {
      if ( row.getOrdReceiptRestartTimestamp() != null )
        return false;
    } else if ( !ord_receipt_restart_timestamp.equals( row.getOrdReceiptRestartTimestamp() ) ) {
        return false;
    }
    if ( trade_stop_restart_timestamp == null ) {
      if ( row.getTradeStopRestartTimestamp() != null )
        return false;
    } else if ( !trade_stop_restart_timestamp.equals( row.getTradeStopRestartTimestamp() ) ) {
        return false;
    }
    if ( status == null ) {
      if ( row.getStatus() != null )
        return false;
    } else if ( !status.equals( row.getStatus() ) ) {
        return false;
    }
    if ( created_timestamp == null ) {
      if ( row.getCreatedTimestamp() != null )
        return false;
    } else if ( !created_timestamp.equals( row.getCreatedTimestamp() ) ) {
        return false;
    }
    if ( last_updated_timestamp == null ) {
      if ( row.getLastUpdatedTimestamp() != null )
        return false;
    } else if ( !last_updated_timestamp.equals( row.getLastUpdatedTimestamp() ) ) {
        return false;
    }
    if ( rowid == null ) {
      if ( row.getRowid() != null )
        return false;
    } else if ( !rowid.equals( row.getRowid() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  (request_code!=null? request_code.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (product_code!=null? product_code.hashCode(): 0) 
        + (sonar_market_code!=null? sonar_market_code.hashCode(): 0) 
        + (attention_info_div_code!=null? attention_info_div_code.hashCode(): 0) 
        + (base_price!=null? base_price.hashCode(): 0) 
        + (high_price_range!=null? high_price_range.hashCode(): 0) 
        + (low_price_range!=null? low_price_range.hashCode(): 0) 
        + (free_format_title!=null? free_format_title.hashCode(): 0) 
        + (free_format_text!=null? free_format_text.hashCode(): 0) 
        + (info_generation_timestamp!=null? info_generation_timestamp.hashCode(): 0) 
        + (ord_receipt_restart_timestamp!=null? ord_receipt_restart_timestamp.hashCode(): 0) 
        + (trade_stop_restart_timestamp!=null? trade_stop_restart_timestamp.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (rowid!=null? rowid.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		if ( request_code != null )
			map.put("request_code",request_code);
		if ( institution_code != null )
			map.put("institution_code",institution_code);
		if ( product_code != null )
			map.put("product_code",product_code);
		if ( sonar_market_code != null )
			map.put("sonar_market_code",sonar_market_code);
		if ( attention_info_div_code != null )
			map.put("attention_info_div_code",attention_info_div_code);
		if ( base_price != null )
			map.put("base_price",base_price);
		if ( high_price_range != null )
			map.put("high_price_range",high_price_range);
		if ( low_price_range != null )
			map.put("low_price_range",low_price_range);
		if ( free_format_title != null )
			map.put("free_format_title",free_format_title);
		if ( free_format_text != null )
			map.put("free_format_text",free_format_text);
		if ( info_generation_timestamp != null )
			map.put("info_generation_timestamp",info_generation_timestamp);
		if ( ord_receipt_restart_timestamp != null )
			map.put("ord_receipt_restart_timestamp",ord_receipt_restart_timestamp);
		if ( trade_stop_restart_timestamp != null )
			map.put("trade_stop_restart_timestamp",trade_stop_restart_timestamp);
		if ( status != null )
			map.put("status",status);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		map.put("rowid",rowid);
		map.remove("rowid");
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( request_code_is_modified )
			map.put("request_code",request_code);
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( product_code_is_modified )
			map.put("product_code",product_code);
		if ( sonar_market_code_is_modified )
			map.put("sonar_market_code",sonar_market_code);
		if ( attention_info_div_code_is_modified )
			map.put("attention_info_div_code",attention_info_div_code);
		if ( base_price_is_modified )
			map.put("base_price",base_price);
		if ( high_price_range_is_modified )
			map.put("high_price_range",high_price_range);
		if ( low_price_range_is_modified )
			map.put("low_price_range",low_price_range);
		if ( free_format_title_is_modified )
			map.put("free_format_title",free_format_title);
		if ( free_format_text_is_modified )
			map.put("free_format_text",free_format_text);
		if ( info_generation_timestamp_is_modified )
			map.put("info_generation_timestamp",info_generation_timestamp);
		if ( ord_receipt_restart_timestamp_is_modified )
			map.put("ord_receipt_restart_timestamp",ord_receipt_restart_timestamp);
		if ( trade_stop_restart_timestamp_is_modified )
			map.put("trade_stop_restart_timestamp",trade_stop_restart_timestamp);
		if ( status_is_modified )
			map.put("status",status);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("request_code",request_code);
			map.put("institution_code",institution_code);
			map.put("product_code",product_code);
			map.put("sonar_market_code",sonar_market_code);
			map.put("attention_info_div_code",attention_info_div_code);
			map.put("base_price",base_price);
			map.put("high_price_range",high_price_range);
			map.put("low_price_range",low_price_range);
			map.put("free_format_title",free_format_title);
			map.put("free_format_text",free_format_text);
			map.put("info_generation_timestamp",info_generation_timestamp);
			map.put("ord_receipt_restart_timestamp",ord_receipt_restart_timestamp);
			map.put("trade_stop_restart_timestamp",trade_stop_restart_timestamp);
			map.put("status",status);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>request_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRequestCode()
  {
    return request_code;
  }


  /** 
   * <em>request_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRequestCodeIsSet() {
    return request_code_is_set;
  }


  /** 
   * <em>request_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRequestCodeIsModified() {
    return request_code_is_modified;
  }


  /** 
   * <em>institution_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInstitutionCode()
  {
    return institution_code;
  }


  /** 
   * <em>institution_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstitutionCodeIsSet() {
    return institution_code_is_set;
  }


  /** 
   * <em>institution_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstitutionCodeIsModified() {
    return institution_code_is_modified;
  }


  /** 
   * <em>product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getProductCode()
  {
    return product_code;
  }


  /** 
   * <em>product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductCodeIsSet() {
    return product_code_is_set;
  }


  /** 
   * <em>product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductCodeIsModified() {
    return product_code_is_modified;
  }


  /** 
   * <em>sonar_market_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSonarMarketCode()
  {
    return sonar_market_code;
  }


  /** 
   * <em>sonar_market_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarMarketCodeIsSet() {
    return sonar_market_code_is_set;
  }


  /** 
   * <em>sonar_market_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarMarketCodeIsModified() {
    return sonar_market_code_is_modified;
  }


  /** 
   * <em>attention_info_div_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAttentionInfoDivCode()
  {
    return attention_info_div_code;
  }


  /** 
   * <em>attention_info_div_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAttentionInfoDivCodeIsSet() {
    return attention_info_div_code_is_set;
  }


  /** 
   * <em>attention_info_div_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAttentionInfoDivCodeIsModified() {
    return attention_info_div_code_is_modified;
  }


  /** 
   * <em>base_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getBasePrice()
  {
    return ( base_price==null? ((double)0): base_price.doubleValue() );
  }


  /** 
   * <em>base_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getBasePriceIsNull()
  {
    return base_price == null;
  }


  /** 
   * <em>base_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBasePriceIsSet() {
    return base_price_is_set;
  }


  /** 
   * <em>base_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBasePriceIsModified() {
    return base_price_is_modified;
  }


  /** 
   * <em>high_price_range</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getHighPriceRange()
  {
    return ( high_price_range==null? ((double)0): high_price_range.doubleValue() );
  }


  /** 
   * <em>high_price_range</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getHighPriceRangeIsNull()
  {
    return high_price_range == null;
  }


  /** 
   * <em>high_price_range</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHighPriceRangeIsSet() {
    return high_price_range_is_set;
  }


  /** 
   * <em>high_price_range</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHighPriceRangeIsModified() {
    return high_price_range_is_modified;
  }


  /** 
   * <em>low_price_range</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getLowPriceRange()
  {
    return ( low_price_range==null? ((double)0): low_price_range.doubleValue() );
  }


  /** 
   * <em>low_price_range</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLowPriceRangeIsNull()
  {
    return low_price_range == null;
  }


  /** 
   * <em>low_price_range</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLowPriceRangeIsSet() {
    return low_price_range_is_set;
  }


  /** 
   * <em>low_price_range</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLowPriceRangeIsModified() {
    return low_price_range_is_modified;
  }


  /** 
   * <em>free_format_title</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFreeFormatTitle()
  {
    return free_format_title;
  }


  /** 
   * <em>free_format_title</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFreeFormatTitleIsSet() {
    return free_format_title_is_set;
  }


  /** 
   * <em>free_format_title</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFreeFormatTitleIsModified() {
    return free_format_title_is_modified;
  }


  /** 
   * <em>free_format_text</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFreeFormatText()
  {
    return free_format_text;
  }


  /** 
   * <em>free_format_text</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFreeFormatTextIsSet() {
    return free_format_text_is_set;
  }


  /** 
   * <em>free_format_text</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFreeFormatTextIsModified() {
    return free_format_text_is_modified;
  }


  /** 
   * <em>info_generation_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getInfoGenerationTimestamp()
  {
    return info_generation_timestamp;
  }


  /** 
   * <em>info_generation_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInfoGenerationTimestampIsSet() {
    return info_generation_timestamp_is_set;
  }


  /** 
   * <em>info_generation_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInfoGenerationTimestampIsModified() {
    return info_generation_timestamp_is_modified;
  }


  /** 
   * <em>ord_receipt_restart_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getOrdReceiptRestartTimestamp()
  {
    return ord_receipt_restart_timestamp;
  }


  /** 
   * <em>ord_receipt_restart_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrdReceiptRestartTimestampIsSet() {
    return ord_receipt_restart_timestamp_is_set;
  }


  /** 
   * <em>ord_receipt_restart_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrdReceiptRestartTimestampIsModified() {
    return ord_receipt_restart_timestamp_is_modified;
  }


  /** 
   * <em>trade_stop_restart_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getTradeStopRestartTimestamp()
  {
    return trade_stop_restart_timestamp;
  }


  /** 
   * <em>trade_stop_restart_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradeStopRestartTimestampIsSet() {
    return trade_stop_restart_timestamp_is_set;
  }


  /** 
   * <em>trade_stop_restart_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradeStopRestartTimestampIsModified() {
    return trade_stop_restart_timestamp_is_modified;
  }


  /** 
   * <em>status</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStatus()
  {
    return status;
  }


  /** 
   * <em>status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStatusIsSet() {
    return status_is_set;
  }


  /** 
   * <em>status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStatusIsModified() {
    return status_is_modified;
  }


  /** 
   * <em>created_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getCreatedTimestamp()
  {
    return created_timestamp;
  }


  /** 
   * <em>created_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreatedTimestampIsSet() {
    return created_timestamp_is_set;
  }


  /** 
   * <em>created_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreatedTimestampIsModified() {
    return created_timestamp_is_modified;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getLastUpdatedTimestamp()
  {
    return last_updated_timestamp;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdatedTimestampIsSet() {
    return last_updated_timestamp_is_set;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdatedTimestampIsModified() {
    return last_updated_timestamp_is_modified;
  }


  /** 
   * <em>rowid</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRowid()
  {
    return rowid;
  }


  /** 
   * <em>rowid</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRowidIsSet() {
    return rowid_is_set;
  }


  /** 
   * <em>rowid</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRowidIsModified() {
    return rowid_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new HostAttentionInfoNotifyPK(rowid);
  }


  /** 
   * <em>request_code</em>カラムの値を設定します。 
   *
   * @@param p_requestCode <em>request_code</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setRequestCode( String p_requestCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    request_code = p_requestCode;
    request_code_is_set = true;
    request_code_is_modified = true;
  }


  /** 
   * <em>institution_code</em>カラムの値を設定します。 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setInstitutionCode( String p_institutionCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    institution_code = p_institutionCode;
    institution_code_is_set = true;
    institution_code_is_modified = true;
  }


  /** 
   * <em>product_code</em>カラムの値を設定します。 
   *
   * @@param p_productCode <em>product_code</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setProductCode( String p_productCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_code = p_productCode;
    product_code_is_set = true;
    product_code_is_modified = true;
  }


  /** 
   * <em>sonar_market_code</em>カラムの値を設定します。 
   *
   * @@param p_sonarMarketCode <em>sonar_market_code</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSonarMarketCode( String p_sonarMarketCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sonar_market_code = p_sonarMarketCode;
    sonar_market_code_is_set = true;
    sonar_market_code_is_modified = true;
  }


  /** 
   * <em>attention_info_div_code</em>カラムの値を設定します。 
   *
   * @@param p_attentionInfoDivCode <em>attention_info_div_code</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setAttentionInfoDivCode( String p_attentionInfoDivCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    attention_info_div_code = p_attentionInfoDivCode;
    attention_info_div_code_is_set = true;
    attention_info_div_code_is_modified = true;
  }


  /** 
   * <em>base_price</em>カラムの値を設定します。 
   *
   * @@param p_basePrice <em>base_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setBasePrice( double p_basePrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    base_price = new Double(p_basePrice);
    base_price_is_set = true;
    base_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>base_price</em>カラムに値を設定します。 
   */
  public final void setBasePrice( Double p_basePrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    base_price = p_basePrice;
    base_price_is_set = true;
    base_price_is_modified = true;
  }


  /** 
   * <em>high_price_range</em>カラムの値を設定します。 
   *
   * @@param p_highPriceRange <em>high_price_range</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setHighPriceRange( double p_highPriceRange )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    high_price_range = new Double(p_highPriceRange);
    high_price_range_is_set = true;
    high_price_range_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>high_price_range</em>カラムに値を設定します。 
   */
  public final void setHighPriceRange( Double p_highPriceRange )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    high_price_range = p_highPriceRange;
    high_price_range_is_set = true;
    high_price_range_is_modified = true;
  }


  /** 
   * <em>low_price_range</em>カラムの値を設定します。 
   *
   * @@param p_lowPriceRange <em>low_price_range</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setLowPriceRange( double p_lowPriceRange )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    low_price_range = new Double(p_lowPriceRange);
    low_price_range_is_set = true;
    low_price_range_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>low_price_range</em>カラムに値を設定します。 
   */
  public final void setLowPriceRange( Double p_lowPriceRange )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    low_price_range = p_lowPriceRange;
    low_price_range_is_set = true;
    low_price_range_is_modified = true;
  }


  /** 
   * <em>free_format_title</em>カラムの値を設定します。 
   *
   * @@param p_freeFormatTitle <em>free_format_title</em>カラムの値をあらわす60桁以下のString値 
   */
  public final void setFreeFormatTitle( String p_freeFormatTitle )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    free_format_title = p_freeFormatTitle;
    free_format_title_is_set = true;
    free_format_title_is_modified = true;
  }


  /** 
   * <em>free_format_text</em>カラムの値を設定します。 
   *
   * @@param p_freeFormatText <em>free_format_text</em>カラムの値をあらわす600桁以下のString値 
   */
  public final void setFreeFormatText( String p_freeFormatText )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    free_format_text = p_freeFormatText;
    free_format_text_is_set = true;
    free_format_text_is_modified = true;
  }


  /** 
   * <em>info_generation_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_infoGenerationTimestamp <em>info_generation_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setInfoGenerationTimestamp( java.sql.Timestamp p_infoGenerationTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    info_generation_timestamp = p_infoGenerationTimestamp;
    info_generation_timestamp_is_set = true;
    info_generation_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>info_generation_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setInfoGenerationTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    info_generation_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    info_generation_timestamp_is_set = true;
    info_generation_timestamp_is_modified = true;
  }


  /** 
   * <em>ord_receipt_restart_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_ordReceiptRestartTimestamp <em>ord_receipt_restart_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setOrdReceiptRestartTimestamp( java.sql.Timestamp p_ordReceiptRestartTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ord_receipt_restart_timestamp = p_ordReceiptRestartTimestamp;
    ord_receipt_restart_timestamp_is_set = true;
    ord_receipt_restart_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>ord_receipt_restart_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setOrdReceiptRestartTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ord_receipt_restart_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    ord_receipt_restart_timestamp_is_set = true;
    ord_receipt_restart_timestamp_is_modified = true;
  }


  /** 
   * <em>trade_stop_restart_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_tradeStopRestartTimestamp <em>trade_stop_restart_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setTradeStopRestartTimestamp( java.sql.Timestamp p_tradeStopRestartTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trade_stop_restart_timestamp = p_tradeStopRestartTimestamp;
    trade_stop_restart_timestamp_is_set = true;
    trade_stop_restart_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>trade_stop_restart_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setTradeStopRestartTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    trade_stop_restart_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    trade_stop_restart_timestamp_is_set = true;
    trade_stop_restart_timestamp_is_modified = true;
  }


  /** 
   * <em>status</em>カラムの値を設定します。 
   *
   * @@param p_status <em>status</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setStatus( String p_status )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    status = p_status;
    status_is_set = true;
    status_is_modified = true;
  }


  /** 
   * <em>created_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_createdTimestamp <em>created_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setCreatedTimestamp( java.sql.Timestamp p_createdTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = p_createdTimestamp;
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>created_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setCreatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_lastUpdatedTimestamp <em>last_updated_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setLastUpdatedTimestamp( java.sql.Timestamp p_lastUpdatedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = p_lastUpdatedTimestamp;
    last_updated_timestamp_is_set = true;
    last_updated_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>last_updated_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setLastUpdatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    last_updated_timestamp_is_set = true;
    last_updated_timestamp_is_modified = true;
  }


  /** 
   * <em>rowid</em>カラムの値を設定します。 
   *
   * @@param p_rowid <em>rowid</em>カラムの値をあらわす20桁以下のString値で、その精度は20桁まで
   */
  public final void setRowid( String p_rowid )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    rowid = p_rowid;
    rowid_is_set = true;
    rowid_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("attention_info_div_code") ) {
                    return this.attention_info_div_code;
                }
                break;
            case 'b':
                if ( name.equals("base_price") ) {
                    return this.base_price;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'f':
                if ( name.equals("free_format_title") ) {
                    return this.free_format_title;
                }
                else if ( name.equals("free_format_text") ) {
                    return this.free_format_text;
                }
                break;
            case 'h':
                if ( name.equals("high_price_range") ) {
                    return this.high_price_range;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("info_generation_timestamp") ) {
                    return this.info_generation_timestamp;
                }
                break;
            case 'l':
                if ( name.equals("low_price_range") ) {
                    return this.low_price_range;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'o':
                if ( name.equals("ord_receipt_restart_timestamp") ) {
                    return this.ord_receipt_restart_timestamp;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    return this.product_code;
                }
                break;
            case 'r':
                if ( name.equals("request_code") ) {
                    return this.request_code;
                }
                else if ( name.equals("rowid") ) {
                    return this.rowid;
                }
                break;
            case 's':
                if ( name.equals("sonar_market_code") ) {
                    return this.sonar_market_code;
                }
                else if ( name.equals("status") ) {
                    return this.status;
                }
                break;
            case 't':
                if ( name.equals("trade_stop_restart_timestamp") ) {
                    return this.trade_stop_restart_timestamp;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }


  /** 
   * @@see com.fitechlabs.dbind.Params#setColumn(String, Object) 
   */
    public void setColumn( String name, Object value ) {
        if(!mutable())
            throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("attention_info_div_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'attention_info_div_code' must be String: '"+value+"' is not." );
                    this.attention_info_div_code = (String) value;
                    if (this.attention_info_div_code_is_set)
                        this.attention_info_div_code_is_modified = true;
                    this.attention_info_div_code_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("base_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'base_price' must be Double: '"+value+"' is not." );
                    this.base_price = (Double) value;
                    if (this.base_price_is_set)
                        this.base_price_is_modified = true;
                    this.base_price_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    if (this.created_timestamp_is_set)
                        this.created_timestamp_is_modified = true;
                    this.created_timestamp_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("free_format_title") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'free_format_title' must be String: '"+value+"' is not." );
                    this.free_format_title = (String) value;
                    if (this.free_format_title_is_set)
                        this.free_format_title_is_modified = true;
                    this.free_format_title_is_set = true;
                    return;
                }
                else if ( name.equals("free_format_text") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'free_format_text' must be String: '"+value+"' is not." );
                    this.free_format_text = (String) value;
                    if (this.free_format_text_is_set)
                        this.free_format_text_is_modified = true;
                    this.free_format_text_is_set = true;
                    return;
                }
                break;
            case 'h':
                if ( name.equals("high_price_range") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'high_price_range' must be Double: '"+value+"' is not." );
                    this.high_price_range = (Double) value;
                    if (this.high_price_range_is_set)
                        this.high_price_range_is_modified = true;
                    this.high_price_range_is_set = true;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'institution_code' must be String: '"+value+"' is not." );
                    this.institution_code = (String) value;
                    if (this.institution_code_is_set)
                        this.institution_code_is_modified = true;
                    this.institution_code_is_set = true;
                    return;
                }
                else if ( name.equals("info_generation_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'info_generation_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.info_generation_timestamp = (java.sql.Timestamp) value;
                    if (this.info_generation_timestamp_is_set)
                        this.info_generation_timestamp_is_modified = true;
                    this.info_generation_timestamp_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("low_price_range") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'low_price_range' must be Double: '"+value+"' is not." );
                    this.low_price_range = (Double) value;
                    if (this.low_price_range_is_set)
                        this.low_price_range_is_modified = true;
                    this.low_price_range_is_set = true;
                    return;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.last_updated_timestamp_is_set)
                        this.last_updated_timestamp_is_modified = true;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("ord_receipt_restart_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'ord_receipt_restart_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.ord_receipt_restart_timestamp = (java.sql.Timestamp) value;
                    if (this.ord_receipt_restart_timestamp_is_set)
                        this.ord_receipt_restart_timestamp_is_modified = true;
                    this.ord_receipt_restart_timestamp_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_code' must be String: '"+value+"' is not." );
                    this.product_code = (String) value;
                    if (this.product_code_is_set)
                        this.product_code_is_modified = true;
                    this.product_code_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("request_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'request_code' must be String: '"+value+"' is not." );
                    this.request_code = (String) value;
                    if (this.request_code_is_set)
                        this.request_code_is_modified = true;
                    this.request_code_is_set = true;
                    return;
                }
                else if ( name.equals("rowid") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'rowid' must be String: '"+value+"' is not." );
                    this.rowid = (String) value;
                    if (this.rowid_is_set)
                        this.rowid_is_modified = true;
                    this.rowid_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sonar_market_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sonar_market_code' must be String: '"+value+"' is not." );
                    this.sonar_market_code = (String) value;
                    if (this.sonar_market_code_is_set)
                        this.sonar_market_code_is_modified = true;
                    this.sonar_market_code_is_set = true;
                    return;
                }
                else if ( name.equals("status") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'status' must be String: '"+value+"' is not." );
                    this.status = (String) value;
                    if (this.status_is_set)
                        this.status_is_modified = true;
                    this.status_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("trade_stop_restart_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'trade_stop_restart_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.trade_stop_restart_timestamp = (java.sql.Timestamp) value;
                    if (this.trade_stop_restart_timestamp_is_set)
                        this.trade_stop_restart_timestamp_is_modified = true;
                    this.trade_stop_restart_timestamp_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
