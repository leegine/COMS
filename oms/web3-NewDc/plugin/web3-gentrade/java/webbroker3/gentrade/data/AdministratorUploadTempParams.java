head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.25.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AdministratorUploadTempParams.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * administrator_upload_temp�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link AdministratorUploadTempRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link AdministratorUploadTempParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link AdministratorUploadTempParams}��{@@link AdministratorUploadTempRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AdministratorUploadTempPK 
 * @@see AdministratorUploadTempRow 
 */
public class AdministratorUploadTempParams extends Params implements AdministratorUploadTempRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "administrator_upload_temp";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = AdministratorUploadTempRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return AdministratorUploadTempRow.TYPE;
  }


  /** 
   * <em>administrator_upload_id</em>�J�����̒l 
   */
  public  long  administrator_upload_id;

  /** 
   * <em>line_number</em>�J�����̒l 
   */
  public  int  line_number;

  /** 
   * <em>csv_line_value</em>�J�����̒l 
   */
  public  String  csv_line_value;

  /** 
   * <em>update_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  update_timestamp;

  private boolean administrator_upload_id_is_set = false;
  private boolean administrator_upload_id_is_modified = false;
  private boolean line_number_is_set = false;
  private boolean line_number_is_modified = false;
  private boolean csv_line_value_is_set = false;
  private boolean csv_line_value_is_modified = false;
  private boolean update_timestamp_is_set = false;
  private boolean update_timestamp_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "administrator_upload_id=" + administrator_upload_id
      + "," + "line_number=" + line_number
      + "," + "csv_line_value=" +csv_line_value
      + "," + "update_timestamp=" +update_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��AdministratorUploadTempParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public AdministratorUploadTempParams() {
    administrator_upload_id_is_modified = true;
    line_number_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���AdministratorUploadTempRow�I�u�W�F�N�g�̒l�𗘗p����AdministratorUploadTempParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����AdministratorUploadTempRow�I�u�W�F�N�g 
   */
  public AdministratorUploadTempParams( AdministratorUploadTempRow p_row )
  {
    administrator_upload_id = p_row.getAdministratorUploadId();
    administrator_upload_id_is_set = p_row.getAdministratorUploadIdIsSet();
    administrator_upload_id_is_modified = p_row.getAdministratorUploadIdIsModified();
    line_number = p_row.getLineNumber();
    line_number_is_set = p_row.getLineNumberIsSet();
    line_number_is_modified = p_row.getLineNumberIsModified();
    csv_line_value = p_row.getCsvLineValue();
    csv_line_value_is_set = p_row.getCsvLineValueIsSet();
    csv_line_value_is_modified = p_row.getCsvLineValueIsModified();
    update_timestamp = p_row.getUpdateTimestamp();
    update_timestamp_is_set = p_row.getUpdateTimestampIsSet();
    update_timestamp_is_modified = p_row.getUpdateTimestampIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      csv_line_value_is_set = true;
      csv_line_value_is_modified = true;
      update_timestamp_is_set = true;
      update_timestamp_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof AdministratorUploadTempRow ) )
       return false;
    return fieldsEqual( (AdministratorUploadTempRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�AdministratorUploadTempRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( AdministratorUploadTempRow row )
  {
    if ( administrator_upload_id != row.getAdministratorUploadId() )
      return false;
    if ( line_number != row.getLineNumber() )
      return false;
    if ( csv_line_value == null ) {
      if ( row.getCsvLineValue() != null )
        return false;
    } else if ( !csv_line_value.equals( row.getCsvLineValue() ) ) {
        return false;
    }
    if ( update_timestamp == null ) {
      if ( row.getUpdateTimestamp() != null )
        return false;
    } else if ( !update_timestamp.equals( row.getUpdateTimestamp() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int�B���ɎZ�o���ۑ����ꂽ���̂�Ԃ��ꍇ���� 
   */
  public int hashCode() {
      return  ((int) administrator_upload_id)
        + ((int) line_number)
        + (csv_line_value!=null? csv_line_value.hashCode(): 0) 
        + (update_timestamp!=null? update_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !csv_line_value_is_set )
			throw new IllegalArgumentException("non-nullable field 'csv_line_value' must be set before inserting.");
		if ( !update_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'update_timestamp' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("administrator_upload_id",new Long(administrator_upload_id));
		map.put("line_number",new Integer(line_number));
		map.put("csv_line_value",csv_line_value);
		map.put("update_timestamp",update_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( csv_line_value_is_modified )
			map.put("csv_line_value",csv_line_value);
		if ( update_timestamp_is_modified )
			map.put("update_timestamp",update_timestamp);
		if (map.size() == 0) {
			if ( csv_line_value_is_set )
				map.put("csv_line_value",csv_line_value);
			if ( update_timestamp_is_set )
				map.put("update_timestamp",update_timestamp);
		}
		return map;
	}


  /** 
   * <em>administrator_upload_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getAdministratorUploadId()
  {
    return administrator_upload_id;
  }


  /** 
   * <em>administrator_upload_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAdministratorUploadIdIsSet() {
    return administrator_upload_id_is_set;
  }


  /** 
   * <em>administrator_upload_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAdministratorUploadIdIsModified() {
    return administrator_upload_id_is_modified;
  }


  /** 
   * <em>line_number</em>�J�����̒l���擾���܂��B
   * @@return int�̒l 
   */
  public final int getLineNumber()
  {
    return line_number;
  }


  /** 
   * <em>line_number</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLineNumberIsSet() {
    return line_number_is_set;
  }


  /** 
   * <em>line_number</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLineNumberIsModified() {
    return line_number_is_modified;
  }


  /** 
   * <em>csv_line_value</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCsvLineValue()
  {
    return csv_line_value;
  }


  /** 
   * <em>csv_line_value</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCsvLineValueIsSet() {
    return csv_line_value_is_set;
  }


  /** 
   * <em>csv_line_value</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCsvLineValueIsModified() {
    return csv_line_value_is_modified;
  }


  /** 
   * <em>update_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getUpdateTimestamp()
  {
    return update_timestamp;
  }


  /** 
   * <em>update_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getUpdateTimestampIsSet() {
    return update_timestamp_is_set;
  }


  /** 
   * <em>update_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getUpdateTimestampIsModified() {
    return update_timestamp_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new AdministratorUploadTempPK(administrator_upload_id, line_number);
  }


  /** 
   * <em>administrator_upload_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_administratorUploadId <em>administrator_upload_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setAdministratorUploadId( long p_administratorUploadId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    administrator_upload_id = p_administratorUploadId;
    administrator_upload_id_is_set = true;
    administrator_upload_id_is_modified = true;
  }


  /** 
   * <em>line_number</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_lineNumber <em>line_number</em>�J�����̒l������킷6���ȉ���int�l 
   */
  public final void setLineNumber( int p_lineNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    line_number = p_lineNumber;
    line_number_is_set = true;
    line_number_is_modified = true;
  }


  /** 
   * <em>csv_line_value</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_csvLineValue <em>csv_line_value</em>�J�����̒l������킷1500���ȉ���String�l 
   */
  public final void setCsvLineValue( String p_csvLineValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    csv_line_value = p_csvLineValue;
    csv_line_value_is_set = true;
    csv_line_value_is_modified = true;
  }


  /** 
   * <em>update_timestamp</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_updateTimestamp <em>update_timestamp</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setUpdateTimestamp( java.sql.Timestamp p_updateTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    update_timestamp = p_updateTimestamp;
    update_timestamp_is_set = true;
    update_timestamp_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>update_timestamp</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setUpdateTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    update_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    update_timestamp_is_set = true;
    update_timestamp_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("administrator_upload_id") ) {
                    return new Long( administrator_upload_id );
                }
                break;
            case 'c':
                if ( name.equals("csv_line_value") ) {
                    return this.csv_line_value;
                }
                break;
            case 'l':
                if ( name.equals("line_number") ) {
                    return new Integer( line_number );
                }
                break;
            case 'u':
                if ( name.equals("update_timestamp") ) {
                    return this.update_timestamp;
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
                if ( name.equals("administrator_upload_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'administrator_upload_id' must be Long: '"+value+"' is not." );
                    this.administrator_upload_id = ((Long) value).longValue();
                    if (this.administrator_upload_id_is_set)
                        this.administrator_upload_id_is_modified = true;
                    this.administrator_upload_id_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("csv_line_value") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'csv_line_value' must be String: '"+value+"' is not." );
                    this.csv_line_value = (String) value;
                    if (this.csv_line_value_is_set)
                        this.csv_line_value_is_modified = true;
                    this.csv_line_value_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("line_number") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'line_number' must be Integer: '"+value+"' is not." );
                    this.line_number = ((Integer) value).intValue();
                    if (this.line_number_is_set)
                        this.line_number_is_modified = true;
                    this.line_number_is_set = true;
                    return;
                }
                break;
            case 'u':
                if ( name.equals("update_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'update_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.update_timestamp = (java.sql.Timestamp) value;
                    if (this.update_timestamp_is_set)
                        this.update_timestamp_is_modified = true;
                    this.update_timestamp_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
