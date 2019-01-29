head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.23.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	SoapConnectPrefRpcParams.java;


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
 * soap_connect_pref_rpcテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link SoapConnectPrefRpcRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link SoapConnectPrefRpcParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link SoapConnectPrefRpcParams}が{@@link SoapConnectPrefRpcRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SoapConnectPrefRpcPK 
 * @@see SoapConnectPrefRpcRow 
 */
public class SoapConnectPrefRpcParams extends Params implements SoapConnectPrefRpcRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "soap_connect_pref_rpc";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = SoapConnectPrefRpcRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return SoapConnectPrefRpcRow.TYPE;
  }


  /** 
   * <em>branch_id</em>カラムの値 
   */
  public  long  branch_id;

  /** 
   * <em>connect_div</em>カラムの値 
   */
  public  String  connect_div;

  /** 
   * <em>endpoint_name</em>カラムの値 
   */
  public  String  endpoint_name;

  /** 
   * <em>target_namespace_name</em>カラムの値 
   */
  public  String  target_namespace_name;

  /** 
   * <em>service_name</em>カラムの値 
   */
  public  String  service_name;

  /** 
   * <em>port_type_name</em>カラムの値 
   */
  public  String  port_type_name;

  /** 
   * <em>operation_name</em>カラムの値 
   */
  public  String  operation_name;

  /** 
   * <em>parameter_list</em>カラムの値 
   */
  public  String  parameter_list;

  /** 
   * <em>parameter_type_list</em>カラムの値 
   */
  public  String  parameter_type_list;

  /** 
   * <em>response_param_type</em>カラムの値 
   */
  public  String  response_param_type;

  /** 
   * <em>response_timeout</em>カラムの値 
   */
  public  String  response_timeout;

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

  private boolean branch_id_is_set = false;
  private boolean branch_id_is_modified = false;
  private boolean connect_div_is_set = false;
  private boolean connect_div_is_modified = false;
  private boolean endpoint_name_is_set = false;
  private boolean endpoint_name_is_modified = false;
  private boolean target_namespace_name_is_set = false;
  private boolean target_namespace_name_is_modified = false;
  private boolean service_name_is_set = false;
  private boolean service_name_is_modified = false;
  private boolean port_type_name_is_set = false;
  private boolean port_type_name_is_modified = false;
  private boolean operation_name_is_set = false;
  private boolean operation_name_is_modified = false;
  private boolean parameter_list_is_set = false;
  private boolean parameter_list_is_modified = false;
  private boolean parameter_type_list_is_set = false;
  private boolean parameter_type_list_is_modified = false;
  private boolean response_param_type_is_set = false;
  private boolean response_param_type_is_modified = false;
  private boolean response_timeout_is_set = false;
  private boolean response_timeout_is_modified = false;
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
       + "branch_id=" + branch_id
      + "," + "connect_div=" + connect_div
      + "," + "endpoint_name=" +endpoint_name
      + "," + "target_namespace_name=" +target_namespace_name
      + "," + "service_name=" +service_name
      + "," + "port_type_name=" +port_type_name
      + "," + "operation_name=" +operation_name
      + "," + "parameter_list=" +parameter_list
      + "," + "parameter_type_list=" +parameter_type_list
      + "," + "response_param_type=" +response_param_type
      + "," + "response_timeout=" +response_timeout
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のSoapConnectPrefRpcParamsオブジェクトを作成します。 
   */
  public SoapConnectPrefRpcParams() {
    branch_id_is_modified = true;
    connect_div_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のSoapConnectPrefRpcRowオブジェクトの値を利用してSoapConnectPrefRpcParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するSoapConnectPrefRpcRowオブジェクト 
   */
  public SoapConnectPrefRpcParams( SoapConnectPrefRpcRow p_row )
  {
    branch_id = p_row.getBranchId();
    branch_id_is_set = p_row.getBranchIdIsSet();
    branch_id_is_modified = p_row.getBranchIdIsModified();
    connect_div = p_row.getConnectDiv();
    connect_div_is_set = p_row.getConnectDivIsSet();
    connect_div_is_modified = p_row.getConnectDivIsModified();
    endpoint_name = p_row.getEndpointName();
    endpoint_name_is_set = p_row.getEndpointNameIsSet();
    endpoint_name_is_modified = p_row.getEndpointNameIsModified();
    target_namespace_name = p_row.getTargetNamespaceName();
    target_namespace_name_is_set = p_row.getTargetNamespaceNameIsSet();
    target_namespace_name_is_modified = p_row.getTargetNamespaceNameIsModified();
    service_name = p_row.getServiceName();
    service_name_is_set = p_row.getServiceNameIsSet();
    service_name_is_modified = p_row.getServiceNameIsModified();
    port_type_name = p_row.getPortTypeName();
    port_type_name_is_set = p_row.getPortTypeNameIsSet();
    port_type_name_is_modified = p_row.getPortTypeNameIsModified();
    operation_name = p_row.getOperationName();
    operation_name_is_set = p_row.getOperationNameIsSet();
    operation_name_is_modified = p_row.getOperationNameIsModified();
    parameter_list = p_row.getParameterList();
    parameter_list_is_set = p_row.getParameterListIsSet();
    parameter_list_is_modified = p_row.getParameterListIsModified();
    parameter_type_list = p_row.getParameterTypeList();
    parameter_type_list_is_set = p_row.getParameterTypeListIsSet();
    parameter_type_list_is_modified = p_row.getParameterTypeListIsModified();
    response_param_type = p_row.getResponseParamType();
    response_param_type_is_set = p_row.getResponseParamTypeIsSet();
    response_param_type_is_modified = p_row.getResponseParamTypeIsModified();
    response_timeout = p_row.getResponseTimeout();
    response_timeout_is_set = p_row.getResponseTimeoutIsSet();
    response_timeout_is_modified = p_row.getResponseTimeoutIsModified();
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
      endpoint_name_is_set = true;
      endpoint_name_is_modified = true;
      target_namespace_name_is_set = true;
      target_namespace_name_is_modified = true;
      service_name_is_set = true;
      service_name_is_modified = true;
      port_type_name_is_set = true;
      port_type_name_is_modified = true;
      operation_name_is_set = true;
      operation_name_is_modified = true;
      parameter_list_is_set = true;
      parameter_list_is_modified = true;
      parameter_type_list_is_set = true;
      parameter_type_list_is_modified = true;
      response_param_type_is_set = true;
      response_param_type_is_modified = true;
      response_timeout_is_set = true;
      response_timeout_is_modified = true;
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
    if ( !( other instanceof SoapConnectPrefRpcRow ) )
       return false;
    return fieldsEqual( (SoapConnectPrefRpcRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のSoapConnectPrefRpcRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( SoapConnectPrefRpcRow row )
  {
    if ( branch_id != row.getBranchId() )
      return false;
    if ( connect_div == null ) {
      if ( row.getConnectDiv() != null )
        return false;
    } else if ( !connect_div.equals( row.getConnectDiv() ) ) {
        return false;
    }
    if ( endpoint_name == null ) {
      if ( row.getEndpointName() != null )
        return false;
    } else if ( !endpoint_name.equals( row.getEndpointName() ) ) {
        return false;
    }
    if ( target_namespace_name == null ) {
      if ( row.getTargetNamespaceName() != null )
        return false;
    } else if ( !target_namespace_name.equals( row.getTargetNamespaceName() ) ) {
        return false;
    }
    if ( service_name == null ) {
      if ( row.getServiceName() != null )
        return false;
    } else if ( !service_name.equals( row.getServiceName() ) ) {
        return false;
    }
    if ( port_type_name == null ) {
      if ( row.getPortTypeName() != null )
        return false;
    } else if ( !port_type_name.equals( row.getPortTypeName() ) ) {
        return false;
    }
    if ( operation_name == null ) {
      if ( row.getOperationName() != null )
        return false;
    } else if ( !operation_name.equals( row.getOperationName() ) ) {
        return false;
    }
    if ( parameter_list == null ) {
      if ( row.getParameterList() != null )
        return false;
    } else if ( !parameter_list.equals( row.getParameterList() ) ) {
        return false;
    }
    if ( parameter_type_list == null ) {
      if ( row.getParameterTypeList() != null )
        return false;
    } else if ( !parameter_type_list.equals( row.getParameterTypeList() ) ) {
        return false;
    }
    if ( response_param_type == null ) {
      if ( row.getResponseParamType() != null )
        return false;
    } else if ( !response_param_type.equals( row.getResponseParamType() ) ) {
        return false;
    }
    if ( response_timeout == null ) {
      if ( row.getResponseTimeout() != null )
        return false;
    } else if ( !response_timeout.equals( row.getResponseTimeout() ) ) {
        return false;
    }
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
      return  ((int) branch_id)
        + (connect_div!=null? connect_div.hashCode(): 0) 
        + (endpoint_name!=null? endpoint_name.hashCode(): 0) 
        + (target_namespace_name!=null? target_namespace_name.hashCode(): 0) 
        + (service_name!=null? service_name.hashCode(): 0) 
        + (port_type_name!=null? port_type_name.hashCode(): 0) 
        + (operation_name!=null? operation_name.hashCode(): 0) 
        + (parameter_list!=null? parameter_list.hashCode(): 0) 
        + (parameter_type_list!=null? parameter_type_list.hashCode(): 0) 
        + (response_param_type!=null? response_param_type.hashCode(): 0) 
        + (response_timeout!=null? response_timeout.hashCode(): 0) 
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
		if ( !endpoint_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'endpoint_name' must be set before inserting.");
		if ( !target_namespace_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'target_namespace_name' must be set before inserting.");
		if ( !service_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'service_name' must be set before inserting.");
		if ( !port_type_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'port_type_name' must be set before inserting.");
		if ( !operation_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'operation_name' must be set before inserting.");
		if ( !response_param_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'response_param_type' must be set before inserting.");
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
		map.put("branch_id",new Long(branch_id));
		map.put("connect_div",connect_div);
		map.put("endpoint_name",endpoint_name);
		map.put("target_namespace_name",target_namespace_name);
		map.put("service_name",service_name);
		map.put("port_type_name",port_type_name);
		map.put("operation_name",operation_name);
		if ( parameter_list != null )
			map.put("parameter_list",parameter_list);
		if ( parameter_type_list != null )
			map.put("parameter_type_list",parameter_type_list);
		map.put("response_param_type",response_param_type);
		if ( response_timeout != null )
			map.put("response_timeout",response_timeout);
		if ( last_updater != null )
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
		if ( endpoint_name_is_modified )
			map.put("endpoint_name",endpoint_name);
		if ( target_namespace_name_is_modified )
			map.put("target_namespace_name",target_namespace_name);
		if ( service_name_is_modified )
			map.put("service_name",service_name);
		if ( port_type_name_is_modified )
			map.put("port_type_name",port_type_name);
		if ( operation_name_is_modified )
			map.put("operation_name",operation_name);
		if ( parameter_list_is_modified )
			map.put("parameter_list",parameter_list);
		if ( parameter_type_list_is_modified )
			map.put("parameter_type_list",parameter_type_list);
		if ( response_param_type_is_modified )
			map.put("response_param_type",response_param_type);
		if ( response_timeout_is_modified )
			map.put("response_timeout",response_timeout);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( endpoint_name_is_set )
				map.put("endpoint_name",endpoint_name);
			if ( target_namespace_name_is_set )
				map.put("target_namespace_name",target_namespace_name);
			if ( service_name_is_set )
				map.put("service_name",service_name);
			if ( port_type_name_is_set )
				map.put("port_type_name",port_type_name);
			if ( operation_name_is_set )
				map.put("operation_name",operation_name);
			map.put("parameter_list",parameter_list);
			map.put("parameter_type_list",parameter_type_list);
			if ( response_param_type_is_set )
				map.put("response_param_type",response_param_type);
			map.put("response_timeout",response_timeout);
			map.put("last_updater",last_updater);
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
   * <em>connect_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getConnectDiv()
  {
    return connect_div;
  }


  /** 
   * <em>connect_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConnectDivIsSet() {
    return connect_div_is_set;
  }


  /** 
   * <em>connect_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConnectDivIsModified() {
    return connect_div_is_modified;
  }


  /** 
   * <em>endpoint_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getEndpointName()
  {
    return endpoint_name;
  }


  /** 
   * <em>endpoint_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEndpointNameIsSet() {
    return endpoint_name_is_set;
  }


  /** 
   * <em>endpoint_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEndpointNameIsModified() {
    return endpoint_name_is_modified;
  }


  /** 
   * <em>target_namespace_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTargetNamespaceName()
  {
    return target_namespace_name;
  }


  /** 
   * <em>target_namespace_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTargetNamespaceNameIsSet() {
    return target_namespace_name_is_set;
  }


  /** 
   * <em>target_namespace_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTargetNamespaceNameIsModified() {
    return target_namespace_name_is_modified;
  }


  /** 
   * <em>service_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getServiceName()
  {
    return service_name;
  }


  /** 
   * <em>service_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getServiceNameIsSet() {
    return service_name_is_set;
  }


  /** 
   * <em>service_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getServiceNameIsModified() {
    return service_name_is_modified;
  }


  /** 
   * <em>port_type_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPortTypeName()
  {
    return port_type_name;
  }


  /** 
   * <em>port_type_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPortTypeNameIsSet() {
    return port_type_name_is_set;
  }


  /** 
   * <em>port_type_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPortTypeNameIsModified() {
    return port_type_name_is_modified;
  }


  /** 
   * <em>operation_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOperationName()
  {
    return operation_name;
  }


  /** 
   * <em>operation_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOperationNameIsSet() {
    return operation_name_is_set;
  }


  /** 
   * <em>operation_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOperationNameIsModified() {
    return operation_name_is_modified;
  }


  /** 
   * <em>parameter_list</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getParameterList()
  {
    return parameter_list;
  }


  /** 
   * <em>parameter_list</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getParameterListIsSet() {
    return parameter_list_is_set;
  }


  /** 
   * <em>parameter_list</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getParameterListIsModified() {
    return parameter_list_is_modified;
  }


  /** 
   * <em>parameter_type_list</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getParameterTypeList()
  {
    return parameter_type_list;
  }


  /** 
   * <em>parameter_type_list</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getParameterTypeListIsSet() {
    return parameter_type_list_is_set;
  }


  /** 
   * <em>parameter_type_list</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getParameterTypeListIsModified() {
    return parameter_type_list_is_modified;
  }


  /** 
   * <em>response_param_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getResponseParamType()
  {
    return response_param_type;
  }


  /** 
   * <em>response_param_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getResponseParamTypeIsSet() {
    return response_param_type_is_set;
  }


  /** 
   * <em>response_param_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getResponseParamTypeIsModified() {
    return response_param_type_is_modified;
  }


  /** 
   * <em>response_timeout</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getResponseTimeout()
  {
    return response_timeout;
  }


  /** 
   * <em>response_timeout</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getResponseTimeoutIsSet() {
    return response_timeout_is_set;
  }


  /** 
   * <em>response_timeout</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getResponseTimeoutIsModified() {
    return response_timeout_is_modified;
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
    return new SoapConnectPrefRpcPK(branch_id, connect_div);
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
   * <em>connect_div</em>カラムの値を設定します。 
   *
   * @@param p_connectDiv <em>connect_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setConnectDiv( String p_connectDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    connect_div = p_connectDiv;
    connect_div_is_set = true;
    connect_div_is_modified = true;
  }


  /** 
   * <em>endpoint_name</em>カラムの値を設定します。 
   *
   * @@param p_endpointName <em>endpoint_name</em>カラムの値をあらわす500桁以下のString値 
   */
  public final void setEndpointName( String p_endpointName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    endpoint_name = p_endpointName;
    endpoint_name_is_set = true;
    endpoint_name_is_modified = true;
  }


  /** 
   * <em>target_namespace_name</em>カラムの値を設定します。 
   *
   * @@param p_targetNamespaceName <em>target_namespace_name</em>カラムの値をあらわす200桁以下のString値 
   */
  public final void setTargetNamespaceName( String p_targetNamespaceName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    target_namespace_name = p_targetNamespaceName;
    target_namespace_name_is_set = true;
    target_namespace_name_is_modified = true;
  }


  /** 
   * <em>service_name</em>カラムの値を設定します。 
   *
   * @@param p_serviceName <em>service_name</em>カラムの値をあらわす200桁以下のString値 
   */
  public final void setServiceName( String p_serviceName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    service_name = p_serviceName;
    service_name_is_set = true;
    service_name_is_modified = true;
  }


  /** 
   * <em>port_type_name</em>カラムの値を設定します。 
   *
   * @@param p_portTypeName <em>port_type_name</em>カラムの値をあらわす200桁以下のString値 
   */
  public final void setPortTypeName( String p_portTypeName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    port_type_name = p_portTypeName;
    port_type_name_is_set = true;
    port_type_name_is_modified = true;
  }


  /** 
   * <em>operation_name</em>カラムの値を設定します。 
   *
   * @@param p_operationName <em>operation_name</em>カラムの値をあらわす200桁以下のString値 
   */
  public final void setOperationName( String p_operationName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    operation_name = p_operationName;
    operation_name_is_set = true;
    operation_name_is_modified = true;
  }


  /** 
   * <em>parameter_list</em>カラムの値を設定します。 
   *
   * @@param p_parameterList <em>parameter_list</em>カラムの値をあらわす1000桁以下のString値 
   */
  public final void setParameterList( String p_parameterList )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    parameter_list = p_parameterList;
    parameter_list_is_set = true;
    parameter_list_is_modified = true;
  }


  /** 
   * <em>parameter_type_list</em>カラムの値を設定します。 
   *
   * @@param p_parameterTypeList <em>parameter_type_list</em>カラムの値をあらわす1000桁以下のString値 
   */
  public final void setParameterTypeList( String p_parameterTypeList )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    parameter_type_list = p_parameterTypeList;
    parameter_type_list_is_set = true;
    parameter_type_list_is_modified = true;
  }


  /** 
   * <em>response_param_type</em>カラムの値を設定します。 
   *
   * @@param p_responseParamType <em>response_param_type</em>カラムの値をあらわす200桁以下のString値 
   */
  public final void setResponseParamType( String p_responseParamType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    response_param_type = p_responseParamType;
    response_param_type_is_set = true;
    response_param_type_is_modified = true;
  }


  /** 
   * <em>response_timeout</em>カラムの値を設定します。 
   *
   * @@param p_responseTimeout <em>response_timeout</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setResponseTimeout( String p_responseTimeout )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    response_timeout = p_responseTimeout;
    response_timeout_is_set = true;
    response_timeout_is_modified = true;
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
            case 'b':
                if ( name.equals("branch_id") ) {
                    return new Long( branch_id );
                }
                break;
            case 'c':
                if ( name.equals("connect_div") ) {
                    return this.connect_div;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("endpoint_name") ) {
                    return this.endpoint_name;
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
                if ( name.equals("operation_name") ) {
                    return this.operation_name;
                }
                break;
            case 'p':
                if ( name.equals("port_type_name") ) {
                    return this.port_type_name;
                }
                else if ( name.equals("parameter_list") ) {
                    return this.parameter_list;
                }
                else if ( name.equals("parameter_type_list") ) {
                    return this.parameter_type_list;
                }
                break;
            case 'r':
                if ( name.equals("response_param_type") ) {
                    return this.response_param_type;
                }
                else if ( name.equals("response_timeout") ) {
                    return this.response_timeout;
                }
                break;
            case 's':
                if ( name.equals("service_name") ) {
                    return this.service_name;
                }
                break;
            case 't':
                if ( name.equals("target_namespace_name") ) {
                    return this.target_namespace_name;
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
                if ( name.equals("connect_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'connect_div' must be String: '"+value+"' is not." );
                    this.connect_div = (String) value;
                    if (this.connect_div_is_set)
                        this.connect_div_is_modified = true;
                    this.connect_div_is_set = true;
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
                if ( name.equals("endpoint_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'endpoint_name' must be String: '"+value+"' is not." );
                    this.endpoint_name = (String) value;
                    if (this.endpoint_name_is_set)
                        this.endpoint_name_is_modified = true;
                    this.endpoint_name_is_set = true;
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
                if ( name.equals("operation_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'operation_name' must be String: '"+value+"' is not." );
                    this.operation_name = (String) value;
                    if (this.operation_name_is_set)
                        this.operation_name_is_modified = true;
                    this.operation_name_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("port_type_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'port_type_name' must be String: '"+value+"' is not." );
                    this.port_type_name = (String) value;
                    if (this.port_type_name_is_set)
                        this.port_type_name_is_modified = true;
                    this.port_type_name_is_set = true;
                    return;
                }
                else if ( name.equals("parameter_list") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'parameter_list' must be String: '"+value+"' is not." );
                    this.parameter_list = (String) value;
                    if (this.parameter_list_is_set)
                        this.parameter_list_is_modified = true;
                    this.parameter_list_is_set = true;
                    return;
                }
                else if ( name.equals("parameter_type_list") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'parameter_type_list' must be String: '"+value+"' is not." );
                    this.parameter_type_list = (String) value;
                    if (this.parameter_type_list_is_set)
                        this.parameter_type_list_is_modified = true;
                    this.parameter_type_list_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("response_param_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'response_param_type' must be String: '"+value+"' is not." );
                    this.response_param_type = (String) value;
                    if (this.response_param_type_is_set)
                        this.response_param_type_is_modified = true;
                    this.response_param_type_is_set = true;
                    return;
                }
                else if ( name.equals("response_timeout") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'response_timeout' must be String: '"+value+"' is not." );
                    this.response_timeout = (String) value;
                    if (this.response_timeout_is_set)
                        this.response_timeout_is_modified = true;
                    this.response_timeout_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("service_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'service_name' must be String: '"+value+"' is not." );
                    this.service_name = (String) value;
                    if (this.service_name_is_set)
                        this.service_name_is_modified = true;
                    this.service_name_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("target_namespace_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'target_namespace_name' must be String: '"+value+"' is not." );
                    this.target_namespace_name = (String) value;
                    if (this.target_namespace_name_is_set)
                        this.target_namespace_name_is_modified = true;
                    this.target_namespace_name_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
