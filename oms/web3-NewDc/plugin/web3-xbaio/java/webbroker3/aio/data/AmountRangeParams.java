head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.40.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	AmountRangeParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * amount_rangeテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link AmountRangeRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link AmountRangeParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link AmountRangeParams}が{@@link AmountRangeRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AmountRangePK 
 * @@see AmountRangeRow 
 */
public class AmountRangeParams extends Params implements AmountRangeRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "amount_range";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = AmountRangeRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return AmountRangeRow.TYPE;
  }


  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>fund_type</em>カラムの値 
   */
  public  String  fund_type;

  /** 
   * <em>transaction_type</em>カラムの値 
   */
  public  String  transaction_type;

  /** 
   * <em>max_amount</em>カラムの値 
   */
  public  Long  max_amount;

  /** 
   * <em>min_amount</em>カラムの値 
   */
  public  Long  min_amount;

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
  private boolean fund_type_is_set = false;
  private boolean fund_type_is_modified = false;
  private boolean transaction_type_is_set = false;
  private boolean transaction_type_is_modified = false;
  private boolean max_amount_is_set = false;
  private boolean max_amount_is_modified = false;
  private boolean min_amount_is_set = false;
  private boolean min_amount_is_modified = false;
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
      + "," + "fund_type=" + fund_type
      + "," + "transaction_type=" + transaction_type
      + "," + "max_amount=" +max_amount
      + "," + "min_amount=" +min_amount
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のAmountRangeParamsオブジェクトを作成します。 
   */
  public AmountRangeParams() {
    institution_code_is_modified = true;
    fund_type_is_modified = true;
    transaction_type_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のAmountRangeRowオブジェクトの値を利用してAmountRangeParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するAmountRangeRowオブジェクト 
   */
  public AmountRangeParams( AmountRangeRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    fund_type = p_row.getFundType();
    fund_type_is_set = p_row.getFundTypeIsSet();
    fund_type_is_modified = p_row.getFundTypeIsModified();
    transaction_type = p_row.getTransactionType();
    transaction_type_is_set = p_row.getTransactionTypeIsSet();
    transaction_type_is_modified = p_row.getTransactionTypeIsModified();
    if ( !p_row.getMaxAmountIsNull() )
      max_amount = new Long( p_row.getMaxAmount() );
    max_amount_is_set = p_row.getMaxAmountIsSet();
    max_amount_is_modified = p_row.getMaxAmountIsModified();
    if ( !p_row.getMinAmountIsNull() )
      min_amount = new Long( p_row.getMinAmount() );
    min_amount_is_set = p_row.getMinAmountIsSet();
    min_amount_is_modified = p_row.getMinAmountIsModified();
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
      max_amount_is_set = true;
      max_amount_is_modified = true;
      min_amount_is_set = true;
      min_amount_is_modified = true;
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
    if ( !( other instanceof AmountRangeRow ) )
       return false;
    return fieldsEqual( (AmountRangeRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のAmountRangeRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( AmountRangeRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( fund_type == null ) {
      if ( row.getFundType() != null )
        return false;
    } else if ( !fund_type.equals( row.getFundType() ) ) {
        return false;
    }
    if ( transaction_type == null ) {
      if ( row.getTransactionType() != null )
        return false;
    } else if ( !transaction_type.equals( row.getTransactionType() ) ) {
        return false;
    }
    if ( max_amount == null ) {
      if ( !row.getMaxAmountIsNull() )
        return false;
    } else if ( row.getMaxAmountIsNull() || ( max_amount.longValue() != row.getMaxAmount() ) ) {
        return false;
    }
    if ( min_amount == null ) {
      if ( !row.getMinAmountIsNull() )
        return false;
    } else if ( row.getMinAmountIsNull() || ( min_amount.longValue() != row.getMinAmount() ) ) {
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
        + (fund_type!=null? fund_type.hashCode(): 0) 
        + (transaction_type!=null? transaction_type.hashCode(): 0) 
        + (max_amount!=null? max_amount.hashCode(): 0) 
        + (min_amount!=null? min_amount.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
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
		map.put("institution_code",institution_code);
		map.put("fund_type",fund_type);
		map.put("transaction_type",transaction_type);
		if ( max_amount != null )
			map.put("max_amount",max_amount);
		if ( min_amount != null )
			map.put("min_amount",min_amount);
		if ( created_timestamp != null )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp != null )
			map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( max_amount_is_modified )
			map.put("max_amount",max_amount);
		if ( min_amount_is_modified )
			map.put("min_amount",min_amount);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("max_amount",max_amount);
			map.put("min_amount",min_amount);
			map.put("created_timestamp",created_timestamp);
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
   * <em>fund_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFundType()
  {
    return fund_type;
  }


  /** 
   * <em>fund_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFundTypeIsSet() {
    return fund_type_is_set;
  }


  /** 
   * <em>fund_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFundTypeIsModified() {
    return fund_type_is_modified;
  }


  /** 
   * <em>transaction_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTransactionType()
  {
    return transaction_type;
  }


  /** 
   * <em>transaction_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransactionTypeIsSet() {
    return transaction_type_is_set;
  }


  /** 
   * <em>transaction_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransactionTypeIsModified() {
    return transaction_type_is_modified;
  }


  /** 
   * <em>max_amount</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMaxAmount()
  {
    return ( max_amount==null? ((long)0): max_amount.longValue() );
  }


  /** 
   * <em>max_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMaxAmountIsNull()
  {
    return max_amount == null;
  }


  /** 
   * <em>max_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxAmountIsSet() {
    return max_amount_is_set;
  }


  /** 
   * <em>max_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxAmountIsModified() {
    return max_amount_is_modified;
  }


  /** 
   * <em>min_amount</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMinAmount()
  {
    return ( min_amount==null? ((long)0): min_amount.longValue() );
  }


  /** 
   * <em>min_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMinAmountIsNull()
  {
    return min_amount == null;
  }


  /** 
   * <em>min_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMinAmountIsSet() {
    return min_amount_is_set;
  }


  /** 
   * <em>min_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMinAmountIsModified() {
    return min_amount_is_modified;
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
    return new AmountRangePK(institution_code, fund_type, transaction_type);
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
   * <em>fund_type</em>カラムの値を設定します。 
   *
   * @@param p_fundType <em>fund_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFundType( String p_fundType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fund_type = p_fundType;
    fund_type_is_set = true;
    fund_type_is_modified = true;
  }


  /** 
   * <em>transaction_type</em>カラムの値を設定します。 
   *
   * @@param p_transactionType <em>transaction_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTransactionType( String p_transactionType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transaction_type = p_transactionType;
    transaction_type_is_set = true;
    transaction_type_is_modified = true;
  }


  /** 
   * <em>max_amount</em>カラムの値を設定します。 
   *
   * @@param p_maxAmount <em>max_amount</em>カラムの値をあらわす11桁以下のlong値 
   */
  public final void setMaxAmount( long p_maxAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    max_amount = new Long(p_maxAmount);
    max_amount_is_set = true;
    max_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>max_amount</em>カラムに値を設定します。 
   */
  public final void setMaxAmount( Long p_maxAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    max_amount = p_maxAmount;
    max_amount_is_set = true;
    max_amount_is_modified = true;
  }


  /** 
   * <em>min_amount</em>カラムの値を設定します。 
   *
   * @@param p_minAmount <em>min_amount</em>カラムの値をあらわす11桁以下のlong値 
   */
  public final void setMinAmount( long p_minAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    min_amount = new Long(p_minAmount);
    min_amount_is_set = true;
    min_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>min_amount</em>カラムに値を設定します。 
   */
  public final void setMinAmount( Long p_minAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    min_amount = p_minAmount;
    min_amount_is_set = true;
    min_amount_is_modified = true;
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
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'f':
                if ( name.equals("fund_type") ) {
                    return this.fund_type;
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
            case 'm':
                if ( name.equals("max_amount") ) {
                    return this.max_amount;
                }
                else if ( name.equals("min_amount") ) {
                    return this.min_amount;
                }
                break;
            case 't':
                if ( name.equals("transaction_type") ) {
                    return this.transaction_type;
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
                if ( name.equals("fund_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fund_type' must be String: '"+value+"' is not." );
                    this.fund_type = (String) value;
                    if (this.fund_type_is_set)
                        this.fund_type_is_modified = true;
                    this.fund_type_is_set = true;
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
            case 'm':
                if ( name.equals("max_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'max_amount' must be Long: '"+value+"' is not." );
                    this.max_amount = (Long) value;
                    if (this.max_amount_is_set)
                        this.max_amount_is_modified = true;
                    this.max_amount_is_set = true;
                    return;
                }
                else if ( name.equals("min_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'min_amount' must be Long: '"+value+"' is not." );
                    this.min_amount = (Long) value;
                    if (this.min_amount_is_set)
                        this.min_amount_is_modified = true;
                    this.min_amount_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("transaction_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'transaction_type' must be String: '"+value+"' is not." );
                    this.transaction_type = (String) value;
                    if (this.transaction_type_is_set)
                        this.transaction_type_is_modified = true;
                    this.transaction_type_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
