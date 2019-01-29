head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.44.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	SrvRegiChargeParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.srvregi.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * srv_regi_chargeテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link SrvRegiChargeRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link SrvRegiChargeParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link SrvRegiChargeParams}が{@@link SrvRegiChargeRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SrvRegiChargePK 
 * @@see SrvRegiChargeRow 
 */
public class SrvRegiChargeParams extends Params implements SrvRegiChargeRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "srv_regi_charge";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = SrvRegiChargeRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return SrvRegiChargeRow.TYPE;
  }


  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>srv_div</em>カラムの値 
   */
  public  String  srv_div;

  /** 
   * <em>consecutive_numbers</em>カラムの値 
   */
  public  long  consecutive_numbers;

  /** 
   * <em>srv_use_period_div</em>カラムの値 
   */
  public  String  srv_use_period_div;

  /** 
   * <em>srv_use_period</em>カラムの値 
   */
  public  int  srv_use_period;

  /** 
   * <em>use_amt</em>カラムの値 
   */
  public  long  use_amt;

  /** 
   * <em>last_updater</em>カラムの値 
   */
  public  String  last_updater;

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
  private boolean srv_div_is_set = false;
  private boolean srv_div_is_modified = false;
  private boolean consecutive_numbers_is_set = false;
  private boolean consecutive_numbers_is_modified = false;
  private boolean srv_use_period_div_is_set = false;
  private boolean srv_use_period_div_is_modified = false;
  private boolean srv_use_period_is_set = false;
  private boolean srv_use_period_is_modified = false;
  private boolean use_amt_is_set = false;
  private boolean use_amt_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
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
      + "," + "srv_div=" + srv_div
      + "," + "consecutive_numbers=" + consecutive_numbers
      + "," + "srv_use_period_div=" +srv_use_period_div
      + "," + "srv_use_period=" +srv_use_period
      + "," + "use_amt=" +use_amt
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のSrvRegiChargeParamsオブジェクトを作成します。 
   */
  public SrvRegiChargeParams() {
    institution_code_is_modified = true;
    srv_div_is_modified = true;
    consecutive_numbers_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のSrvRegiChargeRowオブジェクトの値を利用してSrvRegiChargeParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するSrvRegiChargeRowオブジェクト 
   */
  public SrvRegiChargeParams( SrvRegiChargeRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    srv_div = p_row.getSrvDiv();
    srv_div_is_set = p_row.getSrvDivIsSet();
    srv_div_is_modified = p_row.getSrvDivIsModified();
    consecutive_numbers = p_row.getConsecutiveNumbers();
    consecutive_numbers_is_set = p_row.getConsecutiveNumbersIsSet();
    consecutive_numbers_is_modified = p_row.getConsecutiveNumbersIsModified();
    srv_use_period_div = p_row.getSrvUsePeriodDiv();
    srv_use_period_div_is_set = p_row.getSrvUsePeriodDivIsSet();
    srv_use_period_div_is_modified = p_row.getSrvUsePeriodDivIsModified();
    srv_use_period = p_row.getSrvUsePeriod();
    srv_use_period_is_set = p_row.getSrvUsePeriodIsSet();
    srv_use_period_is_modified = p_row.getSrvUsePeriodIsModified();
    use_amt = p_row.getUseAmt();
    use_amt_is_set = p_row.getUseAmtIsSet();
    use_amt_is_modified = p_row.getUseAmtIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
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
      srv_use_period_div_is_set = true;
      srv_use_period_div_is_modified = true;
      srv_use_period_is_set = true;
      srv_use_period_is_modified = true;
      use_amt_is_set = true;
      use_amt_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
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
    if ( !( other instanceof SrvRegiChargeRow ) )
       return false;
    return fieldsEqual( (SrvRegiChargeRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のSrvRegiChargeRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( SrvRegiChargeRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( srv_div == null ) {
      if ( row.getSrvDiv() != null )
        return false;
    } else if ( !srv_div.equals( row.getSrvDiv() ) ) {
        return false;
    }
    if ( consecutive_numbers != row.getConsecutiveNumbers() )
      return false;
    if ( srv_use_period_div == null ) {
      if ( row.getSrvUsePeriodDiv() != null )
        return false;
    } else if ( !srv_use_period_div.equals( row.getSrvUsePeriodDiv() ) ) {
        return false;
    }
    if ( srv_use_period != row.getSrvUsePeriod() )
      return false;
    if ( use_amt != row.getUseAmt() )
      return false;
    if ( last_updater == null ) {
      if ( row.getLastUpdater() != null )
        return false;
    } else if ( !last_updater.equals( row.getLastUpdater() ) ) {
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
        + (srv_div!=null? srv_div.hashCode(): 0) 
        + ((int) consecutive_numbers)
        + (srv_use_period_div!=null? srv_use_period_div.hashCode(): 0) 
        + ((int) srv_use_period)
        + ((int) use_amt)
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !srv_use_period_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'srv_use_period_div' must be set before inserting.");
		if ( !srv_use_period_is_set )
			throw new IllegalArgumentException("non-nullable field 'srv_use_period' must be set before inserting.");
		if ( !use_amt_is_set )
			throw new IllegalArgumentException("non-nullable field 'use_amt' must be set before inserting.");
		if ( !last_updater_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updater' must be set before inserting.");
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
		map.put("srv_div",srv_div);
		map.put("consecutive_numbers",new Long(consecutive_numbers));
		map.put("srv_use_period_div",srv_use_period_div);
		map.put("srv_use_period",new Integer(srv_use_period));
		map.put("use_amt",new Long(use_amt));
		map.put("last_updater",last_updater);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( srv_use_period_div_is_modified )
			map.put("srv_use_period_div",srv_use_period_div);
		if ( srv_use_period_is_modified )
			map.put("srv_use_period",new Integer(srv_use_period));
		if ( use_amt_is_modified )
			map.put("use_amt",new Long(use_amt));
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( srv_use_period_div_is_set )
				map.put("srv_use_period_div",srv_use_period_div);
			if ( srv_use_period_is_set )
				map.put("srv_use_period",new Integer(srv_use_period));
			if ( use_amt_is_set )
				map.put("use_amt",new Long(use_amt));
			if ( last_updater_is_set )
				map.put("last_updater",last_updater);
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
   * <em>srv_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSrvDiv()
  {
    return srv_div;
  }


  /** 
   * <em>srv_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSrvDivIsSet() {
    return srv_div_is_set;
  }


  /** 
   * <em>srv_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSrvDivIsModified() {
    return srv_div_is_modified;
  }


  /** 
   * <em>consecutive_numbers</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getConsecutiveNumbers()
  {
    return consecutive_numbers;
  }


  /** 
   * <em>consecutive_numbers</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConsecutiveNumbersIsSet() {
    return consecutive_numbers_is_set;
  }


  /** 
   * <em>consecutive_numbers</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConsecutiveNumbersIsModified() {
    return consecutive_numbers_is_modified;
  }


  /** 
   * <em>srv_use_period_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSrvUsePeriodDiv()
  {
    return srv_use_period_div;
  }


  /** 
   * <em>srv_use_period_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSrvUsePeriodDivIsSet() {
    return srv_use_period_div_is_set;
  }


  /** 
   * <em>srv_use_period_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSrvUsePeriodDivIsModified() {
    return srv_use_period_div_is_modified;
  }


  /** 
   * <em>srv_use_period</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getSrvUsePeriod()
  {
    return srv_use_period;
  }


  /** 
   * <em>srv_use_period</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSrvUsePeriodIsSet() {
    return srv_use_period_is_set;
  }


  /** 
   * <em>srv_use_period</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSrvUsePeriodIsModified() {
    return srv_use_period_is_modified;
  }


  /** 
   * <em>use_amt</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getUseAmt()
  {
    return use_amt;
  }


  /** 
   * <em>use_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUseAmtIsSet() {
    return use_amt_is_set;
  }


  /** 
   * <em>use_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUseAmtIsModified() {
    return use_amt_is_modified;
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
    return new SrvRegiChargePK(institution_code, srv_div, consecutive_numbers);
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
   * <em>srv_div</em>カラムの値を設定します。 
   *
   * @@param p_srvDiv <em>srv_div</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setSrvDiv( String p_srvDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    srv_div = p_srvDiv;
    srv_div_is_set = true;
    srv_div_is_modified = true;
  }


  /** 
   * <em>consecutive_numbers</em>カラムの値を設定します。 
   *
   * @@param p_consecutiveNumbers <em>consecutive_numbers</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setConsecutiveNumbers( long p_consecutiveNumbers )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    consecutive_numbers = p_consecutiveNumbers;
    consecutive_numbers_is_set = true;
    consecutive_numbers_is_modified = true;
  }


  /** 
   * <em>srv_use_period_div</em>カラムの値を設定します。 
   *
   * @@param p_srvUsePeriodDiv <em>srv_use_period_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSrvUsePeriodDiv( String p_srvUsePeriodDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    srv_use_period_div = p_srvUsePeriodDiv;
    srv_use_period_div_is_set = true;
    srv_use_period_div_is_modified = true;
  }


  /** 
   * <em>srv_use_period</em>カラムの値を設定します。 
   *
   * @@param p_srvUsePeriod <em>srv_use_period</em>カラムの値をあらわす3桁以下のint値 
   */
  public final void setSrvUsePeriod( int p_srvUsePeriod )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    srv_use_period = p_srvUsePeriod;
    srv_use_period_is_set = true;
    srv_use_period_is_modified = true;
  }


  /** 
   * <em>use_amt</em>カラムの値を設定します。 
   *
   * @@param p_useAmt <em>use_amt</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setUseAmt( long p_useAmt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    use_amt = p_useAmt;
    use_amt_is_set = true;
    use_amt_is_modified = true;
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
                if ( name.equals("consecutive_numbers") ) {
                    return new Long( consecutive_numbers );
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
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
            case 's':
                if ( name.equals("srv_div") ) {
                    return this.srv_div;
                }
                else if ( name.equals("srv_use_period_div") ) {
                    return this.srv_use_period_div;
                }
                else if ( name.equals("srv_use_period") ) {
                    return new Integer( srv_use_period );
                }
                break;
            case 'u':
                if ( name.equals("use_amt") ) {
                    return new Long( use_amt );
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
                if ( name.equals("consecutive_numbers") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'consecutive_numbers' must be Long: '"+value+"' is not." );
                    this.consecutive_numbers = ((Long) value).longValue();
                    if (this.consecutive_numbers_is_set)
                        this.consecutive_numbers_is_modified = true;
                    this.consecutive_numbers_is_set = true;
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
            case 's':
                if ( name.equals("srv_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'srv_div' must be String: '"+value+"' is not." );
                    this.srv_div = (String) value;
                    if (this.srv_div_is_set)
                        this.srv_div_is_modified = true;
                    this.srv_div_is_set = true;
                    return;
                }
                else if ( name.equals("srv_use_period_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'srv_use_period_div' must be String: '"+value+"' is not." );
                    this.srv_use_period_div = (String) value;
                    if (this.srv_use_period_div_is_set)
                        this.srv_use_period_div_is_modified = true;
                    this.srv_use_period_div_is_set = true;
                    return;
                }
                else if ( name.equals("srv_use_period") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'srv_use_period' must be Integer: '"+value+"' is not." );
                    this.srv_use_period = ((Integer) value).intValue();
                    if (this.srv_use_period_is_set)
                        this.srv_use_period_is_modified = true;
                    this.srv_use_period_is_set = true;
                    return;
                }
                break;
            case 'u':
                if ( name.equals("use_amt") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'use_amt' must be Long: '"+value+"' is not." );
                    this.use_amt = ((Long) value).longValue();
                    if (this.use_amt_is_set)
                        this.use_amt_is_modified = true;
                    this.use_amt_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
