head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.28.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	FrgnMmfExchangeRateParams.java;


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
 * frgn_mmf_exchange_rateテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link FrgnMmfExchangeRateRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link FrgnMmfExchangeRateParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link FrgnMmfExchangeRateParams}が{@@link FrgnMmfExchangeRateRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see FrgnMmfExchangeRatePK 
 * @@see FrgnMmfExchangeRateRow 
 */
public class FrgnMmfExchangeRateParams extends Params implements FrgnMmfExchangeRateRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "frgn_mmf_exchange_rate";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = FrgnMmfExchangeRateRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return FrgnMmfExchangeRateRow.TYPE;
  }


  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>currency_code</em>カラムの値 
   */
  public  String  currency_code;

  /** 
   * <em>exec_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  exec_timestamp;

  /** 
   * <em>tt_selling_rate</em>カラムの値 
   */
  public  Double  tt_selling_rate;

  /** 
   * <em>tt_buying_rate</em>カラムの値 
   */
  public  Double  tt_buying_rate;

  /** 
   * <em>sub_currency_ratio</em>カラムの値 
   */
  public  Integer  sub_currency_ratio;

  /** 
   * <em>restrict_rate</em>カラムの値 
   */
  public  Double  restrict_rate;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean currency_code_is_set = false;
  private boolean currency_code_is_modified = false;
  private boolean exec_timestamp_is_set = false;
  private boolean exec_timestamp_is_modified = false;
  private boolean tt_selling_rate_is_set = false;
  private boolean tt_selling_rate_is_modified = false;
  private boolean tt_buying_rate_is_set = false;
  private boolean tt_buying_rate_is_modified = false;
  private boolean sub_currency_ratio_is_set = false;
  private boolean sub_currency_ratio_is_modified = false;
  private boolean restrict_rate_is_set = false;
  private boolean restrict_rate_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "currency_code=" + currency_code
      + "," + "exec_timestamp=" +exec_timestamp
      + "," + "tt_selling_rate=" +tt_selling_rate
      + "," + "tt_buying_rate=" +tt_buying_rate
      + "," + "sub_currency_ratio=" +sub_currency_ratio
      + "," + "restrict_rate=" +restrict_rate
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のFrgnMmfExchangeRateParamsオブジェクトを作成します。 
   */
  public FrgnMmfExchangeRateParams() {
    institution_code_is_modified = true;
    currency_code_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のFrgnMmfExchangeRateRowオブジェクトの値を利用してFrgnMmfExchangeRateParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するFrgnMmfExchangeRateRowオブジェクト 
   */
  public FrgnMmfExchangeRateParams( FrgnMmfExchangeRateRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    currency_code = p_row.getCurrencyCode();
    currency_code_is_set = p_row.getCurrencyCodeIsSet();
    currency_code_is_modified = p_row.getCurrencyCodeIsModified();
    exec_timestamp = p_row.getExecTimestamp();
    exec_timestamp_is_set = p_row.getExecTimestampIsSet();
    exec_timestamp_is_modified = p_row.getExecTimestampIsModified();
    if ( !p_row.getTtSellingRateIsNull() )
      tt_selling_rate = new Double( p_row.getTtSellingRate() );
    tt_selling_rate_is_set = p_row.getTtSellingRateIsSet();
    tt_selling_rate_is_modified = p_row.getTtSellingRateIsModified();
    if ( !p_row.getTtBuyingRateIsNull() )
      tt_buying_rate = new Double( p_row.getTtBuyingRate() );
    tt_buying_rate_is_set = p_row.getTtBuyingRateIsSet();
    tt_buying_rate_is_modified = p_row.getTtBuyingRateIsModified();
    if ( !p_row.getSubCurrencyRatioIsNull() )
      sub_currency_ratio = new Integer( p_row.getSubCurrencyRatio() );
    sub_currency_ratio_is_set = p_row.getSubCurrencyRatioIsSet();
    sub_currency_ratio_is_modified = p_row.getSubCurrencyRatioIsModified();
    if ( !p_row.getRestrictRateIsNull() )
      restrict_rate = new Double( p_row.getRestrictRate() );
    restrict_rate_is_set = p_row.getRestrictRateIsSet();
    restrict_rate_is_modified = p_row.getRestrictRateIsModified();
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
      exec_timestamp_is_set = true;
      exec_timestamp_is_modified = true;
      tt_selling_rate_is_set = true;
      tt_selling_rate_is_modified = true;
      tt_buying_rate_is_set = true;
      tt_buying_rate_is_modified = true;
      sub_currency_ratio_is_set = true;
      sub_currency_ratio_is_modified = true;
      restrict_rate_is_set = true;
      restrict_rate_is_modified = true;
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
    if ( !( other instanceof FrgnMmfExchangeRateRow ) )
       return false;
    return fieldsEqual( (FrgnMmfExchangeRateRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のFrgnMmfExchangeRateRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( FrgnMmfExchangeRateRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( currency_code == null ) {
      if ( row.getCurrencyCode() != null )
        return false;
    } else if ( !currency_code.equals( row.getCurrencyCode() ) ) {
        return false;
    }
    if ( exec_timestamp == null ) {
      if ( row.getExecTimestamp() != null )
        return false;
    } else if ( !exec_timestamp.equals( row.getExecTimestamp() ) ) {
        return false;
    }
    if ( tt_selling_rate == null ) {
      if ( !row.getTtSellingRateIsNull() )
        return false;
    } else if ( row.getTtSellingRateIsNull() || ( tt_selling_rate.doubleValue() != row.getTtSellingRate() ) ) {
        return false;
    }
    if ( tt_buying_rate == null ) {
      if ( !row.getTtBuyingRateIsNull() )
        return false;
    } else if ( row.getTtBuyingRateIsNull() || ( tt_buying_rate.doubleValue() != row.getTtBuyingRate() ) ) {
        return false;
    }
    if ( sub_currency_ratio == null ) {
      if ( !row.getSubCurrencyRatioIsNull() )
        return false;
    } else if ( row.getSubCurrencyRatioIsNull() || ( sub_currency_ratio.intValue() != row.getSubCurrencyRatio() ) ) {
        return false;
    }
    if ( restrict_rate == null ) {
      if ( !row.getRestrictRateIsNull() )
        return false;
    } else if ( row.getRestrictRateIsNull() || ( restrict_rate.doubleValue() != row.getRestrictRate() ) ) {
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
      return  (institution_code!=null? institution_code.hashCode(): 0) 
        + (currency_code!=null? currency_code.hashCode(): 0) 
        + (exec_timestamp!=null? exec_timestamp.hashCode(): 0) 
        + (tt_selling_rate!=null? tt_selling_rate.hashCode(): 0) 
        + (tt_buying_rate!=null? tt_buying_rate.hashCode(): 0) 
        + (sub_currency_ratio!=null? sub_currency_ratio.hashCode(): 0) 
        + (restrict_rate!=null? restrict_rate.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
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
		map.put("institution_code",institution_code);
		map.put("currency_code",currency_code);
		if ( exec_timestamp != null )
			map.put("exec_timestamp",exec_timestamp);
		if ( tt_selling_rate != null )
			map.put("tt_selling_rate",tt_selling_rate);
		if ( tt_buying_rate != null )
			map.put("tt_buying_rate",tt_buying_rate);
		if ( sub_currency_ratio != null )
			map.put("sub_currency_ratio",sub_currency_ratio);
		if ( restrict_rate != null )
			map.put("restrict_rate",restrict_rate);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( exec_timestamp_is_modified )
			map.put("exec_timestamp",exec_timestamp);
		if ( tt_selling_rate_is_modified )
			map.put("tt_selling_rate",tt_selling_rate);
		if ( tt_buying_rate_is_modified )
			map.put("tt_buying_rate",tt_buying_rate);
		if ( sub_currency_ratio_is_modified )
			map.put("sub_currency_ratio",sub_currency_ratio);
		if ( restrict_rate_is_modified )
			map.put("restrict_rate",restrict_rate);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("exec_timestamp",exec_timestamp);
			map.put("tt_selling_rate",tt_selling_rate);
			map.put("tt_buying_rate",tt_buying_rate);
			map.put("sub_currency_ratio",sub_currency_ratio);
			map.put("restrict_rate",restrict_rate);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
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
   * <em>currency_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCurrencyCode()
  {
    return currency_code;
  }


  /** 
   * <em>currency_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCurrencyCodeIsSet() {
    return currency_code_is_set;
  }


  /** 
   * <em>currency_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCurrencyCodeIsModified() {
    return currency_code_is_modified;
  }


  /** 
   * <em>exec_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getExecTimestamp()
  {
    return exec_timestamp;
  }


  /** 
   * <em>exec_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecTimestampIsSet() {
    return exec_timestamp_is_set;
  }


  /** 
   * <em>exec_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecTimestampIsModified() {
    return exec_timestamp_is_modified;
  }


  /** 
   * <em>tt_selling_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTtSellingRate()
  {
    return ( tt_selling_rate==null? ((double)0): tt_selling_rate.doubleValue() );
  }


  /** 
   * <em>tt_selling_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTtSellingRateIsNull()
  {
    return tt_selling_rate == null;
  }


  /** 
   * <em>tt_selling_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTtSellingRateIsSet() {
    return tt_selling_rate_is_set;
  }


  /** 
   * <em>tt_selling_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTtSellingRateIsModified() {
    return tt_selling_rate_is_modified;
  }


  /** 
   * <em>tt_buying_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTtBuyingRate()
  {
    return ( tt_buying_rate==null? ((double)0): tt_buying_rate.doubleValue() );
  }


  /** 
   * <em>tt_buying_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTtBuyingRateIsNull()
  {
    return tt_buying_rate == null;
  }


  /** 
   * <em>tt_buying_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTtBuyingRateIsSet() {
    return tt_buying_rate_is_set;
  }


  /** 
   * <em>tt_buying_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTtBuyingRateIsModified() {
    return tt_buying_rate_is_modified;
  }


  /** 
   * <em>sub_currency_ratio</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getSubCurrencyRatio()
  {
    return ( sub_currency_ratio==null? ((int)0): sub_currency_ratio.intValue() );
  }


  /** 
   * <em>sub_currency_ratio</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSubCurrencyRatioIsNull()
  {
    return sub_currency_ratio == null;
  }


  /** 
   * <em>sub_currency_ratio</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubCurrencyRatioIsSet() {
    return sub_currency_ratio_is_set;
  }


  /** 
   * <em>sub_currency_ratio</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubCurrencyRatioIsModified() {
    return sub_currency_ratio_is_modified;
  }


  /** 
   * <em>restrict_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getRestrictRate()
  {
    return ( restrict_rate==null? ((double)0): restrict_rate.doubleValue() );
  }


  /** 
   * <em>restrict_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getRestrictRateIsNull()
  {
    return restrict_rate == null;
  }


  /** 
   * <em>restrict_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRestrictRateIsSet() {
    return restrict_rate_is_set;
  }


  /** 
   * <em>restrict_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRestrictRateIsModified() {
    return restrict_rate_is_modified;
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
    return new FrgnMmfExchangeRatePK(institution_code, currency_code);
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
   * <em>currency_code</em>カラムの値を設定します。 
   *
   * @@param p_currencyCode <em>currency_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setCurrencyCode( String p_currencyCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    currency_code = p_currencyCode;
    currency_code_is_set = true;
    currency_code_is_modified = true;
  }


  /** 
   * <em>exec_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_execTimestamp <em>exec_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setExecTimestamp( java.sql.Timestamp p_execTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exec_timestamp = p_execTimestamp;
    exec_timestamp_is_set = true;
    exec_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>exec_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setExecTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    exec_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    exec_timestamp_is_set = true;
    exec_timestamp_is_modified = true;
  }


  /** 
   * <em>tt_selling_rate</em>カラムの値を設定します。 
   *
   * @@param p_ttSellingRate <em>tt_selling_rate</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTtSellingRate( double p_ttSellingRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tt_selling_rate = new Double(p_ttSellingRate);
    tt_selling_rate_is_set = true;
    tt_selling_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>tt_selling_rate</em>カラムに値を設定します。 
   */
  public final void setTtSellingRate( Double p_ttSellingRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    tt_selling_rate = p_ttSellingRate;
    tt_selling_rate_is_set = true;
    tt_selling_rate_is_modified = true;
  }


  /** 
   * <em>tt_buying_rate</em>カラムの値を設定します。 
   *
   * @@param p_ttBuyingRate <em>tt_buying_rate</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTtBuyingRate( double p_ttBuyingRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tt_buying_rate = new Double(p_ttBuyingRate);
    tt_buying_rate_is_set = true;
    tt_buying_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>tt_buying_rate</em>カラムに値を設定します。 
   */
  public final void setTtBuyingRate( Double p_ttBuyingRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    tt_buying_rate = p_ttBuyingRate;
    tt_buying_rate_is_set = true;
    tt_buying_rate_is_modified = true;
  }


  /** 
   * <em>sub_currency_ratio</em>カラムの値を設定します。 
   *
   * @@param p_subCurrencyRatio <em>sub_currency_ratio</em>カラムの値をあらわす5桁以下のint値 
   */
  public final void setSubCurrencyRatio( int p_subCurrencyRatio )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sub_currency_ratio = new Integer(p_subCurrencyRatio);
    sub_currency_ratio_is_set = true;
    sub_currency_ratio_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>sub_currency_ratio</em>カラムに値を設定します。 
   */
  public final void setSubCurrencyRatio( Integer p_subCurrencyRatio )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sub_currency_ratio = p_subCurrencyRatio;
    sub_currency_ratio_is_set = true;
    sub_currency_ratio_is_modified = true;
  }


  /** 
   * <em>restrict_rate</em>カラムの値を設定します。 
   *
   * @@param p_restrictRate <em>restrict_rate</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setRestrictRate( double p_restrictRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    restrict_rate = new Double(p_restrictRate);
    restrict_rate_is_set = true;
    restrict_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>restrict_rate</em>カラムに値を設定します。 
   */
  public final void setRestrictRate( Double p_restrictRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    restrict_rate = p_restrictRate;
    restrict_rate_is_set = true;
    restrict_rate_is_modified = true;
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
            case 'c':
                if ( name.equals("currency_code") ) {
                    return this.currency_code;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("exec_timestamp") ) {
                    return this.exec_timestamp;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'r':
                if ( name.equals("restrict_rate") ) {
                    return this.restrict_rate;
                }
                break;
            case 's':
                if ( name.equals("sub_currency_ratio") ) {
                    return this.sub_currency_ratio;
                }
                break;
            case 't':
                if ( name.equals("tt_selling_rate") ) {
                    return this.tt_selling_rate;
                }
                else if ( name.equals("tt_buying_rate") ) {
                    return this.tt_buying_rate;
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
            case 'c':
                if ( name.equals("currency_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'currency_code' must be String: '"+value+"' is not." );
                    this.currency_code = (String) value;
                    if (this.currency_code_is_set)
                        this.currency_code_is_modified = true;
                    this.currency_code_is_set = true;
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
            case 'e':
                if ( name.equals("exec_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'exec_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.exec_timestamp = (java.sql.Timestamp) value;
                    if (this.exec_timestamp_is_set)
                        this.exec_timestamp_is_modified = true;
                    this.exec_timestamp_is_set = true;
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
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.last_updated_timestamp_is_set)
                        this.last_updated_timestamp_is_modified = true;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("restrict_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'restrict_rate' must be Double: '"+value+"' is not." );
                    this.restrict_rate = (Double) value;
                    if (this.restrict_rate_is_set)
                        this.restrict_rate_is_modified = true;
                    this.restrict_rate_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sub_currency_ratio") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'sub_currency_ratio' must be Integer: '"+value+"' is not." );
                    this.sub_currency_ratio = (Integer) value;
                    if (this.sub_currency_ratio_is_set)
                        this.sub_currency_ratio_is_modified = true;
                    this.sub_currency_ratio_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("tt_selling_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'tt_selling_rate' must be Double: '"+value+"' is not." );
                    this.tt_selling_rate = (Double) value;
                    if (this.tt_selling_rate_is_set)
                        this.tt_selling_rate_is_modified = true;
                    this.tt_selling_rate_is_set = true;
                    return;
                }
                else if ( name.equals("tt_buying_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'tt_buying_rate' must be Double: '"+value+"' is not." );
                    this.tt_buying_rate = (Double) value;
                    if (this.tt_buying_rate_is_set)
                        this.tt_buying_rate_is_modified = true;
                    this.tt_buying_rate_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
