head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	HostManagementParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.dirsec.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * host_management�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link HostManagementRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link HostManagementParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link HostManagementParams}��{@@link HostManagementRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see HostManagementPK 
 * @@see HostManagementRow 
 */
public class HostManagementParams extends Params implements HostManagementRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "host_management";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = HostManagementRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return HostManagementRow.TYPE;
  }


  /** 
   * <em>host_table_name</em>�J�����̒l 
   */
  public  String  host_table_name;

  /** 
   * <em>host_table_physics_name</em>�J�����̒l 
   */
  public  String  host_table_physics_name;

  /** 
   * <em>order_request_number_div</em>�J�����̒l 
   */
  public  String  order_request_number_div;

  /** 
   * <em>created_timestamp_div</em>�J�����̒l 
   */
  public  String  created_timestamp_div;

  /** 
   * <em>queryprocessor_name</em>�J�����̒l 
   */
  public  String  queryprocessor_name;

  /** 
   * <em>created_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean host_table_name_is_set = false;
  private boolean host_table_name_is_modified = false;
  private boolean host_table_physics_name_is_set = false;
  private boolean host_table_physics_name_is_modified = false;
  private boolean order_request_number_div_is_set = false;
  private boolean order_request_number_div_is_modified = false;
  private boolean created_timestamp_div_is_set = false;
  private boolean created_timestamp_div_is_modified = false;
  private boolean queryprocessor_name_is_set = false;
  private boolean queryprocessor_name_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "host_table_name=" + host_table_name
      + "," + "host_table_physics_name=" +host_table_physics_name
      + "," + "order_request_number_div=" +order_request_number_div
      + "," + "created_timestamp_div=" +created_timestamp_div
      + "," + "queryprocessor_name=" +queryprocessor_name
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��HostManagementParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public HostManagementParams() {
    host_table_name_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���HostManagementRow�I�u�W�F�N�g�̒l�𗘗p����HostManagementParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����HostManagementRow�I�u�W�F�N�g 
   */
  public HostManagementParams( HostManagementRow p_row )
  {
    host_table_name = p_row.getHostTableName();
    host_table_name_is_set = p_row.getHostTableNameIsSet();
    host_table_name_is_modified = p_row.getHostTableNameIsModified();
    host_table_physics_name = p_row.getHostTablePhysicsName();
    host_table_physics_name_is_set = p_row.getHostTablePhysicsNameIsSet();
    host_table_physics_name_is_modified = p_row.getHostTablePhysicsNameIsModified();
    order_request_number_div = p_row.getOrderRequestNumberDiv();
    order_request_number_div_is_set = p_row.getOrderRequestNumberDivIsSet();
    order_request_number_div_is_modified = p_row.getOrderRequestNumberDivIsModified();
    created_timestamp_div = p_row.getCreatedTimestampDiv();
    created_timestamp_div_is_set = p_row.getCreatedTimestampDivIsSet();
    created_timestamp_div_is_modified = p_row.getCreatedTimestampDivIsModified();
    queryprocessor_name = p_row.getQueryprocessorName();
    queryprocessor_name_is_set = p_row.getQueryprocessorNameIsSet();
    queryprocessor_name_is_modified = p_row.getQueryprocessorNameIsModified();
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
      host_table_physics_name_is_set = true;
      host_table_physics_name_is_modified = true;
      order_request_number_div_is_set = true;
      order_request_number_div_is_modified = true;
      created_timestamp_div_is_set = true;
      created_timestamp_div_is_modified = true;
      queryprocessor_name_is_set = true;
      queryprocessor_name_is_modified = true;
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
    if ( !( other instanceof HostManagementRow ) )
       return false;
    return fieldsEqual( (HostManagementRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�HostManagementRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( HostManagementRow row )
  {
    if ( host_table_name == null ) {
      if ( row.getHostTableName() != null )
        return false;
    } else if ( !host_table_name.equals( row.getHostTableName() ) ) {
        return false;
    }
    if ( host_table_physics_name == null ) {
      if ( row.getHostTablePhysicsName() != null )
        return false;
    } else if ( !host_table_physics_name.equals( row.getHostTablePhysicsName() ) ) {
        return false;
    }
    if ( order_request_number_div == null ) {
      if ( row.getOrderRequestNumberDiv() != null )
        return false;
    } else if ( !order_request_number_div.equals( row.getOrderRequestNumberDiv() ) ) {
        return false;
    }
    if ( created_timestamp_div == null ) {
      if ( row.getCreatedTimestampDiv() != null )
        return false;
    } else if ( !created_timestamp_div.equals( row.getCreatedTimestampDiv() ) ) {
        return false;
    }
    if ( queryprocessor_name == null ) {
      if ( row.getQueryprocessorName() != null )
        return false;
    } else if ( !queryprocessor_name.equals( row.getQueryprocessorName() ) ) {
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
      return  (host_table_name!=null? host_table_name.hashCode(): 0) 
        + (host_table_physics_name!=null? host_table_physics_name.hashCode(): 0) 
        + (order_request_number_div!=null? order_request_number_div.hashCode(): 0) 
        + (created_timestamp_div!=null? created_timestamp_div.hashCode(): 0) 
        + (queryprocessor_name!=null? queryprocessor_name.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !host_table_physics_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'host_table_physics_name' must be set before inserting.");
		if ( !order_request_number_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_request_number_div' must be set before inserting.");
		if ( !created_timestamp_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'created_timestamp_div' must be set before inserting.");
		if ( !queryprocessor_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'queryprocessor_name' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("host_table_name",host_table_name);
		map.put("host_table_physics_name",host_table_physics_name);
		map.put("order_request_number_div",order_request_number_div);
		map.put("created_timestamp_div",created_timestamp_div);
		map.put("queryprocessor_name",queryprocessor_name);
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
		if ( host_table_physics_name_is_modified )
			map.put("host_table_physics_name",host_table_physics_name);
		if ( order_request_number_div_is_modified )
			map.put("order_request_number_div",order_request_number_div);
		if ( created_timestamp_div_is_modified )
			map.put("created_timestamp_div",created_timestamp_div);
		if ( queryprocessor_name_is_modified )
			map.put("queryprocessor_name",queryprocessor_name);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( host_table_physics_name_is_set )
				map.put("host_table_physics_name",host_table_physics_name);
			if ( order_request_number_div_is_set )
				map.put("order_request_number_div",order_request_number_div);
			if ( created_timestamp_div_is_set )
				map.put("created_timestamp_div",created_timestamp_div);
			if ( queryprocessor_name_is_set )
				map.put("queryprocessor_name",queryprocessor_name);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>host_table_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getHostTableName()
  {
    return host_table_name;
  }


  /** 
   * <em>host_table_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getHostTableNameIsSet() {
    return host_table_name_is_set;
  }


  /** 
   * <em>host_table_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getHostTableNameIsModified() {
    return host_table_name_is_modified;
  }


  /** 
   * <em>host_table_physics_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getHostTablePhysicsName()
  {
    return host_table_physics_name;
  }


  /** 
   * <em>host_table_physics_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getHostTablePhysicsNameIsSet() {
    return host_table_physics_name_is_set;
  }


  /** 
   * <em>host_table_physics_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getHostTablePhysicsNameIsModified() {
    return host_table_physics_name_is_modified;
  }


  /** 
   * <em>order_request_number_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getOrderRequestNumberDiv()
  {
    return order_request_number_div;
  }


  /** 
   * <em>order_request_number_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderRequestNumberDivIsSet() {
    return order_request_number_div_is_set;
  }


  /** 
   * <em>order_request_number_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOrderRequestNumberDivIsModified() {
    return order_request_number_div_is_modified;
  }


  /** 
   * <em>created_timestamp_div</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCreatedTimestampDiv()
  {
    return created_timestamp_div;
  }


  /** 
   * <em>created_timestamp_div</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCreatedTimestampDivIsSet() {
    return created_timestamp_div_is_set;
  }


  /** 
   * <em>created_timestamp_div</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCreatedTimestampDivIsModified() {
    return created_timestamp_div_is_modified;
  }


  /** 
   * <em>queryprocessor_name</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getQueryprocessorName()
  {
    return queryprocessor_name;
  }


  /** 
   * <em>queryprocessor_name</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getQueryprocessorNameIsSet() {
    return queryprocessor_name_is_set;
  }


  /** 
   * <em>queryprocessor_name</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getQueryprocessorNameIsModified() {
    return queryprocessor_name_is_modified;
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
    return new HostManagementPK(host_table_name);
  }


  /** 
   * <em>host_table_name</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_hostTableName <em>host_table_name</em>�J�����̒l������킷60���ȉ���String�l 
   */
  public final void setHostTableName( String p_hostTableName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    host_table_name = p_hostTableName;
    host_table_name_is_set = true;
    host_table_name_is_modified = true;
  }


  /** 
   * <em>host_table_physics_name</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_hostTablePhysicsName <em>host_table_physics_name</em>�J�����̒l������킷60���ȉ���String�l 
   */
  public final void setHostTablePhysicsName( String p_hostTablePhysicsName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    host_table_physics_name = p_hostTablePhysicsName;
    host_table_physics_name_is_set = true;
    host_table_physics_name_is_modified = true;
  }


  /** 
   * <em>order_request_number_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_orderRequestNumberDiv <em>order_request_number_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setOrderRequestNumberDiv( String p_orderRequestNumberDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_request_number_div = p_orderRequestNumberDiv;
    order_request_number_div_is_set = true;
    order_request_number_div_is_modified = true;
  }


  /** 
   * <em>created_timestamp_div</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_createdTimestampDiv <em>created_timestamp_div</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setCreatedTimestampDiv( String p_createdTimestampDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp_div = p_createdTimestampDiv;
    created_timestamp_div_is_set = true;
    created_timestamp_div_is_modified = true;
  }


  /** 
   * <em>queryprocessor_name</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_queryprocessorName <em>queryprocessor_name</em>�J�����̒l������킷10���ȉ���String�l 
   */
  public final void setQueryprocessorName( String p_queryprocessorName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    queryprocessor_name = p_queryprocessorName;
    queryprocessor_name_is_set = true;
    queryprocessor_name_is_modified = true;
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
            case 'c':
                if ( name.equals("created_timestamp_div") ) {
                    return this.created_timestamp_div;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'h':
                if ( name.equals("host_table_name") ) {
                    return this.host_table_name;
                }
                else if ( name.equals("host_table_physics_name") ) {
                    return this.host_table_physics_name;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'o':
                if ( name.equals("order_request_number_div") ) {
                    return this.order_request_number_div;
                }
                break;
            case 'q':
                if ( name.equals("queryprocessor_name") ) {
                    return this.queryprocessor_name;
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
                if ( name.equals("created_timestamp_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'created_timestamp_div' must be String: '"+value+"' is not." );
                    this.created_timestamp_div = (String) value;
                    if (this.created_timestamp_div_is_set)
                        this.created_timestamp_div_is_modified = true;
                    this.created_timestamp_div_is_set = true;
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
            case 'h':
                if ( name.equals("host_table_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'host_table_name' must be String: '"+value+"' is not." );
                    this.host_table_name = (String) value;
                    if (this.host_table_name_is_set)
                        this.host_table_name_is_modified = true;
                    this.host_table_name_is_set = true;
                    return;
                }
                else if ( name.equals("host_table_physics_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'host_table_physics_name' must be String: '"+value+"' is not." );
                    this.host_table_physics_name = (String) value;
                    if (this.host_table_physics_name_is_set)
                        this.host_table_physics_name_is_modified = true;
                    this.host_table_physics_name_is_set = true;
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
            case 'o':
                if ( name.equals("order_request_number_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_request_number_div' must be String: '"+value+"' is not." );
                    this.order_request_number_div = (String) value;
                    if (this.order_request_number_div_is_set)
                        this.order_request_number_div_is_modified = true;
                    this.order_request_number_div_is_set = true;
                    return;
                }
                break;
            case 'q':
                if ( name.equals("queryprocessor_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'queryprocessor_name' must be String: '"+value+"' is not." );
                    this.queryprocessor_name = (String) value;
                    if (this.queryprocessor_name_is_set)
                        this.queryprocessor_name_is_modified = true;
                    this.queryprocessor_name_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
