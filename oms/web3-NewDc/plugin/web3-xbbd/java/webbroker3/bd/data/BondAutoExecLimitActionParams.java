head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	BondAutoExecLimitActionParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.*;

/** 
 * bond_auto_exec_limit_actionテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link BondAutoExecLimitActionRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link BondAutoExecLimitActionParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link BondAutoExecLimitActionParams}が{@@link BondAutoExecLimitActionRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BondAutoExecLimitActionPK 
 * @@see BondAutoExecLimitActionRow 
 */
public class BondAutoExecLimitActionParams extends Params implements BondAutoExecLimitActionRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "bond_auto_exec_limit_action";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = BondAutoExecLimitActionRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return BondAutoExecLimitActionRow.TYPE;
  }


  /** 
   * <em>product_id</em>カラムの値 
   */
  public  long  product_id;

  /** 
   * <em>execution_update_date</em>カラムの値 
   */
  public  java.sql.Timestamp  execution_update_date;

  /** 
   * <em>auto_exec_amount</em>カラムの値 
   */
  public  Double  auto_exec_amount;

  /** 
   * <em>auto_exec_limit</em>カラムの値 
   */
  public  Double  auto_exec_limit;

  /** 
   * <em>last_updater</em>カラムの値 
   */
  public  String  last_updater;

  /** 
   * <em>online_disp_div</em>カラムの値 
   */
  public  String  online_disp_div;

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
  private boolean execution_update_date_is_set = false;
  private boolean execution_update_date_is_modified = false;
  private boolean auto_exec_amount_is_set = false;
  private boolean auto_exec_amount_is_modified = false;
  private boolean auto_exec_limit_is_set = false;
  private boolean auto_exec_limit_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean online_disp_div_is_set = false;
  private boolean online_disp_div_is_modified = false;
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
      + "," + "execution_update_date=" + execution_update_date
      + "," + "auto_exec_amount=" +auto_exec_amount
      + "," + "auto_exec_limit=" +auto_exec_limit
      + "," + "last_updater=" +last_updater
      + "," + "online_disp_div=" +online_disp_div
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のBondAutoExecLimitActionParamsオブジェクトを作成します。 
   */
  public BondAutoExecLimitActionParams() {
    product_id_is_modified = true;
    execution_update_date_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のBondAutoExecLimitActionRowオブジェクトの値を利用してBondAutoExecLimitActionParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するBondAutoExecLimitActionRowオブジェクト 
   */
  public BondAutoExecLimitActionParams( BondAutoExecLimitActionRow p_row )
  {
    product_id = p_row.getProductId();
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    execution_update_date = p_row.getExecutionUpdateDate();
    execution_update_date_is_set = p_row.getExecutionUpdateDateIsSet();
    execution_update_date_is_modified = p_row.getExecutionUpdateDateIsModified();
    if ( !p_row.getAutoExecAmountIsNull() )
      auto_exec_amount = new Double( p_row.getAutoExecAmount() );
    auto_exec_amount_is_set = p_row.getAutoExecAmountIsSet();
    auto_exec_amount_is_modified = p_row.getAutoExecAmountIsModified();
    if ( !p_row.getAutoExecLimitIsNull() )
      auto_exec_limit = new Double( p_row.getAutoExecLimit() );
    auto_exec_limit_is_set = p_row.getAutoExecLimitIsSet();
    auto_exec_limit_is_modified = p_row.getAutoExecLimitIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
    online_disp_div = p_row.getOnlineDispDiv();
    online_disp_div_is_set = p_row.getOnlineDispDivIsSet();
    online_disp_div_is_modified = p_row.getOnlineDispDivIsModified();
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
      auto_exec_amount_is_set = true;
      auto_exec_amount_is_modified = true;
      auto_exec_limit_is_set = true;
      auto_exec_limit_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
      online_disp_div_is_set = true;
      online_disp_div_is_modified = true;
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
    if ( !( other instanceof BondAutoExecLimitActionRow ) )
       return false;
    return fieldsEqual( (BondAutoExecLimitActionRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のBondAutoExecLimitActionRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( BondAutoExecLimitActionRow row )
  {
    if ( product_id != row.getProductId() )
      return false;
    if ( execution_update_date == null ) {
      if ( row.getExecutionUpdateDate() != null )
        return false;
    } else if ( !execution_update_date.equals( row.getExecutionUpdateDate() ) ) {
        return false;
    }
    if ( auto_exec_amount == null ) {
      if ( !row.getAutoExecAmountIsNull() )
        return false;
    } else if ( row.getAutoExecAmountIsNull() || ( auto_exec_amount.doubleValue() != row.getAutoExecAmount() ) ) {
        return false;
    }
    if ( auto_exec_limit == null ) {
      if ( !row.getAutoExecLimitIsNull() )
        return false;
    } else if ( row.getAutoExecLimitIsNull() || ( auto_exec_limit.doubleValue() != row.getAutoExecLimit() ) ) {
        return false;
    }
    if ( last_updater == null ) {
      if ( row.getLastUpdater() != null )
        return false;
    } else if ( !last_updater.equals( row.getLastUpdater() ) ) {
        return false;
    }
    if ( online_disp_div == null ) {
      if ( row.getOnlineDispDiv() != null )
        return false;
    } else if ( !online_disp_div.equals( row.getOnlineDispDiv() ) ) {
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
        + (execution_update_date!=null? execution_update_date.hashCode(): 0) 
        + (auto_exec_amount!=null? auto_exec_amount.hashCode(): 0) 
        + (auto_exec_limit!=null? auto_exec_limit.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (online_disp_div!=null? online_disp_div.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !online_disp_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'online_disp_div' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("product_id",new Long(product_id));
		map.put("execution_update_date",execution_update_date);
		if ( auto_exec_amount != null )
			map.put("auto_exec_amount",auto_exec_amount);
		if ( auto_exec_limit != null )
			map.put("auto_exec_limit",auto_exec_limit);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		map.put("online_disp_div",online_disp_div);
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
		if ( auto_exec_amount_is_modified )
			map.put("auto_exec_amount",auto_exec_amount);
		if ( auto_exec_limit_is_modified )
			map.put("auto_exec_limit",auto_exec_limit);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( online_disp_div_is_modified )
			map.put("online_disp_div",online_disp_div);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("auto_exec_amount",auto_exec_amount);
			map.put("auto_exec_limit",auto_exec_limit);
			map.put("last_updater",last_updater);
			if ( online_disp_div_is_set )
				map.put("online_disp_div",online_disp_div);
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
   * <em>execution_update_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getExecutionUpdateDate()
  {
    return execution_update_date;
  }


  /** 
   * <em>execution_update_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecutionUpdateDateIsSet() {
    return execution_update_date_is_set;
  }


  /** 
   * <em>execution_update_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecutionUpdateDateIsModified() {
    return execution_update_date_is_modified;
  }


  /** 
   * <em>auto_exec_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getAutoExecAmount()
  {
    return ( auto_exec_amount==null? ((double)0): auto_exec_amount.doubleValue() );
  }


  /** 
   * <em>auto_exec_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAutoExecAmountIsNull()
  {
    return auto_exec_amount == null;
  }


  /** 
   * <em>auto_exec_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAutoExecAmountIsSet() {
    return auto_exec_amount_is_set;
  }


  /** 
   * <em>auto_exec_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAutoExecAmountIsModified() {
    return auto_exec_amount_is_modified;
  }


  /** 
   * <em>auto_exec_limit</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getAutoExecLimit()
  {
    return ( auto_exec_limit==null? ((double)0): auto_exec_limit.doubleValue() );
  }


  /** 
   * <em>auto_exec_limit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAutoExecLimitIsNull()
  {
    return auto_exec_limit == null;
  }


  /** 
   * <em>auto_exec_limit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAutoExecLimitIsSet() {
    return auto_exec_limit_is_set;
  }


  /** 
   * <em>auto_exec_limit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAutoExecLimitIsModified() {
    return auto_exec_limit_is_modified;
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
   * <em>online_disp_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOnlineDispDiv()
  {
    return online_disp_div;
  }


  /** 
   * <em>online_disp_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOnlineDispDivIsSet() {
    return online_disp_div_is_set;
  }


  /** 
   * <em>online_disp_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOnlineDispDivIsModified() {
    return online_disp_div_is_modified;
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
    return new BondAutoExecLimitActionPK(product_id, execution_update_date);
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
   * <em>execution_update_date</em>カラムの値を設定します。 
   *
   * @@param p_executionUpdateDate <em>execution_update_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setExecutionUpdateDate( java.sql.Timestamp p_executionUpdateDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    execution_update_date = p_executionUpdateDate;
    execution_update_date_is_set = true;
    execution_update_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>execution_update_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setExecutionUpdateDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    execution_update_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    execution_update_date_is_set = true;
    execution_update_date_is_modified = true;
  }


  /** 
   * <em>auto_exec_amount</em>カラムの値を設定します。 
   *
   * @@param p_autoExecAmount <em>auto_exec_amount</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setAutoExecAmount( double p_autoExecAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    auto_exec_amount = new Double(p_autoExecAmount);
    auto_exec_amount_is_set = true;
    auto_exec_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>auto_exec_amount</em>カラムに値を設定します。 
   */
  public final void setAutoExecAmount( Double p_autoExecAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    auto_exec_amount = p_autoExecAmount;
    auto_exec_amount_is_set = true;
    auto_exec_amount_is_modified = true;
  }


  /** 
   * <em>auto_exec_limit</em>カラムの値を設定します。 
   *
   * @@param p_autoExecLimit <em>auto_exec_limit</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setAutoExecLimit( double p_autoExecLimit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    auto_exec_limit = new Double(p_autoExecLimit);
    auto_exec_limit_is_set = true;
    auto_exec_limit_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>auto_exec_limit</em>カラムに値を設定します。 
   */
  public final void setAutoExecLimit( Double p_autoExecLimit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    auto_exec_limit = p_autoExecLimit;
    auto_exec_limit_is_set = true;
    auto_exec_limit_is_modified = true;
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
   * <em>online_disp_div</em>カラムの値を設定します。 
   *
   * @@param p_onlineDispDiv <em>online_disp_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOnlineDispDiv( String p_onlineDispDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    online_disp_div = p_onlineDispDiv;
    online_disp_div_is_set = true;
    online_disp_div_is_modified = true;
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
                if ( name.equals("auto_exec_amount") ) {
                    return this.auto_exec_amount;
                }
                else if ( name.equals("auto_exec_limit") ) {
                    return this.auto_exec_limit;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("execution_update_date") ) {
                    return this.execution_update_date;
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
            case 'o':
                if ( name.equals("online_disp_div") ) {
                    return this.online_disp_div;
                }
                break;
            case 'p':
                if ( name.equals("product_id") ) {
                    return new Long( product_id );
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
                if ( name.equals("auto_exec_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'auto_exec_amount' must be Double: '"+value+"' is not." );
                    this.auto_exec_amount = (Double) value;
                    if (this.auto_exec_amount_is_set)
                        this.auto_exec_amount_is_modified = true;
                    this.auto_exec_amount_is_set = true;
                    return;
                }
                else if ( name.equals("auto_exec_limit") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'auto_exec_limit' must be Double: '"+value+"' is not." );
                    this.auto_exec_limit = (Double) value;
                    if (this.auto_exec_limit_is_set)
                        this.auto_exec_limit_is_modified = true;
                    this.auto_exec_limit_is_set = true;
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
            case 'e':
                if ( name.equals("execution_update_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'execution_update_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.execution_update_date = (java.sql.Timestamp) value;
                    if (this.execution_update_date_is_set)
                        this.execution_update_date_is_modified = true;
                    this.execution_update_date_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
                    if ( value != null )
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
            case 'o':
                if ( name.equals("online_disp_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'online_disp_div' must be String: '"+value+"' is not." );
                    this.online_disp_div = (String) value;
                    if (this.online_disp_div_is_set)
                        this.online_disp_div_is_modified = true;
                    this.online_disp_div_is_set = true;
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
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
