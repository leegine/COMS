head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.16.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4c04d8868c00da0;
filename	RuitoProductParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbruito.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * ruito_productテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link RuitoProductRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link RuitoProductParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link RuitoProductParams}が{@@link RuitoProductRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see RuitoProductPK 
 * @@see RuitoProductRow 
 */
public class RuitoProductParams extends Params implements RuitoProductRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "ruito_product";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = RuitoProductRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return RuitoProductRow.TYPE;
  }


  /** 
   * <em>product_id</em>カラムの値 
   */
  public  long  product_id;

  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>product_code</em>カラムの値 
   */
  public  String  product_code;

  /** 
   * <em>product_issue_code</em>カラムの値 
   */
  public  String  product_issue_code;

  /** 
   * <em>ruito_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum  ruito_type;

  /** 
   * <em>init_purchase_min_qty</em>カラムの値 
   */
  public  long  init_purchase_min_qty;

  /** 
   * <em>addtl_purchase_min_qty</em>カラムの値 
   */
  public  long  addtl_purchase_min_qty;

  /** 
   * <em>standard_name</em>カラムの値 
   */
  public  String  standard_name;

  /** 
   * <em>course</em>カラムの値 
   */
  public  String  course;

  /** 
   * <em>plan</em>カラムの値 
   */
  public  String  plan;

  /** 
   * <em>mrf_fund_code</em>カラムの値 
   */
  public  String  mrf_fund_code;

  /** 
   * <em>buy_max_price</em>カラムの値 
   */
  public  Long  buy_max_price;

  /** 
   * <em>buy_min_price</em>カラムの値 
   */
  public  Long  buy_min_price;

  /** 
   * <em>sell_max_price</em>カラムの値 
   */
  public  Long  sell_max_price;

  /** 
   * <em>sell_min_price</em>カラムの値 
   */
  public  Long  sell_min_price;

  /** 
   * <em>buy_designate_method</em>カラムの値 
   */
  public  String  buy_designate_method;

  /** 
   * <em>sell_designate_method</em>カラムの値 
   */
  public  String  sell_designate_method;

  /** 
   * <em>payment_method</em>カラムの値 
   */
  public  String  payment_method;

  /** 
   * <em>buy_start_date</em>カラムの値 
   */
  public  java.sql.Timestamp  buy_start_date;

  /** 
   * <em>buy_end_date</em>カラムの値 
   */
  public  java.sql.Timestamp  buy_end_date;

  /** 
   * <em>sell_start_date</em>カラムの値 
   */
  public  java.sql.Timestamp  sell_start_date;

  /** 
   * <em>sell_end_date</em>カラムの値 
   */
  public  java.sql.Timestamp  sell_end_date;

  /** 
   * <em>last_updater</em>カラムの値 
   */
  public  String  last_updater;

  /** 
   * <em>online_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  online_updated_timestamp;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean product_id_is_set = false;
  private boolean product_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;
  private boolean product_issue_code_is_set = false;
  private boolean product_issue_code_is_modified = false;
  private boolean ruito_type_is_set = false;
  private boolean ruito_type_is_modified = false;
  private boolean init_purchase_min_qty_is_set = false;
  private boolean init_purchase_min_qty_is_modified = false;
  private boolean addtl_purchase_min_qty_is_set = false;
  private boolean addtl_purchase_min_qty_is_modified = false;
  private boolean standard_name_is_set = false;
  private boolean standard_name_is_modified = false;
  private boolean course_is_set = false;
  private boolean course_is_modified = false;
  private boolean plan_is_set = false;
  private boolean plan_is_modified = false;
  private boolean mrf_fund_code_is_set = false;
  private boolean mrf_fund_code_is_modified = false;
  private boolean buy_max_price_is_set = false;
  private boolean buy_max_price_is_modified = false;
  private boolean buy_min_price_is_set = false;
  private boolean buy_min_price_is_modified = false;
  private boolean sell_max_price_is_set = false;
  private boolean sell_max_price_is_modified = false;
  private boolean sell_min_price_is_set = false;
  private boolean sell_min_price_is_modified = false;
  private boolean buy_designate_method_is_set = false;
  private boolean buy_designate_method_is_modified = false;
  private boolean sell_designate_method_is_set = false;
  private boolean sell_designate_method_is_modified = false;
  private boolean payment_method_is_set = false;
  private boolean payment_method_is_modified = false;
  private boolean buy_start_date_is_set = false;
  private boolean buy_start_date_is_modified = false;
  private boolean buy_end_date_is_set = false;
  private boolean buy_end_date_is_modified = false;
  private boolean sell_start_date_is_set = false;
  private boolean sell_start_date_is_modified = false;
  private boolean sell_end_date_is_set = false;
  private boolean sell_end_date_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean online_updated_timestamp_is_set = false;
  private boolean online_updated_timestamp_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "product_id=" + product_id
      + "," + "institution_code=" +institution_code
      + "," + "product_code=" +product_code
      + "," + "product_issue_code=" +product_issue_code
      + "," + "ruito_type=" +ruito_type
      + "," + "init_purchase_min_qty=" +init_purchase_min_qty
      + "," + "addtl_purchase_min_qty=" +addtl_purchase_min_qty
      + "," + "standard_name=" +standard_name
      + "," + "course=" +course
      + "," + "plan=" +plan
      + "," + "mrf_fund_code=" +mrf_fund_code
      + "," + "buy_max_price=" +buy_max_price
      + "," + "buy_min_price=" +buy_min_price
      + "," + "sell_max_price=" +sell_max_price
      + "," + "sell_min_price=" +sell_min_price
      + "," + "buy_designate_method=" +buy_designate_method
      + "," + "sell_designate_method=" +sell_designate_method
      + "," + "payment_method=" +payment_method
      + "," + "buy_start_date=" +buy_start_date
      + "," + "buy_end_date=" +buy_end_date
      + "," + "sell_start_date=" +sell_start_date
      + "," + "sell_end_date=" +sell_end_date
      + "," + "last_updater=" +last_updater
      + "," + "online_updated_timestamp=" +online_updated_timestamp
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のRuitoProductParamsオブジェクトを作成します。 
   */
  public RuitoProductParams() {
    product_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のRuitoProductRowオブジェクトの値を利用してRuitoProductParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するRuitoProductRowオブジェクト 
   */
  public RuitoProductParams( RuitoProductRow p_row )
  {
    product_id = p_row.getProductId();
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    product_code = p_row.getProductCode();
    product_code_is_set = p_row.getProductCodeIsSet();
    product_code_is_modified = p_row.getProductCodeIsModified();
    product_issue_code = p_row.getProductIssueCode();
    product_issue_code_is_set = p_row.getProductIssueCodeIsSet();
    product_issue_code_is_modified = p_row.getProductIssueCodeIsModified();
    ruito_type = p_row.getRuitoType();
    ruito_type_is_set = p_row.getRuitoTypeIsSet();
    ruito_type_is_modified = p_row.getRuitoTypeIsModified();
    init_purchase_min_qty = p_row.getInitPurchaseMinQty();
    init_purchase_min_qty_is_set = p_row.getInitPurchaseMinQtyIsSet();
    init_purchase_min_qty_is_modified = p_row.getInitPurchaseMinQtyIsModified();
    addtl_purchase_min_qty = p_row.getAddtlPurchaseMinQty();
    addtl_purchase_min_qty_is_set = p_row.getAddtlPurchaseMinQtyIsSet();
    addtl_purchase_min_qty_is_modified = p_row.getAddtlPurchaseMinQtyIsModified();
    standard_name = p_row.getStandardName();
    standard_name_is_set = p_row.getStandardNameIsSet();
    standard_name_is_modified = p_row.getStandardNameIsModified();
    course = p_row.getCourse();
    course_is_set = p_row.getCourseIsSet();
    course_is_modified = p_row.getCourseIsModified();
    plan = p_row.getPlan();
    plan_is_set = p_row.getPlanIsSet();
    plan_is_modified = p_row.getPlanIsModified();
    mrf_fund_code = p_row.getMrfFundCode();
    mrf_fund_code_is_set = p_row.getMrfFundCodeIsSet();
    mrf_fund_code_is_modified = p_row.getMrfFundCodeIsModified();
    if ( !p_row.getBuyMaxPriceIsNull() )
      buy_max_price = new Long( p_row.getBuyMaxPrice() );
    buy_max_price_is_set = p_row.getBuyMaxPriceIsSet();
    buy_max_price_is_modified = p_row.getBuyMaxPriceIsModified();
    if ( !p_row.getBuyMinPriceIsNull() )
      buy_min_price = new Long( p_row.getBuyMinPrice() );
    buy_min_price_is_set = p_row.getBuyMinPriceIsSet();
    buy_min_price_is_modified = p_row.getBuyMinPriceIsModified();
    if ( !p_row.getSellMaxPriceIsNull() )
      sell_max_price = new Long( p_row.getSellMaxPrice() );
    sell_max_price_is_set = p_row.getSellMaxPriceIsSet();
    sell_max_price_is_modified = p_row.getSellMaxPriceIsModified();
    if ( !p_row.getSellMinPriceIsNull() )
      sell_min_price = new Long( p_row.getSellMinPrice() );
    sell_min_price_is_set = p_row.getSellMinPriceIsSet();
    sell_min_price_is_modified = p_row.getSellMinPriceIsModified();
    buy_designate_method = p_row.getBuyDesignateMethod();
    buy_designate_method_is_set = p_row.getBuyDesignateMethodIsSet();
    buy_designate_method_is_modified = p_row.getBuyDesignateMethodIsModified();
    sell_designate_method = p_row.getSellDesignateMethod();
    sell_designate_method_is_set = p_row.getSellDesignateMethodIsSet();
    sell_designate_method_is_modified = p_row.getSellDesignateMethodIsModified();
    payment_method = p_row.getPaymentMethod();
    payment_method_is_set = p_row.getPaymentMethodIsSet();
    payment_method_is_modified = p_row.getPaymentMethodIsModified();
    buy_start_date = p_row.getBuyStartDate();
    buy_start_date_is_set = p_row.getBuyStartDateIsSet();
    buy_start_date_is_modified = p_row.getBuyStartDateIsModified();
    buy_end_date = p_row.getBuyEndDate();
    buy_end_date_is_set = p_row.getBuyEndDateIsSet();
    buy_end_date_is_modified = p_row.getBuyEndDateIsModified();
    sell_start_date = p_row.getSellStartDate();
    sell_start_date_is_set = p_row.getSellStartDateIsSet();
    sell_start_date_is_modified = p_row.getSellStartDateIsModified();
    sell_end_date = p_row.getSellEndDate();
    sell_end_date_is_set = p_row.getSellEndDateIsSet();
    sell_end_date_is_modified = p_row.getSellEndDateIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
    online_updated_timestamp = p_row.getOnlineUpdatedTimestamp();
    online_updated_timestamp_is_set = p_row.getOnlineUpdatedTimestampIsSet();
    online_updated_timestamp_is_modified = p_row.getOnlineUpdatedTimestampIsModified();
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
      institution_code_is_set = true;
      institution_code_is_modified = true;
      product_code_is_set = true;
      product_code_is_modified = true;
      product_issue_code_is_set = true;
      product_issue_code_is_modified = true;
      ruito_type_is_set = true;
      ruito_type_is_modified = true;
      init_purchase_min_qty_is_set = true;
      init_purchase_min_qty_is_modified = true;
      addtl_purchase_min_qty_is_set = true;
      addtl_purchase_min_qty_is_modified = true;
      standard_name_is_set = true;
      standard_name_is_modified = true;
      course_is_set = true;
      course_is_modified = true;
      plan_is_set = true;
      plan_is_modified = true;
      mrf_fund_code_is_set = true;
      mrf_fund_code_is_modified = true;
      buy_max_price_is_set = true;
      buy_max_price_is_modified = true;
      buy_min_price_is_set = true;
      buy_min_price_is_modified = true;
      sell_max_price_is_set = true;
      sell_max_price_is_modified = true;
      sell_min_price_is_set = true;
      sell_min_price_is_modified = true;
      buy_designate_method_is_set = true;
      buy_designate_method_is_modified = true;
      sell_designate_method_is_set = true;
      sell_designate_method_is_modified = true;
      payment_method_is_set = true;
      payment_method_is_modified = true;
      buy_start_date_is_set = true;
      buy_start_date_is_modified = true;
      buy_end_date_is_set = true;
      buy_end_date_is_modified = true;
      sell_start_date_is_set = true;
      sell_start_date_is_modified = true;
      sell_end_date_is_set = true;
      sell_end_date_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
      online_updated_timestamp_is_set = true;
      online_updated_timestamp_is_modified = true;
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
    if ( !( other instanceof RuitoProductRow ) )
       return false;
    return fieldsEqual( (RuitoProductRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のRuitoProductRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( RuitoProductRow row )
  {
    if ( product_id != row.getProductId() )
      return false;
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
    if ( product_issue_code == null ) {
      if ( row.getProductIssueCode() != null )
        return false;
    } else if ( !product_issue_code.equals( row.getProductIssueCode() ) ) {
        return false;
    }
    if ( ruito_type == null ) {
      if ( row.getRuitoType() != null )
        return false;
    } else if ( !ruito_type.equals( row.getRuitoType() ) ) {
        return false;
    }
    if ( init_purchase_min_qty != row.getInitPurchaseMinQty() )
      return false;
    if ( addtl_purchase_min_qty != row.getAddtlPurchaseMinQty() )
      return false;
    if ( standard_name == null ) {
      if ( row.getStandardName() != null )
        return false;
    } else if ( !standard_name.equals( row.getStandardName() ) ) {
        return false;
    }
    if ( course == null ) {
      if ( row.getCourse() != null )
        return false;
    } else if ( !course.equals( row.getCourse() ) ) {
        return false;
    }
    if ( plan == null ) {
      if ( row.getPlan() != null )
        return false;
    } else if ( !plan.equals( row.getPlan() ) ) {
        return false;
    }
    if ( mrf_fund_code == null ) {
      if ( row.getMrfFundCode() != null )
        return false;
    } else if ( !mrf_fund_code.equals( row.getMrfFundCode() ) ) {
        return false;
    }
    if ( buy_max_price == null ) {
      if ( !row.getBuyMaxPriceIsNull() )
        return false;
    } else if ( row.getBuyMaxPriceIsNull() || ( buy_max_price.longValue() != row.getBuyMaxPrice() ) ) {
        return false;
    }
    if ( buy_min_price == null ) {
      if ( !row.getBuyMinPriceIsNull() )
        return false;
    } else if ( row.getBuyMinPriceIsNull() || ( buy_min_price.longValue() != row.getBuyMinPrice() ) ) {
        return false;
    }
    if ( sell_max_price == null ) {
      if ( !row.getSellMaxPriceIsNull() )
        return false;
    } else if ( row.getSellMaxPriceIsNull() || ( sell_max_price.longValue() != row.getSellMaxPrice() ) ) {
        return false;
    }
    if ( sell_min_price == null ) {
      if ( !row.getSellMinPriceIsNull() )
        return false;
    } else if ( row.getSellMinPriceIsNull() || ( sell_min_price.longValue() != row.getSellMinPrice() ) ) {
        return false;
    }
    if ( buy_designate_method == null ) {
      if ( row.getBuyDesignateMethod() != null )
        return false;
    } else if ( !buy_designate_method.equals( row.getBuyDesignateMethod() ) ) {
        return false;
    }
    if ( sell_designate_method == null ) {
      if ( row.getSellDesignateMethod() != null )
        return false;
    } else if ( !sell_designate_method.equals( row.getSellDesignateMethod() ) ) {
        return false;
    }
    if ( payment_method == null ) {
      if ( row.getPaymentMethod() != null )
        return false;
    } else if ( !payment_method.equals( row.getPaymentMethod() ) ) {
        return false;
    }
    if ( buy_start_date == null ) {
      if ( row.getBuyStartDate() != null )
        return false;
    } else if ( !buy_start_date.equals( row.getBuyStartDate() ) ) {
        return false;
    }
    if ( buy_end_date == null ) {
      if ( row.getBuyEndDate() != null )
        return false;
    } else if ( !buy_end_date.equals( row.getBuyEndDate() ) ) {
        return false;
    }
    if ( sell_start_date == null ) {
      if ( row.getSellStartDate() != null )
        return false;
    } else if ( !sell_start_date.equals( row.getSellStartDate() ) ) {
        return false;
    }
    if ( sell_end_date == null ) {
      if ( row.getSellEndDate() != null )
        return false;
    } else if ( !sell_end_date.equals( row.getSellEndDate() ) ) {
        return false;
    }
    if ( last_updater == null ) {
      if ( row.getLastUpdater() != null )
        return false;
    } else if ( !last_updater.equals( row.getLastUpdater() ) ) {
        return false;
    }
    if ( online_updated_timestamp == null ) {
      if ( row.getOnlineUpdatedTimestamp() != null )
        return false;
    } else if ( !online_updated_timestamp.equals( row.getOnlineUpdatedTimestamp() ) ) {
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
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  ((int) product_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (product_code!=null? product_code.hashCode(): 0) 
        + (product_issue_code!=null? product_issue_code.hashCode(): 0) 
        + (ruito_type!=null? ruito_type.hashCode(): 0) 
        + ((int) init_purchase_min_qty)
        + ((int) addtl_purchase_min_qty)
        + (standard_name!=null? standard_name.hashCode(): 0) 
        + (course!=null? course.hashCode(): 0) 
        + (plan!=null? plan.hashCode(): 0) 
        + (mrf_fund_code!=null? mrf_fund_code.hashCode(): 0) 
        + (buy_max_price!=null? buy_max_price.hashCode(): 0) 
        + (buy_min_price!=null? buy_min_price.hashCode(): 0) 
        + (sell_max_price!=null? sell_max_price.hashCode(): 0) 
        + (sell_min_price!=null? sell_min_price.hashCode(): 0) 
        + (buy_designate_method!=null? buy_designate_method.hashCode(): 0) 
        + (sell_designate_method!=null? sell_designate_method.hashCode(): 0) 
        + (payment_method!=null? payment_method.hashCode(): 0) 
        + (buy_start_date!=null? buy_start_date.hashCode(): 0) 
        + (buy_end_date!=null? buy_end_date.hashCode(): 0) 
        + (sell_start_date!=null? sell_start_date.hashCode(): 0) 
        + (sell_end_date!=null? sell_end_date.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (online_updated_timestamp!=null? online_updated_timestamp.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !product_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_code' must be set before inserting.");
		if ( !product_issue_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_issue_code' must be set before inserting.");
		if ( !ruito_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'ruito_type' must be set before inserting.");
		if ( !init_purchase_min_qty_is_set )
			throw new IllegalArgumentException("non-nullable field 'init_purchase_min_qty' must be set before inserting.");
		if ( !addtl_purchase_min_qty_is_set )
			throw new IllegalArgumentException("non-nullable field 'addtl_purchase_min_qty' must be set before inserting.");
		if ( !last_updater_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updater' must be set before inserting.");
		if ( !online_updated_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'online_updated_timestamp' must be set before inserting.");
		if ( !created_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'created_timestamp' must be set before inserting.");
		if ( !last_updated_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updated_timestamp' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("product_id",new Long(product_id));
		map.put("institution_code",institution_code);
		map.put("product_code",product_code);
		map.put("product_issue_code",product_issue_code);
		map.put("ruito_type",ruito_type);
		map.put("init_purchase_min_qty",new Long(init_purchase_min_qty));
		map.put("addtl_purchase_min_qty",new Long(addtl_purchase_min_qty));
		if ( standard_name != null )
			map.put("standard_name",standard_name);
		if ( course != null )
			map.put("course",course);
		if ( plan != null )
			map.put("plan",plan);
		if ( mrf_fund_code != null )
			map.put("mrf_fund_code",mrf_fund_code);
		if ( buy_max_price != null )
			map.put("buy_max_price",buy_max_price);
		if ( buy_min_price != null )
			map.put("buy_min_price",buy_min_price);
		if ( sell_max_price != null )
			map.put("sell_max_price",sell_max_price);
		if ( sell_min_price != null )
			map.put("sell_min_price",sell_min_price);
		if ( buy_designate_method != null )
			map.put("buy_designate_method",buy_designate_method);
		if ( sell_designate_method != null )
			map.put("sell_designate_method",sell_designate_method);
		if ( payment_method != null )
			map.put("payment_method",payment_method);
		if ( buy_start_date != null )
			map.put("buy_start_date",buy_start_date);
		if ( buy_end_date != null )
			map.put("buy_end_date",buy_end_date);
		if ( sell_start_date != null )
			map.put("sell_start_date",sell_start_date);
		if ( sell_end_date != null )
			map.put("sell_end_date",sell_end_date);
		map.put("last_updater",last_updater);
		map.put("online_updated_timestamp",online_updated_timestamp);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( product_code_is_modified )
			map.put("product_code",product_code);
		if ( product_issue_code_is_modified )
			map.put("product_issue_code",product_issue_code);
		if ( ruito_type_is_modified )
			map.put("ruito_type",ruito_type);
		if ( init_purchase_min_qty_is_modified )
			map.put("init_purchase_min_qty",new Long(init_purchase_min_qty));
		if ( addtl_purchase_min_qty_is_modified )
			map.put("addtl_purchase_min_qty",new Long(addtl_purchase_min_qty));
		if ( standard_name_is_modified )
			map.put("standard_name",standard_name);
		if ( course_is_modified )
			map.put("course",course);
		if ( plan_is_modified )
			map.put("plan",plan);
		if ( mrf_fund_code_is_modified )
			map.put("mrf_fund_code",mrf_fund_code);
		if ( buy_max_price_is_modified )
			map.put("buy_max_price",buy_max_price);
		if ( buy_min_price_is_modified )
			map.put("buy_min_price",buy_min_price);
		if ( sell_max_price_is_modified )
			map.put("sell_max_price",sell_max_price);
		if ( sell_min_price_is_modified )
			map.put("sell_min_price",sell_min_price);
		if ( buy_designate_method_is_modified )
			map.put("buy_designate_method",buy_designate_method);
		if ( sell_designate_method_is_modified )
			map.put("sell_designate_method",sell_designate_method);
		if ( payment_method_is_modified )
			map.put("payment_method",payment_method);
		if ( buy_start_date_is_modified )
			map.put("buy_start_date",buy_start_date);
		if ( buy_end_date_is_modified )
			map.put("buy_end_date",buy_end_date);
		if ( sell_start_date_is_modified )
			map.put("sell_start_date",sell_start_date);
		if ( sell_end_date_is_modified )
			map.put("sell_end_date",sell_end_date);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( online_updated_timestamp_is_modified )
			map.put("online_updated_timestamp",online_updated_timestamp);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( product_code_is_set )
				map.put("product_code",product_code);
			if ( product_issue_code_is_set )
				map.put("product_issue_code",product_issue_code);
			if ( ruito_type_is_set )
				map.put("ruito_type",ruito_type);
			if ( init_purchase_min_qty_is_set )
				map.put("init_purchase_min_qty",new Long(init_purchase_min_qty));
			if ( addtl_purchase_min_qty_is_set )
				map.put("addtl_purchase_min_qty",new Long(addtl_purchase_min_qty));
			map.put("standard_name",standard_name);
			map.put("course",course);
			map.put("plan",plan);
			map.put("mrf_fund_code",mrf_fund_code);
			map.put("buy_max_price",buy_max_price);
			map.put("buy_min_price",buy_min_price);
			map.put("sell_max_price",sell_max_price);
			map.put("sell_min_price",sell_min_price);
			map.put("buy_designate_method",buy_designate_method);
			map.put("sell_designate_method",sell_designate_method);
			map.put("payment_method",payment_method);
			map.put("buy_start_date",buy_start_date);
			map.put("buy_end_date",buy_end_date);
			map.put("sell_start_date",sell_start_date);
			map.put("sell_end_date",sell_end_date);
			if ( last_updater_is_set )
				map.put("last_updater",last_updater);
			if ( online_updated_timestamp_is_set )
				map.put("online_updated_timestamp",online_updated_timestamp);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>product_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getProductId()
  {
    return product_id;
  }


  /** 
   * <em>product_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductIdIsSet() {
    return product_id_is_set;
  }


  /** 
   * <em>product_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductIdIsModified() {
    return product_id_is_modified;
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
   * <em>product_issue_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getProductIssueCode()
  {
    return product_issue_code;
  }


  /** 
   * <em>product_issue_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductIssueCodeIsSet() {
    return product_issue_code_is_set;
  }


  /** 
   * <em>product_issue_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductIssueCodeIsModified() {
    return product_issue_code_is_modified;
  }


  /** 
   * <em>ruito_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum getRuitoType()
  {
    return ruito_type;
  }


  /** 
   * <em>ruito_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRuitoTypeIsSet() {
    return ruito_type_is_set;
  }


  /** 
   * <em>ruito_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRuitoTypeIsModified() {
    return ruito_type_is_modified;
  }


  /** 
   * <em>init_purchase_min_qty</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getInitPurchaseMinQty()
  {
    return init_purchase_min_qty;
  }


  /** 
   * <em>init_purchase_min_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInitPurchaseMinQtyIsSet() {
    return init_purchase_min_qty_is_set;
  }


  /** 
   * <em>init_purchase_min_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInitPurchaseMinQtyIsModified() {
    return init_purchase_min_qty_is_modified;
  }


  /** 
   * <em>addtl_purchase_min_qty</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAddtlPurchaseMinQty()
  {
    return addtl_purchase_min_qty;
  }


  /** 
   * <em>addtl_purchase_min_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddtlPurchaseMinQtyIsSet() {
    return addtl_purchase_min_qty_is_set;
  }


  /** 
   * <em>addtl_purchase_min_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddtlPurchaseMinQtyIsModified() {
    return addtl_purchase_min_qty_is_modified;
  }


  /** 
   * <em>standard_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStandardName()
  {
    return standard_name;
  }


  /** 
   * <em>standard_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStandardNameIsSet() {
    return standard_name_is_set;
  }


  /** 
   * <em>standard_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStandardNameIsModified() {
    return standard_name_is_modified;
  }


  /** 
   * <em>course</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCourse()
  {
    return course;
  }


  /** 
   * <em>course</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCourseIsSet() {
    return course_is_set;
  }


  /** 
   * <em>course</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCourseIsModified() {
    return course_is_modified;
  }


  /** 
   * <em>plan</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPlan()
  {
    return plan;
  }


  /** 
   * <em>plan</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPlanIsSet() {
    return plan_is_set;
  }


  /** 
   * <em>plan</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPlanIsModified() {
    return plan_is_modified;
  }


  /** 
   * <em>mrf_fund_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMrfFundCode()
  {
    return mrf_fund_code;
  }


  /** 
   * <em>mrf_fund_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMrfFundCodeIsSet() {
    return mrf_fund_code_is_set;
  }


  /** 
   * <em>mrf_fund_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMrfFundCodeIsModified() {
    return mrf_fund_code_is_modified;
  }


  /** 
   * <em>buy_max_price</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getBuyMaxPrice()
  {
    return ( buy_max_price==null? ((long)0): buy_max_price.longValue() );
  }


  /** 
   * <em>buy_max_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getBuyMaxPriceIsNull()
  {
    return buy_max_price == null;
  }


  /** 
   * <em>buy_max_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyMaxPriceIsSet() {
    return buy_max_price_is_set;
  }


  /** 
   * <em>buy_max_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyMaxPriceIsModified() {
    return buy_max_price_is_modified;
  }


  /** 
   * <em>buy_min_price</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getBuyMinPrice()
  {
    return ( buy_min_price==null? ((long)0): buy_min_price.longValue() );
  }


  /** 
   * <em>buy_min_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getBuyMinPriceIsNull()
  {
    return buy_min_price == null;
  }


  /** 
   * <em>buy_min_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyMinPriceIsSet() {
    return buy_min_price_is_set;
  }


  /** 
   * <em>buy_min_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyMinPriceIsModified() {
    return buy_min_price_is_modified;
  }


  /** 
   * <em>sell_max_price</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getSellMaxPrice()
  {
    return ( sell_max_price==null? ((long)0): sell_max_price.longValue() );
  }


  /** 
   * <em>sell_max_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSellMaxPriceIsNull()
  {
    return sell_max_price == null;
  }


  /** 
   * <em>sell_max_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellMaxPriceIsSet() {
    return sell_max_price_is_set;
  }


  /** 
   * <em>sell_max_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellMaxPriceIsModified() {
    return sell_max_price_is_modified;
  }


  /** 
   * <em>sell_min_price</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getSellMinPrice()
  {
    return ( sell_min_price==null? ((long)0): sell_min_price.longValue() );
  }


  /** 
   * <em>sell_min_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSellMinPriceIsNull()
  {
    return sell_min_price == null;
  }


  /** 
   * <em>sell_min_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellMinPriceIsSet() {
    return sell_min_price_is_set;
  }


  /** 
   * <em>sell_min_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellMinPriceIsModified() {
    return sell_min_price_is_modified;
  }


  /** 
   * <em>buy_designate_method</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBuyDesignateMethod()
  {
    return buy_designate_method;
  }


  /** 
   * <em>buy_designate_method</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyDesignateMethodIsSet() {
    return buy_designate_method_is_set;
  }


  /** 
   * <em>buy_designate_method</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyDesignateMethodIsModified() {
    return buy_designate_method_is_modified;
  }


  /** 
   * <em>sell_designate_method</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSellDesignateMethod()
  {
    return sell_designate_method;
  }


  /** 
   * <em>sell_designate_method</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellDesignateMethodIsSet() {
    return sell_designate_method_is_set;
  }


  /** 
   * <em>sell_designate_method</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellDesignateMethodIsModified() {
    return sell_designate_method_is_modified;
  }


  /** 
   * <em>payment_method</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPaymentMethod()
  {
    return payment_method;
  }


  /** 
   * <em>payment_method</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentMethodIsSet() {
    return payment_method_is_set;
  }


  /** 
   * <em>payment_method</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentMethodIsModified() {
    return payment_method_is_modified;
  }


  /** 
   * <em>buy_start_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getBuyStartDate()
  {
    return buy_start_date;
  }


  /** 
   * <em>buy_start_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyStartDateIsSet() {
    return buy_start_date_is_set;
  }


  /** 
   * <em>buy_start_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyStartDateIsModified() {
    return buy_start_date_is_modified;
  }


  /** 
   * <em>buy_end_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getBuyEndDate()
  {
    return buy_end_date;
  }


  /** 
   * <em>buy_end_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyEndDateIsSet() {
    return buy_end_date_is_set;
  }


  /** 
   * <em>buy_end_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyEndDateIsModified() {
    return buy_end_date_is_modified;
  }


  /** 
   * <em>sell_start_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getSellStartDate()
  {
    return sell_start_date;
  }


  /** 
   * <em>sell_start_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellStartDateIsSet() {
    return sell_start_date_is_set;
  }


  /** 
   * <em>sell_start_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellStartDateIsModified() {
    return sell_start_date_is_modified;
  }


  /** 
   * <em>sell_end_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getSellEndDate()
  {
    return sell_end_date;
  }


  /** 
   * <em>sell_end_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellEndDateIsSet() {
    return sell_end_date_is_set;
  }


  /** 
   * <em>sell_end_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellEndDateIsModified() {
    return sell_end_date_is_modified;
  }


  /** 
   * <em>last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLastUpdater()
  {
    return last_updater;
  }


  /** 
   * <em>last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdaterIsSet() {
    return last_updater_is_set;
  }


  /** 
   * <em>last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdaterIsModified() {
    return last_updater_is_modified;
  }


  /** 
   * <em>online_updated_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getOnlineUpdatedTimestamp()
  {
    return online_updated_timestamp;
  }


  /** 
   * <em>online_updated_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOnlineUpdatedTimestampIsSet() {
    return online_updated_timestamp_is_set;
  }


  /** 
   * <em>online_updated_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOnlineUpdatedTimestampIsModified() {
    return online_updated_timestamp_is_modified;
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
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new RuitoProductPK(product_id);
  }


  /** 
   * <em>product_id</em>カラムの値を設定します。 
   *
   * @@param p_productId <em>product_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setProductId( long p_productId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_id = p_productId;
    product_id_is_set = true;
    product_id_is_modified = true;
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
   * <em>product_issue_code</em>カラムの値を設定します。 
   *
   * @@param p_productIssueCode <em>product_issue_code</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setProductIssueCode( String p_productIssueCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_issue_code = p_productIssueCode;
    product_issue_code_is_set = true;
    product_issue_code_is_modified = true;
  }


  /** 
   * <em>ruito_type</em>カラムの値を設定します。 
   *
   * @@param p_ruitoType <em>ruito_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum値 
   */
  public final void setRuitoType( com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum p_ruitoType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ruito_type = p_ruitoType;
    ruito_type_is_set = true;
    ruito_type_is_modified = true;
  }


  /** 
   * <em>init_purchase_min_qty</em>カラムの値を設定します。 
   *
   * @@param p_initPurchaseMinQty <em>init_purchase_min_qty</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setInitPurchaseMinQty( long p_initPurchaseMinQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    init_purchase_min_qty = p_initPurchaseMinQty;
    init_purchase_min_qty_is_set = true;
    init_purchase_min_qty_is_modified = true;
  }


  /** 
   * <em>addtl_purchase_min_qty</em>カラムの値を設定します。 
   *
   * @@param p_addtlPurchaseMinQty <em>addtl_purchase_min_qty</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setAddtlPurchaseMinQty( long p_addtlPurchaseMinQty )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    addtl_purchase_min_qty = p_addtlPurchaseMinQty;
    addtl_purchase_min_qty_is_set = true;
    addtl_purchase_min_qty_is_modified = true;
  }


  /** 
   * <em>standard_name</em>カラムの値を設定します。 
   *
   * @@param p_standardName <em>standard_name</em>カラムの値をあらわす64桁以下のString値 
   */
  public final void setStandardName( String p_standardName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    standard_name = p_standardName;
    standard_name_is_set = true;
    standard_name_is_modified = true;
  }


  /** 
   * <em>course</em>カラムの値を設定します。 
   *
   * @@param p_course <em>course</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCourse( String p_course )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    course = p_course;
    course_is_set = true;
    course_is_modified = true;
  }


  /** 
   * <em>plan</em>カラムの値を設定します。 
   *
   * @@param p_plan <em>plan</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPlan( String p_plan )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    plan = p_plan;
    plan_is_set = true;
    plan_is_modified = true;
  }


  /** 
   * <em>mrf_fund_code</em>カラムの値を設定します。 
   *
   * @@param p_mrfFundCode <em>mrf_fund_code</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMrfFundCode( String p_mrfFundCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mrf_fund_code = p_mrfFundCode;
    mrf_fund_code_is_set = true;
    mrf_fund_code_is_modified = true;
  }


  /** 
   * <em>buy_max_price</em>カラムの値を設定します。 
   *
   * @@param p_buyMaxPrice <em>buy_max_price</em>カラムの値をあらわす11桁以下のlong値 
   */
  public final void setBuyMaxPrice( long p_buyMaxPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_max_price = new Long(p_buyMaxPrice);
    buy_max_price_is_set = true;
    buy_max_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>buy_max_price</em>カラムに値を設定します。 
   */
  public final void setBuyMaxPrice( Long p_buyMaxPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    buy_max_price = p_buyMaxPrice;
    buy_max_price_is_set = true;
    buy_max_price_is_modified = true;
  }


  /** 
   * <em>buy_min_price</em>カラムの値を設定します。 
   *
   * @@param p_buyMinPrice <em>buy_min_price</em>カラムの値をあらわす11桁以下のlong値 
   */
  public final void setBuyMinPrice( long p_buyMinPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_min_price = new Long(p_buyMinPrice);
    buy_min_price_is_set = true;
    buy_min_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>buy_min_price</em>カラムに値を設定します。 
   */
  public final void setBuyMinPrice( Long p_buyMinPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    buy_min_price = p_buyMinPrice;
    buy_min_price_is_set = true;
    buy_min_price_is_modified = true;
  }


  /** 
   * <em>sell_max_price</em>カラムの値を設定します。 
   *
   * @@param p_sellMaxPrice <em>sell_max_price</em>カラムの値をあらわす11桁以下のlong値 
   */
  public final void setSellMaxPrice( long p_sellMaxPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_max_price = new Long(p_sellMaxPrice);
    sell_max_price_is_set = true;
    sell_max_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>sell_max_price</em>カラムに値を設定します。 
   */
  public final void setSellMaxPrice( Long p_sellMaxPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sell_max_price = p_sellMaxPrice;
    sell_max_price_is_set = true;
    sell_max_price_is_modified = true;
  }


  /** 
   * <em>sell_min_price</em>カラムの値を設定します。 
   *
   * @@param p_sellMinPrice <em>sell_min_price</em>カラムの値をあらわす11桁以下のlong値 
   */
  public final void setSellMinPrice( long p_sellMinPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_min_price = new Long(p_sellMinPrice);
    sell_min_price_is_set = true;
    sell_min_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>sell_min_price</em>カラムに値を設定します。 
   */
  public final void setSellMinPrice( Long p_sellMinPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sell_min_price = p_sellMinPrice;
    sell_min_price_is_set = true;
    sell_min_price_is_modified = true;
  }


  /** 
   * <em>buy_designate_method</em>カラムの値を設定します。 
   *
   * @@param p_buyDesignateMethod <em>buy_designate_method</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setBuyDesignateMethod( String p_buyDesignateMethod )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_designate_method = p_buyDesignateMethod;
    buy_designate_method_is_set = true;
    buy_designate_method_is_modified = true;
  }


  /** 
   * <em>sell_designate_method</em>カラムの値を設定します。 
   *
   * @@param p_sellDesignateMethod <em>sell_designate_method</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSellDesignateMethod( String p_sellDesignateMethod )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_designate_method = p_sellDesignateMethod;
    sell_designate_method_is_set = true;
    sell_designate_method_is_modified = true;
  }


  /** 
   * <em>payment_method</em>カラムの値を設定します。 
   *
   * @@param p_paymentMethod <em>payment_method</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPaymentMethod( String p_paymentMethod )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_method = p_paymentMethod;
    payment_method_is_set = true;
    payment_method_is_modified = true;
  }


  /** 
   * <em>buy_start_date</em>カラムの値を設定します。 
   *
   * @@param p_buyStartDate <em>buy_start_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setBuyStartDate( java.sql.Timestamp p_buyStartDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_start_date = p_buyStartDate;
    buy_start_date_is_set = true;
    buy_start_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>buy_start_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setBuyStartDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    buy_start_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    buy_start_date_is_set = true;
    buy_start_date_is_modified = true;
  }


  /** 
   * <em>buy_end_date</em>カラムの値を設定します。 
   *
   * @@param p_buyEndDate <em>buy_end_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setBuyEndDate( java.sql.Timestamp p_buyEndDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_end_date = p_buyEndDate;
    buy_end_date_is_set = true;
    buy_end_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>buy_end_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setBuyEndDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    buy_end_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    buy_end_date_is_set = true;
    buy_end_date_is_modified = true;
  }


  /** 
   * <em>sell_start_date</em>カラムの値を設定します。 
   *
   * @@param p_sellStartDate <em>sell_start_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setSellStartDate( java.sql.Timestamp p_sellStartDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_start_date = p_sellStartDate;
    sell_start_date_is_set = true;
    sell_start_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>sell_start_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setSellStartDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sell_start_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    sell_start_date_is_set = true;
    sell_start_date_is_modified = true;
  }


  /** 
   * <em>sell_end_date</em>カラムの値を設定します。 
   *
   * @@param p_sellEndDate <em>sell_end_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setSellEndDate( java.sql.Timestamp p_sellEndDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_end_date = p_sellEndDate;
    sell_end_date_is_set = true;
    sell_end_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>sell_end_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setSellEndDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sell_end_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    sell_end_date_is_set = true;
    sell_end_date_is_modified = true;
  }


  /** 
   * <em>last_updater</em>カラムの値を設定します。 
   *
   * @@param p_lastUpdater <em>last_updater</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setLastUpdater( String p_lastUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updater = p_lastUpdater;
    last_updater_is_set = true;
    last_updater_is_modified = true;
  }


  /** 
   * <em>online_updated_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_onlineUpdatedTimestamp <em>online_updated_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setOnlineUpdatedTimestamp( java.sql.Timestamp p_onlineUpdatedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    online_updated_timestamp = p_onlineUpdatedTimestamp;
    online_updated_timestamp_is_set = true;
    online_updated_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>online_updated_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setOnlineUpdatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    online_updated_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    online_updated_timestamp_is_set = true;
    online_updated_timestamp_is_modified = true;
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
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("addtl_purchase_min_qty") ) {
                    return new Long( addtl_purchase_min_qty );
                }
                break;
            case 'b':
                if ( name.equals("buy_max_price") ) {
                    return this.buy_max_price;
                }
                else if ( name.equals("buy_min_price") ) {
                    return this.buy_min_price;
                }
                else if ( name.equals("buy_designate_method") ) {
                    return this.buy_designate_method;
                }
                else if ( name.equals("buy_start_date") ) {
                    return this.buy_start_date;
                }
                else if ( name.equals("buy_end_date") ) {
                    return this.buy_end_date;
                }
                break;
            case 'c':
                if ( name.equals("course") ) {
                    return this.course;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("init_purchase_min_qty") ) {
                    return new Long( init_purchase_min_qty );
                }
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
                    return this.last_updater;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("mrf_fund_code") ) {
                    return this.mrf_fund_code;
                }
                break;
            case 'o':
                if ( name.equals("online_updated_timestamp") ) {
                    return this.online_updated_timestamp;
                }
                break;
            case 'p':
                if ( name.equals("product_id") ) {
                    return new Long( product_id );
                }
                else if ( name.equals("product_code") ) {
                    return this.product_code;
                }
                else if ( name.equals("product_issue_code") ) {
                    return this.product_issue_code;
                }
                else if ( name.equals("plan") ) {
                    return this.plan;
                }
                else if ( name.equals("payment_method") ) {
                    return this.payment_method;
                }
                break;
            case 'r':
                if ( name.equals("ruito_type") ) {
                    return this.ruito_type;
                }
                break;
            case 's':
                if ( name.equals("standard_name") ) {
                    return this.standard_name;
                }
                else if ( name.equals("sell_max_price") ) {
                    return this.sell_max_price;
                }
                else if ( name.equals("sell_min_price") ) {
                    return this.sell_min_price;
                }
                else if ( name.equals("sell_designate_method") ) {
                    return this.sell_designate_method;
                }
                else if ( name.equals("sell_start_date") ) {
                    return this.sell_start_date;
                }
                else if ( name.equals("sell_end_date") ) {
                    return this.sell_end_date;
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
                if ( name.equals("addtl_purchase_min_qty") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'addtl_purchase_min_qty' must be Long: '"+value+"' is not." );
                    this.addtl_purchase_min_qty = ((Long) value).longValue();
                    if (this.addtl_purchase_min_qty_is_set)
                        this.addtl_purchase_min_qty_is_modified = true;
                    this.addtl_purchase_min_qty_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("buy_max_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'buy_max_price' must be Long: '"+value+"' is not." );
                    this.buy_max_price = (Long) value;
                    if (this.buy_max_price_is_set)
                        this.buy_max_price_is_modified = true;
                    this.buy_max_price_is_set = true;
                    return;
                }
                else if ( name.equals("buy_min_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'buy_min_price' must be Long: '"+value+"' is not." );
                    this.buy_min_price = (Long) value;
                    if (this.buy_min_price_is_set)
                        this.buy_min_price_is_modified = true;
                    this.buy_min_price_is_set = true;
                    return;
                }
                else if ( name.equals("buy_designate_method") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'buy_designate_method' must be String: '"+value+"' is not." );
                    this.buy_designate_method = (String) value;
                    if (this.buy_designate_method_is_set)
                        this.buy_designate_method_is_modified = true;
                    this.buy_designate_method_is_set = true;
                    return;
                }
                else if ( name.equals("buy_start_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'buy_start_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.buy_start_date = (java.sql.Timestamp) value;
                    if (this.buy_start_date_is_set)
                        this.buy_start_date_is_modified = true;
                    this.buy_start_date_is_set = true;
                    return;
                }
                else if ( name.equals("buy_end_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'buy_end_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.buy_end_date = (java.sql.Timestamp) value;
                    if (this.buy_end_date_is_set)
                        this.buy_end_date_is_modified = true;
                    this.buy_end_date_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("course") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'course' must be String: '"+value+"' is not." );
                    this.course = (String) value;
                    if (this.course_is_set)
                        this.course_is_modified = true;
                    this.course_is_set = true;
                    return;
                }
                else if ( name.equals("created_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    if (this.created_timestamp_is_set)
                        this.created_timestamp_is_modified = true;
                    this.created_timestamp_is_set = true;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'institution_code' must be String: '"+value+"' is not." );
                    this.institution_code = (String) value;
                    if (this.institution_code_is_set)
                        this.institution_code_is_modified = true;
                    this.institution_code_is_set = true;
                    return;
                }
                else if ( name.equals("init_purchase_min_qty") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'init_purchase_min_qty' must be Long: '"+value+"' is not." );
                    this.init_purchase_min_qty = ((Long) value).longValue();
                    if (this.init_purchase_min_qty_is_set)
                        this.init_purchase_min_qty_is_modified = true;
                    this.init_purchase_min_qty_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'last_updater' must be String: '"+value+"' is not." );
                    this.last_updater = (String) value;
                    if (this.last_updater_is_set)
                        this.last_updater_is_modified = true;
                    this.last_updater_is_set = true;
                    return;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.last_updated_timestamp_is_set)
                        this.last_updated_timestamp_is_modified = true;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 'm':
                if ( name.equals("mrf_fund_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mrf_fund_code' must be String: '"+value+"' is not." );
                    this.mrf_fund_code = (String) value;
                    if (this.mrf_fund_code_is_set)
                        this.mrf_fund_code_is_modified = true;
                    this.mrf_fund_code_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("online_updated_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'online_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.online_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.online_updated_timestamp_is_set)
                        this.online_updated_timestamp_is_modified = true;
                    this.online_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("product_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'product_id' must be Long: '"+value+"' is not." );
                    this.product_id = ((Long) value).longValue();
                    if (this.product_id_is_set)
                        this.product_id_is_modified = true;
                    this.product_id_is_set = true;
                    return;
                }
                else if ( name.equals("product_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_code' must be String: '"+value+"' is not." );
                    this.product_code = (String) value;
                    if (this.product_code_is_set)
                        this.product_code_is_modified = true;
                    this.product_code_is_set = true;
                    return;
                }
                else if ( name.equals("product_issue_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_issue_code' must be String: '"+value+"' is not." );
                    this.product_issue_code = (String) value;
                    if (this.product_issue_code_is_set)
                        this.product_issue_code_is_modified = true;
                    this.product_issue_code_is_set = true;
                    return;
                }
                else if ( name.equals("plan") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'plan' must be String: '"+value+"' is not." );
                    this.plan = (String) value;
                    if (this.plan_is_set)
                        this.plan_is_modified = true;
                    this.plan_is_set = true;
                    return;
                }
                else if ( name.equals("payment_method") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'payment_method' must be String: '"+value+"' is not." );
                    this.payment_method = (String) value;
                    if (this.payment_method_is_set)
                        this.payment_method_is_modified = true;
                    this.payment_method_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("ruito_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum) )
                        throw new IllegalArgumentException( "value for 'ruito_type' must be com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum: '"+value+"' is not." );
                    this.ruito_type = (com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum) value;
                    if (this.ruito_type_is_set)
                        this.ruito_type_is_modified = true;
                    this.ruito_type_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("standard_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'standard_name' must be String: '"+value+"' is not." );
                    this.standard_name = (String) value;
                    if (this.standard_name_is_set)
                        this.standard_name_is_modified = true;
                    this.standard_name_is_set = true;
                    return;
                }
                else if ( name.equals("sell_max_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'sell_max_price' must be Long: '"+value+"' is not." );
                    this.sell_max_price = (Long) value;
                    if (this.sell_max_price_is_set)
                        this.sell_max_price_is_modified = true;
                    this.sell_max_price_is_set = true;
                    return;
                }
                else if ( name.equals("sell_min_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'sell_min_price' must be Long: '"+value+"' is not." );
                    this.sell_min_price = (Long) value;
                    if (this.sell_min_price_is_set)
                        this.sell_min_price_is_modified = true;
                    this.sell_min_price_is_set = true;
                    return;
                }
                else if ( name.equals("sell_designate_method") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sell_designate_method' must be String: '"+value+"' is not." );
                    this.sell_designate_method = (String) value;
                    if (this.sell_designate_method_is_set)
                        this.sell_designate_method_is_modified = true;
                    this.sell_designate_method_is_set = true;
                    return;
                }
                else if ( name.equals("sell_start_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'sell_start_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.sell_start_date = (java.sql.Timestamp) value;
                    if (this.sell_start_date_is_set)
                        this.sell_start_date_is_modified = true;
                    this.sell_start_date_is_set = true;
                    return;
                }
                else if ( name.equals("sell_end_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'sell_end_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.sell_end_date = (java.sql.Timestamp) value;
                    if (this.sell_end_date_is_set)
                        this.sell_end_date_is_modified = true;
                    this.sell_end_date_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
