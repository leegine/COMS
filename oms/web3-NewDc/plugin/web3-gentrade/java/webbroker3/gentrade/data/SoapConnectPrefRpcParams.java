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
 * soap_connect_pref_rpc�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link SoapConnectPrefRpcRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link SoapConnectPrefRpcParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link SoapConnectPrefRpcParams}��{@@link SoapConnectPrefRpcRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SoapConnectPrefRpcPK 
 * @@see SoapConnectPrefRpcRow 
 */
public class SoapConnectPrefRpcParams extends Params implements SoapConnectPrefRpcRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "soap_connect_pref_rpc";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = SoapConnectPrefRpcRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return SoapConnectPrefRpcRow.TYPE;
  }


  /** 
   * <em>branch_id</em>�J�����̒l 
   */
  public  long  branch_id;

  /** 
   * <em>connect_div</em>�J�����̒l 
   */
  public  String  connect_div;

  /** 
   * <em>endpoint_name</em>�J�����̒l 
   */
  public  String  endpoint_name;

  /** 
   * <em>target_namespace_name</em>�J�����̒l 
   */
  public  String  target_namespace_name;

  /** 
   * <em>service_name</em>�J�����̒l 
   */
  public  String  service_name;

  /** 
   * <em>port_type_name</em>�J�����̒l 
   */
  public  String  port_type_name;

  /** 
   * <em>operation_name</em>�J�����̒l 
   */
  public  String  operation_name;

  /** 
   * <em>parameter_list</em>�J�����̒l 
   */
  public  String  parameter_list;

  /** 
   * <em>parameter_type_list</em>�J�����̒l 
   */
  public  String  parameter_type_list;

  /** 
   * <em>response_param_type</em>�J�����̒l 
   */
  public  String  response_param_type;

  /** 
   * <em>response_timeout</em>�J�����̒l 
   */
  public  String  response_timeout;

  /** 
   * <em>last_updater</em>�J�����̒l 
   */
  public  String  last_updater;

  /** 
   * <em>created_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>�J�����̒l 
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
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
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
   * �l�����ݒ��SoapConnectPrefRpcParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public SoapConnectPrefRpcParams() {
    branch_id_is_modified = true;
    connect_div_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���SoapConnectPrefRpcRow�I�u�W�F�N�g�̒l�𗘗p����SoapConnectPrefRpcParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����SoapConnectPrefRpcRow�I�u�W�F�N�g 
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
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof SoapConnectPrefRpcRow ) )
       return false;
    return fieldsEqual( (SoapConnectPrefRpcRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�SoapConnectPrefRpcRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
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
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int�B���ɎZ�o���ۑ����ꂽ���̂�Ԃ��ꍇ���� 
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
   * <em>branch_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getBranchId()
  {
    return branch_id;
  }


  /** 
   * <em>branch_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBranchIdIsSet() {
    return branch_id_is_set;
  }


  /** 
   * <em>branch_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBranchIdIsModified() {
    return branch_id_is_modified;
  }


  /** 
   * <em>connect_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getConnectDiv()
  {
    return connect_div;
  }


  /** 
   * <em>connect_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getConnectDivIsSet() {
    return connect_div_is_set;
  }


  /** 
   * <em>connect_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getConnectDivIsModified() {
    return connect_div_is_modified;
  }


  /** 
   * <em>endpoint_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getEndpointName()
  {
    return endpoint_name;
  }


  /** 
   * <em>endpoint_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEndpointNameIsSet() {
    return endpoint_name_is_set;
  }


  /** 
   * <em>endpoint_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getEndpointNameIsModified() {
    return endpoint_name_is_modified;
  }


  /** 
   * <em>target_namespace_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getTargetNamespaceName()
  {
    return target_namespace_name;
  }


  /** 
   * <em>target_namespace_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTargetNamespaceNameIsSet() {
    return target_namespace_name_is_set;
  }


  /** 
   * <em>target_namespace_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getTargetNamespaceNameIsModified() {
    return target_namespace_name_is_modified;
  }


  /** 
   * <em>service_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getServiceName()
  {
    return service_name;
  }


  /** 
   * <em>service_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getServiceNameIsSet() {
    return service_name_is_set;
  }


  /** 
   * <em>service_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getServiceNameIsModified() {
    return service_name_is_modified;
  }


  /** 
   * <em>port_type_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getPortTypeName()
  {
    return port_type_name;
  }


  /** 
   * <em>port_type_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPortTypeNameIsSet() {
    return port_type_name_is_set;
  }


  /** 
   * <em>port_type_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPortTypeNameIsModified() {
    return port_type_name_is_modified;
  }


  /** 
   * <em>operation_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getOperationName()
  {
    return operation_name;
  }


  /** 
   * <em>operation_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOperationNameIsSet() {
    return operation_name_is_set;
  }


  /** 
   * <em>operation_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOperationNameIsModified() {
    return operation_name_is_modified;
  }


  /** 
   * <em>parameter_list</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getParameterList()
  {
    return parameter_list;
  }


  /** 
   * <em>parameter_list</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getParameterListIsSet() {
    return parameter_list_is_set;
  }


  /** 
   * <em>parameter_list</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getParameterListIsModified() {
    return parameter_list_is_modified;
  }


  /** 
   * <em>parameter_type_list</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getParameterTypeList()
  {
    return parameter_type_list;
  }


  /** 
   * <em>parameter_type_list</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getParameterTypeListIsSet() {
    return parameter_type_list_is_set;
  }


  /** 
   * <em>parameter_type_list</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getParameterTypeListIsModified() {
    return parameter_type_list_is_modified;
  }


  /** 
   * <em>response_param_type</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getResponseParamType()
  {
    return response_param_type;
  }


  /** 
   * <em>response_param_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getResponseParamTypeIsSet() {
    return response_param_type_is_set;
  }


  /** 
   * <em>response_param_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getResponseParamTypeIsModified() {
    return response_param_type_is_modified;
  }


  /** 
   * <em>response_timeout</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getResponseTimeout()
  {
    return response_timeout;
  }


  /** 
   * <em>response_timeout</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getResponseTimeoutIsSet() {
    return response_timeout_is_set;
  }


  /** 
   * <em>response_timeout</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getResponseTimeoutIsModified() {
    return response_timeout_is_modified;
  }


  /** 
   * <em>last_updater</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getLastUpdater()
  {
    return last_updater;
  }


  /** 
   * <em>last_updater</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLastUpdaterIsSet() {
    return last_updater_is_set;
  }


  /** 
   * <em>last_updater</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLastUpdaterIsModified() {
    return last_updater_is_modified;
  }


  /** 
   * <em>created_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getCreatedTimestamp()
  {
    return created_timestamp;
  }


  /** 
   * <em>created_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCreatedTimestampIsSet() {
    return created_timestamp_is_set;
  }


  /** 
   * <em>created_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCreatedTimestampIsModified() {
    return created_timestamp_is_modified;
  }


  /** 
   * <em>last_updated_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getLastUpdatedTimestamp()
  {
    return last_updated_timestamp;
  }


  /** 
   * <em>last_updated_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLastUpdatedTimestampIsSet() {
    return last_updated_timestamp_is_set;
  }


  /** 
   * <em>last_updated_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
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
   * <em>branch_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_branchId <em>branch_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setBranchId( long p_branchId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_id = p_branchId;
    branch_id_is_set = true;
    branch_id_is_modified = true;
  }


  /** 
   * <em>connect_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_connectDiv <em>connect_div</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setConnectDiv( String p_connectDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    connect_div = p_connectDiv;
    connect_div_is_set = true;
    connect_div_is_modified = true;
  }


  /** 
   * <em>endpoint_name</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_endpointName <em>endpoint_name</em>�J�����̒l������킷500���ȉ���String�l 
   */
  public final void setEndpointName( String p_endpointName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    endpoint_name = p_endpointName;
    endpoint_name_is_set = true;
    endpoint_name_is_modified = true;
  }


  /** 
   * <em>target_namespace_name</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_targetNamespaceName <em>target_namespace_name</em>�J�����̒l������킷200���ȉ���String�l 
   */
  public final void setTargetNamespaceName( String p_targetNamespaceName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    target_namespace_name = p_targetNamespaceName;
    target_namespace_name_is_set = true;
    target_namespace_name_is_modified = true;
  }


  /** 
   * <em>service_name</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_serviceName <em>service_name</em>�J�����̒l������킷200���ȉ���String�l 
   */
  public final void setServiceName( String p_serviceName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    service_name = p_serviceName;
    service_name_is_set = true;
    service_name_is_modified = true;
  }


  /** 
   * <em>port_type_name</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_portTypeName <em>port_type_name</em>�J�����̒l������킷200���ȉ���String�l 
   */
  public final void setPortTypeName( String p_portTypeName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    port_type_name = p_portTypeName;
    port_type_name_is_set = true;
    port_type_name_is_modified = true;
  }


  /** 
   * <em>operation_name</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_operationName <em>operation_name</em>�J�����̒l������킷200���ȉ���String�l 
   */
  public final void setOperationName( String p_operationName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    operation_name = p_operationName;
    operation_name_is_set = true;
    operation_name_is_modified = true;
  }


  /** 
   * <em>parameter_list</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_parameterList <em>parameter_list</em>�J�����̒l������킷1000���ȉ���String�l 
   */
  public final void setParameterList( String p_parameterList )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    parameter_list = p_parameterList;
    parameter_list_is_set = true;
    parameter_list_is_modified = true;
  }


  /** 
   * <em>parameter_type_list</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_parameterTypeList <em>parameter_type_list</em>�J�����̒l������킷1000���ȉ���String�l 
   */
  public final void setParameterTypeList( String p_parameterTypeList )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    parameter_type_list = p_parameterTypeList;
    parameter_type_list_is_set = true;
    parameter_type_list_is_modified = true;
  }


  /** 
   * <em>response_param_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_responseParamType <em>response_param_type</em>�J�����̒l������킷200���ȉ���String�l 
   */
  public final void setResponseParamType( String p_responseParamType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    response_param_type = p_responseParamType;
    response_param_type_is_set = true;
    response_param_type_is_modified = true;
  }


  /** 
   * <em>response_timeout</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_responseTimeout <em>response_timeout</em>�J�����̒l������킷10���ȉ���String�l 
   */
  public final void setResponseTimeout( String p_responseTimeout )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    response_timeout = p_responseTimeout;
    response_timeout_is_set = true;
    response_timeout_is_modified = true;
  }


  /** 
   * <em>last_updater</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_lastUpdater <em>last_updater</em>�J�����̒l������킷20���ȉ���String�l 
   */
  public final void setLastUpdater( String p_lastUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updater = p_lastUpdater;
    last_updater_is_set = true;
    last_updater_is_modified = true;
  }


  /** 
   * <em>created_timestamp</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_createdTimestamp <em>created_timestamp</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setCreatedTimestamp( java.sql.Timestamp p_createdTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = p_createdTimestamp;
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>created_timestamp</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setCreatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


  /** 
   * <em>last_updated_timestamp</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_lastUpdatedTimestamp <em>last_updated_timestamp</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setLastUpdatedTimestamp( java.sql.Timestamp p_lastUpdatedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = p_lastUpdatedTimestamp;
    last_updated_timestamp_is_set = true;
    last_updated_timestamp_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>last_updated_timestamp</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
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
