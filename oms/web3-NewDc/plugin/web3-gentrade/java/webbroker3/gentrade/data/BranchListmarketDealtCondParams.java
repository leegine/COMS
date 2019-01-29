head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.42.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	BranchListmarketDealtCondParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * branch_listmarket_dealt_condテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link BranchListmarketDealtCondRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link BranchListmarketDealtCondParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link BranchListmarketDealtCondParams}が{@@link BranchListmarketDealtCondRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BranchListmarketDealtCondPK 
 * @@see BranchListmarketDealtCondRow 
 */
public class BranchListmarketDealtCondParams extends Params implements BranchListmarketDealtCondRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "branch_listmarket_dealt_cond";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = BranchListmarketDealtCondRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return BranchListmarketDealtCondRow.TYPE;
  }


  /** 
   * <em>branch_id</em>カラムの値 
   */
  public  long  branch_id;

  /** 
   * <em>market_id</em>カラムの値 
   */
  public  long  market_id;

  /** 
   * <em>list_type</em>カラムの値 
   */
  public  String  list_type;

  /** 
   * <em>new_list_type</em>カラムの値 
   */
  public  String  new_list_type;

  /** 
   * <em>open_otc_div</em>カラムの値 
   */
  public  String  open_otc_div;

  /** 
   * <em>listmarket_name</em>カラムの値 
   */
  public  String  listmarket_name;

  /** 
   * <em>margin_sec_check_rate</em>カラムの値 
   */
  public  Double  margin_sec_check_rate;

  /** 
   * <em>max_cont_price_ind</em>カラムの値 
   */
  public  Double  max_cont_price_ind;

  /** 
   * <em>max_cont_price_corp</em>カラムの値 
   */
  public  Double  max_cont_price_corp;

  /** 
   * <em>max_cont_unit_ind</em>カラムの値 
   */
  public  Double  max_cont_unit_ind;

  /** 
   * <em>max_cont_unit_corp</em>カラムの値 
   */
  public  Double  max_cont_unit_corp;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean branch_id_is_set = false;
  private boolean branch_id_is_modified = false;
  private boolean market_id_is_set = false;
  private boolean market_id_is_modified = false;
  private boolean list_type_is_set = false;
  private boolean list_type_is_modified = false;
  private boolean new_list_type_is_set = false;
  private boolean new_list_type_is_modified = false;
  private boolean open_otc_div_is_set = false;
  private boolean open_otc_div_is_modified = false;
  private boolean listmarket_name_is_set = false;
  private boolean listmarket_name_is_modified = false;
  private boolean margin_sec_check_rate_is_set = false;
  private boolean margin_sec_check_rate_is_modified = false;
  private boolean max_cont_price_ind_is_set = false;
  private boolean max_cont_price_ind_is_modified = false;
  private boolean max_cont_price_corp_is_set = false;
  private boolean max_cont_price_corp_is_modified = false;
  private boolean max_cont_unit_ind_is_set = false;
  private boolean max_cont_unit_ind_is_modified = false;
  private boolean max_cont_unit_corp_is_set = false;
  private boolean max_cont_unit_corp_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "branch_id=" + branch_id
      + "," + "market_id=" + market_id
      + "," + "list_type=" + list_type
      + "," + "new_list_type=" + new_list_type
      + "," + "open_otc_div=" + open_otc_div
      + "," + "listmarket_name=" +listmarket_name
      + "," + "margin_sec_check_rate=" +margin_sec_check_rate
      + "," + "max_cont_price_ind=" +max_cont_price_ind
      + "," + "max_cont_price_corp=" +max_cont_price_corp
      + "," + "max_cont_unit_ind=" +max_cont_unit_ind
      + "," + "max_cont_unit_corp=" +max_cont_unit_corp
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のBranchListmarketDealtCondParamsオブジェクトを作成します。 
   */
  public BranchListmarketDealtCondParams() {
    branch_id_is_modified = true;
    market_id_is_modified = true;
    list_type_is_modified = true;
    new_list_type_is_modified = true;
    open_otc_div_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のBranchListmarketDealtCondRowオブジェクトの値を利用してBranchListmarketDealtCondParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するBranchListmarketDealtCondRowオブジェクト 
   */
  public BranchListmarketDealtCondParams( BranchListmarketDealtCondRow p_row )
  {
    branch_id = p_row.getBranchId();
    branch_id_is_set = p_row.getBranchIdIsSet();
    branch_id_is_modified = p_row.getBranchIdIsModified();
    market_id = p_row.getMarketId();
    market_id_is_set = p_row.getMarketIdIsSet();
    market_id_is_modified = p_row.getMarketIdIsModified();
    list_type = p_row.getListType();
    list_type_is_set = p_row.getListTypeIsSet();
    list_type_is_modified = p_row.getListTypeIsModified();
    new_list_type = p_row.getNewListType();
    new_list_type_is_set = p_row.getNewListTypeIsSet();
    new_list_type_is_modified = p_row.getNewListTypeIsModified();
    open_otc_div = p_row.getOpenOtcDiv();
    open_otc_div_is_set = p_row.getOpenOtcDivIsSet();
    open_otc_div_is_modified = p_row.getOpenOtcDivIsModified();
    listmarket_name = p_row.getListmarketName();
    listmarket_name_is_set = p_row.getListmarketNameIsSet();
    listmarket_name_is_modified = p_row.getListmarketNameIsModified();
    if ( !p_row.getMarginSecCheckRateIsNull() )
      margin_sec_check_rate = new Double( p_row.getMarginSecCheckRate() );
    margin_sec_check_rate_is_set = p_row.getMarginSecCheckRateIsSet();
    margin_sec_check_rate_is_modified = p_row.getMarginSecCheckRateIsModified();
    if ( !p_row.getMaxContPriceIndIsNull() )
      max_cont_price_ind = new Double( p_row.getMaxContPriceInd() );
    max_cont_price_ind_is_set = p_row.getMaxContPriceIndIsSet();
    max_cont_price_ind_is_modified = p_row.getMaxContPriceIndIsModified();
    if ( !p_row.getMaxContPriceCorpIsNull() )
      max_cont_price_corp = new Double( p_row.getMaxContPriceCorp() );
    max_cont_price_corp_is_set = p_row.getMaxContPriceCorpIsSet();
    max_cont_price_corp_is_modified = p_row.getMaxContPriceCorpIsModified();
    if ( !p_row.getMaxContUnitIndIsNull() )
      max_cont_unit_ind = new Double( p_row.getMaxContUnitInd() );
    max_cont_unit_ind_is_set = p_row.getMaxContUnitIndIsSet();
    max_cont_unit_ind_is_modified = p_row.getMaxContUnitIndIsModified();
    if ( !p_row.getMaxContUnitCorpIsNull() )
      max_cont_unit_corp = new Double( p_row.getMaxContUnitCorp() );
    max_cont_unit_corp_is_set = p_row.getMaxContUnitCorpIsSet();
    max_cont_unit_corp_is_modified = p_row.getMaxContUnitCorpIsModified();
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
      listmarket_name_is_set = true;
      listmarket_name_is_modified = true;
      margin_sec_check_rate_is_set = true;
      margin_sec_check_rate_is_modified = true;
      max_cont_price_ind_is_set = true;
      max_cont_price_ind_is_modified = true;
      max_cont_price_corp_is_set = true;
      max_cont_price_corp_is_modified = true;
      max_cont_unit_ind_is_set = true;
      max_cont_unit_ind_is_modified = true;
      max_cont_unit_corp_is_set = true;
      max_cont_unit_corp_is_modified = true;
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
    if ( !( other instanceof BranchListmarketDealtCondRow ) )
       return false;
    return fieldsEqual( (BranchListmarketDealtCondRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のBranchListmarketDealtCondRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( BranchListmarketDealtCondRow row )
  {
    if ( branch_id != row.getBranchId() )
      return false;
    if ( market_id != row.getMarketId() )
      return false;
    if ( list_type == null ) {
      if ( row.getListType() != null )
        return false;
    } else if ( !list_type.equals( row.getListType() ) ) {
        return false;
    }
    if ( new_list_type == null ) {
      if ( row.getNewListType() != null )
        return false;
    } else if ( !new_list_type.equals( row.getNewListType() ) ) {
        return false;
    }
    if ( open_otc_div == null ) {
      if ( row.getOpenOtcDiv() != null )
        return false;
    } else if ( !open_otc_div.equals( row.getOpenOtcDiv() ) ) {
        return false;
    }
    if ( listmarket_name == null ) {
      if ( row.getListmarketName() != null )
        return false;
    } else if ( !listmarket_name.equals( row.getListmarketName() ) ) {
        return false;
    }
    if ( margin_sec_check_rate == null ) {
      if ( !row.getMarginSecCheckRateIsNull() )
        return false;
    } else if ( row.getMarginSecCheckRateIsNull() || ( margin_sec_check_rate.doubleValue() != row.getMarginSecCheckRate() ) ) {
        return false;
    }
    if ( max_cont_price_ind == null ) {
      if ( !row.getMaxContPriceIndIsNull() )
        return false;
    } else if ( row.getMaxContPriceIndIsNull() || ( max_cont_price_ind.doubleValue() != row.getMaxContPriceInd() ) ) {
        return false;
    }
    if ( max_cont_price_corp == null ) {
      if ( !row.getMaxContPriceCorpIsNull() )
        return false;
    } else if ( row.getMaxContPriceCorpIsNull() || ( max_cont_price_corp.doubleValue() != row.getMaxContPriceCorp() ) ) {
        return false;
    }
    if ( max_cont_unit_ind == null ) {
      if ( !row.getMaxContUnitIndIsNull() )
        return false;
    } else if ( row.getMaxContUnitIndIsNull() || ( max_cont_unit_ind.doubleValue() != row.getMaxContUnitInd() ) ) {
        return false;
    }
    if ( max_cont_unit_corp == null ) {
      if ( !row.getMaxContUnitCorpIsNull() )
        return false;
    } else if ( row.getMaxContUnitCorpIsNull() || ( max_cont_unit_corp.doubleValue() != row.getMaxContUnitCorp() ) ) {
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
      return  ((int) branch_id)
        + ((int) market_id)
        + (list_type!=null? list_type.hashCode(): 0) 
        + (new_list_type!=null? new_list_type.hashCode(): 0) 
        + (open_otc_div!=null? open_otc_div.hashCode(): 0) 
        + (listmarket_name!=null? listmarket_name.hashCode(): 0) 
        + (margin_sec_check_rate!=null? margin_sec_check_rate.hashCode(): 0) 
        + (max_cont_price_ind!=null? max_cont_price_ind.hashCode(): 0) 
        + (max_cont_price_corp!=null? max_cont_price_corp.hashCode(): 0) 
        + (max_cont_unit_ind!=null? max_cont_unit_ind.hashCode(): 0) 
        + (max_cont_unit_corp!=null? max_cont_unit_corp.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !listmarket_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'listmarket_name' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("branch_id",new Long(branch_id));
		map.put("market_id",new Long(market_id));
		map.put("list_type",list_type);
		map.put("new_list_type",new_list_type);
		map.put("open_otc_div",open_otc_div);
		map.put("listmarket_name",listmarket_name);
		if ( margin_sec_check_rate != null )
			map.put("margin_sec_check_rate",margin_sec_check_rate);
		if ( max_cont_price_ind != null )
			map.put("max_cont_price_ind",max_cont_price_ind);
		if ( max_cont_price_corp != null )
			map.put("max_cont_price_corp",max_cont_price_corp);
		if ( max_cont_unit_ind != null )
			map.put("max_cont_unit_ind",max_cont_unit_ind);
		if ( max_cont_unit_corp != null )
			map.put("max_cont_unit_corp",max_cont_unit_corp);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( listmarket_name_is_modified )
			map.put("listmarket_name",listmarket_name);
		if ( margin_sec_check_rate_is_modified )
			map.put("margin_sec_check_rate",margin_sec_check_rate);
		if ( max_cont_price_ind_is_modified )
			map.put("max_cont_price_ind",max_cont_price_ind);
		if ( max_cont_price_corp_is_modified )
			map.put("max_cont_price_corp",max_cont_price_corp);
		if ( max_cont_unit_ind_is_modified )
			map.put("max_cont_unit_ind",max_cont_unit_ind);
		if ( max_cont_unit_corp_is_modified )
			map.put("max_cont_unit_corp",max_cont_unit_corp);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( listmarket_name_is_set )
				map.put("listmarket_name",listmarket_name);
			map.put("margin_sec_check_rate",margin_sec_check_rate);
			map.put("max_cont_price_ind",max_cont_price_ind);
			map.put("max_cont_price_corp",max_cont_price_corp);
			map.put("max_cont_unit_ind",max_cont_unit_ind);
			map.put("max_cont_unit_corp",max_cont_unit_corp);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>branch_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getBranchId()
  {
    return branch_id;
  }


  /** 
   * <em>branch_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchIdIsSet() {
    return branch_id_is_set;
  }


  /** 
   * <em>branch_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchIdIsModified() {
    return branch_id_is_modified;
  }


  /** 
   * <em>market_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMarketId()
  {
    return market_id;
  }


  /** 
   * <em>market_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketIdIsSet() {
    return market_id_is_set;
  }


  /** 
   * <em>market_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketIdIsModified() {
    return market_id_is_modified;
  }


  /** 
   * <em>list_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getListType()
  {
    return list_type;
  }


  /** 
   * <em>list_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getListTypeIsSet() {
    return list_type_is_set;
  }


  /** 
   * <em>list_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getListTypeIsModified() {
    return list_type_is_modified;
  }


  /** 
   * <em>new_list_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getNewListType()
  {
    return new_list_type;
  }


  /** 
   * <em>new_list_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewListTypeIsSet() {
    return new_list_type_is_set;
  }


  /** 
   * <em>new_list_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewListTypeIsModified() {
    return new_list_type_is_modified;
  }


  /** 
   * <em>open_otc_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOpenOtcDiv()
  {
    return open_otc_div;
  }


  /** 
   * <em>open_otc_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOpenOtcDivIsSet() {
    return open_otc_div_is_set;
  }


  /** 
   * <em>open_otc_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOpenOtcDivIsModified() {
    return open_otc_div_is_modified;
  }


  /** 
   * <em>listmarket_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getListmarketName()
  {
    return listmarket_name;
  }


  /** 
   * <em>listmarket_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getListmarketNameIsSet() {
    return listmarket_name_is_set;
  }


  /** 
   * <em>listmarket_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getListmarketNameIsModified() {
    return listmarket_name_is_modified;
  }


  /** 
   * <em>margin_sec_check_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMarginSecCheckRate()
  {
    return ( margin_sec_check_rate==null? ((double)0): margin_sec_check_rate.doubleValue() );
  }


  /** 
   * <em>margin_sec_check_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarginSecCheckRateIsNull()
  {
    return margin_sec_check_rate == null;
  }


  /** 
   * <em>margin_sec_check_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginSecCheckRateIsSet() {
    return margin_sec_check_rate_is_set;
  }


  /** 
   * <em>margin_sec_check_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginSecCheckRateIsModified() {
    return margin_sec_check_rate_is_modified;
  }


  /** 
   * <em>max_cont_price_ind</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMaxContPriceInd()
  {
    return ( max_cont_price_ind==null? ((double)0): max_cont_price_ind.doubleValue() );
  }


  /** 
   * <em>max_cont_price_ind</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMaxContPriceIndIsNull()
  {
    return max_cont_price_ind == null;
  }


  /** 
   * <em>max_cont_price_ind</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxContPriceIndIsSet() {
    return max_cont_price_ind_is_set;
  }


  /** 
   * <em>max_cont_price_ind</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxContPriceIndIsModified() {
    return max_cont_price_ind_is_modified;
  }


  /** 
   * <em>max_cont_price_corp</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMaxContPriceCorp()
  {
    return ( max_cont_price_corp==null? ((double)0): max_cont_price_corp.doubleValue() );
  }


  /** 
   * <em>max_cont_price_corp</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMaxContPriceCorpIsNull()
  {
    return max_cont_price_corp == null;
  }


  /** 
   * <em>max_cont_price_corp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxContPriceCorpIsSet() {
    return max_cont_price_corp_is_set;
  }


  /** 
   * <em>max_cont_price_corp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxContPriceCorpIsModified() {
    return max_cont_price_corp_is_modified;
  }


  /** 
   * <em>max_cont_unit_ind</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMaxContUnitInd()
  {
    return ( max_cont_unit_ind==null? ((double)0): max_cont_unit_ind.doubleValue() );
  }


  /** 
   * <em>max_cont_unit_ind</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMaxContUnitIndIsNull()
  {
    return max_cont_unit_ind == null;
  }


  /** 
   * <em>max_cont_unit_ind</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxContUnitIndIsSet() {
    return max_cont_unit_ind_is_set;
  }


  /** 
   * <em>max_cont_unit_ind</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxContUnitIndIsModified() {
    return max_cont_unit_ind_is_modified;
  }


  /** 
   * <em>max_cont_unit_corp</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMaxContUnitCorp()
  {
    return ( max_cont_unit_corp==null? ((double)0): max_cont_unit_corp.doubleValue() );
  }


  /** 
   * <em>max_cont_unit_corp</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMaxContUnitCorpIsNull()
  {
    return max_cont_unit_corp == null;
  }


  /** 
   * <em>max_cont_unit_corp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxContUnitCorpIsSet() {
    return max_cont_unit_corp_is_set;
  }


  /** 
   * <em>max_cont_unit_corp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxContUnitCorpIsModified() {
    return max_cont_unit_corp_is_modified;
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
    return new BranchListmarketDealtCondPK(branch_id, market_id, list_type, new_list_type, open_otc_div);
  }


  /** 
   * <em>branch_id</em>カラムの値を設定します。 
   *
   * @@param p_branchId <em>branch_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setBranchId( long p_branchId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_id = p_branchId;
    branch_id_is_set = true;
    branch_id_is_modified = true;
  }


  /** 
   * <em>market_id</em>カラムの値を設定します。 
   *
   * @@param p_marketId <em>market_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setMarketId( long p_marketId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_id = p_marketId;
    market_id_is_set = true;
    market_id_is_modified = true;
  }


  /** 
   * <em>list_type</em>カラムの値を設定します。 
   *
   * @@param p_listType <em>list_type</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setListType( String p_listType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    list_type = p_listType;
    list_type_is_set = true;
    list_type_is_modified = true;
  }


  /** 
   * <em>new_list_type</em>カラムの値を設定します。 
   *
   * @@param p_newListType <em>new_list_type</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setNewListType( String p_newListType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    new_list_type = p_newListType;
    new_list_type_is_set = true;
    new_list_type_is_modified = true;
  }


  /** 
   * <em>open_otc_div</em>カラムの値を設定します。 
   *
   * @@param p_openOtcDiv <em>open_otc_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOpenOtcDiv( String p_openOtcDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    open_otc_div = p_openOtcDiv;
    open_otc_div_is_set = true;
    open_otc_div_is_modified = true;
  }


  /** 
   * <em>listmarket_name</em>カラムの値を設定します。 
   *
   * @@param p_listmarketName <em>listmarket_name</em>カラムの値をあらわす80桁以下のString値 
   */
  public final void setListmarketName( String p_listmarketName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    listmarket_name = p_listmarketName;
    listmarket_name_is_set = true;
    listmarket_name_is_modified = true;
  }


  /** 
   * <em>margin_sec_check_rate</em>カラムの値を設定します。 
   *
   * @@param p_marginSecCheckRate <em>margin_sec_check_rate</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMarginSecCheckRate( double p_marginSecCheckRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_sec_check_rate = new Double(p_marginSecCheckRate);
    margin_sec_check_rate_is_set = true;
    margin_sec_check_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>margin_sec_check_rate</em>カラムに値を設定します。 
   */
  public final void setMarginSecCheckRate( Double p_marginSecCheckRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_sec_check_rate = p_marginSecCheckRate;
    margin_sec_check_rate_is_set = true;
    margin_sec_check_rate_is_modified = true;
  }


  /** 
   * <em>max_cont_price_ind</em>カラムの値を設定します。 
   *
   * @@param p_maxContPriceInd <em>max_cont_price_ind</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMaxContPriceInd( double p_maxContPriceInd )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    max_cont_price_ind = new Double(p_maxContPriceInd);
    max_cont_price_ind_is_set = true;
    max_cont_price_ind_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>max_cont_price_ind</em>カラムに値を設定します。 
   */
  public final void setMaxContPriceInd( Double p_maxContPriceInd )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    max_cont_price_ind = p_maxContPriceInd;
    max_cont_price_ind_is_set = true;
    max_cont_price_ind_is_modified = true;
  }


  /** 
   * <em>max_cont_price_corp</em>カラムの値を設定します。 
   *
   * @@param p_maxContPriceCorp <em>max_cont_price_corp</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMaxContPriceCorp( double p_maxContPriceCorp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    max_cont_price_corp = new Double(p_maxContPriceCorp);
    max_cont_price_corp_is_set = true;
    max_cont_price_corp_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>max_cont_price_corp</em>カラムに値を設定します。 
   */
  public final void setMaxContPriceCorp( Double p_maxContPriceCorp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    max_cont_price_corp = p_maxContPriceCorp;
    max_cont_price_corp_is_set = true;
    max_cont_price_corp_is_modified = true;
  }


  /** 
   * <em>max_cont_unit_ind</em>カラムの値を設定します。 
   *
   * @@param p_maxContUnitInd <em>max_cont_unit_ind</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMaxContUnitInd( double p_maxContUnitInd )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    max_cont_unit_ind = new Double(p_maxContUnitInd);
    max_cont_unit_ind_is_set = true;
    max_cont_unit_ind_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>max_cont_unit_ind</em>カラムに値を設定します。 
   */
  public final void setMaxContUnitInd( Double p_maxContUnitInd )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    max_cont_unit_ind = p_maxContUnitInd;
    max_cont_unit_ind_is_set = true;
    max_cont_unit_ind_is_modified = true;
  }


  /** 
   * <em>max_cont_unit_corp</em>カラムの値を設定します。 
   *
   * @@param p_maxContUnitCorp <em>max_cont_unit_corp</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMaxContUnitCorp( double p_maxContUnitCorp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    max_cont_unit_corp = new Double(p_maxContUnitCorp);
    max_cont_unit_corp_is_set = true;
    max_cont_unit_corp_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>max_cont_unit_corp</em>カラムに値を設定します。 
   */
  public final void setMaxContUnitCorp( Double p_maxContUnitCorp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    max_cont_unit_corp = p_maxContUnitCorp;
    max_cont_unit_corp_is_set = true;
    max_cont_unit_corp_is_modified = true;
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
            case 'b':
                if ( name.equals("branch_id") ) {
                    return new Long( branch_id );
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'l':
                if ( name.equals("list_type") ) {
                    return this.list_type;
                }
                else if ( name.equals("listmarket_name") ) {
                    return this.listmarket_name;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("market_id") ) {
                    return new Long( market_id );
                }
                else if ( name.equals("margin_sec_check_rate") ) {
                    return this.margin_sec_check_rate;
                }
                else if ( name.equals("max_cont_price_ind") ) {
                    return this.max_cont_price_ind;
                }
                else if ( name.equals("max_cont_price_corp") ) {
                    return this.max_cont_price_corp;
                }
                else if ( name.equals("max_cont_unit_ind") ) {
                    return this.max_cont_unit_ind;
                }
                else if ( name.equals("max_cont_unit_corp") ) {
                    return this.max_cont_unit_corp;
                }
                break;
            case 'n':
                if ( name.equals("new_list_type") ) {
                    return this.new_list_type;
                }
                break;
            case 'o':
                if ( name.equals("open_otc_div") ) {
                    return this.open_otc_div;
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
            case 'b':
                if ( name.equals("branch_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'branch_id' must be Long: '"+value+"' is not." );
                    this.branch_id = ((Long) value).longValue();
                    if (this.branch_id_is_set)
                        this.branch_id_is_modified = true;
                    this.branch_id_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    if (this.created_timestamp_is_set)
                        this.created_timestamp_is_modified = true;
                    this.created_timestamp_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("list_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'list_type' must be String: '"+value+"' is not." );
                    this.list_type = (String) value;
                    if (this.list_type_is_set)
                        this.list_type_is_modified = true;
                    this.list_type_is_set = true;
                    return;
                }
                else if ( name.equals("listmarket_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'listmarket_name' must be String: '"+value+"' is not." );
                    this.listmarket_name = (String) value;
                    if (this.listmarket_name_is_set)
                        this.listmarket_name_is_modified = true;
                    this.listmarket_name_is_set = true;
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
                if ( name.equals("market_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'market_id' must be Long: '"+value+"' is not." );
                    this.market_id = ((Long) value).longValue();
                    if (this.market_id_is_set)
                        this.market_id_is_modified = true;
                    this.market_id_is_set = true;
                    return;
                }
                else if ( name.equals("margin_sec_check_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_sec_check_rate' must be Double: '"+value+"' is not." );
                    this.margin_sec_check_rate = (Double) value;
                    if (this.margin_sec_check_rate_is_set)
                        this.margin_sec_check_rate_is_modified = true;
                    this.margin_sec_check_rate_is_set = true;
                    return;
                }
                else if ( name.equals("max_cont_price_ind") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'max_cont_price_ind' must be Double: '"+value+"' is not." );
                    this.max_cont_price_ind = (Double) value;
                    if (this.max_cont_price_ind_is_set)
                        this.max_cont_price_ind_is_modified = true;
                    this.max_cont_price_ind_is_set = true;
                    return;
                }
                else if ( name.equals("max_cont_price_corp") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'max_cont_price_corp' must be Double: '"+value+"' is not." );
                    this.max_cont_price_corp = (Double) value;
                    if (this.max_cont_price_corp_is_set)
                        this.max_cont_price_corp_is_modified = true;
                    this.max_cont_price_corp_is_set = true;
                    return;
                }
                else if ( name.equals("max_cont_unit_ind") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'max_cont_unit_ind' must be Double: '"+value+"' is not." );
                    this.max_cont_unit_ind = (Double) value;
                    if (this.max_cont_unit_ind_is_set)
                        this.max_cont_unit_ind_is_modified = true;
                    this.max_cont_unit_ind_is_set = true;
                    return;
                }
                else if ( name.equals("max_cont_unit_corp") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'max_cont_unit_corp' must be Double: '"+value+"' is not." );
                    this.max_cont_unit_corp = (Double) value;
                    if (this.max_cont_unit_corp_is_set)
                        this.max_cont_unit_corp_is_modified = true;
                    this.max_cont_unit_corp_is_set = true;
                    return;
                }
                break;
            case 'n':
                if ( name.equals("new_list_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'new_list_type' must be String: '"+value+"' is not." );
                    this.new_list_type = (String) value;
                    if (this.new_list_type_is_set)
                        this.new_list_type_is_modified = true;
                    this.new_list_type_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("open_otc_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'open_otc_div' must be String: '"+value+"' is not." );
                    this.open_otc_div = (String) value;
                    if (this.open_otc_div_is_set)
                        this.open_otc_div_is_modified = true;
                    this.open_otc_div_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
