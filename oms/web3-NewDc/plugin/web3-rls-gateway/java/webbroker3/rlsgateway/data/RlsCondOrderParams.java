head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.24.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	RlsCondOrderParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.rlsgateway.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * rls_cond_orderテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link RlsCondOrderRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link RlsCondOrderParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link RlsCondOrderParams}が{@@link RlsCondOrderRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see RlsCondOrderPK 
 * @@see RlsCondOrderRow 
 */
public class RlsCondOrderParams extends Params implements RlsCondOrderRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "rls_cond_order";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = RlsCondOrderRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return RlsCondOrderRow.TYPE;
  }


  /** 
   * <em>id</em>カラムの値 
   */
  public  long  id;

  /** 
   * <em>type</em>カラムの値 
   */
  public  long  type;

  /** 
   * <em>parent_id</em>カラムの値 
   */
  public  Long  parent_id;

  /** 
   * <em>status</em>カラムの値 
   */
  public  int  status;

  /** 
   * <em>account_id</em>カラムの値 
   */
  public  long  account_id;

  /** 
   * <em>seq_num</em>カラムの値 
   */
  public  long  seq_num;

  /** 
   * <em>act_ratio</em>カラムの値 
   */
  public  long  act_ratio;

  /** 
   * <em>tstamp</em>カラムの値 
   */
  public  java.sql.Timestamp  tstamp;

  private boolean id_is_set = false;
  private boolean id_is_modified = false;
  private boolean type_is_set = false;
  private boolean type_is_modified = false;
  private boolean parent_id_is_set = false;
  private boolean parent_id_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean seq_num_is_set = false;
  private boolean seq_num_is_modified = false;
  private boolean act_ratio_is_set = false;
  private boolean act_ratio_is_modified = false;
  private boolean tstamp_is_set = false;
  private boolean tstamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "id=" + id
      + "," + "type=" +type
      + "," + "parent_id=" +parent_id
      + "," + "status=" +status
      + "," + "account_id=" +account_id
      + "," + "seq_num=" +seq_num
      + "," + "act_ratio=" +act_ratio
      + "," + "tstamp=" +tstamp
      + "}";
  }


  /** 
   * 値が未設定のRlsCondOrderParamsオブジェクトを作成します。 
   */
  public RlsCondOrderParams() {
    id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のRlsCondOrderRowオブジェクトの値を利用してRlsCondOrderParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するRlsCondOrderRowオブジェクト 
   */
  public RlsCondOrderParams( RlsCondOrderRow p_row )
  {
    id = p_row.getId();
    id_is_set = p_row.getIdIsSet();
    id_is_modified = p_row.getIdIsModified();
    type = p_row.getType();
    type_is_set = p_row.getTypeIsSet();
    type_is_modified = p_row.getTypeIsModified();
    if ( !p_row.getParentIdIsNull() )
      parent_id = new Long( p_row.getParentId() );
    parent_id_is_set = p_row.getParentIdIsSet();
    parent_id_is_modified = p_row.getParentIdIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    seq_num = p_row.getSeqNum();
    seq_num_is_set = p_row.getSeqNumIsSet();
    seq_num_is_modified = p_row.getSeqNumIsModified();
    act_ratio = p_row.getActRatio();
    act_ratio_is_set = p_row.getActRatioIsSet();
    act_ratio_is_modified = p_row.getActRatioIsModified();
    tstamp = p_row.getTstamp();
    tstamp_is_set = p_row.getTstampIsSet();
    tstamp_is_modified = p_row.getTstampIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      type_is_set = true;
      type_is_modified = true;
      parent_id_is_set = true;
      parent_id_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
      account_id_is_set = true;
      account_id_is_modified = true;
      seq_num_is_set = true;
      seq_num_is_modified = true;
      act_ratio_is_set = true;
      act_ratio_is_modified = true;
      tstamp_is_set = true;
      tstamp_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof RlsCondOrderRow ) )
       return false;
    return fieldsEqual( (RlsCondOrderRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のRlsCondOrderRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( RlsCondOrderRow row )
  {
    if ( id != row.getId() )
      return false;
    if ( type != row.getType() )
      return false;
    if ( parent_id == null ) {
      if ( !row.getParentIdIsNull() )
        return false;
    } else if ( row.getParentIdIsNull() || ( parent_id.longValue() != row.getParentId() ) ) {
        return false;
    }
    if ( status != row.getStatus() )
      return false;
    if ( account_id != row.getAccountId() )
      return false;
    if ( seq_num != row.getSeqNum() )
      return false;
    if ( act_ratio != row.getActRatio() )
      return false;
    if ( tstamp == null ) {
      if ( row.getTstamp() != null )
        return false;
    } else if ( !tstamp.equals( row.getTstamp() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  ((int) id)
        + ((int) type)
        + (parent_id!=null? parent_id.hashCode(): 0) 
        + ((int) status)
        + ((int) account_id)
        + ((int) seq_num)
        + ((int) act_ratio)
        + (tstamp!=null? tstamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !type_is_set )
			throw new IllegalArgumentException("non-nullable field 'type' must be set before inserting.");
		if ( !account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_id' must be set before inserting.");
		if ( !seq_num_is_set )
			throw new IllegalArgumentException("non-nullable field 'seq_num' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("id",new Long(id));
		map.put("type",new Long(type));
		if ( parent_id != null )
			map.put("parent_id",parent_id);
		if ( status_is_set )
			map.put("status",new Integer(status));
		map.put("account_id",new Long(account_id));
		map.put("seq_num",new Long(seq_num));
		if ( act_ratio_is_set )
			map.put("act_ratio",new Long(act_ratio));
		if ( tstamp_is_set )
			map.put("tstamp",tstamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( type_is_modified )
			map.put("type",new Long(type));
		if ( parent_id_is_modified )
			map.put("parent_id",parent_id);
		if ( status_is_modified )
			map.put("status",new Integer(status));
		if ( account_id_is_modified )
			map.put("account_id",new Long(account_id));
		if ( seq_num_is_modified )
			map.put("seq_num",new Long(seq_num));
		if ( act_ratio_is_modified )
			map.put("act_ratio",new Long(act_ratio));
		if ( tstamp_is_modified )
			map.put("tstamp",tstamp);
		if (map.size() == 0) {
			if ( type_is_set )
				map.put("type",new Long(type));
			map.put("parent_id",parent_id);
			if ( status_is_set )
				map.put("status",new Integer(status));
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			if ( seq_num_is_set )
				map.put("seq_num",new Long(seq_num));
			if ( act_ratio_is_set )
				map.put("act_ratio",new Long(act_ratio));
			if ( tstamp_is_set )
				map.put("tstamp",tstamp);
		}
		return map;
	}


  /** 
   * <em>id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getId()
  {
    return id;
  }


  /** 
   * <em>id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIdIsSet() {
    return id_is_set;
  }


  /** 
   * <em>id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIdIsModified() {
    return id_is_modified;
  }


  /** 
   * <em>type</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getType()
  {
    return type;
  }


  /** 
   * <em>type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTypeIsSet() {
    return type_is_set;
  }


  /** 
   * <em>type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTypeIsModified() {
    return type_is_modified;
  }


  /** 
   * <em>parent_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getParentId()
  {
    return ( parent_id==null? ((long)0): parent_id.longValue() );
  }


  /** 
   * <em>parent_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getParentIdIsNull()
  {
    return parent_id == null;
  }


  /** 
   * <em>parent_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getParentIdIsSet() {
    return parent_id_is_set;
  }


  /** 
   * <em>parent_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getParentIdIsModified() {
    return parent_id_is_modified;
  }


  /** 
   * <em>status</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getStatus()
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
   * <em>account_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAccountId()
  {
    return account_id;
  }


  /** 
   * <em>account_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountIdIsSet() {
    return account_id_is_set;
  }


  /** 
   * <em>account_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountIdIsModified() {
    return account_id_is_modified;
  }


  /** 
   * <em>seq_num</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getSeqNum()
  {
    return seq_num;
  }


  /** 
   * <em>seq_num</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSeqNumIsSet() {
    return seq_num_is_set;
  }


  /** 
   * <em>seq_num</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSeqNumIsModified() {
    return seq_num_is_modified;
  }


  /** 
   * <em>act_ratio</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getActRatio()
  {
    return act_ratio;
  }


  /** 
   * <em>act_ratio</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getActRatioIsSet() {
    return act_ratio_is_set;
  }


  /** 
   * <em>act_ratio</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getActRatioIsModified() {
    return act_ratio_is_modified;
  }


  /** 
   * <em>tstamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getTstamp()
  {
    return tstamp;
  }


  /** 
   * <em>tstamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTstampIsSet() {
    return tstamp_is_set;
  }


  /** 
   * <em>tstamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTstampIsModified() {
    return tstamp_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new RlsCondOrderPK(id);
  }


  /** 
   * <em>id</em>カラムの値を設定します。 
   *
   * @@param p_id <em>id</em>カラムの値をあらわす38桁以下のlong値 
   */
  public final void setId( long p_id )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    id = p_id;
    id_is_set = true;
    id_is_modified = true;
  }


  /** 
   * <em>type</em>カラムの値を設定します。 
   *
   * @@param p_type <em>type</em>カラムの値をあらわすlong値
   */
  public final void setType( long p_type )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    type = p_type;
    type_is_set = true;
    type_is_modified = true;
  }


  /** 
   * <em>parent_id</em>カラムの値を設定します。 
   *
   * @@param p_parentId <em>parent_id</em>カラムの値をあらわすlong値
   */
  public final void setParentId( long p_parentId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    parent_id = new Long(p_parentId);
    parent_id_is_set = true;
    parent_id_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>parent_id</em>カラムに値を設定します。 
   */
  public final void setParentId( Long p_parentId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    parent_id = p_parentId;
    parent_id_is_set = true;
    parent_id_is_modified = true;
  }


  /** 
   * <em>status</em>カラムの値を設定します。 
   *
   * @@param p_status <em>status</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setStatus( int p_status )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    status = p_status;
    status_is_set = true;
    status_is_modified = true;
  }


  /** 
   * <em>account_id</em>カラムの値を設定します。 
   *
   * @@param p_accountId <em>account_id</em>カラムの値をあらわすlong値
   */
  public final void setAccountId( long p_accountId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_id = p_accountId;
    account_id_is_set = true;
    account_id_is_modified = true;
  }


  /** 
   * <em>seq_num</em>カラムの値を設定します。 
   *
   * @@param p_seqNum <em>seq_num</em>カラムの値をあらわすlong値
   */
  public final void setSeqNum( long p_seqNum )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    seq_num = p_seqNum;
    seq_num_is_set = true;
    seq_num_is_modified = true;
  }


  /** 
   * <em>act_ratio</em>カラムの値を設定します。 
   *
   * @@param p_actRatio <em>act_ratio</em>カラムの値をあらわすlong値
   */
  public final void setActRatio( long p_actRatio )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    act_ratio = p_actRatio;
    act_ratio_is_set = true;
    act_ratio_is_modified = true;
  }


  /** 
   * <em>tstamp</em>カラムの値を設定します。 
   *
   * @@param p_tstamp <em>tstamp</em>カラムの値をあらわす9桁以下のjava.sql.Timestamp値 
   */
  public final void setTstamp( java.sql.Timestamp p_tstamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tstamp = p_tstamp;
    tstamp_is_set = true;
    tstamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>tstamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setTstamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    tstamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    tstamp_is_set = true;
    tstamp_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("account_id") ) {
                    return new Long( account_id );
                }
                else if ( name.equals("act_ratio") ) {
                    return new Long( act_ratio );
                }
                break;
            case 'i':
                if ( name.equals("id") ) {
                    return new Long( id );
                }
                break;
            case 'p':
                if ( name.equals("parent_id") ) {
                    return this.parent_id;
                }
                break;
            case 's':
                if ( name.equals("status") ) {
                    return new Integer( status );
                }
                else if ( name.equals("seq_num") ) {
                    return new Long( seq_num );
                }
                break;
            case 't':
                if ( name.equals("type") ) {
                    return new Long( type );
                }
                else if ( name.equals("tstamp") ) {
                    return this.tstamp;
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
                if ( name.equals("account_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'account_id' must be Long: '"+value+"' is not." );
                    this.account_id = ((Long) value).longValue();
                    if (this.account_id_is_set)
                        this.account_id_is_modified = true;
                    this.account_id_is_set = true;
                    return;
                }
                else if ( name.equals("act_ratio") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'act_ratio' must be Long: '"+value+"' is not." );
                    this.act_ratio = ((Long) value).longValue();
                    if (this.act_ratio_is_set)
                        this.act_ratio_is_modified = true;
                    this.act_ratio_is_set = true;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'id' must be Long: '"+value+"' is not." );
                    this.id = ((Long) value).longValue();
                    if (this.id_is_set)
                        this.id_is_modified = true;
                    this.id_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("parent_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'parent_id' must be Long: '"+value+"' is not." );
                    this.parent_id = (Long) value;
                    if (this.parent_id_is_set)
                        this.parent_id_is_modified = true;
                    this.parent_id_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("status") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'status' must be Integer: '"+value+"' is not." );
                    this.status = ((Integer) value).intValue();
                    if (this.status_is_set)
                        this.status_is_modified = true;
                    this.status_is_set = true;
                    return;
                }
                else if ( name.equals("seq_num") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'seq_num' must be Long: '"+value+"' is not." );
                    this.seq_num = ((Long) value).longValue();
                    if (this.seq_num_is_set)
                        this.seq_num_is_modified = true;
                    this.seq_num_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("type") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'type' must be Long: '"+value+"' is not." );
                    this.type = ((Long) value).longValue();
                    if (this.type_is_set)
                        this.type_is_modified = true;
                    this.type_is_set = true;
                    return;
                }
                else if ( name.equals("tstamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'tstamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.tstamp = (java.sql.Timestamp) value;
                    if (this.tstamp_is_set)
                        this.tstamp_is_modified = true;
                    this.tstamp_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
